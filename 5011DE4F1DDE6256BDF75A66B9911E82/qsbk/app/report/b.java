package qsbk.app.report;

import android.os.Message;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.utils.HttpClient;

// compiled from: ArticleReporter.java
class b extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ ArticleReporter c;

    b(ArticleReporter r1_ArticleReporter, String r2_String, int r3i, String r4_String) {
        this.c = r1_ArticleReporter;
        this.a = r3i;
        this.b = r4_String;
        super(r2_String);
    }

    public void run() {
        Message r0_Message;
        try {
            Map r0_Map = new HashMap();
            r0_Map.put("reason", Integer.valueOf(this.a));
            HttpClient r2_HttpClient = HttpClient.getIntentce();
            String r3_String = Constants.REPORT_ARTICLE;
            Object[] r4_ObjectA = new Object[1];
            r4_ObjectA[0] = this.b;
            JSONObject r1_JSONObject = new JSONObject(r2_HttpClient.post(String.format(r3_String, r4_ObjectA), r0_Map));
            int r0i = r1_JSONObject.getInt("err");
            if (r0i == 0) {
                r0_Message = this.c.a.obtainMessage(0, "\u4e3e\u62a5\u6210\u529f");
                this.c.a.sendMessage(r0_Message);
            } else {
                r0_Message = this.c.a.obtainMessage(r0i, r1_JSONObject.getString("err_msg"));
                this.c.a.sendMessage(r0_Message);
            }
        } catch (Exception e) {
            r0_Message = this.c.a.obtainMessage(AdViewUtil.NETWORK_TYPE_CUSTOMIZE, "\u4e3e\u62a5\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            e.printStackTrace();
        }
    }
}