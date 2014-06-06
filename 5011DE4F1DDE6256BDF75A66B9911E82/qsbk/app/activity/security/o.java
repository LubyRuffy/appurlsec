package qsbk.app.activity.security;

import android.os.Message;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

// compiled from: SecurityBindActivity.java
class o extends Thread {
    final /* synthetic */ SecurityBindActivity a;

    o(SecurityBindActivity r1_SecurityBindActivity, String r2_String) {
        this.a = r1_SecurityBindActivity;
        super(r2_String);
    }

    public void run() {
        Message r1_Message;
        r1_Message = this.a.d.obtainMessage();
        try {
            SecurityBindActivity.a(this.a, new JSONObject(HttpClient.getIntentce().post(Constants.REGISTER, this.a.c)));
            Integer r0_Integer = (Integer) SecurityBindActivity.e(this.a).get("err");
            r1_Message.what = r0_Integer.intValue();
            if (r0_Integer.intValue() != 0) {
                r1_Message.obj = SecurityBindActivity.e(this.a).getString("err_msg");
            }
            if (r1_Message != null) {
                r1_Message.sendToTarget();
            }
        } catch (Throwable th) {
            if (r1_Message != null) {
                r1_Message.sendToTarget();
            }
        }
    }
}