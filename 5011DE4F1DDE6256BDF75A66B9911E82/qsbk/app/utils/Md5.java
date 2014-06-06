package qsbk.app.utils;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.MessageDigest;
import qsbk.app.bean.Base;

public class Md5 {
    public static String MD5(String r6_String) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            byte[] r2_byteA = MessageDigest.getInstance("MD5").digest(r6_String.getBytes(Base.UTF8));
            StringBuffer r3_StringBuffer = new StringBuffer();
            int r1i = 0;
            while (r1i < r2_byteA.length) {
                int r4i = r2_byteA[r1i] & 255;
                if (r4i < 16) {
                    r3_StringBuffer.append("0");
                }
                r3_StringBuffer.append(Integer.toHexString(r4i));
                r1i++;
            }
            return r3_StringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return r0_String;
        }
    }
}