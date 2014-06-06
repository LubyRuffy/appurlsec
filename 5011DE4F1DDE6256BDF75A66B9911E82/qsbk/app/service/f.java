package qsbk.app.service;

import android.os.Message;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: VerifyUserInfoService.java
class f extends Thread {
    final /* synthetic */ VerifyUserInfoService a;

    f(VerifyUserInfoService r1_VerifyUserInfoService, String r2_String) {
        this.a = r1_VerifyUserInfoService;
        super(r2_String);
    }

    public void run() {
        Message r0_Message;
        Message r1_Message;
        Map r0_Map = new HashMap();
        r0_Map.put(LocaleUtil.INDONESIAN, QsbkApp.currentUser.userId);
        try {
            String r0_String = HttpClient.getIntentce().post(Constants.VERIFY, r0_Map);
            DebugUtil.debug("\u9a8c\u8bc1\u7528\u6237\u7ed3\u679c\uff1a" + r0_String);
            JSONObject r1_JSONObject = new JSONObject(r0_String);
            int r0i = r1_JSONObject.getInt("err");
            DebugUtil.debug("\u7528\u6237\u9a8c\u8bc1\u7ed3\u679c:" + r0i);
            boolean r2z = false;
            if (r0i == 0) {
                SharePreferenceUtils.setSharePreferencesValue("lastVerifyTime", System.currentTimeMillis() + RContactStorage.PRIMARY_KEY);
                this.a.updateUserInfo(r1_JSONObject);
                this.a.c();
                r0_Message = this.a.b.obtainMessage(1, Boolean.valueOf(r2z));
                if (r0_Message == null) {
                    r0_Message.sendToTarget();
                }
            } else {
                r0_Message = this.a.b.obtainMessage(0, Boolean.valueOf(r2z));
                if (r0_Message == null) {
                } else {
                    r0_Message.sendToTarget();
                }
            }
        } catch (QiushibaikeException e) {
            r1_Message = this.a.b.obtainMessage(0, Boolean.valueOf(false));
            e.printStackTrace();
            r0_Message = r1_Message;
        } catch (Exception e_2) {
            r1_Message = this.a.b.obtainMessage(0, Boolean.valueOf(false));
            e_2.printStackTrace();
            r0_Message = r1_Message;
        }
    }
}