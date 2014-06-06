package com.tencent.mm.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import qsbk.app.utils.Base64;

public final class MD5 {
    private MD5() {
    }

    public static String getMD5(File r1_File) {
        return getMD5(r1_File, 102400);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String getMD5(File r7_File, int r8i) {
        /*
        r1 = 0;
        if (r7 == 0) goto L_0x000b;
    L_0x0003:
        if (r8 <= 0) goto L_0x000b;
    L_0x0005:
        r0 = r7.exists();
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        r0 = r1;
    L_0x000c:
        return r0;
    L_0x000d:
        r2 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x002f, all -> 0x0038 }
        r2.<init>(r7);	 //Catch:{ Exception -> 0x002f, all -> 0x0038 }
        r3 = (long) r8;
        r5 = r7.length();	 //Catch:{ Exception -> 0x0046, all -> 0x0044 }
        r0 = (r3 > r5 ? 1 : (r3 == r5? 0 : -1));
        if (r0 > 0) goto L_0x002a;
    L_0x001b:
        r3 = (long) r8;	 //Catch:{ Exception -> 0x0046, all -> 0x0044 }
    L_0x001c:
        r0 = (int) r3;	 //Catch:{ Exception -> 0x0046, all -> 0x0044 }
        r0 = getMD5(r2, r0);	 //Catch:{ Exception -> 0x0046, all -> 0x0044 }
        r2.close();	 //Catch:{ Exception -> 0x0046, all -> 0x0044 }
        r2.close();	 //Catch:{ IOException -> 0x0028 }
        goto L_0x000c;
    L_0x0028:
        r1 = move-exception;
        goto L_0x000c;
    L_0x002a:
        r3 = r7.length();	 //Catch:{ Exception -> 0x0046, all -> 0x0044 }
        goto L_0x001c;
    L_0x002f:
        r0 = move-exception;
        r0 = r1;
    L_0x0031:
        if (r0 == 0) goto L_0x0036;
    L_0x0033:
        r0.close();	 //Catch:{ IOException -> 0x0040 }
    L_0x0036:
        r0 = r1;
        goto L_0x000c;
    L_0x0038:
        r0 = move-exception;
        r2 = r1;
    L_0x003a:
        if (r2 == 0) goto L_0x003f;
    L_0x003c:
        r2.close();	 //Catch:{ IOException -> 0x0042 }
    L_0x003f:
        throw r0;
    L_0x0040:
        r0 = move-exception;
        goto L_0x0036;
    L_0x0042:
        r1 = move-exception;
        goto L_0x003f;
    L_0x0044:
        r0 = move-exception;
        goto L_0x003a;
    L_0x0046:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0031;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String getMD5(File r3_File, int r4i, int r5i) {
        /*
        r1 = 0;
        if (r3 == 0) goto L_0x000d;
    L_0x0003:
        r0 = r3.exists();
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        if (r4 < 0) goto L_0x000d;
    L_0x000b:
        if (r5 > 0) goto L_0x000f;
    L_0x000d:
        r0 = r1;
    L_0x000e:
        return r0;
    L_0x000f:
        r2 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x0024, all -> 0x002d }
        r2.<init>(r3);	 //Catch:{ Exception -> 0x0024, all -> 0x002d }
        r0 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
        r0 = getMD5(r2, r0, r4, r5);	 //Catch:{ Exception -> 0x003b, all -> 0x0039 }
        r2.close();	 //Catch:{ Exception -> 0x003b, all -> 0x0039 }
        r2.close();	 //Catch:{ IOException -> 0x0022 }
        goto L_0x000e;
    L_0x0022:
        r1 = move-exception;
        goto L_0x000e;
    L_0x0024:
        r0 = move-exception;
        r0 = r1;
    L_0x0026:
        if (r0 == 0) goto L_0x002b;
    L_0x0028:
        r0.close();	 //Catch:{ IOException -> 0x0035 }
    L_0x002b:
        r0 = r1;
        goto L_0x000e;
    L_0x002d:
        r0 = move-exception;
        r2 = r1;
    L_0x002f:
        if (r2 == 0) goto L_0x0034;
    L_0x0031:
        r2.close();	 //Catch:{ IOException -> 0x0037 }
    L_0x0034:
        throw r0;
    L_0x0035:
        r0 = move-exception;
        goto L_0x002b;
    L_0x0037:
        r1 = move-exception;
        goto L_0x0034;
    L_0x0039:
        r0 = move-exception;
        goto L_0x002f;
    L_0x003b:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0026;
        */

    }

    public static final String getMD5(FileInputStream r7_FileInputStream, int r8i) {
        int r1i = 0;
        if (r7_FileInputStream == null || r8i <= 0) {
            return null;
        }
        try {
            MessageDigest r2_MessageDigest = MessageDigest.getInstance("MD5");
            StringBuilder r3_StringBuilder = new StringBuilder(32);
            byte[] r4_byteA = new byte[r8i];
            while (true) {
                int r5i = r7_FileInputStream.read(r4_byteA);
                if (r5i != -1) {
                    r2_MessageDigest.update(r4_byteA, 0, r5i);
                } else {
                    byte[] r2_byteA = r2_MessageDigest.digest();
                    while (r1i < r2_byteA.length) {
                        r3_StringBuilder.append(Integer.toString(r2_byteA[r1i] & 255 + 256, Base64.URL_SAFE).substring(1));
                        r1i++;
                    }
                    return r3_StringBuilder.toString();
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static final String getMD5(FileInputStream r8_FileInputStream, int r9i, int r10i, int r11i) {
        int r1i = 0;
        if (r8_FileInputStream == null || r9i <= 0 || r10i < 0 || r11i <= 0) {
            return null;
        }
        try {
            if (r8_FileInputStream.skip((long) r10i) < ((long) r10i)) {
                return null;
            }
            MessageDigest r3_MessageDigest = MessageDigest.getInstance("MD5");
            StringBuilder r4_StringBuilder = new StringBuilder(32);
            byte[] r5_byteA = new byte[r9i];
            int r2i = 0;
            while (true) {
                int r6i = r8_FileInputStream.read(r5_byteA);
                if (r6i == -1 || r2i >= r11i) {
                    byte[] r2_byteA = r3_MessageDigest.digest();
                    while (r1i < r2_byteA.length) {
                        r4_StringBuilder.append(Integer.toString(r2_byteA[r1i] & 255 + 256, Base64.URL_SAFE).substring(1));
                        r1i++;
                    }
                    return r4_StringBuilder.toString();
                } else if (r2i + r6i <= r11i) {
                    r3_MessageDigest.update(r5_byteA, 0, r6i);
                    r2i += r6i;
                } else {
                    r3_MessageDigest.update(r5_byteA, 0, r11i - r2i);
                    r2i = r11i;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMD5(String r3_String) {
        if (r3_String == null) {
            return null;
        }
        File r1_File = new File(r3_String);
        return r1_File.exists() ? getMD5(r1_File, 102400) : null;
    }

    public static String getMD5(String r3_String, int r4i, int r5i) {
        if (r3_String == null) {
            return null;
        }
        File r1_File = new File(r3_String);
        return r1_File.exists() ? getMD5(r1_File, r4i, r5i) : null;
    }

    public static final String getMessageDigest(byte[] r9_byteA) {
        int r0i = 0;
        char[] r2_charA = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest r1_MessageDigest = MessageDigest.getInstance("MD5");
            r1_MessageDigest.update(r9_byteA);
            byte[] r3_byteA = r1_MessageDigest.digest();
            int r4i = r3_byteA.length;
            char[] r5_charA = new char[(r4i * 2)];
            int r1i = 0;
            while (r0i < r4i) {
                byte r6b = r3_byteA[r0i];
                int r7i = r1i + 1;
                r5_charA[r1i] = r2_charA[(r6b >>> 4) & 15];
                r1i = r7i + 1;
                r5_charA[r7i] = r2_charA[r6b & 15];
                r0i++;
            }
            return new String(r5_charA);
        } catch (Exception e) {
            return null;
        }
    }

    public static final byte[] getRawDigest(byte[] r1_byteA) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.update(r1_byteA);
            return r0_MessageDigest.digest();
        } catch (Exception e) {
            return null;
        }
    }
}