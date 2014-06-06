package qsbk.app.activity;

import android.util.Pair;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.nearby.ui.HttpAsyncTask;
import qsbk.app.utils.HttpClient;

// compiled from: OneProfileActivity.java
class bq extends HttpAsyncTask {
    final /* synthetic */ OneProfileActivity a;

    bq(OneProfileActivity r1_OneProfileActivity) {
        this.a = r1_OneProfileActivity;
    }

    protected Pair<Integer, String> a(String ... r4_StringA) {
        try {
            Map r0_Map = new HashMap();
            r0_Map.put(QsbkDatabase.ACTION, "logout");
            HttpClient.getIntentce().post(Constants.PUSH_DOMAINS, r0_Map);
            return new Pair(Integer.valueOf(0), RContactStorage.PRIMARY_KEY);
        } catch (QiushibaikeException e) {
            return HttpAsyncTask.getLocalError();
        }
    }
}