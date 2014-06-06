package com.crashlytics.android;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import qsbk.app.bean.Base;

// compiled from: SourceFile
final class ai {
    private final byte[] a;
    private volatile int b;

    static {
        ai r0_ai = new ai(new byte[0]);
    }

    private ai(byte[] r2_byteA) {
        this.b = 0;
        this.a = r2_byteA;
    }

    public static ai a(String r3_String) {
        try {
            return new ai(r3_String.getBytes(Base.UTF8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public static ai a(byte[] r2_byteA, int r3i, int r4i) {
        Object r0_Object = new Object[r4i];
        System.arraycopy(r2_byteA, 0, r0_Object, 0, r4i);
        return new ai(r0_Object);
    }

    public final int a() {
        return this.a.length;
    }

    public final void a(byte[] r2_byteA, int r3i, int r4i, int r5i) {
        System.arraycopy(this.a, r3i, r2_byteA, r4i, r5i);
    }

    public final InputStream b() {
        return new ByteArrayInputStream(this.a);
    }

    public final boolean equals(Object r9_Object) {
        if (r9_Object == this) {
            return true;
        }
        if (!(r9_Object instanceof ai)) {
            return false;
        }
        ai r9_ai = (ai) r9_Object;
        int r3i = this.a.length;
        if (r3i != r9_ai.a.length) {
            return false;
        }
        byte[] r4_byteA = this.a;
        byte[] r5_byteA = r9_ai.a;
        int r2i = 0;
        while (r2i < r3i) {
            if (r4_byteA[r2i] != r5_byteA[r2i]) {
                return false;
            }
            r2i++;
        }
        return true;
    }

    public final int hashCode() {
        int r0i = this.b;
        if (r0i == 0) {
            byte[] r4_byteA = this.a;
            int r2i = this.a.length;
            int r1i = 0;
            r0i = r2i;
            while (r1i < r2i) {
                r1i++;
                r0i = r4_byteA[r1i] + r0i * 31;
            }
            if (r0i == 0) {
                r0i = 1;
            }
            this.b = r0i;
        }
        return r0i;
    }
}