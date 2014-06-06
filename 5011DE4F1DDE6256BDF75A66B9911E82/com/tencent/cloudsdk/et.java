package com.tencent.cloudsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

// compiled from: SourceFile
public class et {
    private static final String a;
    private static long b;
    private static int c;

    static {
        a = et.class.getSimpleName();
        b = 600000;
        c = 15000;
    }

    public static long a() {
        return b;
    }

    public static long a(Context r4_Context, String r5_String) {
        return a(r4_Context).getLong(em.a(r5_String), 0);
    }

    private static SharedPreferences a(Context r2_Context) {
        return r2_Context.getSharedPreferences("expried", 0);
    }

    public static void a(long r0j) {
        b = r0j;
    }

    public static void a(Context r2_Context, String r3_String, long r4j) {
        Editor r0_Editor = a(r2_Context).edit();
        r0_Editor.putLong(em.a(r3_String), r4j);
        r0_Editor.commit();
    }

    public static int b() {
        return bv.c().d() == 0 ? c : bv.c().d();
    }

    public static boolean b(Context r7_Context, String r8_String) {
        long r1j = a(r7_Context, r8_String);
        long r3j = System.currentTimeMillis();
        if (r1j == 0 || r1j > r3j) {
            return true;
        }
        if (r1j + a() < r3j) {
            er.a(a, ">>>\u6d4b\u901f\u7ed3\u679c\u5df2\u8fc7\u671f<<<");
            return true;
        } else {
            er.a(a, ">>>\u6d4b\u901f\u7ed3\u679c\u672a\u8fc7\u671f\uff0c\u7b49\u5f85\u4e0b\u6b21\u89e6\u53d1<<<");
            return false;
        }
    }

    public static void c(Context r2_Context, String r3_String) {
        a(r2_Context, r3_String, 0);
    }
}