package com.rong.litswipecard.cardstack.swipe;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0080\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\bJ$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0013\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0016\u001a\u0004\b\u0019\u0010\b¨\u0006\u001a"}, d2 = {"Lcom/tinder/cardstack/swipe/DragConstraints;", "", "", "canDragUp", "canDragDown", "<init>", "(ZZ)V", "component1", "()Z", "component2", "copy", "(ZZ)Lcom/tinder/cardstack/swipe/DragConstraints;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "a", "Z", "getCanDragUp", "b", "getCanDragDown", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class DragConstraints {

    /* renamed from: a, reason: from kotlin metadata and from toString */
    private final boolean canDragUp;

    /* renamed from: b, reason: from kotlin metadata and from toString */
    private final boolean canDragDown;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public DragConstraints() {
        /*
            r3 = this;
            r0 = 3
            r1 = 0
            r2 = 0
            r3.<init>(r2, r2, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tinder.cardstack.swipe.DragConstraints.<init>():void");
    }

    public static /* synthetic */ DragConstraints copy$default(DragConstraints dragConstraints, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = dragConstraints.canDragUp;
        }
        if ((i & 2) != 0) {
            z2 = dragConstraints.canDragDown;
        }
        return dragConstraints.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getCanDragUp() {
        return this.canDragUp;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getCanDragDown() {
        return this.canDragDown;
    }

    @NotNull
    public final DragConstraints copy(boolean canDragUp, boolean canDragDown) {
        return new DragConstraints(canDragUp, canDragDown);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DragConstraints)) {
            return false;
        }
        DragConstraints dragConstraints = (DragConstraints) other;
        return this.canDragUp == dragConstraints.canDragUp && this.canDragDown == dragConstraints.canDragDown;
    }

    public final boolean getCanDragDown() {
        return this.canDragDown;
    }

    public final boolean getCanDragUp() {
        return this.canDragUp;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.canDragUp;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.canDragDown;
        return i + (z2 ? 1 : z2 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        return "DragConstraints(canDragUp=" + this.canDragUp + ", canDragDown=" + this.canDragDown + ')';
    }

    public DragConstraints(boolean z, boolean z2) {
        this.canDragUp = z;
        this.canDragDown = z2;
    }

    public /* synthetic */ DragConstraints(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2);
    }
}