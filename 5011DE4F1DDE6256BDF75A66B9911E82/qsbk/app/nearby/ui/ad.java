package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: NearByListActivity.java
class ad implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    ad(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(View r2_View) {
        NearByListActivity.c(this.a);
        NearByListActivity.d(this.a).dismiss();
    }
}