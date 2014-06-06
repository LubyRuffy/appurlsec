package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.tencent.tauth.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: SingleArticle.java
class cm extends Handler {
    final /* synthetic */ SingleArticle a;

    cm(SingleArticle r1_SingleArticle) {
        this.a = r1_SingleArticle;
    }

    public void handleMessage(Message r4_Message) {
        JSONObject r0_JSONObject = (JSONObject) r4_Message.obj;
        try {
            String r1_String = r0_JSONObject.getString("articleId");
            QsbkApp.allCollection.remove(r1_String);
            if (r0_JSONObject.getBoolean("collection")) {
                QsbkApp.allCollection.add(r1_String);
            }
            SharePreferenceUtils.setCollections(QsbkApp.allCollection);
            Toast.makeText(QsbkApp.mContext, r0_JSONObject.getString(Constants.PARAM_SEND_MSG), 0).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}