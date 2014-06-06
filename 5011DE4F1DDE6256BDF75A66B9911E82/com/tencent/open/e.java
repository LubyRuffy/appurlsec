package com.tencent.open;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
class e extends Handler {
    private d a;

    public e(d r1_d, Looper r2_Looper) {
        super(r2_Looper);
        this.a = r1_d;
    }

    public void handleMessage(Message r3_Message) {
        switch (r3_Message.what) {
            case XListViewHeader.STATE_READY:
                this.a.a((String) r3_Message.obj);
                break;
            case XListViewHeader.STATE_REFRESHING:
                this.a.onCancel();
                break;
            case XListViewFooter.STATE_NOMORE:
                if (PKDialog.i == null || PKDialog.i.get() == null) {
                } else {
                    PKDialog.c((Context) PKDialog.i.get(), (String) r3_Message.obj);
                }
                break;
            case ShareUtils.SHARE_SMS:
                if (PKDialog.i == null || PKDialog.i.get() == null) {
                } else {
                    PKDialog.d((Context) PKDialog.i.get(), (String) r3_Message.obj);
                }
                break;
        }
    }
}