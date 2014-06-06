package com.google.analytics.tracking.android;

import android.util.Log;

// compiled from: Log.java
class z {
    private static boolean a;

    static int a(String r2_String) {
        return Log.d("GAV2", j(r2_String));
    }

    static void a(boolean r0z) {
        a = r0z;
    }

    static boolean a() {
        return a;
    }

    static int b(String r1_String) {
        return a ? a(r1_String) : 0;
    }

    static int c(String r2_String) {
        return Log.e("GAV2", j(r2_String));
    }

    static int d(String r2_String) {
        return Log.i("GAV2", j(r2_String));
    }

    static int e(String r1_String) {
        return a ? d(r1_String) : 0;
    }

    static int f(String r2_String) {
        return Log.v("GAV2", j(r2_String));
    }

    static int g(String r1_String) {
        return a ? f(r1_String) : 0;
    }

    static int h(String r2_String) {
        return Log.w("GAV2", j(r2_String));
    }

    static int i(String r1_String) {
        return a ? h(r1_String) : 0;
    }

    private static String j(String r2_String) {
        return Thread.currentThread().toString() + ": " + r2_String;
    }
}