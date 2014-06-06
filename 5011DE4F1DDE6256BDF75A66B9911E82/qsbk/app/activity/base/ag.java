package qsbk.app.activity.base;

import android.os.Message;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: LoginRegisterBaseActivity.java
class ag extends Thread {
    final /* synthetic */ LoginRegisterBaseActivity a;

    ag(LoginRegisterBaseActivity r1_LoginRegisterBaseActivity, String r2_String) {
        this.a = r1_LoginRegisterBaseActivity;
        super(r2_String);
    }

    public void run() {
        Message r1_Message;
        r1_Message = this.a.getHanler().obtainMessage();
        String r0_String = this.a.d();
        Map r2_Map = this.a.e();
        try {
            this.a.x = new JSONObject(HttpClient.getIntentce().post(r0_String, r2_Map));
            Integer r0_Integer = (Integer) this.a.x.get("err");
            r1_Message.what = r0_Integer.intValue();
            if (r0_Integer.intValue() != 0) {
                r1_Message.obj = this.a.x.getString("err_msg");
            }
            if (r1_Message != null) {
                r1_Message.sendToTarget();
            }
        } catch (JSONException e) {
            JSONException r0_JSONException = e;
            r1_Message.what = 1;
            r1_Message.obj = this.a.getCustomerTitle() + "\u51fa\u73b0\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
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