package qsbk.app.service;

import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.VersionUtil;

// compiled from: VersionCheckService.java
class j extends Thread {
    final /* synthetic */ VersionCheckService a;

    j(VersionCheckService r1_VersionCheckService, String r2_String) {
        this.a = r1_VersionCheckService;
        super(r2_String);
    }

    public void run() {
        try {
            boolean r0z = VersionUtil.isNeedUpdate(this.a);
            DebugUtil.debug("\u662f\u5426\u6709\u65b0\u7248\u672c\u5b58\u5728\uff1a" + r0z);
            if (r0z && VersionCheckService.b()) {
                this.a.a.obtainMessage().sendToTarget();
            } else {
                this.a.stopSelf();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}