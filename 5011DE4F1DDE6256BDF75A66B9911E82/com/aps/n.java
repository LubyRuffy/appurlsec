package com.aps;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

// compiled from: Storage.java
public class n {
    private static n a;
    private static DecimalFormat b;
    private static SimpleDateFormat c;

    static {
        a = null;
        b = null;
        c = null;
    }

    private n() {
    }

    static String a(Object r4_Object, String r5_String) {
        if (b == null) {
            b = new DecimalFormat("#", new DecimalFormatSymbols(Locale.US));
        }
        b.applyPattern(r5_String);
        return b.format(r4_Object);
    }

    static byte[] a(int r3i) {
        byte[] r0_byteA = new byte[4];
        r0_byteA[0] = (byte) (r3i & 255);
        r0_byteA[1] = (byte) ((r3i >> 8) & 255);
        r0_byteA[2] = (byte) ((r3i >> 16) & 255);
        r0_byteA[3] = (byte) ((r3i >> 24) & 255);
        return r0_byteA;
    }

    public static byte[] a(long r6j) {
        byte[] r1_byteA = new byte[8];
        int r0i = 0;
        while (r0i < r1_byteA.length) {
            r1_byteA[r0i] = (byte) ((int) ((r6j >> (r0i * 8)) & 255));
            r0i++;
        }
        return r1_byteA;
    }

    static byte[] a(String r1_String) {
        return a(Integer.parseInt(r1_String));
    }

    static byte[] b(int r3i) {
        byte[] r0_byteA = new byte[2];
        r0_byteA[0] = (byte) (r3i & 255);
        r0_byteA[1] = (byte) ((r3i >> 8) & 255);
        return r0_byteA;
    }

    static byte[] b(String r1_String) {
        return b(Integer.parseInt(r1_String));
    }
}