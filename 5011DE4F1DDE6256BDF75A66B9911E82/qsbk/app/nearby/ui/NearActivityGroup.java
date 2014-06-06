package qsbk.app.nearby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;

public class NearActivityGroup extends GroupBaseActivity {
    private ProgressBar p;
    private ImageButton q;
    private ImageButton r;

    private void j() {
        this.c.removeAllViews();
        k();
        Window r0_Window = getLocalActivityManager().startActivity(NearByListActivity.DIALOG_KEY_NEARBYLIST, new Intent(this, NearByListActivity.class).addFlags(67108864));
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void k() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity(NearByListActivity.DIALOG_KEY_NEARBYLIST) != null) {
                getLocalActivityManager().destroyActivity(NearByListActivity.DIALOG_KEY_NEARBYLIST, true);
            }
            QsbkApp.isChange = false;
        }
    }

    protected void a() {
        super.a();
        i();
        a("\u9644\u8fd1");
        this.p = (ProgressBar) this.b.findViewById(R.id.topLoading);
        this.q = (ImageButton) this.b.findViewById(R.id.id_extra_btn);
        this.r = (ImageButton) this.b.findViewById(R.id.id_btn_menu);
        LogUtil.d("topbar:" + this.b.findViewById(R.id.topbar).toString());
        LinearLayout r0_LinearLayout = (LinearLayout) this.b.findViewById(R.id.topbar_leftbtn_area);
        if (r0_LinearLayout != null) {
            LogUtil.d("add click listern to topbar_leftbtn_area");
            r0_LinearLayout.setOnClickListener(this.n);
        }
    }

    protected void b() {
        super.b();
    }

    protected int f() {
        return R.layout.activity_group_newtopbar;
    }

    protected void h() {
        if (getCurrentActivity() != null) {
        }
    }

    protected void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night_OVERIDE);
        }
        j();
    }

    public void setExtraBtnListener(OnClickListener r2_OnClickListener) {
        this.q.setOnClickListener(r2_OnClickListener);
    }

    public void setMenuBtnListener(OnClickListener r2_OnClickListener) {
        this.r.setOnClickListener(r2_OnClickListener);
    }
}