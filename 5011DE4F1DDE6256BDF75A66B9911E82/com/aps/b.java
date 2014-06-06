package com.aps;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import qsbk.app.bean.Base;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Aes.java
public class b {
    private String a;
    private Cipher b;
    private Cipher c;

    b() {
        this.a = "AES/CBC/PKCS5Padding";
        this.b = null;
        this.c = null;
        try {
            Key r1_Key = new SecretKeySpec("#a@u!t*o(n)a&v^i".getBytes(Base.UTF8), "AES");
            this.b = Cipher.getInstance(this.a);
            AlgorithmParameterSpec r0_AlgorithmParameterSpec = new IvParameterSpec("_a+m-a=p?a>p<s%3".getBytes(Base.UTF8));
            this.b.init(1, r1_Key, r0_AlgorithmParameterSpec);
            this.c = Cipher.getInstance(this.a);
            this.c.init(XListViewHeader.STATE_REFRESHING, r1_Key, r0_AlgorithmParameterSpec);
        } catch (Exception e) {
            o.a(e);
        }
    }

    static String a(byte[] r8_byteA) {
        StringBuilder r2_StringBuilder = new StringBuilder();
        int r3i = r8_byteA.length;
        int r0i = 0;
        while (r0i < r3i) {
            String r4_String = Integer.toHexString(r8_byteA[r0i] & 255);
            if (r4_String.length() == 1) {
                Object[] r6_ObjectA = new Object[1];
                r6_ObjectA[0] = r4_String;
                r2_StringBuilder.append(String.format("0%s", r6_ObjectA));
            } else {
                r2_StringBuilder.append(r4_String);
            }
            r0i++;
        }
        return r2_StringBuilder.toString();
    }

    private byte[] a(String r6_String) {
        byte[] r0_byteA = null;
        int r1i = 0;
        if (r6_String == null || r6_String.length() == 0 || r6_String.length() % 2 == 1) {
            return r0_byteA;
        }
        try {
            r0_byteA = new byte[(r6_String.length() / 2)];
            StringBuilder r2_StringBuilder = new StringBuilder();
            while (r1i < r6_String.length()) {
                r2_StringBuilder.delete(0, r2_StringBuilder.length());
                r2_StringBuilder.append("0X");
                r2_StringBuilder.append(r6_String.substring(r1i, r1i + 2));
                r0_byteA[r1i / 2] = (byte) Integer.decode(r2_StringBuilder.toString()).intValue();
                r1i += 2;
            }
        } catch (Exception e) {
            o.a(e);
        }
        return r0_byteA;
    }

    public static String b_(byte[] r2_byteA) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (r2_byteA == null) {
            return r0_String;
        }
        try {
            return com.amap.api.location.core.b.a(r2_byteA);
        } catch (Exception e) {
            return r0_String;
        }
    }

    String a(String r4_String, String r5_String) {
        if (r4_String == null || r4_String.length() == 0) {
            return null;
        }
        try {
            return new String(this.c.doFinal(a(r4_String)), r5_String);
        } catch (Exception e) {
            o.a(e);
            return null;
        }
    }
}