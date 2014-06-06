package qsbk.app.service;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.QsbkApp;

// compiled from: VersionCheckService.java
class l implements OnClickListener {
    final /* synthetic */ VersionCheckService a;

    l(VersionCheckService r1_VersionCheckService) {
        this.a = r1_VersionCheckService;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        new m(this, "qbk-VerCheck2").start();
        this.a.startService(new Intent(QsbkApp.mContext, VersionService.class));
    }
}