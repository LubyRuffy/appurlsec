package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;

// compiled from: AccessibilityServiceInfoCompatIcs.java
class a {
    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return r1_AccessibilityServiceInfo.getCanRetrieveWindowContent();
    }

    public static String getDescription(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return r1_AccessibilityServiceInfo.getDescription();
    }

    public static String getId(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return r1_AccessibilityServiceInfo.getId();
    }

    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return r1_AccessibilityServiceInfo.getResolveInfo();
    }

    public static String getSettingsActivityName(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return r1_AccessibilityServiceInfo.getSettingsActivityName();
    }
}