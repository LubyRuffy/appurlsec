package qsbk.app.nearby.ui;

import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.ActivityExitHelper;
import qsbk.app.widget.slidingmenu.ui.MenuHorizontalScrollView;

// compiled from: NearByListActivity.java
class x extends ActivityExitHelper {
    final /* synthetic */ NearByListActivity a;

    x(NearByListActivity r1_NearByListActivity) {
        this.a = r1_NearByListActivity;
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