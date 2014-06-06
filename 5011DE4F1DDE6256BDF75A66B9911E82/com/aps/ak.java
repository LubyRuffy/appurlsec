package com.aps;

import android.os.Handler;
import android.os.Message;
import qsbk.app.widget.listview.XListViewHeader;

final class ak extends Handler {
    private /* synthetic */ aj a;

    ak(aj r1_aj) {
        this.a = r1_aj;
    }

    public final void handleMessage(Message r3_Message) {
        switch (r3_Message.what) {
            case XListViewHeader.STATE_READY:
                if (t.d(this.a.a) != null) {
                    t.d(this.a.a).a((String) r3_Message.obj);
                }
                break;
        }
    }
}