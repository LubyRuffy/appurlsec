package com.amap.api.location;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

// compiled from: IGPSManager.java
public class c {
    private static c b;
    private LocationManager a;
    private a c;
    private com.amap.api.location.core.c d;
    private String e;
    private LocationListener f;

    static {
        b = null;
    }

    private c(Context r2_Context, LocationManager r3_LocationManager, a r4_a) {
        this.a = null;
        this.f = new d(this);
        this.a = r3_LocationManager;
        this.c = r4_a;
        this.d = com.amap.api.location.core.c.a(r2_Context);
        this.e = this.d.c(r2_Context);
    }

    static c a(Context r1_Context, LocationManager r2_LocationManager, a r3_a) {
        if (b == null) {
            b = new c(r1_Context, r2_LocationManager, r3_a);
        }
        return b;
    }

    void a() {
        this.a.removeUpdates(this.f);
    }

    void a(long r7j, float r9f, LocationListener r10_LocationListener, String r11_String) {
        try {
            if (this.a.isProviderEnabled(LocationManagerProxy.GPS_PROVIDER)) {
                this.a.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, r7j, r9f, this.f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}