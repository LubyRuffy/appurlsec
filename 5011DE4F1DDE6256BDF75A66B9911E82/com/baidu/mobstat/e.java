package com.baidu.mobstat;

import com.baidu.mobstat.a.c;

class e implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ c d;

    e(c r1_c, long r2j, String r4_String, String r5_String) {
        this.d = r1_c;
        this.a = r2j;
        this.b = r4_String;
        this.c = r5_String;
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
        h r0_h = new h(this.d);
        r0_h.c = this.a;
        r0_h.a = this.b;
        r0_h.b = this.c;
        String r1_String = this.d.a(this.b, this.c);
        if (this.d.a.get(r1_String) != null) {
            Object[] r2_ObjectA = new Object[2];
            r2_ObjectA[0] = "stat";
            r2_ObjectA[1] = "EventStat: event_id[" + this.b + "] with label[" + this.c + "] is duplicated, older is removed";
            c.b(r2_ObjectA);
        }
        this.d.a.put(r1_String, r0_h);
        c.a("stat", "put a keyword[" + r1_String + "] into durationlist");
    }
}