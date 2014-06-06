package com.tencent.cloudsdk;

// compiled from: SourceFile
public class eo {
    public static int a(byte[] r4_byteA) {
        int r0i = 0;
        int r1i = 0;
        while (r1i < 3) {
            r0i = r4_byteA[r1i] >= 0 ? r0i + r4_byteA[r1i] : r0i + 256 + r4_byteA[r1i];
            r1i++;
            r0i *= 256;
        }
        return r4_byteA[3] >= 0 ? r0i + r4_byteA[3] : r0i + 256 + r4_byteA[3];
    }

    public static short a(byte r1b) {
        return (short) (r1b & 255);
    }

    public static void a(short r2s, byte[] r3_byteA) {
        int r1i = 1;
        if (r3_byteA == null || r3_byteA.length <= 1) {
        } else {
            r3_byteA[r1i] = (byte) (r2s & 255);
            r3_byteA[0] = (byte) ((r2s >> 8) & 255);
        }
    }

    public static byte[] a(int r3i) {
        byte[] r0_byteA = new byte[4];
        r0_byteA[3] = (byte) (r3i & 255);
        r0_byteA[2] = (byte) ((r3i >> 8) & 255);
        r0_byteA[1] = (byte) ((r3i >> 16) & 255);
        r0_byteA[0] = (byte) ((r3i >> 24) & 255);
        return r0_byteA;
    }

    public static byte[] a(short r3s) {
        byte[] r0_byteA = new byte[2];
        r0_byteA[1] = (byte) (r3s & 255);
        r0_byteA[0] = (byte) ((r3s >> 8) & 255);
        return r0_byteA;
    }

    public static int b(byte[] r2_byteA) {
        return (r2_byteA[0] & 255) << 8 + r2_byteA[1] & 255;
    }

    public static short c(byte[] r3_byteA) {
        int r0i = (r3_byteA[0] >= 0 ? r3_byteA[0] + 0 : r3_byteA[0] + 256) * 256;
        return (short) (r3_byteA[1] >= 0 ? r0i + r3_byteA[1] : r0i + 256 + r3_byteA[1]);
    }
}