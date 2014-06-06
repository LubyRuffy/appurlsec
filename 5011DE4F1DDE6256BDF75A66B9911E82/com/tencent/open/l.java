package com.tencent.open;

import android.webkit.CookieSyncManager;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.thirdparty.ThirdParty;

// compiled from: ProGuard
class l implements IUiListener {
    final /* synthetic */ OpenUi a;
    private IUiListener b;
    private boolean c;

    public l(OpenUi r1_OpenUi, IUiListener r2_IUiListener, boolean r3z, boolean r4z) {
        this.a = r1_OpenUi;
        this.b = r2_IUiListener;
        this.c = r3z;
    }

    public void onCancel() {
        this.b.onCancel();
    }

    public void onComplete(JSONObject r5_JSONObject) {
        try {
            String r0_String = r5_JSONObject.getString(ThirdParty.KEY_TOKEN);
            String r1_String = r5_JSONObject.getString(ThirdParty.KEY_EXPIRES);
            String r2_String = r5_JSONObject.getString(Constants.PARAM_OPEN_ID);
            if (r0_String == null || this.a.a == null || r2_String == null) {
                r0_String = r5_JSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (r0_String == null) {
                    try {
                        this.a.a.f().getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, r0_String).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (this.c) {
                    CookieSyncManager.getInstance().sync();
                }
                this.b.onComplete(r5_JSONObject);
            } else {
                this.a.a.a(r0_String, r1_String);
                this.a.a.a(r2_String);
                TencentStat.b(this.a.a, r2_String);
                r0_String = r5_JSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (r0_String == null) {
                    if (this.c) {
                        this.b.onComplete(r5_JSONObject);
                    } else {
                        CookieSyncManager.getInstance().sync();
                        this.b.onComplete(r5_JSONObject);
                    }
                } else {
                    this.a.a.f().getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, r0_String).commit();
                    if (this.c) {
                        CookieSyncManager.getInstance().sync();
                    }
                    this.b.onComplete(r5_JSONObject);
                }
            }
        } catch (JSONException e_2) {
            e_2.printStackTrace();
        }
    }

    public void onError(UiError r2_UiError) {
        this.b.onError(r2_UiError);
    }
}