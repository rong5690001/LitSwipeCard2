package com.rong.litswipecard.cardstack.animation;

import com.tinder.cardstack.utils.ViewHelper;

/* loaded from: classes7.dex */
public class SwipeRightRewindAnimation extends SwipeAnimation {
    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float endRotation() {
        return 0.0f;
    }

    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float startRotation() {
        return 18.0f;
    }

    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float startX() {
        return ViewHelper.getScreenWidth();
    }
}