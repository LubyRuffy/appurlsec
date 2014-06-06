package qsbk.app.message.ui;

import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.ActivityExitHelper;
import qsbk.app.widget.slidingmenu.ui.MenuHorizontalScrollView;

// compiled from: MessageListActivity.java
class c extends ActivityExitHelper {
    final /* synthetic */ MessageListActivity a;

    c(MessageListActivity r1_MessageListActivity) {
        this.a = r1_MessageListActivity;
    }

    public boolean handleCustomBackPressed() {
        ((GroupBaseActivity) this.a.getParent()).getScrollView();
        if (!(MenuHorizontalScrollView.menuOut)) {
            return false;
        }
        ((GroupBaseActivity) this.a.getParent()).resumeMenuLayout();
        return true;
    }
}