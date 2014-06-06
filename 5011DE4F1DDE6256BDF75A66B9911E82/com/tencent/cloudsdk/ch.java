package com.tencent.cloudsdk;

// compiled from: SourceFile
public class ch {
    private byte[] a;
    private String b;
    private short c;
    private short d;
    private short e;
    private int f;

    public ch(String r2_String, int r3i, short r4s, short r5s, short r6s) {
        this.f = 0;
        this.b = r2_String;
        this.f = r3i;
        this.c = r4s;
        this.d = r5s;
        this.e = r6s;
    }

    public ch(byte[] r2_byteA) {
        this.f = 0;
        this.a = r2_byteA;
        f();
    }

    private void f() {
        int r1i = 0;
        StringBuilder r2_StringBuilder = new StringBuilder();
        short r0s = (short) 0;
        while (r0s < (short) 4) {
            r2_StringBuilder.append(String.valueOf(eo.a(this.a[r0s])));
            r2_StringBuilder.append(".");
            r0s = (short) (r0s + 1);
        }
        if (r2_StringBuilder.length() > 0) {
            r2_StringBuilder.deleteCharAt(r2_StringBuilder.length() - 1);
        }
        this.b = r2_StringBuilder.toString();
        this.c = eo.a(this.a[4]);
        this.d = eo.a(this.a[5]);
        this.e = eo.a(this.a[6]);
        byte[] r0_byteA = new byte[2];
        r0_byteA[r1i] = this.a[8];
        r0_byteA[1] = this.a[9];
        this.f = eo.b(r0_byteA);
    }

    public String a() {
        return this.b;
    }

    public short b() {
        return this.c;
    }

    public short c() {
        return this.d;
    }

    public short d() {
        return this.e;
    }

    public int e() {
        return this.f;
    }
}