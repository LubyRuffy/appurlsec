package com.baidu.mobstat;

import android.content.Context;
import java.util.TimerTask;

class n extends TimerTask {
    final /* synthetic */ Context a;
    final /* synthetic */ k b;

    n(k r1_k, Context r2_Context) {
        this.b = r1_k;
        this.a = r2_Context;
    }

    public void run() {
        if (!DataCore.getInstance().isPartEmpty()) {
            this.b.a(this.a, k.d(this.b));
        }
    }
}