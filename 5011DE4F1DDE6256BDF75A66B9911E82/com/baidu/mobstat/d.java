package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.a.c;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ long d;
    final /* synthetic */ Context e;
    final /* synthetic */ c f;

    d(c r1_c, String r2_String, String r3_String, int r4i, long r5j, Context r7_Context) {
        this.f = r1_c;
        this.a = r2_String;
        this.b = r3_String;
        this.c = r4i;
        this.d = r5j;
        this.e = r7_Context;
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
        DataCore.getInstance().putEvent(this.a, this.b, this.c, this.d, 0);
        DataCore.getInstance().flush(this.e);
    }
}