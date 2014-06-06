package com.zkmm.adsdk;

import android.content.Context;
import android.widget.Toast;

// compiled from: SourceFile
final class aa implements Runnable {
    private final /* synthetic */ Context a;

    aa(z r1_z, Context r2_Context) {
        this.a = r2_Context;
    }

    public final void run() {
        Toast.makeText(this.a, "\u8bbe\u5907\u6ca1\u6709\u5916\u90e8\u5b58\u50a8\u5361\uff0c\u4e0d\u80fd\u652f\u6301\u4e0b\u8f7d\u3002", 0).show();
    }
}