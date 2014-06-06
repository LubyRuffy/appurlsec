package qsbk.app.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.StatFragmentActivity;
import qsbk.app.bean.Base;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public abstract class LoginRegisterBaseActivity extends StatFragmentActivity implements OnClickListener {
    protected Context p;
    protected ImageButton q;
    protected Button r;
    protected TextView s;
    protected ProgressBar t;
    protected Tracker u;
    protected EditText v;
    protected EditText w;
    protected JSONObject x;
    protected Class<?> y;

    public LoginRegisterBaseActivity() {
        this.p = this;
        this.x = null;
        this.y = null;
    }

    private void i() {
        EasyTracker.getInstance().setContext(this);
        this.u = EasyTracker.getTracker();
        this.u.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.u);
    }

    public static boolean isCN(String r3_String) {
        try {
            return r3_String.getBytes(Base.UTF8).length != r3_String.length();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void j() {
        this.v.setText(getCachePassword());
        this.w.setText(getCacheUserName());
    }

    protected void c() {
        this.q = (ImageButton) findViewById(R.id.leftBtn);
        if (UIHelper.isNightTheme()) {
            this.q.setBackgroundResource(R.drawable.icon_close_large_night);
        } else {
            this.q.setBackgroundResource(R.drawable.icon_close_large);
        }
        this.r = (Button) findViewById(R.id.rightBtn);
        this.q.setOnTouchListener(QsbkApp.TouchDark);
        LinearLayout r0_LinearLayout = (LinearLayout) this.q.getParent();
        r0_LinearLayout.post(new ad(this, r0_LinearLayout));
        r0_LinearLayout = (LinearLayout) this.r.getParent();
        r0_LinearLayout.post(new ae(this, r0_LinearLayout));
        this.t = (ProgressBar) findViewById(R.id.topLoading);
        this.s = (TextView) findViewById(R.id.title);
        this.s.setText(getCustomerTitle());
        this.v = (EditText) findViewById(R.id.passwd);
        this.w = (EditText) findViewById(R.id.userName);
    }

    protected abstract String d();

    protected HashMap<String, Object> e() {
        HashMap<String, Object> r0_HashMap_String__Object = new HashMap();
        r0_HashMap_String__Object.put(QsbkDatabase.LOGIN, this.w.getText().toString().trim());
        r0_HashMap_String__Object.put("pass", this.v.getText().toString().trim());
        return r0_HashMap_String__Object;
    }

    protected void f() {
        SharePreferenceUtils.setSharePreferencesValue("loginName", this.w.getText().toString().trim());
        SharePreferenceUtils.setSharePreferencesValue("password", this.v.getText().toString().trim());
    }

    public String getCachePassword() {
        return RContactStorage.PRIMARY_KEY;
    }

    public String getCacheUserName() {
        return RContactStorage.PRIMARY_KEY;
    }

    public abstract String getCustomerTitle();

    public abstract Handler getHanler();

    public abstract int getLayoutId();

    protected void h() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), XListViewHeader.STATE_REFRESHING);
        }
    }

    public void handleToken(JSONObject r2_JSONObject) {
        if (r2_JSONObject == null) {
        } else {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(r2_JSONObject);
        }
    }

    protected void initListener() {
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        EditText r0_EditText = this.v;
        InputFilter[] r1_InputFilterA = new InputFilter[2];
        r1_InputFilterA[0] = new LengthFilter(30);
        r1_InputFilterA[1] = new af(this);
        r0_EditText.setFilters(r1_InputFilterA);
    }

    public void onClick(View r8_View) {
        Animation r0_Animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        switch (r8_View.getId()) {
            case R.id.leftBtn:
                h();
                finish();
                overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
                break;
            case R.id.rightBtn:
                this.t.setVisibility(0);
                this.r.setVisibility(Base64.DONT_BREAK_LINES);
                if (HttpUtils.isNetworkConnected(this.p)) {
                    if (this.w.getText().toString().trim().equals(RContactStorage.PRIMARY_KEY)) {
                        findViewById(R.id.input_root).setAnimation(r0_Animation);
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                        this.r.setVisibility(0);
                    } else if (this.w.getText().toString().trim().contains("\"") || this.w.getText().toString().trim().contains("'")) {
                        Toast.makeText(this, "\u7528\u6237\u540d\u4e0d\u80fd\u5305\u542b\u7279\u6b8a\u5b57\u7b26", 1).show();
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                        this.r.setVisibility(0);
                    } else if (this.v.getText().toString().trim().equals(RContactStorage.PRIMARY_KEY)) {
                        findViewById(R.id.input_root).setAnimation(r0_Animation);
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                        this.r.setVisibility(0);
                    } else {
                        submit();
                    }
                } else {
                    this.t.setVisibility(Base64.DONT_BREAK_LINES);
                    this.r.setVisibility(0);
                }
                break;
            case R.id.next:
                this.t.setVisibility(0);
                if (HttpUtils.isNetworkConnected(this.p)) {
                    if (this.w.getText().toString().trim().equals(RContactStorage.PRIMARY_KEY)) {
                        findViewById(R.id.input_root).setAnimation(r0_Animation);
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                    } else if (this.w.getText().toString().trim().contains("\"") || this.w.getText().toString().trim().contains("'")) {
                        Toast.makeText(this, "\u7528\u6237\u540d\u4e0d\u5408\u6cd5", 1).show();
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                    } else if (this.v.getText().toString().trim().equals(RContactStorage.PRIMARY_KEY)) {
                        findViewById(R.id.input_root).setAnimation(r0_Animation);
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                    } else if (this.v.getText().toString().trim().length() < 6) {
                        Toast.makeText(this, "\u5bc6\u7801\u957f\u5ea6\u6700\u5c116\u4f4d", 1).show();
                        this.t.setVisibility(Base64.DONT_BREAK_LINES);
                    } else {
                        submit();
                    }
                } else {
                    this.t.setVisibility(Base64.DONT_BREAK_LINES);
                }
                break;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r3_Bundle);
        setContentView(getLayoutId());
        this.y = (Class) getIntent().getSerializableExtra("targetClass");
        getWindow().setFormat(XListViewFooter.STATE_NODATA);
        i();
        c();
        initListener();
        j();
    }

    public void submit() {
        h();
        new ag(this, "qbk-Register").start();
    }
}