package com.tencent.open;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.UsersAPI;

// compiled from: ProGuard
class k implements IUiListener {
    final /* synthetic */ OpenUi a;
    private IUiListener b;
    private Activity c;
    private Bundle d;
    private String e;
    private String f;
    private String g;
    private String h;
    private Handler i;

    public k(OpenUi r4_OpenUi, Activity r5_Activity, Bundle r6_Bundle, IUiListener r7_IUiListener, String r8_String, String r9_String, String r10_String, String r11_String) {
        this.a = r4_OpenUi;
        this.i = new x(this);
        this.c = r5_Activity;
        this.d = r6_Bundle;
        this.b = r7_IUiListener;
        this.e = r8_String;
        this.f = r9_String;
        this.g = r10_String;
        this.h = r11_String;
        Log.d("toddtest", "EncrytokenListener appid=" + r8_String + ",  openid=" + r9_String + ",  token=" + r10_String + ",  params=" + r6_Bundle.toString() + ", action=" + r11_String);
    }

    private void a() {
        this.a.a.a(null);
        this.a.a.a(null, "0");
        this.a.a(this.c, Constants.ACTION_LOGIN, this.d, this.b);
    }

    private void a(String r8_String) {
        Log.d("toddtest", "validToken encrytoken=" + r8_String);
        OpenApi r0_OpenApi = new OpenApi(this.a.a);
        Bundle r3_Bundle = new Bundle();
        r3_Bundle.putString(Constants.PARAM_CONSUMER_KEY, this.e);
        r3_Bundle.putString(Constants.PARAM_OPEN_ID, this.f);
        r3_Bundle.putString(ThirdParty.KEY_TOKEN, this.g);
        r3_Bundle.putString("encrytoken", r8_String);
        r0_OpenApi.a(this.a.a.f(), "https://openmobile.qq.com/user/user_login_statis", r3_Bundle, UsersAPI.HTTPMETHOD_POST, new n(this), r3_Bundle);
    }

    public void onCancel() {
        if (this.h.equals(Constants.ACTION_CHECK_TOKEN)) {
            a();
        }
    }

    public void onComplete(JSONObject r6_JSONObject) {
        String r0_String = null;
        try {
            r0_String = r6_JSONObject.getString(Constants.PARAM_ENCRY_EOKEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("toddtest", "EncrytokenListener onComplete jsonobj = " + r6_JSONObject.toString());
        if (this.h.equals(Constants.ACTION_CHECK_TOKEN)) {
            if (r0_String == null || r0_String.length() <= 0) {
                a();
            } else {
                a(r0_String);
            }
        } else if (Constants.ACTION_CHALLENGE.equals(this.h) || Constants.ACTION_STORY.equals(this.h) || Constants.ACTION_INVITE.equals(this.h) || Constants.ACTION_BRAG.equals(this.h) || Constants.ACTION_ASK.equals(this.h) || Constants.ACTION_GIFT.equals(this.h)) {
            this.d.putString("encrytoken", r0_String);
            this.a.a(this.c, this.h, this.d, this.b);
        }
        this.a.a();
    }

    public void onError(UiError r3_UiError) {
        if (this.h.equals(Constants.ACTION_CHECK_TOKEN)) {
            a();
        }
    }
}