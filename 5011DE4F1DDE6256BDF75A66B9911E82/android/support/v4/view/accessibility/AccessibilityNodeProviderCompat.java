package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.List;

public class AccessibilityNodeProviderCompat {
    private static final a a;
    private final Object b;

    static interface a {
        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat r1_AccessibilityNodeProviderCompat);
    }

    static class d implements a {
        d() {
        }

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat r2_AccessibilityNodeProviderCompat) {
            return null;
        }
    }

    static class b extends d {
        b() {
        }

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat r2_AccessibilityNodeProviderCompat) {
            return k.newAccessibilityNodeProviderBridge(new i(this, r2_AccessibilityNodeProviderCompat));
        }
    }

    static class c extends d {
        c() {
        }

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat r2_AccessibilityNodeProviderCompat) {
            return m.newAccessibilityNodeProviderBridge(new j(this, r2_AccessibilityNodeProviderCompat));
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new c();
        } else if (VERSION.SDK_INT >= 16) {
            a = new b();
        } else {
            a = new d();
        }
    }

    public AccessibilityNodeProviderCompat() {
        this.b = a.newAccessibilityNodeProviderBridge(this);
    }

    public AccessibilityNodeProviderCompat(Object r1_Object) {
        this.b = r1_Object;
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int r2i) {
        return null;
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String r2_String, int r3i) {
        return null;
    }

    public AccessibilityNodeInfoCompat findFocus(int r2i) {
        return null;
    }

    public Object getProvider() {
        return this.b;
    }

    public boolean performAction(int r2i, int r3i, Bundle r4_Bundle) {
        return false;
    }
}