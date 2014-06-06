package qsbk.app.activity.base;

import android.view.ViewGroup;
import qsbk.app.utils.Base64;

// compiled from: GroupChildBaseListViewActivity.java
class u implements Runnable {
    final /* synthetic */ GroupChildBaseListViewActivity a;

    u(GroupChildBaseListViewActivity r1_GroupChildBaseListViewActivity) {
        this.a = r1_GroupChildBaseListViewActivity;
    }

    public void run() {
        this.a.B.setVisibility(Base64.DONT_BREAK_LINES);
        if (this.a.B.getParent() != null) {
            ((ViewGroup) this.a.B.getParent()).removeView(this.a.B);
        }
    }
}