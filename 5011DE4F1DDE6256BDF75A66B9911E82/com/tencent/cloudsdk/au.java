package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// compiled from: SourceFile
public class au {
    private static final String a;

    static {
        a = au.class.getSimpleName();
    }

    public static boolean a(File r4_File) {
        if (r4_File == null) {
            return false;
        }
        if (r4_File.exists() || r4_File.mkdirs()) {
            return true;
        }
        WnsClientLog.e(a, new StringBuilder(">>> createFile E: \u63d2\u4ef6\u76ee\u5f55\u521b\u5efa\u5931\u8d25, dir: ").append(r4_File.getAbsolutePath()).toString());
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(InputStream r6_InputStream, File r7_File) {
        /*
        r0 = 0;
        if (r6 == 0) goto L_0x000b;
    L_0x0003:
        if (r7 == 0) goto L_0x000b;
    L_0x0005:
        r1 = r7.exists();
        if (r1 != 0) goto L_0x000c;
    L_0x000b:
        return r0;
    L_0x000c:
        r3 = 0;
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];
        r2 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x0058, all -> 0x004d }
        r2.<init>(r7);	 //Catch:{ Exception -> 0x0058, all -> 0x004d }
    L_0x0016:
        r3 = r6.read(r1);	 //Catch:{ Exception -> 0x002d }
        r4 = -1;
        if (r3 != r4) goto L_0x0028;
    L_0x001d:
        r2.flush();	 //Catch:{ Exception -> 0x002d }
        com.tencent.cloudsdk.ax.b(r6);
        com.tencent.cloudsdk.ax.a(r2);
        r0 = 1;
        goto L_0x000b;
    L_0x0028:
        r4 = 0;
        r2.write(r1, r4, r3);	 //Catch:{ Exception -> 0x002d }
        goto L_0x0016;
    L_0x002d:
        r1 = move-exception;
    L_0x002e:
        r3 = a;	 //Catch:{ all -> 0x0056 }
        r4 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0056 }
        r5 = ">>> readDataToFile E: ";
        r4.<init>(r5);	 //Catch:{ all -> 0x0056 }
        r1 = r1.toString();	 //Catch:{ all -> 0x0056 }
        r1 = r4.append(r1);	 //Catch:{ all -> 0x0056 }
        r1 = r1.toString();	 //Catch:{ all -> 0x0056 }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r3, r1);	 //Catch:{ all -> 0x0056 }
        com.tencent.cloudsdk.ax.b(r6);
        com.tencent.cloudsdk.ax.a(r2);
        goto L_0x000b;
    L_0x004d:
        r0 = move-exception;
        r2 = r3;
    L_0x004f:
        com.tencent.cloudsdk.ax.b(r6);
        com.tencent.cloudsdk.ax.a(r2);
        throw r0;
    L_0x0056:
        r0 = move-exception;
        goto L_0x004f;
    L_0x0058:
        r1 = move-exception;
        r2 = r3;
        goto L_0x002e;
        */

    }

    public static boolean a(String r3_String, String r4_String) {
        return a(new File(r3_String)) && b(new File(new StringBuilder(String.valueOf(r3_String)).append(File.separator).append(r4_String).toString()));
    }

    public static boolean b(File r5_File) {
        if (r5_File == null) {
            return false;
        }
        try {
            if (r5_File.exists() || r5_File.createNewFile()) {
                return true;
            }
            WnsClientLog.e(a, new StringBuilder(">>> createFile E: \u521b\u5efa\u6587\u4ef6\u5931\u8d25, path: ").append(r5_File.getAbsolutePath()).toString());
            return false;
        } catch (IOException e) {
            WnsClientLog.e(a, new StringBuilder(">>> createFile E: ").append(e.toString()).append(" \u521b\u5efa\u6587\u4ef6\u5931\u8d25, path: ").append(r5_File.getAbsolutePath()).toString());
            return false;
        }
    }

    public static boolean b(String r3_String, String r4_String) {
        if (!a(new File(r3_String))) {
            return false;
        }
        File r2_File = new File(new StringBuilder(String.valueOf(r3_String)).append(File.separator).append(r4_String).toString());
        return c(r2_File) && b(r2_File);
    }

    public static boolean c(File r4_File) {
        if (r4_File == null) {
            return false;
        }
        if ((!r4_File.exists()) || r4_File.delete()) {
            return true;
        }
        WnsClientLog.e(a, new StringBuilder(">>> createFile E: \u521b\u5efa\u6587\u4ef6\u5931\u8d25, path: ").append(r4_File.getAbsolutePath()).toString());
        return false;
    }
}