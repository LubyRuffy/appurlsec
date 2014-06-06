package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;
import java.util.List;

// compiled from: SourceFile
class x implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ List b;

    x(Context r1_Context, List r2_List) {
        this.a = r1_Context;
        this.b = r2_List;
    }

    public void run() {
        PushManager.setTags(this.a, this.b);
    }
}