package qsbk.app.nearby.ui;

import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastUtil;

// compiled from: NearByListActivity.java
class ab extends HttpAsyncTask {
    final /* synthetic */ NearByListActivity a;

    ab(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    protected Pair<Integer, String> a(String ... r5_StringA) {
        try {
            Map r0_Map = new HashMap();
            r0_Map.put("holder", "true");
            String r0_String = HttpClient.getIntentce().post(NearbyEngine.URL_CLEAR, r0_Map);
            LogUtil.d("response:" + r0_String);
            JSONObject r1_JSONObject = new JSONObject(r0_String);
            return new Pair(Integer.valueOf(r1_JSONObject.getInt("err")), r1_JSONObject.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(HttpClient.RESP_CODE_LOCAL_ERROR), HttpClient.getLocalErrorStr());
        }
    }

    protected void a(Pair<Integer, String> r3_Pair_Integer__String) {
        NearByListActivity.a(this.a, NearByListActivity.DIALOG_KEY_CLEAR_POSITION);
        if (((Integer) r3_Pair_Integer__String.first).equals(Integer.valueOf(0))) {
            this.a.r.clear();
            this.a.q.notifyDataSetChanged();
            NearByListActivity.a(this.a);
            NearByListActivity.a(this.a, 1);
        } else {
            ToastUtil.Short((String) r3_Pair_Integer__String.second);
        }
    }
}