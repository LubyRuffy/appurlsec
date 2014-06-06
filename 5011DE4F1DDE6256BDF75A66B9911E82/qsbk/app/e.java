package qsbk.app;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Qiushibaike.java
class e implements OnTouchListener {
    int a;
    int b;
    final /* synthetic */ Qiushibaike c;

    e(Qiushibaike r2_Qiushibaike) {
        this.c = r2_Qiushibaike;
        this.a = 0;
        this.b = 0;
    }

    public boolean onTouch(View r7_View, MotionEvent r8_MotionEvent) {
        int r0i = (int) r8_MotionEvent.getY();
        switch (r8_MotionEvent.getAction()) {
            case XListViewHeader.STATE_NORMAL:
                this.a = r0i;
                break;
            case XListViewHeader.STATE_READY:
                if (((double) this.a) > ((double) this.c.i) * 0.4d) {
                    if (this.a <= this.b) {
                        if (!this.c.l) {
                            this.c.a();
                        }
                    }
                }
                break;
            case XListViewHeader.STATE_REFRESHING:
                this.b = r0i;
                break;
        }
        return !this.c.l;
    }
}