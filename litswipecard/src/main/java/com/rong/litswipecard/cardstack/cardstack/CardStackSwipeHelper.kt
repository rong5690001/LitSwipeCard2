package com.rong.litswipecard.cardstack.cardstack;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.cardstack.cardtransformer.CardDecorationPairController;
import com.tinder.cardstack.model.SwipeDirection;
import com.tinder.cardstack.swipe.CardAnimation;
import com.tinder.cardstack.swipe.CardAnimator;
import com.tinder.cardstack.swipe.CardItemDecorator;
import com.tinder.cardstack.swipe.CardItemTouchHelperUtil;
import com.tinder.cardstack.swipe.CardItemTouchListener;
import com.tinder.cardstack.swipe.SwipeThresholdDetector;
import com.tinder.cardstack.swipe.TouchPointer;
import com.tinder.cardstack.view.CardDecorationListener;
import com.tinder.cardstack.view.CardStackLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class CardStackSwipeHelper {
    private final RecyclerView a;
    private final CardItemTouchListener b;
    private final SwipeThresholdDetector c;
    private final CardAnimator d;
    private final CardDecorationListenerWrapper e;
    private final CardDecorationPairController f;
    private final d g;
    private final com.tinder.cardstack.cardstack.a h;

    public static class CardDecorationListenerWrapper implements CardDecorationListener {
        private List a0 = new ArrayList();
        private Map b0 = new HashMap();

        /* JADX INFO: Access modifiers changed from: private */
        public void e(View view, CardDecorationListener cardDecorationListener) {
            if (!this.b0.containsKey(view)) {
                this.b0.put(view, new ArrayList(4));
            }
            ((List) this.b0.get(view)).add(cardDecorationListener);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f(CardDecorationListener cardDecorationListener) {
            this.a0.add(cardDecorationListener);
        }

        private List g(View view) {
            return this.b0.containsKey(view) ? Collections.unmodifiableList((List) this.b0.get(view)) : Collections.emptyList();
        }

        private void h(CardDecorationListener cardDecorationListener, Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
            int save = canvas.save();
            cardDecorationListener.onDecorationDrawOver(canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            canvas.restoreToCount(save);
        }

        private void i(CardDecorationListener cardDecorationListener, Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
            int save = canvas.save();
            cardDecorationListener.onDecorationDraw(canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            canvas.restoreToCount(save);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void j(View view, CardDecorationListener cardDecorationListener) {
            if (this.b0.containsKey(view)) {
                List list = (List) this.b0.get(view);
                list.remove(cardDecorationListener);
                if (list.isEmpty()) {
                    this.b0.remove(view);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void k(CardDecorationListener cardDecorationListener) {
            this.a0.remove(cardDecorationListener);
        }

        @Override // com.tinder.cardstack.view.CardDecorationListener
        public void onDecorationDraw(@NonNull Canvas canvas, @NonNull View view, @NonNull ViewGroup viewGroup, float f, float f2, float f3, @NonNull SwipeDirection swipeDirection, boolean z, boolean z2) {
            Iterator it2 = this.a0.iterator();
            while (it2.hasNext()) {
                i((CardDecorationListener) it2.next(), canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            }
            Iterator it3 = g(view).iterator();
            while (it3.hasNext()) {
                i((CardDecorationListener) it3.next(), canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            }
        }

        @Override // com.tinder.cardstack.view.CardDecorationListener
        public void onDecorationDrawOver(@NonNull Canvas canvas, @NonNull View view, @NonNull ViewGroup viewGroup, float f, float f2, float f3, @NonNull SwipeDirection swipeDirection, boolean z, boolean z2) {
            Iterator it2 = this.a0.iterator();
            while (it2.hasNext()) {
                h((CardDecorationListener) it2.next(), canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            }
            Iterator it3 = g(view).iterator();
            while (it3.hasNext()) {
                h((CardDecorationListener) it3.next(), canvas, view, viewGroup, f, f2, f3, swipeDirection, z, z2);
            }
        }
    }

    public interface OnPreSwipeListener {
        boolean onPreSwipe(int i, @NonNull SwipeDirection swipeDirection);
    }

    private class a extends com.tinder.cardstack.cardstack.b {
        a(CardAnimator cardAnimator) {
            super(cardAnimator);
        }

        @Override // com.tinder.cardstack.cardstack.b
        RecyclerView.ViewHolder a(View view) {
            return CardStackSwipeHelper.this.a.findContainingViewHolder(view);
        }

        @Override // com.tinder.cardstack.cardstack.b
        RecyclerView b() {
            return CardStackSwipeHelper.this.a;
        }

        @Override // com.tinder.cardstack.cardstack.b
        TouchPointer c() {
            return CardStackSwipeHelper.this.b.getActiveTouchPointer();
        }

        @Override // com.tinder.cardstack.cardstack.b
        void g(boolean z) {
            CardStackSwipeHelper.this.b.unselectViewHolder(z);
        }
    }

    private class b extends CardItemDecorator {
        b(CardAnimator cardAnimator) {
            super(cardAnimator);
        }

        @Override // com.tinder.cardstack.swipe.CardItemDecorator
        protected TouchPointer getActiveTouchPointer() {
            return CardStackSwipeHelper.this.b.getActiveTouchPointer();
        }

        @Override // com.tinder.cardstack.swipe.CardItemDecorator
        protected SwipeDirection getDirectionFromMovement(float f, float f2) {
            return CardStackSwipeHelper.this.c.getDirectionFromMovement(f, f2);
        }

        @Override // com.tinder.cardstack.swipe.CardItemDecorator
        protected float getRotation(View view, float f, float f2) {
            return CardStackSwipeHelper.this.c.getRotation(view, f, f2);
        }

        @Override // com.tinder.cardstack.swipe.CardItemDecorator
        protected void releaseActiveTouchPointer() {
            CardStackSwipeHelper.this.b.unselectViewHolder(false);
        }
    }

    private class c extends CardItemTouchListener {
        c(SwipeThresholdDetector swipeThresholdDetector, CardAnimator cardAnimator, CardItemTouchHelperUtil cardItemTouchHelperUtil) {
            super(swipeThresholdDetector, cardAnimator, cardItemTouchHelperUtil);
        }

        @Override // com.tinder.cardstack.swipe.CardItemTouchListener
        protected RecyclerView getRecyclerView() {
            return CardStackSwipeHelper.this.a;
        }
    }

    public CardStackSwipeHelper(@NonNull CardStackLayout cardStackLayout) {
        this.a = cardStackLayout;
        CardItemTouchHelperUtil cardItemTouchHelperUtil = new CardItemTouchHelperUtil();
        CardAnimator cardAnimator = new CardAnimator();
        this.d = cardAnimator;
        SwipeThresholdDetector swipeThresholdDetector = new SwipeThresholdDetector(cardStackLayout.getContext());
        this.c = swipeThresholdDetector;
        c cVar = new c(swipeThresholdDetector, cardAnimator, cardItemTouchHelperUtil);
        this.b = cVar;
        com.tinder.cardstack.cardstack.a aVar = new com.tinder.cardstack.cardstack.a(cardAnimator);
        this.h = aVar;
        b bVar = new b(cardAnimator);
        cardStackLayout.addItemDecoration(bVar);
        cardStackLayout.addOnItemTouchListener(cVar);
        cardStackLayout.setItemAnimator(aVar);
        a aVar2 = new a(cardAnimator);
        cardStackLayout.addOnChildAttachStateChangeListener(aVar2);
        cardStackLayout.addOnChildAttachStateChangeListenerPostLayout(aVar2);
        CardDecorationListenerWrapper cardDecorationListenerWrapper = new CardDecorationListenerWrapper();
        this.e = cardDecorationListenerWrapper;
        bVar.setCardDecorationListener(cardDecorationListenerWrapper);
        this.f = new CardDecorationPairController(cardStackLayout, swipeThresholdDetector);
        this.g = new d(cardStackLayout, swipeThresholdDetector);
    }

    public void addCardDecorationListener(@NonNull CardDecorationListener cardDecorationListener) {
        this.e.f(cardDecorationListener);
    }

    public void addOnCardPairStateChangeListener(@NonNull CardStackLayout.OnCardPairStateChangeListener onCardPairStateChangeListener) {
        this.f.addOnCardPairStateChangeListener(onCardPairStateChangeListener);
    }

    public void addTopCardMovedListener(@NonNull CardStackLayout.OnTopCardMovedListener onTopCardMovedListener) {
        this.g.b(onTopCardMovedListener);
    }

    public boolean checkIfNewInsertsAffectFrozenCards(int i, int i2) {
        if (!this.d.isInPausedState()) {
            return false;
        }
        int i3 = (i2 + i) - 1;
        Iterator<CardAnimation> it2 = this.d.getPausedAnimations().iterator();
        while (it2.hasNext()) {
            int adapterPosition = it2.next().getViewHolder().getAdapterPosition();
            if (adapterPosition >= i && adapterPosition <= i3) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFrozenAnimatingCards() {
        return this.d.isInPausedState();
    }

    public void pauseAnimations() {
        TouchPointer activeTouchPointer = this.b.getActiveTouchPointer();
        if (activeTouchPointer != null) {
            RecyclerView recyclerView = this.a;
            this.d.startRecoverAnimation(activeTouchPointer.getViewHolder(), recyclerView, activeTouchPointer.getFirstTouchPoint());
            this.b.unselectViewHolder(false);
        }
        this.d.pauseAnimations();
        this.a.invalidate();
    }

    public void removeCardDecorationListener(@NonNull CardDecorationListener cardDecorationListener) {
        this.e.k(cardDecorationListener);
    }

    public void removeCardRewindAnimationStateListener() {
        this.h.f();
    }

    public void removeOnCardPairStateChangeListener(@NonNull CardStackLayout.OnCardPairStateChangeListener onCardPairStateChangeListener) {
        this.f.removeOnCardPairStateChangeListener(onCardPairStateChangeListener);
    }

    public void removeTopCardMovedListener(@NonNull CardStackLayout.OnTopCardMovedListener onTopCardMovedListener) {
        this.g.i(onTopCardMovedListener);
    }

    public void resumePausedAnimations() {
        this.d.resumePausedAnimations();
        this.a.invalidate();
    }

    public boolean revertLastCardAnimation() {
        boolean revertLastCardAnimation = this.d.revertLastCardAnimation();
        if (revertLastCardAnimation) {
            this.b.unselectViewHolderDoNotPublishUpdate();
            this.a.invalidate();
        }
        return revertLastCardAnimation;
    }

    public void revertPausedAnimations() {
        this.d.revertPausedAnimations();
        this.a.invalidate();
    }

    public void setCardRewindAnimationStateListener(@NonNull CardStackLayout.CardRewindAnimationStateListener cardRewindAnimationStateListener) {
        this.h.g(cardRewindAnimationStateListener);
    }

    public void setOnPreSwipeListener(@NonNull OnPreSwipeListener onPreSwipeListener) {
        this.b.setPreSwipeListener(onPreSwipeListener);
    }

    public void addCardDecorationListener(@NonNull View view, @NonNull CardDecorationListener cardDecorationListener) {
        this.e.e(view, cardDecorationListener);
    }

    public void removeCardDecorationListener(@NonNull View view, @NonNull CardDecorationListener cardDecorationListener) {
        this.e.j(view, cardDecorationListener);
    }
}