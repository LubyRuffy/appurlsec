package com.aps;

import java.util.TimerTask;

final class ag extends TimerTask {
    private /* synthetic */ af a;

    ag(af r1_af) {
        this.a = r1_af;
    }

    public final void run() {
        if (ae.f(this.a.a) != null) {
            ae.f(this.a.a).startScan();
        }
    }
}