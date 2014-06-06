package org.apache.cordova;

import android.location.LocationManager;
import com.amap.api.location.LocationManagerProxy;
import com.tencent.mm.sdk.platformtools.Util;

public class GPSListener extends CordovaLocationListener {
    public GPSListener(LocationManager r2_LocationManager, GeoBroker r3_GeoBroker) {
        super(r2_LocationManager, r3_GeoBroker, "[Cordova GPSListener]");
    }

    protected void start() {
        if (!this.running) {
            if (this.locationManager.getProvider(LocationManagerProxy.GPS_PROVIDER) != null) {
                this.running = true;
                this.locationManager.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, Util.MILLSECONDS_OF_MINUTE, 0.0f, this);
            } else {
                fail(CordovaLocationListener.POSITION_UNAVAILABLE, "GPS provider is not available.");
            }
        }
    }
}