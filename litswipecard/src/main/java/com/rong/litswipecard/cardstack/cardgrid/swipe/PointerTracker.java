package com.rong.litswipecard.cardstack.cardgrid.swipe;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.tinder.cardstack.cardgrid.model.Point;
import com.tinder.cardstack.cardgrid.swipe.model.Pointer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ!\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\f\u0010\nJ\u0019\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0016\u0010\u0013J\u000f\u0010\u0019\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0018\u0010\u0003R$\u0010 \u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR0\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0!j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b`\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010#R\u0018\u0010'\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010&R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0(8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006."}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker;", "", "<init>", "()V", "", "pointerId", "Lcom/tinder/cardstack/cardgrid/model/Point;", "origin", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "a", "(ILcom/tinder/cardstack/cardgrid/model/Point;)Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "position", "c", "d", "(I)Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "Landroid/view/MotionEvent;", "event", "", "e", "(Landroid/view/MotionEvent;)V", "b", "(I)Lcom/tinder/cardstack/cardgrid/model/Point;", "track$cardstack_release", "track", "release$cardstack_release", "release", "Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker$Listener;", "Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker$Listener;", "getListener$cardstack_release", "()Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker$Listener;", "setListener$cardstack_release", "(Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker$Listener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "Ljava/util/HashMap;", "pointerMap", "Landroid/view/VelocityTracker;", "Landroid/view/VelocityTracker;", "velocityTracker", "", "getPointers$cardstack_release", "()Ljava/util/Set;", "pointers", "Companion", "Listener", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class PointerTracker {
    private static final int d = 1000;

    /* renamed from: a, reason: from kotlin metadata */
    private Listener listener;

    /* renamed from: b, reason: from kotlin metadata */
    private final HashMap pointerMap = new HashMap();

    /* renamed from: c, reason: from kotlin metadata */
    private VelocityTracker velocityTracker;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/tinder/cardstack/cardgrid/swipe/PointerTracker$Listener;", "", "onPointerDown", "", "pointer", "Lcom/tinder/cardstack/cardgrid/swipe/model/Pointer;", "onPointerMove", "onPointerUp", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Listener {
        void onPointerDown(@NotNull Pointer pointer);

        void onPointerMove(@NotNull Pointer pointer);

        void onPointerUp(@NotNull Pointer pointer);
    }

    private final Pointer a(int pointerId, Point origin) {
        if (this.pointerMap.containsKey(Integer.valueOf(pointerId))) {
            throw new IllegalStateException(("Cannot add Pointer Id: " + pointerId + " has already been tracked").toString());
        }
        Pointer pointer = new Pointer(pointerId, origin, null, null, 12, null);
        this.pointerMap.put(Integer.valueOf(pointerId), pointer);
        return pointer;
    }

    private final Point b(int pointerId) {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return new Point(0.0f, 0.0f);
        }
        velocityTracker.computeCurrentVelocity(d);
        return new Point(velocityTracker.getXVelocity(pointerId), velocityTracker.getYVelocity(pointerId));
    }

    private final Pointer c(int pointerId, Point position) {
        Pointer pointer = (Pointer) this.pointerMap.get(Integer.valueOf(pointerId));
        if (pointer != null) {
            Pointer copy$default = Pointer.copy$default(pointer, 0, null, position.minus(pointer.getOrigin()), null, 11, null);
            this.pointerMap.put(Integer.valueOf(pointerId), copy$default);
            return copy$default;
        }
        Timber.e(new IllegalStateException("Cannot move Pointer Id: " + pointerId + " is not tracked"));
        return null;
    }

    private final Pointer d(int pointerId) {
        Pointer pointer = (Pointer) this.pointerMap.remove(Integer.valueOf(pointerId));
        if (pointer != null) {
            return Pointer.copy$default(pointer, 0, null, null, b(pointerId), 7, null);
        }
        Timber.e(new IllegalStateException("Cannot remove Pointer Id: " + pointerId + " is not tracked"));
        return null;
    }

    private final void e(MotionEvent event) {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.addMovement(event);
        }
    }

    @Nullable
    /* renamed from: getListener$cardstack_release, reason: from getter */
    public final Listener getListener() {
        return this.listener;
    }

    @NotNull
    public final Set<Pointer> getPointers$cardstack_release() {
        Collection values = this.pointerMap.values();
        Intrinsics.checkNotNullExpressionValue(values, "pointerMap.values");
        return CollectionsKt.toSet(values);
    }

    public final void release$cardstack_release() {
        this.pointerMap.clear();
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.velocityTracker = null;
    }

    public final void setListener$cardstack_release(@Nullable Listener listener) {
        this.listener = listener;
    }

    public final void track$cardstack_release(@NotNull MotionEvent event) {
        Listener listener;
        Listener listener2;
        Intrinsics.checkNotNullParameter(event, "event");
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            release$cardstack_release();
        }
        e(event);
        int actionIndex = event.getActionIndex();
        int pointerId = event.getPointerId(actionIndex);
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int pointerCount = event.getPointerCount();
                    for (int i = 0; i < pointerCount; i++) {
                        Pointer c = c(event.getPointerId(i), new Point(event.getX(i), event.getY(i)));
                        if (c != null && (listener2 = this.listener) != null) {
                            listener2.onPointerMove(c);
                        }
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 5) {
                        if (actionMasked != 6) {
                            return;
                        }
                    }
                }
            }
            Pointer d2 = d(pointerId);
            if (d2 == null || (listener = this.listener) == null) {
                return;
            }
            listener.onPointerUp(d2);
            return;
        }
        Pointer a = a(pointerId, new Point(event.getX(actionIndex), event.getY(actionIndex)));
        Listener listener3 = this.listener;
        if (listener3 != null) {
            listener3.onPointerDown(a);
        }
    }
}