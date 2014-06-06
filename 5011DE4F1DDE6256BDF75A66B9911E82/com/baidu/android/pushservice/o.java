package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.Internal;

// compiled from: SourceFile
class o implements Runnable {
    private final /* synthetic */ Context a;

    o(Context r1_Context) {
        this.a = r1_Context;
    }

    public void run() {
        Internal.createBdussIntent(this.a);
    }
}