package com.rong.litswipecard.cardstack.model;

import androidx.annotation.Nullable;
import com.rong.litswipecard.cardstack.animation.SwipeAnimation;

/* loaded from: classes7.dex */
public class Card<T> {

    @Nullable
    private SwipeAnimation appearingAnimation;

    @Nullable
    private SwipeAnimation disappearingAnimation;
    private String id;
    private T item;
    private boolean showRevertIndicator;

    public Card(String str, T t) {
        this.id = str;
        this.item = t;
    }

    @Nullable
    public SwipeAnimation getAppearingAnimation() {
        return this.appearingAnimation;
    }

    @Nullable
    public SwipeAnimation getDisappearingAnimation() {
        return this.disappearingAnimation;
    }

    public String getId() {
        return this.id;
    }

    public T getItem() {
        return this.item;
    }

    public boolean getShowRevertIndicator() {
        return this.showRevertIndicator;
    }

    public void setAppearingAnimation(@Nullable SwipeAnimation swipeAnimation) {
        this.appearingAnimation = swipeAnimation;
    }

    public void setDisappearingAnimation(@Nullable SwipeAnimation swipeAnimation) {
        this.disappearingAnimation = swipeAnimation;
    }

    public void setShowRevertIndicator(boolean z) {
        this.showRevertIndicator = z;
    }
}