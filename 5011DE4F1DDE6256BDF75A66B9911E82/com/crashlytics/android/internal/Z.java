package com.crashlytics.android.internal;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public abstract class Z {
    private static String a;
    private static final Pattern b;
    private final String c;
    private final av d;
    private final ax e;
    private final String f;

    static {
        a = new StringBuilder("Crashlytics Android SDK/").append(v.a().getVersion()).toString();
        b = Pattern.compile("http(s?)://[^\\/]+", XListViewHeader.STATE_REFRESHING);
    }

    public Z(String r3_String, String r4_String, av r5_av, ax r6_ax) {
        if (r4_String == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (r5_av == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.f = r3_String;
            if (!ab.e(this.f)) {
                r4_String = b.matcher(r4_String).replaceFirst(this.f);
            }
            this.c = r4_String;
            this.d = r5_av;
            this.e = r6_ax;
        }
    }

    protected final ay a(Map<String, String> r4_Map_String__String) {
        return this.d.a(this.e, this.c, r4_Map_String__String).a(false).a(10000).a("User-Agent", a).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "bca6990fc3c15a8105800c0673517a4b579634a1");
    }

    protected final String a() {
        return this.c;
    }

    protected final ay b() {
        return a(Collections.emptyMap());
    }
}