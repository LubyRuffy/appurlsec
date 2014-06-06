package qsbk.app.activity.security;

import android.webkit.WebView;
import android.webkit.WebViewClient;

// compiled from: FindPasswordActivity.java
class g extends WebViewClient {
    final /* synthetic */ FindPasswordActivity a;

    g(FindPasswordActivity r1_FindPasswordActivity) {
        this.a = r1_FindPasswordActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView r3_WebView, String r4_String) {
        r3_WebView.loadUrl(r4_String + "&" + this.a.d);
        return false;
    }
}