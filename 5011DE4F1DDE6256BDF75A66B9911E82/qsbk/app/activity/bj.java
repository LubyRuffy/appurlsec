package qsbk.app.activity;

import android.os.Message;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

// compiled from: MyContentsActivity.java
class bj extends Thread {
    final /* synthetic */ MyContentsActivity a;

    bj(MyContentsActivity r1_MyContentsActivity, String r2_String) {
        this.a = r1_MyContentsActivity;
        super(r2_String);
    }

    public void run() {
        String r0_String = Constants.DELETE_CREATE;
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = this.a.y.id;
        r0_String = String.format(r0_String, r1_ObjectA);
        Message r1_Message = this.a.n.obtainMessage();
        try {
            JSONObject r2_JSONObject = new JSONObject(HttpClient.getIntentce().delete(r0_String));
            int r0i = r2_JSONObject.getInt("err");
            r1_Message.what = r0i;
            if (r0i != 0) {
                if (!r2_JSONObject.isNull("err_msg")) {
                    r1_Message.obj = r2_JSONObject.getString("err_msg");
                }
                r1_Message.obj = "\u5220\u9664\u5931\u8d25";
            }
        } catch (Exception e) {
            r1_Message.what = 999;
            e.printStackTrace();
        }
        r1_Message.sendToTarget();
    }
}