package com.baidu.android.pushservice;

import android.content.Context;
import android.util.Log;

// compiled from: SourceFile
class p extends Thread {
    private final /* synthetic */ Context a;
    private final /* synthetic */ Runnable b;

    p(Context r1_Context, Runnable r2_Runnable) {
        this.a = r1_Context;
        this.b = r2_Runnable;
    }

    public void run() {
        Log.d("YYY", "excuteMethod 333");
        if (LoadExecutor.loadPush(this.a)) {
            Log.d("YYY", "excuteMethod 555");
            this.b.run();
        } else {
            Log.d("YYY", "excuteMethod 444");
        }
    }
}