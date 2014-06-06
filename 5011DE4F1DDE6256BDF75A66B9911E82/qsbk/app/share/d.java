package qsbk.app.share;

import android.app.Activity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import qsbk.app.R;

// compiled from: QQDialogAuthorizeActivity.java
class d extends WebChromeClient {
    final /* synthetic */ Activity a;
    final /* synthetic */ QQDialogAuthorizeActivity b;

    d(QQDialogAuthorizeActivity r1_QQDialogAuthorizeActivity, Activity r2_Activity) {
        this.b = r1_QQDialogAuthorizeActivity;
        this.a = r2_Activity;
    }

    public void onProgressChanged(WebView r3_WebView, int r4i) {
        this.a.setTitle("    Loading...");
        this.a.setProgress(r4i * 100);
        if (r4i == 100) {
            this.a.setTitle(R.string.app_name);
        }
    }
}