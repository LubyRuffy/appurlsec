package com.qq.e.v2.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StringUtil {
    public static boolean isEmpty(String r1_String) {
        return r1_String == null || r1_String.trim().length() == 0;
    }

    public static String join(String r3_String, String[] r4_StringA) {
        StringBuffer r1_StringBuffer = new StringBuffer();
        if (r4_StringA != null) {
            int r0i = 0;
            while (r0i < r4_StringA.length) {
                if (r3_String == null || r0i == 0) {
                    r1_StringBuffer.append(r4_StringA[r0i]);
                    r0i++;
                } else {
                    r1_StringBuffer.append(r3_String);
                    r1_StringBuffer.append(r4_StringA[r0i]);
                    r0i++;
                }
            }
        }
        return r1_StringBuffer.toString();
    }

    public static int parseInteger(String r1_String, int r2i) {
        try {
            return Integer.parseInt(r1_String);
        } catch (Throwable th) {
            return r2i;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String readAll(File r4_File) throws IOException {
        /*
        r0 = 0;
        if (r4 == 0) goto L_0x0009;
    L_0x0003:
        r1 = r4.exists();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r2 = new java.io.FileReader;	 //Catch:{ IOException -> 0x0047, all -> 0x0042 }
        r2.<init>(r4);	 //Catch:{ IOException -> 0x0047, all -> 0x0042 }
        r1 = new java.io.BufferedReader;	 //Catch:{ IOException -> 0x0047, all -> 0x0042 }
        r1.<init>(r2);	 //Catch:{ IOException -> 0x0047, all -> 0x0042 }
        r0 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x0023 }
        r0.<init>();	 //Catch:{ IOException -> 0x0023 }
    L_0x0019:
        r2 = r1.readLine();	 //Catch:{ IOException -> 0x0023 }
        if (r2 == 0) goto L_0x002c;
    L_0x001f:
        r0.append(r2);	 //Catch:{ IOException -> 0x0023 }
        goto L_0x0019;
    L_0x0023:
        r0 = move-exception;
    L_0x0024:
        throw r0;	 //Catch:{ all -> 0x0025 }
    L_0x0025:
        r0 = move-exception;
    L_0x0026:
        if (r1 == 0) goto L_0x002b;
    L_0x0028:
        r1.close();	 //Catch:{ Exception -> 0x003b }
    L_0x002b:
        throw r0;
    L_0x002c:
        r0 = r0.toString();	 //Catch:{ IOException -> 0x0023 }
        r1.close();	 //Catch:{ Exception -> 0x0034 }
        goto L_0x0009;
    L_0x0034:
        r1 = move-exception;
        r2 = "Exception while close bufferreader";
        com.qq.e.v2.util.GDTLogger.e(r2, r1);
        goto L_0x0009;
    L_0x003b:
        r1 = move-exception;
        r2 = "Exception while close bufferreader";
        com.qq.e.v2.util.GDTLogger.e(r2, r1);
        goto L_0x002b;
    L_0x0042:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x0026;
    L_0x0047:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x0024;
        */

    }

    public static void writeTo(String r2_String, File r3_File) throws IOException {
        if (r3_File == null) {
            throw new IOException("Target File Can not be null in StringUtil.writeTo");
        } else {
            FileWriter r0_FileWriter = new FileWriter(r3_File);
            r0_FileWriter.write(r2_String);
            r0_FileWriter.close();
        }
    }
}