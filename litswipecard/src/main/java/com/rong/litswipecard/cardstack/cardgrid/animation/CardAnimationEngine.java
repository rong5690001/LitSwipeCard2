package com.rong.litswipecard.cardstack.cardgrid.animation;

import com.tinder.cardstack.cardgrid.animation.animator.CardPropertyAnimator;
import com.tinder.cardstack.cardgrid.animation.model.CardProperty;
import com.tinder.cardstack.view.CardViewHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0002\u00050B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\u000b\u001a\u00020\b2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\u00020\u00042\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u000f\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0013\u0010\u0003J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0017\u0010\u0018R$\u0010 \u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0018\u0010$\u001a\u00060!R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R6\u0010+\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160%j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0016`&8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0,8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u00061"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine;", "", "<init>", "()V", "", "a", "Lcom/tinder/cardstack/view/CardViewHolder;", "cardViewHolder", "Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator;", "animate$cardstack_release", "(Lcom/tinder/cardstack/view/CardViewHolder;)Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator;", "animate", "reset$cardstack_release", "(Lcom/tinder/cardstack/view/CardViewHolder;)V", "reset", "resetAll$cardstack_release", "resetAll", "onUpdate$cardstack_release", "onUpdate", "onPostUpdate$cardstack_release", "onPostUpdate", "animator", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getCardProperty$cardstack_release", "(Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator;)Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "getCardProperty", "Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine$Renderer;", "Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine$Renderer;", "getRenderer$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine$Renderer;", "setRenderer$cardstack_release", "(Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine$Renderer;)V", "renderer", "Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine$a;", "b", "Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine$a;", "animatorPool", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "c", "Ljava/util/HashMap;", "getTempCardProperties$cardstack_release", "()Ljava/util/HashMap;", "tempCardProperties", "", "getActiveAnimators$cardstack_release", "()Ljava/util/List;", "activeAnimators", "Renderer", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCardAnimationEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CardAnimationEngine.kt\ncom/tinder/cardstack/cardgrid/animation/CardAnimationEngine\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,102:1\n1855#2,2:103\n766#2:105\n857#2,2:106\n1855#2,2:108\n*S KotlinDebug\n*F\n+ 1 CardAnimationEngine.kt\ncom/tinder/cardstack/cardgrid/animation/CardAnimationEngine\n*L\n37#1:103,2\n65#1:105\n65#1:106,2\n68#1:108,2\n*E\n"})
/* loaded from: classes7.dex */
public final class CardAnimationEngine {

    /* renamed from: a, reason: from kotlin metadata */
    private Renderer renderer;

    /* renamed from: b, reason: from kotlin metadata */
    private final a animatorPool = new a();

    /* renamed from: c, reason: from kotlin metadata */
    private final HashMap tempCardProperties = new HashMap();

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine$Renderer;", "", "render", "", "cardViewHolder", "Lcom/tinder/cardstack/view/CardViewHolder;", "cardProperty", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "requestUpdate", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Renderer {
        void render(@NotNull CardViewHolder<?> cardViewHolder, @NotNull CardProperty cardProperty);

        void requestUpdate();
    }

    private final class a {
        private final HashMap a = new HashMap();

        public a() {
        }

        public final List a() {
            Collection values = this.a.values();
            Intrinsics.checkNotNullExpressionValue(values, "animatorMap.values");
            return CollectionsKt.toList(values);
        }

        public final CardPropertyAnimator b(CardViewHolder cardViewHolder) {
            Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
            if (this.a.containsKey(cardViewHolder)) {
                Object obj = this.a.get(cardViewHolder);
                Intrinsics.checkNotNull(obj);
                return (CardPropertyAnimator) obj;
            }
            CardPropertyAnimator cardPropertyAnimator = new CardPropertyAnimator(cardViewHolder);
            this.a.put(cardViewHolder, cardPropertyAnimator);
            return cardPropertyAnimator;
        }

        public final void c(CardPropertyAnimator cardPropertyAnimator) {
            Intrinsics.checkNotNullParameter(cardPropertyAnimator, "cardPropertyAnimator");
            this.a.remove(cardPropertyAnimator.getCardViewHolder());
        }

        public final void d(CardViewHolder cardViewHolder) {
            Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
            this.a.remove(cardViewHolder);
        }
    }

    private final void a() {
        List a2 = this.animatorPool.a();
        ArrayList arrayList = new ArrayList();
        for (Object obj : a2) {
            if (((CardPropertyAnimator) obj).getState() == CardPropertyAnimator.State.STOPPED) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.animatorPool.c((CardPropertyAnimator) it2.next());
        }
    }

    @NotNull
    public final CardPropertyAnimator animate$cardstack_release(@NotNull CardViewHolder<?> cardViewHolder) {
        Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
        CardPropertyAnimator b = this.animatorPool.b(cardViewHolder);
        Renderer renderer = this.renderer;
        if (renderer != null) {
            renderer.requestUpdate();
        }
        return b;
    }

    @NotNull
    public final List<CardPropertyAnimator> getActiveAnimators$cardstack_release() {
        return this.animatorPool.a();
    }

    @NotNull
    public final CardProperty getCardProperty$cardstack_release(@NotNull CardPropertyAnimator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        Object obj = this.tempCardProperties.get(animator);
        Intrinsics.checkNotNull(obj);
        return (CardProperty) obj;
    }

    @Nullable
    /* renamed from: getRenderer$cardstack_release, reason: from getter */
    public final Renderer getRenderer() {
        return this.renderer;
    }

    @NotNull
    public final HashMap<CardPropertyAnimator, CardProperty> getTempCardProperties$cardstack_release() {
        return this.tempCardProperties;
    }

    public final void onPostUpdate$cardstack_release() {
        Renderer renderer;
        this.tempCardProperties.clear();
        if (!this.animatorPool.a().isEmpty() && (renderer = this.renderer) != null) {
            renderer.requestUpdate();
        }
        a();
    }

    public final void onUpdate$cardstack_release() {
        Renderer renderer = this.renderer;
        if (renderer == null) {
            return;
        }
        for (CardPropertyAnimator cardPropertyAnimator : this.animatorPool.a()) {
            CardProperty updateAndGetCurrentValue$cardstack_release = cardPropertyAnimator.updateAndGetCurrentValue$cardstack_release();
            this.tempCardProperties.put(cardPropertyAnimator, updateAndGetCurrentValue$cardstack_release);
            renderer.render(cardPropertyAnimator.getCardViewHolder(), updateAndGetCurrentValue$cardstack_release);
        }
    }

    public final void reset$cardstack_release(@NotNull CardViewHolder<?> cardViewHolder) {
        Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
        this.animatorPool.b(cardViewHolder).stop();
        this.animatorPool.d(cardViewHolder);
        Renderer renderer = this.renderer;
        if (renderer != null) {
            renderer.render(cardViewHolder, CardProperty.INSTANCE.getDefault());
        }
    }

    public final void resetAll$cardstack_release() {
        Iterator<T> it2 = getActiveAnimators$cardstack_release().iterator();
        while (it2.hasNext()) {
            reset$cardstack_release(((CardPropertyAnimator) it2.next()).getCardViewHolder());
        }
    }

    public final void setRenderer$cardstack_release(@Nullable Renderer renderer) {
        this.renderer = renderer;
    }
}