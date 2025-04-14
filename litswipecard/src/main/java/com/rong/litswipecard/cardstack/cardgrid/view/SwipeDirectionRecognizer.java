package com.rong.litswipecard.cardstack.cardgrid.view;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.rong.litswipecard.cardstack.cardgrid.model.Point;
import com.tinder.cardstack.model.SwipeDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/SwipeDirectionRecognizer;", "", "()V", "findSwipeDirection", "Lcom/tinder/cardstack/model/SwipeDirection;", "point", "Lcom/tinder/cardstack/cardgrid/model/Point;", "RotationDegreeRange", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class SwipeDirectionRecognizer {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0017\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/SwipeDirectionRecognizer$RotationDegreeRange;", "", "Lkotlin/ranges/ClosedRange;", "", TtmlNode.START, "endInclusive", "(Ljava/lang/String;IFF)V", "getEndInclusive", "()Ljava/lang/Float;", "getStart", "contains", "", AppMeasurementSdk.ConditionalUserProperty.VALUE, "RIGHT", "LEFT", "UP", "DOWN", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private enum RotationDegreeRange implements ClosedRange<Float> {
        RIGHT(290.0f, 70.0f),
        LEFT(110.0f, 250.0f),
        UP(75.0f, 105.0f),
        DOWN(251.0f, 289.0f);

        private final float endInclusive;
        private final float start;

        RotationDegreeRange(float f, float f2) {
            this.start = f;
            this.endInclusive = f2;
        }

        @Override // kotlin.ranges.ClosedRange
        public /* bridge */ /* synthetic */ boolean contains(Float f) {
            return contains(f.floatValue());
        }

        @Override // kotlin.ranges.ClosedRange
        public boolean isEmpty() {
            return ClosedRange.DefaultImpls.isEmpty(this);
        }

        public boolean contains(float value) {
            return getStart().floatValue() <= getEndInclusive().floatValue() ? ClosedRange.DefaultImpls.contains(this, Float.valueOf(value)) : value > getStart().floatValue() || value <= getEndInclusive().floatValue();
        }

        @Override // kotlin.ranges.ClosedRange
        @NotNull
        public Float getEndInclusive() {
            return Float.valueOf(this.endInclusive);
        }

        @Override // kotlin.ranges.ClosedRange
        @NotNull
        public Float getStart() {
            return Float.valueOf(this.start);
        }
    }

    @NotNull
    public final SwipeDirection findSwipeDirection(@NotNull Point point) {
        Intrinsics.checkNotNullParameter(point, "point");
        float rotationDegree = point.rotationDegree();
        return RotationDegreeRange.LEFT.contains(rotationDegree) ? SwipeDirection.LEFT : RotationDegreeRange.RIGHT.contains(rotationDegree) ? SwipeDirection.RIGHT : RotationDegreeRange.UP.contains(rotationDegree) ? SwipeDirection.UP : RotationDegreeRange.DOWN.contains(rotationDegree) ? SwipeDirection.DOWN : SwipeDirection.NONE;
    }
}