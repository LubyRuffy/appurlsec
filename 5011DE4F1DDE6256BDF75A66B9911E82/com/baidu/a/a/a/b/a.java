package com.baidu.a.a.a.b;

import android.content.Context;
import android.text.TextUtils;

public class a {
    private static final String a;

    static {
        a = a.class.getSimpleName();
    }

    public static String a_(Context r3_Context) {
        String r1_String = b(r3_Context);
        String r0_String = b.b(r3_Context);
        if (TextUtils.isEmpty(r0_String)) {
            r0_String = "0";
        }
        return r1_String + "|" + new StringBuffer(r0_String).reverse().toString();
    }

    private static String b(Context r1_Context) {
        return b.a(r1_Context);
    }
}