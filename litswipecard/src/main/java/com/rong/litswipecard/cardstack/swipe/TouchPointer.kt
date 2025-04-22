package com.rong.litswipecard.cardstack.swipe;

import android.graphics.PointF;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes7.dex */
public class TouchPointer {
    private PointF a;
    private float b;
    private float c;
    private RecyclerView.ViewHolder d;
    private int e;
    private float f;
    private float g;
    private boolean h;
    final DragConstraints i;

    public TouchPointer(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull PointF pointF, float f, float f2, int i, DragConstraints dragConstraints) {
        this.d = viewHolder;
        this.b = f;
        this.c = f2;
        this.e = i;
        this.a = pointF;
        this.i = dragConstraints;
    }

    int a() {
        return this.e;
    }

    float b() {
        return this.f;
    }

    float c() {
        return this.g;
    }

    float d() {
        return this.b;
    }

    float e() {
        return this.c;
    }

    boolean f() {
        return this.h;
    }

    void g(float f) {
        this.f = f;
    }

    @NonNull
    public PointF getFirstTouchPoint() {
        return this.a;
    }

    @NonNull
    public RecyclerView.ViewHolder getViewHolder() {
        RecyclerView.ViewHolder viewHolder = this.d;
        if (viewHolder != null) {
            return viewHolder;
        }
        throw new IllegalStateException("Check implementation: null viewholder");
    }

    void h(float f) {
        this.g = f;
    }

    void i(boolean z) {
        this.h = z;
    }

    void j(float f, float f2) {
        this.a = new PointF(f, f2);
        this.b = f;
        this.c = f2;
    }

    public String toString() {
        return "[sx=" + this.b + "::sy=" + this.c + "::dx=" + this.f + "::dy=" + this.g + "::apid=" + this.e + "::vh=" + this.d + "]";
    }

    TouchPointer(RecyclerView.ViewHolder viewHolder, MotionEvent motionEvent, DragConstraints dragConstraints) {
        this.e = -1;
        this.d = viewHolder;
        this.b = motionEvent.getX();
        this.c = motionEvent.getY();
        this.e = motionEvent.getPointerId(0);
        this.a = new PointF(this.b, this.c);
        this.i = dragConstraints;
    }
}