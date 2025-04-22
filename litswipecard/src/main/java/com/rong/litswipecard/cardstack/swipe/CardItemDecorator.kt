package com.rong.litswipecard.cardstack.swipe

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.view.CardDecorationListener
import com.rong.litswipecard.cardstack.model.SwipeDirection
import com.rong.litswipecard.cardstack.cardstack.SwipeOutCardAnimation

/**
 * 卡片项装饰器
 * 负责处理卡片的视觉效果装饰和动画
 */
abstract class CardItemDecorator(private val cardAnimator: CardAnimator) : RecyclerView.ItemDecoration() {
    private var decorationListener: CardDecorationListener = defaultListener

    /**
     * 空实现的装饰监听器
     */
    internal class EmptyDecorationListener : CardDecorationListener {
        override fun onDecorationDraw(
            canvas: Canvas,
            view: View,
            viewGroup: ViewGroup,
            translationX: Float,
            translationY: Float,
            rotation: Float,
            swipeDirection: SwipeDirection,
            isMoving: Boolean,
            isThresholdCrossed: Boolean
        ) {
            // 空实现
        }

        override fun onDecorationDrawOver(
            canvas: Canvas,
            view: View,
            viewGroup: ViewGroup,
            translationX: Float,
            translationY: Float,
            rotation: Float,
            swipeDirection: SwipeDirection,
            isMoving: Boolean,
            isThresholdCrossed: Boolean
        ) {
            // 空实现
        }
    }

    /**
     * 应用变换和旋转的装饰
     */
    private fun applyTransformWithRotation(canvas: Canvas, view: View, recyclerView: RecyclerView, translationX: Float, translationY: Float, alpha: Float, rotation: Float, isRecovering: Boolean, isThresholdCrossed: Boolean) {
        ViewCompat.setTranslationX(view, translationX)
        ViewCompat.setTranslationY(view, translationY)
        ViewCompat.setRotation(view, rotation)
        if (ViewCompat.getAlpha(view) != alpha) {
            ViewCompat.setAlpha(view, alpha)
        }
        applyDecorationDraw(canvas, view, recyclerView, translationX, translationY, rotation, isRecovering, isThresholdCrossed)
    }

    /**
     * 应用变换但不旋转的装饰
     */
    private fun applyTransformWithoutRotation(canvas: Canvas, view: View, recyclerView: RecyclerView, translationX: Float, translationY: Float, alpha: Float, isRecovering: Boolean, isThresholdCrossed: Boolean) {
        ViewCompat.setTranslationX(view, translationX)
        ViewCompat.setTranslationY(view, translationY)
        if (ViewCompat.getAlpha(view) != alpha) {
            ViewCompat.setAlpha(view, alpha)
        }
        applyDecorationDraw(canvas, view, recyclerView, translationX, translationY, ViewCompat.getRotation(view), isRecovering, isThresholdCrossed)
    }

    /**
     * 应用装饰绘制结束
     */
    private fun applyDecorationDrawOver(canvas: Canvas, view: View, viewGroup: ViewGroup, translationX: Float, translationY: Float, rotation: Float, isRecovering: Boolean, isThresholdCrossed: Boolean) {
        decorationListener.onDecorationDrawOver(canvas, view, viewGroup, translationX, translationY, rotation, getDirectionFromMovement(translationX, translationY), isRecovering, isThresholdCrossed)
    }

    /**
     * 应用装饰绘制
     */
    private fun applyDecorationDraw(canvas: Canvas, view: View, viewGroup: ViewGroup, translationX: Float, translationY: Float, rotation: Float, isRecovering: Boolean, isThresholdCrossed: Boolean) {
        decorationListener.onDecorationDraw(canvas, view, viewGroup, translationX, translationY, rotation, getDirectionFromMovement(translationX, translationY), isRecovering, isThresholdCrossed)
    }

    /**
     * 获取当前活动的触摸指针
     */
    protected abstract val activeTouchPointer: TouchPointer?

    /**
     * 从移动距离获取滑动方向
     */
    protected abstract fun getDirectionFromMovement(dx: Float, dy: Float): SwipeDirection

    /**
     * 计算旋转角度
     */
    protected abstract fun getRotation(view: View, dx: Float, dy: Float): Float

    /**
     * 在RecyclerView绘制期间绘制装饰
     */
    override fun onDraw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, recyclerView, state)
        val activeTouchPointer = activeTouchPointer
        for (cardAnimation in cardAnimator.getActiveAnimations()) {
            if (activeTouchPointer != null && cardAnimation.viewHolder === activeTouchPointer.viewHolder) {
                releaseActiveTouchPointer()
            }
            cardAnimation.updateProperties()
            val currAlpha = cardAnimation.currAlpha
            if (cardAnimation.animationType === CardAnimation.AnimationType.RECOVER) {
                val view = cardAnimation.viewHolder.itemView
                applyTransformWithRotation(
                    canvas,
                    view,
                    recyclerView,
                    cardAnimation.currX,
                    cardAnimation.currY,
                    currAlpha,
                    if (cardAnimation.hasRotation()) cardAnimation.currRotation else getRotation(view, cardAnimation.currX, cardAnimation.firstTouchPoint?.y ?: 0f),
                    true,
                    false
                )
            } else if (cardAnimation is SwipeOutCardAnimation) {
                applyTransformWithRotation(
                    canvas,
                    cardAnimation.viewHolder.itemView,
                    recyclerView,
                    cardAnimation.currX,
                    cardAnimation.currY,
                    currAlpha,
                    cardAnimation.currRotation,
                    false,
                    false
                )
            } else {
                applyTransformWithoutRotation(canvas, cardAnimation.viewHolder.itemView, recyclerView, cardAnimation.currX, cardAnimation.currY, currAlpha, false, false)
            }
            if (cardAnimation.hasRotation() && !cardAnimation.isRunning) {
                cardAnimation.setHasRotation(false)
            }
        }
        val activePointer = this.activeTouchPointer
        if (activePointer != null) {
            val view2 = activePointer.viewHolder.itemView
            applyTransformWithRotation(
                canvas,
                view2,
                recyclerView,
                activePointer.dragX,
                activePointer.dragY,
                view2.alpha,
                getRotation(view2, activePointer.dragX, activePointer.firstTouchPoint.y),
                false,
                true
            )
        }
    }

    /**
     * 在所有内容之上绘制装饰
     */
    override fun onDrawOver(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        var isAnythingAnimating: Boolean
        super.onDrawOver(canvas, recyclerView, state)
        val iterator = cardAnimator.getActiveAnimations().iterator()
        while (true) {
            if (!iterator.hasNext()) {
                isAnythingAnimating = false
                break
            }
            val cardAnimation = iterator.next() as CardAnimation
            val view = cardAnimation.viewHolder.itemView
            isAnythingAnimating = true
            applyDecorationDrawOver(
                canvas,
                view,
                recyclerView,
                ViewCompat.getTranslationX(view),
                ViewCompat.getTranslationY(view),
                ViewCompat.getRotation(view),
                cardAnimation.animationType === CardAnimation.AnimationType.RECOVER,
                false
            )
            if (cardAnimation.isRunning) {
                break
            }
        }
        val activePointer = activeTouchPointer
        if (activePointer != null) {
            val view2 = activePointer.viewHolder.itemView
            applyDecorationDrawOver(
                canvas,
                view2,
                recyclerView,
                ViewCompat.getTranslationX(view2),
                ViewCompat.getTranslationY(view2),
                getRotation(view2, activePointer.dragX, activePointer.firstTouchPoint.y),
                false,
                true
            )
        }
        if (isAnythingAnimating) {
            recyclerView.invalidate()
        }
    }

    /**
     * 释放当前活动的触摸指针
     */
    protected abstract fun releaseActiveTouchPointer()

    /**
     * 设置卡片装饰监听器
     */
    fun setCardDecorationListener(cardDecorationListener: CardDecorationListener) {
        this.decorationListener = cardDecorationListener
    }

    companion object {
        private val defaultListener: CardDecorationListener = EmptyDecorationListener()
    }
}