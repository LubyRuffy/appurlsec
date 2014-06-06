package qsbk.app.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileNameGenerator {
    private static String a(byte[] r5_byteA) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        int r0i = 0;
        while (r0i < r5_byteA.length) {
            String r2_String = Integer.toHexString(r5_byteA[r0i] & 255);
            if (r2_String.length() == 1) {
                r1_StringBuilder.append('0');
            }
            r1_StringBuilder.append(r2_String);
            r0i++;
        }
        return r1_StringBuilder.toString();
    }

    public static String generator(String r2_String) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.update(r2_String.getBytes());
            return a(r0_MessageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(r2_String.hashCode());
        }
    }
}