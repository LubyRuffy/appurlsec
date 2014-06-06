package qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MyCollectionActivity;
import qsbk.app.activity.base.GroupBaseActivity;

public class MyCollectionActivityGroup extends GroupBaseActivity {
    private String p;

    public MyCollectionActivityGroup() {
        this.p = "MyCollectionActivityGroup";
    }

    private void j() {
        this.c.removeAllViews();
        k();
        Window r0_Window = getLocalActivityManager().startActivity("mycollection", new Intent(this, MyCollectionActivity.class).addFlags(67108864));
        if (this.c == null || r0_Window == null) {
        } else {
            this.c.addView(r0_Window.getDecorView());
        }
    }

    private void k() {
        if (QsbkApp.isChange) {
            if (getLocalActivityManager().getActivity("mycollection") != null) {
                getLocalActivityManager().destroyActivity("mycollection", true);
            }
            QsbkApp.isChange = false;
        }
    }

    protected void a() {
        super.a();
        ((TextView) this.b.findViewById(R.id.title)).setText("\u6211\u6536\u85cf\u7684");
    }

    protected void b() {
        super.b();
        ((TextView) this.b.findViewById(R.id.title)).setOnClickListener(new c(this));
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
        MyCollectionActivityGroup r5_MyCollectionActivityGroup = (MyCollectionActivityGroup) r5_Object;
        if (this.p == null) {
            return r5_MyCollectionActivityGroup.p == null;
        } else {
            if (this.p.equals(r5_MyCollectionActivityGroup.p)) {
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
}