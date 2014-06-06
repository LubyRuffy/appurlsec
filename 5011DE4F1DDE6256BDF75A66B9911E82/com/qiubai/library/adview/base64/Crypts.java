package com.qiubai.library.adview.base64;

import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;

public class Crypts {
    static int[] a;
    static int[] b;
    public static BASE64Decoder b64decoder;
    public static BASE64Encoder b64encoder;

    static {
        int[] r0_intA = new int[16];
        r0_intA[0] = 5;
        r0_intA[1] = 4;
        r0_intA[2] = 14;
        r0_intA[3] = 6;
        r0_intA[4] = 2;
        r0_intA[5] = 1;
        r0_intA[6] = 8;
        r0_intA[7] = 12;
        r0_intA[8] = 15;
        r0_intA[10] = 11;
        r0_intA[11] = 9;
        r0_intA[12] = 10;
        r0_intA[13] = 7;
        r0_intA[14] = 13;
        r0_intA[15] = 3;
        a = r0_intA;
        r0_intA = new int[16];
        r0_intA[0] = 9;
        r0_intA[1] = 5;
        r0_intA[2] = 4;
        r0_intA[3] = 15;
        r0_intA[4] = 1;
        r0_intA[6] = 3;
        r0_intA[7] = 13;
        r0_intA[8] = 6;
        r0_intA[9] = 11;
        r0_intA[10] = 12;
        r0_intA[11] = 10;
        r0_intA[12] = 7;
        r0_intA[13] = 14;
        r0_intA[14] = 2;
        r0_intA[15] = 8;
        b = r0_intA;
        b64encoder = new BASE64Encoder();
        b64decoder = new BASE64Decoder();
    }

    static byte[] a(char[] r3_charA) {
        byte[] r1_byteA = new byte[r3_charA.length];
        int r0i = 0;
        while (r0i < r3_charA.length) {
            r1_byteA[r0i] = (byte) r3_charA[r0i];
            r0i++;
        }
        return r1_byteA;
    }

    static char[] a(byte[] r3_byteA) {
        char[] r2_charA = new char[r3_byteA.length];
        int r0i = 0;
        while (r0i < r3_byteA.length) {
            byte r1b = r3_byteA[r0i];
            if (r1b < 0) {
                r1b += 256;
            }
            r2_charA[r0i] = (char) r1b;
            r0i++;
        }
        return r2_charA;
    }

    public static String xorMapDecrypt(String r7_String) throws IOException {
        char[] r2_charA = a(b64decoder.decodeBuffer(r7_String));
        if (r2_charA.length < 1) {
            return RContactStorage.PRIMARY_KEY;
        }
        char[] r3_charA = new char[(r2_charA.length - 1)];
        char r0c = r2_charA[r3_charA.length];
        if (r0c < '\u0000') {
            r0c += 256;
        }
        int r1i = 0;
        while (r1i < r3_charA.length) {
            char r4c = r2_charA[r1i];
            r3_charA[r1i] = (char) ((((b[r4c / 16] * 16) + b[r4c % 16]) ^ r0c) & 255);
            r1i++;
        }
        return new String(a(r3_charA), AdViewNetFetchThread.NetEncoding);
    }

    public static String xorMapEncrypt(int r7i, String r8_String) throws IOException {
        char[] r1_charA = a(r8_String.getBytes(AdViewNetFetchThread.NetEncoding));
        char[] r2_charA = new char[(r1_charA.length + 1)];
        int r3i = r7i % 256;
        r2_charA[r1_charA.length] = (char) ((byte) r3i);
        int r0i = 0;
        while (r0i < r1_charA.length) {
            int r4i = (r1_charA[r0i] ^ r3i) & 255;
            r2_charA[r0i] = (char) (a[r4i / 16] * 16 + a[r4i % 16]);
            r0i++;
        }
        return b64encoder.encode(a(r2_charA));
    }
}