package com.rong.litswipecard.cardstack.cardgrid.view;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.rong.litswipecard.cardstack.cardgrid.model.Point;
import com.tinder.cardstack.view.CardViewHolder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u0003\u001a\u00020\u00072\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH&J8\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J>\u0010\u000b\u001a\u00020\u00072\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\b2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH&J0\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J2\u0010\u0016\u001a\u00020\u00072\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH&J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u001b\u001a\u00020\u00072\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH&J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0014\u0010\u001c\u001a\u00020\u00072\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\bH&J\b\u0010\u001d\u001a\u00020\u0007H\u0016J\b\u0010\u001e\u001a\u00020\u0007H\u0016¨\u0006\u001f"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/SimpleCardItemAnimator;", "Landroidx/recyclerview/widget/SimpleItemAnimator;", "()V", "animateAdd", "", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "Lcom/tinder/cardstack/view/CardViewHolder;", "onComplete", "Lkotlin/Function0;", "animateChange", "oldHolder", "newHolder", "fromLeft", "", "fromTop", "toLeft", "toTop", "fromPosition", "Lcom/tinder/cardstack/cardgrid/model/Point;", "toPosition", "animateMove", "fromX", "fromY", "toX", "toY", "animateRemove", "endAnimation", "endAnimations", "runPendingAnimations", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public abstract class SimpleCardItemAnimator extends SimpleItemAnimator {
    public abstract void animateAdd(@NotNull CardViewHolder<?> holder, @NotNull Function0<Unit> onComplete);

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(@NotNull final RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof CardViewHolder) {
            holder.setIsRecyclable(false);
            animateAdd((CardViewHolder) holder, new Function0<Unit>() { // from class: com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator$animateAdd$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RecyclerView.ViewHolder.this.setIsRecyclable(true);
                    this.dispatchAnimationFinished(RecyclerView.ViewHolder.this);
                }
            });
        } else {
            dispatchAnimationFinished(holder);
        }
        return false;
    }

    public abstract void animateChange(@NotNull CardViewHolder<?> oldHolder, @NotNull CardViewHolder<?> newHolder, @NotNull Point fromPosition, @NotNull Point toPosition, @NotNull Function0<Unit> onComplete);

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(@NotNull final RecyclerView.ViewHolder oldHolder, @NotNull final RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        Intrinsics.checkNotNullParameter(oldHolder, "oldHolder");
        Intrinsics.checkNotNullParameter(newHolder, "newHolder");
        if ((oldHolder instanceof CardViewHolder) && (newHolder instanceof CardViewHolder)) {
            oldHolder.setIsRecyclable(false);
            newHolder.setIsRecyclable(false);
            animateChange((CardViewHolder) oldHolder, (CardViewHolder) newHolder, new Point(fromLeft, fromTop), new Point(toLeft, toTop), new Function0<Unit>() { // from class: com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator$animateChange$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RecyclerView.ViewHolder.this.setIsRecyclable(true);
                    newHolder.setIsRecyclable(true);
                    this.dispatchAnimationFinished(RecyclerView.ViewHolder.this);
                    this.dispatchAnimationFinished(newHolder);
                }
            });
        } else {
            dispatchAnimationFinished(oldHolder);
            dispatchAnimationFinished(newHolder);
        }
        return false;
    }

    public abstract void animateMove(@NotNull CardViewHolder<?> holder, @NotNull Point fromPosition, @NotNull Point toPosition, @NotNull Function0<Unit> onComplete);

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(@NotNull final RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof CardViewHolder) {
            holder.setIsRecyclable(false);
            animateMove((CardViewHolder) holder, new Point(fromX, fromY), new Point(toX, toY), new Function0<Unit>() { // from class: com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator$animateMove$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RecyclerView.ViewHolder.this.setIsRecyclable(true);
                    this.dispatchAnimationFinished(RecyclerView.ViewHolder.this);
                }
            });
        } else {
            dispatchAnimationFinished(holder);
        }
        return false;
    }

    public abstract void animateRemove(@NotNull CardViewHolder<?> holder, @NotNull Function0<Unit> onComplete);

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(@NotNull final RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof CardViewHolder) {
            holder.setIsRecyclable(false);
            animateRemove((CardViewHolder) holder, new Function0<Unit>() { // from class: com.tinder.cardstack.cardgrid.view.SimpleCardItemAnimator$animateRemove$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RecyclerView.ViewHolder.this.setIsRecyclable(true);
                    this.dispatchAnimationFinished(RecyclerView.ViewHolder.this);
                }
            });
        } else {
            dispatchAnimationFinished(holder);
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(@NotNull RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (holder instanceof CardViewHolder) {
            endAnimation((CardViewHolder<?>) holder);
        }
    }

    public abstract void endAnimation(@NotNull CardViewHolder<?> holder);

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
    }
}