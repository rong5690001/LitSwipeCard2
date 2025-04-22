package com.rong.litswipecard.cardstack.view

/**
 * 卡片视图接口
 * 定义卡片视图的基本行为和生命周期方法
 *
 * @param <T> 卡片数据类型
</T> */
interface CardView<T> {
    /**
     * 绑定卡片数据
     *
     * @param cardData 卡片数据
     */
    fun bind(cardData: T)

    /**
     * 当卡片附加到卡片集合布局时调用
     *
     * @param cardCollectionLayout 卡片集合布局
     */
    fun onAttachedToCardCollectionLayout(cardCollectionLayout: CardCollectionLayout)

    /**
     * 当卡片视图被回收时调用
     */
    fun onCardViewRecycled()

    /**
     * 当卡片从卡片集合布局分离时调用
     *
     * @param cardCollectionLayout 卡片集合布局
     */
    fun onDetachedFromCardCollectionLayout(cardCollectionLayout: CardCollectionLayout)

    /**
     * 当卡片移动到顶部时调用
     *
     * @param cardData 卡片数据
     */
    fun onMovedToTop(cardData: T)

    /**
     * 当卡片从顶部移除时调用
     *
     * @param cardData 卡片数据
     */
    fun onRemovedFromTop(cardData: T)
}