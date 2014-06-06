package qsbk.app.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.qiubai.library.adview.AdViewInterface;
import com.qiubai.library.adview.AdViewLayout;
import com.qiubai.library.adview.AdViewTargeting;
import com.qiubai.library.adview.AdViewTargeting.SwitcherMode;
import com.qq.e.comm.DownloadService;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.StatFragmentActivity;
import qsbk.app.nearby.ui.AlertDialog.Builder;
import qsbk.app.utils.Base64;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.listview.XListViewFooter;

public abstract class SecDefaultActivity extends StatFragmentActivity implements AdViewInterface {
    protected Context O;
    protected ImageButton P;
    protected ImageButton Q;
    protected TextView R;
    protected ProgressBar S;
    protected Tracker T;
    protected AdViewLayout U;
    private SwitcherMode n;

    public SecDefaultActivity() {
        this.O = this;
        this.n = SwitcherMode.DEFAULT;
        this.U = null;
    }

    private void d() {
        EasyTracker.getInstance().setContext(this);
        this.T = EasyTracker.getTracker();
        this.T.setAppVersion(Constants.localVersionName);
        QsbkApp.getInstance().setSampleRate(this.T);
    }

    protected void c() {
        this.P = (ImageButton) findViewById(R.id.leftBtn);
        this.Q = (ImageButton) findViewById(R.id.rightBtn);
        this.P.setOnTouchListener(QsbkApp.TouchDark);
        this.Q.setOnTouchListener(QsbkApp.TouchDark);
        findViewById(R.id.message_tip).setVisibility(Base64.DONT_BREAK_LINES);
        FrameLayout r0_FrameLayout = (FrameLayout) this.P.getParent();
        r0_FrameLayout.post(new an(this, r0_FrameLayout));
        LinearLayout r0_LinearLayout = (LinearLayout) this.Q.getParent();
        r0_LinearLayout.post(new ao(this, r0_LinearLayout));
        this.S = (ProgressBar) findViewById(R.id.topLoading);
        this.R = (TextView) findViewById(R.id.title);
        this.R.setText(getCustomerTitle());
    }

    public void cleanAdViewLayout() {
        this.U = null;
    }

    public void createCloseDialog(Context r4_Context, AdViewLayout r5_AdViewLayout) {
        new Builder(r4_Context).setTitle("\u63d0\u793a").setMessage((CharSequence)"\u786e\u8ba4\u8981\u5173\u95ed\u5e7f\u544a\u5417?").setNegativeButton((CharSequence)"\u53d6\u6d88", new aq(this, r5_AdViewLayout)).setPositiveButton((CharSequence)"\u786e\u5b9a", new ap(this, r5_AdViewLayout)).show().setCanceledOnTouchOutside(false);
    }

    protected void f() {
        RelativeLayout r0_RelativeLayout = (RelativeLayout) findViewById(R.id.adLayout);
        String r1_String = HttpUtils.getNetworkType(QsbkApp.mContext);
        if (r0_RelativeLayout != null) {
            String r2_String = SharePreferenceUtils.getSharePreferencesValue("adbanner-close");
            if (!TextUtils.isEmpty(r2_String)) {
                if (r2_String.equals("0")) {
                    this.n = SwitcherMode.DEFAULT;
                } else if ((!r2_String.equals("1")) || r1_String.toUpperCase().equals("WIFI")) {
                    if (r2_String.equals(DownloadService.V2)) {
                        this.n = SwitcherMode.CANCLOSED;
                    }
                } else {
                    this.n = SwitcherMode.CANCLOSED;
                }
            }
            this.U = new AdViewLayout(this, Constants.AD_KEY);
            this.U.setAdViewInterface(this);
            AdViewTargeting.setSwitcherMode(this.n);
            r0_RelativeLayout.addView(this.U);
            r0_RelativeLayout.invalidate();
        }
    }

    public abstract String getCustomerTitle();

    public void onClickAd() {
    }

    public void onClosedAd() {
        this.U.setClosed(true);
        cleanAdViewLayout();
    }

    protected void onCreate(Bundle r3_Bundle) {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        super.onCreate(r3_Bundle);
        getWindow().setFormat(XListViewFooter.STATE_NODATA);
        d();
    }

    public void onDisplayAd() {
    }

    protected void onStop() {
        super.onStop();
    }
}