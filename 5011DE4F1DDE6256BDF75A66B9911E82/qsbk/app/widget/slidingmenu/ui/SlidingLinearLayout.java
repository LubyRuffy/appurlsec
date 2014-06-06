package qsbk.app.widget.slidingmenu.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class SlidingLinearLayout extends LinearLayout {
    private float a;

    public SlidingLinearLayout(Context r2_Context, AttributeSet r3_AttributeSet) {
        super(r2_Context, r3_AttributeSet);
        this.a = -1.0f;
    }

    public boolean onInterceptTouchEvent(MotionEvent r4_MotionEvent) {
        if (r4_MotionEvent.getAction() == 0) {
            this.a = (float) ((int) r4_MotionEvent.getRawX());
        }
        return ((this.a > ((float) MenuHorizontalScrollView.TOUCHE_CONTROL_WIDTH) ? 1 : (this.a == ((float) MenuHorizontalScrollView.TOUCHE_CONTROL_WIDTH)? 0 : -1)) >= 0 && (!MenuHorizontalScrollView.menuOut)) ? super.onInterceptTouchEvent(r4_MotionEvent) : true;
    }
}