package com.rong.litswipecard.cardstack.swipe;

import android.content.Context;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import com.rong.litswipecard.cardstack.R;
import com.tinder.cardstack.model.SwipeDirection;
import com.rong.litswipecard.cardstack.utils.ViewHelper;

/* loaded from: classes7.dex */
public class SwipeThresholdDetector {
    private final float a;
    private final float b;
    private final float c;
    private final float d;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[SwipeDirection.values().length];
            a = iArr;
            try {
                iArr[SwipeDirection.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[SwipeDirection.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[SwipeDirection.UP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[SwipeDirection.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static class b {
        static float a = 290.0f;
        static float b = 70.0f;
        static float c = 110.0f;
        static float d = 250.0f;
        static float e = 75.0f;
        static float f = 105.0f;
        static float g = 251.0f;
        static float h = 289.0f;
    }

    public SwipeThresholdDetector(@NonNull Context context) {
        float screenWidth = ViewHelper.getScreenWidth();
        this.a = context.getResources().getDimension(R.dimen.fling_escape_velocity_dp) * 6.0f;
        float f = screenWidth * 0.25f;
        this.c = f;
        this.d = f / 3.0f;
        this.b = Math.max(8.0f, ViewConfiguration.get(context).getScaledTouchSlop() / 2);
    }

    private SwipeDirection a(float f) {
        return r(f) ? SwipeDirection.LEFT : s(f) ? SwipeDirection.RIGHT : t(f) ? SwipeDirection.UP : p(f) ? SwipeDirection.DOWN : SwipeDirection.NONE;
    }

    private float c() {
        return this.a;
    }

    private float d() {
        return this.b;
    }

    private float e() {
        return b.h;
    }

    private float f() {
        return b.g;
    }

    private float g() {
        return b.d;
    }

    private float h() {
        return b.c;
    }

    private float i() {
        return b.b;
    }

    private float j() {
        return b.a;
    }

    private float k() {
        return b.f;
    }

    private float l() {
        return b.e;
    }

    private float m(View view) {
        return view.getHeight() / 2.0f;
    }

    private boolean p(float f) {
        return f >= f() && f <= e();
    }

    private boolean q(float f, float f2) {
        float abs = Math.abs(f);
        return abs > c() && abs >= Math.abs(f2);
    }

    private boolean r(float f) {
        return f >= h() && f <= g();
    }

    private boolean s(float f) {
        return f >= j() || f <= i();
    }

    private boolean t(float f) {
        return f >= l() && f <= k();
    }

    private boolean u(float f, float f2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        return abs2 > c() && abs2 >= abs;
    }

    private float v() {
        return 0.03f;
    }

    SwipeDirection b(float f, float f2, float f3, float f4) {
        SwipeDirection swipeDirection = SwipeDirection.NONE;
        SwipeDirection a2 = (Math.abs(f) > 0.0f || Math.abs(f2) > 0.0f) ? a(getDegreeOfRotation(f, f2)) : swipeDirection;
        return (((Math.abs(f3) > 0.0f ? 1 : (Math.abs(f3) == 0.0f ? 0 : -1)) > 0 || (Math.abs(f4) > 0.0f ? 1 : (Math.abs(f4) == 0.0f ? 0 : -1)) > 0) ? a(getDegreeOfRotation(f3, f4)) : a2) != a2 ? swipeDirection : a2;
    }

    public float getDegreeOfRotation(float f, float f2) {
        float degrees = (float) Math.toDegrees(Math.atan2(-f2, f));
        return degrees < 0.0f ? degrees + 360.0f : degrees;
    }

    @NonNull
    public SwipeDirection getDirectionFromMovement(float f, float f2) {
        return (Math.abs(f) > 0.0f || Math.abs(f2) > 0.0f) ? a(getDegreeOfRotation(f, f2)) : SwipeDirection.NONE;
    }

    public float getMinThresholdForSwipe() {
        return this.c;
    }

    public float getRotation(@NonNull View view, float f, float f2) {
        return (f2 < m(view) ? 1 : -1) * f * v();
    }

    public boolean isSwipeThresholdCrossed(float f, float f2, float f3, float f4) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        boolean q = q(f3, f3);
        boolean u = u(f3, f4);
        int i = a.a[a(getDegreeOfRotation(f, f2)).ordinal()];
        if (i == 1 || i == 2) {
            if (abs > getMinThresholdForSwipe()) {
                return true;
            }
            if (q && abs > this.d) {
                return true;
            }
        } else if ((i == 3 || i == 4) && abs2 > abs) {
            if (abs2 > getMinThresholdForSwipe()) {
                return true;
            }
            if (u && abs2 > this.d) {
                return true;
            }
        }
        return false;
    }

    int n() {
        return 1000;
    }

    boolean o(float f, float f2) {
        return Math.sqrt(Math.pow((double) Math.abs(f), 2.0d) + Math.pow((double) Math.abs(f2), 2.0d)) <= ((double) d());
    }
}