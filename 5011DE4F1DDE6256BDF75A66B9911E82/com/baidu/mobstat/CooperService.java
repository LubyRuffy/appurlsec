package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.baidu.a.a.a.b.a;
import com.baidu.mobstat.a.c;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class CooperService extends BasicStoreToolsBase {
    private static b a;
    private static JSONObject b;
    private static String c;
    private static HashMap<String, Object> d;

    static {
        a = new b();
        b = new JSONObject();
        c = "activehead";
        d = new HashMap();
    }

    static b a() {
        return a;
    }

    private static String a(String r3_String, Context r4_Context) {
        if (r3_String == null) {
            return null;
        }
        if (!r3_String.equals("000000000000000")) {
            return r3_String;
        }
        r3_String = getMacAddress(r4_Context);
        c.a("stat", "imei=null,mac=" + r3_String);
        return r3_String;
    }

    public static boolean checkCellLocationSetting(Context r2_Context) {
        String r0_String = x.a(r2_Context, "BaiduMobAd_CELL_LOCATION");
        return r0_String == null || (!r0_String.toLowerCase().equals("false"));
    }

    public static boolean checkGPSLocationSetting(Context r2_Context) {
        String r0_String = x.a(r2_Context, "BaiduMobAd_GPS_LOCATION");
        return r0_String == null || (!r0_String.toLowerCase().equals("false"));
    }

    public static boolean checkWifiLocationSetting(Context r2_Context) {
        String r0_String = x.a(r2_Context, "BaiduMobAd_WIFI_LOCATION");
        return r0_String == null || (!r0_String.toLowerCase().equals("false"));
    }

    public static String getAppChannel(Context r3_Context) {
        try {
            if (a.j != null && !a.j.equals(RContactStorage.PRIMARY_KEY)) {
                return a.j;
            }
            boolean r0z = BasicStoreTools.getInstance().h(r3_Context);
            if (r0z) {
                a.j = BasicStoreTools.getInstance().g(r3_Context);
            }
            if (r0z && a.j != null && !a.j.equals(RContactStorage.PRIMARY_KEY)) {
                return a.j;
            }
            a.j = x.a(r3_Context, "BaiduMobAd_CHANNEL");
            return a.j;
        } catch (Exception e) {
            c.a(e);
        }
    }

    public static String getAppKey(Context r2_Context) {
        if (a.c == null) {
            a.c = x.a(r2_Context, "BaiduMobAd_STAT_ID");
        }
        return a.c;
    }

    public static int getAppVersionCode(Context r2_Context) {
        if (a.e == -1) {
            a.e = x.c(r2_Context);
        }
        return a.e;
    }

    public static String getAppVersionName(Context r2_Context) {
        if (a.f != null && !RContactStorage.PRIMARY_KEY.equals(a.f)) {
            return a.f;
        }
        a.f = x.d(r2_Context);
        return a.f;
    }

    public static String getCIUD(Context r4_Context) {
        if (a.d == null) {
            a.d = BasicStoreTools.getInstance().f(r4_Context);
            Matcher r0_Matcher;
            if (a.d == null || RContactStorage.PRIMARY_KEY.equalsIgnoreCase(a.d)) {
                try {
                    a.d = a.a(r4_Context);
                    BasicStoreTools.getInstance().b(r4_Context, a.d);
                } catch (Exception e) {
                    Object[] r1_ObjectA = new Object[2];
                    r1_ObjectA[0] = "sdkstat";
                    r1_ObjectA[1] = e.getMessage();
                    c.c(r1_ObjectA);
                }
                try {
                    r0_Matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(a.d);
                    a.d = r0_Matcher.replaceAll(RContactStorage.PRIMARY_KEY);
                } catch (Exception e_2) {
                    a.d = a.a(r4_Context);
                }
            } else {
                r0_Matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(a.d);
                a.d = r0_Matcher.replaceAll(RContactStorage.PRIMARY_KEY);
            }
        }
        return a.d;
    }

    public static String getDeviceId(TelephonyManager r4_TelephonyManager, Context r5_Context) {
        if (r4_TelephonyManager == null) {
            return a.g;
        }
        String r1_String = a.g;
        if (r1_String != null && !r1_String.equals(RContactStorage.PRIMARY_KEY)) {
            return a.g;
        }
        String r0_String;
        try {
            r0_String = a(Pattern.compile("\\s*|\t|\r|\n").matcher(r4_TelephonyManager.getDeviceId()).replaceAll(RContactStorage.PRIMARY_KEY), r5_Context);
        } catch (Exception e) {
            c.a(e);
            r0_String = r1_String;
        }
        if (r0_String == null) {
            r0_String = getMacAddress(r5_Context);
        }
        if (r0_String == null || r0_String.equals("000000000000000")) {
            r0_String = BasicStoreTools.getInstance().e(r5_Context);
            if (r0_String == null || r0_String.equals("000000000000000")) {
                r0_String = "hol" + (new Date().getTime() + RContactStorage.PRIMARY_KEY).hashCode() + "mes";
                BasicStoreTools.getInstance().a(r5_Context, r0_String);
                c.a("stat", "\u8bbe\u5907id\u4e3a\u7a7a\uff0c\u7cfb\u7edf\u751f\u6210id =" + r0_String);
                a.g = r0_String;
                return a.g;
            } else {
                a.g = r0_String;
                return a.g;
            }
        } else if (r0_String == null || r0_String.equals("000000000000000")) {
            r0_String = "hol" + (new Date().getTime() + RContactStorage.PRIMARY_KEY).hashCode() + "mes";
            BasicStoreTools.getInstance().a(r5_Context, r0_String);
            c.a("stat", "\u8bbe\u5907id\u4e3a\u7a7a\uff0c\u7cfb\u7edf\u751f\u6210id =" + r0_String);
            a.g = r0_String;
            return a.g;
        } else {
            a.g = r0_String;
            return a.g;
        }
    }

    public static String getLinkedWay(Context r2_Context) {
        if (a.p != null && !RContactStorage.PRIMARY_KEY.equals(a.p)) {
            return a.p;
        }
        a.p = x.i(r2_Context);
        return a.p;
    }

    public static String getMTJSDKVersion() {
        return "3.4";
    }

    public static String getMacAddress(Context r3_Context) {
        String r0_String = x.g(r3_Context);
        return r0_String != null ? r0_String.replaceAll(":", RContactStorage.PRIMARY_KEY) : null;
    }

    public static String getOSVersion() {
        if (a.b != null && !RContactStorage.PRIMARY_KEY.equals(a.b)) {
            return a.b;
        }
        a.b = VERSION.SDK;
        return a.b;
    }

    public static String getOperator(TelephonyManager r2_TelephonyManager) {
        if (a.k != null && !RContactStorage.PRIMARY_KEY.equals(a.k)) {
            return a.k;
        }
        a.k = r2_TelephonyManager.getNetworkOperator();
        return a.k;
    }

    public static String getPhoneModel() {
        if (a.l != null && !RContactStorage.PRIMARY_KEY.equals(a.l)) {
            return a.l;
        }
        a.l = Build.MODEL;
        return a.l;
    }
}