package com.rong.litswipecard.cardstack.cardstack.cardtransformer;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\nJ\u0010\u0010\r\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\nJ8\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001b\u001a\u0004\b\u001e\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001b\u001a\u0004\b \u0010\nR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b!\u0010\u001b\u001a\u0004\b\"\u0010\n¨\u0006#"}, d2 = {"Lcom/tinder/cardstack/cardstack/cardtransformer/CardTransform;", "", "", "startScale", "endScale", "startTranslationZ", "endTranslationZ", "<init>", "(FFFF)V", "component1", "()F", "component2", "component3", "component4", "copy", "(FFFF)Lcom/tinder/cardstack/cardstack/cardtransformer/CardTransform;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "a", "F", "getStartScale", "b", "getEndScale", "c", "getStartTranslationZ", "d", "getEndTranslationZ", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class CardTransform {

    /* renamed from: a, reason: from kotlin metadata and from toString */
    private final float startScale;

    /* renamed from: b, reason: from kotlin metadata and from toString */
    private final float endScale;

    /* renamed from: c, reason: from kotlin metadata and from toString */
    private final float startTranslationZ;

    /* renamed from: d, reason: from kotlin metadata and from toString */
    private final float endTranslationZ;

    public CardTransform(float f, float f2, float f3, float f4) {
        this.startScale = f;
        this.endScale = f2;
        this.startTranslationZ = f3;
        this.endTranslationZ = f4;
    }

    public static /* synthetic */ CardTransform copy$default(CardTransform cardTransform, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = cardTransform.startScale;
        }
        if ((i & 2) != 0) {
            f2 = cardTransform.endScale;
        }
        if ((i & 4) != 0) {
            f3 = cardTransform.startTranslationZ;
        }
        if ((i & 8) != 0) {
            f4 = cardTransform.endTranslationZ;
        }
        return cardTransform.copy(f, f2, f3, f4);
    }

    /* renamed from: component1, reason: from getter */
    public final float getStartScale() {
        return this.startScale;
    }

    /* renamed from: component2, reason: from getter */
    public final float getEndScale() {
        return this.endScale;
    }

    /* renamed from: component3, reason: from getter */
    public final float getStartTranslationZ() {
        return this.startTranslationZ;
    }

    /* renamed from: component4, reason: from getter */
    public final float getEndTranslationZ() {
        return this.endTranslationZ;
    }

    @NotNull
    public final CardTransform copy(float startScale, float endScale, float startTranslationZ, float endTranslationZ) {
        return new CardTransform(startScale, endScale, startTranslationZ, endTranslationZ);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardTransform)) {
            return false;
        }
        CardTransform cardTransform = (CardTransform) other;
        return Float.compare(this.startScale, cardTransform.startScale) == 0 && Float.compare(this.endScale, cardTransform.endScale) == 0 && Float.compare(this.startTranslationZ, cardTransform.startTranslationZ) == 0 && Float.compare(this.endTranslationZ, cardTransform.endTranslationZ) == 0;
    }

    public final float getEndScale() {
        return this.endScale;
    }

    public final float getEndTranslationZ() {
        return this.endTranslationZ;
    }

    public final float getStartScale() {
        return this.startScale;
    }

    public final float getStartTranslationZ() {
        return this.startTranslationZ;
    }

    public int hashCode() {
        return (((((Float.hashCode(this.startScale) * 31) + Float.hashCode(this.endScale)) * 31) + Float.hashCode(this.startTranslationZ)) * 31) + Float.hashCode(this.endTranslationZ);
    }

    @NotNull
    public String toString() {
        return "CardTransform(startScale=" + this.startScale + ", endScale=" + this.endScale + ", startTranslationZ=" + this.startTranslationZ + ", endTranslationZ=" + this.endTranslationZ + ')';
    }
}