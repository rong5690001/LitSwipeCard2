package com.rong.litswipecard.cardstack.cardstack

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.ViewCompat
import com.rong.litswipecard.cardstack.model.SwipeDirection
import com.rong.litswipecard.cardstack.swipe.SwipeThresholdDetector
import com.rong.litswipecard.cardstack.view.CardDecorationListener
import com.rong.litswipecard.cardstack.view.CardStackLayout
import com.rong.litswipecard.cardstack.view.OnChildAttachStateChangePostLayoutListeners
import timber.log.Timber
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 卡片装饰管理器
 * 管理卡片堆栈顶部卡片的装饰和移动事件
 */
internal class CardDecorationManager(cardStackLayout: CardStackLayout, swipeThresholdDetector: SwipeThresholdDetector) : CardDecorationListener, OnChildAttachStateChangePostLayoutListeners {
    /** 顶部卡片移动监听器列表  */
    private val topCardMovedListeners: CopyOnWriteArrayList<CardStackLayout.OnTopCardMovedListener> = CopyOnWriteArrayList<CardStackLayout.OnTopCardMovedListener>()

    /** 当前顶部卡片视图  */
    private var topCardView: View? = null

    /** 关联的卡片堆栈布局  */
    private val cardStackLayout: CardStackLayout = cardStackLayout

    /** 滑动阈值检测器  */
    private val swipeThresholdDetector: SwipeThresholdDetector = swipeThresholdDetector

    /** 当前X方向移动距离  */
    private var currentTranslationX = 0f

    /** 当前Y方向移动距离  */
    private var currentTranslationY = 0f

    /** 当前旋转角度  */
    private var currentRotation = 0f

    /**
     * 构造卡片装饰管理器
     * @param cardStackLayout 卡片堆栈布局
     * @param swipeThresholdDetector 滑动阈值检测器
     */
    init {
        cardStackLayout.addOnChildAttachStateChangeListenerPostLayout(this)
    }

    /**
     * 为视图添加卡片装饰监听器
     * @param view 要添加监听器的视图
     */
    private fun addCardDecorationListener(view: View) {
        cardStackLayout.addCardDecorationListener(view, this)
    }

    /**
     * 更新顶部卡片
     * @param preserveCurrentTopCard 是否保留当前顶部卡片（如果可能）
     */
    private fun updateTopCard(preserveCurrentTopCard: Boolean) {
        // 获取应该成为顶部卡片的视图
        val newTopCard = findTopCardView(preserveCurrentTopCard)
        val oldTopCard = this.topCardView

        Timber.d("Updating top card - Preserve: %b, Old: %s, New: %s",
            preserveCurrentTopCard, oldTopCard?.toString(), newTopCard?.toString())

        // 如果顶部卡片没有变化，不执行任何操作
        if (oldTopCard === newTopCard) {
            Timber.d("Top card unchanged")
            return
        }

        // 如果存在旧的顶部卡片，移除其监听器并通知移动结束
        if (oldTopCard != null) {
            Timber.d("Removing old top card")
            removeCardDecorationListener(oldTopCard)
            notifyTopCardMoveEnded(true)
            resetTranslationValues()
            this.topCardView = null
        }

        // 如果存在新的顶部卡片，设置为当前顶部卡片并添加监听器
        if (newTopCard != null) {
            Timber.d("Setting new top card")
            this.topCardView = newTopCard
            addCardDecorationListener(newTopCard)
        }
    }

    /**
     * 查找应该成为顶部卡片的视图
     * @param preserveCurrentTopCard 是否保留当前顶部卡片（如果可能）
     * @return 应该成为顶部卡片的视图，如果没有合适的卡片则返回null
     */
    private fun findTopCardView(preserveCurrentTopCard: Boolean): View? {
        var childCount: Int = cardStackLayout.getChildCount() - 1
        var previousCard: View? = null


        // 从后往前遍历子视图，找到第一个未被标记为移除的视图
        while (childCount >= 0) {
            val childView: View = cardStackLayout.getChildAt(childCount)
            if (!(childView.layoutParams as RecyclerView.LayoutParams).isItemRemoved()) {
                // 如果需要保留当前顶部卡片且前一个卡片就是当前顶部卡片，则返回前一个卡片
                return if (preserveCurrentTopCard && this.topCardView != null && previousCard === this.topCardView)
                    previousCard
                else
                    childView
            }
            childCount--
            previousCard = childView
        }

        return null
    }

    /**
     * 通知所有监听器顶部卡片移动结束
     * @param succeeded 移动是否成功完成
     */
    private fun notifyTopCardMoveEnded(succeeded: Boolean) {
        val it: Iterator<CardStackLayout.OnTopCardMovedListener> = topCardMovedListeners.iterator()
        while (it.hasNext()) {
            it.next().onTopCardMoveEnded(succeeded)
        }
    }

    /**
     * 通知所有监听器顶部卡片开始移动
     */
    private fun notifyTopCardMoveStarted() {
        val it: Iterator<CardStackLayout.OnTopCardMovedListener> = topCardMovedListeners.iterator()
        while (it.hasNext()) {
            it.next().onTopCardMoveStarted()
        }
    }

    /**
     * 通知所有监听器顶部卡片移动
     * @param translationX X方向移动距离
     * @param translationY Y方向移动距离
     * @param rotation 旋转角度
     * @param isTouchActive 触摸是否处于活动状态
     */
    private fun notifyTopCardMoved(translationX: Float, translationY: Float, rotation: Float, isTouchActive: Boolean) {
        // 根据移动方向确定滑动方向
        val direction: SwipeDirection = swipeThresholdDetector.getDirectionFromMovement(translationX, translationY)


        // 通知所有监听器
        val it: Iterator<CardStackLayout.OnTopCardMovedListener> = topCardMovedListeners.iterator()
        while (it.hasNext()) {
            it.next().onTopCardMoved(translationX, translationY, rotation, this.topCardView, direction, isTouchActive)
        }
    }

    /**
     * 移除视图的卡片装饰监听器
     * @param view 要移除监听器的视图
     */
    private fun removeCardDecorationListener(view: View) {
        cardStackLayout.removeCardDecorationListener(view, this)
    }

    /**
     * 重置平移和旋转值
     */
    private fun resetTranslationValues() {
        this.currentTranslationX = 0.0f
        this.currentTranslationY = 0.0f
        this.currentRotation = 0.0f
    }

    /**
     * 添加顶部卡片移动监听器
     * @param listener 要添加的监听器
     */
    fun addTopCardMovedListener(listener: CardStackLayout.OnTopCardMovedListener) {
        topCardMovedListeners.add(listener)
    }

    /**
     * 移除顶部卡片移动监听器
     * @param listener 要移除的监听器
     */
    fun removeTopCardMovedListener(listener: CardStackLayout.OnTopCardMovedListener) {
        topCardMovedListeners.remove(listener)
    }

    override fun onChildViewAttachedPostLayout(view: View?) {
        // 当有新视图附加时，更新顶部卡片，尝试保留当前顶部卡片
        updateTopCard(true)
    }

    override fun onChildViewDetachedPostLayout(view: View?) {
        // 当有视图分离时，更新顶部卡片，不保留当前顶部卡片
        updateTopCard(false)
    }

    override fun onDecorationDraw(
        canvas: Canvas, view: View, viewGroup: ViewGroup,
        translationX: Float, translationY: Float, rotation: Float,
        swipeDirection: SwipeDirection, isThresholdExceeded: Boolean, isTouchActive: Boolean
    ) {
        // 默认实现为空，子类可以覆盖此方法以绘制装饰
    }

    override fun onDecorationDrawOver(
        canvas: Canvas, view: View, viewGroup: ViewGroup,
        translationX: Float, translationY: Float, rotation: Float,
        swipeDirection: SwipeDirection, isThresholdExceeded: Boolean, isTouchActive: Boolean
    ) {
        // 确保当前有顶部卡片，并且传入的视图就是顶部卡片
        val currentTopCard = this.topCardView
        if (currentTopCard == null || view !== currentTopCard) {
            Timber.w("onDecorationDrawOver without a topCard")
            return
        }

        Timber.d("Decoration Draw Over - Translation: (%.2f, %.2f), Rotation: %.2f, Direction: %s, Threshold: %b, TouchActive: %b",
            translationX, translationY, rotation, swipeDirection, isThresholdExceeded, isTouchActive)

        // 确保顶部卡片始终在最上层
        view.bringToFront()

        // 处理移动开始事件
        val oldTranslationX = this.currentTranslationX
        if (oldTranslationX == 0.0f) {
            val oldTranslationY = this.currentTranslationY
            if (oldTranslationY == 0.0f) {
                val oldRotation = this.currentRotation
                if (oldRotation == 0.0f && (oldTranslationX != translationX || oldTranslationY != translationY || oldRotation != rotation)) {
                    // 移动从零开始，通知移动开始
                    Timber.d("Card movement started")
                    notifyTopCardMoveStarted()
                    this.currentTranslationX = translationX
                    this.currentTranslationY = translationY
                    this.currentRotation = rotation
                    // 直接更新视图位置
                    ViewCompat.setTranslationX(view, translationX)
                    ViewCompat.setTranslationY(view, translationY)
                    view.rotation = rotation
                    view.invalidate()
                }
            }
        }

        // 处理移动结束和移动中事件
        if (translationX == 0.0f && translationY == 0.0f && rotation == 0.0f &&
            (oldTranslationX != translationX || this.currentTranslationY != translationY || this.currentRotation != rotation)) {
            // 移动回到零位置，通知移动结束
            Timber.d("Card movement ended - returned to original position")
            notifyTopCardMoveEnded(false)
        } else if (oldTranslationX != translationX || this.currentTranslationY != translationY || this.currentRotation != rotation) {
            // 移动位置发生变化，通知移动中
            Timber.d("Card movement in progress - New position: (%.2f, %.2f), Rotation: %.2f",
                translationX, translationY, rotation)
            notifyTopCardMoved(translationX, translationY, rotation, isTouchActive)
            // 直接更新视图位置
            ViewCompat.setTranslationX(view, translationX)
            ViewCompat.setTranslationY(view, translationY)
            view.rotation = rotation
            view.invalidate()
        }

        // 更新当前位置和旋转值
        this.currentTranslationX = translationX
        this.currentTranslationY = translationY
        this.currentRotation = rotation
    }
}