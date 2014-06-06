package qsbk.app.activity;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

// compiled from: UserSetting.java
class dk extends Thread {
    final /* synthetic */ UserSetting a;

    dk(UserSetting r1_UserSetting, String r2_String) {
        this.a = r1_UserSetting;
        super(r2_String);
    }

    public void run() {
        try {
            Map r0_Map = new HashMap();
            r0_Map.put(QsbkDatabase.ACTION, "logout");
            HttpClient.getIntentce().post(Constants.PUSH_DOMAINS, r0_Map);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        }
    }
}