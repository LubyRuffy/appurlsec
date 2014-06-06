package qsbk.app.nearby.ui;

import qsbk.app.utils.LogUtil;

// compiled from: InfoCompleteActivity.java
class e implements Runnable {
    final /* synthetic */ InfoCompleteActivity a;

    e(InfoCompleteActivity r1_InfoCompleteActivity) {
        this.a = r1_InfoCompleteActivity;
    }

    public void run() {
        LogUtil.d("scrooll to bottom");
        this.a.v.scrollTo(0, this.a.v.getHeight() + 1000);
    }
}