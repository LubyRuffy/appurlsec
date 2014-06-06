package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public class ViewParentCompatICS {
    public static boolean requestSendAccessibilityEvent(ViewParent r1_ViewParent, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return r1_ViewParent.requestSendAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }
}