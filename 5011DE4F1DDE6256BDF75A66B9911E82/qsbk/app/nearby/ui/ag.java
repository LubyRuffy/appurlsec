package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: NearByListActivity.java
class ag implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    ag(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(View r3_View) {
        NearByListActivity.d(this.a).dismiss();
        NearByListActivity.a(this.a, true);
        this.a.init();
    }
}