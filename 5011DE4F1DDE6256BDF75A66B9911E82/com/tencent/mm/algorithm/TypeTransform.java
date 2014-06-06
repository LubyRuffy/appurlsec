package com.tencent.mm.algorithm;

public final class TypeTransform {
    private TypeTransform() {
    }

    private static byte[] a(byte[] r4_byteA) {
        int r1i = r4_byteA.length;
        byte[] r2_byteA = new byte[r1i];
        int r0i = 0;
        while (r0i < r1i) {
            r2_byteA[r0i] = r4_byteA[r1i - 1 - r0i];
            r0i++;
        }
        return r2_byteA;
    }

    public static int byteArrayHLToInt(byte[] r1_byteA) {
        return byteArrayHLToInt(r1_byteA, 0);
    }

    public static int byteArrayHLToInt(byte[] r2_byteA, int r3i) {
        return ((((r2_byteA[r3i] & 255) << 24) | ((r2_byteA[r3i + 1] & 255) << 16)) | ((r2_byteA[r3i + 2] & 255) << 8)) | ((r2_byteA[r3i + 3] & 255) << 0);
    }

    public static long byteArrayHLToLong(byte[] r2_byteA) {
        return byteArrayHLToLong(r2_byteA, 0);
    }

    public static long byteArrayHLToLong(byte[] r7_byteA, int r8i) {
        return ((((((((((long) r7_byteA[r8i]) & 255) << 56) | ((((long) r7_byteA[r8i + 1]) & 255) << 48)) | ((((long) r7_byteA[r8i + 2]) & 255) << 40)) | ((((long) r7_byteA[r8i + 3]) & 255) << 32)) | ((((long) r7_byteA[r8i + 4]) & 255) << 24)) | ((((long) r7_byteA[r8i + 5]) & 255) << 16)) | ((((long) r7_byteA[r8i + 6]) & 255) << 8)) | ((((long) r7_byteA[r8i + 7]) & 255) << 0);
    }

    public static int byteArrayLHToInt(byte[] r1_byteA) {
        return byteArrayLHToInt(r1_byteA, 0);
    }

    public static int byteArrayLHToInt(byte[] r2_byteA, int r3i) {
        return ((((r2_byteA[r3i + 3] & 255) << 24) | ((r2_byteA[r3i + 2] & 255) << 16)) | ((r2_byteA[r3i + 1] & 255) << 8)) | ((r2_byteA[r3i] & 255) << 0);
    }

    public static long byteArrayLHToLong(byte[] r2_byteA) {
        return byteArrayLHToLong(r2_byteA, 0);
    }

    public static long byteArrayLHToLong(byte[] r7_byteA, int r8i) {
        return ((((((((((long) r7_byteA[r8i + 7]) & 255) << 56) | ((((long) r7_byteA[r8i + 6]) & 255) << 48)) | ((((long) r7_byteA[5]) & 255) << 40)) | ((((long) r7_byteA[r8i + 4]) & 255) << 32)) | ((((long) r7_byteA[r8i + 3]) & 255) << 24)) | ((((long) r7_byteA[r8i + 2]) & 255) << 16)) | ((((long) r7_byteA[r8i + 1]) & 255) << 8)) | ((((long) r7_byteA[r8i]) & 255) << 0);
    }

    public static byte[] intToByteArrayHL(int r1i) {
        return a(intToByteArrayLH(r1i));
    }

    public static byte[] intToByteArrayLH(int r4i) {
        byte[] r1_byteA = new byte[4];
        int r0i = 0;
        while (r0i < 4) {
            r1_byteA[r0i] = (byte) ((r4i >> (r0i * 8)) & 255);
            r0i++;
        }
        return r1_byteA;
    }

    public static byte[] longToByteArrayHL(long r1j) {
        return a(longToByteArrayLH(r1j));
    }

    public static byte[] longToByteArrayLH(long r5j) {
        byte[] r1_byteA = new byte[8];
        int r0i = 0;
        while (r0i < 8) {
            r1_byteA[r0i] = (byte) ((int) (r5j >> (r0i * 8)));
            r0i++;
        }
        return r1_byteA;
    }
}