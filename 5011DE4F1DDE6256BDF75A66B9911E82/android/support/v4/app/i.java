package android.support.v4.app;

import android.os.Handler;
import android.os.Message;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: FragmentActivity.java
class i extends Handler {
    final /* synthetic */ FragmentActivity a;

    i(FragmentActivity r1_FragmentActivity) {
        this.a = r1_FragmentActivity;
    }

    public void handleMessage(Message r3_Message) {
        switch (r3_Message.what) {
            case XListViewHeader.STATE_READY:
                if (this.a.f) {
                    this.a.a(false);
                }
                return;
            case XListViewHeader.STATE_REFRESHING:
                this.a.a();
                this.a.b.execPendingActions();
                return;
        }
        super.handleMessage(r3_Message);
    }
}