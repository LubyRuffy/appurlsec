package com.tencent.mm.sdk.platformtools;

import java.util.HashSet;
import java.util.Set;

public final class MMEntryLock {
    private static Set<String> a;

    static {
        a = new HashSet();
    }

    private MMEntryLock() {
    }

    public static boolean isLocked(String r1_String) {
        return a.contains(r1_String);
    }

    public static boolean lock(String r3_String) {
        if (isLocked(r3_String)) {
            Log.d("MicroMsg.MMEntryLock", new StringBuilder("locked-").append(r3_String).toString());
            return false;
        } else {
            Log.d("MicroMsg.MMEntryLock", new StringBuilder("lock-").append(r3_String).toString());
            return a.add(r3_String);
        }
    }

    public static void unlock(String r3_String) {
        a.remove(r3_String);
        Log.d("MicroMsg.MMEntryLock", new StringBuilder("unlock-").append(r3_String).toString());
    }
}