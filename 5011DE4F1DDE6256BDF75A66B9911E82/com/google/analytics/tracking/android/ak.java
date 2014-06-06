package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.nearby.ui.NearbySelectView;

// compiled from: StandardExceptionParser.java
class ak implements ExceptionParser {
    private final TreeSet<String> a;

    public ak(Context r2_Context, Collection<String> r3_Collection_String) {
        this.a = new TreeSet();
        setIncludedPackages(r2_Context, r3_Collection_String);
    }

    protected String a(Throwable r7_Throwable, StackTraceElement r8_StackTraceElement, String r9_String) {
        StringBuilder r1_StringBuilder = new StringBuilder();
        r1_StringBuilder.append(r7_Throwable.getClass().getSimpleName());
        if (r8_StackTraceElement != null) {
            String[] r2_StringA = r8_StackTraceElement.getClassName().split("\\.");
            String r0_String = EDIT_TYPE.TYPE_UNKNOWN;
            Object[] r3_ObjectA;
            if (r2_StringA == null || r2_StringA.length <= 0) {
                r3_ObjectA = new Object[2];
                r3_ObjectA[0] = r0_String;
                r3_ObjectA[1] = r8_StackTraceElement.getMethodName();
                r1_StringBuilder.append(String.format(" (@%s:%s)", r3_ObjectA));
            } else {
                r0_String = r2_StringA[r2_StringA.length - 1];
                r3_ObjectA = new Object[2];
                r3_ObjectA[0] = r0_String;
                r3_ObjectA[1] = r8_StackTraceElement.getMethodName();
                r1_StringBuilder.append(String.format(" (@%s:%s)", r3_ObjectA));
            }
        }
        if (r9_String != null) {
            Object[] r2_ObjectA = new Object[1];
            r2_ObjectA[0] = r9_String;
            r1_StringBuilder.append(String.format(" {%s}", r2_ObjectA));
        }
        return r1_StringBuilder.toString();
    }

    protected Throwable a(Throwable r2_Throwable) {
        while (r2_Throwable.getCause() != null) {
            r2_Throwable = r2_Throwable.getCause();
        }
        return r2_Throwable;
    }

    protected StackTraceElement b(Throwable r9_Throwable) {
        StackTraceElement[] r4_StackTraceElementA = r9_Throwable.getStackTrace();
        if (r4_StackTraceElementA == null || r4_StackTraceElementA.length == 0) {
            return null;
        }
        int r5i = r4_StackTraceElementA.length;
        int r2i = 0;
        while (r2i < r5i) {
            StackTraceElement r1_StackTraceElement = r4_StackTraceElementA[r2i];
            String r6_String = r1_StackTraceElement.getClassName();
            Iterator r7_Iterator = this.a.iterator();
            while (r7_Iterator.hasNext()) {
                if (r6_String.startsWith((String) r7_Iterator.next())) {
                    return r1_StackTraceElement;
                }
            }
            r2i++;
        }
        return r4_StackTraceElementA[0];
    }

    public String getDescription(String r3_String, Throwable r4_Throwable) {
        return a(a(r4_Throwable), b(a(r4_Throwable)), r3_String);
    }

    public void setIncludedPackages(Context r8_Context, Collection<String> r9_Collection_String) {
        String r0_String;
        this.a.clear();
        Set r1_Set = new HashSet();
        if (r9_Collection_String != null) {
            r1_Set.addAll(r9_Collection_String);
        }
        if (r8_Context != null) {
            try {
                r0_String = r8_Context.getApplicationContext().getPackageName();
                this.a.add(r0_String);
                ActivityInfo[] r2_ActivityInfoA = r8_Context.getApplicationContext().getPackageManager().getPackageInfo(r0_String, NearbySelectView.TIME_15MIN).activities;
                if (r2_ActivityInfoA != null) {
                    int r4i = r2_ActivityInfoA.length;
                    int r0i = 0;
                    while (r0i < r4i) {
                        r1_Set.add(r2_ActivityInfoA[r0i].packageName);
                        r0i++;
                    }
                }
            } catch (NameNotFoundException e) {
                z.d("No package found");
            }
        }
        Iterator r4_Iterator = r1_Set.iterator();
        while (r4_Iterator.hasNext()) {
            r0_String = (String) r4_Iterator.next();
            Iterator r5_Iterator = this.a.iterator();
            int r2i = 1;
            while (r5_Iterator.hasNext()) {
                String r1_String = (String) r5_Iterator.next();
                if (r0_String.startsWith(r1_String)) {
                    r2i = 0;
                } else if (r1_String.startsWith(r0_String)) {
                    this.a.remove(r1_String);
                    break;
                }
            }
            if (r2i != 0) {
                this.a.add(r0_String);
            }
        }
    }
}