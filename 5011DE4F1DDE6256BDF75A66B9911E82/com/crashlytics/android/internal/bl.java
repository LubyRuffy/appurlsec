package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import qsbk.app.utils.Base64;

// compiled from: SourceFile
final class bl implements X509TrustManager {
    private final TrustManager[] a;
    private final bm b;
    private final long c;
    private final List<byte[]> d;
    private final Set<X509Certificate> e;

    public bl(bm r6_bm, aG r7_aG) {
        this.d = new LinkedList();
        this.e = Collections.synchronizedSet(new HashSet());
        this.a = a(r6_bm);
        this.b = r6_bm;
        this.c = -1;
        String[] r1_StringA = r7_aG.c();
        int r2i = r1_StringA.length;
        int r0i = 0;
        while (r0i < r2i) {
            this.d.add(a(r1_StringA[r0i]));
            r0i++;
        }
    }

    private boolean a(X509Certificate r4_X509Certificate) throws CertificateException {
        try {
            byte[] r1_byteA = MessageDigest.getInstance("SHA1").digest(r4_X509Certificate.getPublicKey().getEncoded());
            Iterator r2_Iterator = this.d.iterator();
            while (r2_Iterator.hasNext()) {
                if (Arrays.equals((byte[]) r2_Iterator.next(), r1_byteA)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e);
        }
    }

    private static byte[] a(String r7_String) {
        int r1i = r7_String.length();
        byte[] r2_byteA = new byte[(r1i / 2)];
        int r0i = 0;
        while (r0i < r1i) {
            r2_byteA[r0i / 2] = (byte) (Character.digit(r7_String.charAt(r0i), Base64.URL_SAFE) << 4 + Character.digit(r7_String.charAt(r0i + 1), Base64.URL_SAFE));
            r0i += 2;
        }
        return r2_byteA;
    }

    private static TrustManager[] a(bm r2_bm) {
        try {
            TrustManagerFactory r0_TrustManagerFactory = TrustManagerFactory.getInstance("X509");
            r0_TrustManagerFactory.init(r2_bm.a);
            return r0_TrustManagerFactory.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e_2) {
            throw new AssertionError(e_2);
        }
    }

    public final void checkClientTrusted(X509Certificate[] r3_X509CertificateA, String r4_String) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    public final void checkServerTrusted(X509Certificate[] r9_X509CertificateA, String r10_String) throws CertificateException {
        int r1i = 0;
        if (this.e.contains(r9_X509CertificateA[0])) {
        } else {
            TrustManager[] r3_TrustManagerA = this.a;
            int r4i = r3_TrustManagerA.length;
            int r2i = 0;
            while (r2i < r4i) {
                ((X509TrustManager) r3_TrustManagerA[r2i]).checkServerTrusted(r9_X509CertificateA, r10_String);
                r2i++;
            }
            if (this.c == -1 || System.currentTimeMillis() - this.c <= 15552000000L) {
                X509Certificate[] r2_X509CertificateA = av.a(r9_X509CertificateA, this.b);
                int r3i = r2_X509CertificateA.length;
                int r0i = 0;
                while (r0i < r3i) {
                    if (a(r2_X509CertificateA[r0i])) {
                        this.e.add(r9_X509CertificateA[r1i]);
                    } else {
                        r0i++;
                    }
                }
                throw new CertificateException("No valid pins found in chain!");
            } else {
                v.a().b().c(Crashlytics.TAG, new StringBuilder("Certificate pins are stale, (").append(System.currentTimeMillis() - this.c).append(" millis vs 15552000000").append(" millis) falling back to system trust.").toString());
                this.e.add(r9_X509CertificateA[r1i]);
            }
        }
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}