package qsbk.app.activity;

import java.util.Iterator;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.loader.OnAsyncLoadListener;

public class MyLikeActivity extends GroupChildBaseListViewActivity {
    protected void b(String r4_String) {
        super.b(r4_String);
        Iterator r0_Iterator = QsbkApp.getMyLikeManager().getLikes().iterator();
        while (r0_Iterator.hasNext()) {
            Object r1_Object = r0_Iterator.next();
            if (!this.r.contains(r1_Object)) {
                this.r.add(r1_Object);
            }
        }
    }

    protected void c() {
        super.c();
        getmListView().loadNoMore();
    }

    protected void d() {
        super.d();
        onInitHistoryData();
    }

    public String getCacheUniqueName() {
        return "mylike";
    }

    public OnAsyncLoadListener getOnAsyncLoadListener(String r2_String) {
        return new bo(this);
    }

    public String getTargetDataUrl(String r2_String) {
        return Constants.LIKE;
    }

    public String getVotePoint() {
        return "mylike/";
    }
}