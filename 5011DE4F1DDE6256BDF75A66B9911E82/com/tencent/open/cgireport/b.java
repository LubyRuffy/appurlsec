package com.tencent.open.cgireport;

import android.content.Context;
import android.os.Bundle;

// compiled from: ProGuard
class b extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ Bundle c;
    final /* synthetic */ ReportManager d;

    b(ReportManager r1_ReportManager, String r2_String, Context r3_Context, Bundle r4_Bundle) {
        this.d = r1_ReportManager;
        this.a = r2_String;
        this.b = r3_Context;
        this.c = r4_Bundle;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r8_this = this;
        r5 = 0;
        r3 = 1;
        r1 = 0;
        r0 = "cgi_report_debug";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "ReportManager doUploadItems Thread start, url = ";
        r2 = r2.append(r4);
        r4 = r8.a;
        r2 = r2.append(r4);
        r2 = r2.toString();
        android.util.Log.i(r0, r2);
        r0 = r8.d;
        r2 = r8.b;
        r2 = com.tencent.open.OpenConfig.a(r2, r5);
        r4 = "Common_HttpRetryCount";
        r2 = r2.b(r4);
        com.tencent.open.cgireport.ReportManager.a(r0, r2);
        r2 = r8.d;
        r0 = r8.d;
        r0 = com.tencent.open.cgireport.ReportManager.a(r0);
        if (r0 != 0) goto L_0x00c8;
    L_0x0038:
        r0 = 3;
    L_0x0039:
        com.tencent.open.cgireport.ReportManager.a(r2, r0);
        r0 = r1;
        r2 = r1;
    L_0x003e:
        r2 = r2 + 1;
        r4 = "cgi_report_debug";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "ReportManager doUploadItems Thread request count = ";
        r5 = r5.append(r6);
        r5 = r5.append(r2);
        r5 = r5.toString();
        android.util.Log.i(r4, r5);
        r4 = r8.b;	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r5 = 0;
        r6 = r8.a;	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r4 = com.tencent.open.Util.a(r4, r5, r6);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r5 = new org.apache.http.client.methods.HttpPost;	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r6 = r8.a;	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r5.<init>(r6);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r6 = "Accept-Encoding";
        r7 = "gzip";
        r5.addHeader(r6, r7);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r6 = "Content-Type";
        r7 = "application/x-www-form-urlencoded";
        r5.setHeader(r6, r7);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r6 = r8.c;	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r6 = com.tencent.open.Util.a(r6);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r6 = r6.getBytes();	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r7 = new org.apache.http.entity.ByteArrayEntity;	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r7.<init>(r6);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r5.setEntity(r7);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r4 = r4.execute(r5);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r4 = r4.getStatusLine();	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r4 = r4.getStatusCode();	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 == r5) goto L_0x00d0;
    L_0x0098:
        r4 = "cgi_report_debug";
        r5 = "ReportManager doUploadItems : HttpStatuscode != 200";
        android.util.Log.e(r4, r5);	 //Catch:{ ConnectTimeoutException -> 0x00d9, SocketTimeoutException -> 0x00ed, Exception -> 0x00f2 }
    L_0x009f:
        r2 = r8.d;
        com.tencent.open.cgireport.ReportManager.a(r2, r1);
        r1 = "cgi_report_debug";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "ReportManager doUploadItems Thread end, url = ";
        r2 = r2.append(r4);
        r4 = r8.a;
        r2 = r2.append(r4);
        r2 = r2.toString();
        android.util.Log.i(r1, r2);
        if (r0 != r3) goto L_0x00fe;
    L_0x00c0:
        r0 = "cgi_report_debug";
        r1 = "ReportManager doUploadItems Thread request success";
        android.util.Log.i(r0, r1);
    L_0x00c7:
        return;
    L_0x00c8:
        r0 = r8.d;
        r0 = com.tencent.open.cgireport.ReportManager.a(r0);
        goto L_0x0039;
    L_0x00d0:
        r0 = "cgi_report_debug";
        r4 = "ReportManager doUploadItems Thread success";
        android.util.Log.i(r0, r4);	 //Catch:{ ConnectTimeoutException -> 0x011d, SocketTimeoutException -> 0x0119, Exception -> 0x0115 }
        r0 = r3;
        goto L_0x009f;
    L_0x00d9:
        r4 = move-exception;
    L_0x00da:
        r4.printStackTrace();
        r4 = "cgi_report_debug";
        r5 = "ReportManager doUploadItems : ConnectTimeoutException";
        android.util.Log.e(r4, r5);
    L_0x00e4:
        r4 = r8.d;
        r4 = com.tencent.open.cgireport.ReportManager.a(r4);
        if (r2 < r4) goto L_0x003e;
    L_0x00ec:
        goto L_0x009f;
    L_0x00ed:
        r4 = move-exception;
    L_0x00ee:
        r4.printStackTrace();
        goto L_0x00e4;
    L_0x00f2:
        r2 = move-exception;
    L_0x00f3:
        r2.printStackTrace();
        r2 = "cgi_report_debug";
        r4 = "ReportManager doUploadItems : Exception";
        android.util.Log.e(r2, r4);
        goto L_0x009f;
    L_0x00fe:
        r0 = "cgi_report_debug";
        r1 = "ReportManager doUploadItems Thread request failed";
        android.util.Log.e(r0, r1);
        r0 = r8.d;
        r0 = com.tencent.open.cgireport.ReportManager.c(r0);
        r1 = r8.d;
        r1 = com.tencent.open.cgireport.ReportManager.b(r1);
        r0.a(r1);
        goto L_0x00c7;
    L_0x0115:
        r0 = move-exception;
        r2 = r0;
        r0 = r3;
        goto L_0x00f3;
    L_0x0119:
        r0 = move-exception;
        r4 = r0;
        r0 = r3;
        goto L_0x00ee;
    L_0x011d:
        r0 = move-exception;
        r4 = r0;
        r0 = r3;
        goto L_0x00da;
        */

    }
}