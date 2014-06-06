package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class aq implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ int b;
    private final /* synthetic */ String c;

    aq(Context r1_Context, int r2i, String r3_String) {
        this.a = r1_Context;
        this.b = r2i;
        this.c = r3_String;
    }

    public void run() {
        PushManager.startWork(this.a, this.b, this.c);
    }
}