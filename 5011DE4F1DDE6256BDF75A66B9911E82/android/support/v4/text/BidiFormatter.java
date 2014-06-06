package android.support.v4.text;

import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.Locale;
import qsbk.app.R;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewHeader;

public final class BidiFormatter {
    private static TextDirectionHeuristicCompat a;
    private static final String b;
    private static final String c;
    private static final BidiFormatter d;
    private static final BidiFormatter e;
    private final boolean f;
    private final int g;
    private final TextDirectionHeuristicCompat h;


    public static final class Builder {
        private boolean a;
        private int b;
        private TextDirectionHeuristicCompat c;

        public Builder() {
            a(BidiFormatter.b(Locale.getDefault()));
        }

        public Builder(Locale r2_Locale) {
            a(BidiFormatter.b(r2_Locale));
        }

        public Builder(boolean r1z) {
            a(r1z);
        }

        private void a(boolean r2z) {
            this.a = r2z;
            this.c = a;
            this.b = 2;
        }

        private static BidiFormatter b(boolean r1z) {
            return r1z ? e : d;
        }

        public BidiFormatter build() {
            return (this.b == 2 && this.c == a) ? b(this.a) : new BidiFormatter(this.b, this.c, null);
        }

        public android.support.v4.text.BidiFormatter.Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat r1_TextDirectionHeuristicCompat) {
            this.c = r1_TextDirectionHeuristicCompat;
            return this;
        }

        public android.support.v4.text.BidiFormatter.Builder stereoReset(boolean r2z) {
            if (r2z) {
                this.b |= 2;
            } else {
                this.b &= -3;
            }
            return this;
        }
    }

    private static class a {
        private static final byte[] a;
        private final String b;
        private final boolean c;
        private final int d;
        private int e;
        private char f;

        static {
            a = new byte[1792];
            int r0i = 0;
            while (r0i < 1792) {
                a[r0i] = Character.getDirectionality(r0i);
                r0i++;
            }
        }

        a(String r2_String, boolean r3z) {
            this.b = r2_String;
            this.c = r3z;
            this.d = r2_String.length();
        }

        private static byte a_(char r1c) {
            return r1c < '\u0700' ? a[r1c] : Character.getDirectionality(r1c);
        }

        private byte e() {
            int r0i = this.e;
            while (this.e < this.d) {
                String r1_String = this.b;
                int r2i = this.e;
                this.e = r2i + 1;
                this.f = r1_String.charAt(r2i);
                if (this.f == '>') {
                    return (byte) 12;
                }
                if (this.f == '\"' || this.f == '\'') {
                    char r1c = this.f;
                    while (this.e < this.d) {
                        String r2_String = this.b;
                        int r3i = this.e;
                        this.e = r3i + 1;
                        char r2c = r2_String.charAt(r3i);
                        this.f = r2c;
                        if (r2c != r1c) {
                        }
                    }
                }
            }
            this.e = r0i;
            this.f = '<';
            return (byte) 13;
        }

        private byte f() {
            int r0i = this.e;
            while (this.e > 0) {
                String r1_String = this.b;
                int r2i = this.e - 1;
                this.e = r2i;
                this.f = r1_String.charAt(r2i);
                if (this.f == '<') {
                    return (byte) 12;
                }
                if (this.f == '>') {
                    break;
                } else if (this.f == '\"' || this.f == '\'') {
                    char r1c = this.f;
                    while (this.e > 0) {
                        String r2_String = this.b;
                        int r3i = this.e - 1;
                        this.e = r3i;
                        char r2c = r2_String.charAt(r3i);
                        this.f = r2c;
                        if (r2c != r1c) {
                        }
                    }
                }
            }
            this.e = r0i;
            this.f = '>';
            return (byte) 13;
        }

        private byte g() {
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.text.BidiFormatter.a.g():byte");
            /* JADX: method processing error */
/*
            Error: java.lang.StackOverflowError: Deep code hierarchy
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:29)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:16)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:23)
	at jadx.api.Decompiler.processClass(Decompiler.java:185)
	at jadx.api.JavaClass.decompile(JavaClass.java:46)
	at jadx.api.Decompiler$1.run(Decompiler.java:119)
	at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:895)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:918)
	at java.lang.Thread.run(Thread.java:695)
*/
            /*
            private byte g() {
                r3_this = this;
            L_0x0000:
                r0 = r3.e;
                r1 = r3.d;
                if (r0 >= r1) goto L_0x0018;
            L_0x0006:
                r0 = r3.b;
                r1 = r3.e;
                r2 = r1 + 1;
                r3.e = r2;
                r0 = r0.charAt(r1);
                r3.f = r0;
                r1 = 59;
                if (r0 != r1) goto L_0x0000;
            L_0x0018:
                r0 = 12;
                return r0;
            }
            */
        }

        private byte h() {
            int r0i = this.e;
            while (this.e > 0) {
                String r1_String = this.b;
                int r2i = this.e - 1;
                this.e = r2i;
                this.f = r1_String.charAt(r2i);
                if (this.f == '&') {
                    return (byte) 12;
                }
                if (this.f == ';') {
                    break;
                }
            }
            this.e = r0i;
            this.f = ';';
            return (byte) 13;
        }

        int a_() {
            this.e = 0;
            int r0i = 0;
            int r3i = 0;
            int r2i = 0;
            while (this.e < this.d && r0i == 0) {
                switch (c()) {
                    case XListViewHeader.STATE_NORMAL:
                        if (r2i == 0) {
                            return -1;
                        }
                        r0i = r2i;
                        break;
                    case XListViewHeader.STATE_READY:
                    case XListViewHeader.STATE_REFRESHING:
                        if (r2i == 0) {
                            return 1;
                        }
                        r0i = r2i;
                        break;
                    case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                        break;
                    case REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
                    case NearbySelectView.TIME_15MIN:
                        r2i++;
                        r3i = -1;
                        break;
                    case Base64.URL_SAFE:
                    case R.styleable.ActionBar_progressBarPadding:
                        r2i++;
                        r3i = 1;
                        break;
                    case R.styleable.ActionBar_itemPadding:
                        r2i--;
                        r3i = 0;
                        break;
                    default:
                        r0i = r2i;
                        break;
                }
            }
            if (r0i == 0) {
                return 0;
            }
            if (r3i != 0) {
                return r3i;
            }
            while (this.e > 0) {
                switch (d()) {
                    case REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
                    case NearbySelectView.TIME_15MIN:
                        if (r0i == r2i) {
                            return -1;
                        }
                        r2i--;
                        break;
                    case Base64.URL_SAFE:
                    case R.styleable.ActionBar_progressBarPadding:
                        if (r0i == r2i) {
                            return 1;
                        }
                        r2i--;
                        break;
                    case R.styleable.ActionBar_itemPadding:
                        r2i++;
                        break;
                }
            }
            return 0;
        }

        int b() {
            this.e = this.d;
            int r0i = 0;
            int r2i = 0;
            while (this.e > 0) {
                switch (d()) {
                    case XListViewHeader.STATE_NORMAL:
                        if (r2i == 0) {
                            return -1;
                        }
                        if (r0i == 0) {
                            r0i = r2i;
                        }
                        break;
                    case XListViewHeader.STATE_READY:
                    case XListViewHeader.STATE_REFRESHING:
                        if (r2i == 0) {
                            return 1;
                        }
                        if (r0i == 0) {
                            r0i = r2i;
                        }
                        break;
                    case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                        break;
                    case REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
                    case NearbySelectView.TIME_15MIN:
                        if (r0i == r2i) {
                            return -1;
                        }
                        r2i--;
                        break;
                    case Base64.URL_SAFE:
                    case R.styleable.ActionBar_progressBarPadding:
                        if (r0i == r2i) {
                            return 1;
                        }
                        r2i--;
                        break;
                    case R.styleable.ActionBar_itemPadding:
                        r2i++;
                        break;
                    default:
                        if (r0i == 0) {
                            r0i = r2i;
                        }
                        break;
                }
            }
            return 0;
        }

        byte c() {
            this.f = this.b.charAt(this.e);
            if (Character.isHighSurrogate(this.f)) {
                int r0i = Character.codePointAt(this.b, this.e);
                this.e += Character.charCount(r0i);
                return Character.getDirectionality(r0i);
            } else {
                this.e++;
                byte r0b = a(this.f);
                if (!(this.c)) {
                    return r0b;
                }
                if (this.f == '<') {
                    return e();
                }
                if (this.f == '&') {
                    return g();
                }
                return r0b;
            }
        }

        byte d() {
            this.f = this.b.charAt(this.e - 1);
            if (Character.isLowSurrogate(this.f)) {
                int r0i = Character.codePointBefore(this.b, this.e);
                this.e -= Character.charCount(r0i);
                return Character.getDirectionality(r0i);
            } else {
                this.e--;
                byte r0b = a(this.f);
                if (!(this.c)) {
                    return r0b;
                }
                if (this.f == '>') {
                    return f();
                }
                if (this.f == ';') {
                    return h();
                }
                return r0b;
            }
        }
    }

    static {
        a = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        b = Character.toString('\u200e');
        c = Character.toString('\u200f');
        d = new BidiFormatter(false, 2, a);
        e = new BidiFormatter(true, 2, a);
    }

    private BidiFormatter(boolean r1z, int r2i, TextDirectionHeuristicCompat r3_TextDirectionHeuristicCompat) {
        this.f = r1z;
        this.g = r2i;
        this.h = r3_TextDirectionHeuristicCompat;
    }

    private static int a(String r2_String) {
        return new a(r2_String, false).b();
    }

    private String a(String r4_String, TextDirectionHeuristicCompat r5_TextDirectionHeuristicCompat) {
        boolean r0z = r5_TextDirectionHeuristicCompat.isRtl((CharSequence)r4_String, 0, r4_String.length());
        if (!this.f) {
            if (r0z || a(r4_String) == 1) {
                return b;
            }
        }
        if (this.f) {
            if ((!r0z) || a(r4_String) == -1) {
                return c;
            }
        }
        return RContactStorage.PRIMARY_KEY;
    }

    private static int b(String r2_String) {
        return new a(r2_String, false).a();
    }

    private String b(String r4_String, TextDirectionHeuristicCompat r5_TextDirectionHeuristicCompat) {
        boolean r0z = r5_TextDirectionHeuristicCompat.isRtl((CharSequence)r4_String, 0, r4_String.length());
        if (!this.f) {
            if (r0z || b(r4_String) == 1) {
                return b;
            }
        }
        if (this.f) {
            if ((!r0z) || b(r4_String) == -1) {
                return c;
            }
        }
        return RContactStorage.PRIMARY_KEY;
    }

    private static boolean b(Locale r2_Locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(r2_Locale) == 1;
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(Locale r1_Locale) {
        return new Builder(r1_Locale).build();
    }

    public static BidiFormatter getInstance(boolean r1z) {
        return new Builder(r1z).build();
    }

    public boolean getStereoReset() {
        return (this.g & 2) != 0;
    }

    public boolean isRtl(String r4_String) {
        return this.h.isRtl((CharSequence)r4_String, 0, r4_String.length());
    }

    public boolean isRtlContext() {
        return this.f;
    }

    public String unicodeWrap(String r3_String) {
        return unicodeWrap(r3_String, this.h, true);
    }

    public String unicodeWrap(String r2_String, TextDirectionHeuristicCompat r3_TextDirectionHeuristicCompat) {
        return unicodeWrap(r2_String, r3_TextDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String r4_String, TextDirectionHeuristicCompat r5_TextDirectionHeuristicCompat, boolean r6z) {
        boolean r1z = r5_TextDirectionHeuristicCompat.isRtl((CharSequence)r4_String, 0, r4_String.length());
        StringBuilder r2_StringBuilder = new StringBuilder();
        if (getStereoReset() && r6z) {
            r2_StringBuilder.append(b(r4_String, r1z ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
            if (r1z == this.f) {
                r2_StringBuilder.append(r4_String);
            } else {
                r2_StringBuilder.append(r1z ? '\u202b' : '\u202a');
                r2_StringBuilder.append(r4_String);
                r2_StringBuilder.append('\u202c');
            }
            if (r6z) {
                return r2_StringBuilder.toString();
            }
            r2_StringBuilder.append(a(r4_String, r1z ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
            return r2_StringBuilder.toString();
        } else {
            if (r1z == this.f) {
                if (r1z) {
                }
                r2_StringBuilder.append(r1z ? '\u202b' : '\u202a');
                r2_StringBuilder.append(r4_String);
                r2_StringBuilder.append('\u202c');
            } else {
                r2_StringBuilder.append(r4_String);
            }
            if (!r6z) {
                if (r1z) {
                }
                r2_StringBuilder.append(a(r4_String, r1z ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
            }
            return r2_StringBuilder.toString();
        }
    }

    public String unicodeWrap(String r2_String, boolean r3z) {
        return unicodeWrap(r2_String, this.h, r3z);
    }
}