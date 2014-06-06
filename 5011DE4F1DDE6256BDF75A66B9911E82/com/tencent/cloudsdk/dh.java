package com.tencent.cloudsdk;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: SourceFile
public abstract class dh {
    private AtomicBoolean a;
    private Set b;
    private dj c;

    public dh() {
        this.a = new AtomicBoolean(false);
        this.b = Collections.synchronizedSet(new HashSet());
        this.c = e();
    }

    private void f() {
        long r0j = c();
        Runnable r2_Runnable = new cx(null);
        this.b.add(r2_Runnable);
        en.a.postDelayed(r2_Runnable, r0j);
        er.a("AbsCircleReport", new StringBuilder(String.valueOf(r0j)).append("ms\u540e\u5f00\u59cb\u4e0b\u4e00\u6b21\u4e0a\u62a5\u3002").toString());
    }

    private void g() {
        int r0i = this.c.a(em.b());
        dk r1_dk = d();
        if (r0i == 1) {
            er.a("AbsCircleReport", "\u7f51\u7edc\u4e0d\u7b26\u5408\u6761\u4ef6\uff0c\u4e0d\u4e0a\u62a5!");
            r1_dk.a();
        } else if (r0i == 3) {
            er.a("AbsCircleReport", "\u5f00\u59cb\u5168\u91cf\u4e0a\u62a5!");
            r1_dk.b();
        } else {
            if (r0i == 2) {
                er.a("AbsCircleReport", "\u5f00\u59cb\u7b80\u5355\u4e0a\u62a5!");
                r1_dk.c();
            }
        }
    }

    public void a() {
        if (this.b.isEmpty()) {
            this.a.set(false);
            f();
        }
    }

    public void b() {
        if (!this.b.isEmpty()) {
            this.a.set(true);
            Iterator r1_Iterator = this.b.iterator();
            while (r1_Iterator.hasNext()) {
                en.a.removeCallbacks((Runnable) r1_Iterator.next());
            }
        }
    }

    public long c() {
        return this.c.a();
    }

    protected abstract dk d();

    protected abstract dj e();
}