package qsbk.app.activity.publish;

import android.os.Message;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: Publish.java
class g extends Thread {
    final /* synthetic */ Publish a;

    g(Publish r1_Publish, String r2_String) {
        this.a = r1_Publish;
        super(r2_String);
    }

    public void run() {
        Message r0_Message;
        if (Publish.b(this.a)) {
            InputStream r0_InputStream = null;
            try {
                if (!TextUtils.isEmpty(Publish.d(this.a))) {
                    r0_InputStream = new FileInputStream(new File(DeviceUtils.getSDPath() + "/qsbk/send/send.jpg"));
                }
                JSONObject r1_JSONObject = new JSONObject(HttpClient.getIntentce().submit(this.a.getUrl(), this.a.getPostParams(), r0_InputStream));
                if (r1_JSONObject.getInt("err") == 0) {
                    r0_Message = this.a.u.obtainMessage(0, this.a.getSendHint());
                    Publish.a(this.a, r1_JSONObject);
                } else {
                    r0_Message = this.a.u.obtainMessage(1, r1_JSONObject.getString("err_msg"));
                }
            } catch (QiushibaikeException e) {
                if (!TextUtils.isEmpty(Publish.a(this.a))) {
                    SharePreferenceUtils.setSharePreferencesValue(Publish.a(this.a), this.a.n.getText().toString().trim());
                }
                r0_Message = this.a.u.obtainMessage(1, "\u53d1\u9001\u5931\u8d25,\u5185\u5bb9\u4ee5\u4fdd\u5b58\u4e3a\u8349\u7a3f");
            } catch (JSONException e_2) {
                r0_Message = this.a.u.obtainMessage(1, "\u53d1\u9001\u5931\u8d25,\u5185\u5bb9\u4ee5\u4fdd\u5b58\u4e3a\u8349\u7a3f");
                e_2.printStackTrace();
            } catch (Exception e_3) {
                r0_Message = this.a.u.obtainMessage(1, "\u53d1\u9001\u5931\u8d25,\u5185\u5bb9\u4ee5\u4fdd\u5b58\u4e3a\u8349\u7a3f");
                e_3.printStackTrace();
            }
        } else {
            try {
                JSONObject r0_JSONObject = new JSONObject(HttpClient.getIntentce().post(this.a.getUrl(), this.a.getPostParams()));
                if (r0_JSONObject.getInt("err") == 0) {
                    QsbkApp.currentComment = r0_JSONObject;
                    Publish.f(this.a);
                    r0_Message = this.a.u.obtainMessage(0, this.a.getSendHint());
                } else {
                    r0_Message = this.a.u.obtainMessage(1, r0_JSONObject.getString("err_msg"));
                }
            } catch (QiushibaikeException e_4) {
                r0_Message = this.a.u.obtainMessage(1, e_4.getMessage());
            } catch (Exception e_5) {
                Exception r1_Exception = e_5;
                r0_Message = this.a.u.obtainMessage(1, r1_Exception.getMessage());
                r1_Exception.printStackTrace();
            }
        }
        this.a.u.sendMessage(r0_Message);
    }
}