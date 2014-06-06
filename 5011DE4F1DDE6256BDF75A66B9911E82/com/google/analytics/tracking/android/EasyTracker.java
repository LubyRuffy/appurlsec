package com.google.analytics.tracking.android;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import qsbk.app.Constants;

public class EasyTracker {
    private static EasyTracker a;
    private boolean b;
    private String c;
    private String d;
    private String e;
    private int f;
    private boolean g;
    private Double h;
    private boolean i;
    private boolean j;
    private UncaughtExceptionHandler k;
    private boolean l;
    private int m;
    private long n;
    private long o;
    private Context p;
    private final Map<String, String> q;
    private Tracker r;
    private ae s;
    private Analytics t;
    private ServiceManager u;
    private f v;
    private Timer w;
    private TimerTask x;
    private boolean y;

    class a implements Tracker {
        private String b;
        private String c;
        private double d;
        private boolean e;
        private boolean f;
        private ExceptionParser g;

        a() {
            this.d = 100.0d;
        }

        public boolean anonymizeIpEnabled() {
            return this.e;
        }

        public void close() {
        }

        public Map<String, String> constructException(String r2_String, boolean r3z) {
            return new HashMap();
        }

        public Map<String, String> constructRawException(String r2_String, Throwable r3_Throwable, boolean r4z) {
            return new HashMap();
        }

        public Map<String, String> constructTiming(String r2_String, long r3j, String r5_String, String r6_String) {
            return new HashMap();
        }

        public Map<String, String> constructTransaction(Transaction r2_Transaction) {
            return new HashMap();
        }

        public String get(String r2_String) {
            return RContactStorage.PRIMARY_KEY;
        }

        public String getAppId() {
            return this.b;
        }

        public String getAppInstallerId() {
            return this.c;
        }

        public ExceptionParser getExceptionParser() {
            return this.g;
        }

        public double getSampleRate() {
            return this.d;
        }

        public String getTrackingId() {
            return RContactStorage.PRIMARY_KEY;
        }

        public boolean getUseSecure() {
            return this.f;
        }

        public void send(String r1_String, Map<String, String> r2_Map_String__String) {
        }

        public void set(String r1_String, String r2_String) {
        }

        public void setAnonymizeIp(boolean r1z) {
            this.e = r1z;
        }

        public void setAppId(String r1_String) {
            this.b = r1_String;
        }

        public void setAppInstallerId(String r1_String) {
            this.c = r1_String;
        }

        public void setAppName(String r1_String) {
        }

        public void setAppScreen(String r1_String) {
        }

        public void setAppVersion(String r1_String) {
        }

        public void setCampaign(String r1_String) {
        }

        public void setExceptionParser(ExceptionParser r1_ExceptionParser) {
            this.g = r1_ExceptionParser;
        }

        public void setReferrer(String r1_String) {
        }

        public void setSampleRate(double r1d) {
            this.d = r1d;
        }

        public void setStartSession(boolean r1z) {
        }

        public void setUseSecure(boolean r1z) {
            this.f = r1z;
        }

        public void trackEvent(String r1_String, String r2_String, String r3_String, Long r4_Long) {
        }

        public void trackException(String r1_String, Throwable r2_Throwable, boolean r3z) {
        }

        public void trackException(String r1_String, boolean r2z) {
        }

        public void trackTiming(String r1_String, long r2j, String r4_String, String r5_String) {
        }

        public void trackTransaction(Transaction r1_Transaction) {
        }

        public void trackView() {
        }

        public void trackView(String r1_String) {
        }
    }

    private class b extends TimerTask {
        private b() {
        }

        public void run() {
            EasyTracker.this.y = false;
            EasyTracker.this.r.trackEvent(RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, null);
        }
    }

    private EasyTracker() {
        this.b = false;
        this.f = 1800;
        this.l = false;
        this.m = 0;
        this.q = new HashMap();
        this.r = null;
        this.y = false;
        this.v = new h(this);
    }

    private String a(Activity r4_Activity) {
        String r1_String = r4_Activity.getClass().getCanonicalName();
        if (this.q.containsKey(r1_String)) {
            return (String) this.q.get(r1_String);
        }
        String r0_String = this.s.getString(r1_String);
        if (r0_String == null) {
            r0_String = r1_String;
        }
        this.q.put(r1_String, r0_String);
        return r0_String;
    }

    private void b() {
        boolean r0z = true;
        this.c = this.s.getString("ga_trackingId");
        if (TextUtils.isEmpty(this.c)) {
            this.c = this.s.getString("ga_api_key");
            if (TextUtils.isEmpty(this.c)) {
                z.c("EasyTracker requested, but missing required ga_trackingId");
                this.r = new a();
                return;
            }
        }
        this.b = true;
        this.d = this.s.getString("ga_appName");
        this.e = this.s.getString("ga_appVersion");
        this.g = this.s.getBoolean("ga_debug");
        this.h = this.s.getDoubleFromString("ga_sampleFrequency");
        if (this.h == null) {
            this.h = new Double((double) this.s.getInt("ga_sampleRate", 100));
        }
        this.f = this.s.getInt("ga_dispatchPeriod", 1800);
        this.n = (long) (this.s.getInt("ga_sessionTimeout", Constants.pageCount) * 1000);
        if (this.s.getBoolean("ga_autoActivityTracking") || this.s.getBoolean("ga_auto_activity_tracking")) {
            this.l = r0z;
            this.i = this.s.getBoolean("ga_anonymizeIp");
            this.j = this.s.getBoolean("ga_reportUncaughtExceptions");
            this.r = this.t.getTracker(this.c);
        } else {
            r0z = false;
            this.l = r0z;
            this.i = this.s.getBoolean("ga_anonymizeIp");
            this.j = this.s.getBoolean("ga_reportUncaughtExceptions");
            this.r = this.t.getTracker(this.c);
        }
        if (!TextUtils.isEmpty(this.d)) {
            z.d("setting appName to " + this.d);
            this.r.setAppName(this.d);
        }
        if (this.e != null) {
            this.r.setAppVersion(this.e);
        }
        this.r.setAnonymizeIp(this.i);
        this.r.setSampleRate(this.h.doubleValue());
        this.t.setDebug(this.g);
        this.u.setDispatchPeriod(this.f);
        if (this.j) {
            UncaughtExceptionHandler r0_UncaughtExceptionHandler = this.k;
            if (r0_UncaughtExceptionHandler == null) {
                r0_UncaughtExceptionHandler = new ExceptionReporter(this.r, this.u, Thread.getDefaultUncaughtExceptionHandler());
            }
            Thread.setDefaultUncaughtExceptionHandler(r0_UncaughtExceptionHandler);
        }
    }

    private synchronized void c() {
        if (this.w != null) {
            this.w.cancel();
            this.w = null;
        }
    }

    public static EasyTracker getInstance() {
        if (a == null) {
            a = new EasyTracker();
        }
        return a;
    }

    public static Tracker getTracker() {
        if (getInstance().p != null) {
            return getInstance().r;
        }
        throw new IllegalStateException("You must call EasyTracker.getInstance().setContext(context) or startActivity(activity) before calling getTracker()");
    }

    void a(Context r2_Context, ae r3_ae, Analytics r4_Analytics, ServiceManager r5_ServiceManager) {
        if (r2_Context == null) {
            z.c("Context cannot be null");
        }
        if (this.p == null) {
            this.p = r2_Context.getApplicationContext();
            this.t = r4_Analytics;
            this.u = r5_ServiceManager;
            this.s = r3_ae;
            b();
        }
    }

    boolean a() {
        if (this.n != 0) {
            if (this.n <= 0 || this.v.currentTimeMillis() <= this.o + this.n) {
                return false;
            }
        }
        return true;
    }

    public void activityStart(Activity r7_Activity) {
        setContext(r7_Activity);
        if (this.b) {
            c();
            if (!(this.y) && this.m == 0 && a()) {
                this.r.setStartSession(true);
                if (!this.l) {
                    this.r.trackEvent(RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, null);
                }
                this.y = true;
                this.m++;
                if (this.l) {
                    this.r.trackView(a(r7_Activity));
                }
            } else {
                this.y = true;
                this.m++;
                if (this.l) {
                    this.r.trackView(a(r7_Activity));
                }
            }
        }
    }

    public void activityStop(Activity r5_Activity) {
        setContext(r5_Activity);
        if (this.b) {
            this.m--;
            this.m = Math.max(0, this.m);
            this.o = this.v.currentTimeMillis();
            if (this.m == 0) {
                c();
                this.x = new b(null);
                this.w = new Timer("waitForActivityStart");
                this.w.schedule(this.x, Util.MILLSECONDS_OF_SECOND);
            }
        }
    }

    public void dispatch() {
        if (this.b) {
            this.u.dispatch();
        }
    }

    public void setContext(Context r4_Context) {
        if (r4_Context == null) {
            z.c("Context cannot be null");
        } else {
            a(r4_Context, new af(r4_Context.getApplicationContext()), GoogleAnalytics.getInstance(r4_Context.getApplicationContext()), GAServiceManager.getInstance());
        }
    }
}