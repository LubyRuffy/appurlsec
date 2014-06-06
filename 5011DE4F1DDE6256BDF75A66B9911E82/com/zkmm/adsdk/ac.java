package com.zkmm.adsdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

// compiled from: SourceFile
final class ac implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;

    ac(z r1_z, Context r2_Context, String r3_String) {
        this.a = r2_Context;
        this.b = r3_String;
    }

    public final void run() {
        PackageManager r0_PackageManager = this.a.getPackageManager();
        if (this.b != null) {
            Intent r0_Intent = r0_PackageManager.getLaunchIntentForPackage(this.b);
            if (r0_Intent != null) {
                this.a.startActivity(r0_Intent);
            }
        }
    }
}