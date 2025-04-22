package com.rong.litswipecard.cardstack.cardstack.cardtransformer

/**
 * 默认卡片变换类
 * 为卡片堆栈中不同位置的卡片提供默认的变换参数
 */
class DefaultCardTransforms : CardTransforms {

    /**
     * 获取默认变换
     * @return 默认卡片变换（使用第三张卡片的变换作为默认）
     */
    override val defaultTransform: CardTransform
        get() = THIRD_CARD_TRANSFORM

    /**
     * 根据卡片索引获取对应的变换
     * @param cardTransformerIndex 卡片索引
     * @return 对应索引位置的卡片变换
     * @throws IllegalStateException 当索引为负数时抛出
     */
    override fun getTransformForView(cardTransformerIndex: Int): CardTransform {
        if (cardTransformerIndex >= 0) {
            return when (cardTransformerIndex) {
                0 -> TOP_CARD_TRANSFORM
                1 -> SECOND_CARD_TRANSFORM
                else -> THIRD_CARD_TRANSFORM
            }
        }

        throw IllegalStateException("Requesting card transform for index:$cardTransformerIndex")
    }

    companion object {
        // 顶部卡片变换：100%起始缩放，103%结束缩放，12.0起始Z轴，12.05结束Z轴
        private val TOP_CARD_TRANSFORM = CardTransform(1.0f, 1.03f, 12.0f, 12.05f)

        // 第二张卡片变换：95%起始缩放，100%结束缩放，11.75起始Z轴，12.0结束Z轴
        private val SECOND_CARD_TRANSFORM = CardTransform(0.95f, 1.0f, 11.75f, 12.0f)

        // 第三张卡片变换：92%起始缩放，95%结束缩放，11.75起始Z轴，12.0结束Z轴
        private val THIRD_CARD_TRANSFORM = CardTransform(0.92f, 0.95f, 11.75f, 12.0f)
    }
}