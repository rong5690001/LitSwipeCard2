package com.rong.litswipecard.cardstack.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.cardstack.CardStackLayoutManager
import com.rong.litswipecard.cardstack.cardstack.CardStackSwipeHelper
import com.rong.litswipecard.cardstack.cardstack.cardtransformer.CardTransforms
import com.rong.litswipecard.cardstack.cardstack.cardtransformer.DefaultCardTransforms
import com.rong.litswipecard.cardstack.model.Card
import com.rong.litswipecard.cardstack.model.SwipeDirection
import timber.log.Timber
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 卡片堆叠布局
 * 实现卡片堆叠效果的视图控件，继承自CardCollectionLayout
 */
class CardStackLayout @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) : CardCollectionLayout(context, attributeSet) {
    /**
     * 卡片堆叠滑动辅助器
     */
    private val swipeHelper: CardStackSwipeHelper

    /**
     * 卡片堆叠布局管理器
     */
    private val layoutManager: CardStackLayoutManager

    /**
     * 卡片呈现监听器
     */
    private var cardPresentedListener: OnCardPresentedListener? = null

    /**
     * 触摸事件监听器列表
     */
    private val dispatchTouchEventListeners: CopyOnWriteArrayList<OnDispatchTouchEventListener?>

    /**
     * 卡片变换器
     */
    var cardTransforms: CardTransforms = DefaultCardTransforms()
        set(value) {
            field = value
            invalidate()
        }

    /**
     * 卡片回退动画状态监听器接口
     */
    interface CardRewindAnimationStateListener {
        /**
         * 当回退动画结束时调用
         *
         * @param view 执行回退动画的视图
         */
        fun onRewindAnimationEnded(view: View?)

        /**
         * 当回退动画进行中时调用
         *
         * @param view 执行回退动画的视图
         * @param progress 动画进度(0-1)
         */
        fun onRewindAnimationProgress(view: View?, progress: Float)

        /**
         * 当回退动画开始时调用
         *
         * @param view 执行回退动画的视图
         */
        fun onRewindAnimationStarted(view: View?)
    }

    /**
     * 卡片对状态变化监听器接口
     */
    interface OnCardPairStateChangeListener {
        /**
         * 当创建卡片对时调用
         *
         * @param topView 顶部卡片视图
         * @param bottomView 底部卡片视图
         */
        fun onPairCreated(topView: View?, bottomView: View?)

        /**
         * 当销毁卡片对时调用
         *
         * @param topView 顶部卡片视图
         * @param bottomView 底部卡片视图
         */
        fun onPairDestroyed(topView: View?, bottomView: View?)
    }

    /**
     * 卡片呈现监听器接口
     */
    interface OnCardPresentedListener {
        /**
         * 当卡片被呈现时调用
         *
         * @param card 被呈现的卡片
         * @param view 卡片对应的视图
         */
        fun onCardPresented(card: Card<*>, view: View)
    }

    /**
     * 触摸事件分发监听器接口
     */
    interface OnDispatchTouchEventListener {
        /**
         * 当触摸事件被分发时调用
         *
         * @param event 触摸事件
         * @return 是否消费此事件
         */
        fun onDispatchTouchEvent(event: MotionEvent?): Boolean
    }

    /**
     * 顶部卡片移动监听器接口
     */
    interface OnTopCardMovedListener {
        /**
         * 当顶部卡片移动结束时调用
         *
         * @param thresholdExceeded 是否超过阈值
         */
        fun onTopCardMoveEnded(thresholdExceeded: Boolean)

        /**
         * 当顶部卡片开始移动时调用
         */
        fun onTopCardMoveStarted()

        /**
         * 当顶部卡片移动过程中调用
         *
         * @param translationX X轴移动距离
         * @param translationY Y轴移动距离
         * @param rotation 旋转角度
         * @param view 卡片视图
         * @param swipeDirection 滑动方向
         * @param thresholdExceeded 是否超过阈值
         */
        fun onTopCardMoved(translationX: Float, translationY: Float, rotation: Float, view: View?, swipeDirection: SwipeDirection?, thresholdExceeded: Boolean)
    }

    /**
     * 滑动前监听器适配器
     */
    internal inner class PreSwipeListenerAdapter(private val listener: OnPreSwipeListener) : CardStackSwipeHelper.OnPreSwipeListener {
        override fun onPreSwipe(position: Int, swipeDirection: SwipeDirection): Boolean {
            if (position >= 0) {
                return listener.onPreSwipe(this@CardStackLayout.adapter[position], swipeDirection)
            }
            Timber.w("Invalid adapter position:$position", arrayOfNulls<Any>(0))
            return false
        }
    }

    /**
     * 卡片呈现监听器实现
     */
    private inner class CardPresentedListenerImpl() : CardStackLayoutManager.OnCardPresentedListener {
        override fun onCardPresented(view: View) {
            val adapterPosition = getChildViewHolder(view).adapterPosition
            if (this@CardStackLayout.cardPresentedListener != null) {
                cardPresentedListener!!.onCardPresented(this@CardStackLayout.adapter[adapterPosition], view)
            }
        }

        /**
         * 合成构造函数
         */
        /* synthetic */
        constructor(cardStackLayout: CardStackLayout?, adapter: PreSwipeListenerAdapter?) : this()
    }

    /**
     * 添加卡片装饰监听器
     *
     * @param cardDecorationListener 卡片装饰监听器
     */
    fun addCardDecorationListener(cardDecorationListener: CardDecorationListener) {
        swipeHelper.addCardDecorationListener(cardDecorationListener)
    }

    /**
     * 添加卡片对状态变化监听器
     *
     * @param onCardPairStateChangeListener 卡片对状态变化监听器
     */
    fun addOnCardPairStateChangeListener(onCardPairStateChangeListener: OnCardPairStateChangeListener) {
        swipeHelper.addOnCardPairStateChangeListener(onCardPairStateChangeListener)
    }

    /**
     * 添加子视图附加状态变化后布局监听器
     *
     * @param onChildAttachStateChangePostLayoutListeners 子视图附加状态变化后布局监听器
     */
    fun addOnChildAttachStateChangeListenerPostLayout(onChildAttachStateChangePostLayoutListeners: OnChildAttachStateChangePostLayoutListeners) {
        layoutManager.addOnChildAttachStateChangeListenerPostLayout(onChildAttachStateChangePostLayoutListeners)
    }

    /**
     * 添加触摸事件分发监听器
     *
     * @param onDispatchTouchEventListener 触摸事件分发监听器
     */
    fun addOnDispatchTouchEventListener(onDispatchTouchEventListener: OnDispatchTouchEventListener) {
        dispatchTouchEventListeners.add(onDispatchTouchEventListener)
    }

    /**
     * 添加顶部卡片移动监听器
     *
     * @param onTopCardMovedListener 顶部卡片移动监听器
     */
    fun addTopCardMovedListener(onTopCardMovedListener: OnTopCardMovedListener) {
        swipeHelper.addTopCardMovedListener(onTopCardMovedListener)
    }

    /**
     * 检查新插入是否影响已冻结的卡片
     *
     * @param startIndex 开始插入的位置
     * @param insertCount 插入的数量
     */
    override fun checkIfNewInsertsAffectFrozenCards(startIndex: Int, insertCount: Int) {
        if (swipeHelper.checkIfNewInsertsAffectFrozenCards(startIndex, insertCount)) {
            revertFrozenAnimatingCards()
        }
    }

    /**
     * 分发触摸事件
     *
     * @param event 触摸事件
     * @return 是否消费此事件
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val it: Iterator<OnDispatchTouchEventListener?> = dispatchTouchEventListeners.iterator()
        while (it.hasNext()) {
            if (it.next()!!.onDispatchTouchEvent(event)) {
                return true
            }
        }
        return super.dispatchTouchEvent(event)
    }

    /**
     * 冻结正在动画的卡片
     */
    fun freezeAnimatingCards() {
        swipeHelper.pauseAnimations()
    }

    /**
     * 检查是否有冻结的正在动画的卡片
     *
     * @return 是否有冻结的正在动画的卡片
     */
    fun hasFrozenAnimatingCards(): Boolean {
        return swipeHelper.hasFrozenAnimatingCards()
    }

    /**
     * 当大小变化时调用
     *
     * @param width 新宽度
     * @param height 新高度
     * @param oldWidth 旧宽度
     * @param oldHeight 旧高度
     */
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        layoutManager.onSizeChanged(width, height, oldWidth, oldHeight)
    }

    /**
     * 移除卡片装饰监听器
     *
     * @param cardDecorationListener 要移除的卡片装饰监听器
     */
    fun removeCardDecorationListener(cardDecorationListener: CardDecorationListener) {
        swipeHelper.removeCardDecorationListener(cardDecorationListener)
    }

    /**
     * 移除卡片回退动画状态监听器
     */
    fun removeCardRewindAnimationStateListener() {
        swipeHelper.removeCardRewindAnimationStateListener()
    }

    /**
     * 移除卡片对状态变化监听器
     *
     * @param onCardPairStateChangeListener 要移除的卡片对状态变化监听器
     */
    fun removeOnCardPairStateChangeListener(onCardPairStateChangeListener: OnCardPairStateChangeListener) {
        swipeHelper.removeOnCardPairStateChangeListener(onCardPairStateChangeListener)
    }

    /**
     * 移除子视图附加状态变化后布局监听器
     *
     * @param onChildAttachStateChangePostLayoutListeners 要移除的子视图附加状态变化后布局监听器
     */
    fun removeOnChildAttachStateChangeListenerPostLayout(onChildAttachStateChangePostLayoutListeners: OnChildAttachStateChangePostLayoutListeners) {
        layoutManager.removeOnChildAttachStateChangeListenerPostLayout(onChildAttachStateChangePostLayoutListeners)
    }

    /**
     * 移除触摸事件分发监听器
     *
     * @param onDispatchTouchEventListener 要移除的触摸事件分发监听器
     */
    fun removeOnDispatchTouchEventListener(onDispatchTouchEventListener: OnDispatchTouchEventListener) {
        dispatchTouchEventListeners.remove(onDispatchTouchEventListener)
    }

    /**
     * 移除顶部卡片移动监听器
     *
     * @param onTopCardMovedListener 要移除的顶部卡片移动监听器
     */
    fun removeTopCardMovedListener(onTopCardMovedListener: OnTopCardMovedListener) {
        swipeHelper.removeTopCardMovedListener(onTopCardMovedListener)
    }

    /**
     * 恢复冻结的正在动画的卡片
     */
    fun resumeFrozenAnimatingCards() {
        swipeHelper.resumePausedAnimations()
    }

    /**
     * 回退冻结的正在动画的卡片
     */
    fun revertFrozenAnimatingCards() {
        swipeHelper.revertPausedAnimations()
    }

    /**
     * 回退最后一张动画的卡片
     *
     * @return 是否成功回退
     */
    fun revertLastAnimatedCard(): Boolean {
        return swipeHelper.revertLastCardAnimation()
    }

    /**
     * 设置卡片回退动画状态监听器
     *
     * @param cardRewindAnimationStateListener 卡片回退动画状态监听器
     */
    fun setCardRewindAnimationStateListener(cardRewindAnimationStateListener: CardRewindAnimationStateListener) {
        swipeHelper.setCardRewindAnimationStateListener(cardRewindAnimationStateListener)
    }

    /**
     * 设置布局管理器
     *
     * @param layoutManager 布局管理器
     */
    override fun setLayoutManager(layoutManager: LayoutManager?) {
        if (layoutManager is CardStackLayoutManager) {
            super.setLayoutManager(layoutManager)
            return
        }
        throw IllegalStateException("LayoutManager must extend from " + CardStackLayoutManager::class.java)
    }

    /**
     * 设置卡片呈现监听器
     *
     * @param onCardPresentedListener 卡片呈现监听器
     */
    fun setOnCardPresentedListener(onCardPresentedListener: OnCardPresentedListener?) {
        this.cardPresentedListener = onCardPresentedListener
    }

    /**
     * 设置卡片滑动前监听器
     *
     * @param onPreSwipeListener 卡片滑动前监听器
     */
    override fun setOnPreSwipeListener(onPreSwipeListener: OnPreSwipeListener) {
        swipeHelper.setOnPreSwipeListener(PreSwipeListenerAdapter(onPreSwipeListener))
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attributeSet 属性集
     */
    /**
     * 构造函数
     *
     * @param context 上下文
     */
    init {
        this.dispatchTouchEventListeners = CopyOnWriteArrayList<OnDispatchTouchEventListener?>()
        clipChildren = false
        itemAnimator = null
        val cardViewAdapter = CardViewAdapter<Card<*>>()
        cardViewAdapter.setHasStableIds(false)
        setAdapter(cardViewAdapter)
        val cardStackLayoutManager: CardStackLayoutManager = CardStackLayoutManager(this)
        this.layoutManager = cardStackLayoutManager
        cardStackLayoutManager.setOnCardPresentedListener(CardPresentedListenerImpl(this, null))
        setLayoutManager(cardStackLayoutManager)
        this.swipeHelper = CardStackSwipeHelper(this)
    }

    /**
     * 添加卡片装饰监听器
     *
     * @param view 目标视图
     * @param cardDecorationListener 卡片装饰监听器
     */
    override fun addCardDecorationListener(view: View, cardDecorationListener: CardDecorationListener) {
        swipeHelper.addCardDecorationListener(view, cardDecorationListener)
    }

    /**
     * 移除卡片装饰监听器
     *
     * @param view 目标视图
     * @param cardDecorationListener 要移除的卡片装饰监听器
     */
    override fun removeCardDecorationListener(view: View, cardDecorationListener: CardDecorationListener) {
        swipeHelper.removeCardDecorationListener(view, cardDecorationListener)
    }
}