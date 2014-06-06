package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityRecord;

// compiled from: AccessibilityRecordCompatIcsMr1.java
class p {
    public static int getMaxScrollX(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getMaxScrollX();
    }

    public static int getMaxScrollY(Object r1_Object) {
        return ((AccessibilityRecord) r1_Object).getMaxScrollY();
    }

    public static void setMaxScrollX(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setMaxScrollX(r1i);
    }

    public static void setMaxScrollY(Object r0_Object, int r1i) {
        ((AccessibilityRecord) r0_Object).setMaxScrollY(r1i);
    }
}