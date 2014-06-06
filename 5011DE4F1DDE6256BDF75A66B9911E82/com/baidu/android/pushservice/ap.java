package com.baidu.android.pushservice;

import android.content.Context;
import android.util.Log;
import com.baidu.android.pushservice.apiproxy.PushManager;

// compiled from: SourceFile
class ap implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    ap(Context r1_Context, String r2_String, String r3_String) {
        this.a = r1_Context;
        this.b = r2_String;
        this.c = r3_String;
    }

    public void run() {
        Log.d("YYY", "AAA start work1111");
        PushManager.startWork(this.a, this.b, this.c);
    }
}