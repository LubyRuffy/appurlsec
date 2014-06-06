package qsbk.app;

import qsbk.app.exception.CrashHandler;

// compiled from: QsbkApp.java
class i implements Runnable {
    final /* synthetic */ CrashHandler a;
    final /* synthetic */ QsbkApp b;

    i(QsbkApp r1_QsbkApp, CrashHandler r2_CrashHandler) {
        this.b = r1_QsbkApp;
        this.a = r2_CrashHandler;
    }

    public void run() {
        this.a.sendPreviousReportsToServer();
    }
}