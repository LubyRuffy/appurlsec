package android.support.v4.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityDelegateCompat {
    private static final b b;
    private static final Object c;
    final Object a;

    static interface b {
        public boolean dispatchPopulateAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent);

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object r1_Object, View r2_View);

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat r1_AccessibilityDelegateCompat);

        public Object newAccessiblityDelegateDefaultImpl();

        public void onInitializeAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent);

        public void onInitializeAccessibilityNodeInfo(Object r1_Object, View r2_View, AccessibilityNodeInfoCompat r3_AccessibilityNodeInfoCompat);

        public void onPopulateAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent);

        public boolean onRequestSendAccessibilityEvent(Object r1_Object, ViewGroup r2_ViewGroup, View r3_View, AccessibilityEvent r4_AccessibilityEvent);

        public boolean performAccessibilityAction(Object r1_Object, View r2_View, int r3i, Bundle r4_Bundle);

        public void sendAccessibilityEvent(Object r1_Object, View r2_View, int r3i);

        public void sendAccessibilityEventUnchecked(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent);
    }

    static class d implements b {
        d() {
        }

        public boolean dispatchPopulateAccessibilityEvent(Object r2_Object, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
            return false;
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object r2_Object, View r3_View) {
            return null;
        }

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat r2_AccessibilityDelegateCompat) {
            return null;
        }

        public Object newAccessiblityDelegateDefaultImpl() {
            return null;
        }

        public void onInitializeAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        }

        public void onInitializeAccessibilityNodeInfo(Object r1_Object, View r2_View, AccessibilityNodeInfoCompat r3_AccessibilityNodeInfoCompat) {
        }

        public void onPopulateAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        }

        public boolean onRequestSendAccessibilityEvent(Object r2_Object, ViewGroup r3_ViewGroup, View r4_View, AccessibilityEvent r5_AccessibilityEvent) {
            return true;
        }

        public boolean performAccessibilityAction(Object r2_Object, View r3_View, int r4i, Bundle r5_Bundle) {
            return false;
        }

        public void sendAccessibilityEvent(Object r1_Object, View r2_View, int r3i) {
        }

        public void sendAccessibilityEventUnchecked(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        }
    }

    static class a extends d {
        a() {
        }

        public boolean dispatchPopulateAccessibilityEvent(Object r2_Object, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
            return AccessibilityDelegateCompatIcs.dispatchPopulateAccessibilityEvent(r2_Object, r3_View, r4_AccessibilityEvent);
        }

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat r2_AccessibilityDelegateCompat) {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateBridge(new a(this, r2_AccessibilityDelegateCompat));
        }

        public Object newAccessiblityDelegateDefaultImpl() {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateDefaultImpl();
        }

        public void onInitializeAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityEvent(r1_Object, r2_View, r3_AccessibilityEvent);
        }

        public void onInitializeAccessibilityNodeInfo(Object r2_Object, View r3_View, AccessibilityNodeInfoCompat r4_AccessibilityNodeInfoCompat) {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityNodeInfo(r2_Object, r3_View, r4_AccessibilityNodeInfoCompat.getInfo());
        }

        public void onPopulateAccessibilityEvent(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
            AccessibilityDelegateCompatIcs.onPopulateAccessibilityEvent(r1_Object, r2_View, r3_AccessibilityEvent);
        }

        public boolean onRequestSendAccessibilityEvent(Object r2_Object, ViewGroup r3_ViewGroup, View r4_View, AccessibilityEvent r5_AccessibilityEvent) {
            return AccessibilityDelegateCompatIcs.onRequestSendAccessibilityEvent(r2_Object, r3_ViewGroup, r4_View, r5_AccessibilityEvent);
        }

        public void sendAccessibilityEvent(Object r1_Object, View r2_View, int r3i) {
            AccessibilityDelegateCompatIcs.sendAccessibilityEvent(r1_Object, r2_View, r3i);
        }

        public void sendAccessibilityEventUnchecked(Object r1_Object, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
            AccessibilityDelegateCompatIcs.sendAccessibilityEventUnchecked(r1_Object, r2_View, r3_AccessibilityEvent);
        }
    }

    static class c extends a {
        c() {
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object r3_Object, View r4_View) {
            Object r1_Object = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(r3_Object, r4_View);
            return r1_Object != null ? new AccessibilityNodeProviderCompat(r1_Object) : null;
        }

        public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat r2_AccessibilityDelegateCompat) {
            return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge(new b(this, r2_AccessibilityDelegateCompat));
        }

        public boolean performAccessibilityAction(Object r2_Object, View r3_View, int r4i, Bundle r5_Bundle) {
            return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(r2_Object, r3_View, r4i, r5_Bundle);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            b = new c();
        } else if (VERSION.SDK_INT >= 14) {
            b = new a();
        } else {
            b = new d();
        }
        c = b.newAccessiblityDelegateDefaultImpl();
    }

    public AccessibilityDelegateCompat() {
        this.a = b.newAccessiblityDelegateBridge(this);
    }

    Object a() {
        return this.a;
    }

    public boolean dispatchPopulateAccessibilityEvent(View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
        return b.dispatchPopulateAccessibilityEvent(c, r3_View, r4_AccessibilityEvent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View r3_View) {
        return b.getAccessibilityNodeProvider(c, r3_View);
    }

    public void onInitializeAccessibilityEvent(View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
        b.onInitializeAccessibilityEvent(c, r3_View, r4_AccessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View r3_View, AccessibilityNodeInfoCompat r4_AccessibilityNodeInfoCompat) {
        b.onInitializeAccessibilityNodeInfo(c, r3_View, r4_AccessibilityNodeInfoCompat);
    }

    public void onPopulateAccessibilityEvent(View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
        b.onPopulateAccessibilityEvent(c, r3_View, r4_AccessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup r3_ViewGroup, View r4_View, AccessibilityEvent r5_AccessibilityEvent) {
        return b.onRequestSendAccessibilityEvent(c, r3_ViewGroup, r4_View, r5_AccessibilityEvent);
    }

    public boolean performAccessibilityAction(View r3_View, int r4i, Bundle r5_Bundle) {
        return b.performAccessibilityAction(c, r3_View, r4i, r5_Bundle);
    }

    public void sendAccessibilityEvent(View r3_View, int r4i) {
        b.sendAccessibilityEvent(c, r3_View, r4i);
    }

    public void sendAccessibilityEventUnchecked(View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
        b.sendAccessibilityEventUnchecked(c, r3_View, r4_AccessibilityEvent);
    }
}