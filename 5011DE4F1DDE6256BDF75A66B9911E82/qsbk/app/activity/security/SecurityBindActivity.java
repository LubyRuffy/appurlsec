package qsbk.app.activity.security;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.qq.e.v2.constants.Constants.KEYS;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.thirdparty.Oauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyAuthListener;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.ThirdPartyDialogError;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;
import qsbk.app.thirdparty.net.RequestListener;
import qsbk.app.thirdparty.sso.SsoHandler;
import qsbk.app.utils.Base64;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewHeader;

public class SecurityBindActivity extends Activity implements OnClickListener {
    SsoHandler a;
    Tencent b;
    HashMap<String, Object> c;
    Handler d;
    Handler e;
    Handler f;
    private ImageButton g;
    private Button h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private int m;
    private Tracker n;
    private RelativeLayout o;
    private RelativeLayout p;
    private RelativeLayout q;
    private Button r;
    private ThirdParty s;
    private String t;
    private String u;
    private JSONObject v;
    private String w;
    private boolean x;
    private String y;

    class a implements ThirdPartyAuthListener {
        a() {
        }

        public void onCancel() {
            Toast.makeText(SecurityBindActivity.this.getApplicationContext(), "\u53d6\u6d88\u8ba4\u8bc1", 1).show();
        }

        public void onComplete(Bundle r7_Bundle) {
            String r0_String = r7_Bundle.getString(ThirdParty.KEY_TOKEN);
            Oauth2AccessToken r2_Oauth2AccessToken = new Oauth2AccessToken(r0_String, r7_Bundle.getString(ThirdParty.KEY_EXPIRES));
            AccessTokenKeeper.keepAccessToken(SecurityBindActivity.this, r2_Oauth2AccessToken);
            if (TextUtils.isEmpty(SecurityBindActivity.this.u)) {
                if (SecurityBindActivity.this.t.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                    SecurityBindActivity.this.c.put("wbtoken", r0_String);
                } else {
                    SecurityBindActivity.this.c.put("qqtoken", r0_String);
                }
                if (!TextUtils.isEmpty(SecurityBindActivity.this.y)) {
                    SecurityBindActivity.this.updateUserInfo(SecurityBindActivity.this.e.obtainMessage(), SecurityBindActivity.this.c);
                }
            } else {
                SecurityBindActivity.this.i.setText("\u7ed1\u5b9a\u4e2d...");
                UsersAPI r1_UsersAPI = new UsersAPI(r2_Oauth2AccessToken);
                if (SecurityBindActivity.this.t.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                    SecurityBindActivity.this.w = r7_Bundle.getString("uid");
                    r1_UsersAPI.getSinaUser(Long.valueOf(SecurityBindActivity.this.w).longValue(), new d());
                    SecurityBindActivity.this.c.put("wbtoken", r0_String);
                }
            }
        }

        public void onError(ThirdPartyDialogError r1_ThirdPartyDialogError) {
        }

        public void onThirdPartyException(ThirdPartyException r4_ThirdPartyException) {
            Toast.makeText(SecurityBindActivity.this.getApplicationContext(), "\u8ba4\u8bc1\u5f02\u5e38 : " + r4_ThirdPartyException.getMessage(), 1).show();
        }
    }

    private class b implements IRequestListener {
        private String b;
        private Boolean c;

        public b(String r3_String, boolean r4z) {
            this.b = "all";
            this.c = Boolean.valueOf(false);
            this.b = r3_String;
            this.c = Boolean.valueOf(r4z);
        }

        protected void a(JSONObject r3_JSONObject, Object r4_Object) {
            try {
                if (r3_JSONObject.getInt(KEYS.RET) == 100030 && this.c.booleanValue()) {
                    SecurityBindActivity.this.runOnUiThread(new r(this));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("toddtest", r3_JSONObject.toString());
            }
        }

        public void onComplete(JSONObject r3_JSONObject, Object r4_Object) {
            try {
                SecurityBindActivity.this.a(r3_JSONObject.get(RContact.COL_NICKNAME).toString());
                a(r3_JSONObject, r4_Object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onConnectTimeoutException(ConnectTimeoutException r1_ConnectTimeoutException, Object r2_Object) {
        }

        public void onHttpStatusException(HttpStatusException r1_HttpStatusException, Object r2_Object) {
        }

        public void onIOException(IOException r1_IOException, Object r2_Object) {
        }

        public void onJSONException(JSONException r1_JSONException, Object r2_Object) {
        }

        public void onMalformedURLException(MalformedURLException r1_MalformedURLException, Object r2_Object) {
        }

        public void onNetworkUnavailableException(NetworkUnavailableException r1_NetworkUnavailableException, Object r2_Object) {
        }

        public void onSocketTimeoutException(SocketTimeoutException r1_SocketTimeoutException, Object r2_Object) {
        }

        public void onUnknowException(Exception r1_Exception, Object r2_Object) {
        }
    }

    private class c implements IUiListener {
        private String b;

        private c() {
        }

        protected void a(JSONObject r4_JSONObject) {
            try {
                this.b = r4_JSONObject.getString(ThirdParty.KEY_TOKEN);
                SecurityBindActivity.this.c.put("qqtoken", this.b);
                if (!TextUtils.isEmpty(SecurityBindActivity.this.y)) {
                    SecurityBindActivity.this.updateUserInfo(SecurityBindActivity.this.e.obtainMessage(), SecurityBindActivity.this.c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onCancel() {
        }

        public void onComplete(JSONObject r2_JSONObject) {
            SecurityBindActivity.this.f();
            a(r2_JSONObject);
        }

        public void onError(UiError r1_UiError) {
        }
    }

    class d implements RequestListener {
        d() {
        }

        public void onComplete(String r4_String) {
            try {
                JSONObject r0_JSONObject = new JSONObject(r4_String);
                Message r1_Message = SecurityBindActivity.this.f.obtainMessage();
                r1_Message.obj = r0_JSONObject.getString("screen_name");
                r1_Message.sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(ThirdPartyException r1_ThirdPartyException) {
        }

        public void onIOException(IOException r1_IOException) {
        }
    }

    public SecurityBindActivity() {
        this.m = 20;
        this.x = false;
        this.c = new HashMap();
        this.y = RContactStorage.PRIMARY_KEY;
        this.d = new n(this);
        this.e = new p(this);
        this.f = new q(this);
    }

    private void a(String r3_String) {
        Message r0_Message = this.f.obtainMessage();
        r0_Message.what = 1;
        r0_Message.obj = r3_String;
        r0_Message.sendToTarget();
    }

    private void b() {
        EasyTracker.getInstance().setContext(this);
        this.n = EasyTracker.getTracker();
        this.n.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.n);
    }

    private void c() {
        this.g = (ImageButton) findViewById(R.id.leftBtn);
        if (TextUtils.isEmpty(this.y)) {
            if (UIHelper.isNightTheme()) {
                this.g.setBackgroundResource(R.drawable.icon_back_enable_night);
            } else {
                this.g.setBackgroundResource(R.drawable.icon_back_enable);
            }
        } else if (UIHelper.isNightTheme()) {
            this.g.setBackgroundResource(R.drawable.icon_close_large_night);
        } else {
            this.g.setBackgroundResource(R.drawable.icon_close_large);
        }
        this.h = (Button) findViewById(R.id.rightBtn);
        this.h.setVisibility(Base64.DONT_BREAK_LINES);
        this.g.setOnTouchListener(QsbkApp.TouchDark);
        this.h.setOnTouchListener(QsbkApp.TouchDark);
        this.i = (TextView) findViewById(R.id.title);
        if (TextUtils.isEmpty(this.u)) {
            this.i.setText("\u8bbe\u5b9a\u5bc6\u4fdd");
        } else {
            this.i.setText("\u5bc6\u4fdd");
        }
        this.q = (RelativeLayout) findViewById(R.id.mailLayout);
        this.o = (RelativeLayout) findViewById(R.id.sinaLayout);
        this.p = (RelativeLayout) findViewById(R.id.qqLayout);
        this.r = (Button) findViewById(R.id.overRegister);
        if (TextUtils.isEmpty(this.y)) {
            if (TextUtils.isEmpty(this.u)) {
                this.r.setVisibility(Base64.DONT_BREAK_LINES);
                findViewById(R.id.top_hint).setVisibility(Base64.DONT_BREAK_LINES);
            }
        } else {
            this.r.setVisibility(0);
            findViewById(R.id.top_hint).setVisibility(0);
            this.r.setText("\u5b8c\u6210\u7ed1\u5b9a");
            ((TextView) findViewById(R.id.top_hint)).setText("\u60a8\u7684\u5e10\u53f7\u4e0d\u5b89\u5168\uff0c\u8bf7\u7ed1\u5b9a\u4efb\u4e00\u5bc6\u4fdd");
        }
        this.j = (TextView) findViewById(R.id.sina);
        this.k = (TextView) findViewById(R.id.qq);
        this.l = (TextView) findViewById(R.id.mail);
        if (QsbkApp.currentUser != null) {
            if (!TextUtils.isEmpty(QsbkApp.currentUser.email.trim())) {
                this.l.setText(QsbkApp.currentUser.email);
                findViewById(R.id.email_mark).setVisibility(0);
            }
            if (!TextUtils.isEmpty(QsbkApp.currentUser.wb.trim())) {
                this.j.setText(QsbkApp.currentUser.wb);
                findViewById(R.id.sina_mark).setVisibility(0);
            }
            if (!TextUtils.isEmpty(QsbkApp.currentUser.qq)) {
                this.k.setText(QsbkApp.currentUser.qq);
                findViewById(R.id.qq_mark).setVisibility(0);
            }
        }
    }

    private void d() {
        LinearLayout r0_LinearLayout = (LinearLayout) this.g.getParent();
        r0_LinearLayout.post(new h(this, r0_LinearLayout));
        r0_LinearLayout = (LinearLayout) this.h.getParent();
        r0_LinearLayout.post(new j(this, r0_LinearLayout));
        this.r.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
    }

    private void e() {
        new o(this, "bqk-Security1").start();
    }

    private void f() {
        this.b.requestAsync(com.tencent.tauth.Constants.GRAPH_SIMPLE_USER_INFO, null, HttpManager.HTTPMETHOD_GET, new b("get_simple_userinfo", false), null);
    }

    protected void a() {
        SharePreferenceUtils.setSharePreferencesValue("loginName", (String) this.c.get(QsbkDatabase.LOGIN));
        SharePreferenceUtils.setSharePreferencesValue("password", (String) this.c.get("passwd"));
    }

    public void handleToken(JSONObject r2_JSONObject) {
        if (r2_JSONObject == null) {
        } else {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(r2_JSONObject);
        }
    }

    protected void onActivityResult(int r4i, int r5i, Intent r6_Intent) {
        super.onActivityResult(r4i, r5i, r6_Intent);
        if (this.m != r4i || r6_Intent == null) {
            if (this.a == null) {
                this.a.authorizeCallBack(r4i, r5i, r6_Intent);
            }
            if (this.b != null) {
                this.b.onActivityResult(r4i, r5i, r6_Intent);
            }
        } else {
            this.l.setText(r6_Intent.getStringExtra(QsbkDatabase.USER_EMAIL));
            this.c.put(QsbkDatabase.USER_EMAIL, r6_Intent.getStringExtra(QsbkDatabase.USER_EMAIL));
            this.x = true;
            if (this.a == null) {
                if (this.b != null) {
                } else {
                    this.b.onActivityResult(r4i, r5i, r6_Intent);
                }
            } else {
                this.a.authorizeCallBack(r4i, r5i, r6_Intent);
                if (this.b != null) {
                    this.b.onActivityResult(r4i, r5i, r6_Intent);
                }
            }
        }
    }

    public void onClick(View r6_View) {
        int r2i = XListViewHeader.STATE_REFRESHING;
        Builder r0_Builder;
        CharSequence[] r1_CharSequenceA;
        switch (r6_View.getId()) {
            case R.id.sinaLayout:
                this.n.trackView("\u7ed1\u5b9a\u5fae\u535a/");
                if (TextUtils.isEmpty(this.j.getText().toString())) {
                    this.t = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
                    this.s = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
                    this.a = new SsoHandler(this, this.s);
                    this.a.authorize(new a());
                } else {
                    r0_Builder = new Builder(this).setTitle("\u64cd\u4f5c");
                    r1_CharSequenceA = new CharSequence[r2i];
                    r1_CharSequenceA[0] = "\u6362\u4e00\u4e2a";
                    r1_CharSequenceA[1] = "\u53d6\u6d88";
                    r0_Builder.setItems(r1_CharSequenceA, new l(this)).show();
                }
                break;
            case R.id.qqLayout:
                this.n.trackView("\u7ed1\u5b9aQQ");
                if (TextUtils.isEmpty(this.k.getText().toString())) {
                    this.t = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
                    IUiListener r0_IUiListener = new c(null);
                    this.b = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, getApplicationContext());
                    this.b.login(this, "get_user_info,get_simple_userinfo", r0_IUiListener);
                } else {
                    r0_Builder = new Builder(this).setTitle("\u64cd\u4f5c");
                    r1_CharSequenceA = new CharSequence[r2i];
                    r1_CharSequenceA[0] = "\u6362\u4e00\u4e2a";
                    r1_CharSequenceA[1] = "\u53d6\u6d88";
                    r0_Builder.setItems(r1_CharSequenceA, new m(this)).show();
                }
                break;
            case R.id.leftBtn:
                finish();
                if (!TextUtils.isEmpty(this.y)) {
                    overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
                }
                break;
            case R.id.mailLayout:
                this.n.trackView("\u7ed1\u5b9a\u90ae\u7bb1/");
                if (TextUtils.isEmpty(this.l.getText().toString())) {
                    startActivityForResult(new Intent(this, EmailBindActivity.class), this.m);
                    overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
                } else {
                    r0_Builder = new Builder(this).setTitle("\u64cd\u4f5c");
                    r1_CharSequenceA = new CharSequence[r2i];
                    r1_CharSequenceA[0] = "\u6362\u4e00\u4e2a";
                    r1_CharSequenceA[1] = "\u53d6\u6d88";
                    r0_Builder.setItems(r1_CharSequenceA, new k(this)).show();
                }
                break;
            case R.id.overRegister:
                if (TextUtils.isEmpty(this.y)) {
                    if (this.x) {
                        e();
                    } else {
                        Animation r0_Animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                        this.o.startAnimation(r0_Animation);
                        this.p.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake2));
                        this.q.startAnimation(r0_Animation);
                    }
                } else {
                    updateUserInfo(this.e.obtainMessage(), this.c);
                }
                break;
        }
    }

    protected void onCreate(Bundle r5_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r5_Bundle);
        setContentView(R.layout.activity_securitybind);
        try {
            this.y = getIntent().getStringExtra(OneProfileActivity.SOURCE);
            if (TextUtils.isEmpty(this.y)) {
                this.u = getIntent().getStringExtra("response");
                if (!TextUtils.isEmpty(this.u)) {
                    JSONObject r0_JSONObject = new JSONObject(this.u);
                    this.c.put(QsbkDatabase.LOGIN, r0_JSONObject.getString(QsbkDatabase.LOGIN));
                    this.c.put("pass", r0_JSONObject.getString("pass"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        c();
        d();
        b();
    }

    public void updateUserInfo(Message r3_Message, Map<String, Object> r4_Map_String__Object) {
        this.i.setText("\u7ed1\u5b9a\u4e2d...");
        new i(this, "bqk-Security2", r4_Map_String__Object, r3_Message).start();
    }
}