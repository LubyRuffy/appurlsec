package com.tencent.cloudsdk;

import android.os.Build.VERSION;
import android.os.Bundle;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.cloudsdk.common.utils.ClientIdManager;
import com.tencent.cloudsdk.defaultsdk.mna.tsocket.GlobalContext;
import com.tencent.tauth.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// compiled from: SourceFile
class dy {
    private static final String a;
    private String b;
    private String c;
    private int d;
    private HashMap e;
    private List f;
    private List g;
    private List h;
    private String i;
    private Map j;
    private Map k;
    private Map l;
    private Map m;
    private boolean n;

    static {
        a = dy.class.getSimpleName();
    }

    dy() {
        this.e = new HashMap();
        this.j = new HashMap();
        this.k = new HashMap();
        this.l = new HashMap();
        this.m = new HashMap();
    }

    private Bundle a(cm r5_cm) {
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putInt(Constants.PARAM_TYPE_ID, 1000193);
        r0_Bundle.putLong("sip", r5_cm.a);
        r0_Bundle.putInt("sport", r5_cm.b);
        r0_Bundle.putLong("ocip", r5_cm.c);
        r0_Bundle.putInt("pkg", r5_cm.d);
        r0_Bundle.putLong("tc", r5_cm.e);
        r0_Bundle.putInt(KEYS.RET, r5_cm.f);
        r0_Bundle.putLong("octc", r5_cm.g);
        r0_Bundle.putInt("ocret", r5_cm.h);
        r0_Bundle.putInt("nt", r5_cm.i);
        r0_Bundle.putInt("sp", r5_cm.j);
        r0_Bundle.putInt("os", 1);
        r0_Bundle.putString("osv", VERSION.RELEASE);
        r0_Bundle.putString("sdkv", "1.2.0");
        r0_Bundle.putLong("ctc", r5_cm.k);
        r0_Bundle.putLong("occtc", r5_cm.l);
        r0_Bundle.putLong("stc", r5_cm.m);
        r0_Bundle.putLong("ocstc", r5_cm.n);
        r0_Bundle.putLong("rtc", r5_cm.o);
        r0_Bundle.putLong("ocrtc", r5_cm.p);
        r0_Bundle.putInt("seq", ClientIdManager.getInstance().getClientId());
        r0_Bundle.putString("domain", this.c);
        b(r5_cm);
        return r0_Bundle;
    }

    private synchronized void a(String r4_String) {
        et.a(GlobalContext.getContext(), r4_String, System.currentTimeMillis());
        eh.a().a(this.b);
        if (this.e.size() == this.j.size() + this.g.size()) {
            d();
            c();
            b();
        }
    }

    private void a(boolean r8z) throws eq {
        cn r0_cn;
        Iterator r3_Iterator;
        cn r1_cn;
        List r1_List = ep.a(GlobalContext.getContext(), this.c, r8z);
        this.i = ((cn) r1_List.get(0)).a;
        ck r0_ck = ep.a(r1_List);
        this.f = r0_ck.a;
        this.g = r0_ck.c;
        this.h = r0_ck.b;
        Iterator r2_Iterator = this.g.iterator();
        while (r2_Iterator.hasNext()) {
            r0_cn = (cn) r2_Iterator.next();
            this.m.put(r0_cn.a, Integer.valueOf(r0_cn.d));
            r3_Iterator = this.h.iterator();
            while (r3_Iterator.hasNext()) {
                r1_cn = (cn) r3_Iterator.next();
                if (r0_cn.d == r1_cn.d) {
                    this.k.put(r0_cn, r1_cn);
                    this.l.put(r0_cn.a, r1_cn.a);
                }
            }
        }
        r2_Iterator = this.f.iterator();
        while (r2_Iterator.hasNext()) {
            r0_cn = (cn) r2_Iterator.next();
            r3_Iterator = this.g.iterator();
            while (r3_Iterator.hasNext()) {
                r1_cn = (cn) r3_Iterator.next();
                if (r0_cn.d == r1_cn.d) {
                    this.j.put(r0_cn, r1_cn);
                }
            }
            if (!this.j.containsKey(r0_cn)) {
                this.j.put(r0_cn, (cn) this.g.get(0));
            }
        }
    }

    private void b() {
        this.e.clear();
        this.j.clear();
        this.l.clear();
        this.k.clear();
        this.m.clear();
    }

    private void b(cm r5_cm) {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append(">>>\u6d4b\u901f\u7ed3\u679c<<<\n");
        r0_StringBuilder.append(new StringBuilder("RSIP:").append(ep.a(r5_cm.a)).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("OCIP:").append(ep.a(r5_cm.c)).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u6d4b\u901f\u5305\u5927\u5c0f:").append(r5_cm.d).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u76f4\u8fde\u8017\u65f6:").append(r5_cm.e).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("OC\u52a0\u901f\u8017\u65f6:").append(r5_cm.g).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u7f51\u7edc\u7c7b\u578b:").append(r5_cm.i).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u8fd0\u8425\u5546\u7c7b\u578b:").append(r5_cm.j).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u7cfb\u7edf\u7248\u672c:").append(VERSION.RELEASE).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u76f4\u8fde\u5efa\u7acb\u8fde\u63a5\u65f6\u95f4:").append(r5_cm.k).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("OC\u52a0\u901f\u5efa\u7acb\u8fde\u63a5\u65f6\u95f4:").append(r5_cm.l).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u76f4\u8fde\u53d1\u9001\u8017\u65f6:").append(r5_cm.m).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("OC\u52a0\u901f\u53d1\u9001\u8017\u65f6:").append(r5_cm.n).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("\u76f4\u8fde\u63a5\u6536\u8017\u65f6:").append(r5_cm.o).append("\n").toString());
        r0_StringBuilder.append(new StringBuilder("OC\u52a0\u901f\u63a5\u6536\u8017\u65f6:").append(r5_cm.p).append("\n").toString());
        er.a(a, r0_StringBuilder.toString());
    }

    private void c() {
        co r3_co = null;
        Iterator r5_Iterator = this.j.keySet().iterator();
        co r1_co = null;
        while (r5_Iterator.hasNext()) {
            cn r2_cn = (cn) r5_Iterator.next();
            Iterator r6_Iterator = this.e.keySet().iterator();
            co r4_co = r3_co;
            r3_co = r1_co;
            while (r6_Iterator.hasNext()) {
                cn r0_cn = (cn) r6_Iterator.next();
                if (((cn) this.j.get(r2_cn)).a.equals(((co) this.e.get(r0_cn)).f)) {
                    if (((co) this.e.get(r0_cn)).d == 0) {
                        r3_co = (co) this.e.get(r0_cn);
                    }
                    if (((co) this.e.get(r0_cn)).d == 2) {
                        r4_co = (co) this.e.get(r0_cn);
                    }
                }
            }
            if (r3_co == null || r4_co == null) {
                r1_co = r3_co;
                r3_co = r4_co;
            } else {
                cm r0_cm = r3_co.a;
                r0_cm.e = r4_co.a.e;
                r0_cm.f = r4_co.a.f;
                r0_cm.k = r4_co.a.k;
                r0_cm.m = r4_co.a.m;
                r0_cm.o = r4_co.a.o;
                cz.a().a(a(r0_cm));
                r1_co = r3_co;
                r3_co = r4_co;
            }
        }
        Iterator r3_Iterator = this.e.keySet().iterator();
        while (r3_Iterator.hasNext()) {
            co r0_co = (co) this.e.get((cn) r3_Iterator.next());
            if (this.j.size() > 0) {
                Iterator r4_Iterator = this.j.keySet().iterator();
                int r2i = 1;
                while (r4_Iterator.hasNext()) {
                    r2i = r0_co.f.equals(((cn) this.j.get((cn) r4_Iterator.next())).a) ? r2i & 0 : r2i & 1;
                }
                if (r2i != 0) {
                    cz.a().a(a(r0_co.a));
                }
            } else {
                cz.a().a(a(r0_co.a));
            }
        }
    }

    private void d() {
        Iterator r4_Iterator = this.e.keySet().iterator();
        long r2j = 2147483647L;
        co r1_co = null;
        while (r4_Iterator.hasNext()) {
            co r0_co = (co) this.e.get((cn) r4_Iterator.next());
            if (r2j > r0_co.b) {
                r2j = r0_co.b;
                r1_co = r0_co;
            }
        }
        if (r1_co == null || r1_co.g >= 3) {
        } else {
            String r0_String;
            r0_String = r1_co.d == 0 ? r1_co.c : r1_co.e;
            ep.a(this.c, this.i, r0_String, true);
            ep.a(this.c, this.i, r0_String, false);
        }
    }

    public synchronized void a(String r4_String, int r5i, boolean r6z) {
        this.c = r4_String;
        this.d = r5i;
        this.n = r6z;
        this.b = em.a(this.c);
        eh.a().a(this.b, this);
        new Thread(new ef(this, null)).start();
    }
}