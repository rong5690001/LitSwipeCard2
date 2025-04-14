package com.rong.litswipecard.cardstack.swipe;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.animation.SwipeLeftAnimation;
import com.rong.litswipecard.cardstack.animation.SwipeRightAnimation;
import com.rong.litswipecard.cardstack.animation.SwipeUpAnimation;
import com.rong.litswipecard.cardstack.animation.interpolator.SwipeOutInterpolator;
import com.rong.litswipecard.cardstack.cardstack.CardStackSwipeHelper;
import com.tinder.cardstack.model.Direction;
import com.tinder.cardstack.model.SwipeDirection;
import com.tinder.cardstack.swipe.CardAnimation;
import com.tinder.cardstack.view.CardViewHolder;
import timber.log.Timber;

/* loaded from: classes7.dex */
public abstract class CardItemTouchListener implements RecyclerView.OnItemTouchListener {
    private static final CardStackSwipeHelper.OnPreSwipeListener g0 = new a();
    private final SwipeThresholdDetector a0;

    @Nullable
    protected TouchPointer activeTouchPointer;
    private final CardAnimator b0;
    private final CardItemTouchHelperUtil c0;
    private final PointF d0 = new PointF();
    private CardStackSwipeHelper.OnPreSwipeListener e0 = g0;
    private VelocityTracker f0;

    class a implements CardStackSwipeHelper.OnPreSwipeListener {
        a() {
        }

        @Override // com.tinder.cardstack.cardstack.CardStackSwipeHelper.OnPreSwipeListener
        public boolean onPreSwipe(int i, SwipeDirection swipeDirection) {
            return false;
        }
    }

    static /* synthetic */ class b {
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
        }
    }

    public CardItemTouchListener(@NonNull SwipeThresholdDetector swipeThresholdDetector, @NonNull CardAnimator cardAnimator, @NonNull CardItemTouchHelperUtil cardItemTouchHelperUtil) {
        this.a0 = swipeThresholdDetector;
        this.b0 = cardAnimator;
        this.c0 = cardItemTouchHelperUtil;
    }

    private void a() {
        getRecyclerView().requestDisallowInterceptTouchEvent(false);
    }

    private void b(PointF pointF, MotionEvent motionEvent, int i) {
        if (this.activeTouchPointer == null) {
            pointF.set(0.0f, 0.0f);
            return;
        }
        float x = motionEvent.getX(i);
        float y = motionEvent.getY(i);
        float d = x - this.activeTouchPointer.d();
        float e = y - this.activeTouchPointer.e();
        if (this.activeTouchPointer.b() != 0.0f || this.activeTouchPointer.c() != 0.0f) {
            pointF.set(d, e);
        } else if (this.a0.o(d, e)) {
            pointF.set(0.0f, 0.0f);
        } else {
            this.activeTouchPointer.j(x, y);
            pointF.set(0.1f, 0.1f);
        }
    }

    private boolean c(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CardViewHolder) {
            return ((CardViewHolder) viewHolder).canDragCard(Direction.DOWN);
        }
        return true;
    }

    private boolean d(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CardViewHolder) {
            return ((CardViewHolder) viewHolder).canDragCard(Direction.UP);
        }
        return true;
    }

    private void e(View view, MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        view.dispatchTouchEvent(obtain);
        obtain.recycle();
    }

    private void f(View view, MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked != 1 && actionMasked != 3) {
            motionEvent.setAction(1);
        }
        view.dispatchTouchEvent(motionEvent);
    }

    private void g(TouchPointer touchPointer) {
        float endX;
        float f;
        float f2;
        float h;
        float abs;
        float f3;
        float f4;
        float f5;
        float f6;
        float b2 = touchPointer.b();
        float c = touchPointer.c();
        RecyclerView.ViewHolder viewHolder = touchPointer.getViewHolder();
        View view = viewHolder.itemView;
        float translationX = ViewCompat.getTranslationX(view);
        float translationY = ViewCompat.getTranslationY(view);
        float translationX2 = view.getTranslationX();
        float translationY2 = view.getTranslationY();
        float k = k(touchPointer, this.f0);
        int i = b.a[this.a0.getDirectionFromMovement(b2, c).ordinal()];
        if (i == 1) {
            endX = new SwipeLeftAnimation().endX();
            f = endX - translationX;
            f2 = (k * f) + translationY;
            h = h();
            abs = Math.abs(endX);
        } else {
            if (i != 2) {
                if (i != 3) {
                    f5 = 0.0f;
                    f = 0.0f;
                    f4 = 0.0f;
                    f6 = translationY2;
                    f3 = translationX2;
                } else {
                    float endY = new SwipeUpAnimation().endY();
                    f = endY - translationY;
                    f6 = endY;
                    f4 = i();
                    f3 = translationX2;
                    f5 = Math.abs(endY);
                }
                float f7 = f5 / 180.0f;
                CardAnimation cardAnimation = new CardAnimation(viewHolder, CardAnimation.AnimationType.SWIPE_OUT, touchPointer.getFirstTouchPoint(), translationX, translationY, f3, f6);
                float abs2 = Math.abs(f);
                long max = (long) (abs2 / Math.max(f7, f4));
                Interpolator j = j(f4, f7, max, abs2);
                cardAnimation.setDuration(max);
                cardAnimation.setInterpolator(j);
                this.b0.addActiveAnimation(cardAnimation);
                cardAnimation.start();
                getRecyclerView().invalidate();
            }
            endX = new SwipeRightAnimation().endX();
            f = endX - translationX;
            f2 = (k * f) + translationY;
            h = h();
            abs = Math.abs(endX);
        }
        f3 = endX;
        f4 = h;
        f5 = abs;
        f6 = f2;
        float f72 = f5 / 180.0f;
        CardAnimation cardAnimation2 = new CardAnimation(viewHolder, CardAnimation.AnimationType.SWIPE_OUT, touchPointer.getFirstTouchPoint(), translationX, translationY, f3, f6);
        float abs22 = Math.abs(f);
        long max2 = (long) (abs22 / Math.max(f72, f4));
        Interpolator j2 = j(f4, f72, max2, abs22);
        cardAnimation2.setDuration(max2);
        cardAnimation2.setInterpolator(j2);
        this.b0.addActiveAnimation(cardAnimation2);
        cardAnimation2.start();
        getRecyclerView().invalidate();
    }

    private float h() {
        VelocityTracker velocityTracker = this.f0;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(this.a0.n());
        return Math.abs(this.f0.getXVelocity()) / 1000.0f;
    }

    private float i() {
        VelocityTracker velocityTracker = this.f0;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(this.a0.n());
        return Math.abs(this.f0.getYVelocity()) / 1000.0f;
    }

    private Interpolator j(float f, float f2, long j, float f3) {
        return f < f2 ? new SwipeOutInterpolator(f3, f, j) : new LinearInterpolator();
    }

    private float k(TouchPointer touchPointer, VelocityTracker velocityTracker) {
        float b2 = touchPointer.b();
        float c = b2 == 0.0f ? 0.0f : touchPointer.c() / b2;
        if (velocityTracker == null) {
            return c;
        }
        float xVelocity = velocityTracker.getXVelocity();
        return xVelocity != 0.0f ? velocityTracker.getYVelocity() / xVelocity : c;
    }

    private boolean l(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CardViewHolder) {
            return ((CardViewHolder) viewHolder).isSwipable();
        }
        return true;
    }

    private boolean m(TouchPointer touchPointer) {
        VelocityTracker velocityTracker = this.f0;
        if (velocityTracker != null) {
            return this.c0.b(touchPointer, velocityTracker, this.a0);
        }
        Timber.w("Check implementation: velocityTracker is Null::", new Object[0]);
        return false;
    }

    private void n() {
        VelocityTracker velocityTracker = this.f0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f0 = null;
        }
    }

    private void o(MotionEvent motionEvent) {
        TouchPointer touchPointer = this.activeTouchPointer;
        if (touchPointer != null) {
            RecyclerView.ViewHolder viewHolder = touchPointer.getViewHolder();
            boolean f = this.activeTouchPointer.f();
            if (!this.c0.c(this.activeTouchPointer, this.a0) || f) {
                e(viewHolder.itemView, motionEvent);
                boolean isReadyToAcceptSwipes = this.c0.isReadyToAcceptSwipes(viewHolder, getRecyclerView(), this.b0);
                boolean m = m(this.activeTouchPointer);
                if (isReadyToAcceptSwipes && m) {
                    float b2 = this.activeTouchPointer.b();
                    float c = this.activeTouchPointer.c();
                    VelocityTracker velocityTracker = this.f0;
                    float xVelocity = velocityTracker == null ? 0.0f : velocityTracker.getXVelocity();
                    VelocityTracker velocityTracker2 = this.f0;
                    if (this.e0.onPreSwipe(viewHolder.getAdapterPosition(), this.a0.b(b2, c, xVelocity, velocityTracker2 != null ? velocityTracker2.getYVelocity() : 0.0f))) {
                        g(this.activeTouchPointer);
                    } else {
                        this.b0.startRecoverAnimation(viewHolder, getRecyclerView(), this.activeTouchPointer.getFirstTouchPoint());
                    }
                } else {
                    this.b0.startRecoverAnimation(viewHolder, getRecyclerView(), this.activeTouchPointer.getFirstTouchPoint());
                }
            } else {
                this.b0.startRecoverAnimation(viewHolder, getRecyclerView(), this.activeTouchPointer.getFirstTouchPoint());
                f(viewHolder.itemView, motionEvent);
            }
        }
        unselectViewHolder(true);
    }

    private void p(MotionEvent motionEvent) {
        TouchPointer touchPointer = this.activeTouchPointer;
        if (touchPointer != null) {
            RecyclerView.ViewHolder viewHolder = touchPointer.getViewHolder();
            e(viewHolder.itemView, motionEvent);
            this.b0.startRecoverAnimation(viewHolder, getRecyclerView(), this.activeTouchPointer.getFirstTouchPoint());
        }
        unselectViewHolder(false);
    }

    private void q(MotionEvent motionEvent) {
        VelocityTracker velocityTracker = this.f0;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
    }

    protected void disallowTouchIntercept() {
        getRecyclerView().requestDisallowInterceptTouchEvent(true);
    }

    protected void dispatchActionDownEvent(@NonNull View view, @NonNull MotionEvent motionEvent) {
        if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
            motionEvent.setAction(0);
        }
        view.dispatchTouchEvent(motionEvent);
    }

    protected void findAndSelectViewHolder(MotionEvent motionEvent) {
        if (this.activeTouchPointer == null && !this.b0.isInPausedState()) {
            RecyclerView.ViewHolder findSelectableViewHolder = this.c0.findSelectableViewHolder(motionEvent, getRecyclerView(), this.b0);
            if (findSelectableViewHolder != null && l(findSelectableViewHolder)) {
                DragConstraints dragConstraints = new DragConstraints(d(findSelectableViewHolder), c(findSelectableViewHolder));
                CardAnimation findCardAnimation = this.b0.findCardAnimation(findSelectableViewHolder);
                if (findCardAnimation == null || findCardAnimation.getAnimationType() == CardAnimation.AnimationType.SWIPE_OUT) {
                    this.activeTouchPointer = newTouchPointer(findSelectableViewHolder, motionEvent, dragConstraints);
                } else {
                    float x = motionEvent.getX() - findCardAnimation.getCurrX();
                    float y = motionEvent.getY() - findCardAnimation.getCurrY();
                    float x2 = motionEvent.getX() - x;
                    float y2 = motionEvent.getY() - y;
                    TouchPointer newTouchPointer = newTouchPointer(findCardAnimation, motionEvent, dragConstraints);
                    this.activeTouchPointer = newTouchPointer;
                    newTouchPointer.g(x2);
                    this.activeTouchPointer.h(y2);
                    this.b0.endCardAnimation(findSelectableViewHolder);
                }
                disallowTouchIntercept();
                getRecyclerView().invalidate();
            }
        }
    }

    @Nullable
    public TouchPointer getActiveTouchPointer() {
        return this.activeTouchPointer;
    }

    @NonNull
    protected abstract RecyclerView getRecyclerView();

    protected void handleActionUp(@NonNull MotionEvent motionEvent) {
        o(motionEvent);
        n();
    }

    @NonNull
    protected TouchPointer newTouchPointer(@NonNull CardAnimation cardAnimation, @NonNull MotionEvent motionEvent, DragConstraints dragConstraints) {
        return new TouchPointer(cardAnimation.getViewHolder(), cardAnimation.isRunning() ? cardAnimation.getFirstTouchPoint() : new PointF(motionEvent.getX(), motionEvent.getY()), motionEvent.getX() - cardAnimation.getCurrX(), motionEvent.getY() - cardAnimation.getCurrY(), motionEvent.getPointerId(0), dragConstraints);
    }

    protected void obtainVelocityTracker() {
        VelocityTracker velocityTracker = this.f0;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.f0 = VelocityTracker.obtain();
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000d, code lost:
    
        if (r3 != 3) goto L18;
     */
    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView r3, android.view.MotionEvent r4) {
        /*
            r2 = this;
            int r3 = androidx.core.view.MotionEventCompat.getActionMasked(r4)
            r0 = 1
            if (r3 == 0) goto L20
            if (r3 == r0) goto L1c
            r1 = 2
            if (r3 == r1) goto L10
            r1 = 3
            if (r3 == r1) goto L1c
            goto L3a
        L10:
            com.tinder.cardstack.swipe.TouchPointer r3 = r2.activeTouchPointer
            if (r3 == 0) goto L3a
            androidx.recyclerview.widget.RecyclerView r3 = r2.getRecyclerView()
            r2.onTouchEvent(r3, r4)
            goto L3a
        L1c:
            r2.handleActionUp(r4)
            goto L3a
        L20:
            r2.disallowTouchIntercept()
            r2.obtainVelocityTracker()
            r2.findAndSelectViewHolder(r4)
            com.tinder.cardstack.swipe.TouchPointer r3 = r2.activeTouchPointer
            if (r3 == 0) goto L37
            androidx.recyclerview.widget.RecyclerView$ViewHolder r3 = r3.getViewHolder()
            android.view.View r3 = r3.itemView
            r2.dispatchActionDownEvent(r3, r4)
            goto L3a
        L37:
            r2.a()
        L3a:
            com.tinder.cardstack.swipe.TouchPointer r3 = r2.activeTouchPointer
            if (r3 == 0) goto L3f
            goto L40
        L3f:
            r0 = 0
        L40:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tinder.cardstack.swipe.CardItemTouchListener.onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.activeTouchPointer == null) {
            return;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int findPointerIndex = motionEvent.findPointerIndex(this.activeTouchPointer.a());
        if (actionMasked != 1) {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    if (actionMasked == 6 && motionEvent.getPointerId(MotionEventCompat.getActionIndex(motionEvent)) == this.activeTouchPointer.a()) {
                        p(motionEvent);
                    }
                }
            } else {
                if (findPointerIndex < 0) {
                    return;
                }
                b(this.d0, motionEvent, findPointerIndex);
                PointF pointF = this.d0;
                float f = pointF.x;
                if (f == 0.0f && pointF.y == 0.0f) {
                    return;
                }
                this.activeTouchPointer.g(f);
                DragConstraints dragConstraints = this.activeTouchPointer.i;
                boolean z = false;
                boolean z2 = this.d0.y > 0.0f && dragConstraints.getCanDragDown();
                if (this.d0.y < 0.0f && dragConstraints.getCanDragUp()) {
                    z = true;
                }
                if (z2 || z) {
                    this.activeTouchPointer.h(this.d0.y);
                }
                if (!this.c0.c(this.activeTouchPointer, this.a0) && !this.activeTouchPointer.f()) {
                    this.activeTouchPointer.i(true);
                }
                getRecyclerView().invalidate();
            }
            q(motionEvent);
        }
        handleActionUp(motionEvent);
        q(motionEvent);
    }

    public void setPreSwipeListener(@NonNull CardStackSwipeHelper.OnPreSwipeListener onPreSwipeListener) {
        this.e0 = onPreSwipeListener;
    }

    public void unselectViewHolder(boolean z) {
        this.activeTouchPointer = null;
        if (z) {
            a();
        }
    }

    public void unselectViewHolderDoNotPublishUpdate() {
        TouchPointer touchPointer = this.activeTouchPointer;
        if (touchPointer != null) {
            this.b0.startRecoverAnimation(touchPointer.getViewHolder(), getRecyclerView(), this.activeTouchPointer.getFirstTouchPoint());
        }
        unselectViewHolder(false);
    }

    @NonNull
    protected TouchPointer newTouchPointer(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull MotionEvent motionEvent, DragConstraints dragConstraints) {
        return new TouchPointer(viewHolder, motionEvent, dragConstraints);
    }
}