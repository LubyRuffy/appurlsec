package com.aps;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;

public final class q {
    private static String a;
    private static String b;
    private static String c;

    static {
        a = RContactStorage.PRIMARY_KEY;
        b = "log.txt";
        c = RContactStorage.PRIMARY_KEY;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void a(String r9_String, String r10_String) {
        /*
        r7 = 0;
        r6 = 1;
        android.util.Log.i(r9, r10);
        r0 = a();
        if (r0 == 0) goto L_0x01ce;
    L_0x000b:
        r0 = b();
        if (r0 == 0) goto L_0x01ce;
    L_0x0011:
        r0 = "";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = "[";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = "";
        r2 = java.lang.System.currentTimeMillis();
        r4 = java.util.Calendar.getInstance();
        r4.setTimeInMillis(r2);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r2 = "%02d";
        r3 = new java.lang.Object[r6];
        r5 = 2;
        r5 = r4.get(r5);
        r5 = r5 + 1;
        r5 = java.lang.Integer.valueOf(r5);
        r3[r7] = r5;
        r2 = java.lang.String.format(r2, r3);
        r1 = r1.append(r2);
        r2 = "-";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r2 = "%02d";
        r3 = new java.lang.Object[r6];
        r5 = 5;
        r5 = r4.get(r5);
        r5 = java.lang.Integer.valueOf(r5);
        r3[r7] = r5;
        r2 = java.lang.String.format(r2, r3);
        r1 = r1.append(r2);
        r2 = " ";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r2 = "%02d";
        r3 = new java.lang.Object[r6];
        r5 = 11;
        r5 = r4.get(r5);
        r5 = java.lang.Integer.valueOf(r5);
        r3[r7] = r5;
        r2 = java.lang.String.format(r2, r3);
        r1 = r1.append(r2);
        r2 = ":";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r2 = "%02d";
        r3 = new java.lang.Object[r6];
        r5 = 12;
        r5 = r4.get(r5);
        r5 = java.lang.Integer.valueOf(r5);
        r3[r7] = r5;
        r2 = java.lang.String.format(r2, r3);
        r1 = r1.append(r2);
        r2 = ":";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r2 = "%02d";
        r3 = new java.lang.Object[r6];
        r5 = 13;
        r5 = r4.get(r5);
        r5 = java.lang.Integer.valueOf(r5);
        r3[r7] = r5;
        r2 = java.lang.String.format(r2, r3);
        r1 = r1.append(r2);
        r2 = ".";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r2 = "%03d";
        r3 = new java.lang.Object[r6];
        r5 = 14;
        r4 = r4.get(r5);
        r4 = java.lang.Integer.valueOf(r4);
        r3[r7] = r4;
        r2 = java.lang.String.format(r2, r3);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = "]";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = "[";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r0 = r0.append(r9);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = "]";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = " ";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r0 = r0.append(r10);
        r2 = r0.toString();
        r0 = new java.io.File;
        r1 = a;
        r0.<init>(r1);
        r1 = r0.exists();
        if (r1 != 0) goto L_0x01cf;
    L_0x01bb:
        r1 = r0.mkdirs();
        if (r1 != 0) goto L_0x01cf;
    L_0x01c1:
        r0 = r0.isDirectory();
        if (r0 != 0) goto L_0x01cf;
    L_0x01c7:
        r0 = c;
        r1 = "Error: make dir failed!";
        android.util.Log.d(r0, r1);
    L_0x01ce:
        return;
    L_0x01cf:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = a;
        r0 = r0.append(r1);
        r1 = b;
        r0 = r0.append(r1);
        r0 = r0.toString();
        r3 = new java.io.File;
        r3.<init>(r0);
        r1 = 0;
        r0 = new java.io.BufferedWriter;	 //Catch:{ Exception -> 0x0208, all -> 0x0210 }
        r4 = new java.io.OutputStreamWriter;	 //Catch:{ Exception -> 0x0208, all -> 0x0210 }
        r5 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x0208, all -> 0x0210 }
        r6 = 1;
        r5.<init>(r3, r6);	 //Catch:{ Exception -> 0x0208, all -> 0x0210 }
        r4.<init>(r5);	 //Catch:{ Exception -> 0x0208, all -> 0x0210 }
        r3 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r0.<init>(r4, r3);	 //Catch:{ Exception -> 0x0208, all -> 0x0210 }
        r0.write(r2);	 //Catch:{ Exception -> 0x021c, all -> 0x0217 }
        r0.newLine();	 //Catch:{ Exception -> 0x021c, all -> 0x0217 }
        r0.close();	 //Catch:{ Exception -> 0x0206 }
        goto L_0x01ce;
    L_0x0206:
        r0 = move-exception;
        goto L_0x01ce;
    L_0x0208:
        r0 = move-exception;
        r0 = r1;
    L_0x020a:
        r0.close();	 //Catch:{ Exception -> 0x020e }
        goto L_0x01ce;
    L_0x020e:
        r0 = move-exception;
        goto L_0x01ce;
    L_0x0210:
        r0 = move-exception;
    L_0x0211:
        r1.close();	 //Catch:{ Exception -> 0x0215 }
    L_0x0214:
        throw r0;
    L_0x0215:
        r1 = move-exception;
        goto L_0x0214;
    L_0x0217:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0211;
    L_0x021c:
        r1 = move-exception;
        goto L_0x020a;
        */

    }

    private static boolean a() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception e) {
            return false;
        }
    }

    protected static boolean a(Context r2_Context) {
        if (r2_Context != null) {
            c = r2_Context.getPackageName();
            return true;
        } else {
            Log.d(c, "Error: No SD Card!");
            return false;
        }
    }

    private static boolean b() {
        boolean r0z = false;
        try {
            if (a == RContactStorage.PRIMARY_KEY) {
                if (c == RContactStorage.PRIMARY_KEY) {
                    return r0z;
                }
                a = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + c + "/files/";
            }
            r0z = true;
        } catch (Exception e) {
        }
        return r0z;
    }
}