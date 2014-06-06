package com.tencent.open;

import android.os.Handler;
import android.os.Message;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
class x extends Handler {
    final /* synthetic */ k a;

    x(k r1_k) {
        this.a = r1_k;
    }

    public void handleMessage(Message r6_Message) {
        if (r6_Message.what == 0) {
            int r0i;
            try {
                r0i = Integer.parseInt(((JSONObject) r6_Message.obj).getString(KEYS.RET));
            } catch (JSONException e) {
                e.printStackTrace();
                this.a.a();
                r0i = 0;
            }
            if (r0i == 0) {
                this.a.b.onComplete((JSONObject) r6_Message.obj);
            } else {
                this.a.a();
            }
        } else {
            this.a.b.onError(new UiError(r6_Message.what, (String) r6_Message.obj, null));
        }
    }
}