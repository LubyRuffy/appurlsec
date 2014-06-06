package com.tencent.cloudsdk;

import java.io.File;

// compiled from: SourceFile
public class x {
    private static char[] a;

    static {
        a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String a(File r5_File) {
        /*
        if (r5 == 0) goto L_0x0008;
    L_0x0002:
        r0 = r5.exists();
        if (r0 != 0) goto L_0x000b;
    L_0x0008:
        r0 = "";
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = 0;
        r0 = "MD5";
        r0 = java.security.MessageDigest.getInstance(r0);	 //Catch:{ Exception -> 0x006a, all -> 0x0058 }
        r0.reset();	 //Catch:{ Exception -> 0x006a, all -> 0x0058 }
        r1 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x006a, all -> 0x0058 }
        r1.<init>(r5);	 //Catch:{ Exception -> 0x006a, all -> 0x0058 }
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];	 //Catch:{ Exception -> 0x0049 }
    L_0x001e:
        r3 = r1.read(r2);	 //Catch:{ Exception -> 0x0049 }
        if (r3 > 0) goto L_0x0044;
    L_0x0024:
        r1.close();	 //Catch:{ IOException -> 0x0062 }
    L_0x0027:
        r0 = r0.digest();
        r0 = a(r0);
        r1 = "Md5Utils";
        r2 = new java.lang.StringBuilder;
        r3 = "md5: ";
        r2.<init>(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.i(r1, r2);
        goto L_0x000a;
    L_0x0044:
        r4 = 0;
        r0.update(r2, r4, r3);	 //Catch:{ Exception -> 0x0049 }
        goto L_0x001e;
    L_0x0049:
        r0 = move-exception;
    L_0x004a:
        r0.printStackTrace();	 //Catch:{ all -> 0x0067 }
        r1.close();	 //Catch:{ IOException -> 0x0053 }
    L_0x0050:
        r0 = "";
        goto L_0x000a;
    L_0x0053:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0050;
    L_0x0058:
        r0 = move-exception;
    L_0x0059:
        r2.close();	 //Catch:{ IOException -> 0x005d }
    L_0x005c:
        throw r0;
    L_0x005d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x005c;
    L_0x0062:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0027;
    L_0x0067:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0059;
    L_0x006a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x004a;
        */

    }

    private static String a(byte[] r4_byteA) {
        StringBuilder r1_StringBuilder = new StringBuilder(r4_byteA.length * 2);
        int r0i = 0;
        while (r0i < r4_byteA.length) {
            r1_StringBuilder.append(a[(r4_byteA[r0i] & 240) >>> 4]);
            r1_StringBuilder.append(a[r4_byteA[r0i] & 15]);
            r0i++;
        }
        return r1_StringBuilder.toString();
    }
}