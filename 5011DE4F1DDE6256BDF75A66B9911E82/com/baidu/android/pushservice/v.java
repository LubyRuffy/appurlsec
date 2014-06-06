package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class v implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;

    v(Context r1_Context, String r2_String, String r3_String, String r4_String, String r5_String) {
        this.a = r1_Context;
        this.b = r2_String;
        this.c = r3_String;
        this.d = r4_String;
        this.e = r5_String;
    }

    public void run() {
        PushManager.sendMsgToUser(this.a, this.b, this.c, this.d, this.e);
    }
}