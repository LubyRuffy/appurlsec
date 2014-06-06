package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.Gravity;

public class GravityCompat {
    public static final int END = 8388613;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;
    static final a a;

    static interface a {
        public void apply(int r1i, int r2i, int r3i, Rect r4_Rect, int r5i, int r6i, Rect r7_Rect, int r8i);

        public void apply(int r1i, int r2i, int r3i, Rect r4_Rect, Rect r5_Rect, int r6i);

        public void applyDisplay(int r1i, Rect r2_Rect, Rect r3_Rect, int r4i);

        public int getAbsoluteGravity(int r1i, int r2i);
    }

    static class b implements a {
        b() {
        }

        public void apply(int r1i, int r2i, int r3i, Rect r4_Rect, int r5i, int r6i, Rect r7_Rect, int r8i) {
            Gravity.apply(r1i, r2i, r3i, r4_Rect, r5i, r6i, r7_Rect);
        }

        public void apply(int r1i, int r2i, int r3i, Rect r4_Rect, Rect r5_Rect, int r6i) {
            Gravity.apply(r1i, r2i, r3i, r4_Rect, r5_Rect);
        }

        public void applyDisplay(int r1i, Rect r2_Rect, Rect r3_Rect, int r4i) {
            Gravity.applyDisplay(r1i, r2_Rect, r3_Rect);
        }

        public int getAbsoluteGravity(int r2i, int r3i) {
            return -8388609 & r2i;
        }
    }

    static class c implements a {
        c() {
        }

        public void apply(int r1i, int r2i, int r3i, Rect r4_Rect, int r5i, int r6i, Rect r7_Rect, int r8i) {
            e.apply(r1i, r2i, r3i, r4_Rect, r5i, r6i, r7_Rect, r8i);
        }

        public void apply(int r1i, int r2i, int r3i, Rect r4_Rect, Rect r5_Rect, int r6i) {
            e.apply(r1i, r2i, r3i, r4_Rect, r5_Rect, r6i);
        }

        public void applyDisplay(int r1i, Rect r2_Rect, Rect r3_Rect, int r4i) {
            e.applyDisplay(r1i, r2_Rect, r3_Rect, r4i);
        }

        public int getAbsoluteGravity(int r2i, int r3i) {
            return e.getAbsoluteGravity(r2i, r3i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            a = new c();
        } else {
            a = new b();
        }
    }

    public static void apply(int r9i, int r10i, int r11i, Rect r12_Rect, int r13i, int r14i, Rect r15_Rect, int r16i) {
        a.apply(r9i, r10i, r11i, r12_Rect, r13i, r14i, r15_Rect, r16i);
    }

    public static void apply(int r7i, int r8i, int r9i, Rect r10_Rect, Rect r11_Rect, int r12i) {
        a.apply(r7i, r8i, r9i, r10_Rect, r11_Rect, r12i);
    }

    public static void applyDisplay(int r1i, Rect r2_Rect, Rect r3_Rect, int r4i) {
        a.applyDisplay(r1i, r2_Rect, r3_Rect, r4i);
    }

    public static int getAbsoluteGravity(int r1i, int r2i) {
        return a.getAbsoluteGravity(r1i, r2i);
    }
}