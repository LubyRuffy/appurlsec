package com.qiubai.library.adview.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import com.qiubai.library.adview.AdViewTargeting;
import com.qiubai.library.adview.AdViewTargeting.RunMode;
import com.qiubai.library.adview.statistics.LogInterface;
import com.qiubai.library.adview.statistics.StatisticsBean;
import com.qq.e.comm.DownloadService;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.List;
import org.apache.cordova.NetworkManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.HttpUtils;

public class AdViewUtil {
    public static final String ADVIEW = "AdView SDK v2.0.4";
    public static final String ADVIEW_VER = "2.0.4";
    public static String CONFIG_HOST = null;
    public static final String COUNTCLICK = "click";
    public static final String COUNTFAIL = "fail";
    public static final String COUNTREQUEST = "req";
    public static final String COUNTSHOW = "show";
    public static final String COUNTSUCCESS = "suc";
    public static final int NETWORK_TYPE_ADBID = 998;
    public static final int NETWORK_TYPE_ADCHINA = 26;
    public static final int NETWORK_TYPE_ADFILL = 997;
    public static final int NETWORK_TYPE_ADLANTIS = 52;
    public static final int NETWORK_TYPE_ADMOB = 1;
    public static final int NETWORK_TYPE_ADSAGE = 42;
    public static final int NETWORK_TYPE_ADTOUCH = 32;
    public static final int NETWORK_TYPE_ADUU = 48;
    public static final int NETWORK_TYPE_ADVIEWAD = 28;
    public static final int NETWORK_TYPE_ADWO = 33;
    public static final int NETWORK_TYPE_AIRAD = 34;
    public static final int NETWORK_TYPE_APPMEDIA = 36;
    public static final int NETWORK_TYPE_BAIDU = 38;
    public static final int NETWORK_TYPE_CASEE = 24;
    public static final int NETWORK_TYPE_CUSTOMIZE = 999;
    public static final int NETWORK_TYPE_DOMOB = 30;
    public static final int NETWORK_TYPE_DoubleClick = 51;
    public static final int NETWORK_TYPE_FRACTAL = 44;
    public static final int NETWORK_TYPE_GREYSTRIP = 2;
    public static final int NETWORK_TYPE_INMOBI = 3;
    public static final int NETWORK_TYPE_IZPTEC = 41;
    public static final int NETWORK_TYPE_KUAIYOU = 23;
    public static final int NETWORK_TYPE_LMMOB = 45;
    public static final int NETWORK_TYPE_LSENSE = 39;
    public static final int NETWORK_TYPE_MDOTM = 4;
    public static final int NETWORK_TYPE_MILLENNIAL = 6;
    public static final int NETWORK_TYPE_MOBWIN = 46;
    public static final int NETWORK_TYPE_MOMARK = 49;
    public static final int NETWORK_TYPE_PUNCHBOX = 57;
    public static final int NETWORK_TYPE_SMAATO = 7;
    public static final int NETWORK_TYPE_SMARTAD = 29;
    public static final int NETWORK_TYPE_SUIZONG = 47;
    public static final int NETWORK_TYPE_TINMOO = 37;
    public static final int NETWORK_TYPE_UMENG = 43;
    public static final int NETWORK_TYPE_VPON = 31;
    public static final int NETWORK_TYPE_WIYUN = 25;
    public static final int NETWORK_TYPE_WOOBOO = 21;
    public static final int NETWORK_TYPE_WQ = 35;
    public static final int NETWORK_TYPE_YOUMI = 22;
    public static final int NETWORK_TYPE_YUNYUN = 53;
    public static final int NETWORK_TYPE_ZESTADZ = 5;
    public static final int NETWORK_TYPE_ZHIDIAN = 58;
    public static final String NEXTUPLOAD_TIME = "nextupload_time";
    public static final String PREFS_STRING_MULTI = "multi";
    public static final String PREFS_STRING_TIMESTAMP = "timestamp";
    public static final int REQUESTTIMEOUT = 25000;
    public static String SERVER_HOST = null;
    public static String TESTSERVER_HOST = null;
    public static final boolean TEST_SERVER = false;
    public static final String UPLOADINTERVAL_TIME = "uploadinterval_time";
    public static final String UPLOADREQ_TIME = "uploadreq_time";
    public static final int VERSION = 204;
    private static LogInterface a;
    public static int adfill_count;
    public static double adfill_precent;
    public static String appReport;
    private static double b;
    public static int common_count;
    public static int configVer;
    public static StringBuilder sb;
    public static List<StatisticsBean> statisticsList;
    public static String urlClick;
    public static String urlConfig;
    public static String urlImpression;

    static {
        configVer = 0;
        adfill_count = 0;
        common_count = 0;
        adfill_precent = 0.1d;
        SERVER_HOST = "report.adview.cn";
        CONFIG_HOST = "config.adview.cn";
        TESTSERVER_HOST = "test2012.adview.cn";
        statisticsList = null;
        sb = null;
        a = null;
        urlConfig = null;
        urlImpression = null;
        urlClick = null;
        appReport = null;
        b = -1.0d;
        initConfigUrls(SERVER_HOST);
        Log.i(AdViewUtil.class.getSimpleName(), "static");
    }

    private static void a(String r3_String) {
        if (a != null) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(new StringBuilder(String.valueOf(r3_String.toString())).append("\n").toString());
            a.onLogChange(sb);
            sb = null;
        }
    }

    public static boolean checkPermission(Context r2_Context, String r3_String) {
        return r2_Context.getPackageManager().checkPermission(r3_String, r2_Context.getPackageName()) == 0 ? true : TEST_SERVER;
    }

    public static String convertToHex(byte[] r7_byteA) {
        StringBuffer r5_StringBuffer = new StringBuffer();
        int r0i = 0;
        while (r0i < r7_byteA.length) {
            int r3i = (r7_byteA[r0i] >>> 4) & 15;
            int r2i = 0;
            while (true) {
                if (r3i < 0 || r3i > 9) {
                    r5_StringBuffer.append((char) (r3i - 10 + 97));
                } else {
                    r5_StringBuffer.append((char) (r3i + 48));
                }
                int r4i = r7_byteA[r0i] & 15;
                r3i = r2i + 1;
                if (r2i >= 1) {
                    r0i++;
                } else {
                    r2i = r3i;
                    r3i = r4i;
                }
            }
        }
        return r5_StringBuffer.toString();
    }

    public static int convertToScreenPixels(int r2i, double r3d) {
        return (int) ((r3d > 0.0d ? 1 : (r3d == 0.0d? 0 : -1)) > 0 ? ((double) r2i) * r3d : (double) r2i);
    }

    public static long currentSecond() {
        return System.currentTimeMillis() / 1000;
    }

    public static double getDensity(Activity r6_Activity) {
        if (b == -1.0d) {
            try {
                if (r6_Activity.getPackageManager().getApplicationInfo(r6_Activity.getPackageName(), 0).targetSdkVersion < NETWORK_TYPE_MDOTM) {
                    b = 1.0d;
                } else {
                    DisplayMetrics r0_DisplayMetrics = new DisplayMetrics();
                    r6_Activity.getWindowManager().getDefaultDisplay().getMetrics(r0_DisplayMetrics);
                    b = (double) r0_DisplayMetrics.density;
                }
            } catch (Exception e) {
                logError(RContactStorage.PRIMARY_KEY, e);
                b = 1.0d;
            }
        }
        return b;
    }

    public static String getIDByMAC(Context r3_Context) {
        try {
            return ((WifiManager) r3_Context.getSystemService(NetworkManager.WIFI)).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            logError(RContactStorage.PRIMARY_KEY, e);
            return null;
        }
    }

    public static String getImei(Context r3_Context) {
        TelephonyManager r0_TelephonyManager = (TelephonyManager) r3_Context.getSystemService("phone");
        StringBuffer r1_StringBuffer = new StringBuffer();
        try {
            if (checkPermission(r3_Context, "android.permission.READ_PHONE_STATE")) {
                r1_StringBuffer.append(r0_TelephonyManager.getDeviceId());
            }
            while (r1_StringBuffer.length() < 15) {
                r1_StringBuffer.append("0");
            }
        } catch (Exception e) {
            logError(RContactStorage.PRIMARY_KEY, e);
        }
        return r1_StringBuffer.toString().replace("null", "0000");
    }

    public static String getImsi(Context r3_Context) {
        TelephonyManager r0_TelephonyManager = (TelephonyManager) r3_Context.getSystemService("phone");
        StringBuffer r1_StringBuffer = new StringBuffer();
        try {
            if (checkPermission(r3_Context, "android.permission.READ_PHONE_STATE")) {
                r1_StringBuffer.append(r0_TelephonyManager.getSubscriberId() == null ? RContactStorage.PRIMARY_KEY : r0_TelephonyManager.getSubscriberId());
            }
            while (r1_StringBuffer.length() < 15) {
                r1_StringBuffer.append("0");
            }
        } catch (Exception e) {
            logError(RContactStorage.PRIMARY_KEY, e);
            r1_StringBuffer.append("000000000000000");
        }
        return r1_StringBuffer.toString();
    }

    public static String getNetworkType(Context r3_Context) {
        String r1_String = "0";
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        String r0_String;
        if (r0_NetworkInfo != null) {
            r0_String = r0_NetworkInfo.getTypeName();
            if (r0_String.equalsIgnoreCase(NetworkManager.WIFI)) {
                return "4";
            }
            r1_String = getImsi(r3_Context);
            if (r1_String.startsWith("46000") || r1_String.startsWith("46002")) {
                return "1";
            }
            if (r1_String.startsWith("46001")) {
                return DownloadService.V2;
            }
            if (r1_String.startsWith("46003")) {
                return "3";
            }
            return r0_String;
        } else {
            r0_String = getImsi(r3_Context);
            if (r0_String.startsWith("46000") || r0_String.startsWith("46002")) {
                return "1";
            }
            if (r0_String.startsWith("46001")) {
                return DownloadService.V2;
            }
            if (r0_String.startsWith("46003")) {
                return "3";
            }
            return r1_String;
        }
    }

    public static void initConfigUrls(String r2_String) {
        urlConfig = new StringBuilder(HttpUtils.http).append(CONFIG_HOST).append("/agent/agent1_android.php?appid=%s&appver=%s&client=0&simulator=%d&location=%s&time=%d&sdkver=%d").toString();
        urlImpression = new StringBuilder(HttpUtils.http).append(r2_String).append("/agent/agent2.php?appid=%s&nid=%s&type=%d&uuid=%s&country_code=%s&appver=%s&client=0&simulator=%d&keydev=%s&time=%s&sdkver=%d&configVer=%s").toString();
        urlClick = new StringBuilder(HttpUtils.http).append(r2_String).append("/agent/agent3.php?appid=%s&nid=%s&type=%d&uuid=%s&country_code=%s&appver=%s&client=0&simulator=%d&keydev=%s&time=%s&sdkver=%d&configVer=%s").toString();
        appReport = new StringBuilder(HttpUtils.http).append(r2_String).append("/agent/appReport.php?keyAdView=%s&deviceId=%s&deviceModel=%s&osVer=%s&resolution=%s&servicePro=%s&netType=%s&platform=%s&time=%s&sdkver=%d&configVer=%s").toString();
    }

    public static void logDebug(String r2_String) {
        if (AdViewTargeting.getRunMode() == RunMode.TEST) {
            Log.d(ADVIEW, r2_String);
            a(r2_String);
        }
    }

    public static void logError(String r2_String, Throwable r3_Throwable) {
        if (AdViewTargeting.getRunMode() == RunMode.TEST) {
            Log.e(ADVIEW, r2_String, r3_Throwable);
            a(r3_Throwable.toString());
        }
    }

    public static void logInfo(String r2_String) {
        if (AdViewTargeting.getRunMode() == RunMode.TEST) {
            Log.i(ADVIEW, r2_String);
            a(r2_String);
        }
    }

    public static void logWarn(String r2_String, Throwable r3_Throwable) {
        if (AdViewTargeting.getRunMode() == RunMode.TEST) {
            Log.w(ADVIEW, r2_String, r3_Throwable);
            a(r3_Throwable.toString());
        }
    }

    public static void setLogInterface(LogInterface r0_LogInterface) {
        a = r0_LogInterface;
    }

    public static void storeInfo(Context r8_Context, String r9_String, int r10i, String r11_String) {
        JSONArray r1_JSONArray = null;
        SharedPreferences r0_SharedPreferences = r8_Context.getSharedPreferences(r9_String, 0);
        Editor r5_Editor = r0_SharedPreferences.edit();
        JSONArray r0_JSONArray;
        JSONObject r1_JSONObject;
        try {
            JSONArray r0_JSONArray_2;
            JSONObject r1_JSONObject_2;
            String r0_String = r0_SharedPreferences.getString(PREFS_STRING_MULTI, null);
            JSONObject r0_JSONObject;
            if (r0_String == null) {
                r0_JSONObject = new JSONObject();
                r0_JSONObject.put(QsbkDatabase.TYPE, r10i);
                r0_JSONObject.put(r11_String, NETWORK_TYPE_ADMOB);
                r0_JSONArray_2 = r1_JSONArray;
                r1_JSONObject_2 = r0_JSONObject;
            } else {
                JSONArray r2_JSONArray = new JSONArray(r0_String);
                r0_JSONObject = r1_JSONArray;
                int r1i = 0;
                while (r1i < r2_JSONArray.length()) {
                    r0_JSONObject = r2_JSONArray.getJSONObject(r1i);
                    if (r0_JSONObject.getInt(QsbkDatabase.TYPE) == r10i) {
                        if (r0_JSONObject.has(r11_String)) {
                            r0_JSONObject.put(r11_String, r0_JSONObject.getInt(r11_String) + 1);
                        } else {
                            r0_JSONObject.put(r11_String, NETWORK_TYPE_ADMOB);
                        }
                        r1i = 1;
                        break;
                    } else {
                        r1i++;
                    }
                }
                r1i = 0;
                if (r1i == 0) {
                    r0_JSONObject = new JSONObject();
                    r0_JSONObject.put(QsbkDatabase.TYPE, r10i);
                    r0_JSONObject.put(r11_String, NETWORK_TYPE_ADMOB);
                    r2_JSONArray.put(r0_JSONObject);
                    r1_JSONObject_2 = r0_JSONObject;
                    r0_JSONArray_2 = r2_JSONArray;
                } else {
                    r1_JSONObject_2 = r0_JSONObject;
                    r0_JSONArray_2 = r2_JSONArray;
                }
            }
            if (r0_JSONArray_2 == null) {
                r0_JSONArray_2 = new JSONArray();
                r0_JSONArray_2.put(r1_JSONObject_2);
            }
            r5_Editor.putString(PREFS_STRING_MULTI, r0_JSONArray_2.toString());
            r5_Editor.commit();
        } catch (JSONException e) {
            logError("JSONException", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeLogtoFile(String r5_String, String r6_String) {
        /*
        r1 = 0;
        r0 = new java.util.Date;
        r0.<init>();
        r2 = new java.text.SimpleDateFormat;
        r3 = "yyyy-MM-dd HH:mm:ss.SSS";
        r2.<init>(r3);
        r0 = r2.format(r0);
        r2 = new java.lang.StringBuilder;
        r0 = java.lang.String.valueOf(r0);
        r2.<init>(r0);
        r0 = " ";
        r0 = r2.append(r0);
        r0 = r0.append(r6);
        r0 = r0.toString();
        r2 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r4 = android.os.Environment.getExternalStorageDirectory();
        r4 = r4.getAbsolutePath();
        r4 = java.lang.String.valueOf(r4);
        r3.<init>(r4);
        r4 = java.io.File.separator;
        r3 = r3.append(r4);
        r4 = "adview_log";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.<init>(r3);
        r3 = r2.exists();
        if (r3 != 0) goto L_0x0057;
    L_0x0054:
        r2.mkdirs();
    L_0x0057:
        r2 = new java.io.File;	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = android.os.Environment.getExternalStorageDirectory();	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = r4.getAbsolutePath();	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = java.lang.String.valueOf(r4);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r3.<init>(r4);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = java.io.File.separator;	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = "adview_log";
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = java.io.File.separator;	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r3 = r3.append(r5);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = ".txt";
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r3 = r3.toString();	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r3 = r2.exists();	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        if (r3 != 0) goto L_0x0096;
    L_0x0093:
        r2.createNewFile();	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
    L_0x0096:
        r3 = new java.io.FileWriter;	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r4 = 1;
        r3.<init>(r2, r4);	 //Catch:{ IOException -> 0x00b2, all -> 0x00c7 }
        r2 = new java.io.BufferedWriter;	 //Catch:{ IOException -> 0x00e6, all -> 0x00de }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x00e6, all -> 0x00de }
        r2.write(r0);	 //Catch:{ IOException -> 0x00e9, all -> 0x00e0 }
        r2.newLine();	 //Catch:{ IOException -> 0x00e9, all -> 0x00e0 }
        if (r2 == 0) goto L_0x00ac;
    L_0x00a9:
        r2.close();	 //Catch:{ IOException -> 0x00d9 }
    L_0x00ac:
        if (r3 == 0) goto L_0x00b1;
    L_0x00ae:
        r3.close();	 //Catch:{ IOException -> 0x00d9 }
    L_0x00b1:
        return;
    L_0x00b2:
        r0 = move-exception;
        r2 = r1;
    L_0x00b4:
        r0.printStackTrace();	 //Catch:{ all -> 0x00e3 }
        if (r1 == 0) goto L_0x00bc;
    L_0x00b9:
        r1.close();	 //Catch:{ IOException -> 0x00c2 }
    L_0x00bc:
        if (r2 == 0) goto L_0x00b1;
    L_0x00be:
        r2.close();	 //Catch:{ IOException -> 0x00c2 }
        goto L_0x00b1;
    L_0x00c2:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b1;
    L_0x00c7:
        r0 = move-exception;
        r3 = r1;
    L_0x00c9:
        if (r1 == 0) goto L_0x00ce;
    L_0x00cb:
        r1.close();	 //Catch:{ IOException -> 0x00d4 }
    L_0x00ce:
        if (r3 == 0) goto L_0x00d3;
    L_0x00d0:
        r3.close();	 //Catch:{ IOException -> 0x00d4 }
    L_0x00d3:
        throw r0;
    L_0x00d4:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00d3;
    L_0x00d9:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b1;
    L_0x00de:
        r0 = move-exception;
        goto L_0x00c9;
    L_0x00e0:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00c9;
    L_0x00e3:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00c9;
    L_0x00e6:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00b4;
    L_0x00e9:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00b4;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeLogtoFile(String r5_String, boolean r6z, String r7_String) {
        /*
        r1 = 0;
        r0 = new java.util.Date;
        r0.<init>();
        r2 = new java.text.SimpleDateFormat;
        r3 = "yyyy-MM-dd HH:mm:ss.SSS";
        r2.<init>(r3);
        r0 = r2.format(r0);
        if (r6 != 0) goto L_0x0015;
    L_0x0013:
        r0 = "";
    L_0x0015:
        r2 = new java.lang.StringBuilder;
        r0 = java.lang.String.valueOf(r0);
        r2.<init>(r0);
        r0 = " ";
        r0 = r2.append(r0);
        r0 = r0.append(r7);
        r0 = r0.toString();
        r2 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r4 = android.os.Environment.getExternalStorageDirectory();
        r4 = r4.getAbsolutePath();
        r4 = java.lang.String.valueOf(r4);
        r3.<init>(r4);
        r4 = java.io.File.separator;
        r3 = r3.append(r4);
        r4 = "adview_log";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.<init>(r3);
        r3 = r2.exists();
        if (r3 != 0) goto L_0x005b;
    L_0x0058:
        r2.mkdirs();
    L_0x005b:
        r2 = new java.io.File;	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r3 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = android.os.Environment.getExternalStorageDirectory();	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = r4.getAbsolutePath();	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = java.lang.String.valueOf(r4);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r3.<init>(r4);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = java.io.File.separator;	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = "adview_log";
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = java.io.File.separator;	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r3 = r3.append(r5);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = ".txt";
        r3 = r3.append(r4);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r3 = r3.toString();	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r3 = r2.exists();	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        if (r3 != 0) goto L_0x009a;
    L_0x0097:
        r2.createNewFile();	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
    L_0x009a:
        r3 = new java.io.FileWriter;	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r4 = 1;
        r3.<init>(r2, r4);	 //Catch:{ IOException -> 0x00b6, all -> 0x00cb }
        r2 = new java.io.BufferedWriter;	 //Catch:{ IOException -> 0x00ea, all -> 0x00e2 }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x00ea, all -> 0x00e2 }
        r2.write(r0);	 //Catch:{ IOException -> 0x00ed, all -> 0x00e4 }
        r2.newLine();	 //Catch:{ IOException -> 0x00ed, all -> 0x00e4 }
        if (r2 == 0) goto L_0x00b0;
    L_0x00ad:
        r2.close();	 //Catch:{ IOException -> 0x00dd }
    L_0x00b0:
        if (r3 == 0) goto L_0x00b5;
    L_0x00b2:
        r3.close();	 //Catch:{ IOException -> 0x00dd }
    L_0x00b5:
        return;
    L_0x00b6:
        r0 = move-exception;
        r2 = r1;
    L_0x00b8:
        r0.printStackTrace();	 //Catch:{ all -> 0x00e7 }
        if (r1 == 0) goto L_0x00c0;
    L_0x00bd:
        r1.close();	 //Catch:{ IOException -> 0x00c6 }
    L_0x00c0:
        if (r2 == 0) goto L_0x00b5;
    L_0x00c2:
        r2.close();	 //Catch:{ IOException -> 0x00c6 }
        goto L_0x00b5;
    L_0x00c6:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b5;
    L_0x00cb:
        r0 = move-exception;
        r3 = r1;
    L_0x00cd:
        if (r1 == 0) goto L_0x00d2;
    L_0x00cf:
        r1.close();	 //Catch:{ IOException -> 0x00d8 }
    L_0x00d2:
        if (r3 == 0) goto L_0x00d7;
    L_0x00d4:
        r3.close();	 //Catch:{ IOException -> 0x00d8 }
    L_0x00d7:
        throw r0;
    L_0x00d8:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00d7;
    L_0x00dd:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b5;
    L_0x00e2:
        r0 = move-exception;
        goto L_0x00cd;
    L_0x00e4:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00cd;
    L_0x00e7:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00cd;
    L_0x00ea:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00b8;
    L_0x00ed:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00b8;
        */

    }
}