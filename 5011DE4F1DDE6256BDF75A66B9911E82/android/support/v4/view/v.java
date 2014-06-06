package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;

// compiled from: ViewCompatJB.java
class v {
    public static Object getAccessibilityNodeProvider(View r1_View) {
        return r1_View.getAccessibilityNodeProvider();
    }

    public static int getImportantForAccessibility(View r1_View) {
        return r1_View.getImportantForAccessibility();
    }

    public static ViewParent getParentForAccessibility(View r1_View) {
        return r1_View.getParentForAccessibility();
    }

    public static boolean hasTransientState(View r1_View) {
        return r1_View.hasTransientState();
    }

    public static boolean performAccessibilityAction(View r1_View, int r2i, Bundle r3_Bundle) {
        return r1_View.performAccessibilityAction(r2i, r3_Bundle);
    }

    public static void postInvalidateOnAnimation(View r0_View) {
        r0_View.postInvalidateOnAnimation();
    }

    public static void postInvalidateOnAnimation(View r0_View, int r1i, int r2i, int r3i, int r4i) {
        r0_View.postInvalidate(r1i, r2i, r3i, r4i);
    }

    public static void postOnAnimation(View r0_View, Runnable r1_Runnable) {
        r0_View.postOnAnimation(r1_Runnable);
    }

    public static void postOnAnimationDelayed(View r0_View, Runnable r1_Runnable, long r2j) {
        r0_View.postOnAnimationDelayed(r1_Runnable, r2j);
    }

    public static void setHasTransientState(View r0_View, boolean r1z) {
        r0_View.setHasTransientState(r1z);
    }

    public static void setImportantForAccessibility(View r0_View, int r1i) {
        r0_View.setImportantForAccessibility(r1i);
    }
}