package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.About;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.adapter.EditUserInfoAdapter.UserInfoItem;
import qsbk.app.api.StartActivityListenerForClick;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.UserInfo;
import qsbk.app.nearby.ui.HttpAsyncTask;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.thirdparty.Oauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyAuthListener;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.thirdparty.ThirdPartyDialogError;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.net.HttpManager;
import qsbk.app.thirdparty.sso.SsoHandler;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastUtil;
import qsbk.app.widget.EditUserInfoItem;
import qsbk.app.widget.listview.XListViewHeader;

public class EditInfoEntranceActivity extends BaseActionBarActivity implements OnClickListener {
    public static final String RESP_KEY_USER_INFO_CHANGED = "user_info_changed";
    private Map<Integer, EditUserInfoItem> A;
    private EditUserInfoItem B;
    private EditUserInfoItem C;
    private EditUserInfoItem D;
    private ThirdParty E;
    private String F;
    private int G;
    private boolean H;
    SsoHandler o;
    Tencent p;
    Handler q;
    private String r;
    private ImageView s;
    private View t;
    private View u;
    private UserHeaderHelper v;
    private LinearLayout w;
    private LinearLayout x;
    private List<UserInfoItem> y;
    private List<UserInfoItem> z;

    public static interface EDIT_TYPE {
        public static final String TYPE_BIRTH = "birth";
        public static final String TYPE_EMAIL = "email";
        public static final String TYPE_GENDER = "gender";
        public static final String TYPE_HOBBY = "hobby";
        public static final String TYPE_INTRODUCE = "introduce";
        public static final String TYPE_LOCATION = "location";
        public static final String TYPE_NAME = "login";
        public static final String TYPE_SIGNATURE = "signature";
        public static final String TYPE_SINA = "sina_weibo";
        public static final String TYPE_TENCENT = "tencent_weibo";
        public static final String TYPE_UNKNOWN = "unknown";
    }

    public class EditItemSubmitter extends HttpAsyncTask {
        public int requestCode;
        public Serializable value;

        public EditItemSubmitter(int r2i, Serializable r3_Serializable) {
            this.requestCode = r2i;
            this.value = r3_Serializable;
        }

        private void c() {
            switch (this.requestCode) {
                case Base64.DONT_BREAK_LINES:
                    QsbkApp.currentUser.userName = this.value.toString();
                    break;
                case qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                    QsbkApp.currentUser.hobby = this.value.toString();
                    break;
                case qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                    QsbkApp.currentUser.introduce = this.value.toString();
                    break;
                case qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                    QsbkApp.currentUser.signature = this.value.toString();
                    break;
                case qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH:
                    QsbkApp.currentUser.birthday = ((Long) this.value).longValue();
                    Calendar r0_Calendar = Calendar.getInstance();
                    r0_Calendar.setTimeInMillis(QsbkApp.currentUser.birthday * 1000);
                    QsbkApp.currentUser.age = AstrologyUtils.getAge(r0_Calendar);
                    break;
                case qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE.REQUEST_CODE_EDIT_GENDER:
                    QsbkApp.currentUser.gender = this.value.toString();
                    break;
                case qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
                    QsbkApp.currentUser.location = this.value.toString();
                    break;
            }
            QsbkApp.getInstance().setCurrentUserToLocal();
            EditInfoEntranceActivity.this.H = true;
        }

        protected void a(Pair<Integer, String> r3_Pair_Integer__String) {
            LogUtil.d("\u4fee\u6539\u4e2a\u4eba\u4fe1\u606f\u6210\u529f!");
            if (((Integer) r3_Pair_Integer__String.first).equals(Integer.valueOf(0))) {
                c();
            } else {
                ToastUtil.Short((String) r3_Pair_Integer__String.second);
            }
        }

        public Map<String, Object> getPostParams() {
            Map<String, Object> r1_Map_String__Object = new HashMap();
            if (this.requestCode == 12) {
                r1_Map_String__Object.put("birthday", Long.valueOf(((Long) this.value).longValue()));
            } else if (this.requestCode == 13) {
                r1_Map_String__Object.put(qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE.TYPE_GENDER, this.value.toString());
            } else if (this.requestCode == 9) {
                r1_Map_String__Object.put(qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE.TYPE_HOBBY, this.value.toString());
            } else if (this.requestCode == 10) {
                r1_Map_String__Object.put(qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE.TYPE_INTRODUCE, this.value.toString());
            } else if (this.requestCode == 14) {
                r1_Map_String__Object.put(NearByListActivity.DIALOG_KEY_REQ_LOCATION, this.value.toString());
            } else if (this.requestCode == 8) {
                r1_Map_String__Object.put(QsbkDatabase.LOGIN, this.value.toString());
            } else if (this.requestCode == 11) {
                r1_Map_String__Object.put(qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE.TYPE_SIGNATURE, this.value.toString());
            }
            return r1_Map_String__Object;
        }

        public String getURL() {
            String r0_String = Constants.UPDATE_USERINFO_1;
            Object[] r1_ObjectA = new Object[1];
            r1_ObjectA[0] = QsbkApp.currentUser.userId;
            return String.format(r0_String, r1_ObjectA);
        }
    }

    public static interface REQUEST_CODE {
        public static final int REQUEST_CODE_EDIT_BIRTH = 12;
        public static final int REQUEST_CODE_EDIT_GENDER = 13;
        public static final int REQUEST_CODE_EDIT_HOBBY = 9;
        public static final int REQUEST_CODE_EDIT_INTRO = 10;
        public static final int REQUEST_CODE_EDIT_LOCATION = 14;
        public static final int REQUEST_CODE_EDIT_NAME = 8;
        public static final int REQUEST_CODE_EDIT_SIGNATURE = 11;
    }

    class a implements ThirdPartyAuthListener {
        a() {
        }

        public void onCancel() {
            Toast.makeText(EditInfoEntranceActivity.this.getApplicationContext(), "\u53d6\u6d88\u8ba4\u8bc1", 1).show();
        }

        public void onComplete(Bundle r5_Bundle) {
            String r0_String = r5_Bundle.getString(ThirdParty.KEY_TOKEN);
            AccessTokenKeeper.keepAccessToken(EditInfoEntranceActivity.this, new Oauth2AccessToken(r0_String, r5_Bundle.getString(ThirdParty.KEY_EXPIRES)));
            Map r1_Map = new HashMap();
            if (EditInfoEntranceActivity.this.F.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                r1_Map.put("wbtoken", r0_String);
            } else {
                r1_Map.put("qqtoken", r0_String);
            }
            EditInfoEntranceActivity.this.a(r1_Map);
        }

        public void onError(ThirdPartyDialogError r1_ThirdPartyDialogError) {
        }

        public void onThirdPartyException(ThirdPartyException r4_ThirdPartyException) {
            Toast.makeText(EditInfoEntranceActivity.this.getApplicationContext(), "\u8ba4\u8bc1\u5f02\u5e38 : " + r4_ThirdPartyException.getMessage(), 1).show();
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
                    EditInfoEntranceActivity.this.runOnUiThread(new al(this));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("toddtest", r3_JSONObject.toString());
            }
        }

        public void onComplete(JSONObject r3_JSONObject, Object r4_Object) {
            try {
                EditInfoEntranceActivity.this.b(r3_JSONObject.get(RContact.COL_NICKNAME).toString());
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
                Map r0_Map = new HashMap();
                r0_Map.put("qqtoken", this.b);
                EditInfoEntranceActivity.this.a(r0_Map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onCancel() {
        }

        public void onComplete(JSONObject r2_JSONObject) {
            EditInfoEntranceActivity.this.h();
            a(r2_JSONObject);
        }

        public void onError(UiError r1_UiError) {
        }
    }

    public EditInfoEntranceActivity() {
        this.A = new HashMap();
        this.G = 20;
        this.H = false;
        this.q = new ak(this);
    }

    private Intent a(Class r4_Class, Serializable r5_Serializable) {
        Intent r0_Intent = new Intent(this, r4_Class);
        r0_Intent.putExtra(REQUEST_KEY.KEY_AUTO_SUBMIT, false);
        if (r5_Serializable != null) {
            r0_Intent.putExtra(REQUEST_KEY.KEY_DEFAULT_VALUE, r5_Serializable);
        }
        return r0_Intent;
    }

    private EditUserInfoItem a(UserInfoItem r3_UserInfoItem, ViewGroup r4_ViewGroup, OnClickListener r5_OnClickListener) {
        EditUserInfoItem r0_EditUserInfoItem = new EditUserInfoItem(r3_UserInfoItem, (Context)this, r4_ViewGroup);
        r0_EditUserInfoItem.setOnEditListener(r5_OnClickListener);
        r4_ViewGroup.addView(r0_EditUserInfoItem.getView());
        return r0_EditUserInfoItem;
    }

    private void a(int r3i, int r4i, Intent r5_Intent) {
        if (this.G != r3i || r5_Intent == null) {
            if (this.o == null) {
                this.o.authorizeCallBack(r3i, r4i, r5_Intent);
            }
            if (this.p != null) {
                this.p.onActivityResult(r3i, r4i, r5_Intent);
            }
        } else {
            Object r0_Object = r5_Intent.getStringExtra(QsbkDatabase.USER_EMAIL);
            this.D.getValueView().setText(r0_Object);
            QsbkApp.currentUser.email = r0_Object;
            SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
            if (this.o == null) {
                if (this.p != null) {
                } else {
                    this.p.onActivityResult(r3i, r4i, r5_Intent);
                }
            } else {
                this.o.authorizeCallBack(r3i, r4i, r5_Intent);
                if (this.p != null) {
                    this.p.onActivityResult(r3i, r4i, r5_Intent);
                }
            }
        }
    }

    private void a(Map<String, Object> r2_Map_String__Object) {
        new y(this, r2_Map_String__Object).run();
    }

    private void a(EditUserInfoItem r5_EditUserInfoItem, Serializable r6_Serializable, int r7i) {
        r5_EditUserInfoItem.getUserInfo().setInnerValue(r6_Serializable);
        String r0_String = r6_Serializable.toString();
        if (r7i == 12) {
            Calendar r2_Calendar = Calendar.getInstance();
            r2_Calendar.setTimeInMillis(((Long) r6_Serializable).longValue() * 1000);
            r0_String = AstrologyUtils.getAge(r2_Calendar) + RContactStorage.PRIMARY_KEY;
        } else if (r7i == 13) {
            r0_String = (String) UserInfo.MAP.get(r6_Serializable.toString());
        }
        r5_EditUserInfoItem.getValueView().setTextColor(getResources().getColor(R.color.g_txt_middle));
        r5_EditUserInfoItem.getValueView().setText(r0_String);
    }

    private void b(String r3_String) {
        TextView r0_TextView = this.C.getValueView();
        r0_TextView.post(new z(this, r0_TextView, r3_String));
    }

    private void g() {
        this.A.put(Integer.valueOf(Base64.DONT_BREAK_LINES), a(new UserInfoItem("\u6635\u79f0", QsbkApp.currentUser.userName, RContactStorage.PRIMARY_KEY, QsbkApp.currentUser.userName, true), this.w, new StartActivityListenerForClick(a(EditNameActivity.class, QsbkApp.currentUser.userName), this, 8)));
        this.w.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.w, false));
        this.A.put(Integer.valueOf(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER), a(new UserInfoItem("\u6027\u522b", (String) UserInfo.MAP.get(QsbkApp.currentUser.gender), RContactStorage.PRIMARY_KEY, QsbkApp.currentUser.gender, true), this.w, new StartActivityListenerForClick(a(EditGenderActivity.class, QsbkApp.currentUser.gender), this, 13)));
        this.w.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.w, false));
        this.A.put(Integer.valueOf(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH), a(new UserInfoItem("\u5e74\u9f84", QsbkApp.currentUser.age > 99 ? "99" : QsbkApp.currentUser.age + RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, Long.valueOf(QsbkApp.currentUser.birthday), true), this.w, new StartActivityListenerForClick(a(EditBirthActivity.class, Long.valueOf(QsbkApp.currentUser.birthday)), this, 12)));
        this.w.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.w, false));
        this.A.put(Integer.valueOf(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE), a(new UserInfoItem("\u4e2a\u6027\u7b7e\u540d", QsbkApp.currentUser.signature, RContactStorage.PRIMARY_KEY, QsbkApp.currentUser.signature, true), this.w, new StartActivityListenerForClick(a(EditSignatureActivity.class, QsbkApp.currentUser.signature), this, 11)));
        this.w.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.w, false));
        this.A.put(Integer.valueOf(REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION), a(new UserInfoItem("\u5e38\u51fa\u6ca1\u5730", QsbkApp.currentUser.location, RContactStorage.PRIMARY_KEY, QsbkApp.currentUser.location, true), this.w, new StartActivityListenerForClick(a(EditLocationActivity.class, QsbkApp.currentUser.location), this, 14)));
        this.w.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.w, false));
        this.A.put(Integer.valueOf(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY), a(new UserInfoItem("\u5174\u8da3\u7231\u597d", QsbkApp.currentUser.hobby, RContactStorage.PRIMARY_KEY, QsbkApp.currentUser.hobby, true), this.w, new StartActivityListenerForClick(a(EditHobbyActivity.class, QsbkApp.currentUser.hobby), this, 9)));
        this.w.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.w, false));
        this.A.put(Integer.valueOf(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO), a(new UserInfoItem("\u4e2a\u4eba\u8bf4\u660e", QsbkApp.currentUser.introduce, RContactStorage.PRIMARY_KEY, QsbkApp.currentUser.introduce, true), this.w, new StartActivityListenerForClick(a(EditIntrActivity.class, (Serializable)"\u6ca1\u4ec0\u4e48\u597d\u8bf4\u7684"), this, 10)));
    }

    private void h() {
        this.p.requestAsync(com.tencent.tauth.Constants.GRAPH_SIMPLE_USER_INFO, null, HttpManager.HTTPMETHOD_GET, new b("get_simple_userinfo", false), null);
    }

    private void i() {
        this.B = a(TextUtils.isEmpty(QsbkApp.currentUser.wb.trim()) ? new UserInfoItem("\u65b0\u6d6a\u5fae\u535a", null, "\u672a\u7ed1\u5b9a", RContactStorage.PRIMARY_KEY, false) : new UserInfoItem("\u65b0\u6d6a\u5fae\u535a", QsbkApp.currentUser.wb, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, true), this.x, new aa(this));
        this.x.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.x, false));
        LogUtil.d("qq:" + QsbkApp.currentUser.qq);
        this.C = a(TextUtils.isEmpty(QsbkApp.currentUser.qq.trim()) ? new UserInfoItem("\u817e\u8baf\u5fae\u535a", null, "\u672a\u7ed1\u5b9a", RContactStorage.PRIMARY_KEY, false) : new UserInfoItem("\u817e\u8baf\u5fae\u535a", QsbkApp.currentUser.qq, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, true), this.x, new ac(this));
        this.x.addView(LayoutInflater.from(this).inflate(R.layout.divider_horizontal_small, this.x, false));
        this.D = a(TextUtils.isEmpty(QsbkApp.currentUser.email.trim()) ? new UserInfoItem("\u90ae\u7bb1", null, "\u672a\u7ed1\u5b9a", RContactStorage.PRIMARY_KEY, false) : new UserInfoItem("\u90ae\u7bb1", QsbkApp.currentUser.email, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, true), this.x, new ae(this));
    }

    private void j() {
        if (QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.userIcon) || "null".equals(QsbkApp.currentUser.userIcon.toString())) {
            if (QsbkApp.currentUser == null) {
                i();
            }
        } else {
            QsbkApp.getInstance().getAvatarWorker(this).loadImage(UserHeaderHelper.getIconUrl(QsbkApp.currentUser), this.s);
            if (QsbkApp.currentUser == null) {
            } else {
                i();
            }
        }
    }

    protected void c() {
        if (QsbkApp.currentUser == null) {
            finish();
        }
        this.s = (ImageView) findViewById(R.id.userIcon);
        this.t = findViewById(R.id.take_picture_now);
        this.u = findViewById(R.id.select_from_albumn);
        this.w = (LinearLayout) findViewById(R.id.set_base_info);
        this.x = (LinearLayout) findViewById(R.id.set_security);
    }

    protected void d() {
        this.s.setOnClickListener(new ag(this));
        this.u.setOnClickListener(this);
        this.t.setOnClickListener(this);
    }

    protected int e() {
        return R.layout.activity_edit_info_entrance;
    }

    protected void f() {
        this.v = new UserHeaderHelper(this);
        k().trackView("\u4e2a\u4eba\u4e2d\u5fc3/");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.r = getIntent().getStringExtra(OneProfileActivity.SOURCE);
        this.y = new ArrayList();
        this.z = new ArrayList();
        getSupportActionBar().setHomeButtonEnabled(true);
        c();
        d();
        g();
        j();
    }

    public void finish() {
        if (!TextUtils.isEmpty(this.r)) {
            Intent r0_Intent = new Intent();
            r0_Intent.putExtra(RESP_KEY_USER_INFO_CHANGED, this.H);
            setResult(-1, r0_Intent);
        }
        super.finish();
    }

    public String getCustomTitle() {
        return "\u4e2a\u4eba\u8d44\u6599";
    }

    public HashMap<String, Object> getPostParams() {
        return new HashMap();
    }

    protected void onActivityResult(int r3i, int r4i, Intent r5_Intent) {
        super.onActivityResult(r3i, r4i, r5_Intent);
        a(r3i, r4i, r5_Intent);
        if (r4i != -1) {
        } else {
            switch (r3i) {
                case XListViewHeader.STATE_NORMAL:
                    this.v.doCropPhotoWithCaptured();
                case XListViewHeader.STATE_READY:
                    String r0_String = this.v.savePickedBitmap(r5_Intent);
                    if (!TextUtils.isEmpty(r0_String)) {
                        submitAvatar(r0_String);
                    }
            }
            EditUserInfoItem r0_EditUserInfoItem = (EditUserInfoItem) this.A.get(Integer.valueOf(r3i));
            if (r0_EditUserInfoItem != null) {
                Serializable r1_Serializable = r5_Intent.getSerializableExtra(REQUEST_KEY.KEY_RETURN_VALUE);
                if (r1_Serializable != null) {
                    a(r0_EditUserInfoItem, r1_Serializable, r3i);
                    new EditItemSubmitter(r3i, r1_Serializable).run();
                }
            }
        }
    }

    public void onBackPressed() {
        finish();
    }

    public void onClick(View r2_View) {
        switch (r2_View.getId()) {
            case R.id.select_from_albumn:
                this.v.getPicFromContent();
                break;
            case R.id.take_picture_now:
                this.v.getPicFromCapture();
                break;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        if (QsbkApp.currentUser == null) {
            QsbkApp.currentUser = new UserInfo(SharePreferenceUtils.getSharePreferencesValue("loginUser"));
        }
        super.onCreate(r3_Bundle);
    }

    public boolean onCreateOptionsMenu(Menu r3_Menu) {
        getMenuInflater().inflate(R.menu.main, r3_Menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem r4_MenuItem) {
        switch (r4_MenuItem.getItemId()) {
            case 16908332:
                finish();
                break;
            case R.id.action_feedback:
                startActivity(new Intent(this, About.class));
                break;
            case R.id.action_about:
                Intent r0_Intent = new Intent(this, About.class);
                r0_Intent.putExtra("targetPage", "about");
                startActivity(r0_Intent);
                break;
        }
        return super.onOptionsItemSelected(r4_MenuItem);
    }

    protected void onResume() {
        super.onResume();
    }

    public void submitAvatar(String r4_String) {
        int r1i = 1;
        if (HttpUtils.isNetworkConnected(this)) {
            Toast.makeText(this, "\u6b63\u5728\u4e0a\u4f20\u5934\u50cf...", r1i).show();
            new aj(this, r4_String).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        } else {
            Toast.makeText(this, R.string.network_not_connected, 1).show();
        }
    }
}