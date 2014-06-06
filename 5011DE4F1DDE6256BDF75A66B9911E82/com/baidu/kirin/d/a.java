package com.baidu.kirin.d;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import qsbk.app.bean.Base;

public class a {
    private static final char[] a;
    private static final int[] b;

    static {
        a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        b = new int[256];
        Arrays.fill(b, -1);
        int r2i = a.length;
        int r0i = 0;
        while (r0i < r2i) {
            b[a[r0i]] = r0i;
            r0i++;
        }
        b[61] = 0;
    }

    public static final String a_(String r4_String) {
        try {
            return new String(a(r4_String.getBytes(Base.UTF8), false));
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException r0_UnsupportedEncodingException = e;
            System.err.println("Base64 encoding error: " + r0_UnsupportedEncodingException.getMessage());
            r0_UnsupportedEncodingException.printStackTrace();
            return null;
        }
    }

    public static final char[] a_(byte[] r14_byteA, boolean r15z) {
        int r6i;
        int r1i = 0;
        r6i = r14_byteA != null ? r14_byteA.length : 0;
        if (r6i == 0) {
            return new char[0];
        }
        int r7i = (r6i / 3) * 3;
        int r2i = (((r6i - 1) / 3) + 1) << 2;
        int r8i = r2i + (r15z ? ((r2i - 1) / 76) << 1 : 0);
        char[] r4_charA = new char[r8i];
        int r0i = 0;
        r2i = 0;
        int r5i = 0;
        while (r5i < r7i) {
            int r9i = r5i + 1;
            int r10i = r9i + 1;
            r5i = r10i + 1;
            r9i = (((r14_byteA[r9i] & 255) << 8) | ((r14_byteA[r5i] & 255) << 16)) | (r14_byteA[r10i] & 255);
            r10i = r2i + 1;
            r4_charA[r2i] = a[(r9i >>> 18) & 63];
            r2i = r10i + 1;
            r4_charA[r10i] = a[(r9i >>> 12) & 63];
            r10i = r2i + 1;
            r4_charA[r2i] = a[(r9i >>> 6) & 63];
            r2i = r10i + 1;
            r4_charA[r10i] = a[r9i & 63];
            if (r15z) {
                r0i++;
                if (r0i != 19 || r2i >= r8i - 2) {
                } else {
                    r9i = r2i + 1;
                    r4_charA[r2i] = '\r';
                    r4_charA[r9i] = '\n';
                    r2i = r9i + 1;
                    r0i = 0;
                }
            }
        }
        r0i = r6i - r7i;
        if (r0i > 0) {
            r2i = (r14_byteA[r7i] & 255) << 10;
            if (r0i == 2) {
                r1i = (r14_byteA[r6i - 1] & 255) << 2;
            }
            r1i |= r2i;
            r4_charA[r8i - 4] = a[r1i >> 12];
            r4_charA[r8i - 3] = a[(r1i >>> 6) & 63];
            r4_charA[r8i - 2] = r0i == 2 ? a[r1i & 63] : '=';
            r4_charA[r8i - 1] = '=';
        }
        return r4_charA;
    }
}