package qsbk.app.activity.base;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.baidu.mobstat.StatService;
import com.qiubai.library.adview.util.AdViewUtil;

// compiled from: GroupChildBaseListViewActivity.java
class t implements OnClickListener {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    t(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public void onClick(View r4_View) {
        if (r4_View.getParent() != null) {
            ((ViewGroup) r4_View.getParent()).removeView(r4_View);
        }
        StatService.onEvent(this.a, "tab_refresh", AdViewUtil.COUNTCLICK);
        this.a.s.setSelection(0);
        this.a.s.initData();
    }
}