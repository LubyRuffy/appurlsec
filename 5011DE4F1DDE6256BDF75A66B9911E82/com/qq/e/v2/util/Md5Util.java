package com.qq.e.v2.util;

import android.util.Base64;
import com.qq.e.comm.DownloadService;
import com.qq.e.v2.constants.Constants.KEYS;
import java.io.File;
import java.security.MessageDigest;
import qsbk.app.database.QsbkDatabase;

public class Md5Util {
    private static final String[] a;

    static {
        String[] r0_StringA = new String[16];
        r0_StringA[0] = "0";
        r0_StringA[1] = "1";
        r0_StringA[2] = DownloadService.V2;
        r0_StringA[3] = "3";
        r0_StringA[4] = "4";
        r0_StringA[5] = "5";
        r0_StringA[6] = "6";
        r0_StringA[7] = "7";
        r0_StringA[8] = "8";
        r0_StringA[9] = "9";
        r0_StringA[10] = QsbkDatabase.A;
        r0_StringA[11] = "b";
        r0_StringA[12] = KEYS.CTXTSETTING;
        r0_StringA[13] = "d";
        r0_StringA[14] = "e";
        r0_StringA[15] = "f";
        a = r0_StringA;
    }

    public static String byteArrayToHexString(byte[] r6_byteA) {
        StringBuffer r2_StringBuffer = new StringBuffer();
        int r0i = 0;
        while (r0i < r6_byteA.length) {
            byte r1b = r6_byteA[r0i];
            if (r1b < 0) {
                r1b += 256;
            }
            r2_StringBuffer.append(a[r1b / 16] + a[r1b % 16]);
            r0i++;
        }
        return r2_StringBuffer.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String encode(File r6_File) {
        /*
        if (r6 != 0) goto L_0x0005;
    L_0x0002:
        r0 = "";
    L_0x0004:
        return r0;
    L_0x0005:
        r0 = 0;
        r1 = "MD5";
        r2 = java.security.MessageDigest.getInstance(r1);	 //Catch:{ Exception -> 0x0048, all -> 0x0038 }
        r1 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x0048, all -> 0x0038 }
        r1.<init>(r6);	 //Catch:{ Exception -> 0x0048, all -> 0x0038 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 //Catch:{ Exception -> 0x0020, all -> 0x0046 }
    L_0x0015:
        r3 = r1.read(r0);	 //Catch:{ Exception -> 0x0020, all -> 0x0046 }
        if (r3 <= 0) goto L_0x002a;
    L_0x001b:
        r4 = 0;
        r2.update(r0, r4, r3);	 //Catch:{ Exception -> 0x0020, all -> 0x0046 }
        goto L_0x0015;
    L_0x0020:
        r0 = move-exception;
        r0 = r1;
    L_0x0022:
        if (r0 == 0) goto L_0x0027;
    L_0x0024:
        r0.close();	 //Catch:{ Exception -> 0x0042 }
    L_0x0027:
        r0 = "";
        goto L_0x0004;
    L_0x002a:
        r0 = r2.digest();	 //Catch:{ Exception -> 0x0020, all -> 0x0046 }
        r0 = byteArrayToHexString(r0);	 //Catch:{ Exception -> 0x0020, all -> 0x0046 }
        r1.close();	 //Catch:{ Exception -> 0x0036 }
        goto L_0x0004;
    L_0x0036:
        r1 = move-exception;
        goto L_0x0004;
    L_0x0038:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x003c:
        if (r1 == 0) goto L_0x0041;
    L_0x003e:
        r1.close();	 //Catch:{ Exception -> 0x0044 }
    L_0x0041:
        throw r0;
    L_0x0042:
        r0 = move-exception;
        goto L_0x0027;
    L_0x0044:
        r1 = move-exception;
        goto L_0x0041;
    L_0x0046:
        r0 = move-exception;
        goto L_0x003c;
    L_0x0048:
        r1 = move-exception;
        goto L_0x0022;
        */

    }

    public static String encode(String r3_String) {
        String r0_String;
        try {
            r0_String = new String(r3_String);
            return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(r0_String.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    public static String encodeBase64String(String r2_String) {
        try {
            return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(Base64.decode(r2_String, 0)));
        } catch (Exception e) {
            GDTLogger.e("Exception while md5 base64String", e);
            return null;
        }
    }

    public static byte[] hexStringtoByteArray(String r5_String) {
        if (r5_String.length() % 2 != 0) {
            return null;
        }
        byte[] r2_byteA = new byte[(r5_String.length() / 2)];
        int r0i = 0;
        while (r0i < r5_String.length() - 1) {
            char r1c = r5_String.charAt(r0i);
            char r3c = r5_String.charAt(r0i + 1);
            r1c = Character.toLowerCase(r1c);
            r3c = Character.toLowerCase(r3c);
            int r1i = (r1c <= '9' ? r1c - 48 : (r1c - 97) + 10) << 4;
            r1i = r3c <= '9' ? r1i + r3c - 48 : r1i + r3c - 97 + 10;
            if (r1i > 127) {
                r1i -= 256;
            }
            r2_byteA[r0i / 2] = (byte) r1i;
            r0i += 2;
        }
        return r2_byteA;
    }
}