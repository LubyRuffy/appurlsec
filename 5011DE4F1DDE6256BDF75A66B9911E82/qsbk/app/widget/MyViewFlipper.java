package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class MyViewFlipper extends ViewFlipper {
    public MyViewFlipper(Context r1_Context) {
        super(r1_Context);
    }

    public MyViewFlipper(Context r1_Context, AttributeSet r2_AttributeSet) {
        super(r1_Context, r2_AttributeSet);
    }

    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            stopFlipping();
        }
    }
}