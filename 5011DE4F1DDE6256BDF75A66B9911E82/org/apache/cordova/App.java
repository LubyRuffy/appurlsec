package org.apache.cordova;

import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.LOG;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class App extends CordovaPlugin {
    public void backHistory() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                App.this.webView.backHistory();
            }
        });
    }

    public void clearCache() {
        this.webView.clearCache(true);
    }

    public void clearHistory() {
        this.webView.clearHistory();
    }

    public boolean execute(String r7_String, JSONArray r8_JSONArray, CallbackContext r9_CallbackContext) throws JSONException {
        boolean r0z = true;
        Status r2_Status = Status.OK;
        String r3_String = RContactStorage.PRIMARY_KEY;
        try {
            if (r7_String.equals("clearCache")) {
                clearCache();
            } else if (r7_String.equals(AdViewUtil.COUNTSHOW)) {
                this.cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        App.this.webView.postMessage("spinner", "stop");
                    }
                });
            } else if (r7_String.equals("loadUrl")) {
                loadUrl(r8_JSONArray.getString(0), r8_JSONArray.optJSONObject(1));
            } else if (!r7_String.equals("cancelLoadUrl")) {
                if (r7_String.equals("clearHistory")) {
                    clearHistory();
                } else if (r7_String.equals("backHistory")) {
                    backHistory();
                } else if (r7_String.equals("overrideButton")) {
                    overrideButton(r8_JSONArray.getString(0), r8_JSONArray.getBoolean(1));
                } else if (r7_String.equals("overrideBackbutton")) {
                    overrideBackbutton(r8_JSONArray.getBoolean(0));
                } else if (r7_String.equals("exitApp")) {
                    exitApp();
                }
            }
            r9_CallbackContext.sendPluginResult(new PluginResult(r2_Status, r3_String));
        } catch (JSONException e) {
            r9_CallbackContext.sendPluginResult(new PluginResult(Status.JSON_EXCEPTION));
            r0z = false;
        }
        return r0z;
    }

    public void exitApp() {
        this.webView.postMessage("exit", null);
    }

    public boolean isBackbuttonOverridden() {
        return this.webView.isBackButtonBound();
    }

    public void loadUrl(String r11_String, JSONObject r12_JSONObject) throws JSONException {
        boolean r2z;
        boolean r3z;
        int r4i;
        LOG.d("App", "App.loadUrl(" + r11_String + "," + r12_JSONObject + ")");
        HashMap r5_HashMap = new HashMap();
        if (r12_JSONObject != null) {
            JSONArray r6_JSONArray = r12_JSONObject.names();
            int r1i = 0;
            r2z = false;
            r3z = false;
            r4i = 0;
            while (r1i < r6_JSONArray.length()) {
                String r7_String = r6_JSONArray.getString(r1i);
                if (r7_String.equals("wait")) {
                    r4i = r12_JSONObject.getInt(r7_String);
                } else if (r7_String.equalsIgnoreCase("openexternal")) {
                    r3z = r12_JSONObject.getBoolean(r7_String);
                } else if (r7_String.equalsIgnoreCase("clearhistory")) {
                    r2z = r12_JSONObject.getBoolean(r7_String);
                } else {
                    Object r0_Object = r12_JSONObject.get(r7_String);
                    if (r0_Object != null) {
                        if (r0_Object.getClass().equals(String.class)) {
                            r5_HashMap.put(r7_String, (String) r0_Object);
                        } else if (r0_Object.getClass().equals(Boolean.class)) {
                            r5_HashMap.put(r7_String, (Boolean) r0_Object);
                        } else if (r0_Object.getClass().equals(Integer.class)) {
                            r5_HashMap.put(r7_String, (Integer) r0_Object);
                        }
                    }
                }
                r1i++;
            }
        } else {
            r2z = false;
            r3z = false;
            r4i = 0;
        }
        if (r4i > 0) {
            try {
                synchronized (this) {
                    wait((long) r4i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.webView.showWebPage(r11_String, r3z, r2z, r5_HashMap);
    }

    public void overrideBackbutton(boolean r3z) {
        LOG.i("App", "WARNING: Back Button Default Behaviour will be overridden.  The backbutton event will be fired!");
        this.webView.bindButton(r3z);
    }

    public void overrideButton(String r3_String, boolean r4z) {
        LOG.i("DroidGap", "WARNING: Volume Button Default Behaviour will be overridden.  The volume event will be fired!");
        this.webView.bindButton(r3_String, r4z);
    }
}