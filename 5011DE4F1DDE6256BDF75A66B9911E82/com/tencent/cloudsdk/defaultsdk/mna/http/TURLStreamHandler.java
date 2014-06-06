package com.tencent.cloudsdk.defaultsdk.mna.http;

import com.tencent.cloudsdk.er;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

// compiled from: SourceFile
public class TURLStreamHandler extends URLStreamHandler {
    private URLConnection a(URL r10_URL, Proxy r11_Proxy) throws IOException {
        URLConnection r0_URLConnection;
        String r2_String = r10_URL.getHost();
        int r3i = r10_URL.getPort();
        String r0_String = new HttpOptimizer(r10_URL.toString()).findBetterUrl();
        Object[] r5_ObjectA = new Object[2];
        r5_ObjectA[0] = r10_URL.toString();
        r5_ObjectA[1] = r0_String;
        er.a("TURLStreamHandler", String.format("\u8f6c\u6362URL\u4ece %s \u5230 %s", r5_ObjectA));
        URL r1_URL = new URL(r0_String);
        r0_URLConnection = r11_Proxy == null ? r1_URL.openConnection() : r1_URL.openConnection(r11_Proxy);
        r0_URLConnection.setRequestProperty("Host", r3i < 0 ? r2_String : new StringBuilder(String.valueOf(r2_String)).append(":").append(r3i).toString());
        String r1_String = "TURLStreamHandler";
        String r4_String = "\u66ff\u6362Host\u5c5e\u6027\u4e3a%s";
        r5_ObjectA = new Object[1];
        if (r3i < 0) {
            r5_ObjectA[0] = r2_String;
            er.a(r1_String, String.format(r4_String, r5_ObjectA));
            return r0_URLConnection;
        } else {
            r2_String = new StringBuilder(String.valueOf(r2_String)).append(":").append(r3i).toString();
            r5_ObjectA[0] = r2_String;
            er.a(r1_String, String.format(r4_String, r5_ObjectA));
            return r0_URLConnection;
        }
    }

    protected URLConnection openConnection(URL r2_URL) throws IOException {
        return a(r2_URL, null);
    }

    protected URLConnection openConnection(URL r2_URL, Proxy r3_Proxy) throws IOException {
        return a(r2_URL, r3_Proxy);
    }
}