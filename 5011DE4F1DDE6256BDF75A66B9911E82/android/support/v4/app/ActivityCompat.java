package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

public class ActivityCompat extends ContextCompat {
    public static void finishAffinity(Activity r2_Activity) {
        if (VERSION.SDK_INT >= 16) {
            c.finishAffinity(r2_Activity);
        } else {
            r2_Activity.finish();
        }
    }

    public static boolean invalidateOptionsMenu(Activity r2_Activity) {
        if (VERSION.SDK_INT < 11) {
            return false;
        }
        b.a(r2_Activity);
        return true;
    }

    public static void startActivity(Activity r2_Activity, Intent r3_Intent, Bundle r4_Bundle) {
        if (VERSION.SDK_INT >= 16) {
            c.startActivity(r2_Activity, r3_Intent, r4_Bundle);
        } else {
            r2_Activity.startActivity(r3_Intent);
        }
    }

    public static void startActivityForResult(Activity r2_Activity, Intent r3_Intent, int r4i, Bundle r5_Bundle) {
        if (VERSION.SDK_INT >= 16) {
            c.startActivityForResult(r2_Activity, r3_Intent, r4i, r5_Bundle);
        } else {
            r2_Activity.startActivityForResult(r3_Intent, r4i);
        }
    }
}