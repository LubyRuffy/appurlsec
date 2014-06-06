package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;

public class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final d a;

    static interface d {
        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo r1_AccessibilityServiceInfo);

        public int getCapabilities(AccessibilityServiceInfo r1_AccessibilityServiceInfo);

        public String getDescription(AccessibilityServiceInfo r1_AccessibilityServiceInfo);

        public String getId(AccessibilityServiceInfo r1_AccessibilityServiceInfo);

        public ResolveInfo getResolveInfo(AccessibilityServiceInfo r1_AccessibilityServiceInfo);

        public String getSettingsActivityName(AccessibilityServiceInfo r1_AccessibilityServiceInfo);
    }

    static class c implements d {
        c() {
        }

        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return false;
        }

        public int getCapabilities(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return 0;
        }

        public String getDescription(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return null;
        }

        public String getId(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return null;
        }

        public ResolveInfo getResolveInfo(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return null;
        }

        public String getSettingsActivityName(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return null;
        }
    }

    static class a extends c {
        a() {
        }

        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return a.getCanRetrieveWindowContent(r2_AccessibilityServiceInfo);
        }

        public int getCapabilities(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return getCanRetrieveWindowContent(r2_AccessibilityServiceInfo) ? DEFAULT : 0;
        }

        public String getDescription(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return a.getDescription(r2_AccessibilityServiceInfo);
        }

        public String getId(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return a.getId(r2_AccessibilityServiceInfo);
        }

        public ResolveInfo getResolveInfo(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return a.getResolveInfo(r2_AccessibilityServiceInfo);
        }

        public String getSettingsActivityName(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return a.getSettingsActivityName(r2_AccessibilityServiceInfo);
        }
    }

    static class b extends a {
        b() {
        }

        public int getCapabilities(AccessibilityServiceInfo r2_AccessibilityServiceInfo) {
            return b.getCapabilities(r2_AccessibilityServiceInfo);
        }
    }

    static {
        if (VERSION.SDK_INT >= 18) {
            a = new b();
        } else if (VERSION.SDK_INT >= 14) {
            a = new a();
        } else {
            a = new c();
        }
    }

    private AccessibilityServiceInfoCompat() {
    }

    public static String capabilityToString(int r1i) {
        switch (r1i) {
            case DEFAULT:
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            case FLAG_INCLUDE_NOT_IMPORTANT_VIEWS:
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            case FLAG_REQUEST_TOUCH_EXPLORATION_MODE:
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY:
                return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
        }
        return "UNKNOWN";
    }

    public static String feedbackTypeToString(int r4i) {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append("[");
        while (r4i > 0) {
            int r1i = 1 << Integer.numberOfTrailingZeros(r4i);
            r4i &= r1i ^ -1;
            if (r0_StringBuilder.length() > 1) {
                r0_StringBuilder.append(", ");
            }
            switch (r1i) {
                case DEFAULT:
                    r0_StringBuilder.append("FEEDBACK_SPOKEN");
                    break;
                case FLAG_INCLUDE_NOT_IMPORTANT_VIEWS:
                    r0_StringBuilder.append("FEEDBACK_HAPTIC");
                    break;
                case FLAG_REQUEST_TOUCH_EXPLORATION_MODE:
                    r0_StringBuilder.append("FEEDBACK_AUDIBLE");
                    break;
                case FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY:
                    r0_StringBuilder.append("FEEDBACK_VISUAL");
                    break;
                case FLAG_REPORT_VIEW_IDS:
                    r0_StringBuilder.append("FEEDBACK_GENERIC");
                    break;
            }
        }
        r0_StringBuilder.append("]");
        return r0_StringBuilder.toString();
    }

    public static String flagToString(int r1i) {
        switch (r1i) {
            case DEFAULT:
                return "DEFAULT";
            case FLAG_INCLUDE_NOT_IMPORTANT_VIEWS:
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            case FLAG_REQUEST_TOUCH_EXPLORATION_MODE:
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            case FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY:
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            case FLAG_REPORT_VIEW_IDS:
                return "FLAG_REPORT_VIEW_IDS";
            case FLAG_REQUEST_FILTER_KEY_EVENTS:
                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
        }
        return null;
    }

    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return a.getCanRetrieveWindowContent(r1_AccessibilityServiceInfo);
    }

    public static int getCapabilities(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return a.getCapabilities(r1_AccessibilityServiceInfo);
    }

    public static String getDescription(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return a.getDescription(r1_AccessibilityServiceInfo);
    }

    public static String getId(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return a.getId(r1_AccessibilityServiceInfo);
    }

    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return a.getResolveInfo(r1_AccessibilityServiceInfo);
    }

    public static String getSettingsActivityName(AccessibilityServiceInfo r1_AccessibilityServiceInfo) {
        return a.getSettingsActivityName(r1_AccessibilityServiceInfo);
    }
}