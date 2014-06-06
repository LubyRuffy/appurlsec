package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener;
import java.util.List;

// compiled from: AccessibilityManagerCompatIcs.java
class c {

    // compiled from: AccessibilityManagerCompatIcs.java
    static interface a {
        public void onAccessibilityStateChanged(boolean r1z);
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager r1_AccessibilityManager, Object r2_Object) {
        return r1_AccessibilityManager.addAccessibilityStateChangeListener((AccessibilityStateChangeListener) r2_Object);
    }

    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager r1_AccessibilityManager, int r2i) {
        return r1_AccessibilityManager.getEnabledAccessibilityServiceList(r2i);
    }

    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager r1_AccessibilityManager) {
        return r1_AccessibilityManager.getInstalledAccessibilityServiceList();
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager r1_AccessibilityManager) {
        return r1_AccessibilityManager.isTouchExplorationEnabled();
    }

    public static Object newAccessibilityStateChangeListener(a r1_a) {
        return new d(r1_a);
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager r1_AccessibilityManager, Object r2_Object) {
        return r1_AccessibilityManager.removeAccessibilityStateChangeListener((AccessibilityStateChangeListener) r2_Object);
    }
}