package qsbk.app.service;

import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.security.SecurityBindActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.CrashHandler;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: VerifyUserInfoService.java
class h extends Thread {
    final /* synthetic */ VerifyUserInfoService a;

    h(VerifyUserInfoService r1_VerifyUserInfoService, String r2_String) {
        this.a = r1_VerifyUserInfoService;
        super(r2_String);
    }

    public void run() {
        Object r1_Object = null;
        try {
            JSONObject r2_JSONObject = new JSONObject(HttpClient.getIntentce().get(Constants.USERINFO));
            if (((Integer) r2_JSONObject.get("err")).intValue() == 0) {
                String r0_String;
                JSONObject r0_JSONObject = r2_JSONObject.getJSONObject(OneProfileActivity.USER);
                if (!r0_JSONObject.isNull(QsbkDatabase.USER_EMAIL)) {
                    r0_String = r0_JSONObject.getString(QsbkDatabase.USER_EMAIL);
                    if (TextUtils.isEmpty(r0_String) || "null".equals(r0_String)) {
                        r0_JSONObject = r2_JSONObject.getJSONObject("userdata");
                    } else {
                        QsbkApp.currentUser.email = r0_String;
                    }
                }
                r0_JSONObject = r2_JSONObject.getJSONObject("userdata");
                if (!r0_JSONObject.isNull(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                    String r2_String = r0_JSONObject.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
                    if (TextUtils.isEmpty(r2_String) || "null".equals(r2_String)) {
                    } else {
                        QsbkApp.currentUser.wb = r2_String;
                    }
                }
                if (!r0_JSONObject.isNull(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                    r0_String = r0_JSONObject.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                    if (TextUtils.isEmpty(r0_String) || "null".equals(r0_String)) {
                        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    } else {
                        QsbkApp.currentUser.qq = r0_String;
                    }
                }
                SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
            }
        } catch (JSONException e) {
        } catch (QiushibaikeException e_2) {
            e_2.printStackTrace();
        } catch (Exception e_3) {
            Throwable r0_Throwable = e_3;
            if (VerifyUserInfoService.a() < 3) {
                CrashHandler r2_CrashHandler = CrashHandler.getInstance();
                Thread r3_Thread = Thread.currentThread();
                Object[] r6_ObjectA = new Object[1];
                r6_ObjectA[0] = r1_Object;
                r2_CrashHandler.reportGuessException(r3_Thread, r0_Throwable, XListViewHeader.STATE_REFRESHING, String.format("User info from net: %s", r6_ObjectA));
            }
        }
        if (TextUtils.isEmpty(QsbkApp.currentUser.wb) && TextUtils.isEmpty(QsbkApp.currentUser.qq) && TextUtils.isEmpty(QsbkApp.currentUser.email)) {
            Intent r0_Intent = new Intent(QsbkApp.mContext, SecurityBindActivity.class);
            r0_Intent.setFlags(268435456);
            r0_Intent.putExtra(OneProfileActivity.SOURCE, "olduser");
            QsbkApp.mContext.startActivity(r0_Intent);
        }
    }
}