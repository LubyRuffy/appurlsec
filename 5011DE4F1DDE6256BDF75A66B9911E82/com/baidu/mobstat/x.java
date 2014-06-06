package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amap.api.location.LocationManagerProxy;
import com.baidu.mobstat.a.b;
import com.baidu.mobstat.a.c;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.nearby.ui.NearByListActivity;

final class x {
    public static int a(Context r3_Context) {
        DisplayMetrics r0_DisplayMetrics = new DisplayMetrics();
        try {
            r0_DisplayMetrics = j(r3_Context);
        } catch (Exception e) {
            c.a("createAdReqURL", e);
        }
        return r0_DisplayMetrics.widthPixels;
    }

    public static String a(Context r4_Context, String r5_String) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            ApplicationInfo r2_ApplicationInfo = r4_Context.getPackageManager().getApplicationInfo(r4_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (r2_ApplicationInfo != null) {
                Object r1_Object = null;
                if (r2_ApplicationInfo.metaData != null) {
                    r1_Object = r2_ApplicationInfo.metaData.get(r5_String);
                }
                if (r1_Object == null) {
                    c.a("StatSDK", "null,can't find information for key:" + r5_String);
                    if (r5_String == "BaiduMobAd_STAT_ID") {
                        c.c("\u4e0d\u80fd\u5728manifest.xml\u4e2d\u627e\u5230APP Key||can't find app key in manifest.xml.");
                    }
                } else {
                    r0_String = r1_Object.toString();
                    if (!r0_String.trim().equals(RContactStorage.PRIMARY_KEY) || r5_String != "BaiduMobAd_STAT_ID") {
                        return r0_String;
                    }
                    c.c("APP Key\u503c\u4e3a\u7a7a||The value of APP Key is empty.");
                }
            }
        } catch (NameNotFoundException e) {
            if (r5_String == "BaiduMobAd_STAT_ID") {
                c.c("\u4e0d\u80fd\u5728manifest.xml\u4e2d\u627e\u5230APP Key||can't find app key in manifest.xml.");
            }
        }
        return r0_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String a(Context r8_Context, String r9_String, String r10_String, int r11i, int r12i) {
        /*
        r1 = 0;
        r2 = 0;
        r3 = com.baidu.mobstat.a.b.a(r8, r9, r11, r12);
        r0 = 1;
        r3.setDoOutput(r0);
        r3.setInstanceFollowRedirects(r1);
        r3.setUseCaches(r1);
        r0 = "Content-Type";
        r1 = "gzip";
        r3.setRequestProperty(r0, r1);
        r3.connect();
        r0 = "AdUtil.httpPost connected";
        com.baidu.mobstat.a.c.a(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = new java.io.BufferedWriter;	 //Catch:{ IOException -> 0x009f }
        r4 = new java.io.OutputStreamWriter;	 //Catch:{ IOException -> 0x009f }
        r5 = new java.util.zip.GZIPOutputStream;	 //Catch:{ IOException -> 0x009f }
        r6 = r3.getOutputStream();	 //Catch:{ IOException -> 0x009f }
        r5.<init>(r6);	 //Catch:{ IOException -> 0x009f }
        r4.<init>(r5);	 //Catch:{ IOException -> 0x009f }
        r1.<init>(r4);	 //Catch:{ IOException -> 0x009f }
        r1.write(r10);	 //Catch:{ IOException -> 0x00a7 }
        r1.close();	 //Catch:{ IOException -> 0x00a7 }
        r1 = new java.io.BufferedReader;	 //Catch:{ IOException -> 0x009f }
        r4 = new java.io.InputStreamReader;	 //Catch:{ IOException -> 0x009f }
        r5 = r3.getInputStream();	 //Catch:{ IOException -> 0x009f }
        r4.<init>(r5);	 //Catch:{ IOException -> 0x009f }
        r1.<init>(r4);	 //Catch:{ IOException -> 0x009f }
    L_0x004b:
        r4 = r1.readLine();	 //Catch:{ IOException -> 0x0055 }
        if (r4 == 0) goto L_0x0064;
    L_0x0051:
        r0.append(r4);	 //Catch:{ IOException -> 0x0055 }
        goto L_0x004b;
    L_0x0055:
        r0 = move-exception;
    L_0x0056:
        if (r1 == 0) goto L_0x005b;
    L_0x0058:
        r1.close();
    L_0x005b:
        if (r2 == 0) goto L_0x0060;
    L_0x005d:
        r2.close();
    L_0x0060:
        r3.disconnect();
        throw r0;
    L_0x0064:
        r1.close();	 //Catch:{ IOException -> 0x0055 }
        r3.disconnect();	 //Catch:{ IOException -> 0x009f }
        r1 = r3.getContentLength();	 //Catch:{ IOException -> 0x009f }
        r4 = r3.getResponseCode();	 //Catch:{ IOException -> 0x009f }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 != r5) goto L_0x0078;
    L_0x0076:
        if (r1 == 0) goto L_0x00a2;
    L_0x0078:
        r1 = new java.io.IOException;	 //Catch:{ IOException -> 0x009f }
        r4 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x009f }
        r4.<init>();	 //Catch:{ IOException -> 0x009f }
        r5 = "http code =";
        r4 = r4.append(r5);	 //Catch:{ IOException -> 0x009f }
        r5 = r3.getResponseCode();	 //Catch:{ IOException -> 0x009f }
        r4 = r4.append(r5);	 //Catch:{ IOException -> 0x009f }
        r5 = "& contentResponse=";
        r4 = r4.append(r5);	 //Catch:{ IOException -> 0x009f }
        r0 = r4.append(r0);	 //Catch:{ IOException -> 0x009f }
        r0 = r0.toString();	 //Catch:{ IOException -> 0x009f }
        r1.<init>(r0);	 //Catch:{ IOException -> 0x009f }
        throw r1;	 //Catch:{ IOException -> 0x009f }
    L_0x009f:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0056;
    L_0x00a2:
        r0 = r0.toString();
        return r0;
    L_0x00a7:
        r0 = move-exception;
        r7 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x0056;
        */

    }

    public static int b(Context r3_Context) {
        DisplayMetrics r0_DisplayMetrics = new DisplayMetrics();
        try {
            r0_DisplayMetrics = j(r3_Context);
        } catch (Exception e) {
            c.a("createAdReqURL", e);
        }
        return r0_DisplayMetrics.heightPixels;
    }

    public static int c(Context r5_Context) {
        try {
            return r5_Context.getPackageManager().getPackageInfo(r5_Context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Object[] r1_ObjectA = new Object[2];
            r1_ObjectA[0] = "stat";
            r1_ObjectA[1] = "get app version code exception";
            c.c(r1_ObjectA);
            return 1;
        }
    }

    public static String d(Context r4_Context) {
        try {
            return r4_Context.getPackageManager().getPackageInfo(r4_Context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = "stat";
            r0_ObjectA[1] = "get app version name exception";
            c.c(r0_ObjectA);
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static String e(Context r9_Context) {
        String r0_String;
        Object[] r1_ObjectA = new Object[3];
        r1_ObjectA[0] = Integer.valueOf(0);
        r1_ObjectA[1] = Integer.valueOf(0);
        r1_ObjectA[2] = Integer.valueOf(0);
        String r1_String = String.format("%s_%s_%s", r1_ObjectA);
        try {
            if (b.e(r9_Context, "android.permission.ACCESS_FINE_LOCATION") || b.e(r9_Context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation r0_CellLocation = ((TelephonyManager) r9_Context.getSystemService("phone")).getCellLocation();
                c.a("getLocation cell:", r0_CellLocation + RContactStorage.PRIMARY_KEY);
                if (r0_CellLocation == null) {
                    r0_String = r1_String;
                } else if (r0_CellLocation instanceof GsmCellLocation) {
                    GsmCellLocation r0_GsmCellLocation = (GsmCellLocation) r0_CellLocation;
                    r3_ObjectA = new Object[3];
                    Object[] r6_ObjectA = new Object[1];
                    r6_ObjectA[0] = Integer.valueOf(r0_GsmCellLocation.getCid());
                    r3_ObjectA[0] = String.format("%d", r6_ObjectA);
                    r6_ObjectA = new Object[1];
                    r6_ObjectA[0] = Integer.valueOf(r0_GsmCellLocation.getLac());
                    r3_ObjectA[1] = String.format("%d", r6_ObjectA);
                    r3_ObjectA[2] = Integer.valueOf(0);
                    r0_String = String.format("%s_%s_%s", r3_ObjectA);
                } else {
                    String[] r0_StringA = r0_CellLocation.toString().replace("[", RContactStorage.PRIMARY_KEY).replace("]", RContactStorage.PRIMARY_KEY).split(",");
                    r3_ObjectA = new Object[3];
                    r3_ObjectA[0] = r0_StringA[0];
                    r3_ObjectA[1] = r0_StringA[3];
                    r3_ObjectA[2] = r0_StringA[4];
                    r0_String = String.format("%s_%s_%s", r3_ObjectA);
                }
            } else {
                r0_String = r1_String;
            }
        } catch (Exception e) {
            c.a("getLocation", e);
        }
        return r0_String;
    }

    public static String f(Context r7_Context) {
        String r1_String = RContactStorage.PRIMARY_KEY;
        try {
            if (b.e(r7_Context, "android.permission.ACCESS_FINE_LOCATION")) {
                Location r0_Location = ((LocationManager) r7_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION)).getLastKnownLocation(LocationManagerProxy.GPS_PROVIDER);
                c.a("stat", "location: " + r0_Location);
                if (r0_Location != null) {
                    Object[] r3_ObjectA = new Object[3];
                    r3_ObjectA[0] = Long.valueOf(r0_Location.getTime());
                    r3_ObjectA[1] = Double.valueOf(r0_Location.getLongitude());
                    r3_ObjectA[2] = Double.valueOf(r0_Location.getLatitude());
                    return String.format("%s_%s_%s", r3_ObjectA);
                }
            }
        } catch (Exception e) {
            c.a("stat", e);
        }
        return r1_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String g(Context r7_Context) {
        /*
        r1 = "";
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = com.baidu.mobstat.a.b.e(r7, r0);	 //Catch:{ Exception -> 0x003c }
        if (r0 == 0) goto L_0x0035;
    L_0x000a:
        r0 = "wifi";
        r0 = r7.getSystemService(r0);	 //Catch:{ Exception -> 0x003c }
        r0 = (android.net.wifi.WifiManager) r0;	 //Catch:{ Exception -> 0x003c }
        r2 = r0.getConnectionInfo();	 //Catch:{ Exception -> 0x003c }
        r0 = r2.getMacAddress();	 //Catch:{ Exception -> 0x003c }
        r1 = "ssid=%s mac=%s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 //Catch:{ Exception -> 0x0046 }
        r4 = 0;
        r5 = r2.getSSID();	 //Catch:{ Exception -> 0x0046 }
        r3[r4] = r5;	 //Catch:{ Exception -> 0x0046 }
        r4 = 1;
        r2 = r2.getMacAddress();	 //Catch:{ Exception -> 0x0046 }
        r3[r4] = r2;	 //Catch:{ Exception -> 0x0046 }
        r1 = java.lang.String.format(r1, r3);	 //Catch:{ Exception -> 0x0046 }
        com.baidu.mobstat.a.c.a(r1);	 //Catch:{ Exception -> 0x0046 }
    L_0x0034:
        return r0;
    L_0x0035:
        r0 = "You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add:android.permission.ACCESS_WIFI_STATE";
        com.baidu.mobstat.a.c.c(r0);	 //Catch:{ Exception -> 0x003c }
        r0 = r1;
        goto L_0x0034;
    L_0x003c:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0040:
        r2 = "stat";
        com.baidu.mobstat.a.c.a(r2, r1);
        goto L_0x0034;
    L_0x0046:
        r1 = move-exception;
        goto L_0x0040;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String h(Context r12_Context) {
        /*
        r2 = 0;
        r6 = "";
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = com.baidu.mobstat.a.b.e(r12, r0);	 //Catch:{ Exception -> 0x00db }
        if (r0 == 0) goto L_0x00ee;
    L_0x000b:
        r0 = "wifi";
        r0 = r12.getSystemService(r0);	 //Catch:{ Exception -> 0x00db }
        r0 = (android.net.wifi.WifiManager) r0;	 //Catch:{ Exception -> 0x00db }
        r1 = r0.isWifiEnabled();	 //Catch:{ Exception -> 0x00db }
        if (r1 == 0) goto L_0x00ee;
    L_0x0019:
        r1 = "[d]";
        r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00db }
        r3.<init>();	 //Catch:{ Exception -> 0x00db }
        r4 = r0.getScanResults();	 //Catch:{ Exception -> 0x00db }
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x00db }
        r4 = "";
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x00db }
        r3 = r3.toString();	 //Catch:{ Exception -> 0x00db }
        com.baidu.mobstat.a.c.a(r1, r3);	 //Catch:{ Exception -> 0x00db }
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r3 = -1;
    L_0x0039:
        r1 = r0.getScanResults();	 //Catch:{ Exception -> 0x00db }
        r1 = r1.size();	 //Catch:{ Exception -> 0x00db }
        if (r2 >= r1) goto L_0x0079;
    L_0x0043:
        r1 = r0.getScanResults();	 //Catch:{ Exception -> 0x00db }
        r1 = r1.get(r2);	 //Catch:{ Exception -> 0x00db }
        r1 = (android.net.wifi.ScanResult) r1;	 //Catch:{ Exception -> 0x00db }
        r4 = r1.level;	 //Catch:{ Exception -> 0x00db }
        r4 = java.lang.Math.abs(r4);	 //Catch:{ Exception -> 0x00db }
        r7 = "%s %s_%s";
        r8 = 3;
        r8 = new java.lang.Object[r8];	 //Catch:{ Exception -> 0x00db }
        r9 = 0;
        r10 = r1.SSID;	 //Catch:{ Exception -> 0x00db }
        r8[r9] = r10;	 //Catch:{ Exception -> 0x00db }
        r9 = 1;
        r1 = r1.BSSID;	 //Catch:{ Exception -> 0x00db }
        r8[r9] = r1;	 //Catch:{ Exception -> 0x00db }
        r1 = 2;
        r9 = java.lang.Integer.valueOf(r4);	 //Catch:{ Exception -> 0x00db }
        r8[r1] = r9;	 //Catch:{ Exception -> 0x00db }
        r1 = java.lang.String.format(r7, r8);	 //Catch:{ Exception -> 0x00db }
        com.baidu.mobstat.a.c.a(r1);	 //Catch:{ Exception -> 0x00db }
        if (r5 <= r4) goto L_0x00eb;
    L_0x0072:
        r1 = r2;
        r3 = r4;
    L_0x0074:
        r2 = r2 + 1;
        r5 = r3;
        r3 = r1;
        goto L_0x0039;
    L_0x0079:
        if (r3 < 0) goto L_0x00e9;
    L_0x007b:
        r1 = r0.getScanResults();	 //Catch:{ Exception -> 0x00db }
        r1 = r1.get(r3);	 //Catch:{ Exception -> 0x00db }
        r1 = (android.net.wifi.ScanResult) r1;	 //Catch:{ Exception -> 0x00db }
        r2 = r1.BSSID;	 //Catch:{ Exception -> 0x00db }
        r3 = ":";
        r4 = "";
        r2 = r2.replace(r3, r4);	 //Catch:{ Exception -> 0x00db }
        r2 = r2.toLowerCase();	 //Catch:{ Exception -> 0x00db }
        r3 = "%s_%s";
        r4 = 2;
        r4 = new java.lang.Object[r4];	 //Catch:{ Exception -> 0x00db }
        r5 = 0;
        r4[r5] = r2;	 //Catch:{ Exception -> 0x00db }
        r2 = 1;
        r1 = r1.level;	 //Catch:{ Exception -> 0x00db }
        r1 = java.lang.Math.abs(r1);	 //Catch:{ Exception -> 0x00db }
        r1 = java.lang.Integer.valueOf(r1);	 //Catch:{ Exception -> 0x00db }
        r4[r2] = r1;	 //Catch:{ Exception -> 0x00db }
        r1 = java.lang.String.format(r3, r4);	 //Catch:{ Exception -> 0x00db }
    L_0x00ac:
        r0 = r0.getConnectionInfo();	 //Catch:{ Exception -> 0x00e4 }
        r2 = "[active]%s %s_%s";
        r3 = 3;
        r3 = new java.lang.Object[r3];	 //Catch:{ Exception -> 0x00e4 }
        r4 = 0;
        r5 = r0.getSSID();	 //Catch:{ Exception -> 0x00e4 }
        r3[r4] = r5;	 //Catch:{ Exception -> 0x00e4 }
        r4 = 1;
        r5 = r0.getMacAddress();	 //Catch:{ Exception -> 0x00e4 }
        r3[r4] = r5;	 //Catch:{ Exception -> 0x00e4 }
        r4 = 2;
        r0 = r0.getRssi();	 //Catch:{ Exception -> 0x00e4 }
        r0 = java.lang.Math.abs(r0);	 //Catch:{ Exception -> 0x00e4 }
        r0 = java.lang.Integer.valueOf(r0);	 //Catch:{ Exception -> 0x00e4 }
        r3[r4] = r0;	 //Catch:{ Exception -> 0x00e4 }
        r0 = java.lang.String.format(r2, r3);	 //Catch:{ Exception -> 0x00e4 }
        com.baidu.mobstat.a.c.a(r0);	 //Catch:{ Exception -> 0x00e4 }
        r0 = r1;
    L_0x00da:
        return r0;
    L_0x00db:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x00de:
        r2 = "getWifiLocation";
        com.baidu.mobstat.a.c.a(r2, r1);
        goto L_0x00da;
    L_0x00e4:
        r0 = move-exception;
        r11 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x00de;
    L_0x00e9:
        r1 = r6;
        goto L_0x00ac;
    L_0x00eb:
        r1 = r3;
        r3 = r5;
        goto L_0x0074;
    L_0x00ee:
        r0 = r6;
        goto L_0x00da;
        */

    }

    public static String i(Context r3_Context) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        NetworkInfo r1_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        r0_String = r1_NetworkInfo.getTypeName();
        return (r0_String.equals("WIFI") || r1_NetworkInfo.getSubtypeName() == null) ? r0_String : r1_NetworkInfo.getSubtypeName();
    }

    public static DisplayMetrics j(Context r3_Context) {
        DisplayMetrics r1_DisplayMetrics = new DisplayMetrics();
        ((WindowManager) r3_Context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(r1_DisplayMetrics);
        return r1_DisplayMetrics;
    }
}