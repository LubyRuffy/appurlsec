package qsbk.app.widget.imageview;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

// compiled from: GestureImageViewTouchListener.java
class e extends SimpleOnGestureListener {
    final /* synthetic */ GestureImageView a;
    final /* synthetic */ GestureImageViewTouchListener b;

    e(GestureImageViewTouchListener r1_GestureImageViewTouchListener, GestureImageView r2_GestureImageView) {
        this.b = r1_GestureImageViewTouchListener;
        this.a = r2_GestureImageView;
    }

    public boolean onDoubleTap(MotionEvent r2_MotionEvent) {
        this.b.startZoom(r2_MotionEvent);
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent r3_MotionEvent) {
        if (this.b.inZoom || this.b.onClickListener == null) {
            return false;
        }
        this.b.onClickListener.onClick(this.a);
        return true;
    }
}