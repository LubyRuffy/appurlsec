package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BatteryListener extends CordovaPlugin {
    private static final String LOG_TAG = "BatteryManager";
    private CallbackContext batteryCallbackContext;
    BroadcastReceiver receiver;

    public BatteryListener() {
        this.batteryCallbackContext = null;
        this.receiver = null;
    }

    private JSONObject getBatteryInfo(Intent r6_Intent) {
        boolean r0z = false;
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("level", r6_Intent.getIntExtra("level", 0));
            String r2_String = "isPlugged";
            if (r6_Intent.getIntExtra("plugged", -1) > 0) {
                r0z = true;
            }
            r1_JSONObject.put(r2_String, r0z);
        } catch (JSONException e) {
            Throwable r0_Throwable = e;
            Log.e(LOG_TAG, r0_Throwable.getMessage(), r0_Throwable);
        }
        return r1_JSONObject;
    }

    private void removeBatteryListener() {
        if (this.receiver != null) {
            try {
                this.cordova.getActivity().unregisterReceiver(this.receiver);
                this.receiver = null;
            } catch (Exception e) {
                Throwable r0_Throwable = e;
                Log.e(LOG_TAG, "Error unregistering battery receiver: " + r0_Throwable.getMessage(), r0_Throwable);
            }
        }
    }

    private void sendUpdate(JSONObject r3_JSONObject, boolean r4z) {
        if (this.batteryCallbackContext != null) {
            PluginResult r0_PluginResult = new PluginResult(Status.OK, r3_JSONObject);
            r0_PluginResult.setKeepCallback(r4z);
            this.batteryCallbackContext.sendPluginResult(r0_PluginResult);
        }
    }

    private void updateBatteryInfo(Intent r3_Intent) {
        sendUpdate(getBatteryInfo(r3_Intent), true);
    }

    public boolean execute(String r5_String, JSONArray r6_JSONArray, CallbackContext r7_CallbackContext) {
        boolean r1z = false;
        if (r5_String.equals("start")) {
            if (this.batteryCallbackContext != null) {
                r7_CallbackContext.error("Battery listener already running.");
                return true;
            } else {
                this.batteryCallbackContext = r7_CallbackContext;
                IntentFilter r1_IntentFilter = new IntentFilter();
                r1_IntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
                if (this.receiver == null) {
                    this.receiver = new BroadcastReceiver() {
                        public void onReceive(Context r2_Context, Intent r3_Intent) {
                            BatteryListener.this.updateBatteryInfo(r3_Intent);
                        }
                    };
                    this.cordova.getActivity().registerReceiver(this.receiver, r1_IntentFilter);
                }
                PluginResult r1_PluginResult = new PluginResult(Status.NO_RESULT);
                r1_PluginResult.setKeepCallback(true);
                r7_CallbackContext.sendPluginResult(r1_PluginResult);
                return true;
            }
        } else {
            if (!r5_String.equals("stop")) {
                return false;
            }
            removeBatteryListener();
            sendUpdate(new JSONObject(), r1z);
            this.batteryCallbackContext = null;
            r7_CallbackContext.success();
            return true;
        }
    }

    public void onDestroy() {
        removeBatteryListener();
    }

    public void onReset() {
        removeBatteryListener();
    }
}