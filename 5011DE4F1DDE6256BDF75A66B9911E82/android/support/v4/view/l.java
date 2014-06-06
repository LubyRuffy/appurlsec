package android.support.v4.view;

import android.view.MotionEvent;

// compiled from: MotionEventCompatEclair.java
class l {
    public static int findPointerIndex(MotionEvent r1_MotionEvent, int r2i) {
        return r1_MotionEvent.findPointerIndex(r2i);
    }

    public static int getPointerCount(MotionEvent r1_MotionEvent) {
        return r1_MotionEvent.getPointerCount();
    }

    public static int getPointerId(MotionEvent r1_MotionEvent, int r2i) {
        return r1_MotionEvent.getPointerId(r2i);
    }

    public static float getX(MotionEvent r1_MotionEvent, int r2i) {
        return r1_MotionEvent.getX(r2i);
    }

    public static float getY(MotionEvent r1_MotionEvent, int r2i) {
        return r1_MotionEvent.getY(r2i);
    }
}