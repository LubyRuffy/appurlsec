package com.amap.api.location.core;

import android.content.Context;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.Key;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// compiled from: Encrypt.java
public class e {
    static byte[] a;

    static {
        a = new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8};
    }

    static String a() {
        String r1_String = RContactStorage.PRIMARY_KEY;
        int r0i = 0;
        while (r0i < 8) {
            r1_String = r1_String + ((char) ((int) (Math.random() * 94.0d + 33.0d)));
            r0i++;
        }
        return r1_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static PublicKey a(Context r3_Context) throws Exception {
        /*
        r0 = r3.getAssets();
        r1 = "location_public_key.der";
        r0 = r0.open(r1);	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r1 = "X.509";
        r1 = java.security.cert.CertificateFactory.getInstance(r1);	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r2 = "RSA";
        r2 = java.security.KeyFactory.getInstance(r2);	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r1 = r1.generateCertificate(r0);	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r0.close();	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r0 = new java.security.spec.X509EncodedKeySpec;	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r1 = r1.getPublicKey();	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r1 = r1.getEncoded();	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r0.<init>(r1);	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
        r0 = r2.generatePublic(r0);	 //Catch:{ IOException -> 0x004d, CertificateException -> 0x004a, NoSuchAlgorithmException -> 0x002f, InvalidKeySpecException -> 0x0038, NullPointerException -> 0x0041 }
    L_0x002e:
        return r0;
    L_0x002f:
        r0 = move-exception;
        r0 = new java.lang.Exception;
        r1 = "\u65e0\u6b64\u7b97\u6cd5";
        r0.<init>(r1);
        throw r0;
    L_0x0038:
        r0 = move-exception;
        r0 = new java.lang.Exception;
        r1 = "\u516c\u94a5\u975e\u6cd5";
        r0.<init>(r1);
        throw r0;
    L_0x0041:
        r0 = move-exception;
        r0 = new java.lang.Exception;
        r1 = "\u516c\u94a5\u6570\u636e\u4e3a\u7a7a";
        r0.<init>(r1);
        throw r0;
    L_0x004a:
        r0 = move-exception;
    L_0x004b:
        r0 = 0;
        goto L_0x002e;
    L_0x004d:
        r0 = move-exception;
        goto L_0x004b;
        */

    }

    static byte[] a(byte[] r2_byteA, Key r3_Key) throws Exception {
        Cipher r0_Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        r0_Cipher.init(1, r3_Key);
        return r0_Cipher.doFinal(r2_byteA);
    }

    static byte[] a(byte[] r4_byteA, byte[] r5_byteA) throws Exception {
        AlgorithmParameterSpec r0_AlgorithmParameterSpec = new IvParameterSpec(a);
        Key r1_Key = new SecretKeySpec(r4_byteA, "DES");
        Cipher r2_Cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        r2_Cipher.init(1, r1_Key, r0_AlgorithmParameterSpec);
        return r2_Cipher.doFinal(r5_byteA);
    }
}