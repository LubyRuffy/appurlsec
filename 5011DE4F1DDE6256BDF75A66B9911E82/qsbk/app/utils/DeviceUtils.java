package qsbk.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;
import org.apache.cordova.NetworkManager;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.group.SplashGroup;

public class DeviceUtils {
    private static String a;
    private static String b;

    static {
        a = null;
        b = null;
    }

    private static void a(byte[] r4_byteA) {
        try {
            String r0_String = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(r4_byteA))).getSerialNumber().toString();
            Log.e("mamx", "signNumber:" + r0_String);
            if (!"1315484290".equals(r0_String)) {
                Toast.makeText(QsbkApp.mContext, "\u6b64\u7cd7\u4e8b\u767e\u79d1\u4e3a\u76d7\u7248\uff0c\u8bf7\u4e0b\u8f7d\u6b63\u7248", 1).show();
            }
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    public static void addDeviceInfoToParam(Map<String, Object> r5_Map_String__Object) {
        r5_Map_String__Object.put("mobile_brand", Build.MODEL);
        Object[] r2_ObjectA = new Object[4];
        r2_ObjectA[0] = Build.MODEL;
        r2_ObjectA[1] = Build.BRAND;
        r2_ObjectA[2] = Build.DEVICE;
        r2_ObjectA[3] = Build.DISPLAY;
        r5_Map_String__Object.put("device_info", String.format("%s|%s|%s|%s", r2_ObjectA));
    }

    public static void addShortcut(Context r4_Context) {
        if (Build.MODEL.startsWith("MI") || Boolean.valueOf(r4_Context.getSharedPreferences("qiushibaike", 0).getBoolean("qiushibaike", false)).booleanValue()) {
        } else {
            Intent r0_Intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            r0_Intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(r4_Context, R.drawable.ic_launcher));
            r0_Intent.putExtra("android.intent.extra.shortcut.NAME", r4_Context.getString(R.string.app_name));
            r0_Intent.putExtra("duplicate", false);
            Parcelable r1_Parcelable = new Intent("android.intent.action.MAIN");
            r1_Parcelable.addCategory("android.intent.category.LAUNCHER");
            r1_Parcelable.setClass(r4_Context, SplashGroup.class);
            r0_Intent.putExtra("android.intent.extra.shortcut.INTENT", r1_Parcelable);
            r4_Context.sendBroadcast(r0_Intent);
            Editor r0_Editor = r4_Context.getSharedPreferences("qiushibaike", 0).edit();
            r0_Editor.putBoolean("qiushibaike", true);
            r0_Editor.commit();
        }
    }

    public static String getAndroidId() {
        if (b != null) {
            return b;
        }
        b = "IMEI_" + Md5.MD5("\"DEVICEID\":\"" + ((TelephonyManager) QsbkApp.mContext.getSystemService("phone")).getDeviceId() + "\"-" + "\"ANDROID_ID\":\"" + Secure.getString(QsbkApp.mContext.getContentResolver(), "android_id") + "\"");
        return b;
    }

    public static String getDeviceIdInfo() {
        if (a != null) {
            return a;
        }
        JSONObject r1_JSONObject = new JSONObject();
        TelephonyManager r0_TelephonyManager = (TelephonyManager) QsbkApp.mContext.getSystemService("phone");
        try {
            r1_JSONObject.put("DEVICEID", r0_TelephonyManager.getDeviceId());
            r1_JSONObject.put("SIMNO", r0_TelephonyManager.getSimSerialNumber());
            r1_JSONObject.put("IMSI", r0_TelephonyManager.getSubscriberId());
            r1_JSONObject.put("ANDROID_ID", Secure.getString(QsbkApp.mContext.getContentResolver(), "android_id"));
            Class r0_Class = Class.forName("android.os.SystemProperties");
            Class[] r3_ClassA = new Class[1];
            r3_ClassA[0] = String.class;
            Method r2_Method = r0_Class.getMethod("get", r3_ClassA);
            Object[] r3_ObjectA = new Object[1];
            r3_ObjectA[0] = "ro.serialno";
            r1_JSONObject.put("SERIAL", (String) r2_Method.invoke(r0_Class, r3_ObjectA));
            WifiManager r0_WifiManager = (WifiManager) QsbkApp.mContext.getSystemService(NetworkManager.WIFI);
            if (r0_WifiManager != null) {
                r1_JSONObject.put("MAC", r0_WifiManager.getConnectionInfo().getMacAddress());
            }
            r1_JSONObject.put("RANDOM", SharePreferenceUtils.getSharePreferencesValue("random"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a = r1_JSONObject.toString();
        return a;
    }

    public static String getPhoneBuildInfoJson() {
        JSONObject r1_JSONObject = new JSONObject();
        try {
            r1_JSONObject.put("manufacture", Build.MANUFACTURER);
            r1_JSONObject.put("model", Build.MODEL);
            r1_JSONObject.put("brand", Build.BRAND);
            r1_JSONObject.put("device", Build.DEVICE);
            r1_JSONObject.put("display", Build.DISPLAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r1_JSONObject.toString();
    }

    public static String getSDPath() {
        return Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory().toString() : RContactStorage.PRIMARY_KEY;
    }

    public static void getSingInfo() {
        try {
            a(QsbkApp.mContext.getPackageManager().getPackageInfo("qsbk.app", RContact.MM_CONTACTFLAG_FAVOURCONTACT).signatures[0].toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}