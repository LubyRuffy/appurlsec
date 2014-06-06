package com.aps;

import android.os.Looper;

final class aj extends Thread {
    final /* synthetic */ t a;

    aj(t r1_t, String r2_String) {
        this.a = r1_t;
        super(r2_String);
    }

    public final void run() {
        Looper.prepare();
        t.a(this.a, Looper.myLooper());
        t.a(this.a, new ap(this.a));
        t.e(this.a).addGpsStatusListener(t.d(this.a));
        t.e(this.a).addNmeaListener(t.d(this.a));
        t.a(this.a, new ak(this));
        t.e(this.a).requestLocationUpdates("passive", (long) t.l(), 10.0f, t.f(this.a));
        Looper.loop();
    }
}