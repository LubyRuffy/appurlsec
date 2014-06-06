package com.amap.api.location;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;

public class LocationProviderProxy {
    public static final String AMapNetwork = "lbs";
    public static final int AVAILABLE = 2;
    public static final int OUT_OF_SERVICE = 0;
    public static final int TEMPORARILY_UNAVAILABLE = 1;
    private LocationManager a;
    private String b;

    protected LocationProviderProxy(LocationManager r1_LocationManager, String r2_String) {
        this.a = r1_LocationManager;
        this.b = r2_String;
    }

    private LocationProvider a() {
        return this.a.getProvider(this.b);
    }

    static LocationProviderProxy a(LocationManager r1_LocationManager, String r2_String) {
        return new LocationProviderProxy(r1_LocationManager, r2_String);
    }

    public int getAccuracy() {
        return AMapNetwork.equals(this.b) ? AVAILABLE : a().getAccuracy();
    }

    public String getName() {
        return AMapNetwork.equals(this.b) ? AMapNetwork : a().getName();
    }

    public int getPowerRequirement() {
        return AMapNetwork.equals(this.b) ? AVAILABLE : a().getPowerRequirement();
    }

    public boolean hasMonetaryCost() {
        return AMapNetwork.equals(this.b) ? false : a().hasMonetaryCost();
    }

    public boolean meetsCriteria(Criteria r4_Criteria) {
        if (!AMapNetwork.equals(this.b)) {
            return a().meetsCriteria(r4_Criteria);
        }
        if (r4_Criteria == null) {
            return true;
        }
        if (r4_Criteria.isAltitudeRequired() || r4_Criteria.isBearingRequired() || r4_Criteria.isSpeedRequired() || r4_Criteria.getAccuracy() == 1) {
            return false;
        }
        return true;
    }

    public boolean requiresCell() {
        return AMapNetwork.equals(this.b) ? true : a().requiresCell();
    }

    public boolean requiresNetwork() {
        return AMapNetwork.equals(this.b) ? true : a().requiresNetwork();
    }

    public boolean requiresSatellite() {
        return AMapNetwork.equals(this.b) ? false : a().requiresNetwork();
    }

    public boolean supportsAltitude() {
        return AMapNetwork.equals(this.b) ? false : a().supportsAltitude();
    }

    public boolean supportsBearing() {
        return AMapNetwork.equals(this.b) ? false : a().supportsBearing();
    }

    public boolean supportsSpeed() {
        return AMapNetwork.equals(this.b) ? false : a().supportsSpeed();
    }
}