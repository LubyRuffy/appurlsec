package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import java.io.File;

// compiled from: ContextCompatHoneycomb.java
class b {
    static void a(Context r0_Context, Intent[] r1_IntentA) {
        r0_Context.startActivities(r1_IntentA);
    }

    public static File getObbDir(Context r1_Context) {
        return r1_Context.getObbDir();
    }
}