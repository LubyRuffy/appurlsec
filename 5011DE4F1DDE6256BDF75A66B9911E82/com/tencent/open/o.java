package com.tencent.open;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

// compiled from: ProGuard
final class o implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] r2_X509CertificateA, String r3_String) {
        Util.a(RContactStorage.PRIMARY_KEY, r3_String);
    }

    public void checkServerTrusted(X509Certificate[] r2_X509CertificateA, String r3_String) {
        Util.a(RContactStorage.PRIMARY_KEY, r3_String);
    }

    public X509Certificate[] getAcceptedIssuers() {
        Util.a(RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY);
        return null;
    }
}