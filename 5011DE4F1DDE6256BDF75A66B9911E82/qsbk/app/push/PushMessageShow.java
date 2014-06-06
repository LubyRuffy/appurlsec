package qsbk.app.push;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.Base64;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewHeader;

public class PushMessageShow extends Activity {
    public static String title;
    public static String url;
    WebView a;
    protected ImageButton b;
    protected TextView c;

    static {
        title = "\u901a\u77e5";
        url = "http://www.qiushibaike.com";
    }

    private void b() {
        int r0i = getIntent().getIntExtra("messageId", -1);
        if (r0i != -1) {
            PushPingBack r1_PushPingBack = PushMessageReceiver.getPushPingBackForMessageId(Integer.valueOf(r0i));
            if (r1_PushPingBack != null) {
                r1_PushPingBack.receiveMessage();
                PushMessageReceiver.removePushPingBackForMessageId(Integer.valueOf(r0i));
            }
        }
    }

    protected void a() {
        this.b = (ImageButton) findViewById(R.id.leftBtn);
        if (UIHelper.isNightTheme()) {
            this.b.setBackgroundResource(R.drawable.icon_back_enable_night);
        } else {
            this.b.setBackgroundResource(R.drawable.icon_back_enable);
        }
        this.b.setOnClickListener(new a(this));
        this.c = (TextView) findViewById(R.id.title);
        this.c.setText(title);
        findViewById(R.id.rightBtn).setVisibility(Base64.DONT_BREAK_LINES);
        this.a = (WebView) findViewById(R.id.about);
        this.a.setScrollBarStyle(0);
        this.a.getSettings().setCacheMode(XListViewHeader.STATE_REFRESHING);
        this.a.getSettings().setBuiltInZoomControls(true);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.loadUrl(url);
    }

    protected void onCreate(Bundle r2_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r2_Bundle);
        setContentView(R.layout.about);
        b();
        a();
        QsbkApp.messageCount = 1;
    }
}