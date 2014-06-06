package qsbk.app.share;

import android.text.TextUtils;
import android.util.Pair;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.nearby.ui.HttpAsyncTask;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastUtil;

// compiled from: ShareUtils.java
class g extends HttpAsyncTask {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ ShareUtils c;

    g(ShareUtils r1_ShareUtils, String r2_String, boolean r3z) {
        this.c = r1_ShareUtils;
        this.a = r2_String;
        this.b = r3z;
    }

    protected Pair<Integer, String> a(String ... r6_StringA) {
        Object r0_Object;
        int r1i;
        Object r1_Object = null;
        try {
            JSONObject r3_JSONObject = new JSONObject(this.b ? HttpClient.getIntentce().post(getURL(), this.a) : HttpClient.getIntentce().delete(getURL()));
            int r2i = r3_JSONObject.getInt("err");
            r0_Object = r3_JSONObject.optString("err_msg");
            r1i = r2i;
        } catch (Exception e) {
            e.printStackTrace();
            r1i = 9999;
            r0_Object = r1_Object;
        }
        if (r1i == 0) {
            r0_Object = this.b ? "\u5df2\u6536\u85cf" : "\u5df2\u53d6\u6d88\u6536\u85cf";
        } else if (TextUtils.isEmpty(r0_Object)) {
            r0_Object = this.b ? "\u6536\u85cf\u5931\u8d25" : "\u53d6\u6d88\u6536\u85cf\u5931\u8d25";
        }
        return new Pair(Integer.valueOf(r1i), r0_Object);
    }

    protected void a(Pair<Integer, String> r3_Pair_Integer__String) {
        QsbkApp.allCollection.remove(this.a);
        if (this.b) {
            QsbkApp.allCollection.add(this.a);
        }
        SharePreferenceUtils.setCollections(QsbkApp.allCollection);
        ToastUtil.Short((String) r3_Pair_Integer__String.second);
    }

    public String getURL() {
        String r0_String = Constants.COLLECT;
        Object[] r1_ObjectA = new Object[1];
        r1_ObjectA[0] = this.a;
        return String.format(r0_String, r1_ObjectA);
    }
}