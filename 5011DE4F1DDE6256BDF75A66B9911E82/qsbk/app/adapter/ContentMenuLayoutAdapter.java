package qsbk.app.adapter;

import android.app.Activity;
import android.content.Intent;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.AuditNativeActivity;
import qsbk.app.activity.base.IScrollView;
import qsbk.app.provider.QBMenu;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastUtil;

public class ContentMenuLayoutAdapter extends MenuLayoutAdapter {
    public ContentMenuLayoutAdapter(Activity r1_Activity, int r2i, Class r3_Class, List<QBMenu> r4_List_QBMenu) {
        a(r1_Activity, r2i, r3_Class, r4_List_QBMenu);
    }

    protected boolean a(int r6i) {
        Intent r2_Intent = new Intent();
        QBMenu r0_QBMenu = (QBMenu) this.d.get(r6i);
        Class r3_Class = r0_QBMenu.getMenuClass();
        if (r0_QBMenu.isNeedLogin() && QsbkApp.currentUser == null) {
            a(r3_Class);
            return true;
        } else if ((!r0_QBMenu.needCheckNetStatus()) || HttpUtils.isNetworkConnected(this.a)) {
            if (r3_Class != null) {
                if (r3_Class.equals(this.c)) {
                    ((IScrollView) this.a).getScrollView().clickMenuBtn();
                } else if (this.c != null) {
                    r2_Intent.setClass(this.a, r3_Class);
                    if (r0_QBMenu.needFinish()) {
                        ((IScrollView) this.a).getScrollView().clickMenuItem();
                        a(r2_Intent);
                    } else {
                        this.a.startActivity(r2_Intent);
                        this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
                    }
                }
            }
            return false;
        } else {
            ToastUtil.Short((int)R.string.network_not_connected);
            return false;
        }
    }

    protected boolean b(int r3i) {
        QBMenu r0_QBMenu = (QBMenu) this.d.get(r3i);
        return r0_QBMenu == null || (!r0_QBMenu.getMenuClass().equals(AuditNativeActivity.class));
    }
}