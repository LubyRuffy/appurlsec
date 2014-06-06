package android.support.v4.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

// compiled from: TaskStackBuilderHoneycomb.java
class af {
    public static PendingIntent getActivitiesPendingIntent(Context r1_Context, int r2i, Intent[] r3_IntentA, int r4i) {
        return PendingIntent.getActivities(r1_Context, r2i, r3_IntentA, r4i);
    }
}