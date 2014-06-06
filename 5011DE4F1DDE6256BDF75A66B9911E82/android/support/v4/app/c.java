package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

// compiled from: ActivityCompatJB.java
class c {
    public static void finishAffinity(Activity r0_Activity) {
        r0_Activity.finishAffinity();
    }

    public static void startActivity(Context r0_Context, Intent r1_Intent, Bundle r2_Bundle) {
        r0_Context.startActivity(r1_Intent, r2_Bundle);
    }

    public static void startActivityForResult(Activity r0_Activity, Intent r1_Intent, int r2i, Bundle r3_Bundle) {
        r0_Activity.startActivityForResult(r1_Intent, r2i, r3_Bundle);
    }
}