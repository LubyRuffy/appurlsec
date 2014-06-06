package org.apache.cordova;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.amap.api.location.LocationManagerProxy;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.cordova.api.CallbackContext;

public class CordovaLocationListener implements LocationListener {
    public static int PERMISSION_DENIED;
    public static int POSITION_UNAVAILABLE;
    public static int TIMEOUT;
    private String TAG;
    private List<CallbackContext> callbacks;
    protected LocationManager locationManager;
    private GeoBroker owner;
    protected boolean running;
    public HashMap<String, CallbackContext> watches;

    static {
        PERMISSION_DENIED = 1;
        POSITION_UNAVAILABLE = 2;
        TIMEOUT = 3;
    }

    public CordovaLocationListener(LocationManager r2_LocationManager, GeoBroker r3_GeoBroker, String r4_String) {
        this.running = false;
        this.watches = new HashMap();
        this.callbacks = new ArrayList();
        this.TAG = "[Cordova Location Listener]";
        this.locationManager = r2_LocationManager;
        this.owner = r3_GeoBroker;
        this.TAG = r4_String;
    }

    private void stop() {
        if (this.running) {
            this.locationManager.removeUpdates(this);
            this.running = false;
        }
    }

    private void win(Location r4_Location) {
        Iterator r1_Iterator = this.callbacks.iterator();
        while (r1_Iterator.hasNext()) {
            this.owner.win(r4_Location, (CallbackContext) r1_Iterator.next());
        }
        if (this.owner.isGlobalListener(this)) {
            Log.d(this.TAG, "Stopping global listener");
            stop();
        }
        this.callbacks.clear();
        r1_Iterator = this.watches.values().iterator();
        while (r1_Iterator.hasNext()) {
            this.owner.win(r4_Location, (CallbackContext) r1_Iterator.next());
        }
    }

    public void addCallback(CallbackContext r3_CallbackContext) {
        this.callbacks.add(r3_CallbackContext);
        if (size() == 1) {
            start();
        }
    }

    public void addWatch(String r3_String, CallbackContext r4_CallbackContext) {
        this.watches.put(r3_String, r4_CallbackContext);
        if (size() == 1) {
            start();
        }
    }

    public void clearWatch(String r2_String) {
        if (this.watches.containsKey(r2_String)) {
            this.watches.remove(r2_String);
        }
        if (size() == 0) {
            stop();
        }
    }

    public void destroy() {
        stop();
    }

    protected void fail(int r4i, String r5_String) {
        Iterator r1_Iterator = this.callbacks.iterator();
        while (r1_Iterator.hasNext()) {
            this.owner.fail(r4i, r5_String, (CallbackContext) r1_Iterator.next());
        }
        if (this.owner.isGlobalListener(this)) {
            Log.d(this.TAG, "Stopping global listener");
            stop();
        }
        this.callbacks.clear();
        r1_Iterator = this.watches.values().iterator();
        while (r1_Iterator.hasNext()) {
            this.owner.fail(r4i, r5_String, (CallbackContext) r1_Iterator.next());
        }
    }

    public void onLocationChanged(Location r3_Location) {
        Log.d(this.TAG, "The location has been updated!");
        win(r3_Location);
    }

    public void onProviderDisabled(String r4_String) {
        Log.d(this.TAG, "Location provider '" + r4_String + "' disabled.");
        fail(POSITION_UNAVAILABLE, "GPS provider disabled.");
    }

    public void onProviderEnabled(String r4_String) {
        Log.d(this.TAG, "Location provider " + r4_String + " has been enabled");
    }

    public void onStatusChanged(String r4_String, int r5i, Bundle r6_Bundle) {
        Log.d(this.TAG, "The status of the provider " + r4_String + " has changed");
        if (r5i == 0) {
            Log.d(this.TAG, r4_String + " is OUT OF SERVICE");
            fail(POSITION_UNAVAILABLE, "Provider " + r4_String + " is out of service.");
        } else if (r5i == 1) {
            Log.d(this.TAG, r4_String + " is TEMPORARILY_UNAVAILABLE");
        } else {
            Log.d(this.TAG, r4_String + " is AVAILABLE");
        }
    }

    public int size() {
        return this.watches.size() + this.callbacks.size();
    }

    protected void start() {
        if (!this.running) {
            if (this.locationManager.getProvider(LocationManagerProxy.NETWORK_PROVIDER) != null) {
                this.running = true;
                this.locationManager.requestLocationUpdates(LocationManagerProxy.NETWORK_PROVIDER, Util.MILLSECONDS_OF_MINUTE, 10.0f, this);
            } else {
                fail(POSITION_UNAVAILABLE, "Network provider is not available.");
            }
        }
    }
}