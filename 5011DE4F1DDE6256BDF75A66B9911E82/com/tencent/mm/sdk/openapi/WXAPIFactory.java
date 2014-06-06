package com.tencent.mm.sdk.openapi;

import android.content.Context;

public class WXAPIFactory {
    private static WXAPIFactory a;

    static {
        a = null;
    }

    private WXAPIFactory() {
    }

    public static IWXAPI createWXAPI(Context r1_Context, String r2_String) {
        if (a == null) {
            a = new WXAPIFactory();
        }
        return new a(r1_Context, r2_String);
    }

    public static IWXAPI createWXAPI(Context r1_Context, String r2_String, boolean r3z) {
        if (a == null) {
            a = new WXAPIFactory();
        }
        return new a(r1_Context, r2_String, r3z);
    }
}