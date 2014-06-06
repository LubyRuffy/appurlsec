package com.flurry.android;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

// compiled from: SourceFile
final class j extends SSLSocketFactory {
    private SSLContext a;

    public j(FlurryAgent r6_FlurryAgent, KeyStore r7_KeyStore) {
        super(r7_KeyStore);
        this.a = SSLContext.getInstance("TLS");
        z r0_z = new z();
        SSLContext r1_SSLContext = this.a;
        TrustManager[] r2_TrustManagerA = new TrustManager[1];
        r2_TrustManagerA[0] = r0_z;
        r1_SSLContext.init(null, r2_TrustManagerA, null);
    }

    public final Socket createSocket() {
        return this.a.getSocketFactory().createSocket();
    }

    public final Socket createSocket(Socket r2_Socket, String r3_String, int r4i, boolean r5z) {
        return this.a.getSocketFactory().createSocket(r2_Socket, r3_String, r4i, r5z);
    }
}