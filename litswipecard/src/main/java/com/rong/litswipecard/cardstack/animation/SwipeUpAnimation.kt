package com.rong.litswipecard.cardstack.animation

import com.rong.litswipecard.cardstack.utils.ViewHelper

/**
 * 向上滑动动画实现
 * 定义了卡片向上滑动的移动距离
 */
class SwipeUpAnimation : SwipeAnimation() {
    // com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun endY(): Float {
        // 向上滑动结束位置为负的垂直滑动距离（屏幕顶部）
        return ViewHelper.VERTICAL_SWIPE_DISTANCE * (-1.0f)
    }
}