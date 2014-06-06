package com.tencent.plus;

import android.content.Context;

// compiled from: ProGuard
public class DensityUtil {
    public static int dip2px(Context r2_Context, float r3f) {
        return (int) (r2_Context.getResources().getDisplayMetrics().density * r3f + 0.5f);
    }

    public static int px2dip(Context r2_Context, float r3f) {
        return (int) (r3f / r2_Context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int px2sp(Context r2_Context, float r3f) {
        return (int) (r3f / r2_Context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static int sp2px(Context r2_Context, float r3f) {
        return (int) (r2_Context.getResources().getDisplayMetrics().scaledDensity * r3f + 0.5f);
    }
}