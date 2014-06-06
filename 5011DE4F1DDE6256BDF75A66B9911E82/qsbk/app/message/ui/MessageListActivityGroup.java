package qsbk.app.message.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.Base64;

public class MessageListActivityGroup extends GroupBaseActivity {
    private ProgressBar p;
    private boolean q;

    public MessageListActivityGroup() {
        this.q = false;
    }

    private void j() {
        this.c.removeAllViews();
        k();
        Window r0_Window = getLocalActivityManager().startActivity("messagelist", new Intent(this, MessageListActivity.class).addFlags(67108864));
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void k() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity("messagelist") != null) {
                getLocalActivityManager().destroyActivity("messagelist", true);
            }
            QsbkApp.isChange = false;
        }
    }

    protected void a() {
        super.a();
        this.m = null;
        i();
        ((TextView) this.b.findViewById(R.id.title)).setText("\u5c0f\u7eb8\u6761Beta");
        this.p = (ProgressBar) this.b.findViewById(R.id.topLoading);
    }

    protected void a(boolean r3z) {
        if (r3z) {
            this.p.setVisibility(Base64.DONT_BREAK_LINES);
        } else {
            this.p.setVisibility(0);
        }
    }

    protected void b() {
        super.b();
        ((TextView) this.b.findViewById(R.id.title)).setOnClickListener(new d(this));
    }

    protected int f() {
        return R.layout.activity_group;
    }

    protected void h() {
        if (getCurrentActivity() != null) {
            ((MessageListActivity) getCurrentActivity()).q.setSelection(0);
        }
    }

    protected void onCreate(Bundle r1_Bundle) {
        super.onCreate(r1_Bundle);
        j();
    }

    protected void onPause() {
        super.onPause();
        if (this.q) {
            overridePendingTransition(R.anim.roll_right, R.anim.still_when_right);
            this.q = false;
        }
    }

    public void overridePendingAnimation() {
        this.q = true;
    }
}