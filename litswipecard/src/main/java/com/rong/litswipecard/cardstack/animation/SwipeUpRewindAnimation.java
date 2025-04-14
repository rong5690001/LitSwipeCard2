package com.rong.litswipecard.cardstack.animation;

import com.tinder.cardstack.utils.ViewHelper;

/* loaded from: classes7.dex */
public class SwipeUpRewindAnimation extends SwipeAnimation {
    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float startY() {
        return ViewHelper.getScreenHeight() * (-1.0f);
    }
}