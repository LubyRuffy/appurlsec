package com.crashlytics.android.internal;

// compiled from: SourceFile
final class bw {
    static final bw a;
    final int b;
    final int c;

    static {
        a = new bw(0, 0);
    }

    bw(int r1i, int r2i) {
        this.b = r1i;
        this.c = r2i;
    }

    public final String toString() {
        return getClass().getSimpleName() + "[position = " + this.b + ", length = " + this.c + "]";
    }
}