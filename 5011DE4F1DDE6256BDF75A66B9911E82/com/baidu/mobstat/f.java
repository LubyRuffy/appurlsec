package com.baidu.mobstat;

import android.content.Context;
import com.baidu.mobstat.a.c;

class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ Context d;
    final /* synthetic */ c e;

    f(c r1_c, String r2_String, String r3_String, long r4j, Context r6_Context) {
        this.e = r1_c;
        this.a = r2_String;
        this.b = r3_String;
        this.c = r4j;
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
        String r1_String = this.e.a(this.a, this.b);
        h r4_h = (h) this.e.a.get(r1_String);
        if (r4_h == null) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "stat";
            r0_ObjectA[1] = "EventStat: event_id[" + this.a + "] with label[" + this.b + "] is not started or alread done.";
            c.b(r0_ObjectA);
        } else if (this.a.equals(r4_h.a) && this.b.equals(r4_h.b)) {
            this.e.a.remove(r1_String);
            long r6j = this.c - r4_h.c;
            if (r6j <= 0) {
                c.a("stat", "EventStat: Wrong Case, Duration must be positive");
            } else {
                DataCore.getInstance().putEvent(this.a, this.b, 1, r4_h.c, r6j);
                DataCore.getInstance().flush(this.d);
            }
        } else {
            c.a("stat", "EventStat: Wrong Case, eventId/label pair not match");
        }
    }
}