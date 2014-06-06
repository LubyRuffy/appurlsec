package com.tencent.open;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.tencent.tauth.UiError;

// compiled from: ProGuard
class b extends WebViewClient {
    final /* synthetic */ PKDialog a;

    private b(PKDialog r1_PKDialog) {
        this.a = r1_PKDialog;
    }

    public void onPageFinished(WebView r3_WebView, String r4_String) {
        super.onPageFinished(r3_WebView, r4_String);
        this.a.h.setVisibility(0);
    }

    public void onPageStarted(WebView r4_WebView, String r5_String, Bitmap r6_Bitmap) {
        Util.a(PKDialog.b, "Webview loading URL: " + r5_String);
        super.onPageStarted(r4_WebView, r5_String, r6_Bitmap);
    }

    public void onReceivedError(WebView r4_WebView, int r5i, String r6_String, String r7_String) {
        super.onReceivedError(r4_WebView, r5i, r6_String, r7_String);
        this.a.e.onError(new UiError(r5i, r6_String, r7_String));
        if (PKDialog.i == null || PKDialog.i.get() == null) {
            this.a.dismiss();
        } else {
            Toast.makeText((Context) PKDialog.i.get(), "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\u6216\u7cfb\u7edf\u9519\u8bef", 0).show();
            this.a.dismiss();
        }
    }

    public void onReceivedSslError(WebView r1_WebView, SslErrorHandler r2_SslErrorHandler, SslError r3_SslError) {
        r2_SslErrorHandler.proceed();
    }

    public boolean shouldOverrideUrlLoading(WebView r5_WebView, String r6_String) {
        Util.a(PKDialog.b, "Redirect URL: " + r6_String);
        if (r6_String.startsWith(ServerSetting.getInstance().getSettingUrl((Context) PKDialog.i.get(), 1))) {
            this.a.e.onComplete(Util.c(r6_String));
            this.a.dismiss();
            return true;
        } else if (r6_String.startsWith("auth://cancel")) {
            this.a.e.onCancel();
            this.a.dismiss();
            return true;
        } else {
            if (!r6_String.startsWith("auth://close")) {
                return false;
            }
            this.a.dismiss();
            return true;
        }
    }
}