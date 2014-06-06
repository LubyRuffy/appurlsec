package qsbk.app.activity;

import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.nearby.ui.HttpAsyncTask;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;

// compiled from: OneProfileActivity.java
class cd extends HttpAsyncTask {
    final /* synthetic */ String a;
    final /* synthetic */ OneProfileActivity b;

    cd(OneProfileActivity r1_OneProfileActivity, String r2_String) {
        this.b = r1_OneProfileActivity;
        this.a = r2_String;
    }

    protected Pair<Integer, String> a(String ... r4_StringA) {
        Map r0_Map = new HashMap();
        DeviceUtils.addDeviceInfoToParam(r0_Map);
        try {
            HttpClient.getIntentce().post(this.a, r0_Map);
        } catch (QiushibaikeException e) {
        }
        return null;
    }
}