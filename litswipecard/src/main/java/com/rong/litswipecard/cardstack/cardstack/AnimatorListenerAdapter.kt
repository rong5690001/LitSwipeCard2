package com.rong.litswipecard.cardstack.cardstack

import android.animation.Animator

/**
 * 动画监听器适配器
 * 提供动画事件监听的基础实现，子类可以只覆盖需要的方法
 */
internal abstract class AnimatorListenerAdapter : Animator.AnimatorListener {
    // android.animation.Animator.AnimatorListener
    override fun onAnimationCancel(animator: Animator) {
    }

    // android.animation.Animator.AnimatorListener
    override fun onAnimationEnd(animator: Animator) {
    }

    // android.animation.Animator.AnimatorListener
    override fun onAnimationRepeat(animator: Animator) {
    }

    // android.animation.Animator.AnimatorListener
    override fun onAnimationStart(animator: Animator) {
    }
}