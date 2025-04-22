package com.rong.litswipecard.cardstack.view

import com.rong.litswipecard.cardstack.model.Card
import com.rong.litswipecard.cardstack.model.SwipeDirection

/**
 * 卡片滑动前监听器接口
 * 用于在卡片即将被滑动时进行拦截判断
 */
interface OnPreSwipeListener {
    /**
     * 当卡片即将被滑动时调用
     *
     * @param card 即将被滑动的卡片
     * @param swipeDirection 滑动方向
     * @return 是否允许滑动，返回true表示拦截滑动
     */
    fun onPreSwipe(card: Card<*>, swipeDirection: SwipeDirection): Boolean
}