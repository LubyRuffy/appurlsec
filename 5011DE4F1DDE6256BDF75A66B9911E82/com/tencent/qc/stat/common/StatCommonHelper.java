package com.tencent.qc.stat.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.cordova.NetworkManager;
import org.apache.http.HttpHost;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.HttpUtils;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: ProGuard
public class StatCommonHelper {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static Random e;
    private static StatLogger f;

    static {
        a = null;
        b = null;
        c = null;
        d = null;
        e = null;
        f = null;
    }

    public static int a() {
        return e().nextInt(a.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public static Long a(String r10_String, String r11_String, int r12i, int r13i, Long r14_Long) {
        if (r10_String == null || r11_String == null) {
            return r14_Long;
        }
        String[] r2_StringA;
        if (r11_String.equalsIgnoreCase(".") || r11_String.equalsIgnoreCase("|")) {
            r11_String = "\\" + r11_String;
            r2_StringA = r10_String.split(r11_String);
        } else {
            r2_StringA = r10_String.split(r11_String);
        }
        if (r2_StringA.length != r13i) {
            return r14_Long;
        }
        try {
            Long r1_Long = Long.valueOf(0);
            int r3i = r2_StringA.length - 1;
            int r0i = 0;
            while (r0i < r3i) {
                r1_Long = Long.valueOf(r1_Long.longValue() + ((long) r12i) * Long.valueOf(r2_StringA[r0i]).longValue());
                r0i++;
            }
            return Long.valueOf(r1_Long.longValue() + Long.valueOf(r2_StringA[r3i]).longValue());
        } catch (NumberFormatException e) {
            return r14_Long;
        }
    }

    public static String a(long r2j) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(r2j));
    }

    public static String a(String r5_String) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.update(r5_String.getBytes());
            byte[] r1_byteA = r0_MessageDigest.digest();
            StringBuffer r2_StringBuffer = new StringBuffer();
            int r0i = 0;
            while (r0i < r1_byteA.length) {
                int r3i = r1_byteA[r0i] & 255;
                if (r3i < 16) {
                    r2_StringBuffer.append("0");
                }
                r2_StringBuffer.append(Integer.toHexString(r3i));
                r0i++;
            }
            return r2_StringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            f.b(e);
            return "0";
        }
    }

    public static HttpHost a(Context r4_Context) {
        if (r4_Context == null) {
            return null;
        }
        if (r4_Context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", r4_Context.getPackageName()) != 0) {
            return null;
        }
        try {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r4_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (r0_NetworkInfo == null) {
                return null;
            }
            if (r0_NetworkInfo.getTypeName() != null && r0_NetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String r0_String = r0_NetworkInfo.getExtraInfo();
            if (r0_String == null) {
                return null;
            }
            if (r0_String.equals("cmwap") || r0_String.equals("3gwap") || r0_String.equals("uniwap")) {
                return new HttpHost(HttpUtils.PROXY_IP, 80);
            }
            if (r0_String.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            return null;
        } catch (Exception e) {
            f.b(e);
        }
    }

    public static void a(JSONObject r1_JSONObject, String r2_String, String r3_String) {
        if (r3_String == null || r3_String.length() <= 0) {
        } else {
            r1_JSONObject.put(r2_String, r3_String);
        }
    }

    public static boolean a(Context r2_Context, String r3_String) {
        return r2_Context.getPackageManager().checkPermission(r3_String, r2_Context.getPackageName()) == 0;
    }

    public static byte[] a(byte[] r5_byteA) {
        GZIPInputStream r1_GZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(r5_byteA));
        byte[] r0_byteA = new byte[4096];
        ByteArrayOutputStream r2_ByteArrayOutputStream = new ByteArrayOutputStream(r5_byteA.length * 2);
        while (true) {
            int r3i = r1_GZIPInputStream.read(r0_byteA);
            if (r3i == -1) {
                return r2_ByteArrayOutputStream.toByteArray();
            }
            r2_ByteArrayOutputStream.write(r0_byteA, 0, r3i);
        }
    }

    public static long b(String r5_String) {
        return a(r5_String, ".", 100, XListViewFooter.STATE_NOMORE, Long.valueOf(0)).longValue();
    }

    public static StatLogger b() {
        if (f == null) {
            f = new StatLogger("qc_MtaSDK");
            f.a(false);
        }
        return f;
    }

    public static String b(Context r2_Context) {
        if (a != null) {
            return a;
        }
        a = k(r2_Context);
        if (a == null) {
            a = Integer.toString(e().nextInt(a.MAX_ACTIVITY_COUNT_UNLIMITED));
        }
        return a;
    }

    public static long c() {
        Calendar r0_Calendar = Calendar.getInstance();
        r0_Calendar.set(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE, 0);
        r0_Calendar.set(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH, 0);
        r0_Calendar.set(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, 0);
        r0_Calendar.set(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, 0);
        return 86400000 + r0_Calendar.getTimeInMillis();
    }

    public static String c(Context r2_Context) {
        if (c != null && RContactStorage.PRIMARY_KEY != c) {
            return c;
        }
        c = f(r2_Context);
        return c;
    }

    public static DisplayMetrics d(Context r3_Context) {
        DisplayMetrics r1_DisplayMetrics = new DisplayMetrics();
        ((WindowManager) r3_Context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(r1_DisplayMetrics);
        return r1_DisplayMetrics;
    }

    public static String d() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        String r1_String = Environment.getExternalStorageDirectory().getPath();
        if (r1_String == null) {
            return null;
        }
        StatFs r0_StatFs = new StatFs(r1_String);
        return String.valueOf((((long) r0_StatFs.getAvailableBlocks()) * ((long) r0_StatFs.getBlockSize())) / 1000000) + "/" + String.valueOf((((long) r0_StatFs.getBlockCount()) * ((long) r0_StatFs.getBlockSize())) / 1000000);
    }

    private static Random e() {
        if (e == null) {
            e = new Random();
        }
        return e;
    }

    public static boolean e(Context r5_Context) {
        if (a(r5_Context, "android.permission.ACCESS_WIFI_STATE")) {
            ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r5_Context.getApplicationContext().getSystemService("connectivity");
            if (r0_ConnectivityManager == null) {
                return false;
            }
            NetworkInfo[] r2_NetworkInfoA = r0_ConnectivityManager.getAllNetworkInfo();
            if (r2_NetworkInfoA == null) {
                return false;
            }
            int r0i = 0;
            while (r0i < r2_NetworkInfoA.length) {
                if (r2_NetworkInfoA[r0i].getTypeName().equalsIgnoreCase("WIFI") && r2_NetworkInfoA[r0i].isConnected()) {
                    return true;
                }
                r0i++;
            }
            return false;
        } else {
            f.c("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return false;
        }
    }

    public static String f(Context r2_Context) {
        String r0_String;
        try {
            WifiManager r0_WifiManager = (WifiManager) r2_Context.getSystemService(NetworkManager.WIFI);
            if (r0_WifiManager == null) {
                r0_String = RContactStorage.PRIMARY_KEY;
                return r0_String;
            } else {
                r0_String = r0_WifiManager.getConnectionInfo().getMacAddress();
                return r0_String;
            }
        } catch (Exception e) {
            f.b(e);
            r0_String = RContactStorage.PRIMARY_KEY;
        }
    }

    public static boolean g(Context r3_Context) {
        if (a(r3_Context, "android.permission.INTERNET")) {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            return r0_NetworkInfo != null && r0_NetworkInfo.isAvailable() && r0_NetworkInfo.getTypeName().equalsIgnoreCase("WIFI");
        } else {
            f.c("can not get the permisson of android.permission.INTERNET");
            return false;
        }
    }

    public static boolean h(Context r3_Context) {
        if (a(r3_Context, "android.permission.INTERNET")) {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (r0_NetworkInfo != null && r0_NetworkInfo.isAvailable()) {
                return true;
            }
            f.d("Network error");
            return false;
        } else {
            f.c("can not get the permisson of android.permission.INTERNET");
            return false;
        }
    }

    public static String i(Context r3_Context) {
        if (b != null) {
            return b;
        }
        try {
            ApplicationInfo r0_ApplicationInfo = r3_Context.getPackageManager().getApplicationInfo(r3_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (r0_ApplicationInfo != null) {
                String r0_String = r0_ApplicationInfo.metaData.getString("TA_APPKEY");
                if (r0_String != null) {
                    b = r0_String;
                    return r0_String;
                } else {
                    f.f("Could not read APPKEY meta-data from AndroidManifest.xml");
                }
            }
        } catch (Exception e) {
            f.f("Could not read APPKEY meta-data from AndroidManifest.xml");
        }
        return null;
    }

    public static String j(Context r3_Context) {
        String r0_String;
        try {
            ApplicationInfo r0_ApplicationInfo = r3_Context.getPackageManager().getApplicationInfo(r3_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (r0_ApplicationInfo != null) {
                Object r0_Object = r0_ApplicationInfo.metaData.get("InstallChannel");
                if (r0_Object != null) {
                    r0_String = r0_Object.toString();
                    return r0_String;
                } else {
                    f.f("Could not read InstallChannel meta-data from AndroidManifest.xml");
                }
            }
        } catch (Exception e) {
            f.f("Could not read InstallChannel meta-data from AndroidManifest.xml");
        }
        r0_String = null;
        return r0_String;
    }

    public static String k(Context r2_Context) {
        if (a(r2_Context, "android.permission.READ_PHONE_STATE")) {
            String r0_String = RContactStorage.PRIMARY_KEY;
            if (m(r2_Context)) {
                r0_String = ((TelephonyManager) r2_Context.getSystemService("phone")).getDeviceId();
            }
            if (r0_String != null) {
                return r0_String;
            }
            f.e("deviceId is null");
            return null;
        } else {
            f.f("Could not get permission of android.permission.READ_PHONE_STATE");
            return RContactStorage.PRIMARY_KEY;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String l(Context r5_Context) {
        /*
        r1 = "";
        r0 = r5.getPackageManager();	 //Catch:{ Exception -> 0x0016 }
        r2 = r5.getPackageName();	 //Catch:{ Exception -> 0x0016 }
        r3 = 0;
        r0 = r0.getPackageInfo(r2, r3);	 //Catch:{ Exception -> 0x0016 }
        r0 = r0.versionName;	 //Catch:{ Exception -> 0x0016 }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        r0 = "";
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x001a:
        r2 = f;
        r2.b(r1);
        goto L_0x0015;
    L_0x0020:
        r1 = move-exception;
        goto L_0x001a;
        */

    }

    public static boolean m(Context r3_Context) {
        return r3_Context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", r3_Context.getPackageName()) == 0;
    }

    public static String n(Context r3_Context) {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo == null || (!r0_NetworkInfo.isConnected())) {
            return null;
        }
        String r1_String = r0_NetworkInfo.getTypeName();
        String r0_String = r0_NetworkInfo.getExtraInfo();
        if (r1_String != null) {
            if (r1_String.equalsIgnoreCase("WIFI")) {
                return "WIFI";
            }
            if (r1_String.equalsIgnoreCase("MOBILE")) {
                return r0_String == null ? "MOBILE" : r0_String;
            } else {
                if (r0_String == null) {
                    return r1_String;
                }
                return r0_String;
            }
        }
        return null;
    }

    public static Integer o(Context r1_Context) {
        TelephonyManager r0_TelephonyManager = (TelephonyManager) r1_Context.getSystemService("phone");
        return r0_TelephonyManager != null ? Integer.valueOf(r0_TelephonyManager.getNetworkType()) : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String p(Context r5_Context) {
        /*
        r1 = "";
        r0 = r5.getPackageManager();	 //Catch:{ Exception -> 0x001c }
        r2 = r5.getPackageName();	 //Catch:{ Exception -> 0x001c }
        r3 = 0;
        r0 = r0.getPackageInfo(r2, r3);	 //Catch:{ Exception -> 0x001c }
        r0 = r0.versionName;	 //Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x0019;
    L_0x0013:
        r1 = r0.length();	 //Catch:{ Exception -> 0x0026 }
        if (r1 != 0) goto L_0x001b;
    L_0x0019:
        r0 = "unknown";
    L_0x001b:
        return r0;
    L_0x001c:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0020:
        r2 = f;
        r2.b(r1);
        goto L_0x001b;
    L_0x0026:
        r1 = move-exception;
        goto L_0x0020;
        */

    }

    public static int q(Context r1_Context) {
        return a.a() ? 1 : 0;
    }
}