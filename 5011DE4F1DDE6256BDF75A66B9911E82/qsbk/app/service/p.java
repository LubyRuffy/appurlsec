package qsbk.app.service;

import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;

// compiled from: VersionService.java
class p extends Handler {
    final /* synthetic */ VersionService a;

    p(VersionService r1_VersionService) {
        this.a = r1_VersionService;
    }

    public void handleMessage(Message r3_Message) {
        if (QsbkApp.loading_process > 99) {
            this.a.a.cancel(0);
            this.a.stopSelf();
        } else {
            if (QsbkApp.loading_process > this.a.b) {
                this.a.a(QsbkApp.loading_process);
            }
            new q(this, "qbk-VersionSrv").start();
            this.a.b = QsbkApp.loading_process;
        }
    }
}