package qsbk.app.push;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

public class Utils {
    public static final String ACTION_RESPONSE = "bccsclient.action.RESPONSE";
    public static final String ACTION_SHOW_MESSAGE = "bccsclient.action.SHOW_MESSAGE";
    public static final String EXTRA_MESSAGE = "message";
    public static final String RESPONSE_CONTENT = "content";
    public static final String RESPONSE_ERRCODE = "errcode";
    public static final String RESPONSE_METHOD = "method";

    public static String getMetaValue(Context r4_Context, String r5_String) {
        if (r4_Context == null || r5_String == null) {
            return null;
        }
        Bundle r1_Bundle;
        try {
            Bundle r1_Bundle_2;
            ApplicationInfo r1_ApplicationInfo = r4_Context.getPackageManager().getApplicationInfo(r4_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            r1_Bundle_2 = r1_ApplicationInfo != null ? r1_ApplicationInfo.metaData : null;
            return r1_Bundle_2 != null ? r1_Bundle_2.getString(r5_String) : null;
        } catch (NameNotFoundException e) {
            return null;
        }
    }
}