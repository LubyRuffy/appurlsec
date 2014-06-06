package com.tencent.cloudsdk;

import java.net.InetAddress;

// compiled from: SourceFile
public class cb {
    private static cb d;
    private final int a;
    private final int b;
    private cd c;

    static {
        d = null;
    }

    public cb() {
        this.a = 8;
        this.b = 32;
        this.c = new cd(8, 32);
    }

    public static synchronized cb a() {
        cb r0_cb;
        synchronized (cb.class) {
            if (d == null) {
                d = new cb();
            }
            r0_cb = d;
        }
        return r0_cb;
    }

    public void a(String r3_String, InetAddress[] r4_InetAddressA, long r5j) {
        by r0_by = new by(null);
        r0_by.a = r5j;
        r0_by.b = r4_InetAddressA;
        r0_by.c = em.d();
        if (this.c.containsKey(r3_String)) {
            this.c.remove(r3_String);
        }
        this.c.put(r3_String, r0_by);
    }

    public InetAddress[] a(String r3_String) {
        by r0_by = (by) this.c.get(r3_String);
        if (r0_by != null) {
            if (r0_by.a()) {
                return r0_by.b;
            }
            this.c.remove(r3_String);
        }
        return null;
    }
}