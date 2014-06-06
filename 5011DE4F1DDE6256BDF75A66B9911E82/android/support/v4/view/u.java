package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// compiled from: ViewCompatICS.java
class u {
    public static boolean canScrollHorizontally(View r1_View, int r2i) {
        return r1_View.canScrollHorizontally(r2i);
    }

    public static boolean canScrollVertically(View r1_View, int r2i) {
        return r1_View.canScrollVertically(r2i);
    }

    public static void onInitializeAccessibilityEvent(View r0_View, AccessibilityEvent r1_AccessibilityEvent) {
        r0_View.onInitializeAccessibilityEvent(r1_AccessibilityEvent);
    }

    public static void onInitializeAccessibilityNodeInfo(View r0_View, Object r1_Object) {
        r0_View.onInitializeAccessibilityNodeInfo((AccessibilityNodeInfo) r1_Object);
    }

    public static void onPopulateAccessibilityEvent(View r0_View, AccessibilityEvent r1_AccessibilityEvent) {
        r0_View.onPopulateAccessibilityEvent(r1_AccessibilityEvent);
    }

    public static void setAccessibilityDelegate(View r0_View, Object r1_Object) {
        r0_View.setAccessibilityDelegate((AccessibilityDelegate) r1_Object);
    }
}