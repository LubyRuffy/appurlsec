package qsbk.app.share;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.SecDefaultActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.thirdparty.Oauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

public class QQAuthorizeActivity extends SecDefaultActivity {
    private static Oauth2AccessToken q;
    public String mAppid;
    int n;
    private String o;
    private String p;
    private Tencent r;

    private class a implements IUiListener {
        private a() {
        }

        public void onCancel() {
            Toast.makeText(QQAuthorizeActivity.this.getApplicationContext(), "\u53d6\u6d88\u8ba4\u8bc1", 1).show();
            QQAuthorizeActivity.this.finish();
        }

        public void onComplete(JSONObject r5_JSONObject) {
            try {
                QQAuthorizeActivity.this.o = r5_JSONObject.getString(ThirdParty.KEY_TOKEN);
                QQAuthorizeActivity.this.p = r5_JSONObject.getString(ThirdParty.KEY_EXPIRES);
                q = new Oauth2AccessToken(QQAuthorizeActivity.this.o, QQAuthorizeActivity.this.p);
                AccessTokenKeeper.keepAccessToken(QQAuthorizeActivity.this, q);
                QQAuthorizeActivity.this.a(QQAuthorizeActivity.this.o, QQAuthorizeActivity.this.p, QsbkApp.currentUser.userId + "_qq_access_token");
                QQAuthorizeActivity.this.setResult(QQAuthorizeActivity.this.n, new Intent());
                QQAuthorizeActivity.this.finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(UiError r1_UiError) {
        }
    }

    public QQAuthorizeActivity() {
        this.mAppid = ThirdPartyConstants.QQ_CONSUMER_KEY;
        this.n = 0;
    }

    private void a(String r7_String, String r8_String, String r9_String) {
        StringBuffer r0_StringBuffer = new StringBuffer("accessToken=");
        r0_StringBuffer.append(r7_String);
        r0_StringBuffer.append("&");
        r0_StringBuffer.append("expires_in=").append(Long.valueOf(r8_String).longValue() * 1000 + System.currentTimeMillis());
        SharePreferenceUtils.setSharePreferencesValue(r9_String, r0_StringBuffer.toString());
    }

    public String getCustomerTitle() {
        return "\u7cd7\u4e8b\u767e\u79d1-\u817e\u8baf\u5206\u4eab";
    }

    protected void onActivityResult(int r2i, int r3i, Intent r4_Intent) {
        super.onActivityResult(r2i, r3i, r4_Intent);
        if (this.r != null) {
            this.r.onActivityResult(r2i, r3i, r4_Intent);
        }
    }

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        setContentView(R.layout.weibo_authorize);
        c();
        this.n = getIntent().getIntExtra("resultCode", 0);
        findViewById(R.id.topLoading).setVisibility(0);
        this.Q.setVisibility(Base64.DONT_BREAK_LINES);
        if (UIHelper.isNightTheme()) {
            this.P.setBackgroundResource(R.drawable.icon_back_enable_night);
        } else {
            this.P.setBackgroundResource(R.drawable.icon_back_enable);
        }
        this.P.setOnClickListener(new c(this));
        IUiListener r0_IUiListener = new a(null);
        this.r = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, getApplicationContext());
        this.r.login(this, "get_user_info,get_user_profile,add_share,add_topic,list_album,upload_pic,add_album,add_t,add_pic_t", r0_IUiListener);
    }
}