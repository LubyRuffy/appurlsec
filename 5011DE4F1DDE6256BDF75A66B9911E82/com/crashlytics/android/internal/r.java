package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
public class r implements q {
    public static int a(int r3i) {
        if (r3i >= 200 && r3i <= 299) {
            return 0;
        }
        if (r3i >= 300 && r3i <= 399) {
            return 1;
        }
        if (r3i >= 400 && r3i <= 499) {
            return 0;
        }
        if (r3i >= 500) {
            return 1;
        }
        return 1;
    }

    static /* synthetic */ Activity a(Context r1_Context) {
        return r1_Context instanceof Activity ? (Activity) r1_Context : null;
    }

    public static String a(Context r6_Context, boolean r7z) {
        String r0_String;
        int r2i;
        try {
            Context r0_Context = r6_Context.getApplicationContext();
            Bundle r0_Bundle = r0_Context.getPackageManager().getApplicationInfo(r0_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData;
            if (r0_Bundle != null) {
                r0_String = r0_Bundle.getString("com.crashlytics.ApiKey");
                if (!ab.e(r0_String)) {
                    r2i = ab.a(r6_Context, "com.crashlytics.ApiKey", "string");
                    if (r2i == 0) {
                        r0_String = r6_Context.getResources().getString(r2i);
                    }
                }
                if (!ab.e(r0_String)) {
                    if (r7z || ab.f(r6_Context)) {
                        throw new IllegalArgumentException("Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>");
                    } else {
                        cl.a.b().a(Crashlytics.TAG, "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>", null);
                    }
                }
                return r0_String;
            }
        } catch (Exception e) {
            cl.a.b().a(Crashlytics.TAG, new StringBuilder("Caught non-fatal exception while retrieving apiKey: ").append(e).toString());
        }
        r0_String = null;
        if (ab.e(r0_String)) {
            if (ab.e(r0_String)) {
                return r0_String;
            }
            if (r7z || ab.f(r6_Context)) {
                throw new IllegalArgumentException("Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>");
            } else {
                cl.a.b().a(Crashlytics.TAG, "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>", null);
                return r0_String;
            }
        } else {
            r2i = ab.a(r6_Context, "com.crashlytics.ApiKey", "string");
            if (r2i == 0) {
                if (ab.e(r0_String)) {
                    if (r7z || ab.f(r6_Context)) {
                        throw new IllegalArgumentException("Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>");
                    } else {
                        cl.a.b().a(Crashlytics.TAG, "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>", null);
                    }
                }
                return r0_String;
            } else {
                r0_String = r6_Context.getResources().getString(r2i);
                if (ab.e(r0_String)) {
                    return r0_String;
                }
                if (r7z || ab.f(r6_Context)) {
                    throw new IllegalArgumentException("Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>");
                } else {
                    cl.a.b().a(Crashlytics.TAG, "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>", null);
                    return r0_String;
                }
            }
        }
    }

    static /* synthetic */ Application b(Context r1_Context) {
        if (r1_Context instanceof Application) {
            return (Application) r1_Context;
        }
        if (r1_Context instanceof Activity) {
            return ((Activity) r1_Context).getApplication();
        }
        if (r1_Context instanceof Service) {
            return ((Service) r1_Context).getApplication();
        }
        if (r1_Context.getApplicationContext() instanceof Application) {
            return (Application) r1_Context.getApplicationContext();
        }
        return null;
    }

    private static boolean b(int r1i) {
        return cl.a.g() <= r1i;
    }

    public final void a(int r2i, String r3_String, String r4_String) {
        a(r2i, r3_String, r4_String, false);
    }

    public final void a(int r2i, String r3_String, String r4_String, boolean r5z) {
        if (r5z || b(r2i)) {
            Log.println(r2i, r3_String, r4_String);
        }
    }

    public final void a(String r2_String, String r3_String) {
        if (b((int)XListViewFooter.STATE_NOMORE)) {
            Log.d(r2_String, r3_String, null);
        }
    }

    public final void a(String r2_String, String r3_String, Throwable r4_Throwable) {
        if (b((int)ShareUtils.SHARE_COPY)) {
            Log.e(r2_String, r3_String, r4_Throwable);
        }
    }

    public final void b(String r2_String, String r3_String) {
        if (b((int)XListViewFooter.STATE_NODATA)) {
            Log.i(r2_String, r3_String, null);
        }
    }

    public final void c(String r2_String, String r3_String) {
        if (b((int)ShareUtils.SHARE_SMS)) {
            Log.w(r2_String, r3_String, null);
        }
    }

    public final void d(String r2_String, String r3_String) {
        a(r2_String, r3_String, null);
    }
}