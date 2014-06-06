package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.service.VersionService;

// compiled from: UserSettingNavi.java
class ea implements OnClickListener {
    final /* synthetic */ UserSettingNavi a;

    ea(UserSettingNavi r1_UserSettingNavi) {
        this.a = r1_UserSettingNavi;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        new eb(this, "qbk-UserSetN3").start();
        this.a.startService(new Intent(QsbkApp.mContext, VersionService.class));
    }
}