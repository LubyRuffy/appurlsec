package com.tencent.cloudsdk;

// compiled from: SourceFile
public class ds extends dh {
    private dq a;
    private dt b;

    public ds(du r7_du, dn r8_dn, eb r9_eb, eb r10_eb) {
        if (this.b == null) {
            this.b = new dt();
        }
        this.a = new dq(r7_du, r8_dn, this.b, r9_eb, r10_eb);
    }

    protected dk d() {
        return this.a;
    }

    protected dj e() {
        if (this.b == null) {
            this.b = new dt();
        }
        return this.b;
    }

    public dt f() {
        return this.b;
    }
}