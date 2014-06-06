package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;

public class NetworkManager extends CordovaPlugin {
    public static final String CDMA = "cdma";
    public static final String EDGE = "edge";
    public static final String EHRPD = "ehrpd";
    public static final String GPRS = "gprs";
    public static final String GSM = "gsm";
    public static final String HSDPA = "hsdpa";
    public static final String HSPA = "hspa";
    public static final String HSPA_PLUS = "hspa+";
    public static final String HSUPA = "hsupa";
    private static final String LOG_TAG = "NetworkManager";
    public static final String LTE = "lte";
    public static final String MOBILE = "mobile";
    public static int NOT_REACHABLE = 0;
    public static final String ONEXRTT = "1xrtt";
    public static int REACHABLE_VIA_CARRIER_DATA_NETWORK = 0;
    public static int REACHABLE_VIA_WIFI_NETWORK = 0;
    public static final String TYPE_2G = "2g";
    public static final String TYPE_3G = "3g";
    public static final String TYPE_4G = "4g";
    public static final String TYPE_ETHERNET = "ethernet";
    public static final String TYPE_NONE = "none";
    public static final String TYPE_UNKNOWN = "unknown";
    public static final String TYPE_WIFI = "wifi";
    public static final String UMB = "umb";
    public static final String UMTS = "umts";
    public static final String WIFI = "wifi";
    public static final String WIMAX = "wimax";
    private CallbackContext connectionCallbackContext;
    private String lastStatus;
    BroadcastReceiver receiver;
    private boolean registered;
    ConnectivityManager sockMan;

    static {
        NOT_REACHABLE = 0;
        REACHABLE_VIA_CARRIER_DATA_NETWORK = 1;
        REACHABLE_VIA_WIFI_NETWORK = 2;
    }

    public NetworkManager() {
        this.registered = false;
        this.lastStatus = RContactStorage.PRIMARY_KEY;
        this.receiver = null;
    }

    private String getConnectionInfo(NetworkInfo r5_NetworkInfo) {
        String r0_String = TYPE_NONE;
        if (r5_NetworkInfo != null) {
            r0_String = r5_NetworkInfo.isConnected() ? getType(r5_NetworkInfo) : TYPE_NONE;
        }
        Log.d("CordovaNetworkManager", "Connection Type: " + r0_String);
        return r0_String;
    }

    private String getType(NetworkInfo r4_NetworkInfo) {
        if (r4_NetworkInfo == null) {
            return TYPE_NONE;
        }
        String r0_String = r4_NetworkInfo.getTypeName();
        if (r0_String.toLowerCase().equals(WIFI)) {
            return WIFI;
        }
        if (r0_String.toLowerCase().equals(MOBILE)) {
            r0_String = r4_NetworkInfo.getSubtypeName();
            if (r0_String.toLowerCase().equals(GSM) || r0_String.toLowerCase().equals(GPRS) || r0_String.toLowerCase().equals(EDGE)) {
                return TYPE_2G;
            }
            if (r0_String.toLowerCase().startsWith(CDMA) || r0_String.toLowerCase().equals(UMTS) || r0_String.toLowerCase().equals(ONEXRTT) || r0_String.toLowerCase().equals(EHRPD) || r0_String.toLowerCase().equals(HSUPA) || r0_String.toLowerCase().equals(HSDPA) || r0_String.toLowerCase().equals(HSPA)) {
                return TYPE_3G;
            }
            if (r0_String.toLowerCase().equals(LTE) || r0_String.toLowerCase().equals(UMB) || r0_String.toLowerCase().equals(HSPA_PLUS)) {
                return TYPE_4G;
            }
        }
        return TYPE_UNKNOWN;
    }

    private void sendUpdate(String r3_String) {
        if (this.connectionCallbackContext != null) {
            PluginResult r0_PluginResult = new PluginResult(Status.OK, r3_String);
            r0_PluginResult.setKeepCallback(true);
            this.connectionCallbackContext.sendPluginResult(r0_PluginResult);
        }
        this.webView.postMessage("networkconnection", r3_String);
    }

    private void updateConnectionInfo(NetworkInfo r3_NetworkInfo) {
        String r0_String = getConnectionInfo(r3_NetworkInfo);
        if (!r0_String.equals(this.lastStatus)) {
            sendUpdate(r0_String);
            this.lastStatus = r0_String;
        }
    }

    public boolean execute(String r5_String, JSONArray r6_JSONArray, CallbackContext r7_CallbackContext) {
        if (!r5_String.equals("getConnectionInfo")) {
            return false;
        }
        this.connectionCallbackContext = r7_CallbackContext;
        PluginResult r2_PluginResult = new PluginResult(Status.OK, getConnectionInfo(this.sockMan.getActiveNetworkInfo()));
        r2_PluginResult.setKeepCallback(true);
        r7_CallbackContext.sendPluginResult(r2_PluginResult);
        return true;
    }

    public void initialize(CordovaInterface r4_CordovaInterface, CordovaWebView r5_CordovaWebView) {
        super.initialize(r4_CordovaInterface, r5_CordovaWebView);
        this.sockMan = (ConnectivityManager) r4_CordovaInterface.getActivity().getSystemService("connectivity");
        this.connectionCallbackContext = null;
        IntentFilter r0_IntentFilter = new IntentFilter();
        r0_IntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (this.receiver == null) {
            this.receiver = new BroadcastReceiver() {
                public void onReceive(Context r3_Context, Intent r4_Intent) {
                    if (NetworkManager.this.webView != null) {
                        NetworkManager.this.updateConnectionInfo(NetworkManager.this.sockMan.getActiveNetworkInfo());
                    }
                }
            };
            r4_CordovaInterface.getActivity().registerReceiver(this.receiver, r0_IntentFilter);
            this.registered = true;
        }
    }

    public void onDestroy() {
        if (this.receiver == null || (!this.registered)) {
        } else {
            try {
                this.cordova.getActivity().unregisterReceiver(this.receiver);
                this.registered = false;
            } catch (Exception e) {
                Throwable r0_Throwable = e;
                Log.e(LOG_TAG, "Error unregistering network receiver: " + r0_Throwable.getMessage(), r0_Throwable);
            }
        }
    }
}