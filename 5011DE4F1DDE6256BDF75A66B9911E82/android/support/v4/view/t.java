package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

// compiled from: ViewCompatHC.java
class t {
    static long a() {
        return ValueAnimator.getFrameDelay();
    }

    public static float getAlpha(View r1_View) {
        return r1_View.getAlpha();
    }

    public static int getLayerType(View r1_View) {
        return r1_View.getLayerType();
    }

    public static int getMeasuredHeightAndState(View r1_View) {
        return r1_View.getMeasuredHeightAndState();
    }

    public static int getMeasuredState(View r1_View) {
        return r1_View.getMeasuredState();
    }

    public static int getMeasuredWidthAndState(View r1_View) {
        return r1_View.getMeasuredWidthAndState();
    }

    public static int resolveSizeAndState(int r1i, int r2i, int r3i) {
        return View.resolveSizeAndState(r1i, r2i, r3i);
    }

    public static void setLayerType(View r0_View, int r1i, Paint r2_Paint) {
        r0_View.setLayerType(r1i, r2_Paint);
    }
}