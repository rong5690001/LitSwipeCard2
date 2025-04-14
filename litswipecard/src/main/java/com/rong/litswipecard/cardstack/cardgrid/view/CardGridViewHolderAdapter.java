package com.rong.litswipecard.cardstack.cardgrid.view;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tinder.cardstack.view.CardViewAdapter;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001:\u0001)B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0006¢\u0006\u0004\b\u001b\u0010\u0003J\r\u0010\u001c\u001a\u00020\u0006¢\u0006\u0004\b\u001c\u0010\u0003J\r\u0010\u001d\u001a\u00020\u000e¢\u0006\u0004\b\u001d\u0010\u0014R$\u0010%\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0016\u0010(\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'¨\u0006*"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter;", "Lcom/tinder/cardstack/view/CardViewAdapter;", "<init>", "()V", "Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter$State;", "newState", "", "b", "(Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter$State;)V", "", "a", "()Z", "Landroid/view/ViewGroup;", "parent", "", "viewType", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onCreateViewHolder", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getItemCount", "()I", "position", "getItemViewType", "(I)I", "holder", "onBindViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "appendLoadingStatus", "removeLoadingStatus", "findLoadingStatusPosition", "Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;", "c", "Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;", "getLoadingStatusViewHolderFactory", "()Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;", "setLoadingStatusViewHolderFactory", "(Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;)V", "loadingStatusViewHolderFactory", "d", "Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter$State;", "state", "State", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public class CardGridViewHolderAdapter extends CardViewAdapter {

    /* renamed from: c, reason: from kotlin metadata */
    private LoadingStatusViewHolderFactory loadingStatusViewHolderFactory;

    /* renamed from: d, reason: from kotlin metadata */
    private State state = State.NO_MORE_REC;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter$State;", "", "(Ljava/lang/String;I)V", "HAS_MORE_REC", "NO_MORE_REC", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private enum State {
        HAS_MORE_REC,
        NO_MORE_REC
    }

    private final boolean a() {
        return this.state == State.HAS_MORE_REC;
    }

    private final void b(State newState) {
        State state = this.state;
        if (state == newState) {
            return;
        }
        int findLoadingStatusPosition = findLoadingStatusPosition();
        this.state = newState;
        Pair pair = TuplesKt.to(state, newState);
        State state2 = State.NO_MORE_REC;
        State state3 = State.HAS_MORE_REC;
        if (Intrinsics.areEqual(pair, TuplesKt.to(state2, state3))) {
            notifyItemInserted(findLoadingStatusPosition());
        } else if (Intrinsics.areEqual(pair, TuplesKt.to(state3, state2))) {
            notifyItemRemoved(findLoadingStatusPosition);
        }
    }

    public final void appendLoadingStatus() {
        b(State.HAS_MORE_REC);
    }

    public final int findLoadingStatusPosition() {
        int itemCount = super.getItemCount();
        if (a()) {
            return itemCount;
        }
        return -1;
    }

    @Override // com.tinder.cardstack.view.CardViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int itemCount = super.getItemCount();
        return !a() ? itemCount : itemCount + 1;
    }

    @Override // com.tinder.cardstack.view.CardViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        LoadingStatusViewHolderFactory loadingStatusViewHolderFactory = this.loadingStatusViewHolderFactory;
        if (loadingStatusViewHolderFactory != null) {
            return position == findLoadingStatusPosition() ? loadingStatusViewHolderFactory.getViewType() : super.getItemViewType(position);
        }
        throw new IllegalStateException("Loading Status Factory is not set");
    }

    @Nullable
    public final LoadingStatusViewHolderFactory getLoadingStatusViewHolderFactory() {
        return this.loadingStatusViewHolderFactory;
    }

    @Override // com.tinder.cardstack.view.CardViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (position != findLoadingStatusPosition()) {
            super.onBindViewHolder(holder, position);
        }
    }

    @Override // com.tinder.cardstack.view.CardViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LoadingStatusViewHolderFactory loadingStatusViewHolderFactory = this.loadingStatusViewHolderFactory;
        if (loadingStatusViewHolderFactory == null) {
            throw new IllegalStateException("Loading Status Factory is not set");
        }
        int viewType2 = loadingStatusViewHolderFactory.getViewType();
        if (viewType == viewType2) {
            return loadingStatusViewHolderFactory.createViewHolder(parent, viewType2);
        }
        RecyclerView.ViewHolder onCreateViewHolder = super.onCreateViewHolder(parent, viewType);
        Intrinsics.checkNotNullExpressionValue(onCreateViewHolder, "super.onCreateViewHolder(parent, viewType)");
        return onCreateViewHolder;
    }

    public final void removeLoadingStatus() {
        b(State.NO_MORE_REC);
    }

    public final void setLoadingStatusViewHolderFactory(@Nullable LoadingStatusViewHolderFactory loadingStatusViewHolderFactory) {
        this.loadingStatusViewHolderFactory = loadingStatusViewHolderFactory;
    }
}