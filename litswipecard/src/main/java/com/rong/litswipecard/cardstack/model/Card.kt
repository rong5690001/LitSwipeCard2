package com.rong.litswipecard.cardstack.model

import com.rong.litswipecard.cardstack.animation.SwipeAnimation

class Card<T>(val id: String, val item: T) {
    var appearingAnimation: SwipeAnimation? = null
    var disappearingAnimation: SwipeAnimation? = null
    var showRevertIndicator: Boolean = false
}