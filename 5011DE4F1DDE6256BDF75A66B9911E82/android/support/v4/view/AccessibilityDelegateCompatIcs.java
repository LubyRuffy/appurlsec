package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityDelegateCompatIcs {

    public static interface AccessibilityDelegateBridge {
        public boolean dispatchPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public void onInitializeAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public void onInitializeAccessibilityNodeInfo(View r1_View, Object r2_Object);

        public void onPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public boolean onRequestSendAccessibilityEvent(ViewGroup r1_ViewGroup, View r2_View, AccessibilityEvent r3_AccessibilityEvent);

        public void sendAccessibilityEvent(View r1_View, int r2i);

        public void sendAccessibilityEventUnchecked(View r1_View, AccessibilityEvent r2_AccessibilityEvent);
    }

    public static boolean dispatchPopulateAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return ((AccessibilityDelegate) r1_Object).dispatchPopulateAccessibilityEvent(r2_View, r3_AccessibilityEvent);
    }

    public static Object newAccessibilityDelegateBridge(AccessibilityDelegateBridge r1_AccessibilityDelegateBridge) {
        return new c(r1_AccessibilityDelegateBridge);
    }

    public static Object newAccessibilityDelegateDefaultImpl() {
        return new AccessibilityDelegate();
    }

    public static void onInitializeAccessibilityEvent(Object r0_Object, View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
        ((AccessibilityDelegate) r0_Object).onInitializeAccessibilityEvent(r1_View, r2_AccessibilityEvent);
    }

    public static void onInitializeAccessibilityNodeInfo(Object r0_Object, View r1_View, Object r2_Object) {
        ((AccessibilityDelegate) r0_Object).onInitializeAccessibilityNodeInfo(r1_View, (AccessibilityNodeInfo) r2_Object);
    }

    public static void onPopulateAccessibilityEvent(Object r0_Object, View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
        ((AccessibilityDelegate) r0_Object).onPopulateAccessibilityEvent(r1_View, r2_AccessibilityEvent);
    }

    public static boolean onRequestSendAccessibilityEvent(Object r1_Object, ViewGroup r2_ViewGroup, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
        return ((AccessibilityDelegate) r1_Object).onRequestSendAccessibilityEvent(r2_ViewGroup, r3_View, r4_AccessibilityEvent);
    }

    public static void sendAccessibilityEvent(Object r0_Object, View r1_View, int r2i) {
        ((AccessibilityDelegate) r0_Object).sendAccessibilityEvent(r1_View, r2i);
    }

    public static void sendAccessibilityEventUnchecked(Object r0_Object, View r1_View, AccessibilityEvent r2_AccessibilityEvent) {
        ((AccessibilityDelegate) r0_Object).sendAccessibilityEventUnchecked(r1_View, r2_AccessibilityEvent);
    }
}