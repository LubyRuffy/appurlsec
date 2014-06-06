package com.tencent.cloudsdk;

import android.os.Bundle;
import android.os.Handler;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.tsocket.GlobalContext;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.image.ImageFetcher;

// compiled from: SourceFile
public class u extends Thread {
    public String a;
    public Bundle b;
    public Handler c;
    public int d;
    private w e;
    private volatile boolean f;
    private boolean g;
    private byte[] h;
    private boolean i;
    private Map j;
    private URI k;

    public u(String r9_String, boolean r10z, int r11i, byte[] r12_byteA, w r13_w) {
        this(r9_String, r10z, r11i, r12_byteA, r13_w, null, null);
    }

    public u(String r9_String, boolean r10z, int r11i, byte[] r12_byteA, w r13_w, Bundle r14_Bundle) {
        this(r9_String, r10z, r11i, r12_byteA, r13_w, null, r14_Bundle);
    }

    public u(String r5_String, boolean r6z, int r7i, byte[] r8_byteA, w r9_w, Handler r10_Handler, Bundle r11_Bundle) {
        this.e = null;
        this.f = false;
        this.a = RContactStorage.PRIMARY_KEY;
        this.b = null;
        this.g = true;
        this.h = null;
        this.c = null;
        this.d = 0;
        this.i = false;
        this.j = new HashMap();
        this.k = null;
        this.a = r5_String;
        this.g = r6z;
        this.d = r7i;
        this.h = r8_byteA;
        this.e = r9_w;
        this.c = r10_Handler;
        this.b = r11_Bundle;
        try {
            this.k = new URI(this.a);
        } catch (URISyntaxException e) {
            WnsClientLog.e("HttpTask", new StringBuilder(">>> HttpTask() E: ").append(e.toString()).toString());
        }
    }

    private void a(URI r4_URI, String r5_String) {
        if (r5_String.equalsIgnoreCase("cmwap") || r5_String.equalsIgnoreCase("cmnet")) {
            this.j.put("x-online-host", r4_URI.getHost());
        }
    }

    private void a(HttpClient r5_HttpClient, String r6_String) {
        if (r5_HttpClient == null) {
        } else if (r6_String.equalsIgnoreCase("cmwap") || r6_String.equalsIgnoreCase("3gwap") || r6_String.equalsIgnoreCase("uniwap")) {
            r5_HttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(HttpUtils.PROXY_IP, 80, ImageFetcher.HTTP_CACHE_DIR));
        } else {
            if (r6_String.equalsIgnoreCase("ctwap")) {
                r5_HttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost("10.0.0.200", 80, ImageFetcher.HTTP_CACHE_DIR));
            }
        }
    }

    private boolean a(URI r3_URI) {
        return r3_URI.getScheme() != null && r3_URI.getScheme().startsWith(ImageFetcher.HTTP_CACHE_DIR);
    }

    private HttpClient b() {
        HttpParams r0_HttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(r0_HttpParams, 30000);
        HttpConnectionParams.setSoTimeout(r0_HttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(r0_HttpParams, LVBuffer.LENGTH_ALLOC_PER_NEW);
        HttpClientParams.setRedirecting(r0_HttpParams, true);
        HttpClient r1_HttpClient = new DefaultHttpClient(r0_HttpParams);
        String r2_String = ax.a(GlobalContext.getContext());
        ((DefaultHttpClient) r1_HttpClient).setRedirectHandler(new eg(this, r2_String));
        a(r1_HttpClient, r2_String);
        a(this.k, r2_String);
        return r1_HttpClient;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
        r12_this = this;
        r4 = 0;
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = 0;
        r5 = r4;
        r4 = r3;
        r3 = r2;
    L_0x0008:
        r2 = r0 + 1;
        r0 = r12.k;
        if (r0 == 0) goto L_0x0016;
    L_0x000e:
        r0 = r12.k;
        r0 = r12.a(r0);
        if (r0 != 0) goto L_0x001e;
    L_0x0016:
        r0 = r12.e;
        r1 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0.a(r1, r12);
    L_0x001d:
        return;
    L_0x001e:
        r6 = r12.b();	 //Catch:{ Exception -> 0x0265, all -> 0x0261 }
        r0 = r12.f;	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        if (r0 == 0) goto L_0x0040;
    L_0x0026:
        r0 = r6.getConnectionManager();	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        r0.shutdown();	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        r0 = 0;
        if (r0 == 0) goto L_0x0037;
    L_0x0030:
        r0 = r0.getConnectionManager();
        r0.shutdown();
    L_0x0037:
        if (r4 == 0) goto L_0x0039;
    L_0x0039:
        r0 = 2;
        if (r2 < r0) goto L_0x001d;
    L_0x003c:
        r0 = 0;
        r12.i = r0;
        goto L_0x001d;
    L_0x0040:
        r0 = r12.g;	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        if (r0 == 0) goto L_0x00d7;
    L_0x0044:
        r5 = new org.apache.http.client.methods.HttpPost;	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        r5.<init>();	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        r0 = r12.k;	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r5.setURI(r0);	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r0 = "Content-Type";
        r3 = "application/octet-stream";
        r5.addHeader(r0, r3);	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r0 = r12.h;	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        if (r0 == 0) goto L_0x0288;
    L_0x0059:
        r0 = r12.h;	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r0 = r0.length;	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        if (r0 <= 0) goto L_0x0288;
    L_0x005e:
        r0 = new org.apache.http.entity.ByteArrayEntity;	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r3 = r12.h;	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r0.<init>(r3);	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r5.setEntity(r0);	 //Catch:{ Exception -> 0x0278, all -> 0x016b }
        r3 = r1;
    L_0x0069:
        r0 = r12.j;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0 = r0.size();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r0 <= 0) goto L_0x0081;
    L_0x0071:
        r0 = r12.j;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0 = r0.keySet();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r0.iterator();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
    L_0x007b:
        r0 = r7.hasNext();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r0 != 0) goto L_0x00ed;
    L_0x0081:
        r0 = new org.apache.http.protocol.BasicHttpContext;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0.<init>();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = r12.g;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r1 == 0) goto L_0x017e;
    L_0x008a:
        r1 = "HttpTask";
        r7 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = "httpSend ";
        r7.<init>(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = java.lang.Thread.currentThread();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = r8.getName();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.append(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = "URL:";
        r7 = r7.append(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = r5.getURI();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.append(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.toString();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.i(r1, r7);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r4 = r6.execute(r5, r0);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
    L_0x00b8:
        r0 = r12.f;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r0 == 0) goto L_0x01ae;
    L_0x00bc:
        r0 = r6.getConnectionManager();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0.shutdown();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0 = 0;
        if (r0 == 0) goto L_0x00cd;
    L_0x00c6:
        r0 = r0.getConnectionManager();
        r0.shutdown();
    L_0x00cd:
        if (r4 == 0) goto L_0x00cf;
    L_0x00cf:
        r0 = 2;
        if (r2 < r0) goto L_0x001d;
    L_0x00d2:
        r0 = 0;
        r12.i = r0;
        goto L_0x001d;
    L_0x00d7:
        r5 = new org.apache.http.client.methods.HttpGet;	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        r5.<init>();	 //Catch:{ Exception -> 0x026f, all -> 0x016b }
        r0 = r12.k;	 //Catch:{ Exception -> 0x0280, all -> 0x016b }
        r5.setURI(r0);	 //Catch:{ Exception -> 0x0280, all -> 0x016b }
        r0 = "Content-Type";
        r1 = "application/octet-stream";
        r5.addHeader(r0, r1);	 //Catch:{ Exception -> 0x0280, all -> 0x016b }
        r10 = r5;
        r5 = r3;
        r3 = r10;
        goto L_0x0069;
    L_0x00ed:
        r0 = r7.next();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0 = (java.lang.String) r0;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = r12.g;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r1 == 0) goto L_0x015e;
    L_0x00f7:
        r1 = r12.j;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = r1.get(r0);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = (java.lang.String) r1;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r5.addHeader(r0, r1);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        goto L_0x007b;
    L_0x0104:
        r0 = move-exception;
        r1 = r5;
        r5 = r4;
        r4 = r6;
        r10 = r3;
        r3 = r0;
        r0 = r10;
    L_0x010b:
        r3.printStackTrace();	 //Catch:{ all -> 0x025c }
        r6 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r7 = r3 instanceof org.apache.http.client.ClientProtocolException;	 //Catch:{ all -> 0x025c }
        if (r7 != 0) goto L_0x0120;
    L_0x0114:
        r7 = r3 instanceof java.net.SocketException;	 //Catch:{ all -> 0x025c }
        if (r7 != 0) goto L_0x0120;
    L_0x0118:
        r7 = r3 instanceof java.net.SocketTimeoutException;	 //Catch:{ all -> 0x025c }
        if (r7 != 0) goto L_0x0120;
    L_0x011c:
        r7 = r3 instanceof java.io.IOException;	 //Catch:{ all -> 0x025c }
        if (r7 == 0) goto L_0x0241;
    L_0x0120:
        r6 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x0122:
        r7 = "HttpTask";
        r8 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x025c }
        r9 = ">>> HttpTask.exec() E: ";
        r8.<init>(r9);	 //Catch:{ all -> 0x025c }
        r9 = r3.getMessage();	 //Catch:{ all -> 0x025c }
        r8 = r8.append(r9);	 //Catch:{ all -> 0x025c }
        r8 = r8.toString();	 //Catch:{ all -> 0x025c }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r7, r8, r3);	 //Catch:{ all -> 0x025c }
        r3 = 1;
        if (r2 != r3) goto L_0x0251;
    L_0x013d:
        r3 = 1;
        r12.i = r3;	 //Catch:{ all -> 0x025c }
    L_0x0140:
        if (r4 == 0) goto L_0x014a;
    L_0x0142:
        r3 = r4.getConnectionManager();
        r3.shutdown();
        r4 = 0;
    L_0x014a:
        if (r5 == 0) goto L_0x028b;
    L_0x014c:
        r3 = 0;
    L_0x014d:
        r5 = 2;
        if (r2 < r5) goto L_0x0153;
    L_0x0150:
        r5 = 0;
        r12.i = r5;
    L_0x0153:
        r5 = r12.i;
        if (r5 == 0) goto L_0x001d;
    L_0x0157:
        r5 = r4;
        r4 = r3;
        r3 = r1;
        r1 = r0;
        r0 = r2;
        goto L_0x0008;
    L_0x015e:
        r1 = r12.j;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = r1.get(r0);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = (java.lang.String) r1;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r3.addHeader(r0, r1);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        goto L_0x007b;
    L_0x016b:
        r0 = move-exception;
    L_0x016c:
        if (r6 == 0) goto L_0x0175;
    L_0x016e:
        r1 = r6.getConnectionManager();
        r1.shutdown();
    L_0x0175:
        if (r4 == 0) goto L_0x0177;
    L_0x0177:
        r1 = 2;
        if (r2 < r1) goto L_0x017d;
    L_0x017a:
        r1 = 0;
        r12.i = r1;
    L_0x017d:
        throw r0;
    L_0x017e:
        r1 = "HttpTask";
        r7 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = "httpSend ";
        r7.<init>(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = java.lang.Thread.currentThread();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = r8.getName();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.append(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = "URL:";
        r7 = r7.append(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = r3.getURI();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.append(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.toString();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.i(r1, r7);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r4 = r6.execute(r3, r0);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        goto L_0x00b8;
    L_0x01ae:
        r0 = r4.getStatusLine();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0 = r0.getStatusCode();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 == r1) goto L_0x01f9;
    L_0x01ba:
        r1 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r0 == r1) goto L_0x01f9;
    L_0x01be:
        r1 = r12.g;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r1 == 0) goto L_0x01f5;
    L_0x01c2:
        r5.abort();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
    L_0x01c5:
        r1 = r12.e;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r1 == 0) goto L_0x01e2;
    L_0x01c9:
        r1 = "HttpTask";
        r7 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r8 = ">>> HttpTask.exec() E: Http \u9519\u8bef\uff1a ";
        r7.<init>(r8);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.append(r0);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r7 = r7.toString();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        com.tencent.cloudsdk.common.record.debug.WnsClientLog.e(r1, r7);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1 = r12.e;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r1.a(r0, r12);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
    L_0x01e2:
        if (r6 == 0) goto L_0x01eb;
    L_0x01e4:
        r0 = r6.getConnectionManager();
        r0.shutdown();
    L_0x01eb:
        if (r4 == 0) goto L_0x01ed;
    L_0x01ed:
        r0 = 2;
        if (r2 < r0) goto L_0x001d;
    L_0x01f0:
        r0 = 0;
        r12.i = r0;
        goto L_0x001d;
    L_0x01f5:
        r3.abort();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        goto L_0x01c5;
    L_0x01f9:
        r0 = r12.f;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r0 == 0) goto L_0x0218;
    L_0x01fd:
        r0 = r6.getConnectionManager();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0.shutdown();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0 = 0;
        if (r0 == 0) goto L_0x020e;
    L_0x0207:
        r0 = r0.getConnectionManager();
        r0.shutdown();
    L_0x020e:
        if (r4 == 0) goto L_0x0210;
    L_0x0210:
        r0 = 2;
        if (r2 < r0) goto L_0x001d;
    L_0x0213:
        r0 = 0;
        r12.i = r0;
        goto L_0x001d;
    L_0x0218:
        r0 = r12.e;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        if (r0 == 0) goto L_0x0221;
    L_0x021c:
        r0 = r12.e;	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0.a(r4, r12);	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
    L_0x0221:
        if (r6 == 0) goto L_0x022b;
    L_0x0223:
        r0 = r6.getConnectionManager();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r0.shutdown();	 //Catch:{ Exception -> 0x0104, all -> 0x016b }
        r6 = 0;
    L_0x022b:
        if (r4 == 0) goto L_0x022e;
    L_0x022d:
        r4 = 0;
    L_0x022e:
        if (r6 == 0) goto L_0x0237;
    L_0x0230:
        r0 = r6.getConnectionManager();
        r0.shutdown();
    L_0x0237:
        if (r4 == 0) goto L_0x0239;
    L_0x0239:
        r0 = 2;
        if (r2 < r0) goto L_0x001d;
    L_0x023c:
        r0 = 0;
        r12.i = r0;
        goto L_0x001d;
    L_0x0241:
        r7 = r3 instanceof com.tencent.cloudsdk.r;	 //Catch:{ all -> 0x025c }
        if (r7 == 0) goto L_0x0249;
    L_0x0245:
        r6 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x0122;
    L_0x0249:
        r7 = r3 instanceof com.tencent.cloudsdk.s;	 //Catch:{ all -> 0x025c }
        if (r7 == 0) goto L_0x0122;
    L_0x024d:
        r6 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x0122;
    L_0x0251:
        r3 = r12.e;	 //Catch:{ all -> 0x025c }
        if (r3 == 0) goto L_0x0140;
    L_0x0255:
        r3 = r12.e;	 //Catch:{ all -> 0x025c }
        r3.a(r6, r12);	 //Catch:{ all -> 0x025c }
        goto L_0x0140;
    L_0x025c:
        r0 = move-exception;
        r6 = r4;
        r4 = r5;
        goto L_0x016c;
    L_0x0261:
        r0 = move-exception;
        r6 = r5;
        goto L_0x016c;
    L_0x0265:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r3;
        r3 = r10;
        r11 = r4;
        r4 = r5;
        r5 = r11;
        goto L_0x010b;
    L_0x026f:
        r0 = move-exception;
        r5 = r4;
        r4 = r6;
        r10 = r3;
        r3 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x010b;
    L_0x0278:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r5;
        r5 = r4;
        r4 = r6;
        goto L_0x010b;
    L_0x0280:
        r0 = move-exception;
        r1 = r3;
        r3 = r0;
        r0 = r5;
        r5 = r4;
        r4 = r6;
        goto L_0x010b;
    L_0x0288:
        r3 = r1;
        goto L_0x0069;
    L_0x028b:
        r3 = r5;
        goto L_0x014d;
        */

    }

    public void run() {
        if (ax.b() != -1) {
            a();
        } else {
            WnsClientLog.i("HttpTask", ">>> httpTask \u6ca1\u6709\u7f51\u7edc\uff01");
        }
    }
}