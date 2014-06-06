package com.amap.api.location.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.LocationProviderProxy;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.apache.cordova.Globalization;

// compiled from: CoreUtil.java
public class d {
    public static String a;
    public static String b;
    public static String c;
    static int d;

    static {
        a = "V1.1.0";
        b = "AMAP Location SDK Android 1.1.0";
        c = "http://restapi.amap.com";
        d = 2048;
    }

    public static void a(Context r4_Context, AMapLocation r5_AMapLocation) {
        Editor r0_Editor = r4_Context.getSharedPreferences("last_know_location", 0).edit();
        r0_Editor.putString("last_know_lat", String.valueOf(r5_AMapLocation.getLatitude()));
        r0_Editor.putString("last_know_lng", String.valueOf(r5_AMapLocation.getLongitude()));
        r0_Editor.putString("province", r5_AMapLocation.getProvince());
        r0_Editor.putString("city", r5_AMapLocation.getCity());
        r0_Editor.putString("district", r5_AMapLocation.getDistrict());
        r0_Editor.putString("cityCode", r5_AMapLocation.getCityCode());
        r0_Editor.putString("adCode", r5_AMapLocation.getAdCode());
        r0_Editor.putFloat("accuracy", r5_AMapLocation.getAccuracy());
        r0_Editor.putLong(Globalization.TIME, r5_AMapLocation.getTime());
        r0_Editor.commit();
    }

    public static boolean a(Context r3_Context) {
        if (r3_Context == null) {
            return false;
        }
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r3_Context.getSystemService("connectivity");
        if (r0_ConnectivityManager == null) {
            return false;
        }
        NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
        if (r0_NetworkInfo == null) {
            return false;
        }
        State r0_State = r0_NetworkInfo.getState();
        return r0_State != null && r0_State != State.DISCONNECTED && r0_State != State.DISCONNECTING;
    }

    public static AMapLocation b(Context r7_Context) {
        SharedPreferences r0_SharedPreferences = r7_Context.getSharedPreferences("last_know_location", 0);
        AMapLocation r2_AMapLocation = new AMapLocation(RContactStorage.PRIMARY_KEY);
        r2_AMapLocation.setProvider(LocationProviderProxy.AMapNetwork);
        double r3d = Double.parseDouble(r0_SharedPreferences.getString("last_know_lat", "0.0"));
        double r5d = Double.parseDouble(r0_SharedPreferences.getString("last_know_lng", "0.0"));
        r2_AMapLocation.setLatitude(r3d);
        r2_AMapLocation.setLongitude(r5d);
        r2_AMapLocation.setProvince(r0_SharedPreferences.getString("province", RContactStorage.PRIMARY_KEY));
        r2_AMapLocation.setCity(r0_SharedPreferences.getString("city", RContactStorage.PRIMARY_KEY));
        r2_AMapLocation.setDistrict(r0_SharedPreferences.getString("district", RContactStorage.PRIMARY_KEY));
        r2_AMapLocation.setCityCode(r0_SharedPreferences.getString("cityCode", RContactStorage.PRIMARY_KEY));
        r2_AMapLocation.setAdCode(r0_SharedPreferences.getString("adCode", RContactStorage.PRIMARY_KEY));
        r2_AMapLocation.setAccuracy(r0_SharedPreferences.getFloat("accuracy", 0.0f));
        r2_AMapLocation.setTime(r0_SharedPreferences.getLong(Globalization.TIME, 0));
        return r2_AMapLocation;
    }
}