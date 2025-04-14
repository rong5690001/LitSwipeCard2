package com.rong.litswipecard.cardstack.cardgrid.view;

import android.content.res.Resources;
import com.rong.litswipecard.cardstack.cardgrid.model.Point;
import com.rong.litswipecard.cardstack.cardgrid.swipe.SwipeGestureDetector;
import com.rong.litswipecard.cardstack.cardgrid.swipe.model.Pointer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/HorizontalTouchInterceptPredicate;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$TouchInterceptPredicate;", "()V", "shouldIntercept", "", "pointer", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "Companion", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class HorizontalTouchInterceptPredicate implements SwipeGestureDetector.TouchInterceptPredicate {

    /* renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final float a = 4 * Resources.getSystem().getDisplayMetrics().density;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/HorizontalTouchInterceptPredicate$Companion;", "", "()V", "MIN_DISPLACEMENT_FOR_HORIZONTAL_SWIPE", "", "getMIN_DISPLACEMENT_FOR_HORIZONTAL_SWIPE", "()F", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final float getMIN_DISPLACEMENT_FOR_HORIZONTAL_SWIPE() {
            return HorizontalTouchInterceptPredicate.a;
        }

        private Companion() {
        }
    }

    @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.TouchInterceptPredicate
    public boolean shouldIntercept(@NotNull Pointer pointer) {
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        Point displacement = pointer.getDisplacement();
        if (displacement.isZero()) {
            return false;
        }
        float x = displacement.getX();
        float y = displacement.getY();
        float abs = Math.abs(x);
        float f = a;
        if (abs < f && Math.abs(y) < f) {
            return false;
        }
        float rotationDegree = displacement.rotationDegree();
        if (rotationDegree < 45.0f || rotationDegree > 360 - 45.0f) {
            return true;
        }
        float f2 = 180;
        return rotationDegree > f2 - 45.0f && rotationDegree < f2 + 45.0f;
    }
}