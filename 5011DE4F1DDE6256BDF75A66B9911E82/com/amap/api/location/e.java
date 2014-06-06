package com.amap.api.location;

import android.location.Location;
import android.os.Bundle;

// compiled from: LocationListenerProxy.java
public class e implements AMapLocationListener {
    private LocationManagerProxy a;
    private AMapLocationListener b;

    public e(LocationManagerProxy r2_LocationManagerProxy) {
        this.b = null;
        this.a = r2_LocationManagerProxy;
    }

    public void a() {
        if (this.a != null) {
            this.a.removeUpdates((AMapLocationListener)this);
        }
        this.b = null;
    }

    public boolean a(AMapLocationListener r8_AMapLocationListener, long r9j, float r11f, String r12_String) {
        this.b = r8_AMapLocationListener;
        if (!LocationProviderProxy.AMapNetwork.equals(r12_String)) {
            return false;
        }
        this.a.requestLocationUpdates(r12_String, r9j, r11f, (AMapLocationListener)this);
        return true;
    }

    public void onLocationChanged(Location r2_Location) {
        if (this.b != null) {
            this.b.onLocationChanged(r2_Location);
        }
    }

    public void onLocationChanged(AMapLocation r2_AMapLocation) {
        if (this.b != null) {
            this.b.onLocationChanged(r2_AMapLocation);
        }
    }

    public void onProviderDisabled(String r2_String) {
        if (this.b != null) {
            this.b.onProviderDisabled(r2_String);
        }
    }

    public void onProviderEnabled(String r2_String) {
        if (this.b != null) {
            this.b.onProviderEnabled(r2_String);
        }
    }

    public void onStatusChanged(String r2_String, int r3i, Bundle r4_Bundle) {
        if (this.b != null) {
            this.b.onStatusChanged(r2_String, r3i, r4_Bundle);
        }
    }
}