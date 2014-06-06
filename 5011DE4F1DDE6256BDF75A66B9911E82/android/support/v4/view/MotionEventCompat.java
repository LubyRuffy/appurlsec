package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;

public class MotionEventCompat {
    public static final int ACTION_HOVER_ENTER = 9;
    public static final int ACTION_HOVER_EXIT = 10;
    public static final int ACTION_HOVER_MOVE = 7;
    public static final int ACTION_MASK = 255;
    public static final int ACTION_POINTER_DOWN = 5;
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int ACTION_POINTER_UP = 6;
    public static final int ACTION_SCROLL = 8;
    static final c a;

    static interface c {
        public int findPointerIndex(MotionEvent r1_MotionEvent, int r2i);

        public int getPointerCount(MotionEvent r1_MotionEvent);

        public int getPointerId(MotionEvent r1_MotionEvent, int r2i);

        public float getX(MotionEvent r1_MotionEvent, int r2i);

        public float getY(MotionEvent r1_MotionEvent, int r2i);
    }

    static class a implements c {
        a() {
        }

        public int findPointerIndex(MotionEvent r2_MotionEvent, int r3i) {
            return r3i == 0 ? 0 : -1;
        }

        public int getPointerCount(MotionEvent r2_MotionEvent) {
            return 1;
        }

        public int getPointerId(MotionEvent r3_MotionEvent, int r4i) {
            if (r4i == 0) {
                return 0;
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float getX(MotionEvent r3_MotionEvent, int r4i) {
            if (r4i == 0) {
                return r3_MotionEvent.getX();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float getY(MotionEvent r3_MotionEvent, int r4i) {
            if (r4i == 0) {
                return r3_MotionEvent.getY();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }
    }

    static class b implements c {
        b() {
        }

        public int findPointerIndex(MotionEvent r2_MotionEvent, int r3i) {
            return l.findPointerIndex(r2_MotionEvent, r3i);
        }

        public int getPointerCount(MotionEvent r2_MotionEvent) {
            return l.getPointerCount(r2_MotionEvent);
        }

        public int getPointerId(MotionEvent r2_MotionEvent, int r3i) {
            return l.getPointerId(r2_MotionEvent, r3i);
        }

        public float getX(MotionEvent r2_MotionEvent, int r3i) {
            return l.getX(r2_MotionEvent, r3i);
        }

        public float getY(MotionEvent r2_MotionEvent, int r3i) {
            return l.getY(r2_MotionEvent, r3i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 5) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public static int findPointerIndex(MotionEvent r1_MotionEvent, int r2i) {
        return a.findPointerIndex(r1_MotionEvent, r2i);
    }

    public static int getActionIndex(MotionEvent r2_MotionEvent) {
        return (r2_MotionEvent.getAction() & 65280) >> 8;
    }

    public static int getActionMasked(MotionEvent r1_MotionEvent) {
        return r1_MotionEvent.getAction() & 255;
    }

    public static int getPointerCount(MotionEvent r1_MotionEvent) {
        return a.getPointerCount(r1_MotionEvent);
    }

    public static int getPointerId(MotionEvent r1_MotionEvent, int r2i) {
        return a.getPointerId(r1_MotionEvent, r2i);
    }

    public static float getX(MotionEvent r1_MotionEvent, int r2i) {
        return a.getX(r1_MotionEvent, r2i);
    }

    public static float getY(MotionEvent r1_MotionEvent, int r2i) {
        return a.getY(r1_MotionEvent, r2i);
    }
}