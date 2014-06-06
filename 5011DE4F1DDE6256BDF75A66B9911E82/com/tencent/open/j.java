package com.tencent.open;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
class j extends Handler {
    private i a;

    public j(i r1_i, Looper r2_Looper) {
        super(r2_Looper);
        this.a = r1_i;
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
                if (TDialog.c == null || TDialog.c.get() == null) {
                } else {
                    TDialog.c((Context) TDialog.c.get(), (String) r3_Message.obj);
                }
                break;
            case XListViewFooter.STATE_NODATA:
                if (TDialog.d == null || TDialog.d.get() == null) {
                } else {
                    ((View) TDialog.d.get()).setVisibility(Base64.DONT_BREAK_LINES);
                }
                break;
            case ShareUtils.SHARE_SMS:
                if (TDialog.c == null || TDialog.c.get() == null) {
                } else {
                    TDialog.d((Context) TDialog.c.get(), (String) r3_Message.obj);
                }
                break;
        }
    }
}