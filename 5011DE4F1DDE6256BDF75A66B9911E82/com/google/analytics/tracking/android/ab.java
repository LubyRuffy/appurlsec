package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.MetaModel.Formatter;

// compiled from: MetaModelInitializer.java
final class ab implements Formatter {
    ab() {
    }

    public String format(String r2_String) {
        return am.safeParseBoolean(r2_String) ? "1" : "0";
    }
}