package com.rong.litswipecard.cardstack.cardstack

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.cardstack.cardtransformer.CardDecorationPairController
import com.rong.litswipecard.cardstack.model.SwipeDirection
import com.rong.litswipecard.cardstack.swipe.CardAnimator
import com.rong.litswipecard.cardstack.swipe.CardItemDecorator
import com.rong.litswipecard.cardstack.swipe.CardItemTouchHelperUtil
import com.rong.litswipecard.cardstack.swipe.CardItemTouchListener
import com.rong.litswipecard.cardstack.swipe.SwipeThresholdDetector
import com.rong.litswipecard.cardstack.swipe.TouchPointer
import com.rong.litswipecard.cardstack.view.CardDecorationListener
import com.rong.litswipecard.cardstack.view.CardStackLayout
import java.util.Collections
import timber.log.Timber


/**
 * 卡片堆栈滑动助手
 * 负责管理卡片的滑动动画和交互
 */
class CardStackSwipeHelper(cardStackLayout: CardStackLayout) {
    private val recyclerView: RecyclerView = cardStackLayout
    private val cardTouchListener: CardItemTouchListener
    private val swipeThresholdDetector: SwipeThresholdDetector
    private val cardAnimator: CardAnimator
    private val decorationListenerWrapper: CardDecorationListenerWrapper
    private val decorationPairController: CardDecorationPairController
    private val topCardMovedManager: CardDecorationManager
    private val cardStackItemAnimator: CardStackItemAnimator

    /**
     * 卡片装饰监听器包装类
     * 管理全局和特定视图的装饰监听器
     */
    class CardDecorationListenerWrapper : CardDecorationListener {
        private val globalListeners: MutableList<CardDecorationListener> = ArrayList()
        private val viewSpecificListeners: MutableMap<View, MutableList<CardDecorationListener>> = HashMap()

        /**
         * 为特定视图添加监听器
         */
        internal fun addViewSpecificListener(view: View?, listener: CardDecorationListener?) {
            if (view != null && listener != null) {
                if (!viewSpecificListeners.containsKey(view)) {
                    viewSpecificListeners[view] = ArrayList(4)
                }
                viewSpecificListeners[view]?.add(listener)
            }
        }

        /**
         * 添加全局监听器
         */
        internal fun addGlobalListener(listener: CardDecorationListener?) {
            if (listener != null) {
                globalListeners.add(listener)
            }
        }

        /**
         * 获取特定视图的监听器列表
         */
        private fun getViewSpecificListeners(view: View): List<CardDecorationListener> {
            return if (viewSpecificListeners.containsKey(view)) {
                Collections.unmodifiableList(viewSpecificListeners[view])
            } else {
                emptyList()
            }
        }

        /**
         * 调用装饰绘制结束监听器
         */
        private fun callDecorationDrawOver(
            listener: CardDecorationListener,
            canvas: Canvas,
            view: View,
            viewGroup: ViewGroup,
            translationX: Float,
            translationY: Float,
            rotation: Float,
            swipeDirection: SwipeDirection,
            isMoving: Boolean,
            isThresholdCrossed: Boolean
        ) {
            val saveCount = canvas.save()
            listener.onDecorationDrawOver(canvas, view, viewGroup, translationX, translationY, rotation, swipeDirection, isMoving, isThresholdCrossed)
            canvas.restoreToCount(saveCount)
        }

        /**
         * 调用装饰绘制监听器
         */
        private fun callDecorationDraw(
            listener: CardDecorationListener,
            canvas: Canvas,
            view: View,
            viewGroup: ViewGroup,
            translationX: Float,
            translationY: Float,
            rotation: Float,
            swipeDirection: SwipeDirection,
            isMoving: Boolean,
            isThresholdCrossed: Boolean
        ) {
            val saveCount = canvas.save()
            listener.onDecorationDraw(canvas, view, viewGroup, translationX, translationY, rotation, swipeDirection, isMoving, isThresholdCrossed)
            canvas.restoreToCount(saveCount)
        }

        /**
         * 移除特定视图的监听器
         */
        internal fun removeViewSpecificListener(view: View?, listener: CardDecorationListener?) {
            if (view != null && listener != null && viewSpecificListeners.containsKey(view)) {
                val listeners = viewSpecificListeners[view]
                listeners?.remove(listener)
                if (listeners?.isEmpty() == true) {
                    viewSpecificListeners.remove(view)
                }
            }
        }

        /**
         * 移除全局监听器
         */
        internal fun removeGlobalListener(listener: CardDecorationListener?) {
            if (listener != null) {
                globalListeners.remove(listener)
            }
        }

        // 实现 CardDecorationListener 接口
        override fun onDecorationDraw(canvas: Canvas, view: View, viewGroup: ViewGroup, translationX: Float, translationY: Float, rotation: Float, swipeDirection: SwipeDirection, isMoving: Boolean, isThresholdCrossed: Boolean) {
            // 调用全局监听器
            for (listener in globalListeners) {
                callDecorationDraw(listener, canvas, view, viewGroup, translationX, translationY, rotation, swipeDirection, isMoving, isThresholdCrossed)
            }
            
            // 调用视图特定监听器
            for (listener in getViewSpecificListeners(view)) {
                callDecorationDraw(listener, canvas, view, viewGroup, translationX, translationY, rotation, swipeDirection, isMoving, isThresholdCrossed)
            }
        }

        // 实现 CardDecorationListener 接口
        override fun onDecorationDrawOver(canvas: Canvas, view: View, viewGroup: ViewGroup, translationX: Float, translationY: Float, rotation: Float, swipeDirection: SwipeDirection, isMoving: Boolean, isThresholdCrossed: Boolean) {
            // 调用全局监听器
            for (listener in globalListeners) {
                callDecorationDrawOver(listener, canvas, view, viewGroup, translationX, translationY, rotation, swipeDirection, isMoving, isThresholdCrossed)
            }
            
            // 调用视图特定监听器
            for (listener in getViewSpecificListeners(view)) {
                callDecorationDrawOver(listener, canvas, view, viewGroup, translationX, translationY, rotation, swipeDirection, isMoving, isThresholdCrossed)
            }
        }
    }

    interface OnPreSwipeListener {
        fun onPreSwipe(position: Int, swipeDirection: SwipeDirection): Boolean
    }

    /**
     * 卡片滑动监听适配器
     */
    private inner class CardAttachmentListener(cardAnimator: CardAnimator) : CardStackChangeListener(cardAnimator) {
        override fun getViewHolder(view: View): RecyclerView.ViewHolder? {
            return recyclerView.findContainingViewHolder(view)
        }

        override val recyclerView: RecyclerView
            get() = this@CardStackSwipeHelper.recyclerView

        override val touchPointer: TouchPointer?
            get() = cardTouchListener.activeTouchPointer

        override fun setTouchPointerDetached(isDetached: Boolean) {
            cardTouchListener.unselectViewHolder(isDetached)
        }
    }

    /**
     * 卡片装饰器
     */
    private inner class CardDecoratorImpl(cardAnimator: CardAnimator) : CardItemDecorator(cardAnimator) {
        override val activeTouchPointer: TouchPointer?
            get() = cardTouchListener.activeTouchPointer

        override fun getDirectionFromMovement(dx: Float, dy: Float): SwipeDirection {
            return swipeThresholdDetector.getDirectionFromMovement(dx, dy)
        }

        override fun getRotation(view: View, dx: Float, dy: Float): Float {
            return swipeThresholdDetector.getRotation(view, dx, dy)
        }

        override fun releaseActiveTouchPointer() {
            cardTouchListener.unselectViewHolder(false)
        }
    }

    /**
     * 卡片触摸监听器
     */
    private inner class CardTouchListenerImpl(
        swipeThresholdDetector: SwipeThresholdDetector,
        cardAnimator: CardAnimator,
        cardItemTouchHelperUtil: CardItemTouchHelperUtil
    ) : CardItemTouchListener(swipeThresholdDetector, cardAnimator, cardItemTouchHelperUtil) {
        override val recyclerView: RecyclerView
            get() = this@CardStackSwipeHelper.recyclerView
    }

    init {
        Timber.d("Initializing CardStackSwipeHelper")
        
        // 初始化工具类和组件
        val cardItemTouchHelperUtil = CardItemTouchHelperUtil()
        val animator = CardAnimator()
        this.cardAnimator = animator
        val thresholdDetector = SwipeThresholdDetector(cardStackLayout.context)
        this.swipeThresholdDetector = thresholdDetector
        
        // 创建触摸监听器
        val touchListener = CardTouchListenerImpl(thresholdDetector, animator, cardItemTouchHelperUtil)
        this.cardTouchListener = touchListener
        
        // 创建状态监听器
        val cardStackItemAnimator = CardStackItemAnimator(animator)
        this.cardStackItemAnimator = cardStackItemAnimator
        
        // 创建卡片装饰器
        val decorator = CardDecoratorImpl(animator)
        
        // 添加到卡片堆栈布局
        cardStackLayout.addItemDecoration(decorator)
        cardStackLayout.addOnItemTouchListener(touchListener)
        cardStackLayout.itemAnimator = cardStackItemAnimator

        val changeListener = CardAttachmentListener(animator)
        
        // 添加状态变化监听器
        cardStackLayout.addOnChildAttachStateChangeListener(changeListener)
        cardStackLayout.addOnChildAttachStateChangeListenerPostLayout(changeListener)
        
        // 创建装饰监听器包装器
        val wrapperListener = CardDecorationListenerWrapper()
        this.decorationListenerWrapper = wrapperListener
        decorator.setCardDecorationListener(wrapperListener)
        
        // 创建卡片对控制器和顶部卡片移动管理器
        this.decorationPairController = CardDecorationPairController(cardStackLayout, thresholdDetector)
        this.topCardMovedManager = CardDecorationManager(cardStackLayout, thresholdDetector)
        
        Timber.d("CardStackSwipeHelper initialization completed")
    }

    /**
     * 添加卡片装饰监听器
     * @param listener 监听器
     */
    fun addCardDecorationListener(listener: CardDecorationListener) {
        Timber.d("Adding card decoration listener: %s", listener.javaClass.simpleName)
        decorationListenerWrapper.addGlobalListener(listener)
    }

    /**
     * 添加卡片对状态变化监听器
     * @param listener 监听器
     */
    fun addOnCardPairStateChangeListener(listener: CardStackLayout.OnCardPairStateChangeListener) {
        Timber.d("Adding card pair state change listener: %s", listener.javaClass.simpleName)
        decorationPairController.addOnCardPairStateChangeListener(listener)
    }

    /**
     * 添加顶部卡片移动监听器
     * @param listener 监听器
     */
    fun addTopCardMovedListener(listener: CardStackLayout.OnTopCardMovedListener) {
        Timber.d("Adding top card moved listener: %s", listener.javaClass.simpleName)
        topCardMovedManager.addTopCardMovedListener(listener)
    }

    /**
     * 检查新插入是否影响已冻结的卡片
     * @param startIndex 开始索引
     * @param count 数量
     * @return 是否影响已冻结的卡片
     */
    fun checkIfNewInsertsAffectFrozenCards(startIndex: Int, count: Int): Boolean {
        Timber.d("Checking if new inserts affect frozen cards - Start: %d, Count: %d", startIndex, count)
        if (!cardAnimator.isInPausedState) {
            Timber.d("No frozen cards found")
            return false
        }
        val endIndex = (count + startIndex) - 1
        val pausedAnimations = cardAnimator.getPausedAnimations()
        for (animation in pausedAnimations) {
            val adapterPosition = animation.viewHolder.adapterPosition
            if (adapterPosition >= startIndex && adapterPosition <= endIndex) {
                Timber.d("Found affected frozen card at position: %d", adapterPosition)
                return true
            }
        }
        Timber.d("No affected frozen cards found")
        return false
    }

    /**
     * 检查是否有被冻结的动画卡片
     * @return 是否有被冻结的动画卡片
     */
    fun hasFrozenAnimatingCards(): Boolean {
        val hasFrozen = cardAnimator.isInPausedState
        Timber.d("Checking frozen animating cards: %b", hasFrozen)
        return hasFrozen
    }

    /**
     * 暂停所有动画
     */
    fun pauseAnimations() {
        Timber.d("Pausing all animations")
        val activeTouchPointer = cardTouchListener.activeTouchPointer
        if (activeTouchPointer != null) {
            Timber.d("Starting recover animation for active touch pointer")
            cardAnimator.startRecoverAnimation(
                activeTouchPointer.viewHolder,
                recyclerView,
                activeTouchPointer.firstTouchPoint
            )
            cardTouchListener.unselectViewHolder(false)
        }
        cardAnimator.pauseAnimations()
        recyclerView.invalidate()
    }

    /**
     * 移除卡片装饰监听器
     * @param listener 监听器
     */
    fun removeCardDecorationListener(listener: CardDecorationListener) {
        Timber.d("Removing card decoration listener: %s", listener.javaClass.simpleName)
        decorationListenerWrapper.removeGlobalListener(listener)
    }

    /**
     * 移除卡片恢复动画状态监听器
     */
    fun removeCardRewindAnimationStateListener() {
        Timber.d("Removing card rewind animation state listener")
        cardStackItemAnimator.resetRewindAnimationListener()
    }

    /**
     * 移除卡片对状态变化监听器
     * @param listener 监听器
     */
    fun removeOnCardPairStateChangeListener(listener: CardStackLayout.OnCardPairStateChangeListener) {
        Timber.d("Removing card pair state change listener: %s", listener.javaClass.simpleName)
        decorationPairController.removeOnCardPairStateChangeListener(listener)
    }

    /**
     * 移除顶部卡片移动监听器
     * @param listener 监听器
     */
    fun removeTopCardMovedListener(listener: CardStackLayout.OnTopCardMovedListener) {
        Timber.d("Removing top card moved listener: %s", listener.javaClass.simpleName)
        topCardMovedManager.removeTopCardMovedListener(listener)
    }

    /**
     * 恢复暂停的动画
     */
    fun resumePausedAnimations() {
        Timber.d("Resuming paused animations")
        cardAnimator.resumePausedAnimations()
        recyclerView.invalidate()
    }

    /**
     * 恢复最后一张卡片的动画
     * @return 是否恢复成功
     */
    fun revertLastCardAnimation(): Boolean {
        Timber.d("Attempting to revert last card animation")
        val result = cardAnimator.revertLastCardAnimation()
        if (result) {
            Timber.d("Successfully reverted last card animation")
            cardTouchListener.unselectViewHolderDoNotPublishUpdate()
            recyclerView.invalidate()
        } else {
            Timber.d("Failed to revert last card animation")
        }
        return result
    }

    /**
     * 恢复暂停的动画
     */
    fun revertPausedAnimations() {
        Timber.d("Reverting paused animations")
        cardAnimator.revertPausedAnimations()
        recyclerView.invalidate()
    }

    /**
     * 设置卡片恢复动画状态监听器
     * @param listener 监听器
     */
    fun setCardRewindAnimationStateListener(listener: CardStackLayout.CardRewindAnimationStateListener) {
        Timber.d("Setting card rewind animation state listener: %s", listener.javaClass.simpleName)
        cardStackItemAnimator.setRewindAnimationListener(listener)
    }

    /**
     * 设置滑动前监听器
     * @param listener 监听器
     */
    fun setOnPreSwipeListener(listener: OnPreSwipeListener) {
        Timber.d("Setting pre-swipe listener: %s", listener.javaClass.simpleName)
        cardTouchListener.setPreSwipeListener(listener)
    }

    /**
     * 为特定视图添加卡片装饰监听器
     * @param view 视图
     * @param listener 监听器
     */
    fun addCardDecorationListener(view: View, listener: CardDecorationListener) {
        Timber.d("Adding card decoration listener for specific view: %s", view.toString())
        decorationListenerWrapper.addViewSpecificListener(view, listener)
    }

    /**
     * 从特定视图移除卡片装饰监听器
     * @param view 视图
     * @param listener 监听器
     */
    fun removeCardDecorationListener(view: View, listener: CardDecorationListener) {
        Timber.d("Removing card decoration listener from specific view: %s", view.toString())
        decorationListenerWrapper.removeViewSpecificListener(view, listener)
    }
}