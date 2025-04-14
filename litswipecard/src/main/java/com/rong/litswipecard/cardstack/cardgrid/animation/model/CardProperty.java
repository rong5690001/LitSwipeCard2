package com.rong.litswipecard.cardstack.cardgrid.animation.model;

import android.view.View;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.tinder.cardstack.cardgrid.model.Point;
import com.tinder.cardstack.view.CardViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0080\b\u0018\u0000 &2\u00020\u0001:\u0001&B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\rJ8\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\"\u0010 \u001a\u0004\b#\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b$\u0010 \u001a\u0004\b%\u0010\r¨\u0006'"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "", "Lcom/tinder/cardstack/cardgrid/model/Point;", "position", "", "height", "width", "rotation", "<init>", "(Lcom/tinder/cardstack/cardgrid/model/Point;FFF)V", "component1", "()Lcom/tinder/cardstack/cardgrid/model/Point;", "component2", "()F", "component3", "component4", "copy", "(Lcom/tinder/cardstack/cardgrid/model/Point;FFF)Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "a", "Lcom/tinder/cardstack/cardgrid/model/Point;", "getPosition", "b", "F", "getHeight", "c", "getWidth", "d", "getRotation", "Factory", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class CardProperty {

    /* renamed from: Factory, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final CardProperty e = new CardProperty(Point.INSTANCE.getZero(), 0.0f, 0.0f, 0.0f);

    /* renamed from: a, reason: from kotlin metadata and from toString */
    private final Point position;

    /* renamed from: b, reason: from kotlin metadata and from toString */
    private final float height;

    /* renamed from: c, reason: from kotlin metadata and from toString */
    private final float width;

    /* renamed from: d, reason: from kotlin metadata and from toString */
    private final float rotation;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty$Factory;", "", "()V", "default", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getDefault", "()Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "fromCardViewHolder", "cardViewHolder", "Lcom/tinder/cardstack/view/CardViewHolder;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: com.tinder.cardstack.cardgrid.animation.model.CardProperty$Factory, reason: from kotlin metadata */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CardProperty fromCardViewHolder(@NotNull CardViewHolder<?> cardViewHolder) {
            Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
            View view = cardViewHolder.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "cardViewHolder.itemView");
            return new CardProperty(new Point(view.getTranslationX(), view.getTranslationY()), view.getHeight(), view.getWidth(), view.getRotation());
        }

        @NotNull
        public final CardProperty getDefault() {
            return CardProperty.e;
        }

        private Companion() {
        }
    }

    public CardProperty(@NotNull Point position, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(position, "position");
        this.position = position;
        this.height = f;
        this.width = f2;
        this.rotation = f3;
    }

    public static /* synthetic */ CardProperty copy$default(CardProperty cardProperty, Point point, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            point = cardProperty.position;
        }
        if ((i & 2) != 0) {
            f = cardProperty.height;
        }
        if ((i & 4) != 0) {
            f2 = cardProperty.width;
        }
        if ((i & 8) != 0) {
            f3 = cardProperty.rotation;
        }
        return cardProperty.copy(point, f, f2, f3);
    }

    @NotNull
    /* renamed from: component1, reason: from getter */
    public final Point getPosition() {
        return this.position;
    }

    /* renamed from: component2, reason: from getter */
    public final float getHeight() {
        return this.height;
    }

    /* renamed from: component3, reason: from getter */
    public final float getWidth() {
        return this.width;
    }

    /* renamed from: component4, reason: from getter */
    public final float getRotation() {
        return this.rotation;
    }

    @NotNull
    public final CardProperty copy(@NotNull Point position, float height, float width, float rotation) {
        Intrinsics.checkNotNullParameter(position, "position");
        return new CardProperty(position, height, width, rotation);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardProperty)) {
            return false;
        }
        CardProperty cardProperty = (CardProperty) other;
        return Intrinsics.areEqual(this.position, cardProperty.position) && Float.compare(this.height, cardProperty.height) == 0 && Float.compare(this.width, cardProperty.width) == 0 && Float.compare(this.rotation, cardProperty.rotation) == 0;
    }

    public final float getHeight() {
        return this.height;
    }

    @NotNull
    public final Point getPosition() {
        return this.position;
    }

    public final float getRotation() {
        return this.rotation;
    }

    public final float getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (((((this.position.hashCode() * 31) + Float.hashCode(this.height)) * 31) + Float.hashCode(this.width)) * 31) + Float.hashCode(this.rotation);
    }

    @NotNull
    public String toString() {
        return "CardProperty(position=" + this.position + ", height=" + this.height + ", width=" + this.width + ", rotation=" + this.rotation + ')';
    }
}