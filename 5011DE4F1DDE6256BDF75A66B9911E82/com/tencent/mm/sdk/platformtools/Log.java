package com.tencent.mm.sdk.platformtools;

import android.os.Build;
import android.os.Build.VERSION;
import android.widget.Toast;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.algorithm.MD5;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import qsbk.app.share.ShareUtils;

public class Log {
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_NONE = 6;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static int a;
    private static PrintStream b;
    private static byte[] c;
    private static final String d;

    static {
        a = 0;
        c = null;
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append(new StringBuilder("VERSION.RELEASE:[").append(VERSION.RELEASE).toString());
        r0_StringBuilder.append(new StringBuilder("] VERSION.CODENAME:[").append(VERSION.CODENAME).toString());
        r0_StringBuilder.append(new StringBuilder("] VERSION.INCREMENTAL:[").append(VERSION.INCREMENTAL).toString());
        r0_StringBuilder.append(new StringBuilder("] BOARD:[").append(Build.BOARD).toString());
        r0_StringBuilder.append(new StringBuilder("] DEVICE:[").append(Build.DEVICE).toString());
        r0_StringBuilder.append(new StringBuilder("] DISPLAY:[").append(Build.DISPLAY).toString());
        r0_StringBuilder.append(new StringBuilder("] FINGERPRINT:[").append(Build.FINGERPRINT).toString());
        r0_StringBuilder.append(new StringBuilder("] HOST:[").append(Build.HOST).toString());
        r0_StringBuilder.append(new StringBuilder("] MANUFACTURER:[").append(Build.MANUFACTURER).toString());
        r0_StringBuilder.append(new StringBuilder("] MODEL:[").append(Build.MODEL).toString());
        r0_StringBuilder.append(new StringBuilder("] PRODUCT:[").append(Build.PRODUCT).toString());
        r0_StringBuilder.append(new StringBuilder("] TAGS:[").append(Build.TAGS).toString());
        r0_StringBuilder.append(new StringBuilder("] TYPE:[").append(Build.TYPE).toString());
        r0_StringBuilder.append(new StringBuilder("] USER:[").append(Build.USER).append("]").toString());
        d = r0_StringBuilder.toString();
    }

    protected Log() {
    }

    public static void d(String r1_String, String r2_String) {
        d(r1_String, r2_String, null);
    }

    public static void d(String r4_String, String r5_String, Object ... r6_ObjectA) {
        if (a <= 1) {
            if (r6_ObjectA == null) {
                android.util.Log.d(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("D/").append(r4_String).toString(), r5_String);
            } else {
                r5_String = String.format(r5_String, r6_ObjectA);
                android.util.Log.d(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("D/").append(r4_String).toString(), r5_String);
            }
        }
    }

    public static void e(String r1_String, String r2_String) {
        e(r1_String, r2_String, null);
    }

    public static void e(String r4_String, String r5_String, Object ... r6_ObjectA) {
        if (a <= 4) {
            if (r6_ObjectA == null) {
                android.util.Log.e(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("E/").append(r4_String).toString(), r5_String);
            } else {
                r5_String = String.format(r5_String, r6_ObjectA);
                android.util.Log.e(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("E/").append(r4_String).toString(), r5_String);
            }
        }
    }

    public static void f(String r1_String, String r2_String) {
        f(r1_String, r2_String, null);
    }

    public static void f(String r4_String, String r5_String, Object ... r6_ObjectA) {
        if (a <= 5) {
            if (r6_ObjectA == null) {
                android.util.Log.e(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("F/").append(r4_String).toString(), r5_String);
                Toast.makeText(MMApplicationContext.getContext(), r5_String, LEVEL_DEBUG).show();
            } else {
                r5_String = String.format(r5_String, r6_ObjectA);
                android.util.Log.e(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("F/").append(r4_String).toString(), r5_String);
                Toast.makeText(MMApplicationContext.getContext(), r5_String, LEVEL_DEBUG).show();
            }
        }
    }

    public static int getLevel() {
        return a;
    }

    public static String getSysInfo() {
        return d;
    }

    public static void i(String r1_String, String r2_String) {
        i(r1_String, r2_String, null);
    }

    public static void i(String r4_String, String r5_String, Object ... r6_ObjectA) {
        if (a <= 2) {
            if (r6_ObjectA == null) {
                android.util.Log.i(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("I/").append(r4_String).toString(), r5_String);
            } else {
                r5_String = String.format(r5_String, r6_ObjectA);
                android.util.Log.i(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("I/").append(r4_String).toString(), r5_String);
            }
        }
    }

    public static void reset() {
        b = null;
        c = null;
    }

    public static void setLevel(int r3i, boolean r4z) {
        a = r3i;
        android.util.Log.w("MicroMsg.SDK.Log", new StringBuilder("new log level: ").append(r3i).toString());
        if (r4z) {
            android.util.Log.e("MicroMsg.SDK.Log", "no jni log level support");
        }
    }

    public static void setOutputPath(String r4_String, String r5_String, String r6_String, int r7i) {
        if (r4_String == null || r4_String.length() == 0 || r6_String == null || r6_String.length() == 0) {
        } else {
            try {
                File r0_File = new File(r4_String);
                if (r0_File.exists()) {
                    InputStream r0_InputStream;
                    r0_InputStream = (r0_File.length() > 0 ? 1 : (r0_File.length() == 0? 0 : -1)) > 0 ? new FileInputStream(r4_String) : null;
                    setOutputStream(r0_InputStream, new FileOutputStream(r4_String, true), r5_String, r6_String, r7i);
                    r0_InputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setOutputStream(InputStream r7_InputStream, OutputStream r8_OutputStream, String r9_String, String r10_String, int r11i) {
        long r3j;
        try {
            long r3j_2;
            b = new PrintStream(new BufferedOutputStream(r8_OutputStream));
            if (r7_InputStream != null) {
                BufferedReader r0_BufferedReader = new BufferedReader(new InputStreamReader(r7_InputStream));
                String r1_String = r0_BufferedReader.readLine().substring(LEVEL_INFO).trim();
                r10_String = r0_BufferedReader.readLine().substring(LEVEL_INFO).trim();
                r3j_2 = Util.getLong(r0_BufferedReader.readLine().trim().substring(LEVEL_INFO), 0);
                Object[] r5_ObjectA = new Object[3];
                r5_ObjectA[0] = r1_String;
                r5_ObjectA[1] = r10_String;
                r5_ObjectA[2] = Long.valueOf(r3j_2);
                d("MicroMsg.SDK.Log", "using provided info, type=%s, user=%s, createtime=%d", r5_ObjectA);
            } else {
                r3j_2 = System.currentTimeMillis();
                f.initLogHeader(b, r9_String, r10_String, r3j_2, r11i);
            }
            StringBuffer r0_StringBuffer = new StringBuffer();
            r0_StringBuffer.append(r10_String);
            r0_StringBuffer.append(r3j_2);
            r0_StringBuffer.append("dfdhgc");
            c = MD5.getMessageDigest(r0_StringBuffer.toString().getBytes()).substring(ShareUtils.SHARE_COLLECT, AdViewUtil.NETWORK_TYPE_WOOBOO).getBytes();
            android.util.Log.d("MicroMsg.SDK.Log", "set up out put stream");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void v(String r1_String, String r2_String) {
        v(r1_String, r2_String, null);
    }

    public static void v(String r4_String, String r5_String, Object ... r6_ObjectA) {
        if (a <= 0) {
            if (r6_ObjectA == null) {
                android.util.Log.v(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("V/").append(r4_String).toString(), r5_String);
            } else {
                r5_String = String.format(r5_String, r6_ObjectA);
                android.util.Log.v(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("V/").append(r4_String).toString(), r5_String);
            }
        }
    }

    public static void w(String r1_String, String r2_String) {
        w(r1_String, r2_String, null);
    }

    public static void w(String r4_String, String r5_String, Object ... r6_ObjectA) {
        if (a <= 3) {
            if (r6_ObjectA == null) {
                android.util.Log.w(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("W/").append(r4_String).toString(), r5_String);
            } else {
                r5_String = String.format(r5_String, r6_ObjectA);
                android.util.Log.w(r4_String, r5_String);
                f.writeToStream(b, c, new StringBuilder("W/").append(r4_String).toString(), r5_String);
            }
        }
    }
}