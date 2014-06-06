package com.tencent.open;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.UiError;
import org.json.JSONObject;
import qsbk.app.utils.Base64;

// compiled from: ProGuard
class g extends WebViewClient {
    final /* synthetic */ TDialog a;

    private g(TDialog r1_TDialog) {
        this.a = r1_TDialog;
    }

    public void onPageFinished(WebView r3_WebView, String r4_String) {
        super.onPageFinished(r3_WebView, r4_String);
        if (TDialog.d == null || TDialog.d.get() == null) {
            this.a.j.setVisibility(0);
        } else {
            ((View) TDialog.d.get()).setVisibility(Base64.DONT_BREAK_LINES);
            this.a.j.setVisibility(0);
        }
    }

    public void onPageStarted(WebView r4_WebView, String r5_String, Bitmap r6_Bitmap) {
        Util.a("TDialog", "Webview loading URL: " + r5_String);
        super.onPageStarted(r4_WebView, r5_String, r6_Bitmap);
        if (TDialog.d == null || TDialog.d.get() == null) {
        } else {
            ((View) TDialog.d.get()).setVisibility(0);
        }
    }

    public void onReceivedError(WebView r4_WebView, int r5i, String r6_String, String r7_String) {
        super.onReceivedError(r4_WebView, r5i, r6_String, r7_String);
        this.a.g.onError(new UiError(r5i, r6_String, r7_String));
        if (TDialog.c == null || TDialog.c.get() == null) {
            this.a.dismiss();
        } else {
            Toast.makeText((Context) TDialog.c.get(), "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\u6216\u7cfb\u7edf\u9519\u8bef", 0).show();
            this.a.dismiss();
        }
    }

    public void onReceivedSslError(WebView r1_WebView, SslErrorHandler r2_SslErrorHandler, SslError r3_SslError) {
        r2_SslErrorHandler.proceed();
    }

    public boolean shouldOverrideUrlLoading(WebView r6_WebView, String r7_String) {
        Util.a("TDialog", "Redirect URL: " + r7_String);
        if (r7_String.startsWith("auth://browser")) {
            JSONObject r0_JSONObject = Util.c(r7_String);
            this.a.n = this.a.e();
            if (this.a.n) {
                return true;
            }
            if (r0_JSONObject.optString("fail_cb", null) != null) {
                this.a.a(r0_JSONObject.optString("fail_cb"), RContactStorage.PRIMARY_KEY);
                return true;
            } else if (r0_JSONObject.optInt("fall_to_wv") == 1) {
                TDialog.a(this.a, this.a.f.indexOf("?") > -1 ? "&" : "?");
                TDialog.a(this.a, (Object)"browser_error=1");
                this.a.j.loadUrl(this.a.f);
                return true;
            } else {
                String r0_String = r0_JSONObject.optString("redir", null);
                if (r0_String != null) {
                    this.a.j.loadUrl(r0_String);
                }
                return true;
            }
        } else if (r7_String.startsWith(ServerSetting.getInstance().getSettingUrl((Context) TDialog.c.get(), 1))) {
            this.a.g.onComplete(Util.c(r7_String));
            this.a.dismiss();
            return true;
        } else if (r7_String.startsWith("auth://cancel")) {
            this.a.g.onCancel();
            this.a.dismiss();
            return true;
        } else if (r7_String.startsWith("auth://close")) {
            this.a.dismiss();
            return true;
        } else {
            if (!r7_String.startsWith("download://")) {
                return false;
            }
            Intent r2_Intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(r7_String.substring("download://".length()))));
            if (TDialog.c == null || TDialog.c.get() == null) {
                return true;
            }
            ((Context) TDialog.c.get()).startActivity(r2_Intent);
            return true;
        }
    }
}