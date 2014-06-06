package android.support.v4.view;

import android.support.v4.view.AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

// compiled from: AccessibilityDelegateCompat.java
class a implements AccessibilityDelegateBridge {
    final /* synthetic */ AccessibilityDelegateCompat a;
    final /* synthetic */ a b;

    a(a r1_a, AccessibilityDelegateCompat r2_AccessibilityDelegateCompat) {
        this.b = r1_a;
        this.a = r2_AccessibilityDelegateCompat;
    }

    public boolean dispatchPopulateAccessibilityEvent(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return this.a.dispatchPopulateAccessibilityEvent(r2_View, r3_AccessibilityEvent);
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

    public void sendAccessibilityEvent(View r2_View, int r3i) {
        this.a.sendAccessibilityEvent(r2_View, r3i);
    }

    public void sendAccessibilityEventUnchecked(View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        this.a.sendAccessibilityEventUnchecked(r2_View, r3_AccessibilityEvent);
    }
}