package com.tencent.qc.stat;

import android.content.Context;
import com.baidu.location.LocationClientOption;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.qc.stat.common.StatCommonHelper;
import com.tencent.qc.stat.common.StatLogger;
import com.tencent.qc.stat.common.StatPreferences;
import java.util.Iterator;
import org.apache.http.HttpHost;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class StatConfig {
    static r a;
    static r b;
    static String c;
    static String d;
    public static boolean e;
    private static StatLogger f;
    private static StatReportStrategy g;
    private static boolean h;
    private static int i;
    private static int j;
    private static int k;
    private static int l;
    private static int m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static int r;
    private static int s;
    private static boolean t;
    private static long u;
    private static long v;

    static {
        f = StatCommonHelper.b();
        a = new r(2);
        b = new r(1);
        g = StatReportStrategy.f;
        h = true;
        i = 30000;
        j = 1024;
        k = 30;
        l = 3;
        m = 30;
        c = "__HIBERNATE__";
        n = null;
        o = null;
        d = RContactStorage.PRIMARY_KEY;
        r = 1440;
        s = 1024;
        t = true;
        u = 0;
        v = 300000;
        e = true;
    }

    public static StatReportStrategy a() {
        return g;
    }

    public static String a(Context r2_Context) {
        if (p != null) {
            return p;
        }
        p = StatCommonHelper.i(r2_Context);
        if (p != null && p.trim().length() != 0) {
            return p;
        }
        f.e("AppKey can not be null or empty, please read Developer's Guide first!");
        return p;
    }

    public static void a(int r2i) {
        if (a(r2i, LocationClientOption.MIN_SCAN_SPAN, 86400000)) {
            i = r2i;
        } else {
            f.e("setSessionTimoutMillis can not exceed the range of [1000, 24 * 60 * 60 * 1000].");
        }
    }

    public static void a(StatReportStrategy r3_StatReportStrategy) {
        g = r3_StatReportStrategy;
        f.h("Change to SendStrategy:" + r3_StatReportStrategy.name());
    }

    static void a(r r2_r) {
        if (r2_r.a == b.a) {
            b = r2_r;
            b(b.b);
        } else {
            if (r2_r.a == a.a) {
                a = r2_r;
            }
        }
    }

    static void a(r r3_r, JSONObject r4_JSONObject) {
        try {
            Iterator r1_Iterator = r4_JSONObject.keys();
            while (r1_Iterator.hasNext()) {
                String r0_String = (String) r1_Iterator.next();
                if (r0_String.equalsIgnoreCase("v")) {
                    r3_r.d = r4_JSONObject.getInt(r0_String);
                } else if (r0_String.equalsIgnoreCase(KEYS.CTXTSETTING)) {
                    r0_String = r4_JSONObject.getString(KEYS.CTXTSETTING);
                    if (r0_String.length() > 0) {
                        r3_r.b = new JSONObject(r0_String);
                    }
                } else if (r0_String.equalsIgnoreCase("m")) {
                    r3_r.c = r4_JSONObject.getString("m");
                }
            }
            StatStore r0_StatStore = StatStore.a(l.a());
            if (r0_StatStore != null) {
                r0_StatStore.a(r3_r);
            }
            if (r3_r.a == b.a) {
                b(r3_r.b);
                c(r3_r.b);
            }
        } catch (JSONException e) {
            f.b(e);
        }
    }

    public static void a(String r2_String) {
        if (r2_String.length() > 256) {
            f.e("The length of appkey cann't exceed 256 bytes.");
        } else {
            p = r2_String;
        }
    }

    static void a(JSONObject r5_JSONObject) {
        try {
            Iterator r1_Iterator = r5_JSONObject.keys();
            while (r1_Iterator.hasNext()) {
                String r0_String = (String) r1_Iterator.next();
                if (r0_String.equalsIgnoreCase(Integer.toString(b.a))) {
                    a(b, r5_JSONObject.getJSONObject(r0_String));
                } else if (r0_String.equalsIgnoreCase(Integer.toString(a.a))) {
                    a(a, r5_JSONObject.getJSONObject(r0_String));
                } else if (r0_String.equalsIgnoreCase("rs")) {
                    StatReportStrategy r0_StatReportStrategy = StatReportStrategy.a(r5_JSONObject.getInt(r0_String));
                    if (r0_StatReportStrategy != null) {
                        g = r0_StatReportStrategy;
                        f.h("Change to ReportStrategy:" + r0_StatReportStrategy.name());
                    }
                } else {
                    return;
                }
            }
        } catch (JSONException e) {
            f.b(e);
        }
    }

    public static void a(boolean r2z) {
        h = r2z;
        if (!r2z) {
            f.c("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    static boolean a(int r1i, int r2i, int r3i) {
        return r1i >= r2i && r1i <= r3i;
    }

    public static String b(Context r2_Context) {
        if (q != null) {
            return q;
        }
        q = StatCommonHelper.j(r2_Context);
        if (q != null && q.trim().length() != 0) {
            return q;
        }
        f.e("installChannel can not be null or empty, please read Developer's Guide first!");
        return q;
    }

    public static void b(int r2i) {
        if (a(r2i, 1, 10080)) {
            r = r2i;
        } else {
            f.e("setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
        }
    }

    public static void b(String r2_String) {
        if (r2_String.length() > 128) {
            f.e("the length of installChannel can not exceed the range of 128 bytes.");
        } else {
            q = r2_String;
        }
    }

    static void b(JSONObject r4_JSONObject) {
        try {
            StatReportStrategy r0_StatReportStrategy = StatReportStrategy.a(r4_JSONObject.getInt("rs"));
            if (r0_StatReportStrategy != null) {
                a(r0_StatReportStrategy);
                f.g("Change to ReportStrategy: " + r0_StatReportStrategy.name());
            }
        } catch (JSONException e) {
            f.h("rs not found.");
        }
    }

    public static void b(boolean r0z) {
        t = r0z;
    }

    public static boolean b() {
        return StatCommonHelper.b().a();
    }

    static void c(JSONObject r5_JSONObject) {
        try {
            String r0_String = r5_JSONObject.getString(c);
            f.h("hibernateVer:" + r0_String + ", current version:" + "0.6.12");
            long r1j = StatCommonHelper.b(r0_String);
            if (StatCommonHelper.b("0.6.12") <= r1j) {
                StatPreferences.b(l.a(), c, r1j);
                a(false);
                f.c("MTA has disable for SDK version of " + r0_String + " or lower.");
            }
        } catch (JSONException e) {
            f.h("__HIBERNATE__ not found.");
        }
    }

    public static void c(boolean r0z) {
        e = r0z;
    }

    public static boolean c() {
        return h;
    }

    public static int d() {
        return i;
    }

    public static int e() {
        return m;
    }

    public static int f() {
        return l;
    }

    static int g() {
        return k;
    }

    public static int h() {
        return j;
    }

    static HttpHost i() {
        if (o == null || o.length() <= 0) {
            return null;
        }
        String r1_String = o;
        String[] r2_StringA = r1_String.split(":");
        int r0i = 80;
        if (r2_StringA.length == 2) {
            r1_String = r2_StringA[0];
            r0i = Integer.parseInt(r2_StringA[1]);
        }
        return new HttpHost(r1_String, r0i);
    }

    static String j() {
        return (n == null || n.length() <= 0) ? "cgi.connect.qq.com" : n;
    }

    public static String k() {
        return d;
    }

    public static int l() {
        return r;
    }

    public static boolean m() {
        return t;
    }
}