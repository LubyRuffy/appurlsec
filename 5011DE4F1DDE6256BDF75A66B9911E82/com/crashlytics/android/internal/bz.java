package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// compiled from: SourceFile
final class bz extends aA<ay> {
    private /* synthetic */ InputStream a;
    private /* synthetic */ OutputStream b;
    private /* synthetic */ ay c;

    bz(ay r1_ay, Closeable r2_Closeable, boolean r3z, InputStream r4_InputStream, OutputStream r5_OutputStream) {
        this.c = r1_ay;
        this.a = r4_InputStream;
        this.b = r5_OutputStream;
        super(r2_Closeable, r3z);
    }

    public final /* synthetic */ Object a() throws aD, IOException {
        byte[] r0_byteA = new byte[this.c.i];
        while (true) {
            int r1i = this.a.read(r0_byteA);
            if (r1i == -1) {
                return this.c;
            }
            this.b.write(r0_byteA, 0, r1i);
        }
    }
}