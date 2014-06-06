package qsbk.app.activity.security;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.R;

// compiled from: SecurityBindActivity.java
class k implements OnClickListener {
    final /* synthetic */ SecurityBindActivity a;

    k(SecurityBindActivity r1_SecurityBindActivity) {
        this.a = r1_SecurityBindActivity;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        if (r5i == 0) {
            this.a.startActivityForResult(new Intent(this.a, EmailBindActivity.class), SecurityBindActivity.c(this.a));
            this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        }
        r4_DialogInterface.dismiss();
    }
}