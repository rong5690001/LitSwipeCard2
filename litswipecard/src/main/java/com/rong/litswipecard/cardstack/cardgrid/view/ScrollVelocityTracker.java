package com.rong.litswipecard.cardstack.cardgrid.view;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/tinder/cardstack/cardgrid/view/ScrollVelocityTracker;", "", "<init>", "()V", "", "a", "()F", "getVelocity", "", "getLastUpdateTimestamp", "()J", "scrollOffset", "", "track", "(F)V", "F", "velocity", "Lcom/tinder/cardstack/cardgrid/view/ScrollVelocityTracker$a;", "b", "Lcom/tinder/cardstack/cardgrid/view/ScrollVelocityTracker$a;", "scrollOffsetUpdate", "c", "previousScrollOffsetUpdate", "cardstack_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ScrollVelocityTracker {

    /* renamed from: a, reason: from kotlin metadata */
    private float velocity;

    /* renamed from: b, reason: from kotlin metadata */
    private final a scrollOffsetUpdate = new a(0.0f, 0, 3, null);

    /* renamed from: c, reason: from kotlin metadata */
    private final a previousScrollOffsetUpdate = new a(0.0f, 0, 3, null);

    private static final class a {
        private float a;
        private long b;

        public a(float f, long j) {
            this.a = f;
            this.b = j;
        }

        public final float a() {
            return this.a;
        }

        public final long b() {
            return this.b;
        }

        public final void c(float f) {
            this.a = f;
        }

        public final void d(long j) {
            this.b = j;
        }

        public /* synthetic */ a(float f, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0.0f : f, (i & 2) != 0 ? 0L : j);
        }
    }

    private final float a() {
        float a2 = this.scrollOffsetUpdate.a() - this.previousScrollOffsetUpdate.a();
        float b = (this.scrollOffsetUpdate.b() - this.previousScrollOffsetUpdate.b()) / 1000.0f;
        if (b == 0.0f) {
            return 0.0f;
        }
        return a2 / b;
    }

    public final long getLastUpdateTimestamp() {
        return this.scrollOffsetUpdate.b();
    }

    public final float getVelocity() {
        return this.velocity;
    }

    public final void track(float scrollOffset) {
        this.previousScrollOffsetUpdate.c(this.scrollOffsetUpdate.a());
        this.previousScrollOffsetUpdate.d(this.scrollOffsetUpdate.b());
        this.scrollOffsetUpdate.c(scrollOffset);
        this.scrollOffsetUpdate.d(System.currentTimeMillis());
        this.velocity = a();
    }
}