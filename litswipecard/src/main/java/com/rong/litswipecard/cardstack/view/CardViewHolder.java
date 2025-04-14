package com.rong.litswipecard.cardstack.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.animation.SwipeAnimation;
import com.rong.litswipecard.cardstack.model.Card;
import com.rong.litswipecard.cardstack.model.Direction;

/* loaded from: classes7.dex */
public class CardViewHolder<M extends Card> extends RecyclerView.ViewHolder {
    private CardView<M> cardView;
    private M cardViewModel;
    private boolean isAtTop;

    public interface Factory<VH extends CardViewHolder, T extends Card> {
        VH createViewHolder(ViewGroup viewGroup, int i);

        int getViewType(T t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CardViewHolder(View view) {
        super(view);
        if (view instanceof CardView) {
            this.cardView = (CardView) view;
            return;
        }
        throw new IllegalArgumentException("cardView must implement " + CardView.class.getSimpleName());
    }

    void bind(M m) {
        this.cardViewModel = m;
        this.cardView.bind(m);
    }

    public boolean canDragCard(Direction direction, M m) {
        return true;
    }

    @Nullable
    public SwipeAnimation getAppearingAnimation() {
        return this.cardViewModel.getAppearingAnimation();
    }

    @Nullable
    public SwipeAnimation getDisappearingAnimation() {
        return this.cardViewModel.getDisappearingAnimation();
    }

    public boolean isSwipable(M m) {
        return true;
    }

    public void onAttachedToCardCollectionLayout(@NonNull CardCollectionLayout cardCollectionLayout) {
        this.cardView.onAttachedToCardCollectionLayout(cardCollectionLayout);
    }

    public void onCardAtTop(boolean z) {
        if (z && !this.isAtTop) {
            this.cardView.onMovedToTop(this.cardViewModel);
        } else if (!z && this.isAtTop) {
            this.cardView.onRemovedFromTop(this.cardViewModel);
        }
        this.isAtTop = z;
    }

    public void onCardViewRecycled() {
        this.cardView.onCardViewRecycled();
    }

    public void onDetachedFromCardCollectionLayout(@NonNull CardCollectionLayout cardCollectionLayout) {
        this.cardView.onDetachedFromCardCollectionLayout(cardCollectionLayout);
    }

    public boolean canDragCard(Direction direction) {
        return canDragCard(direction, this.cardViewModel);
    }

    public boolean isSwipable() {
        return isSwipable(this.cardViewModel);
    }
}