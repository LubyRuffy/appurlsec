package android.support.v4.view;

import android.graphics.Rect;
import android.view.Gravity;

// compiled from: GravityCompatJellybeanMr1.java
class e {
    public static void apply(int r0i, int r1i, int r2i, Rect r3_Rect, int r4i, int r5i, Rect r6_Rect, int r7i) {
        Gravity.apply(r0i, r1i, r2i, r3_Rect, r4i, r5i, r6_Rect, r7i);
    }

    public static void apply(int r0i, int r1i, int r2i, Rect r3_Rect, Rect r4_Rect, int r5i) {
        Gravity.apply(r0i, r1i, r2i, r3_Rect, r4_Rect, r5i);
    }

    public static void applyDisplay(int r0i, Rect r1_Rect, Rect r2_Rect, int r3i) {
        Gravity.applyDisplay(r0i, r1_Rect, r2_Rect, r3i);
    }

    public static int getAbsoluteGravity(int r1i, int r2i) {
        return Gravity.getAbsoluteGravity(r1i, r2i);
    }
}