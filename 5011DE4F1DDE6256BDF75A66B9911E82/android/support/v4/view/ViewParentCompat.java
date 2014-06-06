package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

public class ViewParentCompat {
    static final b a;

    static interface b {
        public boolean requestSendAccessibilityEvent(ViewParent r1_ViewParent, View r2_View, AccessibilityEvent r3_AccessibilityEvent);
    }

    static class c implements b {
        c() {
        }

        public boolean requestSendAccessibilityEvent(ViewParent r3_ViewParent, View r4_View, AccessibilityEvent r5_AccessibilityEvent) {
            if (r4_View == null) {
                return false;
            }
            ((AccessibilityManager) r4_View.getContext().getSystemService("accessibility")).sendAccessibilityEvent(r5_AccessibilityEvent);
            return true;
        }
    }

    static class a extends c {
        a() {
        }

        public boolean requestSendAccessibilityEvent(ViewParent r2_ViewParent, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
            return ViewParentCompatICS.requestSendAccessibilityEvent(r2_ViewParent, r3_View, r4_AccessibilityEvent);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            a = new a();
        } else {
            a = new c();
        }
    }

    private ViewParentCompat() {
    }

    public static boolean requestSendAccessibilityEvent(ViewParent r1_ViewParent, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return a.requestSendAccessibilityEvent(r1_ViewParent, r2_View, r3_AccessibilityEvent);
    }
}