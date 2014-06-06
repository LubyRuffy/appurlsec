package com.flurry.android;

import android.util.Log;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
final class i {
    private static boolean a;
    private static int b;

    static {
        a = false;
        b = 5;
    }

    static int a(String r2_String, String r3_String) {
        return (a || b <= 3) ? 0 : Log.d(r2_String, r3_String);
    }

    static int a(String r2_String, String r3_String, Throwable r4_Throwable) {
        return (a || b <= 3) ? 0 : Log.d(r2_String, r3_String, r4_Throwable);
    }

    static void a() {
        a = true;
    }

    static void a(int r0i) {
        b = r0i;
    }

    static boolean a(String r1_String) {
        return Log.isLoggable(r1_String, XListViewFooter.STATE_NOMORE);
    }

    static int b(String r2_String, String r3_String) {
        return (a || b <= 6) ? 0 : Log.e(r2_String, r3_String);
    }

    static int b(String r2_String, String r3_String, Throwable r4_Throwable) {
        return (a || b <= 6) ? 0 : Log.e(r2_String, r3_String, r4_Throwable);
    }

    static void b() {
        a = false;
    }

    static int c(String r2_String, String r3_String) {
        return (a || b <= 4) ? 0 : Log.i(r2_String, r3_String);
    }

    static int c(String r2_String, String r3_String, Throwable r4_Throwable) {
        return (a || b <= 4) ? 0 : Log.i(r2_String, r3_String, r4_Throwable);
    }

    static int d(String r2_String, String r3_String) {
        return (a || b <= 5) ? 0 : Log.w(r2_String, r3_String);
    }

    static int d(String r2_String, String r3_String, Throwable r4_Throwable) {
        return (a || b <= 5) ? 0 : Log.w(r2_String, r3_String, r4_Throwable);
    }
}