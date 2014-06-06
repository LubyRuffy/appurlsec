package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

public class DrawableCompat {
    static final b a;

    static interface b {
        public boolean isAutoMirrored(Drawable r1_Drawable);

        public void jumpToCurrentState(Drawable r1_Drawable);

        public void setAutoMirrored(Drawable r1_Drawable, boolean r2z);
    }

    static class a implements b {
        a() {
        }

        public boolean isAutoMirrored(Drawable r2_Drawable) {
            return false;
        }

        public void jumpToCurrentState(Drawable r1_Drawable) {
        }

        public void setAutoMirrored(Drawable r1_Drawable, boolean r2z) {
        }
    }

    static class c extends a {
        c() {
        }

        public void jumpToCurrentState(Drawable r1_Drawable) {
            a.jumpToCurrentState(r1_Drawable);
        }
    }

    static class d extends c {
        d() {
        }

        public boolean isAutoMirrored(Drawable r2_Drawable) {
            return b.isAutoMirrored(r2_Drawable);
        }

        public void setAutoMirrored(Drawable r1_Drawable, boolean r2z) {
            b.setAutoMirrored(r1_Drawable, r2z);
        }
    }

    static {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 19) {
            a = new d();
        } else if (r0i >= 11) {
            a = new c();
        } else {
            a = new a();
        }
    }

    public static boolean isAutoMirrored(Drawable r1_Drawable) {
        return a.isAutoMirrored(r1_Drawable);
    }

    public static void jumpToCurrentState(Drawable r1_Drawable) {
        a.jumpToCurrentState(r1_Drawable);
    }

    public static void setAutoMirrored(Drawable r1_Drawable, boolean r2z) {
        a.setAutoMirrored(r1_Drawable, r2z);
    }
}