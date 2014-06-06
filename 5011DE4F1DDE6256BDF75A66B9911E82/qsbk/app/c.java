package qsbk.app;

import android.app.AlertDialog.Builder;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

// compiled from: About.java
class c extends WebChromeClient {
    final /* synthetic */ About a;

    c(About r1_About) {
        this.a = r1_About;
    }

    public boolean onJsAlert(WebView r4_WebView, String r5_String, String r6_String, JsResult r7_JsResult) {
        new Builder(this.a).setTitle("\u6e29\u99a8\u63d0\u793a\uff1a").setMessage(r6_String).setPositiveButton(17039370, new d(this, r7_JsResult)).setCancelable(false).create().show();
        return true;
    }
}