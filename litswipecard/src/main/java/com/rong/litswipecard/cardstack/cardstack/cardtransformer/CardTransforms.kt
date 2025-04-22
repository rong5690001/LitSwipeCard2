package com.rong.litswipecard.cardstack.cardstack.cardtransformer

interface CardTransforms {
    val defaultTransform: CardTransform

    fun getTransformForView(cardTransformerIndex: Int): CardTransform
}