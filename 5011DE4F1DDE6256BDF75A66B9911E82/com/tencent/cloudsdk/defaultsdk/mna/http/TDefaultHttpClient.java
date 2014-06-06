package com.tencent.cloudsdk.defaultsdk.mna.http;

import com.tencent.cloudsdk.de;
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

// compiled from: SourceFile
public class TDefaultHttpClient extends DefaultHttpClient {
    public TDefaultHttpClient(ClientConnectionManager r1_ClientConnectionManager, HttpParams r2_HttpParams) {
        super(r1_ClientConnectionManager, r2_HttpParams);
    }

    public TDefaultHttpClient(HttpParams r1_HttpParams) {
        super(r1_HttpParams);
    }

    protected RequestDirector createClientRequestDirector(HttpRequestExecutor r14_HttpRequestExecutor, ClientConnectionManager r15_ClientConnectionManager, ConnectionReuseStrategy r16_ConnectionReuseStrategy, ConnectionKeepAliveStrategy r17_ConnectionKeepAliveStrategy, HttpRoutePlanner r18_HttpRoutePlanner, HttpProcessor r19_HttpProcessor, HttpRequestRetryHandler r20_HttpRequestRetryHandler, RedirectHandler r21_RedirectHandler, AuthenticationHandler r22_AuthenticationHandler, AuthenticationHandler r23_AuthenticationHandler, UserTokenHandler r24_UserTokenHandler, HttpParams r25_HttpParams) {
        return new de(r14_HttpRequestExecutor, r15_ClientConnectionManager, r16_ConnectionReuseStrategy, r17_ConnectionKeepAliveStrategy, r18_HttpRoutePlanner, r19_HttpProcessor, r20_HttpRequestRetryHandler, r21_RedirectHandler, r22_AuthenticationHandler, r23_AuthenticationHandler, r24_UserTokenHandler, r25_HttpParams);
    }
}