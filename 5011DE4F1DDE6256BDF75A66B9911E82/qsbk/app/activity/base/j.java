package qsbk.app.activity.base;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

// compiled from: GroupBaseActivity.java
class j implements OnLongClickListener {
    final /* synthetic */ GroupBaseActivity a;

    j(GroupBaseActivity r1_GroupBaseActivity) {
        this.a = r1_GroupBaseActivity;
    }

    public boolean onLongClick(View r5_View) {
        if (UIHelper.isNightTheme()) {
            SharePreferenceUtils.setSharePreferencesValue("themeid", "day");
            StatService.onEvent(this.a, "nightTheme", "close", 1);
        } else {
            SharePreferenceUtils.setSharePreferencesValue("themeid", "night");
            StatService.onEvent(this.a, "nightTheme", "open", 1);
        }
        QsbkApp.isChange = true;
        this.a.recreate();
        return true;
    }
}