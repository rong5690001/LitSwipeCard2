package com.rong.litswipecard.cardstack.cardstack

import android.graphics.PointF
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.swipe.CardAnimation

/**
 * 卡片滑出动画
 * 继承自基础卡片动画，用于处理卡片滑出屏幕的动画效果
 */
open class SwipeOutCardAnimation
/**
 * 构造卡片滑出动画
 *
 * @param viewHolder 卡片视图持有者
 * @param animationType 动画类型
 * @param pointF 动画起始点
 * @param f 起始X坐标
 * @param f2 起始Y坐标
 * @param f3 结束X坐标
 * @param f4 结束Y坐标
 * @param f5 起始旋转角度
 * @param f6 结束旋转角度
 * @param f7 起始透明度
 * @param f8 结束透明度
 */
internal constructor(viewHolder: RecyclerView.ViewHolder, animationType: AnimationType?, pointF: PointF?, f: Float, f2: Float, f3: Float, f4: Float, f5: Float, f6: Float, f7: Float, f8: Float) :
    CardAnimation(viewHolder, animationType, pointF, f, f2, f3, f4, f5, f6, f7, f8)