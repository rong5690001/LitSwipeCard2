package com.rong.litswipecard.cardstack.cardgrid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.R;
import com.rong.litswipecard.cardstack.animation.SwipeAnimation;
import com.rong.litswipecard.cardstack.cardgrid.animation.CardAnimationEngine;
import com.rong.litswipecard.cardstack.cardgrid.animation.animator.CardPropertyAnimator;
import com.rong.litswipecard.cardstack.cardgrid.animation.model.CardProperty;
import com.rong.litswipecard.cardstack.cardgrid.animation.model.CardPropertyAnimation;
import com.rong.litswipecard.cardstack.cardgrid.model.Point;
import com.rong.litswipecard.cardstack.cardgrid.selection.CardViewHolderSelector;
import com.rong.litswipecard.cardstack.cardgrid.selection.model.CardViewHolderSelection;
import com.rong.litswipecard.cardstack.cardgrid.swipe.SwipeGestureDetector;
import com.rong.litswipecard.cardstack.cardgrid.swipe.model.Pointer;
import com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout;
import com.rong.litswipecard.cardstack.cardgrid.view.ClickActionRecognizerImpl;
import com.rong.litswipecard.cardstack.cardgrid.view.SwipeActionRecognizerImpl;
import com.tinder.cardstack.model.Card;
import com.tinder.cardstack.model.SwipeDirection;
import com.tinder.cardstack.utils.ViewHelper;
import com.tinder.cardstack.view.CardCollectionLayout;
import com.tinder.cardstack.view.CardDecorationListener;
import com.tinder.cardstack.view.CardViewHolder;
import com.tinder.cardstack.view.OnPreSwipeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\b&\u0018\u0000 H2\u00020\u0001:\u000bIJKHLMNOPQRB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u001b\u0010\f\u001a\u00020\u000b2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\tH\u0002¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0011\u001a\u00020\u00102\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001d\u0010\u001cJ\u0015\u0010 \u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b \u0010!J\u0019\u0010$\u001a\u00020\u000b2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\"¢\u0006\u0004\b$\u0010%R\u0014\u0010)\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010-\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u00101\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00105\u001a\u0002028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0018\u00109\u001a\u000606R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0018\u0010=\u001a\u00060:R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010A\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0018\u0010D\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010G\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010F¨\u0006S"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/BaseCardCollectionLayout;", "Lcom/tinder/cardstack/view/CardCollectionLayout;", "Landroid/content/Context;", "ctx", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Lcom/tinder/cardstack/view/CardViewHolder;", "cardViewHolder", "", "i1", "(Lcom/tinder/cardstack/view/CardViewHolder;)V", "Lcom/tinder/cardstack/model/SwipeDirection;", "swipeDirection", "", "j1", "(Lcom/tinder/cardstack/view/CardViewHolder;Lcom/tinder/cardstack/model/SwipeDirection;)Z", "Lcom/tinder/cardstack/view/OnPreSwipeListener;", "preSwipeListener", "setOnPreSwipeListener", "(Lcom/tinder/cardstack/view/OnPreSwipeListener;)V", "Landroid/view/View;", "view", "Lcom/tinder/cardstack/view/CardDecorationListener;", "cardDecorationListener", "addCardDecorationListener", "(Landroid/view/View;Lcom/tinder/cardstack/view/CardDecorationListener;)V", "removeCardDecorationListener", "Lcom/tinder/cardstack/cardgrid/view/DragSessionListener;", "dragSessionListener", "setDragSessionListener", "(Lcom/tinder/cardstack/cardgrid/view/DragSessionListener;)V", "Lcom/tinder/cardstack/model/Card;", "card", "revertAnimatingCard", "(Lcom/tinder/cardstack/model/Card;)V", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector;", "N1", "Lcom/tinder/cardstack/cardgrid/swipe/SwipeGestureDetector;", "swipeGestureDetector", "Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine;", "O1", "Lcom/tinder/cardstack/cardgrid/animation/CardAnimationEngine;", "cardAnimationEngine", "Lcom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector;", "P1", "Lcom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector;", "cardViewHolderSelector", "Lcom/tinder/cardstack/cardgrid/view/SwipeDirectionRecognizer;", "Q1", "Lcom/tinder/cardstack/cardgrid/view/SwipeDirectionRecognizer;", "swipeDirectionRecognizer", "Lcom/tinder/cardstack/cardgrid/view/BaseCardCollectionLayout$c;", "R1", "Lcom/tinder/cardstack/cardgrid/view/BaseCardCollectionLayout$c;", "itemDecoration", "Lcom/tinder/cardstack/cardgrid/view/BaseCardCollectionLayout$ChildDrawingOrderCallbackTracker;", "S1", "Lcom/tinder/cardstack/cardgrid/view/BaseCardCollectionLayout$ChildDrawingOrderCallbackTracker;", "childDrawingOrderCallbackTracker", "Lcom/tinder/cardstack/cardgrid/view/CardDragSessionDetector;", "T1", "Lcom/tinder/cardstack/cardgrid/view/CardDragSessionDetector;", "cardDragSessionDetector", "U1", "Lcom/tinder/cardstack/view/OnPreSwipeListener;", "onPreSwipeListener", "V1", "Z", "isScrolling", "Companion", "a", "b", "ChildDrawingOrderCallbackTracker", "ItemAnimator", "c", "d", "e", "f", "g", "h", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public abstract class BaseCardCollectionLayout extends CardCollectionLayout {
    private static final float W1 = 0.25f;
    private static final float X1 = 8.0f;
    private static final float Y1 = Math.max(ViewHelper.getScreenWidth(), ViewHelper.getScreenHeight()) * 1.2f;

    /* renamed from: N1, reason: from kotlin metadata */
    private final SwipeGestureDetector swipeGestureDetector;

    /* renamed from: O1, reason: from kotlin metadata */
    private final CardAnimationEngine cardAnimationEngine;

    /* renamed from: P1, reason: from kotlin metadata */
    private final CardViewHolderSelector cardViewHolderSelector;

    /* renamed from: Q1, reason: from kotlin metadata */
    private final SwipeDirectionRecognizer swipeDirectionRecognizer;

    /* renamed from: R1, reason: from kotlin metadata */
    private final c itemDecoration;

    /* renamed from: S1, reason: from kotlin metadata */
    private final ChildDrawingOrderCallbackTracker childDrawingOrderCallbackTracker;

    /* renamed from: T1, reason: from kotlin metadata */
    private final CardDragSessionDetector cardDragSessionDetector;

    /* renamed from: U1, reason: from kotlin metadata */
    private OnPreSwipeListener onPreSwipeListener;

    /* renamed from: V1, reason: from kotlin metadata */
    private boolean isScrolling;

    /* JADX INFO: Access modifiers changed from: private */
    final class ChildDrawingOrderCallbackTracker implements RecyclerView.ChildDrawingOrderCallback {
        private final List a = new ArrayList();
        private final List b = new ArrayList();

        public ChildDrawingOrderCallbackTracker() {
        }

        private final boolean b(RecyclerView.ViewHolder viewHolder) {
            List<CardPropertyAnimator> activeAnimators$cardstack_release = BaseCardCollectionLayout.this.cardAnimationEngine.getActiveAnimators$cardstack_release();
            if ((activeAnimators$cardstack_release instanceof Collection) && activeAnimators$cardstack_release.isEmpty()) {
                return false;
            }
            Iterator<T> it2 = activeAnimators$cardstack_release.iterator();
            while (it2.hasNext()) {
                if (Intrinsics.areEqual(((CardPropertyAnimator) it2.next()).getCardViewHolder(), viewHolder)) {
                    return true;
                }
            }
            return false;
        }

        private final boolean c(RecyclerView.ViewHolder viewHolder) {
            List list = this.b;
            if ((list instanceof Collection) && list.isEmpty()) {
                return false;
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                if (Intrinsics.areEqual((RecyclerView.ViewHolder) it2.next(), viewHolder)) {
                    return true;
                }
            }
            return false;
        }

        private final void f(int i) {
            this.a.clear();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = i - 1; -1 < i2; i2--) {
                RecyclerView.ViewHolder viewHolder = BaseCardCollectionLayout.this.getChildViewHolder(BaseCardCollectionLayout.this.getChildAt(i2));
                Intrinsics.checkNotNullExpressionValue(viewHolder, "viewHolder");
                if (c(viewHolder)) {
                    arrayList.add(TuplesKt.to(viewHolder, Integer.valueOf(i2)));
                } else if (b(viewHolder)) {
                    arrayList2.add(TuplesKt.to(viewHolder, Integer.valueOf(i2)));
                } else {
                    this.a.add(0, Integer.valueOf(i2));
                }
            }
            final BaseCardCollectionLayout baseCardCollectionLayout = BaseCardCollectionLayout.this;
            if (arrayList2.size() > 1) {
                CollectionsKt.sortWith(arrayList2, new Comparator() { // from class: com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout$ChildDrawingOrderCallbackTracker$updateDrawingOrder$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        CardAnimationEngine cardAnimationEngine = BaseCardCollectionLayout.this.cardAnimationEngine;
                        Object first = ((Pair) t).getFirst();
                        Intrinsics.checkNotNull(first, "null cannot be cast to non-null type com.tinder.cardstack.view.CardViewHolder<*>");
                        CardPropertyAnimation cardAnimation = cardAnimationEngine.animate$cardstack_release((CardViewHolder) first).getCardAnimation();
                        int i3 = 0;
                        Integer valueOf = Integer.valueOf(cardAnimation instanceof CardPropertyAnimation.Swipe ? 3 : cardAnimation instanceof CardPropertyAnimation.Recover ? 2 : 0);
                        CardAnimationEngine cardAnimationEngine2 = BaseCardCollectionLayout.this.cardAnimationEngine;
                        Object first2 = ((Pair) t2).getFirst();
                        Intrinsics.checkNotNull(first2, "null cannot be cast to non-null type com.tinder.cardstack.view.CardViewHolder<*>");
                        CardPropertyAnimation cardAnimation2 = cardAnimationEngine2.animate$cardstack_release((CardViewHolder) first2).getCardAnimation();
                        if (cardAnimation2 instanceof CardPropertyAnimation.Swipe) {
                            i3 = 3;
                        } else if (cardAnimation2 instanceof CardPropertyAnimation.Recover) {
                            i3 = 2;
                        }
                        return ComparisonsKt.compareValues(valueOf, Integer.valueOf(i3));
                    }
                });
            }
            if (arrayList.size() > 1) {
                CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout$ChildDrawingOrderCallbackTracker$updateDrawingOrder$$inlined$sortBy$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        List list;
                        List list2;
                        list = BaseCardCollectionLayout.ChildDrawingOrderCallbackTracker.this.b;
                        Integer valueOf = Integer.valueOf(-list.indexOf(((Pair) t).getFirst()));
                        list2 = BaseCardCollectionLayout.ChildDrawingOrderCallbackTracker.this.b;
                        return ComparisonsKt.compareValues(valueOf, Integer.valueOf(-list2.indexOf(((Pair) t2).getFirst())));
                    }
                });
            }
            for (Pair pair : CollectionsKt.plus((Collection) arrayList2, (Iterable) arrayList)) {
                List list = this.a;
                list.add(list.size(), pair.getSecond());
            }
        }

        public final void d() {
            if (BaseCardCollectionLayout.this.cardAnimationEngine.getActiveAnimators$cardstack_release().isEmpty() && BaseCardCollectionLayout.this.cardViewHolderSelector.getSelectedCardViewHolders().isEmpty()) {
                this.b.clear();
            }
        }

        public final void e(CardViewHolder cardViewHolder) {
            Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
            if (this.b.contains(cardViewHolder)) {
                return;
            }
            this.b.add(cardViewHolder);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ChildDrawingOrderCallback
        public int onGetChildDrawingOrder(int i, int i2) {
            if (i2 == 0) {
                f(i);
            }
            return ((Number) this.a.get(i2)).intValue();
        }
    }

    private final class ItemAnimator extends SimpleCardItemAnimator {
        private final Rect a = new Rect();

        public ItemAnimator() {
        }

        private final Point a(RecyclerView.ViewHolder viewHolder, Point point) {
            this.a.left = (int) point.getX();
            this.a.top = (int) point.getY();
            View view = viewHolder.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
            BaseCardCollectionLayout.this.offsetRectIntoDescendantCoords(view, this.a);
            Rect rect = this.a;
            return new Point(rect.left, rect.top);
        }

        @Override // com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator
        public void animateAdd(CardViewHolder holder, final Function0 onComplete) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(onComplete, "onComplete");
            CardPropertyAnimator animate$cardstack_release = BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(holder);
            SwipeAnimation appearingAnimation = holder.getAppearingAnimation();
            if (appearingAnimation == null) {
                onComplete.invoke();
                return;
            }
            if (animate$cardstack_release.getState() == CardPropertyAnimator.State.RUNNING) {
                if (animate$cardstack_release.getCardAnimation() instanceof CardPropertyAnimation.Recover) {
                    animate$cardstack_release.addOnCompleteListener(new CardPropertyAnimator.OnCompleteListener() { // from class: com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout$ItemAnimator$animateAdd$1
                        @Override // com.tinder.cardstack.cardgrid.animation.animator.CardPropertyAnimator.OnCompleteListener
                        public void onComplete() {
                            Function0.this.invoke();
                        }
                    });
                    return;
                }
                animate$cardstack_release.stop();
            }
            animate$cardstack_release.recover(new Point(appearingAnimation.startX(), appearingAnimation.startY()), Point.INSTANCE.getZero());
            animate$cardstack_release.addOnCompleteListener(new CardPropertyAnimator.OnCompleteListener() { // from class: com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout$ItemAnimator$animateAdd$2
                @Override // com.tinder.cardstack.cardgrid.animation.animator.CardPropertyAnimator.OnCompleteListener
                public void onComplete() {
                    Function0.this.invoke();
                }
            });
        }

        @Override // com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator
        public void animateChange(CardViewHolder oldHolder, CardViewHolder newHolder, Point fromPosition, Point toPosition, Function0 onComplete) {
            Intrinsics.checkNotNullParameter(oldHolder, "oldHolder");
            Intrinsics.checkNotNullParameter(newHolder, "newHolder");
            Intrinsics.checkNotNullParameter(fromPosition, "fromPosition");
            Intrinsics.checkNotNullParameter(toPosition, "toPosition");
            Intrinsics.checkNotNullParameter(onComplete, "onComplete");
            onComplete.invoke();
        }

        @Override // com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator
        public void animateMove(CardViewHolder holder, Point fromPosition, Point toPosition, final Function0 onComplete) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(fromPosition, "fromPosition");
            Intrinsics.checkNotNullParameter(toPosition, "toPosition");
            Intrinsics.checkNotNullParameter(onComplete, "onComplete");
            Point a = a(holder, fromPosition);
            Point a2 = a(holder, toPosition);
            CardPropertyAnimator animate$cardstack_release = BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(holder);
            animate$cardstack_release.stop();
            CardProperty fromCardViewHolder = CardProperty.INSTANCE.fromCardViewHolder(holder);
            if (animate$cardstack_release.getCardAnimation() instanceof CardPropertyAnimation.Recover) {
                animate$cardstack_release.recover(a.plus(fromCardViewHolder.getPosition()), a2);
            } else {
                animate$cardstack_release.translate(a.plus(fromCardViewHolder.getPosition()), a2);
            }
            animate$cardstack_release.addOnCompleteListener(new CardPropertyAnimator.OnCompleteListener() { // from class: com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout$ItemAnimator$animateMove$1
                @Override // com.tinder.cardstack.cardgrid.animation.animator.CardPropertyAnimator.OnCompleteListener
                public void onComplete() {
                    Function0.this.invoke();
                }
            });
        }

        @Override // com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator
        public void animateRemove(CardViewHolder holder, final Function0 onComplete) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            Intrinsics.checkNotNullParameter(onComplete, "onComplete");
            CardPropertyAnimator animate$cardstack_release = BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(holder);
            SwipeAnimation disappearingAnimation = holder.getDisappearingAnimation();
            if (disappearingAnimation == null) {
                onComplete.invoke();
                return;
            }
            if (animate$cardstack_release.getState() == CardPropertyAnimator.State.RUNNING) {
                if (animate$cardstack_release.getCardAnimation() instanceof CardPropertyAnimation.Swipe) {
                    animate$cardstack_release.addOnCompleteListener(new CardPropertyAnimator.OnCompleteListener() { // from class: com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout$ItemAnimator$animateRemove$1
                        @Override // com.tinder.cardstack.cardgrid.animation.animator.CardPropertyAnimator.OnCompleteListener
                        public void onComplete() {
                            Function0.this.invoke();
                        }
                    });
                    return;
                }
                animate$cardstack_release.stop();
            }
            CardProperty fromCardViewHolder = CardProperty.INSTANCE.fromCardViewHolder(holder);
            animate$cardstack_release.swipe(new Point(disappearingAnimation.endX(), disappearingAnimation.endY()), new Point(fromCardViewHolder.getWidth() / 2.0f, fromCardViewHolder.getHeight() * 0.4f), disappearingAnimation.durationMilli());
            animate$cardstack_release.addOnCompleteListener(new CardPropertyAnimator.OnCompleteListener() { // from class: com.tinder.cardstack.cardgrid.view.BaseCardCollectionLayout$ItemAnimator$animateRemove$2
                @Override // com.tinder.cardstack.cardgrid.animation.animator.CardPropertyAnimator.OnCompleteListener
                public void onComplete() {
                    Function0.this.invoke();
                }
            });
        }

        @Override // com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator
        public void endAnimation(CardViewHolder holder) {
            Intrinsics.checkNotNullParameter(holder, "holder");
            BaseCardCollectionLayout.this.cardAnimationEngine.reset$cardstack_release(holder);
        }

        @Override // com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void endAnimations() {
            BaseCardCollectionLayout.this.cardAnimationEngine.resetAll$cardstack_release();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public boolean isRunning() {
            return !BaseCardCollectionLayout.this.cardAnimationEngine.getActiveAnimators$cardstack_release().isEmpty();
        }
    }

    private final class a implements CardAnimationEngine.Renderer {
        public a() {
        }

        @Override // com.tinder.cardstack.cardgrid.animation.CardAnimationEngine.Renderer
        public void render(CardViewHolder cardViewHolder, CardProperty cardProperty) {
            Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
            Intrinsics.checkNotNullParameter(cardProperty, "cardProperty");
            Point position = cardProperty.getPosition();
            float rotation = cardProperty.getRotation();
            float x = position.getX();
            float y = position.getY();
            View view = cardViewHolder.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "cardViewHolder.itemView");
            ViewCompat.setTranslationX(view, x);
            ViewCompat.setTranslationY(view, y);
            ViewCompat.setRotation(view, rotation);
        }

        @Override // com.tinder.cardstack.cardgrid.animation.CardAnimationEngine.Renderer
        public void requestUpdate() {
            BaseCardCollectionLayout.this.invalidate();
        }
    }

    private final class b implements CardViewHolderSelector.CardViewHolderFinder {
        public b() {
        }

        public final boolean a(View child, float f, float f2, float f3, float f4) {
            Intrinsics.checkNotNullParameter(child, "child");
            return f >= f3 && f <= f3 + ((float) child.getWidth()) && f2 >= f4 && f2 <= f4 + ((float) child.getHeight());
        }

        @Override // com.tinder.cardstack.cardgrid.selection.CardViewHolderSelector.CardViewHolderFinder
        public CardViewHolder findCardViewHolder(Point position) {
            Intrinsics.checkNotNullParameter(position, "position");
            float x = position.getX();
            float y = position.getY();
            int childCount = BaseCardCollectionLayout.this.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    return null;
                }
                View view = BaseCardCollectionLayout.this.getChildAt(i);
                RecyclerView.ViewHolder findContainingViewHolder = BaseCardCollectionLayout.this.findContainingViewHolder(view);
                CardViewHolder<?> cardViewHolder = findContainingViewHolder instanceof CardViewHolder ? (CardViewHolder) findContainingViewHolder : null;
                if (cardViewHolder != null) {
                    Intrinsics.checkNotNullExpressionValue(view, "view");
                    if (a(view, x, y, view.getX(), view.getY())) {
                        CardPropertyAnimator animate$cardstack_release = BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(cardViewHolder);
                        if (!(animate$cardstack_release.getCardAnimation() instanceof CardPropertyAnimation.Swipe) && !(animate$cardstack_release.getCardAnimation() instanceof CardPropertyAnimation.Recover) && !(animate$cardstack_release.getCardAnimation() instanceof CardPropertyAnimation.Translate)) {
                            return cardViewHolder;
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
        }
    }

    private final class c extends RecyclerView.ItemDecoration {
        private final HashMap a0 = new HashMap();

        public c() {
        }

        private final List b(View view) {
            if (!this.a0.containsKey(view)) {
                return CollectionsKt.emptyList();
            }
            Object obj = this.a0.get(view);
            Intrinsics.checkNotNull(obj);
            return CollectionsKt.toList((Iterable) obj);
        }

        private final void c(CardDecorationListener cardDecorationListener, Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
            int save = canvas.save();
            cardDecorationListener.onDecorationDrawOver(canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            canvas.restoreToCount(save);
        }

        private final void d(CardDecorationListener cardDecorationListener, Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
            int save = canvas.save();
            cardDecorationListener.onDecorationDraw(canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            canvas.restoreToCount(save);
        }

        public final void a(View view, CardDecorationListener cardDecorationListener) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(cardDecorationListener, "cardDecorationListener");
            if (!this.a0.containsKey(view)) {
                this.a0.put(view, new ArrayList());
            }
            Object obj = this.a0.get(view);
            Intrinsics.checkNotNull(obj);
            ((List) obj).add(cardDecorationListener);
        }

        public final void e(View view, CardDecorationListener cardDecorationListener) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(cardDecorationListener, "cardDecorationListener");
            if (this.a0.containsKey(view)) {
                List list = (List) this.a0.get(view);
                Intrinsics.checkNotNull(list);
                list.remove(cardDecorationListener);
                if (list.isEmpty()) {
                    this.a0.remove(view);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            BaseCardCollectionLayout.this.cardAnimationEngine.onUpdate$cardstack_release();
            super.onDraw(canvas, parent, state);
            for (CardPropertyAnimator cardPropertyAnimator : BaseCardCollectionLayout.this.cardAnimationEngine.getActiveAnimators$cardstack_release()) {
                if (!(cardPropertyAnimator.getCardAnimation() instanceof CardPropertyAnimation.Translate)) {
                    View view = cardPropertyAnimator.getCardViewHolder().itemView;
                    Intrinsics.checkNotNullExpressionValue(view, "animator.cardViewHolder.itemView");
                    CardProperty cardProperty$cardstack_release = BaseCardCollectionLayout.this.cardAnimationEngine.getCardProperty$cardstack_release(cardPropertyAnimator);
                    Point position = cardProperty$cardstack_release.getPosition();
                    float rotation = cardProperty$cardstack_release.getRotation();
                    float x = position.getX();
                    float y = position.getY();
                    SwipeDirection findSwipeDirection = BaseCardCollectionLayout.this.swipeDirectionRecognizer.findSwipeDirection(position);
                    Iterator it2 = b(view).iterator();
                    while (it2.hasNext()) {
                        d((CardDecorationListener) it2.next(), canvas, view, parent, x, y, rotation, findSwipeDirection, false, false);
                    }
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            super.onDrawOver(canvas, parent, state);
            for (CardPropertyAnimator cardPropertyAnimator : BaseCardCollectionLayout.this.cardAnimationEngine.getActiveAnimators$cardstack_release()) {
                if (!(cardPropertyAnimator.getCardAnimation() instanceof CardPropertyAnimation.Translate)) {
                    View view = cardPropertyAnimator.getCardViewHolder().itemView;
                    Intrinsics.checkNotNullExpressionValue(view, "animator.cardViewHolder.itemView");
                    CardProperty cardProperty$cardstack_release = BaseCardCollectionLayout.this.cardAnimationEngine.getCardProperty$cardstack_release(cardPropertyAnimator);
                    Point position = cardProperty$cardstack_release.getPosition();
                    float rotation = cardProperty$cardstack_release.getRotation();
                    float x = position.getX();
                    float y = position.getY();
                    SwipeDirection findSwipeDirection = BaseCardCollectionLayout.this.swipeDirectionRecognizer.findSwipeDirection(position);
                    Iterator it2 = b(view).iterator();
                    while (it2.hasNext()) {
                        c((CardDecorationListener) it2.next(), canvas, view, parent, x, y, rotation, findSwipeDirection, false, false);
                    }
                }
            }
            BaseCardCollectionLayout.this.cardAnimationEngine.onPostUpdate$cardstack_release();
            BaseCardCollectionLayout.this.childDrawingOrderCallbackTracker.d();
        }
    }

    private final class d implements RecyclerView.OnChildAttachStateChangeListener {
        public d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewAttachedToWindow(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            RecyclerView.ViewHolder findContainingViewHolder = BaseCardCollectionLayout.this.findContainingViewHolder(view);
            CardViewHolder<?> cardViewHolder = findContainingViewHolder instanceof CardViewHolder ? (CardViewHolder) findContainingViewHolder : null;
            if (cardViewHolder == null) {
                return;
            }
            cardViewHolder.onAttachedToCardCollectionLayout(BaseCardCollectionLayout.this);
            BaseCardCollectionLayout.this.cardAnimationEngine.reset$cardstack_release(cardViewHolder);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewDetachedFromWindow(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            RecyclerView.ViewHolder findContainingViewHolder = BaseCardCollectionLayout.this.findContainingViewHolder(view);
            CardViewHolder<?> cardViewHolder = findContainingViewHolder instanceof CardViewHolder ? (CardViewHolder) findContainingViewHolder : null;
            if (cardViewHolder == null) {
                return;
            }
            cardViewHolder.onDetachedFromCardCollectionLayout(BaseCardCollectionLayout.this);
            BaseCardCollectionLayout.this.cardAnimationEngine.reset$cardstack_release(cardViewHolder);
        }
    }

    private final class e implements SwipeGestureDetector.OnGestureListener {
        private final List a = new ArrayList();

        public e() {
        }

        private final void a() {
            if (BaseCardCollectionLayout.this.cardViewHolderSelector.getSelectedCardViewHolders().isEmpty()) {
                List<h> list = this.a;
                BaseCardCollectionLayout baseCardCollectionLayout = BaseCardCollectionLayout.this;
                for (h hVar : list) {
                    CardViewHolder<?> a = hVar.a();
                    if (!baseCardCollectionLayout.j1(a, hVar.b())) {
                        CardPropertyAnimator.recover$default(baseCardCollectionLayout.cardAnimationEngine.animate$cardstack_release(a), null, 1, null);
                    }
                }
                this.a.clear();
            }
        }

        private final void b(h hVar) {
            this.a.add(hVar);
        }

        private final void c() {
            if (BaseCardCollectionLayout.this.cardViewHolderSelector.getSelectedCardViewHolders().isEmpty()) {
                BaseCardCollectionLayout.this.requestDisallowInterceptTouchEvent(false);
            } else {
                BaseCardCollectionLayout.this.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.OnGestureListener
        public void onClick(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            c();
            BaseCardCollectionLayout.this.cardDragSessionDetector.removeDragPointer(pointer.getId());
            CardViewHolderSelection unselect = BaseCardCollectionLayout.this.cardViewHolderSelector.unselect(pointer);
            if (unselect == null) {
                return;
            }
            CardViewHolder<?> component1 = unselect.component1();
            Pointer component3 = unselect.component3();
            if (component3 == null || Intrinsics.areEqual(pointer, component3)) {
                CardPropertyAnimator.recover$default(BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(component1), null, 1, null);
                a();
            }
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.OnGestureListener
        public void onMove(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            c();
            BaseCardCollectionLayout.this.cardDragSessionDetector.addDragPointer(pointer.getId());
            CardViewHolderSelection select = BaseCardCollectionLayout.this.cardViewHolderSelector.select(pointer);
            if (select == null) {
                return;
            }
            CardViewHolder<?> component1 = select.component1();
            Point firstTouchPoint = select.getFirstTouchPoint();
            Pointer component3 = select.component3();
            Point primaryPointerOffset = select.getPrimaryPointerOffset();
            BaseCardCollectionLayout.this.childDrawingOrderCallbackTracker.e(component1);
            if (Intrinsics.areEqual(pointer, component3)) {
                BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(component1).move(firstTouchPoint, pointer.getDisplacement().plus(primaryPointerOffset));
            }
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.OnGestureListener
        public void onRecover(Pointer pointer) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            c();
            BaseCardCollectionLayout.this.cardDragSessionDetector.removeDragPointer(pointer.getId());
            CardViewHolderSelection unselect = BaseCardCollectionLayout.this.cardViewHolderSelector.unselect(pointer);
            if (unselect == null) {
                return;
            }
            CardViewHolder<?> component1 = unselect.component1();
            Pointer component3 = unselect.component3();
            if (component3 == null || Intrinsics.areEqual(pointer, component3)) {
                CardPropertyAnimator.recover$default(BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(component1), null, 1, null);
                a();
            }
        }

        @Override // com.tinder.cardstack.cardgrid.swipe.SwipeGestureDetector.OnGestureListener
        public void onSwipe(Pointer pointer, SwipeDirection swipeDirection) {
            Intrinsics.checkNotNullParameter(pointer, "pointer");
            Intrinsics.checkNotNullParameter(swipeDirection, "swipeDirection");
            c();
            BaseCardCollectionLayout.this.cardDragSessionDetector.removeDragPointer(pointer.getId());
            CardViewHolderSelection unselect = BaseCardCollectionLayout.this.cardViewHolderSelector.unselect(pointer);
            if (unselect == null) {
                return;
            }
            CardViewHolder<?> component1 = unselect.component1();
            Point firstTouchPoint = unselect.getFirstTouchPoint();
            Pointer component3 = unselect.component3();
            if (component3 == null || Intrinsics.areEqual(pointer, component3)) {
                Point velocity = pointer.getVelocity();
                Intrinsics.checkNotNull(velocity);
                BaseCardCollectionLayout.this.cardAnimationEngine.animate$cardstack_release(component1).swipe(velocity.normalized().times(BaseCardCollectionLayout.Y1), firstTouchPoint, (long) ((BaseCardCollectionLayout.Y1 / velocity.magnitude()) * 1000));
                b(new h(component1, swipeDirection));
                a();
            }
        }
    }

    private final class f implements RecyclerView.OnItemTouchListener {
        public f() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            Intrinsics.checkNotNullParameter(event, "event");
            if (BaseCardCollectionLayout.this.isScrolling) {
                return false;
            }
            return BaseCardCollectionLayout.this.swipeGestureDetector.shouldInterceptTouchEvent$cardstack_release(event);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent event) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            Intrinsics.checkNotNullParameter(event, "event");
            BaseCardCollectionLayout.this.swipeGestureDetector.handleTouchEvent$cardstack_release(event);
        }
    }

    private final class g extends RecyclerView.OnScrollListener {
        public g() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, i);
            BaseCardCollectionLayout.this.isScrolling = i != 0;
        }
    }

    private static final class h {
        private final CardViewHolder a;
        private final SwipeDirection b;

        public h(CardViewHolder cardViewHolder, SwipeDirection swipeDirection) {
            Intrinsics.checkNotNullParameter(cardViewHolder, "cardViewHolder");
            Intrinsics.checkNotNullParameter(swipeDirection, "swipeDirection");
            this.a = cardViewHolder;
            this.b = swipeDirection;
        }

        public final CardViewHolder a() {
            return this.a;
        }

        public final SwipeDirection b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof h)) {
                return false;
            }
            h hVar = (h) obj;
            return Intrinsics.areEqual(this.a, hVar.a) && this.b == hVar.b;
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }

        public String toString() {
            return "Swipe(cardViewHolder=" + this.a + ", swipeDirection=" + this.b + ')';
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BaseCardCollectionLayout(@NotNull Context ctx) {
        this(ctx, null);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
    }

    private final void i1(CardViewHolder cardViewHolder) {
        this.cardAnimationEngine.animate$cardstack_release(cardViewHolder).recover(new Point(cardViewHolder.itemView.getTranslationX(), cardViewHolder.itemView.getTranslationY()), new Point(0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean j1(CardViewHolder cardViewHolder, SwipeDirection swipeDirection) {
        if (cardViewHolder.getAdapterPosition() < 0) {
            return false;
        }
        Card card = getAdapter().get(cardViewHolder.getAdapterPosition());
        Intrinsics.checkNotNullExpressionValue(card, "adapter.get(cardViewHolder.adapterPosition)");
        OnPreSwipeListener onPreSwipeListener = this.onPreSwipeListener;
        if (onPreSwipeListener != null) {
            return onPreSwipeListener.onPreSwipe(card, swipeDirection);
        }
        return false;
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    public void addCardDecorationListener(@NotNull View view, @NotNull CardDecorationListener cardDecorationListener) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(cardDecorationListener, "cardDecorationListener");
        this.itemDecoration.a(view, cardDecorationListener);
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    public void removeCardDecorationListener(@NotNull View view, @NotNull CardDecorationListener cardDecorationListener) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(cardDecorationListener, "cardDecorationListener");
        this.itemDecoration.e(view, cardDecorationListener);
    }

    public final void revertAnimatingCard(@NotNull Card<?> card) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        Intrinsics.checkNotNullParameter(card, "card");
        int positionForCard = getAdapter().getPositionForCard(card);
        if (positionForCard == -1 || (findViewHolderForAdapterPosition = findViewHolderForAdapterPosition(positionForCard)) == null) {
            return;
        }
        i1((CardViewHolder) findViewHolderForAdapterPosition);
    }

    public final void setDragSessionListener(@NotNull DragSessionListener dragSessionListener) {
        Intrinsics.checkNotNullParameter(dragSessionListener, "dragSessionListener");
        this.cardDragSessionDetector.setDragSessionListener(dragSessionListener);
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    public void setOnPreSwipeListener(@NotNull OnPreSwipeListener preSwipeListener) {
        Intrinsics.checkNotNullParameter(preSwipeListener, "preSwipeListener");
        this.onPreSwipeListener = preSwipeListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseCardCollectionLayout(@NotNull Context ctx, @Nullable AttributeSet attributeSet) {
        super(ctx, attributeSet);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        CardAnimationEngine cardAnimationEngine = new CardAnimationEngine();
        this.cardAnimationEngine = cardAnimationEngine;
        SwipeDirectionRecognizer swipeDirectionRecognizer = new SwipeDirectionRecognizer();
        this.swipeDirectionRecognizer = swipeDirectionRecognizer;
        c cVar = new c();
        this.itemDecoration = cVar;
        ChildDrawingOrderCallbackTracker childDrawingOrderCallbackTracker = new ChildDrawingOrderCallbackTracker();
        this.childDrawingOrderCallbackTracker = childDrawingOrderCallbackTracker;
        this.cardDragSessionDetector = new CardDragSessionDetector();
        addItemDecoration(cVar);
        addOnItemTouchListener(new f());
        addOnChildAttachStateChangeListener(new d());
        setChildDrawingOrderCallback(childDrawingOrderCallbackTracker);
        addOnScrollListener(new g());
        setItemAnimator(new ItemAnimator());
        float screenWidth = ViewHelper.getScreenWidth();
        float dimension = getContext().getResources().getDimension(R.dimen.fling_escape_velocity_dp);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        float f2 = screenWidth * W1;
        SwipeGestureDetector swipeGestureDetector = new SwipeGestureDetector(new HorizontalTouchInterceptPredicate(), new SwipeActionRecognizerImpl(new SwipeActionRecognizerImpl.Configuration(dimension * 6, f2, f2 / 3), swipeDirectionRecognizer), new ClickActionRecognizerImpl(new ClickActionRecognizerImpl.Configuration(Math.max(X1, viewConfiguration.getScaledTouchSlop() / 2))));
        this.swipeGestureDetector = swipeGestureDetector;
        swipeGestureDetector.setOnGestureListener$cardstack_release(new e());
        cardAnimationEngine.setRenderer$cardstack_release(new a());
        this.cardViewHolderSelector = new CardViewHolderSelector(new b());
    }
}