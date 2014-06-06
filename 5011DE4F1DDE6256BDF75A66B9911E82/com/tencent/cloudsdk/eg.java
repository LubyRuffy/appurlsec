package com.tencent.cloudsdk;

import com.tencent.cloudsdk.common.record.debug.WnsClientLog;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.protocol.HttpContext;

// compiled from: SourceFile
class eg implements RedirectHandler {
    final /* synthetic */ u a;
    private final /* synthetic */ String b;

    eg(u r1_u, String r2_String) {
        this.a = r1_u;
        this.b = r2_String;
    }

    public URI getLocationURI(HttpResponse r6_HttpResponse, HttpContext r7_HttpContext) throws ProtocolException {
        URI r0_URI;
        Header r0_Header = r6_HttpResponse.getFirstHeader("Location");
        if (r0_Header != null) {
            this.a.a = r0_Header.getValue();
        }
        try {
            r0_URI = new URI(this.a.a);
        } catch (URISyntaxException e) {
            WnsClientLog.e("HttpTask", new StringBuilder(">>> createHttpClient() E: ").append(e.toString()).toString());
            r0_URI = null;
        }
        u.a(this.a, r0_URI, this.b);
        return r0_URI;
    }

    public boolean isRedirectRequested(HttpResponse r3_HttpResponse, HttpContext r4_HttpContext) {
        int r0i = r3_HttpResponse.getStatusLine().getStatusCode();
        return r0i == 302 || r0i == 301;
    }
}