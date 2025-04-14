package com.rong.litswipecard.cardstack.cardgrid.view;

import com.rong.litswipecard.cardstack.cardgrid.swipe.SwipeGestureDetector;
import com.rong.litswipecard.cardstack.cardgrid.swipe.model.Pointer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/AllDirectionalTouchInterceptPredicate;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$TouchInterceptPredicate;", "()V", "shouldIntercept", "", "pointer", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AllDirectionalTouchInterceptPredicate implements SwipeGestureDetector.TouchInterceptPredicate {
    @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.TouchInterceptPredicate
    public boolean shouldIntercept(@NotNull Pointer pointer) {
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        return true;
    }
}