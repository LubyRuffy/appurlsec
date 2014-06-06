package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class SuggestActivity extends GroupChildBaseListViewActivity {
    public String getCacheUniqueName() {
        return "suggest";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u968f\u4fbf\u901b\u901b/" + this.t);
        }
        return Constants.SUGGEST;
    }

    public String getVotePoint() {
        return "suggest/";
    }
}