package com.google.analytics.tracking.android;

import android.content.Context;
import android.text.TextUtils;

// compiled from: ParameterLoaderImpl.java
class af implements ae {
    private final Context a;

    public af(Context r3_Context) {
        if (r3_Context == null) {
            throw new NullPointerException("Context cannot be null");
        } else {
            this.a = r3_Context.getApplicationContext();
        }
    }

    private int a(String r3_String, String r4_String) {
        return this.a == null ? 0 : this.a.getResources().getIdentifier(r3_String, r4_String, this.a.getPackageName());
    }

    public boolean getBoolean(String r4_String) {
        int r0i = a(r4_String, "bool");
        return r0i == 0 ? false : "true".equalsIgnoreCase(this.a.getString(r0i));
    }

    public Double getDoubleFromString(String r5_String) {
        String r1_String = getString(r5_String);
        if (TextUtils.isEmpty(r1_String)) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(r1_String));
        } catch (NumberFormatException e) {
            z.h("NumberFormatException parsing " + r1_String);
            return null;
        }
    }

    public int getInt(String r4_String, int r5i) {
        int r0i = a(r4_String, "integer");
        if (r0i == 0) {
            return r5i;
        }
        try {
            return Integer.parseInt(this.a.getString(r0i));
        } catch (NumberFormatException e) {
            z.h("NumberFormatException parsing " + this.a.getString(r0i));
            return r5i;
        }
    }

    public String getString(String r3_String) {
        int r0i = a(r3_String, "string");
        return r0i == 0 ? null : this.a.getString(r0i);
    }

    public boolean isBooleanKeyPresent(String r2_String) {
        return a(r2_String, "bool") != 0;
    }
}