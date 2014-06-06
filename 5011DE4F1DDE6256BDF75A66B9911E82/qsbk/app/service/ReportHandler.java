package qsbk.app.service;

import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.model.AppInfo;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Md5;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.XOR;

public class ReportHandler extends Handler {
    public static final String LATEST_REPORT_TIME = "latestReportTime";

    public ReportHandler(Looper r1_Looper) {
        super(r1_Looper);
    }

    private String a(String r4_String) {
        return XOR.encode(HttpUtils.getTrafficStats(), Md5.MD5(r4_String + DeviceUtils.getAndroidId()).toUpperCase()).replaceAll("\\s", RContactStorage.PRIMARY_KEY);
    }

    private static boolean a() {
        String r2_String = SharePreferenceUtils.getSharePreferencesValue(LATEST_REPORT_TIME);
        long r0j = 0;
        if (!TextUtils.isEmpty(r2_String)) {
            r0j = Long.valueOf(r2_String).longValue();
        }
        if (r0j + 259200000 >= System.currentTimeMillis()) {
            return false;
        }
        SharePreferenceUtils.setSharePreferencesValue(LATEST_REPORT_TIME, String.valueOf(System.currentTimeMillis()));
        return true;
    }

    private String b() {
        StringBuffer r2_StringBuffer = new StringBuffer();
        List r3_List = QsbkApp.mContext.getPackageManager().getInstalledPackages(0);
        int r1i = 0;
        while (r1i < r3_List.size()) {
            PackageInfo r0_PackageInfo = (PackageInfo) r3_List.get(r1i);
            if ((r0_PackageInfo.applicationInfo.flags & 1) == 0) {
                r2_StringBuffer.append(new AppInfo(r0_PackageInfo.applicationInfo.loadLabel(QsbkApp.mContext.getPackageManager()).toString(), r0_PackageInfo.packageName, r0_PackageInfo.versionName).toString());
                r2_StringBuffer.append(";");
            }
            r1i++;
        }
        return r2_StringBuffer.toString();
    }

    private String b(String r4_String) {
        return XOR.encode(b(), Md5.MD5(r4_String + DeviceUtils.getAndroidId()).toUpperCase()).replaceAll("\\s", RContactStorage.PRIMARY_KEY);
    }

    public void handleMessage(Message r8_Message) {
        try {
            JSONObject r2_JSONObject = new JSONObject((String) r8_Message.obj);
            JSONArray r3_JSONArray = r2_JSONObject.names();
            if (a()) {
                int r1i = 0;
                while (r1i < r3_JSONArray.length()) {
                    String r4_String = r3_JSONArray.getString(r1i);
                    String r0_String = RContactStorage.PRIMARY_KEY;
                    if (r4_String.equals("001")) {
                        r0_String = b(r2_JSONObject.getString(r4_String));
                    } else if (r4_String.equals("002")) {
                        r0_String = a(r2_JSONObject.getString(r4_String));
                    }
                    JSONObject r5_JSONObject = new JSONObject();
                    r5_JSONObject.put(LocaleUtil.INDONESIAN, r4_String);
                    r5_JSONObject.put("data", r0_String);
                    try {
                        HttpClient.getIntentce().post(Constants.APP_LOGS, r5_JSONObject.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    r1i++;
                }
            }
        } catch (JSONException e_2) {
            e_2.printStackTrace();
        }
    }
}