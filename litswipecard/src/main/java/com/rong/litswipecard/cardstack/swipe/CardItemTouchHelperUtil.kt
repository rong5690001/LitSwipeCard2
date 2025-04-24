package com.rong.litswipecard.cardstack.swipe

import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import androidx.core.view.size

/**
 * 卡片项触摸辅助工具类
 * 提供卡片触摸交互相关的辅助方法
 */
class CardItemTouchHelperUtil {
    /**
     * 获取视图在RecyclerView中的索引
     * @param view 目标视图
     * @param recyclerView RecyclerView实例
     * @return 视图索引，如果未找到则返回负值
     */
    private fun getChildViewIndex(view: View, recyclerView: RecyclerView): Int {
        val indexOfChild: Int = recyclerView.indexOfChild(view)
        if (indexOfChild < 0) {
            Timber.w("getChildViewIndex::for:index=$indexOfChild for view: $view", arrayOfNulls<Any>(0))
        }
        return indexOfChild
    }

    /**
     * 检查是否应该执行滑动动画
     * @param touchPointer 触摸指针
     * @param velocityTracker 速度追踪器
     * @param swipeThresholdDetector 滑动阈值检测器
     * @return 如果应该执行滑动动画返回true，否则返回false
     */
    fun shouldPerformSwipeAnimation(touchPointer: TouchPointer, velocityTracker: VelocityTracker, swipeThresholdDetector: SwipeThresholdDetector): Boolean {
        touchPointer.pointerId
        velocityTracker.computeCurrentVelocity(swipeThresholdDetector.velocityTrackingUnits)
        return swipeThresholdDetector.isSwipeThresholdCrossed(
            touchPointer.dragX,
            touchPointer.dragY,
            velocityTracker.xVelocity,
            velocityTracker.yVelocity
        )
    }

    /**
     * 检查移动是否低于阈值
     * @param touchPointer 触摸指针
     * @param swipeThresholdDetector 滑动阈值检测器
     * @return 如果移动低于阈值返回true，否则返回false
     */
    fun isBelowThreshold(touchPointer: TouchPointer, swipeThresholdDetector: SwipeThresholdDetector): Boolean {
        return swipeThresholdDetector.isBelowThreshold(touchPointer.dragX, touchPointer.dragY)
    }

    /**
     * 寻找可选择的视图持有者
     * @param motionEvent 触摸事件
     * @param recyclerView RecyclerView实例
     * @param cardAnimator 卡片动画控制器
     * @return 视图持有者，如果未找到则返回null
     */
    protected fun findSelectableViewHolder(motionEvent: MotionEvent, recyclerView: RecyclerView, cardAnimator: CardAnimator): RecyclerView.ViewHolder? {
        var cardAnimation: CardAnimation? = null
        val x: Float = motionEvent.getX()
        val y: Float = motionEvent.getY()
        var childCount: Int = recyclerView.size

        do {
            childCount--
            if (childCount >= 0) {
                val childView: View = recyclerView.getChildAt(childCount)
                val viewHolder: RecyclerView.ViewHolder = recyclerView.findContainingViewHolder(childView)!!
                cardAnimation = cardAnimator.findCardAnimation(childView)

                if (cardAnimation != null) {
                    if (hitTest(childView, x, y, cardAnimation.currX, cardAnimation.currY)) {
                        if (cardAnimation.animationType === CardAnimation.AnimationType.SWIPE_OUT) {
                            return null
                        }
                        return viewHolder
                    }
                } else if (hitTest(childView, x, y, childView.x, childView.y)) {
                    return viewHolder
                }
            }
            return null
        } while (cardAnimation?.animationType == CardAnimation.AnimationType.SWIPE_OUT)

        return null
    }

    /**
     * 检查是否准备好接受滑动操作
     * @param viewHolder 视图持有者
     * @param recyclerView RecyclerView实例
     * @param cardAnimator 卡片动画控制器
     * @return 如果准备好接受滑动操作返回true，否则返回false
     */
    fun isReadyToAcceptSwipes(viewHolder: RecyclerView.ViewHolder, recyclerView: RecyclerView, cardAnimator: CardAnimator): Boolean {
        Timber.d("isReadyToAcceptSwipes: 开始检查是否准备好接受滑动")
        val viewIndex = getChildViewIndex(viewHolder.itemView, recyclerView)
        Timber.d("isReadyToAcceptSwipes: viewIndex=%d, recyclerView.size=%d", viewIndex, recyclerView.size)
        
        if (viewIndex < 0) {
            Timber.w("isReadyToAcceptSwipes: 索引小于0，viewHolder=%s", viewHolder.toString())
            return false
        }

        if (viewIndex < 0) {
            Timber.w("isReadyToAcceptSwipes: 重复检查，索引小于0")
            return false
        }

        for (i in recyclerView.size - 1 downTo viewIndex) {
            Timber.d("isReadyToAcceptSwipes: 检查索引i=%d", i)
            val cardAnimation: CardAnimation? = cardAnimator.findCardAnimation(recyclerView.getChildAt(i))
            if (cardAnimation != null) {
                Timber.d("isReadyToAcceptSwipes: 找到卡片动画，类型=%s", cardAnimation.animationType)
                if (cardAnimation.animationType === CardAnimation.AnimationType.SWIPE_OUT) {
                    // 有正在执行滑出动画的卡片
                    Timber.d("isReadyToAcceptSwipes: 有正在执行滑出动画的卡片，返回false")
                }
            } else if (i == viewIndex) {
                Timber.d("isReadyToAcceptSwipes: 当前索引等于目标索引且无动画，返回true")
                return true
            }
            Timber.d("isReadyToAcceptSwipes: 循环中提前返回false")
            return false
        }

        Timber.d("isReadyToAcceptSwipes: 循环结束后返回true")
        return true
    }

    companion object {
        /**
         * 检查点是否在视图范围内
         * @param view 目标视图
         * @param x 检查点的X坐标
         * @param y 检查点的Y坐标
         * @param viewX 视图左上角X坐标
         * @param viewY 视图左上角Y坐标
         * @return 如果点在视图范围内返回true，否则返回false
         */
        fun hitTest(view: View, x: Float, y: Float, viewX: Float, viewY: Float): Boolean {
            return x >= viewX && x <= viewX + (view.width.toFloat()) && y >= viewY && y <= viewY + (view.height.toFloat())
        }
    }
}