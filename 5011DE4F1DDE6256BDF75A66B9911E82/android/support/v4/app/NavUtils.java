package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;

public class NavUtils {
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final a a;

    static interface a {
        public Intent getParentActivityIntent(Activity r1_Activity);

        public String getParentActivityName(Context r1_Context, ActivityInfo r2_ActivityInfo);

        public void navigateUpTo(Activity r1_Activity, Intent r2_Intent);

        public boolean shouldUpRecreateTask(Activity r1_Activity, Intent r2_Intent);
    }

    static class b implements a {
        b() {
        }

        public Intent getParentActivityIntent(Activity r6_Activity) {
            String r1_String = NavUtils.getParentActivityName(r6_Activity);
            if (r1_String == null) {
                return null;
            }
            ComponentName r2_ComponentName = new ComponentName(r6_Activity, r1_String);
            try {
                return NavUtils.getParentActivityName(r6_Activity, r2_ComponentName) == null ? IntentCompat.makeMainActivity(r2_ComponentName) : new Intent().setComponent(r2_ComponentName);
            } catch (NameNotFoundException e) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + r1_String + "' in manifest");
                return null;
            }
        }

        public String getParentActivityName(Context r4_Context, ActivityInfo r5_ActivityInfo) {
            if (r5_ActivityInfo.metaData == null) {
                return null;
            }
            String r0_String = r5_ActivityInfo.metaData.getString(PARENT_ACTIVITY);
            if (r0_String == null) {
                return null;
            }
            if (r0_String.charAt(0) == '.') {
                return r4_Context.getPackageName() + r0_String;
            }
            return r0_String;
        }

        public void navigateUpTo(Activity r2_Activity, Intent r3_Intent) {
            r3_Intent.addFlags(67108864);
            r2_Activity.startActivity(r3_Intent);
            r2_Activity.finish();
        }

        public boolean shouldUpRecreateTask(Activity r3_Activity, Intent r4_Intent) {
            String r0_String = r3_Activity.getIntent().getAction();
            return r0_String != null && !r0_String.equals("android.intent.action.MAIN");
        }
    }

    static class c extends b {
        c() {
        }

        Intent a(Activity r2_Activity) {
            return super.getParentActivityIntent(r2_Activity);
        }

        public Intent getParentActivityIntent(Activity r2_Activity) {
            Intent r0_Intent = x.getParentActivityIntent(r2_Activity);
            return r0_Intent == null ? a(r2_Activity) : r0_Intent;
        }

        public String getParentActivityName(Context r2_Context, ActivityInfo r3_ActivityInfo) {
            String r0_String = x.getParentActivityName(r3_ActivityInfo);
            return r0_String == null ? super.getParentActivityName(r2_Context, r3_ActivityInfo) : r0_String;
        }

        public void navigateUpTo(Activity r1_Activity, Intent r2_Intent) {
            x.navigateUpTo(r1_Activity, r2_Intent);
        }

        public boolean shouldUpRecreateTask(Activity r2_Activity, Intent r3_Intent) {
            return x.shouldUpRecreateTask(r2_Activity, r3_Intent);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new c();
        } else {
            a = new b();
        }
    }

    private NavUtils() {
    }

    public static Intent getParentActivityIntent(Activity r1_Activity) {
        return a.getParentActivityIntent(r1_Activity);
    }

    public static Intent getParentActivityIntent(Context r3_Context, ComponentName r4_ComponentName) throws NameNotFoundException {
        String r0_String = getParentActivityName(r3_Context, r4_ComponentName);
        if (r0_String == null) {
            return null;
        }
        ComponentName r1_ComponentName = new ComponentName(r4_ComponentName.getPackageName(), r0_String);
        return getParentActivityName(r3_Context, r1_ComponentName) == null ? IntentCompat.makeMainActivity(r1_ComponentName) : new Intent().setComponent(r1_ComponentName);
    }

    public static Intent getParentActivityIntent(Context r2_Context, Class<?> r3_Class_) throws NameNotFoundException {
        String r0_String = getParentActivityName(r2_Context, new ComponentName(r2_Context, r3_Class_));
        if (r0_String == null) {
            return null;
        }
        ComponentName r1_ComponentName = new ComponentName(r2_Context, r0_String);
        return getParentActivityName(r2_Context, r1_ComponentName) == null ? IntentCompat.makeMainActivity(r1_ComponentName) : new Intent().setComponent(r1_ComponentName);
    }

    public static String getParentActivityName(Activity r2_Activity) {
        try {
            return getParentActivityName(r2_Activity, r2_Activity.getComponentName());
        } catch (NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getParentActivityName(Context r2_Context, ComponentName r3_ComponentName) throws NameNotFoundException {
        return a.getParentActivityName(r2_Context, r2_Context.getPackageManager().getActivityInfo(r3_ComponentName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS));
    }

    public static void navigateUpFromSameTask(Activity r3_Activity) {
        Intent r0_Intent = getParentActivityIntent(r3_Activity);
        if (r0_Intent == null) {
            throw new IllegalArgumentException("Activity " + r3_Activity.getClass().getSimpleName() + " does not have a parent activity name specified." + " (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> " + " element in your manifest?)");
        } else {
            navigateUpTo(r3_Activity, r0_Intent);
        }
    }

    public static void navigateUpTo(Activity r1_Activity, Intent r2_Intent) {
        a.navigateUpTo(r1_Activity, r2_Intent);
    }

    public static boolean shouldUpRecreateTask(Activity r1_Activity, Intent r2_Intent) {
        return a.shouldUpRecreateTask(r1_Activity, r2_Intent);
    }
}