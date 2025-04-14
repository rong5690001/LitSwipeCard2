package com.rong.litswipecard.cardstack.cardstack;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.view.CardStackLayout;
import com.tinder.cardstack.view.CardViewHolder;
import com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;

/* loaded from: classes7.dex */
public class CardStackLayoutManager extends RecyclerView.LayoutManager {
    private final CardStackLayout s0;
    private boolean x0;
    private final List t0 = new ArrayList();
    private final List u0 = new ArrayList();
    private int v0 = Integer.MIN_VALUE;
    private int w0 = Integer.MIN_VALUE;
    private View y0 = null;
    private OnCardPresentedListener z0 = new OnCardPresentedListener() { // from class: com.tinder.cardstack.cardstack.c
        @Override // com.tinder.cardstack.cardstack.CardStackLayoutManager.OnCardPresentedListener
        public final void onCardPresented(View view) {
            CardStackLayoutManager.I(view);
        }
    };

    public interface OnCardPresentedListener {
        void onCardPresented(@NonNull View view);
    }

    private class b implements RecyclerView.OnChildAttachStateChangeListener {
        private b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewAttachedToWindow(View view) {
            CardViewHolder cardViewHolder = (CardViewHolder) CardStackLayoutManager.this.s0.getChildViewHolder(view);
            if (cardViewHolder != null) {
                cardViewHolder.onAttachedToCardCollectionLayout(CardStackLayoutManager.this.s0);
            }
            if (CardStackLayoutManager.this.isInLayout()) {
                CardStackLayoutManager.this.u0.add(new c(view, true));
            } else {
                CardStackLayoutManager.this.M(view);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
        public void onChildViewDetachedFromWindow(View view) {
            CardViewHolder cardViewHolder = (CardViewHolder) CardStackLayoutManager.this.s0.getChildViewHolder(view);
            if (cardViewHolder != null) {
                cardViewHolder.onCardAtTop(false);
                cardViewHolder.onDetachedFromCardCollectionLayout(CardStackLayoutManager.this.s0);
            }
            if (view == CardStackLayoutManager.this.y0) {
                CardStackLayoutManager.this.y0 = null;
            }
            if (CardStackLayoutManager.this.isInLayout()) {
                CardStackLayoutManager.this.u0.add(new c(view, false));
            } else {
                CardStackLayoutManager.this.O(view);
            }
        }
    }

    private static class c {
        private final View a;
        private final boolean b;

        c(View view, boolean z) {
            this.a = view;
            this.b = z;
        }

        boolean b() {
            return this.b;
        }
    }

    public CardStackLayoutManager(@NonNull CardStackLayout cardStackLayout) {
        this.s0 = cardStackLayout;
        cardStackLayout.addOnChildAttachStateChangeListener(new b());
        setItemPrefetchEnabled(false);
    }

    private void E(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int min = Math.min(2, getItemCount());
        ArrayDeque arrayDeque = new ArrayDeque(min);
        for (int i = 0; i < min; i++) {
            if (L()) {
                K(recycler);
            }
            View viewForPosition = recycler.getViewForPosition(i);
            viewForPosition.setLayoutDirection(getLayoutDirection());
            measureChildWithMargins(viewForPosition, 0, 0);
            arrayDeque.push(viewForPosition);
            layoutDecorated(viewForPosition, 0, 0, this.v0, this.w0);
        }
        while (!arrayDeque.isEmpty()) {
            View view = (View) arrayDeque.pop();
            H(view);
            addView(view);
            CardViewHolder cardViewHolder = (CardViewHolder) this.s0.getChildViewHolder(view);
            if (!arrayDeque.isEmpty()) {
                cardViewHolder.onCardAtTop(false);
            } else if (view != this.y0) {
                cardViewHolder.onCardAtTop(true);
                this.y0 = view;
                this.z0.onCardPresented(view);
            }
        }
    }

    private String F(Object obj) {
        obj.getClass();
        String canonicalName = obj.getClass().getCanonicalName();
        return canonicalName != null ? canonicalName : "NA";
    }

    private boolean G(View view) {
        ViewParent parent = view.getParent();
        return (parent == null || parent == this.s0) ? false : true;
    }

    private void H(View view) {
        if (G(view)) {
            J(view);
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void I(View view) {
    }

    private void J(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this.s0) {
            return;
        }
        Timber.e(new RuntimeException("View has unexpected parent: ParentClassName=" + F(parent) + ", ViewClassName=" + F(view)));
    }

    private void K(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(0);
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, 0, 0);
        this.w0 = getDecoratedMeasuredHeight(viewForPosition);
        this.v0 = getDecoratedMeasuredWidth(viewForPosition);
        detachAndScrapView(viewForPosition, recycler);
    }

    private boolean L() {
        return this.v0 == Integer.MIN_VALUE || this.w0 == Integer.MIN_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(View view) {
        Iterator it2 = this.t0.iterator();
        while (it2.hasNext()) {
            ((OnChildAttachStateChangePostLayoutListeners) it2.next()).onChildViewAttachedPostLayout(view);
        }
    }

    private void N() {
        for (c cVar : this.u0) {
            if (cVar.b()) {
                M(cVar.a);
            } else {
                O(cVar.a);
            }
        }
        this.u0.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(View view) {
        Iterator it2 = this.t0.iterator();
        while (it2.hasNext()) {
            ((OnChildAttachStateChangePostLayoutListeners) it2.next()).onChildViewDetachedPostLayout(view);
        }
    }

    public void addOnChildAttachStateChangeListenerPostLayout(@NonNull OnChildAttachStateChangePostLayoutListeners onChildAttachStateChangePostLayoutListeners) {
        this.t0.add(onChildAttachStateChangePostLayoutListeners);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public boolean isInLayout() {
        return this.x0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.x0 = true;
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
        } else {
            detachAndScrapAttachedViews(recycler);
            E(recycler, state);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.x0 = false;
        N();
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i4 == i2 && i3 == i) {
            return;
        }
        this.w0 = Integer.MIN_VALUE;
        this.v0 = Integer.MIN_VALUE;
    }

    public void removeOnChildAttachStateChangeListenerPostLayout(@NonNull OnChildAttachStateChangePostLayoutListeners onChildAttachStateChangePostLayoutListeners) {
        this.t0.remove(onChildAttachStateChangePostLayoutListeners);
    }

    public void setOnCardPresentedListener(@NonNull OnCardPresentedListener onCardPresentedListener) {
        this.z0 = onCardPresentedListener;
    }
}