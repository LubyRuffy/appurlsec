package qsbk.app.nearby.ui;

import qsbk.app.nearby.ui.SizeChangeScrollView.OnSizeChangeListener;
import qsbk.app.utils.LogUtil;

// compiled from: InfoCompleteActivity.java
class l implements OnSizeChangeListener {
    final /* synthetic */ InfoCompleteActivity a;

    l(InfoCompleteActivity r1_InfoCompleteActivity) {
        this.a = r1_InfoCompleteActivity;
    }

    public void onSizeChange(int r2i, int r3i, int r4i, int r5i) {
        LogUtil.d("focus status change");
        this.a.scrollViewToBottom();
    }
}