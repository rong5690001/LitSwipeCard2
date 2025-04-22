package com.rong.litswipecard2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rong.litswipecard.cardstack.model.Card
import com.rong.litswipecard.cardstack.model.SwipeDirection
import com.rong.litswipecard.cardstack.view.CardCollectionLayout
import com.rong.litswipecard.cardstack.view.CardStackLayout
import com.rong.litswipecard.cardstack.view.CardView
import com.rong.litswipecard.cardstack.view.CardViewAdapter
import com.rong.litswipecard.cardstack.view.CardViewHolder
import com.rong.litswipecard2.databinding.ItemCardBinding
import timber.log.Timber

class MainActivity : AppCompatActivity(), CardStackLayout.OnCardPresentedListener, CardStackLayout.OnTopCardMovedListener {

    private lateinit var cardStackLayout: CardStackLayout
    private val cards = ArrayList<Card<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化Timber日志
        Timber.plant(Timber.DebugTree())

        setContentView(R.layout.activity_main)

        // 初始化CardStackLayout
        cardStackLayout = findViewById(R.id.card_stack_layout)

        // 设置监听器
        cardStackLayout.setOnCardPresentedListener(this)
        cardStackLayout.addTopCardMovedListener(this)

        // 创建适配器
        val adapter = CardViewAdapter<Card<*>>()

        // 设置ViewHolder工厂，用于创建卡片视图
        adapter.setViewHolderFactory(object : CardViewHolder.Factory<CardViewHolder<Card<*>>, Card<*>> {
            override fun createViewHolder(viewGroup: ViewGroup?, viewType: Int): CardViewHolder<Card<*>> {
                return CardViewHolder(ItemCardBinding.inflate(LayoutInflater.from(this@MainActivity), cardStackLayout, false).root)
            }

            override fun getViewType(card: Card<*>): Int {
                return 0
            }
        })

        // 设置适配器
        cardStackLayout.adapter = adapter

        // 生成200条卡片数据
        generateCards(200)

        // 添加卡片数据
        cardStackLayout.adapter.insert(0, cards)
    }

    /**
     * 生成卡片数据
     * @param count 生成的卡片数量
     */
    private fun generateCards(count: Int) {
        for (i in 1..count) {
            cards.add(Card(i.toString(), "卡片 #$i"))
        }
        Timber.d("生成了 $count 张卡片")
    }

    /**
     * 当卡片呈现时调用
     */
    override fun onCardPresented(card: Card<*>, view: View) {
        val textView = view.findViewById<TextView>(R.id.card_text)
        val cardData = card.item as? String ?: "未知"
        textView.text = cardData
        Timber.d("卡片呈现: $cardData")
    }

    /**
     * 当顶部卡片移动结束时调用
     */
    override fun onTopCardMoveEnded(thresholdExceeded: Boolean) {
        if (thresholdExceeded) {
            Timber.d("卡片滑出")
        } else {
            Timber.d("卡片返回原位")
        }
    }

    /**
     * 当顶部卡片开始移动时调用
     */
    override fun onTopCardMoveStarted() {
        Timber.d("卡片开始移动")
    }

    /**
     * 当顶部卡片移动时调用
     */
    override fun onTopCardMoved(translationX: Float, translationY: Float, rotation: Float, view: View?, swipeDirection: SwipeDirection?, thresholdExceeded: Boolean) {
        // 可以在这里根据卡片移动情况做一些视觉反馈
        if (thresholdExceeded) {
            when (swipeDirection) {
                SwipeDirection.LEFT -> Timber.d("向左移动，超过阈值")
                SwipeDirection.RIGHT -> Timber.d("向右移动，超过阈值")
                SwipeDirection.UP -> Timber.d("向上移动，超过阈值")
                else -> Timber.d("移动方向: $swipeDirection，超过阈值")
            }
        }
    }
}