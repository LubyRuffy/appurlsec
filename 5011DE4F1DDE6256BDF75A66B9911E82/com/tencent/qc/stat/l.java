package com.tencent.qc.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.qc.stat.common.StatCommonHelper;
import com.tencent.qc.stat.common.StatLogger;
import com.tencent.qc.stat.event.Event;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.bean.Base;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: ProGuard
class l {
    static final byte[] c;
    private static StatLogger d;
    private static long e;
    private static l f;
    private static Context g;
    DefaultHttpClient a;
    Handler b;

    static {
        d = StatCommonHelper.b();
        e = -1;
        c = "03a976511e2cbe3a7f26808fb7af3c05".getBytes();
        f = new l();
        g = null;
    }

    private l() {
        this.a = null;
        this.b = null;
        HandlerThread r0_HandlerThread = new HandlerThread("StatDispatcher");
        r0_HandlerThread.start();
        e = r0_HandlerThread.getId();
        this.b = new Handler(r0_HandlerThread.getLooper());
        HttpParams r0_HttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(r0_HttpParams, 10000);
        HttpConnectionParams.setSoTimeout(r0_HttpParams, 10000);
        this.a = new DefaultHttpClient(r0_HttpParams);
        this.a.setKeepAliveStrategy(new n(this));
        if (StatConfig.i() != null) {
            this.a.getParams().setParameter("http.route.default-proxy", StatConfig.i());
        }
    }

    static Context a() {
        return g;
    }

    static void a(Context r1_Context) {
        g = r1_Context.getApplicationContext();
    }

    static l b() {
        return f;
    }

    void a(Event r4_Event, q r5_q) {
        String[] r0_StringA = new String[1];
        r0_StringA[0] = r4_Event.d();
        b(Arrays.asList(r0_StringA), r5_q);
    }

    void a(List r9_List, q r10_q) {
        int r2i = 0;
        StringBuilder r3_StringBuilder = new StringBuilder();
        r3_StringBuilder.append("[");
        int r1i = 0;
        while (r1i < r9_List.size()) {
            r3_StringBuilder.append((String) r9_List.get(r1i));
            if (r1i != r9_List.size() - 1) {
                r3_StringBuilder.append(",");
            }
            r1i++;
        }
        r3_StringBuilder.append("]");
        d.b("[" + StatConfig.j() + "]Send request(" + r3_StringBuilder.toString().length() + "bytes):" + r3_StringBuilder.toString());
        HttpUriRequest r1_HttpUriRequest = new HttpPost(HttpUtils.http + StatConfig.j() + "/qqconnectutil/sdk");
        try {
            r1_HttpUriRequest.addHeader("Accept-Encoding", "gzip");
            r1_HttpUriRequest.setHeader("Connection", "Keep-Alive");
            r1_HttpUriRequest.removeHeaders("Cache-Control");
            HttpHost r4_HttpHost = StatCommonHelper.a(g);
            if (r4_HttpHost != null) {
                this.a.getParams().setParameter("http.route.default-proxy", StatCommonHelper.a(g));
                r1_HttpUriRequest.addHeader("X-Online-Host", "cgi.connect.qq.com");
                r1_HttpUriRequest.addHeader("Accept", "*/*");
                r1_HttpUriRequest.addHeader("Content-Type", "json");
                r2i = 1;
            }
            OutputStream r5_OutputStream = new ByteArrayOutputStream();
            byte[] r0_byteA = r3_StringBuilder.toString().getBytes(Base.UTF8);
            int r6i = r0_byteA.length;
            if (r3_StringBuilder.length() < 256) {
                if (r4_HttpHost == null) {
                    r1_HttpUriRequest.addHeader("Content-Encoding", "rc4");
                } else {
                    r1_HttpUriRequest.addHeader("X-Content-Encoding", "rc4");
                }
            } else {
                if (r4_HttpHost == null) {
                    r1_HttpUriRequest.addHeader("Content-Encoding", "rc4,gzip");
                } else {
                    r1_HttpUriRequest.addHeader("X-Content-Encoding", "rc4,gzip");
                }
                r5_OutputStream.write(new byte[4]);
                GZIPOutputStream r3_GZIPOutputStream = new GZIPOutputStream(r5_OutputStream);
                r3_GZIPOutputStream.write(r0_byteA);
                r3_GZIPOutputStream.close();
                r0_byteA = r5_OutputStream.toByteArray();
                ByteBuffer.wrap(r0_byteA, 0, XListViewFooter.STATE_NODATA).putInt(r6i);
                d.h("before Gzip:" + r6i + " bytes, after Gzip:" + r0_byteA.length + " bytes");
            }
            r1_HttpUriRequest.setEntity(new ByteArrayEntity(j.a(r0_byteA, c)));
            HttpResponse r1_HttpResponse = this.a.execute(r1_HttpUriRequest);
            if (r2i != 0) {
                this.a.getParams().removeParameter("http.route.default-proxy");
            }
            HttpEntity r0_HttpEntity = r1_HttpResponse.getEntity();
            if (r1_HttpResponse.getStatusLine().getStatusCode() == 200) {
                if (r0_HttpEntity.getContentLength() > 0) {
                    InputStream r2_InputStream = r0_HttpEntity.getContent();
                    r0_byteA = new byte[((int) r0_HttpEntity.getContentLength())];
                    new DataInputStream(r2_InputStream).readFully(r0_byteA);
                    Header r3_Header = r1_HttpResponse.getFirstHeader("Content-Encoding");
                    if (r3_Header != null) {
                        if (r3_Header.getValue().equalsIgnoreCase("gzip,rc4")) {
                            r0_byteA = j.b(StatCommonHelper.a(r0_byteA), c);
                        } else if (r3_Header.getValue().equalsIgnoreCase("rc4,gzip")) {
                            r0_byteA = StatCommonHelper.a(j.b(r0_byteA, c));
                        } else if (r3_Header.getValue().equalsIgnoreCase("gzip")) {
                            r0_byteA = StatCommonHelper.a(r0_byteA);
                        } else if (r3_Header.getValue().equalsIgnoreCase("rc4")) {
                            r0_byteA = j.b(r0_byteA, c);
                        }
                        try {
                            d.h(new String(r0_byteA, Base.UTF8));
                            JSONObject r0_JSONObject = new JSONObject(new String(r0_byteA, Base.UTF8)).getJSONObject("cfg");
                            if (r0_JSONObject != null) {
                                StatConfig.a(r0_JSONObject);
                            }
                        } catch (JSONException e) {
                            d.b(e.toString());
                        }
                    }
                    r2_InputStream.close();
                } else {
                    EntityUtils.toString(r0_HttpEntity);
                }
            }
            d.b("recv response:" + Integer.toString(r1_HttpResponse.getStatusLine().getStatusCode()));
            if (r10_q != null) {
                r10_q.a();
            }
        } catch (Exception e_2) {
            d.b(e_2);
            if (r10_q != null) {
                r10_q.b();
            }
        }
    }

    void b(List r3_List, q r4_q) {
        if (r3_List.isEmpty()) {
        } else {
            this.b.post(new o(this, r3_List, r4_q));
        }
    }
}