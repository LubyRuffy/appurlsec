package com.tencent.open;

import android.os.Bundle;
import qsbk.app.thirdparty.net.HttpManager;

// compiled from: ProGuard
class m extends Thread {
    final /* synthetic */ Bundle a;
    final /* synthetic */ OpenConfig b;

    m(OpenConfig r1_OpenConfig, Bundle r2_Bundle) {
        this.b = r1_OpenConfig;
        this.a = r2_Bundle;
    }

    public void run() {
        try {
            this.b.a(Util.d(Util.a(this.b.c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", HttpManager.HTTPMETHOD_GET, this.a).a));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.b.g = 0;
    }
}