package qsbk.app.service;

import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: VerifyUserInfoService.java
class g extends Thread {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ VerifyUserInfoService b;

    g(VerifyUserInfoService r1_VerifyUserInfoService, String r2_String, JSONObject r3_JSONObject) {
        this.b = r1_VerifyUserInfoService;
        this.a = r3_JSONObject;
        super(r2_String);
    }

    public void run() {
        try {
            JSONObject r0_JSONObject = this.a.getJSONObject(OneProfileActivity.USER);
            UserInfo r1_UserInfo = new UserInfo(SharePreferenceUtils.getSharePreferencesValue("loginUser"));
            r1_UserInfo.userId = r0_JSONObject.getString(LocaleUtil.INDONESIAN);
            r1_UserInfo.userName = r0_JSONObject.getString(QsbkDatabase.LOGIN);
            r1_UserInfo.userIcon = r0_JSONObject.getString(QsbkDatabase.ICON);
            if (r0_JSONObject.has(QsbkDatabase.USER_EMAIL)) {
                String r0_String = r0_JSONObject.getString(QsbkDatabase.USER_EMAIL);
                if (TextUtils.isEmpty(r0_String) || r0_String.equals("null")) {
                    r0_String = RContactStorage.PRIMARY_KEY;
                    r1_UserInfo.email = r0_String;
                } else {
                    r1_UserInfo.email = r0_String;
                }
            }
            if (this.a.has("userdata")) {
                r0_JSONObject = this.a.getJSONObject("userdata").getJSONObject("articles");
                r1_UserInfo.t_all = r0_JSONObject.getInt(QsbkDatabase.T_ALL);
                r1_UserInfo.t = r0_JSONObject.getInt(QsbkDatabase.T);
                r1_UserInfo.a = r0_JSONObject.getInt(QsbkDatabase.A);
            }
            QsbkApp.currentUser = r1_UserInfo;
            SharePreferenceUtils.setSharePreferencesValue("loginUser", r1_UserInfo.toString());
            DebugUtil.debug("\u65b0\u7528\u6237\u4fdd\u5b58\u6210\u529f");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}