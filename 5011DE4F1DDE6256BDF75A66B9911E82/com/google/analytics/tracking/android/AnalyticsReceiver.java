package com.google.analytics.tracking.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.io.IOException;
import java.io.OutputStream;

public class AnalyticsReceiver extends BroadcastReceiver {
    public void onReceive(Context r4_Context, Intent r5_Intent) {
        String r0_String = r5_Intent.getStringExtra(ModelFields.REFERRER);
        if ((!"com.android.vending.INSTALL_REFERRER".equals(r5_Intent.getAction())) || r0_String == null) {
        } else {
            try {
                OutputStream r1_OutputStream = r4_Context.openFileOutput("gaInstallData", 0);
                r1_OutputStream.write(r0_String.getBytes());
                r1_OutputStream.close();
            } catch (IOException e) {
                z.c("Error storing install campaign.");
            }
        }
    }
}