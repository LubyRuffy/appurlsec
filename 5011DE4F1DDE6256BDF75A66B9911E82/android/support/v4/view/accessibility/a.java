package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;

// compiled from: AccessibilityEventCompatIcs.java
class a {
    public static void appendRecord(AccessibilityEvent r0_AccessibilityEvent, Object r1_Object) {
        r0_AccessibilityEvent.appendRecord((AccessibilityRecord) r1_Object);
    }

    public static Object getRecord(AccessibilityEvent r1_AccessibilityEvent, int r2i) {
        return r1_AccessibilityEvent.getRecord(r2i);
    }

    public static int getRecordCount(AccessibilityEvent r1_AccessibilityEvent) {
        return r1_AccessibilityEvent.getRecordCount();
    }
}