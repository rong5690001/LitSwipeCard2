package com.rong.litswipecard.cardstack.cardstack;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.swipe.CardAnimation;
import com.tinder.cardstack.swipe.CardAnimator;
import com.tinder.cardstack.swipe.TouchPointer;
import com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners;

/* loaded from: classes7.dex */
abstract class b implements RecyclerView.OnChildAttachStateChangeListener, OnChildAttachStateChangePostLayoutListeners {
    private final CardAnimator a0;

    b(CardAnimator cardAnimator) {
        this.a0 = cardAnimator;
    }

    private boolean d(View view) {
        if (((RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
            return true;
        }
        CardAnimation findCardAnimation = this.a0.findCardAnimation(view);
        return findCardAnimation != null && findCardAnimation.isFlaggedForRemoval();
    }

    private boolean e(TouchPointer touchPointer) {
        RecyclerView b = b();
        for (int childCount = b.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = b.getChildAt(childCount);
            if (!((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                return childAt == touchPointer.getViewHolder().itemView;
            }
        }
        return false;
    }

    private void f(TouchPointer touchPointer, RecyclerView recyclerView) {
        this.a0.startRecoverAnimation(touchPointer.getViewHolder(), recyclerView, touchPointer.getFirstTouchPoint());
    }

    abstract RecyclerView.ViewHolder a(View view);

    abstract RecyclerView b();

    abstract TouchPointer c();

    abstract void g(boolean z);

    @Override // com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners
    public void onChildViewAttachedPostLayout(View view) {
        TouchPointer c = c();
        if (c == null || e(c)) {
            return;
        }
        if (!d(c.getViewHolder().itemView)) {
            f(c, b());
        }
        g(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(View view) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(View view) {
        CardAnimation findCardAnimation;
        RecyclerView.ViewHolder a = a(view);
        if (a == null && (findCardAnimation = this.a0.findCardAnimation(view)) != null) {
            a = findCardAnimation.getViewHolder();
        }
        if (a == null) {
            return;
        }
        TouchPointer c = c();
        if (c == null || c.getViewHolder().itemView != view) {
            this.a0.endCardAnimation(a);
        } else {
            g(true);
        }
    }

    @Override // com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners
    public void onChildViewDetachedPostLayout(View view) {
    }
}