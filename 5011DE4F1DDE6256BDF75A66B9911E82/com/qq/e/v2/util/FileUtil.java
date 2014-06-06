package com.qq.e.v2.util;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copyTo(InputStream r7_InputStream, File r8_File) {
        /*
        r1 = 1;
        r0 = 0;
        if (r7 != 0) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r3 = 0;
        r2 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x0048, all -> 0x003d }
        r2.<init>(r8);	 //Catch:{ IOException -> 0x0048, all -> 0x003d }
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = new byte[r3];	 //Catch:{ IOException -> 0x001a }
    L_0x000f:
        r4 = r7.read(r3);	 //Catch:{ IOException -> 0x001a }
        if (r4 <= 0) goto L_0x0035;
    L_0x0015:
        r5 = 0;
        r2.write(r3, r5, r4);	 //Catch:{ IOException -> 0x001a }
        goto L_0x000f;
    L_0x001a:
        r1 = move-exception;
    L_0x001b:
        r3 = "Exception while copy  from InputStream to File %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 //Catch:{ all -> 0x0046 }
        r5 = 0;
        r6 = r8.getAbsolutePath();	 //Catch:{ all -> 0x0046 }
        r4[r5] = r6;	 //Catch:{ all -> 0x0046 }
        r3 = java.lang.String.format(r3, r4);	 //Catch:{ all -> 0x0046 }
        com.qq.e.v2.util.GDTLogger.report(r3, r1);	 //Catch:{ all -> 0x0046 }
        tryClose(r7);
        tryClose(r2);
        goto L_0x0004;
    L_0x0035:
        tryClose(r7);
        tryClose(r2);
        r0 = r1;
        goto L_0x0004;
    L_0x003d:
        r0 = move-exception;
        r2 = r3;
    L_0x003f:
        tryClose(r7);
        tryClose(r2);
        throw r0;
    L_0x0046:
        r0 = move-exception;
        goto L_0x003f;
    L_0x0048:
        r1 = move-exception;
        r2 = r3;
        goto L_0x001b;
        */

    }

    public static boolean renameTo(File r1_File, File r2_File) {
        if (r1_File == null || r2_File == null || (!r1_File.exists())) {
            return false;
        }
        if (r1_File.renameTo(r2_File)) {
            return true;
        }
        return copyTo(null, r2_File);
    }

    public static void tryClose(InputStream r1_InputStream) {
        if (r1_InputStream != null) {
            try {
                r1_InputStream.close();
            } catch (Exception e) {
            }
        }
    }

    public static void tryClose(OutputStream r1_OutputStream) {
        if (r1_OutputStream != null) {
            try {
                r1_OutputStream.close();
            } catch (Exception e) {
            }
        }
    }
}