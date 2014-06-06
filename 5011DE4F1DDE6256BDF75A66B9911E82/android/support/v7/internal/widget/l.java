package android.support.v7.internal.widget;

import android.view.View;

// compiled from: ListPopupWindow.java
class l implements Runnable {
    final /* synthetic */ ListPopupWindow a;

    l(ListPopupWindow r1_ListPopupWindow) {
        this.a = r1_ListPopupWindow;
    }

    public void run() {
        View r0_View = this.a.getAnchorView();
        if (r0_View == null || r0_View.getWindowToken() == null) {
        } else {
            this.a.show();
        }
    }
}