package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class DayActivity extends GroupChildBaseListViewActivity {
    public String getCacheUniqueName() {
        return "day";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u672c\u65e5\u7cbe\u534e/" + this.t);
        }
        return Constants.DAY;
    }

    public String getVotePoint() {
        return "day/";
    }
}