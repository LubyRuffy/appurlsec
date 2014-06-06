package qsbk.app.widget.barcode;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import qsbk.app.widget.listview.XListViewHeader;

public class Layer extends FrameLayout {
    public Layer(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    protected View a(int r5i, int r6i) {
        View r1_View = null;
        int r2i = getChildCount() - 1;
        while (r2i >= 0) {
            r1_View = getChildAt(r2i);
            if (r1_View instanceof BarcodeView) {
                Rect r0_Rect = ((BarcodeView) r1_View).getInnerRect();
                if (r5i >= r0_Rect.left && r5i <= r0_Rect.right && r6i >= r0_Rect.top && r6i <= r0_Rect.bottom) {
                    return r1_View;
                }
                r2i--;
            }
            r2i--;
        }
        return r1_View;
    }

    public boolean onInterceptTouchEvent(MotionEvent r3_MotionEvent) {
        switch (r3_MotionEvent.getAction()) {
            case XListViewHeader.STATE_NORMAL:
                View r0_View = a((int) r3_MotionEvent.getX(), (int) r3_MotionEvent.getY());
                if (r0_View == getChildAt(getChildCount() - 1) || (!r0_View instanceof BarcodeView)) {
                } else {
                    removeView(r0_View);
                    addView(r0_View);
                }
                break;
        }
        return super.onInterceptTouchEvent(r3_MotionEvent);
    }
}