package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.http.TAndroidHttpClient;
import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

// compiled from: SourceFile
public class dc implements HttpRequestInterceptor {
    final /* synthetic */ TAndroidHttpClient a;

    private dc(TAndroidHttpClient r1_TAndroidHttpClient) {
        this.a = r1_TAndroidHttpClient;
    }

    public void process(HttpRequest r3_HttpRequest, HttpContext r4_HttpContext) throws HttpException, IOException {
        dd r0_dd = TAndroidHttpClient.access$0(this.a);
        if (r0_dd != null && r0_dd.a() && r3_HttpRequest instanceof HttpUriRequest) {
            r0_dd.a(TAndroidHttpClient.access$1((HttpUriRequest) r3_HttpRequest, false));
        }
    }
}