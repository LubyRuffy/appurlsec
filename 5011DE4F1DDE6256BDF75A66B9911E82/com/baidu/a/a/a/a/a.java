package com.baidu.a.a.a.a;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import qsbk.app.widget.listview.XListViewHeader;

public final class a {
    public static byte[] a_(String r4_String, String r5_String, byte[] r6_byteA) {
        Key r0_Key = new SecretKeySpec(r5_String.getBytes(), "AES");
        Cipher r1_Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        r1_Cipher.init(1, r0_Key, new IvParameterSpec(r4_String.getBytes()));
        return r1_Cipher.doFinal(r6_byteA);
    }

    public static byte[] b(String r4_String, String r5_String, byte[] r6_byteA) {
        Key r0_Key = new SecretKeySpec(r5_String.getBytes(), "AES");
        Cipher r1_Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        r1_Cipher.init(XListViewHeader.STATE_REFRESHING, r0_Key, new IvParameterSpec(r4_String.getBytes()));
        return r1_Cipher.doFinal(r6_byteA);
    }
}