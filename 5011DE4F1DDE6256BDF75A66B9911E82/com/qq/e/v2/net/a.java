package com.qq.e.v2.net;

import qsbk.app.utils.Base64;

public final class a {
    private static int[] a;

    static {
        a = new int[]{2022921542, -159656524, -2120859926, 1165995080};
    }

    private static int a_(byte r0b) {
        return r0b < 0 ? r0b + 256 : r0b;
    }

    public static byte[] a_(byte[] r14_byteA) {
        int r0i = 8 - r14_byteA.length % 8;
        byte[] r6_byteA = new byte[(r14_byteA.length + r0i)];
        r6_byteA[0] = (byte) r0i;
        System.arraycopy(r14_byteA, 0, r6_byteA, r0i, r14_byteA.length);
        Object r1_Object = new Object[r6_byteA.length];
        r0i = 0;
        while (r0i < r1_Object.length) {
            int[] r4_intA = a;
            int[] r7_intA = a(r6_byteA, r0i);
            int r5i = r7_intA[0];
            int r3i = r7_intA[1];
            int r2i = 0;
            int r8i = r4_intA[0];
            int r9i = r4_intA[1];
            int r10i = r4_intA[2];
            int r11i = r4_intA[3];
            int r4i = 0;
            while (r4i < 32) {
                r2i -= 1640531527;
                r5i += (((r3i << 4) + r8i) ^ (r3i + r2i)) ^ ((r3i >> 5) + r9i);
                r3i += (((r5i << 4) + r10i) ^ (r5i + r2i)) ^ ((r5i >> 5) + r11i);
                r4i++;
            }
            r7_intA[0] = r5i;
            r7_intA[1] = r3i;
            System.arraycopy(a(r7_intA, 0), 0, r1_Object, r0i, Base64.DONT_BREAK_LINES);
            r0i += 8;
        }
        return r1_Object.length > 0 ? r1_Object : null;
    }

    private static byte[] a_(int[] r5_intA, int r6i) {
        int r0i = 0;
        byte[] r2_byteA = new byte[(r5_intA.length << 2)];
        int r1i = 0;
        while (r0i < r2_byteA.length) {
            r2_byteA[r0i + 3] = (byte) r5_intA[r1i];
            r2_byteA[r0i + 2] = (byte) (r5_intA[r1i] >> 8);
            r2_byteA[r0i + 1] = (byte) (r5_intA[r1i] >> 16);
            r2_byteA[r0i] = (byte) (r5_intA[r1i] >>> 24);
            r1i++;
            r0i += 4;
        }
        return r2_byteA;
    }

    private static int[] a_(byte[] r4_byteA, int r5i) {
        int[] r1_intA = new int[(r4_byteA.length >> 2)];
        int r0i = 0;
        while (r5i < r4_byteA.length) {
            r1_intA[r0i] = ((a(r4_byteA[r5i + 3]) | (a(r4_byteA[r5i + 2]) << 8)) | (a(r4_byteA[r5i + 1]) << 16)) | (r4_byteA[r5i] << 24);
            r0i++;
            r5i += 4;
        }
        return r1_intA;
    }

    public static byte[] b(byte[] r15_byteA) {
        Object r6_Object = new Object[r15_byteA.length];
        int r0i = 0;
        while (r0i < r15_byteA.length) {
            int[] r3_intA = a;
            int[] r7_intA = a(r15_byteA, r0i);
            int r4i = r7_intA[0];
            int r2i = r7_intA[1];
            int r8i = r3_intA[0];
            int r9i = r3_intA[1];
            int r10i = r3_intA[2];
            int r11i = r3_intA[3];
            int r5i = r4i;
            r4i = -957401312;
            int r3i = r2i;
            r2i = 0;
            while (r2i < 32) {
                r3i -= (((r5i << 4) + r10i) ^ (r5i + r4i)) ^ ((r5i >> 5) + r11i);
                r5i -= (((r3i << 4) + r8i) ^ (r3i + r4i)) ^ ((r3i >> 5) + r9i);
                r4i -= -1640531527;
                r2i++;
            }
            r7_intA[0] = r5i;
            r7_intA[1] = r3i;
            System.arraycopy(a(r7_intA, 0), 0, r6_Object, r0i, Base64.DONT_BREAK_LINES);
            r0i += 8;
        }
        if (r6_Object.length <= 0) {
            return null;
        }
        byte r2b = r6_Object[0];
        Object r0_Object = new Object[(r6_Object.length - r2b)];
        System.arraycopy(r6_Object, r2b, r0_Object, 0, r0_Object.length);
        return r0_Object;
    }
}