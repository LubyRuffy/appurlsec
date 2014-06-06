package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityManager;
import java.util.Collections;
import java.util.List;

public class AccessibilityManagerCompat {
    private static final c a;

    public static abstract class AccessibilityStateChangeListenerCompat {
        final Object a;

        public AccessibilityStateChangeListenerCompat() {
            this.a = a.newAccessiblityStateChangeListener(this);
        }

        public abstract void onAccessibilityStateChanged(boolean r1z);
    }

    static interface c {
        public boolean addAccessibilityStateChangeListener(AccessibilityManager r1_AccessibilityManager, android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r2_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat);

        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager r1_AccessibilityManager, int r2i);

        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager r1_AccessibilityManager);

        public boolean isTouchExplorationEnabled(AccessibilityManager r1_AccessibilityManager);

        public Object newAccessiblityStateChangeListener(android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r1_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat);

        public boolean removeAccessibilityStateChangeListener(AccessibilityManager r1_AccessibilityManager, android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r2_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat);
    }

    static class b implements c {
        b() {
        }

        public boolean addAccessibilityStateChangeListener(AccessibilityManager r2_AccessibilityManager, android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r3_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat) {
            return false;
        }

        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager r2_AccessibilityManager, int r3i) {
            return Collections.emptyList();
        }

        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager r2_AccessibilityManager) {
            return Collections.emptyList();
        }

        public boolean isTouchExplorationEnabled(AccessibilityManager r2_AccessibilityManager) {
            return false;
        }

        public Object newAccessiblityStateChangeListener(android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r2_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat) {
            return null;
        }

        public boolean removeAccessibilityStateChangeListener(AccessibilityManager r2_AccessibilityManager, android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r3_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat) {
            return false;
        }
    }

    static class a extends b {
        a() {
        }

        public boolean addAccessibilityStateChangeListener(AccessibilityManager r2_AccessibilityManager, android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r3_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat) {
            return c.addAccessibilityStateChangeListener(r2_AccessibilityManager, r3_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat.a);
        }

        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager r2_AccessibilityManager, int r3i) {
            return c.getEnabledAccessibilityServiceList(r2_AccessibilityManager, r3i);
        }

        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager r2_AccessibilityManager) {
            return c.getInstalledAccessibilityServiceList(r2_AccessibilityManager);
        }

        public boolean isTouchExplorationEnabled(AccessibilityManager r2_AccessibilityManager) {
            return c.isTouchExplorationEnabled(r2_AccessibilityManager);
        }

        public Object newAccessiblityStateChangeListener(android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r2_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat) {
            return c.newAccessibilityStateChangeListener(new b(this, r2_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat));
        }

        public boolean removeAccessibilityStateChangeListener(AccessibilityManager r2_AccessibilityManager, android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat r3_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat) {
            return c.removeAccessibilityStateChangeListener(r2_AccessibilityManager, r3_android_support_v4_view_accessibility_AccessibilityManagerCompat_AccessibilityStateChangeListenerCompat.a);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager r1_AccessibilityManager, AccessibilityStateChangeListenerCompat r2_AccessibilityStateChangeListenerCompat) {
        return a.addAccessibilityStateChangeListener(r1_AccessibilityManager, r2_AccessibilityStateChangeListenerCompat);
    }

    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager r1_AccessibilityManager, int r2i) {
        return a.getEnabledAccessibilityServiceList(r1_AccessibilityManager, r2i);
    }

    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager r1_AccessibilityManager) {
        return a.getInstalledAccessibilityServiceList(r1_AccessibilityManager);
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager r1_AccessibilityManager) {
        return a.isTouchExplorationEnabled(r1_AccessibilityManager);
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager r1_AccessibilityManager, AccessibilityStateChangeListenerCompat r2_AccessibilityStateChangeListenerCompat) {
        return a.removeAccessibilityStateChangeListener(r1_AccessibilityManager, r2_AccessibilityStateChangeListenerCompat);
    }
}