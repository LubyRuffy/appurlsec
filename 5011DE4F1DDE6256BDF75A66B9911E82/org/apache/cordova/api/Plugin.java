package org.apache.cordova.api;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Plugin extends CordovaPlugin {
    public LegacyContext ctx;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ String val$action;
        final /* synthetic */ JSONArray val$args;
        final /* synthetic */ String val$callbackId;

        AnonymousClass_1(String r2_String, JSONArray r3_JSONArray, String r4_String) {
            this.val$action = r2_String;
            this.val$args = r3_JSONArray;
            this.val$callbackId = r4_String;
        }

        public void run() {
            PluginResult r0_PluginResult;
            try {
                r0_PluginResult = Plugin.this.execute(this.val$action, this.val$args, this.val$callbackId);
            } catch (Throwable th) {
                r0_PluginResult = new PluginResult(Status.ERROR, th.getMessage());
            }
            Plugin.this.sendPluginResult(r0_PluginResult, this.val$callbackId);
        }
    }

    public void error(String r4_String, String r5_String) {
        this.webView.sendPluginResult(new PluginResult(Status.ERROR, r4_String), r5_String);
    }

    public void error(PluginResult r2_PluginResult, String r3_String) {
        this.webView.sendPluginResult(r2_PluginResult, r3_String);
    }

    public void error(JSONObject r4_JSONObject, String r5_String) {
        this.webView.sendPluginResult(new PluginResult(Status.ERROR, r4_JSONObject), r5_String);
    }

    public abstract PluginResult execute(String r1_String, JSONArray r2_JSONArray, String r3_String);

    public boolean execute(String r5_String, JSONArray r6_JSONArray, CallbackContext r7_CallbackContext) throws JSONException {
        String r2_String = r7_CallbackContext.getCallbackId();
        if ((isSynch(r5_String) ? 0 : 1) != 0) {
            this.cordova.getThreadPool().execute(new AnonymousClass_1(r5_String, r6_JSONArray, r2_String));
        } else {
            PluginResult r0_PluginResult = execute(r5_String, r6_JSONArray, r2_String);
            if (r0_PluginResult == null) {
                r0_PluginResult = new PluginResult(Status.NO_RESULT);
            }
            r7_CallbackContext.sendPluginResult(r0_PluginResult);
        }
        return true;
    }

    public void initialize(CordovaInterface r1_CordovaInterface, CordovaWebView r2_CordovaWebView) {
        super.initialize(r1_CordovaInterface, r2_CordovaWebView);
        setContext(r1_CordovaInterface);
        setView(r2_CordovaWebView);
    }

    public boolean isSynch(String r2_String) {
        return false;
    }

    public void sendJavascript(String r2_String) {
        this.webView.sendJavascript(r2_String);
    }

    public void sendPluginResult(PluginResult r2_PluginResult, String r3_String) {
        this.webView.sendPluginResult(r2_PluginResult, r3_String);
    }

    public void setContext(CordovaInterface r3_CordovaInterface) {
        this.cordova = r3_CordovaInterface;
        this.ctx = new LegacyContext(this.cordova);
    }

    public void setView(CordovaWebView r1_CordovaWebView) {
        this.webView = r1_CordovaWebView;
    }

    public void success(String r4_String, String r5_String) {
        this.webView.sendPluginResult(new PluginResult(Status.OK, r4_String), r5_String);
    }

    public void success(PluginResult r2_PluginResult, String r3_String) {
        this.webView.sendPluginResult(r2_PluginResult, r3_String);
    }

    public void success(JSONObject r4_JSONObject, String r5_String) {
        this.webView.sendPluginResult(new PluginResult(Status.OK, r4_JSONObject), r5_String);
    }
}