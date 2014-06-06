package com.tencent.open;

import android.util.Log;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: ProGuard
class h {
    final /* synthetic */ TDialog a;

    private h(TDialog r1_TDialog) {
        this.a = r1_TDialog;
    }

    public void onAddShare(String r1_String) {
        onComplete(r1_String);
    }

    public void onCancel(String r3_String) {
        this.a.m.obtainMessage(XListViewHeader.STATE_REFRESHING, r3_String).sendToTarget();
        this.a.dismiss();
    }

    public void onCancelAddShare(int r2i) {
        onCancel(null);
    }

    public void onCancelInvite() {
        onCancel(null);
    }

    public void onCancelLogin() {
        onCancel(null);
    }

    public void onComplete(String r3_String) {
        this.a.m.obtainMessage(1, r3_String).sendToTarget();
        Log.e("onComplete", r3_String);
        this.a.dismiss();
    }

    public void onInvite(String r1_String) {
        onComplete(r1_String);
    }

    public void onLoad(String r3_String) {
        this.a.m.obtainMessage(XListViewFooter.STATE_NODATA, r3_String).sendToTarget();
    }

    public void showMsg(String r3_String) {
        this.a.m.obtainMessage(XListViewFooter.STATE_NOMORE, r3_String).sendToTarget();
    }
}