package qsbk.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.util.Properties;
import qsbk.app.utils.Md5;
import qsbk.app.utils.StringUtils;

public class AppContext extends Application {
    public static final int NETTYPE_CMNET = 3;
    public static final int NETTYPE_CMWAP = 2;
    public static final int NETTYPE_WIFI = 1;
    public static final int PAGE_SIZE = 20;

    public static boolean isMethodsCompat(int r1i) {
        return VERSION.SDK_INT >= r1i;
    }

    public boolean containsProperty(String r2_String) {
        return getProperties().containsKey(r2_String);
    }

    public String getAppId() {
        String r0_String = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (!StringUtils.isEmpty(r0_String)) {
            return r0_String;
        }
        r0_String = "IMEI_" + Md5.MD5(((TelephonyManager) QsbkApp.mContext.getSystemService("phone")).getDeviceId() + "-" + Secure.getString(QsbkApp.mContext.getContentResolver(), "android_id"));
        setProperty(AppConfig.CONF_APP_UNIQUEID, r0_String);
        return r0_String;
    }

    public int getNetworkType() {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (r0_NetworkInfo == null) {
            return 0;
        }
        int r0i;
        int r3i = r0_NetworkInfo.getType();
        if (r3i == 0) {
            String r0_String = r0_NetworkInfo.getExtraInfo();
            if (!StringUtils.isEmpty(r0_String)) {
                r0i = r0_String.toLowerCase().equals("cmnet") ? NETTYPE_CMNET : NETTYPE_CMWAP;
            }
            r0i = 0;
        } else {
            if (r3i == 1) {
                r0i = 1;
            }
            r0i = 0;
        }
        return r0i;
    }

    public PackageInfo getPackageInfo() {
        PackageInfo r0_PackageInfo;
        try {
            r0_PackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
            r0_PackageInfo = null;
        }
        return r0_PackageInfo == null ? new PackageInfo() : r0_PackageInfo;
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public String getProperty(String r2_String) {
        return AppConfig.getAppConfig(this).get(r2_String);
    }

    public boolean isAudioNormal() {
        return ((AudioManager) getSystemService("audio")).getRingerMode() == 2;
    }

    public boolean isNetworkConnected() {
        NetworkInfo r0_NetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return r0_NetworkInfo != null && r0_NetworkInfo.isConnectedOrConnecting();
    }

    public void onCreate() {
        super.onCreate();
    }

    public void removeProperty(String ... r2_StringA) {
        AppConfig.getAppConfig(this).remove(r2_StringA);
    }

    public void setProperties(Properties r2_Properties) {
        AppConfig.getAppConfig(this).set(r2_Properties);
    }

    public void setProperty(String r2_String, String r3_String) {
        AppConfig.getAppConfig(this).set(r2_String, r3_String);
    }
}