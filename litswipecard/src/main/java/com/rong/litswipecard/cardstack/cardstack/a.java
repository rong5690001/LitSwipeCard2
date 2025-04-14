package com.rong.litswipecard.cardstack.cardstack;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.animation.SwipeAnimation;
import com.tinder.cardstack.animation.SwipeLeftRewindAnimation;
import com.tinder.cardstack.animation.SwipeRightRewindAnimation;
import com.tinder.cardstack.animation.SwipeUpRewindAnimation;
import com.tinder.cardstack.swipe.CardAnimation;
import com.tinder.cardstack.swipe.CardAnimator;
import com.tinder.cardstack.view.CardStackLayout;
import com.tinder.cardstack.view.CardViewHolder;

/* loaded from: classes7.dex */
class a extends RecyclerView.ItemAnimator {
    private static final CardStackLayout.CardRewindAnimationStateListener e = new d();
    private final CardAnimator c;
    private final String a = a.class.getSimpleName();
    private CardStackLayout.CardRewindAnimationStateListener b = e;
    private int d = 0;

    /* renamed from: com.tinder.cardstack.cardstack.a$a, reason: collision with other inner class name */
    class C0274a extends com.rong.litswipecard.cardstack.cardstack.e {
        final /* synthetic */ RecyclerView.ViewHolder a0;

        C0274a(RecyclerView.ViewHolder viewHolder) {
            this.a0 = viewHolder;
        }

        @Override // com.tinder.cardstack.cardstack.e, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            a.a(a.this);
            a.this.dispatchAnimationFinished(this.a0);
        }
    }

    class b extends CardAnimation {
        final /* synthetic */ RecyclerView.ViewHolder w0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(RecyclerView.ViewHolder viewHolder, CardAnimation.AnimationType animationType, PointF pointF, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, RecyclerView.ViewHolder viewHolder2) {
            super(viewHolder, animationType, pointF, f, f2, f3, f4, f5, f6, f7, f8);
            this.w0 = viewHolder2;
        }

        @Override // com.tinder.cardstack.swipe.CardAnimation, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            a.a(a.this);
            a.this.dispatchAnimationFinished(this.w0);
        }
    }

    class c extends SwipeOutCardAnimation {
        final /* synthetic */ RecyclerView.ViewHolder w0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(RecyclerView.ViewHolder viewHolder, CardAnimation.AnimationType animationType, PointF pointF, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, RecyclerView.ViewHolder viewHolder2) {
            super(viewHolder, animationType, pointF, f, f2, f3, f4, f5, f6, f7, f8);
            this.w0 = viewHolder2;
        }

        @Override // com.tinder.cardstack.swipe.CardAnimation, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            a.a(a.this);
            a.this.dispatchAnimationFinished(this.w0);
        }
    }

    class d implements CardStackLayout.CardRewindAnimationStateListener {
        d() {
        }

        @Override // com.tinder.cardstack.view.CardStackLayout.CardRewindAnimationStateListener
        public void onRewindAnimationEnded(View view) {
        }

        @Override // com.tinder.cardstack.view.CardStackLayout.CardRewindAnimationStateListener
        public void onRewindAnimationProgress(View view, float f) {
        }

        @Override // com.tinder.cardstack.view.CardStackLayout.CardRewindAnimationStateListener
        public void onRewindAnimationStarted(View view) {
        }
    }

    private class e extends com.rong.litswipecard.cardstack.cardstack.e implements ValueAnimator.AnimatorUpdateListener {
        private final View a0;

        e(View view) {
            this.a0 = view;
        }

        @Override // com.tinder.cardstack.cardstack.e, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            a.this.b.onRewindAnimationEnded(this.a0);
        }

        @Override // com.tinder.cardstack.cardstack.e, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.b.onRewindAnimationEnded(this.a0);
        }

        @Override // com.tinder.cardstack.cardstack.e, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.b.onRewindAnimationStarted(this.a0);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.b.onRewindAnimationProgress(this.a0, valueAnimator.getAnimatedFraction());
        }
    }

    a(CardAnimator cardAnimator) {
        this.c = cardAnimator;
    }

    static /* synthetic */ int a(a aVar) {
        int i = aVar.d;
        aVar.d = i - 1;
        return i;
    }

    private SwipeAnimation c(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CardViewHolder) {
            return ((CardViewHolder) viewHolder).getAppearingAnimation();
        }
        return null;
    }

    private SwipeAnimation d(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CardViewHolder) {
            return ((CardViewHolder) viewHolder).getDisappearingAnimation();
        }
        return null;
    }

    private boolean e(SwipeAnimation swipeAnimation) {
        return (swipeAnimation instanceof SwipeLeftRewindAnimation) || (swipeAnimation instanceof SwipeRightRewindAnimation) || (swipeAnimation instanceof SwipeUpRewindAnimation);
    }

    private void h(RecyclerView.ViewHolder viewHolder, SwipeAnimation swipeAnimation) {
        this.d++;
        View view = viewHolder.itemView;
        float translationX = ViewCompat.getTranslationX(view);
        float translationY = ViewCompat.getTranslationY(view);
        float rotation = ViewCompat.getRotation(view);
        if (translationX == 0.0f) {
            translationX = swipeAnimation.startX();
        }
        float f = translationX;
        float endX = swipeAnimation.endX();
        float startY = translationY != 0.0f ? translationY : swipeAnimation.startY();
        float endY = swipeAnimation.endY();
        if (rotation == 0.0f) {
            rotation = swipeAnimation.startRotation();
        }
        float f2 = rotation;
        c cVar = new c(viewHolder, CardAnimation.AnimationType.SWIPE_OUT, new PointF(f, startY), f, startY, endX, endY, f2, (swipeAnimation.endRotation() != -2.1474836E9f || f2 == -2.1474836E9f) ? swipeAnimation.endRotation() : f2, swipeAnimation.startAlpha(), swipeAnimation.endAlpha(), viewHolder);
        if (swipeAnimation.durationMilli() > 0) {
            cVar.setDuration(swipeAnimation.durationMilli());
        }
        TimeInterpolator interpolator = swipeAnimation.interpolator();
        if (interpolator != null) {
            cVar.setInterpolator(interpolator);
        }
        cVar.setFlaggedForRemoval(true);
        this.c.addActiveAnimation(cVar);
        cVar.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateAppearance(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        CardAnimation cardAnimation;
        CardAnimation findCardAnimation = this.c.findCardAnimation(viewHolder);
        SwipeAnimation c2 = c(viewHolder);
        if (findCardAnimation != null) {
            this.c.endCardAnimation(viewHolder);
        }
        ViewCompat.setAlpha(viewHolder.itemView, 1.0f);
        if (c2 == null) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        this.d++;
        float startX = c2.startX();
        float startY = c2.startY();
        b bVar = new b(viewHolder, CardAnimation.AnimationType.RECOVER, new PointF(startX, startY), startX, startY, c2.endX(), c2.endY(), c2.startRotation(), c2.endRotation(), c2.startAlpha(), c2.endAlpha(), viewHolder);
        if (c2.durationMilli() > 0) {
            bVar.setDuration(c2.durationMilli());
        }
        TimeInterpolator interpolator = c2.interpolator();
        if (interpolator != null) {
            bVar.setInterpolator(interpolator);
        }
        if (e(c2)) {
            cardAnimation = bVar;
            e eVar = new e(viewHolder.itemView);
            cardAnimation.addListener(eVar);
            cardAnimation.addUpdateListener(eVar);
        } else {
            cardAnimation = bVar;
        }
        this.c.addActiveAnimation(cardAnimation);
        cardAnimation.start();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        dispatchAnimationFinished(viewHolder);
        if (viewHolder == viewHolder2) {
            return false;
        }
        dispatchAnimationFinished(viewHolder2);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateDisappearance(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        CardAnimation findCardAnimation = this.c.findCardAnimation(viewHolder);
        SwipeAnimation d2 = d(viewHolder);
        if (findCardAnimation == null || d2 == null) {
            if (d2 != null) {
                h(viewHolder, d2);
                return true;
            }
            if (findCardAnimation != null && findCardAnimation.isRunning()) {
                this.c.endCardAnimation(viewHolder);
            }
            ViewCompat.setAlpha(viewHolder.itemView, 0.0f);
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        findCardAnimation.setFlaggedForRemoval(true);
        if (findCardAnimation.isRunning() && findCardAnimation.getAnimationType() == CardAnimation.AnimationType.SWIPE_OUT) {
            this.d++;
            findCardAnimation.addListener(new C0274a(viewHolder));
            return true;
        }
        if (!findCardAnimation.isRunning() && findCardAnimation.getAnimationType() == CardAnimation.AnimationType.RECOVER) {
            this.c.endCardAnimation(findCardAnimation.getViewHolder());
            h(viewHolder, d2);
            return false;
        }
        if (findCardAnimation.isRunning() && findCardAnimation.getAnimationType() == CardAnimation.AnimationType.RECOVER) {
            this.c.endCardAnimation(viewHolder);
            h(viewHolder, d2);
            return false;
        }
        this.c.endCardAnimation(viewHolder);
        dispatchAnimationFinished(findCardAnimation.getViewHolder());
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animatePersistence(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        dispatchAnimationFinished(viewHolder);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
    }

    public void f() {
        this.b = e;
    }

    public void g(CardStackLayout.CardRewindAnimationStateListener cardRewindAnimationStateListener) {
        this.b = cardRewindAnimationStateListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean isRunning() {
        return this.d != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void onAnimationFinished(RecyclerView.ViewHolder viewHolder) {
        super.onAnimationFinished(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void onAnimationStarted(RecyclerView.ViewHolder viewHolder) {
        super.onAnimationStarted(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
    }
}