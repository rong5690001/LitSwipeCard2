package com.rong.litswipecard.cardstack.cardgrid.animation.animator;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.tinder.cardstack.cardgrid.animation.model.CardProperty;
import com.tinder.cardstack.cardgrid.animation.model.CardPropertyAnimation;
import com.tinder.cardstack.cardgrid.model.Point;
import com.tinder.cardstack.view.CardViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0007\b\u0000\u0018\u0000 B2\u00020\u0001:\u0004'BCDB\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\r¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r¢\u0006\u0004\b\u0017\u0010\u0011J\u001d\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r¢\u0006\u0004\b\u001a\u0010\u0011J\r\u0010\u001b\u001a\u00020\b¢\u0006\u0004\b\u001b\u0010\fJ\r\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010!\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b!\u0010\"J\u000f\u0010&\u001a\u00020#H\u0000¢\u0006\u0004\b$\u0010%R\u001b\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00028\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\"\u0010/\u001a\u00020\u00068\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010\nR$\u00104\u001a\u0002002\u0006\u00101\u001a\u0002008\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b\t\u00102\"\u0004\b'\u00103R\u0016\u00108\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u001c\u0010=\u001a\n :*\u0004\u0018\u000109098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u001a\u0010A\u001a\b\u0012\u0004\u0012\u00020\u001f0>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@¨\u0006E"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator;", "", "Lcom/tinder/cardstack/view/CardViewHolder;", "cardViewHolder", "<init>", "(Lcom/tinder/cardstack/view/CardViewHolder;)V", "Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$State;", "newState", "", "c", "(Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$State;)V", "b", "()V", "Lcom/tinder/cardstack/cardgrid/model/Point;", "firstTouchPoint", "delta", "move", "(Lcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;)V", "", TypedValues.TransitionType.S_DURATION, "swipe", "(Lcom/tinder/cardstack/cardgrid/model/Point;Lcom/tinder/cardstack/cardgrid/model/Point;J)V", "endPosition", "recover", "(Lcom/tinder/cardstack/cardgrid/model/Point;)V", "fromPosition", "translate", "stop", "Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "getCardAnimation", "()Lcom/tinder/cardstack/cardgrid/animation/model/CardPropertyAnimation;", "Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$OnCompleteListener;", "onCompleteListener", "addOnCompleteListener", "(Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$OnCompleteListener;)V", "Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "updateAndGetCurrentValue$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/animation/model/CardProperty;", "updateAndGetCurrentValue", "a", "Lcom/tinder/cardstack/view/CardViewHolder;", "getCardViewHolder", "()Lcom/tinder/cardstack/view/CardViewHolder;", "Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$State;", "getState$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$State;", "setState$cardstack_release", "state", "Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$a;", AppMeasurementSdk.ConditionalUserProperty.VALUE, "Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$a;", "(Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$a;)V", "animationConfig", "", "d", "Z", "shouldStartNewAnimation", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "e", "Landroid/animation/ValueAnimator;", "valueAnimator", "", "f", "Ljava/util/List;", "onCompleteListeners", "Companion", "OnCompleteListener", "State", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCardPropertyAnimator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CardPropertyAnimator.kt\ncom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,170:1\n1855#2,2:171\n*S KotlinDebug\n*F\n+ 1 CardPropertyAnimator.kt\ncom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator\n*L\n127#1:171,2\n*E\n"})
/* loaded from: classes7.dex */
public final class CardPropertyAnimator {
    private static final long g = 0;

    /* renamed from: a, reason: from kotlin metadata */
    private final CardViewHolder cardViewHolder;

    /* renamed from: b, reason: from kotlin metadata */
    private State state;

    /* renamed from: c, reason: from kotlin metadata */
    private a animationConfig;

    /* renamed from: d, reason: from kotlin metadata */
    private boolean shouldStartNewAnimation;

    /* renamed from: e, reason: from kotlin metadata */
    private final ValueAnimator valueAnimator;

    /* renamed from: f, reason: from kotlin metadata */
    private final List onCompleteListeners;
    private static final long h = 1000;
    private static final long i = 500;
    private static final long j = 250;
    private static final long k = 250;
    private static final AccelerateInterpolator l = new AccelerateInterpolator();
    private static final long m = 180;
    private static final CardPropertyAnimation.Default n = new CardPropertyAnimation.Default();

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$OnCompleteListener;", "", "onComplete", "", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface OnCompleteListener {
        void onComplete();
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/tinder/cardstack/cardgrid/animation/animator/CardPropertyAnimator$State;", "", "(Ljava/lang/String;I)V", DebugCoroutineInfoImplKt.RUNNING, "STOPPED", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum State {
        RUNNING,
        STOPPED
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[State.values().length];
            try {
                iArr[State.STOPPED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[State.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CardPropertyAnimator(@NotNull CardViewHolder<?> cardViewHolder) {
        Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
        this.cardViewHolder = cardViewHolder;
        this.state = State.STOPPED;
        this.animationConfig = new a(null, 0L, null, 7, null);
        this.valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.onCompleteListeners = new ArrayList();
    }

    private final void a(a aVar) {
        if (Intrinsics.areEqual(this.animationConfig, aVar)) {
            return;
        }
        this.shouldStartNewAnimation = true;
        this.animationConfig = aVar;
    }

    private final void b() {
        this.valueAnimator.setInterpolator(this.animationConfig.c());
        this.valueAnimator.setDuration(this.animationConfig.b());
        this.valueAnimator.cancel();
        this.valueAnimator.start();
    }

    private final void c(State newState) {
        if (this.state == newState) {
            return;
        }
        this.state = newState;
        if (WhenMappings.$EnumSwitchMapping$0[newState.ordinal()] != 1) {
            return;
        }
        this.valueAnimator.cancel();
        Iterator it2 = this.onCompleteListeners.iterator();
        while (it2.hasNext()) {
            ((OnCompleteListener) it2.next()).onComplete();
        }
        this.onCompleteListeners.clear();
    }

    public static /* synthetic */ void recover$default(CardPropertyAnimator cardPropertyAnimator, Point point, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            point = Point.INSTANCE.getZero();
        }
        cardPropertyAnimator.recover(point);
    }

    public final void addOnCompleteListener(@NotNull OnCompleteListener onCompleteListener) {
        Intrinsics.checkNotNullParameter(onCompleteListener, "onCompleteListener");
        this.onCompleteListeners.add(onCompleteListener);
    }

    @NotNull
    public final CardPropertyAnimation getCardAnimation() {
        return this.animationConfig.a();
    }

    @NotNull
    public final CardViewHolder<?> getCardViewHolder() {
        return this.cardViewHolder;
    }

    @NotNull
    /* renamed from: getState$cardstack_release, reason: from getter */
    public final State getState() {
        return this.state;
    }

    public final void move(@NotNull Point firstTouchPoint, @NotNull Point delta) {
        Intrinsics.checkNotNullParameter(firstTouchPoint, "firstTouchPoint");
        Intrinsics.checkNotNullParameter(delta, "delta");
        a(new a(new CardPropertyAnimation.Move(CardProperty.INSTANCE.fromCardViewHolder(this.cardViewHolder), firstTouchPoint, delta), g, null, 4, null));
    }

    public final void recover(@NotNull Point endPosition) {
        Intrinsics.checkNotNullParameter(endPosition, "endPosition");
        a(new a(new CardPropertyAnimation.Recover(CardProperty.INSTANCE.fromCardViewHolder(this.cardViewHolder), endPosition, 0.0f), j, null, 4, null));
    }

    public final void setState$cardstack_release(@NotNull State state) {
        Intrinsics.checkNotNullParameter(state, "<set-?>");
        this.state = state;
    }

    public final void stop() {
        c(State.STOPPED);
    }

    public final void swipe(@NotNull Point delta, @NotNull Point firstTouchPoint, long duration) {
        Intrinsics.checkNotNullParameter(delta, "delta");
        Intrinsics.checkNotNullParameter(firstTouchPoint, "firstTouchPoint");
        CardProperty fromCardViewHolder = CardProperty.INSTANCE.fromCardViewHolder(this.cardViewHolder);
        a(new a(new CardPropertyAnimation.Swipe(fromCardViewHolder, fromCardViewHolder.getPosition().plus(delta), firstTouchPoint), Math.max(Math.min(h, duration), i), new LinearInterpolator()));
    }

    public final void translate(@NotNull Point fromPosition, @NotNull Point endPosition) {
        Intrinsics.checkNotNullParameter(fromPosition, "fromPosition");
        Intrinsics.checkNotNullParameter(endPosition, "endPosition");
        a(new a(new CardPropertyAnimation.Translate(CardProperty.copy$default(CardProperty.INSTANCE.fromCardViewHolder(this.cardViewHolder), fromPosition, 0.0f, 0.0f, 0.0f, 14, null), endPosition, 0.0f), k, new LinearInterpolator()));
    }

    @NotNull
    public final CardProperty updateAndGetCurrentValue$cardstack_release() {
        if (this.shouldStartNewAnimation) {
            b();
            this.shouldStartNewAnimation = false;
        }
        if (this.valueAnimator.isStarted() || this.valueAnimator.isRunning()) {
            c(State.RUNNING);
        } else {
            c(State.STOPPED);
        }
        return this.animationConfig.a().getValue(this.valueAnimator.getAnimatedFraction());
    }

    private static final class a {
        private final CardPropertyAnimation a;
        private final long b;
        private final Interpolator c;

        public a(CardPropertyAnimation cardAnimation, long j, Interpolator interpolator) {
            Intrinsics.checkNotNullParameter(cardAnimation, "cardAnimation");
            Intrinsics.checkNotNullParameter(interpolator, "interpolator");
            this.a = cardAnimation;
            this.b = j;
            this.c = interpolator;
        }

        public final CardPropertyAnimation a() {
            return this.a;
        }

        public final long b() {
            return this.b;
        }

        public final Interpolator c() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && this.b == aVar.b && Intrinsics.areEqual(this.c, aVar.c);
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + Long.hashCode(this.b)) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "AnimationConfig(cardAnimation=" + this.a + ", duration=" + this.b + ", interpolator=" + this.c + ')';
        }

        public /* synthetic */ a(CardPropertyAnimation cardPropertyAnimation, long j, Interpolator interpolator, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CardPropertyAnimator.n : cardPropertyAnimation, (i & 2) != 0 ? CardPropertyAnimator.m : j, (i & 4) != 0 ? CardPropertyAnimator.l : interpolator);
        }
    }

    public final void recover(@NotNull Point fromPosition, @NotNull Point endPosition) {
        Intrinsics.checkNotNullParameter(fromPosition, "fromPosition");
        Intrinsics.checkNotNullParameter(endPosition, "endPosition");
        a(new a(new CardPropertyAnimation.Recover(CardProperty.copy$default(CardProperty.INSTANCE.fromCardViewHolder(this.cardViewHolder), fromPosition, 0.0f, 0.0f, 0.0f, 14, null), endPosition, 0.0f), j, null, 4, null));
    }
}