package qsbk.app.activity;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.manager.PushMessageManager;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;

// compiled from: LoginActivity.java
class ay extends Thread {
    final /* synthetic */ LoginActivity a;

    ay(LoginActivity r1_LoginActivity, String r2_String) {
        this.a = r1_LoginActivity;
        super(r2_String);
    }

    public void run() {
        Map r0_Map;
        try {
            r0_Map = new HashMap();
            r0_Map.put(QsbkDatabase.TOKEN, PushMessageManager.getBaiduBindId());
            LogUtil.d("baiduid_id:" + PushMessageManager.getBaiduBindId());
            r0_Map.put(QsbkDatabase.ACTION, QsbkDatabase.LOGIN);
            HttpClient.getIntentce().post(Constants.PUSH_DOMAINS, r0_Map);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        }
        try {
            r0_Map = new HashMap();
            DeviceUtils.addDeviceInfoToParam(r0_Map);
            String r1_String = Constants.UPDATE_USERINFO_1;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = QsbkApp.currentUser.userId;
            LogUtil.d("on loin success response:" + HttpClient.getIntentce().post(String.format(r1_String, r2_ObjectA), r0_Map));
        } catch (QiushibaikeException e_2) {
            QiushibaikeException r0_QiushibaikeException = e_2;
            LogUtil.d("statusCode:" + r0_QiushibaikeException.getStatusCode());
            r0_QiushibaikeException.printStackTrace();
        }
    }
}