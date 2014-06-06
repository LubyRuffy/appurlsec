package com.amap.api.location.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: ClientInfoUtil.java
public class c {
    public static String a;
    private static c b;
    private static String c;
    private static Context d;
    private static TelephonyManager e;
    private static ConnectivityManager f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;

    static {
        b = null;
        c = null;
        d = null;
        a = RContactStorage.PRIMARY_KEY;
    }

    private c() {
    }

    public static c a(Context r2_Context) {
        if (b == null) {
            b = new c();
            d = r2_Context;
            e = (TelephonyManager) d.getSystemService("phone");
            f = (ConnectivityManager) d.getSystemService("connectivity");
            g = d.getApplicationContext().getPackageName();
            h = j();
            i = h();
            j = i();
            a = b(d);
        }
        return b;
    }

    public static String a() {
        return g;
    }

    public static String b(Context r3_Context) {
        if (a != null && !a.equals(RContactStorage.PRIMARY_KEY)) {
            return a;
        }
        try {
            a = r3_Context.getPackageManager().getApplicationInfo(r3_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData.getString("com.amap.api.v2.apikey");
        } catch (Exception e) {
            Log.e("AuthLocation", "key\u9274\u6743\u5931\u8d25");
        }
        return a;
    }

    public static String e() {
        return VERSION.RELEASE;
    }

    public static String f() {
        return Build.MODEL;
    }

    public static String h() {
        String r1_String = RContactStorage.PRIMARY_KEY;
        try {
            PackageManager r0_PackageManager = d.getPackageManager();
            return (String) r0_PackageManager.getApplicationLabel(r0_PackageManager.getApplicationInfo(d.getPackageName(), 0));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return r1_String;
        }
    }

    public static String i() {
        PackageManager r1_PackageManager = d.getPackageManager();
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            return r1_PackageManager.getPackageInfo(d.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return r0_String;
        }
    }

    private static String j() {
        DisplayMetrics r1_DisplayMetrics = new DisplayMetrics();
        ((WindowManager) d.getSystemService("window")).getDefaultDisplay().getMetrics(r1_DisplayMetrics);
        int r0i = r1_DisplayMetrics.widthPixels;
        int r1i = r1_DisplayMetrics.heightPixels;
        h = r1i > r0i ? r0i + "*" + r1i : r1i + "*" + r0i;
        return h;
    }

    public boolean a(String r3_String) {
        return r3_String == null || r3_String.equals(RContactStorage.PRIMARY_KEY) || r3_String.equals("0") || r3_String.equals("000") || r3_String.equalsIgnoreCase("nul") || r3_String.equals("460") || r3_String.equals("461");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String b() {
        /*
        r7_this = this;
        r0 = com.amap.api.location.core.e.a();
        r1 = 0;
        r2 = d;	 //Catch:{ Exception -> 0x003d }
        r2 = com.amap.api.location.core.e.a(r2);	 //Catch:{ Exception -> 0x003d }
        r3 = r0.getBytes();	 //Catch:{ Exception -> 0x003d }
        r2 = com.amap.api.location.core.e.a(r3, r2);	 //Catch:{ Exception -> 0x003d }
        r0 = r0.getBytes();	 //Catch:{ Exception -> 0x003d }
        r3 = r7.c();	 //Catch:{ Exception -> 0x003d }
        r3 = r3.getBytes();	 //Catch:{ Exception -> 0x003d }
        r3 = com.amap.api.location.core.e.a(r0, r3);	 //Catch:{ Exception -> 0x003d }
        r0 = r2.length;	 //Catch:{ Exception -> 0x003d }
        r4 = r3.length;	 //Catch:{ Exception -> 0x003d }
        r0 = r0 + r4;
        r0 = new byte[r0];	 //Catch:{ Exception -> 0x003d }
        r1 = 0;
        r4 = 0;
        r5 = r2.length;	 //Catch:{ Exception -> 0x0045 }
        java.lang.System.arraycopy(r2, r1, r0, r4, r5);	 //Catch:{ Exception -> 0x0045 }
        r1 = 0;
        r2 = r2.length;	 //Catch:{ Exception -> 0x0045 }
        r4 = r3.length;	 //Catch:{ Exception -> 0x0045 }
        java.lang.System.arraycopy(r3, r1, r0, r2, r4);	 //Catch:{ Exception -> 0x0045 }
    L_0x0034:
        r0 = com.aps.o.a(r0);
        r0 = com.aps.b.b(r0);
        return r0;
    L_0x003d:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0041:
        r1.printStackTrace();
        goto L_0x0034;
    L_0x0045:
        r1 = move-exception;
        goto L_0x0041;
        */

    }

    public String c_() {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append("ia=1&");
        String r1_String;
        String r2_String;
        if (a == null || a.length() <= 0) {
            r0_StringBuilder.append("ct=android");
            r1_String = e.getDeviceId();
            r2_String = e.getSubscriberId();
            r0_StringBuilder.append("&ime=" + r1_String);
            r0_StringBuilder.append("&sim=" + r2_String);
            r0_StringBuilder.append("&pkg=" + g);
            r0_StringBuilder.append("&mod=");
            r0_StringBuilder.append(f());
            r0_StringBuilder.append("&sv=");
            r0_StringBuilder.append(e());
            r0_StringBuilder.append("&nt=");
            r0_StringBuilder.append(g());
            r1_String = e.getNetworkOperatorName();
            r0_StringBuilder.append("&np=");
            r0_StringBuilder.append(r1_String);
            r0_StringBuilder.append("&ctm=" + System.currentTimeMillis());
            r0_StringBuilder.append("&re=" + h);
            r0_StringBuilder.append("&av=" + d.a);
            r0_StringBuilder.append("&apn=" + i);
            r0_StringBuilder.append("&apv=" + j);
            r0_StringBuilder.append("&pro=loc");
            return r0_StringBuilder.toString();
        } else {
            r0_StringBuilder.append("key=");
            r0_StringBuilder.append(a);
            r0_StringBuilder.append("&");
            r0_StringBuilder.append("ct=android");
            r1_String = e.getDeviceId();
            r2_String = e.getSubscriberId();
            r0_StringBuilder.append("&ime=" + r1_String);
            r0_StringBuilder.append("&sim=" + r2_String);
            r0_StringBuilder.append("&pkg=" + g);
            r0_StringBuilder.append("&mod=");
            r0_StringBuilder.append(f());
            r0_StringBuilder.append("&sv=");
            r0_StringBuilder.append(e());
            r0_StringBuilder.append("&nt=");
            r0_StringBuilder.append(g());
            r1_String = e.getNetworkOperatorName();
            r0_StringBuilder.append("&np=");
            r0_StringBuilder.append(r1_String);
            r0_StringBuilder.append("&ctm=" + System.currentTimeMillis());
            r0_StringBuilder.append("&re=" + h);
            r0_StringBuilder.append("&av=" + d.a);
            r0_StringBuilder.append("&apn=" + i);
            r0_StringBuilder.append("&apv=" + j);
            r0_StringBuilder.append("&pro=loc");
            return r0_StringBuilder.toString();
        }
    }

    public String c_(Context r5_Context) {
        String r1_String = RContactStorage.PRIMARY_KEY;
        TelephonyManager r0_TelephonyManager = (TelephonyManager) r5_Context.getSystemService("phone");
        if (r0_TelephonyManager == null) {
            return r1_String;
        }
        try {
            return r0_TelephonyManager.getNetworkOperator().substring(0, XListViewFooter.STATE_NOMORE);
        } catch (Exception e) {
            return r1_String;
        }
    }

    public String d() {
        StringBuilder r0_StringBuilder = new StringBuilder();
        r0_StringBuilder.append("mod=" + f());
        r0_StringBuilder.append("&sv=" + e());
        r0_StringBuilder.append("&nt=" + g());
        r0_StringBuilder.append("&np=" + e.getNetworkOperatorName());
        return r0_StringBuilder.toString();
    }

    public String g() {
        if (d.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return RContactStorage.PRIMARY_KEY;
        }
        if (f == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        NetworkInfo r0_NetworkInfo = f.getActiveNetworkInfo();
        return r0_NetworkInfo == null ? RContactStorage.PRIMARY_KEY : r0_NetworkInfo.getTypeName();
    }
}