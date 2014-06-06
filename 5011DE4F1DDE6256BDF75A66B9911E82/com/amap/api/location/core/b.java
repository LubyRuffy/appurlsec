package com.amap.api.location.core;

// compiled from: Base64Util.java
public class b {
    private static final char[] a;
    private static byte[] b;

    static {
        a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        b = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};
    }

    private b() {
    }

    public static String a(byte[] r8_byteA) {
        StringBuffer r1_StringBuffer = new StringBuffer();
        int r2i = r8_byteA.length;
        int r0i = 0;
        while (r0i < r2i) {
            int r3i = r0i + 1;
            int r4i = r8_byteA[r0i] & 255;
            if (r3i == r2i) {
                r1_StringBuffer.append(a[r4i >>> 2]);
                r1_StringBuffer.append(a[(r4i & 3) << 4]);
                r1_StringBuffer.append("==");
                break;
            } else {
                int r5i = r3i + 1;
                r3i = r8_byteA[r3i] & 255;
                if (r5i == r2i) {
                    r1_StringBuffer.append(a[r4i >>> 2]);
                    r1_StringBuffer.append(a[((r4i & 3) << 4) | ((r3i & 240) >>> 4)]);
                    r1_StringBuffer.append(a[(r3i & 15) << 2]);
                    r1_StringBuffer.append("=");
                    break;
                } else {
                    r0i = r5i + 1;
                    r5i = r8_byteA[r5i] & 255;
                    r1_StringBuffer.append(a[r4i >>> 2]);
                    r1_StringBuffer.append(a[((r4i & 3) << 4) | ((r3i & 240) >>> 4)]);
                    r1_StringBuffer.append(a[((r3i & 15) << 2) | ((r5i & 192) >>> 6)]);
                    r1_StringBuffer.append(a[r5i & 63]);
                }
            }
        }
        return r1_StringBuffer.toString();
    }
}