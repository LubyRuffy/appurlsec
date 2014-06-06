package org.apache.cordova;

import android.location.LocationManager;

public class NetworkListener extends CordovaLocationListener {
    public NetworkListener(LocationManager r2_LocationManager, GeoBroker r3_GeoBroker) {
        super(r2_LocationManager, r3_GeoBroker, "[Cordova NetworkListener]");
    }
}