package com.rong.litswipecard.cardstack.cardstack

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.PointF
import android.view.View
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
import timber.log.Timber


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
    private var rewindAnimationListener: CardRewindAnimationStateListener = DefaultRewindAnimationListener()
    private var pendingAnimationsCount = 0

    private inner class AnimationEndListener(private val holder: RecyclerView.ViewHolder) : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            dispatchAnimationFinished(holder)
            pendingAnimationsCount--
        }
    }

    private inner class CardAnimationWrapper(
        viewHolder: RecyclerView.ViewHolder,
        animationType: AnimationType,
        pointF: PointF,
        startTranslationX: Float,
        startTranslationY: Float,
        targetTranslationX: Float,
        targetTranslationY: Float,
        startRotation: Float,
        endRotation: Float,
        startAlpha: Float,
        endAlpha: Float,
        private val holder: RecyclerView.ViewHolder
    ) : CardAnimation(
        viewHolder,
        animationType,
        pointF,
        startTranslationX,
        startTranslationY,
        targetTranslationX,
        targetTranslationY,
        startRotation,
        endRotation,
        startAlpha,
        endAlpha
    ) {
        override fun onAnimationEnd(animator: Animator) {
            super.onAnimationEnd(animator)
            pendingAnimationsCount--
            this@CardStackItemAnimator.dispatchAnimationFinished(holder)
        }
    }

    private inner class SwipeOutAnimationWrapper(
        viewHolder: RecyclerView.ViewHolder,
        animationType: AnimationType,
        pointF: PointF,
        startTranslationX: Float,
        startTranslationY: Float,
        targetTranslationX: Float,
        targetTranslationY: Float,
        startRotation: Float,
        endRotation: Float,
        startAlpha: Float,
        endAlpha: Float,
        private val holder: RecyclerView.ViewHolder
    ) : SwipeOutCardAnimation(
        viewHolder,
        animationType,
        pointF,
        startTranslationX,
        startTranslationY,
        targetTranslationX,
        targetTranslationY,
        startRotation,
        endRotation,
        startAlpha,
        endAlpha
    ) {
        override fun onAnimationEnd(animator: Animator) {
            super.onAnimationEnd(animator)
            pendingAnimationsCount--
            this@CardStackItemAnimator.dispatchAnimationFinished(holder)
        }
    }

    private class DefaultRewindAnimationListener : CardRewindAnimationStateListener {
        override fun onRewindAnimationStarted(view: View?) {}
        override fun onRewindAnimationProgress(view: View?, progress: Float) {}
        override fun onRewindAnimationEnded(view: View?) {}
    }


    private inner class RewindAnimationListener(private val view: View) : AnimatorListenerAdapter(), ValueAnimator.AnimatorUpdateListener {
        override fun onAnimationStart(animation: Animator) {
            rewindAnimationListener.onRewindAnimationStarted(view)
        }

        override fun onAnimationEnd(animation: Animator) {
            rewindAnimationListener.onRewindAnimationEnded(view)
        }

        override fun onAnimationCancel(animation: Animator) {
            rewindAnimationListener.onRewindAnimationEnded(view)
        }

        override fun onAnimationRepeat(animation: Animator) {}

        override fun onAnimationUpdate(animation: ValueAnimator) {
            rewindAnimationListener.onRewindAnimationProgress(view, animation.animatedFraction)
        }
    }

    private fun getAppearingAnimation(holder: RecyclerView.ViewHolder): SwipeAnimation? {
        return if (holder is CardViewHolder<*>) {
            holder.appearingAnimation
        } else null
    }

    private fun getDisappearingAnimation(holder: RecyclerView.ViewHolder): SwipeAnimation? {
        return if (holder is CardViewHolder<*>) {
            holder.disappearingAnimation
        } else null
    }

    private fun isRewindAnimation(animation: SwipeAnimation): Boolean {
        return animation is SwipeLeftRewindAnimation ||
               animation is SwipeRightRewindAnimation ||
               animation is SwipeUpRewindAnimation
    }

    private fun handleDisappearingAnimation(holder: RecyclerView.ViewHolder, animation: SwipeAnimation) {
        Timber.d("handleDisappearingAnimation:: start, holder position=%d", holder.adapterPosition)
        pendingAnimationsCount++
        val view = holder.itemView
        var translationX = ViewCompat.getTranslationX(view)
        val translationY = ViewCompat.getTranslationY(view)
        var rotation = ViewCompat.getRotation(view)

        if (translationX == 0f) translationX = animation.startX()
        val startX = translationX
        val endX = animation.endX()
        val startY = if (translationY != 0f) translationY else animation.startY()
        val endY = animation.endY()
        if (rotation == 0f) rotation = animation.startRotation()
        val startRotation = rotation
        val endRotation = if (animation.endRotation() != -2.1474836E9f || startRotation == -2.1474836E9f)
            animation.endRotation() else startRotation
        val cardAnimation = SwipeOutAnimationWrapper(
            holder,
            AnimationType.SWIPE_OUT,
            PointF(startX, startY),
            startX,
            startY,
            endX,
            endY,
            startRotation,
            endRotation,
            animation.startAlpha(),
            animation.endAlpha(),
            holder
        ).apply {
            if (animation.durationMilli() > 0) {
                duration = animation.durationMilli().toLong()
            }
            animation.interpolator()?.let { setInterpolator(it) }
            isFlaggedForRemoval = true
            addListener(AnimationEndListener(holder))
        }

        Timber.d("handleDisappearingAnimation:: created animation with isFlaggedForRemoval=true")
        cardAnimator.addActiveAnimation(cardAnimation)
        cardAnimation.start()
        Timber.d("handleDisappearingAnimation:: end, animation started")
    }

    override fun animateAppearance(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo?,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        val cardAnimation: CardAnimation
        val findCardAnimation = this.cardAnimator.findCardAnimation(viewHolder)
        val appearingAnimation = getAppearingAnimation(viewHolder)
        if (findCardAnimation != null) {
            this.cardAnimator.endCardAnimation(viewHolder)
        }
        ViewCompat.setAlpha(viewHolder.itemView, 1.0f)
        if (appearingAnimation == null) {
            dispatchAnimationFinished(viewHolder)
            return false
        }
        this.pendingAnimationsCount++
        val startX = appearingAnimation.startX()
        val startY = appearingAnimation.startY()
        val bVar =
            CardAnimationWrapper(viewHolder, AnimationType.RECOVER, PointF(startX, startY), startX, startY, appearingAnimation.endX(), appearingAnimation.endY(), appearingAnimation.startRotation(), appearingAnimation.endRotation(), appearingAnimation.startAlpha(), appearingAnimation.endAlpha(), viewHolder)
        if (appearingAnimation.durationMilli() > 0) {
            bVar.duration = appearingAnimation.durationMilli().toLong()
        }
        val interpolator = appearingAnimation.interpolator()
        if (interpolator != null) {
            bVar.setInterpolator(interpolator)
        }
        if (isRewindAnimation(appearingAnimation)) {
            cardAnimation = bVar
            val eVar = RewindAnimationListener(viewHolder.itemView)
            cardAnimation.addListener(eVar)
            cardAnimation.addUpdateListener(eVar)
        } else {
            cardAnimation = bVar
        }
        this.cardAnimator.addActiveAnimation(cardAnimation)
        cardAnimation.start()
        return true
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        dispatchAnimationFinished(oldHolder)
        if (oldHolder === newHolder) return false
        dispatchAnimationFinished(newHolder)
        return false
    }

    override fun animateDisappearance(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo,
        postLayoutInfo: ItemHolderInfo?
    ): Boolean {
        Timber.d("animateDisappearance:: start, viewHolder position=%d", viewHolder.adapterPosition)
        val findCardAnimation = this.cardAnimator.findCardAnimation(viewHolder)
        val disappearingAnimation = getDisappearingAnimation(viewHolder)
        
        Timber.d("animateDisappearance:: findCardAnimation=%s, disappearingAnimation=%s", 
            findCardAnimation?.animationType, disappearingAnimation?.javaClass?.simpleName)

        if (findCardAnimation == null || disappearingAnimation == null) {
            if (disappearingAnimation != null) {
                Timber.d("animateDisappearance:: handling disappearing animation")
                handleDisappearingAnimation(viewHolder, disappearingAnimation)
                return true
            }
            if (findCardAnimation != null && findCardAnimation.isRunning) {
                Timber.d("animateDisappearance:: ending running card animation")
                this.cardAnimator.endCardAnimation(viewHolder)
            }
            ViewCompat.setAlpha(viewHolder.itemView, 0.0f)
            dispatchAnimationFinished(viewHolder)
            return false
        }
        Timber.d("animateDisappearance:: flagging animation for removal")
        findCardAnimation.isFlaggedForRemoval = true
        if (findCardAnimation.isRunning && findCardAnimation.animationType == AnimationType.SWIPE_OUT) {
            Timber.d("animateDisappearance:: adding end listener to running swipe out animation")
            this.pendingAnimationsCount++
            findCardAnimation.addListener(AnimationEndListener(viewHolder))
            return true
        }
        if (!findCardAnimation.isRunning && findCardAnimation.animationType == AnimationType.RECOVER) {
            Timber.d("animateDisappearance:: ending non-running recover animation and handling disappearing")
            this.cardAnimator.endCardAnimation(findCardAnimation.viewHolder)
            handleDisappearingAnimation(viewHolder, disappearingAnimation)
            return false
        }
        if (findCardAnimation.isRunning && findCardAnimation.animationType == AnimationType.RECOVER) {
            Timber.d("animateDisappearance:: ending running recover animation and handling disappearing")
            this.cardAnimator.endCardAnimation(viewHolder)
            handleDisappearingAnimation(viewHolder, disappearingAnimation)
            return false
        }
        Timber.d("animateDisappearance:: ending card animation and finishing")
        this.cardAnimator.endCardAnimation(viewHolder)
        dispatchAnimationFinished(findCardAnimation.viewHolder)
        return false
    }

    override fun animatePersistence(
        holder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        dispatchAnimationFinished(holder)
        return false
    }

    override fun runPendingAnimations() {}

    override fun endAnimation(holder: RecyclerView.ViewHolder) {
        cardAnimator.endCardAnimation(holder)
    }

    override fun endAnimations() {

    }

    override fun isRunning(): Boolean = pendingAnimationsCount > 0

    fun setRewindAnimationListener(listener: CardRewindAnimationStateListener) {
        this.rewindAnimationListener = listener
    }

    fun resetRewindAnimationListener() {
        this.rewindAnimationListener = DefaultRewindAnimationListener()
    }
}