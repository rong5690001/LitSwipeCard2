package com.rong.litswipecard.cardstack.animation;

import com.tinder.cardstack.utils.ViewHelper;

/* loaded from: classes7.dex */
public class SwipeLeftRewindAnimation extends SwipeRightRewindAnimation {
    @Override // com.tinder.cardstack.animation.SwipeRightRewindAnimation, com.tinder.cardstack.animation.SwipeAnimation
    public float endRotation() {
        return super.endRotation() * (-1.0f);
    }

    @Override // com.tinder.cardstack.animation.SwipeRightRewindAnimation, com.tinder.cardstack.animation.SwipeAnimation
    public float startRotation() {
        return super.startRotation() * (-1.0f);
    }

    @Override // com.tinder.cardstack.animation.SwipeRightRewindAnimation, com.tinder.cardstack.animation.SwipeAnimation
    public float startX() {
        return ViewHelper.getScreenWidth() * (-1.0f);
    }
}