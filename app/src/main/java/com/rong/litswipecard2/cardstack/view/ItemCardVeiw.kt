package com.rong.litswipecard2.cardstack.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.rong.litswipecard.cardstack.model.Card
import com.rong.litswipecard.cardstack.view.CardCollectionLayout
import com.rong.litswipecard2.R

class ItemCardVeiw @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs),com.rong.litswipecard.cardstack.view.CardView<Card<String>> {

    private val cardColors = arrayOf(
        Color.parseColor("#FFB6C1"), // 浅粉色
        Color.parseColor("#98FB98"), // 浅绿色
        Color.parseColor("#87CEEB"), // 天蓝色
        Color.parseColor("#DDA0DD"), // 梅红色
        Color.parseColor("#F0E68C"), // 卡其色
        Color.parseColor("#E6E6FA"), // 薰衣草色
        Color.parseColor("#FFA07A"), // 浅珊瑚色
        Color.parseColor("#20B2AA"), // 浅海绿色
        Color.parseColor("#D8BFD8"), // 蓟色
        Color.parseColor("#B0C4DE")  // 钢蓝色
    )

    override fun bind(cardData: Card<String>) {
        findViewById<TextView>(R.id.card_text).text = cardData.item
        setBackgroundColor(cardColors[(Math.random() * 10).toInt()])
    }

    override fun onAttachedToCardCollectionLayout(cardCollectionLayout: CardCollectionLayout) {

    }

    override fun onCardViewRecycled() {

    }

    override fun onDetachedFromCardCollectionLayout(cardCollectionLayout: CardCollectionLayout) {

    }

    override fun onRemovedFromTop(cardData: Card<String>) {

    }

    override fun onMovedToTop(cardData: Card<String>) {

    }
}