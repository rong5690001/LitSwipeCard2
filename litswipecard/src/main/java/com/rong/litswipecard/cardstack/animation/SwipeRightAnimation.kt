package com.rong.litswipecard.cardstack.animation;

import com.tinder.cardstack.utils.ViewHelper;

/* loaded from: classes7.dex */
public class SwipeRightAnimation extends SwipeAnimation {
    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float endRotation() {
        return 18.0f;
    }

    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float endX() {
        return ViewHelper.HORIZONTAL_SWIPE_DISTANCE;
    }

    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float startRotation() {
        return 0.0f;
    }
}