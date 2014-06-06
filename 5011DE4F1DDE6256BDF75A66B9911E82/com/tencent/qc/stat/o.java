package com.tencent.qc.stat;

import java.util.List;

// compiled from: ProGuard
class o implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ q b;
    final /* synthetic */ l c;

    o(l r1_l, List r2_List, q r3_q) {
        this.c = r1_l;
        this.a = r2_List;
        this.b = r3_q;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}