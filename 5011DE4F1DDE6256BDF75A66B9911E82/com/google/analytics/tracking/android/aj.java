package com.google.analytics.tracking.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;
import qsbk.app.utils.image.Utils;

// compiled from: SimpleNetworkDispatcher.java
class aj implements g {
    private final String a;
    private final y b;
    private final Context c;

    aj(d r1_d, y r2_y, Context r3_Context) {
        this(r2_y, r3_Context);
    }

    aj(y r8_y, Context r9_Context) {
        this.c = r9_Context.getApplicationContext();
        this.a = a("GoogleAnalytics", "2.0", VERSION.RELEASE, am.a(Locale.getDefault()), Build.MODEL, Build.ID);
        this.b = r8_y;
    }

    private URL a(w r4_w) {
        if (TextUtils.isEmpty(r4_w.getHitUrl())) {
            return null;
        }
        try {
            return new URL(r4_w.getHitUrl());
        } catch (MalformedURLException e) {
            return new URL("http://www.google-analytics.com/collect");
        }
    }

    private HttpEntityEnclosingRequest a(String r5_String, String r6_String) {
        if (TextUtils.isEmpty(r5_String)) {
            z.h("Empty hit, discarding.");
            return null;
        } else {
            HttpEntityEnclosingRequest r0_HttpEntityEnclosingRequest;
            String r2_String = r6_String + "?" + r5_String;
            if (r2_String.length() < 2036) {
                r0_HttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest(HttpManager.HTTPMETHOD_GET, r2_String);
            } else {
                r0_HttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest(UsersAPI.HTTPMETHOD_POST, r6_String);
                r0_HttpEntityEnclosingRequest.addHeader("Content-Length", Integer.toString(r5_String.length()));
                r0_HttpEntityEnclosingRequest.addHeader("Content-Type", "text/plain");
                try {
                    r0_HttpEntityEnclosingRequest.setEntity(new StringEntity(r5_String));
                } catch (UnsupportedEncodingException e) {
                    z.h("Encoding error, discarding hit");
                    return null;
                }
            }
            r0_HttpEntityEnclosingRequest.addHeader("User-Agent", this.a);
            return r0_HttpEntityEnclosingRequest;
        }
    }

    private void a(boolean r7z, HttpEntityEnclosingRequest r8_HttpEntityEnclosingRequest) {
        if (r7z) {
            StringBuffer r1_StringBuffer = new StringBuffer();
            Header[] r2_HeaderA = r8_HttpEntityEnclosingRequest.getAllHeaders();
            int r3i = r2_HeaderA.length;
            int r0i = 0;
            while (r0i < r3i) {
                r1_StringBuffer.append(r2_HeaderA[r0i].toString()).append("\n");
                r0i++;
            }
            r1_StringBuffer.append(r8_HttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
            z.d(r1_StringBuffer.toString());
        }
    }

    String a(String r4_String, String r5_String, String r6_String, String r7_String, String r8_String, String r9_String) {
        Object[] r1_ObjectA = new Object[6];
        r1_ObjectA[0] = r4_String;
        r1_ObjectA[1] = r5_String;
        r1_ObjectA[2] = r6_String;
        r1_ObjectA[3] = r7_String;
        r1_ObjectA[4] = r8_String;
        r1_ObjectA[5] = r9_String;
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", r1_ObjectA);
    }

    public int dispatchHits(List<w> r11_List_w) {
        int r3i = Math.min(r11_List_w.size(), 40);
        int r2i = 0;
        int r1i = 0;
        while (r2i < r3i) {
            int r0i;
            HttpClient r4_HttpClient = this.b.newInstance();
            w r0_w = (w) r11_List_w.get(r2i);
            URL r5_URL = a(r0_w);
            if (r5_URL == null) {
                if (z.a()) {
                    z.h("No destination: discarding hit: " + r0_w.a());
                } else {
                    z.h("No destination: discarding hit.");
                }
                r0i = r1i + 1;
            } else {
                String r0_String;
                HttpHost r6_HttpHost = new HttpHost(r5_URL.getHost(), r5_URL.getPort(), r5_URL.getProtocol());
                String r5_String = r5_URL.getPath();
                r0_String = TextUtils.isEmpty(r0_w.a()) ? RContactStorage.PRIMARY_KEY : x.a(r0_w, System.currentTimeMillis());
                HttpEntityEnclosingRequest r5_HttpEntityEnclosingRequest = a(r0_String, r5_String);
                if (r5_HttpEntityEnclosingRequest == null) {
                    r0i = r1i + 1;
                } else {
                    r5_HttpEntityEnclosingRequest.addHeader("Host", r6_HttpHost.toHostString());
                    a(z.a(), r5_HttpEntityEnclosingRequest);
                    if (r0_String.length() > Utils.IO_BUFFER_SIZE) {
                        z.h("Hit too long (> 8192 bytes)--not sent");
                    } else {
                        try {
                            HttpResponse r0_HttpResponse = r4_HttpClient.execute(r6_HttpHost, r5_HttpEntityEnclosingRequest);
                            if (r0_HttpResponse.getStatusLine().getStatusCode() != 200) {
                                z.h("Bad response: " + r0_HttpResponse.getStatusLine().getStatusCode());
                                return r1i;
                            }
                        } catch (IOException e) {
                            z.h("Exception sending hit: " + e.getClass().getSimpleName());
                            return r1i;
                        }
                    }
                    r0i = r1i + 1;
                }
            }
            r2i++;
            r1i = r0i;
        }
        return r1i;
    }

    public boolean okToDispatch() {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) this.c.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo != null && r0_NetworkInfo.isConnected()) {
            return true;
        }
        z.g("...no network connectivity");
        return false;
    }
}