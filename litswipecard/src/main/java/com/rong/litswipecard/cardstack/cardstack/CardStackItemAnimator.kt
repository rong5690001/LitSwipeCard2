package com.rong.litswipecard.cardstack.cardstack

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.PointF
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.rong.litswipecard.cardstack.animation.SwipeAnimation
import com.rong.litswipecard.cardstack.animation.SwipeLeftRewindAnimation
import com.rong.litswipecard.cardstack.animation.SwipeRightRewindAnimation
import com.rong.litswipecard.cardstack.animation.SwipeUpRewindAnimation
import com.rong.litswipecard.cardstack.swipe.CardAnimation
import com.rong.litswipecard.cardstack.swipe.CardAnimation.AnimationType
import com.rong.litswipecard.cardstack.swipe.CardAnimator
import com.rong.litswipecard.cardstack.view.CardStackLayout.CardRewindAnimationStateListener
import com.rong.litswipecard.cardstack.view.CardViewHolder

/**
 * 卡片堆栈项目动画器
 * 负责管理卡片堆栈中项目的动画效果
 */
class CardStackItemAnimator
/**
 * 构造函数
 * @param cardAnimator 卡片动画器
 */(
    /** 卡片动画器  */
    private val cardAnimator: CardAnimator
) : ItemAnimator() {
    /** 类名标记，用于日志  */
    private val TAG: String = CardStackItemAnimator::class.java.simpleName

    /** 卡片回弹动画状态监听器  */
    private var rewindAnimationListener = DEFAULT_REWIND_LISTENER

    /** 运行中的动画计数器  */
    private var runningAnimationsCount = 0

    /**
     * 默认回弹动画状态监听器实现
     * 提供默认的空实现
     */
    private class DefaultRewindAnimationStateListener : CardRewindAnimationStateListener {
        override fun onRewindAnimationStarted(view: View?) {
            // 默认实现为空
        }

        override fun onRewindAnimationProgress(view: View?, progress: Float) {
            // 默认实现为空
        }

        override fun onRewindAnimationEnded(view: View?) {
            // 默认实现为空
        }
    }

    /**
     * 恢复动画类
     * 处理卡片从滑出位置恢复到原位的动画
     */
    private inner class RecoverAnimation(
        private val viewHolder: RecyclerView.ViewHolder,
        private val fromX: Float,
        private val fromY: Float,
        private val fromRotation: Float,
        private val toX: Float,
        private val toY: Float,
        private val toRotation: Float
    ) :
        Animator.AnimatorListener {
        private val animator: ValueAnimator
        private val targetView = viewHolder.itemView
        private val interpolator: TimeInterpolator = AccelerateDecelerateInterpolator()

        init {
            val valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f)
            this.animator = valueAnimator
            valueAnimator.addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                targetView.translationX =
                    this@RecoverAnimation.fromX + ((this@RecoverAnimation.toX - this@RecoverAnimation.fromX) * fraction)
                targetView.translationY =
                    this@RecoverAnimation.fromY + ((this@RecoverAnimation.toY - this@RecoverAnimation.fromY) * fraction)
                targetView.rotation =
                    this@RecoverAnimation.fromRotation + ((this@RecoverAnimation.toRotation - this@RecoverAnimation.fromRotation) * fraction)
                rewindAnimationListener.onRewindAnimationProgress(this@RecoverAnimation.targetView, fraction)
            }
            valueAnimator.setDuration(CardAnimation.DEFAULT_ANIMATION_DURATION)
            valueAnimator.interpolator = interpolator
            valueAnimator.addListener(this)
        }

        fun start() {
            animator.start()
        }

        fun cancel() {
            animator.cancel()
        }

        override fun onAnimationCancel(animator: Animator) {
            rewindAnimationListener.onRewindAnimationEnded(this.targetView)
        }

        override fun onAnimationEnd(animator: Animator) {
            rewindAnimationListener.onRewindAnimationEnded(this.targetView)
        }

        override fun onAnimationStart(animator: Animator) {
            rewindAnimationListener.onRewindAnimationStarted(this.targetView)
        }

        override fun onAnimationRepeat(animator: Animator) {
            // 不处理重复动画
        }
    }

    /**
     * 动画结束监听器
     * 在动画结束时减少运行中的动画计数并分发动画完成事件
     */
    private inner class AnimationEndListener(
        /** 目标视图持有者  */
        private val viewHolder: RecyclerView.ViewHolder
    ) : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animator: Animator) {
            super.onAnimationEnd(animator)
            decrementRunningAnimationsCount()
            this@CardStackItemAnimator.dispatchAnimationFinished(this.viewHolder)
        }
    }

    /**
     * 恢复动画类
     * 处理卡片从滑出位置恢复到原位的动画
     */
    private inner class RecoverCardAnimation(
        viewHolder: RecyclerView.ViewHolder,
        animationType: AnimationType?,
        startPoint: PointF?,
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        startRotation: Float,
        endRotation: Float,
        startAlpha: Float,
        endAlpha: Float,
        /** 目标视图持有者  */
    ) : CardAnimation(viewHolder, animationType, startPoint, startX, startY, endX, endY, startRotation, endRotation, startAlpha, endAlpha) {
        override fun onAnimationEnd(animator: Animator) {
            super.onAnimationEnd(animator)
            decrementRunningAnimationsCount()
            this@CardStackItemAnimator.dispatchAnimationFinished(this.viewHolder)
        }
    }

    /**
     * 滑出卡片动画类
     * 处理卡片滑出屏幕的动画
     */
    private inner class SwipeOutAnimation(
        viewHolder: RecyclerView.ViewHolder, animationType: AnimationType?,
        startPoint: PointF?, startX: Float, startY: Float, endX: Float, endY: Float,
        startRotation: Float, endRotation: Float, startAlpha: Float, endAlpha: Float,
    ) : SwipeOutCardAnimation(viewHolder, animationType, startPoint, startX, startY, endX, endY, startRotation, endRotation, startAlpha, endAlpha) {
        override fun onAnimationEnd(animator: Animator) {
            super.onAnimationEnd(animator)
            decrementRunningAnimationsCount()
            this@CardStackItemAnimator.dispatchAnimationFinished(this.viewHolder)
        }
    }

    /**
     * 回弹动画监听器
     * 将动画事件转发给回弹动画状态监听器
     */
    private inner class RewindAnimationListener(
        /** 目标视图  */
        private val targetView: View
    ) : AnimatorListenerAdapter(), AnimatorUpdateListener {
        override fun onAnimationCancel(animator: Animator) {
            rewindAnimationListener.onRewindAnimationEnded(this.targetView)
        }

        override fun onAnimationEnd(animator: Animator) {
            rewindAnimationListener.onRewindAnimationEnded(this.targetView)
        }

        override fun onAnimationStart(animator: Animator) {
            rewindAnimationListener.onRewindAnimationStarted(this.targetView)
        }

        override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
            rewindAnimationListener.onRewindAnimationProgress(this.targetView, valueAnimator.animatedFraction)
        }
    }

    /**
     * 增加运行中的动画计数
     */
    private fun incrementRunningAnimationsCount() {
        runningAnimationsCount++
    }

    /**
     * 减少运行中的动画计数
     * 如果所有动画都已完成，通知回弹动画完成
     */
    private fun decrementRunningAnimationsCount() {
        runningAnimationsCount--
        if (this.runningAnimationsCount <= 0) {
            rewindAnimationListener.onRewindAnimationEnded(null)
        }
    }

    /**
     * 清除回弹动画状态监听器
     * 恢复为默认监听器
     */
    fun clearRewindAnimationStateListener() {
        this.rewindAnimationListener = DEFAULT_REWIND_LISTENER
    }

    /**
     * 设置回弹动画状态监听器
     * @param listener 监听器
     */
    fun setRewindAnimationStateListener(listener: CardRewindAnimationStateListener?) {
        this.rewindAnimationListener = listener ?: DEFAULT_REWIND_LISTENER
    }

    /**
     * 获取卡片出现动画
     * @param viewHolder 视图持有者
     * @return 卡片出现动画，如果不是CardViewHolder则返回null
     */
    private fun getAppearingAnimation(viewHolder: RecyclerView.ViewHolder): SwipeAnimation? {
        if (viewHolder is CardViewHolder<*>) {
            return viewHolder.appearingAnimation
        }
        return null
    }

    /**
     * 获取卡片消失动画
     * @param viewHolder 视图持有者
     * @return 卡片消失动画，如果不是CardViewHolder则返回null
     */
    private fun getDisappearingAnimation(viewHolder: RecyclerView.ViewHolder): SwipeAnimation? {
        if (viewHolder is CardViewHolder<*>) {
            return viewHolder.disappearingAnimation
        }
        return null
    }

    /**
     * 检查是否为回弹动画
     * @param swipeAnimation 滑动动画
     * @return 如果是回弹动画则返回true
     */
    private fun isRewindAnimation(swipeAnimation: SwipeAnimation): Boolean {
        return (swipeAnimation is SwipeLeftRewindAnimation) ||
                (swipeAnimation is SwipeRightRewindAnimation) ||
                (swipeAnimation is SwipeUpRewindAnimation)
    }

    /**
     * 执行滑出动画
     * @param viewHolder 视图持有者
     * @param swipeAnimation 滑动动画
     */
    private fun performSwipeOutAnimation(viewHolder: RecyclerView.ViewHolder, swipeAnimation: SwipeAnimation) {
        runningAnimationsCount++
        val itemView = viewHolder.itemView


        // 获取当前位置和旋转
        var translationX: Float = ViewCompat.getTranslationX(itemView)
        val translationY: Float = ViewCompat.getTranslationY(itemView)
        var rotation: Float = ViewCompat.getRotation(itemView)


        // 如果没有X轴偏移，使用动画定义的起始位置
        if (translationX == 0.0f) {
            translationX = swipeAnimation.startX()
        }

        val startX = translationX
        val endX = swipeAnimation.endX()


        // 如果没有Y轴偏移，使用动画定义的起始位置
        val startY = if (translationY != 0.0f) translationY else swipeAnimation.startY()
        val endY = swipeAnimation.endY()


        // 如果没有旋转，使用动画定义的起始旋转
        if (rotation == 0.0f) {
            rotation = swipeAnimation.startRotation()
        }

        val startRotation = rotation


        // 计算结束旋转角度，如果动画没有指定则使用当前旋转
        val endRotation = if (swipeAnimation.endRotation() != -2.1474836E9f || startRotation == -2.1474836E9f)
            swipeAnimation.endRotation()
        else
            startRotation


        // 创建滑出动画
        val animation = SwipeOutAnimation(
            viewHolder,
            AnimationType.SWIPE_OUT,
            PointF(startX, startY),
            startX, startY, endX, endY,
            startRotation, endRotation,
            swipeAnimation.startAlpha(), swipeAnimation.endAlpha(),
        )


        // 设置动画持续时间
        if (swipeAnimation.durationMilli() > 0) {
            animation.duration = swipeAnimation.durationMilli().toLong()
        }


        // 设置动画插值器
        val interpolator = swipeAnimation.interpolator()
        if (interpolator != null) {
            animation.setInterpolator(interpolator)
        }

        animation.isFlaggedForRemoval = true
        cardAnimator.addActiveAnimation(animation)
        animation.start()
    }

    override fun animateAppearance(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo?, postLayoutInfo: ItemHolderInfo): Boolean {
        return false
    }

    override fun animateChange(oldHolder: RecyclerView.ViewHolder, newHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo): Boolean {
        return false
    }

    override fun animateDisappearance(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo?): Boolean {
        return false
    }

    override fun animatePersistence(viewHolder: RecyclerView.ViewHolder, preLayoutInfo: ItemHolderInfo, postLayoutInfo: ItemHolderInfo): Boolean {
        return false
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        cardAnimator.endCardAnimation(item)
    }

    override fun endAnimations() {
        // 结束所有动画
        val animations: List<CardAnimation> = ArrayList(cardAnimator.getActiveAnimations())
        for (animation in animations) {
            cardAnimator.endCardAnimation(animation.viewHolder)
        }
    }

    /**
     * 结束运行的动画
     */
    fun finish() {
        endAnimations()
    }

    override fun isRunning(): Boolean {
        return this.runningAnimationsCount > 0
    }

    override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
        super.onAnimationFinished(viewHolder)
    }

    override fun onAnimationStarted(viewHolder: RecyclerView.ViewHolder) {
        super.onAnimationStarted(viewHolder)
    }

    override fun runPendingAnimations() {
        // 空实现，所有动画都是显式启动的
    }

    companion object {
        /** 默认的卡片回弹动画状态监听器  */
        private val DEFAULT_REWIND_LISTENER: CardRewindAnimationStateListener = DefaultRewindAnimationStateListener()
    }
}