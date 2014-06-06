package qsbk.app.widget.imageview;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class FlingListener extends SimpleOnGestureListener {
    private float velocityX;
    private float velocityY;

    public float getVelocityX() {
        return this.velocityX;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public boolean onFling(MotionEvent r2_MotionEvent, MotionEvent r3_MotionEvent, float r4f, float r5f) {
        this.velocityX = r4f;
        this.velocityY = r5f;
        return true;
    }
}