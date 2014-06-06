package qsbk.app.nearby.ui;

import android.util.Pair;
import com.google.analytics.tracking.android.ModelFields;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.Globalization;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastUtil;

// compiled from: NearByListActivity.java
class w extends HttpAsyncTask {
    JSONObject a;
    final /* synthetic */ boolean b;
    final /* synthetic */ NearByListActivity c;

    w(NearByListActivity r2_NearByListActivity, boolean r3z) {
        this.c = r2_NearByListActivity;
        this.b = r3z;
        this.a = null;
    }

    protected Pair<Integer, String> a(String ... r5_StringA) {
        Pair<Integer, String> r0_Pair_Integer__String;
        LogUtil.d("fetch nearby background");
        LogUtil.d("longitude:" + NearByListActivity.c());
        try {
            Map r1_Map = new HashMap();
            r1_Map.put("longitude", String.valueOf(NearByListActivity.c()));
            r1_Map.put("latitude", String.valueOf(NearByListActivity.d()));
            r1_Map.put(EDIT_TYPE.TYPE_GENDER, NearByListActivity.j(this.c));
            r1_Map.put(Globalization.TIME, Integer.valueOf(NearByListActivity.k(this.c)));
            DeviceUtils.addDeviceInfoToParam(r1_Map);
            r1_Map.put(ModelFields.PAGE, Integer.valueOf(this.b ? NearByListActivity.l(this.c) + 1 : NearByListActivity.l(this.c)));
            String r0_String = HttpClient.getIntentce().post(NearbyEngine.URL_FETCH, r1_Map);
            LogUtil.d("response:" + r0_String);
            this.a = new JSONObject(r0_String);
            r0_Pair_Integer__String = new Pair(Integer.valueOf(this.a.getInt("err")), this.a.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            r0_Pair_Integer__String = new Pair(Integer.valueOf(HttpClient.RESP_CODE_LOCAL_ERROR), HttpClient.getLocalErrorStr());
        }
        return r0_Pair_Integer__String;
    }

    protected void a(Pair<Integer, String> r5_Pair_Integer__String) {
        NearByListActivity.a(this.c, NearByListActivity.DIALOG_KEY_NEARBYLIST);
        if (((Integer) r5_Pair_Integer__String.first).equals(Integer.valueOf(NearbyEngine.RESP_NEED_INFO))) {
            ToastUtil.Short((String) r5_Pair_Integer__String.second);
            NearByListActivity.m(this.c);
        } else if (((Integer) r5_Pair_Integer__String.first).equals(Integer.valueOf(0))) {
            if (this.a != null) {
                JSONArray r0_JSONArray = this.a.optJSONArray("nearby");
                NearByListActivity.a(this.c, r0_JSONArray);
                if (this.a.optInt("has_more") == 1) {
                    this.c.n.setPullLoadEnable(true);
                } else {
                    this.c.n.loadNoMore();
                }
                if (this.b) {
                    NearByListActivity.n(this.c);
                } else if (r0_JSONArray == null || r0_JSONArray.length() == 0) {
                    this.c.show_restart_with_msg("\u627e\u4e0d\u5230\u7b26\u5408\u7b5b\u9009\u6761\u4ef6\u7684\u7528\u6237\uff0c\u8bf7\u4fee\u6539\u7b5b\u9009\u6761\u4ef6\u4e4b\u540e\u518d\u8bd5!");
                }
            } else {
                ToastUtil.Short("\u627e\u4e0d\u5230\u9644\u8fd1\u7684\u4eba\uff0c\u8bf7\u91cd\u65b0\u70b9\u51fb\u5237\u65b0");
                this.c.show_restart();
            }
        } else {
            ToastUtil.Short((String) r5_Pair_Integer__String.second);
            this.c.show_restart();
        }
    }
}