package qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.baidu.android.pushservice.PushManager;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MyContentsActivity;
import qsbk.app.activity.base.GroupBaseActivity;

public class MyContentsActivityGroup extends GroupBaseActivity {
    private String p;

    public MyContentsActivityGroup() {
        this.p = "MyContentsActivityGroup";
    }

    private void j() {
        this.c.removeAllViews();
        k();
        Window r0_Window = getLocalActivityManager().startActivity("mycontent", new Intent(this, MyContentsActivity.class).addFlags(67108864));
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void k() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity("mycontent") != null) {
                getLocalActivityManager().destroyActivity("mycontent", true);
            }
            QsbkApp.isChange = false;
        }
    }

    protected void a() {
        super.a();
        ((TextView) this.b.findViewById(R.id.title)).setText("\u6211\u53d1\u8868\u7684");
    }

    protected void b() {
        super.b();
        ((TextView) this.b.findViewById(R.id.title)).setOnClickListener(new d(this));
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
        MyContentsActivityGroup r5_MyContentsActivityGroup = (MyContentsActivityGroup) r5_Object;
        if (this.p == null) {
            return r5_MyContentsActivityGroup.p == null;
        } else {
            if (this.p.equals(r5_MyContentsActivityGroup.p)) {
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

    protected void onCreate(Bundle r2_Bundle) {
        super.onCreate(r2_Bundle);
        j();
        QsbkApp.messageCount = 1;
    }

    protected void onStart() {
        super.onStart();
        PushManager.activityStarted(this);
    }

    protected void onStop() {
        PushManager.activityStoped(this);
        super.onStop();
    }
}