package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient;
import com.tencent.cloudsdk.defaultsdk.mna.http.TDefaultHttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;

// compiled from: SourceFile
public class db extends TDefaultHttpClient {
    final /* synthetic */ TAndroidHttpClient a;

    public db(TAndroidHttpClient r1_TAndroidHttpClient, ClientConnectionManager r2_ClientConnectionManager, HttpParams r3_HttpParams) {
        this.a = r1_TAndroidHttpClient;
        super(r2_ClientConnectionManager, r3_HttpParams);
    }

    protected HttpContext createHttpContext() {
        HttpContext r0_HttpContext = new BasicHttpContext();
        r0_HttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
        r0_HttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
        r0_HttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return r0_HttpContext;
    }

    protected BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor r0_BasicHttpProcessor = super.createHttpProcessor();
        r0_BasicHttpProcessor.addRequestInterceptor(TAndroidHttpClient.access$2());
        r0_BasicHttpProcessor.addRequestInterceptor(new dc(this.a, null));
        return r0_BasicHttpProcessor;
    }
}