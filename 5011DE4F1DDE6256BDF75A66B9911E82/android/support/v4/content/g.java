package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: LocalBroadcastManager.java
class g extends Handler {
    final /* synthetic */ LocalBroadcastManager a;

    g(LocalBroadcastManager r1_LocalBroadcastManager, Looper r2_Looper) {
        this.a = r1_LocalBroadcastManager;
        super(r2_Looper);
    }

    public void handleMessage(Message r2_Message) {
        switch (r2_Message.what) {
            case XListViewHeader.STATE_READY:
                LocalBroadcastManager.a(this.a);
                return;
        }
        super.handleMessage(r2_Message);
    }
}