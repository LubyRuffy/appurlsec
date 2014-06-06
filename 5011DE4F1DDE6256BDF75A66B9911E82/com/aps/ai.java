package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class ai extends BroadcastReceiver {
    ai(t r1_t) {
    }

    public final void onReceive(Context r3_Context, Intent r4_Intent) {
        if (r4_Intent == null || (!r4_Intent.getAction().equals("android.location.GPS_FIX_CHANGE"))) {
        } else {
            t.b = false;
        }
    }
}