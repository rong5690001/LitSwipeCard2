package com.rong.litswipecard.cardstack.view;

import androidx.annotation.NonNull;
import com.rong.litswipecard.cardstack.model.Card;
import com.rong.litswipecard.cardstack.model.SwipeDirection;

/* loaded from: classes7.dex */
public interface OnPreSwipeListener {
    boolean onPreSwipe(@NonNull Card card, @NonNull SwipeDirection swipeDirection);
}