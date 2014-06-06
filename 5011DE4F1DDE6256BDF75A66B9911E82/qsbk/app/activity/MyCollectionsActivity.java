package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.activity.base.MysBaseActivity;
import qsbk.app.utils.SharePreferenceUtils;

public class MyCollectionsActivity extends MysBaseActivity {
    protected void b(String r2_String) {
        super.b(r2_String);
        SharePreferenceUtils.setCollectionsByArticle(this.q);
        SharePreferenceUtils.getCollections();
    }

    protected boolean c() {
        return false;
    }

    public String getCacheUniqueName() {
        return "mycollection";
    }

    protected String getCustomTitle() {
        return "\u6211\u6536\u85cf\u7684";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            k().trackView("\u6211\u6536\u85cf\u7684/" + this.t);
        }
        return Constants.COLLECT_LIST;
    }

    public String getVotePoint() {
        return "mycollection/";
    }
}