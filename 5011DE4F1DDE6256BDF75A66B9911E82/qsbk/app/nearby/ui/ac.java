package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: NearByListActivity.java
class ac implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    ac(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(DialogInterface r2_DialogInterface, int r3i) {
        r2_DialogInterface.dismiss();
        NearByListActivity.b(this.a);
    }
}