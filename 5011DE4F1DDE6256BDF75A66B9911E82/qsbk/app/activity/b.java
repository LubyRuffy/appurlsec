package qsbk.app.activity;

import android.view.View;
import qsbk.app.utils.audit.ScrollViewTouchListener.OnDirection;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: AuditNativeActivity.java
class b implements OnDirection {
    final /* synthetic */ AuditNativeActivity a;

    b(AuditNativeActivity r1_AuditNativeActivity) {
        this.a = r1_AuditNativeActivity;
    }

    public void toRight(View r3_View) {
        if (this.a.V) {
            this.a.V = false;
            this.a.b((int)XListViewHeader.STATE_REFRESHING);
        }
    }
}