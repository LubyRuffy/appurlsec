package com.tencent.qc.stat;

import com.tencent.qc.stat.event.Event;

// compiled from: ProGuard
class f implements Runnable {
    final /* synthetic */ Event a;
    final /* synthetic */ q b;
    final /* synthetic */ StatStore c;

    f(StatStore r1_StatStore, Event r2_Event, q r3_q) {
        this.c = r1_StatStore;
        this.a = r2_Event;
        this.b = r3_q;
    }

    public void run() {
        this.c.a(this.a, this.b);
    }
}