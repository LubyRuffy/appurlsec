package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;

public class ContextCompat {
    private static File a(File r5_File, String ... r6_StringA) {
        int r3i = r6_StringA.length;
        int r2i = 0;
        File r1_File = r5_File;
        while (r2i < r3i) {
            File r0_File;
            String r4_String = r6_StringA[r2i];
            if (r1_File == null) {
                r0_File = new File(r4_String);
            } else if (r4_String != null) {
                r0_File = new File(r1_File, r4_String);
            } else {
                r0_File = r1_File;
            }
            r2i++;
            r1_File = r0_File;
        }
        return r1_File;
    }

    public static File[] getExternalCacheDirs(Context r6_Context) {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 19) {
            return d.getExternalCacheDirs(r6_Context);
        }
        File r0_File;
        if (r0i >= 8) {
            r0_File = a.getExternalCacheDir(r6_Context);
        } else {
            r0_File = Environment.getExternalStorageDirectory();
            String[] r1_StringA = new String[4];
            r1_StringA[0] = "Android";
            r1_StringA[1] = "data";
            r1_StringA[2] = r6_Context.getPackageName();
            r1_StringA[3] = "cache";
            r0_File = a(r0_File, r1_StringA);
        }
        File[] r1_FileA = new File[1];
        r1_FileA[0] = r0_File;
        return r1_FileA;
    }

    public static File[] getExternalFilesDirs(Context r6_Context, String r7_String) {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 19) {
            return d.getExternalFilesDirs(r6_Context, r7_String);
        }
        File r0_File;
        if (r0i >= 8) {
            r0_File = a.getExternalFilesDir(r6_Context, r7_String);
        } else {
            r0_File = Environment.getExternalStorageDirectory();
            String[] r1_StringA = new String[5];
            r1_StringA[0] = "Android";
            r1_StringA[1] = "data";
            r1_StringA[2] = r6_Context.getPackageName();
            r1_StringA[3] = "files";
            r1_StringA[4] = r7_String;
            r0_File = a(r0_File, r1_StringA);
        }
        File[] r1_FileA = new File[1];
        r1_FileA[0] = r0_File;
        return r1_FileA;
    }

    public static File[] getObbDirs(Context r6_Context) {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 19) {
            return d.getObbDirs(r6_Context);
        }
        File r0_File;
        if (r0i >= 11) {
            r0_File = b.getObbDir(r6_Context);
        } else {
            r0_File = Environment.getExternalStorageDirectory();
            String[] r1_StringA = new String[3];
            r1_StringA[0] = "Android";
            r1_StringA[1] = "obb";
            r1_StringA[2] = r6_Context.getPackageName();
            r0_File = a(r0_File, r1_StringA);
        }
        File[] r1_FileA = new File[1];
        r1_FileA[0] = r0_File;
        return r1_FileA;
    }

    public static boolean startActivities(Context r1_Context, Intent[] r2_IntentA) {
        return startActivities(r1_Context, r2_IntentA, null);
    }

    public static boolean startActivities(Context r3_Context, Intent[] r4_IntentA, Bundle r5_Bundle) {
        int r1i = VERSION.SDK_INT;
        if (r1i >= 16) {
            c.startActivities(r3_Context, r4_IntentA, r5_Bundle);
            return true;
        } else {
            if (r1i < 11) {
                return false;
            }
            b.a(r3_Context, r4_IntentA);
            return true;
        }
    }
}