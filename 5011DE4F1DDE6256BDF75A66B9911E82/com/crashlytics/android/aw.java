package com.crashlytics.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: SourceFile
final class aw implements OnClickListener {
    private /* synthetic */ au a;

    aw(au r1_au) {
        this.a = r1_au;
    }

    public final void onClick(DialogInterface r3_DialogInterface, int r4i) {
        this.a.a.a(false);
        r3_DialogInterface.dismiss();
    }
}