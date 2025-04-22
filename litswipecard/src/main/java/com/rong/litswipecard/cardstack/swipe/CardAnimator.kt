package com.rong.litswipecard.cardstack.swipe;

import android.graphics.PointF;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.swipe.CardAnimation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;

/* loaded from: classes7.dex */
public class CardAnimator {
    private State a = State.ACTIVE;
    private List b = new ArrayList();
    private List c = new ArrayList();

    private enum State {
        PAUSED,
        ACTIVE
    }

    private void a(CardAnimation cardAnimation) {
        if (this.a == State.PAUSED) {
            this.c.add(cardAnimation);
            return;
        }
        Timber.w("addPausedAnimation called in Active state::" + this.a, new Object[0]);
    }

    private void b() {
        this.c.clear();
    }

    private CardAnimation c() {
        CardAnimation cardAnimation = null;
        for (CardAnimation cardAnimation2 : this.b) {
            if (cardAnimation2.getViewHolder().getAdapterPosition() != -1 && !cardAnimation2.isFlaggedForRemoval() && (cardAnimation == null || cardAnimation2.getViewHolder().getAdapterPosition() < cardAnimation.getViewHolder().getAdapterPosition())) {
                cardAnimation = cardAnimation2;
            }
        }
        return cardAnimation;
    }

    public void addActiveAnimation(@NonNull CardAnimation cardAnimation) {
        if (this.a == State.PAUSED) {
            Timber.w("addActiveAnimation called in paused state::" + this.a, new Object[0]);
            return;
        }
        if (findCardAnimation(cardAnimation.getViewHolder()) != null) {
            Timber.w("Animation already exists for a view::Check implementation", new Object[0]);
        } else {
            this.b.add(cardAnimation);
        }
    }

    List d() {
        return Collections.unmodifiableList(this.b);
    }

    public void endCardAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
        Iterator it2 = this.b.iterator();
        while (it2.hasNext()) {
            CardAnimation cardAnimation = (CardAnimation) it2.next();
            if (cardAnimation.getViewHolder() == viewHolder) {
                it2.remove();
                if (cardAnimation.isRunning()) {
                    cardAnimation.b();
                }
            }
        }
        Iterator it3 = this.c.iterator();
        while (it3.hasNext()) {
            CardAnimation cardAnimation2 = (CardAnimation) it3.next();
            if (cardAnimation2.getViewHolder() == viewHolder) {
                it3.remove();
                cardAnimation2.b();
            }
        }
    }

    @Nullable
    public CardAnimation findCardAnimation(@Nullable View view) {
        for (CardAnimation cardAnimation : this.b) {
            if (cardAnimation.getViewHolder().itemView == view) {
                return cardAnimation;
            }
        }
        return null;
    }

    public List<CardAnimation> getPausedAnimations() {
        return Collections.unmodifiableList(this.c);
    }

    public boolean isInPausedState() {
        return this.a == State.PAUSED;
    }

    public void pauseAnimations() {
        State state = this.a;
        State state2 = State.PAUSED;
        if (state == state2) {
            Timber.w("pauseAnimations called in Paused State::" + this.a, new Object[0]);
            return;
        }
        this.a = state2;
        Iterator it2 = this.b.iterator();
        while (it2.hasNext()) {
            CardAnimation cardAnimation = (CardAnimation) it2.next();
            if (!cardAnimation.isFlaggedForRemoval()) {
                CardAnimation k = cardAnimation.k();
                it2.remove();
                if (cardAnimation.isRunning()) {
                    cardAnimation.b();
                }
                a(k);
            }
        }
    }

    public void resumePausedAnimations() {
        if (this.a != State.PAUSED) {
            Timber.w("Called resumePausedAnimations in Active state:" + this.a, new Object[0]);
            return;
        }
        this.a = State.ACTIVE;
        for (CardAnimation cardAnimation : this.c) {
            addActiveAnimation(cardAnimation);
            cardAnimation.start();
        }
        b();
    }

    public boolean revertLastCardAnimation() {
        CardAnimation c = c();
        if (c == null) {
            return false;
        }
        View view = c.getViewHolder().itemView;
        PointF firstTouchPoint = c.getFirstTouchPoint();
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        if (translationX == 0.0f && translationY == 0.0f) {
            return false;
        }
        RecyclerView.ViewHolder viewHolder = c.getViewHolder();
        endCardAnimation(viewHolder);
        CardAnimation cardAnimation = new CardAnimation(viewHolder, CardAnimation.AnimationType.RECOVER, firstTouchPoint, translationX, translationY, 0.0f, 0.0f);
        addActiveAnimation(cardAnimation);
        cardAnimation.start();
        return true;
    }

    public void revertPausedAnimations() {
        if (this.a != State.PAUSED) {
            Timber.w("Called revertPausedAnimations in when not in paused state:" + this.a, new Object[0]);
            return;
        }
        this.a = State.ACTIVE;
        for (CardAnimation cardAnimation : this.c) {
            float f = cardAnimation.f();
            float g = cardAnimation.g();
            long c = (long) ((cardAnimation.c() * Math.sqrt(Math.pow(f, 2.0d) + Math.pow(g, 2.0d))) / Math.sqrt(Math.pow(f - cardAnimation.d(), 2.0d) + Math.pow(g - cardAnimation.e(), 2.0d)));
            CardAnimation cardAnimation2 = new CardAnimation(cardAnimation.getViewHolder(), CardAnimation.AnimationType.RECOVER, cardAnimation.getFirstTouchPoint(), f, g, 0.0f, 0.0f);
            cardAnimation2.setDuration(c);
            addActiveAnimation(cardAnimation2);
            cardAnimation2.start();
        }
        b();
    }

    public void startRecoverAnimation(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView recyclerView, @NonNull PointF pointF) {
        View view = viewHolder.itemView;
        CardAnimation cardAnimation = new CardAnimation(viewHolder, CardAnimation.AnimationType.RECOVER, pointF, ViewCompat.getTranslationX(view), ViewCompat.getTranslationY(view), 0.0f, 0.0f);
        addActiveAnimation(cardAnimation);
        cardAnimation.start();
        recyclerView.invalidate();
    }

    @Nullable
    public CardAnimation findCardAnimation(@NonNull RecyclerView.ViewHolder viewHolder) {
        for (CardAnimation cardAnimation : this.b) {
            if (cardAnimation.getViewHolder().itemView == viewHolder.itemView) {
                return cardAnimation;
            }
        }
        return null;
    }
}