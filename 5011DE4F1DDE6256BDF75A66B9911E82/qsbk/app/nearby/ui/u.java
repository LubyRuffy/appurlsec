package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: NearByListActivity.java
class u implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    u(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        NearByListActivity.a(this.a, null);
        r3_DialogInterface.dismiss();
    }
}