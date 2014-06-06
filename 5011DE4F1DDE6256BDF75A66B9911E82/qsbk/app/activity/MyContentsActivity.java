package qsbk.app.activity;

import android.os.Handler;
import qsbk.app.Constants;
import qsbk.app.activity.base.GroupChildBaseListViewActivity;
import qsbk.app.activity.base.MysBaseActivity;
import qsbk.app.adapter.DefaultAdapter;
import qsbk.app.adapter.MyContentsAdapter;

public class MyContentsActivity extends GroupChildBaseListViewActivity {
    Handler n;

    public MyContentsActivity() {
        this.n = new bi(this);
    }

    private void k() {
        new bj(this, "qbk-MyCtnAct").start();
    }

    protected void f() {
        this.s.setOnItemLongClickListener(new bk(this));
    }

    public String getCacheUniqueName() {
        return "myContent";
    }

    public String getTargetDataUrl(String r4_String) {
        if (MysBaseActivity.LOAD.equals(r4_String)) {
            this.A.trackView("\u6211\u7684\u7cd7\u4e8b/" + this.t);
        }
        return Constants.MYCONTENTS;
    }

    public String getVotePoint() {
        return "mycontent/";
    }

    public DefaultAdapter getmAdapter() {
        return new MyContentsAdapter(this, this.s, this.r);
    }
}