package com.tencent.cloudsdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// compiled from: SourceFile
public class bn implements bp {
    protected List a;
    private br b;
    private bm c;
    private bu d;
    private bk e;

    public bn(byte[] r3_byteA) {
        this.a = new ArrayList();
        this.b = new br();
        this.b.a(0);
        this.b.a(r3_byteA);
        c();
    }

    public bf a() {
        this.c.a(this.b);
        return this.c.a();
    }

    public bo b() {
        Iterator r1_Iterator = this.a.iterator();
        while (r1_Iterator.hasNext()) {
            ((bs) r1_Iterator.next()).a(this.b);
        }
        bo r0_bo = new bo();
        r0_bo.a(this.e.a());
        r0_bo.a(this.d.c());
        r0_bo.a(this.c.a());
        r0_bo.b(this.d.b());
        r0_bo.c(this.d.a());
        return r0_bo;
    }

    protected void c() {
        this.c = new bm();
        this.d = new bu();
        this.e = new bk();
        this.a.add(this.d);
        this.a.add(new bt(4));
        this.a.add(this.e);
    }

    public /* synthetic */ Object d() {
        return b();
    }
}