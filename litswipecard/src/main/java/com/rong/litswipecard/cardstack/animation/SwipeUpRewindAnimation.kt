package com.rong.litswipecard.cardstack.animation

import com.rong.litswipecard.cardstack.utils.ViewHelper

/**
 * 向上滑动回弹动画实现
 * 定义了卡片从上方回弹到中心位置的动画参数
 */
class SwipeUpRewindAnimation : SwipeAnimation() {
    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun startY(): Float {
        // 回弹开始时位于屏幕上方（负值表示在屏幕上方）
        return ViewHelper.screenHeight * (-1.0f)
    }
}