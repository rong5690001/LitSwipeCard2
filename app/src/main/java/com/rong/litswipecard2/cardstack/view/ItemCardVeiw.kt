package com.rong.litswipecard2.cardstack.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.rong.litswipecard.cardstack.model.Card
import com.rong.litswipecard.cardstack.view.CardCollectionLayout
import com.rong.litswipecard2.R

class ItemCardVeiw @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs),com.rong.litswipecard.cardstack.view.CardView<Card<String>> {

    override fun bind(cardData: Card<String>) {
        findViewById<TextView>(R.id.card_text).text = cardData.item
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