package com.tencent.cloudsdk;

import java.text.DecimalFormat;

// compiled from: SourceFile
public class cr {
    private static final DecimalFormat a;

    static {
        a = new DecimalFormat();
    }

    public static String a(byte[] r7_byteA, int r8i) {
        StringBuffer r1_StringBuffer = new StringBuffer();
        int r0i = 0;
        while (r0i < r8i) {
            int r2i = r7_byteA[r0i] & 255;
            if (r2i <= 32 || r2i >= 127) {
                r1_StringBuffer.append('\\');
                r1_StringBuffer.append(a.format((long) r2i));
            } else if (r2i == 34 || r2i == 40 || r2i == 41 || r2i == 46 || r2i == 59 || r2i == 92 || r2i == 64 || r2i == 36) {
                r1_StringBuffer.append('\\');
                r1_StringBuffer.append((char) r2i);
            } else {
                r1_StringBuffer.append((char) r2i);
            }
            r0i++;
        }
        return r1_StringBuffer.toString();
    }
}