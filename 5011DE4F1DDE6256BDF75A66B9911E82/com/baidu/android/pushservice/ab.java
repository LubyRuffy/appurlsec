package com.baidu.android.pushservice;

import android.app.Activity;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ab implements Runnable {
    private final /* synthetic */ Activity a;

    ab(Activity r1_Activity) {
        this.a = r1_Activity;
    }

    public void run() {
        PushManager.activityStarted(this.a);
    }
}