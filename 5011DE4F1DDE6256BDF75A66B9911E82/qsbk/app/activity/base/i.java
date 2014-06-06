package qsbk.app.activity.base;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.UserSettingNavi;

// compiled from: GroupBaseActivity.java
class i implements OnClickListener {
    final /* synthetic */ GroupBaseActivity a;

    i(GroupBaseActivity r1_GroupBaseActivity) {
        this.a = r1_GroupBaseActivity;
    }

    public void onClick(View r4_View) {
        this.a.a.startActivity(new Intent(this.a.a, UserSettingNavi.class));
        this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        QsbkApp.tmpContext = this.a;
    }
}