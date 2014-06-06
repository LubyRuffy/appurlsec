package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class MonthActivity extends GroupChildBaseListViewActivity {
    public String getCacheUniqueName() {
        return "month";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u672c\u6708\u7cbe\u534e/" + this.t);
        }
        return Constants.MONTH;
    }

    public String getVotePoint() {
        return "month/";
    }
}