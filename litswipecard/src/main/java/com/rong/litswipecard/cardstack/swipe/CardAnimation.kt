package com.rong.litswipecard.cardstack.swipe

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.PointF
import android.view.animation.AccelerateInterpolator
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import kotlin.math.max

/**
 * 卡片动画类
 * 负责管理卡片的滑动和恢复动画效果
 */
open class CardAnimation protected constructor(
    val viewHolder: RecyclerView.ViewHolder,
    val animationType: AnimationType?,
    val firstTouchPoint: PointF?,
    private val initialStartX: Float,
    private val initialStartY: Float,
    private val endX: Float,
    private val endY: Float,
    startRotation: Float,
    endRotation: Float,
    startAlpha: Float,
    endAlpha: Float
) : Animator.AnimatorListener {
    
    private val valueAnimator: ValueAnimator
    private val listeners: MutableSet<Animator.AnimatorListener> = HashSet(4)
    var hasAlphaAnimation: Boolean = false
    private val initialRotation: Float
    private val endRotation: Float
    private var initialAlpha = 0f
    private var endAlpha = 0f
    private var state: State
    
    var currX: Float = 0f
        private set
    var currY: Float = 0f
        private set
    var currRotation: Float = 0f
        private set
    
    private var currentAlpha: Float
    var duration = 180L
        set(value) {
            field = value
            valueAnimator.duration = value
        }
    private var fraction = 0f
    private var hasRotation = false
    var isFlaggedForRemoval: Boolean = false

    /**
     * 动画类型枚举
     */
    enum class AnimationType {
        /** 恢复到原位的动画 */
        RECOVER,
        /** 滑出屏幕的动画 */
        SWIPE_OUT
    }

    /**
     * 动画状态枚举
     */
    private enum class State {
        /** 已初始化 */
        INITIALIZED,
        /** 正在运行 */
        RUNNING,
        /** 已完成 */
        FINISHED
    }

    /**
     * 动画更新监听器
     */
    internal inner class AnimatorUpdateListenerImpl : AnimatorUpdateListener {
        override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
            this@CardAnimation.updateAnimation(valueAnimator.animatedFraction)
        }
    }

    init {
        this.state = State.INITIALIZED
        val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
        this.valueAnimator = animator
        animator.addUpdateListener(AnimatorUpdateListenerImpl())
        animator.interpolator = AccelerateInterpolator()
        animator.setDuration(this.duration)
        animator.setTarget(viewHolder.itemView)
        animator.addListener(this)
        updateAnimation(0.0f)
        
        if (startRotation != -2.1474836E9f && endRotation != -2.1474836E9f) {
            this.hasRotation = true
        } else {
            check(startRotation == endRotation) { "Invalid Rotation values: startRotation=$startRotation::endRotation$endRotation" }
        }
        
        this.initialRotation = startRotation
        this.endRotation = endRotation
        this.currentAlpha = viewHolder.itemView.alpha
        
        if (startAlpha == Float.MIN_VALUE || endAlpha == Float.MIN_VALUE) {
            this.hasAlphaAnimation = false
            this.initialAlpha = Float.MIN_VALUE
            this.endAlpha = Float.MIN_VALUE
        } else {
            this.hasAlphaAnimation = true
            this.initialAlpha = startAlpha
            this.endAlpha = endAlpha
        }
    }

    /**
     * 更新动画进度
     */
    private fun updateAnimation(newFraction: Float) {
        this.fraction = newFraction
    }

    /**
     * 通知监听器动画已取消
     */
    private fun notifyAnimationCancel(animator: Animator) {
        val iterator = listeners.iterator()
        while (iterator.hasNext()) {
            (iterator.next() as Animator.AnimatorListener).onAnimationCancel(animator)
        }
        listeners.clear()
    }

    /**
     * 通知监听器动画已结束
     */
    private fun notifyAnimationEnd(animator: Animator) {
        val iterator = listeners.iterator()
        while (iterator.hasNext()) {
            (iterator.next() as Animator.AnimatorListener).onAnimationEnd(animator)
        }
        listeners.clear()
    }

    /**
     * 通知监听器动画已开始
     */
    private fun notifyAnimationStart(animator: Animator) {
        val iterator = listeners.iterator()
        while (iterator.hasNext()) {
            (iterator.next() as Animator.AnimatorListener).onAnimationStart(animator)
        }
    }

    /**
     * 添加动画监听器
     */
    fun addListener(animatorListener: Animator.AnimatorListener) {
        if (this.state != State.FINISHED) {
            listeners.add(animatorListener)
            return
        }
        Timber.w("Attaching Listener after animation is over::$this")
    }

    /**
     * 添加动画更新监听器
     */
    fun addUpdateListener(animatorUpdateListener: AnimatorUpdateListener) {
        valueAnimator.addUpdateListener(animatorUpdateListener)
    }

    /**
     * 取消动画
     */
    fun cancel() {
        valueAnimator.cancel()
    }

    /**
     * 获取结束X坐标
     */
    fun getEndX(): Float {
        return this.endX
    }

    /**
     * 获取结束Y坐标
     */
    fun getEndY(): Float {
        return this.endY
    }

    /**
     * 获取起始X坐标
     */
    fun getStartX(): Float {
        return this.initialStartX
    }

    /**
     * 获取起始Y坐标
     */
    fun getStartY(): Float {
        return this.initialStartY
    }

    /**
     * 获取当前透明度
     */
    val currAlpha: Float
        get() = if (this.hasAlphaAnimation) this.currentAlpha else viewHolder.itemView.alpha

    /**
     * 是否有旋转动画
     */
    fun hasRotation(): Boolean {
        return this.hasRotation
    }

    /**
     * 检查动画是否正在运行
     */
    val isRunning: Boolean
        get() = this.state == State.RUNNING

    /**
     * 设置是否有旋转动画
     */
    fun setHasRotation(hasRotation: Boolean) {
        this.hasRotation = hasRotation
    }

    /**
     * 创建动画的副本
     */
    fun copy(): CardAnimation {
        updateProperties()
        val currentX = this.currX
        val currentY = this.currY
        val targetX = this.endX
        val targetY = this.endY
        val remainingDuration = this.duration
        val remainingTime = max(0.0, (remainingDuration - ((this.fraction * remainingDuration).toLong())).toDouble()).toLong()
        val animationCopy = CardAnimation(this.viewHolder, this.animationType!!, this.firstTouchPoint!!, currentX, currentY, targetX, targetY)
        animationCopy.duration = remainingTime
        return animationCopy
    }

    /**
     * 更新动画属性
     */
    fun updateProperties() {
        val startX = this.initialStartX
        val targetX = this.endX
        if (startX == targetX) {
            this.currX = ViewCompat.getTranslationX(viewHolder.itemView)
        } else {
            this.currX = startX + (this.fraction * (targetX - startX))
        }
        
        val startY = this.initialStartY
        val targetY = this.endY
        if (startY == targetY) {
            this.currY = ViewCompat.getTranslationY(viewHolder.itemView)
        } else {
            this.currY = startY + (this.fraction * (targetY - startY))
        }
        
        if (hasRotation()) {
            val startRotation = this.initialRotation
            val targetRotation = this.endRotation
            if (startRotation == targetRotation) {
                this.currRotation = ViewCompat.getRotation(viewHolder.itemView)
            } else {
                this.currRotation = startRotation + (this.fraction * (targetRotation - startRotation))
            }
        }
        
        if (this.hasAlphaAnimation) {
            val startAlpha = this.initialAlpha
            val targetAlpha = this.endAlpha
            if (startAlpha == targetAlpha) {
                this.currentAlpha = ViewCompat.getAlpha(viewHolder.itemView)
            } else {
                this.currentAlpha = startAlpha + (this.fraction * (targetAlpha - startAlpha))
            }
        }
    }

    // android.animation.Animator.AnimatorListener
    override fun onAnimationCancel(animator: Animator) {
        if (isRunning) {
            viewHolder.setIsRecyclable(true)
            valueAnimator.removeAllUpdateListeners()
        }
        updateAnimation(1.0f)
        this.state = State.FINISHED
        notifyAnimationCancel(animator)
    }

    // android.animation.Animator.AnimatorListener
    override fun onAnimationEnd(animator: Animator) {
        if (isRunning) {
            viewHolder.setIsRecyclable(true)
            valueAnimator.removeAllUpdateListeners()
        }
        this.state = State.FINISHED
        notifyAnimationEnd(animator)
    }

    // android.animation.Animator.AnimatorListener
    override fun onAnimationRepeat(animator: Animator) {
        // 不需要实现
    }

    // android.animation.Animator.AnimatorListener
    override fun onAnimationStart(animator: Animator) {
        notifyAnimationStart(animator)
    }

    /**
     * 设置动画插值器
     */
    fun setInterpolator(timeInterpolator: TimeInterpolator) {
        valueAnimator.interpolator = timeInterpolator
    }

    /**
     * 开始动画
     */
    fun start() {
        this.state = State.RUNNING
        viewHolder.setIsRecyclable(false)
        updateProperties()
        valueAnimator.start()
    }

    /**
     * 获取动画的字符串表示
     */
    override fun toString(): String {
        return "[vh=" + this.viewHolder + 
               ":animationType=" + this.animationType + 
               "::sx=" + this.initialStartX + 
               "::sy=" + this.initialStartY + 
               "::ex=" + this.endX + 
               "::ey=" + this.endY + 
               "::currX=" + this.currX + 
               "::currY=" + this.currY + 
               "::duration=" + this.duration + 
               "::aimationState=" + this.state + 
               "::flaggedForRemoval=" + this.isFlaggedForRemoval + 
               "::hasRot=" + hasRotation() + 
               "::fraction=" + this.fraction + "]"
    }

    /**
     * 简化的构造函数，不含旋转和透明度参数
     */
    internal constructor(
        viewHolder: RecyclerView.ViewHolder, 
        animationType: AnimationType, 
        pointF: PointF, 
        startX: Float, 
        startY: Float, 
        endX: Float, 
        endY: Float
    ) : this(
        viewHolder,
        animationType,
        pointF,
        startX,
        startY,
        endX,
        endY,
        -2.1474836E9f,
        -2.1474836E9f,
        Float.MIN_VALUE,
        Float.MIN_VALUE
    )

    companion object {
        const val DEFAULT_ANIMATION_DURATION: Long = 180
        const val INVALID_ALPHA: Float = Float.MIN_VALUE
    }
}