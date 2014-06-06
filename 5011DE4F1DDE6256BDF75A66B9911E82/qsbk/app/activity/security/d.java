package qsbk.app.activity.security;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.Base64;

// compiled from: EmailBindActivity.java
class d extends Handler {
    final /* synthetic */ EmailBindActivity a;

    d(EmailBindActivity r1_EmailBindActivity) {
        this.a = r1_EmailBindActivity;
    }

    public void handleMessage(Message r4_Message) {
        this.a.c.setVisibility(Base64.DONT_BREAK_LINES);
        this.a.b.setVisibility(0);
        if (r4_Message.what != 0) {
            this.a.d.setText("\u7ed1\u5b9a\u90ae\u7bb1");
            Toast.makeText(this.a, (String) r4_Message.obj, 1).show();
        } else {
            Intent r0_Intent = new Intent();
            r0_Intent.putExtra(QsbkDatabase.USER_EMAIL, this.a.g.getText().toString().trim());
            this.a.setResult(OneProfileActivity.REQ_CODE_SHARE, r0_Intent);
            this.a.finish();
        }
    }
}