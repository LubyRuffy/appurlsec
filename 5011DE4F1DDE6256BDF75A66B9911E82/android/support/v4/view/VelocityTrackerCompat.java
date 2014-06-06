package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.VelocityTracker;

public class VelocityTrackerCompat {
    static final c a;

    static interface c {
        public float getXVelocity(VelocityTracker r1_VelocityTracker, int r2i);

        public float getYVelocity(VelocityTracker r1_VelocityTracker, int r2i);
    }

    static class a implements c {
        a() {
        }

        public float getXVelocity(VelocityTracker r2_VelocityTracker, int r3i) {
            return r2_VelocityTracker.getXVelocity();
        }

        public float getYVelocity(VelocityTracker r2_VelocityTracker, int r3i) {
            return r2_VelocityTracker.getYVelocity();
        }
    }

    static class b implements c {
        b() {
        }

        public float getXVelocity(VelocityTracker r2_VelocityTracker, int r3i) {
            return q.getXVelocity(r2_VelocityTracker, r3i);
        }

        public float getYVelocity(VelocityTracker r2_VelocityTracker, int r3i) {
            return q.getYVelocity(r2_VelocityTracker, r3i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public static float getXVelocity(VelocityTracker r1_VelocityTracker, int r2i) {
        return a.getXVelocity(r1_VelocityTracker, r2i);
    }

    public static float getYVelocity(VelocityTracker r1_VelocityTracker, int r2i) {
        return a.getYVelocity(r1_VelocityTracker, r2i);
    }
}