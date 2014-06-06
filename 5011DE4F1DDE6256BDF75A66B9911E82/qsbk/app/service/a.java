package qsbk.app.service;

import android.os.Handler;
import android.os.Message;

// compiled from: ConfigService.java
class a extends Handler {
    final /* synthetic */ ConfigService a;

    a(ConfigService r1_ConfigService) {
        this.a = r1_ConfigService;
    }

    public void handleMessage(Message r2_Message) {
        this.a.stopSelf();
    }
}