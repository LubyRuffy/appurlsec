package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.tauth.Constants;
import java.util.TimeZone;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

public class Device extends CordovaPlugin {
    public static final String TAG = "Device";
    public static String cordovaVersion;
    public static String platform;
    public static String uuid;
    BroadcastReceiver telephonyReceiver;

    static {
        cordovaVersion = "2.5.0";
        platform = "Android";
    }

    public Device() {
        this.telephonyReceiver = null;
    }

    private void initTelephonyReceiver() {
        IntentFilter r0_IntentFilter = new IntentFilter();
        r0_IntentFilter.addAction("android.intent.action.PHONE_STATE");
        this.telephonyReceiver = new BroadcastReceiver() {
            public void onReceive(Context r4_Context, Intent r5_Intent) {
                if (r5_Intent != null && r5_Intent.getAction().equals("android.intent.action.PHONE_STATE") && r5_Intent.hasExtra(QsbkDatabase.STATE)) {
                    String r0_String = r5_Intent.getStringExtra(QsbkDatabase.STATE);
                    if (r0_String.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        LOG.i(TAG, "Telephone RINGING");
                        Device.this.webView.postMessage("telephone", "ringing");
                    } else if (r0_String.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                        LOG.i(TAG, "Telephone OFFHOOK");
                        Device.this.webView.postMessage("telephone", "offhook");
                    } else {
                        if (r0_String.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                            LOG.i(TAG, "Telephone IDLE");
                            Device.this.webView.postMessage("telephone", "idle");
                        }
                    }
                }
            }
        };
        this.cordova.getActivity().registerReceiver(this.telephonyReceiver, r0_IntentFilter);
    }

    public boolean execute(String r4_String, JSONArray r5_JSONArray, CallbackContext r6_CallbackContext) throws JSONException {
        if (!r4_String.equals("getDeviceInfo")) {
            return false;
        }
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject.put("uuid", uuid);
        r0_JSONObject.put("version", getOSVersion());
        r0_JSONObject.put(Constants.PARAM_PLATFORM, platform);
        r0_JSONObject.put("name", getProductName());
        r0_JSONObject.put("cordova", cordovaVersion);
        r0_JSONObject.put("model", getModel());
        r6_CallbackContext.success(r0_JSONObject);
        return true;
    }

    public String getCordovaVersion() {
        return cordovaVersion;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getOSVersion() {
        return VERSION.RELEASE;
    }

    public String getPlatform() {
        return platform;
    }

    public String getProductName() {
        return Build.PRODUCT;
    }

    public String getSDKVersion() {
        return VERSION.SDK;
    }

    public String getTimeZoneID() {
        return TimeZone.getDefault().getID();
    }

    public String getUuid() {
        return Secure.getString(this.cordova.getActivity().getContentResolver(), "android_id");
    }

    public void initialize(CordovaInterface r2_CordovaInterface, CordovaWebView r3_CordovaWebView) {
        super.initialize(r2_CordovaInterface, r3_CordovaWebView);
        uuid = getUuid();
        initTelephonyReceiver();
    }

    public void onDestroy() {
        this.cordova.getActivity().unregisterReceiver(this.telephonyReceiver);
    }
}