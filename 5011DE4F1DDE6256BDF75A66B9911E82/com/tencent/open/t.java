package com.tencent.open;

import android.graphics.Bitmap;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.tauth.UiError;
import org.json.JSONObject;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.ThirdParty;

// compiled from: ProGuard
class t extends WebViewClient {
    final /* synthetic */ RedoDialog a;

    private t(RedoDialog r1_RedoDialog) {
        this.a = r1_RedoDialog;
    }

    public void onPageFinished(WebView r3_WebView, String r4_String) {
        super.onPageFinished(r3_WebView, r4_String);
        this.a.g.dismiss();
        this.a.i.setBackgroundColor(0);
        this.a.h.setVisibility(0);
    }

    public void onPageStarted(WebView r4_WebView, String r5_String, Bitmap r6_Bitmap) {
        Util.a("TDialog", "Webview loading URL: " + r5_String);
        super.onPageStarted(r4_WebView, r5_String, r6_Bitmap);
        this.a.g.show();
    }

    public void onReceivedError(WebView r3_WebView, int r4i, String r5_String, String r6_String) {
        super.onReceivedError(r3_WebView, r4i, r5_String, r6_String);
        this.a.f.onError(new UiError(r4i, r5_String, r6_String));
        this.a.dismiss();
    }

    public void onReceivedHttpAuthRequest(WebView r1_WebView, HttpAuthHandler r2_HttpAuthHandler, String r3_String, String r4_String) {
        super.onReceivedHttpAuthRequest(r1_WebView, r2_HttpAuthHandler, r3_String, r4_String);
    }

    public boolean shouldOverrideUrlLoading(WebView r5_WebView, String r6_String) {
        boolean r0z = true;
        Util.a("TDialog", "Redirect URL: " + r6_String);
        if (r6_String.startsWith(ServerSetting.getInstance().getSettingUrl(null, 1))) {
            JSONObject r1_JSONObject = Util.c(r6_String);
            if (r1_JSONObject.getString(QQDialogAuthorizeActivity.ERROR_RET) == null) {
                r1_JSONObject.getString("error_type");
            }
            this.a.c = r1_JSONObject.getString(ThirdParty.KEY_TOKEN);
            this.a.d = r1_JSONObject.getString(ThirdParty.KEY_EXPIRES);
            if (this.a.c == null || this.a.d == null) {
                this.a.f.onComplete(this.a.b);
                this.a.dismiss();
            }
        } else {
            r0z = false;
        }
        return r0z;
    }
}