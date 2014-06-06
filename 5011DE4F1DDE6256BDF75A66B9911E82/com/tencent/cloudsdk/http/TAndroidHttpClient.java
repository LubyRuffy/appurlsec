package com.tencent.cloudsdk.http;

import android.content.ContentResolver;
import android.content.Context;
import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.d;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class TAndroidHttpClient {
    public static long DEFAULT_SYNC_MIN_GZIP_BYTES;
    private static final String a;
    private d b;

    static {
        a = TAndroidHttpClient.class.getName();
        DEFAULT_SYNC_MIN_GZIP_BYTES = 256;
    }

    private TAndroidHttpClient(ClientConnectionManager r7_ClientConnectionManager, HttpParams r8_HttpParams) {
        int r3i = XListViewHeader.STATE_REFRESHING;
        int r2i = 1;
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            Class[] r4_ClassA = new Class[r3i];
            r4_ClassA[r1i] = ClientConnectionManager.class;
            r4_ClassA[r2i] = HttpParams.class;
            Object[] r5_ObjectA = new Object[r3i];
            r5_ObjectA[r1i] = r7_ClientConnectionManager;
            r5_ObjectA[r2i] = r8_HttpParams;
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TAndroidHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient", r4_ClassA, r5_ObjectA);
        }
    }

    public static AbstractHttpEntity getCompressedEntity(byte[] r6_byteA, ContentResolver r7_ContentResolver) throws IOException {
        Class[] r4_ClassA = new Class[2];
        r4_ClassA[0] = byte[].class;
        r4_ClassA[1] = ContentResolver.class;
        Object[] r5_ObjectA = new Object[2];
        r5_ObjectA[0] = r6_byteA;
        r5_ObjectA[1] = r7_ContentResolver;
        return (AbstractHttpEntity) d.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TAndroidHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient", "getCompressedEntity", r4_ClassA, r5_ObjectA);
    }

    public static long getMinGzipSize(ContentResolver r6_ContentResolver) {
        Class[] r4_ClassA = new Class[1];
        r4_ClassA[0] = ContentResolver.class;
        Object[] r5_ObjectA = new Object[1];
        r5_ObjectA[0] = r6_ContentResolver;
        return ((Long) d.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TAndroidHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient", "getMinGzipSize", r4_ClassA, r5_ObjectA)).longValue();
    }

    public static InputStream getUngzippedContent(HttpEntity r6_HttpEntity) throws IOException {
        Class[] r4_ClassA = new Class[1];
        r4_ClassA[0] = HttpEntity.class;
        Object[] r5_ObjectA = new Object[1];
        r5_ObjectA[0] = r6_HttpEntity;
        return (InputStream) d.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TAndroidHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient", "getUngzippedContent", r4_ClassA, r5_ObjectA);
    }

    public static void modifyRequestToAcceptGzipResponse(HttpRequest r6_HttpRequest) {
        Class[] r4_ClassA = new Class[1];
        r4_ClassA[0] = HttpRequest.class;
        Object[] r5_ObjectA = new Object[1];
        r5_ObjectA[0] = r6_HttpRequest;
        d.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TAndroidHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient", "modifyRequestToAcceptGzipResponse", r4_ClassA, r5_ObjectA);
    }

    public static TAndroidHttpClient newInstance(String r1_String) {
        return newInstance(r1_String, null);
    }

    public static TAndroidHttpClient newInstance(String r6_String, Context r7_Context) {
        Class[] r4_ClassA = new Class[2];
        r4_ClassA[0] = String.class;
        r4_ClassA[1] = Context.class;
        Object[] r5_ObjectA = new Object[2];
        r5_ObjectA[0] = r6_String;
        r5_ObjectA[1] = r7_Context;
        return (TAndroidHttpClient) d.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TAndroidHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient", "newInstance", r4_ClassA, r5_ObjectA);
    }

    public void close() {
        int r1i = 0;
        if (this.b != null) {
            this.b.a("close", new Class[r1i], new Object[r1i]);
        }
    }

    public void disableCurlLogging() {
        int r1i = 0;
        if (this.b != null) {
            this.b.a("disableCurlLogging", new Class[r1i], new Object[r1i]);
        }
    }

    public void enableCurlLogging(String r6_String, int r7i) {
        int r3i = 1;
        int r2i = 0;
        if (this.b != null) {
            Class[] r0_ClassA = new Class[2];
            r0_ClassA[r2i] = String.class;
            r0_ClassA[r3i] = Integer.TYPE;
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[r2i] = r6_String;
            r1_ObjectA[r3i] = Integer.valueOf(r7i);
            this.b.a("enableCurlLogging", r0_ClassA, r1_ObjectA);
        }
    }

    public Object execute(HttpHost r7_HttpHost, HttpRequest r8_HttpRequest, ResponseHandler r9_ResponseHandler) throws IOException, ClientProtocolException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[3];
        r0_ClassA[r2i] = HttpHost.class;
        r0_ClassA[r3i] = HttpRequest.class;
        r0_ClassA[2] = ResponseHandler.class;
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[r2i] = r7_HttpHost;
        r1_ObjectA[r3i] = r8_HttpRequest;
        r1_ObjectA[2] = r9_ResponseHandler;
        return this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    public Object execute(HttpHost r8_HttpHost, HttpRequest r9_HttpRequest, ResponseHandler r10_ResponseHandler, HttpContext r11_HttpContext) throws IOException, ClientProtocolException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[4];
        r0_ClassA[r2i] = HttpHost.class;
        r0_ClassA[r3i] = HttpRequest.class;
        r0_ClassA[2] = ResponseHandler.class;
        r0_ClassA[3] = HttpContext.class;
        Object[] r1_ObjectA = new Object[4];
        r1_ObjectA[r2i] = r8_HttpHost;
        r1_ObjectA[r3i] = r9_HttpRequest;
        r1_ObjectA[2] = r10_ResponseHandler;
        r1_ObjectA[3] = r11_HttpContext;
        return this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    public Object execute(HttpUriRequest r6_HttpUriRequest, ResponseHandler r7_ResponseHandler) throws IOException, ClientProtocolException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[2];
        r0_ClassA[r2i] = HttpUriRequest.class;
        r0_ClassA[r3i] = ResponseHandler.class;
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[r2i] = r6_HttpUriRequest;
        r1_ObjectA[r3i] = r7_ResponseHandler;
        return this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    public Object execute(HttpUriRequest r7_HttpUriRequest, ResponseHandler r8_ResponseHandler, HttpContext r9_HttpContext) throws IOException, ClientProtocolException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[3];
        r0_ClassA[r2i] = HttpUriRequest.class;
        r0_ClassA[r3i] = ResponseHandler.class;
        r0_ClassA[2] = HttpContext.class;
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[r2i] = r7_HttpUriRequest;
        r1_ObjectA[r3i] = r8_ResponseHandler;
        r1_ObjectA[2] = r9_HttpContext;
        return this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    public HttpResponse execute(HttpHost r6_HttpHost, HttpRequest r7_HttpRequest) throws IOException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[2];
        r0_ClassA[r2i] = HttpHost.class;
        r0_ClassA[r3i] = HttpRequest.class;
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[r2i] = r6_HttpHost;
        r1_ObjectA[r3i] = r7_HttpRequest;
        return (HttpResponse) this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    public HttpResponse execute(HttpHost r7_HttpHost, HttpRequest r8_HttpRequest, HttpContext r9_HttpContext) throws IOException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[3];
        r0_ClassA[r2i] = HttpHost.class;
        r0_ClassA[r3i] = HttpRequest.class;
        r0_ClassA[2] = HttpContext.class;
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[r2i] = r7_HttpHost;
        r1_ObjectA[r3i] = r8_HttpRequest;
        r1_ObjectA[2] = r9_HttpContext;
        return (HttpResponse) this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    public HttpResponse execute(HttpUriRequest r5_HttpUriRequest) throws IOException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[r3i];
        r0_ClassA[r2i] = HttpUriRequest.class;
        Object[] r1_ObjectA = new Object[r3i];
        r1_ObjectA[r2i] = r5_HttpUriRequest;
        return (HttpResponse) this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    public HttpResponse execute(HttpUriRequest r6_HttpUriRequest, HttpContext r7_HttpContext) throws IOException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[2];
        r0_ClassA[r2i] = HttpUriRequest.class;
        r0_ClassA[r3i] = HttpContext.class;
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[r2i] = r6_HttpUriRequest;
        r1_ObjectA[r3i] = r7_HttpContext;
        return (HttpResponse) this.b.a("execute", r0_ClassA, r1_ObjectA);
    }

    protected void finalize() throws Throwable {
        int r1i = 0;
        if (this.b != null) {
            this.b.a("finalize", new Class[r1i], new Object[r1i]);
        }
    }

    public ClientConnectionManager getConnectionManager() {
        int r1i = 0;
        return this.b != null ? (ClientConnectionManager) this.b.a("getConnectionManager", new Class[r1i], new Object[r1i]) : null;
    }

    public HttpClient getHttpClient() {
        return this.b != null ? (HttpClient) this.b.b() : null;
    }

    public HttpParams getParams() {
        int r1i = 0;
        return this.b != null ? (HttpParams) this.b.a("getParams", new Class[r1i], new Object[r1i]) : null;
    }
}