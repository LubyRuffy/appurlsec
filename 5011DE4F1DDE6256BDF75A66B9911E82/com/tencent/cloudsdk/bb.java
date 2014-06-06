package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;

// compiled from: SourceFile
public class bb {
    private static final String a;

    static {
        a = bb.class.getName();
    }

    public static cl a(String r1_String, boolean r2z) {
        return a(r1_String, r2z, null);
    }

    public static cl a(String r8_String, boolean r9z, bc r10_bc) {
        bv.m();
        cf.a(GlobalContext.getContext()).b();
        er.a(a, new StringBuilder(">>>\u4eceANS\u67e5\u8be2\u57df\u540d\u4fe1\u606f:").append(r8_String).toString());
        long r0j = System.currentTimeMillis();
        er.a(a, new StringBuilder(">>>\u67e5\u8be2\u5f00\u59cb\u65f6\u95f4:").append(r0j).toString());
        ay r2_ay = new ay(r8_String);
        r2_ay.a(r10_bc);
        cl r2_cl = r2_ay.a(r9z);
        long r3j = System.currentTimeMillis();
        er.a(a, new StringBuilder(">>>\u67e5\u8be2\u7ed3\u675f\u65f6\u95f4:").append(r3j).append(", \u8017\u65f6:").append(r3j - r0j).toString());
        return r2_cl;
    }
}