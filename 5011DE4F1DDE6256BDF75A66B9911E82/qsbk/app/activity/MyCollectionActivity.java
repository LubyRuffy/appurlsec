package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;
import qsbk.app.utils.SharePreferenceUtils;

public class MyCollectionActivity extends GroupChildBaseListViewActivity {
    protected void b(String r2_String) {
        super.b(r2_String);
        SharePreferenceUtils.setCollectionsByArticle(this.r);
        SharePreferenceUtils.getCollections();
    }

    public String getCacheUniqueName() {
        return "mycollection";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u6211\u6536\u85cf\u7684/" + this.t);
        }
        return Constants.COLLECT_LIST;
    }

    public String getVotePoint() {
        return "mycollection/";
    }
}