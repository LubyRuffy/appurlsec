package com.tencent.cloudsdk;

import java.util.concurrent.atomic.AtomicInteger;

// compiled from: SourceFile
public class cq {
    private static cq a;
    private static AtomicInteger b;

    static {
        a = null;
        b = new AtomicInteger(100);
    }

    public static synchronized cq a() {
        cq r0_cq;
        synchronized (cq.class) {
            if (a == null) {
                a = new cq();
            }
            r0_cq = a;
        }
        return r0_cq;
    }

    public synchronized int b() {
        int r0i;
        r0i = b.getAndIncrement();
        if (r0i >= 65535) {
            b = new AtomicInteger(100);
            r0i = b.getAndIncrement();
        }
        return r0i;
    }
}