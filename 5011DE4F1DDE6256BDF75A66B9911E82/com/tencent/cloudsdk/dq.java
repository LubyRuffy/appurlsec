package com.tencent.cloudsdk;

// compiled from: SourceFile
public class dq implements dk {
    private dw a;
    private dp b;

    public dq(du r2_du, dn r3_dn, dt r4_dt, eb r5_eb, eb r6_eb) {
        this.a = new dw(r2_du, r4_dt, r5_eb);
        this.b = new dp(r3_dn, r4_dt, r6_eb);
    }

    public void a() {
        this.a.a();
        this.b.a();
    }

    public void b() {
        this.a.b();
        this.b.b();
    }

    public void c() {
        this.a.c();
        this.b.c();
    }
}