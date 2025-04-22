package com.rong.litswipecard.cardstack.animation

/**
 * 向左滑动的动画实现
 * 继承自SwipeRightAnimation，通过将其参数取反来实现向左滑动
 */
class SwipeLeftAnimation : SwipeRightAnimation() {
    // com.rong.litswipecard.cardstack.animation.SwipeRightAnimation, com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun endRotation(): Float {
        // 将右滑的结束旋转角度取反，实现向左旋转
        return super.endRotation() * (-1.0f)
    }

    // com.rong.litswipecard.cardstack.animation.SwipeRightAnimation, com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun endX(): Float {
        // 将右滑的结束X位置取反，实现向左移动
        return super.endX() * (-1.0f)
    }

    // com.rong.litswipecard.cardstack.animation.SwipeRightAnimation, com.rong.litswipecard.cardstack.animation.SwipeAnimation
    override fun startRotation(): Float {
        // 将右滑的开始旋转角度取反
        return super.startRotation() * (-1.0f)
    }
}