package qsbk.app.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DebugUtil {
    public static final boolean DEBUG = false;
    public static final String TAG = "DebugUtil";

    public static void debug(String r0_String) {
    }

    public static void debug(String r0_String, String r1_String) {
    }

    public static void error(String r1_String) {
        Log.e(TAG, r1_String);
    }

    public static void error(String r0_String, String r1_String) {
        Log.e(r0_String, r1_String);
    }

    public static void toast(Context r1_Context, String r2_String) {
        Toast.makeText(r1_Context, r2_String, 0).show();
    }
}