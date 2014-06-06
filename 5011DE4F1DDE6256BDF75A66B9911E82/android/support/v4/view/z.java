package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

// compiled from: ViewGroupCompatIcs.java
class z {
    public static boolean onRequestSendAccessibilityEvent(ViewGroup r1_ViewGroup, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return r1_ViewGroup.onRequestSendAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }
}