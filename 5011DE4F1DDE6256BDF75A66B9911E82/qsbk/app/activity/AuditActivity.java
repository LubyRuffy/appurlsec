package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewClient;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

public class AuditActivity extends StatFragmentActivity implements CordovaInterface {
    String n;
    CordovaWebView o;
    RelativeLayout p;
    ImageButton q;

    class a extends CordovaWebViewClient {
        public a(CordovaInterface r2_CordovaInterface, CordovaWebView r3_CordovaWebView) {
            super(r2_CordovaInterface, r3_CordovaWebView);
        }

        public void onPageFinished(WebView r3_WebView, String r4_String) {
            AuditActivity.this.p.setVisibility(Base64.DONT_BREAK_LINES);
            super.onPageFinished(r3_WebView, r4_String);
        }

        public void onReceivedError(WebView r4_WebView, int r5i, String r6_String, String r7_String) {
            if (r7_String == null || r7_String.indexOf("exception firing destroy event from native") == -1) {
                Toast.makeText(AuditActivity.this.getActivity(), "\u5ba1\u6838\u52a0\u8f7d\u5931\u8d25\uff0c\u7f51\u7edc\u4e0d\u7ed9\u529b\u554a", 0).show();
                AuditActivity.this.finish();
            }
        }
    }

    public AuditActivity() {
        this.n = "http://insp.qiushibaike.com/app/and_review.html";
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }

    public Activity getActivity() {
        return this;
    }

    public ExecutorService getThreadPool() {
        return null;
    }

    public void onCreate(Bundle r6_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r6_Bundle);
        setContentView(R.layout.activity_audit);
        this.o = (CordovaWebView) findViewById(R.id.webview);
        this.o.clearCache(true);
        this.p = (RelativeLayout) findViewById(R.id.mark);
        this.o.setWebViewClient(new a(this, this.o));
        this.q = (ImageButton) findViewById(R.id.close);
        if (UIHelper.isNightTheme()) {
            this.q.setBackgroundResource(R.drawable.icon_back_enable_night);
            ((ProgressBar) findViewById(R.id.loadmore_progressbar)).setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressbar_night));
        } else {
            this.q.setBackgroundResource(R.drawable.icon_back_enable);
            ((ProgressBar) findViewById(R.id.loadmore_progressbar)).setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressbar));
        }
        this.q.setOnClickListener(new a(this));
        String r0_String = SharePreferenceUtils.getSharePreferencesValue("cacheTime");
        if (TextUtils.isEmpty(r0_String) || Long.valueOf(r0_String).longValue() + 86400000 >= System.currentTimeMillis() || Integer.valueOf(VERSION.SDK).intValue() >= 14) {
            SharePreferenceUtils.setSharePreferencesValue("cacheTime", String.valueOf(System.currentTimeMillis()));
            this.o.loadUrl(this.n);
            QsbkApp.audit = this;
        } else {
            this.o.clearCache(true);
            SharePreferenceUtils.setSharePreferencesValue("cacheTime", String.valueOf(System.currentTimeMillis()));
            this.o.loadUrl(this.n);
            QsbkApp.audit = this;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.o.handleDestroy();
    }

    public Object onMessage(String r2_String, Object r3_Object) {
        return null;
    }

    protected void onPause() {
        super.onPause();
    }

    public void setActivityResultCallback(CordovaPlugin r1_CordovaPlugin) {
    }

    public void setFullscreen() {
        getWindow().clearFlags(LVBuffer.MAX_STRING_LENGTH);
        requestWindowFeature(1);
        getWindow().setFlags(Util.BYTE_OF_KB, Util.BYTE_OF_KB);
    }

    public void startActivityForResult(CordovaPlugin r1_CordovaPlugin, Intent r2_Intent, int r3i) {
    }
}