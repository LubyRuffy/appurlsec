package com.aps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Process;
import java.io.File;

public final class as {
    private Context a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private long j;
    private ar k;

    private as(Context r5_Context) {
        this.a = null;
        this.b = true;
        this.c = 1270;
        this.d = 310;
        this.e = 4;
        this.f = 200;
        this.g = 1;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = null;
        this.a = r5_Context;
    }

    private static int a(byte[] r4_byteA, int r5i) {
        int r0i = 0;
        int r1i = 0;
        while (r0i < 4) {
            r1i += (r4_byteA[r0i + r5i] & 255) << (r0i << 3);
            r0i++;
        }
        return r1i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static as a(Context r12_Context) {
        /*
        r2 = 1;
        r9 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r3 = 0;
        r4 = new com.aps.as;
        r4.<init>(r12);
        r4.h = r3;
        r4.i = r3;
        r0 = java.lang.System.currentTimeMillis();
        r5 = 28800000; // 0x1b77400 float:6.7390035E-38 double:1.42290906E-316;
        r0 = r0 + r5;
        r0 = r0 / r9;
        r0 = r0 * r9;
        r4.j = r0;
        r1 = 0;
        r0 = new java.io.FileInputStream;	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r5 = new java.io.File;	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6.<init>();	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r7 = b(r12);	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r7 = java.io.File.separator;	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r7 = "data_carrier_status";
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r6 = r6.toString();	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r5.<init>(r6);	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r0.<init>(r5);	 //Catch:{ Exception -> 0x00d0, all -> 0x00c0 }
        r5 = new java.io.ByteArrayOutputStream;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r5.<init>();	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 32;
        r1 = new byte[r1];	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
    L_0x004b:
        r6 = r0.read(r1);	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r7 = -1;
        if (r6 == r7) goto L_0x005e;
    L_0x0052:
        r7 = 0;
        r5.write(r1, r7, r6);	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        goto L_0x004b;
    L_0x0057:
        r1 = move-exception;
    L_0x0058:
        if (r0 == 0) goto L_0x005d;
    L_0x005a:
        r0.close();	 //Catch:{ Exception -> 0x00c7 }
    L_0x005d:
        return r4;
    L_0x005e:
        r5.flush();	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r6 = r5.toByteArray();	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        if (r6 == 0) goto L_0x00b5;
    L_0x0067:
        r1 = r6.length;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r7 = 22;
        if (r1 < r7) goto L_0x00b5;
    L_0x006c:
        r1 = 0;
        r1 = r6[r1];	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        if (r1 == 0) goto L_0x00be;
    L_0x0071:
        r1 = r2;
    L_0x0072:
        r4.b = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 1;
        r1 = r6[r1];	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = r1 * 10;
        r1 = r1 << 10;
        r4.c = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 2;
        r1 = r6[r1];	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = r1 * 10;
        r1 = r1 << 10;
        r4.d = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 3;
        r1 = r6[r1];	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.e = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 4;
        r1 = r6[r1];	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = r1 * 10;
        r4.f = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 5;
        r1 = r6[r1];	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.g = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 14;
        r1 = b(r6, r1);	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r7 = r4.j;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r7 = r7 - r1;
        r3 = (r7 > r9 ? 1 : (r7 == r9? 0 : -1));
        if (r3 >= 0) goto L_0x00b5;
    L_0x00a4:
        r4.j = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 6;
        r1 = a(r6, r1);	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.h = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r1 = 10;
        r1 = a(r6, r1);	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r4.i = r1;	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
    L_0x00b5:
        r5.close();	 //Catch:{ Exception -> 0x0057, all -> 0x00cb }
        r0.close();	 //Catch:{ Exception -> 0x00bc }
        goto L_0x005d;
    L_0x00bc:
        r0 = move-exception;
        goto L_0x005d;
    L_0x00be:
        r1 = r3;
        goto L_0x0072;
    L_0x00c0:
        r0 = move-exception;
    L_0x00c1:
        if (r1 == 0) goto L_0x00c6;
    L_0x00c3:
        r1.close();	 //Catch:{ Exception -> 0x00c9 }
    L_0x00c6:
        throw r0;
    L_0x00c7:
        r0 = move-exception;
        goto L_0x005d;
    L_0x00c9:
        r1 = move-exception;
        goto L_0x00c6;
    L_0x00cb:
        r1 = move-exception;
        r11 = r1;
        r1 = r0;
        r0 = r11;
        goto L_0x00c1;
    L_0x00d0:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0058;
        */

    }

    private static byte[] a(long r7j) {
        byte[] r1_byteA = new byte[8];
        int r0i = 0;
        while (r0i < 8) {
            r1_byteA[r0i] = (byte) ((int) ((r7j >> (r0i << 3)) & 255));
            r0i++;
        }
        return r1_byteA;
    }

    private static long b(byte[] r4_byteA, int r5i) {
        int r0i = 0;
        int r1i = 0;
        while (r0i < 8) {
            r1i += (r4_byteA[r0i + 14] & 255) << (r0i << 3);
            r0i++;
        }
        return (long) r1i;
    }

    private static String b(Context r3_Context) {
        File r0_File = null;
        if (Process.myUid() != 1000) {
            r0_File = ab.a(r3_Context);
        }
        return (("mounted".equals(Environment.getExternalStorageState()) || (!ab.c())) && r0_File != null) ? r0_File.getPath() : r3_Context.getFilesDir().getPath();
    }

    private static byte[] c(int r4i) {
        byte[] r1_byteA = new byte[4];
        int r0i = 0;
        while (r0i < 4) {
            r1_byteA[r0i] = (byte) (r4i >> (r0i << 3));
            r0i++;
        }
        return r1_byteA;
    }

    private void g() {
        long r0j = System.currentTimeMillis() + 28800000;
        if (r0j - this.j > 86400000) {
            this.j = (r0j / 86400000) * 86400000;
            this.h = 0;
            this.i = 0;
        }
    }

    protected final void a(int r1i) {
        g();
        if (r1i < 0) {
            r1i = 0;
        }
        this.h = r1i;
    }

    protected final void a(ar r1_ar) {
        this.k = r1_ar;
    }

    protected final boolean a() {
        g();
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo == null || (!r0_NetworkInfo.isConnected())) {
            return this.b;
        }
        if (r0_NetworkInfo.getType() == 1) {
            return this.b && this.h < this.c;
        } else {
            if ((!this.b) || this.i >= this.d) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final boolean a(String r11_String) {
        /*
        r10_this = this;
        r1 = 1;
        r2 = 0;
        r3 = new org.json.JSONObject;	 //Catch:{ Exception -> 0x0160 }
        r3.<init>(r11);	 //Catch:{ Exception -> 0x0160 }
        r0 = "e";
        r0 = r3.has(r0);	 //Catch:{ Exception -> 0x0160 }
        if (r0 == 0) goto L_0x001a;
    L_0x000f:
        r0 = "e";
        r0 = r3.getInt(r0);	 //Catch:{ Exception -> 0x0160 }
        if (r0 == 0) goto L_0x015a;
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r10.b = r0;	 //Catch:{ Exception -> 0x0160 }
    L_0x001a:
        r0 = "d";
        r0 = r3.has(r0);	 //Catch:{ Exception -> 0x0160 }
        if (r0 == 0) goto L_0x0065;
    L_0x0022:
        r0 = "d";
        r0 = r3.getInt(r0);	 //Catch:{ Exception -> 0x0160 }
        r4 = r0 & 127;
        r4 = r4 * 10;
        r4 = r4 << 10;
        r10.c = r4;	 //Catch:{ Exception -> 0x0160 }
        r4 = r0 & 3968;
        r4 = r4 >> 7;
        r4 = r4 * 10;
        r4 = r4 << 10;
        r10.d = r4;	 //Catch:{ Exception -> 0x0160 }
        r4 = 520192; // 0x7f000 float:7.28944E-40 double:2.57009E-318;
        r4 = r4 & r0;
        r4 = r4 >> 12;
        r10.e = r4;	 //Catch:{ Exception -> 0x0160 }
        r4 = 66584576; // 0x3f80000 float:1.457613E-36 double:3.28971515E-316;
        r4 = r4 & r0;
        r4 = r4 >> 19;
        r4 = r4 * 10;
        r10.f = r4;	 //Catch:{ Exception -> 0x0160 }
        r4 = 2080374784; // 0x7c000000 float:2.658456E36 double:1.0278417112E-314;
        r0 = r0 & r4;
        r0 = r0 >> 26;
        r10.g = r0;	 //Catch:{ Exception -> 0x0160 }
        r0 = r10.g;	 //Catch:{ Exception -> 0x0160 }
        r4 = 31;
        if (r0 != r4) goto L_0x005c;
    L_0x0058:
        r0 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r10.g = r0;	 //Catch:{ Exception -> 0x0160 }
    L_0x005c:
        r0 = r10.k;	 //Catch:{ Exception -> 0x0160 }
        if (r0 == 0) goto L_0x0065;
    L_0x0060:
        r0 = r10.k;	 //Catch:{ Exception -> 0x0160 }
        r0.a();	 //Catch:{ Exception -> 0x0160 }
    L_0x0065:
        r0 = "u";
        r0 = r3.has(r0);	 //Catch:{ Exception -> 0x0160 }
        if (r0 == 0) goto L_0x0182;
    L_0x006d:
        r0 = "u";
        r0 = r3.getInt(r0);	 //Catch:{ Exception -> 0x0160 }
        if (r0 == 0) goto L_0x015d;
    L_0x0075:
        r0 = r1;
    L_0x0076:
        r4 = 0;
        r10.g();	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r3 = new java.io.FileOutputStream;	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r5 = new java.io.File;	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r6 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r6.<init>();	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r7 = r10.a;	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r7 = b(r7);	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r7 = java.io.File.separator;	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r7 = "data_carrier_status";
        r6 = r6.append(r7);	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r6 = r6.toString();	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r5.<init>(r6);	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r3.<init>(r5);	 //Catch:{ Exception -> 0x0167, all -> 0x0171 }
        r4 = r10.h;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r4 = c(r4);	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r5 = r10.i;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r5 = c(r5);	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r6 = r10.j;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r6 = a(r6);	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7 = 22;
        r7 = new byte[r7];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r8 = 0;
        r9 = r10.b;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        if (r9 == 0) goto L_0x0164;
    L_0x00be:
        r1 = (byte) r1;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r8] = r1;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 1;
        r2 = r10.c;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r2 = r2 / 10240;
        r2 = (byte) r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 2;
        r2 = r10.d;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r2 = r2 / 10240;
        r2 = (byte) r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 3;
        r2 = r10.e;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r2 = (byte) r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 4;
        r2 = r10.f;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r2 = r2 / 10;
        r2 = (byte) r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 5;
        r2 = r10.g;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r2 = (byte) r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 6;
        r2 = 0;
        r2 = r4[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 7;
        r2 = 1;
        r2 = r4[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 8;
        r2 = 2;
        r2 = r4[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 9;
        r2 = 3;
        r2 = r4[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 10;
        r2 = 0;
        r2 = r5[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 11;
        r2 = 1;
        r2 = r5[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 12;
        r2 = 2;
        r2 = r5[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 13;
        r2 = 3;
        r2 = r5[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 14;
        r2 = 0;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 15;
        r2 = 1;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 16;
        r2 = 2;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 17;
        r2 = 3;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 18;
        r2 = 4;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 19;
        r2 = 5;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 20;
        r2 = 6;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r1 = 21;
        r2 = 7;
        r2 = r6[r2];	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r7[r1] = r2;	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r3.write(r7);	 //Catch:{ Exception -> 0x017f, all -> 0x017d }
        r3.close();	 //Catch:{ Exception -> 0x0179 }
    L_0x0159:
        return r0;
    L_0x015a:
        r0 = r2;
        goto L_0x0018;
    L_0x015d:
        r0 = r2;
        goto L_0x0076;
    L_0x0160:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0076;
    L_0x0164:
        r1 = r2;
        goto L_0x00be;
    L_0x0167:
        r1 = move-exception;
        r1 = r4;
    L_0x0169:
        if (r1 == 0) goto L_0x0159;
    L_0x016b:
        r1.close();	 //Catch:{ Exception -> 0x016f }
        goto L_0x0159;
    L_0x016f:
        r1 = move-exception;
        goto L_0x0159;
    L_0x0171:
        r0 = move-exception;
        r3 = r4;
    L_0x0173:
        if (r3 == 0) goto L_0x0178;
    L_0x0175:
        r3.close();	 //Catch:{ Exception -> 0x017b }
    L_0x0178:
        throw r0;
    L_0x0179:
        r1 = move-exception;
        goto L_0x0159;
    L_0x017b:
        r1 = move-exception;
        goto L_0x0178;
    L_0x017d:
        r0 = move-exception;
        goto L_0x0173;
    L_0x017f:
        r1 = move-exception;
        r1 = r3;
        goto L_0x0169;
    L_0x0182:
        r0 = r2;
        goto L_0x0076;
        */

    }

    protected final int b() {
        return this.e;
    }

    protected final void b(int r1i) {
        g();
        if (r1i < 0) {
            r1i = 0;
        }
        this.i = r1i;
    }

    protected final int c() {
        return this.f;
    }

    protected final int d() {
        return this.g;
    }

    protected final int e() {
        g();
        return this.h;
    }

    protected final int f() {
        g();
        return this.i;
    }
}