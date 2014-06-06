package com.zkmm.adsdk;

import android.app.Activity;
import android.widget.Toast;

// compiled from: SourceFile
final class t implements Runnable {
    private final /* synthetic */ Activity a;
    private final /* synthetic */ String b;

    t(Activity r1_Activity, String r2_String) {
        this.a = r1_Activity;
        this.b = r2_String;
    }

    public final void run() {
        Toast.makeText(this.a, new StringBuilder("\u5f00\u59cb\u4e0b\u8f7d\n").append(this.b).append("\n\u81f3\u76ee\u5f55").append(m.O).toString(), 1).show();
    }
}