package com.tencent.mm.sdk.platformtools;

final class c implements CharSequence {
    final /* synthetic */ byte[] a;

    c(byte[] r1_byteA) {
        this.a = r1_byteA;
    }

    public final char charAt(int r2i) {
        return (char) this.a[r2i];
    }

    public final int length() {
        return this.a.length;
    }

    public final CharSequence subSequence(int r2i, int r3i) {
        return CharSequences.forAsciiBytes(this.a, r2i, r3i);
    }

    public final String toString() {
        return new String(this.a);
    }
}