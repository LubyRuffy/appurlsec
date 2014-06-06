package qsbk.app.nearby.ui;

import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastUtil;

// compiled from: NearByListActivity.java
class z extends HttpAsyncTask {
    final /* synthetic */ NearByListActivity a;

    z(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    protected Pair<Integer, String> a(String ... r5_StringA) {
        try {
            Map r0_Map = new HashMap();
            r0_Map.put("holder", "test");
            JSONObject r1_JSONObject = new JSONObject(HttpClient.getIntentce().post(NearbyEngine.URL_FETCH, r0_Map));
            return new Pair(Integer.valueOf(r1_JSONObject.getInt("err")), r1_JSONObject.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(HttpClient.RESP_CODE_LOCAL_ERROR), HttpClient.getLocalErrorStr());
        }
    }

    protected void a() {
    }

    protected void a(Pair<Integer, String> r3_Pair_Integer__String) {
        NearByListActivity.a(this.a, NearByListActivity.DIALOG_KEY_INFOCOMPLETE);
        if (((Integer) r3_Pair_Integer__String.first).equals(Integer.valueOf(NearbyEngine.RESP_NEED_INFO))) {
            NearByListActivity.m(this.a);
        } else if (((Integer) r3_Pair_Integer__String.first).equals(Integer.valueOf(0))) {
            NearbyEngine.instance().setNearbyInfoComplete(true);
            NearByListActivity.o(this.a);
        } else {
            ToastUtil.Short((String) r3_Pair_Integer__String.second);
            this.a.show_restart();
        }
    }
}