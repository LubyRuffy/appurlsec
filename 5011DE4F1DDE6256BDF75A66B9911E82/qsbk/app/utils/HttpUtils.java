package qsbk.app.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.Uri;
import android.net.wifi.WifiManager;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Iterator;
import java.util.List;
import org.apache.cordova.NetworkManager;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.params.HttpParams;
import qsbk.app.QsbkApp;

public class HttpUtils {
    public static final int HTTP_OK_CODE = 202;
    public static final String NET = "net";
    public static final String PROXY_IP = "10.0.0.172";
    public static final int TYPE_NET = 2;
    public static final int TYPE_UNKNOWN = 3;
    public static final int TYPE_WAP = 1;
    public static final String WAP = "wap";
    public static final String http = "http://";
    public static final String https = "https://";

    public static String buildParamListInHttpRequest(List<NameValuePair> r3_List_NameValuePair) {
        StringBuffer r2_StringBuffer = new StringBuffer();
        int r1i = 0;
        while (r1i < r3_List_NameValuePair.size()) {
            r2_StringBuffer.append(((NameValuePair) r3_List_NameValuePair.get(r1i)).getName());
            r2_StringBuffer.append("=");
            r2_StringBuffer.append(((NameValuePair) r3_List_NameValuePair.get(r1i)).getValue());
            if (r1i < r3_List_NameValuePair.size() - 1) {
                r2_StringBuffer.append("&");
            }
            r1i++;
        }
        return r2_StringBuffer.toString();
    }

    public static void fillProxy(Context r6_Context, HttpParams r7_HttpParams) {
        if (!((WifiManager) r6_Context.getSystemService(NetworkManager.WIFI)).isWifiEnabled()) {
            int r0i = getNetType(r6_Context);
            if (r0i == 1) {
                r7_HttpParams.setParameter("http.route.default-proxy", new HttpHost(PROXY_IP, 80));
            } else if (r0i != 2) {
                Cursor r0_Cursor;
                try {
                    r0_Cursor = r6_Context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                } catch (Exception e) {
                }
                if (r0_Cursor == null || (!r0_Cursor.moveToNext())) {
                } else {
                    String r0_String = r0_Cursor.getString(r0_Cursor.getColumnIndex("proxy"));
                    if (r0_String == null || r0_String.trim().length() <= 0) {
                    } else {
                        r7_HttpParams.setParameter("http.route.default-proxy", new HttpHost(r0_String, 80));
                    }
                }
            }
        }
    }

    public static String filterXmlTags(String r5_String, List<String> r6_List_String) {
        if (r6_List_String != null) {
            Iterator r1_Iterator = r6_List_String.iterator();
            while (r1_Iterator.hasNext()) {
                String r0_String = (String) r1_Iterator.next();
                String r2_String = "<" + r0_String + ">";
                r5_String = r5_String.replaceAll(r2_String, RContactStorage.PRIMARY_KEY).replaceAll("</" + r0_String + ">", RContactStorage.PRIMARY_KEY);
            }
        }
        return r5_String;
    }

    public static int getNetType(Context r3_Context) {
        if (r3_Context == null) {
            return 3;
        }
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo == null || r0_NetworkInfo.getExtraInfo() == null) {
            return 3;
        }
        String r0_String = r0_NetworkInfo.getExtraInfo();
        if (r0_String.endsWith(WAP)) {
            return TYPE_WAP;
        }
        if (r0_String.endsWith(NET)) {
            return TYPE_NET;
        }
        return 3;
    }

    public static String getNetworkType(Context r3_Context) {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r3_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo != null) {
            int r1i = r0_NetworkInfo.getType();
            if (r1i == 0) {
                String r0_String = r0_NetworkInfo.getSubtypeName();
                return r0_String != null ? r0_String.replace(" ", RContactStorage.PRIMARY_KEY) : "GPRS";
            } else if (r1i == 1) {
                return "WIFI";
            }
        }
        return "UNKNOWN";
    }

    public static String getTrafficStats() {
        int r1i = 0;
        int r3i;
        try {
            int r3i_2;
            PackageManager r4_PackageManager = QsbkApp.mContext.getPackageManager();
            r3i_2 = (TrafficStats.getMobileRxBytes() > -1 ? 1 : (TrafficStats.getMobileRxBytes() == -1? 0 : -1)) != 0 ? (int) ((TrafficStats.getMobileRxBytes() / 1024) / 1024) : 0;
            Iterator r4_Iterator = r4_PackageManager.getInstalledApplications(0).iterator();
            while (r4_Iterator.hasNext()) {
                int r0i;
                ApplicationInfo r0_ApplicationInfo = (ApplicationInfo) r4_Iterator.next();
                int r5i = r0_ApplicationInfo.uid;
                r0i = ((!r0_ApplicationInfo.packageName.equals("qsbk.app")) || TrafficStats.getUidRxBytes(r5i) == -1) ? r1i : (int) ((TrafficStats.getUidRxBytes(r5i) / 1024) / 1024);
                r1i = r0i;
            }
            return "qb:" + r1i + " all:" + r3i_2;
        } catch (Exception e) {
            return RContactStorage.PRIMARY_KEY;
        }
    }

    public static boolean isHttp(String r1_String) {
        return r1_String == null ? false : r1_String.startsWith(http);
    }

    public static boolean isHttps(String r1_String) {
        return r1_String == null ? false : r1_String.startsWith(https);
    }

    public static boolean isNetworkConnected(Context r1_Context) {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r1_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        return r0_NetworkInfo != null ? r0_NetworkInfo.isAvailable() : false;
    }

    public static boolean isWifi(Context r2_Context) {
        if (r2_Context == null) {
            return false;
        }
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r2_Context.getSystemService("connectivity")).getActiveNetworkInfo();
        return r0_NetworkInfo != null ? r0_NetworkInfo.getTypeName().toLowerCase().endsWith(NetworkManager.WIFI) : false;
    }

    public static boolean netIsAvailable() {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) QsbkApp.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return r0_NetworkInfo != null ? r0_NetworkInfo.isConnected() : false;
    }

    public static int safePositiveInteger(String r2_String) {
        try {
            int r1i = Integer.parseInt(r2_String);
            return r1i < 0 ? 0 : r1i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long safePositiveLong(String r5_String) {
        try {
            long r2j = Long.parseLong(r5_String);
            return (r2j > 0 ? 1 : (r2j == 0? 0 : -1)) < 0 ? 0 : r2j;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}