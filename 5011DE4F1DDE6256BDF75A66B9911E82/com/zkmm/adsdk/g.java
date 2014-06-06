package com.zkmm.adsdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// compiled from: SourceFile
final class g implements Serializable {
    protected int a;
    protected String b;
    protected String c;
    protected String d;
    protected String e;
    protected int f;
    protected int g;
    protected List h;
    protected List i;
    protected List j;
    protected byte k;
    protected byte l;
    protected ErrorCode m;
    protected boolean n;
    protected String o;
    protected short p;
    protected boolean q;

    protected g() {
        this.a = -1;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 320;
        this.g = 480;
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = (byte) 0;
        this.l = (byte) 0;
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = (short) 0;
        this.q = false;
    }

    protected final void a() {
        new Thread(new ba(this)).start();
    }

    protected final void b() {
        new Thread(new bb(this)).start();
    }

    public final boolean equals(Object r4_Object) {
        if (!(r4_Object instanceof g)) {
            return false;
        }
        g r4_g = (g) r4_Object;
        return this.d != null && r4_g.d != null && this.d.equals(r4_g.d);
    }

    public final int hashCode() {
        return toString().hashCode();
    }
}