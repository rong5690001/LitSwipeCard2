package com.rong.litswipecard.cardstack.swipe;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.swipe.CardAnimation;
import timber.log.Timber;

/* loaded from: classes7.dex */
public class CardItemTouchHelperUtil {
    private int a(View view, RecyclerView recyclerView) {
        int indexOfChild = recyclerView.indexOfChild(view);
        if (indexOfChild < 0) {
            Timber.w("getChildViewIndex::for:index=" + indexOfChild + " for view: " + view, new Object[0]);
        }
        return indexOfChild;
    }

    public static boolean hitTest(View view, float f, float f2, float f3, float f4) {
        return f >= f3 && f <= f3 + ((float) view.getWidth()) && f2 >= f4 && f2 <= f4 + ((float) view.getHeight());
    }

    boolean b(TouchPointer touchPointer, VelocityTracker velocityTracker, SwipeThresholdDetector swipeThresholdDetector) {
        touchPointer.a();
        velocityTracker.computeCurrentVelocity(swipeThresholdDetector.n());
        return swipeThresholdDetector.isSwipeThresholdCrossed(touchPointer.b(), touchPointer.c(), velocityTracker.getXVelocity(), velocityTracker.getYVelocity());
    }

    boolean c(TouchPointer touchPointer, SwipeThresholdDetector swipeThresholdDetector) {
        return swipeThresholdDetector.o(touchPointer.b(), touchPointer.c());
    }

    @Nullable
    protected RecyclerView.ViewHolder findSelectableViewHolder(MotionEvent motionEvent, RecyclerView recyclerView, CardAnimator cardAnimator) {
        CardAnimation findCardAnimation;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int childCount = recyclerView.getChildCount();
        do {
            childCount--;
            if (childCount >= 0) {
                View childAt = recyclerView.getChildAt(childCount);
                RecyclerView.ViewHolder findContainingViewHolder = recyclerView.findContainingViewHolder(childAt);
                findCardAnimation = cardAnimator.findCardAnimation(childAt);
                if (findCardAnimation != null) {
                    if (hitTest(childAt, x, y, findCardAnimation.getCurrX(), findCardAnimation.getCurrY())) {
                        if (findCardAnimation.getAnimationType() == CardAnimation.AnimationType.SWIPE_OUT) {
                            return null;
                        }
                        return findContainingViewHolder;
                    }
                } else if (hitTest(childAt, x, y, childAt.getX(), childAt.getY())) {
                    return findContainingViewHolder;
                }
            }
            return null;
        } while (findCardAnimation.getAnimationType() == CardAnimation.AnimationType.SWIPE_OUT);
        return null;
    }

    protected boolean isReadyToAcceptSwipes(RecyclerView.ViewHolder viewHolder, RecyclerView recyclerView, CardAnimator cardAnimator) {
        int a = a(viewHolder.itemView, recyclerView);
        if (a < 0) {
            Timber.w("isReadyToAcceptSwipes for index <0 " + viewHolder, new Object[0]);
            return false;
        }
        if (a < 0) {
            return false;
        }
        for (int childCount = recyclerView.getChildCount() - 1; childCount >= a; childCount--) {
            CardAnimation findCardAnimation = cardAnimator.findCardAnimation(recyclerView.getChildAt(childCount));
            if (findCardAnimation != null) {
                if (findCardAnimation.getAnimationType() == CardAnimation.AnimationType.SWIPE_OUT) {
                }
            } else if (childCount == a) {
                return true;
            }
            return false;
        }
        return true;
    }
}