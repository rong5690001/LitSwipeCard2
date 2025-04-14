package com.rong.litswipecard.cardstack.view;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.model.Card;
import com.tinder.cardstack.view.CardViewHolder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class CardViewAdapter extends RecyclerView.Adapter {
    private final List a = new ArrayList();
    private CardViewHolder.Factory b;

    public CardViewAdapter() {
        setHasStableIds(true);
    }

    @NonNull
    public Card get(int i) {
        return (Card) this.a.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        if (i >= this.a.size()) {
            return -1L;
        }
        return get(i).getItem().hashCode();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        CardViewHolder.Factory factory = this.b;
        if (factory != null) {
            return factory.getViewType((Card) this.a.get(i));
        }
        throw new IllegalStateException("getItemViewType() called without providing a " + CardViewHolder.Factory.class);
    }

    public int getPositionForCard(Card card) {
        String id = card.getId();
        for (int i = 0; i < this.a.size(); i++) {
            if (((Card) this.a.get(i)).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void insert(int i, Card card) {
        this.a.add(i, card);
        notifyItemInserted(i);
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof CardViewHolder) {
            ((CardViewHolder) viewHolder).bind((Card) this.a.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CardViewHolder.Factory factory = this.b;
        if (factory != null) {
            return factory.createViewHolder(viewGroup, i);
        }
        throw new IllegalStateException("onCreateViewHolder() called without providing a " + CardViewHolder.Factory.class);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CardViewHolder) {
            ((CardViewHolder) viewHolder).onCardViewRecycled();
        }
    }

    @Nullable
    public Card peek() {
        if (isEmpty()) {
            return null;
        }
        return (Card) this.a.get(0);
    }

    public void remove(int i) {
        this.a.remove(i);
        notifyItemRemoved(i);
    }

    public void removeAll() {
        this.a.clear();
        notifyDataSetChanged();
    }

    public void setViewHolderFactory(CardViewHolder.Factory<CardViewHolder<Card>, Card> factory) {
        this.b = factory;
    }

    public void insert(int i, @NonNull List<Card> list) {
        this.a.addAll(i, list);
        notifyItemRangeInserted(i, list.size());
    }
}