package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

// compiled from: AccessibilityDelegateCompatJellyBean.java
final class d extends AccessibilityDelegate {
    final /* synthetic */ AccessibilityDelegateBridgeJellyBean a;

    d(AccessibilityDelegateBridgeJellyBean r1_AccessibilityDelegateBridgeJellyBean) {
        this.a = r1_AccessibilityDelegateBridgeJellyBean;
    }

    public boolean dispatchPopulateAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return this.a.dispatchPopulateAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider(View r2_View) {
        return (AccessibilityNodeProvider) this.a.getAccessibilityNodeProvider(r2_View);
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

    public boolean performAccessibilityAction(View r2_View, int r3i, Bundle r4_Bundle) {
        return this.a.performAccessibilityAction(r2_View, r3i, r4_Bundle);
    }

    public void sendAccessibilityEvent(View r2_View, int r3i) {
        this.a.sendAccessibilityEvent(r2_View, r3i);
    }

    public void sendAccessibilityEventUnchecked(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        this.a.sendAccessibilityEventUnchecked(r2_View, r3_AccessibilityEvent);
    }
}