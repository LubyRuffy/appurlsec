package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View.OnTouchListener;

public class PopupMenuCompat {
    static final c a;

    static interface c {
        public OnTouchListener getDragToOpenListener(Object r1_Object);
    }

    static class a implements c {
        a() {
        }

        public OnTouchListener getDragToOpenListener(Object r2_Object) {
            return null;
        }
    }

    static class b extends a {
        b() {
        }

        public OnTouchListener getDragToOpenListener(Object r2_Object) {
            return h.getDragToOpenListener(r2_Object);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new b();
        } else {
            a = new a();
        }
    }

    private PopupMenuCompat() {
    }

    public static OnTouchListener getDragToOpenListener(Object r1_Object) {
        return a.getDragToOpenListener(r1_Object);
    }
}