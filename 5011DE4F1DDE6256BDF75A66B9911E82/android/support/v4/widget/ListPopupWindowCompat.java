package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnTouchListener;

public class ListPopupWindowCompat {
    static final c a;

    static interface c {
        public OnTouchListener createDragToOpenListener(Object r1_Object, View r2_View);
    }

    static class a implements c {
        a() {
        }

        public OnTouchListener createDragToOpenListener(Object r2_Object, View r3_View) {
            return null;
        }
    }

    static class b extends a {
        b() {
        }

        public OnTouchListener createDragToOpenListener(Object r2_Object, View r3_View) {
            return g.createDragToOpenListener(r2_Object, r3_View);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new b();
        } else {
            a = new a();
        }
    }

    private ListPopupWindowCompat() {
    }

    public static OnTouchListener createDragToOpenListener(Object r1_Object, View r2_View) {
        return a.createDragToOpenListener(r1_Object, r2_View);
    }
}