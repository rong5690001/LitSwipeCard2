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
        if (this.activeTouchPointer == null) {
            deltaPoint.set(0.0f, 0.0f)
            return
        }
        val x: Float = motionEvent.getX(pointerIndex)
        val y: Float = motionEvent.getY(pointerIndex)
        val deltaX = x - activeTouchPointer!!.startX
        val deltaY = y - activeTouchPointer!!.startY
        if (activeTouchPointer!!.dragX != 0.0f || activeTouchPointer!!.dragY != 0.0f) {
            deltaPoint.set(deltaX, deltaY)
        } else if (swipeThresholdDetector.isBelowThreshold(deltaX, deltaY)) {
            deltaPoint.set(0.0f, 0.0f)
        } else {
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

        if (directionOrdinal == 1) { // LEFT
            endX = SwipeLeftAnimation().endX()
            deltaX = endX - currentTranslationX
            finalY = (yToXRatio * deltaX) + currentTranslationY
            velocityX = xVelocity
            absEndX = abs(endX.toDouble()).toFloat()
            finalX = endX
            velocityY = velocityX
            absAnimDistance = absEndX
            finalTranslationY = finalY
        } else if (directionOrdinal == 2) { // RIGHT
            endX = SwipeRightAnimation().endX()
            deltaX = endX - currentTranslationX
            finalY = (yToXRatio * deltaX) + currentTranslationY
            velocityX = xVelocity
            absEndX = abs(endX.toDouble()).toFloat()
            finalX = endX
            velocityY = velocityX
            absAnimDistance = absEndX
            finalTranslationY = finalY
        } else if (directionOrdinal == 3) { // UP
            val endY: Float = SwipeUpAnimation().endY()
            deltaX = endY - currentTranslationY
            finalTranslationY = endY
            velocityY = yVelocity
            finalX = translationX
            absAnimDistance = abs(endY.toDouble()).toFloat()
        } else { // 默认情况
            absAnimDistance = 0.0f
            deltaX = 0.0f
            velocityY = 0.0f
            finalTranslationY = translationY
            finalX = translationX
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
            return touchHelperUtil.shouldPerformSwipeAnimation(touchPointer, velocityTracker, this.swipeThresholdDetector)
        }
        Timber.w("Check implementation: velocityTracker is Null::", arrayOfNulls<Any>(0))
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
     * 处理动作按下事件
     * @param motionEvent 触摸事件
     */
    private fun handleActionDown(motionEvent: MotionEvent) {
        releaseVelocityTracker()
        obtainVelocityTracker()
        velocityTracker!!.addMovement(motionEvent)
        findAndSelectViewHolder(motionEvent)
    }

    /**
     * 处理动作移动事件
     * @param motionEvent 触摸事件
     */
    private fun handleActionMove(motionEvent: MotionEvent) {
        val velocityTracker = this.velocityTracker
        velocityTracker?.addMovement(motionEvent)
        val pointerIndex: Int = motionEvent.findPointerIndex(activeTouchPointer!!.pointerId)
        if (pointerIndex < 0) {
            Timber.w("Pointer index < 0!", arrayOfNulls<Any>(0))
            return
        }
        calculateTouchDelta(this.touchDelta, motionEvent, pointerIndex)
        val deltaX: Float = touchDelta.x
        val deltaY: Float = touchDelta.y
        
        // 获取当前卡片视图
        val view = activeTouchPointer!!.viewHolder.itemView
        
        Timber.d("Touch Move - DeltaX: %.2f, DeltaY: %.2f, CurrentX: %.2f, CurrentY: %.2f", 
            deltaX, deltaY, ViewCompat.getTranslationX(view), ViewCompat.getTranslationY(view))
        
        if (activeTouchPointer!!.isDragging) {
            activeTouchPointer!!.updateDragDelta(deltaX, deltaY)
            // 确保卡片在滑动时保持在最上层
            view.bringToFront()
            // 直接更新卡片位置
            ViewCompat.setTranslationX(view, ViewCompat.getTranslationX(view) + deltaX)
            ViewCompat.setTranslationY(view, ViewCompat.getTranslationY(view) + deltaY)
            // 强制重绘
            view.invalidate()
            recyclerView.invalidate()
            Timber.d("Dragging - New Position - X: %.2f, Y: %.2f", 
                ViewCompat.getTranslationX(view), ViewCompat.getTranslationY(view))
        } else if (deltaX != 0.0f || deltaY != 0.0f) {
            activeTouchPointer!!.startDragging(true)
            activeTouchPointer!!.updateDragDelta(deltaX, deltaY)
            // 确保卡片在滑动时保持在最上层
            view.bringToFront()
            // 直接更新卡片位置
            ViewCompat.setTranslationX(view, ViewCompat.getTranslationX(view) + deltaX)
            ViewCompat.setTranslationY(view, ViewCompat.getTranslationY(view) + deltaY)
            // 强制重绘
            view.invalidate()
            recyclerView.invalidate()
            Timber.d("Start Dragging - Initial Position - X: %.2f, Y: %.2f", 
                ViewCompat.getTranslationX(view), ViewCompat.getTranslationY(view))
        }
    }

    /**
     * 处理动作取消事件
     * @param motionEvent 触摸事件
     */
    private fun handleActionCancel(motionEvent: MotionEvent) {
        val touchPointer = this.activeTouchPointer
        if (touchPointer != null) {
            val viewHolder = touchPointer.viewHolder
            dispatchActionDownEvent(viewHolder.itemView, motionEvent)
            this.cardAnimator.startRecoverAnimation(viewHolder, recyclerView, activeTouchPointer!!.firstTouchPoint)
        }
        unselectViewHolder(false)
    }

    private fun q(motionEvent: MotionEvent) {
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
        val childView: View? = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY())
        if (childView == null) {
            Timber.w("Child view is null!", arrayOfNulls<Any>(0))
            return
        }
        val viewHolder: RecyclerView.ViewHolder? = recyclerView.getChildViewHolder(childView)
        if (viewHolder == null) {
            Timber.w("View holder is null!", arrayOfNulls<Any>(0))
            return
        }
        if (!isSwipable(viewHolder)) {
            Timber.w("View holder not swipable!", arrayOfNulls<Any>(0))
            return
        }
        val dragConstraints = createDragConstraints(viewHolder)
        val touchPointer: TouchPointer = newTouchPointer(viewHolder, motionEvent, dragConstraints)
        this.activeTouchPointer = touchPointer
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
        unselectViewHolder(true)
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
        if (this.activeTouchPointer != null) {
            return true
        }
        val actionMasked: Int = MotionEventCompat.getActionMasked(motionEvent)
        if (actionMasked != MotionEvent.ACTION_DOWN) {
            return false
        }
        handleActionDown(motionEvent)
        if (this.activeTouchPointer == null) {
            return false
        }
        disallowTouchIntercept()

        if (preSwipeListener.onPreSwipe(this.recyclerView.getChildAdapterPosition(activeTouchPointer!!.viewHolder.itemView), SwipeDirection.NONE)) {
            handleActionCancel(motionEvent)
            return false
        }

        this.recyclerView.parent.requestDisallowInterceptTouchEvent(true)
        return activeTouchPointer!!.isDragging
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
        if (this.activeTouchPointer == null) {
            return
        }
        val actionMasked = MotionEventCompat.getActionMasked(motionEvent)
        val findPointerIndex = motionEvent.findPointerIndex(activeTouchPointer!!.pointerId)
        if (actionMasked != MotionEvent.ACTION_UP) {
            if (actionMasked != MotionEvent.ACTION_MOVE) {
                if (actionMasked != MotionEvent.ACTION_CANCEL) {
                    if (actionMasked == MotionEvent.ACTION_POINTER_UP && motionEvent.getPointerId(MotionEventCompat.getActionIndex(motionEvent)) === activeTouchPointer!!.pointerId) {
                        handleActionCancel(motionEvent)
                    }
                }
            } else {
                if (findPointerIndex < 0) {
                    return
                }
                calculateTouchDelta(this.touchDelta, motionEvent, findPointerIndex)
                val pointF: PointF = this.touchDelta
                val deltaX = pointF.x
                if (deltaX == 0.0f && pointF.y == 0.0f) {
                    return
                }
                activeTouchPointer!!.updateDragX(deltaX)
                val dragConstraints: DragConstraints = activeTouchPointer!!.dragConstraints!!
                var canDragUp = false
                val canDragDown = this.touchDelta.y > 0.0f && dragConstraints.canDragDown
                if (this.touchDelta.y < 0.0f && dragConstraints.canDragUp) {
                    canDragUp = true
                }
                if (canDragDown || canDragUp) {
                    activeTouchPointer!!.updateDragY(this.touchDelta.y)
                }
                if (!this.touchHelperUtil.isBelowThreshold(this.activeTouchPointer!!, this.swipeThresholdDetector) && !activeTouchPointer!!.isDragging) {
                    activeTouchPointer!!.isDragging = true
                }
                recyclerView.invalidate()
            }
            q(motionEvent)
        }
        handleActionUp(motionEvent)
        q(motionEvent)
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
        val touchPointer = this.activeTouchPointer
        if (touchPointer != null) {
            this.activeTouchPointer = null
            releaseVelocityTracker()
            allowParentTouchIntercept()
        }
    }

    /**
     * 取消选择视图持有者（不发布更新）
     */
    fun unselectViewHolderDoNotPublishUpdate() {
        val touchPointer = this.activeTouchPointer
        if (touchPointer != null) {
            this.activeTouchPointer = null
            releaseVelocityTracker()
            recyclerView.getParent().requestDisallowInterceptTouchEvent(false)
        }
    }

    /**
     * 创建新的触摸指针
     * @param viewHolder 视图持有者
     * @param motionEvent 触摸事件
     * @param dragConstraints 拖拽约束
     * @return 新的触摸指针
     */
    protected fun newTouchPointer(viewHolder: RecyclerView.ViewHolder, motionEvent: MotionEvent, dragConstraints: DragConstraints?): TouchPointer {
        return TouchPointer(viewHolder, motionEvent, dragConstraints)
    }

    companion object {
        private val DEFAULT_PRE_SWIPE_LISTENER: CardStackSwipeHelper.OnPreSwipeListener = DefaultPreSwipeListener()
    }
}