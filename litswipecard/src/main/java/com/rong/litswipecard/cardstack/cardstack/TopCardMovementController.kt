package com.rong.litswipecard.cardstack.cardstack

import com.rong.litswipecard.cardstack.swipe.SwipeThresholdDetector
import com.rong.litswipecard.cardstack.view.CardStackLayout
import com.rong.litswipecard.cardstack.view.CardStackLayout.OnTopCardMovedListener

/**
 * 顶部卡片移动控制器
 * 负责处理顶部卡片的移动事件并通知监听器
 */
class TopCardMovementController(private val cardStackLayout: CardStackLayout, private val thresholdDetector: SwipeThresholdDetector) {
    private val topCardMovedListeners: MutableList<OnTopCardMovedListener> = ArrayList()

    /**
     * 添加顶部卡片移动监听器
     * @param listener 监听器
     */
    fun addTopCardMovedListener(listener: OnTopCardMovedListener) {
        if (!topCardMovedListeners.contains(listener)) {
            topCardMovedListeners.add(listener)
        }
    }

    /**
     * 移除顶部卡片移动监听器
     * @param listener 监听器
     */
    fun removeTopCardMovedListener(listener: OnTopCardMovedListener) {
        topCardMovedListeners.remove(listener)
    }

    /**
     * 通知所有监听器顶部卡片已移动
     * @param translationX X轴偏移量
     * @param translationY Y轴偏移量
     * @param rotation 旋转角度
     * @param thresholdCrossed 是否超过阈值
     */
    fun notifyTopCardMoved(translationX: Float, translationY: Float, rotation: Float, thresholdCrossed: Boolean) {
        val it: Iterator<OnTopCardMovedListener> = topCardMovedListeners.iterator()
        while (it.hasNext()) {
            it.next().onTopCardMoved(translationX, translationY, rotation, null, null, thresholdCrossed)
        }
    }
}