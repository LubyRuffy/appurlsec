package qsbk.app.activity;

import com.baidu.mobstat.StatService;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class IndexActivity extends GroupChildBaseListViewActivity {
    private String G;
    private String H;
    private String I;
    private boolean J;
    private String n;

    public IndexActivity() {
        this.n = RContactStorage.PRIMARY_KEY;
        this.G = RContactStorage.PRIMARY_KEY;
        this.H = RContactStorage.PRIMARY_KEY;
        this.I = "\u5e72\u8d27";
    }

    protected boolean b_() {
        return this.J;
    }

    protected void d() {
        this.G = getIntent().getStringExtra("targetDataUrl");
        this.H = getIntent().getStringExtra("cacheUniqueName");
        this.n = this.H + "/";
        this.J = getIntent().getBooleanExtra("shuffle", false);
        this.I = getIntent().getStringExtra(Constants.PARAM_TITLE);
        super.d();
    }

    public String getCacheUniqueName() {
        return this.H;
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            if (this.H.equals("suggest")) {
                this.A.trackView(this.I + "/" + this.t);
            } else if (this.H.equals("latest")) {
                this.A.trackView(this.I + "/" + this.t);
            } else {
                this.A.trackView(this.I + "/" + this.t);
            }
        }
        return this.G;
    }

    public String getVotePoint() {
        return this.n;
    }

    public boolean needActivityStat() {
        return false;
    }

    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, this.I);
    }

    protected void onResume() {
        super.onResume();
        StatService.onPageStart(this, this.I);
    }
}