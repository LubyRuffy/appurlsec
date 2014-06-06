package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.baidu.mobstat.a.a;
import com.baidu.mobstat.a.c;
import com.tencent.mm.sdk.contact.RContactStorage;
import qsbk.app.widget.listview.XListViewHeader;

public class StatService {
    public static final int EXCEPTION_LOG = 1;
    private static boolean a;

    static {
        a = false;
    }

    private static void a(Context r3_Context) {
        if (a(r3_Context, "onError(...)")) {
            i.a().a(r3_Context.getApplicationContext());
            k.a().a(true, r3_Context.getApplicationContext());
        }
    }

    private static boolean a() {
        return a;
    }

    private static boolean a(Context r5_Context, String r6_String) {
        if (r5_Context != null) {
            return true;
        }
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = "stat";
        r2_ObjectA[1] = r6_String + ":context=null";
        c.c(r2_ObjectA);
        return false;
    }

    private static boolean a(Class<?> r8_Class_, String r9_String) {
        boolean r1z = false;
        int r0i = XListViewHeader.STATE_REFRESHING;
        StackTraceElement[] r4_StackTraceElementA = new Throwable().getStackTrace();
        Object[] r3_ObjectA = new Object[4];
        r3_ObjectA[0] = "isCalledBy";
        r3_ObjectA[1] = Integer.valueOf(r4_StackTraceElementA.length);
        r3_ObjectA[2] = r8_Class_;
        r3_ObjectA[3] = r9_String;
        c.a(r3_ObjectA);
        if (r4_StackTraceElementA.length < 2) {
            return r1z;
        }
        while (r0i < r4_StackTraceElementA.length) {
            StackTraceElement r3_StackTraceElement = r4_StackTraceElementA[r0i];
            if (r3_StackTraceElement.getMethodName().equals(r9_String)) {
                try {
                    Class r3_Class = Class.forName(r3_StackTraceElement.getClassName());
                    Object[] r5_ObjectA = new Object[2];
                    r5_ObjectA[0] = "isCalledBy";
                    r5_ObjectA[1] = r3_Class;
                    c.a(r5_ObjectA);
                    while (r3_Class.getSuperclass() != null && r3_Class.getSuperclass() != r8_Class_) {
                        r3_Class = r3_Class.getSuperclass();
                        r5_ObjectA = new Object[2];
                        r5_ObjectA[0] = "isCalledBy";
                        r5_ObjectA[1] = r3_Class;
                        c.a(r5_ObjectA);
                    }
                    r1z = true;
                } catch (Exception e) {
                    c.a(e);
                }
            }
            r0i++;
        }
        return r1z;
    }

    private static void b() {
        a = true;
    }

    private static void b(Context r2_Context) {
        if (!j.a().b()) {
            j.a().a(r2_Context.getApplicationContext());
        }
    }

    public static void onEvent(Context r1_Context, String r2_String, String r3_String) {
        onEvent(r1_Context, r2_String, r3_String, EXCEPTION_LOG);
    }

    public static void onEvent(Context r7_Context, String r8_String, String r9_String, int r10i) {
        if (!(!a(r7_Context, "onEvent(...)") || r8_String == null || r8_String.equals(RContactStorage.PRIMARY_KEY))) {
            b(r7_Context);
            c.a().a(r7_Context.getApplicationContext(), r8_String, r9_String, r10i, System.currentTimeMillis());
        }
    }

    public static void onEventDuration(Context r6_Context, String r7_String, String r8_String, long r9j) {
        if (!(!a(r6_Context, "onEventDuration(...)") || r7_String == null || r7_String.equals(RContactStorage.PRIMARY_KEY))) {
            if (r9j <= 0) {
                Object[] r0_ObjectA = new Object[2];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onEventDuration: duration must be greater than zero";
                c.b(r0_ObjectA);
            } else {
                b(r6_Context);
                c.a().c(r6_Context.getApplicationContext(), r7_String, r8_String, r9j);
            }
        }
    }

    public static void onEventEnd(Context r6_Context, String r7_String, String r8_String) {
        if (!(!a(r6_Context, "onEventEnd(...)") || r7_String == null || r7_String.equals(RContactStorage.PRIMARY_KEY))) {
            b(r6_Context);
            c.a().b(r6_Context.getApplicationContext(), r7_String, r8_String, System.currentTimeMillis());
        }
    }

    public static void onEventStart(Context r6_Context, String r7_String, String r8_String) {
        if (!(!a(r6_Context, "onEventStart(...)") || r7_String == null || r7_String.equals(RContactStorage.PRIMARY_KEY))) {
            b(r6_Context);
            c.a().a(r6_Context.getApplicationContext(), r7_String, r8_String, System.currentTimeMillis());
        }
    }

    public static synchronized void onPageEnd(Context r4_Context, String r5_String) {
        synchronized (StatService.class) {
            Object[] r0_ObjectA;
            if (r4_Context == null || r5_String == null) {
                r0_ObjectA = new Object[2];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onPageEnd :parame=null || empty";
                c.c(r0_ObjectA);
            } else if (r5_String.equals(RContactStorage.PRIMARY_KEY)) {
                r0_ObjectA = new Object[2];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onPageEnd :parame=null || empty";
                c.c(r0_ObjectA);
            } else {
                q.b().b(r4_Context, System.currentTimeMillis(), r5_String);
            }
        }
    }

    public static synchronized void onPageStart(Context r4_Context, String r5_String) {
        synchronized (StatService.class) {
            Object[] r0_ObjectA;
            if (r4_Context == null || r5_String == null) {
                r0_ObjectA = new Object[2];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onPageStart :parame=null || empty";
                c.c(r0_ObjectA);
            } else if (r5_String.equals(RContactStorage.PRIMARY_KEY)) {
                r0_ObjectA = new Object[2];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onPageStart :parame=null || empty";
                c.c(r0_ObjectA);
            } else {
                b(r4_Context);
                q.b().a(r4_Context, System.currentTimeMillis(), r5_String);
            }
        }
    }

    public static synchronized void onPause(Context r4_Context) {
        synchronized (StatService.class) {
            if (a(r4_Context, "onPause(...)")) {
                if (a(Activity.class, "onPause")) {
                    q.b().b(r4_Context, System.currentTimeMillis());
                } else {
                    throw new SecurityException("onPause(Context context)\u4e0d\u5728Activity.onPause()\u4e2d\u88ab\u8c03\u7528||onPause(Context context)is not called in Activity.onPause().");
                }
            }
        }
    }

    public static synchronized void onPause(Fragment r4_Fragment) {
        synchronized (StatService.class) {
            if (r4_Fragment == null) {
                Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onResume :parame=null";
                c.c(r0_ObjectA);
            } else if (a(Fragment.class, "onPause")) {
                q.b().b(r4_Fragment, System.currentTimeMillis());
            } else {
                throw new SecurityException("Fragment onPause(Context context)\u4e0d\u5728Fragment.onPause()\u4e2d\u88ab\u8c03\u7528||onPause(Context context)is not called in Fragment.onPause().");
            }
        }
    }

    public static synchronized void onPause(Object r4_Object) {
        synchronized (StatService.class) {
            if (r4_Object == null) {
                Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "android.app.Fragment onResume :parame=null";
                c.c(r0_ObjectA);
            } else if (a(r4_Object.getClass(), "onPause")) {
                q.b().b(r4_Object, System.currentTimeMillis());
            } else {
                throw new SecurityException("android.app.Fragment onPause(Context context)\u4e0d\u5728android.app.Fragment.onPause()\u4e2d\u88ab\u8c03\u7528||onPause(Context context)is not called in android.app.Fragment.onPause().");
            }
        }
    }

    public static synchronized void onResume(Context r4_Context) {
        synchronized (StatService.class) {
            if (a(r4_Context, "onResume(...)")) {
                if (a(Activity.class, "onResume")) {
                    b(r4_Context);
                    q.b().a(r4_Context, System.currentTimeMillis());
                } else {
                    throw new SecurityException("onResume(Context context)\u4e0d\u5728Activity.onResume()\u4e2d\u88ab\u8c03\u7528||onResume(Context context)is not called in Activity.onResume().");
                }
            }
        }
    }

    public static synchronized void onResume(Fragment r4_Fragment) {
        synchronized (StatService.class) {
            if (r4_Fragment == null) {
                Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onResume :parame=null";
                c.c(r0_ObjectA);
            } else if (a(Fragment.class, "onResume")) {
                b(r4_Fragment.getActivity());
                q.b().a(r4_Fragment, System.currentTimeMillis());
            } else {
                throw new SecurityException("onResume(Context context)\u4e0d\u5728Activity.onResume()\u4e2d\u88ab\u8c03\u7528||onResume(Context context)is not called in Activity.onResume().");
            }
        }
    }

    public static synchronized void onResume(Object r4_Object) {
        synchronized (StatService.class) {
            if (r4_Object == null) {
                Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
                r0_ObjectA[0] = "stat";
                r0_ObjectA[1] = "onResume :parame=null";
                c.c(r0_ObjectA);
            } else if (a(r4_Object.getClass(), "onResume")) {
                b(q.a(r4_Object));
                q.b().a(r4_Object, System.currentTimeMillis());
            } else {
                throw new SecurityException("onResume(Context context)\u4e0d\u5728Activity.onResume()\u4e2d\u88ab\u8c03\u7528||onResume(Context context)is not called in Activity.onResume().");
            }
        }
    }

    public static void setAppChannel(Context r1_Context, String r2_String, boolean r3z) {
        DataCore.getInstance().setAppChannel(r1_Context, r2_String, r3z);
    }

    public static void setAppChannel(String r1_String) {
        DataCore.getInstance().setAppChannel(r1_String);
    }

    public static void setAppKey(String r1_String) {
        DataCore.getInstance().setAppKey(r1_String);
    }

    public static void setDebugOn(boolean r1z) {
        if (r1z) {
            a.a = 2;
        } else {
            a.a = 7;
        }
    }

    public static void setLogSenderDelayed(int r1i) {
        k.a().a(r1i);
    }

    public static void setOn(Context r2_Context, int r3i) {
        if (a(r2_Context, "setOn(...)")) {
            if (a(Activity.class, "onCreate")) {
                if (!a()) {
                    b();
                    if ((r3i & 1) != 0) {
                        a(r2_Context);
                    }
                }
            } else {
                throw new SecurityException("setOn()\u6ca1\u6709\u5728Activity.onCreate()\u5185\u88ab\u8c03\u7528||setOn()is not called in Activity.onCreate().");
            }
        }
    }

    public static void setSendLogStrategy(Context r1_Context, SendStrategyEnum r2_SendStrategyEnum, int r3i) {
        setSendLogStrategy(r1_Context, r2_SendStrategyEnum, r3i, false);
    }

    public static void setSendLogStrategy(Context r2_Context, SendStrategyEnum r3_SendStrategyEnum, int r4i, boolean r5z) {
        if (a(r2_Context, "setSendLogStrategy(...)")) {
            b(r2_Context);
            k.a().a(r2_Context.getApplicationContext(), r3_SendStrategyEnum, r4i, r5z);
        }
    }

    public static void setSessionTimeOut(int r1i) {
        if (r1i <= 0) {
            c.b("SessionTimeOut is between 1 and 600. Default value[30] is used");
        } else if (r1i <= 600) {
            q.b().a(r1i);
        } else {
            c.b("SessionTimeOut is between 1 and 600. Value[600] is used");
            q.b().a(r1i);
        }
    }
}