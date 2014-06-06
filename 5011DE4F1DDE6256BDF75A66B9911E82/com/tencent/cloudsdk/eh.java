package com.tencent.cloudsdk;

import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import java.util.HashMap;
import java.util.Map;

// compiled from: SourceFile
public class eh {
    private static final String a;
    private static eh b;
    private Map c;

    static {
        a = eh.class.getSimpleName();
    }

    private eh() {
        this.c = new HashMap();
    }

    public static synchronized eh a() {
        eh r0_eh;
        synchronized (eh.class) {
            if (b == null) {
                b = new eh();
            }
            r0_eh = b;
        }
        return r0_eh;
    }

    public void a(String r2_String) {
        if (this.c.containsKey(r2_String)) {
            this.c.remove(r2_String);
        }
    }

    public synchronized void a(String r4_String, int r5i, boolean r6z) {
        String r0_String = em.a(r4_String);
        if (this.c.containsKey(r0_String)) {
            er.a(a, ">>\u5f53\u524d\u6709\u6d4b\u901f\u7ebf\u7a0b\u5728\u6267\u884c<<<");
        } else if (et.b(GlobalContext.getContext(), r4_String)) {
            dy r1_dy = new dy();
            this.c.put(r0_String, r1_dy);
            r1_dy.a(r4_String, r5i, r6z);
        }
    }

    public void a(String r2_String, dy r3_dy) {
        this.c.put(r2_String, r3_dy);
    }
}