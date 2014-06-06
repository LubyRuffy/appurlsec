package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.http.HttpOptimizer;
import java.io.IOException;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.client.DefaultRequestDirector;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;

// compiled from: SourceFile
public class de extends DefaultRequestDirector {
    public de(HttpRequestExecutor r1_HttpRequestExecutor, ClientConnectionManager r2_ClientConnectionManager, ConnectionReuseStrategy r3_ConnectionReuseStrategy, ConnectionKeepAliveStrategy r4_ConnectionKeepAliveStrategy, HttpRoutePlanner r5_HttpRoutePlanner, HttpProcessor r6_HttpProcessor, HttpRequestRetryHandler r7_HttpRequestRetryHandler, RedirectHandler r8_RedirectHandler, AuthenticationHandler r9_AuthenticationHandler, AuthenticationHandler r10_AuthenticationHandler, UserTokenHandler r11_UserTokenHandler, HttpParams r12_HttpParams) {
        super(r1_HttpRequestExecutor, r2_ClientConnectionManager, r3_ConnectionReuseStrategy, r4_ConnectionKeepAliveStrategy, r5_HttpRoutePlanner, r6_HttpProcessor, r7_HttpRequestRetryHandler, r8_RedirectHandler, r9_AuthenticationHandler, r10_AuthenticationHandler, r11_UserTokenHandler, r12_HttpParams);
    }

    public HttpResponse execute(HttpHost r12_HttpHost, HttpRequest r13_HttpRequest, HttpContext r14_HttpContext) throws HttpException, IOException {
        String r1_String = r12_HttpHost.getHostName();
        int r0i = r12_HttpHost.getPort();
        if (r0i < 0) {
            r0i = 80;
        }
        cn r2_cn = HttpOptimizer.findBetterAddress(r1_String, r0i);
        String r3_String = r2_cn.a;
        int r2i = r2_cn.c;
        Object[] r6_ObjectA = new Object[2];
        r6_ObjectA[0] = r1_String;
        r6_ObjectA[1] = Integer.valueOf(r0i);
        er.a("TDefaultRequestDirector", String.format("\u4fee\u6539Request\u7684\u5c5e\u6027Host\u4e3a%s:%d", r6_ObjectA));
        r13_HttpRequest.setHeader("Host", new StringBuilder(String.valueOf(r1_String)).append(":").append(r0i).toString());
        HttpHost r0_HttpHost = new HttpHost(r3_String, r2i, r12_HttpHost.getSchemeName());
        Object[] r3_ObjectA = new Object[2];
        r3_ObjectA[0] = r12_HttpHost;
        r3_ObjectA[1] = r0_HttpHost;
        er.a("TDefaultRequestDirector", String.format("\u4fee\u6539target\u7684Host\u548c\u7aef\u53e3\uff0c\u4ece%s\u5230%s", r3_ObjectA));
        return super.execute(r0_HttpHost, r13_HttpRequest, r14_HttpContext);
    }
}