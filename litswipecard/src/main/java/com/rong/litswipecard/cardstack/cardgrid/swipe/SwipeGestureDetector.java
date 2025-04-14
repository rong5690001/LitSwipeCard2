package com.rong.litswipecard.cardstack.cardgrid.swipe;

import android.view.MotionEvent;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.tinder.cardstack.cardgrid.swipe.PointerTracker;
import com.tinder.cardstack.cardgrid.swipe.model.Pointer;
import com.tinder.cardstack.model.SwipeDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\u00074\u00145678\u0018B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR$\u0010'\u001a\u0004\u0018\u00010 8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0014\u0010+\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010/\u001a\u00060,R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0018\u00103\u001a\u000600R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102¨\u00069"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector;", "", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$TouchInterceptPredicate;", "touchInterceptPredicate", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$SwipeActionRecognizer;", "swipeActionRecognizer", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$ClickActionRecognizer;", "clickActionRecognizer", "<init>", "(Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$TouchInterceptPredicate;Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$SwipeActionRecognizer;Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$ClickActionRecognizer;)V", "Landroid/view/MotionEvent;", "event", "", "shouldInterceptTouchEvent$cardstack_release", "(Landroid/view/MotionEvent;)Z", "shouldInterceptTouchEvent", "", "handleTouchEvent$cardstack_release", "(Landroid/view/MotionEvent;)V", "handleTouchEvent", "a", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$TouchInterceptPredicate;", "getTouchInterceptPredicate$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$TouchInterceptPredicate;", "b", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$SwipeActionRecognizer;", "getSwipeActionRecognizer$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$SwipeActionRecognizer;", "c", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$ClickActionRecognizer;", "getClickActionRecognizer$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$ClickActionRecognizer;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$OnGestureListener;", "d", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$OnGestureListener;", "getOnGestureListener$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$OnGestureListener;", "setOnGestureListener$cardstack_release", "(Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$OnGestureListener;)V", "onGestureListener", "Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker;", "e", "Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker;", "pointerTracker", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$b;", "f", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$b;", "touchInterceptor", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$a;", "g", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$a;", "actionRecognizer", "Action", "ClickActionRecognizer", "OnGestureListener", "SwipeActionRecognizer", "TouchInterceptPredicate", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class SwipeGestureDetector {

    /* renamed from: a, reason: from kotlin metadata */
    private final TouchInterceptPredicate touchInterceptPredicate;

    /* renamed from: b, reason: from kotlin metadata */
    private final SwipeActionRecognizer swipeActionRecognizer;

    /* renamed from: c, reason: from kotlin metadata */
    private final ClickActionRecognizer clickActionRecognizer;

    /* renamed from: d, reason: from kotlin metadata */
    private OnGestureListener onGestureListener;

    /* renamed from: e, reason: from kotlin metadata */
    private final PointerTracker pointerTracker;

    /* renamed from: f, reason: from kotlin metadata */
    private final b touchInterceptor;

    /* renamed from: g, reason: from kotlin metadata */
    private final a actionRecognizer;

    private static abstract class Action {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$Action$CLICK;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$Action;", "()V", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class CLICK extends Action {

            @NotNull
            public static final CLICK INSTANCE = new CLICK();

            private CLICK() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$Action$RECOVER;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$Action;", "()V", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class RECOVER extends Action {

            @NotNull
            public static final RECOVER INSTANCE = new RECOVER();

            private RECOVER() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$Action$SWIPE;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$Action;", "Lcom/tinder/cardstack/model/SwipeDirection;", "swipeDirection", "<init>", "(Lcom/tinder/cardstack/model/SwipeDirection;)V", "component1", "()Lcom/tinder/cardstack/model/SwipeDirection;", "copy", "(Lcom/tinder/cardstack/model/SwipeDirection;)Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$Action$SWIPE;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "a", "Lcom/tinder/cardstack/model/SwipeDirection;", "getSwipeDirection", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final /* data */ class SWIPE extends Action {

            /* renamed from: a, reason: from kotlin metadata and from toString */
            private final SwipeDirection swipeDirection;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SWIPE(@NotNull SwipeDirection swipeDirection) {
                super(null);
                Intrinsics.checkNotNullParameter(swipeDirection, "swipeDirection");
                this.swipeDirection = swipeDirection;
            }

            public static /* synthetic */ SWIPE copy$default(SWIPE swipe, SwipeDirection swipeDirection, int i, Object obj) {
                if ((i & 1) != 0) {
                    swipeDirection = swipe.swipeDirection;
                }
                return swipe.copy(swipeDirection);
            }

            @NotNull
            /* renamed from: component1, reason: from getter */
            public final SwipeDirection getSwipeDirection() {
                return this.swipeDirection;
            }

            @NotNull
            public final SWIPE copy(@NotNull SwipeDirection swipeDirection) {
                Intrinsics.checkNotNullParameter(swipeDirection, "swipeDirection");
                return new SWIPE(swipeDirection);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof SWIPE) && this.swipeDirection == ((SWIPE) other).swipeDirection;
            }

            @NotNull
            public final SwipeDirection getSwipeDirection() {
                return this.swipeDirection;
            }

            public int hashCode() {
                return this.swipeDirection.hashCode();
            }

            @NotNull
            public String toString() {
                return "SWIPE(swipeDirection=" + this.swipeDirection + ')';
            }
        }

        public /* synthetic */ Action(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Action() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$ClickActionRecognizer;", "", "isClick", "", "pointer", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface ClickActionRecognizer {
        boolean isClick(@NotNull Pointer pointer);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$OnGestureListener;", "", "onClick", "", "pointer", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "onMove", "onRecover", "onSwipe", "swipeDirection", "Lcom/tinder/cardstack/model/SwipeDirection;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface OnGestureListener {
        void onClick(@NotNull Pointer pointer);

        void onMove(@NotNull Pointer pointer);

        void onRecover(@NotNull Pointer pointer);

        void onSwipe(@NotNull Pointer pointer, @NotNull SwipeDirection swipeDirection);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$SwipeActionRecognizer;", "", "findSwipeDirection", "Lcom/tinder/cardstack/model/SwipeDirection;", "pointer", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface SwipeActionRecognizer {
        @NotNull
        SwipeDirection findSwipeDirection(@NotNull Pointer pointer);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$TouchInterceptPredicate;", "", "shouldIntercept", "", "pointer", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface TouchInterceptPredicate {
        boolean shouldIntercept(@NotNull Pointer pointer);
    }

    private final class a implements PointerTracker.Listener {
        public a() {
        }

        private final Action a(Pointer pointer) {
            if (SwipeGestureDetector.this.getClickActionRecognizer().isClick(pointer)) {
                return Action.CLICK.INSTANCE;
            }
            SwipeDirection findSwipeDirection = SwipeGestureDetector.this.getSwipeActionRecognizer().findSwipeDirection(pointer);
            return findSwipeDirection != SwipeDirection.NONE ? new Action.SWIPE(findSwipeDirection) : Action.RECOVER.INSTANCE;
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.PointerTracker.Listener
        public void onPointerDown(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.PointerTracker.Listener
        public void onPointerMove(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            OnGestureListener onGestureListener = SwipeGestureDetector.this.getOnGestureListener();
            if (onGestureListener != null) {
                onGestureListener.onMove(pointer);
            }
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.PointerTracker.Listener
        public void onPointerUp(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            Action a = a(pointer);
            if (a instanceof Action.CLICK) {
                OnGestureListener onGestureListener = SwipeGestureDetector.this.getOnGestureListener();
                if (onGestureListener != null) {
                    onGestureListener.onClick(pointer);
                    return;
                }
                return;
            }
            if (a instanceof Action.SWIPE) {
                OnGestureListener onGestureListener2 = SwipeGestureDetector.this.getOnGestureListener();
                if (onGestureListener2 != null) {
                    onGestureListener2.onSwipe(pointer, ((Action.SWIPE) a).getSwipeDirection());
                    return;
                }
                return;
            }
            OnGestureListener onGestureListener3 = SwipeGestureDetector.this.getOnGestureListener();
            if (onGestureListener3 != null) {
                onGestureListener3.onRecover(pointer);
            }
        }
    }

    private final class b implements PointerTracker.Listener {
        private boolean a;

        public b() {
        }

        private final void b(Pointer pointer) {
            if (this.a || !SwipeGestureDetector.this.getTouchInterceptPredicate().shouldIntercept(pointer)) {
                return;
            }
            this.a = true;
        }

        private final void c() {
            if (SwipeGestureDetector.this.pointerTracker.getPointers$cardstack_release().isEmpty()) {
                this.a = false;
                SwipeGestureDetector.this.pointerTracker.release$cardstack_release();
            }
        }

        public final boolean a() {
            return this.a;
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.PointerTracker.Listener
        public void onPointerDown(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.PointerTracker.Listener
        public void onPointerMove(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            b(pointer);
            if (this.a) {
                SwipeGestureDetector.this.actionRecognizer.onPointerMove(pointer);
            }
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.PointerTracker.Listener
        public void onPointerUp(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            if (this.a) {
                SwipeGestureDetector.this.actionRecognizer.onPointerUp(pointer);
            }
            c();
        }
    }

    public SwipeGestureDetector(@NotNull TouchInterceptPredicate touchInterceptPredicate, @NotNull SwipeActionRecognizer swipeActionRecognizer, @NotNull ClickActionRecognizer clickActionRecognizer) {
        Intrinsics.checkNotNullParameter(touchInterceptPredicate, "touchInterceptPredicate");
        Intrinsics.checkNotNullParameter(swipeActionRecognizer, "swipeActionRecognizer");
        Intrinsics.checkNotNullParameter(clickActionRecognizer, "clickActionRecognizer");
        this.touchInterceptPredicate = touchInterceptPredicate;
        this.swipeActionRecognizer = swipeActionRecognizer;
        this.clickActionRecognizer = clickActionRecognizer;
        PointerTracker pointerTracker = new PointerTracker();
        this.pointerTracker = pointerTracker;
        b bVar = new b();
        this.touchInterceptor = bVar;
        this.actionRecognizer = new a();
        pointerTracker.setListener$cardstack_release(bVar);
    }

    @NotNull
    /* renamed from: getClickActionRecognizer$cardstack_release, reason: from getter */
    public final ClickActionRecognizer getClickActionRecognizer() {
        return this.clickActionRecognizer;
    }

    @Nullable
    /* renamed from: getOnGestureListener$cardstack_release, reason: from getter */
    public final OnGestureListener getOnGestureListener() {
        return this.onGestureListener;
    }

    @NotNull
    /* renamed from: getSwipeActionRecognizer$cardstack_release, reason: from getter */
    public final SwipeActionRecognizer getSwipeActionRecognizer() {
        return this.swipeActionRecognizer;
    }

    @NotNull
    /* renamed from: getTouchInterceptPredicate$cardstack_release, reason: from getter */
    public final TouchInterceptPredicate getTouchInterceptPredicate() {
        return this.touchInterceptPredicate;
    }

    public final void handleTouchEvent$cardstack_release(@NotNull MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.pointerTracker.track$cardstack_release(event);
    }

    public final void setOnGestureListener$cardstack_release(@Nullable OnGestureListener onGestureListener) {
        this.onGestureListener = onGestureListener;
    }

    public final boolean shouldInterceptTouchEvent$cardstack_release(@NotNull MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        handleTouchEvent$cardstack_release(event);
        return this.touchInterceptor.a();
    }
}