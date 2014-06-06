package com.tencent.cloudsdk.http;

import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.d;
import java.net.MalformedURLException;

// compiled from: SourceFile
public class HttpOptimizer {
    private static final String a;
    private d b;

    static {
        a = HttpOptimizer.class.getName();
    }

    public HttpOptimizer(String r7_String) throws MalformedURLException {
        int r2i = 1;
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            Class[] r4_ClassA = new Class[r2i];
            r4_ClassA[r1i] = String.class;
            Object[] r5_ObjectA = new Object[r2i];
            r5_ObjectA[r1i] = r7_String;
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.HttpOptimizer", "com.tencent.cloudsdk.defaultsdk.mna.http.HttpOptimizer", r4_ClassA, r5_ObjectA);
        }
    }

    public String findBetterUrl() {
        int r1i = 0;
        return this.b != null ? (String) this.b.a("findBetterUrl", new Class[r1i], new Object[r1i]) : null;
    }
}