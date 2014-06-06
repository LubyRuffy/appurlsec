package qsbk.app.activity.security;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.activity.group.TopActivityGroup;

// compiled from: SecurityBindActivity.java
class n extends Handler {
    final /* synthetic */ SecurityBindActivity a;

    n(SecurityBindActivity r1_SecurityBindActivity) {
        this.a = r1_SecurityBindActivity;
    }

    public void handleMessage(Message r4_Message) {
        if (r4_Message.what != 0) {
            Toast.makeText(this.a, (String) r4_Message.obj, 1).show();
        } else {
            this.a.handleToken(SecurityBindActivity.e(this.a));
            this.a.a();
            this.a.startActivity(new Intent(this.a, TopActivityGroup.class));
            this.a.finish();
            if (QsbkApp.register != null) {
                QsbkApp.register.finish();
            }
        }
    }
}