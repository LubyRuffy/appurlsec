package com.qiubai.library.adview.util;

import android.os.Handler;
import android.os.Message;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: AdViewNetFetchThread.java
class a extends Handler {
    final /* synthetic */ AdViewNetFetchThread a;

    a(AdViewNetFetchThread r1_AdViewNetFetchThread) {
        this.a = r1_AdViewNetFetchThread;
    }

    public void handleMessage(Message r6_Message) {
        super.handleMessage(r6_Message);
        synchronized (this.a.g) {
            if (this.a.b == null) {
            } else {
                switch (r6_Message.what) {
                    case XListViewHeader.STATE_NORMAL:
                        this.a.b.notifyFetchStatus(this.a, r6_Message.arg1, r6_Message.obj);
                        break;
                }
            }
        }
    }
}