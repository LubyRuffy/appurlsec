package qsbk.app.activity.base;

import android.widget.Toast;
import com.baidu.kirin.KirinConfig;
import com.baidu.location.LocationClientOption;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.utils.HttpUtils;

// compiled from: GroupChildBaseListViewActivity.java
class x implements Runnable {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    x(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public void run() {
        if (!this.a.n) {
            this.a.m();
        }
        if (HttpUtils.netIsAvailable()) {
            this.a.x = true;
            this.a.I = true;
            this.a.t = 1;
            if (this.a.lastRefreshFirstPageTime == null) {
                new AsyncDataLoader(this.a.getOnAsyncLoadListener(MysBaseActivity.LOAD), "qsbk-AT-BGA-01").execute(new Void[0]);
            } else if (Long.valueOf(System.currentTimeMillis()).longValue() - this.a.lastRefreshFirstPageTime.longValue() > 60000) {
                new AsyncDataLoader(this.a.getOnAsyncLoadListener(MysBaseActivity.LOAD), "qsbk-AT-BGA-02").execute(new Void[0]);
            } else {
                this.a.a((int)KirinConfig.READ_TIME_OUT);
            }
        } else {
            this.a.a((int)LocationClientOption.MIN_SCAN_SPAN);
            Toast.makeText(QsbkApp.mContext, R.string.network_not_connected, 1).show();
        }
    }
}