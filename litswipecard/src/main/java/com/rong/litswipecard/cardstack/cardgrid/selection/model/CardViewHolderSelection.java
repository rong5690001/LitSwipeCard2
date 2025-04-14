package com.rong.litswipecard.cardstack.cardgrid.selection.model;

import com.tinder.cardstack.cardgrid.model.Point;
import com.tinder.cardstack.cardgrid.swipe.model.Pointer;
import com.tinder.cardstack.view.CardViewHolder;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000e\u0010\fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0086\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\bH\u0086\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u001d\u0010\u001aR\u001b\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010\u001aR\"\u0010(\u001a\u00020\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b$\u0010\"\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010'R$\u0010-\u001a\u0012\u0012\u0004\u0012\u00020\b0)j\b\u0012\u0004\u0012\u00020\b`*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010/\u001a\u0004\u0018\u00010\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u001cR\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020\b008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00106\u001a\u00020\u00148@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b4\u00105¨\u00067"}, d2 = {"Lcom/tinder/cardstack/cardgrid/selection/model/CardViewHolderSelection;", "", "Lcom/tinder/cardstack/view/CardViewHolder;", "cardViewHolder", "Lcom/tinder/cardstack/cardgrid/model/Point;", "firstTouchPoint", "<init>", "(Lcom/tinder/cardstack/view/CardViewHolder;Lcom/tinder/cardstack/cardgrid/model/Point;)V", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "pointer", "", "addPointer$cardstack_release", "(Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;)V", "addPointer", "removePointer$cardstack_release", "removePointer", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "component1", "()Lcom/tinder/cardstack/view/CardViewHolder;", "component2", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "component3", "()Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "component4", "a", "Lcom/tinder/cardstack/view/CardViewHolder;", "getCardViewHolder", "b", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getFirstTouchPoint", "c", "getPrimaryPointerOffset$cardstack_release", "setPrimaryPointerOffset$cardstack_release", "(Lcom/tinder/cardstack/cardgrid/model/Point;)V", "primaryPointerOffset", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "d", "Ljava/util/LinkedHashSet;", "pointerSet", "getPrimaryPointer$cardstack_release", "primaryPointer", "", "getPointers$cardstack_release", "()Ljava/util/Set;", "pointers", "isEmpty$cardstack_release", "()Z", "isEmpty", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class CardViewHolderSelection {

    /* renamed from: a, reason: from kotlin metadata */
    private final CardViewHolder cardViewHolder;

    /* renamed from: b, reason: from kotlin metadata */
    private final Point firstTouchPoint;

    /* renamed from: c, reason: from kotlin metadata */
    private Point primaryPointerOffset;

    /* renamed from: d, reason: from kotlin metadata */
    private final LinkedHashSet pointerSet;

    public CardViewHolderSelection(@NotNull CardViewHolder<?> cardViewHolder, @NotNull Point firstTouchPoint) {
        Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
        Intrinsics.checkNotNullParameter(firstTouchPoint, "firstTouchPoint");
        this.cardViewHolder = cardViewHolder;
        this.firstTouchPoint = firstTouchPoint;
        this.primaryPointerOffset = Point.INSTANCE.getZero();
        this.pointerSet = new LinkedHashSet();
    }

    public final void addPointer$cardstack_release(@NotNull Pointer pointer) {
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        this.pointerSet.add(pointer);
    }

    @NotNull
    public final CardViewHolder<?> component1() {
        return this.cardViewHolder;
    }

    @NotNull
    /* renamed from: component2, reason: from getter */
    public final Point getFirstTouchPoint() {
        return this.firstTouchPoint;
    }

    @Nullable
    public final Pointer component3() {
        return getPrimaryPointer$cardstack_release();
    }

    @NotNull
    /* renamed from: component4, reason: from getter */
    public final Point getPrimaryPointerOffset() {
        return this.primaryPointerOffset;
    }

    public boolean equals(@Nullable Object other) {
        return Intrinsics.areEqual(this.cardViewHolder, other);
    }

    @NotNull
    public final CardViewHolder<?> getCardViewHolder() {
        return this.cardViewHolder;
    }

    @NotNull
    public final Point getFirstTouchPoint() {
        return this.firstTouchPoint;
    }

    @NotNull
    public final Set<Pointer> getPointers$cardstack_release() {
        return CollectionsKt.toSet(this.pointerSet);
    }

    @Nullable
    public final Pointer getPrimaryPointer$cardstack_release() {
        return (Pointer) CollectionsKt.firstOrNull(getPointers$cardstack_release());
    }

    @NotNull
    public final Point getPrimaryPointerOffset$cardstack_release() {
        return this.primaryPointerOffset;
    }

    public int hashCode() {
        return this.cardViewHolder.hashCode();
    }

    public final boolean isEmpty$cardstack_release() {
        return getPrimaryPointer$cardstack_release() == null;
    }

    public final void removePointer$cardstack_release(@NotNull Pointer pointer) {
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        boolean areEqual = Intrinsics.areEqual(getPrimaryPointer$cardstack_release(), pointer);
        this.pointerSet.remove(pointer);
        if (areEqual) {
            if (getPrimaryPointer$cardstack_release() == null) {
                this.primaryPointerOffset = Point.INSTANCE.getZero();
                return;
            }
            Pointer primaryPointer$cardstack_release = getPrimaryPointer$cardstack_release();
            if (primaryPointer$cardstack_release == null) {
                return;
            }
            this.primaryPointerOffset = this.primaryPointerOffset.plus(pointer.getDisplacement().minus(primaryPointer$cardstack_release.getDisplacement()));
        }
    }

    public final void setPrimaryPointerOffset$cardstack_release(@NotNull Point point) {
        Intrinsics.checkNotNullParameter(point, "<set-?>");
        this.primaryPointerOffset = point;
    }
}