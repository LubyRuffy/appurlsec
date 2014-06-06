package qsbk.app.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.utils.SharePreferenceUtils;

// compiled from: DialogAuthorizeActivity.java
class b extends WebViewClient {
    final /* synthetic */ Activity a;
    final /* synthetic */ DialogAuthorizeActivity b;
    private int c;

    b(DialogAuthorizeActivity r2_DialogAuthorizeActivity, Activity r3_Activity) {
        this.b = r2_DialogAuthorizeActivity;
        this.a = r3_Activity;
        this.c = 0;
    }

    public void onPageFinished(WebView r1_WebView, String r2_String) {
        super.onPageFinished(r1_WebView, r2_String);
    }

    public void onPageStarted(WebView r9_WebView, String r10_String, Bitmap r11_Bitmap) {
        int r4i = 1;
        super.onPageStarted(r9_WebView, r10_String, r11_Bitmap);
        if (r10_String.contains(ThirdParty.KEY_TOKEN) && this.c == 0) {
            this.c++;
            String r0_String = RContactStorage.PRIMARY_KEY;
            if (r10_String.contains("renren")) {
                r0_String = this.b.d.RENREN_ACCESS_TOKEN;
            } else if (r10_String.contains("sina")) {
                r0_String = this.b.d.SINA_ACCESS_TOKEN;
            } else {
                r0_String = this.b.d.QQ_ACCESS_TOKEN;
            }
            StringBuffer r1_StringBuffer = new StringBuffer("accessToken=");
            Matcher r2_Matcher = Pattern.compile("access_token=(.+?)(?:&|$)").matcher(r10_String);
            if (r2_Matcher.find()) {
                r1_StringBuffer.append(r2_Matcher.group(1));
            }
            r2_Matcher = Pattern.compile("expires_in=(.+?)(?:&|$)").matcher(r10_String);
            r1_StringBuffer.append("&");
            if (r2_Matcher.find()) {
                r1_StringBuffer.append("expires_in=").append(Long.valueOf(r2_Matcher.group(r4i)).longValue() * 1000 + System.currentTimeMillis());
            }
            SharePreferenceUtils.setSharePreferencesValue(r0_String, r1_StringBuffer.toString());
            this.b.setResult(this.b.b, new Intent());
            this.a.finish();
        }
    }

    public boolean shouldOverrideUrlLoading(WebView r2_WebView, String r3_String) {
        r2_WebView.loadUrl(r3_String);
        return true;
    }
}