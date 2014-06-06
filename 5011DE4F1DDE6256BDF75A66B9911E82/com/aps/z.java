package com.aps;

import android.telephony.CellLocation;
import qsbk.app.message.api.ChatEngine;

final class z extends Thread {
    private /* synthetic */ ae a;

    public z(ae r1_ae, String r2_String) {
        this.a = r1_ae;
        super(r2_String);
    }

    public final void run() {
        while (this.a.t) {
            try {
                Thread.sleep(ChatEngine.mQueryUnreadInterval);
            } catch (Exception e) {
            }
            CellLocation.requestLocationUpdate();
        }
    }
}