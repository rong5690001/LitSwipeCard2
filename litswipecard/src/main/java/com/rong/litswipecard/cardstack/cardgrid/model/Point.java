package com.rong.litswipecard.cardstack.cardgrid.model;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.tinder.experiences.ui.view.NumPadButtonView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\r\b\u0080\b\u0018\u0000 (2\u00020\u0001:\u0001(B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\bJ\u0018\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\f\u0010\u000bJ\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\r\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0011J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0011J$\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u001cHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010!\u001a\u00020\u00132\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b&\u0010$\u001a\u0004\b'\u0010\u0011¨\u0006)"}, d2 = {"Lcom/tinder/cardstack/cardgrid/model/Point;", "", "", NumPadButtonView.INPUT_CODE_BACKSPACE, "y", "<init>", "(FF)V", "", "(II)V", "other", "plus", "(Lcom/tinder/cardstack/cardgrid/model/Point;)Lcom/tinder/cardstack/cardgrid/model/Point;", "minus", "times", "(F)Lcom/tinder/cardstack/cardgrid/model/Point;", TtmlNode.TAG_DIV, "magnitude", "()F", "rotationDegree", "", "isZero", "()Z", "normalized", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "component1", "component2", "copy", "(FF)Lcom/tinder/cardstack/cardgrid/model/Point;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "hashCode", "()I", "equals", "(Ljava/lang/Object;)Z", "a", "F", "getX", "b", "getY", "Companion", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class Point {

    /* renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final Point c = new Point(0.0f, 0.0f);

    /* renamed from: a, reason: from kotlin metadata and from toString */
    private final float x;

    /* renamed from: b, reason: from kotlin metadata and from toString */
    private final float y;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/tinder/cardstack/cardgrid/model/Point$Companion;", "", "()V", "zero", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getZero", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Point getZero() {
            return Point.c;
        }

        private Companion() {
        }
    }

    public Point(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public static /* synthetic */ Point copy$default(Point point, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = point.x;
        }
        if ((i & 2) != 0) {
            f2 = point.y;
        }
        return point.copy(f, f2);
    }

    /* renamed from: component1, reason: from getter */
    public final float getX() {
        return this.x;
    }

    /* renamed from: component2, reason: from getter */
    public final float getY() {
        return this.y;
    }

    @NotNull
    public final Point copy(float x, float y) {
        return new Point(x, y);
    }

    @NotNull
    public final Point div(float other) {
        return new Point(this.x / other, this.y / other);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Point)) {
            return false;
        }
        Point point = (Point) other;
        return Float.compare(this.x, point.x) == 0 && Float.compare(this.y, point.y) == 0;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public int hashCode() {
        return (Float.hashCode(this.x) * 31) + Float.hashCode(this.y);
    }

    public final boolean isZero() {
        return Intrinsics.areEqual(this, c);
    }

    public final float magnitude() {
        return (float) Math.sqrt(Math.pow(this.x, 2.0d) + Math.pow(this.y, 2.0d));
    }

    @NotNull
    public final Point minus(@NotNull Point other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Point(this.x - other.x, this.y - other.y);
    }

    @NotNull
    public final Point normalized() {
        return div(magnitude());
    }

    @NotNull
    public final Point plus(@NotNull Point other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return new Point(this.x + other.x, this.y + other.y);
    }

    public final float rotationDegree() {
        return (((float) Math.toDegrees(Math.atan2(-this.y, this.x))) + 360.0f) % 360.0f;
    }

    @NotNull
    public final Point times(float other) {
        return new Point(this.x * other, this.y * other);
    }

    @NotNull
    public String toString() {
        return "Point(x=" + this.x + ", y=" + this.y + ')';
    }

    public Point(int i, int i2) {
        this(i, i2);
    }
}