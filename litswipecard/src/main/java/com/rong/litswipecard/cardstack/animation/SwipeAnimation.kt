package com.rong.litswipecard.cardstack.animation

import android.animation.TimeInterpolator

/**
 * 滑动动画基类
 * 定义了卡片滑动动画的基本属性和方法
 */
abstract class SwipeAnimation {
    /**
     * 获取动画持续时间（毫秒）
     * 默认返回-1，表示使用默认持续时间
     */
    fun durationMilli(): Int {
        return -1
    }

    /**
     * 获取动画结束时的透明度
     * 默认返回无效值，表示不改变透明度
     */
    fun endAlpha(): Float {
        return Float.MIN_VALUE
    }

    /**
     * 获取动画结束时的旋转角度
     * 默认返回无效值，表示不改变旋转
     */
    open fun endRotation(): Float {
        return -2.1474836E9f
    }

    /**
     * 获取动画结束时的X坐标位置
     * 默认为0，表示回到原始位置
     */
    open fun endX(): Float {
        return 0.0f
    }

    /**
     * 获取动画结束时的Y坐标位置
     * 默认为0，表示回到原始位置
     */
    open fun endY(): Float {
        return 0.0f
    }

    /**
     * 获取动画的插值器
     * 默认返回null，表示使用默认线性插值器
     */
    fun interpolator(): TimeInterpolator? {
        return null
    }

    /**
     * 获取动画开始时的透明度
     * 默认返回无效值，表示不改变透明度
     */
    fun startAlpha(): Float {
        return Float.MIN_VALUE
    }

    /**
     * 获取动画开始时的旋转角度
     * 默认返回无效值，表示不改变旋转
     */
    open fun startRotation(): Float {
        return -2.1474836E9f
    }

    /**
     * 获取动画开始时的X坐标位置
     * 默认为0，表示从原始位置开始
     */
    open fun startX(): Float {
        return 0.0f
    }

    /**
     * 获取动画开始时的Y坐标位置
     * 默认为0，表示从原始位置开始
     */
    open fun startY(): Float {
        return 0.0f
    }

    /**
     * 获取动画的字符串表示形式
     * 返回动画类的简单名称
     */
    override fun toString(): String {
        return javaClass.simpleName
    }

    companion object {
        /**
         * 无效的透明度值常量
         */
        const val INVALID_ALPHA: Float = Float.MIN_VALUE

        /**
         * 无效的旋转角度常量
         */
        const val INVALID_ROTATION: Int = Int.MIN_VALUE
    }
}