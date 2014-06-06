package com.tencent.cloudsdk;

// compiled from: SourceFile
public class bf {
    private short a;
    private short b;
    private short c;
    private byte[] d;
    private short e;

    private bf() {
        this.c = (short) 0;
        this.d = new byte[5];
        this.e = (short) 1;
        this.a = (short) 0;
        this.b = (short) 0;
        this.c = (short) -1;
        this.e = (short) 0;
    }

    public bf(short r2s, short r3s) {
        this.c = (short) 0;
        this.d = new byte[5];
        this.e = (short) 1;
        this.a = r2s;
        this.b = r3s;
        c();
    }

    public bf(short r2s, short r3s, short r4s, short r5s) {
        this.c = (short) 0;
        this.d = new byte[5];
        this.e = (short) 1;
        this.a = r2s;
        this.b = r3s;
        this.c = r4s;
        this.e = r5s;
        c();
    }

    public static bf a(byte[] r2_byteA) {
        bm r0_bm = new bm();
        br r1_br = new br();
        r1_br.a(r2_byteA);
        r0_bm.a(r1_br);
        return r0_bm.a();
    }

    private void c() {
        eo.a(this.a, this.d);
        this.d[2] = (byte) this.b;
        this.d[3] = (byte) this.c;
        this.d[4] = (byte) this.e;
    }

    public byte[] a() {
        return this.d;
    }

    public short b() {
        return this.c;
    }
}