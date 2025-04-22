package com.rong.litswipecard.cardstack.view

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import com.rong.litswipecard.cardstack.model.SwipeDirection

/**
 * 卡片装饰监听器接口
 * 用于在卡片绘制过程中添加自定义装饰效果
 */
interface CardDecorationListener {
    /**
     * 在卡片正常绘制过程中的装饰回调
     *
     * @param canvas 绘制画布
     * @param view 被装饰的视图
     * @param viewGroup 父视图组
     * @param translationX X轴平移值
     * @param translationY Y轴平移值
     * @param rotation 旋转角度
     * @param swipeDirection 滑动方向
     * @param isMoving 是否正在移动
     * @param isThresholdCrossed 是否超过阈值
     */
    fun onDecorationDraw(
        canvas: Canvas,
        view: View,
        viewGroup: ViewGroup,
        translationX: Float,
        translationY: Float,
        rotation: Float,
        swipeDirection: SwipeDirection,
        isMoving: Boolean,
        isThresholdCrossed: Boolean
    )

    /**
     * 在卡片绘制完成后的额外装饰回调
     *
     * @param canvas 绘制画布
     * @param view 被装饰的视图
     * @param viewGroup 父视图组
     * @param translationX X轴平移值
     * @param translationY Y轴平移值
     * @param rotation 旋转角度
     * @param swipeDirection 滑动方向
     * @param isMoving 是否正在移动
     * @param isThresholdCrossed 是否超过阈值
     */
    fun onDecorationDrawOver(
        canvas: Canvas,
        view: View,
        viewGroup: ViewGroup,
        translationX: Float,
        translationY: Float,
        rotation: Float,
        swipeDirection: SwipeDirection,
        isMoving: Boolean,
        isThresholdCrossed: Boolean
    )
}