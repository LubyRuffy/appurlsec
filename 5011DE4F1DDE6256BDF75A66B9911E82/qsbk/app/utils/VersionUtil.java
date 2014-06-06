package qsbk.app.utils;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;

public class VersionUtil {
    public static final String LATEST_CHECK_UPDATE_TIME = "latestCheckUpdateTime";
    private static String a;

    static {
        a = RContactStorage.PRIMARY_KEY;
    }

    private static boolean a() {
        String r2_String = SharePreferenceUtils.getSharePreferencesValue(LATEST_CHECK_UPDATE_TIME);
        long r0j = 0;
        if (!TextUtils.isEmpty(r2_String)) {
            r0j = Long.valueOf(r2_String).longValue();
        }
        if (r0j + 86400000 >= System.currentTimeMillis()) {
            return false;
        }
        SharePreferenceUtils.setSharePreferencesValue(LATEST_CHECK_UPDATE_TIME, String.valueOf(System.currentTimeMillis()));
        return true;
    }

    public static void getServiceVersion(Context r4_Context, boolean r5z) throws Exception {
        String r0_String = RContactStorage.PRIMARY_KEY;
        try {
            int r0i = r4_Context.getPackageManager().getPackageInfo(r4_Context.getPackageName(), 0).versionCode;
            String r1_String = r4_Context.getPackageManager().getPackageInfo(r4_Context.getPackageName(), 0).versionName;
            Constants.localVersion = r0i;
            Constants.localVersionName = r1_String;
        } catch (Exception e) {
            e.printStackTrace();
        }
        r0_String = HttpClient.getIntentce().get(Constants.APPINFO);
        if (!TextUtils.isEmpty(r0_String)) {
            JSONObject r1_JSONObject = new JSONObject(r0_String);
            Constants.serverVersion = r1_JSONObject.getInt("build");
            Constants.serviceVersionName = r1_JSONObject.getString("version");
            Constants.change = r1_JSONObject.getString("change");
            if (!r1_JSONObject.isNull(com.tencent.tauth.Constants.PARAM_URL)) {
                Constants.UPDATE_URL = r1_JSONObject.getString(com.tencent.tauth.Constants.PARAM_URL);
            }
            if (r5z || r1_JSONObject.isNull("_logs")) {
            } else {
                a = r1_JSONObject.getString("_logs");
                if (!TextUtils.isEmpty(a)) {
                    QsbkApp.reportAppInfo();
                    Message r0_Message = QsbkApp.reportHandler.obtainMessage();
                    r0_Message.obj = a;
                    r0_Message.sendToTarget();
                }
            }
        }
    }

    public static boolean isNeedUpdate(Context r4_Context) {
        boolean r0z = true;
        try {
            if (HttpUtils.isWifi(r4_Context)) {
                getServiceVersion(r4_Context, false);
                if (Constants.serverVersion > Constants.localVersion) {
                    return r0z;
                }
                r0z = false;
                return r0z;
            } else {
                if (a()) {
                    getServiceVersion(r4_Context, false);
                    if (Constants.serverVersion <= Constants.localVersion) {
                        r0z = false;
                    }
                    return r0z;
                }
                r0z = false;
                return r0z;
            }
        } catch (Exception e) {
            DebugUtil.error("\u7248\u672c\u66f4\u65b0\u68c0\u67e5\u5931\u8d25");
            e.printStackTrace();
        }
    }

    public static boolean manualCheck(Context r4_Context) {
        try {
            getServiceVersion(r4_Context, true);
            if (Constants.serverVersion > Constants.localVersion) {
                return true;
            }
        } catch (Exception e) {
            DebugUtil.error("\u7248\u672c\u66f4\u65b0\u68c0\u67e5\u5931\u8d25");
            e.printStackTrace();
        }
        return false;
    }
}