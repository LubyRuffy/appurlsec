package com.baidu.mobstat.a;

import android.text.format.DateFormat;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;

public final class c {
    private static DateFormat a;

    static {
        a = null;
        a();
        a = new DateFormat();
    }

    public static int a(String r1_String) {
        return a("sdkstat", r1_String);
    }

    public static int a(String r1_String, String r2_String) {
        if (!a((int)XListViewFooter.STATE_NOMORE)) {
            return -1;
        }
        b(r1_String, r2_String);
        return Log.d(r1_String, r2_String);
    }

    public static int a(String r1_String, Throwable r2_Throwable) {
        if (!a((int)XListViewFooter.STATE_NOMORE)) {
            return -1;
        }
        a("sdkstat", r1_String, r2_Throwable);
        return Log.d("sdkstat", r1_String, r2_Throwable);
    }

    public static int a(Throwable r1_Throwable) {
        return a(RContactStorage.PRIMARY_KEY, r1_Throwable);
    }

    public static int a(Object ... r1_ObjectA) {
        return a((int)XListViewFooter.STATE_NOMORE) ? a(d(r1_ObjectA)) : -1;
    }

    public static void a() {
        b.a("_b_sdk.log");
    }

    private static void a(String r4_String, String r5_String, Throwable r6_Throwable) {
        Writer r0_Writer = new StringWriter();
        PrintWriter r1_PrintWriter = new PrintWriter(r0_Writer);
        r6_Throwable.printStackTrace(r1_PrintWriter);
        b(r4_String, new StringBuilder(r5_String).append("\n").append(r0_Writer.toString()).toString());
        r1_PrintWriter.close();
        try {
            r0_Writer.close();
        } catch (IOException e) {
            Log.w("Log.debug", RContactStorage.PRIMARY_KEY, e);
        }
    }

    public static boolean a(int r1i) {
        return a("sdkstat", r1i);
    }

    public static boolean a(String r1_String, int r2i) {
        return r2i >= a.a;
    }

    public static int b(String r1_String) {
        if (!a((int)ShareUtils.SHARE_SMS)) {
            return -1;
        }
        b("sdkstat", r1_String);
        return Log.w("sdkstat", r1_String);
    }

    public static int b(String r1_String, Throwable r2_Throwable) {
        if (!a((int)ShareUtils.SHARE_COPY)) {
            return -1;
        }
        a("sdkstat", r1_String, r2_Throwable);
        return Log.e("sdkstat", r1_String, r2_Throwable);
    }

    public static int b(Throwable r1_Throwable) {
        return b(RContactStorage.PRIMARY_KEY, r1_Throwable);
    }

    public static int b(Object ... r1_ObjectA) {
        return a((int)ShareUtils.SHARE_SMS) ? b(d(r1_ObjectA)) : -1;
    }

    private static synchronized void b(String r1_String, String r2_String) {
        synchronized (c.class) {
        }
    }

    public static int c_(String r1_String) {
        if (!a((int)ShareUtils.SHARE_COPY)) {
            return -1;
        }
        b("sdkstat", r1_String);
        return Log.e("sdkstat", r1_String);
    }

    public static int c_(Object ... r1_ObjectA) {
        return a((int)ShareUtils.SHARE_COPY) ? c(d(r1_ObjectA)) : -1;
    }

    private static String d(Object[] r5_ObjectA) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        int r2i = r5_ObjectA.length;
        int r0i = 0;
        while (r0i < r2i) {
            r1_StringBuilder.append(r5_ObjectA[r0i]).append(' ');
            r0i++;
        }
        return r1_StringBuilder.toString();
    }
}