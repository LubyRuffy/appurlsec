package com.tencent.qc.stat;

import android.content.Context;

// compiled from: ProGuard
class k implements q {
    final /* synthetic */ Context a;
    final /* synthetic */ t b;

    k(t r1_t, Context r2_Context) {
        this.b = r1_t;
        this.a = r2_Context;
    }

    public void a() {
        if (StatStore.a(this.a).a() >= StatConfig.e()) {
            StatStore.a(this.a).a(StatConfig.e());
        }
    }

    public void b() {
    }
}