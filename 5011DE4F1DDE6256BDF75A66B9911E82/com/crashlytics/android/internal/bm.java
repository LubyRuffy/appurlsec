package com.crashlytics.android.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

// compiled from: SourceFile
final class bm {
    final KeyStore a;
    private final HashMap<Principal, X509Certificate> b;

    public bm(InputStream r3_InputStream, String r4_String) {
        KeyStore r0_KeyStore = a(r3_InputStream, r4_String);
        this.b = a(r0_KeyStore);
        this.a = r0_KeyStore;
    }

    private static KeyStore a(InputStream r3_InputStream, String r4_String) {
        try {
            KeyStore r0_KeyStore = KeyStore.getInstance("BKS");
            InputStream r1_InputStream = new BufferedInputStream(r3_InputStream);
            r0_KeyStore.load(r1_InputStream, r4_String.toCharArray());
            r1_InputStream.close();
            return r0_KeyStore;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        } catch (NoSuchAlgorithmException e_2) {
            throw new AssertionError(e_2);
        } catch (CertificateException e_3) {
            throw new AssertionError(e_3);
        } catch (IOException e_4) {
            throw new AssertionError(e_4);
        }
    }

    private static HashMap<Principal, X509Certificate> a(KeyStore r4_KeyStore) {
        try {
            HashMap<Principal, X509Certificate> r1_HashMap_Principal__X509Certificate = new HashMap();
            Enumeration r2_Enumeration = r4_KeyStore.aliases();
            while (r2_Enumeration.hasMoreElements()) {
                X509Certificate r0_X509Certificate = (X509Certificate) r4_KeyStore.getCertificate((String) r2_Enumeration.nextElement());
                if (r0_X509Certificate != null) {
                    r1_HashMap_Principal__X509Certificate.put(r0_X509Certificate.getSubjectX500Principal(), r0_X509Certificate);
                }
            }
            return r1_HashMap_Principal__X509Certificate;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean a(X509Certificate r3_X509Certificate) {
        X509Certificate r0_X509Certificate = (X509Certificate) this.b.get(r3_X509Certificate.getSubjectX500Principal());
        return r0_X509Certificate != null && r0_X509Certificate.getPublicKey().equals(r3_X509Certificate.getPublicKey());
    }

    public final X509Certificate b(X509Certificate r5_X509Certificate) {
        X509Certificate r0_X509Certificate = (X509Certificate) this.b.get(r5_X509Certificate.getIssuerX500Principal());
        if (r0_X509Certificate == null) {
            return null;
        }
        if (r0_X509Certificate.getSubjectX500Principal().equals(r5_X509Certificate.getSubjectX500Principal())) {
            return null;
        }
        try {
            r5_X509Certificate.verify(r0_X509Certificate.getPublicKey());
            return r0_X509Certificate;
        } catch (GeneralSecurityException e) {
            return null;
        }
    }
}