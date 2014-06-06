package com.aps;

import android.location.GpsStatus.NmeaListener;

final class ad implements NmeaListener {
    private /* synthetic */ ae a;

    private ad(ae r1_ae) {
        this.a = r1_ae;
    }

    public final void onNmeaReceived(long r2j, String r4_String) {
        ae.c(this.a, r2j);
        ae.a(this.a, r4_String);
    }
}