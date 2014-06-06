package qsbk.app.activity.base;

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
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.bean.Base;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewHeader;

public abstract class LRParentActivity extends SecDefaultActivity implements OnClickListener {
    protected EditText n;
    protected EditText o;
    protected Button p;
    protected JSONObject q;
    protected TextView r;
    private Class<?> s;
    private Handler t;

    public LRParentActivity() {
        this.q = null;
        this.s = null;
        this.t = new ab(this);
    }

    private void h() {
        this.n.setText(getCachePassword());
        this.o.setText(getCacheUserName());
    }

    private HashMap<String, Object> i() {
        HashMap<String, Object> r0_HashMap_String__Object = new HashMap();
        r0_HashMap_String__Object.put(QsbkDatabase.LOGIN, this.o.getText().toString().trim());
        r0_HashMap_String__Object.put("pass", this.n.getText().toString().trim());
        return r0_HashMap_String__Object;
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
        SharePreferenceUtils.setSharePreferencesValue("loginName", this.o.getText().toString().trim());
        SharePreferenceUtils.setSharePreferencesValue("password", this.n.getText().toString().trim());
    }

    private void k() {
        l();
        new ac(this, "qbk-LRParent").start();
    }

    private void l() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), XListViewHeader.STATE_REFRESHING);
        }
    }

    protected void c() {
        super.c();
        if (UIHelper.isNightTheme()) {
            this.P.setBackgroundResource(R.drawable.icon_close_large_night);
        } else {
            this.P.setBackgroundResource(R.drawable.icon_close_large);
        }
        this.Q.setBackgroundResource(R.drawable.head_button);
        this.r = (TextView) findViewById(R.id.to_other);
        this.r.getPaint().setFlags(Base64.DONT_BREAK_LINES);
        this.n = (EditText) findViewById(R.id.passwd);
        this.o = (EditText) findViewById(R.id.userName);
        this.p = (Button) findViewById(R.id.enter);
    }

    protected void d() {
        this.P.setOnClickListener(this);
        this.p.setOnClickListener(this);
        EditText r0_EditText = this.n;
        InputFilter[] r1_InputFilterA = new InputFilter[2];
        r1_InputFilterA[0] = new LengthFilter(30);
        r1_InputFilterA[1] = new aa(this);
        r0_EditText.setFilters(r1_InputFilterA);
    }

    protected abstract String e();

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }

    public String getCachePassword() {
        return RContactStorage.PRIMARY_KEY;
    }

    public String getCacheUserName() {
        return RContactStorage.PRIMARY_KEY;
    }

    public void handleToken(JSONObject r2_JSONObject) {
        if (r2_JSONObject == null) {
        } else {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(r2_JSONObject);
        }
    }

    public void onClick(View r6_View) {
        Animation r0_Animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        switch (r6_View.getId()) {
            case R.id.enter:
                this.S.setVisibility(0);
                if (HttpUtils.isNetworkConnected(this.O)) {
                    if (this.o.getText().toString().trim().equals(RContactStorage.PRIMARY_KEY)) {
                        findViewById(R.id.input_root).setAnimation(r0_Animation);
                        this.S.setVisibility(Base64.DONT_BREAK_LINES);
                    } else if (this.o.getText().toString().trim().contains("\"") || this.o.getText().toString().trim().contains("'")) {
                        Toast.makeText(this, "\u7528\u6237\u540d\u4e0d\u80fd\u5305\u542b\u7279\u6b8a\u5b57\u7b26", 1).show();
                        this.S.setVisibility(Base64.DONT_BREAK_LINES);
                    } else if (this.n.getText().toString().trim().equals(RContactStorage.PRIMARY_KEY)) {
                        findViewById(R.id.input_root).setAnimation(r0_Animation);
                        this.S.setVisibility(Base64.DONT_BREAK_LINES);
                    } else {
                        k();
                    }
                } else {
                    this.S.setVisibility(Base64.DONT_BREAK_LINES);
                }
                break;
            case R.id.leftBtn:
                l();
                finish();
                break;
        }
    }

    protected void onCreate(Bundle r3_Bundle) {
        super.onCreate(r3_Bundle);
        setContentView(R.layout.activity_lr);
        this.s = (Class) getIntent().getSerializableExtra("targetClass");
        c();
        d();
        h();
    }
}