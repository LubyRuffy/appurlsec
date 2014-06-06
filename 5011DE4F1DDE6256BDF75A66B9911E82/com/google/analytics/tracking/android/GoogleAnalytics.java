package com.google.analytics.tracking.android;

import android.content.Context;
import com.google.analytics.tracking.android.Analytics.AppOptOutCallback;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import qsbk.app.utils.audit.Rotate3dAnimation;

public class GoogleAnalytics implements Analytics, al {
    private static GoogleAnalytics j;
    private boolean a;
    private AnalyticsThread b;
    private Context c;
    private Tracker d;
    private a e;
    private volatile String f;
    private volatile Boolean g;
    private final Map<String, Tracker> h;
    private String i;

    private GoogleAnalytics() {
        this.h = new HashMap();
    }

    private GoogleAnalytics(Context r2_Context) {
        this(r2_Context, o.a(r2_Context));
    }

    private GoogleAnalytics(Context r3_Context, AnalyticsThread r4_AnalyticsThread) {
        this.h = new HashMap();
        if (r3_Context == null) {
            throw new IllegalArgumentException("context cannot be null");
        } else {
            this.c = r3_Context.getApplicationContext();
            this.b = r4_AnalyticsThread;
            this.e = new a();
            this.b.requestAppOptOut(new u(this));
            this.b.requestClientId(new v(this));
        }
    }

    public static synchronized GoogleAnalytics getInstance(Context r2_Context) {
        GoogleAnalytics r0_GoogleAnalytics;
        synchronized (GoogleAnalytics.class) {
            if (j == null) {
                j = new GoogleAnalytics(r2_Context);
            }
            r0_GoogleAnalytics = j;
        }
        return r0_GoogleAnalytics;
    }

    public synchronized void closeTracker(Tracker r2_Tracker) {
        this.h.values().remove(r2_Tracker);
        if (r2_Tracker == this.d) {
            this.d = null;
        }
    }

    public boolean debugEnabled() {
        return this.a;
    }

    public synchronized Tracker getDefaultTracker() {
        return this.d;
    }

    public synchronized Tracker getTracker(String r3_String) {
        Tracker r0_Tracker;
        if (r3_String == null) {
            throw new IllegalArgumentException("trackingId cannot be null");
        } else {
            r0_Tracker = (Tracker) this.h.get(r3_String);
            if (r0_Tracker == null) {
                r0_Tracker = new GoogleTracker(r3_String, this);
                this.h.put(r3_String, r0_Tracker);
                if (this.d == null) {
                    this.d = r0_Tracker;
                }
            }
        }
        return r0_Tracker;
    }

    public void requestAppOptOut(AppOptOutCallback r2_AppOptOutCallback) {
        if (this.g != null) {
            r2_AppOptOutCallback.reportAppOptOut(this.g.booleanValue());
        } else {
            this.b.requestAppOptOut(r2_AppOptOutCallback);
        }
    }

    public synchronized void sendHit(Map<String, String> r4_Map_String__String) {
        if (r4_Map_String__String == null) {
            throw new IllegalArgumentException("hit cannot be null");
        } else {
            r4_Map_String__String.put(ModelFields.LANGUAGE, am.a(Locale.getDefault()));
            r4_Map_String__String.put("adSenseAdMobHitId", Integer.toString(this.e.a()));
            r4_Map_String__String.put(ModelFields.SCREEN_RESOLUTION, this.c.getResources().getDisplayMetrics().widthPixels + Rotate3dAnimation.ROTATE_X + this.c.getResources().getDisplayMetrics().heightPixels);
            this.b.sendHit(r4_Map_String__String);
            this.i = (String) r4_Map_String__String.get(ModelFields.TRACKING_ID);
        }
    }

    public void setAppOptOut(boolean r2z) {
        this.g = Boolean.valueOf(r2z);
        this.b.setAppOptOut(r2z);
    }

    public void setDebug(boolean r1z) {
        this.a = r1z;
        z.a(r1z);
    }

    public synchronized void setDefaultTracker(Tracker r2_Tracker) {
        this.d = r2_Tracker;
    }
}