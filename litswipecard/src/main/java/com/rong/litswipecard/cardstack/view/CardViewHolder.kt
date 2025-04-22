package com.rong.litswipecard.cardstack.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.animation.SwipeAnimation
import com.rong.litswipecard.cardstack.model.Card
import com.rong.litswipecard.cardstack.model.Direction

/**
 * 卡片视图持有者
 * 负责关联卡片数据和视图，并处理卡片的生命周期事件
 *
 * @param <M> 卡片数据模型类型，必须继承自Card
</M> */
class CardViewHolder<M : Card<*>?>(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * 卡片视图
     */
    private var cardView: CardView<M>? = null

    /**
     * 卡片数据模型
     */
    private var cardViewModel: M? = null

    /**
     * 是否位于顶部
     */
    private var isAtTop = false

    /**
     * 视图持有者工厂接口
     * 用于创建特定类型的CardViewHolder
     */
    interface Factory<VH : CardViewHolder<*>?, T : Card<*>?> {
        /**
         * 创建视图持有者
         *
         * @param viewGroup 父视图组
         * @param viewType 视图类型
         * @return 创建的视图持有者
         */
        fun createViewHolder(viewGroup: ViewGroup?, viewType: Int): VH

        /**
         * 获取卡片对应的视图类型
         *
         * @param card 卡片数据
         * @return 视图类型
         */
        fun getViewType(card: T): Int
    }

    /**
     * 构造函数
     *
     * @param view 卡片视图
     */
    init {
        try {
            @Suppress("UNCHECKED_CAST")
            this.cardView = view as CardView<M>
        } catch (e: Exception) {
            throw IllegalArgumentException("cardView must implement ${CardView::class.java.simpleName}, but is ${view::class.java.simpleName}")
        }
    }

    /**
     * 绑定卡片数据
     *
     * @param cardModel 卡片数据模型
     */
    fun bind(cardModel: M) {
        this.cardViewModel = cardModel
        cardView!!.bind(cardModel)
    }

    /**
     * 判断卡片是否可以向指定方向拖动
     *
     * @param direction 拖动方向
     * @param cardModel 卡片数据模型
     * @return 是否可拖动
     */
    /**
     * 判断当前卡片是否可以向指定方向拖动
     *
     * @param direction 拖动方向
     * @return 是否可拖动
     */
    @JvmOverloads
    fun canDragCard(direction: Direction?, cardModel: M? = this.cardViewModel): Boolean {
        return true
    }

    val appearingAnimation: SwipeAnimation?
        /**
         * 获取卡片出现时的动画
         *
         * @return 出现动画
         */
        get() = cardViewModel!!.appearingAnimation

    val disappearingAnimation: SwipeAnimation?
        /**
         * 获取卡片消失时的动画
         *
         * @return 消失动画
         */
        get() = cardViewModel!!.disappearingAnimation

    /**
     * 判断卡片是否可滑动
     *
     * @param cardModel 卡片数据模型
     * @return 是否可滑动
     */
    fun swipable(): Boolean {
        return true
    }

    /**
     * 当卡片附加到卡片集合布局时调用
     *
     * @param cardCollectionLayout 卡片集合布局
     */
    fun onAttachedToCardCollectionLayout(cardCollectionLayout: CardCollectionLayout) {
        cardView!!.onAttachedToCardCollectionLayout(cardCollectionLayout)
    }

    /**
     * 当卡片位置变为顶部或从顶部移除时调用
     *
     * @param isAtTop 是否位于顶部
     */
    fun onCardAtTop(isAtTop: Boolean) {
        if (isAtTop && !this.isAtTop) {
            this.cardViewModel?.let { cardView!!.onMovedToTop(it) }
        } else if (!isAtTop && this.isAtTop) {
            this.cardViewModel?.let { cardView!!.onRemovedFromTop(it) }
        }
        this.isAtTop = isAtTop
    }

    /**
     * 当卡片视图被回收时调用
     */
    fun onCardViewRecycled() {
        cardView!!.onCardViewRecycled()
    }

    /**
     * 当卡片从卡片集合布局分离时调用
     *
     * @param cardCollectionLayout 卡片集合布局
     */
    fun onDetachedFromCardCollectionLayout(cardCollectionLayout: CardCollectionLayout) {
        cardView!!.onDetachedFromCardCollectionLayout(cardCollectionLayout)
    }

    val isSwipable: Boolean
        /**
         * 判断当前卡片是否可滑动
         *
         * @return 是否可滑动
         */
        get() = this.cardViewModel?.let { swipable() } == true
}