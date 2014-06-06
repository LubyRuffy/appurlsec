package com.tencent.mm.sdk.platformtools;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

final class o implements OnTouchListener {
    final /* synthetic */ View a;
    final /* synthetic */ View b;

    o(View r1_View, View r2_View) {
        this.a = r1_View;
        this.b = r2_View;
    }

    public final boolean onTouch(View r4_View, MotionEvent r5_MotionEvent) {
        switch (r5_MotionEvent.getAction()) {
            case XListViewHeader.STATE_NORMAL:
                this.a.setSelected(true);
                break;
            case XListViewHeader.STATE_READY:
            case XListViewFooter.STATE_NOMORE:
            case XListViewFooter.STATE_NODATA:
                this.a.setSelected(false);
                break;
            case XListViewHeader.STATE_REFRESHING:
                this.a.setSelected(this.b.isPressed());
                break;
        }
        return false;
    }
}