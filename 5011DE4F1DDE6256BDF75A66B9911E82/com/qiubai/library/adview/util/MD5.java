package com.qiubai.library.adview.util;

import com.qq.e.comm.DownloadService;
import com.qq.e.v2.constants.Constants.KEYS;
import qsbk.app.database.QsbkDatabase;

public class MD5 {
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String MD5Encode(String r4_String) {
        /*
        r2 = 0;
        r1 = new java.lang.String;	 //Catch:{ Exception -> 0x0019 }
        r1.<init>(r4);	 //Catch:{ Exception -> 0x0019 }
        r0 = "MD5";
        r0 = java.security.MessageDigest.getInstance(r0);	 //Catch:{ Exception -> 0x0020 }
        r2 = r1.getBytes();	 //Catch:{ Exception -> 0x0020 }
        r0 = r0.digest(r2);	 //Catch:{ Exception -> 0x0020 }
        r0 = byteArrayToHexString(r0);	 //Catch:{ Exception -> 0x0020 }
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x001c:
        r1.printStackTrace();
        goto L_0x0018;
    L_0x0020:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x001c;
        */

    }

    private static String a(byte r4b) {
        if (r4b < 0) {
            r4b += 256;
        }
        return new StringBuilder(String.valueOf(a[r4b / 16])).append(a[r4b % 16]).toString();
    }

    public static String byteArrayToHexString(byte[] r3_byteA) {
        StringBuffer r1_StringBuffer = new StringBuffer();
        int r0i = 0;
        while (r0i < r3_byteA.length) {
            r1_StringBuffer.append(a(r3_byteA[r0i]));
            r0i++;
        }
        return r1_StringBuffer.toString();
    }
}