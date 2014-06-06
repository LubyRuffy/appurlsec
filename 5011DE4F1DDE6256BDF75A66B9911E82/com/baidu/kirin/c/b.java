package com.baidu.kirin.c;

import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import com.baidu.kirin.KirinConfig;
import com.baidu.kirin.a.a;
import com.baidu.kirin.d.c;
import com.baidu.kirin.d.d;
import com.baidu.kirin.d.f;
import com.baidu.mobstat.BasicStoreTools;
import com.baidu.mobstat.CooperService;
import com.google.analytics.tracking.android.ModelFields;
import com.tencent.tauth.Constants;
import java.io.IOException;
import java.util.Locale;
import org.apache.cordova.Globalization;
import org.apache.cordova.NetworkManager;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.nearby.ui.NearByListActivity;

public class b extends a {
    private String g;
    private JSONObject h;
    private JSONObject i;
    private JSONObject j;
    private boolean k;

    public b(Context r2_Context, String r3_String) {
        super(r2_Context, r3_String);
        this.c = getClass().getName();
    }

    private JSONObject a(Context r7_Context) {
        WifiManager r0_WifiManager = (WifiManager) r7_Context.getSystemService(NetworkManager.WIFI);
        LocationManager r1_LocationManager = (LocationManager) r7_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        JSONObject r3_JSONObject = new JSONObject();
        try {
            r3_JSONObject.put("appkey", a.b(r7_Context));
            r3_JSONObject.put("channel", a.a(this.a));
            r3_JSONObject.put("os_version", a.e(r7_Context));
            r3_JSONObject.put("manufacturer", a.g(r7_Context));
            r3_JSONObject.put("phone_type", a.i(r7_Context));
            r3_JSONObject.put("deviceid", a.h(r7_Context));
            r3_JSONObject.put("imei", a.k(r7_Context));
            r3_JSONObject.put("resolution", a.m(r7_Context));
            r3_JSONObject.put(Constants.PARAM_PLATFORM, "android");
            r3_JSONObject.put("is_mobile_device", true);
            r3_JSONObject.put(ModelFields.LANGUAGE, Locale.getDefault().getLanguage());
            r3_JSONObject.put("modulename", CooperService.getPhoneModel());
            r3_JSONObject.put("wifimac", r0_WifiManager.getConnectionInfo().getMacAddress());
            r3_JSONObject.put("havegps", r1_LocationManager != null);
            r3_JSONObject.put("os_sdk", a.f(r7_Context));
            r3_JSONObject.put(BasicStoreTools.DEVICE_CUID, a.j(r7_Context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        d.a("Satic Data : " + r3_JSONObject.toString());
        return r3_JSONObject;
    }

    private JSONObject b_(Context r4_Context) {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put(Globalization.TIME, a.a());
            r1_JSONObject.put("version_name", a.c(r4_Context));
            r1_JSONObject.put("version_code", a.d(r4_Context));
            r1_JSONObject.put("network_type", a.l(r4_Context));
            r1_JSONObject.put("latlongitude", a.a(r4_Context, CooperService.checkGPSLocationSetting(r4_Context)));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e_2) {
            e_2.printStackTrace();
        }
        d.a("Dyna Data : " + r1_JSONObject.toString());
        return r1_JSONObject;
    }

    private boolean g() {
        this.g = KirinConfig.CLIENT_STATIC_DATA_FILE + this.a.getPackageName();
        this.i = com.baidu.kirin.d.b.a(this.a, this.g);
        if (this.i == null) {
            d.a("Static file is empty, need collect static data!");
            this.h = a(this.a);
            return true;
        } else {
            this.h = a(this.a);
            return !f.a(c.a(this.i.toString())).equals(f.a(c.a(this.h.toString())));
        }
    }

    protected void b_() {
        this.k = g();
        try {
            this.j = b(this.a);
            this.d = c.a(this.d, this.h);
            this.d = c.a(this.d, this.j);
            if (this.k) {
                d.a("send new static data!");
                this.d.put("isUpdateClientData", "1");
            } else {
                d.a("send cache static data!");
                this.d.put("isUpdateClientData", "0");
            }
        } catch (Exception e) {
            Exception r0_Exception = e;
            d.a("what's going on?? : " + r0_Exception.toString());
            r0_Exception.printStackTrace();
        }
    }

    protected void e() {
        d.a("isInfoChanged : " + this.k + "  dump static data after success!!");
        if (this.k) {
            try {
                com.baidu.kirin.d.b.a(this.a, this.g, this.h);
            } catch (IOException e) {
                d.c("Dump static file has exception!!");
                e.printStackTrace();
            }
        }
        try {
            com.baidu.kirin.b.a.a(this.a).a(true, new JSONObject(this.e.getString("updateConfig")));
            com.baidu.kirin.b.a.a(this.a).b(this.e.getString("logID"));
        } catch (JSONException e_2) {
            e_2.printStackTrace();
            com.baidu.kirin.b.a.a(this.a).a(false, null);
            com.baidu.kirin.b.a.a(this.a).b("0");
        }
    }

    protected void f() {
        if (a.o(this.a)) {
            com.baidu.kirin.b.a.a(this.a).a(false, null);
        }
    }
}