package com.tencent.cloudsdk;

import java.io.IOException;
import java.io.OutputStream;

// compiled from: SourceFile
public class el extends OutputStream {
    private OutputStream a;
    private String b;
    private String c;
    private int d;

    public el(OutputStream r1_OutputStream, String r2_String, String r3_String, int r4i) {
        this.a = r1_OutputStream;
        a(r2_String);
        b(r3_String);
        a(r4i);
    }

    public void a(int r1i) {
        this.d = r1i;
    }

    public void a(String r1_String) {
        this.b = r1_String;
    }

    public void b(String r1_String) {
        this.c = r1_String;
    }

    public void close() throws IOException {
        this.a.close();
    }

    public void flush() throws IOException {
        this.a.flush();
    }

    public void write(int r10i) throws IOException {
        long r1j = System.currentTimeMillis();
        try {
            this.a.write(r10i);
            cz.b().a(this.b, this.c, this.d, 0, 1, (int) (System.currentTimeMillis() - r1j));
        } catch (IOException e) {
            cz.b().a(this.b, this.c, this.d, -1, 1, (int) (System.currentTimeMillis() - r1j));
            throw e;
        }
    }

    public void write(byte[] r10_byteA) throws IOException {
        long r1j = System.currentTimeMillis();
        try {
            this.a.write(r10_byteA);
            cz.b().a(this.b, this.c, this.d, 0, (long) r10_byteA.length, (int) (System.currentTimeMillis() - r1j));
        } catch (IOException e) {
            cz.b().a(this.b, this.c, this.d, -1, (long) r10_byteA.length, (int) (System.currentTimeMillis() - r1j));
            throw e;
        }
    }

    public void write(byte[] r10_byteA, int r11i, int r12i) throws IOException {
        long r1j = System.currentTimeMillis();
        try {
            this.a.write(r10_byteA, r11i, r12i);
            cz.b().a(this.b, this.c, this.d, 0, (long) r12i, (int) (System.currentTimeMillis() - r1j));
        } catch (IOException e) {
            cz.b().a(this.b, this.c, this.d, -1, (long) r12i, (int) (System.currentTimeMillis() - r1j));
            throw e;
        }
    }
}