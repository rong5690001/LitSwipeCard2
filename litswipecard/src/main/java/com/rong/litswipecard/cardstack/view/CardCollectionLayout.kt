package com.rong.litswipecard.cardstack.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.animation.SwipeAnimation;
import com.rong.litswipecard.cardstack.model.Card;
import com.tinder.cardstack.view.CardViewHolder;
import java.util.List;
import timber.log.Timber;

/* loaded from: classes7.dex */
public abstract class CardCollectionLayout extends RecyclerView {
    protected static final boolean DEBUG = false;

    public CardCollectionLayout(Context context) {
        this(context, null);
    }

    private boolean e1(int i) {
        return i >= 0 && i <= getAdapter().getItemCount();
    }

    private boolean f1(int i) {
        return i >= 0 && i < getAdapter().getItemCount();
    }

    private void g1(int i) {
        Timber.wtf("Inserting card at invalid position, position:" + i + ", itemCount:" + getAdapter().getItemCount(), new Object[0]);
    }

    private void h1(int i) {
        Timber.wtf("Removing card from invalid position, position:" + i + ", itemCount:" + getAdapter().getItemCount(), new Object[0]);
    }

    public abstract void addCardDecorationListener(@NonNull View view, @NonNull CardDecorationListener cardDecorationListener);

    protected abstract void checkIfNewInsertsAffectFrozenCards(int i, int i2);

    public void insertCard(int i, @NonNull Card card) {
        if (!e1(i)) {
            g1(i);
        } else {
            checkIfNewInsertsAffectFrozenCards(i, 1);
            getAdapter().insert(i, card);
        }
    }

    public void insertCards(int i, @NonNull List<Card> list) {
        if (!e1(i)) {
            g1(i);
        } else {
            checkIfNewInsertsAffectFrozenCards(i, list.size());
            getAdapter().insert(i, list);
        }
    }

    public boolean isEmpty() {
        return getAdapter().isEmpty();
    }

    @Nullable
    public Card peek() {
        return getAdapter().peek();
    }

    public void refresh(@NonNull List<Card> list) {
        removeAllCards();
        insertCards(0, list);
    }

    public void refreshTopCard() {
        getAdapter().notifyItemChanged(0);
    }

    public void removeAllCards() {
        getAdapter().removeAll();
    }

    public void removeCard(int i) {
        if (f1(i)) {
            getAdapter().remove(i);
        } else {
            h1(i);
        }
    }

    public abstract void removeCardDecorationListener(@NonNull View view, @NonNull CardDecorationListener cardDecorationListener);

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(@NonNull RecyclerView.Adapter adapter) {
        if (!(adapter instanceof CardViewAdapter)) {
            throw new IllegalArgumentException("CardCollectionLayout only supports CardViewAdapter");
        }
        super.setAdapter(adapter);
    }

    public void setCardStackViewHolderFactory(CardViewHolder.Factory<CardViewHolder<Card>, Card> factory) {
        getAdapter().setViewHolderFactory(factory);
    }

    public abstract void setOnPreSwipeListener(@NonNull OnPreSwipeListener onPreSwipeListener);

    public CardCollectionLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    @NonNull
    public CardViewAdapter getAdapter() {
        if (super.getAdapter() != null) {
            return (CardViewAdapter) super.getAdapter();
        }
        throw new IllegalStateException("Adapter is null. Have you set the Adapter?");
    }

    public void removeCard(int i, @Nullable SwipeAnimation swipeAnimation) {
        if (!f1(i)) {
            h1(i);
            return;
        }
        CardViewAdapter adapter = getAdapter();
        adapter.get(i).setDisappearingAnimation(swipeAnimation);
        adapter.remove(i);
    }

    public void insertCard(int i, @NonNull Card card, @NonNull SwipeAnimation swipeAnimation) {
        if (!e1(i)) {
            g1(i);
            return;
        }
        checkIfNewInsertsAffectFrozenCards(i, 1);
        card.setAppearingAnimation(swipeAnimation);
        getAdapter().insert(i, card);
    }
}