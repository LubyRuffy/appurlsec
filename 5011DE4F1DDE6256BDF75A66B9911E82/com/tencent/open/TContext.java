package com.tencent.open;

import android.content.Context;

// compiled from: ProGuard
public class TContext {
    public String a;
    private String b;
    private String c;
    private long d;
    private String e;
    private Context f;

    public TContext(String r3_String, Context r4_Context) {
        this.d = -1;
        this.a = "webview";
        this.b = r3_String;
        a(r4_Context);
    }

    public void a(Context r1_Context) {
        this.f = r1_Context;
    }

    public void a(String r1_String) {
        this.e = r1_String;
    }

    public void a(String r7_String, String r8_String) {
        this.d = 0;
        this.c = null;
        if (r8_String == null) {
            r8_String = "0";
        }
        this.d = System.currentTimeMillis() + Long.parseLong(r8_String) * 1000;
        this.c = r7_String;
    }

    public boolean a() {
        return this.c != null && System.currentTimeMillis() < this.d;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.b;
    }

    public long e() {
        return this.d;
    }

    public Context f() {
        return this.f;
    }
}