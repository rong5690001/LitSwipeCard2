package com.rong.litswipecard.cardstack.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.model.Card

/**
 * 卡片视图适配器
 * 管理卡片数据和视图之间的绑定关系
 */
class CardViewAdapter<T : Card<*>?> : RecyclerView.Adapter<CardViewHolder<T>>() {
    /**
     * 卡片数据列表
     */
    private val cardList: MutableList<T> = ArrayList()

    /**
     * 卡片视图持有者工厂
     */
    private var viewHolderFactory: CardViewHolder.Factory<CardViewHolder<T>, T>? = null

    /**
     * 构造函数
     */
    init {
        setHasStableIds(true)
    }

    /**
     * 获取指定位置的卡片
     *
     * @param position 卡片位置
     * @return 卡片对象
     */
    operator fun get(position: Int): Card<*> {
        return cardList[position] as Card<*>
    }

    /**
     * 获取指定位置卡片的ID
     *
     * @param position 卡片位置
     * @return 卡片ID
     */
    override fun getItemId(position: Int): Long {
        if (position >= cardList.size) {
            return -1L
        }
        return get(position).item.hashCode().toLong()
    }

    override fun getItemCount() = cardList.size

    /**
     * 获取指定位置卡片的视图类型
     *
     * @param position 卡片位置
     * @return 视图类型
     */
    override fun getItemViewType(position: Int): Int {
        val factory: CardViewHolder.Factory<CardViewHolder<T>, T>? = this.viewHolderFactory
        if (factory != null) {
            return factory.getViewType(cardList[position])
        }
        throw IllegalStateException("getItemViewType() called without providing a " + CardViewHolder.Factory::class.java)
    }

    /**
     * 获取指定卡片的位置
     *
     * @param card 要查找的卡片
     * @return 卡片位置，如果不存在则返回-1
     */
    fun getPositionForCard(card: Card<*>): Int {
        val id = card.id
        for (i in cardList.indices) {
            if ((cardList[i] as Card<*>).id == id) {
                return i
            }
        }
        return -1
    }

    /**
     * 在指定位置插入卡片
     *
     * @param position 插入位置
     * @param card 要插入的卡片
     */
    fun insert(position: Int, card: T) {
        cardList.add(position, card)
        notifyItemInserted(position)
    }

    val isEmpty: Boolean
        /**
         * 检查卡片列表是否为空
         *
         * @return 是否为空
         */
        get() = cardList.isEmpty()

    /**
     * 绑定视图持有者和数据
     *
     * @param viewHolder 视图持有者
     * @param position 数据位置
     */
    override fun onBindViewHolder(viewHolder: CardViewHolder<T>, position: Int) {
        viewHolder.bind(cardList[position])
    }

    /**
     * 创建视图持有者
     *
     * @param viewGroup 父视图组
     * @param viewType 视图类型
     * @return 创建的视图持有者
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder<T> {
        val factory: CardViewHolder.Factory<CardViewHolder<T>, T>? = this.viewHolderFactory
        if (factory != null) {
            return factory.createViewHolder(viewGroup, viewType)
        }
        throw IllegalStateException("onCreateViewHolder() called without providing a " + CardViewHolder.Factory::class.java)
    }

    /**
     * 当视图被回收时调用
     *
     * @param viewHolder 被回收的视图持有者
     */
    override fun onViewRecycled(viewHolder: CardViewHolder<T>) {
        viewHolder.onCardViewRecycled()
    }

    /**
     * 获取顶部卡片
     *
     * @return 顶部卡片，如果列表为空则返回null
     */
    fun peek(): Card<*>? {
        if (isEmpty) {
            return null
        }
        return cardList[0]
    }

    /**
     * 移除指定位置的卡片
     *
     * @param position 要移除的位置
     */
    fun remove(position: Int) {
        cardList.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * 移除所有卡片
     */
    fun removeAll() {
        cardList.clear()
        notifyDataSetChanged()
    }

    /**
     * 设置视图持有者工厂
     *
     * @param factory 视图持有者工厂
     */
    fun setViewHolderFactory(factory: CardViewHolder.Factory<CardViewHolder<T>, T>?) {
        this.viewHolderFactory = factory
    }

    /**
     * 在指定位置插入多张卡片
     *
     * @param position 插入位置
     * @param cards 要插入的卡片列表
     */
    fun insert(position: Int, cards: List<T>) {
        cardList.addAll(position, cards)
        notifyItemRangeInserted(position, cards.size)
    }
}