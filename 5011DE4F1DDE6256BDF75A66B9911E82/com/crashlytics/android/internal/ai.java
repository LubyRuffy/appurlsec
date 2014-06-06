package com.crashlytics.android.internal;

// compiled from: SourceFile
public enum ai {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);

    private final int e;

    static {
        a = new ai("DEVELOPER", 0, 1);
        b = new ai("USER_SIDELOAD", 1, 2);
        c = new ai("TEST_DISTRIBUTION", 2, 3);
        d = new ai("APP_STORE", 3, 4);
        ai[] r0_aiA = new ai[4];
        r0_aiA[0] = a;
        r0_aiA[1] = b;
        r0_aiA[2] = c;
        r0_aiA[3] = d;
        f = r0_aiA;
    }

    private ai(int r3i) {
        this.e = r3i;
    }

    public static ai a(String r1_String) {
        return (r1_String != null ? 1 : 0) != 0 ? d : a;
    }

    public final int a() {
        return this.e;
    }

    public final String toString() {
        return Integer.toString(this.e);
    }
}