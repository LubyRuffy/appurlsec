package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.utils.Base64;

// compiled from: UserSetting.java
class dn extends Handler {
    final /* synthetic */ UserSetting a;

    dn(UserSetting r1_UserSetting) {
        this.a = r1_UserSetting;
    }

    public void handleMessage(Message r4_Message) {
        Toast.makeText(this.a, (String) r4_Message.obj, 1).show();
        this.a.w.setVisibility(Base64.DONT_BREAK_LINES);
    }
}