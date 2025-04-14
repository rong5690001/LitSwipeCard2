package com.rong.litswipecard.cardstack.cardgrid.animation.model;

import androidx.webkit.Profile;
import com.tinder.cardstack.cardgrid.model.Point;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&\u0082\u0001\u0005\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "", "()V", "getValue", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "progress", "", Profile.DEFAULT_PROFILE_NAME, "Move", "Recover", "Swipe", "Translate", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Default;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Move;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Recover;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Swipe;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Translate;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public abstract class CardPropertyAnimation {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Default;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "()V", "getValue", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "progress", "", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Default extends CardPropertyAnimation {
        public Default() {
            super(null);
        }

        @Override // com.tinder.cardstack.cardgrid.animation.model.CardPropertyAnimation
        @NotNull
        public CardProperty getValue(float progress) {
            return CardProperty.INSTANCE.getDefault();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0013\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Move;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "startValue", "Lcom/tinder/cardstack/cardgrid/model/Point;", "firstTouchPoint", "endPosition", "<init>", "(Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;Lcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;)V", "", "height", "a", "(F)F", "progress", "getValue", "(F)Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getRotation", "()F", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getStartValue", "()Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "b", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getFirstTouchPoint", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "c", "getEndPosition", "Companion", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Move extends CardPropertyAnimation {
        private static final float d = 2.0f;
        private static final float e = 0.03f;

        /* renamed from: a, reason: from kotlin metadata */
        private final CardProperty startValue;

        /* renamed from: b, reason: from kotlin metadata */
        private final Point firstTouchPoint;

        /* renamed from: c, reason: from kotlin metadata */
        private final Point endPosition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Move(@NotNull CardProperty startValue, @NotNull Point firstTouchPoint, @NotNull Point endPosition) {
            super(null);
            Intrinsics.checkNotNullParameter(startValue, "startValue");
            Intrinsics.checkNotNullParameter(firstTouchPoint, "firstTouchPoint");
            Intrinsics.checkNotNullParameter(endPosition, "endPosition");
            this.startValue = startValue;
            this.firstTouchPoint = firstTouchPoint;
            this.endPosition = endPosition;
        }

        private final float a(float height) {
            return height / d;
        }

        @NotNull
        public final Point getEndPosition() {
            return this.endPosition;
        }

        @NotNull
        public final Point getFirstTouchPoint() {
            return this.firstTouchPoint;
        }

        public final float getRotation() {
            return (this.firstTouchPoint.getY() < a(this.startValue.getHeight()) ? 1 : -1) * this.endPosition.getX() * e;
        }

        @NotNull
        public final CardProperty getStartValue() {
            return this.startValue;
        }

        @Override // com.tinder.cardstack.cardgrid.animation.model.CardPropertyAnimation
        @NotNull
        public CardProperty getValue(float progress) {
            return CardProperty.copy$default(this.startValue, this.endPosition, 0.0f, 0.0f, getRotation(), 6, null);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0015\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Recover;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "startValue", "Lcom/tinder/cardstack/cardgrid/model/Point;", "endPosition", "", "endRotation", "<init>", "(Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;Lcom/tinder/cardstack/cardgrid/model/Point;F)V", "progress", "a", "(F)Lcom/tinder/cardstack/cardgrid/model/Point;", "b", "(F)F", "getValue", "(F)Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getStartValue", "()Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getEndPosition", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "c", "F", "getEndRotation", "()F", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Recover extends CardPropertyAnimation {

        /* renamed from: a, reason: from kotlin metadata */
        private final CardProperty startValue;

        /* renamed from: b, reason: from kotlin metadata */
        private final Point endPosition;

        /* renamed from: c, reason: from kotlin metadata */
        private final float endRotation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Recover(@NotNull CardProperty startValue, @NotNull Point endPosition, float f) {
            super(null);
            Intrinsics.checkNotNullParameter(startValue, "startValue");
            Intrinsics.checkNotNullParameter(endPosition, "endPosition");
            this.startValue = startValue;
            this.endPosition = endPosition;
            this.endRotation = f;
        }

        private final Point a(float progress) {
            Point position = this.startValue.getPosition();
            return position.plus(this.endPosition.minus(position).times(progress));
        }

        private final float b(float progress) {
            float rotation = this.startValue.getRotation();
            return rotation + ((this.endRotation - rotation) * progress);
        }

        @NotNull
        public final Point getEndPosition() {
            return this.endPosition;
        }

        public final float getEndRotation() {
            return this.endRotation;
        }

        @NotNull
        public final CardProperty getStartValue() {
            return this.startValue;
        }

        @Override // com.tinder.cardstack.cardgrid.animation.model.CardPropertyAnimation
        @NotNull
        public CardProperty getValue(float progress) {
            return CardProperty.copy$default(this.startValue, a(progress), 0.0f, 0.0f, b(progress), 6, null);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0015\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Swipe;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "startValue", "Lcom/tinder/cardstack/cardgrid/model/Point;", "endPosition", "firstTouchPoint", "<init>", "(Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;Lcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;)V", "", "progress", "a", "(F)Lcom/tinder/cardstack/cardgrid/model/Point;", "height", "b", "(F)F", "getValue", "(F)Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "position", "getRotation", "(Lcom/tinder/cardstack/cardgrid/model/Point;)F", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getStartValue", "()Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getEndPosition", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "c", "getFirstTouchPoint", "Companion", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Swipe extends CardPropertyAnimation {
        private static final float d = 2.0f;
        private static final float e = 0.03f;

        /* renamed from: a, reason: from kotlin metadata */
        private final CardProperty startValue;

        /* renamed from: b, reason: from kotlin metadata */
        private final Point endPosition;

        /* renamed from: c, reason: from kotlin metadata */
        private final Point firstTouchPoint;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Swipe(@NotNull CardProperty startValue, @NotNull Point endPosition, @NotNull Point firstTouchPoint) {
            super(null);
            Intrinsics.checkNotNullParameter(startValue, "startValue");
            Intrinsics.checkNotNullParameter(endPosition, "endPosition");
            Intrinsics.checkNotNullParameter(firstTouchPoint, "firstTouchPoint");
            this.startValue = startValue;
            this.endPosition = endPosition;
            this.firstTouchPoint = firstTouchPoint;
        }

        private final Point a(float progress) {
            Point position = this.startValue.getPosition();
            return position.plus(this.endPosition.minus(position).times(progress));
        }

        private final float b(float height) {
            return height / d;
        }

        @NotNull
        public final Point getEndPosition() {
            return this.endPosition;
        }

        @NotNull
        public final Point getFirstTouchPoint() {
            return this.firstTouchPoint;
        }

        public final float getRotation(@NotNull Point position) {
            Intrinsics.checkNotNullParameter(position, "position");
            return (this.firstTouchPoint.getY() < b(this.startValue.getHeight()) ? 1 : -1) * position.getX() * e;
        }

        @NotNull
        public final CardProperty getStartValue() {
            return this.startValue;
        }

        @Override // com.tinder.cardstack.cardgrid.animation.model.CardPropertyAnimation
        @NotNull
        public CardProperty getValue(float progress) {
            return CardProperty.copy$default(this.startValue, a(progress), 0.0f, 0.0f, getRotation(a(progress)), 6, null);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0015\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation$Translate;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "startValue", "Lcom/tinder/cardstack/cardgrid/model/Point;", "endPosition", "", "endRotation", "<init>", "(Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;Lcom/tinder/cardstack/cardgrid/model/Point;F)V", "progress", "a", "(F)Lcom/tinder/cardstack/cardgrid/model/Point;", "b", "(F)F", "getValue", "(F)Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getStartValue", "()Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getEndPosition", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "c", "F", "getEndRotation", "()F", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Translate extends CardPropertyAnimation {

        /* renamed from: a, reason: from kotlin metadata */
        private final CardProperty startValue;

        /* renamed from: b, reason: from kotlin metadata */
        private final Point endPosition;

        /* renamed from: c, reason: from kotlin metadata */
        private final float endRotation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Translate(@NotNull CardProperty startValue, @NotNull Point endPosition, float f) {
            super(null);
            Intrinsics.checkNotNullParameter(startValue, "startValue");
            Intrinsics.checkNotNullParameter(endPosition, "endPosition");
            this.startValue = startValue;
            this.endPosition = endPosition;
            this.endRotation = f;
        }

        private final Point a(float progress) {
            Point position = this.startValue.getPosition();
            return position.plus(this.endPosition.minus(position).times(progress));
        }

        private final float b(float progress) {
            float rotation = this.startValue.getRotation();
            return rotation + ((this.endRotation - rotation) * progress);
        }

        @NotNull
        public final Point getEndPosition() {
            return this.endPosition;
        }

        public final float getEndRotation() {
            return this.endRotation;
        }

        @NotNull
        public final CardProperty getStartValue() {
            return this.startValue;
        }

        @Override // com.tinder.cardstack.cardgrid.animation.model.CardPropertyAnimation
        @NotNull
        public CardProperty getValue(float progress) {
            return CardProperty.copy$default(this.startValue, a(progress), 0.0f, 0.0f, b(progress), 6, null);
        }
    }

    public /* synthetic */ CardPropertyAnimation(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public abstract CardProperty getValue(float progress);

    private CardPropertyAnimation() {
    }
}