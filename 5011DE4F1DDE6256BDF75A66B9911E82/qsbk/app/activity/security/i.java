package qsbk.app.activity.security;

import android.os.Message;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

// compiled from: SecurityBindActivity.java
class i extends Thread {
    final /* synthetic */ Map a;
    final /* synthetic */ Message b;
    final /* synthetic */ SecurityBindActivity c;

    i(SecurityBindActivity r1_SecurityBindActivity, String r2_String, Map r3_Map, Message r4_Message) {
        this.c = r1_SecurityBindActivity;
        this.a = r3_Map;
        this.b = r4_Message;
        super(r2_String);
    }

    public void run() {
        try {
            SecurityBindActivity.a(this.c, new JSONObject(HttpClient.getIntentce().post(Constants.UPDATE_USERINFO, this.a)));
            Integer r0_Integer = (Integer) SecurityBindActivity.e(this.c).get("err");
            this.b.what = r0_Integer.intValue();
            if (r0_Integer.intValue() != 0) {
                this.b.obj = SecurityBindActivity.e(this.c).getString("err_msg");
            }
            if (this.b != null) {
                this.b.sendToTarget();
            }
        } catch (Throwable th) {
            if (this.b != null) {
                this.b.sendToTarget();
            }
        }
    }
}