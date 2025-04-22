package com.rong.litswipecard.cardstack.animation

import com.rong.litswipecard.cardstack.utils.ViewHelper

/**
 * 向右滑动回弹动画实现
 * 定义了卡片从右侧回弹到中心位置的动画参数
 */
open class SwipeRightRewindAnimation : SwipeAnimation() {
    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun endRotation(): Float {
        // 回弹结束时没有旋转
        return 0.0f
    }

    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun startRotation(): Float {
        // 回弹开始时的旋转角度（顺时针18度）
        return 18.0f
    }

    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun startX(): Float {
        // 回弹开始时位于屏幕右侧
        return ViewHelper.screenWidth
    }
}