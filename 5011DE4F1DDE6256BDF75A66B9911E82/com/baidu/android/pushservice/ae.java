package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ae implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ int c;
    private final /* synthetic */ int d;

    ae(Context r1_Context, String r2_String, int r3i, int r4i) {
        this.a = r1_Context;
        this.b = r2_String;
        this.c = r3i;
        this.d = r4i;
    }

    public void run() {
        PushManager.fetchGroupMessages(this.a, this.b, this.c, this.d);
    }
}