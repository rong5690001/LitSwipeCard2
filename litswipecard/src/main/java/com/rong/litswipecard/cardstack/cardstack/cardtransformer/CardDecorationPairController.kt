package com.rong.litswipecard.cardstack.cardstack.cardtransformer

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.rong.litswipecard.cardstack.swipe.SwipeThresholdDetector
import com.rong.litswipecard.cardstack.view.CardStackLayout
import com.rong.litswipecard.cardstack.view.OnChildAttachStateChangePostLayoutListeners

/**
 * 卡片装饰对控制器
 * 管理卡片堆栈中卡片对之间的视觉效果和变换
 */
class CardDecorationPairController(
    /** 卡片堆栈布局  */
    private val cardStackLayout: CardStackLayout,
    /** 滑动阈值检测器  */
    private val swipeThresholdDetector: SwipeThresholdDetector
) : OnChildAttachStateChangePostLayoutListeners {
    /** 卡片装饰对列表  */
    private val decorationPairs: MutableList<CardDecorationPair> = ArrayList()

    /** 卡片对状态变化监听器列表  */
    private val pairStateChangeListeners: MutableList<CardStackLayout.OnCardPairStateChangeListener> = ArrayList()

    /**
     * 构造卡片装饰对控制器
     */
    init {
        cardStackLayout.addOnChildAttachStateChangeListenerPostLayout(this)
    }

    /**
     * 创建卡片装饰对
     * @param upperView 上层卡片视图
     * @param lowerView 下层卡片视图
     */
    private fun createDecorationPair(upperView: View, lowerView: View) {
        val decorationPair = CardDecorationPair(lowerView, upperView, this.cardStackLayout, this.swipeThresholdDetector)
        decorationPairs.add(decorationPair)
        notifyPairCreated(decorationPair)
    }

    /**
     * 销毁卡片装饰对
     * @param decorationPair 要销毁的卡片装饰对
     */
    private fun destroyDecorationPair(decorationPair: CardDecorationPair) {
        notifyPairDestroyed(decorationPair)
        decorationPair.destroy()
    }

    /**
     * 获取视图的布局参数
     * @param view 视图
     * @return 布局参数
     */
    private fun getLayoutParams(view: View): RecyclerView.LayoutParams {
        return view.layoutParams as RecyclerView.LayoutParams
    }

    /**
     * 通知卡片对创建事件
     * @param decorationPair 创建的卡片装饰对
     */
    private fun notifyPairCreated(decorationPair: CardDecorationPair) {
        for (listener in pairStateChangeListeners) {
            listener.onPairCreated(decorationPair.upperCardView, decorationPair.lowerCardView)
        }
    }

    /**
     * 通知卡片对销毁事件
     * @param decorationPair 销毁的卡片装饰对
     */
    private fun notifyPairDestroyed(decorationPair: CardDecorationPair) {
        for (listener in pairStateChangeListeners) {
            listener.onPairDestroyed(decorationPair.upperCardView, decorationPair.lowerCardView)
        }
    }

    /**
     * 重置视图的变换
     * @param view 要重置的视图
     */
    private fun resetViewTransform(view: View) {
        applyTransform(view, cardStackLayout.cardTransforms.defaultTransform)
    }

    /**
     * 应用变换到视图
     * @param view 目标视图
     * @param cardTransform 卡片变换
     */
    private fun applyTransform(view: View, cardTransform: CardTransform) {
        ViewCompat.setScaleX(view, cardTransform.startScale)
        ViewCompat.setScaleY(view, cardTransform.startScale)
        ViewCompat.setTranslationY(view, 0.0f)
        ViewCompat.setTranslationX(view, 0.0f)
        ViewCompat.setTranslationZ(view, cardTransform.startTranslationZ)
        ViewCompat.setRotation(view, 0.0f)
        ViewCompat.setRotationY(view, 0.0f)
        ViewCompat.setRotationX(view, 0.0f)
    }

    /**
     * 移除包含指定视图的所有卡片装饰对
     * @param view 目标视图
     */
    private fun removeDecorationPairsContaining(view: View) {
        val it = decorationPairs.iterator()
        while (it.hasNext()) {
            val decorationPair = it.next()
            if (decorationPair.lowerCardView === view || decorationPair.upperCardView === view) {
                it.remove()
                destroyDecorationPair(decorationPair)
            }
        }
    }

    /**
     * 移除以指定视图为上层卡片的装饰对
     * @param view 上层卡片视图
     */
    private fun removeDecorationPairsWithUpperCard(view: View) {
        val it = decorationPairs.iterator()
        while (it.hasNext()) {
            val decorationPair = it.next()
            if (decorationPair.upperCardView === view) {
                destroyDecorationPair(decorationPair)
                it.remove()
            }
        }
    }

    /**
     * 移除以指定视图为下层卡片的装饰对
     * @param view 下层卡片视图
     */
    private fun removeDecorationPairsWithLowerCard(view: View) {
        val it = decorationPairs.iterator()
        while (it.hasNext()) {
            val decorationPair = it.next()
            if (decorationPair.lowerCardView === view) {
                destroyDecorationPair(decorationPair)
                it.remove()
            }
        }
    }

    /**
     * 添加卡片对状态变化监听器
     * @param listener 监听器
     */
    fun addOnCardPairStateChangeListener(listener: CardStackLayout.OnCardPairStateChangeListener) {
        pairStateChangeListeners.add(listener)
    }

    /**
     * 当子视图在布局完成后被附加时调用
     * @param view 被附加的子视图
     */
    override fun onChildViewAttachedPostLayout(view: View?) {
        view?.let {
            val viewIndex = cardStackLayout.indexOfChild(it)
            val nextView = cardStackLayout.getChildAt(viewIndex + 1)
            val prevView = cardStackLayout.getChildAt(viewIndex - 1)

            // 如果有下一个视图，创建以当前视图为上层、下一个视图为下层的卡片对
            if (nextView != null) {
                removeDecorationPairsWithUpperCard(nextView)
                createDecorationPair(nextView, it)
            }

            // 如果有前一个视图且未被标记为移除，创建以当前视图为下层、前一个视图为上层的卡片对
            if (prevView != null && !getLayoutParams(prevView).isItemRemoved) {
                removeDecorationPairsWithLowerCard(prevView)
                createDecorationPair(it, prevView)
            }

            // 如果没有相邻视图，应用顶部卡片变换
            if (nextView == null && prevView == null) {
                val transform = cardStackLayout.cardTransforms.getTransformForView(0)
                applyTransform(it, transform)
            }
        }
    }

    /**
     * 当子视图在布局完成后被分离时调用
     * @param view 被分离的子视图
     */
    override fun onChildViewDetachedPostLayout(view: View?) {
        view?.let {
            removeDecorationPairsContaining(it)
            resetViewTransform(it)
        }
    }

    /**
     * 移除卡片对状态变化监听器
     * @param listener 监听器
     */
    fun removeOnCardPairStateChangeListener(listener: CardStackLayout.OnCardPairStateChangeListener) {
        pairStateChangeListeners.remove(listener)
    }
}