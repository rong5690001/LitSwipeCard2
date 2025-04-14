package com.rong.litswipecard.cardstack.cardgrid.view;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.rong.litswipecard.cardstack.cardgrid.model.Point;
import com.rong.litswipecard.cardstack.cardgrid.swipe.SwipeGestureDetector;
import com.rong.litswipecard.cardstack.cardgrid.swipe.model.Pointer;
import com.tinder.cardstack.model.SwipeDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\u00020\t*\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\t*\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\u000bJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u0012R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/SwipeActionRecognizerImpl;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$SwipeActionRecognizer;", "Lcom/tinder/cardstack/cardgrid/view/SwipeActionRecognizerImpl$Configuration;", "configuration", "Lcom/tinder/cardstack/cardgrid/view/SwipeDirectionRecognizer;", "swipeDirectionRecognizer", "<init>", "(Lcom/tinder/cardstack/cardgrid/view/SwipeActionRecognizerImpl$Configuration;Lcom/tinder/cardstack/cardgrid/view/SwipeDirectionRecognizer;)V", "Lcom/tinder/cardstack/cardgrid/model/Point;", "", "b", "(Lcom/tinder/cardstack/cardgrid/model/Point;)Z", "a", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "pointer", "Lcom/tinder/cardstack/model/SwipeDirection;", "findSwipeDirection", "(Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;)Lcom/tinder/cardstack/model/SwipeDirection;", "Lcom/tinder/cardstack/cardgrid/view/SwipeActionRecognizerImpl$Configuration;", "Lcom/tinder/cardstack/cardgrid/view/SwipeDirectionRecognizer;", "Configuration", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class SwipeActionRecognizerImpl implements SwipeGestureDetector.SwipeActionRecognizer {

    /* renamed from: a, reason: from kotlin metadata */
    private final Configuration configuration;

    /* renamed from: b, reason: from kotlin metadata */
    private final SwipeDirectionRecognizer swipeDirectionRecognizer;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\tJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\tJ.\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0019\u001a\u0004\b\u001c\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001e\u0010\t¨\u0006\u001f"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/SwipeActionRecognizerImpl$Configuration;", "", "", "flingEscapeVelocity", "minThresholdForSwipe", "minThresholdForSwipeWhenFling", "<init>", "(FFF)V", "component1", "()F", "component2", "component3", "copy", "(FFF)Lcom/tinder/cardstack/cardgrid/view/SwipeActionRecognizerImpl$Configuration;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "a", "F", "getFlingEscapeVelocity", "b", "getMinThresholdForSwipe", "c", "getMinThresholdForSwipeWhenFling", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Configuration {

        /* renamed from: a, reason: from kotlin metadata and from toString */
        private final float flingEscapeVelocity;

        /* renamed from: b, reason: from kotlin metadata and from toString */
        private final float minThresholdForSwipe;

        /* renamed from: c, reason: from kotlin metadata and from toString */
        private final float minThresholdForSwipeWhenFling;

        public Configuration(float f, float f2, float f3) {
            this.flingEscapeVelocity = f;
            this.minThresholdForSwipe = f2;
            this.minThresholdForSwipeWhenFling = f3;
        }

        public static /* synthetic */ Configuration copy$default(Configuration configuration, float f, float f2, float f3, int i, Object obj) {
            if ((i & 1) != 0) {
                f = configuration.flingEscapeVelocity;
            }
            if ((i & 2) != 0) {
                f2 = configuration.minThresholdForSwipe;
            }
            if ((i & 4) != 0) {
                f3 = configuration.minThresholdForSwipeWhenFling;
            }
            return configuration.copy(f, f2, f3);
        }

        /* renamed from: component1, reason: from getter */
        public final float getFlingEscapeVelocity() {
            return this.flingEscapeVelocity;
        }

        /* renamed from: component2, reason: from getter */
        public final float getMinThresholdForSwipe() {
            return this.minThresholdForSwipe;
        }

        /* renamed from: component3, reason: from getter */
        public final float getMinThresholdForSwipeWhenFling() {
            return this.minThresholdForSwipeWhenFling;
        }

        @NotNull
        public final Configuration copy(float flingEscapeVelocity, float minThresholdForSwipe, float minThresholdForSwipeWhenFling) {
            return new Configuration(flingEscapeVelocity, minThresholdForSwipe, minThresholdForSwipeWhenFling);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Configuration)) {
                return false;
            }
            Configuration configuration = (Configuration) other;
            return Float.compare(this.flingEscapeVelocity, configuration.flingEscapeVelocity) == 0 && Float.compare(this.minThresholdForSwipe, configuration.minThresholdForSwipe) == 0 && Float.compare(this.minThresholdForSwipeWhenFling, configuration.minThresholdForSwipeWhenFling) == 0;
        }

        public final float getFlingEscapeVelocity() {
            return this.flingEscapeVelocity;
        }

        public final float getMinThresholdForSwipe() {
            return this.minThresholdForSwipe;
        }

        public final float getMinThresholdForSwipeWhenFling() {
            return this.minThresholdForSwipeWhenFling;
        }

        public int hashCode() {
            return (((Float.hashCode(this.flingEscapeVelocity) * 31) + Float.hashCode(this.minThresholdForSwipe)) * 31) + Float.hashCode(this.minThresholdForSwipeWhenFling);
        }

        @NotNull
        public String toString() {
            return "Configuration(flingEscapeVelocity=" + this.flingEscapeVelocity + ", minThresholdForSwipe=" + this.minThresholdForSwipe + ", minThresholdForSwipeWhenFling=" + this.minThresholdForSwipeWhenFling + ')';
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SwipeDirection.values().length];
            try {
                iArr[SwipeDirection.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SwipeDirection.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SwipeDirection.UP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[SwipeDirection.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SwipeActionRecognizerImpl(@NotNull Configuration configuration, @NotNull SwipeDirectionRecognizer swipeDirectionRecognizer) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(swipeDirectionRecognizer, "swipeDirectionRecognizer");
        this.configuration = configuration;
        this.swipeDirectionRecognizer = swipeDirectionRecognizer;
    }

    private final boolean a(Point point) {
        float abs = Math.abs(point.getX());
        return abs > this.configuration.getFlingEscapeVelocity() && abs >= Math.abs(point.getY());
    }

    private final boolean b(Point point) {
        float abs = Math.abs(point.getX());
        float abs2 = Math.abs(point.getY());
        return abs2 > this.configuration.getFlingEscapeVelocity() && abs2 >= abs;
    }

    @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.SwipeActionRecognizer
    @NotNull
    public SwipeDirection findSwipeDirection(@NotNull Pointer pointer) {
        SwipeDirection findSwipeDirection;
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        Point displacement = pointer.getDisplacement();
        Point velocity = pointer.getVelocity();
        if (velocity != null && (findSwipeDirection = this.swipeDirectionRecognizer.findSwipeDirection(displacement)) == this.swipeDirectionRecognizer.findSwipeDirection(velocity)) {
            float abs = Math.abs(displacement.getX());
            float abs2 = Math.abs(displacement.getY());
            boolean a = a(velocity);
            boolean b = b(velocity);
            int i = WhenMappings.$EnumSwitchMapping$0[findSwipeDirection.ordinal()];
            if (i == 1 || i == 2) {
                if (abs > this.configuration.getMinThresholdForSwipe()) {
                    return findSwipeDirection;
                }
                if (a && abs > this.configuration.getMinThresholdForSwipeWhenFling()) {
                    return findSwipeDirection;
                }
            } else if ((i == 3 || i == 4) && abs2 > abs) {
                if (abs2 > this.configuration.getMinThresholdForSwipe()) {
                    return findSwipeDirection;
                }
                if (b && abs2 > this.configuration.getMinThresholdForSwipeWhenFling()) {
                    return findSwipeDirection;
                }
            }
            return SwipeDirection.NONE;
        }
        return SwipeDirection.NONE;
    }
}