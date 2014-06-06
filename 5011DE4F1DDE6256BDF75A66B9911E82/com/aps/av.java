package com.aps;

import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

public final class av {
    int a;
    int b;
    int c;
    int d;
    int e;

    av(CellLocation r2_CellLocation) {
        this.a = 2147483647;
        this.b = 2147483647;
        this.c = 2147483647;
        this.d = 2147483647;
        this.e = 2147483647;
        if (r2_CellLocation != null) {
            if (r2_CellLocation instanceof GsmCellLocation) {
                GsmCellLocation r2_GsmCellLocation = (GsmCellLocation) r2_CellLocation;
                this.e = r2_GsmCellLocation.getCid();
                this.d = r2_GsmCellLocation.getLac();
            } else if (r2_CellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation r2_CdmaCellLocation = (CdmaCellLocation) r2_CellLocation;
                this.c = r2_CdmaCellLocation.getBaseStationId();
                this.b = r2_CdmaCellLocation.getNetworkId();
                this.a = r2_CdmaCellLocation.getSystemId();
            }
        }
    }
}