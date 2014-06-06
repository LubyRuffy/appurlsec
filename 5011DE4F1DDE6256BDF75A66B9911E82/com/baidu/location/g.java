package com.baidu.location;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.os.Handler;
import com.amap.api.location.LocationManagerProxy;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import org.json.JSONObject;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewHeader;

class g {
    private static String a = null;
    private static int b = 0;
    private static int c = 0;
    private static String d = null;
    private static String e = null;
    private static String f = null;
    private static String g = null;
    private static String h = null;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;
    private static boolean l = false;
    private static boolean m = false;
    public static final int n = 3;
    private static ArrayList o;
    private static int p;
    private static Uri q;
    private static int r;
    private static String s;
    private static int t;
    private static int u;
    private static Handler v;
    private static Handler w;
    private static Handler x;

    static {
        a = f.v;
        b = 2048;
        c = 3;
        h = null;
        i = false;
        j = false;
        k = false;
        l = false;
        m = false;
        o = null;
        p = 12000;
        q = null;
        r = 4;
        s = HttpUtils.PROXY_IP;
        t = 80;
        u = 0;
        v = null;
        w = null;
        x = null;
    }

    private static int a(Context r5_Context, NetworkInfo r6_NetworkInfo) {
        String r2_String;
        if (r6_NetworkInfo == null || r6_NetworkInfo.getExtraInfo() == null) {
            r2_String = Proxy.getDefaultHost();
            if (r2_String == null || r2_String.length() <= 0) {
                return XListViewHeader.STATE_REFRESHING;
            }
            if (HttpUtils.PROXY_IP.equals(r2_String.trim())) {
                if ("10.0.0.200".equals(r2_String.trim())) {
                    return XListViewHeader.STATE_REFRESHING;
                }
                s = "10.0.0.200";
                return 1;
            } else {
                s = HttpUtils.PROXY_IP;
                return 1;
            }
        } else {
            r2_String = r6_NetworkInfo.getExtraInfo().toLowerCase();
            if (r2_String != null) {
                String r0_String;
                if (r2_String.startsWith("cmwap") || r2_String.startsWith("uniwap") || r2_String.startsWith("3gwap")) {
                    r0_String = Proxy.getDefaultHost();
                    if (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY) || r0_String.equals("null")) {
                        r0_String = HttpUtils.PROXY_IP;
                        s = r0_String;
                        return 1;
                    } else {
                        s = r0_String;
                        return 1;
                    }
                } else if (r2_String.startsWith("ctwap")) {
                    r0_String = Proxy.getDefaultHost();
                    if (r0_String == null || r0_String.equals(RContactStorage.PRIMARY_KEY) || r0_String.equals("null")) {
                        r0_String = "10.0.0.200";
                        s = r0_String;
                        return 1;
                    } else {
                        s = r0_String;
                        return 1;
                    }
                } else if (r2_String.startsWith("cmnet") || r2_String.startsWith("uninet") || r2_String.startsWith("ctnet") || r2_String.startsWith("3gnet")) {
                    return XListViewHeader.STATE_REFRESHING;
                }
            }
            r2_String = Proxy.getDefaultHost();
            if (r2_String == null || r2_String.length() <= 0) {
                return XListViewHeader.STATE_REFRESHING;
            }
            if (HttpUtils.PROXY_IP.equals(r2_String.trim())) {
                if ("10.0.0.200".equals(r2_String.trim())) {
                    return XListViewHeader.STATE_REFRESHING;
                }
                s = "10.0.0.200";
                return 1;
            } else {
                s = HttpUtils.PROXY_IP;
                return 1;
            }
        }
    }

    public static void a(String r1_String, boolean r2z) {
        if (l || r1_String == null) {
        } else {
            h = Jni.if(r1_String);
            m = r2z;
            l = true;
            new m().start();
        }
    }

    public static boolean a(Context r3_Context) {
        if (r3_Context == null) {
            return false;
        }
        do(r3_Context);
        return r == 3;
    }

    public static boolean a(String r4_String, Handler r5_Handler) {
        if (i || r4_String == null) {
            return false;
        }
        i = true;
        j.if(a, "bloc : " + d);
        d = Jni.if(r4_String);
        j.if(a, "NUMBER_e : " + d.length());
        j.if(a, "content: " + d);
        v = r5_Handler;
        if (f == null) {
            f = n.do();
        }
        new h().start();
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int b(Context r9_Context) {
        /*
        r7 = 1;
        r6 = 4;
        r1 = 0;
        r0 = "connectivity";
        r0 = r9.getSystemService(r0);	 //Catch:{ SecurityException -> 0x00be, Exception -> 0x00d1 }
        r0 = (android.net.ConnectivityManager) r0;	 //Catch:{ SecurityException -> 0x00be, Exception -> 0x00d1 }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        r0 = r6;
    L_0x000e:
        return r0;
    L_0x000f:
        r8 = r0.getActiveNetworkInfo();	 //Catch:{ SecurityException -> 0x00be, Exception -> 0x00d1 }
        if (r8 == 0) goto L_0x001b;
    L_0x0015:
        r0 = r8.isAvailable();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 != 0) goto L_0x001d;
    L_0x001b:
        r0 = r6;
        goto L_0x000e;
    L_0x001d:
        r0 = r8.getType();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 != r7) goto L_0x0025;
    L_0x0023:
        r0 = 3;
        goto L_0x000e;
    L_0x0025:
        r0 = "content://telephony/carriers/preferapn";
        r0 = android.net.Uri.parse(r0);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        q = r0;	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = r9.getContentResolver();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r1 = q;	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r1 == 0) goto L_0x00b6;
    L_0x003d:
        r0 = r1.moveToFirst();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00b6;
    L_0x0043:
        r0 = "apn";
        r0 = r1.getColumnIndex(r0);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = r1.getString(r0);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x0081;
    L_0x004f:
        r2 = r0.toLowerCase();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r3 = "ctwap";
        r2 = r2.contains(r3);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 == 0) goto L_0x0081;
    L_0x005b:
        r0 = android.net.Proxy.getDefaultHost();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x007e;
    L_0x0061:
        r2 = "";
        r2 = r0.equals(r2);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x007e;
    L_0x0069:
        r2 = "null";
        r2 = r0.equals(r2);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x007e;
    L_0x0071:
        s = r0;	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = 80;
        t = r0;	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r1 == 0) goto L_0x007c;
    L_0x0079:
        r1.close();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
    L_0x007c:
        r0 = r7;
        goto L_0x000e;
    L_0x007e:
        r0 = "10.0.0.200";
        goto L_0x0071;
    L_0x0081:
        if (r0 == 0) goto L_0x00b6;
    L_0x0083:
        r0 = r0.toLowerCase();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r2 = "wap";
        r0 = r0.contains(r2);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00b6;
    L_0x008f:
        r0 = android.net.Proxy.getDefaultHost();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00b3;
    L_0x0095:
        r2 = "";
        r2 = r0.equals(r2);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x00b3;
    L_0x009d:
        r2 = "null";
        r2 = r0.equals(r2);	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x00b3;
    L_0x00a5:
        s = r0;	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = 80;
        t = r0;	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r1 == 0) goto L_0x00b0;
    L_0x00ad:
        r1.close();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
    L_0x00b0:
        r0 = r7;
        goto L_0x000e;
    L_0x00b3:
        r0 = "10.0.0.172";
        goto L_0x00a5;
    L_0x00b6:
        if (r1 == 0) goto L_0x00bb;
    L_0x00b8:
        r1.close();	 //Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
    L_0x00bb:
        r0 = 2;
        goto L_0x000e;
    L_0x00be:
        r0 = move-exception;
        r0 = r1;
    L_0x00c0:
        r1 = a;	 //Catch:{ Exception -> 0x00cd }
        r2 = "APN security...";
        com.baidu.location.j.if(r1, r2);	 //Catch:{ Exception -> 0x00cd }
        r0 = a(r9, r0);	 //Catch:{ Exception -> 0x00cd }
        goto L_0x000e;
    L_0x00cd:
        r0 = move-exception;
        r0 = r6;
        goto L_0x000e;
    L_0x00d1:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r6;
        goto L_0x000e;
    L_0x00d8:
        r0 = move-exception;
        r0 = r8;
        goto L_0x00c0;
        */

    }

    public static void c() {
        int r0i;
        int r3i;
        int r1i = 1;
        int r2i = 0;
        String r4_String = f.a + "/config.dat";
        r0i = j.void ? 1 : 0;
        r3i = j.try ? 1 : 0;
        Object[] r6_ObjectA = new Object[35];
        r6_ObjectA[r2i] = Integer.valueOf(j.g);
        r6_ObjectA[r1i] = Float.valueOf(j.am);
        r6_ObjectA[2] = Float.valueOf(j.c);
        r6_ObjectA[3] = Float.valueOf(j.F);
        r6_ObjectA[4] = Float.valueOf(j.U);
        r6_ObjectA[5] = Integer.valueOf(j.p);
        r6_ObjectA[6] = Integer.valueOf(j.K);
        r6_ObjectA[7] = Integer.valueOf(j.X);
        r6_ObjectA[8] = Integer.valueOf(j.int);
        r6_ObjectA[9] = Integer.valueOf(j.for);
        r6_ObjectA[10] = Integer.valueOf(j.ad);
        r6_ObjectA[11] = Integer.valueOf(j.long);
        r6_ObjectA[12] = Float.valueOf(j.D);
        r6_ObjectA[13] = Float.valueOf(j.C);
        r6_ObjectA[14] = Float.valueOf(j.ai);
        r6_ObjectA[15] = Float.valueOf(j.Q);
        r6_ObjectA[16] = Integer.valueOf(j.Y);
        r6_ObjectA[17] = Float.valueOf(j.byte);
        r6_ObjectA[18] = Integer.valueOf(j.S);
        r6_ObjectA[19] = Float.valueOf(j.a);
        r6_ObjectA[20] = Float.valueOf(j.u);
        r6_ObjectA[21] = Float.valueOf(j.s);
        r6_ObjectA[22] = Integer.valueOf(j.r);
        r6_ObjectA[23] = Integer.valueOf(j.q);
        r6_ObjectA[24] = Integer.valueOf(r0i);
        r6_ObjectA[25] = Integer.valueOf(r3i);
        r6_ObjectA[26] = Integer.valueOf(j.V);
        r6_ObjectA[27] = Integer.valueOf(j.L);
        r6_ObjectA[28] = Long.valueOf(j.ac);
        r6_ObjectA[29] = Integer.valueOf(j.af);
        r6_ObjectA[30] = Float.valueOf(j.w);
        r6_ObjectA[31] = Float.valueOf(j.W);
        r6_ObjectA[32] = Integer.valueOf(j.v);
        r6_ObjectA[33] = Integer.valueOf(j.ae);
        r6_ObjectA[34] = Integer.valueOf(j.goto);
        String r0_String = String.format("{\"ver\":\"%d\",\"gps\":\"%.1f|%.1f|%.1f|%.1f|%d|%d|%d|%d|%d|%d|%d\",\"up\":\"%.1f|%.1f|%.1f|%.1f\",\"wf\":\"%d|%.1f|%d|%.1f\",\"ab\":\"%.2f|%.2f|%d|%d\",\"gpc\":\"%d|%d|%d|%d|%d|%d\",\"zxd\":\"%.1f|%.1f|%d|%d|%d\"}", r6_ObjectA);
        j.if(a, "save2Config : " + r0_String);
        byte[] r0_byteA = r0_String.getBytes();
        RandomAccessFile r2_RandomAccessFile;
        try {
            RandomAccessFile r2_RandomAccessFile_2;
            File r1_File = new File(r4_String);
            if (!r1_File.exists()) {
                File r2_File = new File(f.a);
                if (!r2_File.exists()) {
                    r2_File.mkdirs();
                }
                if (r1_File.createNewFile()) {
                    j.if(a, "upload manager create file success");
                    r2_RandomAccessFile_2 = new RandomAccessFile(r1_File, "rw");
                    r2_RandomAccessFile_2.seek(0);
                    r2_RandomAccessFile_2.writeBoolean(false);
                    r2_RandomAccessFile_2.writeBoolean(false);
                    r2_RandomAccessFile_2.close();
                } else {
                    return;
                }
            }
            r2_RandomAccessFile = new RandomAccessFile(r1_File, "rw");
            r2_RandomAccessFile.seek(0);
            r2_RandomAccessFile.writeBoolean(true);
            r2_RandomAccessFile.seek(2);
            r2_RandomAccessFile.writeInt(r0_byteA.length);
            r2_RandomAccessFile.write(r0_byteA);
            r2_RandomAccessFile.close();
        } catch (Exception e) {
        }
    }

    public static int do(Context r1_Context) {
        r = b(r1_Context);
        return r;
    }

    public static void f() {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.f():void");
        /* JADX: method processing error */
/*
        Error: java.lang.StackOverflowError: Deep code hierarchy
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:29)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.ProcessClass.process(ProcessClass.java:23)
	at jadx.api.Decompiler.processClass(Decompiler.java:185)
	at jadx.api.JavaClass.decompile(JavaClass.java:46)
	at jadx.api.Decompiler$1.run(Decompiler.java:119)
	at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:895)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:918)
	at java.lang.Thread.run(Thread.java:695)
*/
        /*
        public static void f() {
            r7 = 2;
            r3 = 0;
            r1 = 0;
            r6 = 1;
            r0 = k;
            if (r0 == 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            k = r6;
            r0 = o;
            if (r0 != 0) goto L_0x0039;
        L_0x000f:
            u = r1;
            r0 = new java.util.ArrayList;
            r0.<init>();
            o = r0;
            r0 = r1;
        L_0x0019:
            r2 = u;
            if (r2 >= r7) goto L_0x009e;
        L_0x001d:
            r2 = com.baidu.location.n.do();
        L_0x0021:
            if (r2 != 0) goto L_0x0063;
        L_0x0023:
            r4 = u;
            if (r4 == r6) goto L_0x0063;
        L_0x0027:
            u = r7;
            r4 = com.baidu.location.j.af;	 //Catch:{ Exception -> 0x0060 }
            if (r4 != 0) goto L_0x0051;
        L_0x002d:
            r2 = com.baidu.location.f.new();	 //Catch:{ Exception -> 0x0060 }
            if (r2 != 0) goto L_0x0037;
        L_0x0033:
            r2 = com.baidu.location.b.j();	 //Catch:{ Exception -> 0x0060 }
        L_0x0037:
            if (r2 != 0) goto L_0x0066;
        L_0x0039:
            r0 = o;
            if (r0 == 0) goto L_0x0045;
        L_0x003d:
            r0 = o;
            r0 = r0.size();
            if (r0 >= r6) goto L_0x008d;
        L_0x0045:
            o = r3;
            k = r1;
            r0 = a;
            r1 = "No upload data...";
            com.baidu.location.j.if(r0, r1);
            goto L_0x0008;
        L_0x0051:
            r4 = com.baidu.location.j.af;	 //Catch:{ Exception -> 0x0060 }
            if (r4 != r6) goto L_0x0037;
        L_0x0055:
            r2 = com.baidu.location.b.j();	 //Catch:{ Exception -> 0x0060 }
            if (r2 != 0) goto L_0x0037;
        L_0x005b:
            r2 = com.baidu.location.f.new();	 //Catch:{ Exception -> 0x0060 }
            goto L_0x0037;
        L_0x0060:
            r2 = move-exception;
            r2 = r3;
            goto L_0x0037;
        L_0x0063:
            u = r6;
            goto L_0x0037;
        L_0x0066:
            r4 = o;
            r4.add(r2);
            r2 = r2.length();
            r0 = r0 + r2;
            r2 = a;
            r4 = new java.lang.StringBuilder;
            r4.<init>();
            r5 = "upload data size:";
            r4 = r4.append(r5);
            r4 = r4.append(r0);
            r4 = r4.toString();
            com.baidu.location.j.if(r2, r4);
            r2 = b;
            if (r0 < r2) goto L_0x0019;
        L_0x008c:
            goto L_0x0039;
        L_0x008d:
            r0 = a;
            r1 = "Beging upload data...";
            com.baidu.location.j.if(r0, r1);
            r0 = new com.baidu.location.l;
            r0.<init>();
            r0.start();
            goto L_0x0008;
        L_0x009e:
            r2 = r3;
            goto L_0x0021;
        }
        */
    }

    public static void for() {
        RandomAccessFile r0_RandomAccessFile;
        try {
            RandomAccessFile r0_RandomAccessFile_2;
            File r1_File = new File(f.a + "/config.dat");
            if (!r1_File.exists()) {
                File r0_File = new File(f.a);
                if (!r0_File.exists()) {
                    r0_File.mkdirs();
                }
                if (r1_File.createNewFile()) {
                    j.if(a, "upload manager create file success");
                    r0_RandomAccessFile_2 = new RandomAccessFile(r1_File, "rw");
                    r0_RandomAccessFile_2.seek(0);
                    r0_RandomAccessFile_2.writeBoolean(false);
                    r0_RandomAccessFile_2.writeBoolean(false);
                    r0_RandomAccessFile_2.close();
                } else {
                    return;
                }
            }
            r0_RandomAccessFile = new RandomAccessFile(r1_File, "rw");
            r0_RandomAccessFile.seek(1);
            r0_RandomAccessFile.writeBoolean(true);
            r0_RandomAccessFile.seek(1024);
            r0_RandomAccessFile.writeDouble(j.if);
            r0_RandomAccessFile.writeDouble(j.o);
            r0_RandomAccessFile.writeBoolean(j.ag);
            if ((!j.ag) || j.j == null) {
                r0_RandomAccessFile.close();
            } else {
                r0_RandomAccessFile.write(j.j);
                r0_RandomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static void for(Handler r4_Handler) {
        try {
            File r1_File = new File(f.a + "/config.dat");
            if (r1_File.exists()) {
                RandomAccessFile r0_RandomAccessFile = new RandomAccessFile(r1_File, "rw");
                if (r0_RandomAccessFile.readBoolean()) {
                    r0_RandomAccessFile.seek(2);
                    int r1i = r0_RandomAccessFile.readInt();
                    byte[] r2_byteA = new byte[r1i];
                    r0_RandomAccessFile.read(r2_byteA, 0, r1i);
                    if(new String(r2_byteA));
                }
                r0_RandomAccessFile.seek(1);
                if (r0_RandomAccessFile.readBoolean()) {
                    r0_RandomAccessFile.seek(1024);
                    j.if = r0_RandomAccessFile.readDouble();
                    j.o = r0_RandomAccessFile.readDouble();
                    j.ag = r0_RandomAccessFile.readBoolean();
                    if (j.ag) {
                        j.j = new byte[625];
                        r0_RandomAccessFile.read(j.j, 0, 625);
                    }
                }
                r0_RandomAccessFile.close();
            }
            String r0_String = "&ver=" + j.g + "&usr=" + j.do + "&app=" + j.ak + "&prod=" + j.b;
            j.if(a, r0_String);
            x = r4_Handler;
            a(r0_String, false);
        } catch (Exception e) {
        }
    }

    public static boolean for(Context r3_Context) {
        boolean r0z = true;
        if (r3_Context == null) {
            return false;
        }
        do(r3_Context);
        if (r == 1) {
            return r0z;
        }
        r0z = false;
        return r0z;
    }

    public static boolean if(String r9_String) {
        if (r9_String != null) {
            try {
                JSONObject r2_JSONObject = new JSONObject(r9_String);
                int r3i = Integer.parseInt(r2_JSONObject.getString("ver"));
                if (r3i > j.g) {
                    String[] r3_StringA;
                    j.g = r3i;
                    if (r2_JSONObject.has(LocationManagerProxy.GPS_PROVIDER)) {
                        j.if(a, "has gps...");
                        r3_StringA = r2_JSONObject.getString(LocationManagerProxy.GPS_PROVIDER).split("\\|");
                        if (r3_StringA.length > 10) {
                            if (r3_StringA[0] == null || r3_StringA[0].equals(RContactStorage.PRIMARY_KEY)) {
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.F = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    j.c = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.F = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                j.am = Float.parseFloat(r3_StringA[0]);
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.F = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    j.c = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.F = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            j.U = Float.parseFloat(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                j.p = Integer.parseInt(r3_StringA[4]);
                                                if (r3_StringA[5] == null || r3_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    j.K = Integer.parseInt(r3_StringA[5]);
                                                    if (r3_StringA[6] == null || r3_StringA[6].equals(RContactStorage.PRIMARY_KEY)) {
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        j.X = Integer.parseInt(r3_StringA[6]);
                                                        if (r3_StringA[7] == null || r3_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            j.int = Integer.parseInt(r3_StringA[7]);
                                                            if (r3_StringA[8] == null || r3_StringA[8].equals(RContactStorage.PRIMARY_KEY)) {
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            } else {
                                                                j.for = Integer.parseInt(r3_StringA[8]);
                                                                if (r3_StringA[9] == null || r3_StringA[9].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                } else {
                                                                    j.ad = Integer.parseInt(r3_StringA[9]);
                                                                    if (r3_StringA[10] == null || r3_StringA[10].equals(RContactStorage.PRIMARY_KEY)) {
                                                                    } else {
                                                                        j.long = Integer.parseInt(r3_StringA[10]);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (r2_JSONObject.has("up")) {
                        j.if(a, "has up...");
                        r3_StringA = r2_JSONObject.getString("up").split("\\|");
                        if (r3_StringA.length > 3) {
                            if (r3_StringA[0] == null || r3_StringA[0].equals(RContactStorage.PRIMARY_KEY)) {
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.ai = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                } else {
                                    j.C = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.ai = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                }
                            } else {
                                j.D = Float.parseFloat(r3_StringA[0]);
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.ai = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                } else {
                                    j.C = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.ai = Float.parseFloat(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.Q = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (r2_JSONObject.has("wf")) {
                        j.if(a, "has wf...");
                        r3_StringA = r2_JSONObject.getString("wf").split("\\|");
                        if (r3_StringA.length > 3) {
                            if (r3_StringA[0] == null || r3_StringA[0].equals(RContactStorage.PRIMARY_KEY)) {
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.S = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                } else {
                                    j.byte = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.S = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                }
                            } else {
                                j.Y = Integer.parseInt(r3_StringA[0]);
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.S = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                } else {
                                    j.byte = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    } else {
                                        j.S = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.a = Float.parseFloat(r3_StringA[3]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (r2_JSONObject.has("ab")) {
                        j.if(a, "has ab...");
                        r3_StringA = r2_JSONObject.getString("ab").split("\\|");
                        if (r3_StringA.length > 3) {
                            if (r3_StringA[0] == null || r3_StringA[0].equals(RContactStorage.PRIMARY_KEY)) {
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    } else {
                                        j.r = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    }
                                } else {
                                    j.s = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    } else {
                                        j.r = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    }
                                }
                            } else {
                                j.u = Float.parseFloat(r3_StringA[0]);
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    } else {
                                        j.r = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    }
                                } else {
                                    j.s = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    } else {
                                        j.r = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                        } else {
                                            j.q = Integer.parseInt(r3_StringA[3]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (r2_JSONObject.has("zxd")) {
                        r3_StringA = r2_JSONObject.getString("zxd").split("\\|");
                        if (r3_StringA.length > 4) {
                            if (r3_StringA[0] == null || r3_StringA[0].equals(RContactStorage.PRIMARY_KEY)) {
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    } else {
                                        j.v = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    }
                                } else {
                                    j.W = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    } else {
                                        j.v = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    }
                                }
                            } else {
                                j.w = Float.parseFloat(r3_StringA[0]);
                                if (r3_StringA[1] == null || r3_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    } else {
                                        j.v = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    }
                                } else {
                                    j.W = Float.parseFloat(r3_StringA[1]);
                                    if (r3_StringA[2] == null || r3_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    } else {
                                        j.v = Integer.parseInt(r3_StringA[2]);
                                        if (r3_StringA[3] == null || r3_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        } else {
                                            j.ae = Integer.parseInt(r3_StringA[3]);
                                            if (r3_StringA[4] == null || r3_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                            } else {
                                                j.goto = Integer.parseInt(r3_StringA[4]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (r2_JSONObject.has("gpc")) {
                        j.if(a, "has gpc...");
                        String[] r2_StringA = r2_JSONObject.getString("gpc").split("\\|");
                        if (r2_StringA.length > ShareUtils.SHARE_SMS) {
                            if (r2_StringA[0] == null || r2_StringA[0].equals(RContactStorage.PRIMARY_KEY)) {
                                if (r2_StringA[1] == null || r2_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    try {
                                                    } catch (Exception e) {
                                                        return true;
                                                    }
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if (Integer.parseInt(r2_StringA[1]) > 0) {
                                    j.try = true;
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    j.try = false;
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (Integer.parseInt(r2_StringA[0]) > 0) {
                                j.void = true;
                                if (r2_StringA[1] == null || r2_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if (Integer.parseInt(r2_StringA[1]) > 0) {
                                    j.try = false;
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    j.try = true;
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                j.void = false;
                                if (r2_StringA[1] == null || r2_StringA[1].equals(RContactStorage.PRIMARY_KEY)) {
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if (Integer.parseInt(r2_StringA[1]) > 0) {
                                    j.try = true;
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    j.try = false;
                                    if (r2_StringA[2] == null || r2_StringA[2].equals(RContactStorage.PRIMARY_KEY)) {
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        j.V = Integer.parseInt(r2_StringA[2]);
                                        if (r2_StringA[3] == null || r2_StringA[3].equals(RContactStorage.PRIMARY_KEY)) {
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        } else {
                                            j.L = Integer.parseInt(r2_StringA[3]);
                                            if (r2_StringA[4] == null || r2_StringA[4].equals(RContactStorage.PRIMARY_KEY)) {
                                                if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                } else {
                                                    j.af = Integer.parseInt(r2_StringA[5]);
                                                }
                                            } else {
                                                r3i = Integer.parseInt(r2_StringA[4]);
                                                if (r3i <= 0) {
                                                    j.G = false;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                } else {
                                                    j.ac = (long) r3i;
                                                    j.M = (j.ac * 1000) * 60;
                                                    j.al = j.M >> 2;
                                                    if (r2_StringA[5] == null || r2_StringA[5].equals(RContactStorage.PRIMARY_KEY)) {
                                                    } else {
                                                        j.af = Integer.parseInt(r2_StringA[5]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    j.if(a, "config change true...");
                    return true;
                }
            } catch (Exception e_2) {
                return false;
            }
        }
        return false;
    }

    public static boolean if(String r4_String, Handler r5_Handler) {
        if (j || r4_String == null) {
            return false;
        }
        j = true;
        e = Jni.if(r4_String);
        j.if(a, "bloc : " + e);
        w = r5_Handler;
        if (g == null) {
            g = n.do();
        }
        new k().start();
        return true;
    }
}