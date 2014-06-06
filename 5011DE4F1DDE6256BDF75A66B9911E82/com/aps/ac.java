package com.aps;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;

final class ac extends PhoneStateListener {
    private /* synthetic */ ae a;

    private ac(ae r1_ae) {
        this.a = r1_ae;
    }

    public final void onCellLocationChanged(CellLocation r4_CellLocation) {
        ae.b(this.a, System.currentTimeMillis());
        ae.a(this.a, r4_CellLocation);
        super.onCellLocationChanged(r4_CellLocation);
    }

    public final void onServiceStateChanged(ServiceState r5_ServiceState) {
        boolean r2z = false;
        if (r5_ServiceState.getState() == 0) {
            ae.b(this.a, true);
            String[] r0_StringA = ae.a(ae.i(this.a));
            ae.a(this.a, Integer.parseInt(r0_StringA[r2z]));
            ae.b(this.a, Integer.parseInt(r0_StringA[1]));
        } else {
            ae.b(this.a, false);
        }
        super.onServiceStateChanged(r5_ServiceState);
    }

    public final void onSignalStrengthsChanged(SignalStrength r3_SignalStrength) {
        if (ae.j(this.a)) {
            ae.c(this.a, r3_SignalStrength.getCdmaDbm());
        } else {
            ae.c(this.a, r3_SignalStrength.getGsmSignalStrength());
            if (ae.k(this.a) == 99) {
                ae.c(this.a, -1);
            } else {
                ae.c(this.a, ae.k(this.a) * 2 - 113);
            }
        }
        super.onSignalStrengthsChanged(r3_SignalStrength);
    }
}