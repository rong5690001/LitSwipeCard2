package com.rong.litswipecard.cardstack.cardgrid.view;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/CardDragSessionDetector;", "", "<init>", "()V", "", "pointerId", "", "addDragPointer", "(I)V", "removeDragPointer", "Lcom/tinder/cardstack/cardgrid/view/DragSessionListener;", "a", "Lcom/tinder/cardstack/cardgrid/view/DragSessionListener;", "getDragSessionListener", "()Lcom/tinder/cardstack/cardgrid/view/DragSessionListener;", "setDragSessionListener", "(Lcom/tinder/cardstack/cardgrid/view/DragSessionListener;)V", "dragSessionListener", "", "b", "Ljava/util/Set;", "pointerSet", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class CardDragSessionDetector {

    /* renamed from: a, reason: from kotlin metadata */
    private DragSessionListener dragSessionListener;

    /* renamed from: b, reason: from kotlin metadata */
    private final Set pointerSet = new LinkedHashSet();

    public final void addDragPointer(int pointerId) {
        DragSessionListener dragSessionListener;
        if (this.pointerSet.contains(Integer.valueOf(pointerId))) {
            return;
        }
        if (this.pointerSet.isEmpty() && (dragSessionListener = this.dragSessionListener) != null) {
            dragSessionListener.onDragStarted();
        }
        this.pointerSet.add(Integer.valueOf(pointerId));
        Timber.v("Pointer added, size: %d", Integer.valueOf(this.pointerSet.size()));
    }

    @Nullable
    public final DragSessionListener getDragSessionListener() {
        return this.dragSessionListener;
    }

    public final void removeDragPointer(int pointerId) {
        DragSessionListener dragSessionListener;
        boolean remove = this.pointerSet.remove(Integer.valueOf(pointerId));
        if (this.pointerSet.isEmpty() && remove && (dragSessionListener = this.dragSessionListener) != null) {
            dragSessionListener.onDragEnded();
        }
        Timber.v("Pointer removed, size: %d", Integer.valueOf(this.pointerSet.size()));
    }

    public final void setDragSessionListener(@Nullable DragSessionListener dragSessionListener) {
        this.dragSessionListener = dragSessionListener;
    }
}