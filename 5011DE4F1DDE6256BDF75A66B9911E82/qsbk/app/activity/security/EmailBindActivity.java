package qsbk.app.activity.security;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.regex.Pattern;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewHeader;

public class EmailBindActivity extends Activity implements OnClickListener {
    protected ImageButton a;
    protected Button b;
    protected ProgressBar c;
    protected TextView d;
    protected Tracker e;
    protected JSONObject f;
    private EditText g;
    private Handler h;

    public EmailBindActivity() {
        this.f = null;
        this.h = new d(this);
    }

    private void b() {
        EasyTracker.getInstance().setContext(this);
        this.e = EasyTracker.getTracker();
        this.e.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.e);
    }

    private void c() {
        this.a = (ImageButton) findViewById(R.id.leftBtn);
        if (UIHelper.isNightTheme()) {
            this.a.setBackgroundResource(R.drawable.icon_close_large_night);
        } else {
            this.a.setBackgroundResource(R.drawable.icon_close_large);
        }
        this.b = (Button) findViewById(R.id.rightBtn);
        this.b.setText("\u5b8c\u6210");
        this.a.setOnTouchListener(QsbkApp.TouchDark);
        this.b.setOnTouchListener(QsbkApp.TouchDark);
        this.c = (ProgressBar) findViewById(R.id.topLoading);
        LinearLayout r0_LinearLayout = (LinearLayout) this.a.getParent();
        r0_LinearLayout.post(new a(this, r0_LinearLayout));
        r0_LinearLayout = (LinearLayout) this.b.getParent();
        r0_LinearLayout.post(new b(this, r0_LinearLayout));
        this.d = (TextView) findViewById(R.id.title);
        this.d.setText("\u7ed1\u5b9a\u90ae\u7bb1");
        this.g = (EditText) findViewById(R.id.email);
    }

    private void d() {
        this.b.setOnClickListener(this);
        this.a.setOnClickListener(new c(this));
    }

    private String e() {
        return "?email=" + this.g.getText().toString().trim();
    }

    protected void a() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), XListViewHeader.STATE_REFRESHING);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }

    public boolean isEmail(String r3_String) {
        return r3_String.trim().equals(RContactStorage.PRIMARY_KEY) ? false : Pattern.compile("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$").matcher(r3_String).matches();
    }

    public void onClick(View r6_View) {
        Animation r0_Animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        switch (r6_View.getId()) {
            case R.id.rightBtn:
                this.c.setVisibility(0);
                this.b.setVisibility(Base64.DONT_BREAK_LINES);
                if (HttpUtils.isNetworkConnected(this)) {
                    String r1_String = this.g.getText().toString().trim();
                    if (r1_String.equals(RContactStorage.PRIMARY_KEY) || (!isEmail(r1_String))) {
                        this.g.startAnimation(r0_Animation);
                        Toast.makeText(this, "\u90ae\u7bb1\u683c\u5f0f\u4e0d\u5408\u6cd5\uff01", 1).show();
                        this.c.setVisibility(Base64.DONT_BREAK_LINES);
                        this.b.setVisibility(0);
                    } else {
                        submit();
                    }
                } else {
                    this.c.setVisibility(Base64.DONT_BREAK_LINES);
                    this.b.setVisibility(0);
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
        setContentView(R.layout.activity_emailbind);
        c();
        d();
        b();
        this.e.trackView("\u7ed1\u5b9a\u90ae\u7bb1/");
    }

    public void submit() {
        a();
        this.d.setText("\u7ed1\u5b9a\u4e2d...");
        new e(this, "qbk-EmailBind").start();
    }
}