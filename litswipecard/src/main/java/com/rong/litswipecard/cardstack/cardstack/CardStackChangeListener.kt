package com.rong.litswipecard.cardstack.cardstack

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
import com.rong.litswipecard.cardstack.swipe.CardAnimation
import com.rong.litswipecard.cardstack.swipe.CardAnimator
import com.rong.litswipecard.cardstack.swipe.TouchPointer
import com.rong.litswipecard.cardstack.view.OnChildAttachStateChangePostLayoutListeners

/**
 * 卡片堆栈变化监听器抽象基类
 * 监听卡片视图的附加和分离事件，处理相关的动画和状态转换
 */
internal abstract class CardStackChangeListener
/**
 * 构造卡片堆栈变化监听器
 * @param cardAnimator 卡片动画器
 */(
    /** 卡片动画器  */
    private val cardAnimator: CardAnimator
) : OnChildAttachStateChangeListener, OnChildAttachStateChangePostLayoutListeners {
    /**
     * 检查视图是否标记为已移除
     * @param view 要检查的视图
     * @return 如果视图已标记为移除则返回true
     */
    private fun isItemRemoved(view: View): Boolean {
        // 检查布局参数中的移除标记
        if ((view.layoutParams as RecyclerView.LayoutParams).isItemRemoved) {
            return true
        }
        // 检查卡片动画中的移除标记
        val animation = cardAnimator.findCardAnimation(view)
        return animation != null && animation.isFlaggedForRemoval
    }

    /**
     * 检查触摸指针是否指向顶部卡片
     * @param touchPointer 触摸指针
     * @return 如果触摸指针指向顶部卡片则返回true
     */
    private fun isTouchPointerOnTopCard(touchPointer: TouchPointer): Boolean {
        val recyclerView = recyclerView
        // 从末尾向前遍历子视图，找到第一个未移除的视图
        for (i in recyclerView.childCount - 1 downTo 0) {
            val childView = recyclerView.getChildAt(i)
            if (!(childView.layoutParams as RecyclerView.LayoutParams).isItemRemoved) {
                // 检查这个视图是否是触摸指针指向的视图
                return childView === touchPointer.viewHolder.itemView
            }
        }
        return false
    }

    /**
     * 开始恢复动画
     * @param touchPointer 触摸指针
     * @param recyclerView 回收视图
     */
    private fun startRecoverAnimation(touchPointer: TouchPointer, recyclerView: RecyclerView) {
        cardAnimator.startRecoverAnimation(touchPointer.viewHolder, recyclerView, touchPointer.firstTouchPoint)
    }

    /**
     * 获取视图的ViewHolder
     * @param view 视图
     * @return 视图对应的ViewHolder
     */
    abstract fun getViewHolder(view: View): RecyclerView.ViewHolder?

    /**
     * 获取RecyclerView实例
     * @return RecyclerView实例
     */
    abstract val recyclerView: RecyclerView

    /**
     * 获取当前触摸指针
     * @return 触摸指针，如果没有则返回null
     */
    abstract val touchPointer: TouchPointer?

    /**
     * 设置触摸指针的分离状态
     * @param isDetached 如果触摸指针已分离则为true
     */
    abstract fun setTouchPointerDetached(isDetached: Boolean)

    override fun onChildViewAttachedPostLayout(view: View?) {
        // 获取当前触摸指针
        val touchPointer = touchPointer


        // 如果没有触摸指针或指针指向的是顶部卡片，不执行任何操作
        if (touchPointer == null || isTouchPointerOnTopCard(touchPointer)) {
            return
        }


        // 如果触摸指针指向的卡片未标记为移除，执行恢复动画
        if (!isItemRemoved(touchPointer.viewHolder.itemView)) {
            startRecoverAnimation(touchPointer, recyclerView)
        }


        // 重置触摸指针的分离状态
        setTouchPointerDetached(false)
    }

    override fun onChildViewAttachedToWindow(view: View) {
        // 默认不执行任何操作
    }

    override fun onChildViewDetachedFromWindow(view: View) {
        // 尝试获取视图的ViewHolder
        var viewHolder = getViewHolder(view)


        // 如果无法直接获取，尝试从卡片动画中获取
        var animation: CardAnimation? = null
        if (viewHolder == null) {
            animation = cardAnimator.findCardAnimation(view)
            if (animation != null) {
                viewHolder = animation.viewHolder
            }
        }


        // 如果仍然无法获取ViewHolder，不执行任何操作
        if (viewHolder == null) {
            return
        }


        // 获取当前触摸指针
        val touchPointer = touchPointer


        // 如果没有触摸指针或触摸指针不指向当前分离的视图，结束卡片动画
        if (touchPointer == null || touchPointer.viewHolder.itemView !== view) {
            cardAnimator.endCardAnimation(viewHolder)
        } else {
            // 标记触摸指针为已分离状态
            setTouchPointerDetached(true)
        }
    }

    override fun onChildViewDetachedPostLayout(view: View?) {
        // 默认不执行任何操作
    }
}