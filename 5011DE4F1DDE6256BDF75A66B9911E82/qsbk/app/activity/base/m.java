package qsbk.app.activity.base;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LogUtil;

// compiled from: GroupBaseActivity.java
class m implements OnClickListener {
    final /* synthetic */ GroupBaseActivity a;

    m(GroupBaseActivity r1_GroupBaseActivity) {
        this.a = r1_GroupBaseActivity;
    }

    public void onClick(View r2_View) {
        LogUtil.d("nearby clicked");
        this.a.resumeMenuLayout();
    }
}