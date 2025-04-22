package com.rong.litswipecard.cardstack.swipe

import android.graphics.PointF
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * 触摸指针类
 * 用于跟踪手指触摸的位置、拖动状态和相关视图
 */
class TouchPointer {
    /**
     * 获取初始触摸点
     * @return 初始触摸点
     */
    var firstTouchPoint: PointF
        private set

    /**
     * 获取起始X坐标
     * @return 起始X坐标
     */
    var startX: Float
        private set

    /**
     * 获取起始Y坐标
     * @return 起始Y坐标
     */
    var startY: Float
        private set
    val viewHolder: RecyclerView.ViewHolder

    /**
     * 获取指针ID
     * @return 指针ID
     */
    var pointerId: Int
        private set

    /**
     * 获取X方向拖动距离
     * @return X方向拖动距离
     */
    var dragX: Float = 0f
        private set

    /**
     * 获取Y方向拖动距离
     * @return Y方向拖动距离
     */
    var dragY: Float = 0f
        private set

    /**
     * 是否正在拖动
     * @return 是否正在拖动
     */
    var isDragging: Boolean = false
        private set
    val dragConstraints: DragConstraints?

    /**
     * 构造函数
     * @param viewHolder 视图持有者
     * @param touchPoint 初始触摸点
     * @param startX 起始X坐标
     * @param startY 起始Y坐标
     * @param pointerId 指针ID
     * @param dragConstraints 拖拽约束
     */
    constructor(viewHolder: RecyclerView.ViewHolder, touchPoint: PointF, startX: Float, startY: Float, pointerId: Int, dragConstraints: DragConstraints?) {
        this.viewHolder = viewHolder
        this.startX = startX
        this.startY = startY
        this.pointerId = pointerId
        this.firstTouchPoint = touchPoint
        this.dragConstraints = dragConstraints
    }

    /**
     * 设置X方向拖动距离
     * @param dragX X方向拖动距离
     */
    fun updateDragX(dragX: Float) {
        this.dragX = dragX
    }

    /**
     * 设置Y方向拖动距离
     * @param dragY Y方向拖动距离
     */
    fun updateDragY(dragY: Float) {
        this.dragY = dragY
    }

    /**
     * 设置拖动状态
     * @param isDragging 是否正在拖动
     */
    fun startDragging(isDragging: Boolean) {
        this.isDragging = isDragging
    }

    /**
     * 更新触摸起始点
     * @param x X坐标
     * @param y Y坐标
     */
    fun updateTouchStartPoint(x: Float, y: Float) {
        this.firstTouchPoint = PointF(x, y)
        this.startX = x
        this.startY = y
    }

    /**
     * 更新拖动增量
     * @param deltaX X方向增量
     * @param deltaY Y方向增量
     */
    fun updateDragDelta(deltaX: Float, deltaY: Float) {
        this.dragX = deltaX
        this.dragY = deltaY
    }

    /**
     * 字符串表示
     * @return 对象的字符串表示
     */
    override fun toString(): String {
        return "[sx=" + this.startX + "::sy=" + this.startY + "::dx=" + this.dragX + "::dy=" + this.dragY + "::apid=" + this.pointerId + "::vh=" + this.viewHolder + "]"
    }

    /**
     * 从MotionEvent构造TouchPointer
     * @param viewHolder 视图持有者
     * @param motionEvent 触摸事件
     * @param dragConstraints 拖拽约束
     */
    internal constructor(viewHolder: RecyclerView.ViewHolder, motionEvent: MotionEvent, dragConstraints: DragConstraints?) {
        this.pointerId = -1
        this.viewHolder = viewHolder
        this.startX = motionEvent.x
        this.startY = motionEvent.y
        this.pointerId = motionEvent.getPointerId(0)
        this.firstTouchPoint = PointF(this.startX, this.startY)
        this.dragConstraints = dragConstraints
    }
}