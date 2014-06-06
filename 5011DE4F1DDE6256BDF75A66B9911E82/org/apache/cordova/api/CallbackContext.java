package org.apache.cordova.api;

import android.util.Log;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONObject;

public class CallbackContext {
    private static final String LOG_TAG = "CordovaPlugin";
    private String callbackId;
    private int changingThreads;
    private boolean finished;
    private CordovaWebView webView;

    public CallbackContext(String r1_String, CordovaWebView r2_CordovaWebView) {
        this.callbackId = r1_String;
        this.webView = r2_CordovaWebView;
    }

    public void error(int r3i) {
        sendPluginResult(new PluginResult(Status.ERROR, r3i));
    }

    public void error(String r3_String) {
        sendPluginResult(new PluginResult(Status.ERROR, r3_String));
    }

    public void error(JSONObject r3_JSONObject) {
        sendPluginResult(new PluginResult(Status.ERROR, r3_JSONObject));
    }

    public String getCallbackId() {
        return this.callbackId;
    }

    public boolean isChangingThreads() {
        return this.changingThreads > 0;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void sendPluginResult(PluginResult r4_PluginResult) {
        synchronized (this) {
            if (this.finished) {
                Log.w(LOG_TAG, "Attempted to send a second callback for ID: " + this.callbackId + "\nResult was: " + r4_PluginResult.getMessage());
            } else {
                this.finished = !r4_PluginResult.getKeepCallback();
                this.webView.sendPluginResult(r4_PluginResult, this.callbackId);
            }
        }
    }

    public void success() {
        sendPluginResult(new PluginResult(Status.OK));
    }

    public void success(String r3_String) {
        sendPluginResult(new PluginResult(Status.OK, r3_String));
    }

    public void success(JSONArray r3_JSONArray) {
        sendPluginResult(new PluginResult(Status.OK, r3_JSONArray));
    }

    public void success(JSONObject r3_JSONObject) {
        sendPluginResult(new PluginResult(Status.OK, r3_JSONObject));
    }

    public void success(byte[] r3_byteA) {
        sendPluginResult(new PluginResult(Status.OK, r3_byteA));
    }
}