package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import qsbk.app.utils.LogUtil;

// compiled from: InfoCompleteActivity.java
class k implements OnFocusChangeListener {
    final /* synthetic */ InfoCompleteActivity a;

    k(InfoCompleteActivity r1_InfoCompleteActivity) {
        this.a = r1_InfoCompleteActivity;
    }

    public void onFocusChange(View r2_View, boolean r3z) {
        if (r3z) {
            LogUtil.d("focus status change");
            this.a.scrollViewToBottom();
        }
    }
}