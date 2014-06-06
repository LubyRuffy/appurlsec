package android.support.v4.text;

import android.os.Build.VERSION;

public class ICUCompat {
    private static final a a;

    static interface a {
        public String addLikelySubtags(String r1_String);

        public String getScript(String r1_String);
    }

    static class b implements a {
        b() {
        }

        public String addLikelySubtags(String r1_String) {
            return r1_String;
        }

        public String getScript(String r2_String) {
            return null;
        }
    }

    static class c implements a {
        c() {
        }

        public String addLikelySubtags(String r2_String) {
            return a.addLikelySubtags(r2_String);
        }

        public String getScript(String r2_String) {
            return a.getScript(r2_String);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            a = new c();
        } else {
            a = new b();
        }
    }

    public static String addLikelySubtags(String r1_String) {
        return a.addLikelySubtags(r1_String);
    }

    public static String getScript(String r1_String) {
        return a.getScript(r1_String);
    }
}