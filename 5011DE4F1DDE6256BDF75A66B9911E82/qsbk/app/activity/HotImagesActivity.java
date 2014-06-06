package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class HotImagesActivity extends GroupChildBaseListViewActivity {
    public String getCacheUniqueName() {
        return "imageshot";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u56fe\u7247\u6700\u70ed/" + this.t);
        }
        return Constants.HOT_IMAGES;
    }

    public String getVotePoint() {
        return "imageshot/";
    }
}