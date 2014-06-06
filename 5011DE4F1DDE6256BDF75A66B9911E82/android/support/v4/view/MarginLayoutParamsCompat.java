package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewGroup.MarginLayoutParams;

public class MarginLayoutParamsCompat {
    static final a a;

    static interface a {
        public int getLayoutDirection(MarginLayoutParams r1_MarginLayoutParams);

        public int getMarginEnd(MarginLayoutParams r1_MarginLayoutParams);

        public int getMarginStart(MarginLayoutParams r1_MarginLayoutParams);

        public boolean isMarginRelative(MarginLayoutParams r1_MarginLayoutParams);

        public void resolveLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i);

        public void setLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i);

        public void setMarginEnd(MarginLayoutParams r1_MarginLayoutParams, int r2i);

        public void setMarginStart(MarginLayoutParams r1_MarginLayoutParams, int r2i);
    }

    static class b implements a {
        b() {
        }

        public int getLayoutDirection(MarginLayoutParams r2_MarginLayoutParams) {
            return 0;
        }

        public int getMarginEnd(MarginLayoutParams r2_MarginLayoutParams) {
            return r2_MarginLayoutParams.rightMargin;
        }

        public int getMarginStart(MarginLayoutParams r2_MarginLayoutParams) {
            return r2_MarginLayoutParams.leftMargin;
        }

        public boolean isMarginRelative(MarginLayoutParams r2_MarginLayoutParams) {
            return false;
        }

        public void resolveLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
        }

        public void setLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
        }

        public void setMarginEnd(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
            r1_MarginLayoutParams.rightMargin = r2i;
        }

        public void setMarginStart(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
            r1_MarginLayoutParams.leftMargin = r2i;
        }
    }

    static class c implements a {
        c() {
        }

        public int getLayoutDirection(MarginLayoutParams r2_MarginLayoutParams) {
            return h.getLayoutDirection(r2_MarginLayoutParams);
        }

        public int getMarginEnd(MarginLayoutParams r2_MarginLayoutParams) {
            return h.getMarginEnd(r2_MarginLayoutParams);
        }

        public int getMarginStart(MarginLayoutParams r2_MarginLayoutParams) {
            return h.getMarginStart(r2_MarginLayoutParams);
        }

        public boolean isMarginRelative(MarginLayoutParams r2_MarginLayoutParams) {
            return h.isMarginRelative(r2_MarginLayoutParams);
        }

        public void resolveLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
            h.resolveLayoutDirection(r1_MarginLayoutParams, r2i);
        }

        public void setLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
            h.setLayoutDirection(r1_MarginLayoutParams, r2i);
        }

        public void setMarginEnd(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
            h.setMarginEnd(r1_MarginLayoutParams, r2i);
        }

        public void setMarginStart(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
            h.setMarginStart(r1_MarginLayoutParams, r2i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            a = new c();
        } else {
            a = new b();
        }
    }

    public static int getLayoutDirection(MarginLayoutParams r1_MarginLayoutParams) {
        return a.getLayoutDirection(r1_MarginLayoutParams);
    }

    public static int getMarginEnd(MarginLayoutParams r1_MarginLayoutParams) {
        return a.getMarginEnd(r1_MarginLayoutParams);
    }

    public static int getMarginStart(MarginLayoutParams r1_MarginLayoutParams) {
        return a.getMarginStart(r1_MarginLayoutParams);
    }

    public static boolean isMarginRelative(MarginLayoutParams r1_MarginLayoutParams) {
        return a.isMarginRelative(r1_MarginLayoutParams);
    }

    public static void resolveLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
        a.resolveLayoutDirection(r1_MarginLayoutParams, r2i);
    }

    public static void setLayoutDirection(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
        a.setLayoutDirection(r1_MarginLayoutParams, r2i);
    }

    public static void setMarginEnd(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
        a.setMarginEnd(r1_MarginLayoutParams, r2i);
    }

    public static void setMarginStart(MarginLayoutParams r1_MarginLayoutParams, int r2i) {
        a.setMarginStart(r1_MarginLayoutParams, r2i);
    }
}