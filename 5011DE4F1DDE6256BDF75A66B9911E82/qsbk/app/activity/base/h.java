package qsbk.app.activity.base;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.activity.UserSetting;

// compiled from: GroupBaseActivity.java
class h implements OnClickListener {
    final /* synthetic */ GroupBaseActivity a;

    h(GroupBaseActivity r1_GroupBaseActivity) {
        this.a = r1_GroupBaseActivity;
    }

    public void onClick(View r4_View) {
        if (QsbkApp.currentUser == null) {
            this.a.a(UserSetting.class);
        } else {
            Intent r0_Intent = new Intent(this.a.a, OneProfileActivity.class);
            r0_Intent.putExtra(OneProfileActivity.USER, QsbkApp.currentUser.toString());
            r0_Intent.putExtra(OneProfileActivity.SOURCE, "userLayout");
            this.a.startActivity(r0_Intent);
            this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
        }
    }
}