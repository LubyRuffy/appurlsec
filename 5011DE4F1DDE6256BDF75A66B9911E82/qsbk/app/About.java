package qsbk.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import qsbk.app.bean.Base;
import qsbk.app.utils.Base64;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

public class About extends Activity {
    WebView a;
    protected ImageButton b;
    protected TextView c;
    WebViewClient d;
    WebChromeClient e;
    private String f;
    private String g;

    private class a {
        private a() {
        }

        public void jsMethod(String r3_String) {
            About.this.loadUrl("file:///android_asset/feedback_success.html");
        }
    }

    public About() {
        this.d = new b(this);
        this.e = new c(this);
    }

    public static String readData(InputStream r4_InputStream, String r5_String) throws Exception {
        ByteArrayOutputStream r0_ByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] r1_byteA = new byte[1024];
        while (true) {
            int r2i = r4_InputStream.read(r1_byteA);
            if (r2i != -1) {
                r0_ByteArrayOutputStream.write(r1_byteA, 0, r2i);
            } else {
                r1_byteA = r0_ByteArrayOutputStream.toByteArray();
                r0_ByteArrayOutputStream.close();
                r4_InputStream.close();
                return new String(r1_byteA, r5_String);
            }
        }
    }

    protected void a() {
        boolean r2z = true;
        this.b = (ImageButton) findViewById(R.id.leftBtn);
        if (UIHelper.isNightTheme()) {
            this.b.setBackgroundResource(R.drawable.icon_back_enable_night);
        } else {
            this.b.setBackgroundResource(R.drawable.icon_back_enable);
        }
        this.b.setOnClickListener(new a(this));
        this.c = (TextView) findViewById(R.id.title);
        this.c.setText(b());
        findViewById(R.id.rightBtn).setVisibility(Base64.DONT_BREAK_LINES);
        this.a = (WebView) findViewById(R.id.about);
        this.a.setScrollBarStyle(0);
        this.a.getSettings().setBuiltInZoomControls(r2z);
        this.a.getSettings().setJavaScriptEnabled(r2z);
        this.a.setWebViewClient(this.d);
        this.a.setWebChromeClient(this.e);
        this.a.addJavascriptInterface(new a(null), "stub");
    }

    protected String b() {
        return "about".equals(this.g) ? getResources().getString(R.string.title_about) : getResources().getString(R.string.title_feedback);
    }

    protected void onCreate(Bundle r3_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r3_Bundle);
        setContentView(R.layout.about);
        this.g = getIntent().getStringExtra("targetPage");
        a();
    }

    protected void onResume() {
        super.onResume();
        if ("about".equals(this.g)) {
            try {
                this.f = readData(getAssets().open("about.html"), Base.UTF8);
                this.f = this.f.replace("#AppVersion#", Constants.localVersionName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e_2) {
                e_2.printStackTrace();
            }
            this.a.loadDataWithBaseURL("file:///android_asset/", this.f, "text/html", AdViewNetFetchThread.NetEncoding, null);
        } else {
            try {
                this.f = readData(getAssets().open("feedback.html"), Base.UTF8);
                this.f = this.f.replace("#PH_FEEDBACK_URL#", Constants.FEEDBACK);
                this.f = this.f.replace("#PH_SOURCE#", "android_" + Constants.localVersionName);
                if (QsbkApp.currentUser == null) {
                    this.f = this.f.replace("#PH_USERID#", DeviceUtils.getAndroidId());
                } else {
                    this.f = this.f.replace("#PH_USERID#", QsbkApp.currentUser.userId + "|" + DeviceUtils.getAndroidId());
                }
                this.f = this.f.replace("#PH_THEME#", SharePreferenceUtils.getSharePreferencesValue("themeid"));
                this.f = this.f.replace("#PH_DEVICE#", Build.FINGERPRINT);
                this.a.loadDataWithBaseURL("file:///android_asset/", this.f, "text/html", AdViewNetFetchThread.NetEncoding, null);
            } catch (Exception e_3) {
                e_3.printStackTrace();
            }
        }
    }
}