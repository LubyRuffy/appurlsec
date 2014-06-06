package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

class AccessibilityDelegateCompatJellyBean {

    public static interface AccessibilityDelegateBridgeJellyBean {
        public boolean dispatchPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public Object getAccessibilityNodeProvider(View r1_View);

        public void onInitializeAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public void onInitializeAccessibilityNodeInfo(View r1_View, Object r2_Object);

        public void onPopulateAccessibilityEvent(View r1_View, AccessibilityEvent r2_AccessibilityEvent);

        public boolean onRequestSendAccessibilityEvent(ViewGroup r1_ViewGroup, View r2_View, AccessibilityEvent r3_AccessibilityEvent);

        public boolean performAccessibilityAction(View r1_View, int r2i, Bundle r3_Bundle);

        public void sendAccessibilityEvent(View r1_View, int r2i);

        public void sendAccessibilityEventUnchecked(View r1_View, AccessibilityEvent r2_AccessibilityEvent);
    }

    public static Object getAccessibilityNodeProvider(Object r1_Object, View r2_View) {
        return ((AccessibilityDelegate) r1_Object).getAccessibilityNodeProvider(r2_View);
    }

    public static Object newAccessibilityDelegateBridge(AccessibilityDelegateBridgeJellyBean r1_AccessibilityDelegateBridgeJellyBean) {
        return new d(r1_AccessibilityDelegateBridgeJellyBean);
    }

    public static boolean performAccessibilityAction(Object r1_Object, View r2_View, int r3i, Bundle r4_Bundle) {
        return ((AccessibilityDelegate) r1_Object).performAccessibilityAction(r2_View, r3i, r4_Bundle);
    }
}