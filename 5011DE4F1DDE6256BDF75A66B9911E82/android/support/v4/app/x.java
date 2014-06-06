package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

// compiled from: NavUtilsJB.java
class x {
    public static Intent getParentActivityIntent(Activity r1_Activity) {
        return r1_Activity.getParentActivityIntent();
    }

    public static String getParentActivityName(ActivityInfo r1_ActivityInfo) {
        return r1_ActivityInfo.parentActivityName;
    }

    public static void navigateUpTo(Activity r0_Activity, Intent r1_Intent) {
        r0_Activity.navigateUpTo(r1_Intent);
    }

    public static boolean shouldUpRecreateTask(Activity r1_Activity, Intent r2_Intent) {
        return r1_Activity.shouldUpRecreateTask(r2_Intent);
    }
}