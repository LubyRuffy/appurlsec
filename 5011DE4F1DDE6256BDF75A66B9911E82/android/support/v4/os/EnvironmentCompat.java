package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";

    public static String getStorageState(File r4_File) {
        if (VERSION.SDK_INT >= 19) {
            return a.getStorageState(r4_File);
        }
        try {
            if (r4_File.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
                return Environment.getExternalStorageState();
            }
        } catch (IOException e) {
            Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + e);
        }
        return MEDIA_UNKNOWN;
    }
}