package com.baidu.kirin.a;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.baidu.kirin.d.d;
import com.baidu.kirin.objects.LatitudeAndLongitude;
import com.baidu.kirin.objects.NetworkStatus;
import com.baidu.mobstat.CooperService;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class a {
    public static String a_() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String a_(Context r1_Context) {
        return CooperService.getAppChannel(r1_Context);
    }

    public static String a_(Context r7_Context, boolean r8z) {
        LatitudeAndLongitude r2_LatitudeAndLongitude = new LatitudeAndLongitude();
        if (r8z) {
            LocationManager r0_LocationManager = (LocationManager) r7_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
            Iterator r3_Iterator = r0_LocationManager.getAllProviders().iterator();
            while (r3_Iterator.hasNext()) {
                String r1_String = (String) r3_Iterator.next();
                System.out.println(r1_String);
                Location r1_Location = r0_LocationManager.getLastKnownLocation(r1_String);
                if (r1_Location != null) {
                    r2_LatitudeAndLongitude.latitude = r1_Location.getLatitude() + RContactStorage.PRIMARY_KEY;
                    r2_LatitudeAndLongitude.longitude = r1_Location.getLongitude() + RContactStorage.PRIMARY_KEY;
                } else {
                    r2_LatitudeAndLongitude.latitude = RContactStorage.PRIMARY_KEY;
                    r2_LatitudeAndLongitude.longitude = RContactStorage.PRIMARY_KEY;
                }
            }
        } else {
            r2_LatitudeAndLongitude.latitude = RContactStorage.PRIMARY_KEY;
            r2_LatitudeAndLongitude.longitude = RContactStorage.PRIMARY_KEY;
        }
        return r2_LatitudeAndLongitude.latitude + "," + r2_LatitudeAndLongitude.longitude;
    }

    public static boolean a_(Context r2_Context, String r3_String) {
        return r2_Context.getPackageManager().checkPermission(r3_String, r2_Context.getPackageName()) == 0;
    }

    public static String b(Context r1_Context) {
        return CooperService.getAppKey(r1_Context);
    }

    public static String c(Context r1_Context) {
        return CooperService.getAppVersionName(r1_Context);
    }

    public static int d(Context r1_Context) {
        return CooperService.getAppVersionCode(r1_Context);
    }

    public static String e(Context r3_Context) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (n(r3_Context)) {
            r0_String = VERSION.RELEASE;
            d.a("android_osVersion : " + r0_String);
            return r0_String;
        } else {
            d.c("android OsVerson get failed");
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static String f(Context r2_Context) {
        return "Android" + CooperService.getOSVersion();
    }

    public static String g(Context r3_Context) {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (n(r3_Context)) {
            r0_String = Build.MANUFACTURER;
            d.a("manufacturer_info : " + r0_String);
            return r0_String;
        } else {
            d.c("android manufacturer get failed!");
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static String h(Context r1_Context) {
        return CooperService.getDeviceId((TelephonyManager) r1_Context.getSystemService("phone"), r1_Context);
    }

    public static String i(Context r3_Context) {
        if (a(r3_Context, "android.permission.READ_PHONE_STATE")) {
            String r0_String = RContactStorage.PRIMARY_KEY;
            if (n(r3_Context)) {
                r0_String = ((TelephonyManager) r3_Context.getSystemService("phone")).getPhoneType() + RContactStorage.PRIMARY_KEY;
            }
            if (r0_String.length() != 0) {
                d.a("phoneType : " + r0_String);
                return r0_String;
            } else {
                d.b("phoneType get nothing");
                return RContactStorage.PRIMARY_KEY;
            }
        } else {
            d.c("lost permissioin : android.permission.READ_PHONE_STATE");
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static String j(Context r1_Context) {
        return CooperService.getCIUD(r1_Context);
    }

    public static String k(Context r3_Context) {
        if (a(r3_Context, "android.permission.READ_PHONE_STATE")) {
            String r0_String = RContactStorage.PRIMARY_KEY;
            if (n(r3_Context)) {
                r0_String = ((TelephonyManager) r3_Context.getSystemService("phone")).getDeviceId();
            }
            if (r0_String != null) {
                d.a("Imei:" + r0_String);
                return r0_String;
            } else {
                d.b("Imei is null");
                return RContactStorage.PRIMARY_KEY;
            }
        } else {
            d.c("lost permissioin : android.permission.READ_PHONE_STATE");
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static NetworkStatus l(Context r3_Context) {
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r3_Context.getSystemService("connectivity");
        if (r0_ConnectivityManager == null) {
            return NetworkStatus.NotReachable;
        }
        NetworkInfo r1_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
        if (r1_NetworkInfo == null || (!r1_NetworkInfo.isAvailable())) {
            return NetworkStatus.NotReachable;
        }
        TelephonyManager r1_TelephonyManager = (TelephonyManager) r3_Context.getSystemService("phone");
        if (r0_ConnectivityManager.getActiveNetworkInfo().getType() == 1) {
            return NetworkStatus.Wifi;
        }
        switch (r1_TelephonyManager.getNetworkType()) {
            case XListViewHeader.STATE_NORMAL:
                return NetworkStatus.TwoG;
            case XListViewHeader.STATE_READY:
                return NetworkStatus.TwoG;
            case XListViewHeader.STATE_REFRESHING:
                return NetworkStatus.TwoG;
            case XListViewFooter.STATE_NOMORE:
                return NetworkStatus.ThreeG;
            case XListViewFooter.STATE_NODATA:
                return NetworkStatus.TwoG;
            case ShareUtils.SHARE_SMS:
                return NetworkStatus.ThreeG;
            case ShareUtils.SHARE_COPY:
                return NetworkStatus.ThreeG;
            case ShareUtils.SHARE_COLLECT:
                return NetworkStatus.TwoG;
            case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                return NetworkStatus.TwoG;
            case REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH:
                return NetworkStatus.ThreeG;
            case REQUEST_CODE.REQUEST_CODE_EDIT_GENDER:
                return NetworkStatus.ThreeG;
            case REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
                return NetworkStatus.ThreeG;
            case NearbySelectView.TIME_15MIN:
                return NetworkStatus.ThreeG;
        }
        return NetworkStatus.TwoG;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String m(Context r9_Context) {
        /*
        r7 = 13;
        r3 = android.os.Build.VERSION.SDK_INT;
        r4 = new android.util.DisplayMetrics;
        r4.<init>();
        r0 = "window";
        r0 = r9.getSystemService(r0);
        r0 = (android.view.WindowManager) r0;
        r0 = r0.getDefaultDisplay();
        r0.getMetrics(r4);
        r2 = r4.widthPixels;
        r1 = r4.heightPixels;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Run1 first get resolution:";
        r5 = r5.append(r6);
        r5 = r5.append(r2);
        r6 = " * ";
        r5 = r5.append(r6);
        r5 = r5.append(r1);
        r6 = ",ver ";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        com.baidu.kirin.d.d.a(r5);
        if (r3 >= r7) goto L_0x0083;
    L_0x0048:
        r0 = r4.heightPixels;
        r1 = r2;
    L_0x004b:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Run2 Calibration resolution:";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r3 = " * ";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        com.baidu.kirin.d.d.a(r2);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r2 = "*";
        r1 = r1.append(r2);
        r0 = r1.append(r0);
        r0 = r0.toString();
        return r0;
    L_0x0083:
        if (r3 != r7) goto L_0x00a8;
    L_0x0085:
        r3 = r0.getClass();	 //Catch:{ Exception -> 0x00a1 }
        r4 = "getRealHeight";
        r5 = 0;
        r5 = new java.lang.Class[r5];	 //Catch:{ Exception -> 0x00a1 }
        r3 = r3.getMethod(r4, r5);	 //Catch:{ Exception -> 0x00a1 }
        r4 = 0;
        r4 = new java.lang.Object[r4];	 //Catch:{ Exception -> 0x00a1 }
        r0 = r3.invoke(r0, r4);	 //Catch:{ Exception -> 0x00a1 }
        r0 = (java.lang.Integer) r0;	 //Catch:{ Exception -> 0x00a1 }
        r0 = r0.intValue();	 //Catch:{ Exception -> 0x00a1 }
        r1 = r2;
        goto L_0x004b;
    L_0x00a1:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        r1 = r2;
        goto L_0x004b;
    L_0x00a8:
        if (r3 <= r7) goto L_0x0100;
    L_0x00aa:
        r3 = r0.getClass();	 //Catch:{ Exception -> 0x00f1 }
        r4 = "getSize";
        r5 = 1;
        r5 = new java.lang.Class[r5];	 //Catch:{ Exception -> 0x00f1 }
        r6 = 0;
        r7 = android.graphics.Point.class;
        r5[r6] = r7;	 //Catch:{ Exception -> 0x00f1 }
        r3 = r3.getMethod(r4, r5);	 //Catch:{ Exception -> 0x00f1 }
        r4 = new android.graphics.Point;	 //Catch:{ Exception -> 0x00f1 }
        r4.<init>();	 //Catch:{ Exception -> 0x00f1 }
        r5 = 1;
        r5 = new java.lang.Object[r5];	 //Catch:{ Exception -> 0x00f1 }
        r6 = 0;
        r5[r6] = r4;	 //Catch:{ Exception -> 0x00f1 }
        r3.invoke(r0, r5);	 //Catch:{ Exception -> 0x00f1 }
        r2 = r4.x;	 //Catch:{ Exception -> 0x00f1 }
        r0 = r4.y;	 //Catch:{ Exception -> 0x00f1 }
        r1 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00fb }
        r1.<init>();	 //Catch:{ Exception -> 0x00fb }
        r3 = "ver>13 resolution : ";
        r1 = r1.append(r3);	 //Catch:{ Exception -> 0x00fb }
        r1 = r1.append(r2);	 //Catch:{ Exception -> 0x00fb }
        r3 = " * ";
        r1 = r1.append(r3);	 //Catch:{ Exception -> 0x00fb }
        r1 = r1.append(r0);	 //Catch:{ Exception -> 0x00fb }
        r1 = r1.toString();	 //Catch:{ Exception -> 0x00fb }
        com.baidu.kirin.d.d.a(r1);	 //Catch:{ Exception -> 0x00fb }
        r1 = r2;
        goto L_0x004b;
    L_0x00f1:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r8;
    L_0x00f6:
        r2.printStackTrace();
        goto L_0x004b;
    L_0x00fb:
        r1 = move-exception;
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x00f6;
    L_0x0100:
        r0 = r1;
        r1 = r2;
        goto L_0x004b;
        */

    }

    public static boolean n(Context r3_Context) {
        return r3_Context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", r3_Context.getPackageName()) == 0;
    }

    public static boolean o(Context r2_Context) {
        if (a(r2_Context, "android.permission.INTERNET")) {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r2_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (r0_NetworkInfo != null && r0_NetworkInfo.isAvailable()) {
                return true;
            }
            d.c("Network error");
            return false;
        } else {
            d.c(" lost  permission : android.permission.INTERNET");
            return false;
        }
    }
}