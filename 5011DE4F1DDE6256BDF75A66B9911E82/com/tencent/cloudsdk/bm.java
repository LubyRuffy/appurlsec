package com.tencent.cloudsdk;

// compiled from: SourceFile
public class bm implements bs {
    bf a;

    public bm() {
        this.a = new bf((short) 0, (short) 0, (short) -1, (short) 0);
    }

    public bf a() {
        return this.a;
    }

    public void a(br r8_br) {
        byte[] r0_byteA = r8_br.a();
        if (r0_byteA == null || r0_byteA.length < 5) {
        } else {
            int r1i = r8_br.b();
            byte[] r2_byteA = new byte[2];
            int r4i = r1i + 1;
            r2_byteA[0] = r0_byteA[r1i];
            int r3i = r4i + 1;
            r2_byteA[1] = r0_byteA[r4i];
            r4i = r3i + 1;
            int r5i = r4i + 1;
            this.a = new bf(eo.c(r2_byteA), eo.a(r0_byteA[r3i]), eo.a(r0_byteA[r4i]), eo.a(r0_byteA[r5i]));
            r8_br.a(r5i + 1);
        }
    }
}