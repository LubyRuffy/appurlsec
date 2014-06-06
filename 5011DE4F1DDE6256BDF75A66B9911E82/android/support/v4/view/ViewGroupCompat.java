package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class ViewGroupCompat {
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;
    static final c a;

    static interface c {
        public int getLayoutMode(ViewGroup r1_ViewGroup);

        public boolean onRequestSendAccessibilityEvent(ViewGroup r1_ViewGroup, View r2_View, AccessibilityEvent r3_AccessibilityEvent);

        public void setLayoutMode(ViewGroup r1_ViewGroup, int r2i);

        public void setMotionEventSplittingEnabled(ViewGroup r1_ViewGroup, boolean r2z);
    }

    static class e implements c {
        e() {
        }

        public int getLayoutMode(ViewGroup r2_ViewGroup) {
            return LAYOUT_MODE_CLIP_BOUNDS;
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup r2_ViewGroup, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
            return true;
        }

        public void setLayoutMode(ViewGroup r1_ViewGroup, int r2i) {
        }

        public void setMotionEventSplittingEnabled(ViewGroup r1_ViewGroup, boolean r2z) {
        }
    }

    static class a extends e {
        a() {
        }

        public void setMotionEventSplittingEnabled(ViewGroup r1_ViewGroup, boolean r2z) {
            y.setMotionEventSplittingEnabled(r1_ViewGroup, r2z);
        }
    }

    static class b extends a {
        b() {
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup r2_ViewGroup, View r3_View, AccessibilityEvent r4_AccessibilityEvent) {
            return z.onRequestSendAccessibilityEvent(r2_ViewGroup, r3_View, r4_AccessibilityEvent);
        }
    }

    static class d extends b {
        d() {
        }

        public int getLayoutMode(ViewGroup r2_ViewGroup) {
            return aa.getLayoutMode(r2_ViewGroup);
        }

        public void setLayoutMode(ViewGroup r1_ViewGroup, int r2i) {
            aa.setLayoutMode(r1_ViewGroup, r2i);
        }
    }

    static {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 18) {
            a = new d();
        } else if (r0i >= 14) {
            a = new b();
        } else if (r0i >= 11) {
            a = new a();
        } else {
            a = new e();
        }
    }

    private ViewGroupCompat() {
    }

    public static int getLayoutMode(ViewGroup r1_ViewGroup) {
        return a.getLayoutMode(r1_ViewGroup);
    }

    public static boolean onRequestSendAccessibilityEvent(ViewGroup r1_ViewGroup, View r2_View, AccessibilityEvent r3_AccessibilityEvent) {
        return a.onRequestSendAccessibilityEvent(r1_ViewGroup, r2_View, r3_AccessibilityEvent);
    }

    public static void setLayoutMode(ViewGroup r1_ViewGroup, int r2i) {
        a.setLayoutMode(r1_ViewGroup, r2i);
    }

    public static void setMotionEventSplittingEnabled(ViewGroup r1_ViewGroup, boolean r2z) {
        a.setMotionEventSplittingEnabled(r1_ViewGroup, r2z);
    }
}