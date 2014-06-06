package com.tencent.cloudsdk;

import android.util.Log;

// compiled from: SourceFile
public class dd {
    private final String a;
    private final int b;

    private dd(String r1_String, int r2i) {
        this.a = r1_String;
        this.b = r2i;
    }

    private void a(String r3_String) {
        Log.println(this.b, this.a, r3_String);
    }

    private boolean a() {
        return Log.isLoggable(this.a, this.b);
    }
}