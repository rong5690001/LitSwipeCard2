package com.rong.litswipecard.cardstack.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.cardstack.CardStackLayoutManager;
import com.rong.litswipecard.cardstack.cardstack.CardStackSwipeHelper;
import com.rong.litswipecard.cardstack.cardstack.cardtransformer.CardTransforms;
import com.rong.litswipecard.cardstack.cardstack.cardtransformer.DefaultCardTransforms;
import com.rong.litswipecard.cardstack.model.Card;
import com.rong.litswipecard.cardstack.model.SwipeDirection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import timber.log.Timber;

/* loaded from: classes7.dex */
public class CardStackLayout extends CardCollectionLayout {
    private final CardStackSwipeHelper N1;
    private final CardStackLayoutManager O1;
    private OnCardPresentedListener P1;
    private CopyOnWriteArrayList Q1;
    private CardTransforms R1;

    public interface CardRewindAnimationStateListener {
        void onRewindAnimationEnded(View view);

        void onRewindAnimationProgress(View view, float f);

        void onRewindAnimationStarted(View view);
    }

    public interface OnCardPairStateChangeListener {
        void onPairCreated(View view, View view2);

        void onPairDestroyed(View view, View view2);
    }

    public interface OnCardPresentedListener {
        void onCardPresented(@NonNull Card card, @NonNull View view);
    }

    public interface OnDispatchTouchEventListener {
        boolean onDispatchTouchEvent(MotionEvent motionEvent);
    }

    public interface OnTopCardMovedListener {
        void onTopCardMoveEnded(boolean z);

        void onTopCardMoveStarted();

        void onTopCardMoved(float f, float f2, float f3, View view, SwipeDirection swipeDirection, boolean z);
    }

    class a implements CardStackSwipeHelper.OnPreSwipeListener {
        final /* synthetic */ OnPreSwipeListener a;

        a(OnPreSwipeListener onPreSwipeListener) {
            this.a = onPreSwipeListener;
        }

        @Override // com.tinder.cardstack.cardstack.CardStackSwipeHelper.OnPreSwipeListener
        public boolean onPreSwipe(int i, SwipeDirection swipeDirection) {
            if (i >= 0) {
                return this.a.onPreSwipe(CardStackLayout.this.getAdapter().get(i), swipeDirection);
            }
            Timber.w("Invalid adapter position:" + i, new Object[0]);
            return false;
        }
    }

    private class b implements CardStackLayoutManager.OnCardPresentedListener {
        private b() {
        }

        @Override // com.tinder.cardstack.cardstack.CardStackLayoutManager.OnCardPresentedListener
        public void onCardPresented(View view) {
            int adapterPosition = CardStackLayout.this.getChildViewHolder(view).getAdapterPosition();
            if (CardStackLayout.this.P1 != null) {
                CardStackLayout.this.P1.onCardPresented(CardStackLayout.this.getAdapter().get(adapterPosition), view);
            }
        }

        /* synthetic */ b(CardStackLayout cardStackLayout, a aVar) {
            this();
        }
    }

    public CardStackLayout(Context context) {
        this(context, null);
    }

    public void addCardDecorationListener(@NonNull CardDecorationListener cardDecorationListener) {
        this.N1.addCardDecorationListener(cardDecorationListener);
    }

    public void addOnCardPairStateChangeListener(@NonNull OnCardPairStateChangeListener onCardPairStateChangeListener) {
        this.N1.addOnCardPairStateChangeListener(onCardPairStateChangeListener);
    }

    public void addOnChildAttachStateChangeListenerPostLayout(@NonNull OnChildAttachStateChangePostLayoutListeners onChildAttachStateChangePostLayoutListeners) {
        this.O1.addOnChildAttachStateChangeListenerPostLayout(onChildAttachStateChangePostLayoutListeners);
    }

    public void addOnDispatchTouchEventListener(@NonNull OnDispatchTouchEventListener onDispatchTouchEventListener) {
        this.Q1.add(onDispatchTouchEventListener);
    }

    public void addTopCardMovedListener(@NonNull OnTopCardMovedListener onTopCardMovedListener) {
        this.N1.addTopCardMovedListener(onTopCardMovedListener);
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    protected void checkIfNewInsertsAffectFrozenCards(int i, int i2) {
        if (this.N1.checkIfNewInsertsAffectFrozenCards(i, i2)) {
            revertFrozenAnimatingCards();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Iterator it2 = this.Q1.iterator();
        while (it2.hasNext()) {
            if (((OnDispatchTouchEventListener) it2.next()).onDispatchTouchEvent(motionEvent)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void freezeAnimatingCards() {
        this.N1.pauseAnimations();
    }

    @NonNull
    public CardTransforms getCardTransforms() {
        return this.R1;
    }

    public boolean hasFrozenAnimatingCards() {
        return this.N1.hasFrozenAnimatingCards();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.O1.onSizeChanged(i, i2, i3, i4);
    }

    public void removeCardDecorationListener(@NonNull CardDecorationListener cardDecorationListener) {
        this.N1.removeCardDecorationListener(cardDecorationListener);
    }

    public void removeCardRewindAnimationStateListener() {
        this.N1.removeCardRewindAnimationStateListener();
    }

    public void removeOnCardPairStateChangeListener(@NonNull OnCardPairStateChangeListener onCardPairStateChangeListener) {
        this.N1.removeOnCardPairStateChangeListener(onCardPairStateChangeListener);
    }

    public void removeOnChildAttachStateChangeListenerPostLayout(@NonNull OnChildAttachStateChangePostLayoutListeners onChildAttachStateChangePostLayoutListeners) {
        this.O1.removeOnChildAttachStateChangeListenerPostLayout(onChildAttachStateChangePostLayoutListeners);
    }

    public void removeOnDispatchTouchEventListener(@NonNull OnDispatchTouchEventListener onDispatchTouchEventListener) {
        this.Q1.remove(onDispatchTouchEventListener);
    }

    public void removeTopCardMovedListener(@NonNull OnTopCardMovedListener onTopCardMovedListener) {
        this.N1.removeTopCardMovedListener(onTopCardMovedListener);
    }

    public void resumeFrozenAnimatingCards() {
        this.N1.resumePausedAnimations();
    }

    public void revertFrozenAnimatingCards() {
        this.N1.revertPausedAnimations();
    }

    public boolean revertLastAnimatedCard() {
        return this.N1.revertLastCardAnimation();
    }

    public void setCardRewindAnimationStateListener(@NonNull CardRewindAnimationStateListener cardRewindAnimationStateListener) {
        this.N1.setCardRewindAnimationStateListener(cardRewindAnimationStateListener);
    }

    public void setCardTransforms(@NonNull CardTransforms cardTransforms) {
        this.R1 = cardTransforms;
        invalidate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof CardStackLayoutManager) {
            super.setLayoutManager(layoutManager);
            return;
        }
        throw new IllegalStateException("LayoutManager must extend from " + CardStackLayoutManager.class);
    }

    public void setOnCardPresentedListener(@Nullable OnCardPresentedListener onCardPresentedListener) {
        this.P1 = onCardPresentedListener;
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    public void setOnPreSwipeListener(@NonNull OnPreSwipeListener onPreSwipeListener) {
        this.N1.setOnPreSwipeListener(new a(onPreSwipeListener));
    }

    public CardStackLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.R1 = new DefaultCardTransforms();
        this.Q1 = new CopyOnWriteArrayList();
        setClipChildren(false);
        setItemAnimator(null);
        RecyclerView.Adapter cardViewAdapter = new CardViewAdapter();
        cardViewAdapter.setHasStableIds(false);
        setAdapter(cardViewAdapter);
        CardStackLayoutManager cardStackLayoutManager = new CardStackLayoutManager(this);
        this.O1 = cardStackLayoutManager;
        cardStackLayoutManager.setOnCardPresentedListener(new b(this, null));
        setLayoutManager(cardStackLayoutManager);
        this.N1 = new CardStackSwipeHelper(this);
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    public void addCardDecorationListener(@NonNull View view, @NonNull CardDecorationListener cardDecorationListener) {
        this.N1.addCardDecorationListener(view, cardDecorationListener);
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    public void removeCardDecorationListener(@NonNull View view, @NonNull CardDecorationListener cardDecorationListener) {
        this.N1.removeCardDecorationListener(view, cardDecorationListener);
    }
}