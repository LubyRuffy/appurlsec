package com.zkmm.adsdk;

import java.util.ArrayList;
import java.util.List;

// compiled from: SourceFile
final class j {
    protected int a;
    protected ErrorCode b;
    protected String c;
    protected String d;
    protected List e;
    protected List f;
    protected List g;
    protected boolean h;
    protected boolean i;
    protected boolean j;
    protected String k;
    protected short l;
    protected byte m;

    protected j() {
        this.a = 0;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = true;
        this.i = false;
        this.j = false;
        this.k = null;
        this.l = (short) 0;
        this.m = (byte) 0;
    }

    public final ErrorCode a() {
        return this.b;
    }

    protected final void a(ErrorCode r1_ErrorCode) {
        this.b = r1_ErrorCode;
    }

    protected final String b() {
        return this.d;
    }

    public final boolean equals(Object r4_Object) {
        if (!(r4_Object instanceof j)) {
            return false;
        }
        j r4_j = (j) r4_Object;
        return this.d != null && r4_j.d != null && this.d.equals(r4_j.d);
    }

    public final int hashCode() {
        return toString().hashCode();
    }
}