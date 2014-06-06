package com.baidu.a.a.a.b;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class c {
    public static String a(byte[] r6_byteA, String r7_String, boolean r8z) {
        StringBuilder r2_StringBuilder = new StringBuilder();
        int r3i = r6_byteA.length;
        int r1i = 0;
        while (r1i < r3i) {
            String r0_String = Integer.toHexString(r6_byteA[r1i] & 255);
            if (r8z) {
                r0_String = r0_String.toUpperCase();
            }
            if (r0_String.length() == 1) {
                r2_StringBuilder.append("0");
            }
            r2_StringBuilder.append(r0_String).append(r7_String);
            r1i++;
        }
        return r2_StringBuilder.toString();
    }

    public static String a(byte[] r2_byteA, boolean r3z) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.reset();
            r0_MessageDigest.update(r2_byteA);
            return a(r0_MessageDigest.digest(), RContactStorage.PRIMARY_KEY, r3z);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}