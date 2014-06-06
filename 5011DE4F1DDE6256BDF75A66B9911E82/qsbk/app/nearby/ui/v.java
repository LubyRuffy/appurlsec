package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: NearByListActivity.java
class v implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    v(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(DialogInterface r4_DialogInterface, int r5i) {
        r4_DialogInterface.dismiss();
        NearByListActivity.a(this.a, NearByListActivity.i(this.a).getGender(), NearByListActivity.i(this.a).getTimeInMinute());
        NearByListActivity.a(this.a, null);
    }
}