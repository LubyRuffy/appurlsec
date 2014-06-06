package com.zkmm.adsdk;

import android.content.Context;
import android.widget.Toast;

// compiled from: SourceFile
final class ay implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;

    ay(z r1_z, Context r2_Context, String r3_String) {
        this.a = r2_Context;
        this.b = r3_String;
    }

    public final void run() {
        Toast.makeText(this.a, new StringBuilder(String.valueOf(this.b)).append("\n\u5df2\u7ecf\u88ab\u4e0b\u8f7d\u5230:").append(m.O).append(" \u76ee\u5f55\u4e0b\u3002").toString(), 1).show();
    }
}