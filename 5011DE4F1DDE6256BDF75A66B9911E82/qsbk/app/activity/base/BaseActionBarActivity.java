package qsbk.app.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import com.baidu.mobstat.StatService;
import com.flurry.android.FlurryAgent;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import java.lang.reflect.Field;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.share.ShareUtils;

public abstract class BaseActionBarActivity extends ActionBarActivity {
    private Tracker o;
    private Handler p;
    private View q;

    public BaseActionBarActivity() {
        this.p = new Handler(Looper.getMainLooper());
    }

    private void c() {
        try {
            ViewConfiguration r0_ViewConfiguration = ViewConfiguration.get(this);
            Field r1_Field = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (r1_Field != null) {
                r1_Field.setAccessible(true);
                r1_Field.setBoolean(r0_ViewConfiguration, false);
            }
        } catch (Exception e) {
        }
    }

    private void d() {
        EasyTracker.getInstance().setContext(this);
        this.o = EasyTracker.getTracker();
        this.o.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.o);
    }

    protected abstract int e();

    protected abstract void f();

    protected abstract String getCustomTitle();

    protected Tracker k() {
        return this.o;
    }

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        requestWindowFeature(ShareUtils.SHARE_SMS);
        this.q = LayoutInflater.from(this).inflate(e(), null);
        setContentView(this.q);
        c();
        getSupportActionBar().setTitle(getCustomTitle());
        d();
        f();
    }

    protected void onPause() {
        FlurryAgent.onEndSession(this);
        StatService.onPause((Context)this);
        super.onPause();
    }

    protected void onResume() {
        StatService.onResume((Context)this);
        FlurryAgent.onStartSession(this, "LLLGV7Y72RGDIMUHII8Z");
        super.onResume();
    }

    public void setTitle(CharSequence r2_CharSequence) {
        getSupportActionBar().setTitle(r2_CharSequence);
    }
}