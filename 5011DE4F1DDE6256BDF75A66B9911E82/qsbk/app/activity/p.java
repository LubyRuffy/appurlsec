package qsbk.app.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import qsbk.app.widget.ProfileHeaderListView;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: AuditNativeActivity.java
class p extends Handler {
    final /* synthetic */ AuditNativeActivity a;

    p(AuditNativeActivity r1_AuditNativeActivity, Looper r2_Looper) {
        this.a = r1_AuditNativeActivity;
        super(r2_Looper);
    }

    public void handleMessage(Message r4_Message) {
        switch (r4_Message.what) {
            case ProfileHeaderListView.INVALID_TAB_ID:
                postDelayed(this.a.ak, 1500);
                break;
            case XListViewHeader.STATE_NORMAL:
                this.a.h();
                break;
            case XListViewHeader.STATE_READY:
                this.a.a(this.a.ah.size());
                this.a.b(-1);
                break;
        }
        super.handleMessage(r4_Message);
    }
}