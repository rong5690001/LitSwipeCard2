package com.rong.litswipecard.cardstack.view;

import androidx.annotation.NonNull;

/* loaded from: classes7.dex */
public interface CardView<T> {
    void bind(T t);

    void onAttachedToCardCollectionLayout(@NonNull CardCollectionLayout cardCollectionLayout);

    void onCardViewRecycled();

    void onDetachedFromCardCollectionLayout(@NonNull CardCollectionLayout cardCollectionLayout);

    void onMovedToTop(T t);

    void onRemovedFromTop(T t);
}