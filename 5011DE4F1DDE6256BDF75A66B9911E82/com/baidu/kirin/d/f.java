package com.baidu.kirin.d;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class f {
    public static String a(String r5_String) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.update(r5_String.getBytes());
            byte[] r1_byteA = r0_MessageDigest.digest();
            StringBuffer r2_StringBuffer = new StringBuffer();
            int r0i = 0;
            while (r0i < r1_byteA.length) {
                int r3i = r1_byteA[r0i] & 255;
                if (r3i < 16) {
                    r2_StringBuffer.append("0");
                }
                r2_StringBuffer.append(Integer.toHexString(r3i));
                r0i++;
            }
            return r2_StringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            d.c("MD5Utility : getMD5 error");
            e.printStackTrace();
            return RContactStorage.PRIMARY_KEY;
        }
    }
}