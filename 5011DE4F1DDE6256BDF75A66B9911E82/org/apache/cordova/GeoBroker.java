package org.apache.cordova;

import android.location.Location;
import android.location.LocationManager;
import com.amap.api.location.LocationManagerProxy;
import com.qiubai.library.adview.util.AdViewUtil;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.push.Utils;

public class GeoBroker extends CordovaPlugin {
    private GPSListener gpsListener;
    private LocationManager locationManager;
    private NetworkListener networkListener;

    private void addWatch(String r2_String, CallbackContext r3_CallbackContext, boolean r4z) {
        if (r4z) {
            this.gpsListener.addWatch(r2_String, r3_CallbackContext);
        } else {
            this.networkListener.addWatch(r2_String, r3_CallbackContext);
        }
    }

    private void clearWatch(String r2_String) {
        this.gpsListener.clearWatch(r2_String);
        this.networkListener.clearWatch(r2_String);
    }

    private void getCurrentLocation(CallbackContext r2_CallbackContext, boolean r3z) {
        if (r3z) {
            this.gpsListener.addCallback(r2_CallbackContext);
        } else {
            this.networkListener.addCallback(r2_CallbackContext);
        }
    }

    public boolean execute(String r9_String, JSONArray r10_JSONArray, CallbackContext r11_CallbackContext) throws JSONException {
        int r1i = 0;
        if (this.locationManager == null) {
            this.locationManager = (LocationManager) this.cordova.getActivity().getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
            this.networkListener = new NetworkListener(this.locationManager, this);
            this.gpsListener = new GPSListener(this.locationManager, this);
        }
        if (this.locationManager.isProviderEnabled(LocationManagerProxy.GPS_PROVIDER) || this.locationManager.isProviderEnabled(LocationManagerProxy.NETWORK_PROVIDER)) {
            if (r9_String.equals("getLocation")) {
                boolean r1z = r10_JSONArray.getBoolean(r1i);
                int r3i = r10_JSONArray.getInt(1);
                Location r0_Location = this.locationManager.getLastKnownLocation(r1z ? LocationManagerProxy.GPS_PROVIDER : LocationManagerProxy.NETWORK_PROVIDER);
                if (r0_Location == null || System.currentTimeMillis() - r0_Location.getTime() > ((long) r3i)) {
                    getCurrentLocation(r11_CallbackContext, r1z);
                } else {
                    r11_CallbackContext.sendPluginResult(new PluginResult(Status.OK, returnLocationJSON(r0_Location)));
                }
            } else if (r9_String.equals("addWatch")) {
                addWatch(r10_JSONArray.getString(r1i), r11_CallbackContext, r10_JSONArray.getBoolean(1));
            } else {
                if (!r9_String.equals("clearWatch")) {
                    return false;
                }
                clearWatch(r10_JSONArray.getString(0));
            }
        } else {
            r11_CallbackContext.sendPluginResult(new PluginResult(Status.NO_RESULT, "Location API is not available for this device."));
        }
        return true;
    }

    public void fail(int r5i, String r6_String, CallbackContext r7_CallbackContext) {
        JSONObject r2_JSONObject;
        String r1_String;
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("code", r5i);
            r1_JSONObject.put(Utils.EXTRA_MESSAGE, r6_String);
            r2_JSONObject = r1_JSONObject;
            r1_String = null;
        } catch (JSONException e) {
            r1_String = "{'code':" + r5i + ",'message':'" + r6_String.replaceAll("'", "'") + "'}";
            r2_JSONObject = null;
        }
        r7_CallbackContext.sendPluginResult(r2_JSONObject != null ? new PluginResult(Status.ERROR, r2_JSONObject) : new PluginResult(Status.ERROR, r1_String));
    }

    public boolean isGlobalListener(CordovaLocationListener r3_CordovaLocationListener) {
        if (this.gpsListener == null || this.networkListener == null) {
            return false;
        }
        if (this.gpsListener.equals(r3_CordovaLocationListener) || this.networkListener.equals(r3_CordovaLocationListener)) {
            return true;
        }
        return false;
    }

    public void onDestroy() {
        if (this.networkListener != null) {
            this.networkListener.destroy();
            this.networkListener = null;
        }
        if (this.gpsListener != null) {
            this.gpsListener.destroy();
            this.gpsListener = null;
        }
    }

    public void onReset() {
        onDestroy();
    }

    public JSONObject returnLocationJSON(Location r7_Location) {
        Float r0_Float = null;
        JSONObject r2_JSONObject = new JSONObject();
        try {
            r2_JSONObject.put("latitude", r7_Location.getLatitude());
            r2_JSONObject.put("longitude", r7_Location.getLongitude());
            r2_JSONObject.put("altitude", r7_Location.hasAltitude() ? Double.valueOf(r7_Location.getAltitude()) : null);
            r2_JSONObject.put("accuracy", (double) r7_Location.getAccuracy());
            String r1_String = "heading";
            if (r7_Location.hasBearing() && r7_Location.hasSpeed()) {
                r0_Float = Float.valueOf(r7_Location.getBearing());
                r2_JSONObject.put(r1_String, r0_Float);
                r2_JSONObject.put("speed", (double) r7_Location.getSpeed());
                r2_JSONObject.put(AdViewUtil.PREFS_STRING_TIMESTAMP, r7_Location.getTime());
            } else {
                r2_JSONObject.put(r1_String, r0_Float);
                r2_JSONObject.put("speed", (double) r7_Location.getSpeed());
                r2_JSONObject.put(AdViewUtil.PREFS_STRING_TIMESTAMP, r7_Location.getTime());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r2_JSONObject;
    }

    public void win(Location r4_Location, CallbackContext r5_CallbackContext) {
        r5_CallbackContext.sendPluginResult(new PluginResult(Status.OK, returnLocationJSON(r4_Location)));
    }
}