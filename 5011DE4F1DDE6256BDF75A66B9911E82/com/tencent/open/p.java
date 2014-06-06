package com.tencent.open;

import android.content.Context;
import android.os.Bundle;
import qsbk.app.thirdparty.net.HttpManager;

// compiled from: ProGuard
final class p extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ Bundle b;

    p(Context r1_Context, Bundle r2_Bundle) {
        this.a = r1_Context;
        this.b = r2_Bundle;
    }

    public void run() {
        try {
            Util.a(this.a, "http://cgi.qplus.com/report/report", HttpManager.HTTPMETHOD_GET, this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}