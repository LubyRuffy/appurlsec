package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.Internal;

// compiled from: SourceFile
class m implements Runnable {
    private final /* synthetic */ Context a;

    m(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        Internal.disablePushConnection(this.a);
    }
}