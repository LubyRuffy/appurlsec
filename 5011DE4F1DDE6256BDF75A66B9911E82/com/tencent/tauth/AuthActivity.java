package com.tencent.tauth;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.open.BrowserAuth;
import com.tencent.open.BrowserAuth.Auth;
import com.tencent.open.TemporaryStorage;
import com.tencent.open.Util;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.ThirdParty;

// compiled from: ProGuard
public class AuthActivity extends Activity {
    private void a(Uri r5_Uri) {
        if (r5_Uri == null || r5_Uri.toString().equals(RContactStorage.PRIMARY_KEY)) {
            finish();
        } else {
            String r0_String = r5_Uri.toString();
            Bundle r1_Bundle = Util.a(r0_String.substring(r0_String.indexOf("#") + 1));
            String r2_String = r1_Bundle.getString(QsbkDatabase.ACTION);
            if (r2_String == null) {
                a(r1_Bundle, r0_String);
            } else if (r2_String.equals("shareToQQ")) {
                a(r1_Bundle);
            } else {
                a(r1_Bundle, r0_String);
            }
        }
    }

    private void a(Bundle r7_Bundle) {
        Object r0_Object = TemporaryStorage.a("shareToQQ");
        if (r0_Object == null) {
            finish();
        } else {
            IUiListener r0_IUiListener = (IUiListener) r0_Object;
            String r2_String = r7_Bundle.getString("result");
            String r1_String = r7_Bundle.getString("response");
            if (r2_String.equals("cancel")) {
                r0_IUiListener.onCancel();
            } else if (r2_String.equals(QQDialogAuthorizeActivity.ERROR_RET)) {
                r0_IUiListener.onError(new UiError(-6, "unknown error", r1_String + RContactStorage.PRIMARY_KEY));
            } else if (r2_String.equals("complete")) {
                r2_String = r1_String == null ? "{\"ret\": 0}" : r1_String;
                try {
                    r0_IUiListener.onComplete(new JSONObject(r2_String));
                } catch (JSONException e) {
                    e.printStackTrace();
                    r0_IUiListener.onError(new UiError(-4, "json error", r2_String + RContactStorage.PRIMARY_KEY));
                }
            }
            finish();
        }
    }

    private void a(Bundle r7_Bundle, String r8_String) {
        BrowserAuth r0_BrowserAuth = BrowserAuth.a();
        String r1_String = r7_Bundle.getString("serial");
        Auth r2_Auth = r0_BrowserAuth.a(r1_String);
        if (r2_Auth != null) {
            if (r8_String.indexOf("://cancel") != -1) {
                r2_Auth.a.onCancel();
                r2_Auth.b.dismiss();
            } else {
                String r3_String = r7_Bundle.getString(ThirdParty.KEY_TOKEN);
                if (r3_String != null) {
                    r7_Bundle.putString(ThirdParty.KEY_TOKEN, r0_BrowserAuth.a(r3_String, r2_Auth.c));
                }
                JSONObject r3_JSONObject = Util.a(new JSONObject(), Util.a(r7_Bundle));
                String r4_String = r3_JSONObject.optString("cb");
                if (RContactStorage.PRIMARY_KEY.equals(r4_String)) {
                    r2_Auth.a.onComplete(r3_JSONObject);
                    r2_Auth.b.dismiss();
                } else {
                    r2_Auth.b.a(r4_String, r3_JSONObject.toString());
                }
            }
            r0_BrowserAuth.b(r1_String);
        }
        finish();
    }

    protected void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        a(getIntent().getData());
    }
}