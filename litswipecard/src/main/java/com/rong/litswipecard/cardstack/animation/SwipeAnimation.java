package com.rong.litswipecard.cardstack.animation;

import android.animation.TimeInterpolator;
import androidx.annotation.Nullable;

/* loaded from: classes7.dex */
public abstract class SwipeAnimation {
    public static final float INVALID_ALPHA = Float.MIN_VALUE;
    public static final int INVALID_ROTATION = Integer.MIN_VALUE;

    public int durationMilli() {
        return -1;
    }

    public float endAlpha() {
        return Float.MIN_VALUE;
    }

    public float endRotation() {
        return -2.1474836E9f;
    }

    public float endX() {
        return 0.0f;
    }

    public float endY() {
        return 0.0f;
    }

    @Nullable
    public TimeInterpolator interpolator() {
        return null;
    }

    public float startAlpha() {
        return Float.MIN_VALUE;
    }

    public float startRotation() {
        return -2.1474836E9f;
    }

    public float startX() {
        return 0.0f;
    }

    public float startY() {
        return 0.0f;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}