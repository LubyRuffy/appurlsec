package com.tencent.cloudsdk.defaultsdk.mna.tsocket;

import java.util.Random;

// compiled from: SourceFile
public class TSocketMain {
    private static String[] a;
    private static Random b;

    static {
        String[] r0_StringA = new String[2];
        r0_StringA[0] = "ocspeed2.tc.qq.com:80";
        r0_StringA[1] = "cmatjrstest.tc.qq.com:8080";
        a = r0_StringA;
        b = new Random();
    }

    private static String a() {
        int r0i = b.nextInt(a.length);
        return r0i < a.length ? a[r0i] : a[0];
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void test() {
        /*
        r2 = 0;
        r0 = a();	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r1 = ":";
        r0 = r0.split(r1);	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r1 = 0;
        r3 = r0[r1];	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r1 = 1;
        r0 = r0[r1];	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r0 = java.lang.Integer.valueOf(r0);	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r0 = r0.intValue();	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r1 = com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext.getContext();	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        com.tencent.cloudsdk.et.c(r1, r3);	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r1 = new com.tencent.cloudsdk.defaultsdk.mna.tsocket.TSocket;	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r1.<init>();	 //Catch:{ UnknownHostException -> 0x0060, IOException -> 0x0066 }
        r2 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r1.connect(r3, r0, r2);	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r1.setSoTimeout(r0);	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r0 = r1.getOutputStream();	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r2 = r1.getInputStream();	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r3 = new java.io.PrintWriter;	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r3.<init>(r0);	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r0 = "0";
        r3.println(r0);	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r3.flush();	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r0 = new java.io.BufferedReader;	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r3 = new java.io.InputStreamReader;	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r3.<init>(r2);	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r0.<init>(r3);	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r0.readLine();	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
        r0.close();	 //Catch:{ UnknownHostException -> 0x0073, IOException -> 0x0071 }
    L_0x0054:
        if (r1 == 0) goto L_0x005f;
    L_0x0056:
        r0 = r1.isClosed();
        if (r0 != 0) goto L_0x005f;
    L_0x005c:
        r1.close();	 //Catch:{ IOException -> 0x006c }
    L_0x005f:
        return;
    L_0x0060:
        r0 = move-exception;
        r1 = r2;
    L_0x0062:
        r0.printStackTrace();
        goto L_0x0054;
    L_0x0066:
        r0 = move-exception;
        r1 = r2;
    L_0x0068:
        r0.printStackTrace();
        goto L_0x0054;
    L_0x006c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x005f;
    L_0x0071:
        r0 = move-exception;
        goto L_0x0068;
    L_0x0073:
        r0 = move-exception;
        goto L_0x0062;
        */

    }
}