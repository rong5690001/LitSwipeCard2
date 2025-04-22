package com.rong.litswipecard.cardstack.animation.interpolator

import android.view.animation.Interpolator
import kotlin.math.pow

/**
 * 卡片滑出插值器
 * 实现基于物理模型的滑出动画效果
 */
class SwipeOutInterpolator(// 总距离
    private val totalDistance: Float, // 初始速度
    private val initialVelocity: Float, animDuration: Long
) : Interpolator {
    private val duration: Long // 动画持续时间
    private val accelerationFactor: Double // 加速度系数

    /**
     * 创建卡片滑出插值器
     * @param distance 总滑动距离
     * @param velocity 初始速度
     * @param animDuration 动画持续时间
     */
    init {
        this.duration = animDuration
        // 计算加速度系数，使动画在指定时间内完成指定距离
        this.accelerationFactor = ((totalDistance - (initialVelocity * animDuration)) * 2.0f) / animDuration.toDouble().pow(2.0)
    }

    // android.animation.TimeInterpolator
    override fun getInterpolation(fraction: Float): Float {
        // 基于物理模型计算当前位置：初始速度 + 加速度
        return (((this.initialVelocity * fraction) +
                ((this.accelerationFactor * 0.5) * (fraction * this.duration).toDouble().pow(2.0))).toFloat()) / this.totalDistance
    }
}