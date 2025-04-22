package com.rong.litswipecard.cardstack.animation.interpolator;

import android.view.animation.Interpolator;

/* loaded from: classes7.dex */
public class SwipeOutInterpolator implements Interpolator {
    private final float a;
    private final float b;
    private final long c;
    private final double d;

    public SwipeOutInterpolator(float f, float f2, long j) {
        this.a = f;
        this.b = f2;
        this.c = j;
        this.d = ((f - (f2 * j)) * 2.0f) / Math.pow(j, 2.0d);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return ((float) ((this.b * r9) + ((this.d * 0.5d) * Math.pow(f * this.c, 2.0d)))) / this.a;
    }
}