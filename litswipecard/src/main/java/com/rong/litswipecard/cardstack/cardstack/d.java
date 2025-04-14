package com.rong.litswipecard.cardstack.cardstack;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.model.SwipeDirection;
import com.tinder.cardstack.swipe.SwipeThresholdDetector;
import com.tinder.cardstack.view.CardDecorationListener;
import com.tinder.cardstack.view.CardStackLayout;
import com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import timber.log.Timber;

/* loaded from: classes7.dex */
class d implements CardDecorationListener, OnChildAttachStateChangePostLayoutListeners {
    private final CopyOnWriteArrayList a0 = new CopyOnWriteArrayList();
    private View b0;
    private final CardStackLayout c0;
    private final SwipeThresholdDetector d0;
    private float e0;
    private float f0;
    private float g0;

    d(CardStackLayout cardStackLayout, SwipeThresholdDetector swipeThresholdDetector) {
        this.c0 = cardStackLayout;
        this.d0 = swipeThresholdDetector;
        cardStackLayout.addOnChildAttachStateChangeListenerPostLayout(this);
    }

    private void a(View view) {
        this.c0.addCardDecorationListener(view, this);
    }

    private void c(boolean z) {
        View d = d(z);
        View view = this.b0;
        if (view == d) {
            return;
        }
        if (view != null) {
            h(view);
            e(true);
            j();
            this.b0 = null;
        }
        if (d != null) {
            this.b0 = d;
            a(d);
        }
    }

    private View d(boolean z) {
        View view;
        int childCount = this.c0.getChildCount() - 1;
        View view2 = null;
        while (childCount >= 0) {
            View childAt = this.c0.getChildAt(childCount);
            if (!((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                return (z && (view = this.b0) != null && view2 == view) ? view2 : childAt;
            }
            childCount--;
            view2 = childAt;
        }
        return null;
    }

    private void e(boolean z) {
        Iterator it2 = this.a0.iterator();
        while (it2.hasNext()) {
            ((CardStackLayout.OnTopCardMovedListener) it2.next()).onTopCardMoveEnded(z);
        }
    }

    private void f() {
        Iterator it2 = this.a0.iterator();
        while (it2.hasNext()) {
            ((CardStackLayout.OnTopCardMovedListener) it2.next()).onTopCardMoveStarted();
        }
    }

    private void g(float f, float f2, float f3, boolean z) {
        SwipeDirection directionFromMovement = this.d0.getDirectionFromMovement(f, f2);
        Iterator it2 = this.a0.iterator();
        while (it2.hasNext()) {
            ((CardStackLayout.OnTopCardMovedListener) it2.next()).onTopCardMoved(f, f2, f3, this.b0, directionFromMovement, z);
        }
    }

    private void h(View view) {
        this.c0.removeCardDecorationListener(view, this);
    }

    private void j() {
        this.e0 = 0.0f;
        this.f0 = 0.0f;
        this.g0 = 0.0f;
    }

    void b(CardStackLayout.OnTopCardMovedListener onTopCardMovedListener) {
        this.a0.add(onTopCardMovedListener);
    }

    void i(CardStackLayout.OnTopCardMovedListener onTopCardMovedListener) {
        this.a0.remove(onTopCardMovedListener);
    }

    @Override // com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners
    public void onChildViewAttachedPostLayout(View view) {
        c(true);
    }

    @Override // com.tinder.cardstack.view.OnChildAttachStateChangePostLayoutListeners
    public void onChildViewDetachedPostLayout(View view) {
        c(false);
    }

    @Override // com.tinder.cardstack.view.CardDecorationListener
    public void onDecorationDraw(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
    }

    @Override // com.tinder.cardstack.view.CardDecorationListener
    public void onDecorationDrawOver(Canvas canvas, View view, ViewGroup viewGroup, float f, float f2, float f3, SwipeDirection swipeDirection, boolean z, boolean z2) {
        View view2 = this.b0;
        if (view2 == null || view != view2) {
            Timber.w("onDecorationDrawOver without a topCard", new Object[0]);
            return;
        }
        float f4 = this.e0;
        if (f4 == 0.0f) {
            float f5 = this.f0;
            if (f5 == 0.0f) {
                float f6 = this.g0;
                if (f6 == 0.0f && (f4 != f || f5 != f2 || f6 != f3)) {
                    f();
                    this.e0 = f;
                    this.f0 = f2;
                    this.g0 = f3;
                }
            }
        }
        if (f == 0.0f && f2 == 0.0f && f3 == 0.0f && (f4 != f || this.f0 != f2 || this.g0 != f3)) {
            e(false);
        } else if (f4 != f || this.f0 != f2 || this.g0 != f3) {
            g(f, f2, f3, z2);
        }
        this.e0 = f;
        this.f0 = f2;
        this.g0 = f3;
    }
}