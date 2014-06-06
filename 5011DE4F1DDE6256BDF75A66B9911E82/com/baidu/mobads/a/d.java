package com.baidu.mobads.a;

import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public final class d {
    private static final SimpleDateFormat a;

    static {
        a = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        a();
    }

    public static int a(String r1_String) {
        if (!a((int)XListViewFooter.STATE_NOMORE)) {
            return -1;
        }
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        a("Mobads Proxy", r1_String);
        return Log.d("Mobads Proxy", r1_String);
    }

    public static int a(String r1_String, Throwable r2_Throwable) {
        if (!a((int)XListViewHeader.STATE_REFRESHING)) {
            return -1;
        }
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        a("Mobads Proxy", r1_String, r2_Throwable);
        return Log.v("Mobads Proxy", r1_String, r2_Throwable);
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

    private static synchronized void a(String r1_String, String r2_String) {
        synchronized (d.class) {
        }
    }

    private static void a(String r4_String, String r5_String, Throwable r6_Throwable) {
        Writer r0_Writer = new StringWriter();
        PrintWriter r1_PrintWriter = new PrintWriter(r0_Writer);
        r6_Throwable.printStackTrace(r1_PrintWriter);
        a(r4_String, new StringBuilder(r5_String).append("\n").append(r0_Writer.toString()).toString());
        r1_PrintWriter.close();
        try {
            r0_Writer.close();
        } catch (IOException e) {
            Log.w("Log.debug", RContactStorage.PRIMARY_KEY, e);
        }
    }

    public static boolean a(int r1i) {
        return a("Mobads Proxy", r1i);
    }

    public static boolean a(String r1_String, int r2i) {
        return r2i >= a.b;
    }

    public static int b(String r1_String) {
        if (!a((int)ShareUtils.SHARE_SMS)) {
            return -1;
        }
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        a("Mobads Proxy", r1_String);
        return Log.w("Mobads Proxy", r1_String);
    }

    public static int b(String r1_String, Throwable r2_Throwable) {
        if (!a((int)XListViewFooter.STATE_NOMORE)) {
            return -1;
        }
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        a("Mobads Proxy", r1_String, r2_Throwable);
        return Log.d("Mobads Proxy", r1_String, r2_Throwable);
    }

    public static int b(Throwable r1_Throwable) {
        return b(RContactStorage.PRIMARY_KEY, r1_Throwable);
    }

    public static int b(Object ... r1_ObjectA) {
        return a((int)ShareUtils.SHARE_COPY) ? c(d(r1_ObjectA)) : -1;
    }

    public static int c(String r1_String) {
        if (!a((int)ShareUtils.SHARE_COPY)) {
            return -1;
        }
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        a("Mobads Proxy", r1_String);
        return Log.e("Mobads Proxy", r1_String);
    }

    public static int c(String r1_String, Throwable r2_Throwable) {
        if (!a((int)ShareUtils.SHARE_SMS)) {
            return -1;
        }
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        a("Mobads Proxy", r1_String, r2_Throwable);
        return Log.w("Mobads Proxy", r1_String, r2_Throwable);
    }

    public static int c(Throwable r1_Throwable) {
        return c(RContactStorage.PRIMARY_KEY, r1_Throwable);
    }

    public static int c(Object ... r1_ObjectA) {
        return a((int)XListViewFooter.STATE_NODATA) ? d(d(r1_ObjectA)) : -1;
    }

    public static int d_(String r1_String) {
        if (!a((int)XListViewFooter.STATE_NODATA)) {
            return -1;
        }
        if (r1_String == null) {
            r1_String = RContactStorage.PRIMARY_KEY;
        }
        a("Mobads Proxy", r1_String);
        return Log.i("Mobads Proxy", r1_String);
    }

    private static String d_(Object[] r5_ObjectA) {
        StringBuilder r2_StringBuilder = new StringBuilder();
        int r3i = r5_ObjectA.length;
        int r1i = 0;
        while (r1i < r3i) {
            String r0_String = r5_ObjectA[r1i];
            if (r0_String == null) {
                r0_String = RContactStorage.PRIMARY_KEY;
            }
            r2_StringBuilder.append(r0_String).append(' ');
            r1i++;
        }
        return r2_StringBuilder.toString();
    }
}