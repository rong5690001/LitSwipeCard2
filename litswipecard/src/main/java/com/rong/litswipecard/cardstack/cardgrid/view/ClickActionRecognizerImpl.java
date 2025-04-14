package com.rong.litswipecard.cardstack.cardgrid.view;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.rong.litswipecard.cardstack.cardgrid.swipe.SwipeGestureDetector;
import com.rong.litswipecard.cardstack.cardgrid.swipe.model.Pointer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/ClickActionRecognizerImpl;", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector$ClickActionRecognizer;", "Lcom/tinder/cardstack/cardgrid/view/ClickActionRecognizerImpl$Configuration;", "configuration", "<init>", "(Lcom/tinder/cardstack/cardgrid/view/ClickActionRecognizerImpl$Configuration;)V", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "pointer", "", "isClick", "(Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;)Z", "a", "Lcom/tinder/cardstack/cardgrid/view/ClickActionRecognizerImpl$Configuration;", "Configuration", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ClickActionRecognizerImpl implements SwipeGestureDetector.ClickActionRecognizer {

    /* renamed from: a, reason: from kotlin metadata */
    private final Configuration configuration;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/ClickActionRecognizerImpl$Configuration;", "", "", "minDisplacementForClick", "<init>", "(F)V", "component1", "()F", "copy", "(F)Lcom/tinder/cardstack/cardgrid/view/ClickActionRecognizerImpl$Configuration;", "", InAppPurchaseConstants.METHOD_TO_STRING, "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "a", "F", "getMinDisplacementForClick", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Configuration {

        /* renamed from: a, reason: from kotlin metadata and from toString */
        private final float minDisplacementForClick;

        public Configuration(float f) {
            this.minDisplacementForClick = f;
        }

        public static /* synthetic */ Configuration copy$default(Configuration configuration, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = configuration.minDisplacementForClick;
            }
            return configuration.copy(f);
        }

        /* renamed from: component1, reason: from getter */
        public final float getMinDisplacementForClick() {
            return this.minDisplacementForClick;
        }

        @NotNull
        public final Configuration copy(float minDisplacementForClick) {
            return new Configuration(minDisplacementForClick);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Configuration) && Float.compare(this.minDisplacementForClick, ((Configuration) other).minDisplacementForClick) == 0;
        }

        public final float getMinDisplacementForClick() {
            return this.minDisplacementForClick;
        }

        public int hashCode() {
            return Float.hashCode(this.minDisplacementForClick);
        }

        @NotNull
        public String toString() {
            return "Configuration(minDisplacementForClick=" + this.minDisplacementForClick + ')';
        }
    }

    public ClickActionRecognizerImpl(@NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.configuration = configuration;
    }

    @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.ClickActionRecognizer
    public boolean isClick(@NotNull Pointer pointer) {
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        return pointer.getDisplacement().magnitude() <= this.configuration.getMinDisplacementForClick();
    }
}