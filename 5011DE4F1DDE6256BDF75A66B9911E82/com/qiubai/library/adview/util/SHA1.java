package com.qiubai.library.adview.util;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.MessageDigest;

public class SHA1 {
    public static String SHA2(String r2_String) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("sha-1");
            r0_MessageDigest.update(r2_String.getBytes());
            return a(r0_MessageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] r6_byteA) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        int r1i = 0;
        while (r1i < r6_byteA.length) {
            String r2_String = Integer.toHexString(r6_byteA[r1i] & 255);
            if (r2_String.length() == 1) {
                r0_String = new StringBuilder(String.valueOf(r0_String)).append("0").toString();
            }
            r1i++;
            r0_String = new StringBuilder(String.valueOf(r0_String)).append(r2_String).toString();
        }
        return r0_String;
    }
}