package com.aps;

public final class ay {
    protected static final String[] a;

    static {
        String[] r0_StringA = new String[7];
        r0_StringA[0] = "android.permission.READ_PHONE_STATE";
        r0_StringA[1] = "android.permission.ACCESS_WIFI_STATE";
        r0_StringA[2] = "android.permission.WRITE_EXTERNAL_STORAGE";
        r0_StringA[3] = "android.permission.ACCESS_FINE_LOCATION";
        r0_StringA[4] = "android.permission.ACCESS_COARSE_LOCATION";
        r0_StringA[5] = "android.permission.CHANGE_WIFI_STATE";
        r0_StringA[6] = "android.permission.ACCESS_NETWORK_STATE";
        a = r0_StringA;
    }

    protected static boolean a(String[] r3_StringA, String r4_String) {
        if (r3_StringA == null || r4_String == null) {
            return false;
        }
        int r0i = 0;
        while (r0i < r3_StringA.length) {
            if (r3_StringA[r0i].equals(r4_String)) {
                return true;
            }
            r0i++;
        }
        return false;
    }
}