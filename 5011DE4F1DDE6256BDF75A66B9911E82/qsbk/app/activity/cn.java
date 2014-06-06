package qsbk.app.activity;

import android.os.Message;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

// compiled from: SingleArticle.java
class cn extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ JSONObject b;
    final /* synthetic */ boolean c;
    final /* synthetic */ SingleArticle d;

    cn(SingleArticle r1_SingleArticle, String r2_String, String r3_String, JSONObject r4_JSONObject, boolean r5z) {
        this.d = r1_SingleArticle;
        this.a = r3_String;
        this.b = r4_JSONObject;
        this.c = r5z;
        super(r2_String);
    }

    public void run() {
        String r0_String = Constants.COLLECT;
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = this.a;
        r0_String = String.format(r0_String, r1_ObjectA);
        Message r1_Message = this.d.K.obtainMessage();
        try {
            this.b.put("articleId", this.a);
            this.b.put("collection", this.c);
            String r2_String = RContactStorage.PRIMARY_KEY;
            JSONObject r2_JSONObject = new JSONObject(this.c ? HttpClient.getIntentce().post(r0_String, this.a) : HttpClient.getIntentce().delete(r0_String));
            int r0i = r2_JSONObject.getInt("err");
            r1_Message.what = r0i;
            if (r0i != 0) {
                this.b.put("what", r0i);
                if (r2_JSONObject.isNull("err_msg")) {
                    if (this.c) {
                        this.b.put(com.tencent.tauth.Constants.PARAM_SEND_MSG, "\u6536\u85cf\u5931\u8d25");
                        r1_Message.obj = this.b;
                        r1_Message.sendToTarget();
                    } else {
                        this.b.put(com.tencent.tauth.Constants.PARAM_SEND_MSG, "\u53d6\u6d88\u6536\u85cf\u5931\u8d25");
                        r1_Message.obj = this.b;
                        r1_Message.sendToTarget();
                    }
                } else {
                    this.b.put(com.tencent.tauth.Constants.PARAM_SEND_MSG, r2_JSONObject.getString("err_msg"));
                    r1_Message.obj = this.b;
                    r1_Message.sendToTarget();
                }
            } else {
                this.b.put("what", 0);
                if (this.c) {
                    this.b.put(com.tencent.tauth.Constants.PARAM_SEND_MSG, "\u5df2\u6536\u85cf");
                    r1_Message.obj = this.b;
                    r1_Message.sendToTarget();
                } else {
                    this.b.put(com.tencent.tauth.Constants.PARAM_SEND_MSG, "\u5df2\u53d6\u6d88\u6536\u85cf");
                    r1_Message.obj = this.b;
                    r1_Message.sendToTarget();
                }
            }
        } catch (Exception e) {
            this.b.put("what", AdViewUtil.NETWORK_TYPE_CUSTOMIZE);
            if (this.c) {
                this.b.put(com.tencent.tauth.Constants.PARAM_SEND_MSG, "\u6536\u85cf\u5931\u8d25");
            } else {
                this.b.put(com.tencent.tauth.Constants.PARAM_SEND_MSG, "\u53d6\u6d88\u6536\u85cf\u5931\u8d25");
            }
        }
    }
}