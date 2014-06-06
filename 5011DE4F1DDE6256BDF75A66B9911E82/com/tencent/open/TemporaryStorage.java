package com.tencent.open;

import java.util.HashMap;

// compiled from: ProGuard
public class TemporaryStorage {
    private static HashMap a;

    static {
        a = new HashMap();
    }

    public static Object a(String r1_String) {
        return a.remove(r1_String);
    }

    public static Object a(String r1_String, Object r2_Object) {
        return a.put(r1_String, r2_Object);
    }
}