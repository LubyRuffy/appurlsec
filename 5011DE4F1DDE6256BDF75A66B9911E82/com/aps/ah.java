package com.aps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.amap.api.location.LocationManagerProxy;

final class ah implements LocationListener {
    private /* synthetic */ t a;

    ah(t r1_t) {
        this.a = r1_t;
    }

    private static boolean a(Location r4_Location) {
        return r4_Location != null && LocationManagerProxy.GPS_PROVIDER.equalsIgnoreCase(r4_Location.getProvider()) && r4_Location.getLatitude() > -90.0d && r4_Location.getLatitude() < 90.0d && r4_Location.getLongitude() > -180.0d && r4_Location.getLongitude() < 180.0d;
    }

    public final void onLocationChanged(Location r4_Location) {
        if (r4_Location == null || (!a(r4_Location))) {
        } else {
            if (r4_Location.getSpeed() > ((float) t.g())) {
                at.a(t.h());
            } else if (r4_Location.getSpeed() > ((float) t.i())) {
                at.a(t.j());
            } else {
                at.a(t.k());
            }
            t.b(this.a).a();
            a(r4_Location);
            if (t.b(this.a).a() && a(r4_Location)) {
                r4_Location.setTime(System.currentTimeMillis());
                t.a(this.a, System.currentTimeMillis());
                t.a(this.a, r4_Location);
                if (!t.c(this.a)) {
                    t.a(this.a, r4_Location, t.c(this.a));
                } else {
                    t.a(this.a, "new location in indoor collect");
                }
            }
        }
    }

    public final void onProviderDisabled(String r1_String) {
    }

    public final void onProviderEnabled(String r1_String) {
    }

    public final void onStatusChanged(String r1_String, int r2i, Bundle r3_Bundle) {
    }
}