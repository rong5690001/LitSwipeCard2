package com.rong.litswipecard.cardstack.cardstack

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.view.CardStackLayout
import com.rong.litswipecard.cardstack.view.CardViewHolder
import com.rong.litswipecard.cardstack.view.OnChildAttachStateChangePostLayoutListeners
import timber.log.Timber
import java.util.ArrayDeque
import kotlin.math.min

/**
 * 卡片堆叠布局管理器
 * 负责管理卡片堆叠视图的布局逻辑，处理卡片的添加、移除和布局
 */
class CardStackLayoutManager(cardStackLayout: CardStackLayout) : RecyclerView.LayoutManager() {
    /** 关联的卡片堆叠布局  */
    private val cardStackLayout: CardStackLayout = cardStackLayout
    /**
     * 检查是否正在布局过程中
     * @return 如果正在布局过程中则返回true
     */
    /** 标记是否正在布局过程中  */
    var isInLayout: Boolean = false
        private set

    /** 后布局阶段的子视图附加状态变化监听器列表  */
    private val postLayoutListeners: MutableList<OnChildAttachStateChangePostLayoutListeners> = ArrayList()

    /** 布局过程中的子视图附加状态变化事件队列  */
    private val pendingAttachStateEvents: MutableList<AttachStateChangeEvent> = ArrayList()

    /** 卡片宽度  */
    private var cardWidth = Int.MIN_VALUE

    /** 卡片高度  */
    private var cardHeight = Int.MIN_VALUE

    /** 当前顶部卡片  */
    private var topCard: View? = null

    /** 卡片呈现监听器  */
    private var cardPresentedListener: OnCardPresentedListener = object : OnCardPresentedListener {
        override fun onCardPresented(view: View) {
            // 默认空实现
        }
    }

    /**
     * 卡片呈现监听器接口
     * 当新卡片被展示在顶部时触发
     */
    interface OnCardPresentedListener {
        /**
         * 当卡片被呈现在顶部时调用
         * @param view 呈现的卡片视图
         */
        fun onCardPresented(view: View)
    }

    /**
     * 子视图附加状态变化监听器
     * 处理子视图附加和分离的事件
     */
    private inner class ChildAttachStateChangeListener : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            // 通知CardViewHolder它已被附加到卡片收集布局
            val cardViewHolder: CardViewHolder<*> = cardStackLayout.getChildViewHolder(view) as CardViewHolder<*>
            if (cardViewHolder != null) {
                cardViewHolder.onAttachedToCardCollectionLayout(this@CardStackLayoutManager.cardStackLayout)
            }


            // 如果当前正在布局中，将事件添加到队列，否则直接处理
            if (this@CardStackLayoutManager.isInLayout) {
                pendingAttachStateEvents.add(AttachStateChangeEvent(view, true))
            } else {
                this@CardStackLayoutManager.notifyChildAttached(view)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            // 通知CardViewHolder它已从卡片收集布局分离
            val cardViewHolder: CardViewHolder<*> = cardStackLayout.getChildViewHolder(view) as CardViewHolder<*>
            if (cardViewHolder != null) {
                cardViewHolder.onCardAtTop(false)
                cardViewHolder.onDetachedFromCardCollectionLayout(this@CardStackLayoutManager.cardStackLayout)
            }


            // 如果分离的是顶部卡片，清除顶部卡片引用
            if (view === this@CardStackLayoutManager.topCard) {
                this@CardStackLayoutManager.topCard = null
            }


            // 如果当前正在布局中，将事件添加到队列，否则直接处理
            if (this@CardStackLayoutManager.isInLayout) {
                pendingAttachStateEvents.add(AttachStateChangeEvent(view, false))
            } else {
                this@CardStackLayoutManager.notifyChildDetached(view)
            }
        }
    }

    /**
     * 是否为附加事件
     * @return 如果是附加事件返回true，否则返回false
     */
    /**
     * 附加状态变化事件类
     * 用于在布局过程中缓存子视图的附加状态变化事件
     */
    private class AttachStateChangeEvent(
        /** 相关的视图  */
        val view: View,
        /** 是否为附加事件（true为附加，false为分离）  */
        val isAttached: Boolean
    )

    /**
     * 构造卡片堆叠布局管理器
     * @param cardStackLayout 关联的卡片堆叠布局
     */
    init {
        cardStackLayout.addOnChildAttachStateChangeListener(ChildAttachStateChangeListener())
        setItemPrefetchEnabled(false)
    }

    /**
     * 布局子视图
     * 从Recycler获取视图并添加到布局中
     *
     * @param recycler 回收器
     * @param state 状态
     */
    private fun layoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        // 最多显示2张卡片，或者可用的卡片数量
        val cardCount = min(2.0, getItemCount().toDouble()).toInt()
        val viewStack = ArrayDeque<View>(cardCount)


        // 准备所有需要的卡片视图
        for (i in 0..<cardCount) {
            // 如果尺寸未初始化，先初始化
            if (needsMeasurement()) {
                initCardMeasurements(recycler)
            }

            // 获取卡片视图并测量
            val cardView: View = recycler.getViewForPosition(i)
            cardView.layoutDirection = layoutDirection
            measureChildWithMargins(cardView, 0, 0)
            viewStack.push(cardView)
            val cardViewHolder = cardStackLayout.getChildViewHolder(cardView) as CardViewHolder<*>
            val cardViewModel = cardViewHolder.cardViewModel
            if (cardViewModel != null) {
                Timber.d("layoutChildren: pushView CardViewModel = %s", cardViewModel.toString())
            } else {
                Timber.d("layoutChildren: pushView CardViewModel is null for ViewHolder: %s", getCanonicalClassName(cardViewHolder))
            }

            // 布局卡片
            layoutDecorated(cardView, 0, 0, this.cardWidth, this.cardHeight)
        }


        // 按照从下到上的顺序添加卡片视图
        while (!viewStack.isEmpty()) {
            val cardView = viewStack.pop()
            ensureNotAttachedToAnotherParent(cardView)
            addView(cardView)


            // 设置卡片状态
            val cardViewHolder: CardViewHolder<*> = cardStackLayout.getChildViewHolder(cardView) as CardViewHolder<*>
            // 打印CardViewHolder中的cardViewModel
            val cardViewModel = cardViewHolder.cardViewModel
            if (cardViewModel != null) {
                Timber.d("layoutChildren: addView CardViewModel = %s", cardViewModel.toString())
            } else {
                Timber.d("layoutChildren: addView CardViewModel is null for ViewHolder: %s", getCanonicalClassName(cardViewHolder))
            }
            if (!viewStack.isEmpty()) {
                // 不是顶部卡片
                cardViewHolder.onCardAtTop(false)
            } else if (cardView !== this.topCard) {
                // 是顶部卡片，且不同于之前的顶部卡片
                cardViewHolder.onCardAtTop(true)
                this.topCard = cardView
                cardPresentedListener.onCardPresented(cardView)
            }
        }
    }

    /**
     * 获取对象的规范类名
     * @param obj 对象
     * @return 类的规范名称，如果无法获取则返回"NA"
     */
    private fun getCanonicalClassName(obj: Any): String {
        obj.javaClass
        val canonicalName = obj.javaClass.canonicalName
        return canonicalName ?: "NA"
    }

    /**
     * 检查视图是否附加到了另一个父视图
     * @param view 要检查的视图
     * @return 如果视图附加到了其他父视图则返回true
     */
    private fun isAttachedToAnotherParent(view: View): Boolean {
        val parent: ViewParent? = view.parent
        return if (parent == null || parent === this.cardStackLayout) false else true
    }

    /**
     * 确保视图没有附加到其他父视图
     * 如果已附加，则将其从父视图中移除
     *
     * @param view 要检查的视图
     */
    private fun ensureNotAttachedToAnotherParent(view: View) {
        if (isAttachedToAnotherParent(view)) {
            logUnexpectedParent(view)
            val parent: ViewParent = view.parent
            if (parent is ViewGroup) {
                (parent as ViewGroup).removeView(view)
            }
        }
    }

    /**
     * 记录视图有意外的父视图
     * @param view 视图
     */
    private fun logUnexpectedParent(view: View) {
        val parent: ViewParent? = view.parent
        if (parent == null || parent === this.cardStackLayout) {
            return
        }
        Timber.e(RuntimeException("View has unexpected parent: ParentClassName=" + getCanonicalClassName(parent) + ", ViewClassName=" + getCanonicalClassName(view)))
    }

    /**
     * 初始化卡片测量值
     * @param recycler 回收器
     */
    private fun initCardMeasurements(recycler: RecyclerView.Recycler) {
        val cardView: View = recycler.getViewForPosition(0)
        addView(cardView)
        measureChildWithMargins(cardView, 0, 0)
        this.cardHeight = getDecoratedMeasuredHeight(cardView)
        this.cardWidth = getDecoratedMeasuredWidth(cardView)
        detachAndScrapView(cardView, recycler)
    }

    /**
     * 检查是否需要测量卡片尺寸
     * @return 如果卡片尺寸未初始化则返回true
     */
    private fun needsMeasurement(): Boolean {
        return this.cardWidth == Int.MIN_VALUE || this.cardHeight == Int.MIN_VALUE
    }

    /**
     * 通知子视图已附加
     * @param view 附加的视图
     */
    private fun notifyChildAttached(view: View) {
        val it: Iterator<OnChildAttachStateChangePostLayoutListeners> = postLayoutListeners.iterator()
        while (it.hasNext()) {
            it.next().onChildViewAttachedPostLayout(view)
        }
    }

    /**
     * 处理所有挂起的附加状态变化事件
     */
    private fun processPendingAttachStateEvents() {
        for (event in this.pendingAttachStateEvents) {
            if (event.isAttached) {
                notifyChildAttached(event.view)
            } else {
                notifyChildDetached(event.view)
            }
        }
        pendingAttachStateEvents.clear()
    }

    /**
     * 通知子视图已分离
     * @param view 分离的视图
     */
    private fun notifyChildDetached(view: View) {
        val it: Iterator<OnChildAttachStateChangePostLayoutListeners> = postLayoutListeners.iterator()
        while (it.hasNext()) {
            it.next().onChildViewDetachedPostLayout(view)
        }
    }

    /**
     * 添加后布局阶段的子视图附加状态变化监听器
     * @param listener 监听器
     */
    fun addOnChildAttachStateChangeListenerPostLayout(listener: OnChildAttachStateChangePostLayoutListeners) {
        postLayoutListeners.add(listener)
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        Timber.d("onLayoutChildren - Item count: %d", state.itemCount)
        
        if (state.itemCount == 0) {
            Timber.d("No items to layout")
            removeAndRecycleAllViews(recycler)
            return
        }

        // 确保顶部卡片始终在最上层
        val topCard = findTopCardView()
        if (topCard != null) {
            Timber.d("onLayoutChildren: Bringing top card to front, %s", topCard.toString())
            topCard.bringToFront()
        }

        this.isInLayout = true
        detachAndScrapAttachedViews(recycler)
        layoutChildren(recycler, state)
        this.isInLayout = false
    }

    override fun onLayoutCompleted(state: RecyclerView.State) {
        super.onLayoutCompleted(state)
        processPendingAttachStateEvents()
    }

    /**
     * 处理尺寸变化
     * 在卡片堆叠布局尺寸变化时调用
     *
     * @param width 新宽度
     * @param height 新高度
     * @param oldWidth 旧宽度
     * @param oldHeight 旧高度
     */
    fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        this.cardWidth = Int.MIN_VALUE
        this.cardHeight = Int.MIN_VALUE
        requestLayout()
    }

    /**
     * 移除后布局阶段的子视图附加状态变化监听器
     * @param listener 要移除的监听器
     */
    fun removeOnChildAttachStateChangeListenerPostLayout(listener: OnChildAttachStateChangePostLayoutListeners) {
        postLayoutListeners.remove(listener)
    }

    /**
     * 设置卡片呈现监听器
     * @param listener 监听器
     */
    fun setOnCardPresentedListener(listener: OnCardPresentedListener) {
        this.cardPresentedListener = listener
    }

    private fun findTopCardView(): View? {
        val childCount = childCount
        Timber.d("Finding top card - Child count: %d", childCount)
        
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child != null) {
                Timber.d("Checking child at position %d: %s", i, child.toString())
                return child
            }
        }
        Timber.d("No top card found")
        return null
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        Timber.d("scrollVerticallyBy - dy: %d", dy)
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        Timber.d("scrollHorizontallyBy - dx: %d", dx)
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    companion object {
        /**
         * 记录视图有意外的父视图
         */
        private fun defaultCardPresentedAction(view: View) {
            // 默认空实现
        }
    }
}