package com.tencent.mm.sdk.platformtools;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;

class j extends PhoneStateListener {
    final /* synthetic */ i a;

    j(i r1_i) {
        this.a = r1_i;
    }

    public void onSignalStrengthsChanged(SignalStrength r4_SignalStrength) {
        super.onSignalStrengthsChanged(r4_SignalStrength);
        if (i.a(this.a) == 2) {
            i.a(r4_SignalStrength.getCdmaDbm());
        }
        if (i.a(this.a) == 1) {
            i.a(r4_SignalStrength.getGsmSignalStrength() * 2 - 113);
        }
        if (i.b(this.a) != null) {
            i.b(this.a).listen(i.c(this.a), 0);
        }
    }
}