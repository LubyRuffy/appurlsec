package qsbk.app.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.R;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.widget.listview.XListViewHeader;

public class QQDialogAuthorizeActivity extends Activity {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String AUTH_BROADCAST = "com.tencent.auth.BROWSER";
    public static final String CALLBACK = "tencentauth://auth.qq.com";
    public static final String CLIENT_ID = "client_id";
    public static final String ERROR_DES = "error_description";
    public static final String ERROR_RET = "error";
    public static final String EXPIRES_IN = "expires_in";
    public static final String SCOPE = "scope";
    public static final String TARGET = "target";
    ShareUtils a;
    int b;
    ProgressBar c;
    private String d;
    private String e;
    private WebView f;
    private String g;
    public String mAppid;

    private class a extends WebViewClient {
        private a() {
        }

        public void onPageFinished(WebView r3_WebView, String r4_String) {
            QQDialogAuthorizeActivity.this.c.setVisibility(Base64.DONT_BREAK_LINES);
        }

        public void onPageStarted(WebView r1_WebView, String r2_String, Bitmap r3_Bitmap) {
        }

        public void onReceivedError(WebView r1_WebView, int r2i, String r3_String, String r4_String) {
            super.onReceivedError(r1_WebView, r2i, r3_String, r4_String);
        }

        public void onReceivedSslError(WebView r1_WebView, SslErrorHandler r2_SslErrorHandler, SslError r3_SslError) {
            r2_SslErrorHandler.proceed();
        }

        public boolean shouldOverrideUrlLoading(WebView r4_WebView, String r5_String) {
            if (!r5_String.startsWith(QQDialogAuthorizeActivity.this.e + "?")) {
                return false;
            }
            QQDialogAuthorizeActivity.this.a(r5_String);
            QQDialogAuthorizeActivity.this.setResult(QQDialogAuthorizeActivity.this.b, new Intent());
            return true;
        }
    }

    public QQDialogAuthorizeActivity() {
        this.e = "auth://tauth.qq.com/";
        this.mAppid = ThirdPartyConstants.QQ_CONSUMER_KEY;
        this.g = "get_user_info,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album";
        this.a = new ShareUtils();
        this.b = 0;
    }

    private String a() {
        try {
            Enumeration r1_Enumeration = NetworkInterface.getNetworkInterfaces();
            while (r1_Enumeration.hasMoreElements()) {
                Enumeration r2_Enumeration = ((NetworkInterface) r1_Enumeration.nextElement()).getInetAddresses();
                while (r2_Enumeration.hasMoreElements()) {
                    InetAddress r0_InetAddress = (InetAddress) r2_Enumeration.nextElement();
                    if (!r0_InetAddress.isLoopbackAddress()) {
                        return r0_InetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            Log.d("TAG", e.toString());
        }
        return RContactStorage.PRIMARY_KEY;
    }

    private void a(String r8_String) {
        int r3i = 1;
        StringBuffer r0_StringBuffer = new StringBuffer("accessToken=");
        Matcher r1_Matcher = Pattern.compile("access_token=(.+?)(?:&|$)").matcher(r8_String);
        if (r1_Matcher.find()) {
            r0_StringBuffer.append(r1_Matcher.group(1));
        }
        r1_Matcher = Pattern.compile("expires_in=(.+?)(?:&|$)").matcher(r8_String);
        r0_StringBuffer.append("&");
        if (r1_Matcher.find()) {
            r0_StringBuffer.append("expires_in=").append(Long.valueOf(r1_Matcher.group(r3i)).longValue() * 1000 + System.currentTimeMillis());
        }
        SharePreferenceUtils.setSharePreferencesValue(this.a.QQ_ACCESS_TOKEN, r0_StringBuffer.toString());
        finish();
    }

    private String b() {
        return VERSION.RELEASE;
    }

    private String c() {
        return Build.MODEL;
    }

    private String d() {
        return VERSION.SDK;
    }

    protected void onCreate(Bundle r10_Bundle) {
        super.onCreate(r10_Bundle);
        getWindow().requestFeature(XListViewHeader.STATE_REFRESHING);
        setContentView(R.layout.weibo_dialog_authorize);
        Display r0_Display = getWindowManager().getDefaultDisplay();
        LayoutParams r1_LayoutParams = getWindow().getAttributes();
        r1_LayoutParams.height = (int) (((double) r0_Display.getHeight()) * 0.9d);
        r1_LayoutParams.width = (int) (((double) r0_Display.getWidth()) * 0.95d);
        r1_LayoutParams.alpha = 1.0f;
        getWindow().setAttributes(r1_LayoutParams);
        getWindow().setGravity(R.styleable.ActionBar_progressBarPadding);
        this.c = (ProgressBar) findViewById(R.id.loading);
        this.f = (WebView) findViewById(R.id.webview);
        this.d = "https://graph.qq.com/oauth2.0/authorize?response_type=token&display=mobile&client_id=%s&scope=%s&redirect_uri=%s&status_userip=%s&status_os=%s&status_machine=%s&status_version=%s#" + System.currentTimeMillis();
        String r0_String = this.d;
        Object[] r1_ObjectA = new Object[7];
        r1_ObjectA[0] = this.mAppid;
        r1_ObjectA[1] = this.g;
        r1_ObjectA[2] = this.e;
        r1_ObjectA[3] = a();
        r1_ObjectA[4] = b();
        r1_ObjectA[5] = c();
        r1_ObjectA[6] = d();
        r0_String = String.format(r0_String, r1_ObjectA);
        this.f.clearCache(true);
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.getSettings().setBuiltInZoomControls(true);
        this.b = getIntent().getIntExtra("resultCode", 0);
        this.f.setWebChromeClient(new d(this, this));
        this.f.setInitialScale(100);
        this.f.setVerticalScrollBarEnabled(false);
        this.f.setWebViewClient(new a(null));
        WebSettings r1_WebSettings = this.f.getSettings();
        r1_WebSettings.setJavaScriptEnabled(true);
        r1_WebSettings.setBuiltInZoomControls(true);
        this.f.loadUrl(r0_String);
    }
}