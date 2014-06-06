package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class LatestActivity extends GroupChildBaseListViewActivity {
    protected boolean b_() {
        return true;
    }

    public String getCacheUniqueName() {
        return "latest";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u65b0\u9c9c\u51fa\u7089/" + this.t);
        }
        return Constants.LATEST;
    }

    public String getVotePoint() {
        return "latest/";
    }
}