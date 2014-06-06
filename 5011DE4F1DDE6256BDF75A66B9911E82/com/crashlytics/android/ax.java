package com.crashlytics.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: SourceFile
final class ax implements OnClickListener {
    private /* synthetic */ au a;

    ax(au r1_au) {
        this.a = r1_au;
    }

    public final void onClick(DialogInterface r3_DialogInterface, int r4i) {
        Crashlytics r0_Crashlytics = this.a.b;
        Crashlytics.a(true);
        this.a.a.a(true);
        r3_DialogInterface.dismiss();
    }
}