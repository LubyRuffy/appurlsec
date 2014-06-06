package com.crashlytics.android.internal;

import android.os.Build;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// compiled from: SourceFile
enum br {
    X86_32,
    X86_64,
    ARM_UNKNOWN,
    PPC,
    PPC64,
    ARMV6,
    ARMV7,
    UNKNOWN,
    ARMV7S,
    ARM64;

    private static final Map<String, br> k;

    static {
        a = new br("X86_32", 0);
        b = new br("X86_64", 1);
        c = new br("ARM_UNKNOWN", 2);
        d = new br("PPC", 3);
        e = new br("PPC64", 4);
        f = new br("ARMV6", 5);
        g = new br("ARMV7", 6);
        h = new br("UNKNOWN", 7);
        i = new br("ARMV7S", 8);
        j = new br("ARM64", 9);
        br[] r0_brA = new br[10];
        r0_brA[0] = a;
        r0_brA[1] = b;
        r0_brA[2] = c;
        r0_brA[3] = d;
        r0_brA[4] = e;
        r0_brA[5] = f;
        r0_brA[6] = g;
        r0_brA[7] = h;
        r0_brA[8] = i;
        r0_brA[9] = j;
        l = r0_brA;
        Map r0_Map = new HashMap(4);
        k = r0_Map;
        r0_Map.put("armeabi-v7a", g);
        k.put("armeabi", f);
        k.put("x86", a);
    }

    static br a() {
        String r0_String = Build.CPU_ABI;
        if (TextUtils.isEmpty(r0_String)) {
            v.a().b().a(Crashlytics.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return h;
        } else {
            br r0_br = (br) k.get(r0_String.toLowerCase(Locale.US));
            return r0_br == null ? h : r0_br;
        }
    }
}