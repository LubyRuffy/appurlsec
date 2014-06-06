package qsbk.app.nearby.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.activity.UserSettingNavi;

// compiled from: NearByListActivity.java
class ae implements OnClickListener {
    final /* synthetic */ NearByListActivity a;

    ae(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
    }

    public void onClick(View r4_View) {
        NearByListActivity.d(this.a).dismiss();
        this.a.startActivity(new Intent(this.a, UserSettingNavi.class));
        this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
    }
}