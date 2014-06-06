package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class WeekActivity extends GroupChildBaseListViewActivity {
    public String getCacheUniqueName() {
        return "week";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u672c\u5468\u7cbe\u534e/" + this.t);
        }
        return Constants.WEEK;
    }

    public String getVotePoint() {
        return "week/";
    }
}