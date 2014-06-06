package com.tencent.mm.sdk.platformtools;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.format.DateFormat;
import com.tencent.mm.algorithm.TypeTransform;
import java.io.PrintStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

final class f {
    private static final byte[] a;

    static {
        a = new byte[]{(byte) 4, (byte) 0, (byte) 0, (byte) 0, (byte) -1, (byte) -1, (byte) -1, (byte) 0};
    }

    public static void initLogHeader(PrintStream r2_PrintStream, String r3_String, String r4_String, long r5j, int r7i) {
        if (r2_PrintStream == null || Util.isNullOrNil(r4_String) || r5j == 0) {
        } else {
            r2_PrintStream.println(new StringBuilder("1 ").append(r3_String).toString());
            r2_PrintStream.println(new StringBuilder("2 ").append(r4_String).toString());
            r2_PrintStream.println(new StringBuilder("3 ").append(r5j).toString());
            r2_PrintStream.println(new StringBuilder("4 ").append(Integer.toHexString(r7i)).toString());
            r2_PrintStream.println(new StringBuilder("5 ").append(VERSION.RELEASE).toString());
            r2_PrintStream.println(new StringBuilder("6 ").append(VERSION.CODENAME).toString());
            r2_PrintStream.println(new StringBuilder("7 ").append(VERSION.INCREMENTAL).toString());
            r2_PrintStream.println(new StringBuilder("8 ").append(Build.BOARD).toString());
            r2_PrintStream.println(new StringBuilder("9 ").append(Build.DEVICE).toString());
            r2_PrintStream.println(new StringBuilder("10 ").append(Build.DISPLAY).toString());
            r2_PrintStream.println(new StringBuilder("11 ").append(Build.FINGERPRINT).toString());
            r2_PrintStream.println(new StringBuilder("12 ").append(Build.HOST).toString());
            r2_PrintStream.println(new StringBuilder("13 ").append(Build.MANUFACTURER).toString());
            r2_PrintStream.println(new StringBuilder("14 ").append(Build.MODEL).toString());
            r2_PrintStream.println(new StringBuilder("15 ").append(Build.PRODUCT).toString());
            r2_PrintStream.println(new StringBuilder("16 ").append(Build.TAGS).toString());
            r2_PrintStream.println(new StringBuilder("17 ").append(Build.TYPE).toString());
            r2_PrintStream.println(new StringBuilder("18 ").append(Build.USER).toString());
            r2_PrintStream.println();
            r2_PrintStream.flush();
        }
    }

    public static void writeToStream(PrintStream r4_PrintStream, byte[] r5_byteA, String r6_String, String r7_String) {
        if (r4_PrintStream == null || Util.isNullOrNil(r5_byteA) || Util.isNullOrNil(r6_String) || Util.isNullOrNil(r7_String)) {
        } else {
            synchronized (r4_PrintStream) {
                StringBuffer r0_StringBuffer = new StringBuffer();
                r0_StringBuffer.append(DateFormat.format("MM-dd kk:mm:ss", System.currentTimeMillis()));
                r0_StringBuffer.append(" ").append(r6_String).append(" ").append(r7_String);
                String r0_String = r0_StringBuffer.toString();
                try {
                    Key r1_Key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(r5_byteA));
                    Cipher r2_Cipher = Cipher.getInstance("DES");
                    r2_Cipher.init(1, r1_Key);
                    byte[] r0_byteA = r2_Cipher.doFinal(r0_String.getBytes());
                    r4_PrintStream.write(TypeTransform.intToByteArrayLH(r0_byteA.length));
                    r4_PrintStream.write(r0_byteA);
                    r4_PrintStream.write(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                r4_PrintStream.flush();
            }
        }
    }
}