package com.rong.litswipecard.cardstack.utils;

import android.content.res.Resources;

/* loaded from: classes7.dex */
public class ViewHelper {
    public static final float HORIZONTAL_SWIPE_DISTANCE = getScreenWidth() * 1.4f;
    public static final float VERTICAL_SWIPE_DISTANCE = getScreenHeight();

    public static float getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static float getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}