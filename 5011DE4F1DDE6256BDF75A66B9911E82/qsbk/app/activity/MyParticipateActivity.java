package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class MyParticipateActivity extends GroupChildBaseListViewActivity {
    public String getCacheUniqueName() {
        return "myparticipate";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u6211\u53c2\u4e0e\u7684/" + this.t);
        }
        return Constants.PARTICIPATE;
    }

    public String getVotePoint() {
        return "myparticipate/";
    }
}