package android.support.v4.text;

import java.nio.CharBuffer;
import java.util.Locale;
import qsbk.app.R;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewHeader;

public class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE;
    public static final TextDirectionHeuristicCompat LTR;
    public static final TextDirectionHeuristicCompat RTL;


    private static interface c {
        public int checkRtl(CharSequence r1_CharSequence, int r2i, int r3i);
    }

    private static class a implements c {
        public static final a INSTANCE_LTR;
        public static final a INSTANCE_RTL;
        private final boolean a;

        static {
            INSTANCE_RTL = new a(true);
            INSTANCE_LTR = new a(false);
        }

        private a(boolean r1z) {
            this.a = r1z;
        }

        public int checkRtl(CharSequence r6_CharSequence, int r7i, int r8i) {
            int r3i = r7i + r8i;
            int r0i = 0;
            while (r7i < r3i) {
                switch (TextDirectionHeuristicsCompat.c(Character.getDirectionality(r6_CharSequence.charAt(r7i)))) {
                    case XListViewHeader.STATE_NORMAL:
                        if (this.a) {
                            return 0;
                        }
                        r0i = 1;
                        break;
                    case XListViewHeader.STATE_READY:
                        if (!(this.a)) {
                            return 1;
                        }
                        r0i = 1;
                        break;
                }
                r7i++;
            }
            if (r0i == 0) {
                return XListViewHeader.STATE_REFRESHING;
            }
            if (this.a) {
                return 1;
            }
            return 0;
        }
    }

    private static class b implements c {
        public static final b INSTANCE;

        static {
            INSTANCE = new b();
        }

        private b() {
        }

        public int checkRtl(CharSequence r4_CharSequence, int r5i, int r6i) {
            int r2i = r5i + r6i;
            int r0i = 2;
            while (r5i < r2i && r0i == 2) {
                r0i = TextDirectionHeuristicsCompat.d(Character.getDirectionality(r4_CharSequence.charAt(r5i)));
                r5i++;
            }
            return r0i;
        }
    }

    private static abstract class d implements TextDirectionHeuristicCompat {
        private final c a;

        public d(c r1_c) {
            this.a = r1_c;
        }

        private boolean a(CharSequence r2_CharSequence, int r3i, int r4i) {
            switch (this.a.checkRtl(r2_CharSequence, r3i, r4i)) {
                case XListViewHeader.STATE_NORMAL:
                    return true;
                case XListViewHeader.STATE_READY:
                    return false;
            }
            return a();
        }

        protected abstract boolean a();

        public boolean isRtl(CharSequence r2_CharSequence, int r3i, int r4i) {
            if (r2_CharSequence == null || r3i < 0 || r4i < 0 || r2_CharSequence.length() - r4i < r3i) {
                throw new IllegalArgumentException();
            } else {
                if (this.a == null) {
                    return a();
                }
                return a(r2_CharSequence, r3i, r4i);
            }
        }

        public boolean isRtl(char[] r2_charA, int r3i, int r4i) {
            return isRtl(CharBuffer.wrap(r2_charA), r3i, r4i);
        }
    }

    private static class e extends d {
        private final boolean a;

        private e(c r1_c, boolean r2z) {
            super(r1_c);
            this.a = r2z;
        }

        protected boolean a() {
            return this.a;
        }
    }

    private static class f extends d {
        public static final f INSTANCE;

        static {
            INSTANCE = new f();
        }

        public f() {
            super(null);
        }

        protected boolean a() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }
    }

    static {
        LTR = new e(false, null);
        RTL = new e(true, null);
        FIRSTSTRONG_LTR = new e(false, null);
        FIRSTSTRONG_RTL = new e(true, null);
        ANYRTL_LTR = new e(false, null);
        LOCALE = f.INSTANCE;
    }

    private static int c(int r1i) {
        switch (r1i) {
            case XListViewHeader.STATE_NORMAL:
                return 1;
            case XListViewHeader.STATE_READY:
            case XListViewHeader.STATE_REFRESHING:
                return 0;
        }
        return XListViewHeader.STATE_REFRESHING;
    }

    private static int d(int r1i) {
        switch (r1i) {
            case XListViewHeader.STATE_NORMAL:
            case REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
            case NearbySelectView.TIME_15MIN:
                return 1;
            case XListViewHeader.STATE_READY:
            case XListViewHeader.STATE_REFRESHING:
            case Base64.URL_SAFE:
            case R.styleable.ActionBar_progressBarPadding:
                return 0;
        }
        return XListViewHeader.STATE_REFRESHING;
    }
}