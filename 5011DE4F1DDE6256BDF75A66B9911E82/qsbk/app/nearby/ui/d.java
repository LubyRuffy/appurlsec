package qsbk.app.nearby.ui;

import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.core.AsyncTask;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastUtil;

// compiled from: InfoCompleteActivity.java
class d extends AsyncTask<String, Void, Pair<Integer, String>> {
    JSONObject a;
    final /* synthetic */ InfoCompleteActivity b;

    d(InfoCompleteActivity r2_InfoCompleteActivity) {
        this.b = r2_InfoCompleteActivity;
        this.a = null;
    }

    protected Pair<Integer, String> a(String ... r7_StringA) {
        try {
            Map r0_Map = new HashMap();
            r0_Map.put(EDIT_TYPE.TYPE_GENDER, String.valueOf(this.b.x));
            r0_Map.put("birthday", String.valueOf((int) (this.b.y / 1000)));
            if (this.b.w.getText() != null) {
                r0_Map.put(EDIT_TYPE.TYPE_SIGNATURE, this.b.w.getText());
            }
            DeviceUtils.addDeviceInfoToParam(r0_Map);
            String r1_String = NearbyEngine.instance().getCurrentUserInfoUrl();
            LogUtil.d("url:" + r1_String);
            LogUtil.d("params:" + r7_StringA);
            this.a = new JSONObject(HttpClient.getIntentce().post(r1_String, r0_Map));
            return new Pair(Integer.valueOf(this.a.getInt("err")), this.a.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(HttpClient.RESP_CODE_LOCAL_ERROR), HttpClient.getLocalErrorStr());
        }
    }

    protected void a(Pair<Integer, String> r4_Pair_Integer__String) {
        if (this.b.z != null) {
            this.b.z.dismiss();
            this.b.z = null;
        }
        if (((Integer) r4_Pair_Integer__String.first).equals(Integer.valueOf(0))) {
            ToastUtil.Short((int)R.string.save_nearby_info_success);
            QsbkApp.currentUser.gender = this.b.x;
            QsbkApp.currentUser.birthday = this.b.y;
            QsbkApp.currentUser.signature = this.b.w.getText().toString();
            QsbkApp.getInstance().setCurrentUserToLocal();
            this.b.setResult(-1);
            this.b.finish();
        } else {
            ToastUtil.Short((String) r4_Pair_Integer__String.second);
        }
    }
}