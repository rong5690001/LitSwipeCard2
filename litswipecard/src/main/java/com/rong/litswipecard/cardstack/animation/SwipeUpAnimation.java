package com.rong.litswipecard.cardstack.animation;

import com.tinder.cardstack.utils.ViewHelper;

/* loaded from: classes7.dex */
public class SwipeUpAnimation extends SwipeAnimation {
    @Override // com.tinder.cardstack.animation.SwipeAnimation
    public float endY() {
        return ViewHelper.VERTICAL_SWIPE_DISTANCE * (-1.0f);
    }
}