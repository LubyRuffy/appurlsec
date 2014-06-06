package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

// compiled from: NearByListActivity.java
class o implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    o(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(View r5_View) {
        if (NearByListActivity.d(this.a) != null) {
            NearByListActivity.d(this.a).showAsDropDown(r5_View, 0, UIHelper.dip2px(this.a, -10.0f));
        }
    }
}