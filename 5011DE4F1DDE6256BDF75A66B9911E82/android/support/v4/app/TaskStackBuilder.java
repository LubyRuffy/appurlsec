package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskStackBuilder implements Iterable<Intent> {
    private static final a a;
    private final ArrayList<Intent> b;
    private final Context c;

    public static interface SupportParentable {
        public Intent getSupportParentActivityIntent();
    }

    static interface a {
        public PendingIntent getPendingIntent(Context r1_Context, Intent[] r2_IntentA, int r3i, int r4i, Bundle r5_Bundle);
    }

    static class b implements a {
        b() {
        }

        public PendingIntent getPendingIntent(Context r3_Context, Intent[] r4_IntentA, int r5i, int r6i, Bundle r7_Bundle) {
            Intent r0_Intent = new Intent(r4_IntentA[r4_IntentA.length - 1]);
            r0_Intent.addFlags(268435456);
            return PendingIntent.getActivity(r3_Context, r5i, r0_Intent, r6i);
        }
    }

    static class c implements a {
        c() {
        }

        public PendingIntent getPendingIntent(Context r4_Context, Intent[] r5_IntentA, int r6i, int r7i, Bundle r8_Bundle) {
            r5_IntentA[0] = new Intent(r5_IntentA[0]).addFlags(268484608);
            return af.getActivitiesPendingIntent(r4_Context, r6i, r5_IntentA, r7i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            a = new c();
        } else {
            a = new b();
        }
    }

    private TaskStackBuilder(Context r2_Context) {
        this.b = new ArrayList();
        this.c = r2_Context;
    }

    public static TaskStackBuilder create(Context r1_Context) {
        return new TaskStackBuilder(r1_Context);
    }

    public static TaskStackBuilder from(Context r1_Context) {
        return create(r1_Context);
    }

    public TaskStackBuilder addNextIntent(Intent r2_Intent) {
        this.b.add(r2_Intent);
        return this;
    }

    public TaskStackBuilder addNextIntentWithParentStack(Intent r2_Intent) {
        ComponentName r0_ComponentName = r2_Intent.getComponent();
        if (r0_ComponentName == null) {
            r0_ComponentName = r2_Intent.resolveActivity(this.c.getPackageManager());
        }
        if (r0_ComponentName != null) {
            addParentStack(r0_ComponentName);
        }
        addNextIntent(r2_Intent);
        return this;
    }

    public TaskStackBuilder addParentStack(Activity r3_Activity) {
        Intent r1_Intent;
        Intent r0_Intent = null;
        if (r3_Activity instanceof SupportParentable) {
            r0_Intent = ((SupportParentable) r3_Activity).getSupportParentActivityIntent();
        }
        r1_Intent = r0_Intent == null ? NavUtils.getParentActivityIntent(r3_Activity) : r0_Intent;
        if (r1_Intent != null) {
            ComponentName r0_ComponentName = r1_Intent.getComponent();
            if (r0_ComponentName == null) {
                r0_ComponentName = r1_Intent.resolveActivity(this.c.getPackageManager());
            }
            addParentStack(r0_ComponentName);
            addNextIntent(r1_Intent);
        }
        return this;
    }

    public TaskStackBuilder addParentStack(ComponentName r4_ComponentName) {
        int r1i = this.b.size();
        try {
            Intent r0_Intent = NavUtils.getParentActivityIntent(this.c, r4_ComponentName);
            while (r0_Intent != null) {
                this.b.add(r1i, r0_Intent);
                r0_Intent = NavUtils.getParentActivityIntent(this.c, r0_Intent.getComponent());
            }
            return this;
        } catch (NameNotFoundException e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    public TaskStackBuilder addParentStack(Class<?> r3_Class_) {
        return addParentStack(new ComponentName(this.c, r3_Class_));
    }

    public Intent editIntentAt(int r2i) {
        return (Intent) this.b.get(r2i);
    }

    public Intent getIntent(int r2i) {
        return editIntentAt(r2i);
    }

    public int getIntentCount() {
        return this.b.size();
    }

    public Intent[] getIntents() {
        Intent[] r2_IntentA = new Intent[this.b.size()];
        if (r2_IntentA.length == 0) {
            return r2_IntentA;
        }
        r2_IntentA[0] = new Intent((Intent) this.b.get(0)).addFlags(268484608);
        int r1i = 1;
        while (r1i < r2_IntentA.length) {
            r2_IntentA[r1i] = new Intent((Intent) this.b.get(r1i));
            r1i++;
        }
        return r2_IntentA;
    }

    public PendingIntent getPendingIntent(int r2i, int r3i) {
        return getPendingIntent(r2i, r3i, null);
    }

    public PendingIntent getPendingIntent(int r7i, int r8i, Bundle r9_Bundle) {
        int r3i = 0;
        if (this.b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        } else {
            Intent[] r2_IntentA = (Intent[]) this.b.toArray(new Intent[this.b.size()]);
            r2_IntentA[r3i] = new Intent(r2_IntentA[r3i]).addFlags(268484608);
            return a.getPendingIntent(this.c, r2_IntentA, r7i, r8i, r9_Bundle);
        }
    }

    public Iterator<Intent> iterator() {
        return this.b.iterator();
    }

    public void startActivities() {
        startActivities(null);
    }

    public void startActivities(Bundle r5_Bundle) {
        if (this.b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        } else {
            Intent[] r0_IntentA = (Intent[]) this.b.toArray(new Intent[this.b.size()]);
            r0_IntentA[0] = new Intent(r0_IntentA[0]).addFlags(268484608);
            if (!ContextCompat.startActivities(this.c, r0_IntentA, r5_Bundle)) {
                Intent r1_Intent = new Intent(r0_IntentA[r0_IntentA.length - 1]);
                r1_Intent.addFlags(268435456);
                this.c.startActivity(r1_Intent);
            }
        }
    }
}