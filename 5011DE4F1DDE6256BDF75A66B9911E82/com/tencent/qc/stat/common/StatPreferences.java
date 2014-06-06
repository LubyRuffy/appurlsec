package com.tencent.qc.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

// compiled from: ProGuard
public class StatPreferences {
    private static SharedPreferences a;

    static {
        a = null;
    }

    public static long a(Context r2_Context, String r3_String, long r4j) {
        return a(r2_Context).getLong("qc_" + r3_String, r4j);
    }

    static SharedPreferences a(Context r1_Context) {
        if (a == null) {
            a = PreferenceManager.getDefaultSharedPreferences(r1_Context);
        }
        return a;
    }

    public static void b(Context r2_Context, String r3_String, long r4j) {
        String r0_String = "qc_" + r3_String;
        Editor r1_Editor = a(r2_Context).edit();
        r1_Editor.putLong(r0_String, r4j);
        r1_Editor.commit();
    }
}