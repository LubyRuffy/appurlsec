package org.apache.cordova;

import com.qiubai.library.adview.util.AdViewUtil;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;

public class SplashScreen extends CordovaPlugin {
    public boolean execute(String r4_String, JSONArray r5_JSONArray, CallbackContext r6_CallbackContext) {
        if (r4_String.equals("hide")) {
            this.webView.postMessage("splashscreen", "hide");
        } else {
            if (!r4_String.equals(AdViewUtil.COUNTSHOW)) {
                return false;
            }
            this.webView.postMessage("splashscreen", AdViewUtil.COUNTSHOW);
        }
        r6_CallbackContext.success();
        return true;
    }
}