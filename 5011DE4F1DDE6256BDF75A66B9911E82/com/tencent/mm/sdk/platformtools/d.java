package com.tencent.mm.sdk.platformtools;

final class d implements CharSequence {
    final /* synthetic */ byte[] a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;

    d(byte[] r1_byteA, int r2i, int r3i) {
        this.a = r1_byteA;
        this.b = r2i;
        this.c = r3i;
    }

    public final char charAt(int r3i) {
        return (char) this.a[this.b + r3i];
    }

    public final int length() {
        return this.c - this.b;
    }

    public final CharSequence subSequence(int r4i, int r5i) {
        int r0i = r4i - this.b;
        int r1i = r5i - this.b;
        CharSequences.a(r0i, r1i, length());
        return CharSequences.forAsciiBytes(this.a, r0i, r1i);
    }

    public final String toString() {
        return new String(this.a, this.b, length());
    }
}