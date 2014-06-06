package qsbk.app.service;

import android.os.Handler;
import android.os.Message;

// compiled from: VersionCheckService.java
class i extends Handler {
    final /* synthetic */ VersionCheckService a;

    i(VersionCheckService r1_VersionCheckService) {
        this.a = r1_VersionCheckService;
    }

    public void handleMessage(Message r2_Message) {
        this.a.c();
    }
}