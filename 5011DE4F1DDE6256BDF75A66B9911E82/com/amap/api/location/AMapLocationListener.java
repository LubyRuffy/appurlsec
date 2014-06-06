package com.amap.api.location;

import android.location.LocationListener;

public interface AMapLocationListener extends LocationListener {
    public void onLocationChanged(AMapLocation r1_AMapLocation);
}