package com.rong.litswipecard.cardstack.cardstack.cardtransformer;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.model.SwipeDirection;
import com.tinder.cardstack.swipe.SwipeThresholdDetector;
import com.tinder.cardstack.view.CardDecorationListener;
import com.tinder.cardstack.view.CardStackLayout;
import timber.log.Timber;

/* loaded from: classes7.dex */
class a implements CardDecorationListener {
    private final View a0;
    private final View b0;
    private final CardStackLayout c0;
    private final SwipeThresholdDetector d0;

    a(View view, View view2, CardStackLayout cardStackLayout, SwipeThresholdDetector swipeThresholdDetector) {
        this.a0 = view;
        this.b0 = view2;
        this.c0 = cardStackLayout;
        this.d0 = swipeThresholdDetector;
        cardStackLayout.addCardDecorationListener(view2, this);
        int i = cardStackLayout.indexOfChild(view2) == cardStackLayout.getChildCount() - 1 ? 0 : 1;
        i(view2, b().getTransformForView(i));
        i(view, b().getTransformForView(i + 1));
    }

    private void a(View view) {
        i(view, b().getTransformForView(this.c0.getChildAdapterPosition(view)));
    }

    private CardTransforms b() {
        return this.c0.getCardTransforms();
    }

    private static float f(float f, float f2, float f3, float f4, float f5) {
        return (((f5 - f3) / (f4 - f2)) * (f - f2)) + f3;
    }

    private void g(ViewGroup viewGroup) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.b0.getLayoutParams();
        if (viewGroup.indexOfChild(this.a0) <= 0 && viewGroup.indexOfChild(this.b0) != 1 && viewGroup.indexOfChild(this.a0) != 0) {
            Timber.w("Invalid index:: check Implementation::high=" + viewGroup.indexOfChild(this.b0) + "::low=" + viewGroup.indexOfChild(this.a0) + "::h=" + this.b0.hashCode() + "::l=" + this.a0.hashCode(), new Object[0]);
        }
        if (!layoutParams.isItemRemoved() || viewGroup.indexOfChild(this.a0) >= 0) {
            return;
        }
        Timber.w("::high=" + viewGroup.indexOfChild(this.b0) + "::low=" + viewGroup.indexOfChild(this.a0) + "::childcount=" + viewGroup.getChildCount() + "::h=" + this.b0.hashCode() + "::l=" + this.a0.hashCode(), new Object[0]);
    }

    private void h(float f) {
        CardTransform transformForView = b().getTransformForView(0);
        ViewCompat.setTranslationZ(this.b0, f(f, 0.0f, transformForView.getStartTranslationZ(), 1.0f, transformForView.getEndTranslationZ()));
    }

    private void i(View view, CardTransform cardTransform) {
        j(view, cardTransform.getStartScale(), cardTransform.getStartScale(), cardTransform.getStartTranslationZ());
    }

    private void j(View view, float f, float f2, float f3) {
        ViewCompat.setScaleX(view, f);
        ViewCompat.setScaleY(view, f2);
        ViewCompat.setTranslationZ(view, f3);
    }

    void c() {
        this.c0.removeCardDecorationListener(this.b0, this);
        boolean z = this.c0.getChildAdapterPosition(this.b0) >= 0;
        boolean z2 = this.c0.getChildAdapterPosition(this.a0) >= 0;
        if (z) {
            a(this.b0);
        }
        if (z2) {
            a(this.a0);
        }
    }

    View d() {
        return this.b0;
    }

    View e() {
        return this.a0;
    }

    @Override // com.tinder.cardstack.view.CardDecorationListener
    public void onDecorationDraw(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
        float min = (float) Math.min(1.0d, Math.sqrt(Math.pow(f, 2.0d) + Math.pow(f2, 2.0d)) / this.d0.getMinThresholdForSwipe());
        g(viewGroup);
        h(min);
        CardTransform transformForView = b().getTransformForView(1);
        float f4 = f(min, 0.0f, transformForView.getStartScale(), 1.0f, transformForView.getEndScale());
        float f5 = f(min, 0.0f, transformForView.getStartTranslationZ(), 1.0f, transformForView.getEndTranslationZ());
        ViewCompat.setScaleX(this.a0, f4);
        ViewCompat.setScaleY(this.a0, f4);
        ViewCompat.setTranslationZ(this.a0, f5);
    }

    @Override // com.tinder.cardstack.view.CardDecorationListener
    public void onDecorationDrawOver(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
    }

    public String toString() {
        return "[low=" + e().hashCode() + ", high=" + d().hashCode() + "]";
    }
}