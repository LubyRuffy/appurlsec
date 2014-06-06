package qsbk.app.activity.base;

import qsbk.app.utils.ActivityExitHelper;
import qsbk.app.widget.slidingmenu.ui.MenuHorizontalScrollView;

// compiled from: GroupBaseActivity.java
class o extends ActivityExitHelper {
    final /* synthetic */ GroupBaseActivity a;

    o(GroupBaseActivity r1_GroupBaseActivity) {
        this.a = r1_GroupBaseActivity;
    }

    public boolean handleCustomBackPressed() {
        GroupBaseActivity.a(this.a);
        if (!(MenuHorizontalScrollView.menuOut)) {
            return false;
        }
        this.a.resumeMenuLayout();
        return true;
    }
}