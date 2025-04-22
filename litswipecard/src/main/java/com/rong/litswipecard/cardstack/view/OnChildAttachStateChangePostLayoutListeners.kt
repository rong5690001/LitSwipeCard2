package com.rong.litswipecard.cardstack.view

import android.view.View

/**
 * 子视图附加状态变化后布局监听器接口
 * 用于监听子视图在布局完成后的附加和分离事件
 */
interface OnChildAttachStateChangePostLayoutListeners {
    /**
     * 当子视图在布局完成后被附加时调用
     *
     * @param view 被附加的子视图
     */
    fun onChildViewAttachedPostLayout(view: View?)

    /**
     * 当子视图在布局完成后被分离时调用
     *
     * @param view 被分离的子视图
     */
    fun onChildViewDetachedPostLayout(view: View?)
}