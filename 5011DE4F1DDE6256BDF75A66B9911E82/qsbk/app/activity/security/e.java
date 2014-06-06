package qsbk.app.activity.security;

import android.os.Message;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

// compiled from: EmailBindActivity.java
class e extends Thread {
    final /* synthetic */ EmailBindActivity a;

    e(EmailBindActivity r1_EmailBindActivity, String r2_String) {
        this.a = r1_EmailBindActivity;
        super(r2_String);
    }

    public void run() {
        Message r1_Message;
        r1_Message = this.a.h.obtainMessage();
        try {
            this.a.f = new JSONObject(HttpClient.getIntentce().get(Constants.REGISTER + this.a.e()));
            Integer r0_Integer = (Integer) this.a.f.get("err");
            r1_Message.what = r0_Integer.intValue();
            if (r0_Integer.intValue() != 0) {
                r1_Message.obj = this.a.f.getString("err_msg");
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