package com.tencent.cloudsdk;

import java.io.IOException;
import java.io.InputStream;

// compiled from: SourceFile
public class ek extends InputStream {
    private InputStream a;
    private String b;
    private String c;
    private int d;

    public ek(InputStream r1_InputStream, String r2_String, String r3_String, int r4i) {
        this.a = r1_InputStream;
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

    public int available() throws IOException {
        return this.a.available();
    }

    public void b(String r1_String) {
        this.c = r1_String;
    }

    public void close() throws IOException {
        this.a.close();
    }

    public void mark(int r2i) {
        this.a.mark(r2i);
    }

    public boolean markSupported() {
        return this.a.markSupported();
    }

    public int read() throws IOException {
        long r9j = System.currentTimeMillis();
        try {
            int r8i = this.a.read();
            if (r8i >= 0) {
                cz.b().b(this.b, this.c, this.d, 0, (long) r8i, (int) (System.currentTimeMillis() - r9j));
            }
            return r8i;
        } catch (IOException e) {
            cz.b().b(this.b, this.c, this.d, -1, 0, (int) (System.currentTimeMillis() - r9j));
            throw e;
        }
    }

    public int read(byte[] r12_byteA) throws IOException {
        long r9j = System.currentTimeMillis();
        try {
            int r8i = this.a.read(r12_byteA);
            if (r8i < 0 || r8i >= 1048576) {
                return r8i;
            }
            cz.b().b(this.b, this.c, this.d, 0, (long) r8i, (int) (System.currentTimeMillis() - r9j));
            return r8i;
        } catch (IOException e) {
            cz.b().b(this.b, this.c, this.d, -1, 0, (int) (System.currentTimeMillis() - r9j));
            throw e;
        }
    }

    public int read(byte[] r12_byteA, int r13i, int r14i) throws IOException {
        long r9j = System.currentTimeMillis();
        try {
            int r8i = this.a.read(r12_byteA, r13i, r14i);
            if (r8i < 0 || r8i >= 1048576) {
                return r8i;
            }
            cz.b().b(this.b, this.c, this.d, 0, (long) r8i, (int) (System.currentTimeMillis() - r9j));
            return r8i;
        } catch (IOException e) {
            cz.b().b(this.b, this.c, this.d, -1, 0, (int) (System.currentTimeMillis() - r9j));
            throw e;
        }
    }

    public synchronized void reset() throws IOException {
        this.a.reset();
    }

    public long skip(long r3j) throws IOException {
        return this.a.skip(r3j);
    }
}