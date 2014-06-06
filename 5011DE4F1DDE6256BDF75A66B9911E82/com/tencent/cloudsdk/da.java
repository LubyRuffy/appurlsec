package com.tencent.cloudsdk;

import android.os.Looper;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

// compiled from: SourceFile
public class da implements HttpRequestInterceptor {
    public void process(HttpRequest r3_HttpRequest, HttpContext r4_HttpContext) {
        if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
        } else {
            throw new RuntimeException("This thread forbids HTTP requests");
        }
    }
}