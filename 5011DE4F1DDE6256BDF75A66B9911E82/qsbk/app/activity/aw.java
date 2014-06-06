package qsbk.app.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.Base64;

// compiled from: LoginActivity.java
class aw extends Handler {
    final /* synthetic */ LoginActivity a;

    aw(LoginActivity r1_LoginActivity) {
        this.a = r1_LoginActivity;
    }

    public void handleMessage(Message r5_Message) {
        this.a.r.setVisibility(0);
        this.a.t.setVisibility(Base64.DONT_BREAK_LINES);
        if (r5_Message.what != 0) {
            Toast.makeText(this.a.p, (String) r5_Message.obj, 1).show();
            if (r5_Message.what == 114) {
                this.a.startActivity(new Intent(this.a, RegisterActivity.class));
                this.a.finish();
            }
        } else {
            this.a.handleToken(this.a.x);
            this.a.f();
            this.a.finish();
            if (this.a.y != null) {
                this.a.p.startActivity(new Intent(this.a.p, this.a.y));
            }
            this.a.a(this.a.B, this.a.C, this.a.A.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA) ? QsbkApp.currentUser.userId + "_sina_access_token" : QsbkApp.currentUser.userId + "_qq_access_token");
            this.a.onLoginSuccess();
        }
    }
}