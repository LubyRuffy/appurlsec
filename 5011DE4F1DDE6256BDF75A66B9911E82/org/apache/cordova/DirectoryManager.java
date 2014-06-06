package org.apache.cordova;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;

public class DirectoryManager {
    private static final String LOG_TAG = "DirectoryManager";

    private static File constructFilePaths(String r3_String, String r4_String) {
        return r4_String.startsWith(r3_String) ? new File(r4_String) : new File(r3_String + "/" + r4_String);
    }

    private static long freeSpaceCalculation(String r5_String) {
        StatFs r0_StatFs = new StatFs(r5_String);
        return (((long) r0_StatFs.getAvailableBlocks()) * ((long) r0_StatFs.getBlockSize())) / 1024;
    }

    protected static long getFreeDiskSpace(boolean r2z) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return freeSpaceCalculation(Environment.getExternalStorageDirectory().getPath());
        }
        if (r2z) {
            return freeSpaceCalculation("/");
        }
        return -1;
    }

    protected static String getTempDirectoryPath(Context r3_Context) {
        File r0_File;
        r0_File = Environment.getExternalStorageState().equals("mounted") ? new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + r3_Context.getPackageName() + "/cache/") : r3_Context.getCacheDir();
        if (!r0_File.exists()) {
            r0_File.mkdirs();
        }
        return r0_File.getAbsolutePath();
    }

    protected static boolean testFileExists(String r1_String) {
        return ((!testSaveLocationExists()) || r1_String.equals(RContactStorage.PRIMARY_KEY)) ? false : constructFilePaths(Environment.getExternalStorageDirectory().toString(), r1_String).exists();
    }

    protected static boolean testSaveLocationExists() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}