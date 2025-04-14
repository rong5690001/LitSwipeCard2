package com.rong.litswipecard.cardstack.cardgrid.selection;

import com.tinder.cardstack.cardgrid.model.Point;
import com.tinder.cardstack.cardgrid.selection.model.CardViewHolderSelection;
import com.tinder.cardstack.cardgrid.swipe.model.Pointer;
import com.tinder.cardstack.view.CardViewHolder;
import com.tinder.pushnotifications.model.SelectNotification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR0\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000ej\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b`\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0017\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00138F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector;", "", "Lcom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector$CardViewHolderFinder;", "cardViewHolderFinder", "<init>", "(Lcom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector$CardViewHolderFinder;)V", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "pointer", "Lcom/tinder/cardstack/cardgrid/selection/model/CardViewHolderSelection;", SelectNotification.TYPE_NAME, "(Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;)Lcom/tinder/cardstack/cardgrid/selection/model/CardViewHolderSelection;", "unselect", "a", "Lcom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector$CardViewHolderFinder;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "b", "Ljava/util/HashMap;", "selectionMap", "", "Lcom/tinder/cardstack/view/CardViewHolder;", "getSelectedCardViewHolders", "()Ljava/util/Set;", "selectedCardViewHolders", "CardViewHolderFinder", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCardViewHolderSelector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CardViewHolderSelector.kt\ncom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,57:1\n1549#2:58\n1620#2,3:59\n1#3:62\n*S KotlinDebug\n*F\n+ 1 CardViewHolderSelector.kt\ncom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector\n*L\n17#1:58\n17#1:59,3\n*E\n"})
/* loaded from: classes7.dex */
public final class CardViewHolderSelector {

    /* renamed from: a, reason: from kotlin metadata */
    private final CardViewHolderFinder cardViewHolderFinder;

    /* renamed from: b, reason: from kotlin metadata */
    private final HashMap selectionMap;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/tinder/cardstack/cardgrid/selection/CardViewHolderSelector$CardViewHolderFinder;", "", "findCardViewHolder", "Lcom/tinder/cardstack/view/CardViewHolder;", "position", "Lcom/tinder/cardstack/cardgrid/model/Point;", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface CardViewHolderFinder {
        @Nullable
        CardViewHolder<?> findCardViewHolder(@NotNull Point position);
    }

    public CardViewHolderSelector(@NotNull CardViewHolderFinder cardViewHolderFinder) {
        Intrinsics.checkNotNullParameter(cardViewHolderFinder, "cardViewHolderFinder");
        this.cardViewHolderFinder = cardViewHolderFinder;
        this.selectionMap = new HashMap();
    }

    @NotNull
    public final Set<CardViewHolder<?>> getSelectedCardViewHolders() {
        Collection values = this.selectionMap.values();
        Intrinsics.checkNotNullExpressionValue(values, "selectionMap.values");
        Collection collection = values;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            arrayList.add(((CardViewHolderSelection) it2.next()).getCardViewHolder());
        }
        return CollectionsKt.toSet(arrayList);
    }

    @Nullable
    public final CardViewHolderSelection select(@NotNull Pointer pointer) {
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        if (this.selectionMap.containsKey(pointer)) {
            Object obj = this.selectionMap.get(pointer);
            Intrinsics.checkNotNull(obj);
            return (CardViewHolderSelection) obj;
        }
        CardViewHolder<?> findCardViewHolder = this.cardViewHolderFinder.findCardViewHolder(pointer.getOrigin());
        Object obj2 = null;
        if (findCardViewHolder == null || !findCardViewHolder.isSwipable()) {
            return null;
        }
        Collection values = this.selectionMap.values();
        Intrinsics.checkNotNullExpressionValue(values, "selectionMap.values");
        Iterator it2 = values.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (Intrinsics.areEqual(((CardViewHolderSelection) next).getCardViewHolder(), findCardViewHolder)) {
                obj2 = next;
                break;
            }
        }
        CardViewHolderSelection cardViewHolderSelection = (CardViewHolderSelection) obj2;
        if (cardViewHolderSelection != null) {
            cardViewHolderSelection.addPointer$cardstack_release(pointer);
            this.selectionMap.put(pointer, cardViewHolderSelection);
            return cardViewHolderSelection;
        }
        CardViewHolderSelection cardViewHolderSelection2 = new CardViewHolderSelection(findCardViewHolder, pointer.getOrigin().minus(new Point(findCardViewHolder.itemView.getX(), findCardViewHolder.itemView.getY())));
        cardViewHolderSelection2.addPointer$cardstack_release(pointer);
        this.selectionMap.put(pointer, cardViewHolderSelection2);
        return cardViewHolderSelection2;
    }

    @Nullable
    public final CardViewHolderSelection unselect(@NotNull Pointer pointer) {
        Intrinsics.checkNotNullParameter(pointer, "pointer");
        CardViewHolderSelection cardViewHolderSelection = (CardViewHolderSelection) this.selectionMap.get(pointer);
        if (cardViewHolderSelection == null) {
            return null;
        }
        cardViewHolderSelection.removePointer$cardstack_release(pointer);
        this.selectionMap.remove(pointer);
        return cardViewHolderSelection;
    }
}