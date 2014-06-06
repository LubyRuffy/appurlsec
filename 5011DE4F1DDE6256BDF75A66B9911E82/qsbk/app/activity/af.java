package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.R;
import qsbk.app.activity.security.EmailBindActivity;

// compiled from: EditInfoEntranceActivity.java
class af implements OnClickListener {
    final /* synthetic */ ae a;

    af(ae r1_ae) {
        this.a = r1_ae;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        if (r5i == 0) {
            this.a.a.startActivityForResult(new Intent(this.a.a, EmailBindActivity.class), this.a.a.G);
            this.a.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        }
        r4_DialogInterface.dismiss();
    }
}