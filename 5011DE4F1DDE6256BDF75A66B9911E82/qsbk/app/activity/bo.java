package qsbk.app.activity;

import qsbk.app.loader.OnAsyncLoadListener;

// compiled from: MyLikeActivity.java
class bo implements OnAsyncLoadListener {
    final /* synthetic */ MyLikeActivity a;

    bo(MyLikeActivity r1_MyLikeActivity) {
        this.a = r1_MyLikeActivity;
    }

    public void onFinishListener(String r2_String) {
        this.a.c();
    }

    public void onPrepareListener() {
    }

    public String onStartListener() {
        return null;
    }
}