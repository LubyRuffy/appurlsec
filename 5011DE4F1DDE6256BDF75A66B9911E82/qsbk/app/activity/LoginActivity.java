package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.LoginRegisterBaseActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.thirdparty.Oauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyAuthListener;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.ThirdPartyDialogError;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.sso.SsoHandler;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;

public class LoginActivity extends LoginRegisterBaseActivity {
    public static Oauth2AccessToken accessToken;
    private String A;
    private String B;
    private String C;
    private LinearLayout D;
    private LinearLayout E;
    private Handler F;
    private Handler G;
    SsoHandler n;
    Tencent o;
    private ThirdParty z;

    class a implements ThirdPartyAuthListener {
        a() {
        }

        public void onCancel() {
            Toast.makeText(LoginActivity.this.getApplicationContext(), "\u53d6\u6d88\u8ba4\u8bc1", 1).show();
        }

        public void onComplete(Bundle r4_Bundle) {
            LoginActivity.this.B = r4_Bundle.getString(ThirdParty.KEY_TOKEN);
            LoginActivity.this.C = r4_Bundle.getString(ThirdParty.KEY_EXPIRES);
            accessToken = new Oauth2AccessToken(LoginActivity.this.B, LoginActivity.this.C);
            AccessTokenKeeper.keepAccessToken(LoginActivity.this, accessToken);
            LoginActivity.this.a(LoginActivity.this.B, LoginActivity.this.C);
        }

        public void onError(ThirdPartyDialogError r1_ThirdPartyDialogError) {
        }

        public void onThirdPartyException(ThirdPartyException r4_ThirdPartyException) {
            Toast.makeText(LoginActivity.this.getApplicationContext(), "\u8ba4\u8bc1\u5f02\u5e38 : " + r4_ThirdPartyException.getMessage(), 1).show();
        }
    }

    private class b implements IUiListener {
        private b() {
        }

        public void onCancel() {
        }

        public void onComplete(JSONObject r4_JSONObject) {
            try {
                LoginActivity.this.B = r4_JSONObject.getString(ThirdParty.KEY_TOKEN);
                LoginActivity.this.C = r4_JSONObject.getString(ThirdParty.KEY_EXPIRES);
                accessToken = new Oauth2AccessToken(LoginActivity.this.B, LoginActivity.this.C);
                AccessTokenKeeper.keepAccessToken(LoginActivity.this, accessToken);
                LoginActivity.this.a(LoginActivity.this.B, LoginActivity.this.C);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(UiError r1_UiError) {
        }
    }

    public LoginActivity() {
        this.F = new aw(this);
        this.G = new az(this);
    }

    private void a(String r4_String, String r5_String) {
        HashMap r0_HashMap = new HashMap();
        r0_HashMap.put("sns", this.A);
        r0_HashMap.put(QsbkDatabase.TOKEN, r4_String);
        r0_HashMap.put(ThirdParty.KEY_EXPIRES, r5_String);
        this.r.setVisibility(Base64.DONT_BREAK_LINES);
        this.t.setVisibility(0);
        new ax(this, "qbk-LoginAct-1", r0_HashMap).start();
    }

    private void a(String r7_String, String r8_String, String r9_String) {
        StringBuffer r0_StringBuffer = new StringBuffer("accessToken=");
        r0_StringBuffer.append(r7_String);
        r0_StringBuffer.append("&");
        r0_StringBuffer.append("expires_in=").append(Long.valueOf(r8_String).longValue() * 1000 + System.currentTimeMillis());
        SharePreferenceUtils.setSharePreferencesValue(r9_String, r0_StringBuffer.toString());
    }

    protected void c() {
        super.c();
        ((EditText) findViewById(R.id.userName)).setHint("\u6635\u79f0/\u90ae\u7bb1");
        this.v.setHint("\u5bc6\u7801\u586b\u5728\u8fd9\u91cc");
        findViewById(R.id.register).setOnClickListener(new as(this));
        findViewById(R.id.question).setOnClickListener(new at(this));
        this.D = (LinearLayout) findViewById(R.id.sinaLayout);
        this.D.setOnTouchListener(QsbkApp.TouchDark);
        this.E = (LinearLayout) findViewById(R.id.qqLayout);
        this.E.setOnTouchListener(QsbkApp.TouchDark);
    }

    protected String d() {
        this.u.trackView("\u767b\u5f55\u7cd7\u767e/");
        return Constants.LOGIN;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }

    public String getCachePassword() {
        return SharePreferenceUtils.getSharePreferencesValue("password");
    }

    public String getCacheUserName() {
        return SharePreferenceUtils.getSharePreferencesValue("loginName");
    }

    public String getCustomerTitle() {
        return "\u767b\u5f55\u7cd7\u767e";
    }

    public Handler getHanler() {
        return this.G;
    }

    public int getLayoutId() {
        return R.layout.activity_login;
    }

    public void initListener() {
        super.initListener();
        this.D.setOnClickListener(this);
        this.E.setOnClickListener(this);
    }

    protected void onActivityResult(int r2i, int r3i, Intent r4_Intent) {
        super.onActivityResult(r2i, r3i, r4_Intent);
        if (this.n != null) {
            this.n.authorizeCallBack(r2i, r3i, r4_Intent);
        }
        if (this.o != null) {
            this.o.onActivityResult(r2i, r3i, r4_Intent);
        }
    }

    public void onClick(View r4_View) {
        super.onClick(r4_View);
        switch (r4_View.getId()) {
            case R.id.sinaLayout:
                this.A = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
                this.z = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
                this.n = new SsoHandler(this, this.z);
                this.n.authorize(new a());
                break;
            case R.id.qqLayout:
                this.A = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
                IUiListener r0_IUiListener = new b(null);
                this.o = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, getApplicationContext());
                this.o.login(this, "get_user_info,get_simple_userinfo", r0_IUiListener);
                break;
            case R.id.leftBtn:
                finish();
                break;
        }
    }

    public void onLoginSuccess() {
        new ay(this, "qbk-LoginAct-2").start();
    }
}