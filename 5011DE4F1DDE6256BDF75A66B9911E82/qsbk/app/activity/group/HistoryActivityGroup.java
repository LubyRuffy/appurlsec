package qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.HistoryActivity;
import qsbk.app.activity.base.GroupBaseActivity;
import qsbk.app.utils.UIHelper;

public class HistoryActivityGroup extends GroupBaseActivity {
    private String p;

    public HistoryActivityGroup() {
        this.p = "HistoryActivityGroup";
    }

    private void j() {
        this.c.removeAllViews();
        k();
        Window r0_Window = getLocalActivityManager().startActivity("history", new Intent(this, HistoryActivity.class).addFlags(67108864));
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void k() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity("history") != null) {
                getLocalActivityManager().destroyActivity("history", true);
            }
            QsbkApp.isChange = false;
        }
    }

    private void l() {
        ((HistoryActivity) getCurrentActivity()).agaginTraver();
    }

    protected void a() {
        super.a();
        if (UIHelper.isNightTheme()) {
            this.f.setBackgroundResource(R.drawable.icon_timeagain_enable_night);
        } else {
            this.f.setBackgroundResource(R.drawable.icon_timeagain_enable);
        }
        setTitleName("\u7a7f\u8d8a\u4e2d...");
    }

    protected void b() {
        super.b();
        ((TextView) this.b.findViewById(R.id.title)).setOnClickListener(new a(this));
    }

    protected OnClickListener e() {
        return new b(this);
    }

    public boolean equals(Object r5_Object) {
        if (this == r5_Object) {
            return true;
        }
        if (r5_Object == null) {
            return false;
        }
        if (getClass() != r5_Object.getClass()) {
            return false;
        }
        HistoryActivityGroup r5_HistoryActivityGroup = (HistoryActivityGroup) r5_Object;
        if (this.p == null) {
            return r5_HistoryActivityGroup.p == null;
        } else {
            if (this.p.equals(r5_HistoryActivityGroup.p)) {
                return true;
            }
            return false;
        }
    }

    protected int f() {
        return R.layout.activity_group;
    }

    public int hashCode() {
        return (this.p == null ? 0 : this.p.hashCode()) + 31;
    }

    protected void onCreate(Bundle r1_Bundle) {
        super.onCreate(r1_Bundle);
        j();
    }

    public void setTitleName(String r3_String) {
        ((TextView) this.b.findViewById(R.id.title)).setText(r3_String);
    }
}