package qsbk.app.activity.base;

import android.os.Handler;
import android.os.Message;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: GroupBaseActivity.java
class e extends Handler {
    final /* synthetic */ GroupBaseActivity a;

    e(GroupBaseActivity r1_GroupBaseActivity) {
        this.a = r1_GroupBaseActivity;
    }

    public void handleMessage(Message r2_Message) {
        switch (r2_Message.what) {
            case XListViewHeader.STATE_READY:
                this.a.d();
                break;
        }
    }
}