package com.rong.litswipecard.cardstack.cardstack.cardtransformer;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.swipe.SwipeThresholdDetector;
import com.tinder.cardstack.view.CardStackLayout;
import com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class CardDecorationPairController implements OnChildAttachStateChangePostLayoutListeners {
    private final CardStackLayout a0;
    private final SwipeThresholdDetector b0;
    private final List c0 = new ArrayList();
    private final List d0 = new ArrayList();

    public CardDecorationPairController(@NonNull CardStackLayout cardStackLayout, @NonNull SwipeThresholdDetector swipeThresholdDetector) {
        this.a0 = cardStackLayout;
        this.b0 = swipeThresholdDetector;
        cardStackLayout.addOnChildAttachStateChangeListenerPostLayout(this);
    }

    private void a(View view, View view2) {
        a aVar = new a(view2, view, this.a0, this.b0);
        this.c0.add(aVar);
        d(aVar);
    }

    private void b(a aVar) {
        e(aVar);
        aVar.c();
    }

    private RecyclerView.LayoutParams c(View view) {
        return (RecyclerView.LayoutParams) view.getLayoutParams();
    }

    private void d(a aVar) {
        Iterator it2 = this.d0.iterator();
        while (it2.hasNext()) {
            ((CardStackLayout.OnCardPairStateChangeListener) it2.next()).onPairCreated(aVar.d(), aVar.e());
        }
    }

    private void e(a aVar) {
        Iterator it2 = this.d0.iterator();
        while (it2.hasNext()) {
            ((CardStackLayout.OnCardPairStateChangeListener) it2.next()).onPairDestroyed(aVar.d(), aVar.e());
        }
    }

    private void f(View view) {
        g(view, this.a0.getCardTransforms().getDefaultTransform());
    }

    private void g(View view, CardTransform cardTransform) {
        ViewCompat.setScaleX(view, cardTransform.getStartScale());
        ViewCompat.setScaleY(view, cardTransform.getStartScale());
        ViewCompat.setTranslationY(view, 0.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setTranslationZ(view, cardTransform.getStartTranslationZ());
        ViewCompat.setRotation(view, 0.0f);
        ViewCompat.setRotationY(view, 0.0f);
        ViewCompat.setRotationX(view, 0.0f);
    }

    private void h(View view) {
        Iterator it2 = this.c0.iterator();
        while (it2.hasNext()) {
            a aVar = (a) it2.next();
            if (aVar.e() == view || aVar.d() == view) {
                it2.remove();
                b(aVar);
            }
        }
    }

    private void i(View view) {
        Iterator it2 = this.c0.iterator();
        while (it2.hasNext()) {
            a aVar = (a) it2.next();
            if (aVar.d() == view) {
                b(aVar);
                it2.remove();
            }
        }
    }

    private void j(View view) {
        Iterator it2 = this.c0.iterator();
        while (it2.hasNext()) {
            a aVar = (a) it2.next();
            if (aVar.e() == view) {
                b(aVar);
                it2.remove();
            }
        }
    }

    public void addOnCardPairStateChangeListener(@NonNull CardStackLayout.OnCardPairStateChangeListener onCardPairStateChangeListener) {
        this.d0.add(onCardPairStateChangeListener);
    }

    @Override // com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners
    public void onChildViewAttachedPostLayout(@NonNull View view) {
        int indexOfChild = this.a0.indexOfChild(view);
        View childAt = this.a0.getChildAt(indexOfChild + 1);
        View childAt2 = this.a0.getChildAt(indexOfChild - 1);
        if (childAt != null) {
            i(childAt);
            a(childAt, view);
        }
        if (childAt2 != null && !c(childAt2).isItemRemoved()) {
            j(childAt2);
            a(view, childAt2);
        }
        if (childAt == null && childAt2 == null) {
            g(view, DefaultCardTransforms.INSTANCE.getTOP_CARD_TRANSFORM());
        }
    }

    @Override // com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners
    public void onChildViewDetachedPostLayout(@NonNull View view) {
        h(view);
        f(view);
    }

    public void removeOnCardPairStateChangeListener(@NonNull CardStackLayout.OnCardPairStateChangeListener onCardPairStateChangeListener) {
        this.d0.remove(onCardPairStateChangeListener);
    }
}