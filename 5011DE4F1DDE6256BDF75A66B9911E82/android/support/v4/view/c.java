package android.support.v4.view;

import android.support.v4.view.AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// compiled from: AccessibilityDelegateCompatIcs.java
final class c extends AccessibilityDelegate {
    final /* synthetic */ AccessibilityDelegateBridge a;

    c(AccessibilityDelegateBridge r1_AccessibilityDelegateBridge) {
        this.a = r1_AccessibilityDelegateBridge;
    }

    public boolean dispatchPopulateAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return this.a.dispatchPopulateAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }

    public void onInitializeAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        this.a.onInitializeAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View r2_View, AccessibilityNodeInfo r3_AccessibilityNodeInfo) {
        this.a.onInitializeAccessibilityNodeInfo(r2_View, r3_AccessibilityNodeInfo);
    }

    public void onPopulateAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        this.a.onPopulateAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup r2_ViewGroup, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
        return this.a.onRequestSendAccessibilityEvent(r2_ViewGroup, r3_View, r4_AccessibilityEvent);
    }

    public void sendAccessibilityEvent(View r2_View, int r3i) {
        this.a.sendAccessibilityEvent(r2_View, r3i);
    }

    public void sendAccessibilityEventUnchecked(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        this.a.sendAccessibilityEventUnchecked(r2_View, r3_AccessibilityEvent);
    }
}