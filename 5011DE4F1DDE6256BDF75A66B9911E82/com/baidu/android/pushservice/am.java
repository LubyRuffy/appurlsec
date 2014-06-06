package com.baidu.android.pushservice;

import android.app.Activity;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class am implements Runnable {
    private final /* synthetic */ Activity a;

    am(Activity r1_Activity) {
        this.a = r1_Activity;
    }

    public void run() {
        PushManager.activityStoped(this.a);
    }
}