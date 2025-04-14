package com.rong.litswipecard.cardstack.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rong.litswipecard.cardstack.cardgrid.view.BaseCardCollectionLayout;
import com.rong.litswipecard.cardstack.cardgrid.view.CardGridLayoutManager;
import com.rong.litswipecard.cardstack.cardgrid.view.CardGridViewHolderAdapter;
import com.rong.litswipecard.cardstack.cardgrid.view.GridSpacingItemDecoration;
import com.rong.litswipecard.cardstack.cardgrid.view.LoadingStatusViewHolderFactory;
import com.rong.litswipecard.cardstack.cardgrid.view.ScrollVelocityTracker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 @2\u00020\u0001:\u0004@ABCB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0012\u001a\u00020\u00112\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0011¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0011¢\u0006\u0004\b\u001b\u0010\u001aJ\u0015\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010\"\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0014H\u0014¢\u0006\u0004\b\"\u0010\u0018J\u0017\u0010$\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0014H\u0016¢\u0006\u0004\b$\u0010%R$\u0010-\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0016\u00100\u001a\u00020\f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00104\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00108\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u0010<\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010?\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>¨\u0006D"}, d2 = {"Lcom/tinder/cardstack/view/CardGridLayout;", "Lcom/tinder/cardstack/cardgrid/view/BaseCardCollectionLayout;", "Landroid/content/Context;", "ctx", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "getScrollProgressPercent", "()F", "Landroidx/recyclerview/widget/GridLayoutManager;", "getLayoutManager", "()Landroidx/recyclerview/widget/GridLayoutManager;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "adapter", "", "setAdapter", "(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V", "", "spanCount", "headerOffset", "setSpanCount", "(II)V", "showLoadingMore", "()V", "hideLoading", "Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;", "factory", "setLoadingStatusViewHolderFactory", "(Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;)V", "startIndex", "numberOfItemsInserted", "checkIfNewInsertsAffectFrozenCards", "position", "smoothScrollToPosition", "(I)V", "Lcom/tinder/cardstack/view/CardGridLayout$OnScrollProgressListener;", "Z1", "Lcom/tinder/cardstack/view/CardGridLayout$OnScrollProgressListener;", "getOnScrollProgressListener", "()Lcom/tinder/cardstack/view/CardGridLayout$OnScrollProgressListener;", "setOnScrollProgressListener", "(Lcom/tinder/cardstack/view/CardGridLayout$OnScrollProgressListener;)V", "onScrollProgressListener", "a2", "Landroidx/recyclerview/widget/GridLayoutManager;", "gridLayoutManager", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "b2", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "gridSpacingItemDecoration", "Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter;", "c2", "Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter;", "cardGridViewHolderAdapter", "Lcom/tinder/cardstack/cardgrid/view/ScrollVelocityTracker;", "d2", "Lcom/tinder/cardstack/cardgrid/view/ScrollVelocityTracker;", "velocityTracker", "e2", "I", "itemSpacingPx", "Companion", "OnScrollProgressListener", "a", "b", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCardGridLayout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CardGridLayout.kt\ncom/tinder/cardstack/view/CardGridLayout\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,129:1\n1#2:130\n*E\n"})
/* loaded from: classes7.dex */
public final class CardGridLayout extends BaseCardCollectionLayout {
    private static final int f2 = 2;
    private static final float g2 = 8.0f;
    private static final int h2 = 100;
    private static final int i2 = 10;

    /* renamed from: Z1, reason: from kotlin metadata */
    private OnScrollProgressListener onScrollProgressListener;

    /* renamed from: a2, reason: from kotlin metadata */
    private GridLayoutManager gridLayoutManager;

    /* renamed from: b2, reason: from kotlin metadata */
    private RecyclerView.ItemDecoration gridSpacingItemDecoration;

    /* renamed from: c2, reason: from kotlin metadata */
    private CardGridViewHolderAdapter cardGridViewHolderAdapter;

    /* renamed from: d2, reason: from kotlin metadata */
    private final ScrollVelocityTracker velocityTracker;

    /* renamed from: e2, reason: from kotlin metadata */
    private final int itemSpacingPx;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/tinder/cardstack/view/CardGridLayout$OnScrollProgressListener;", "", "onScrolled", "", "progress", "", "velocity", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface OnScrollProgressListener {
        void onScrolled(float progress, float velocity);
    }

    private final class a extends RecyclerView.OnScrollListener {
        public a() {
        }

        public final boolean a() {
            return ((long) CardGridLayout.h2) < System.currentTimeMillis() - CardGridLayout.this.velocityTracker.getLastUpdateTimestamp();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            if (a()) {
                float scrollProgressPercent = CardGridLayout.this.getScrollProgressPercent();
                CardGridLayout.this.velocityTracker.track(scrollProgressPercent);
                OnScrollProgressListener onScrollProgressListener = CardGridLayout.this.getOnScrollProgressListener();
                if (onScrollProgressListener != null) {
                    onScrollProgressListener.onScrolled(scrollProgressPercent, CardGridLayout.this.velocityTracker.getVelocity());
                }
            }
        }
    }

    private final class b extends GridLayoutManager.SpanSizeLookup {
        public b() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            if (i != CardGridLayout.this.cardGridViewHolderAdapter.findLoadingStatusPosition()) {
                return 1;
            }
            GridLayoutManager gridLayoutManager = CardGridLayout.this.gridLayoutManager;
            if (gridLayoutManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager");
                gridLayoutManager = null;
            }
            return gridLayoutManager.getSpanCount();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CardGridLayout(@NotNull Context ctx) {
        this(ctx, null);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getScrollProgressPercent() {
        return computeVerticalScrollOffset() / computeVerticalScrollRange();
    }

    public static /* synthetic */ void setSpanCount$default(CardGridLayout cardGridLayout, int i, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = 0;
        }
        cardGridLayout.setSpanCount(i, i3);
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout
    protected void checkIfNewInsertsAffectFrozenCards(int startIndex, int numberOfItemsInserted) {
    }

    @Nullable
    public final OnScrollProgressListener getOnScrollProgressListener() {
        return this.onScrollProgressListener;
    }

    public final void hideLoading() {
        this.cardGridViewHolderAdapter.removeLoadingStatus();
    }

    @Override // com.tinder.cardstack.view.CardCollectionLayout, androidx.recyclerview.widget.RecyclerView
    public void setAdapter(@NotNull RecyclerView.Adapter<?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        super.setAdapter(adapter);
        this.cardGridViewHolderAdapter = (CardGridViewHolderAdapter) adapter;
    }

    public final void setLoadingStatusViewHolderFactory(@NotNull LoadingStatusViewHolderFactory factory) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        this.cardGridViewHolderAdapter.setLoadingStatusViewHolderFactory(factory);
    }

    public final void setOnScrollProgressListener(@Nullable OnScrollProgressListener onScrollProgressListener) {
        this.onScrollProgressListener = onScrollProgressListener;
    }

    public final void setSpanCount(int spanCount, int headerOffset) {
        CardGridLayoutManager cardGridLayoutManager = new CardGridLayoutManager(getContext(), spanCount);
        this.gridLayoutManager = cardGridLayoutManager;
        cardGridLayoutManager.setSpanSizeLookup(new b());
        GridLayoutManager gridLayoutManager = this.gridLayoutManager;
        if (gridLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager");
            gridLayoutManager = null;
        }
        setLayoutManager(gridLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = this.gridSpacingItemDecoration;
        if (itemDecoration != null) {
            removeItemDecoration(itemDecoration);
        }
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(spanCount, this.itemSpacingPx, headerOffset);
        this.gridSpacingItemDecoration = gridSpacingItemDecoration;
        addItemDecoration(gridSpacingItemDecoration);
    }

    public final void showLoadingMore() {
        this.cardGridViewHolderAdapter.appendLoadingStatus();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void smoothScrollToPosition(int position) {
        int scrollProgressPercent = (int) (getScrollProgressPercent() * getAdapter().getItemCount());
        int abs = Math.abs(position - scrollProgressPercent);
        int i = i2;
        if (abs > i) {
            scrollToPosition(scrollProgressPercent < position ? position - i : position + i);
        }
        super.smoothScrollToPosition(position);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardGridLayout(@NotNull Context ctx, @Nullable AttributeSet attributeSet) {
        super(ctx, attributeSet);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        this.cardGridViewHolderAdapter = new CardGridViewHolderAdapter();
        this.velocityTracker = new ScrollVelocityTracker();
        setAdapter(this.cardGridViewHolderAdapter);
        this.itemSpacingPx = (int) TypedValue.applyDimension(1, g2, getContext().getResources().getDisplayMetrics());
        setSpanCount$default(this, f2, 0, 2, null);
        addOnScrollListener(new a());
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    @NotNull
    public GridLayoutManager getLayoutManager() {
        GridLayoutManager gridLayoutManager = this.gridLayoutManager;
        if (gridLayoutManager != null) {
            return gridLayoutManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager");
        return null;
    }
}