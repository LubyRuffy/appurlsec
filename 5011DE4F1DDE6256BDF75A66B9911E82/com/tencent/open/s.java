package com.tencent.open;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

// compiled from: ProGuard
class s implements X509TrustManager {
    final /* synthetic */ MySSLSocketFactory a;

    s(MySSLSocketFactory r1_MySSLSocketFactory) {
        this.a = r1_MySSLSocketFactory;
    }

    public void checkClientTrusted(X509Certificate[] r1_X509CertificateA, String r2_String) {
    }

    public void checkServerTrusted(X509Certificate[] r1_X509CertificateA, String r2_String) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}