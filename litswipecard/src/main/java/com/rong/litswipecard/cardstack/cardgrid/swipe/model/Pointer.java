package com.rong.litswipecard.cardstack.cardgrid.swipe.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.tinder.cardstack.cardgrid.model.Point;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\r\b\u0080\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000bJ\u0010\u0010\u0011\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0012J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0012J:\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b \u0010\u001e\u001a\u0004\b!\u0010\u0012R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010\u0012¨\u0006$"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "", "", "id", "Lcom/tinder/cardstack/cardgrid/model/Point;", "origin", "displacement", "velocity", "<init>", "(ILcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;)V", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "component1", "component2", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "component3", "component4", "copy", "(ILcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;)Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "a", "I", "getId", "b", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getOrigin", "c", "getDisplacement", "d", "getVelocity", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class Pointer {

    /* renamed from: a, reason: from kotlin metadata and from toString */
    private final int id;

    /* renamed from: b, reason: from kotlin metadata and from toString */
    private final Point origin;

    /* renamed from: c, reason: from kotlin metadata and from toString */
    private final Point displacement;

    /* renamed from: d, reason: from kotlin metadata and from toString */
    private final Point velocity;

    public Pointer(int i, @NotNull Point origin, @NotNull Point displacement, @Nullable Point point) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(displacement, "displacement");
        this.id = i;
        this.origin = origin;
        this.displacement = displacement;
        this.velocity = point;
    }

    public static /* synthetic */ Pointer copy$default(Pointer pointer, int i, Point point, Point point2, Point point3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = pointer.id;
        }
        if ((i2 & 2) != 0) {
            point = pointer.origin;
        }
        if ((i2 & 4) != 0) {
            point2 = pointer.displacement;
        }
        if ((i2 & 8) != 0) {
            point3 = pointer.velocity;
        }
        return pointer.copy(i, point, point2, point3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    @NotNull
    /* renamed from: component2, reason: from getter */
    public final Point getOrigin() {
        return this.origin;
    }

    @NotNull
    /* renamed from: component3, reason: from getter */
    public final Point getDisplacement() {
        return this.displacement;
    }

    @Nullable
    /* renamed from: component4, reason: from getter */
    public final Point getVelocity() {
        return this.velocity;
    }

    @NotNull
    public final Pointer copy(int id, @NotNull Point origin, @NotNull Point displacement, @Nullable Point velocity) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(displacement, "displacement");
        return new Pointer(id, origin, displacement, velocity);
    }

    public boolean equals(@Nullable Object other) {
        Pointer pointer = other instanceof Pointer ? (Pointer) other : null;
        return pointer != null && this.id == pointer.id;
    }

    @NotNull
    public final Point getDisplacement() {
        return this.displacement;
    }

    public final int getId() {
        return this.id;
    }

    @NotNull
    public final Point getOrigin() {
        return this.origin;
    }

    @Nullable
    public final Point getVelocity() {
        return this.velocity;
    }

    public int hashCode() {
        return Integer.hashCode(this.id);
    }

    @NotNull
    public String toString() {
        return "Pointer(id=" + this.id + ", origin=" + this.origin + ", displacement=" + this.displacement + ", velocity=" + this.velocity + ')';
    }

    public /* synthetic */ Pointer(int i, Point point, Point point2, Point point3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? Point.INSTANCE.getZero() : point, (i2 & 4) != 0 ? Point.INSTANCE.getZero() : point2, (i2 & 8) != 0 ? null : point3);
    }
}