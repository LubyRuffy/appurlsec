package qsbk.app.activity.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.StatFragmentActivity;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewFooter;

public abstract class BaseActivity extends StatFragmentActivity {
    protected ImageButton A;
    protected TextView B;
    protected Tracker C;
    protected String D;
    private LayoutInflater n;
    protected View y;
    protected ImageButton z;

    private void f() {
        EasyTracker.getInstance().setContext(this);
        this.C = EasyTracker.getTracker();
        this.C.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.C);
    }

    protected void c() {
        this.D = getIntent().getStringExtra(OneProfileActivity.SOURCE);
        this.z = (ImageButton) this.y.findViewById(R.id.leftBtn);
        if (TextUtils.isEmpty(this.D)) {
            if (UIHelper.isNightTheme()) {
                this.z.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_back_enable_night));
            } else {
                this.z.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_back_enable));
            }
        } else if (UIHelper.isNightTheme()) {
            this.z.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_close_large_night));
        } else {
            this.z.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_close_large));
        }
        this.A = (ImageButton) this.y.findViewById(R.id.rightBtn);
        this.B = (TextView) this.y.findViewById(R.id.title);
        this.B.setText(getCustomerTitle());
    }

    protected void d() {
        this.z.setOnTouchListener(QsbkApp.TouchDark);
        FrameLayout r0_FrameLayout = (FrameLayout) this.z.getParent();
        r0_FrameLayout.post(new a(this, r0_FrameLayout));
        LinearLayout r0_LinearLayout = (LinearLayout) this.A.getParent();
        r0_LinearLayout.post(new b(this, r0_LinearLayout));
    }

    protected abstract int e();

    protected abstract String getCustomerTitle();

    protected void onCreate(Bundle r4_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r4_Bundle);
        getWindow().setFormat(XListViewFooter.STATE_NODATA);
        f();
        this.n = LayoutInflater.from(this);
        this.y = this.n.inflate(e(), null);
        setContentView(this.y);
        c();
        d();
    }

    public boolean onKeyDown(int r3i, KeyEvent r4_KeyEvent) {
        if (r3i != 4) {
            return super.onKeyDown(r3i, r4_KeyEvent);
        }
        finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
        return true;
    }
}