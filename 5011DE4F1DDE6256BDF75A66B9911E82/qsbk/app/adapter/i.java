package qsbk.app.adapter;

import android.os.Message;
import qsbk.app.utils.VersionUtil;

// compiled from: UserMenuLayoutAdapter.java
class i extends Thread {
    final /* synthetic */ UserMenuLayoutAdapter a;

    i(UserMenuLayoutAdapter r1_UserMenuLayoutAdapter, String r2_String) {
        this.a = r1_UserMenuLayoutAdapter;
        super(r2_String);
    }

    public void run() {
        boolean r0z = VersionUtil.isNeedUpdate(this.a.a);
        Message r1_Message = this.a.e.obtainMessage();
        r1_Message.obj = Boolean.valueOf(r0z);
        r1_Message.sendToTarget();
    }
}