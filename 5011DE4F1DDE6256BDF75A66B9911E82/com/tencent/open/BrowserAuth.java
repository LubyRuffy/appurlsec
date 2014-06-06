package com.tencent.open;

import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.IUiListener;
import java.util.HashMap;
import qsbk.app.utils.Base64;

// compiled from: ProGuard
public class BrowserAuth {
    public static BrowserAuth a;
    static final /* synthetic */ boolean d;
    private static int e;
    public HashMap b;
    public final String c;

    // compiled from: ProGuard
    public class Auth {
        public IUiListener a;
        public TDialog b;
        public String c;
    }

    static {
        d = !BrowserAuth.class.desiredAssertionStatus();
        e = 0;
    }

    public BrowserAuth() {
        this.b = new HashMap();
        this.c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    }

    public static BrowserAuth a() {
        if (a == null) {
            a = new BrowserAuth();
        }
        return a;
    }

    public static int b() {
        int r0i = e + 1;
        e = r0i;
        return r0i;
    }

    private String b(String r8_String, String r9_String) {
        int r0i = 0;
        if (d || r8_String.length() % 2 == 0) {
            StringBuilder r2_StringBuilder = new StringBuilder();
            int r3i = r9_String.length();
            int r4i = r8_String.length() / 2;
            int r1i = 0;
            while (r0i < r4i) {
                r2_StringBuilder.append((char) (Integer.parseInt(r8_String.substring(r0i * 2, (r0i * 2) + 2), Base64.URL_SAFE) ^ r9_String.charAt(r1i)));
                r1i = (r1i + 1) % r3i;
                r0i++;
            }
            return r2_StringBuilder.toString();
        } else {
            throw new AssertionError();
        }
    }

    public Auth a(String r2_String) {
        return (Auth) this.b.get(r2_String);
    }

    public String a(Auth r5_Auth) {
        int r1i = b();
        try {
            this.b.put(RContactStorage.PRIMARY_KEY + r1i, r5_Auth);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return RContactStorage.PRIMARY_KEY + r1i;
    }

    public String a(String r2_String, String r3_String) {
        return b(r2_String, r3_String);
    }

    public void b(String r2_String) {
        this.b.remove(r2_String);
    }

    public String c() {
        int r1i = (int) Math.ceil(Math.random() * 20.0d + 3.0d);
        char[] r2_charA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int r3i = r2_charA.length;
        StringBuffer r4_StringBuffer = new StringBuffer();
        int r0i = 0;
        while (r0i < r1i) {
            r4_StringBuffer.append(r2_charA[(int) (Math.random() * ((double) r3i))]);
            r0i++;
        }
        return r4_StringBuffer.toString();
    }
}