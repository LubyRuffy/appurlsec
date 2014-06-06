package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;

public class IntentCompat {
    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
    private static final a a;

    static interface a {
        public Intent makeMainActivity(ComponentName r1_ComponentName);

        public Intent makeMainSelectorActivity(String r1_String, String r2_String);

        public Intent makeRestartActivityTask(ComponentName r1_ComponentName);
    }

    static class b implements a {
        b() {
        }

        public Intent makeMainActivity(ComponentName r3_ComponentName) {
            Intent r0_Intent = new Intent("android.intent.action.MAIN");
            r0_Intent.setComponent(r3_ComponentName);
            r0_Intent.addCategory("android.intent.category.LAUNCHER");
            return r0_Intent;
        }

        public Intent makeMainSelectorActivity(String r2_String, String r3_String) {
            Intent r0_Intent = new Intent(r2_String);
            r0_Intent.addCategory(r3_String);
            return r0_Intent;
        }

        public Intent makeRestartActivityTask(ComponentName r3_ComponentName) {
            Intent r0_Intent = makeMainActivity(r3_ComponentName);
            r0_Intent.addFlags(268468224);
            return r0_Intent;
        }
    }

    static class c extends b {
        c() {
        }

        public Intent makeMainActivity(ComponentName r2_ComponentName) {
            return e.makeMainActivity(r2_ComponentName);
        }

        public Intent makeRestartActivityTask(ComponentName r2_ComponentName) {
            return e.makeRestartActivityTask(r2_ComponentName);
        }
    }

    static class d extends c {
        d() {
        }

        public Intent makeMainSelectorActivity(String r2_String, String r3_String) {
            return f.makeMainSelectorActivity(r2_String, r3_String);
        }
    }

    static {
        int r0i = VERSION.SDK_INT;
        if (r0i >= 15) {
            a = new d();
        } else if (r0i >= 11) {
            a = new c();
        } else {
            a = new b();
        }
    }

    private IntentCompat() {
    }

    public static Intent makeMainActivity(ComponentName r1_ComponentName) {
        return a.makeMainActivity(r1_ComponentName);
    }

    public static Intent makeMainSelectorActivity(String r1_String, String r2_String) {
        return a.makeMainSelectorActivity(r1_String, r2_String);
    }

    public static Intent makeRestartActivityTask(ComponentName r1_ComponentName) {
        return a.makeRestartActivityTask(r1_ComponentName);
    }
}