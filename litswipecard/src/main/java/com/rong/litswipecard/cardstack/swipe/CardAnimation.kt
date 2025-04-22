package com.rong.litswipecard.cardstack.swipe;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.animation.AccelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import timber.log.Timber;

/* loaded from: classes7.dex */
public class CardAnimation implements Animator.AnimatorListener {
    public static final long DEFAULT_DURATION = 180;
    public static final float INVALID_ALPHA = Float.MIN_VALUE;
    private final RecyclerView.ViewHolder a0;
    private final PointF b0;
    private final ValueAnimator c0;
    private final AnimationType d0;
    private final Set e0;
    private final float f0;
    private final float g0;
    private final float h0;
    public final boolean hasAlphaAnimation;
    private final float i0;
    private final float j0;
    private final float k0;
    private final float l0;
    private final float m0;
    private State n0;
    private float o0;
    private float p0;
    private float q0;
    private float r0;
    private long s0;
    private float t0;
    private boolean u0;
    private boolean v0;

    public enum AnimationType {
        RECOVER,
        SWIPE_OUT
    }

    private enum State {
        INITIALIZED,
        RUNNING,
        FINISHED
    }

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CardAnimation.this.i(valueAnimator.getAnimatedFraction());
        }
    }

    protected CardAnimation(@NonNull RecyclerView.ViewHolder viewHolder, AnimationType animationType, PointF pointF, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.e0 = new HashSet(4);
        this.s0 = 180L;
        this.a0 = viewHolder;
        this.d0 = animationType;
        this.b0 = pointF;
        this.f0 = f;
        this.g0 = f2;
        this.h0 = f3;
        this.i0 = f4;
        this.n0 = State.INITIALIZED;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.c0 = ofFloat;
        ofFloat.addUpdateListener(new a());
        ofFloat.setInterpolator(new AccelerateInterpolator());
        ofFloat.setDuration(this.s0);
        ofFloat.setTarget(viewHolder.itemView);
        ofFloat.addListener(this);
        i(0.0f);
        if (f5 != -2.1474836E9f && f6 != -2.1474836E9f) {
            this.u0 = true;
        } else if (f5 != f6) {
            throw new IllegalStateException("Invalid Rotation values: startRotation=" + f5 + "::endRotation" + f6);
        }
        this.j0 = f5;
        this.k0 = f6;
        this.r0 = viewHolder.itemView.getAlpha();
        if (f7 == Float.MIN_VALUE || f8 == Float.MIN_VALUE) {
            this.hasAlphaAnimation = false;
            this.l0 = Float.MIN_VALUE;
            this.m0 = Float.MIN_VALUE;
        } else {
            this.hasAlphaAnimation = true;
            this.l0 = f7;
            this.m0 = f8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(float f) {
        this.t0 = f;
    }

    private void l(Animator animator) {
        Iterator it2 = this.e0.iterator();
        while (it2.hasNext()) {
            ((Animator.AnimatorListener) it2.next()).onAnimationCancel(animator);
        }
        this.e0.clear();
    }

    private void m(Animator animator) {
        Iterator it2 = this.e0.iterator();
        while (it2.hasNext()) {
            ((Animator.AnimatorListener) it2.next()).onAnimationEnd(animator);
        }
        this.e0.clear();
    }

    private void n(Animator animator) {
        Iterator it2 = this.e0.iterator();
        while (it2.hasNext()) {
            ((Animator.AnimatorListener) it2.next()).onAnimationStart(animator);
        }
    }

    public void addListener(@NonNull Animator.AnimatorListener animatorListener) {
        if (this.n0 != State.FINISHED) {
            this.e0.add(animatorListener);
            return;
        }
        Timber.w("Attaching Listener after animation is over::" + this, new Object[0]);
    }

    public void addUpdateListener(@NonNull ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.c0.addUpdateListener(animatorUpdateListener);
    }

    void b() {
        this.c0.cancel();
    }

    long c() {
        return this.s0;
    }

    float d() {
        return this.h0;
    }

    float e() {
        return this.i0;
    }

    float f() {
        return this.f0;
    }

    float g() {
        return this.g0;
    }

    public AnimationType getAnimationType() {
        return this.d0;
    }

    public float getCurrAlpha() {
        return this.hasAlphaAnimation ? this.r0 : this.a0.itemView.getAlpha();
    }

    public float getCurrRotation() {
        return this.q0;
    }

    public float getCurrX() {
        return this.o0;
    }

    public float getCurrY() {
        return this.p0;
    }

    public PointF getFirstTouchPoint() {
        return this.b0;
    }

    @NonNull
    public RecyclerView.ViewHolder getViewHolder() {
        return this.a0;
    }

    boolean h() {
        return this.u0;
    }

    public boolean isFlaggedForRemoval() {
        return this.v0;
    }

    public boolean isRunning() {
        return this.n0 == State.RUNNING;
    }

    void j(boolean z) {
        this.u0 = z;
    }

    CardAnimation k() {
        o();
        float f = this.o0;
        float f2 = this.p0;
        float f3 = this.h0;
        float f4 = this.i0;
        long j = this.s0;
        long max = Math.max(0L, j - ((long) (this.t0 * j)));
        CardAnimation cardAnimation = new CardAnimation(this.a0, this.d0, this.b0, f, f2, f3, f4);
        cardAnimation.setDuration(max);
        return cardAnimation;
    }

    void o() {
        float f = this.f0;
        float f2 = this.h0;
        if (f == f2) {
            this.o0 = ViewCompat.getTranslationX(this.a0.itemView);
        } else {
            this.o0 = f + (this.t0 * (f2 - f));
        }
        float f3 = this.g0;
        float f4 = this.i0;
        if (f3 == f4) {
            this.p0 = ViewCompat.getTranslationY(this.a0.itemView);
        } else {
            this.p0 = f3 + (this.t0 * (f4 - f3));
        }
        if (h()) {
            float f5 = this.j0;
            float f6 = this.k0;
            if (f5 == f6) {
                this.q0 = ViewCompat.getRotation(getViewHolder().itemView);
            } else {
                this.q0 = f5 + (this.t0 * (f6 - f5));
            }
        }
        if (this.hasAlphaAnimation) {
            float f7 = this.l0;
            float f8 = this.m0;
            if (f7 == f8) {
                this.r0 = ViewCompat.getAlpha(getViewHolder().itemView);
            } else {
                this.r0 = f7 + (this.t0 * (f8 - f7));
            }
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        if (isRunning()) {
            this.a0.setIsRecyclable(true);
            this.c0.removeAllUpdateListeners();
        }
        i(1.0f);
        this.n0 = State.FINISHED;
        l(animator);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (isRunning()) {
            this.a0.setIsRecyclable(true);
            this.c0.removeAllUpdateListeners();
        }
        this.n0 = State.FINISHED;
        m(animator);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        n(animator);
    }

    public void setDuration(long j) {
        this.s0 = j;
        this.c0.setDuration(j);
    }

    public void setFlaggedForRemoval(boolean z) {
        this.v0 = z;
    }

    public void setInterpolator(@NonNull TimeInterpolator timeInterpolator) {
        this.c0.setInterpolator(timeInterpolator);
    }

    public void start() {
        this.n0 = State.RUNNING;
        this.a0.setIsRecyclable(false);
        o();
        this.c0.start();
    }

    public String toString() {
        return "[vh=" + this.a0 + ":animationType=" + this.d0 + "::sx=" + this.f0 + "::sy=" + this.g0 + "::ex=" + this.h0 + "::ey=" + this.i0 + "::currX=" + this.o0 + "::currY=" + this.p0 + "::duration=" + this.s0 + "::aimationState=" + this.n0 + "::flaggedForRemoval=" + this.v0 + "::hasRot=" + h() + "::fraction=" + this.t0 + "]";
    }

    CardAnimation(RecyclerView.ViewHolder viewHolder, AnimationType animationType, PointF pointF, float f, float f2, float f3, float f4) {
        this(viewHolder, animationType, pointF, f, f2, f3, f4, -2.1474836E9f, -2.1474836E9f, Float.MIN_VALUE, Float.MIN_VALUE);
    }
}