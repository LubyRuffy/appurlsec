package qsbk.app.thirdparty.net;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

// compiled from: HttpManager.java
class b implements X509TrustManager {
    final /* synthetic */ a a;

    b(a r1_a) {
        this.a = r1_a;
    }

    public void checkClientTrusted(X509Certificate[] r1_X509CertificateA, String r2_String) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] r1_X509CertificateA, String r2_String) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}