package com.rong.litswipecard.cardstack.swipe;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.cardstack.SwipeOutCardAnimation;
import com.tinder.cardstack.model.SwipeDirection;
import com.tinder.cardstack.swipe.CardAnimation;
import com.tinder.cardstack.view.CardDecorationListener;
import java.util.Iterator;

/* loaded from: classes7.dex */
public abstract class CardItemDecorator extends RecyclerView.ItemDecoration {
    private static CardDecorationListener c0 = new a();
    private final CardAnimator a0;
    private CardDecorationListener b0 = c0;

    class a implements CardDecorationListener {
        a() {
        }

        @Override // com.tinder.cardstack.view.CardDecorationListener
        public void onDecorationDraw(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
        }

        @Override // com.tinder.cardstack.view.CardDecorationListener
        public void onDecorationDrawOver(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
        }
    }

    public CardItemDecorator(@NonNull CardAnimator cardAnimator) {
        this.a0 = cardAnimator;
    }

    private void a(Canvas canvas, View view, RecyclerView recyclerView, float f, float f2, float f3, float f4, boolean z, boolean z2) {
        ViewCompat.setTranslationX(view, f);
        ViewCompat.setTranslationY(view, f2);
        ViewCompat.setRotation(view, f4);
        if (ViewCompat.getAlpha(view) != f3) {
            ViewCompat.setAlpha(view, f3);
        }
        d(canvas, view, recyclerView, f, f2, f4, z, z2);
    }

    private void b(Canvas canvas, View view, RecyclerView recyclerView, float f, float f2, float f3, boolean z, boolean z2) {
        ViewCompat.setTranslationX(view, f);
        ViewCompat.setTranslationY(view, f2);
        if (ViewCompat.getAlpha(view) != f3) {
            ViewCompat.setAlpha(view, f3);
        }
        d(canvas, view, recyclerView, f, f2, ViewCompat.getRotation(view), z, z2);
    }

    private void c(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, boolean z, boolean z2) {
        this.b0.onDecorationDrawOver(canvas, view, viewGroup, f, f2, f3, getDirectionFromMovement(f, f2), z, z2);
    }

    private void d(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, boolean z, boolean z2) {
        this.b0.onDecorationDraw(canvas, view, viewGroup, f, f2, f3, getDirectionFromMovement(f, f2), z, z2);
    }

    @Nullable
    protected abstract TouchPointer getActiveTouchPointer();

    @NonNull
    protected abstract SwipeDirection getDirectionFromMovement(float f, float f2);

    protected abstract float getRotation(@NonNull View view, float f, float f2);

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
        TouchPointer activeTouchPointer = getActiveTouchPointer();
        for (CardAnimation cardAnimation : this.a0.d()) {
            if (activeTouchPointer != null && cardAnimation.getViewHolder() == activeTouchPointer.getViewHolder()) {
                releaseActiveTouchPointer();
            }
            cardAnimation.o();
            float currAlpha = cardAnimation.getCurrAlpha();
            if (cardAnimation.getAnimationType() == CardAnimation.AnimationType.RECOVER) {
                View view = cardAnimation.getViewHolder().itemView;
                a(canvas, view, recyclerView, cardAnimation.getCurrX(), cardAnimation.getCurrY(), currAlpha, cardAnimation.h() ? cardAnimation.getCurrRotation() : getRotation(view, cardAnimation.getCurrX(), cardAnimation.getFirstTouchPoint().y), true, false);
            } else if (cardAnimation instanceof SwipeOutCardAnimation) {
                a(canvas, cardAnimation.getViewHolder().itemView, recyclerView, cardAnimation.getCurrX(), cardAnimation.getCurrY(), currAlpha, ((SwipeOutCardAnimation) cardAnimation).getCurrRotation(), false, false);
            } else {
                b(canvas, cardAnimation.getViewHolder().itemView, recyclerView, cardAnimation.getCurrX(), cardAnimation.getCurrY(), currAlpha, false, false);
            }
            if (cardAnimation.h() && !cardAnimation.isRunning()) {
                cardAnimation.j(false);
            }
        }
        TouchPointer activeTouchPointer2 = getActiveTouchPointer();
        if (activeTouchPointer2 != null) {
            View view2 = activeTouchPointer2.getViewHolder().itemView;
            a(canvas, view2, recyclerView, activeTouchPointer2.b(), activeTouchPointer2.c(), view2.getAlpha(), getRotation(view2, activeTouchPointer2.b(), activeTouchPointer2.getFirstTouchPoint().y), false, true);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        boolean z;
        super.onDrawOver(canvas, recyclerView, state);
        Iterator it2 = this.a0.d().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            CardAnimation cardAnimation = (CardAnimation) it2.next();
            View view = cardAnimation.getViewHolder().itemView;
            z = true;
            c(canvas, view, recyclerView, ViewCompat.getTranslationX(view), ViewCompat.getTranslationY(view), ViewCompat.getRotation(view), cardAnimation.getAnimationType() == CardAnimation.AnimationType.RECOVER, false);
            if (cardAnimation.isRunning()) {
                break;
            }
        }
        TouchPointer activeTouchPointer = getActiveTouchPointer();
        if (activeTouchPointer != null) {
            View view2 = activeTouchPointer.getViewHolder().itemView;
            c(canvas, view2, recyclerView, ViewCompat.getTranslationX(view2), ViewCompat.getTranslationY(view2), getRotation(view2, activeTouchPointer.b(), activeTouchPointer.getFirstTouchPoint().y), false, true);
        }
        if (z) {
            recyclerView.invalidate();
        }
    }

    protected abstract void releaseActiveTouchPointer();

    public void setCardDecorationListener(@NonNull CardDecorationListener cardDecorationListener) {
        this.b0 = cardDecorationListener;
    }
}