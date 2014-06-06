package com.crashlytics.android;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Environment;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.ah;
import com.crashlytics.android.internal.ap;
import com.crashlytics.android.internal.aq;
import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.bean.Base;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class ba implements UncaughtExceptionHandler {
    static final FilenameFilter a;
    private static Comparator<File> b;
    private static Comparator<File> c;
    private static final Pattern d;
    private static final Map<String, String> e;
    private static final ai f;
    private final AtomicInteger g;
    private final AtomicBoolean h;
    private final int i;
    private final UncaughtExceptionHandler j;
    private final File k;
    private final File l;
    private final AtomicBoolean m;
    private final String n;
    private final BroadcastReceiver o;
    private final BroadcastReceiver p;
    private final ai q;
    private final ai r;
    private final ExecutorService s;
    private RunningAppProcessInfo t;
    private aq u;
    private boolean v;
    private Thread[] w;
    private List<StackTraceElement[]> x;
    private StackTraceElement[] y;

    static {
        a = new bb();
        b = new h();
        c = new j();
        k r0_k = new k();
        d = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
        e = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
        f = ai.a("0");
    }

    public ba(UncaughtExceptionHandler r2_UncaughtExceptionHandler, CrashlyticsListener r3_CrashlyticsListener, String r4_String) {
        this(r2_UncaughtExceptionHandler, r3_CrashlyticsListener, ah.a("Crashlytics Exception Handler"), r4_String);
    }

    private ba(UncaughtExceptionHandler r7_UncaughtExceptionHandler, CrashlyticsListener r8_CrashlyticsListener, ExecutorService r9_ExecutorService, String r10_String) {
        this.g = new AtomicInteger(0);
        this.h = new AtomicBoolean(false);
        this.j = r7_UncaughtExceptionHandler;
        this.s = r9_ExecutorService;
        this.m = new AtomicBoolean(false);
        this.k = v.a().h();
        this.l = new File(this.k, "initialization_marker");
        Locale r0_Locale = Locale.US;
        Object[] r2_ObjectA = new Object[1];
        r2_ObjectA[0] = Crashlytics.getInstance().getVersion();
        this.n = String.format(r0_Locale, "Crashlytics Android SDK/%s", r2_ObjectA);
        this.i = 8;
        v.a().b().a(Crashlytics.TAG, "Checking for previous crash marker.");
        File r0_File = new File(v.a().h(), "crash_marker");
        if (r0_File.exists()) {
            r0_File.delete();
            if (r8_CrashlyticsListener != null) {
                try {
                    r8_CrashlyticsListener.crashlyticsDidDetectCrashDuringPreviousExecution();
                } catch (Exception e) {
                    v.a().b().a(Crashlytics.TAG, "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
                }
            }
        }
        this.q = ai.a(Crashlytics.d());
        this.r = r10_String == null ? null : ai.a(r10_String.replace("-", RContactStorage.PRIMARY_KEY));
        this.p = new l(this);
        IntentFilter r0_IntentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        this.o = new m(this);
        IntentFilter r1_IntentFilter = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
        Crashlytics.getInstance().getContext().registerReceiver(this.p, r0_IntentFilter);
        Crashlytics.getInstance().getContext().registerReceiver(this.o, r1_IntentFilter);
        this.h.set(true);
    }

    private static int a(float r2f, int r3i, boolean r4z, int r5i, long r6j, long r8j) {
        return am.b(1, r2f) + 0 + am.f(XListViewHeader.STATE_REFRESHING, r3i) + am.b((int)XListViewFooter.STATE_NOMORE, r4z) + am.d(XListViewFooter.STATE_NODATA, r5i) + am.b((int)ShareUtils.SHARE_SMS, r6j) + am.b((int)ShareUtils.SHARE_COPY, r8j);
    }

    private int a(int r7i, ai r8_ai, ai r9_ai, int r10i, long r11j, long r13j, boolean r15z, Map<ap, String> r16_Map_ap__String, int r17i, ai r18_ai, ai r19_ai) {
        int r3i;
        int r1i = (r9_ai == null ? 0 : am.b((int)XListViewFooter.STATE_NODATA, r9_ai)) + am.e(XListViewFooter.STATE_NOMORE, r7i) + am.b(1, r8_ai) + 0 + am.d(ShareUtils.SHARE_SMS, r10i) + am.b((int)ShareUtils.SHARE_COPY, r11j) + am.b((int)ShareUtils.SHARE_COLLECT, r13j) + am.b((int)REQUEST_CODE.REQUEST_CODE_EDIT_INTRO, r15z);
        if (r16_Map_ap__String != null) {
            Iterator r4_Iterator = r16_Map_ap__String.entrySet().iterator();
            r3i = r1i;
            while (r4_Iterator.hasNext()) {
                Entry r2_Entry = (Entry) r4_Iterator.next();
                r1i = a((ap) r2_Entry.getKey(), (String) r2_Entry.getValue());
                r3i = r1i + am.a((int)REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE) + am.c(r1i) + r3i;
            }
        } else {
            r3i = r1i;
        }
        return (r19_ai == null ? 0 : am.b((int)REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION, r19_ai)) + r3i + am.d(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH, r17i) + (r18_ai == null ? 0 : am.b((int)REQUEST_CODE.REQUEST_CODE_EDIT_GENDER, r18_ai));
    }

    private static int a(ap r3_ap, String r4_String) {
        return am.e(1, r3_ap.f) + am.b((int)XListViewHeader.STATE_REFRESHING, ai.a(r4_String));
    }

    private static int a(StackTraceElement r6_StackTraceElement, boolean r7z) {
        int r3i;
        int r0i = (r6_StackTraceElement.isNativeMethod() ? am.b(1, (long) Math.max(r6_StackTraceElement.getLineNumber(), 0)) + 0 : am.b(1, 0) + 0) + am.b((int)XListViewHeader.STATE_REFRESHING, ai.a(r6_StackTraceElement.getClassName() + "." + r6_StackTraceElement.getMethodName()));
        if (r6_StackTraceElement.getFileName() != null) {
            r0i += am.b((int)XListViewFooter.STATE_NOMORE, ai.a(r6_StackTraceElement.getFileName()));
        }
        r3i = (r6_StackTraceElement.isNativeMethod() || r6_StackTraceElement.getLineNumber() <= 0) ? r0i : r0i + am.b((int)XListViewFooter.STATE_NODATA, (long) r6_StackTraceElement.getLineNumber());
        return am.d(ShareUtils.SHARE_SMS, r7z ? 2 : 0) + r3i;
    }

    private static int a(String r3_String, String r4_String) {
        int r0i = am.b(1, ai.a(r3_String));
        int r1i = XListViewHeader.STATE_REFRESHING;
        if (r4_String == null) {
            r4_String = RContactStorage.PRIMARY_KEY;
        }
        return r0i + am.b(r1i, ai.a(r4_String));
    }

    private int a(Thread r7_Thread, Throwable r8_Throwable, Map<String, String> r9_Map_String__String) {
        int r2i;
        int r0i = b(r7_Thread, r8_Throwable);
        r0i = r0i + am.a(1) + am.c(r0i) + 0;
        if (r9_Map_String__String != null) {
            Iterator r4_Iterator = r9_Map_String__String.entrySet().iterator();
            r2i = r0i;
            while (r4_Iterator.hasNext()) {
                Entry r1_Entry = (Entry) r4_Iterator.next();
                r0i = a((String) r1_Entry.getKey(), (String) r1_Entry.getValue());
                r2i = r0i + am.a((int)XListViewHeader.STATE_REFRESHING) + am.c(r0i) + r2i;
            }
        } else {
            r2i = r0i;
        }
        if (this.t != null) {
            r2i += am.b((int)XListViewFooter.STATE_NOMORE, this.t.importance != 100);
        }
        return am.d(XListViewFooter.STATE_NODATA, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation) + r2i;
    }

    private int a(Thread r7_Thread, StackTraceElement[] r8_StackTraceElementA, int r9i, boolean r10z) {
        int r1i = am.d(XListViewHeader.STATE_REFRESHING, r9i) + am.b(1, ai.a(r7_Thread.getName()));
        int r2i = r8_StackTraceElementA.length;
        int r0i = 0;
        while (r0i < r2i) {
            int r3i = a(r8_StackTraceElementA[r0i], r10z);
            r1i += r3i + am.a((int)XListViewFooter.STATE_NOMORE) + am.c(r3i);
            r0i++;
        }
        return r1i;
    }

    private int a(Throwable r10_Throwable, int r11i) {
        int r1i = 0;
        int r0i = am.b(1, ai.a(r10_Throwable.getClass().getName())) + 0;
        String r2_String = r10_Throwable.getLocalizedMessage();
        if (r2_String != null) {
            r0i += am.b((int)XListViewFooter.STATE_NOMORE, ai.a(r2_String));
        }
        StackTraceElement[] r4_StackTraceElementA = r10_Throwable.getStackTrace();
        int r5i = r4_StackTraceElementA.length;
        int r2i = 0;
        while (r2i < r5i) {
            int r3i = a(r4_StackTraceElementA[r2i], true);
            r2i++;
            r0i = r3i + am.a((int)XListViewFooter.STATE_NODATA) + am.c(r3i) + r0i;
        }
        Throwable r2_Throwable = r10_Throwable.getCause();
        if (r2_Throwable == null) {
            return r0i;
        }
        if (r11i < 8) {
            r1i = a(r2_Throwable, r11i + 1);
            return r0i + r1i + am.a((int)ShareUtils.SHARE_COPY) + am.c(r1i);
        }
        while (r2_Throwable != null) {
            r2_Throwable = r2_Throwable.getCause();
            r1i++;
        }
        return r0i + am.d(ShareUtils.SHARE_COLLECT, r1i);
    }

    private ai a(aq r8_aq) {
        if (r8_aq == null) {
            return null;
        }
        int[] r1_intA = new int[1];
        r1_intA[0] = 0;
        byte[] r2_byteA = new byte[r8_aq.a()];
        try {
            r8_aq.a(new bd(this, r2_byteA, r1_intA));
        } catch (IOException e) {
            v.a().b().a(Crashlytics.TAG, "A problem occurred while reading the Crashlytics log file.", e);
        }
        return ai.a(r2_byteA, 0, r1_intA[0]);
    }

    private <T> T a(Callable<T> r6_Callable_T) {
        try {
            return this.s.submit(r6_Callable_T).get();
        } catch (RejectedExecutionException e) {
            v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Exception e_2) {
            v.a().b().a(Crashlytics.TAG, "Failed to execute task.", e_2);
            return null;
        }
    }

    private static String a(File r3_File) {
        return r3_File.getName().substring(0, AdViewUtil.NETWORK_TYPE_WQ);
    }

    private Future<?> a(Runnable r4_Runnable) {
        try {
            return this.s.submit(new g(this, r4_Runnable));
        } catch (RejectedExecutionException e) {
            v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    private static void a(ak r4_ak) {
        if (r4_ak != null) {
            try {
                r4_ak.a();
            } catch (IOException e) {
                v.a().b().a(Crashlytics.TAG, "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private void a(am r7_am, int r8i, StackTraceElement r9_StackTraceElement, boolean r10z) throws Exception {
        int r0i = XListViewFooter.STATE_NODATA;
        r7_am.g(r8i, XListViewHeader.STATE_REFRESHING);
        r7_am.b(a(r9_StackTraceElement, r10z));
        if (r9_StackTraceElement.isNativeMethod()) {
            r7_am.a(1, (long) Math.max(r9_StackTraceElement.getLineNumber(), 0));
        } else {
            r7_am.a(1, 0);
        }
        r7_am.a((int)XListViewHeader.STATE_REFRESHING, ai.a(r9_StackTraceElement.getClassName() + "." + r9_StackTraceElement.getMethodName()));
        if (r9_StackTraceElement.getFileName() != null) {
            r7_am.a((int)XListViewFooter.STATE_NOMORE, ai.a(r9_StackTraceElement.getFileName()));
        }
        if (r9_StackTraceElement.isNativeMethod() || r9_StackTraceElement.getLineNumber() <= 0) {
            if (r10z) {
                r0i = 0;
                r7_am.a((int)ShareUtils.SHARE_SMS, r0i);
            } else {
                r7_am.a((int)ShareUtils.SHARE_SMS, r0i);
            }
        } else {
            r7_am.a((int)XListViewFooter.STATE_NODATA, (long) r9_StackTraceElement.getLineNumber());
            if (r10z) {
                r0i = 0;
            }
            r7_am.a((int)ShareUtils.SHARE_SMS, r0i);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(am r5_am, File r6_File) throws IOException {
        /*
        r2 = 0;
        r0 = r6.exists();
        if (r0 == 0) goto L_0x0032;
    L_0x0007:
        r0 = r6.length();
        r0 = (int) r0;
        r3 = new byte[r0];
        r1 = new java.io.FileInputStream;	 //Catch:{ all -> 0x002a }
        r1.<init>(r6);	 //Catch:{ all -> 0x002a }
        r0 = 0;
    L_0x0014:
        r2 = r3.length;	 //Catch:{ all -> 0x0053 }
        if (r0 >= r2) goto L_0x0021;
    L_0x0017:
        r2 = r3.length;	 //Catch:{ all -> 0x0053 }
        r2 = r2 - r0;
        r2 = r1.read(r3, r0, r2);	 //Catch:{ all -> 0x0053 }
        if (r2 < 0) goto L_0x0021;
    L_0x001f:
        r0 = r0 + r2;
        goto L_0x0014;
    L_0x0021:
        r0 = "Failed to close file input stream.";
        com.crashlytics.android.internal.ab.a(r1, r0);
        r5.a(r3);
    L_0x0029:
        return;
    L_0x002a:
        r0 = move-exception;
        r1 = r2;
    L_0x002c:
        r2 = "Failed to close file input stream.";
        com.crashlytics.android.internal.ab.a(r1, r2);
        throw r0;
    L_0x0032:
        r0 = com.crashlytics.android.internal.v.a();
        r0 = r0.b();
        r1 = "Crashlytics";
        r3 = new java.lang.StringBuilder;
        r4 = "Tried to include a file that doesn't exist: ";
        r3.<init>(r4);
        r4 = r6.getName();
        r3 = r3.append(r4);
        r3 = r3.toString();
        r0.a(r1, r3, r2);
        goto L_0x0029;
    L_0x0053:
        r0 = move-exception;
        goto L_0x002c;
        */

    }

    private void a(am r11_am, String r12_String) throws IOException {
        String[] r2_StringA = new String[4];
        r2_StringA[0] = "SessionUser";
        r2_StringA[1] = "SessionApp";
        r2_StringA[2] = "SessionOS";
        r2_StringA[3] = "SessionDevice";
        int r3i = r2_StringA.length;
        int r0i = 0;
        while (r0i < r3i) {
            String r4_String = r2_StringA[r0i];
            File[] r5_FileA = a(new r(r12_String + r4_String));
            if (r5_FileA.length == 0) {
                v.a().b().a(Crashlytics.TAG, new StringBuilder("Can't find ").append(r4_String).append(" data for session ID ").append(r12_String).toString(), null);
            } else {
                v.a().b().a(Crashlytics.TAG, new StringBuilder("Collecting ").append(r4_String).append(" data for session ID ").append(r12_String).toString());
                a(r11_am, r5_FileA[0]);
            }
            r0i++;
        }
    }

    private void a(am r9_am, Thread r10_Thread, Throwable r11_Throwable) throws Exception {
        r9_am.g(1, XListViewHeader.STATE_REFRESHING);
        r9_am.b(b(r10_Thread, r11_Throwable));
        a(r9_am, r10_Thread, this.y, XListViewFooter.STATE_NODATA, true);
        int r7i = this.w.length;
        int r6i = 0;
        while (r6i < r7i) {
            a(r9_am, this.w[r6i], (StackTraceElement[]) this.x.get(r6i), 0, false);
            r6i++;
        }
        a(r9_am, r11_Throwable, 1, (int)XListViewHeader.STATE_REFRESHING);
        r9_am.g(XListViewFooter.STATE_NOMORE, XListViewHeader.STATE_REFRESHING);
        r9_am.b(s());
        r9_am.a(1, f);
        r9_am.a((int)XListViewHeader.STATE_REFRESHING, f);
        r9_am.a((int)XListViewFooter.STATE_NOMORE, 0);
        r9_am.g(XListViewFooter.STATE_NODATA, XListViewHeader.STATE_REFRESHING);
        r9_am.b(r());
        r9_am.a(1, 0);
        r9_am.a((int)XListViewHeader.STATE_REFRESHING, 0);
        r9_am.a((int)XListViewFooter.STATE_NOMORE, this.q);
        if (this.r != null) {
            r9_am.a((int)XListViewFooter.STATE_NODATA, this.r);
        }
    }

    private void a(am r5_am, Thread r6_Thread, StackTraceElement[] r7_StackTraceElementA, int r8i, boolean r9z) throws Exception {
        r5_am.g(1, XListViewHeader.STATE_REFRESHING);
        r5_am.b(a(r6_Thread, r7_StackTraceElementA, r8i, r9z));
        r5_am.a(1, ai.a(r6_Thread.getName()));
        r5_am.a((int)XListViewHeader.STATE_REFRESHING, r8i);
        int r1i = r7_StackTraceElementA.length;
        int r0i = 0;
        while (r0i < r1i) {
            a(r5_am, (int)XListViewFooter.STATE_NOMORE, r7_StackTraceElementA[r0i], r9z);
            r0i++;
        }
    }

    private void a(am r8_am, Throwable r9_Throwable, int r10i, int r11i) throws Exception {
        int r0i = 0;
        r8_am.g(r11i, XListViewHeader.STATE_REFRESHING);
        r8_am.b(a(r9_Throwable, 1));
        r8_am.a(1, ai.a(r9_Throwable.getClass().getName()));
        String r1_String = r9_Throwable.getLocalizedMessage();
        if (r1_String != null) {
            r8_am.a((int)XListViewFooter.STATE_NOMORE, ai.a(r1_String));
        }
        StackTraceElement[] r2_StackTraceElementA = r9_Throwable.getStackTrace();
        int r3i = r2_StackTraceElementA.length;
        int r1i = 0;
        while (r1i < r3i) {
            a(r8_am, (int)XListViewFooter.STATE_NODATA, r2_StackTraceElementA[r1i], true);
            r1i++;
        }
        Throwable r1_Throwable = r9_Throwable.getCause();
        if (r1_Throwable != null) {
            if (r10i < 8) {
                a(r8_am, r1_Throwable, r10i + 1, (int)ShareUtils.SHARE_COPY);
            }
            while (r1_Throwable != null) {
                r1_Throwable = r1_Throwable.getCause();
                r0i++;
            }
            r8_am.a((int)ShareUtils.SHARE_COLLECT, r0i);
        }
    }

    private void a(am r6_am, Map<String, String> r7_Map_String__String) throws Exception {
        Iterator r3_Iterator = r7_Map_String__String.entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            r6_am.g(XListViewHeader.STATE_REFRESHING, XListViewHeader.STATE_REFRESHING);
            r6_am.b(a((String) r0_Entry.getKey(), (String) r0_Entry.getValue()));
            r6_am.a(1, ai.a((String) r0_Entry.getKey()));
            String r0_String = (String) r0_Entry.getValue();
            if (r0_String == null) {
                r0_String = RContactStorage.PRIMARY_KEY;
            }
            r6_am.a((int)XListViewHeader.STATE_REFRESHING, ai.a(r0_String));
        }
    }

    private void a(am r11_am, File[] r12_FileA, String r13_String) {
        Arrays.sort(r12_FileA, ab.a);
        int r2i = r12_FileA.length;
        int r1i = 0;
        while (r1i < r2i) {
            File r0_File = r12_FileA[r1i];
            try {
                q r3_q = v.a().b();
                String r4_String = Crashlytics.TAG;
                Locale r5_Locale = Locale.US;
                Object[] r7_ObjectA = new Object[2];
                r7_ObjectA[0] = r13_String;
                r7_ObjectA[1] = r0_File.getName();
                r3_q.a(r4_String, String.format(r5_Locale, "Found Non Fatal for session ID %s in %s ", r7_ObjectA));
                a(r11_am, r0_File);
            } catch (Exception e) {
                v.a().b().a(Crashlytics.TAG, "Error writting non-fatal to session.", e);
            }
            r1i++;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(ba r8_ba, Date r9_Date, Thread r10_Thread, Throwable r11_Throwable) throws Exception {
        /*
        r2 = 0;
        r0 = new java.io.File;	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r1 = r8.k;	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r3 = "crash_marker";
        r0.<init>(r1, r3);	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r0.createNewFile();	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r0 = r8.n();	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        if (r0 == 0) goto L_0x0067;
    L_0x0013:
        com.crashlytics.android.Crashlytics.b(r0);	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r7 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r1 = r8.k;	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r3.<init>();	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r0 = r3.append(r0);	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r3 = "SessionCrash";
        r0 = r0.append(r3);	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r0 = r0.toString();	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r7.<init>(r1, r0);	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r2 = com.crashlytics.android.am.a(r7);	 //Catch:{ Exception -> 0x00a7 }
        r5 = "crash";
        r6 = 1;
        r0 = r8;
        r1 = r9;
        r3 = r10;
        r4 = r11;
        r0.a(r1, r2, r3, r4, r5, r6);	 //Catch:{ Exception -> 0x00a7 }
        r0 = r7;
    L_0x003f:
        r1 = "Failed to flush to session begin file.";
        com.crashlytics.android.internal.ab.a(r2, r1);
        r1 = "Failed to close fatal exception file output stream.";
        com.crashlytics.android.internal.ab.a(r0, r1);
    L_0x0049:
        r8.m();
        r8.l();
        r0 = r8.k;
        r1 = a;
        r2 = 4;
        r3 = c;
        com.crashlytics.android.ag.a(r0, r1, r2, r3);
        r0 = com.crashlytics.android.Crashlytics.getInstance();
        r0 = r0.j();
        if (r0 != 0) goto L_0x0066;
    L_0x0063:
        r8.p();
    L_0x0066:
        return;
    L_0x0067:
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r0 = r0.b();	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r1 = "Crashlytics";
        r3 = "Tried to write a fatal exception while no session was open.";
        r4 = 0;
        r0.a(r1, r3, r4);	 //Catch:{ Exception -> 0x0079, all -> 0x0098 }
        r0 = r2;
        goto L_0x003f;
    L_0x0079:
        r0 = move-exception;
        r7 = r2;
    L_0x007b:
        r1 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x00a5 }
        r1 = r1.b();	 //Catch:{ all -> 0x00a5 }
        r3 = "Crashlytics";
        r4 = "An error occurred in the fatal exception logger";
        r1.a(r3, r4, r0);	 //Catch:{ all -> 0x00a5 }
        r8.a(r0, r7);	 //Catch:{ all -> 0x00a5 }
        r0 = "Failed to flush to session begin file.";
        com.crashlytics.android.internal.ab.a(r2, r0);
        r0 = "Failed to close fatal exception file output stream.";
        com.crashlytics.android.internal.ab.a(r7, r0);
        goto L_0x0049;
    L_0x0098:
        r0 = move-exception;
        r7 = r2;
    L_0x009a:
        r1 = "Failed to flush to session begin file.";
        com.crashlytics.android.internal.ab.a(r2, r1);
        r1 = "Failed to close fatal exception file output stream.";
        com.crashlytics.android.internal.ab.a(r7, r1);
        throw r0;
    L_0x00a5:
        r0 = move-exception;
        goto L_0x009a;
    L_0x00a7:
        r0 = move-exception;
        goto L_0x007b;
        */

    }

    static void a(aq r6_aq, int r7i, long r8j, String r10_String) {
        if (r6_aq == null) {
        } else {
            String r0_String;
            r0_String = r10_String == null ? "null" : r10_String;
            try {
                if (r0_String.length() > 16384) {
                    r0_String = new StringBuilder("...").append(r0_String.substring(r0_String.length() - 16384)).toString();
                }
                r0_String = r0_String.replaceAll("\r", " ").replaceAll("\n", " ");
                Locale r1_Locale = Locale.US;
                Object[] r3_ObjectA = new Object[2];
                r3_ObjectA[0] = Long.valueOf(r8j);
                r3_ObjectA[1] = r0_String;
                r6_aq.a(String.format(r1_Locale, "%d %s%n", r3_ObjectA).getBytes(Base.UTF8));
                while (!r6_aq.b() && r6_aq.a() > 65536) {
                    r6_aq.c();
                }
            } catch (IOException e) {
                v.a().b().a(Crashlytics.TAG, "There was a problem writing to the Crashlytics log.", e);
            }
        }
    }

    private void a(String r5_String) {
        File[] r1_FileA = a(new s(r5_String));
        int r2i = r1_FileA.length;
        int r0i = 0;
        while (r0i < r2i) {
            r1_FileA[r0i].delete();
            r0i++;
        }
    }

    private void a(String r5_String, int r6i) {
        ag.a(this.k, new r(r5_String + "SessionEvent"), r6i, c);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(Throwable r6_Throwable, OutputStream r7_OutputStream) {
        /*
        r5_this = this;
        if (r7 == 0) goto L_0x0010;
    L_0x0002:
        r2 = 0;
        r1 = new java.io.PrintWriter;	 //Catch:{ Exception -> 0x0011, all -> 0x0028 }
        r1.<init>(r7);	 //Catch:{ Exception -> 0x0011, all -> 0x0028 }
        a(r6, r1);	 //Catch:{ Exception -> 0x0032 }
        r0 = "Failed to close stack trace writer.";
        com.crashlytics.android.internal.ab.a(r1, r0);
    L_0x0010:
        return;
    L_0x0011:
        r0 = move-exception;
        r1 = r2;
    L_0x0013:
        r2 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x0030 }
        r2 = r2.b();	 //Catch:{ all -> 0x0030 }
        r3 = "Crashlytics";
        r4 = "Failed to create PrintWriter";
        r2.a(r3, r4, r0);	 //Catch:{ all -> 0x0030 }
        r0 = "Failed to close stack trace writer.";
        com.crashlytics.android.internal.ab.a(r1, r0);
        goto L_0x0010;
    L_0x0028:
        r0 = move-exception;
        r1 = r2;
    L_0x002a:
        r2 = "Failed to close stack trace writer.";
        com.crashlytics.android.internal.ab.a(r1, r2);
        throw r0;
    L_0x0030:
        r0 = move-exception;
        goto L_0x002a;
    L_0x0032:
        r0 = move-exception;
        goto L_0x0013;
        */

    }

    private static void a(Throwable r7_Throwable, Writer r8_Writer) {
        int r3i = 1;
        while (r7_Throwable != null) {
            try {
                String r0_String = r7_Throwable.getLocalizedMessage();
                r0_String = r0_String == null ? null : r0_String.replaceAll("(\r\n|\n|\f)", " ");
                r8_Writer.write((r3i != 0 ? RContactStorage.PRIMARY_KEY : "Caused by: ") + r7_Throwable.getClass().getName() + ": " + (r0_String != null ? r0_String : RContactStorage.PRIMARY_KEY) + "\n");
                StackTraceElement[] r2_StackTraceElementA = r7_Throwable.getStackTrace();
                r3i = r2_StackTraceElementA.length;
                int r0i = 0;
                while (r0i < r3i) {
                    r8_Writer.write(new StringBuilder("\tat ").append(r2_StackTraceElementA[r0i].toString()).append("\n").toString());
                    r0i++;
                }
                r7_Throwable = r7_Throwable.getCause();
                r3i = 0;
            } catch (Exception e) {
                v.a().b().a(Crashlytics.TAG, "Could not write stack trace", e);
            }
        }
    }

    private void a(Date r21_Date, am r22_am, Thread r23_Thread, Throwable r24_Throwable, String r25_String, boolean r26z) throws Exception {
        Map r13_Map;
        Map r12_Map;
        long r15j = r21_Date.getTime() / 1000;
        float r4f = ab.b(Crashlytics.getInstance().getContext());
        int r5i = ab.a(this.v);
        boolean r6z = ab.c(Crashlytics.getInstance().getContext());
        int r7i = Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation;
        long r8j = ab.c() - ab.a(Crashlytics.getInstance().getContext());
        long r10j = ab.b(Environment.getDataDirectory().getPath());
        this.t = ab.a(Crashlytics.d(), Crashlytics.getInstance().getContext());
        this.x = new LinkedList();
        this.y = r24_Throwable.getStackTrace();
        if (r26z) {
            r13_Map = Thread.getAllStackTraces();
            this.w = new Thread[r13_Map.size()];
            Iterator r17_Iterator = r13_Map.entrySet().iterator();
            int r14i = 0;
            while (r17_Iterator.hasNext()) {
                Entry r12_Entry = (Entry) r17_Iterator.next();
                this.w[r14i] = (Thread) r12_Entry.getKey();
                this.x.add(r12_Entry.getValue());
                r14i++;
            }
        } else {
            this.w = new Thread[0];
        }
        ai r14_ai = a(this.u);
        if (r14_ai == null) {
            v.a().b().a(Crashlytics.TAG, "No log data to include with this event.");
        }
        ab.a(this.u, "There was a problem closing the Crashlytics log file.");
        this.u = null;
        if (ab.a(Crashlytics.getInstance().getContext(), "com.crashlytics.CollectCustomKeys", true)) {
            r13_Map = Crashlytics.getInstance().a();
            r12_Map = (r13_Map == null || r13_Map.size() <= 1) ? r13_Map : new TreeMap(r13_Map);
        } else {
            r12_Map = new TreeMap();
        }
        r22_am.g(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO, 2);
        int r17i = a(r23_Thread, r24_Throwable, r12_Map);
        r17i = a(r4f, r5i, r6z, r7i, r8j, r10j);
        int r13i = am.b(1, r15j) + 0 + am.b((int)XListViewHeader.STATE_REFRESHING, ai.a(r25_String)) + r17i + am.a((int)XListViewFooter.STATE_NOMORE) + am.c(r17i) + r17i + am.a((int)ShareUtils.SHARE_SMS) + am.c(r17i);
        if (r14_ai != null) {
            r17i = am.b(1, r14_ai);
            r13i += r17i + am.a((int)ShareUtils.SHARE_COPY) + am.c(r17i);
        }
        r22_am.b(r13i);
        r22_am.a(1, r15j);
        r22_am.a((int)XListViewHeader.STATE_REFRESHING, ai.a(r25_String));
        r22_am.g(XListViewFooter.STATE_NOMORE, XListViewHeader.STATE_REFRESHING);
        r22_am.b(a(r23_Thread, r24_Throwable, r12_Map));
        a(r22_am, r23_Thread, r24_Throwable);
        if (r12_Map == null || r12_Map.isEmpty()) {
            if (this.t == null) {
                r22_am.a((int)XListViewFooter.STATE_NOMORE, this.t.importance == 100);
            }
            r22_am.a((int)XListViewFooter.STATE_NODATA, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation);
            r22_am.g(ShareUtils.SHARE_SMS, XListViewHeader.STATE_REFRESHING);
            r22_am.b(a(r4f, r5i, r6z, r7i, r8j, r10j));
            r22_am.a(1, r4f);
            r22_am.c(XListViewHeader.STATE_REFRESHING, r5i);
            r22_am.a((int)XListViewFooter.STATE_NOMORE, r6z);
            r22_am.a((int)XListViewFooter.STATE_NODATA, r7i);
            r22_am.a((int)ShareUtils.SHARE_SMS, r8j);
            r22_am.a((int)ShareUtils.SHARE_COPY, r10j);
            if (r14_ai != null) {
                r22_am.g(ShareUtils.SHARE_COPY, XListViewHeader.STATE_REFRESHING);
                r22_am.b(am.b(1, r14_ai));
                r22_am.a(1, r14_ai);
            }
        } else {
            a(r22_am, r12_Map);
            if (this.t == null) {
                r22_am.a((int)XListViewFooter.STATE_NODATA, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation);
                r22_am.g(ShareUtils.SHARE_SMS, XListViewHeader.STATE_REFRESHING);
                r22_am.b(a(r4f, r5i, r6z, r7i, r8j, r10j));
                r22_am.a(1, r4f);
                r22_am.c(XListViewHeader.STATE_REFRESHING, r5i);
                r22_am.a((int)XListViewFooter.STATE_NOMORE, r6z);
                r22_am.a((int)XListViewFooter.STATE_NODATA, r7i);
                r22_am.a((int)ShareUtils.SHARE_SMS, r8j);
                r22_am.a((int)ShareUtils.SHARE_COPY, r10j);
                if (r14_ai != null) {
                } else {
                    r22_am.g(ShareUtils.SHARE_COPY, XListViewHeader.STATE_REFRESHING);
                    r22_am.b(am.b(1, r14_ai));
                    r22_am.a(1, r14_ai);
                }
            } else {
                if (this.t.importance == 100) {
                }
                r22_am.a((int)XListViewFooter.STATE_NOMORE, this.t.importance == 100);
                r22_am.a((int)XListViewFooter.STATE_NODATA, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation);
                r22_am.g(ShareUtils.SHARE_SMS, XListViewHeader.STATE_REFRESHING);
                r22_am.b(a(r4f, r5i, r6z, r7i, r8j, r10j));
                r22_am.a(1, r4f);
                r22_am.c(XListViewHeader.STATE_REFRESHING, r5i);
                r22_am.a((int)XListViewFooter.STATE_NOMORE, r6z);
                r22_am.a((int)XListViewFooter.STATE_NODATA, r7i);
                r22_am.a((int)ShareUtils.SHARE_SMS, r8j);
                r22_am.a((int)ShareUtils.SHARE_COPY, r10j);
                if (r14_ai != null) {
                    r22_am.g(ShareUtils.SHARE_COPY, XListViewHeader.STATE_REFRESHING);
                    r22_am.b(am.b(1, r14_ai));
                    r22_am.a(1, r14_ai);
                }
            }
        }
    }

    private File[] a(FilenameFilter r2_FilenameFilter) {
        File[] r0_FileA = this.k.listFiles(r2_FilenameFilter);
        return r0_FileA == null ? new File[0] : r0_FileA;
    }

    private int b(Thread r10_Thread, Throwable r11_Throwable) {
        int r0i = a(r10_Thread, this.y, (int)XListViewFooter.STATE_NODATA, true);
        int r4i = this.w.length;
        int r1i = 0;
        int r3i = r0i + am.a(1) + am.c(r0i) + 0;
        while (r1i < r4i) {
            r0i = a(this.w[r1i], (StackTraceElement[]) this.x.get(r1i), 0, false);
            r3i += r0i + am.a(1) + am.c(r0i);
            r1i++;
        }
        r0i = a(r11_Throwable, 1);
        r1i = s();
        r1i = r();
        return r0i + am.a((int)XListViewHeader.STATE_REFRESHING) + am.c(r0i) + r3i + r1i + am.a((int)XListViewFooter.STATE_NOMORE) + am.c(r1i) + r1i + am.a((int)XListViewFooter.STATE_NOMORE) + am.c(r1i);
    }

    private static ai b(String r1_String) {
        return r1_String == null ? null : ai.a(r1_String);
    }

    private <T> Future<T> b(Callable<T> r4_Callable_T) {
        try {
            return this.s.submit(new i(this, r4_Callable_T));
        } catch (RejectedExecutionException e) {
            v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void b(ba r9_ba, Date r10_Date, Thread r11_Thread, Throwable r12_Throwable) {
        /*
        r2 = 0;
        r8 = r9.n();
        if (r8 == 0) goto L_0x00b7;
    L_0x0007:
        com.crashlytics.android.Crashlytics.a(r8);
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r0 = r0.b();	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r1 = "Crashlytics";
        r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r4 = "Crashlytics is logging non-fatal exception \"";
        r3.<init>(r4);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r3 = r3.append(r12);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r4 = "\" from thread ";
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r4 = r11.getName();	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r3 = r3.toString();	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r0.a(r1, r3);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r0 = r9.g;	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r0 = r0.getAndIncrement();	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r0 = com.crashlytics.android.internal.ab.a(r0);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r1 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r1.<init>();	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r1 = r1.append(r8);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r3 = "SessionEvent";
        r1 = r1.append(r3);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r0 = r1.append(r0);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r0 = r0.toString();	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r7 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r1 = r9.k;	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r7.<init>(r1, r0);	 //Catch:{ Exception -> 0x007a, all -> 0x0099 }
        r2 = com.crashlytics.android.am.a(r7);	 //Catch:{ Exception -> 0x00cc, all -> 0x00c7 }
        r5 = "error";
        r6 = 0;
        r0 = r9;
        r1 = r10;
        r3 = r11;
        r4 = r12;
        r0.a(r1, r2, r3, r4, r5, r6);	 //Catch:{ Exception -> 0x00cc, all -> 0x00c7 }
        r0 = "Failed to flush to non-fatal file.";
        com.crashlytics.android.internal.ab.a(r2, r0);
        r0 = "Failed to close non-fatal file output stream.";
        com.crashlytics.android.internal.ab.a(r7, r0);
    L_0x0074:
        r0 = 64;
        r9.a(r8, r0);	 //Catch:{ Exception -> 0x00a6 }
    L_0x0079:
        return;
    L_0x007a:
        r0 = move-exception;
        r1 = r2;
    L_0x007c:
        r3 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x00ca }
        r3 = r3.b();	 //Catch:{ all -> 0x00ca }
        r4 = "Crashlytics";
        r5 = "An error occurred in the non-fatal exception logger";
        r3.a(r4, r5, r0);	 //Catch:{ all -> 0x00ca }
        r9.a(r0, r1);	 //Catch:{ all -> 0x00ca }
        r0 = "Failed to flush to non-fatal file.";
        com.crashlytics.android.internal.ab.a(r2, r0);
        r0 = "Failed to close non-fatal file output stream.";
        com.crashlytics.android.internal.ab.a(r1, r0);
        goto L_0x0074;
    L_0x0099:
        r0 = move-exception;
        r1 = r2;
    L_0x009b:
        r3 = "Failed to flush to non-fatal file.";
        com.crashlytics.android.internal.ab.a(r2, r3);
        r2 = "Failed to close non-fatal file output stream.";
        com.crashlytics.android.internal.ab.a(r1, r2);
        throw r0;
    L_0x00a6:
        r0 = move-exception;
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = "An error occurred when trimming non-fatal files.";
        r1.a(r2, r3, r0);
        goto L_0x0079;
    L_0x00b7:
        r0 = com.crashlytics.android.internal.v.a();
        r0 = r0.b();
        r1 = "Crashlytics";
        r3 = "Tried to write a non-fatal exception while no session was open.";
        r0.a(r1, r3, r2);
        goto L_0x0079;
    L_0x00c7:
        r0 = move-exception;
        r1 = r7;
        goto L_0x009b;
    L_0x00ca:
        r0 = move-exception;
        goto L_0x009b;
    L_0x00cc:
        r0 = move-exception;
        r1 = r7;
        goto L_0x007c;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(String r19_String) throws Exception {
        /*
        r18_this = this;
        r3 = 0;
        r2 = 0;
        r16 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r1 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r1 = r1.h();	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r4 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r4.<init>();	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r0 = r19;
        r4 = r4.append(r0);	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r5 = "SessionDevice";
        r4 = r4.append(r5);	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r4 = r4.toString();	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r0 = r16;
        r0.<init>(r1, r4);	 //Catch:{ Exception -> 0x013e, all -> 0x0134 }
        r15 = com.crashlytics.android.am.a(r16);	 //Catch:{ Exception -> 0x0140, all -> 0x0139 }
        r1 = new android.os.StatFs;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r2 = android.os.Environment.getDataDirectory();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r2 = r2.getPath();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1.<init>(r2);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r2 = com.crashlytics.android.internal.ab.b();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = android.os.Build.MODEL;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r4 = b(r3);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = android.os.Build.MANUFACTURER;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r13 = b(r3);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = android.os.Build.PRODUCT;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r14 = b(r3);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = java.lang.Runtime.getRuntime();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r5 = r3.availableProcessors();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r6 = com.crashlytics.android.internal.ab.c();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = r1.getBlockCount();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r8 = (long) r3;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = r1.getBlockSize();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r10 = (long) r1;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r8 = r8 * r10;
        r10 = com.crashlytics.android.internal.ab.d();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = com.crashlytics.android.Crashlytics.getInstance();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = r1.b();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = r1.e();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = com.crashlytics.android.ai.a(r3);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r11 = r1.f();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r12 = com.crashlytics.android.internal.ab.f();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 9;
        r17 = 2;
        r0 = r17;
        r15.g(r1, r0);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = r18;
        r1 = r1.a(r2, r3, r4, r5, r6, r8, r10, r11, r12, r13, r14);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r15.b(r1);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 1;
        r15.a(r1, r3);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 3;
        r15.b(r1, r2);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 4;
        r15.a(r1, r4);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 5;
        r15.a(r1, r5);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 6;
        r15.a(r1, r6);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 7;
        r15.a(r1, r8);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = 10;
        r15.a(r1, r10);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = r11.entrySet();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r4 = r1.iterator();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
    L_0x00b7:
        r1 = r4.hasNext();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        if (r1 == 0) goto L_0x0114;
    L_0x00bd:
        r1 = r4.next();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r0 = r1;
        r0 = (java.util.Map.Entry) r0;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r3 = r0;
        r1 = 11;
        r2 = 2;
        r15.g(r1, r2);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = r3.getKey();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = (com.crashlytics.android.internal.ap) r1;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r2 = r3.getValue();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r2 = (java.lang.String) r2;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = a(r1, r2);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r15.b(r1);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r2 = 1;
        r1 = r3.getKey();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = (com.crashlytics.android.internal.ap) r1;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = r1.f;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r15.b(r2, r1);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r2 = 2;
        r1 = r3.getValue();	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = (java.lang.String) r1;	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r1 = com.crashlytics.android.ai.a(r1);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        r15.a(r2, r1);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        goto L_0x00b7;
    L_0x00f9:
        r1 = move-exception;
        r2 = r15;
        r3 = r16;
    L_0x00fd:
        r0 = r18;
        r0.a(r1, r3);	 //Catch:{ all -> 0x0103 }
        throw r1;	 //Catch:{ all -> 0x0103 }
    L_0x0103:
        r1 = move-exception;
        r15 = r2;
        r16 = r3;
    L_0x0107:
        r2 = "Failed to flush session device info.";
        com.crashlytics.android.internal.ab.a(r15, r2);
        r2 = "Failed to close session device file.";
        r0 = r16;
        com.crashlytics.android.internal.ab.a(r0, r2);
        throw r1;
    L_0x0114:
        r1 = 12;
        r15.a(r1, r12);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
        if (r13 == 0) goto L_0x0120;
    L_0x011b:
        r1 = 13;
        r15.a(r1, r13);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
    L_0x0120:
        if (r14 == 0) goto L_0x0127;
    L_0x0122:
        r1 = 14;
        r15.a(r1, r14);	 //Catch:{ Exception -> 0x00f9, all -> 0x013c }
    L_0x0127:
        r1 = "Failed to flush session device info.";
        com.crashlytics.android.internal.ab.a(r15, r1);
        r1 = "Failed to close session device file.";
        r0 = r16;
        com.crashlytics.android.internal.ab.a(r0, r1);
        return;
    L_0x0134:
        r1 = move-exception;
        r15 = r2;
        r16 = r3;
        goto L_0x0107;
    L_0x0139:
        r1 = move-exception;
        r15 = r2;
        goto L_0x0107;
    L_0x013c:
        r1 = move-exception;
        goto L_0x0107;
    L_0x013e:
        r1 = move-exception;
        goto L_0x00fd;
    L_0x0140:
        r1 = move-exception;
        r3 = r16;
        goto L_0x00fd;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean k() {
        /*
        r7_this = this;
        r1 = 1;
        r0 = 0;
        r2 = com.crashlytics.android.Crashlytics.getInstance();
        r2 = r2.getContext();
        r3 = "com.crashlytics.CollectCustomLogs";
        r2 = com.crashlytics.android.internal.ab.a(r2, r3, r1);
        if (r2 != 0) goto L_0x0022;
    L_0x0012:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = "Preferences requested not to collect custom logs. Aborting log file creation.";
        r1.a(r2, r3);
    L_0x0021:
        return r0;
    L_0x0022:
        r2 = r7.u;
        r3 = new java.lang.StringBuilder;
        r4 = "Could not close log file: ";
        r3.<init>(r4);
        r4 = r7.u;
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.crashlytics.android.internal.ab.a(r2, r3);
        r3 = 0;
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x006f }
        r4 = "crashlytics-userlog-";
        r2.<init>(r4);	 //Catch:{ Exception -> 0x006f }
        r4 = java.util.UUID.randomUUID();	 //Catch:{ Exception -> 0x006f }
        r4 = r4.toString();	 //Catch:{ Exception -> 0x006f }
        r2 = r2.append(r4);	 //Catch:{ Exception -> 0x006f }
        r4 = ".temp";
        r2 = r2.append(r4);	 //Catch:{ Exception -> 0x006f }
        r4 = r2.toString();	 //Catch:{ Exception -> 0x006f }
        r2 = new java.io.File;	 //Catch:{ Exception -> 0x006f }
        r5 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x006f }
        r5 = r5.h();	 //Catch:{ Exception -> 0x006f }
        r2.<init>(r5, r4);	 //Catch:{ Exception -> 0x006f }
        r3 = new com.crashlytics.android.internal.aq;	 //Catch:{ Exception -> 0x008e }
        r3.<init>(r2);	 //Catch:{ Exception -> 0x008e }
        r7.u = r3;	 //Catch:{ Exception -> 0x008e }
        r2.delete();	 //Catch:{ Exception -> 0x008e }
        r0 = r1;
        goto L_0x0021;
    L_0x006f:
        r1 = move-exception;
        r2 = r3;
    L_0x0071:
        r3 = com.crashlytics.android.internal.v.a();
        r3 = r3.b();
        r4 = "Crashlytics";
        r5 = new java.lang.StringBuilder;
        r6 = "Could not create log file: ";
        r5.<init>(r6);
        r2 = r5.append(r2);
        r2 = r2.toString();
        r3.a(r4, r2, r1);
        goto L_0x0021;
    L_0x008e:
        r1 = move-exception;
        goto L_0x0071;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void l() throws Exception {
        /*
        r13_this = this;
        r2 = 0;
        r0 = new java.util.Date;
        r0.<init>();
        r1 = new com.crashlytics.android.aj;
        r3 = com.crashlytics.android.Crashlytics.getInstance();
        r3 = r3.b();
        r1.<init>(r3);
        r4 = r1.toString();
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r3 = "Crashlytics";
        r5 = new java.lang.StringBuilder;
        r6 = "Opening an new session with ID ";
        r5.<init>(r6);
        r5 = r5.append(r4);
        r5 = r5.toString();
        r1.a(r3, r5);
        r3 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r1 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r1 = r1.h();	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r5 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r5.<init>();	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r5 = r5.append(r4);	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r6 = "BeginSession";
        r5 = r5.append(r6);	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r5 = r5.toString();	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r3.<init>(r1, r5);	 //Catch:{ Exception -> 0x01d6, all -> 0x0225 }
        r1 = com.crashlytics.android.am.a(r3);	 //Catch:{ Exception -> 0x022d, all -> 0x0228 }
        r5 = 1;
        r6 = r13.n;	 //Catch:{ Exception -> 0x0231, all -> 0x022a }
        r6 = com.crashlytics.android.ai.a(r6);	 //Catch:{ Exception -> 0x0231, all -> 0x022a }
        r1.a(r5, r6);	 //Catch:{ Exception -> 0x0231, all -> 0x022a }
        r5 = 2;
        r6 = com.crashlytics.android.ai.a(r4);	 //Catch:{ Exception -> 0x0231, all -> 0x022a }
        r1.a(r5, r6);	 //Catch:{ Exception -> 0x0231, all -> 0x022a }
        r5 = 3;
        r6 = r0.getTime();	 //Catch:{ Exception -> 0x0231, all -> 0x022a }
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r6 / r8;
        r1.a(r5, r6);	 //Catch:{ Exception -> 0x0231, all -> 0x022a }
        r0 = "Failed to flush to session begin file.";
        com.crashlytics.android.internal.ab.a(r1, r0);
        r0 = "Failed to close begin session file.";
        com.crashlytics.android.internal.ab.a(r3, r0);
        r3 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r0 = r0.h();	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r1 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r1.<init>();	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r1 = r1.append(r4);	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r5 = "SessionApp";
        r1 = r1.append(r5);	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r1 = r1.toString();	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r3.<init>(r0, r1);	 //Catch:{ Exception -> 0x01ea, all -> 0x0215 }
        r1 = com.crashlytics.android.am.a(r3);	 //Catch:{ Exception -> 0x021e, all -> 0x0219 }
        r0 = com.crashlytics.android.Crashlytics.d();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = com.crashlytics.android.ai.a(r0);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r5 = com.crashlytics.android.Crashlytics.g();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r5 = com.crashlytics.android.ai.a(r5);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r6 = com.crashlytics.android.Crashlytics.f();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r6 = com.crashlytics.android.ai.a(r6);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = com.crashlytics.android.Crashlytics.h();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        com.crashlytics.android.ai.a(r7);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = com.crashlytics.android.Crashlytics.getInstance();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = r7.getContext();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = r7.getPackageCodePath();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        com.crashlytics.android.ai.a(r7);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = com.crashlytics.android.Crashlytics.getInstance();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = r7.b();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = r7.b();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r7 = com.crashlytics.android.ai.a(r7);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r8 = com.crashlytics.android.Crashlytics.e();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r8 = com.crashlytics.android.internal.ai.a(r8);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r8 = r8.a();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = 7;
        r10 = 2;
        r1.g(r9, r10);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = 1;
        r9 = com.crashlytics.android.am.b(r9, r0);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = r9 + 0;
        r10 = 2;
        r10 = com.crashlytics.android.am.b(r10, r5);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = r9 + r10;
        r10 = 3;
        r10 = com.crashlytics.android.am.b(r10, r6);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = r9 + r10;
        r10 = q();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r11 = 5;
        r11 = com.crashlytics.android.am.a(r11);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r12 = com.crashlytics.android.am.c(r10);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r11 = r11 + r12;
        r10 = r10 + r11;
        r9 = r9 + r10;
        r10 = 6;
        r10 = com.crashlytics.android.am.b(r10, r7);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = r9 + r10;
        r10 = 10;
        r10 = com.crashlytics.android.am.e(r10, r8);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = r9 + r10;
        r1.b(r9);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r9 = 1;
        r1.a(r9, r0);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = 2;
        r1.a(r0, r5);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = 3;
        r1.a(r0, r6);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = 5;
        r5 = 2;
        r1.g(r0, r5);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = q();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r1.b(r0);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = 1;
        r5 = com.crashlytics.android.Crashlytics.getInstance();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r5 = r5.getContext();	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r6 = 0;
        r5 = com.crashlytics.android.internal.r.a(r5, r6);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r1.a(r0, r5);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = 6;
        r1.a(r0, r7);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = 10;
        r1.b(r0, r8);	 //Catch:{ Exception -> 0x0221, all -> 0x021c }
        r0 = "Failed to flush to session app file.";
        com.crashlytics.android.internal.ab.a(r1, r0);
        r0 = "Failed to close session app file.";
        com.crashlytics.android.internal.ab.a(r3, r0);
        r1 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r0 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r0 = r0.h();	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r3.<init>();	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r3 = r3.append(r4);	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r5 = "SessionOS";
        r3 = r3.append(r5);	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r3 = r3.toString();	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r1.<init>(r0, r3);	 //Catch:{ Exception -> 0x01fe, all -> 0x0210 }
        r2 = com.crashlytics.android.am.a(r1);	 //Catch:{ Exception -> 0x0213 }
        r0 = android.os.Build.VERSION.RELEASE;	 //Catch:{ Exception -> 0x0213 }
        r0 = com.crashlytics.android.ai.a(r0);	 //Catch:{ Exception -> 0x0213 }
        r3 = android.os.Build.VERSION.CODENAME;	 //Catch:{ Exception -> 0x0213 }
        r3 = com.crashlytics.android.ai.a(r3);	 //Catch:{ Exception -> 0x0213 }
        r5 = com.crashlytics.android.internal.ab.e();	 //Catch:{ Exception -> 0x0213 }
        r6 = 8;
        r7 = 2;
        r2.g(r6, r7);	 //Catch:{ Exception -> 0x0213 }
        r6 = 1;
        r7 = 3;
        r6 = com.crashlytics.android.am.e(r6, r7);	 //Catch:{ Exception -> 0x0213 }
        r6 = r6 + 0;
        r7 = 2;
        r7 = com.crashlytics.android.am.b(r7, r0);	 //Catch:{ Exception -> 0x0213 }
        r6 = r6 + r7;
        r7 = 3;
        r7 = com.crashlytics.android.am.b(r7, r3);	 //Catch:{ Exception -> 0x0213 }
        r6 = r6 + r7;
        r7 = 4;
        r7 = com.crashlytics.android.am.b(r7, r5);	 //Catch:{ Exception -> 0x0213 }
        r6 = r6 + r7;
        r2.b(r6);	 //Catch:{ Exception -> 0x0213 }
        r6 = 1;
        r7 = 3;
        r2.b(r6, r7);	 //Catch:{ Exception -> 0x0213 }
        r6 = 2;
        r2.a(r6, r0);	 //Catch:{ Exception -> 0x0213 }
        r0 = 3;
        r2.a(r0, r3);	 //Catch:{ Exception -> 0x0213 }
        r0 = 4;
        r2.a(r0, r5);	 //Catch:{ Exception -> 0x0213 }
        r0 = "Failed to flush to session OS file.";
        com.crashlytics.android.internal.ab.a(r2, r0);
        r0 = "Failed to close session OS file.";
        com.crashlytics.android.internal.ab.a(r1, r0);
        r13.c(r4);
        return;
    L_0x01d6:
        r0 = move-exception;
        r1 = r2;
    L_0x01d8:
        r13.a(r0, r2);	 //Catch:{ all -> 0x01dc }
        throw r0;	 //Catch:{ all -> 0x01dc }
    L_0x01dc:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
    L_0x01df:
        r1 = "Failed to flush to session begin file.";
        com.crashlytics.android.internal.ab.a(r2, r1);
        r1 = "Failed to close begin session file.";
        com.crashlytics.android.internal.ab.a(r3, r1);
        throw r0;
    L_0x01ea:
        r0 = move-exception;
        r1 = r2;
    L_0x01ec:
        r13.a(r0, r1);	 //Catch:{ all -> 0x01f0 }
        throw r0;	 //Catch:{ all -> 0x01f0 }
    L_0x01f0:
        r0 = move-exception;
        r3 = r1;
        r1 = r2;
    L_0x01f3:
        r2 = "Failed to flush to session app file.";
        com.crashlytics.android.internal.ab.a(r1, r2);
        r1 = "Failed to close session app file.";
        com.crashlytics.android.internal.ab.a(r3, r1);
        throw r0;
    L_0x01fe:
        r0 = move-exception;
        r1 = r2;
    L_0x0200:
        r13.a(r0, r1);	 //Catch:{ all -> 0x0204 }
        throw r0;	 //Catch:{ all -> 0x0204 }
    L_0x0204:
        r0 = move-exception;
    L_0x0205:
        r3 = "Failed to flush to session OS file.";
        com.crashlytics.android.internal.ab.a(r2, r3);
        r2 = "Failed to close session OS file.";
        com.crashlytics.android.internal.ab.a(r1, r2);
        throw r0;
    L_0x0210:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0205;
    L_0x0213:
        r0 = move-exception;
        goto L_0x0200;
    L_0x0215:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x01f3;
    L_0x0219:
        r0 = move-exception;
        r1 = r2;
        goto L_0x01f3;
    L_0x021c:
        r0 = move-exception;
        goto L_0x01f3;
    L_0x021e:
        r0 = move-exception;
        r1 = r3;
        goto L_0x01ec;
    L_0x0221:
        r0 = move-exception;
        r2 = r1;
        r1 = r3;
        goto L_0x01ec;
    L_0x0225:
        r0 = move-exception;
        r3 = r2;
        goto L_0x01df;
    L_0x0228:
        r0 = move-exception;
        goto L_0x01df;
    L_0x022a:
        r0 = move-exception;
        r2 = r1;
        goto L_0x01df;
    L_0x022d:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x01d8;
    L_0x0231:
        r0 = move-exception;
        r2 = r3;
        goto L_0x01d8;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m() throws Exception {
        /*
        r18_this = this;
        r2 = new java.util.HashSet;
        r2.<init>();
        r3 = r18.o();
        r1 = b;
        java.util.Arrays.sort(r3, r1);
        r1 = 8;
        r4 = r3.length;
        r4 = java.lang.Math.min(r1, r4);
        r1 = 0;
    L_0x0016:
        if (r1 >= r4) goto L_0x0024;
    L_0x0018:
        r5 = r3[r1];
        r5 = a(r5);
        r2.add(r5);
        r1 = r1 + 1;
        goto L_0x0016;
    L_0x0024:
        r1 = new com.crashlytics.android.q;
        r3 = 0;
        r1.<init>();
        r0 = r18;
        r3 = r0.a(r1);
        r4 = r3.length;
        r1 = 0;
    L_0x0032:
        if (r1 >= r4) goto L_0x0070;
    L_0x0034:
        r5 = r3[r1];
        r6 = r5.getName();
        r7 = d;
        r7 = r7.matcher(r6);
        r7.matches();
        r8 = 1;
        r7 = r7.group(r8);
        r7 = r2.contains(r7);
        if (r7 != 0) goto L_0x006d;
    L_0x004e:
        r7 = com.crashlytics.android.internal.v.a();
        r7 = r7.b();
        r8 = "Crashlytics";
        r9 = new java.lang.StringBuilder;
        r10 = "Trimming open session file: ";
        r9.<init>(r10);
        r6 = r9.append(r6);
        r6 = r6.toString();
        r7.a(r8, r6);
        r5.delete();
    L_0x006d:
        r1 = r1 + 1;
        goto L_0x0032;
    L_0x0070:
        r1 = r18.n();
        if (r1 == 0) goto L_0x036d;
    L_0x0076:
        r4 = 0;
        r2 = 0;
        r3 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r0 = r18;
        r5 = r0.k;	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r6 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r6.<init>();	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r1 = r6.append(r1);	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r6 = "SessionUser";
        r1 = r1.append(r6);	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r1 = r1.toString();	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r3.<init>(r5, r1);	 //Catch:{ Exception -> 0x02e9, all -> 0x038c }
        r2 = com.crashlytics.android.am.a(r3);	 //Catch:{ Exception -> 0x0390 }
        r1 = com.crashlytics.android.Crashlytics.getInstance();	 //Catch:{ Exception -> 0x0390 }
        r1 = r1.m();	 //Catch:{ Exception -> 0x0390 }
        r4 = com.crashlytics.android.Crashlytics.getInstance();	 //Catch:{ Exception -> 0x0390 }
        r4 = r4.o();	 //Catch:{ Exception -> 0x0390 }
        r5 = com.crashlytics.android.Crashlytics.getInstance();	 //Catch:{ Exception -> 0x0390 }
        r6 = r5.n();	 //Catch:{ Exception -> 0x0390 }
        if (r1 != 0) goto L_0x0292;
    L_0x00b2:
        if (r4 != 0) goto L_0x0292;
    L_0x00b4:
        if (r6 != 0) goto L_0x0292;
    L_0x00b6:
        r1 = "Failed to flush session user file.";
        com.crashlytics.android.internal.ab.a(r2, r1);
        r1 = "Failed to close session user file.";
        com.crashlytics.android.internal.ab.a(r3, r1);
    L_0x00c0:
        r1 = com.crashlytics.android.Crashlytics.getInstance();
        r1 = r1.r();
        if (r1 == 0) goto L_0x035d;
    L_0x00ca:
        r8 = r1.a;
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = "Closing all open sessions.";
        r1.a(r2, r3);
        r9 = r18.o();
        if (r9 == 0) goto L_0x036c;
    L_0x00e1:
        r1 = r9.length;
        if (r1 <= 0) goto L_0x036c;
    L_0x00e4:
        r10 = r9.length;
        r1 = 0;
        r6 = r1;
    L_0x00e7:
        if (r6 >= r10) goto L_0x036c;
    L_0x00e9:
        r11 = r9[r6];
        r12 = a(r11);
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = new java.lang.StringBuilder;
        r4 = "Closing session: ";
        r3.<init>(r4);
        r3 = r3.append(r12);
        r3 = r3.toString();
        r1.a(r2, r3);
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = new java.lang.StringBuilder;
        r4 = "Collecting session parts for ID ";
        r3.<init>(r4);
        r3 = r3.append(r12);
        r3 = r3.toString();
        r1.a(r2, r3);
        r1 = new com.crashlytics.android.r;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2 = r2.append(r12);
        r3 = "SessionCrash";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        r0 = r18;
        r13 = r0.a(r1);
        if (r13 == 0) goto L_0x02fd;
    L_0x0147:
        r1 = r13.length;
        if (r1 <= 0) goto L_0x02fd;
    L_0x014a:
        r1 = 1;
        r2 = r1;
    L_0x014c:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r3 = "Crashlytics";
        r4 = java.util.Locale.US;
        r5 = "Session %s has fatal exception: %s";
        r7 = 2;
        r7 = new java.lang.Object[r7];
        r14 = 0;
        r7[r14] = r12;
        r14 = 1;
        r15 = java.lang.Boolean.valueOf(r2);
        r7[r14] = r15;
        r4 = java.lang.String.format(r4, r5, r7);
        r1.a(r3, r4);
        r1 = new com.crashlytics.android.r;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3 = r3.append(r12);
        r4 = "SessionEvent";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r1.<init>(r3);
        r0 = r18;
        r7 = r0.a(r1);
        if (r7 == 0) goto L_0x0301;
    L_0x018e:
        r1 = r7.length;
        if (r1 <= 0) goto L_0x0301;
    L_0x0191:
        r1 = 1;
    L_0x0192:
        r3 = com.crashlytics.android.internal.v.a();
        r3 = r3.b();
        r4 = "Crashlytics";
        r5 = java.util.Locale.US;
        r14 = "Session %s has non-fatal exceptions: %s";
        r15 = 2;
        r15 = new java.lang.Object[r15];
        r16 = 0;
        r15[r16] = r12;
        r16 = 1;
        r17 = java.lang.Boolean.valueOf(r1);
        r15[r16] = r17;
        r5 = java.lang.String.format(r5, r14, r15);
        r3.a(r4, r5);
        if (r2 != 0) goto L_0x01ba;
    L_0x01b8:
        if (r1 == 0) goto L_0x033f;
    L_0x01ba:
        r5 = 0;
        r3 = 0;
        r4 = new com.crashlytics.android.ak;	 //Catch:{ Exception -> 0x0304, all -> 0x0332 }
        r0 = r18;
        r14 = r0.k;	 //Catch:{ Exception -> 0x0304, all -> 0x0332 }
        r4.<init>(r14, r12);	 //Catch:{ Exception -> 0x0304, all -> 0x0332 }
        r3 = com.crashlytics.android.am.a(r4);	 //Catch:{ Exception -> 0x0383, all -> 0x037d }
        r5 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5 = r5.b();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r14 = "Crashlytics";
        r15 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r16 = "Collecting SessionStart data for session ID ";
        r15.<init>(r16);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r15 = r15.append(r12);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r15 = r15.toString();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5.a(r14, r15);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        a(r3, r11);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5 = 4;
        r11 = new java.util.Date;	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r11.<init>();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r14 = r11.getTime();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r14 = r14 / r16;
        r3.a(r5, r14);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5 = 5;
        r3.a(r5, r2);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r0 = r18;
        r0.a(r3, r12);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        if (r1 == 0) goto L_0x024e;
    L_0x0204:
        r1 = r7.length;	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        if (r1 <= r8) goto L_0x0393;
    L_0x0207:
        r1 = com.crashlytics.android.internal.v.a();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r1 = r1.b();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5 = "Crashlytics";
        r7 = java.util.Locale.US;	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r11 = "Trimming down to %d logged exceptions.";
        r14 = 1;
        r14 = new java.lang.Object[r14];	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r15 = 0;
        r16 = java.lang.Integer.valueOf(r8);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r14[r15] = r16;	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r7 = java.lang.String.format(r7, r11, r14);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r1.a(r5, r7);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r0 = r18;
        r0.a(r12, r8);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r1 = new com.crashlytics.android.r;	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5.<init>();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5 = r5.append(r12);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r7 = "SessionEvent";
        r5 = r5.append(r7);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r5 = r5.toString();	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r1.<init>(r5);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r0 = r18;
        r1 = r0.a(r1);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
    L_0x0249:
        r0 = r18;
        r0.a(r3, r1, r12);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
    L_0x024e:
        if (r2 == 0) goto L_0x0256;
    L_0x0250:
        r1 = 0;
        r1 = r13[r1];	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        a(r3, r1);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
    L_0x0256:
        r1 = 11;
        r2 = 1;
        r3.a(r1, r2);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r1 = 12;
        r2 = 3;
        r3.b(r1, r2);	 //Catch:{ Exception -> 0x0387, all -> 0x037d }
        r1 = "Error flushing session file stream";
        com.crashlytics.android.internal.ab.a(r3, r1);
        r1 = "Failed to close CLS file";
        com.crashlytics.android.internal.ab.a(r4, r1);
    L_0x026c:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = new java.lang.StringBuilder;
        r4 = "Removing session part files for ID ";
        r3.<init>(r4);
        r3 = r3.append(r12);
        r3 = r3.toString();
        r1.a(r2, r3);
        r0 = r18;
        r0.a(r12);
        r1 = r6 + 1;
        r6 = r1;
        goto L_0x00e7;
    L_0x0292:
        if (r1 != 0) goto L_0x0296;
    L_0x0294:
        r1 = "";
    L_0x0296:
        r7 = com.crashlytics.android.ai.a(r1);	 //Catch:{ Exception -> 0x0390 }
        if (r4 != 0) goto L_0x02dd;
    L_0x029c:
        r1 = 0;
        r5 = r1;
    L_0x029e:
        if (r6 != 0) goto L_0x02e3;
    L_0x02a0:
        r1 = 0;
        r4 = r1;
    L_0x02a2:
        r1 = 1;
        r1 = com.crashlytics.android.am.b(r1, r7);	 //Catch:{ Exception -> 0x0390 }
        r1 = r1 + 0;
        if (r5 == 0) goto L_0x02b1;
    L_0x02ab:
        r6 = 2;
        r6 = com.crashlytics.android.am.b(r6, r5);	 //Catch:{ Exception -> 0x0390 }
        r1 = r1 + r6;
    L_0x02b1:
        if (r4 == 0) goto L_0x02b9;
    L_0x02b3:
        r6 = 3;
        r6 = com.crashlytics.android.am.b(r6, r4);	 //Catch:{ Exception -> 0x0390 }
        r1 = r1 + r6;
    L_0x02b9:
        r6 = 6;
        r8 = 2;
        r2.g(r6, r8);	 //Catch:{ Exception -> 0x0390 }
        r2.b(r1);	 //Catch:{ Exception -> 0x0390 }
        r1 = 1;
        r2.a(r1, r7);	 //Catch:{ Exception -> 0x0390 }
        if (r5 == 0) goto L_0x02cb;
    L_0x02c7:
        r1 = 2;
        r2.a(r1, r5);	 //Catch:{ Exception -> 0x0390 }
    L_0x02cb:
        if (r4 == 0) goto L_0x02d1;
    L_0x02cd:
        r1 = 3;
        r2.a(r1, r4);	 //Catch:{ Exception -> 0x0390 }
    L_0x02d1:
        r1 = "Failed to flush session user file.";
        com.crashlytics.android.internal.ab.a(r2, r1);
        r1 = "Failed to close session user file.";
        com.crashlytics.android.internal.ab.a(r3, r1);
        goto L_0x00c0;
    L_0x02dd:
        r1 = com.crashlytics.android.ai.a(r4);	 //Catch:{ Exception -> 0x0390 }
        r5 = r1;
        goto L_0x029e;
    L_0x02e3:
        r1 = com.crashlytics.android.ai.a(r6);	 //Catch:{ Exception -> 0x0390 }
        r4 = r1;
        goto L_0x02a2;
    L_0x02e9:
        r1 = move-exception;
        r3 = r4;
    L_0x02eb:
        r0 = r18;
        r0.a(r1, r3);	 //Catch:{ all -> 0x02f1 }
        throw r1;	 //Catch:{ all -> 0x02f1 }
    L_0x02f1:
        r1 = move-exception;
    L_0x02f2:
        r4 = "Failed to flush session user file.";
        com.crashlytics.android.internal.ab.a(r2, r4);
        r2 = "Failed to close session user file.";
        com.crashlytics.android.internal.ab.a(r3, r2);
        throw r1;
    L_0x02fd:
        r1 = 0;
        r2 = r1;
        goto L_0x014c;
    L_0x0301:
        r1 = 0;
        goto L_0x0192;
    L_0x0304:
        r1 = move-exception;
        r2 = r3;
        r3 = r5;
    L_0x0307:
        r4 = com.crashlytics.android.internal.v.a();	 //Catch:{ all -> 0x037f }
        r4 = r4.b();	 //Catch:{ all -> 0x037f }
        r5 = "Crashlytics";
        r7 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x037f }
        r11 = "Failed to write session file for session ID: ";
        r7.<init>(r11);	 //Catch:{ all -> 0x037f }
        r7 = r7.append(r12);	 //Catch:{ all -> 0x037f }
        r7 = r7.toString();	 //Catch:{ all -> 0x037f }
        r4.a(r5, r7, r1);	 //Catch:{ all -> 0x037f }
        r0 = r18;
        r0.a(r1, r3);	 //Catch:{ all -> 0x037f }
        r1 = "Error flushing session file stream";
        com.crashlytics.android.internal.ab.a(r2, r1);
        a(r3);
        goto L_0x026c;
    L_0x0332:
        r1 = move-exception;
        r4 = r5;
    L_0x0334:
        r2 = "Error flushing session file stream";
        com.crashlytics.android.internal.ab.a(r3, r2);
        r2 = "Failed to close CLS file";
        com.crashlytics.android.internal.ab.a(r4, r2);
        throw r1;
    L_0x033f:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = new java.lang.StringBuilder;
        r4 = "No events present for session ID ";
        r3.<init>(r4);
        r3 = r3.append(r12);
        r3 = r3.toString();
        r1.a(r2, r3);
        goto L_0x026c;
    L_0x035d:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = "No session begin files found.";
        r1.a(r2, r3);
    L_0x036c:
        return;
    L_0x036d:
        r1 = com.crashlytics.android.internal.v.a();
        r1 = r1.b();
        r2 = "Crashlytics";
        r3 = "Unable to close session. Settings are not loaded.";
        r1.a(r2, r3);
        goto L_0x036c;
    L_0x037d:
        r1 = move-exception;
        goto L_0x0334;
    L_0x037f:
        r1 = move-exception;
        r4 = r3;
        r3 = r2;
        goto L_0x0334;
    L_0x0383:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x0307;
    L_0x0387:
        r1 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x0307;
    L_0x038c:
        r1 = move-exception;
        r3 = r4;
        goto L_0x02f2;
    L_0x0390:
        r1 = move-exception;
        goto L_0x02eb;
    L_0x0393:
        r1 = r7;
        goto L_0x0249;
        */

    }

    private String n() {
        File[] r0_FileA = a(new r("BeginSession"));
        Arrays.sort(r0_FileA, b);
        return r0_FileA.length > 0 ? a(r0_FileA[0]) : null;
    }

    private File[] o() {
        return a(new r("BeginSession"));
    }

    private void p() {
        File[] r1_FileA = a(a);
        int r2i = r1_FileA.length;
        int r0i = 0;
        while (r0i < r2i) {
            File r3_File = r1_FileA[r0i];
            v.a().b().a(Crashlytics.TAG, "Attempting to send crash report at time of crash...");
            new Thread(new d(this, r3_File), "Crashlytics Report Uploader").start();
            r0i++;
        }
    }

    private static int q() {
        return am.b(1, ai.a(r.a(Crashlytics.getInstance().getContext(), v.a().f()))) + 0;
    }

    private int r() {
        int r0i = am.b(1, 0) + 0 + am.b((int)XListViewHeader.STATE_REFRESHING, 0) + am.b((int)XListViewFooter.STATE_NOMORE, this.q);
        return this.r != null ? r0i + am.b((int)XListViewFooter.STATE_NODATA, this.r) : r0i;
    }

    private static int s() {
        return am.b(1, f) + 0 + am.b((int)XListViewHeader.STATE_REFRESHING, f) + am.b((int)XListViewFooter.STATE_NOMORE, 0);
    }

    final void a(long r2j, String r4_String) {
        b(new bc(this, r2j, r4_String));
    }

    final void a(Thread r3_Thread, Throwable r4_Throwable) {
        a(new p(this, new Date(), r3_Thread, r4_Throwable));
    }

    final void a(File[] r12_FileA) {
        int r0i;
        File r2_File = new File(v.a().h(), "invalidClsFiles");
        if (r2_File.exists()) {
            if (r2_File.isDirectory()) {
                File[] r3_FileA = r2_File.listFiles();
                int r4i = r3_FileA.length;
                r0i = 0;
                while (r0i < r4i) {
                    r3_FileA[r0i].delete();
                    r0i++;
                }
            }
            r2_File.delete();
        }
        int r3i = r12_FileA.length;
        int r2i = 0;
        while (r2i < r3i) {
            File r0_File = r12_FileA[r2i];
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Found invalid session part file: ").append(r0_File).toString());
            String r0_String = a(r0_File);
            FilenameFilter r4_FilenameFilter = new f(this, r0_String);
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Deleting all part files for invalid session: ").append(r0_String).toString());
            File[] r4_FileA = a(r4_FilenameFilter);
            int r5i = r4_FileA.length;
            r0i = 0;
            while (r0i < r5i) {
                File r6_File = r4_FileA[r0i];
                v.a().b().a(Crashlytics.TAG, new StringBuilder("Deleting session file: ").append(r6_File).toString());
                r6_File.delete();
                r0i++;
            }
            r2i++;
        }
    }

    final boolean a() {
        return this.m.get();
    }

    final boolean b() {
        return ((Boolean) a(new o(this))).booleanValue();
    }

    final void c() {
        b(new be(this));
    }

    final void d() {
        b(new a(this));
    }

    final void e() {
        b(new b(this));
    }

    final boolean f() {
        return ((Boolean) a(new c(this))).booleanValue();
    }

    final boolean g() {
        return o().length > 0;
    }

    final void h() {
        a(new e(this));
    }

    public final synchronized void uncaughtException(Thread r5_Thread, Throwable r6_Throwable) {
        try {
            this.m.set(true);
            v.a().b().a(Crashlytics.TAG, new StringBuilder("Crashlytics is handling uncaught exception \"").append(r6_Throwable).append("\" from thread ").append(r5_Thread.getName()).toString());
            if (!this.h.getAndSet(true)) {
                v.a().b().a(Crashlytics.TAG, "Unregistering power receivers.");
                Crashlytics.getInstance().getContext().unregisterReceiver(this.p);
                Crashlytics.getInstance().getContext().unregisterReceiver(this.o);
            }
            a(new n(this, new Date(), r5_Thread, r6_Throwable));
            v.a().b().a(Crashlytics.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(r5_Thread, r6_Throwable);
            this.m.set(false);
        } catch (Throwable th) {
        }
    }
}