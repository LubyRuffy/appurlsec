package com.tencent.qc.stat;

import java.util.List;

// compiled from: ProGuard
class e implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ StatStore b;

    e(StatStore r1_StatStore, List r2_List) {
        this.b = r1_StatStore;
        this.a = r2_List;
    }

    public void run() {
        this.b.b(this.a);
    }
}