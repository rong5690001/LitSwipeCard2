package com.rong.litswipecard.cardstack.swipe

import android.graphics.PointF
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.animation.SwipeLeftAnimation
import com.rong.litswipecard.cardstack.animation.SwipeRightAnimation
import com.rong.litswipecard.cardstack.animation.SwipeUpAnimation
import com.rong.litswipecard.cardstack.animation.interpolator.SwipeOutInterpolator
import com.rong.litswipecard.cardstack.cardstack.CardStackSwipeHelper
import com.rong.litswipecard.cardstack.model.Direction
import com.rong.litswipecard.cardstack.model.SwipeDirection
import com.rong.litswipecard.cardstack.view.CardViewHolder
import timber.log.Timber
import kotlin.math.abs
import kotlin.math.max

/**
 * 卡片项触摸监听器
 * 负责处理卡片的触摸事件、滑动判断和动画
 */
abstract class CardItemTouchListener
/**
 * 构造函数
 * @param swipeThresholdDetector 滑动阈值检测器
 * @param cardAnimator 卡片动画控制器
 * @param cardItemTouchHelperUtil 触摸辅助工具
 */(
    private val swipeThresholdDetector: SwipeThresholdDetector,
    private val cardAnimator: CardAnimator,
    private val touchHelperUtil: CardItemTouchHelperUtil
) : RecyclerView.OnItemTouchListener {
    /**
     * 获取当前活动的触摸指针
     * @return 触摸指针，可能为null
     */
    var activeTouchPointer: TouchPointer? = null
        protected set
    private val touchDelta: PointF = PointF()
    private var preSwipeListener = DEFAULT_PRE_SWIPE_LISTENER
    private var velocityTracker: VelocityTracker? = null

    /**
     * 默认预滑动监听器实现
     * 默认不阻止任何滑动方向
     */
    private class DefaultPreSwipeListener : CardStackSwipeHelper.OnPreSwipeListener {
        override fun onPreSwipe(position: Int, swipeDirection: SwipeDirection): Boolean {
            return false // 默认不阻止任何滑动
        }
    }

    /**
     * 用于枚举方向的辅助类
     */
    private object SwipeDirectionOrdinalMap {
        val DIRECTION_ORDINALS: IntArray

        init {
            val directions = IntArray(SwipeDirection.entries.toTypedArray().size)
            DIRECTION_ORDINALS = directions
            try {
                directions[SwipeDirection.LEFT.ordinal] = 1
            } catch (unused: NoSuchFieldError) {
            }
            try {
                DIRECTION_ORDINALS[SwipeDirection.RIGHT.ordinal] = 2
            } catch (unused2: NoSuchFieldError) {
            }
            try {
                DIRECTION_ORDINALS[SwipeDirection.UP.ordinal] = 3
            } catch (unused3: NoSuchFieldError) {
            }
        }
    }

    /**
     * 允许父视图拦截触摸事件
     */
    private fun allowParentTouchIntercept() {
        recyclerView.requestDisallowInterceptTouchEvent(false)
    }

    /**
     * 计算触摸移动的增量
     * @param deltaPoint 存储增量结果的点
     * @param motionEvent 触摸事件
     * @param pointerIndex 指针索引
     */
    private fun calculateTouchDelta(deltaPoint: PointF, motionEvent: MotionEvent, pointerIndex: Int) {
        Timber.d("calculateTouchDelta: 开始计算触摸增量")
        if (this.activeTouchPointer == null) {
            Timber.d("calculateTouchDelta: 活动触摸指针为空，设置增量为0")
            deltaPoint.set(0.0f, 0.0f)
            return
        }
        val x: Float = motionEvent.getX(pointerIndex)
        val y: Float = motionEvent.getY(pointerIndex)
        val deltaX = x - activeTouchPointer!!.startX
        val deltaY = y - activeTouchPointer!!.startY
        Timber.d("calculateTouchDelta: x=%.2f, y=%.2f, deltaX=%.2f, deltaY=%.2f", x, y, deltaX, deltaY)
        
        if (activeTouchPointer!!.dragX != 0.0f || activeTouchPointer!!.dragY != 0.0f) {
            Timber.d("calculateTouchDelta: 已有拖拽值，设置增量为 deltaX=%.2f, deltaY=%.2f", deltaX, deltaY)
            deltaPoint.set(deltaX, deltaY)
        } else if (swipeThresholdDetector.isBelowThreshold(deltaX, deltaY)) {
            Timber.d("calculateTouchDelta: 低于阈值，设置增量为0")
            deltaPoint.set(0.0f, 0.0f)
        } else {
            Timber.d("calculateTouchDelta: 更新触摸起始点并设置小增量")
            activeTouchPointer!!.updateTouchStartPoint(x, y)
            deltaPoint.set(0.1f, 0.1f)
        }
    }

    /**
     * 检查视图持有者是否可以向下拖动
     * @param viewHolder 视图持有者
     * @return 是否可以向下拖动
     */
    private fun canDragDown(viewHolder: RecyclerView.ViewHolder): Boolean {
        if (viewHolder is CardViewHolder<*>) {
            return (viewHolder as CardViewHolder<*>).canDragCard(Direction.DOWN)
        }
        return true
    }

    /**
     * 检查视图持有者是否可以向上拖动
     * @param viewHolder 视图持有者
     * @return 是否可以向上拖动
     */
    private fun canDragUp(viewHolder: RecyclerView.ViewHolder): Boolean {
        if (viewHolder is CardViewHolder<*>) {
            return viewHolder.canDragCard(Direction.UP)
        }
        return true
    }

    /**
     * 发送取消触摸事件
     * @param view 目标视图
     * @param motionEvent 原始触摸事件
     */
    private fun dispatchCancelEvent(view: View, motionEvent: MotionEvent) {
        val obtain: MotionEvent = MotionEvent.obtain(motionEvent)
        obtain.setAction(MotionEvent.ACTION_CANCEL)
        view.dispatchTouchEvent(obtain)
        obtain.recycle()
    }

    /**
     * 发送触摸结束事件
     * @param view 目标视图
     * @param motionEvent 原始触摸事件
     */
    private fun dispatchTouchEndEvent(view: View, motionEvent: MotionEvent) {
        val actionMasked: Int = MotionEventCompat.getActionMasked(motionEvent)
        if (actionMasked != MotionEvent.ACTION_UP && actionMasked != MotionEvent.ACTION_CANCEL) {
            motionEvent.setAction(MotionEvent.ACTION_UP)
        }
        view.dispatchTouchEvent(motionEvent)
    }

    /**
     * 执行卡片甩出动画
     * @param touchPointer 触摸指针
     */
    private fun performSwipeOutAnimation(touchPointer: TouchPointer) {
        Timber.d("performSwipeOutAnimation: start, viewHolder position=%d", touchPointer.viewHolder.adapterPosition)
        val endX: Float
        val deltaX: Float
        val finalY: Float
        val velocityX: Float
        val absEndX: Float
        val finalX: Float
        val velocityY: Float
        val absAnimDistance: Float
        val finalTranslationY: Float

        val dragX = touchPointer.dragX
        val dragY = touchPointer.dragY
        val viewHolder: RecyclerView.ViewHolder = touchPointer.viewHolder
        val view: View = viewHolder.itemView
        val currentTranslationX: Float = ViewCompat.getTranslationX(view)
        val currentTranslationY: Float = ViewCompat.getTranslationY(view)
        val translationX = view.translationX
        val translationY = view.translationY
        val yToXRatio = calculateYToXRatio(touchPointer, this.velocityTracker)

        val directionOrdinal = SwipeDirectionOrdinalMap.DIRECTION_ORDINALS[swipeThresholdDetector.getDirectionFromMovement(dragX, dragY).ordinal]

        when (directionOrdinal) {
            1 -> { // LEFT
                endX = SwipeLeftAnimation().endX()
                deltaX = endX - currentTranslationX
                finalY = (yToXRatio * deltaX) + currentTranslationY
                velocityX = xVelocity
                absEndX = abs(endX.toDouble()).toFloat()
                finalX = endX
                velocityY = velocityX
                absAnimDistance = absEndX
                finalTranslationY = finalY
            }
            2 -> { // RIGHT
                endX = SwipeRightAnimation().endX()
                deltaX = endX - currentTranslationX
                finalY = (yToXRatio * deltaX) + currentTranslationY
                velocityX = xVelocity
                absEndX = abs(endX.toDouble()).toFloat()
                finalX = endX
                velocityY = velocityX
                absAnimDistance = absEndX
                finalTranslationY = finalY
            }
            3 -> { // UP
                val endY: Float = SwipeUpAnimation().endY()
                deltaX = endY - currentTranslationY
                finalTranslationY = endY
                velocityY = yVelocity
                finalX = translationX
                absAnimDistance = abs(endY.toDouble()).toFloat()
            }
            else -> { // 默认情况
                absAnimDistance = 0.0f
                deltaX = 0.0f
                velocityY = 0.0f
                finalTranslationY = translationY
                finalX = translationX
            }
        }

        val animationSpeedFactor = absAnimDistance / 180.0f
        val cardAnimation = CardAnimation(
            viewHolder,
            CardAnimation.AnimationType.SWIPE_OUT,
            touchPointer.firstTouchPoint,
            currentTranslationX,
            currentTranslationY,
            finalX,
            finalTranslationY
        )

        val absDeltaX = abs(deltaX.toDouble()).toFloat()
        val duration = (absDeltaX / max(animationSpeedFactor.toDouble(), velocityY.toDouble())).toLong()
        val interpolator = createInterpolator(velocityY, animationSpeedFactor, duration, absDeltaX)

        cardAnimation.duration = duration
        cardAnimation.setInterpolator(interpolator)
        cardAnimator.addActiveAnimation(cardAnimation)
        cardAnimation.start()
        recyclerView.invalidate()
        Timber.d("performSwipeOutAnimation:: end, animation started with duration=%d", duration)
    }

    private val xVelocity: Float
        /**
         * 获取X轴方向的速度
         * @return X轴速度（像素/秒）
         */
        get() {
            val velocityTracker = this.velocityTracker ?: return 0.0f
            velocityTracker.computeCurrentVelocity(swipeThresholdDetector.velocityTrackingUnits)
            return (abs(this.velocityTracker!!.xVelocity.toDouble()) / 1000.0f).toFloat()
        }

    private val yVelocity: Float
        /**
         * 获取Y轴方向的速度
         * @return Y轴速度（像素/秒）
         */
        get() {
            val velocityTracker = this.velocityTracker ?: return 0.0f
            velocityTracker.computeCurrentVelocity(swipeThresholdDetector.velocityTrackingUnits)
            return (abs(this.velocityTracker!!.yVelocity.toDouble()) / 1000.0f).toFloat()
        }

    /**
     * 创建插值器
     * @param velocity 速度
     * @param animSpeedFactor 动画速度因子
     * @param duration 动画时长
     * @param distance 动画距离
     * @return 适合的插值器
     */
    private fun createInterpolator(velocity: Float, animSpeedFactor: Float, duration: Long, distance: Float): Interpolator {
        return if (velocity < animSpeedFactor) SwipeOutInterpolator(distance, velocity, duration) else LinearInterpolator()
    }

    /**
     * 计算Y轴与X轴的比率
     * @param touchPointer 触摸指针
     * @param velocityTracker 速度追踪器
     * @return Y/X比率
     */
    private fun calculateYToXRatio(touchPointer: TouchPointer, velocityTracker: VelocityTracker?): Float {
        val dragX = touchPointer.dragX
        val ratio = if (dragX == 0.0f) 0.0f else touchPointer.dragY / dragX
        if (velocityTracker == null) {
            return ratio
        }
        val xVelocity = velocityTracker.xVelocity
        return if (xVelocity != 0.0f) velocityTracker.yVelocity / xVelocity else ratio
    }

    /**
     * 检查视图持有者是否可滑动
     * @param viewHolder 视图持有者
     * @return 是否可滑动
     */
    private fun isSwipable(viewHolder: RecyclerView.ViewHolder): Boolean {
        if (viewHolder is CardViewHolder<*>) {
            return viewHolder.swipable()
        }
        return true
    }

    /**
     * 检查是否应该执行滑出动画
     * @param touchPointer 触摸指针
     * @return 是否应执行滑出动画
     */
    private fun shouldPerformSwipeOutAnimation(touchPointer: TouchPointer): Boolean {
        val velocityTracker = this.velocityTracker
        if (velocityTracker != null) {
            val shouldPerform = touchHelperUtil.shouldPerformSwipeAnimation(touchPointer, velocityTracker, this.swipeThresholdDetector)
            Timber.d("shouldPerformSwipeOutAnimation:: velocityTracker exists, shouldPerform=%b", shouldPerform)
            return shouldPerform
        }
        Timber.w("shouldPerformSwipeOutAnimation:: velocityTracker is Null")
        return false
    }

    /**
     * 释放速度追踪器
     */
    private fun releaseVelocityTracker() {
        val velocityTracker = this.velocityTracker
        if (velocityTracker != null) {
            velocityTracker.recycle()
            this.velocityTracker = null
        }
    }

    /**
     * 处理动作取消事件
     * @param motionEvent 触摸事件
     */
    private fun processActionUp(motionEvent: MotionEvent) {
        Timber.d("processActionUp: 开始处理ACTION_UP事件")
        val touchPointer = this.activeTouchPointer
        if (touchPointer != null) {
            val viewHolder = touchPointer.viewHolder
            val isDragging = touchPointer.isDragging
            val isBelowThreshold = this.touchHelperUtil.isBelowThreshold(this.activeTouchPointer!!, this.swipeThresholdDetector)
            Timber.d("processActionUp: isDragging=%b, isBelowThreshold=%b", isDragging, isBelowThreshold)
            if (!isBelowThreshold || isDragging) {
                Timber.d("processActionUp: 超过阈值或正在拖拽，发送取消事件")
                dispatchCancelEvent(viewHolder.itemView, motionEvent)
                val isReadyToAcceptSwipes: Boolean = this.touchHelperUtil.isReadyToAcceptSwipes(viewHolder, recyclerView, this.cardAnimator)
                val shouldPerformSwipeOut: Boolean = shouldPerformSwipeOutAnimation(this.activeTouchPointer!!)
                Timber.d("processActionUp: isReadyToAcceptSwipes=%b, shouldPerformSwipeOut=%b", isReadyToAcceptSwipes, shouldPerformSwipeOut)
                if (isReadyToAcceptSwipes && shouldPerformSwipeOut) {
                    val dragX: Float = activeTouchPointer!!.dragX
                    val dragY: Float = activeTouchPointer!!.dragY
                    val velocityTracker: VelocityTracker = this.velocityTracker!!
                    val xVelocity = velocityTracker.xVelocity
                    val yVelocity = velocityTracker.yVelocity
                    Timber.d("processActionUp: dragX=%.2f, dragY=%.2f, xVelocity=%.2f, yVelocity=%.2f", dragX, dragY, xVelocity, yVelocity)
                    val swipeDirection = this.swipeThresholdDetector.determineSwipeDirection(dragX, dragY, xVelocity, yVelocity)
                    Timber.d("processActionUp: 确定的滑动方向=%s", swipeDirection)
                    if (this.preSwipeListener.onPreSwipe(viewHolder.adapterPosition, swipeDirection)) {
                        Timber.d("processActionUp: preSwipeListener允许滑动，执行滑出动画")
                        performSwipeOutAnimation(this.activeTouchPointer!!)
                    } else {
                        Timber.d("processActionUp: preSwipeListener不允许滑动，执行恢复动画")
                        this.cardAnimator.startRecoverAnimation(viewHolder, recyclerView, activeTouchPointer!!.firstTouchPoint)
                    }
                } else {
                    Timber.d("processActionUp: 不满足滑出条件，执行恢复动画")
                    this.cardAnimator.startRecoverAnimation(viewHolder, recyclerView, activeTouchPointer!!.firstTouchPoint)
                }
            } else {
                Timber.d("processActionUp: 低于阈值且未拖拽，执行恢复动画并分发触摸结束事件")
                this.cardAnimator.startRecoverAnimation(viewHolder, recyclerView, activeTouchPointer!!.firstTouchPoint)
                dispatchTouchEndEvent(viewHolder.itemView, motionEvent)
            }
        } else {
            Timber.d("processActionUp: 没有活动的触摸指针")
        }
        Timber.d("processActionUp: 取消选择视图持有者")
        unselectViewHolder(true)
    }

    /**
     * 处理动作取消事件
     * @param motionEvent 触摸事件
     */
    private fun handleActionCancel(motionEvent: MotionEvent) {
        val touchPointer = this.activeTouchPointer
        if (touchPointer != null) {
            val viewHolder = touchPointer.viewHolder
            dispatchCancelEvent(viewHolder.itemView, motionEvent)
            this.cardAnimator.startRecoverAnimation(viewHolder, recyclerView, activeTouchPointer!!.firstTouchPoint)
        }
        unselectViewHolder(false)
    }


    /**
     * 处理动作按下事件
     * @param motionEvent 触摸事件
     */
    private fun handleActionDown(motionEvent: MotionEvent) {
        Timber.d("handleActionDown:: start x=%.2f, y=%.2f", motionEvent.x, motionEvent.y)
        releaseVelocityTracker()
        obtainVelocityTracker()
        velocityTracker!!.addMovement(motionEvent)
        findAndSelectViewHolder(motionEvent)
        Timber.d("handleActionDown:: end, activeTouchPointer=%s",
            if (activeTouchPointer != null) "set" else "null")
    }

    /**
     * 添加移动事件到速度追踪器
     * @param motionEvent 触摸事件
     */
    private fun addMovementToVelocityTracker(motionEvent: MotionEvent) {
        this.velocityTracker?.addMovement(motionEvent)
    }

    /**
     * 不允许父视图拦截触摸事件
     */
    protected fun disallowTouchIntercept() {
        recyclerView.requestDisallowInterceptTouchEvent(true)
    }

    /**
     * 分发动作按下事件
     * @param view 目标视图
     * @param motionEvent 触摸事件
     */
    protected fun dispatchActionDownEvent(view: View, motionEvent: MotionEvent) {
        val obtain: MotionEvent = MotionEvent.obtain(motionEvent)
        obtain.setAction(MotionEvent.ACTION_DOWN)
        view.dispatchTouchEvent(obtain)
        obtain.recycle()
    }

    /**
     * 寻找并选择视图持有者
     * @param motionEvent 触摸事件
     */
    protected fun findAndSelectViewHolder(motionEvent: MotionEvent) {
        Timber.d("findAndSelectViewHolder:: start x=%.2f, y=%.2f", motionEvent.x, motionEvent.y)
        
        val childView: View? = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY())
        if (childView == null) {
            Timber.w("findAndSelectViewHolder:: Child view is null!")
            return
        }
        
        Timber.d("findAndSelectViewHolder:: found child view: %s", childView.toString())
        
        val viewHolder: RecyclerView.ViewHolder? = recyclerView.getChildViewHolder(childView)
        if (viewHolder == null) {
            Timber.w("findAndSelectViewHolder:: View holder is null!")
            return
        }
        
        Timber.d("findAndSelectViewHolder:: found view holder: %s", viewHolder.toString())
        
        if (!isSwipable(viewHolder)) {
            Timber.w("findAndSelectViewHolder:: View holder not swipable!")
            return
        }
        
        Timber.d("findAndSelectViewHolder:: Found valid swipable view holder")
        val dragConstraints = createDragConstraints(viewHolder)
        val touchPointer: TouchPointer = newTouchPointer(viewHolder, motionEvent, dragConstraints)
        this.activeTouchPointer = touchPointer
        // 打印触摸指针信息
        Timber.d("findAndSelectViewHolder: 创建触摸指针成功，位置=%d, 起始点=(%.2f, %.2f), 约束=[上=%b, 下=%b]", 
            touchPointer.viewHolder.adapterPosition,
            touchPointer.startX,
            touchPointer.startY,
            dragConstraints.canDragUp,
            dragConstraints.canDragDown)
        
        if (touchPointer.firstTouchPoint != null) {
            Timber.d("findAndSelectViewHolder: 首次触摸点=(%.2f, %.2f)", 
                touchPointer.firstTouchPoint.x,
                touchPointer.firstTouchPoint.y)
        }
        Timber.d("findAndSelectViewHolder:: Created touch pointer and dispatching ACTION_DOWN")
        dispatchActionDownEvent(childView, motionEvent)
    }

    /**
     * 获取关联的RecyclerView
     * @return RecyclerView实例
     */
    protected abstract val recyclerView: RecyclerView

    /**
     * 处理ACTION_UP事件
     * @param motionEvent 触摸事件
     */
    protected fun handleActionUp(motionEvent: MotionEvent) {
        processActionUp(motionEvent)
        releaseVelocityTracker()
    }

    /**
     * 创建新的触摸指针（使用CardAnimation版本）
     * @param cardAnimation 卡片动画
     * @param motionEvent 触摸事件
     * @param dragConstraints 拖拽约束
     * @return 新的触摸指针
     */
    protected fun newTouchPointer(cardAnimation: CardAnimation, motionEvent: MotionEvent, dragConstraints: DragConstraints?): TouchPointer {
        throw UnsupportedOperationException("Not implemented")
    }

    /**
     * 获取速度追踪器
     */
    protected fun obtainVelocityTracker() {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain()
        }
    }

    /**
     * 创建拖拽约束
     * @param viewHolder 视图持有者
     * @return 拖拽约束
     */
    private fun createDragConstraints(viewHolder: RecyclerView.ViewHolder): DragConstraints {
        val canDragUp = canDragUp(viewHolder)
        val canDragDown = canDragDown(viewHolder)
        return DragConstraints(canDragUp, canDragDown)
    }

    /**
     * 拦截触摸事件
     * @param recyclerView RecyclerView
     * @param motionEvent 触摸事件
     * @return 是否拦截
     */
    override fun onInterceptTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent): Boolean {
        val actionMasked: Int = MotionEventCompat.getActionMasked(motionEvent)
        Timber.d("onInterceptTouchEvent:: action=%d, x=%.2f, y=%.2f, actionMasked=%d", 
            motionEvent.action, motionEvent.x, motionEvent.y, actionMasked)
            
        if (this.activeTouchPointer != null) {
            Timber.d("onInterceptTouchEvent:: already has active pointer, isDragging=%b", 
                activeTouchPointer!!.isDragging)
            return true
        }
        
        if (actionMasked != MotionEvent.ACTION_DOWN) {
            Timber.d("onInterceptTouchEvent:: not ACTION_DOWN, returning false")
            return false
        }
        
        Timber.d("onInterceptTouchEvent:: handling ACTION_DOWN")
        handleActionDown(motionEvent)
        
        if (this.activeTouchPointer == null) {
            Timber.d("onInterceptTouchEvent:: no active pointer after ACTION_DOWN")
            return false
        }
        
        Timber.d("onInterceptTouchEvent:: active pointer set, disallowing intercept")
        disallowTouchIntercept()

        val position = this.recyclerView.getChildAdapterPosition(activeTouchPointer!!.viewHolder.itemView)
        Timber.d("onInterceptTouchEvent:: checking preSwipe for position %d", position)
        
        if (preSwipeListener.onPreSwipe(position, SwipeDirection.NONE)) {
            Timber.d("onInterceptTouchEvent:: preSwipe returned true, canceling")
            handleActionCancel(motionEvent)
            return false
        }

        this.recyclerView.parent.requestDisallowInterceptTouchEvent(true)
        val shouldIntercept = activeTouchPointer!!.isDragging
        Timber.d("onInterceptTouchEvent:: returning %b (isDragging)", shouldIntercept)
        return shouldIntercept
    }

    /**
     * 处理请求不允许拦截触摸事件
     * @param disallowIntercept 是否不允许拦截
     */
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    /**
     * 处理触摸事件
     * @param recyclerView RecyclerView
     * @param motionEvent 触摸事件
     */
    override fun onTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent) {
        val activeTouchPointer = this.activeTouchPointer ?: return
        val actionMasked = motionEvent.actionMasked
        val findPointerIndex = motionEvent.findPointerIndex(activeTouchPointer.pointerId)
        if (actionMasked != MotionEvent.ACTION_UP) {
            if (actionMasked != MotionEvent.ACTION_MOVE) {
                if (actionMasked != MotionEvent.ACTION_CANCEL) {
                    if (actionMasked == MotionEvent.ACTION_POINTER_UP && motionEvent.getPointerId(motionEvent.actionIndex) === activeTouchPointer.pointerId) {
                        handleActionCancel(motionEvent)
                    }
                }
            } else {
                if (findPointerIndex < 0) {
                    return
                }
                calculateTouchDelta(touchDelta, motionEvent, findPointerIndex)
                val pointF: PointF = this.touchDelta
                val f = pointF.x
                if (f == 0.0f && pointF.y == 0.0f) {
                    return
                }
                activeTouchPointer.updateDragX(f)
                val dragConstraints: DragConstraints = activeTouchPointer.dragConstraints
                var z = false
                val z2 = this.touchDelta.y > 0.0f && dragConstraints.canDragDown
                if (this.touchDelta.y < 0.0f && dragConstraints.canDragUp) {
                    z = true
                }
                if (z2 || z) {
                    activeTouchPointer.dragY = this.touchDelta.y
                }
                if (!this.touchHelperUtil.isBelowThreshold(activeTouchPointer, this.swipeThresholdDetector) && !activeTouchPointer.isDragging) {
                    activeTouchPointer.isDragging = true
                }
                recyclerView.invalidate()
                return
            }
            addMovementToVelocityTracker(motionEvent)
        }
        handleActionUp(motionEvent)
        addMovementToVelocityTracker(motionEvent)
    }

    /**
     * 设置预滑动监听器
     * @param listener 预滑动监听器
     */
    fun setPreSwipeListener(listener: CardStackSwipeHelper.OnPreSwipeListener) {
        this.preSwipeListener = listener
    }

    /**
     * 取消选择视图持有者
     * @param shouldPublishUpdate 是否应发布更新
     */
    fun unselectViewHolder(shouldPublishUpdate: Boolean) {
        Timber.i("unselectViewHolder: shouldPublishUpdate=%b", shouldPublishUpdate)
        this.activeTouchPointer = null
        if (shouldPublishUpdate) {
            allowParentTouchIntercept()
        }
    }

    /**
     * 取消选择视图持有者（不发布更新）
     */
    fun unselectViewHolderDoNotPublishUpdate() {
        Timber.i("unselectViewHolderDoNotPublishUpdate: start")
        val touchPointer = this.activeTouchPointer
        if (touchPointer != null) {
            this.cardAnimator.startRecoverAnimation(touchPointer.viewHolder, recyclerView, touchPointer.firstTouchPoint)
        }
        unselectViewHolder(false)
    }

    /**
     * 创建新的触摸指针
     * @param viewHolder 视图持有者
     * @param motionEvent 触摸事件
     * @param dragConstraints 拖拽约束
     * @return 新的触摸指针
     */
    protected fun newTouchPointer(viewHolder: RecyclerView.ViewHolder, motionEvent: MotionEvent, dragConstraints: DragConstraints): TouchPointer {
        return TouchPointer(viewHolder, motionEvent, dragConstraints)
    }

    companion object {
        private val DEFAULT_PRE_SWIPE_LISTENER: CardStackSwipeHelper.OnPreSwipeListener = DefaultPreSwipeListener()
    }
}