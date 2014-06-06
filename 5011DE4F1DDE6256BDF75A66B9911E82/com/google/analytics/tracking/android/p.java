package com.google.analytics.tracking.android;

import android.text.TextUtils;
import java.util.Map;

// compiled from: GAThread.java
class p implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ long b;
    final /* synthetic */ o c;

    p(o r1_o, Map r2_Map, long r3j) {
        this.c = r1_o;
        this.a = r2_Map;
        this.b = r3j;
    }

    public void run() {
        if (o.a(this.c) || o.a(this.c, this.a)) {
        } else {
            this.a.put(ModelFields.CLIENT_ID, o.b(this.c));
            if (!TextUtils.isEmpty(o.c(this.c))) {
                this.a.put(ModelFields.CAMPAIGN, o.c(this.c));
                o.a(this.c, null);
            }
            o.b(this.c, this.a);
            o.c(this.c, this.a);
            o.d(this.c, this.a);
            o.f(this.c).putHit(x.generateHitParams(o.d(this.c), this.a), this.b, o.e(this.c, this.a), o.e(this.c));
        }
    }
}