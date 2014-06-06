package com.tencent.mm.sdk.platformtools;

import java.util.HashMap;

public final class SystemProperty {
    private static final HashMap<String, String> a;

    static {
        a = new HashMap();
    }

    private SystemProperty() {
    }

    public static String getProperty(String r1_String) {
        return (String) a.get(r1_String);
    }

    public static void setProperty(String r1_String, String r2_String) {
        a.put(r1_String, r2_String);
    }
}