package com.crashlytics.android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.crashlytics.android.internal.A;
import com.crashlytics.android.internal.D;
import com.crashlytics.android.internal.aM;
import com.crashlytics.android.internal.aQ;
import com.crashlytics.android.internal.aR;
import com.crashlytics.android.internal.aS;
import com.crashlytics.android.internal.aX;
import com.crashlytics.android.internal.ab;
import com.crashlytics.android.internal.af;
import com.crashlytics.android.internal.ag;
import com.crashlytics.android.internal.ai;
import com.crashlytics.android.internal.ao;
import com.crashlytics.android.internal.av;
import com.crashlytics.android.internal.ax;
import com.crashlytics.android.internal.ay;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.u;
import com.crashlytics.android.internal.v;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;

// compiled from: SourceFile
public final class Crashlytics extends u {
    public static final String TAG = "Crashlytics";
    private static ContextWrapper j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static boolean r;
    private static PinningInfoProvider s;
    private static av t;
    private static float u;
    private static Crashlytics v;
    private final long a;
    private final ConcurrentHashMap<String, String> b;
    private CrashlyticsListener c;
    private ba d;
    private ao e;
    private String f;
    private String g;
    private String h;
    private String i;

    static {
        r = false;
        s = null;
    }

    public Crashlytics() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.b = new ConcurrentHashMap();
        this.a = System.currentTimeMillis();
    }

    private ag a(y r12_y) {
        String[] r0_StringA = new String[1];
        r0_StringA[0] = this.i;
        return new ag(p, k, o, n, ab.a(r0_StringA), m, ai.a(l).a(), q, "0", r12_y);
    }

    private static void a(int r5i, String r6_String, String r7_String) {
        Crashlytics r0_Crashlytics = getInstance();
        if (r0_Crashlytics == null || r0_Crashlytics.d == null) {
            v.a().b().a(r6_String, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging messages.", null);
        } else {
            r0_Crashlytics.d.a(System.currentTimeMillis() - r0_Crashlytics.a, ab.b(r5i) + "/" + r6_String + " " + r7_String);
        }
    }

    static void a(String r2_String) {
        D r0_D = (D) v.a().a(D.class);
        if (r0_D != null) {
            r0_D.a(new ag(r2_String));
        }
    }

    private synchronized void a(String r7_String, Context r8_Context, float r9f) {
        boolean r1z = false;
        synchronized (this) {
            if (j != null) {
                v.a().b().a(TAG, "Crashlytics already started, ignoring re-initialization attempt.");
            } else {
                p = r7_String;
                j = new ContextWrapper(r8_Context.getApplicationContext());
                t = new av(v.a().b());
                v.a().b().b(TAG, new StringBuilder("Initializing Crashlytics ").append(getCrashlyticsVersion()).toString());
                try {
                    k = j.getPackageName();
                    PackageManager r0_PackageManager = j.getPackageManager();
                    l = r0_PackageManager.getInstallerPackageName(k);
                    v.a().b().a(TAG, new StringBuilder("Installer package name is: ").append(l).toString());
                    PackageInfo r0_PackageInfo = r0_PackageManager.getPackageInfo(k, 0);
                    n = Integer.toString(r0_PackageInfo.versionCode);
                    o = r0_PackageInfo.versionName == null ? "0.0" : r0_PackageInfo.versionName;
                    m = r8_Context.getPackageManager().getApplicationLabel(r8_Context.getApplicationInfo()).toString();
                    q = Integer.toString(r8_Context.getApplicationInfo().targetSdkVersion);
                    this.i = ab.i(r8_Context);
                } catch (Exception e) {
                    v.a().b().a(TAG, "Error setting up app properties", e);
                }
                this.e = new ao(j);
                this.e.h();
                new ah(this.i, ab.a(j, "com.crashlytics.RequireBuildId", true)).a(r7_String, k);
                try {
                    v.a().b().a(TAG, "Installing exception handler...");
                    this.d = new ba(Thread.getDefaultUncaughtExceptionHandler(), this.c, this.i);
                    r1z = this.d.f();
                    this.d.d();
                    this.d.c();
                    this.d.h();
                    Thread.setDefaultUncaughtExceptionHandler(this.d);
                    v.a().b().a(TAG, "Successfully installed exception handler.");
                } catch (Exception e_2) {
                    v.a().b().a(TAG, "There was a problem installing the exception handler.", e_2);
                }
                CountDownLatch r0_CountDownLatch = new CountDownLatch(1);
                new Thread(new ay(this, r8_Context, r9f, r0_CountDownLatch), "Crashlytics Initializer").start();
                if (r1z) {
                    v.a().b().a(TAG, "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
                    try {
                        if (!r0_CountDownLatch.await(4000, TimeUnit.MILLISECONDS)) {
                            v.a().b().c(TAG, "Crashlytics initialization was not completed in the allotted time.");
                        }
                    } catch (InterruptedException e_3) {
                        v.a().b().a(TAG, "Crashlytics was interrupted during initialization.", e_3);
                    }
                }
            }
        }
    }

    static void a(boolean r3z) {
        ab.a().edit().putBoolean("always_send_reports_opt_in", true).commit();
    }

    private boolean a(Context r11_Context, float r12f) {
        aX r2_aX;
        boolean r1z;
        boolean r7z = true;
        int r6i = 0;
        String r9_String = ab.g(getContext());
        try {
            aS.a().a(r11_Context, t, n, o, i()).c();
            r2_aX = aS.a().b();
        } catch (Exception e) {
            v.a().b().a(TAG, "Error dealing with settings", e);
            r2_aX = null;
        }
        boolean r0z;
        if (r2_aX != null) {
            try {
                aM r0_aM = r2_aX.a;
                if ("new".equals(r0_aM.a)) {
                    if (new t(i(), r0_aM.b, t).a(a(y.a(getContext(), r9_String)))) {
                        r0z = aS.a().d();
                    } else {
                        v.a().b().a(TAG, "Failed to create app with Crashlytics service.", null);
                        r0z = false;
                    }
                } else if ("configured".equals(r0_aM.a)) {
                    r0z = aS.a().d();
                } else {
                    if (r0_aM.d) {
                        v.a().b().a(TAG, "Server says an update is required - forcing a full App update.");
                        new ae(i(), r0_aM.b, t).a(a(y.a(getContext(), r9_String)));
                    }
                    r0z = true;
                }
                r1z = r0z;
            } catch (Exception e_2) {
                v.a().b().a(TAG, "Error performing auto configuration.", e_2);
                r1z = false;
            }
            try {
                r0z = r2_aX.d.b;
            } catch (Exception e_3) {
                v.a().b().a(TAG, "Error getting collect reports setting.", e_3);
                r0z = false;
            }
        } else {
            r0z = false;
            r1z = false;
        }
        if (r1z && r0z) {
            try {
                r7z = this.d.b() & 1;
                v r0_v = q();
                if (r0_v != null) {
                    new ab(r0_v).a(r12f);
                }
            } catch (Exception e_4) {
                v.a().b().a(TAG, "Error sending crash report", e_4);
            }
        } else {
            r6i = 1;
        }
        if (r6i != 0) {
            v.a().b().a(TAG, "Crash reporting disabled.");
        }
        return r7z;
    }

    static /* synthetic */ boolean a(Crashlytics r6_Crashlytics, Activity r7_Activity, aQ r8_aQ) {
        x r4_x = new x(r7_Activity, r8_aQ);
        az r3_az = new az(r6_Crashlytics, (byte) 0);
        r7_Activity.runOnUiThread(new au(r6_Crashlytics, r7_Activity, r3_az, r4_x, r8_aQ));
        v.a().b().a(TAG, "Waiting for user opt-in.");
        r3_az.b();
        return r3_az.a();
    }

    static void b(String r2_String) {
        D r0_D = (D) v.a().a(D.class);
        if (r0_D != null) {
            r0_D.a(new af(r2_String));
        }
    }

    private static String c(String r2_String) {
        if (r2_String == null) {
            return r2_String;
        }
        r2_String = r2_String.trim();
        return r2_String.length() > 1024 ? r2_String.substring(0, Util.BYTE_OF_KB) : r2_String;
    }

    static String d() {
        return k;
    }

    static String e() {
        return l;
    }

    static String f() {
        return o;
    }

    static String g() {
        return n;
    }

    public static String getCrashlyticsVersion() {
        return getInstance().getVersion();
    }

    public static synchronized Crashlytics getInstance() {
        Crashlytics r0_Crashlytics;
        synchronized (Crashlytics.class) {
            r0_Crashlytics = (Crashlytics) v.a().a(Crashlytics.class);
            if (r0_Crashlytics != null) {
            } else {
                if (v == null) {
                    v = new Crashlytics();
                }
                r0_Crashlytics = v;
            }
        }
        return r0_Crashlytics;
    }

    public static PinningInfoProvider getPinningInfoProvider() {
        return s;
    }

    static String h() {
        return m;
    }

    static String i() {
        return ab.a(j, "com.crashlytics.ApiEndpoint");
    }

    static boolean k() {
        return ab.a().getBoolean("always_send_reports_opt_in", false);
    }

    public static void log(int r4i, String r5_String, String r6_String) {
        a(r4i, r5_String, r6_String);
        v.a().b().a(r4i, r5_String, r6_String, true);
    }

    public static void log(String r2_String) {
        a((int)XListViewFooter.STATE_NOMORE, TAG, r2_String);
    }

    public static void logException(Throwable r4_Throwable) {
        Crashlytics r0_Crashlytics = getInstance();
        if (r0_Crashlytics == null || r0_Crashlytics.d == null) {
            v.a().b().a(TAG, "Crashlytics must be initialized by calling Crashlytics.start(Context) prior to logging exceptions.", null);
        } else if (r4_Throwable == null) {
            v.a().b().a((int)ShareUtils.SHARE_SMS, TAG, "Crashlytics is ignoring a request to log a null exception.");
        } else {
            r0_Crashlytics.d.a(Thread.currentThread(), r4_Throwable);
        }
    }

    public static void setApplicationInstallationIdentifier(String r2_String) {
        v.a().a(c(r2_String));
    }

    public static void setBool(String r1_String, boolean r2z) {
        setString(r1_String, Boolean.toString(r2z));
    }

    public static void setDouble(String r1_String, double r2d) {
        setString(r1_String, Double.toString(r2d));
    }

    public static void setFloat(String r1_String, float r2f) {
        setString(r1_String, Float.toString(r2f));
    }

    public static void setInt(String r1_String, int r2i) {
        setString(r1_String, Integer.toString(r2i));
    }

    public static void setLong(String r1_String, long r2j) {
        setString(r1_String, Long.toString(r2j));
    }

    public static void setPinningInfoProvider(PinningInfoProvider r2_PinningInfoProvider) {
        if (s != r2_PinningInfoProvider) {
            s = r2_PinningInfoProvider;
            if (t != null) {
                if (r2_PinningInfoProvider == null) {
                    t.a(null);
                } else {
                    t.a(new ap(r2_PinningInfoProvider));
                }
            }
        }
    }

    public static void setString(String r4_String, String r5_String) {
        if (r4_String == null) {
            if (j == null || (!ab.f(j))) {
                v.a().b().a(TAG, "Attempting to set custom attribute with null key, ignoring.", null);
            } else {
                throw new IllegalArgumentException("Custom attribute key cannot be null.");
            }
        } else {
            String r1_String = c(r4_String);
            if (getInstance().b.size() < 64 || getInstance().b.containsKey(r1_String)) {
                getInstance().b.put(r1_String, r5_String == null ? RContactStorage.PRIMARY_KEY : c(r5_String));
            } else {
                v.a().b().a(TAG, "Exceeded maximum number of custom attributes (64)");
            }
        }
    }

    public static void setUserEmail(String r2_String) {
        getInstance().g = c(r2_String);
    }

    public static void setUserIdentifier(String r2_String) {
        getInstance().f = c(r2_String);
    }

    public static void setUserName(String r2_String) {
        getInstance().h = c(r2_String);
    }

    public static void start(Context r1_Context) {
        start(r1_Context, 1.0f);
    }

    public static void start(Context r3_Context, float r4f) {
        u = r4f;
        if (!ab.d(r3_Context)) {
            v.a().a(new A());
        }
        u[] r0_uA = new u[2];
        r0_uA[0] = getInstance();
        r0_uA[1] = new D();
        v.a(r3_Context, r0_uA);
    }

    final Map<String, String> a() {
        return Collections.unmodifiableMap(this.b);
    }

    final ao b() {
        return this.e;
    }

    protected final void c() {
        Context r0_Context = super.getContext();
        String r1_String = r.a(r0_Context, false);
        if (r1_String == null) {
        } else {
            try {
                a(r1_String, r0_Context, u);
            } catch (CrashlyticsMissingDependencyException e) {
                throw e;
            } catch (Exception e_2) {
                v.a().b().a(TAG, "Crashlytics was not started due to an exception during initialization", e_2);
            }
        }
    }

    public final void crash() {
        new CrashTest().indexOutOfBounds();
    }

    public final boolean getDebugMode() {
        return v.a().f();
    }

    public final String getVersion() {
        return v.a().getVersion();
    }

    final boolean j() {
        return ((Boolean) aS.a().a(new aq(this), Boolean.valueOf(false))).booleanValue();
    }

    final ba l() {
        return this.d;
    }

    final String m() {
        return this.e.a() ? this.f : null;
    }

    final String n() {
        return this.e.a() ? this.g : null;
    }

    final String o() {
        return this.e.a() ? this.h : null;
    }

    final boolean p() {
        return ((Boolean) aS.a().a(new ar(this), Boolean.valueOf(true))).booleanValue();
    }

    final v q() {
        return (v) aS.a().a(new as(this), null);
    }

    final aR r() {
        return (aR) aS.a().a(new at(this), null);
    }

    public final void setDebugMode(boolean r2z) {
        v.a().a(r2z);
    }

    public final void setListener(CrashlyticsListener r1_CrashlyticsListener) {
        this.c = r1_CrashlyticsListener;
    }

    public final boolean verifyPinning(URL r6_URL) {
        try {
            if (getPinningInfoProvider() == null) {
                return false;
            }
            ay r2_ay = t.a(ax.a, r6_URL.toString());
            ((HttpsURLConnection) r2_ay.a()).setInstanceFollowRedirects(false);
            r2_ay.b();
            return true;
        } catch (Exception e) {
            v.a().b().a(TAG, "Could not verify SSL pinning", e);
            return false;
        }
    }
}