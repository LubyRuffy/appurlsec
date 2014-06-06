package com.tencent.open;

import com.tencent.tauth.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
class i implements IUiListener {
    private IUiListener a;

    public i(IUiListener r1_IUiListener) {
        this.a = r1_IUiListener;
    }

    private void a(String r4_String) {
        try {
            onComplete(Util.d(r4_String));
        } catch (JSONException e) {
            e.printStackTrace();
            onError(new UiError(-4, Constants.MSG_JSON_ERROR, r4_String));
        }
    }

    public void onCancel() {
        if (this.a != null) {
            this.a.onCancel();
            this.a = null;
        }
    }

    public void onComplete(JSONObject r2_JSONObject) {
        if (this.a != null) {
            this.a.onComplete(r2_JSONObject);
            this.a = null;
        }
    }

    public void onError(UiError r2_UiError) {
        if (this.a != null) {
            this.a.onError(r2_UiError);
            this.a = null;
        }
    }
}