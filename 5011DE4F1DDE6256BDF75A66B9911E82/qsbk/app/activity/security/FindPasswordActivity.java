package qsbk.app.activity.security;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.UIHelper;

public class FindPasswordActivity extends Activity {
    WebView a;
    protected ImageButton b;
    protected TextView c;
    protected String d;
    protected Tracker e;
    WebViewClient f;

    public FindPasswordActivity() {
        this.f = new g(this);
    }

    private void b() {
        EasyTracker.getInstance().setContext(this);
        this.e = EasyTracker.getTracker();
        this.e.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.e);
    }

    protected void a() {
        this.d = "uuid=" + DeviceUtils.getAndroidId() + "&Source=" + "android_" + Constants.localVersionName;
        this.b = (ImageButton) findViewById(R.id.leftBtn);
        if (UIHelper.isNightTheme()) {
            this.b.setBackgroundResource(R.drawable.icon_back_enable_night);
        } else {
            this.b.setBackgroundResource(R.drawable.icon_back_enable);
        }
        this.b.setOnClickListener(new f(this));
        this.c = (TextView) findViewById(R.id.title);
        this.c.setText("\u627e\u56de\u5bc6\u7801");
        findViewById(R.id.rightBtn).setVisibility(Base64.DONT_BREAK_LINES);
        this.a = (WebView) findViewById(R.id.about);
        this.a.setScrollBarStyle(0);
        this.a.getSettings().setBuiltInZoomControls(true);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.setWebViewClient(this.f);
    }

    protected void onCreate(Bundle r4_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r4_Bundle);
        setContentView(R.layout.about);
        a();
        this.a.loadUrl("http://www.qiushibaike.com/new4/fetchpass?" + this.d);
        b();
        this.e.trackView("\u627e\u56de\u5bc6\u7801/");
    }
}