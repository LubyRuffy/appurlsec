package android.support.v4.database;

import android.text.TextUtils;

public class DatabaseUtilsCompat {
    private DatabaseUtilsCompat() {
    }

    public static String[] appendSelectionArgs(String[] r4_StringA, String[] r5_StringA) {
        if (r4_StringA == null || r4_StringA.length == 0) {
            return r5_StringA;
        }
        Object r0_Object = new Object[(r4_StringA.length + r5_StringA.length)];
        System.arraycopy(r4_StringA, 0, r0_Object, 0, r4_StringA.length);
        System.arraycopy(r5_StringA, 0, r0_Object, r4_StringA.length, r5_StringA.length);
        return r0_Object;
    }

    public static String concatenateWhere(String r2_String, String r3_String) {
        if (TextUtils.isEmpty(r2_String)) {
            return r3_String;
        }
        if (TextUtils.isEmpty(r3_String)) {
            return r2_String;
        }
        return "(" + r2_String + ") AND (" + r3_String + ")";
    }
}