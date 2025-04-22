package com.rong.litswipecard.cardstack.swipe

import android.content.Context
import android.view.View
import android.view.ViewConfiguration
import com.rong.litswipecard.R
import com.rong.litswipecard.cardstack.model.SwipeDirection
import com.rong.litswipecard.cardstack.utils.ViewHelper
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * 滑动阈值检测器
 * 负责检测和判断滑动手势是否达到触发阈值
 */
class SwipeThresholdDetector(context: Context) {
    /**
     * 获取滑动逃逸速度
     * @return 滑动逃逸速度
     */
    private val swipeEscapeVelocity: Float // 滑动逃逸速度

    /**
     * 获取触摸滑动阈值
     * @return 触摸滑动阈值
     */
    private val touchSlop: Float // 触摸滑动阈值

    /**
     * 获取最小滑动阈值
     * @return 最小滑动阈值
     */
    val minThresholdForSwipe: Float // 最小滑动阈值
    private val velocitySwipeThreshold: Float // 速度触发滑动阈值

    /**
     * 方向枚举映射助手类
     */
    private object DirectionOrdinalMap {
        val DIRECTION_ORDINALS: IntArray

        init {
            val directions = IntArray(SwipeDirection.entries.size)
            DIRECTION_ORDINALS = directions
            try {
                directions[SwipeDirection.LEFT.ordinal] = 1
            } catch (unused: NoSuchFieldError) {
            }
            try {
                DIRECTION_ORDINALS[SwipeDirection.RIGHT.ordinal] = 2
            } catch (unused2: NoSuchFieldError) {
            }
            try {
                DIRECTION_ORDINALS[SwipeDirection.UP.ordinal] = 3
            } catch (unused3: NoSuchFieldError) {
            }
            try {
                DIRECTION_ORDINALS[SwipeDirection.DOWN.ordinal] = 4
            } catch (unused4: NoSuchFieldError) {
            }
        }
    }

    /**
     * 角度常量类
     * 定义不同方向的角度范围
     */
    private object AngleConstants {
        // 右方向角度范围 (290-360度 或 0-70度)
        var rightAngleStart = 290.0f
            /**
             * 获取右方向角度上限
             * @return 右方向角度上限
             */
        var rightAngleEnd = 70.0f
            /**
             * 获取右方向角度下限
             * @return 右方向角度下限
             */
        // 左方向角度范围 (110-250度)
        var leftAngleStart = 110.0f
            /**
             * 获取左方向角度下限
             * @return 左方向角度下限
             */
        var leftAngleEnd = 250.0f
            /**
             * 获取左方向角度上限
             * @return 左方向角度上限
             */
        // 上方向角度范围 (75-105度)
        var upAngleStart = 75.0f
            /**
             * 获取上方向角度下限
             * @return 上方向角度下限
             */
        var upAngleEnd = 105.0f
            /**
             * 获取上方向角度上限
             * @return 上方向角度上限
             */

        // 下方向角度范围 (251-289度)
        var downAngleStart = 251.0f
            /**
             * 获取下方向角度下限
             * @return 下方向角度下限
             */
        var downAngleEnd = 289.0f
            /**
             * 获取下方向角度上限
             * @return 下方向角度上限
             */
    }

    /**
     * 构造函数
     * @param context 上下文
     */
    init {
        val screenWidth: Float = ViewHelper.screenWidth
        this.swipeEscapeVelocity = context.resources.getDimension(R.dimen.fling_escape_velocity_dp) * 6.0f
        val threshold = screenWidth * 0.25f
        this.minThresholdForSwipe = threshold
        this.velocitySwipeThreshold = threshold / 3.0f
        this.touchSlop = max(8.0, (ViewConfiguration.get(context).scaledTouchSlop / 2).toDouble()).toFloat()
    }

    /**
     * 从角度获取滑动方向
     * @param angle 角度 (0-360度)
     * @return 滑动方向
     */
    private fun getDirectionFromAngle(angle: Float): SwipeDirection {
        return if (isLeftAngle(angle)) SwipeDirection.LEFT else if (isRightAngle(angle)) SwipeDirection.RIGHT else if (isUpAngle(angle)) SwipeDirection.UP else if (isDownAngle(angle)) SwipeDirection.DOWN else SwipeDirection.NONE
    }

    /**
     * 获取视图一半高度
     * @param view 视图
     * @return 一半高度
     */
    private fun getHalfHeight(view: View): Float {
        return view.height / 2.0f
    }

    /**
     * 判断角度是否在下方向范围内
     * @param angle 角度
     * @return 是否是下方向
     */
    private fun isDownAngle(angle: Float): Boolean {
        return angle >= AngleConstants.downAngleStart && angle <= AngleConstants.downAngleEnd
    }

    /**
     * 判断是否是水平方向滑动
     * @param velocityX X方向速度
     * @param velocityY Y方向速度
     * @return 是否是水平滑动
     */
    private fun isHorizontalSwipe(velocityX: Float, velocityY: Float): Boolean {
        val absVelocityX = abs(velocityX.toDouble()).toFloat()
        return absVelocityX > swipeEscapeVelocity && absVelocityX >= abs(velocityY.toDouble())
    }

    /**
     * 判断角度是否在左方向范围内
     * @param angle 角度
     * @return 是否是左方向
     */
    private fun isLeftAngle(angle: Float): Boolean {
        return angle >= AngleConstants.leftAngleStart && angle <= AngleConstants.leftAngleEnd
    }

    /**
     * 判断角度是否在右方向范围内
     * @param angle 角度
     * @return 是否是右方向
     */
    private fun isRightAngle(angle: Float): Boolean {
        return angle >= AngleConstants.rightAngleStart || angle <= AngleConstants.rightAngleEnd
    }

    /**
     * 判断角度是否在上方向范围内
     * @param angle 角度
     * @return 是否是上方向
     */
    private fun isUpAngle(angle: Float): Boolean {
        return angle >= AngleConstants.upAngleStart && angle <= AngleConstants.upAngleEnd
    }

    /**
     * 判断是否是垂直方向滑动
     * @param velocityX X方向速度
     * @param velocityY Y方向速度
     * @return 是否是垂直滑动
     */
    private fun isVerticalSwipe(velocityX: Float, velocityY: Float): Boolean {
        val absVelocityX = abs(velocityX.toDouble()).toFloat()
        val absVelocityY = abs(velocityY.toDouble()).toFloat()
        return absVelocityY > swipeEscapeVelocity && absVelocityY >= absVelocityX
    }

    private val rotationFactor: Float
        /**
         * 获取旋转因子
         * @return 旋转因子
         */
        get() = 0.03f

    /**
     * 从拖动和速度确定滑动方向
     * @param dragX X方向拖动距离
     * @param dragY Y方向拖动距离
     * @param velocityX X方向速度
     * @param velocityY Y方向速度
     * @return 滑动方向
     */
    fun determineSwipeDirection(dragX: Float, dragY: Float, velocityX: Float, velocityY: Float): SwipeDirection {
        val defaultDirection: SwipeDirection = SwipeDirection.NONE
        val dragDirection: SwipeDirection = if (abs(dragX.toDouble()) > 0.0f || abs(dragY.toDouble()) > 0.0f) getDirectionFromAngle(getDegreeOfRotation(dragX, dragY)) else defaultDirection

        val velocityDirection: SwipeDirection =
            if (abs(velocityX.toDouble()) > 0.0f || abs(velocityY.toDouble()) > 0.0f) getDirectionFromAngle(getDegreeOfRotation(velocityX, velocityY)) else dragDirection

        return if (velocityDirection !== dragDirection) defaultDirection else dragDirection
    }

    /**
     * 计算旋转角度
     * @param x X方向
     * @param y Y方向
     * @return 角度 (0-360度)
     */
    fun getDegreeOfRotation(x: Float, y: Float): Float {
        val degrees = Math.toDegrees(atan2(-y.toDouble(), x.toDouble())).toFloat()
        return if (degrees < 0.0f) degrees + 360.0f else degrees
    }

    /**
     * 从移动获取滑动方向
     * @param moveX X方向移动
     * @param moveY Y方向移动
     * @return 滑动方向
     */
    fun getDirectionFromMovement(moveX: Float, moveY: Float): SwipeDirection {
        return if (abs(moveX.toDouble()) > 0.0f || abs(moveY.toDouble()) > 0.0f) getDirectionFromAngle(getDegreeOfRotation(moveX, moveY)) else SwipeDirection.NONE
    }

    /**
     * 计算视图旋转角度
     * @param view 视图
     * @param dragX X方向拖动
     * @param dragY Y方向拖动
     * @return 旋转角度
     */
    fun getRotation(view: View, dragX: Float, dragY: Float): Float {
        return (if (dragY < getHalfHeight(view)) 1 else -1) * dragX * rotationFactor
    }

    /**
     * 判断是否达到滑动阈值
     * @param dragX X方向拖动
     * @param dragY Y方向拖动
     * @param velocityX X方向速度
     * @param velocityY Y方向速度
     * @return 是否达到滑动阈值
     */
    fun isSwipeThresholdCrossed(dragX: Float, dragY: Float, velocityX: Float, velocityY: Float): Boolean {
        val absDragX = abs(dragX.toDouble()).toFloat()
        val absDragY = abs(dragY.toDouble()).toFloat()
        val isHorizontalSwipe = isHorizontalSwipe(velocityX, velocityY)
        val isVerticalSwipe = isVerticalSwipe(velocityX, velocityY)

        val directionOrdinal = DirectionOrdinalMap.DIRECTION_ORDINALS[getDirectionFromAngle(getDegreeOfRotation(dragX, dragY)).ordinal]

        if (directionOrdinal == 1 || directionOrdinal == 2) { // 左右方向
            if (absDragX > minThresholdForSwipe) {
                return true
            }
            if (isHorizontalSwipe && absDragX > this.velocitySwipeThreshold) {
                return true
            }
        } else if ((directionOrdinal == 3 || directionOrdinal == 4) && absDragY > absDragX) { // 上下方向
            if (absDragY > minThresholdForSwipe) {
                return true
            }
            if (isVerticalSwipe && absDragY > this.velocitySwipeThreshold) {
                return true
            }
        }

        return false
    }

    val velocityTrackingUnits: Int
        /**
         * 获取速度跟踪单位
         * @return 速度跟踪单位
         */
        get() = 1000

    /**
     * 判断移动是否低于阈值
     * @param moveX X方向移动
     * @param moveY Y方向移动
     * @return 是否低于阈值
     */
    fun isBelowThreshold(moveX: Float, moveY: Float): Boolean {
        return sqrt(abs(moveX.toDouble()).pow(2.0) + abs(moveY.toDouble()).pow(2.0)) <= (touchSlop.toDouble())
    }
}