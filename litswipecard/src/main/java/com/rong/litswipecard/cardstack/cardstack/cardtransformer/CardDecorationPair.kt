package com.rong.litswipecard.cardstack.cardstack.cardtransformer

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.model.SwipeDirection
import com.rong.litswipecard.cardstack.swipe.SwipeThresholdDetector
import com.rong.litswipecard.cardstack.view.CardDecorationListener
import com.rong.litswipecard.cardstack.view.CardStackLayout
import timber.log.Timber
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * 卡片装饰对
 * 负责处理两张相邻卡片之间的视觉转换效果
 * 当顶部卡片移动时，会相应地调整下方卡片的缩放和Z轴位置
 */
internal class CardDecorationPair(
    /** 底部卡片视图  */
    @JvmField val lowerCardView: View,
    /** 顶部卡片视图  */
    @JvmField val upperCardView: View, cardStackLayout: CardStackLayout, swipeThresholdDetector: SwipeThresholdDetector
) :
    CardDecorationListener {
    /**
     * 获取底部卡片视图
     * @return 底部卡片视图
     */

    /**
     * 获取顶部卡片视图
     * @return 顶部卡片视图
     */

    /** 卡片堆栈布局  */
    private val cardStackLayout: CardStackLayout = cardStackLayout

    /** 滑动阈值检测器  */
    private val swipeThresholdDetector: SwipeThresholdDetector = swipeThresholdDetector

    /**
     * 构造卡片装饰对
     * @param lowerView 底部卡片视图
     * @param upperView 顶部卡片视图
     * @param cardStackLayout 卡片堆栈布局
     * @param swipeThresholdDetector 滑动阈值检测器
     */
    init {
        // 为顶部卡片添加装饰监听器
        cardStackLayout.addCardDecorationListener(upperCardView, this)


        // 确定卡片在堆栈中的位置索引
        val upperCardIndex = if (cardStackLayout.indexOfChild(upperCardView) == cardStackLayout.getChildCount() - 1) 0 else 1


        // 应用初始变换
        applyTransform(upperCardView, cardTransforms.getTransformForView(upperCardIndex))
        applyTransform(lowerCardView, cardTransforms.getTransformForView(upperCardIndex + 1))
    }

    /**
     * 根据卡片在适配器中的位置应用变换
     * @param view 目标视图
     */
    private fun applyAdapterPositionTransform(view: View) {
        applyTransform(view, cardTransforms.getTransformForView(cardStackLayout.getChildAdapterPosition(view)))
    }

    private val cardTransforms: CardTransforms
        /**
         * 获取卡片变换管理器
         * @return 卡片变换管理器
         */
        get() = cardStackLayout.cardTransforms

    /**
     * 检查卡片索引是否有效
     * @param viewGroup 视图组
     */
    private fun checkCardIndices(viewGroup: ViewGroup) {
        val layoutParams: RecyclerView.LayoutParams = upperCardView.layoutParams as RecyclerView.LayoutParams


        // 检查索引是否有效
        if (viewGroup.indexOfChild(this.lowerCardView) <= 0 && viewGroup.indexOfChild(this.upperCardView) != 1 && viewGroup.indexOfChild(this.lowerCardView) != 0) {
            Timber.w(
                "Invalid index:: check Implementation::high=" +
                        viewGroup.indexOfChild(this.upperCardView) +
                        "::low=" + viewGroup.indexOfChild(this.lowerCardView) +
                        "::h=" + upperCardView.hashCode() +
                        "::l=" + lowerCardView.hashCode()
            )
        }


        // 检查底部卡片是否仍在视图层次结构中
        if (!layoutParams.isItemRemoved() || viewGroup.indexOfChild(this.lowerCardView) >= 0) {
            return
        }

        Timber.w(
            "::high=" + viewGroup.indexOfChild(this.upperCardView) +
                    "::low=" + viewGroup.indexOfChild(this.lowerCardView) +
                    "::childcount=" + viewGroup.getChildCount() +
                    "::h=" + upperCardView.hashCode() +
                    "::l=" + lowerCardView.hashCode()
        )
    }

    /**
     * 更新顶部卡片的Z轴位置
     * @param progress 滑动进度（0-1之间）
     */
    private fun updateUpperCardZ(progress: Float) {
        val topCardTransform = cardTransforms.getTransformForView(0)
        ViewCompat.setTranslationZ(
            this.upperCardView,
            linearInterpolate(progress, 0.0f, topCardTransform.startTranslationZ, 1.0f, topCardTransform.endTranslationZ)
        )
    }

    /**
     * 将变换应用到视图
     * @param view 目标视图
     * @param transform 卡片变换
     */
    private fun applyTransform(view: View, transform: CardTransform) {
        applyViewProperties(view, transform.startScale, transform.startScale, transform.startTranslationZ)
    }

    /**
     * 应用视图属性
     * @param view 目标视图
     * @param scaleX X轴缩放
     * @param scaleY Y轴缩放
     * @param translationZ Z轴平移
     */
    private fun applyViewProperties(view: View, scaleX: Float, scaleY: Float, translationZ: Float) {
        ViewCompat.setScaleX(view, scaleX)
        ViewCompat.setScaleY(view, scaleY)
        ViewCompat.setTranslationZ(view, translationZ)
    }

    /**
     * 销毁卡片装饰对
     * 移除监听器并重置卡片变换
     */
    fun destroy() {
        cardStackLayout.removeCardDecorationListener(this.upperCardView, this)


        // 检查卡片是否仍在适配器中
        val upperCardStillValid: Boolean = cardStackLayout.getChildAdapterPosition(this.upperCardView) >= 0
        val lowerCardStillValid: Boolean = cardStackLayout.getChildAdapterPosition(this.lowerCardView) >= 0


        // 重置有效卡片的变换
        if (upperCardStillValid) {
            applyAdapterPositionTransform(this.upperCardView)
        }
        if (lowerCardStillValid) {
            applyAdapterPositionTransform(this.lowerCardView)
        }
    }

    override fun onDecorationDraw(
        canvas: Canvas, view: View, viewGroup: ViewGroup,
        translationX: Float, translationY: Float, rotation: Float,
        swipeDirection: SwipeDirection, isThresholdExceeded: Boolean, isTouchActive: Boolean
    ) {
        // 计算滑动进度（0-1之间）

        val swipeProgress = min(
            1.0,
            sqrt(translationX.toDouble().pow(2.0) + translationY.toDouble().pow(2.0)) /
                    swipeThresholdDetector.minThresholdForSwipe
        ).toFloat()


        // 检查卡片索引
        checkCardIndices(viewGroup)


        // 更新顶部卡片Z轴位置
        updateUpperCardZ(swipeProgress)


        // 获取下一张卡片的变换并应用
        val nextCardTransform = cardTransforms.getTransformForView(1)


        // 计算底部卡片的缩放和Z轴位置
        val lowerCardScale = linearInterpolate(swipeProgress, 0.0f, nextCardTransform.startScale, 1.0f, nextCardTransform.endScale)
        val lowerCardZ = linearInterpolate(swipeProgress, 0.0f, nextCardTransform.startTranslationZ, 1.0f, nextCardTransform.endTranslationZ)


        // 应用到底部卡片
        ViewCompat.setScaleX(this.lowerCardView, lowerCardScale)
        ViewCompat.setScaleY(this.lowerCardView, lowerCardScale)
        ViewCompat.setTranslationZ(this.lowerCardView, lowerCardZ)
    }

    override fun onDecorationDrawOver(
        canvas: Canvas, view: View, viewGroup: ViewGroup,
        translationX: Float, translationY: Float, rotation: Float,
        swipeDirection: SwipeDirection, isThresholdExceeded: Boolean, isTouchActive: Boolean
    ) {
        // 在装饰绘制完成后执行的操作，此处不需要实现
    }

    override fun toString(): String {
        return "[low=" + lowerCardView.hashCode() + ", high=" + upperCardView.hashCode() + "]"
    }

    companion object {
        /**
         * 线性插值计算
         * 根据给定的值和范围，计算对应的插值结果
         *
         * @param value 当前值
         * @param fromMin 源范围最小值
         * @param fromMax 源范围最大值
         * @param toMin 目标范围最小值
         * @param toMax 目标范围最大值
         * @return 插值结果
         */
        private fun linearInterpolate(value: Float, fromMin: Float, fromMax: Float, toMin: Float, toMax: Float): Float {
            return (((toMax - toMin) / (fromMax - fromMin)) * (value - fromMin)) + toMin
        }
    }
}