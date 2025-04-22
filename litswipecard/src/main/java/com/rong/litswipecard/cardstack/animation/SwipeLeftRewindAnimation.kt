package com.rong.litswipecard.cardstack.animation

import com.rong.litswipecard.cardstack.utils.ViewHelper

/**
 * 向左滑动回弹动画实现
 * 继承自SwipeRightRewindAnimation，通过修改参数实现从左侧回弹到中心位置
 */
class SwipeLeftRewindAnimation : SwipeRightRewindAnimation() {
    // com.rong.litswipecard.cardstack.animation.SwipeRightRewindAnimation, com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun endRotation(): Float {
        // 将右滑回弹的结束旋转角度取反
        return super.endRotation() * (-1.0f)
    }

    // com.rong.litswipecard.cardstack.animation.SwipeRightRewindAnimation, com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun startRotation(): Float {
        // 将右滑回弹的开始旋转角度取反
        return super.startRotation() * (-1.0f)
    }

    // com.rong.litswipecard.cardstack.animation.SwipeRightRewindAnimation, com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun startX(): Float {
        // 设置起始位置在屏幕左侧
        return ViewHelper.screenWidth * (-1.0f)
    }
}