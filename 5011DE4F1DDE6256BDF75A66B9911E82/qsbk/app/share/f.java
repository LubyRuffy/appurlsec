package qsbk.app.share;

import android.os.Message;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

// compiled from: ShareUtils.java
class f extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ Map b;
    final /* synthetic */ int c;
    final /* synthetic */ ShareUtils d;

    f(ShareUtils r1_ShareUtils, String r2_String, String r3_String, Map r4_Map, int r5i) {
        this.d = r1_ShareUtils;
        this.a = r3_String;
        this.b = r4_Map;
        this.c = r5i;
        super(r2_String);
    }

    public void run() {
        Message r0_Message;
        try {
            HttpClient r0_HttpClient = HttpClient.getIntentce();
            String r1_String = Constants.SHARE_URL;
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = this.a;
            int r0i = new JSONObject(r0_HttpClient.post(String.format(r1_String, r2_ObjectA), this.b)).getInt("err");
            if (r0i == 0) {
                r0_Message = this.d.a.obtainMessage(r0i, this.d.getTarget(this.c));
                r0_Message.sendToTarget();
            } else {
                r0_Message = this.d.a.obtainMessage(AdViewUtil.NETWORK_TYPE_CUSTOMIZE);
                r0_Message.sendToTarget();
            }
        } catch (QiushibaikeException e) {
            r0_Message = this.d.a.obtainMessage(AdViewUtil.NETWORK_TYPE_CUSTOMIZE);
            e.printStackTrace();
        } catch (Exception e_2) {
            r0_Message = this.d.a.obtainMessage(AdViewUtil.NETWORK_TYPE_CUSTOMIZE);
            e_2.printStackTrace();
        }
    }
}