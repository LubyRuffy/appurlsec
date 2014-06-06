package qsbk.app.message.ui;

import android.os.Message;
import org.json.JSONObject;
import qsbk.app.utils.HttpClient;

// compiled from: MessageSendActivity.java
class j extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ MessageSendActivity c;

    j(MessageSendActivity r1_MessageSendActivity, String r2_String, int r3i, String r4_String) {
        this.c = r1_MessageSendActivity;
        this.a = r3i;
        this.b = r4_String;
        super(r2_String);
    }

    public void run() {
        Message r1_Message = this.c.n.obtainMessage();
        String r0_String;
        try {
            String r0_String_2;
            r0_String_2 = this.a == 1 ? HttpClient.getIntentce().delete(this.b) : HttpClient.getIntentce().messgePorst(this.b);
            r1_Message.arg1 = this.a;
            if (r0_String_2 != null) {
                JSONObject r2_JSONObject = new JSONObject(r0_String_2);
                r1_Message.obj = r2_JSONObject.getString("err_msg");
                r1_Message.what = r2_JSONObject.getInt("err");
            } else {
                r1_Message.obj = "\u7f51\u7edc\u8fde\u63a5\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5";
                r1_Message.what = 9999;
            }
            r1_Message.sendToTarget();
        } catch (Exception e) {
            e.printStackTrace();
            r1_Message.obj = "\u7f51\u7edc\u8fde\u63a5\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5";
            r1_Message.what = 9999;
            r1_Message.sendToTarget();
        }
    }
}