package com.tencent.open;

import android.util.Log;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
class c {
    final /* synthetic */ PKDialog a;

    private c(PKDialog r1_PKDialog) {
        this.a = r1_PKDialog;
    }

    public void onCancel(String r3_String) {
        this.a.f.obtainMessage(XListViewHeader.STATE_REFRESHING, r3_String).sendToTarget();
        this.a.dismiss();
    }

    public void onComplete(String r3_String) {
        this.a.f.obtainMessage(1, r3_String).sendToTarget();
        Log.e("onComplete", r3_String);
        this.a.dismiss();
    }

    public void onLoad(String r3_String) {
        this.a.f.obtainMessage(XListViewFooter.STATE_NODATA, r3_String).sendToTarget();
    }

    public void showMsg(String r3_String) {
        this.a.f.obtainMessage(XListViewFooter.STATE_NOMORE, r3_String).sendToTarget();
    }
}