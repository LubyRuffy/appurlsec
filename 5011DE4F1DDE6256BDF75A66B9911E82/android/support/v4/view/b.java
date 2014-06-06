package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

// compiled from: AccessibilityDelegateCompat.java
class b implements AccessibilityDelegateBridgeJellyBean {
    final /* synthetic */ AccessibilityDelegateCompat a;
    final /* synthetic */ c b;

    b(c r1_c, AccessibilityDelegateCompat r2_AccessibilityDelegateCompat) {
        this.b = r1_c;
        this.a = r2_AccessibilityDelegateCompat;
    }

    public boolean dispatchPopulateAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return this.a.dispatchPopulateAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }

    public Object getAccessibilityNodeProvider(View r2_View) {
        AccessibilityNodeProviderCompat r0_AccessibilityNodeProviderCompat = this.a.getAccessibilityNodeProvider(r2_View);
        return r0_AccessibilityNodeProviderCompat != null ? r0_AccessibilityNodeProviderCompat.getProvider() : null;
    }

    public void onInitializeAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        this.a.onInitializeAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View r3_View, Object r4_Object) {
        this.a.onInitializeAccessibilityNodeInfo(r3_View, new AccessibilityNodeInfoCompat(r4_Object));
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