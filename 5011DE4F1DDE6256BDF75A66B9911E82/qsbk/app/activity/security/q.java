package qsbk.app.activity.security;

import android.os.Handler;
import android.os.Message;

// compiled from: SecurityBindActivity.java
class q extends Handler {
    final /* synthetic */ SecurityBindActivity a;

    q(SecurityBindActivity r1_SecurityBindActivity) {
        this.a = r1_SecurityBindActivity;
    }

    public void handleMessage(Message r4_Message) {
        SecurityBindActivity.i(this.a).setText("\u8bbe\u5b9a\u5bc6\u4fdd");
        if (r4_Message.what == 1) {
            SecurityBindActivity.l(this.a).setText((String) r4_Message.obj);
        } else {
            SecurityBindActivity.k(this.a).setText((String) r4_Message.obj);
        }
        SecurityBindActivity.a(this.a, true);
    }
}