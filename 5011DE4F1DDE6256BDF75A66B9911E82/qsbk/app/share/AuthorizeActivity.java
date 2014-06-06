package qsbk.app.share;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.SecDefaultActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.thirdparty.Oauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyAuthListener;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.ThirdPartyDialogError;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.sso.SsoHandler;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

public class AuthorizeActivity extends SecDefaultActivity {
    public static Oauth2AccessToken accessToken;
    SsoHandler n;
    int o;
    private ThirdParty p;
    private String q;
    private String r;

    class a implements ThirdPartyAuthListener {
        a() {
        }

        public void onCancel() {
            Toast.makeText(AuthorizeActivity.this.getApplicationContext(), "\u53d6\u6d88\u8ba4\u8bc1", 1).show();
            AuthorizeActivity.this.finish();
        }

        public void onComplete(Bundle r5_Bundle) {
            AuthorizeActivity.this.q = r5_Bundle.getString(ThirdParty.KEY_TOKEN);
            AuthorizeActivity.this.r = r5_Bundle.getString(ThirdParty.KEY_EXPIRES);
            accessToken = new Oauth2AccessToken(AuthorizeActivity.this.q, AuthorizeActivity.this.r);
            AccessTokenKeeper.keepAccessToken(AuthorizeActivity.this, accessToken);
            AuthorizeActivity.this.a(AuthorizeActivity.this.q, AuthorizeActivity.this.r, QsbkApp.currentUser.userId + "_sina_access_token");
            AuthorizeActivity.this.setResult(AuthorizeActivity.this.o, new Intent());
            AuthorizeActivity.this.finish();
        }

        public void onError(ThirdPartyDialogError r1_ThirdPartyDialogError) {
        }

        public void onThirdPartyException(ThirdPartyException r4_ThirdPartyException) {
            Toast.makeText(AuthorizeActivity.this.getApplicationContext(), "\u8ba4\u8bc1\u5f02\u5e38 : " + r4_ThirdPartyException.getMessage(), 1).show();
        }
    }

    public AuthorizeActivity() {
        this.o = 0;
    }

    private void a(String r7_String, String r8_String, String r9_String) {
        StringBuffer r0_StringBuffer = new StringBuffer("accessToken=");
        r0_StringBuffer.append(r7_String);
        r0_StringBuffer.append("&");
        r0_StringBuffer.append("expires_in=").append(Long.valueOf(r8_String).longValue() * 1000 + System.currentTimeMillis());
        SharePreferenceUtils.setSharePreferencesValue(r9_String, r0_StringBuffer.toString());
    }

    public String getCustomerTitle() {
        return "sina".equals(getIntent().getStringExtra(QQDialogAuthorizeActivity.TARGET)) ? "\u7cd7\u4e8b\u767e\u79d1-\u65b0\u6d6a\u5fae\u535a" : "\u7cd7\u4e8b\u767e\u79d1-\u4eba\u4eba\u7f51";
    }

    protected void onActivityResult(int r2i, int r3i, Intent r4_Intent) {
        super.onActivityResult(r2i, r3i, r4_Intent);
        if (this.n != null) {
            this.n.authorizeCallBack(r2i, r3i, r4_Intent);
        }
    }

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        setContentView(R.layout.weibo_authorize);
        this.o = getIntent().getIntExtra("resultCode", 0);
        c();
        findViewById(R.id.topLoading).setVisibility(0);
        this.Q.setVisibility(Base64.DONT_BREAK_LINES);
        if (UIHelper.isNightTheme()) {
            this.P.setBackgroundResource(R.drawable.icon_back_enable_night);
        } else {
            this.P.setBackgroundResource(R.drawable.icon_back_enable);
        }
        this.P.setOnClickListener(new a(this));
        this.p = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        this.n = new SsoHandler(this, this.p);
        this.n.authorize(new a());
    }
}