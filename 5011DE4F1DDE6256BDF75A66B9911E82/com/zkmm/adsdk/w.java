package com.zkmm.adsdk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: SourceFile
final class w implements OnClickListener {
    private final /* synthetic */ String a;
    private final /* synthetic */ Context b;

    w(String r1_String, Context r2_Context) {
        this.a = r1_String;
        this.b = r2_Context;
    }

    public final void onClick(DialogInterface r5_DialogInterface, int r6i) {
        new Thread(new x(this, this.a, this.b)).start();
    }
}