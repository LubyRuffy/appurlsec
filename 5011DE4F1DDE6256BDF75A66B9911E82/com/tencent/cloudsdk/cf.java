package com.tencent.cloudsdk;

import android.content.Context;
import com.tencent.cloudsdk.common.record.debug.WnsClientLog;

// compiled from: SourceFile
public class cf extends ce {
    private static final String a;
    private static cf b;
    private static cd c;
    private boolean d;

    static {
        a = cf.class.getName();
        b = null;
        c = null;
        c = new cd(32);
    }

    private cf(Context r2_Context) {
        super(r2_Context);
        this.d = false;
    }

    public static synchronized cf a(Context r2_Context) {
        cf r0_cf;
        synchronized (cf.class) {
            if (b == null) {
                b = new cf(r2_Context);
            }
            r0_cf = b;
        }
        return r0_cf;
    }

    private String b(int r2i) {
        return r2i == 0 ? em.d() : r2i;
    }

    private String c(String r2_String, int r3i, boolean r4z) {
        return c(r2_String, b(r3i), r4z);
    }

    private String c(String r3_String, String r4_String, boolean r5z) {
        return new StringBuilder(String.valueOf(r3_String)).append(r4_String).append(r5z).toString();
    }

    public cl a(String r6_String, int r7i, boolean r8z) {
        cl r0_cl;
        String r1_String = c(r6_String, r7i, r8z);
        String r2_String = b(r7i);
        er.a(a, new StringBuilder(">>>\u4ece\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: key=").append(r1_String).toString());
        if (c.containsKey(r1_String)) {
            r0_cl = (cl) c.get(r1_String);
            if (r0_cl == null || r0_cl.a()) {
                c.remove(r1_String);
                er.a(a, new StringBuilder(">>>\u4ece\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: \u5185\u5b58\u7f13\u5b58\u4e0d\u5b58\u5728 key=").append(r1_String).append("\uff0c\u4eceDB\u67e5\u627e").toString());
                r0_cl = super.b(r6_String, r2_String, r8z);
                if (r0_cl == null) {
                    c.put(r1_String, r0_cl);
                }
            } else {
                er.a(a, ">>>\u4ece\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: \u4ece\u5185\u5b58\u7f13\u5b58\u4e2d\u8fd4\u56de");
            }
        } else {
            er.a(a, new StringBuilder(">>>\u4ece\u7f13\u5b58\u4e2d\u67e5\u627eIP\u4fe1\u606f: \u5185\u5b58\u7f13\u5b58\u4e0d\u5b58\u5728 key=").append(r1_String).append("\uff0c\u4eceDB\u67e5\u627e").toString());
            r0_cl = super.b(r6_String, r2_String, r8z);
            if (r0_cl == null) {
                return r0_cl;
            }
            c.put(r1_String, r0_cl);
        }
        return r0_cl;
    }

    public void a(cl r3_cl, boolean r4z) {
        String r0_String = c(r3_cl.b, Integer.valueOf(r3_cl.c).intValue(), r4z);
        if (!c.containsKey(r0_String)) {
            c.put(r0_String, r3_cl);
            super.a(r3_cl, r4z);
        }
    }

    public void a(String r2_String) {
        a(r2_String, true);
        a(r2_String, false);
    }

    public void a(String r3_String, String r4_String, boolean r5z, String r6_String, String r7_String) {
        c.remove(c(r3_String, r4_String, r5z));
        super.a(r3_String, r4_String, r5z, r6_String, r7_String);
    }

    public void a(String r7_String, boolean r8z) {
        WnsClientLog.i(a, new StringBuilder("clearDomain, domain = ").append(r7_String).toString());
        int[] r1_intA = new int[4];
        r1_intA[1] = 4;
        r1_intA[2] = 1;
        r1_intA[3] = 2;
        int r2i = r1_intA.length;
        int r0i = 0;
        while (r0i < r2i) {
            b(r7_String, r1_intA[r0i], r8z);
            r0i++;
        }
    }

    public void b() {
        if (!this.d) {
            en.a.post(new cc(this, null, (short) 2, false));
        }
    }

    public void b(cl r4_cl, boolean r5z) {
        en.a.post(new cc(this, r4_cl, (short) 1, r5z));
    }

    public void b(String r6_String, int r7i, boolean r8z) {
        String r0_String = c(r6_String, r7i, r8z);
        String r1_String = b(r7i);
        WnsClientLog.i(a, new StringBuilder("clearDomain, key = ").append(r0_String).toString());
        c.remove(r0_String);
        a(r6_String, r1_String, r8z);
    }
}