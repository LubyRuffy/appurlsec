package qsbk.app.activity;

import android.os.Message;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: LoginActivity.java
class ax extends Thread {
    final /* synthetic */ HashMap a;
    final /* synthetic */ LoginActivity b;

    ax(LoginActivity r1_LoginActivity, String r2_String, HashMap r3_HashMap) {
        this.b = r1_LoginActivity;
        this.a = r3_HashMap;
        super(r2_String);
    }

    public void run() {
        Message r1_Message;
        r1_Message = this.b.F.obtainMessage();
        try {
            this.b.x = new JSONObject(HttpClient.getIntentce().post(Constants.THIRDPARTY_LOGIN, this.a));
            Integer r0_Integer = (Integer) this.b.x.get("err");
            r1_Message.what = r0_Integer.intValue();
            if (r0_Integer.intValue() != 0) {
                r1_Message.obj = this.b.x.getString("err_msg");
            }
            if (r1_Message != null) {
                r1_Message.sendToTarget();
            }
        } catch (JSONException e) {
            JSONException r0_JSONException = e;
            r1_Message.what = 1;
            r1_Message.obj = this.b.getCustomerTitle() + "\u51fa\u73b0\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
            r0_JSONException.printStackTrace();
            if (r1_Message != null) {
                r1_Message.sendToTarget();
            }
        } catch (QiushibaikeException e_2) {
            QiushibaikeException r0_QiushibaikeException = e_2;
            r1_Message.what = XListViewHeader.STATE_REFRESHING;
            r1_Message.obj = r0_QiushibaikeException.getMessage();
            r0_QiushibaikeException.printStackTrace();
            if (r1_Message != null) {
                r1_Message.sendToTarget();
            }
        }
    }
}