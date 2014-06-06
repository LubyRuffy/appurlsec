package qsbk.app.activity;

import android.util.Pair;
import android.widget.Toast;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.nearby.ui.HttpAsyncTask;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: EditInfoEntranceActivity.java
class y extends HttpAsyncTask {
    JSONObject a;
    final /* synthetic */ Map b;
    final /* synthetic */ EditInfoEntranceActivity c;

    y(EditInfoEntranceActivity r1_EditInfoEntranceActivity, Map r2_Map) {
        this.c = r1_EditInfoEntranceActivity;
        this.b = r2_Map;
    }

    protected Pair<Integer, String> a(String ... r5_StringA) {
        try {
            this.a = new JSONObject(HttpClient.getIntentce().post(Constants.UPDATE_USERINFO, this.b));
            return new Pair((Integer) this.a.get("err"), this.a.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpAsyncTask.getLocalError();
        }
    }

    protected void a(Pair<Integer, String> r4_Pair_Integer__String) {
        if (((Integer) r4_Pair_Integer__String.first).equals(Integer.valueOf(0))) {
            try {
                if (this.c.F.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                    this.c.B.getValueView().setText(this.a.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA));
                    QsbkApp.currentUser.wb = this.a.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    super.a(r4_Pair_Integer__String);
                } else {
                    this.c.C.getValueView().setText(this.a.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ));
                    QsbkApp.currentUser.qq = this.a.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    super.a(r4_Pair_Integer__String);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this.c, (CharSequence) r4_Pair_Integer__String.second, 1).show();
        }
    }
}