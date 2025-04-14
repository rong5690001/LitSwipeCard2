package com.rong.litswipecard.cardstack.view;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.rong.litswipecard.cardstack.model.SwipeDirection;

/* loaded from: classes7.dex */
public interface CardDecorationListener {
    void onDecorationDraw(@NonNull Canvas canvas, @NonNull View view, @NonNull ViewGroup viewGroup, float f, float f2, float f3, @NonNull SwipeDirection swipeDirection, boolean z, boolean z2);

    void onDecorationDrawOver(@NonNull Canvas canvas, @NonNull View view, @NonNull ViewGroup viewGroup, float f, float f2, float f3, @NonNull SwipeDirection swipeDirection, boolean z, boolean z2);
}