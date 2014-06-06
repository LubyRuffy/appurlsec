package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;

public class ImagesActivity extends GroupChildBaseListViewActivity {
    public String getCacheUniqueName() {
        return "images";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u56fe\u7247\u6700\u65b0/" + this.t);
        }
        return Constants.IMAGES;
    }

    public String getVotePoint() {
        return "images/";
    }
}