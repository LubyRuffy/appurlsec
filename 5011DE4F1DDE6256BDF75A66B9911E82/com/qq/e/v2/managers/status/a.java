package com.qq.e.v2.managers.status;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

class a implements LocationListener {
    private /* synthetic */ LocationManager a;
    private /* synthetic */ DeviceStatus b;

    a(DeviceStatus r1_DeviceStatus, LocationManager r2_LocationManager) {
        this.b = r1_DeviceStatus;
        this.a = r2_LocationManager;
    }

    public void onLocationChanged(Location r5_Location) {
        DeviceStatus.a(this.b, r5_Location.getLatitude());
        DeviceStatus.b(this.b, r5_Location.getLongitude());
        this.a.removeUpdates(this);
    }

    public void onProviderDisabled(String r1_String) {
    }

    public void onProviderEnabled(String r1_String) {
    }

    public void onStatusChanged(String r1_String, int r2i, Bundle r3_Bundle) {
    }
}