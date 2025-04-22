package com.rong.litswipecard.cardstack.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.animation.SwipeAnimation
import com.rong.litswipecard.cardstack.model.Card
import timber.log.Timber

/**
 * 卡片集合布局基类
 * 提供卡片集合的基本功能和操作
 * @param T 卡片数据类型
 */
abstract class CardCollectionLayout @JvmOverloads constructor(
    /**
     * 构造函数
     *
     * @param context 上下文
     * @param attributeSet 属性集
     */
    context: Context, 
    attributeSet: AttributeSet? = null
) : RecyclerView(context, attributeSet) {
    /**
     * 检查插入位置是否有效
     *
     * @param position 要检查的位置
     * @return 位置是否有效
     */
    private fun isValidInsertPosition(position: Int): Boolean {
        val adapter = getAdapter() ?: return position == 0 // 如果适配器为null，只有位置0是有效的
        return position >= 0 && position <= adapter.itemCount
    }

    /**
     * 检查移除位置是否有效
     *
     * @param position 要检查的位置
     * @return 位置是否有效
     */
    private fun isValidRemovePosition(position: Int): Boolean {
        val adapter = getAdapter() ?: return false // 如果适配器为null，任何位置都是无效的
        return position >= 0 && position < adapter.itemCount
    }

    /**
     * 记录无效插入位置的日志
     *
     * @param position 无效的位置
     */
    private fun logInvalidInsertPosition(position: Int) {
        val itemCount = getAdapter()?.itemCount ?: 0
        Timber.wtf("Inserting card at invalid position, position: $position, itemCount: $itemCount")
    }

    /**
     * 记录无效移除位置的日志
     *
     * @param position 无效的位置
     */
    private fun logInvalidRemovePosition(position: Int) {
        val itemCount = getAdapter()?.itemCount ?: 0
        Timber.wtf("Removing card from invalid position, position: $position, itemCount: $itemCount")
    }

    /**
     * 为视图添加卡片装饰监听器
     *
     * @param view 目标视图
     * @param cardDecorationListener 卡片装饰监听器
     */
    abstract fun addCardDecorationListener(view: View, cardDecorationListener: CardDecorationListener)

    /**
     * 检查新插入是否影响已冻结的卡片
     *
     * @param startIndex 开始插入的位置
     * @param insertCount 插入的数量
     */
    protected abstract fun checkIfNewInsertsAffectFrozenCards(startIndex: Int, insertCount: Int)

    /**
     * 在指定位置插入单张卡片
     *
     * @param position 插入位置
     * @param card 要插入的卡片
     */
    fun insertCard(position: Int, card: Card<*>) {
        if (!isValidInsertPosition(position)) {
            logInvalidInsertPosition(position)
        } else {
            checkIfNewInsertsAffectFrozenCards(position, 1)
            adapter.insert(position, card)
        }
    }

    /**
     * 在指定位置插入多张卡片
     *
     * @param position 插入位置
     * @param cards 要插入的卡片列表
     */
    fun insertCards(position: Int, cards: List<Card<*>>) {
        if (!isValidInsertPosition(position)) {
            logInvalidInsertPosition(position)
        } else {
            checkIfNewInsertsAffectFrozenCards(position, cards.size)
            adapter.insert(position, cards)
        }
    }

    val isEmpty: Boolean
        /**
         * 检查卡片集合是否为空
         *
         * @return 是否为空
         */
        get() = adapter.isEmpty

    /**
     * 获取顶部卡片
     *
     * @return 顶部卡片，如果为空则返回null
     */
    fun peek(): Card<*>? {
        return adapter.peek()
    }

    /**
     * 刷新所有卡片
     *
     * @param cards 新的卡片列表
     */
    fun refresh(cards: List<Card<*>>) {
        removeAllCards()
        insertCards(0, cards)
    }

    /**
     * 刷新顶部卡片
     */
    fun refreshTopCard() {
        adapter.notifyItemChanged(0)
    }

    /**
     * 移除所有卡片
     */
    fun removeAllCards() {
        adapter.removeAll()
    }

    /**
     * 移除指定位置的卡片
     *
     * @param position 要移除的位置
     */
    fun removeCard(position: Int) {
        if (isValidRemovePosition(position)) {
            adapter.remove(position)
        } else {
            logInvalidRemovePosition(position)
        }
    }

    /**
     * 从视图移除卡片装饰监听器
     *
     * @param view 目标视图
     * @param cardDecorationListener 要移除的卡片装饰监听器
     */
    abstract fun removeCardDecorationListener(view: View, cardDecorationListener: CardDecorationListener)

    /**
     * 设置卡片视图持有者工厂
     *
     * @param factory 卡片视图持有者工厂
     */
    fun setCardStackViewHolderFactory(factory: CardViewHolder.Factory<CardViewHolder<Card<*>>, Card<*>>) {
        adapter.setViewHolderFactory(factory)
    }

    /**
     * 设置卡片滑动前监听器
     *
     * @param onPreSwipeListener 卡片滑动前监听器
     */
    abstract fun setOnPreSwipeListener(onPreSwipeListener: OnPreSwipeListener)

    var adapter: CardViewAdapter<Card<*>>
        /**
         * 获取卡片视图适配器
         *
         * @return 卡片视图适配器
         * @throws IllegalStateException 如果适配器为空
         */
        get() {
            return super.getAdapter() as? CardViewAdapter<Card<*>>
                ?: throw IllegalStateException("Adapter is null. Have you set the Adapter?")
        }
        /**
         * 设置适配器
         *
         * @param adapter 适配器
         */
        set(adapter) {
            require(adapter is CardViewAdapter<*>) { "CardCollectionLayout only supports CardViewAdapter" }
            super.setAdapter(adapter)
        }

    /**
     * 移除指定位置的卡片，并应用动画
     *
     * @param position 要移除的位置
     * @param swipeAnimation 滑动动画
     */
    fun removeCard(position: Int, swipeAnimation: SwipeAnimation?) {
        if (!isValidRemovePosition(position)) {
            logInvalidRemovePosition(position)
            return
        }
        val adapter = adapter
        adapter[position].disappearingAnimation = swipeAnimation
        adapter.remove(position)
    }

    /**
     * 在指定位置插入卡片，并应用动画
     *
     * @param position 插入位置
     * @param card 要插入的卡片
     * @param swipeAnimation 滑动动画
     */
    fun insertCard(position: Int, card: Card<*>, swipeAnimation: SwipeAnimation) {
        if (!isValidInsertPosition(position)) {
            logInvalidInsertPosition(position)
            return
        }
        checkIfNewInsertsAffectFrozenCards(position, 1)
        card.appearingAnimation = swipeAnimation
        adapter.insert(position, card)
    }

    companion object {
        protected const val DEBUG: Boolean = false
    }
}