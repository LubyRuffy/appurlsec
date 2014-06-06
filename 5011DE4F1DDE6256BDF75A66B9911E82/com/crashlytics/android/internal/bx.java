package com.crashlytics.android.internal;

import java.io.IOException;
import java.io.InputStream;

// compiled from: SourceFile
final class bx extends InputStream {
    private int a;
    private int b;
    private /* synthetic */ aq c;

    private bx(aq r2_aq, bw r3_bw) {
        this.c = r2_aq;
        this.a = r2_aq.b(r3_bw.b + 4);
        this.b = r3_bw.c;
    }

    public final int read() throws IOException {
        if (this.b == 0) {
            return -1;
        }
        this.c.b.seek((long) this.a);
        int r0i = this.c.b.read();
        this.a = this.c.b(this.a + 1);
        this.b--;
        return r0i;
    }

    public final int read(byte[] r3_byteA, int r4i, int r5i) throws IOException {
        aq.b(r3_byteA, "buffer");
        if ((r4i | r5i) < 0 || r5i > r3_byteA.length - r4i) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            if (this.b <= 0) {
                return -1;
            }
            if (r5i > this.b) {
                r5i = this.b;
            }
            this.c.b(this.a, r3_byteA, r4i, r5i);
            this.a = this.c.b(this.a + r5i);
            this.b -= r5i;
            return r5i;
        }
    }
}