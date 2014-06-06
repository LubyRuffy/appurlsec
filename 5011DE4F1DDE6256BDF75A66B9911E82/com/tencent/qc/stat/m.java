package com.tencent.qc.stat;

import java.util.List;

// compiled from: ProGuard
class m implements q {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ b c;

    m(b r1_b, List r2_List, int r3i) {
        this.c = r1_b;
        this.a = r2_List;
        this.b = r3i;
    }

    public void a() {
        int r0i;
        this.c.b.a(this.a);
        r0i = this.c.a == -1 ? this.c.a : this.c.a - this.a.size();
        if (r0i == -1 || r0i > 0) {
            this.c.b.a(r0i);
        }
    }

    public void b() {
        StatStore r0_StatStore = this.c.b;
        r0_StatStore.b += this.b;
        this.c.b.a(this.a, 1);
    }
}