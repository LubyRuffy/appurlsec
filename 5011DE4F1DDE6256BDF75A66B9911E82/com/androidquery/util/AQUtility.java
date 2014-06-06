package com.androidquery.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.androidquery.AQuery;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import qsbk.app.widget.listview.XListViewHeader;

public class AQUtility {
    public static boolean TEST_IO_EXCEPTION;
    private static boolean a;
    private static Object b;
    private static UncaughtExceptionHandler c;
    private static Map<String, Long> d;
    private static Handler e;
    private static ScheduledExecutorService f;
    private static File g;
    private static File h;
    private static Context i;
    private static final char[] j;
    private static final byte[] k;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r5 = 64;
        r1 = 0;
        a = r1;
        r0 = new java.util.HashMap;
        r0.<init>();
        d = r0;
        TEST_IO_EXCEPTION = r1;
        r0 = new char[r5];
        j = r0;
        r0 = 65;
        r2 = r1;
    L_0x0015:
        r3 = 90;
        if (r0 <= r3) goto L_0x0044;
    L_0x0019:
        r0 = 97;
    L_0x001b:
        r3 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r0 <= r3) goto L_0x004f;
    L_0x001f:
        r0 = 48;
    L_0x0021:
        r3 = 57;
        if (r0 <= r3) goto L_0x005a;
    L_0x0025:
        r0 = j;
        r3 = r2 + 1;
        r4 = 43;
        r0[r2] = r4;
        r0 = j;
        r2 = r3 + 1;
        r2 = 47;
        r0[r3] = r2;
        r0 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = new byte[r0];
        k = r0;
        r0 = r1;
    L_0x003c:
        r2 = k;
        r2 = r2.length;
        if (r0 < r2) goto L_0x0065;
    L_0x0041:
        if (r1 < r5) goto L_0x006d;
    L_0x0043:
        return;
    L_0x0044:
        r4 = j;
        r3 = r2 + 1;
        r4[r2] = r0;
        r0 = r0 + 1;
        r0 = (char) r0;
        r2 = r3;
        goto L_0x0015;
    L_0x004f:
        r4 = j;
        r3 = r2 + 1;
        r4[r2] = r0;
        r0 = r0 + 1;
        r0 = (char) r0;
        r2 = r3;
        goto L_0x001b;
    L_0x005a:
        r4 = j;
        r3 = r2 + 1;
        r4[r2] = r0;
        r0 = r0 + 1;
        r0 = (char) r0;
        r2 = r3;
        goto L_0x0021;
    L_0x0065:
        r2 = k;
        r3 = -1;
        r2[r0] = r3;
        r0 = r0 + 1;
        goto L_0x003c;
    L_0x006d:
        r0 = k;
        r2 = j;
        r2 = r2[r1];
        r3 = (byte) r1;
        r0[r2] = r3;
        r1 = r1 + 1;
        goto L_0x0041;
        */

    }

    private static File a(File r1_File, String r2_String) {
        return new File(r1_File, r2_String);
    }

    private static Object a(Object r3_Object, String r4_String, boolean r5z, Class<?>[] r6_Class_A, Class<?>[] r7_Class_A, Object ... r8_ObjectA) throws Exception {
        if (r3_Object == null || r4_String == null) {
            return null;
        }
        if (r6_Class_A == null) {
            r6_Class_A = new Class<?>[0];
        }
        return r3_Object.getClass().getMethod(r4_String, r6_Class_A).invoke(r3_Object, r8_ObjectA);
    }

    private static String a(String r2_String) {
        return new BigInteger(a(r2_String.getBytes())).abs().toString(AdViewUtil.NETWORK_TYPE_APPMEDIA);
    }

    private static ScheduledExecutorService a() {
        if (f == null) {
            f = Executors.newSingleThreadScheduledExecutor();
        }
        return f;
    }

    private static void a(View r3_View, float r4f) {
        if (r4f == 1.0f) {
            r3_View.clearAnimation();
        } else {
            Animation r0_Animation = new AlphaAnimation(r4f, r4f);
            r0_Animation.setDuration(0);
            r0_Animation.setFillAfter(true);
            r3_View.startAnimation(r0_Animation);
        }
    }

    private static void a(File r2_File) {
        r2_File.setLastModified(System.currentTimeMillis());
    }

    private static boolean a(File[] r9_FileA, long r10j) {
        int r4i = r9_FileA.length;
        long r2j = 0;
        int r1i = 0;
        while (r1i < r4i) {
            r2j += r9_FileA[r1i].length();
            if (r2j > r10j) {
                return true;
            }
            r1i++;
        }
        return false;
    }

    private static byte[] a(byte[] r1_byteA) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.update(r1_byteA);
            return r0_MessageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            report(e);
            return null;
        }
    }

    public static void apply(Editor r6_Editor) {
        if (AQuery.SDK_INT >= 9) {
            invokeHandler(r6_Editor, "apply", false, true, null, null);
        } else {
            r6_Editor.commit();
        }
    }

    private static String b(String r1_String) {
        return a(r1_String);
    }

    private static void b(File[] r9_FileA, long r10j) {
        int r0i = 0;
        long r2j = 0;
        int r1i = 0;
        while (r0i < r9_FileA.length) {
            File r4_File = r9_FileA[r0i];
            if (r4_File.isFile()) {
                r2j += r4_File.length();
                if (r2j >= r10j) {
                    r4_File.delete();
                    r1i++;
                }
            }
            r0i++;
        }
        debug("deleted", Integer.valueOf(r1i));
    }

    public static void cleanCache(File r3_File, long r4j, long r6j) {
        try {
            File[] r0_FileA = r3_File.listFiles();
            if (r0_FileA == null) {
            } else {
                Arrays.sort(r0_FileA, new Common());
                if (a(r0_FileA, r4j)) {
                    b(r0_FileA, r6j);
                }
                File r0_File = getTempDir();
                if (r0_File == null || (!r0_File.exists())) {
                } else {
                    b(r0_File.listFiles(), 0);
                }
            }
        } catch (Exception e) {
            report(e);
        }
    }

    public static void cleanCacheAsync(Context r4_Context) {
        cleanCacheAsync(r4_Context, 3000000, 2000000);
    }

    public static void cleanCacheAsync(Context r5_Context, long r6j, long r8j) {
        try {
            File r0_File = getCacheDir(r5_Context);
            Common r1_Common = new Common();
            Object[] r3_ObjectA = new Object[3];
            r3_ObjectA[0] = r0_File;
            r3_ObjectA[1] = Long.valueOf(r6j);
            r3_ObjectA[2] = Long.valueOf(r8j);
            a().schedule(r1_Common.method(XListViewHeader.STATE_REFRESHING, r3_ObjectA), 0, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            report(e);
        }
    }

    public static void close(Closeable r1_Closeable) {
        if (r1_Closeable != null) {
            try {
                r1_Closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static void copy(InputStream r2_InputStream, OutputStream r3_OutputStream) throws IOException {
        copy(r2_InputStream, r3_OutputStream, 0, null);
    }

    public static void copy(InputStream r5_InputStream, OutputStream r6_OutputStream, int r7i, Progress r8_Progress) throws IOException {
        if (r8_Progress != null) {
            r8_Progress.reset();
            r8_Progress.setBytes(r7i);
        }
        byte[] r2_byteA = new byte[4096];
        int r0i = 0;
        while (true) {
            int r3i = r5_InputStream.read(r2_byteA);
            if (r3i == -1) {
                if (r8_Progress != null) {
                    r8_Progress.done();
                }
                return;
            } else {
                r6_OutputStream.write(r2_byteA, 0, r3i);
                r0i++;
                if ((!TEST_IO_EXCEPTION) || r0i <= 2) {
                    if (r8_Progress != null) {
                        r8_Progress.increment(r3i);
                    }
                } else {
                    debug((Object)"simulating internet error");
                    throw new IOException();
                }
            }
        }
    }

    public static void debug(Object r2_Object) {
        if (a) {
            Log.w("AQuery", r2_Object);
        }
    }

    public static void debug(Object r3_Object, Object r4_Object) {
        if (a) {
            Log.w("AQuery", r3_Object + ":" + r4_Object);
        }
    }

    public static void debug(Throwable r2_Throwable) {
        if (a) {
            Log.w("AQuery", Log.getStackTraceString(r2_Throwable));
        }
    }

    public static void debugNotify() {
        if ((!a) || b == null) {
        } else {
            synchronized (b) {
                b.notifyAll();
            }
        }
    }

    public static void debugWait(long r2j) {
        if (a) {
            if (b == null) {
                b = new Object();
            }
            synchronized (b) {
                try {
                    b.wait(r2j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int dip2pixel(Context r2_Context, float r3f) {
        return (int) TypedValue.applyDimension(1, r3f, r2_Context.getResources().getDisplayMetrics());
    }

    public static char[] encode64(byte[] r13_byteA, int r14i, int r15i) {
        int r6i = ((r15i * 4) + 2) / 3;
        char[] r7_charA = new char[(((r15i + 2) / 3) * 4)];
        int r8i = r14i + r15i;
        int r5i = 0;
        while (r14i < r8i) {
            int r4i;
            int r2i;
            int r0i = r14i + 1;
            int r9i = r13_byteA[r14i] & 255;
            if (r0i < r8i) {
                r4i = r13_byteA[r0i] & 255;
                r0i++;
            } else {
                r4i = 0;
            }
            if (r0i < r8i) {
                r2i = r0i + 1;
                r0i = r13_byteA[r0i] & 255;
            } else {
                r2i = r0i;
                r0i = 0;
            }
            r4i = ((r4i & 15) << 2) | (r0i >>> 6);
            int r11i = r0i & 63;
            r0i = r5i + 1;
            r7_charA[r5i] = j[r9i >>> 2];
            r5i = r0i + 1;
            r7_charA[r0i] = j[((r9i & 3) << 4) | (r4i >>> 4)];
            r7_charA[r5i] = r5i < r6i ? j[r4i] : '=';
            r4i = r5i + 1;
            r7_charA[r4i] = r4i < r6i ? j[r11i] : '=';
            r5i = r4i + 1;
            r14i = r2i;
        }
        return r7_charA;
    }

    public static void ensureUIThread() {
        if (!isUIThread()) {
            report(new IllegalStateException("Not UI Thread"));
        }
    }

    public static File getCacheDir(Context r3_Context) {
        if (g == null) {
            g = new File(r3_Context.getCacheDir(), "aquery");
            g.mkdirs();
        }
        return g;
    }

    public static File getCacheDir(Context r3_Context, int r4i) {
        if (r4i != 1) {
            return getCacheDir(r3_Context);
        }
        if (h != null) {
            return h;
        }
        h = new File(getCacheDir(r3_Context), "persistent");
        h.mkdirs();
        return h;
    }

    public static File getCacheFile(File r1_File, String r2_String) {
        if (r2_String == null) {
            return null;
        }
        if (r2_String.startsWith(File.separator)) {
            return new File(r2_String);
        }
        return a(r1_File, b(r2_String));
    }

    public static Context getContext() {
        if (i == null) {
            warn("warn", "getContext with null");
            debug(new IllegalStateException());
        }
        return i;
    }

    public static File getExistedCacheByUrl(File r5_File, String r6_String) {
        File r0_File = getCacheFile(r5_File, r6_String);
        return (r0_File == null || (!r0_File.exists()) || r0_File.length() == 0) ? null : r0_File;
    }

    public static File getExistedCacheByUrlSetAccess(File r1_File, String r2_String) {
        File r0_File = getExistedCacheByUrl(r1_File, r2_String);
        if (r0_File != null) {
            a(r0_File);
        }
        return r0_File;
    }

    public static Handler getHandler() {
        if (e == null) {
            e = new Handler(Looper.getMainLooper());
        }
        return e;
    }

    public static File getTempDir() {
        File r0_File = new File(Environment.getExternalStorageDirectory(), "aquery/temp");
        r0_File.mkdirs();
        return (r0_File.exists() && r0_File.canWrite()) ? r0_File : null;
    }

    public static Object invokeHandler(Object r6_Object, String r7_String, boolean r8z, boolean r9z, Class<?>[] r10_Class_A, Class<?>[] r11_Class_A, Object ... r12_ObjectA) {
        try {
            return a(r6_Object, r7_String, r8z, r10_Class_A, r11_Class_A, r12_ObjectA);
        } catch (Exception e) {
            Throwable r0_Throwable = e;
            if (r9z) {
                report(r0_Throwable);
            } else {
                debug(r0_Throwable);
            }
            return null;
        }
    }

    public static Object invokeHandler(Object r7_Object, String r8_String, boolean r9z, boolean r10z, Class<?>[] r11_Class_A, Object ... r12_ObjectA) {
        return invokeHandler(r7_Object, r8_String, r9z, r10z, r11_Class_A, null, r12_ObjectA);
    }

    public static boolean isDebug() {
        return a;
    }

    public static boolean isUIThread() {
        return (Looper.getMainLooper().getThread().getId() > Thread.currentThread().getId() ? 1 : (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()? 0 : -1)) == 0;
    }

    public static float pixel2dip(Context r2_Context, float r3f) {
        return r3f / (((float) r2_Context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static void post(Object r2_Object, String r3_String) {
        post(r2_Object, r3_String, new Class[0], new Object[0]);
    }

    public static void post(Object r1_Object, String r2_String, Class<?>[] r3_Class_A, Object ... r4_ObjectA) {
        post(new a(r1_Object, r2_String, r3_Class_A, r4_ObjectA));
    }

    public static void post(Runnable r1_Runnable) {
        getHandler().post(r1_Runnable);
    }

    public static void postAsync(Object r2_Object, String r3_String) {
        postAsync(r2_Object, r3_String, new Class[0], new Object[0]);
    }

    public static void postAsync(Object r1_Object, String r2_String, Class<?>[] r3_Class_A, Object ... r4_ObjectA) {
        postAsync(new c(r1_Object, r2_String, r3_Class_A, r4_ObjectA));
    }

    public static void postAsync(Runnable r2_Runnable) {
        new b(r2_Runnable).execute(new Void[0]);
    }

    public static void postDelayed(Runnable r1_Runnable, long r2j) {
        getHandler().postDelayed(r1_Runnable, r2j);
    }

    public static void removePost(Runnable r1_Runnable) {
        getHandler().removeCallbacks(r1_Runnable);
    }

    public static void report(Throwable r2_Throwable) {
        if (r2_Throwable == null) {
        } else {
            try {
                warn("reporting", Log.getStackTraceString(r2_Throwable));
                if (c != null) {
                    c.uncaughtException(Thread.currentThread(), r2_Throwable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setCacheDir(File r1_File) {
        g = r1_File;
        if (g != null) {
            g.mkdirs();
        }
    }

    public static void setContext(Application r1_Application) {
        i = r1_Application.getApplicationContext();
    }

    public static void setDebug(boolean r0z) {
        a = r0z;
    }

    public static void setExceptionHandler(UncaughtExceptionHandler r0_UncaughtExceptionHandler) {
        c = r0_UncaughtExceptionHandler;
    }

    public static void store(File r1_File, byte[] r2_byteA) {
        if (r1_File != null) {
            try {
                write(r1_File, r2_byteA);
            } catch (Exception e) {
                report(e);
            }
        }
    }

    public static void storeAsync(File r5_File, byte[] r6_byteA, long r7j) {
        ScheduledExecutorService r0_ScheduledExecutorService = a();
        Common r1_Common = new Common();
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = r5_File;
        r2_ObjectA[1] = r6_byteA;
        r0_ScheduledExecutorService.schedule(r1_Common.method(1, r2_ObjectA), r7j, TimeUnit.MILLISECONDS);
    }

    public static void time(String r3_String) {
        d.put(r3_String, Long.valueOf(System.currentTimeMillis()));
    }

    public static long timeEnd(String r6_String, long r7j) {
        long r2j = 0;
        Long r0_Long = (Long) d.get(r6_String);
        if (r0_Long == null) {
            return 0;
        }
        long r0j = System.currentTimeMillis() - r0_Long.longValue();
        if (r7j != r2j && r0j <= r7j) {
            return r0j;
        }
        debug(r6_String, Long.valueOf(r0j));
        return r0j;
    }

    public static byte[] toBytes(InputStream r2_InputStream) {
        byte[] r0_byteA = null;
        OutputStream r1_OutputStream = new ByteArrayOutputStream();
        try {
            copy(r2_InputStream, r1_OutputStream);
            r0_byteA = r1_OutputStream.toByteArray();
        } catch (IOException e) {
            report(e);
        }
        close(r2_InputStream);
        return r0_byteA;
    }

    public static void transparent(View r1_View, boolean r2z) {
        float r0f = 1.0f;
        if (r2z) {
            r0f = 0.5f;
        }
        a(r1_View, r0f);
    }

    public static void warn(Object r3_Object, Object r4_Object) {
        Log.w("AQuery", r3_Object + ":" + r4_Object);
    }

    public static void write(File r2_File, byte[] r3_byteA) {
        try {
            if (!r2_File.exists()) {
                try {
                    r2_File.createNewFile();
                } catch (Exception e) {
                    debug("file create fail", r2_File);
                    report(e);
                }
            }
            FileOutputStream r0_FileOutputStream = new FileOutputStream(r2_File);
            r0_FileOutputStream.write(r3_byteA);
            r0_FileOutputStream.close();
        } catch (Exception e_2) {
            report(e_2);
        }
    }
}