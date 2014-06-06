package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.activity.security.FindPasswordActivity;

// compiled from: LoginActivity.java
class av implements OnClickListener {
    final /* synthetic */ at a;

    av(at r1_at) {
        this.a = r1_at;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        this.a.a.startActivity(new Intent(this.a.a, FindPasswordActivity.class));
    }
}