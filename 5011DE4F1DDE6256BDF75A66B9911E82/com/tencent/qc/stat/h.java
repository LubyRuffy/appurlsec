package com.tencent.qc.stat;

import java.util.List;

// compiled from: ProGuard
class h implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ StatStore c;

    h(StatStore r1_StatStore, List r2_List, int r3i) {
        this.c = r1_StatStore;
        this.a = r2_List;
        this.b = r3i;
    }

    public void run() {
        this.c.b(this.a, this.b);
    }
}