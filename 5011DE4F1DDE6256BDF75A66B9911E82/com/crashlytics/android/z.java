package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import com.qiubai.library.adview.util.AdViewUtil;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// compiled from: SourceFile
class z {
    private final File a;
    private final Map<String, String> b;

    public z(File r2_File) {
        this(r2_File, Collections.emptyMap());
    }

    public z(File r5_File, Map<String, String> r6_Map_String__String) {
        this.a = r5_File;
        this.b = new HashMap(r6_Map_String__String);
        if (this.a.length() == 0) {
            this.b.putAll(ab.a);
        }
    }

    public boolean a() {
        v.a().b().a(Crashlytics.TAG, new StringBuilder("Removing report at ").append(this.a.getPath()).toString());
        return this.a.delete();
    }

    public String b() {
        return d().getName();
    }

    public String c() {
        String r0_String = b();
        return r0_String.substring(0, r0_String.lastIndexOf(AdViewUtil.NETWORK_TYPE_MOBWIN));
    }

    public File d() {
        return this.a;
    }

    public Map<String, String> e() {
        return Collections.unmodifiableMap(this.b);
    }
}