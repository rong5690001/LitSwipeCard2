package com.rong.litswipecard.cardstack.swipe

import android.graphics.PointF
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import java.util.Collections
import kotlin.math.pow
import kotlin.math.sqrt
import androidx.core.view.ViewCompat

/**
 * 卡片动画管理器
 * 负责管理和控制卡片的动画效果
 */
class CardAnimator {
    private var currentState = State.ACTIVE
    private val activeAnimations: MutableList<CardAnimation> = ArrayList()
    private val pausedAnimations: MutableList<CardAnimation> = ArrayList()

    /**
     * 动画器状态枚举
     */
    private enum class State {
        PAUSED,  // 暂停状态
        ACTIVE // 活动状态
    }

    /**
     * 添加一个暂停的动画
     * @param cardAnimation 要添加的动画
     */
    private fun addPausedAnimation(cardAnimation: CardAnimation) {
        if (this.currentState == State.PAUSED) {
            pausedAnimations.add(cardAnimation)
            return
        }
        Timber.w("addPausedAnimation called in Active state::" + this.currentState, arrayOfNulls<Any>(0))
    }

    /**
     * 清除所有暂停的动画
     */
    private fun clearPausedAnimations() {
        pausedAnimations.clear()
    }

    /**
     * 查找最顶层的卡片动画
     * @return 最顶层的卡片动画，如果没有则返回null
     */
    private fun findTopCardAnimation(): CardAnimation? {
        var topAnimation: CardAnimation? = null
        for (animation in this.activeAnimations) {
            if (animation.viewHolder.adapterPosition != -1 && !animation.isFlaggedForRemoval && (topAnimation == null || animation.viewHolder.adapterPosition < topAnimation.viewHolder.adapterPosition)) {
                topAnimation = animation
            }
        }
        return topAnimation
    }

    /**
     * 添加一个活动的动画
     * @param cardAnimation 要添加的动画
     */
    fun addActiveAnimation(cardAnimation: CardAnimation) {
        if (this.currentState == State.PAUSED) {
            Timber.w("addActiveAnimation called in paused state::" + this.currentState, arrayOfNulls<Any>(0))
            return
        }
        if (findCardAnimation(cardAnimation.viewHolder) != null) {
            Timber.w("Animation already exists for a view::Check implementation", arrayOfNulls<Any>(0))
        } else {
            activeAnimations.add(cardAnimation)
        }
    }

    /**
     * 获取所有活动的动画
     * @return 活动动画列表的不可修改视图
     */
    fun getActiveAnimations(): List<CardAnimation> {
        return Collections.unmodifiableList(this.activeAnimations)
    }

    /**
     * 结束指定视图持有者的卡片动画
     * @param viewHolder 视图持有者
     */
    fun endCardAnimation(viewHolder: RecyclerView.ViewHolder) {
        val it2 = activeAnimations.iterator()
        while (it2.hasNext()) {
            val animation = it2.next()
            if (animation.viewHolder === viewHolder) {
                it2.remove()
                if (animation.isRunning) {
                    animation.cancel()
                }
            }
        }
        val it3 = pausedAnimations.iterator()
        while (it3.hasNext()) {
            val animation = it3.next()
            if (animation.viewHolder === viewHolder) {
                it3.remove()
                animation.cancel()
            }
        }
    }

    /**
     * 根据视图查找卡片动画
     * @param view 要查找的视图
     * @return 关联的卡片动画，如果没有则返回null
     */
    fun findCardAnimation(view: View?): CardAnimation? {
        for (animation in this.activeAnimations) {
            if (animation.viewHolder.itemView === view) {
                return animation
            }
        }
        return null
    }

    /**
     * 获取所有暂停的动画
     * @return 暂停动画列表的不可修改视图
     */
    fun getPausedAnimations(): List<CardAnimation> {
        return Collections.unmodifiableList(this.pausedAnimations)
    }

    val isInPausedState: Boolean
        /**
         * 判断当前是否处于暂停状态
         * @return 如果处于暂停状态则返回true，否则返回false
         */
        get() = this.currentState == State.PAUSED

    /**
     * 暂停所有动画
     */
    fun pauseAnimations() {
        val state = this.currentState
        val state2 = State.PAUSED
        if (state == state2) {
            Timber.w("pauseAnimations called in Paused State::" + this.currentState, arrayOfNulls<Any>(0))
            return
        }
        this.currentState = state2
        val it2 = activeAnimations.iterator()
        while (it2.hasNext()) {
            val animation = it2.next()
            if (!animation.isFlaggedForRemoval) {
                val savedAnimation: CardAnimation = animation.copy()
                it2.remove()
                if (animation.isRunning) {
                    animation.cancel()
                }
                addPausedAnimation(savedAnimation)
            }
        }
    }

    /**
     * 恢复所有暂停的动画
     */
    fun resumePausedAnimations() {
        if (this.currentState != State.PAUSED) {
            Timber.w("Called resumePausedAnimations in Active state:" + this.currentState, arrayOfNulls<Any>(0))
            return
        }
        this.currentState = State.ACTIVE
        for (animation in this.pausedAnimations) {
            addActiveAnimation(animation)
            animation.start()
        }
        clearPausedAnimations()
    }

    /**
     * 恢复最后一个卡片的动画
     * @return 如果成功恢复了动画则返回true，否则返回false
     */
    fun revertLastCardAnimation(): Boolean {
        val topAnimation = findTopCardAnimation() ?: return false
        val view = topAnimation.viewHolder.itemView
        val firstTouchPoint: PointF? = topAnimation.firstTouchPoint
        val translationX = view.translationX
        val translationY = view.translationY
        if (translationX == 0.0f && translationY == 0.0f) {
            return false
        }
        val viewHolder: RecyclerView.ViewHolder = topAnimation.viewHolder
        endCardAnimation(viewHolder)
        val cardAnimation = firstTouchPoint?.let { CardAnimation(viewHolder, CardAnimation.AnimationType.RECOVER, it, translationX, translationY, 0.0f, 0.0f) }
        if (cardAnimation != null) {
            addActiveAnimation(cardAnimation)
        }
        cardAnimation?.start()
        return true
    }

    /**
     * 恢复所有暂停的动画到初始状态
     */
    fun revertPausedAnimations() {
        if (this.currentState != State.PAUSED) {
            Timber.w("Called revertPausedAnimations in when not in paused state:" + this.currentState, arrayOfNulls<Any>(0))
            return
        }
        this.currentState = State.ACTIVE
        for (animation in this.pausedAnimations) {
            val startX: Float = animation.getStartX()
            val startY: Float = animation.getStartY()
            val duration = ((animation.duration * sqrt(
                startX.toDouble().pow(2.0) + startY.toDouble().pow(2.0)
            )) / sqrt((startX - animation.getEndX()).pow(2.0f) + (startY - animation.getEndY()).pow(2.0f))).toLong()
            val recoverAnimation = CardAnimation(animation.viewHolder, CardAnimation.AnimationType.RECOVER, animation.firstTouchPoint!!, startX, startY, 0.0f, 0.0f)
            recoverAnimation.duration = duration
            addActiveAnimation(recoverAnimation)
            recoverAnimation.start()
        }
        clearPausedAnimations()
    }

    /**
     * 开始一个恢复动画
     * @param viewHolder 视图持有者
     * @param recyclerView RecyclerView实例
     * @param touchPoint 触摸点
     */
    fun startRecoverAnimation(viewHolder: RecyclerView.ViewHolder, recyclerView: RecyclerView, touchPoint: PointF) {
        val view: View = viewHolder.itemView
        val cardAnimation = CardAnimation(viewHolder, CardAnimation.AnimationType.RECOVER, touchPoint, ViewCompat.getTranslationX(view), ViewCompat.getTranslationY(view), 0.0f, 0.0f)
        addActiveAnimation(cardAnimation)
        cardAnimation.start()
        recyclerView.invalidate()
    }

    /**
     * 根据视图持有者查找卡片动画
     * @param viewHolder 要查找的视图持有者
     * @return 关联的卡片动画，如果没有则返回null
     */
    fun findCardAnimation(viewHolder: RecyclerView.ViewHolder): CardAnimation? {
        for (animation in this.activeAnimations) {
            if (animation.viewHolder.itemView === viewHolder.itemView) {
                return animation
            }
        }
        return null
    }

    /**
     * 处理触摸结束事件
     * @param itemView 卡片项视图
     */
    fun onTouchEnd(itemView: View?) {
        // 触摸结束的处理逻辑
    }
}