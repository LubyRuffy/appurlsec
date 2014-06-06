package com.baidu.mobstat;

import android.content.Context;

class l implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ k b;

    l(k r1_k, Context r2_Context) {
        this.b = r1_k;
        this.a = r2_Context;
    }

    public void run() {
        k.a(this.b, BasicStoreTools.getInstance().a(this.a));
        if (k.a(this.b)) {
            i.a().b(this.a);
        }
        if (k.b(this.b) != null) {
            k.b(this.b).cancel();
            k.a(this.b, null);
        }
        k.a(this.b, SendStrategyEnum.values()[BasicStoreTools.getInstance().b(this.a)]);
        k.a(this.b, BasicStoreTools.getInstance().c(this.a));
        k.b(this.b, BasicStoreTools.getInstance().d(this.a));
        if (k.c(this.b).equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.b.d(this.a);
        } else if (k.c(this.b).equals(SendStrategyEnum.ONCE_A_DAY)) {
            this.b.d(this.a);
        }
        k.b().postDelayed(new m(this), (long) (k.e(this.b) * 1000));
    }
}