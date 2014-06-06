package qsbk.app.activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import com.baidu.mobstat.StatService;
import com.flurry.android.FlurryAgent;

public class StatFragmentActivity extends FragmentActivity {
    private boolean n;

    public StatFragmentActivity() {
        this.n = false;
    }

    protected void g() {
    }

    public boolean needActivityStat() {
        return true;
    }

    protected void onPause() {
        if (needActivityStat()) {
            FlurryAgent.onEndSession(this);
            StatService.onPause((Context)this);
        }
        super.onPause();
    }

    protected void onResume() {
        if (!this.n) {
            this.n = true;
            g();
        }
        if (needActivityStat()) {
            FlurryAgent.onStartSession(this, "LLLGV7Y72RGDIMUHII8Z");
            StatService.onResume((Context)this);
        }
        super.onResume();
    }
}