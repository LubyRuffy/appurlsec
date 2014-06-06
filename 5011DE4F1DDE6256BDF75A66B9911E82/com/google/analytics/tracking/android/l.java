package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.Intent;
import com.google.analytics.tracking.android.AnalyticsGmsCoreClient.OnConnectedListener;
import com.google.analytics.tracking.android.AnalyticsGmsCoreClient.OnConnectionFailedListener;
import com.google.android.gms.analytics.internal.Command;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: GAServiceProxy.java
class l implements OnConnectedListener, OnConnectionFailedListener, ai {
    private volatile long a;
    private volatile a b;
    private volatile c c;
    private d d;
    private d e;
    private final AnalyticsThread f;
    private final Context g;
    private final Queue<d> h;
    private volatile int i;
    private volatile Timer j;
    private volatile Timer k;
    private volatile Timer l;
    private boolean m;
    private boolean n;
    private f o;
    private long p;

    // compiled from: GAServiceProxy.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.CONNECTED_LOCAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.CONNECTED_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError e_2) {
            }
            a[a.DISCONNECTED.ordinal()] = 3;
        }
    }

    // compiled from: GAServiceProxy.java
    private static enum a {
        CONNECTING,
        CONNECTED_SERVICE,
        CONNECTED_LOCAL,
        BLOCKED,
        PENDING_CONNECTION,
        PENDING_DISCONNECT,
        DISCONNECTED;


        static {
            CONNECTING = new a("CONNECTING", 0);
            CONNECTED_SERVICE = new a("CONNECTED_SERVICE", 1);
            CONNECTED_LOCAL = new a("CONNECTED_LOCAL", 2);
            BLOCKED = new a("BLOCKED", 3);
            PENDING_CONNECTION = new a("PENDING_CONNECTION", 4);
            PENDING_DISCONNECT = new a("PENDING_DISCONNECT", 5);
            DISCONNECTED = new a("DISCONNECTED", 6);
            a[] r0_aA = new a[7];
            r0_aA[0] = CONNECTING;
            r0_aA[1] = CONNECTED_SERVICE;
            r0_aA[2] = CONNECTED_LOCAL;
            r0_aA[3] = BLOCKED;
            r0_aA[4] = PENDING_CONNECTION;
            r0_aA[5] = PENDING_DISCONNECT;
            r0_aA[6] = DISCONNECTED;
            a = r0_aA;
        }
    }

    // compiled from: GAServiceProxy.java
    private class b extends TimerTask {
        private b() {
        }

        public void run() {
            if (l.this.b == a.CONNECTED_SERVICE && l.this.h.isEmpty() && l.this + l.this.p < l.this.o.currentTimeMillis()) {
                z.e("Disconnecting due to inactivity");
                l.this.f();
            } else {
                l.this.l.schedule(new b(), l.this.p);
            }
        }
    }

    // compiled from: GAServiceProxy.java
    private class c extends TimerTask {
        private c() {
        }

        public void run() {
            if (l.this.b == a.CONNECTING) {
                l.this.d();
            }
        }
    }

    // compiled from: GAServiceProxy.java
    private static class d {
        private final Map<String, String> a;
        private final long b;
        private final String c;
        private final List<Command> d;

        public d(Map<String, String> r1_Map_String__String, long r2j, String r4_String, List<Command> r5_List_Command) {
            this.a = r1_Map_String__String;
            this.b = r2j;
            this.c = r4_String;
            this.d = r5_List_Command;
        }

        public List<Command> getCommands() {
            return this.d;
        }

        public long getHitTimeInMilliseconds() {
            return this.b;
        }

        public String getPath() {
            return this.c;
        }

        public Map<String, String> getWireFormatParams() {
            return this.a;
        }
    }

    // compiled from: GAServiceProxy.java
    private class e extends TimerTask {
        private e() {
        }

        public void run() {
            l.this.e();
        }
    }

    l(Context r2_Context, AnalyticsThread r3_AnalyticsThread) {
        this(r2_Context, r3_AnalyticsThread, null);
    }

    l(Context r3_Context, AnalyticsThread r4_AnalyticsThread, d r5_d) {
        this.h = new ConcurrentLinkedQueue();
        this.p = 300000;
        this.e = r5_d;
        this.g = r3_Context;
        this.f = r4_AnalyticsThread;
        this.o = new m(this);
        this.i = 0;
        this.b = a.DISCONNECTED;
    }

    private Timer a(Timer r2_Timer) {
        if (r2_Timer != null) {
            r2_Timer.cancel();
        }
        return null;
    }

    private void a() {
        this.j = a(this.j);
        this.k = a(this.k);
        this.l = a(this.l);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void b() {
        /*
        r7_this = this;
        monitor-enter(r7);
        r1 = java.lang.Thread.currentThread();	 //Catch:{ all -> 0x0063 }
        r2 = r7.f;	 //Catch:{ all -> 0x0063 }
        r2 = r2.getThread();	 //Catch:{ all -> 0x0063 }
        r1 = r1.equals(r2);	 //Catch:{ all -> 0x0063 }
        if (r1 != 0) goto L_0x0021;
    L_0x0011:
        r1 = r7.f;	 //Catch:{ all -> 0x0063 }
        r1 = r1.getQueue();	 //Catch:{ all -> 0x0063 }
        r2 = new com.google.analytics.tracking.android.n;	 //Catch:{ all -> 0x0063 }
        r2.<init>(r7);	 //Catch:{ all -> 0x0063 }
        r1.add(r2);	 //Catch:{ all -> 0x0063 }
    L_0x001f:
        monitor-exit(r7);
        return;
    L_0x0021:
        r1 = r7.n;	 //Catch:{ all -> 0x0063 }
        if (r1 == 0) goto L_0x0028;
    L_0x0025:
        r7.clearHits();	 //Catch:{ all -> 0x0063 }
    L_0x0028:
        r1 = com.google.analytics.tracking.android.l.AnonymousClass_1.a;	 //Catch:{ all -> 0x0063 }
        r2 = r7.b;	 //Catch:{ all -> 0x0063 }
        r2 = r2.ordinal();	 //Catch:{ all -> 0x0063 }
        r1 = r1[r2];	 //Catch:{ all -> 0x0063 }
        switch(r1) {
            case 1: goto L_0x0036;
            case 2: goto L_0x006e;
            case 3: goto L_0x00aa;
            default: goto L_0x0035;
        };	 //Catch:{ all -> 0x0063 }
    L_0x0035:
        goto L_0x001f;
    L_0x0036:
        r1 = r7.h;	 //Catch:{ all -> 0x0063 }
        r1 = r1.isEmpty();	 //Catch:{ all -> 0x0063 }
        if (r1 != 0) goto L_0x0066;
    L_0x003e:
        r1 = r7.h;	 //Catch:{ all -> 0x0063 }
        r1 = r1.poll();	 //Catch:{ all -> 0x0063 }
        r0 = r1;
        r0 = (com.google.analytics.tracking.android.l.d) r0;	 //Catch:{ all -> 0x0063 }
        r6 = r0;
        r1 = "Sending hit to store";
        com.google.analytics.tracking.android.z.e(r1);	 //Catch:{ all -> 0x0063 }
        r1 = r7.d;	 //Catch:{ all -> 0x0063 }
        r2 = r6.getWireFormatParams();	 //Catch:{ all -> 0x0063 }
        r3 = r6.getHitTimeInMilliseconds();	 //Catch:{ all -> 0x0063 }
        r5 = r6.getPath();	 //Catch:{ all -> 0x0063 }
        r6 = r6.getCommands();	 //Catch:{ all -> 0x0063 }
        r1.putHit(r2, r3, r5, r6);	 //Catch:{ all -> 0x0063 }
        goto L_0x0036;
    L_0x0063:
        r1 = move-exception;
        monitor-exit(r7);
        throw r1;
    L_0x0066:
        r1 = r7.m;	 //Catch:{ all -> 0x0063 }
        if (r1 == 0) goto L_0x001f;
    L_0x006a:
        r7.c();	 //Catch:{ all -> 0x0063 }
        goto L_0x001f;
    L_0x006e:
        r1 = r7.h;	 //Catch:{ all -> 0x0063 }
        r1 = r1.isEmpty();	 //Catch:{ all -> 0x0063 }
        if (r1 != 0) goto L_0x00a0;
    L_0x0076:
        r1 = r7.h;	 //Catch:{ all -> 0x0063 }
        r1 = r1.peek();	 //Catch:{ all -> 0x0063 }
        r0 = r1;
        r0 = (com.google.analytics.tracking.android.l.d) r0;	 //Catch:{ all -> 0x0063 }
        r6 = r0;
        r1 = "Sending hit to service";
        com.google.analytics.tracking.android.z.e(r1);	 //Catch:{ all -> 0x0063 }
        r1 = r7.c;	 //Catch:{ all -> 0x0063 }
        r2 = r6.getWireFormatParams();	 //Catch:{ all -> 0x0063 }
        r3 = r6.getHitTimeInMilliseconds();	 //Catch:{ all -> 0x0063 }
        r5 = r6.getPath();	 //Catch:{ all -> 0x0063 }
        r6 = r6.getCommands();	 //Catch:{ all -> 0x0063 }
        r1.sendHit(r2, r3, r5, r6);	 //Catch:{ all -> 0x0063 }
        r1 = r7.h;	 //Catch:{ all -> 0x0063 }
        r1.poll();	 //Catch:{ all -> 0x0063 }
        goto L_0x006e;
    L_0x00a0:
        r1 = r7.o;	 //Catch:{ all -> 0x0063 }
        r1 = r1.currentTimeMillis();	 //Catch:{ all -> 0x0063 }
        r7.a = r1;	 //Catch:{ all -> 0x0063 }
        goto L_0x001f;
    L_0x00aa:
        r1 = "Need to reconnect";
        com.google.analytics.tracking.android.z.e(r1);	 //Catch:{ all -> 0x0063 }
        r1 = r7.h;	 //Catch:{ all -> 0x0063 }
        r1 = r1.isEmpty();	 //Catch:{ all -> 0x0063 }
        if (r1 != 0) goto L_0x001f;
    L_0x00b7:
        r7.e();	 //Catch:{ all -> 0x0063 }
        goto L_0x001f;
        */

    }

    private void c() {
        this.d.dispatch();
        this.m = false;
    }

    private synchronized void d() {
        if (this.b == a.CONNECTED_LOCAL) {
        } else {
            a();
            z.e("falling back to local store");
            if (this.e != null) {
                this.d = this.e;
            } else {
                GAServiceManager r0_GAServiceManager = GAServiceManager.getInstance();
                r0_GAServiceManager.a(this.g, this.f);
                this.d = r0_GAServiceManager.a();
            }
            this.b = a.CONNECTED_LOCAL;
            b();
        }
    }

    private synchronized void e() {
        if (this.c == null || this.b == a.CONNECTED_LOCAL) {
            z.h("client not initialized.");
            d();
        } else {
            try {
                this.i++;
                a(this.k);
                this.b = a.CONNECTING;
                this.k = new Timer("Failed Connect");
                this.k.schedule(new c(null), 3000);
                z.e("connecting to Analytics service");
                this.c.connect();
            } catch (SecurityException e) {
                z.h("security exception on connectToService");
                d();
            }
        }
    }

    private synchronized void f() {
        if (this.c == null || this.b != a.CONNECTED_SERVICE) {
        } else {
            this.b = a.PENDING_DISCONNECT;
            this.c.disconnect();
        }
    }

    private void g() {
        this.j = a(this.j);
        this.j = new Timer("Service Reconnect");
        this.j.schedule(new e(null), 5000);
    }

    public void clearHits() {
        z.d("clearHits called");
        this.h.clear();
        switch (AnonymousClass_1.a[this.b.ordinal()]) {
            case XListViewHeader.STATE_READY:
                this.d.clearHits();
                this.n = false;
                return;
            case XListViewHeader.STATE_REFRESHING:
                this.c.clearHits();
                this.n = false;
                return;
        }
        this.n = true;
    }

    public void createService() {
        if (this.c != null) {
        } else {
            this.c = new AnalyticsGmsCoreClient(this.g, this, this);
            e();
        }
    }

    public void dispatch() {
        switch (AnonymousClass_1.a[this.b.ordinal()]) {
            case XListViewHeader.STATE_READY:
                c();
                break;
            case XListViewHeader.STATE_REFRESHING:
                break;
            default:
                this.m = true;
                break;
        }
    }

    public synchronized void onConnected() {
        this.k = a(this.k);
        this.i = 0;
        z.e("Connected to service");
        this.b = a.CONNECTED_SERVICE;
        b();
        this.l = a(this.l);
        this.l = new Timer("disconnect check");
        this.l.schedule(new b(null), this.p);
    }

    public synchronized void onConnectionFailed(int r3i, Intent r4_Intent) {
        z.h("Connection to service failed " + r3i);
        this.b = a.PENDING_CONNECTION;
        if (this.i < XListViewHeader.STATE_REFRESHING) {
            g();
        } else {
            d();
        }
    }

    public synchronized void onDisconnected() {
        if (this.b == a.PENDING_DISCONNECT) {
            z.e("Disconnected from service");
            a();
            this.b = a.DISCONNECTED;
        } else {
            z.e("Unexpected disconnect.");
            this.b = a.PENDING_CONNECTION;
            if (this.i < 2) {
                g();
            } else {
                d();
            }
        }
    }

    public void putHit(Map<String, String> r8_Map_String__String, long r9j, String r11_String, List<Command> r12_List_Command) {
        z.e("putHit called");
        this.h.add(new d(r8_Map_String__String, r9j, r11_String, r12_List_Command));
        b();
    }

    public void setIdleTimeout(long r1j) {
        this.p = r1j;
    }
}