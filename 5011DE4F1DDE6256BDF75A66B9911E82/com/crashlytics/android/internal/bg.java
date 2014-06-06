package com.crashlytics.android.internal;

// compiled from: SourceFile
enum bg {
    CREATE,
    START,
    RESUME,
    SAVE_INSTANCE_STATE,
    PAUSE,
    STOP,
    DESTROY,
    ERROR,
    CRASH,
    INSTALL;


    static {
        a = new bg("CREATE", 0);
        b = new bg("START", 1);
        c = new bg("RESUME", 2);
        d = new bg("SAVE_INSTANCE_STATE", 3);
        e = new bg("PAUSE", 4);
        f = new bg("STOP", 5);
        g = new bg("DESTROY", 6);
        h = new bg("ERROR", 7);
        i = new bg("CRASH", 8);
        j = new bg("INSTALL", 9);
        bg[] r0_bgA = new bg[10];
        r0_bgA[0] = a;
        r0_bgA[1] = b;
        r0_bgA[2] = c;
        r0_bgA[3] = d;
        r0_bgA[4] = e;
        r0_bgA[5] = f;
        r0_bgA[6] = g;
        r0_bgA[7] = h;
        r0_bgA[8] = i;
        r0_bgA[9] = j;
        k = r0_bgA;
    }
}