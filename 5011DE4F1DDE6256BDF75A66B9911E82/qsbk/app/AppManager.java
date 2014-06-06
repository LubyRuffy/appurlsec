package qsbk.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import java.util.Iterator;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> a;
    private static AppManager b;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (b == null) {
            b = new AppManager();
        }
        return b;
    }

    public void AppExit(Context r3_Context) {
        try {
            finishAllActivity();
            ((ActivityManager) r3_Context.getSystemService("activity")).restartPackage(r3_Context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public void addActivity(Activity r2_Activity) {
        if (a == null) {
            a = new Stack();
        }
        a.add(r2_Activity);
    }

    public Activity currentActivity() {
        return (Activity) a.lastElement();
    }

    public void finishActivity() {
        finishActivity((Activity) a.lastElement());
    }

    public void finishActivity(Activity r2_Activity) {
        if (r2_Activity != null) {
            a.remove(r2_Activity);
            r2_Activity.finish();
        }
    }

    public void finishActivity(Class<?> r4_Class_) {
        Iterator r1_Iterator = a.iterator();
        while (r1_Iterator.hasNext()) {
            Activity r0_Activity = (Activity) r1_Iterator.next();
            if (r0_Activity.getClass().equals(r4_Class_)) {
                finishActivity(r0_Activity);
            }
        }
    }

    public void finishAllActivity() {
        int r2i = a.size();
        int r1i = 0;
        while (r1i < r2i) {
            if (a.get(r1i) != null) {
                ((Activity) a.get(r1i)).finish();
            }
            r1i++;
        }
        a.clear();
    }
}