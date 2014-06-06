package android.support.v4.view;

import android.os.Build.VERSION;

public class ScaleGestureDetectorCompat {
    static final c a;


    static interface c {
        public boolean isQuickScaleEnabled(Object r1_Object);

        public void setQuickScaleEnabled(Object r1_Object, boolean r2z);
    }

    private static class a implements c {
        private a() {
        }

        public boolean isQuickScaleEnabled(Object r2_Object) {
            return false;
        }

        public void setQuickScaleEnabled(Object r1_Object, boolean r2z) {
        }
    }

    private static class b implements c {
        private b() {
        }

        public boolean isQuickScaleEnabled(Object r2_Object) {
            return p.isQuickScaleEnabled(r2_Object);
        }

        public void setQuickScaleEnabled(Object r1_Object, boolean r2z) {
            p.setQuickScaleEnabled(r1_Object, r2z);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new b();
        } else {
            a = new a();
        }
    }

    private ScaleGestureDetectorCompat() {
    }

    public static boolean isQuickScaleEnabled(Object r1_Object) {
        return a.isQuickScaleEnabled(r1_Object);
    }

    public static void setQuickScaleEnabled(Object r1_Object, boolean r2z) {
        a.setQuickScaleEnabled(r1_Object, r2z);
    }
}