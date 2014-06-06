package com.tencent.cloudsdk.http;

import com.tencent.cloudsdk.a;
import com.tencent.cloudsdk.d;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

// compiled from: SourceFile
public class TURLStreamHandler {
    private static final String a;
    private d b;

    static {
        a = TURLStreamHandler.class.getName();
    }

    public TURLStreamHandler() {
        int r1i = 0;
        this.b = new d();
        if (this.b != null) {
            this.b.a(a.a, "com.tencent.cloudsdk.pluginsdk.mna.http.TURLStreamHandler", "com.tencent.cloudsdk.defaultsdk.mna.http.TURLStreamHandler", new Class[r1i], new Object[r1i]);
        }
    }

    private URLConnection a(URL r6_URL, Proxy r7_Proxy) throws IOException {
        int r3i = 1;
        int r2i = 0;
        if (this.b == null) {
            return null;
        }
        Class[] r0_ClassA = new Class[2];
        r0_ClassA[r2i] = URL.class;
        r0_ClassA[r3i] = Proxy.class;
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[r2i] = r6_URL;
        r1_ObjectA[r3i] = r7_Proxy;
        return (URLConnection) this.b.a("openConnectionInternal", r0_ClassA, r1_ObjectA);
    }

    public URLStreamHandler getURLStreamHandler() {
        return this.b != null ? (URLStreamHandler) this.b.b() : null;
    }

    protected URLConnection openConnection(URL r2_URL) throws IOException {
        return a(r2_URL, null);
    }

    protected URLConnection openConnection(URL r2_URL, Proxy r3_Proxy) throws IOException {
        return a(r2_URL, r3_Proxy);
    }
}