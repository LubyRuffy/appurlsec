package qsbk.app;

import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

// compiled from: QsbkApp.java
final class j implements OnTouchListener {
    public final float[] BT_NOT_SELECTED;
    public final float[] BT_SELECTED;

    j() {
        this.BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -50.0f, 0.0f, 1.0f, 0.0f, 0.0f, -50.0f, 0.0f, 0.0f, 1.0f, 0.0f, -50.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        this.BT_NOT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    }

    public boolean onTouch(View r4_View, MotionEvent r5_MotionEvent) {
        if (r5_MotionEvent.getAction() == 0) {
            r4_View.getBackground().setColorFilter(new ColorMatrixColorFilter(this.BT_SELECTED));
            r4_View.setBackgroundDrawable(r4_View.getBackground());
        } else if (r5_MotionEvent.getAction() == 1) {
            r4_View.getBackground().setColorFilter(new ColorMatrixColorFilter(this.BT_NOT_SELECTED));
            r4_View.setBackgroundDrawable(r4_View.getBackground());
        }
        return false;
    }
}