package com.aps;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: APS.java
class r extends PhoneStateListener {
    final /* synthetic */ a a;

    r(a r1_a) {
        this.a = r1_a;
    }

    public void onCellLocationChanged(CellLocation r4_CellLocation) {
        if (!(r4_CellLocation == null || this.a.q())) {
            if (this.a.j != null) {
                this.a.J = this.a.j.getCellLocation();
            }
            if (this.a.J == null) {
                this.a.J = r4_CellLocation;
            }
            this.a.y = o.a();
        }
    }

    public void onServiceStateChanged(ServiceState r3_ServiceState) {
        switch (r3_ServiceState.getState()) {
            case XListViewHeader.STATE_READY:
                this.a.k.clear();
                this.a.p = -113;
                break;
        }
    }

    public void onSignalStrengthChanged(int r3i) {
        int r0i = -113;
        switch (this.a.g) {
            case XListViewHeader.STATE_READY:
                r0i = o.a(r3i);
                break;
            case XListViewHeader.STATE_REFRESHING:
                r0i = o.a(r3i);
                break;
        }
        this.a.b(r0i);
    }

    public void onSignalStrengthsChanged(SignalStrength r3_SignalStrength) {
        int r0i = -113;
        switch (this.a.g) {
            case XListViewHeader.STATE_READY:
                r0i = o.a(r3_SignalStrength.getGsmSignalStrength());
                break;
            case XListViewHeader.STATE_REFRESHING:
                r0i = r3_SignalStrength.getCdmaDbm();
                break;
        }
        this.a.b(r0i);
    }
}