package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewConfiguration;

public class ViewConfigurationCompat {
    static final c a;

    static interface c {
        public int getScaledPagingTouchSlop(ViewConfiguration r1_ViewConfiguration);
    }

    static class a implements c {
        a() {
        }

        public int getScaledPagingTouchSlop(ViewConfiguration r2_ViewConfiguration) {
            return r2_ViewConfiguration.getScaledTouchSlop();
        }
    }

    static class b implements c {
        b() {
        }

        public int getScaledPagingTouchSlop(ViewConfiguration r2_ViewConfiguration) {
            return x.getScaledPagingTouchSlop(r2_ViewConfiguration);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public static int getScaledPagingTouchSlop(ViewConfiguration r1_ViewConfiguration) {
        return a.getScaledPagingTouchSlop(r1_ViewConfiguration);
    }
}