package qsbk.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.qiubai.library.adview.util.AdViewNetFetchThread;

// compiled from: About.java
class b extends WebViewClient {
    final /* synthetic */ About a;

    b(About r1_About) {
        this.a = r1_About;
    }

    public void onPageFinished(WebView r1_WebView, String r2_String) {
    }

    public void onPageStarted(WebView r1_WebView, String r2_String, Bitmap r3_Bitmap) {
    }

    public void onReceivedError(WebView r4_WebView, int r5i, String r6_String, String r7_String) {
        Toast.makeText(QsbkApp.mContext, "Oh no! " + r6_String, 0).show();
    }

    public boolean shouldOverrideUrlLoading(WebView r7_WebView, String r8_String) {
        new Intent().setAction("android.intent.action.VIEW");
        r7_WebView.loadDataWithBaseURL("file:///android_asset/", this.a.f, "text/html", AdViewNetFetchThread.NetEncoding, null);
        return true;
    }
}