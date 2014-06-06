package com.crashlytics.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: SourceFile
final class l extends BroadcastReceiver {
    private /* synthetic */ ba a;

    l(ba r1_ba) {
        this.a = r1_ba;
    }

    public final void onReceive(Context r3_Context, Intent r4_Intent) {
        ba.a(this.a, true);
    }
}