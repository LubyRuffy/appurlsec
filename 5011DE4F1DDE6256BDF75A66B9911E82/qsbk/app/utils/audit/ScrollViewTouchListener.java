package qsbk.app.utils.audit;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class ScrollViewTouchListener implements OnTouchListener {
    private static final String a;
    private VelocityTracker b;
    private float c;
    private float d;
    private int e;
    private boolean f;
    private float g;
    private float h;
    private int i;
    private int j;
    private OnDirection k;

    public static interface OnDirection {
        public void toRight(View r1_View);
    }

    static {
        a = ScrollViewTouchListener.class.getName();
    }

    public ScrollViewTouchListener(Context r3_Context, OnDirection r4_OnDirection) {
        this.b = null;
        this.f = false;
        this.j = -1;
        ViewConfiguration r0_ViewConfiguration = ViewConfiguration.get(r3_Context);
        float r1f = r3_Context.getResources().getDisplayMetrics().density;
        this.e = r0_ViewConfiguration.getScaledMaximumFlingVelocity();
        this.i = (int) (50.0f * r1f);
        this.k = r4_OnDirection;
    }

    public boolean onTouch(View r5_View, MotionEvent r6_MotionEvent) {
        switch ((r6_MotionEvent.getAction() & 255)) {
            case XListViewHeader.STATE_NORMAL:
                float r0f = r6_MotionEvent.getX();
                this.g = r0f;
                this.c = r0f;
                r0f = r6_MotionEvent.getY();
                this.h = r0f;
                this.d = r0f;
                this.j = MotionEventCompat.getPointerId(r6_MotionEvent, 0);
                break;
            case XListViewHeader.STATE_READY:
            case XListViewFooter.STATE_NOMORE:
                int r0i = MotionEventCompat.findPointerIndex(r6_MotionEvent, this.j);
                if (r0i != -1) {
                    float r1f = MotionEventCompat.getX(r6_MotionEvent, r0i);
                    int r1i = (int) (r1f - this.g);
                    r0i = (int) (MotionEventCompat.getY(r6_MotionEvent, r0i) - this.h);
                    if (r1i >= 0 || Math.abs(r1i) <= Math.abs(r0i) || Math.abs(r1i) <= this.i) {
                        if ((!this.f) || this.k == null) {
                        } else {
                            this.f = false;
                            this.k.toRight(r5_View);
                        }
                    } else {
                        this.f = true;
                        if (this.f || this.k == null) {
                        } else {
                            this.f = false;
                            this.k.toRight(r5_View);
                        }
                    }
                }
                break;
            case ShareUtils.SHARE_SMS:
                this.j = MotionEventCompat.getPointerId(r6_MotionEvent, MotionEventCompat.getActionIndex(r6_MotionEvent));
                break;
        }
        return this.f;
    }
}