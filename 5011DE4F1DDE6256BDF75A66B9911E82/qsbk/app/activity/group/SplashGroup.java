package qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.util.Calendar;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.gdtad.GdtAd;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

public class SplashGroup extends FragmentActivity {
    private IWXAPI n;


    private class a implements Runnable {
        Intent a;

        private a() {
            this.a = null;
        }

        protected void finalize() throws Throwable {
            this.a = null;
            super.finalize();
        }

        public void run() {
            this.a = new Intent(SplashGroup.this, TopActivityGroup.class);
            SplashGroup.this.startActivity(this.a);
            SplashGroup.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            SplashGroup.this.finish();
        }
    }

    private boolean c() {
        Calendar r2_Calendar = Calendar.getInstance();
        r2_Calendar.set(2014, ShareUtils.SHARE_SMS, 1);
        String r3_String = QsbkApp.getInstance().getBaiduChannel();
        if (System.currentTimeMillis() >= r2_Calendar.getTimeInMillis()) {
            return false;
        }
        if (r3_String.equals("7")) {
            return true;
        }
        return false;
    }

    private void d() {
        if (QsbkApp.getInstance().getBaiduChannel().equals("7")) {
            ((ImageView) findViewById(R.id.marketLogo)).setImageResource(R.drawable.zhushoulogo);
        }
    }

    private void e() {
        if (c()) {
            new Handler().postDelayed(new a(null), 1500);
        } else {
            startActivity(new Intent(this, TopActivityGroup.class));
            finish();
        }
    }

    protected void onCreate(Bundle r4_Bundle) {
        super.onCreate(r4_Bundle);
        if (c()) {
            setContentView(R.layout.layout_splash);
            d();
        } else {
            setContentView(R.layout.layout_two_tab_topbar);
        }
        startService(new Intent("qsbk.app.GCService"));
        this.n = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        this.n.registerApp(Constants.APP_ID);
        DeviceUtils.addShortcut(this);
        e();
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        QsbkApp.getInstance().getImageWorker(this);
        QsbkApp.getInstance().getAvatarWorker(this);
        GdtAd.getInstance().initFeedAd(this);
        SharePreferenceUtils.setSharePreferencesValue("appStartTime", String.valueOf(System.currentTimeMillis()));
    }
}