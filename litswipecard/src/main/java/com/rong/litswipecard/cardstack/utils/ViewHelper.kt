package com.rong.litswipecard.cardstack.utils

import android.content.res.Resources

/**
 * 视图辅助工具类
 * 提供屏幕尺寸和滑动距离相关的常量及方法
 */
object ViewHelper {
    /**
     * 水平滑动距离常量
     * 定义为屏幕宽度的1.4倍，用于确定卡片水平滑动的最终位置
     */
    val HORIZONTAL_SWIPE_DISTANCE: Float = screenWidth * 1.4f

    /**
     * 垂直滑动距离常量
     * 定义为屏幕高度，用于确定卡片垂直滑动的最终位置
     */
    val VERTICAL_SWIPE_DISTANCE: Float = screenHeight

    val screenHeight: Float
        /**
         * 获取屏幕高度
         * @return 屏幕高度（像素）
         */
        get() = Resources.getSystem().displayMetrics.heightPixels.toFloat()

    val screenWidth: Float
        /**
         * 获取屏幕宽度
         * @return 屏幕宽度（像素）
         */
        get() = Resources.getSystem().displayMetrics.widthPixels.toFloat()
}