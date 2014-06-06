package qsbk.app.utils.image;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public class Utils {
    public static final int IO_BUFFER_SIZE = 8192;

    private Utils() {
    }

    public static void disableConnectionReuseIfNecessary() {
        if (hasHttpConnectionBug()) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    public static int getBitmapSize(Bitmap r2_Bitmap) {
        return r2_Bitmap.getRowBytes() * r2_Bitmap.getHeight();
    }

    public static File getExternalCacheDir(Context r4_Context) {
        if (hasExternalCacheDir()) {
            return r4_Context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + r4_Context.getPackageName() + "/cache/"));
    }

    public static int getMemoryClass(Context r1_Context) {
        return ((ActivityManager) r1_Context.getSystemService("activity")).getMemoryClass();
    }

    public static long getUsableSpace(File r5_File) {
        if (VERSION.SDK_INT >= 9) {
            return r5_File.getUsableSpace();
        }
        StatFs r0_StatFs = new StatFs(r5_File.getPath());
        return ((long) r0_StatFs.getBlockSize()) * ((long) r0_StatFs.getAvailableBlocks());
    }

    public static boolean hasActionBar() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean hasExternalCacheDir() {
        return VERSION.SDK_INT >= 8;
    }

    public static boolean hasHttpConnectionBug() {
        return VERSION.SDK_INT < 8;
    }

    public static boolean isExternalStorageRemovable() {
        return VERSION.SDK_INT >= 9 ? Environment.isExternalStorageRemovable() : true;
    }
}