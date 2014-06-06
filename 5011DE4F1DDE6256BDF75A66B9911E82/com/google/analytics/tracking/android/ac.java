package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.MetaModel.Formatter;
import java.text.DecimalFormat;

// compiled from: MetaModelInitializer.java
final class ac implements Formatter {
    private final DecimalFormat a;

    ac() {
        this.a = new DecimalFormat("0.##");
    }

    public String format(String r4_String) {
        return this.a.format(am.safeParseDouble(r4_String));
    }
}