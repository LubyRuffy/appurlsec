package com.tencent.cloudsdk.http;

import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.d;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class TDefaultHttpClient {
    private static final String a;
    private d b;

    static {
        a = TDefaultHttpClient.class.getName();
    }

    public TDefaultHttpClient() {
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TDefaultHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TDefaultHttpClient", new Class[r1i], new Object[r1i]);
        }
    }

    public TDefaultHttpClient(ClientConnectionManager r7_ClientConnectionManager, HttpParams r8_HttpParams) {
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
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TDefaultHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TDefaultHttpClient", r4_ClassA, r5_ObjectA);
        }
    }

    public TDefaultHttpClient(HttpParams r7_HttpParams) {
        int r2i = 1;
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            Class[] r4_ClassA = new Class[r2i];
            r4_ClassA[r1i] = HttpParams.class;
            Object[] r5_ObjectA = new Object[r2i];
            r5_ObjectA[r1i] = r7_HttpParams;
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TDefaultHttpClient", "com.tencent.cloudsdk.defaultsdk.mna.http.TDefaultHttpClient", r4_ClassA, r5_ObjectA);
        }
    }

    protected RequestDirector createClientRequestDirector(HttpRequestExecutor r5_HttpRequestExecutor, ClientConnectionManager r6_ClientConnectionManager, ConnectionReuseStrategy r7_ConnectionReuseStrategy, ConnectionKeepAliveStrategy r8_ConnectionKeepAliveStrategy, HttpRoutePlanner r9_HttpRoutePlanner, HttpProcessor r10_HttpProcessor, HttpRequestRetryHandler r11_HttpRequestRetryHandler, RedirectHandler r12_RedirectHandler, AuthenticationHandler r13_AuthenticationHandler, AuthenticationHandler r14_AuthenticationHandler, UserTokenHandler r15_UserTokenHandler, HttpParams r16_HttpParams) {
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[12];
        r0_ClassA[0] = HttpRequestExecutor.class;
        r0_ClassA[1] = ClientConnectionManager.class;
        r0_ClassA[2] = ConnectionReuseStrategy.class;
        r0_ClassA[3] = ConnectionKeepAliveStrategy.class;
        r0_ClassA[4] = HttpRoutePlanner.class;
        r0_ClassA[5] = HttpProcessor.class;
        r0_ClassA[6] = HttpRequestRetryHandler.class;
        r0_ClassA[7] = RedirectHandler.class;
        r0_ClassA[8] = AuthenticationHandler.class;
        r0_ClassA[9] = AuthenticationHandler.class;
        r0_ClassA[10] = UserTokenHandler.class;
        r0_ClassA[11] = HttpParams.class;
        Object[] r1_ObjectA = new Object[12];
        r1_ObjectA[0] = r5_HttpRequestExecutor;
        r1_ObjectA[1] = r6_ClientConnectionManager;
        r1_ObjectA[2] = r7_ConnectionReuseStrategy;
        r1_ObjectA[3] = r8_ConnectionKeepAliveStrategy;
        r1_ObjectA[4] = r9_HttpRoutePlanner;
        r1_ObjectA[5] = r10_HttpProcessor;
        r1_ObjectA[6] = r11_HttpRequestRetryHandler;
        r1_ObjectA[7] = r12_RedirectHandler;
        r1_ObjectA[8] = r13_AuthenticationHandler;
        r1_ObjectA[9] = r14_AuthenticationHandler;
        r1_ObjectA[10] = r15_UserTokenHandler;
        r1_ObjectA[11] = r16_HttpParams;
        return (RequestDirector) this.b.a("createClientRequestDirector", r0_ClassA, r1_ObjectA);
    }

    public DefaultHttpClient getDefaultHttpClient() {
        return this.b != null ? (DefaultHttpClient) this.b.b() : null;
    }
}