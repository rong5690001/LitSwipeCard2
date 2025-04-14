package com.rong.litswipecard.cardstack.cardstack.cardtransformer;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lcom/tinder/cardstack/cardstack/cardtransformer/DefaultCardTransforms;", "Lcom/tinder/cardstack/cardstack/cardtransformer/CardTransforms;", "()V", "getDefaultTransform", "Lcom/tinder/cardstack/cardstack/cardtransformer/CardTransform;", "getTransformForView", "cardTransformerIndex", "", "Companion", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class DefaultCardTransforms implements CardTransforms {

    /* renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final CardTransform a = new CardTransform(1.0f, 1.03f, 12.0f, 12.05f);
    private static final CardTransform b = new CardTransform(0.95f, 1.0f, 11.75f, 12.0f);
    private static final CardTransform c = new CardTransform(0.92f, 0.95f, 11.75f, 12.0f);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/tinder/cardstack/cardstack/cardtransformer/DefaultCardTransforms$Companion;", "", "()V", "MAX_ELEVATION", "", "SECOND_CARD_END_SCALE", "SECOND_CARD_END_Z", "SECOND_CARD_START_SCALE", "SECOND_CARD_START_Z", "SECOND_CARD_TRANSFORM", "Lcom/tinder/cardstack/cardstack/cardtransformer/CardTransform;", "getSECOND_CARD_TRANSFORM", "()Lcom/tinder/cardstack/cardstack/cardtransformer/CardTransform;", "THIRD_CARD_END_SCALE", "THIRD_CARD_END_Z", "THIRD_CARD_START_SCALE", "THIRD_CARD_START_Z", "THIRD_CARD_TRANSFORM", "getTHIRD_CARD_TRANSFORM", "TOP_CARD_END_SCALE", "TOP_CARD_END_Z", "TOP_CARD_START_SCALE", "TOP_CARD_START_Z", "TOP_CARD_TRANSFORM", "getTOP_CARD_TRANSFORM", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CardTransform getSECOND_CARD_TRANSFORM() {
            return DefaultCardTransforms.b;
        }

        @NotNull
        public final CardTransform getTHIRD_CARD_TRANSFORM() {
            return DefaultCardTransforms.c;
        }

        @NotNull
        public final CardTransform getTOP_CARD_TRANSFORM() {
            return DefaultCardTransforms.a;
        }

        private Companion() {
        }
    }

    @Override // com.tinder.cardstack.cardstack.cardtransformer.CardTransforms
    @NotNull
    public CardTransform getDefaultTransform() {
        return c;
    }

    @Override // com.tinder.cardstack.cardstack.cardtransformer.CardTransforms
    @NotNull
    public CardTransform getTransformForView(int cardTransformerIndex) {
        if (cardTransformerIndex >= 0) {
            return cardTransformerIndex != 0 ? cardTransformerIndex != 1 ? cardTransformerIndex != 2 ? c : c : b : a;
        }
        throw new IllegalStateException(("Requesting card transform for index:" + cardTransformerIndex).toString());
    }
}