package qsbk.app.gdtad;

import android.content.SharedPreferences;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.QsbkApp;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;

public class FeedsAdUtils {
    private static boolean a(int r7i) {
        SharedPreferences r1_SharedPreferences = QsbkApp.mContext.getSharedPreferences("associative_memory", 0);
        int r2i = r1_SharedPreferences.getInt("_sub_value", 0);
        Log.i("Test", "sub_value_2:" + r2i);
        Log.i("Test", "main_condition_value_2:" + r1_SharedPreferences.getString("_main_condition", RContactStorage.PRIMARY_KEY));
        return r2i > r7i;
    }

    private static boolean a(String r5_String) {
        String r1_String = QsbkApp.mContext.getSharedPreferences("associative_memory", 0).getString("_main_condition", RContactStorage.PRIMARY_KEY);
        Log.i("Test", "main_condition_value_1:" + r1_String);
        return r1_String.equalsIgnoreCase(r5_String);
    }

    public static void addMain_condition(String r4_String) {
        SharedPreferences r0_SharedPreferences = QsbkApp.mContext.getSharedPreferences("associative_memory", 0);
        r0_SharedPreferences.edit().putString("_main_condition", r4_String).commit();
        r0_SharedPreferences.edit().putInt("_sub_value", r0_SharedPreferences.getInt("_sub_value", 0) + 1).commit();
        Log.i("Test", "addMain_condition:" + r4_String);
    }

    public static int getMaxFeedAdShowTime() {
        try {
            int r1i = Integer.parseInt(SharePreferenceUtils.getSharePreferencesValue("feed_ad_max_time"));
            return r1i < 9 ? REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY : r1i;
        } catch (Exception e) {
            return HttpClient.RESP_CODE_LOCAL_ERROR;
        }
    }

    public static boolean isOver(String r3_String, int r4i) {
        if (a(r3_String)) {
            return a(r4i);
        }
        QsbkApp.mContext.getSharedPreferences("associative_memory", 0).edit().putInt("_sub_value", 0).commit();
        return false;
    }

    public static void setMaxFeedAdShowTime(int r1i) {
        if (r1i < 9) {
            r1i = 9;
        }
        SharePreferenceUtils.setSharePreferencesValue("feed_ad_max_time", r1i);
    }
}