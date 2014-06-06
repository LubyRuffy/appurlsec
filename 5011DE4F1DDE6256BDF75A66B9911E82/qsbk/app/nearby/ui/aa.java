package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: NearByListActivity.java
class aa implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    aa(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(View r3_View) {
        this.a.openLocationSettingActivity(this.a);
    }
}