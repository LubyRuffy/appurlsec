package qsbk.app.activity;

import android.os.Message;
import com.qiubai.library.adview.util.AdViewUtil;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

// compiled from: UserSetting.java
class dm extends Thread {
    final /* synthetic */ UserSetting a;

    dm(UserSetting r1_UserSetting, String r2_String) {
        this.a = r1_UserSetting;
        super(r2_String);
    }

    public void run() {
        Message r0_Message;
        try {
            JSONObject r0_JSONObject = new JSONObject(HttpClient.getIntentce().post(Constants.UPDATE_USERINFO, this.a.getPostParams()));
            int r1i = r0_JSONObject.getInt("err");
            if (r1i == 0) {
                r0_Message = this.a.x.obtainMessage(6666, "\u90ae\u7bb1\u5df2\u4fee\u6539\uff0c\u8bf7\u8bb0\u5f97\u53ca\u65f6\u9a8c\u8bc1\u60a8\u7684\u90ae\u7bb1");
                this.a.x.sendMessage(r0_Message);
            } else {
                r0_Message = this.a.x.obtainMessage(r1i, r0_JSONObject.getString("err_msg"));
                this.a.x.sendMessage(r0_Message);
            }
        } catch (Exception e) {
            r0_Message = this.a.x.obtainMessage(AdViewUtil.NETWORK_TYPE_CUSTOMIZE, "\u89e3\u6790\u9519\u8bef");
            e.printStackTrace();
        }
    }
}