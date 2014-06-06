package qsbk.app.activity;

import android.os.Message;
import qsbk.app.utils.VersionUtil;

// compiled from: UserSettingNavi.java
class dz extends Thread {
    final /* synthetic */ UserSettingNavi a;

    dz(UserSettingNavi r1_UserSettingNavi, String r2_String) {
        this.a = r1_UserSettingNavi;
        super(r2_String);
    }

    public void run() {
        boolean r0z = VersionUtil.manualCheck(this.a);
        Message r1_Message = this.a.a.obtainMessage();
        r1_Message.obj = Boolean.valueOf(r0z);
        r1_Message.sendToTarget();
    }
}