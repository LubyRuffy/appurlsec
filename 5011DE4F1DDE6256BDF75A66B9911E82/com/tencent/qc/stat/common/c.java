package com.tencent.qc.stat.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.tencent.qc.stat.StatConfig;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

// compiled from: ProGuard
class c {
    String a;
    String b;
    DisplayMetrics c;
    int d;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;
    int k;

    private c(Context r2_Context) {
        this.b = "0.6.12";
        this.d = VERSION.SDK_INT;
        this.e = Build.MODEL;
        this.f = Build.MANUFACTURER;
        this.g = Locale.getDefault().getLanguage();
        this.k = 0;
        this.c = StatCommonHelper.d(r2_Context);
        this.a = StatCommonHelper.l(r2_Context);
        this.h = StatConfig.b(r2_Context);
        this.i = ((TelephonyManager) r2_Context.getSystemService("phone")).getNetworkOperator();
        this.j = TimeZone.getDefault().getID();
        this.k = StatCommonHelper.q(r2_Context);
    }

    void a(JSONObject r4_JSONObject) {
        r4_JSONObject.put("sr", this.c.widthPixels + "*" + this.c.heightPixels);
        StatCommonHelper.a(r4_JSONObject, "av", this.a);
        StatCommonHelper.a(r4_JSONObject, "ch", this.h);
        StatCommonHelper.a(r4_JSONObject, "mf", this.f);
        StatCommonHelper.a(r4_JSONObject, "sv", this.b);
        StatCommonHelper.a(r4_JSONObject, "ov", Integer.toString(this.d));
        r4_JSONObject.put("os", 1);
        StatCommonHelper.a(r4_JSONObject, "op", this.i);
        StatCommonHelper.a(r4_JSONObject, "lg", this.g);
        StatCommonHelper.a(r4_JSONObject, "md", this.e);
        StatCommonHelper.a(r4_JSONObject, "tz", this.j);
        if (this.k != 0) {
            r4_JSONObject.put("jb", this.k);
        }
        StatCommonHelper.a(r4_JSONObject, "sd", StatCommonHelper.d());
    }
}