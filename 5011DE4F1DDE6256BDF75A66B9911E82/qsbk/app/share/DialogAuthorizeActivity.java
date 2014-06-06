package qsbk.app.share;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import qsbk.app.R;
import qsbk.app.utils.Base64;

public class DialogAuthorizeActivity extends Activity {
    WebView a;
    int b;
    ProgressBar c;
    ShareUtils d;

    public DialogAuthorizeActivity() {
        this.b = 0;
        this.d = new ShareUtils();
    }

    protected void onCreate(Bundle r9_Bundle) {
        super.onCreate(r9_Bundle);
        setContentView(R.layout.weibo_dialog_authorize);
        Display r0_Display = getWindowManager().getDefaultDisplay();
        LayoutParams r1_LayoutParams = getWindow().getAttributes();
        r1_LayoutParams.height = (int) (((double) r0_Display.getHeight()) * 0.9d);
        r1_LayoutParams.width = (int) (((double) r0_Display.getWidth()) * 0.95d);
        r1_LayoutParams.alpha = 1.0f;
        getWindow().setAttributes(r1_LayoutParams);
        getWindow().setGravity(R.styleable.ActionBar_progressBarPadding);
        this.c = (ProgressBar) findViewById(R.id.loading);
        this.c.setVisibility(Base64.DONT_BREAK_LINES);
        this.a = (WebView) findViewById(R.id.webview);
        this.a.clearCache(true);
        this.a.setInitialScale(100);
        this.a.setVerticalScrollBarEnabled(false);
        this.b = getIntent().getIntExtra("resultCode", 0);
        WebSettings r0_WebSettings = this.a.getSettings();
        r0_WebSettings.setJavaScriptEnabled(true);
        r0_WebSettings.setBuiltInZoomControls(false);
        this.a.setWebViewClient(new b(this, this));
        this.a.loadUrl(ShareUrl.weiboUrl);
    }
}