package com.tencent.cloudsdk;

import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class cs {
    private byte[] a;
    private int b;
    private int c;
    private int d;
    private int e;

    public cs(byte[] r3_byteA) {
        this.a = r3_byteA;
        this.b = 0;
        this.c = this.a.length;
        this.d = -1;
        this.e = -1;
    }

    private void c(int r3i) throws cy {
        if (r3i > b()) {
            throw new cy("end of input");
        }
    }

    public int a() {
        return this.b;
    }

    public void a(int r3i) {
        if (r3i > this.a.length - this.b) {
            throw new IllegalArgumentException("cannot set active region past end of input");
        } else {
            this.c = this.b + r3i;
        }
    }

    public void a(byte[] r3_byteA, int r4i, int r5i) throws cy {
        c(r5i);
        System.arraycopy(this.a, this.b, r3_byteA, r4i, r5i);
        this.b += r5i;
    }

    public int b() {
        return this.c - this.b;
    }

    public void b(int r3i) {
        if (r3i >= this.a.length) {
            throw new IllegalArgumentException("cannot jump past end of input");
        } else {
            this.b = r3i;
            this.c = this.a.length;
        }
    }

    public void c() {
        this.d = this.b;
        this.e = this.c;
    }

    public void d() {
        if (this.d < 0) {
            throw new IllegalStateException("no previous state");
        } else {
            this.b = this.d;
            this.c = this.e;
            this.d = -1;
            this.e = -1;
        }
    }

    public int e() throws cy {
        c(1);
        byte[] r0_byteA = this.a;
        int r1i = this.b;
        this.b = r1i + 1;
        return r0_byteA[r1i] & 255;
    }

    public int f() throws cy {
        c(XListViewHeader.STATE_REFRESHING);
        byte[] r0_byteA = this.a;
        int r1i = this.b;
        this.b = r1i + 1;
        byte[] r1_byteA = this.a;
        int r2i = this.b;
        this.b = r2i + 1;
        return (r0_byteA[r1i] & 255) << 8 + r1_byteA[r2i] & 255;
    }

    public long g() throws cy {
        c(XListViewFooter.STATE_NODATA);
        byte[] r0_byteA = this.a;
        int r1i = this.b;
        this.b = r1i + 1;
        byte[] r1_byteA = this.a;
        int r2i = this.b;
        this.b = r2i + 1;
        byte[] r2_byteA = this.a;
        int r3i = this.b;
        this.b = r3i + 1;
        byte[] r3_byteA = this.a;
        int r4i = this.b;
        this.b = r4i + 1;
        return ((long) ((r1_byteA[r2i] & 255) << 16)) + ((long) (r0_byteA[r1i] & 255)) << 24 + ((long) ((r2_byteA[r3i] & 255) << 8)) + ((long) (r3_byteA[r4i] & 255));
    }

    public byte[] h() {
        int r0i = b();
        Object r1_Object = new Object[r0i];
        System.arraycopy(this.a, this.b, r1_Object, 0, r0i);
        this.b = r0i + this.b;
        this.c = this.a.length - 1;
        return r1_Object;
    }
}