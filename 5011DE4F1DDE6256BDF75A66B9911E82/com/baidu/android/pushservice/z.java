package com.baidu.android.pushservice;

import android.content.Context;
import com.baidu.android.pushservice.apiproxy.PushManager;
import java.util.List;

// compiled from: SourceFile
class z implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ List b;

    z(Context r1_Context, List r2_List) {
        this.a = r1_Context;
        this.b = r2_List;
    }

    public void run() {
        PushManager.delTags(this.a, this.b);
    }
}