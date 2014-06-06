package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// compiled from: AccessibilityNodeInfoCompatJellyBean.java
class f {
    public static void addChild(Object r0_Object, View r1_View, int r2i) {
        ((AccessibilityNodeInfo) r0_Object).addChild(r1_View, r2i);
    }

    public static Object findFocus(Object r1_Object, int r2i) {
        return ((AccessibilityNodeInfo) r1_Object).findFocus(r2i);
    }

    public static Object focusSearch(Object r1_Object, int r2i) {
        return ((AccessibilityNodeInfo) r1_Object).focusSearch(r2i);
    }

    public static int getMovementGranularities(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).getMovementGranularities();
    }

    public static boolean isAccessibilityFocused(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isAccessibilityFocused();
    }

    public static boolean isVisibleToUser(Object r1_Object) {
        return ((AccessibilityNodeInfo) r1_Object).isVisibleToUser();
    }

    public static Object obtain(View r1_View, int r2i) {
        return AccessibilityNodeInfo.obtain(r1_View, r2i);
    }

    public static boolean performAction(Object r1_Object, int r2i, Bundle r3_Bundle) {
        return ((AccessibilityNodeInfo) r1_Object).performAction(r2i, r3_Bundle);
    }

    public static void setAccesibilityFocused(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setAccessibilityFocused(r1z);
    }

    public static void setMovementGranularities(Object r0_Object, int r1i) {
        ((AccessibilityNodeInfo) r0_Object).setMovementGranularities(r1i);
    }

    public static void setParent(Object r0_Object, View r1_View, int r2i) {
        ((AccessibilityNodeInfo) r0_Object).setParent(r1_View, r2i);
    }

    public static void setSource(Object r0_Object, View r1_View, int r2i) {
        ((AccessibilityNodeInfo) r0_Object).setSource(r1_View, r2i);
    }

    public static void setVisibleToUser(Object r0_Object, boolean r1z) {
        ((AccessibilityNodeInfo) r0_Object).setVisibleToUser(r1z);
    }
}