package com.rong.litswipecard.cardstack.animation

import com.rong.litswipecard.cardstack.utils.ViewHelper

/**
 * 向右滑动的动画实现
 * 定义了卡片向右滑动时的旋转角度和移动距离
 */
open class SwipeRightAnimation : SwipeAnimation() {
    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun endRotation(): Float {
        // 定义滑动结束时的旋转角度（顺时针18度）
        return 18.0f
    }

    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun endX(): Float {
        // 使用ViewHelper中定义的水平滑动距离常量作为最终X轴偏移
        return ViewHelper.HORIZONTAL_SWIPE_DISTANCE
    }

    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun startRotation(): Float {
        // 初始没有旋转
        return 0.0f
    }
}