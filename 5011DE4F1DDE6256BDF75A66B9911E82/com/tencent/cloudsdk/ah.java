package com.tencent.cloudsdk;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Environment;
import com.baidu.location.BDLocation;
import com.tencent.cloudsdk.common.record.info.Global;
import java.io.File;
import qsbk.app.utils.Base64;

// compiled from: SourceFile
public class ah implements OnSharedPreferenceChangeListener {
    protected static z CLIENT_CONFIG;
    protected static z SERVICE_CONFIG;
    private volatile boolean a;
    private volatile boolean b;
    private volatile boolean c;
    protected y fileTracer;

    public ah() {
        this.a = true;
        this.b = true;
        this.c = false;
        File r1_File = getLogFilePath();
        CLIENT_CONFIG = new z(r1_File, 24, 262144, 8192, "CloudSdk.Client.File.Tracer", 10000, 10, ".CloudSdk.log", 604800000);
        SERVICE_CONFIG = new z(r1_File, 24, 262144, 8192, "CloudSdk.File.Tracer", 10000, 10, ".CloudSdk.log", 604800000);
    }

    public static void deleteFile(File r4_File) {
        if (r4_File == null || (!r4_File.exists())) {
        } else if (r4_File.isFile()) {
            r4_File.delete();
        } else {
            File[] r1_FileA = r4_File.listFiles();
            int r2i = r1_FileA.length;
            int r0i = 0;
            while (r0i < r2i) {
                deleteFile(r1_FileA[r0i]);
                r0i++;
            }
        }
    }

    public static File getLogFilePath() {
        int r0i = 0;
        String r1_String = new StringBuilder(String.valueOf(aj.a)).append(File.separator).append(Global.getPackageName()).toString();
        al r2_al = ak.b();
        if (r2_al == null || r2_al.c() <= 8388608) {
            return r0i == 0 ? new File(Environment.getExternalStorageDirectory(), r1_String) : new File(Global.getFilesDir(), r1_String);
        } else {
            r0i = 1;
            if (r0i == 0) {
            }
        }
    }

    public static void setFileTraceLevel(int r1i) {
        if (r1i > 63 || r1i >= 0) {
        }
    }

    public static void setMaxFolderSize(long r2j) {
        if (((int) (r2j / 262144)) < 1) {
        }
    }

    public static void setMaxKeepPeriod(long r2j) {
        if (r2j < 86400000) {
        }
    }

    public void flush() {
        if (this.fileTracer != null) {
            this.fileTracer.a();
        }
    }

    public final boolean isEnabled() {
        return this.a;
    }

    public final boolean isFileTracerEnabled() {
        return this.b;
    }

    public final boolean isLogcatTracerEnabled() {
        return this.c;
    }

    public void onSharedPreferenceChanged(SharedPreferences r6_SharedPreferences, String r7_String) {
        if ("debug.file.tracelevel".equals(r7_String)) {
            int r0i = r6_SharedPreferences.getInt("debug.file.tracelevel", BDLocation.TypeCriteriaException);
            trace(Base64.DONT_BREAK_LINES, "WnsTracer", new StringBuilder("File Trace Level Changed = ").append(r0i).toString(), null);
            this.fileTracer.a(r0i);
        }
    }

    public final void setEnabled(boolean r1z) {
        this.a = r1z;
    }

    public final void setFileTracerEnabled(boolean r2z) {
        this.fileTracer.a();
        this.b = r2z;
    }

    public final void setFileTracerLevel(int r2i) {
        this.fileTracer.a(r2i);
    }

    public final void setLogcatTracerEnabled(boolean r1z) {
        this.c = r1z;
    }

    public void stop() {
        if (this.fileTracer != null) {
            this.fileTracer.a();
            this.fileTracer.b();
        }
    }

    public void trace(int r9i, String r10_String, String r11_String, Throwable r12_Throwable) {
        if (isEnabled()) {
            if (isFileTracerEnabled()) {
                this.fileTracer.b(r9i, Thread.currentThread(), System.currentTimeMillis(), r10_String, r11_String, r12_Throwable);
            }
            if (isLogcatTracerEnabled()) {
                ad.a.b(r9i, Thread.currentThread(), System.currentTimeMillis(), r10_String, r11_String, r12_Throwable);
            }
        }
    }
}