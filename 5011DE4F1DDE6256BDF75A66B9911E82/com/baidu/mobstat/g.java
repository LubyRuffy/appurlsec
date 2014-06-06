package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.a.c;

class g implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ Context d;
    final /* synthetic */ c e;

    g(c r1_c, long r2j, String r4_String, String r5_String, Context r6_Context) {
        this.e = r1_c;
        this.a = r2j;
        this.b = r4_String;
        this.c = r5_String;
        this.d = r6_Context;
    }

    public void run() {
        if (!j.a().c()) {
            synchronized (j.a()) {
                try {
                    j.a().wait();
                } catch (InterruptedException e) {
                    c.a("stat", e);
                }
            }
        }
        if (this.a <= 0) {
            c.a("stat", "EventStat: Wrong Case, Duration must be positive");
        } else {
            DataCore.getInstance().putEvent(this.b, this.c, 1, System.currentTimeMillis(), this.a);
            DataCore.getInstance().flush(this.d);
        }
    }
}