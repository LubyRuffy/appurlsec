package qsbk.app.manager;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

// compiled from: PushMessageManager.java
final class a extends Thread {
    final /* synthetic */ String a;

    a(String r1_String, String r2_String) {
        this.a = r2_String;
        super(r1_String);
    }

    public void run() {
        try {
            Map r0_Map = new HashMap();
            r0_Map.put(QsbkDatabase.ACTION, this.a);
            r0_Map.put(QsbkDatabase.TOKEN, PushMessageManager.getBaiduBindId());
            if (new JSONObject(HttpClient.getIntentce().post(Constants.PUSH_DOMAINS, r0_Map)).getInt("err") == 0) {
                if (this.a.equals("start")) {
                    PushMessageManager.setLastBindedPushVersion(Constants.localVersionName);
                } else if (this.a.equals("stop")) {
                    PushMessageManager.setLastBindedPushVersion(RContactStorage.PRIMARY_KEY);
                }
            }
        } catch (JSONException e) {
        } catch (QiushibaikeException e_2) {
            e_2.printStackTrace();
        }
    }
}