package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public class av {
    private final q a;
    private aG b;
    private SSLSocketFactory c;
    private boolean d;

    public av() {
        this(new r());
    }

    public av(q r1_q) {
        this.a = r1_q;
    }

    private synchronized void a() {
        this.d = false;
        this.c = null;
    }

    private static boolean a(X509Certificate r3_X509Certificate, X509Certificate r4_X509Certificate) {
        if (!r3_X509Certificate.getSubjectX500Principal().equals(r4_X509Certificate.getIssuerX500Principal())) {
            return false;
        }
        try {
            r4_X509Certificate.verify(r3_X509Certificate.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }

    public static X509Certificate[] a(X509Certificate[] r6_X509CertificateA, bm r7_bm) throws CertificateException {
        int r0i;
        int r2i = 0;
        int r1i = 1;
        LinkedList r3_LinkedList = new LinkedList();
        r0i = r7_bm.a(r6_X509CertificateA[0]) ? 1 : 0;
        r3_LinkedList.add(r6_X509CertificateA[r2i]);
        r2i = r0i;
        r0i = 1;
        while (r0i < r6_X509CertificateA.length) {
            if (r7_bm.a(r6_X509CertificateA[r0i])) {
                r2i = 1;
            }
            if (a(r6_X509CertificateA[r0i], r6_X509CertificateA[r0i - 1])) {
                r3_LinkedList.add(r6_X509CertificateA[r0i]);
                r0i++;
            } else {
                break;
            }
        }
        X509Certificate r0_X509Certificate = r7_bm.b(r6_X509CertificateA[r0i - 1]);
        if (r0_X509Certificate != null) {
            r3_LinkedList.add(r0_X509Certificate);
        } else {
            r1i = r2i;
        }
        if (r1i != 0) {
            return (X509Certificate[]) r3_LinkedList.toArray(new X509Certificate[r3_LinkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    private synchronized SSLSocketFactory b() {
        if (this.c != null || this.d) {
        } else {
            this.c = c();
        }
        return this.c;
    }

    private synchronized SSLSocketFactory c() {
        SSLSocketFactory r0_SSLSocketFactory;
        this.d = true;
        try {
            aG r0_aG = this.b;
            SSLContext r2_SSLContext = SSLContext.getInstance("TLS");
            TrustManager[] r3_TrustManagerA = new TrustManager[1];
            r3_TrustManagerA[0] = new bl(new bm(r0_aG.a(), r0_aG.b()), r0_aG);
            r2_SSLContext.init(null, r3_TrustManagerA, null);
            r0_SSLSocketFactory = r2_SSLContext.getSocketFactory();
            this.a.a(Crashlytics.TAG, "Custom SSL pinning enabled");
        } catch (Exception e) {
            this.a.a(Crashlytics.TAG, "Exception while validating pinned certs", e);
            r0_SSLSocketFactory = null;
        }
        return r0_SSLSocketFactory;
    }

    public ay a(ax r2_ax, String r3_String) {
        return a(r2_ax, r3_String, Collections.emptyMap());
    }

    public ay a(ax r4_ax, String r5_String, Map<String, String> r6_Map_String__String) {
        ay r1_ay;
        SSLSocketFactory r2_SSLSocketFactory;
        switch (by.a[r4_ax.ordinal()]) {
            case XListViewHeader.STATE_READY:
                r1_ay = ay.a((CharSequence)r5_String, (Map)r6_Map_String__String, true);
                if ((!r5_String != null ? false : r5_String.toLowerCase().startsWith("https")) || this.b == null) {
                    return r1_ay;
                }
                r2_SSLSocketFactory = b();
                if (r2_SSLSocketFactory == null) {
                    ((HttpsURLConnection) r1_ay.a()).setSSLSocketFactory(r2_SSLSocketFactory);
                }
                return r1_ay;
            case XListViewHeader.STATE_REFRESHING:
                r1_ay = ay.b((CharSequence)r5_String, (Map)r6_Map_String__String, true);
                if (r5_String != null) {
                }
                if (r5_String != null ? false : r5_String.toLowerCase().startsWith("https") || this.b == null) {
                    return r1_ay;
                }
                r2_SSLSocketFactory = b();
                if (r2_SSLSocketFactory == null) {
                    return r1_ay;
                }
                ((HttpsURLConnection) r1_ay.a()).setSSLSocketFactory(r2_SSLSocketFactory);
                return r1_ay;
            case XListViewFooter.STATE_NOMORE:
                r1_ay = ay.a((CharSequence)r5_String);
                if (r5_String != null) {
                }
                if (r5_String != null ? false : r5_String.toLowerCase().startsWith("https") || this.b == null) {
                    return r1_ay;
                }
                r2_SSLSocketFactory = b();
                if (r2_SSLSocketFactory == null) {
                    ((HttpsURLConnection) r1_ay.a()).setSSLSocketFactory(r2_SSLSocketFactory);
                }
                return r1_ay;
            case XListViewFooter.STATE_NODATA:
                r1_ay = ay.b((CharSequence)r5_String);
                if (r5_String != null) {
                }
                if (r5_String != null ? false : r5_String.toLowerCase().startsWith("https") || this.b == null) {
                    return r1_ay;
                }
                r2_SSLSocketFactory = b();
                if (r2_SSLSocketFactory == null) {
                    return r1_ay;
                }
                ((HttpsURLConnection) r1_ay.a()).setSSLSocketFactory(r2_SSLSocketFactory);
                return r1_ay;
        }
        throw new IllegalArgumentException("Unsupported HTTP method!");
    }

    public void a(aG r2_aG) {
        if (this.b != r2_aG) {
            this.b = r2_aG;
            a();
        }
    }
}