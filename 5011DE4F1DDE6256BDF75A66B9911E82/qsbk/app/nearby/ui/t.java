package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

// compiled from: NearByListActivity.java
class t implements OnCancelListener {
    final /* synthetic */ NearByListActivity a;

    t(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onCancel(DialogInterface r2_DialogInterface) {
        this.a.show_restart();
    }
}