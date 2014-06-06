package qsbk.app.activity;

import android.os.Message;
import java.net.URLEncoder;
import org.json.JSONObject;
import qsbk.app.bean.Base;
import qsbk.app.utils.HttpClient;

// compiled from: RegisterActivity.java
class ck extends Thread {
    final /* synthetic */ RegisterActivity a;

    ck(RegisterActivity r1_RegisterActivity, String r2_String) {
        this.a = r1_RegisterActivity;
        super(r2_String);
    }

    public void run() {
        Message r1_Message;
        r1_Message = this.a.getHanler().obtainMessage();
        try {
            this.a.x = new JSONObject(HttpClient.getIntentce().get("http://m2.qiushibaike.com/user/v2/signup?login=" + URLEncoder.encode(this.a.w.getText().toString().trim(), Base.UTF8)));
            Integer r0_Integer = (Integer) this.a.x.get("err");
            r1_Message.what = r0_Integer.intValue();
            if (r0_Integer.intValue() != 0) {
                r1_Message.obj = this.a.x.getString("err_msg");
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