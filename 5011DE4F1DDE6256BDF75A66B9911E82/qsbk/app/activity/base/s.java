package qsbk.app.activity.base;

import com.baidu.mobstat.StatService;
import com.qiubai.library.adview.util.AdViewUtil;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.widget.slidingmenu.ui.MenuHorizontalScrollView;

// compiled from: GroupChildBaseListViewActivity.java
class s implements Runnable {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    s(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public void run() {
        if ((!ListViewHelper.isOutSideInterval(this.a, this.a.getCacheUniqueName(), -1)) || MenuHorizontalScrollView.menuOut || (!this.a.n) || this.a.x) {
        } else {
            StatService.onEvent(this.a, "tab_refresh", AdViewUtil.COUNTSHOW);
            this.a.i();
        }
    }
}