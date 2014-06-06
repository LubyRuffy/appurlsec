package com.tencent.cloudsdk.defaultsdk.mna.http;

import android.content.ContentResolver;
import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.util.Base64;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import com.tencent.cloudsdk.da;
import com.tencent.cloudsdk.db;
import com.tencent.cloudsdk.dd;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import qsbk.app.activity.base.MysBaseActivity;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class TAndroidHttpClient implements HttpClient {
    public static long DEFAULT_SYNC_MIN_GZIP_BYTES;
    private static String[] a;
    private static final HttpRequestInterceptor b;
    private final HttpClient c;
    private RuntimeException d;
    private volatile dd e;

    static {
        DEFAULT_SYNC_MIN_GZIP_BYTES = 256;
        String[] r0_StringA = new String[3];
        r0_StringA[0] = "text/";
        r0_StringA[1] = "application/xml";
        r0_StringA[2] = "application/json";
        a = r0_StringA;
        b = new da();
    }

    private TAndroidHttpClient(ClientConnectionManager r3_ClientConnectionManager, HttpParams r4_HttpParams) {
        this.d = new IllegalStateException("AndroidHttpClient created and never closed");
        this.c = new db(this, r3_ClientConnectionManager, r4_HttpParams);
    }

    public static AbstractHttpEntity getCompressedEntity(byte[] r4_byteA, ContentResolver r5_ContentResolver) throws IOException {
        if (((long) r4_byteA.length) < getMinGzipSize(r5_ContentResolver)) {
            return new ByteArrayEntity(r4_byteA);
        }
        OutputStream r1_OutputStream = new ByteArrayOutputStream();
        OutputStream r0_OutputStream = new GZIPOutputStream(r1_OutputStream);
        r0_OutputStream.write(r4_byteA);
        r0_OutputStream.close();
        AbstractHttpEntity r0_AbstractHttpEntity = new ByteArrayEntity(r1_OutputStream.toByteArray());
        r0_AbstractHttpEntity.setContentEncoding("gzip");
        return r0_AbstractHttpEntity;
    }

    public static long getMinGzipSize(ContentResolver r2_ContentResolver) {
        return DEFAULT_SYNC_MIN_GZIP_BYTES;
    }

    public static InputStream getUngzippedContent(HttpEntity r3_HttpEntity) throws IOException {
        InputStream r1_InputStream = r3_HttpEntity.getContent();
        if (r1_InputStream == null) {
            return r1_InputStream;
        }
        Header r0_Header = r3_HttpEntity.getContentEncoding();
        if (r0_Header == null) {
            return r1_InputStream;
        }
        String r0_String = r0_Header.getValue();
        if (r0_String == null) {
            return r1_InputStream;
        }
        return r0_String.contains("gzip") ? new GZIPInputStream(r1_InputStream) : r1_InputStream;
    }

    private static boolean isBinaryContent(HttpUriRequest r11_HttpUriRequest) {
        int r2i;
        Header[] r3_HeaderA = r11_HttpUriRequest.getHeaders("Content-Encoding");
        if (r3_HeaderA != null) {
            int r4i = r3_HeaderA.length;
            r2i = 0;
            while (r2i < r4i) {
                if ("gzip".equalsIgnoreCase(r3_HeaderA[r2i].getValue())) {
                    return true;
                }
                r2i++;
            }
        }
        Header[] r4_HeaderA = r11_HttpUriRequest.getHeaders("Content-Type");
        if (r4_HeaderA == null) {
            return true;
        }
        int r5i = r4_HeaderA.length;
        int r3i = 0;
        while (r3i < r5i) {
            Header r6_Header = r4_HeaderA[r3i];
            String[] r7_StringA = a;
            int r8i = r7_StringA.length;
            r2i = 0;
            while (r2i < r8i) {
                if (r6_Header.getValue().startsWith(r7_StringA[r2i])) {
                    return false;
                }
                r2i++;
            }
            r3i++;
        }
        return true;
    }

    public static void modifyRequestToAcceptGzipResponse(HttpRequest r2_HttpRequest) {
        r2_HttpRequest.addHeader("Accept-Encoding", "gzip");
    }

    public static com.tencent.cloudsdk.http.TAndroidHttpClient newInstance(String r1_String) {
        return newInstance(r1_String, null);
    }

    public static com.tencent.cloudsdk.http.TAndroidHttpClient newInstance(String r12_String, Context r13_Context) {
        SSLSessionCache r0_SSLSessionCache;
        HttpParams r2_HttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(r2_HttpParams, false);
        HttpConnectionParams.setConnectionTimeout(r2_HttpParams, MysBaseActivity.DEFAULT_REFRESH_INTERVAL);
        HttpConnectionParams.setSoTimeout(r2_HttpParams, MysBaseActivity.DEFAULT_REFRESH_INTERVAL);
        HttpConnectionParams.setSocketBufferSize(r2_HttpParams, Utils.IO_BUFFER_SIZE);
        HttpClientParams.setRedirecting(r2_HttpParams, false);
        r0_SSLSessionCache = r13_Context == null ? null : new SSLSessionCache(r13_Context);
        HttpProtocolParams.setUserAgent(r2_HttpParams, r12_String);
        SchemeRegistry r3_SchemeRegistry = new SchemeRegistry();
        r3_SchemeRegistry.register(new Scheme(ImageFetcher.HTTP_CACHE_DIR, PlainSocketFactory.getSocketFactory(), 80));
        r3_SchemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(MysBaseActivity.DEFAULT_REFRESH_INTERVAL, r0_SSLSessionCache), 443));
        ThreadSafeClientConnManager r0_ThreadSafeClientConnManager = new ThreadSafeClientConnManager(r2_HttpParams, r3_SchemeRegistry);
        Class[] r3_ClassA = new Class[2];
        r3_ClassA[0] = ClientConnectionManager.class;
        r3_ClassA[1] = HttpParams.class;
        Object[] r4_ObjectA = new Object[2];
        r4_ObjectA[0] = r0_ThreadSafeClientConnManager;
        r4_ObjectA[1] = r2_HttpParams;
        try {
            Constructor r0_Constructor = com.tencent.cloudsdk.http.TAndroidHttpClient.class.getDeclaredConstructor(r3_ClassA);
            r0_Constructor.setAccessible(true);
            return (com.tencent.cloudsdk.http.TAndroidHttpClient) r0_Constructor.newInstance(r4_ObjectA);
        } catch (Exception e) {
            Throwable r0_Throwable = e;
            WnsClientLog.e("AndroidHttpClient", new StringBuilder(">>> newInstance E: ").append(r0_Throwable.getMessage()).toString(), r0_Throwable);
            return null;
        }
    }

    private static String toCurl(HttpUriRequest r8_HttpUriRequest, boolean r9z) throws IOException {
        URI r0_URI;
        StringBuilder r3_StringBuilder = new StringBuilder();
        r3_StringBuilder.append("curl ");
        Header[] r1_HeaderA = r8_HttpUriRequest.getAllHeaders();
        int r4i = r1_HeaderA.length;
        int r0i = 0;
        while (r0i < r4i) {
            Header r5_Header = r1_HeaderA[r0i];
            if (r9z) {
                r3_StringBuilder.append("--header \"");
                r3_StringBuilder.append(r5_Header.toString().trim());
                r3_StringBuilder.append("\" ");
            } else if (r5_Header.getName().equals("Authorization") || r5_Header.getName().equals("Cookie")) {
                r0i++;
            } else {
                r3_StringBuilder.append("--header \"");
                r3_StringBuilder.append(r5_Header.toString().trim());
                r3_StringBuilder.append("\" ");
            }
            r0i++;
        }
        URI r1_URI = r8_HttpUriRequest.getURI();
        if (r8_HttpUriRequest instanceof RequestWrapper) {
            HttpRequest r0_HttpRequest = ((RequestWrapper) r8_HttpUriRequest).getOriginal();
            if (r0_HttpRequest instanceof HttpUriRequest) {
                r0_URI = ((HttpUriRequest) r0_HttpRequest).getURI();
            }
            r0_URI = r1_URI;
        } else {
            r0_URI = r1_URI;
        }
        r3_StringBuilder.append("\"");
        r3_StringBuilder.append(r0_URI);
        r3_StringBuilder.append("\"");
        if (r8_HttpUriRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntity r0_HttpEntity = ((HttpEntityEnclosingRequest) r8_HttpUriRequest).getEntity();
            if (r0_HttpEntity == null || (!r0_HttpEntity.isRepeatable())) {
            } else if (r0_HttpEntity.getContentLength() < 1024) {
                OutputStream r1_OutputStream = new ByteArrayOutputStream();
                r0_HttpEntity.writeTo(r1_OutputStream);
                if (isBinaryContent(r8_HttpUriRequest)) {
                    r3_StringBuilder.insert(0, new StringBuilder("echo '").append(Base64.encodeToString(r1_OutputStream.toByteArray(), XListViewHeader.STATE_REFRESHING)).append("' | base64 -d > /tmp/$$.bin; ").toString());
                    r3_StringBuilder.append(" --data-binary @/tmp/$$.bin");
                } else {
                    r3_StringBuilder.append(" --data-ascii \"").append(r1_OutputStream.toString()).append("\"");
                }
            } else {
                r3_StringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
            }
        }
        return r3_StringBuilder.toString();
    }

    public void close() {
        if (this.d != null) {
            getConnectionManager().shutdown();
            this.d = null;
        }
    }

    public void disableCurlLogging() {
        this.e = null;
    }

    public void enableCurlLogging(String r3_String, int r4i) {
        if (r3_String == null) {
            throw new NullPointerException("name");
        } else if (r4i < 2 || r4i > 7) {
            throw new IllegalArgumentException("Level is out of range [2..7]");
        } else {
            this.e = new dd(r4i, null);
        }
    }

    public Object execute(HttpHost r2_HttpHost, HttpRequest r3_HttpRequest, ResponseHandler r4_ResponseHandler) throws IOException, ClientProtocolException {
        return this.c.execute(r2_HttpHost, r3_HttpRequest, r4_ResponseHandler);
    }

    public Object execute(HttpHost r2_HttpHost, HttpRequest r3_HttpRequest, ResponseHandler r4_ResponseHandler, HttpContext r5_HttpContext) throws IOException, ClientProtocolException {
        return this.c.execute(r2_HttpHost, r3_HttpRequest, r4_ResponseHandler, r5_HttpContext);
    }

    public Object execute(HttpUriRequest r2_HttpUriRequest, ResponseHandler r3_ResponseHandler) throws IOException, ClientProtocolException {
        return this.c.execute(r2_HttpUriRequest, r3_ResponseHandler);
    }

    public Object execute(HttpUriRequest r2_HttpUriRequest, ResponseHandler r3_ResponseHandler, HttpContext r4_HttpContext) throws IOException, ClientProtocolException {
        return this.c.execute(r2_HttpUriRequest, r3_ResponseHandler, r4_HttpContext);
    }

    public HttpResponse execute(HttpHost r2_HttpHost, HttpRequest r3_HttpRequest) throws IOException {
        return this.c.execute(r2_HttpHost, r3_HttpRequest);
    }

    public HttpResponse execute(HttpHost r2_HttpHost, HttpRequest r3_HttpRequest, HttpContext r4_HttpContext) throws IOException {
        return this.c.execute(r2_HttpHost, r3_HttpRequest, r4_HttpContext);
    }

    public HttpResponse execute(HttpUriRequest r2_HttpUriRequest) throws IOException {
        return this.c.execute(r2_HttpUriRequest);
    }

    public HttpResponse execute(HttpUriRequest r2_HttpUriRequest, HttpContext r3_HttpContext) throws IOException {
        return this.c.execute(r2_HttpUriRequest, r3_HttpContext);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.d != null) {
            WnsClientLog.e("AndroidHttpClient", "Leak found", this.d);
            this.d = null;
        }
    }

    public ClientConnectionManager getConnectionManager() {
        return this.c.getConnectionManager();
    }

    public HttpParams getParams() {
        return this.c.getParams();
    }
}