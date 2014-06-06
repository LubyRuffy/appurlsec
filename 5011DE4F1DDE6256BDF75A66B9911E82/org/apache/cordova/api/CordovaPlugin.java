package org.apache.cordova.api;

import android.content.Intent;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaPlugin {
    static final /* synthetic */ boolean $assertionsDisabled;
    public CordovaInterface cordova;
    public String id;
    public CordovaWebView webView;

    static {
        $assertionsDisabled = !CordovaPlugin.class.desiredAssertionStatus();
    }

    public boolean execute(String r2_String, String r3_String, CallbackContext r4_CallbackContext) throws JSONException {
        return execute(r2_String, new JSONArray(r3_String), r4_CallbackContext);
    }

    public boolean execute(String r2_String, CordovaArgs r3_CordovaArgs, CallbackContext r4_CallbackContext) throws JSONException {
        return false;
    }

    public boolean execute(String r2_String, JSONArray r3_JSONArray, CallbackContext r4_CallbackContext) throws JSONException {
        return execute(r2_String, new CordovaArgs(r3_JSONArray), r4_CallbackContext);
    }

    public void initialize(CordovaInterface r2_CordovaInterface, CordovaWebView r3_CordovaWebView) {
        if ($assertionsDisabled || this.cordova == null) {
            this.cordova = r2_CordovaInterface;
            this.webView = r3_CordovaWebView;
        } else {
            throw new AssertionError();
        }
    }

    public void onActivityResult(int r1i, int r2i, Intent r3_Intent) {
    }

    public void onDestroy() {
    }

    public Object onMessage(String r2_String, Object r3_Object) {
        return null;
    }

    public void onNewIntent(Intent r1_Intent) {
    }

    public boolean onOverrideUrlLoading(String r2_String) {
        return false;
    }

    public void onPause(boolean r1z) {
    }

    public void onReset() {
    }

    public void onResume(boolean r1z) {
    }
}