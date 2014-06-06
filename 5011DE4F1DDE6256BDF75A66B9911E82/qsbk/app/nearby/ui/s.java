package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.CheckBox;
import qsbk.app.R;
import qsbk.app.nearby.api.NearbyEngine;

// compiled from: NearByListActivity.java
class s implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    s(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(DialogInterface r3_DialogInterface, int r4i) {
        if (((CheckBox) NearByListActivity.g(this.a).findViewById(R.id.checkBox)).isChecked()) {
            NearbyEngine.instance().setNearbyNoMoreWarn();
        }
        NearByListActivity.a(this.a, null);
        r3_DialogInterface.dismiss();
        NearByListActivity.h(this.a);
        NearByListActivity.b(this.a, false);
    }
}