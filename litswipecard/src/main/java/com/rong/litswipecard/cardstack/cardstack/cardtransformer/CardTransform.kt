package com.rong.litswipecard.cardstack.cardstack.cardtransformer

/**
 * 卡片变换数据类
 * 定义卡片动画变换的起始和结束参数
 */
data class CardTransform(
    val startScale: Float,
    val endScale: Float,
    val startTranslationZ: Float,
    val endTranslationZ: Float
)