package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.Internal;

// compiled from: SourceFile
class n implements Runnable {
    private final /* synthetic */ Context a;

    n(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        Internal.enablePushConnection(this.a);
    }
}