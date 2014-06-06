package com.tencent.qc.stat;

// compiled from: ProGuard
class j {
    private static int[] a;
    private static int[] b;

    static {
        a = new int[256];
        b = new int[256];
    }

    public static byte[] a(byte[] r7_byteA, byte[] r8_byteA) {
        int r0i = 0;
        int r2i = r8_byteA.length;
        if (r2i < 1 || r2i > 256) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        } else {
            int r1i = 0;
            while (r1i < 256) {
                a[r1i] = r1i;
                b[r1i] = r8_byteA[r1i % r2i];
                r1i++;
            }
            r1i = 0;
            r2i = 0;
            while (r1i < 256) {
                r2i = ((r2i + a[r1i]) + b[r1i]) & 255;
                int r3i = a[r1i];
                a[r1i] = a[r2i];
                a[r2i] = r3i;
                r1i++;
            }
            byte[] r3_byteA = new byte[r7_byteA.length];
            r1i = 0;
            r2i = 0;
            while (r0i < r7_byteA.length) {
                r2i = (r2i + 1) & 255;
                r1i = (r1i + a[r2i]) & 255;
                int r4i = a[r2i];
                a[r2i] = a[r1i];
                a[r1i] = r4i;
                r3_byteA[r0i] = (byte) (a[(a[r2i] + a[r1i]) & 255] ^ r7_byteA[r0i]);
                r0i++;
            }
            return r3_byteA;
        }
    }

    public static byte[] b(byte[] r1_byteA, byte[] r2_byteA) {
        return a(r1_byteA, r2_byteA);
    }
}