package com.rong.litswipecard.cardstack.animation;

/* loaded from: classes7.dex */
public class SwipeLeftAnimation extends SwipeRightAnimation {
    @Override // com.tinder.cardstack.animation.SwipeRightAnimation, com.tinder.cardstack.animation.SwipeAnimation
    public float endRotation() {
        return super.endRotation() * (-1.0f);
    }

    @Override // com.tinder.cardstack.animation.SwipeRightAnimation, com.tinder.cardstack.animation.SwipeAnimation
    public float endX() {
        return super.endX() * (-1.0f);
    }

    @Override // com.tinder.cardstack.animation.SwipeRightAnimation, com.tinder.cardstack.animation.SwipeAnimation
    public float startRotation() {
        return super.startRotation() * (-1.0f);
    }
}