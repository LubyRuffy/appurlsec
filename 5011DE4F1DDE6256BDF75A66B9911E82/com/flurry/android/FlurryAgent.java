package com.flurry.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.WeakHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.utils.Base64;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
public final class FlurryAgent implements LocationListener {
    static String a;
    private static final String[] b;
    private static volatile String c;
    private static volatile String d;
    private static volatile String e;
    private static volatile String f;
    private static volatile String g;
    private static volatile String h;
    private static volatile String i;
    private static final FlurryAgent j;
    private static long k;
    private static boolean l;
    private static boolean m;
    private static boolean n;
    private static boolean o;
    private static Criteria p;
    private static boolean q;
    private static AppCircle r;
    private String A;
    private String B;
    private boolean C;
    private List D;
    private LocationManager E;
    private String F;
    private boolean G;
    private long H;
    private List I;
    private long J;
    private long K;
    private long L;
    private String M;
    private String N;
    private byte O;
    private String P;
    private byte Q;
    private Long R;
    private int S;
    private Location T;
    private Map U;
    private List V;
    private boolean W;
    private int X;
    private List Y;
    private int Z;
    private ag aa;
    private final Handler s;
    private File t;
    private File u;
    private volatile boolean v;
    private volatile boolean w;
    private long x;
    private Map y;
    private String z;

    // compiled from: SourceFile
    public class FlurryDefaultExceptionHandler implements UncaughtExceptionHandler {
        private UncaughtExceptionHandler a;

        FlurryDefaultExceptionHandler() {
            this.a = Thread.getDefaultUncaughtExceptionHandler();
        }

        public void uncaughtException(Thread r4_Thread, Throwable r5_Throwable) {
            try {
                j.a(r5_Throwable);
            } catch (Throwable th) {
                i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
            }
            if (this.a != null) {
                this.a.uncaughtException(r4_Thread, r5_Throwable);
            }
        }
    }

    static {
        String[] r0_StringA = new String[2];
        r0_StringA[0] = "9774d56d682e549c";
        r0_StringA[1] = "dead00beef";
        b = r0_StringA;
        c = null;
        d = "http://data.flurry.com/aap.do";
        e = "https://data.flurry.com/aap.do";
        f = null;
        g = "http://ad.flurry.com/getCanvas.do";
        h = null;
        i = "http://ad.flurry.com/getAndroidApp.do";
        j = new FlurryAgent();
        k = 10000;
        l = true;
        m = false;
        n = false;
        o = true;
        p = null;
        q = false;
        r = new AppCircle();
    }

    private FlurryAgent() {
        this.u = null;
        this.v = false;
        this.w = false;
        this.y = new WeakHashMap();
        this.C = true;
        this.I = new ArrayList();
        this.M = RContactStorage.PRIMARY_KEY;
        this.N = RContactStorage.PRIMARY_KEY;
        this.O = (byte) -1;
        this.P = RContactStorage.PRIMARY_KEY;
        this.Q = (byte) -1;
        this.U = new HashMap();
        this.V = new ArrayList();
        this.Y = new ArrayList();
        this.aa = new ag();
        HandlerThread r0_HandlerThread = new HandlerThread("FlurryAgent");
        r0_HandlerThread.start();
        this.s = new Handler(r0_HandlerThread.getLooper());
    }

    private static double a(double r4d) {
        return ((double) Math.round(r4d * 1000.0d)) / 1000.0d;
    }

    static View a(Context r4_Context, String r5_String, int r6i) {
        if (!(q)) {
            return null;
        }
        try {
            return j.aa.a(r4_Context, r5_String, r6i);
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
            return null;
        }
    }

    static Offer a(String r1_String) {
        return q ? j.aa.b(r1_String) : null;
    }

    private HttpClient a(HttpParams r7_HttpParams) {
        try {
            KeyStore r0_KeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            r0_KeyStore.load(null, null);
            SocketFactory r1_SocketFactory = new j(this, r0_KeyStore);
            SchemeRegistry r0_SchemeRegistry = new SchemeRegistry();
            r0_SchemeRegistry.register(new Scheme(ImageFetcher.HTTP_CACHE_DIR, PlainSocketFactory.getSocketFactory(), 80));
            r0_SchemeRegistry.register(new Scheme("https", r1_SocketFactory, 443));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(r7_HttpParams, r0_SchemeRegistry), r7_HttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient(r7_HttpParams);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(Context r9_Context) {
        /*
        r8_this = this;
        monitor-enter(r8);
        r0 = r8.b(r9);	 //Catch:{ all -> 0x010b }
        r8.F = r0;	 //Catch:{ all -> 0x010b }
        r0 = r8.u;	 //Catch:{ all -> 0x010b }
        r0 = r0.exists();	 //Catch:{ all -> 0x010b }
        if (r0 == 0) goto L_0x0127;
    L_0x000f:
        r0 = "FlurryAgent";
        r1 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x010b }
        r1.<init>();	 //Catch:{ all -> 0x010b }
        r2 = "loading persistent data: ";
        r1 = r1.append(r2);	 //Catch:{ all -> 0x010b }
        r2 = r8.u;	 //Catch:{ all -> 0x010b }
        r2 = r2.getAbsolutePath();	 //Catch:{ all -> 0x010b }
        r1 = r1.append(r2);	 //Catch:{ all -> 0x010b }
        r1 = r1.toString();	 //Catch:{ all -> 0x010b }
        com.flurry.android.i.c(r0, r1);	 //Catch:{ all -> 0x010b }
        r2 = 0;
        r0 = new java.io.FileInputStream;	 //Catch:{ Throwable -> 0x0132, all -> 0x010e }
        r1 = r8.u;	 //Catch:{ Throwable -> 0x0132, all -> 0x010e }
        r0.<init>(r1);	 //Catch:{ Throwable -> 0x0132, all -> 0x010e }
        r1 = new java.io.DataInputStream;	 //Catch:{ Throwable -> 0x0132, all -> 0x010e }
        r1.<init>(r0);	 //Catch:{ Throwable -> 0x0132, all -> 0x010e }
        r0 = r1.readUnsignedShort();	 //Catch:{ Throwable -> 0x00fe }
        r2 = "FlurryAgent";
        r3 = new java.lang.StringBuilder;	 //Catch:{ Throwable -> 0x00fe }
        r3.<init>();	 //Catch:{ Throwable -> 0x00fe }
        r4 = "Magic: ";
        r3 = r3.append(r4);	 //Catch:{ Throwable -> 0x00fe }
        r3 = r3.append(r0);	 //Catch:{ Throwable -> 0x00fe }
        r3 = r3.toString();	 //Catch:{ Throwable -> 0x00fe }
        com.flurry.android.i.c(r2, r3);	 //Catch:{ Throwable -> 0x00fe }
        r2 = 46586; // 0xb5fa float:6.5281E-41 double:2.30165E-319;
        if (r0 != r2) goto L_0x00f5;
    L_0x005b:
        r8.b(r1);	 //Catch:{ Throwable -> 0x00fe }
    L_0x005e:
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x010b }
    L_0x0061:
        r0 = r8.w;	 //Catch:{ Throwable -> 0x011d }
        if (r0 != 0) goto L_0x0074;
    L_0x0065:
        r0 = r8.u;	 //Catch:{ Throwable -> 0x011d }
        r0 = r0.delete();	 //Catch:{ Throwable -> 0x011d }
        if (r0 == 0) goto L_0x0114;
    L_0x006d:
        r0 = "FlurryAgent";
        r1 = "Deleted persistence file";
        com.flurry.android.i.a(r0, r1);	 //Catch:{ Throwable -> 0x011d }
    L_0x0074:
        r0 = r8.w;	 //Catch:{ all -> 0x010b }
        if (r0 != 0) goto L_0x0082;
    L_0x0078:
        r0 = 0;
        r8.G = r0;	 //Catch:{ all -> 0x010b }
        r0 = r8.J;	 //Catch:{ all -> 0x010b }
        r8.H = r0;	 //Catch:{ all -> 0x010b }
        r0 = 1;
        r8.w = r0;	 //Catch:{ all -> 0x010b }
    L_0x0082:
        r0 = r8.F;	 //Catch:{ all -> 0x010b }
        if (r0 != 0) goto L_0x00d5;
    L_0x0086:
        r0 = java.lang.Math.random();	 //Catch:{ all -> 0x010b }
        r0 = java.lang.Double.doubleToLongBits(r0);	 //Catch:{ all -> 0x010b }
        r2 = 37;
        r4 = java.lang.System.nanoTime();	 //Catch:{ all -> 0x010b }
        r6 = r8.z;	 //Catch:{ all -> 0x010b }
        r6 = r6.hashCode();	 //Catch:{ all -> 0x010b }
        r6 = r6 * 37;
        r6 = (long) r6;	 //Catch:{ all -> 0x010b }
        r4 = r4 + r6;
        r2 = r2 * r4;
        r0 = r0 + r2;
        r2 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x010b }
        r2.<init>();	 //Catch:{ all -> 0x010b }
        r3 = "ID";
        r2 = r2.append(r3);	 //Catch:{ all -> 0x010b }
        r3 = 16;
        r0 = java.lang.Long.toString(r0, r3);	 //Catch:{ all -> 0x010b }
        r0 = r2.append(r0);	 //Catch:{ all -> 0x010b }
        r0 = r0.toString();	 //Catch:{ all -> 0x010b }
        r8.F = r0;	 //Catch:{ all -> 0x010b }
        r0 = "FlurryAgent";
        r1 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x010b }
        r1.<init>();	 //Catch:{ all -> 0x010b }
        r2 = "Generated phoneId: ";
        r1 = r1.append(r2);	 //Catch:{ all -> 0x010b }
        r2 = r8.F;	 //Catch:{ all -> 0x010b }
        r1 = r1.append(r2);	 //Catch:{ all -> 0x010b }
        r1 = r1.toString();	 //Catch:{ all -> 0x010b }
        com.flurry.android.i.c(r0, r1);	 //Catch:{ all -> 0x010b }
    L_0x00d5:
        r0 = r8.aa;	 //Catch:{ all -> 0x010b }
        r1 = r8.F;	 //Catch:{ all -> 0x010b }
        r0.a(r1);	 //Catch:{ all -> 0x010b }
        r0 = r8.F;	 //Catch:{ all -> 0x010b }
        r1 = "AND";
        r0 = r0.startsWith(r1);	 //Catch:{ all -> 0x010b }
        if (r0 != 0) goto L_0x00f3;
    L_0x00e6:
        r0 = r8.t;	 //Catch:{ all -> 0x010b }
        r0 = r0.exists();	 //Catch:{ all -> 0x010b }
        if (r0 != 0) goto L_0x00f3;
    L_0x00ee:
        r0 = r8.F;	 //Catch:{ all -> 0x010b }
        r8.c(r9, r0);	 //Catch:{ all -> 0x010b }
    L_0x00f3:
        monitor-exit(r8);
        return;
    L_0x00f5:
        r0 = "FlurryAgent";
        r2 = "Unexpected file type";
        com.flurry.android.i.a(r0, r2);	 //Catch:{ Throwable -> 0x00fe }
        goto L_0x005e;
    L_0x00fe:
        r0 = move-exception;
    L_0x00ff:
        r2 = "FlurryAgent";
        r3 = "Error when loading persistent file";
        com.flurry.android.i.b(r2, r3, r0);	 //Catch:{ all -> 0x0130 }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x010b }
        goto L_0x0061;
    L_0x010b:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x010e:
        r0 = move-exception;
        r1 = r2;
    L_0x0110:
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x010b }
        throw r0;	 //Catch:{ all -> 0x010b }
    L_0x0114:
        r0 = "FlurryAgent";
        r1 = "Cannot delete persistence file";
        com.flurry.android.i.b(r0, r1);	 //Catch:{ Throwable -> 0x011d }
        goto L_0x0074;
    L_0x011d:
        r0 = move-exception;
        r1 = "FlurryAgent";
        r2 = "";
        com.flurry.android.i.b(r1, r2, r0);	 //Catch:{ all -> 0x010b }
        goto L_0x0074;
    L_0x0127:
        r0 = "FlurryAgent";
        r1 = "Agent cache file doesn't exist.";
        com.flurry.android.i.c(r0, r1);	 //Catch:{ all -> 0x010b }
        goto L_0x0074;
    L_0x0130:
        r0 = move-exception;
        goto L_0x0110;
    L_0x0132:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ff;
        */

    }

    static void a(Context r2_Context, long r3j) {
        if (!q) {
            i.d("FlurryAgent", "Cannot accept Offer. AppCircle is not enabled");
        }
        j.aa.a(r2_Context, r3j);
    }

    static void a(Context r1_Context, String r2_String) {
        if (q) {
            j.aa.a(r1_Context, r2_String);
        }
    }

    private synchronized void a(Context r6_Context, boolean r7z) {
        Context r0_Context;
        String r1_String;
        long r1j;
        if (r6_Context != null) {
            if (((Context) this.y.remove(r6_Context)) == null) {
                i.d("FlurryAgent", "onEndSession called without context from corresponding onStartSession");
            }
            if (this.v && this.y.isEmpty()) {
                i.a("FlurryAgent", "Ending session");
                n();
                r0_Context = r6_Context != null ? null : r6_Context.getApplicationContext();
                if (r6_Context == null) {
                    r1_String = r0_Context.getPackageName();
                    if (this.A.equals(r1_String)) {
                        i.b("FlurryAgent", "onEndSession called from different application package, expected: " + this.A + " actual: " + r1_String);
                    }
                }
                r1j = SystemClock.elapsedRealtime();
                this.x = r1j;
                this.L = r1j - this.K;
                if (this.F != null) {
                    i.b("FlurryAgent", "Not creating report because of bad Android ID or generated ID is null");
                }
                a(new n(this, r7z, r0_Context));
                this.v = false;
            }
        } else if (this.v || this.y.isEmpty()) {
        } else {
            i.a("FlurryAgent", "Ending session");
            n();
            if (r6_Context != null) {
            }
            if (r6_Context == null) {
                r1j = SystemClock.elapsedRealtime();
                this.x = r1j;
                this.L = r1j - this.K;
                if (this.F != null) {
                    a(new n(this, r7z, r0_Context));
                    this.v = false;
                } else {
                    i.b("FlurryAgent", "Not creating report because of bad Android ID or generated ID is null");
                    a(new n(this, r7z, r0_Context));
                    this.v = false;
                }
            } else {
                r1_String = r0_Context.getPackageName();
                if (this.A.equals(r1_String)) {
                    r1j = SystemClock.elapsedRealtime();
                    this.x = r1j;
                    this.L = r1j - this.K;
                    if (this.F != null) {
                        i.b("FlurryAgent", "Not creating report because of bad Android ID or generated ID is null");
                    }
                    a(new n(this, r7z, r0_Context));
                    this.v = false;
                } else {
                    i.b("FlurryAgent", "onEndSession called from different application package, expected: " + this.A + " actual: " + r1_String);
                    r1j = SystemClock.elapsedRealtime();
                    this.x = r1j;
                    this.L = r1j - this.K;
                    if (this.F != null) {
                        a(new n(this, r7z, r0_Context));
                        this.v = false;
                    } else {
                        i.b("FlurryAgent", "Not creating report because of bad Android ID or generated ID is null");
                        a(new n(this, r7z, r0_Context));
                        this.v = false;
                    }
                }
            }
        }
    }

    static void a(AppCircleCallback r1_AppCircleCallback) {
        j.aa.a(r1_AppCircleCallback);
    }

    static /* synthetic */ void a(FlurryAgent r3_FlurryAgent, Context r4_Context, boolean r5z) {
        Location r0_Location = null;
        if (r5z) {
            r0_Location = r3_FlurryAgent.d(r4_Context);
            synchronized (r3_FlurryAgent) {
                r3_FlurryAgent.T = r0_Location;
            }
            if (!q) {
                r3_FlurryAgent.aa.c();
            }
            r3_FlurryAgent.c(true);
            return;
        } else {
            synchronized (r3_FlurryAgent) {
                r3_FlurryAgent.T = r0_Location;
            }
            if (q) {
                r3_FlurryAgent.c(true);
                return;
            } else {
                r3_FlurryAgent.aa.c();
                r3_FlurryAgent.c(true);
                return;
            }
        }
        i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
    }

    private void a(DataInputStream r15_DataInputStream) {
        Map r1_Map = new HashMap();
        Map r4_Map = new HashMap();
        Map r5_Map = new HashMap();
        Map r2_Map = new HashMap();
        Map r3_Map = new HashMap();
        Map r6_Map = new HashMap();
        while (true) {
            int r8i = r15_DataInputStream.readUnsignedShort();
            int r0i = r15_DataInputStream.readInt();
            byte r7b;
            int r7i;
            switch (r8i) {
                case 258:
                    r15_DataInputStream.readInt();
                    break;
                case 259:
                    r7b = r15_DataInputStream.readByte();
                    int r9i = r15_DataInputStream.readUnsignedShort();
                    Object r10_Object = new Object[r9i];
                    r0i = 0;
                    while (r0i < r9i) {
                        r10_Object[r0i] = new ah(r15_DataInputStream);
                        r0i++;
                    }
                    r1_Map.put(Byte.valueOf(r7b), r10_Object);
                    break;
                case 262:
                    r7i = r15_DataInputStream.readUnsignedShort();
                    r0i = 0;
                    while (r0i < r7i) {
                        AdImage r9_AdImage = new AdImage(r15_DataInputStream);
                        r4_Map.put(Long.valueOf(r9_AdImage.a), r9_AdImage);
                        i.a("FlurryAgent", "Parsed image: " + r9_AdImage.a);
                        r0i++;
                    }
                    break;
                case 263:
                    r7i = r15_DataInputStream.readInt();
                    r0i = 0;
                    while (r0i < r7i) {
                        q r9_q = new q(r15_DataInputStream);
                        r2_Map.put(r9_q.a, r9_q);
                        r0i++;
                    }
                    break;
                case 264:
                    break;
                case 266:
                    r7b = r15_DataInputStream.readByte();
                    byte r0b = (byte) 0;
                    while (r0b < r7b) {
                        o r9_o = new o(r15_DataInputStream);
                        r3_Map.put(Byte.valueOf(r9_o.a), r9_o);
                        r0b++;
                    }
                    break;
                case 268:
                    r7i = r15_DataInputStream.readInt();
                    r0i = 0;
                    while (r0i < r7i) {
                        r6_Map.put(Short.valueOf(r15_DataInputStream.readShort()), Long.valueOf(r15_DataInputStream.readLong()));
                        r0i++;
                    }
                    break;
                case 269:
                    r15_DataInputStream.skipBytes(r0i);
                    break;
                case 270:
                    r15_DataInputStream.skipBytes(r0i);
                    break;
                case 271:
                    byte r9b = r15_DataInputStream.readByte();
                    r7b = (byte) 0;
                    while (r7b < r9b) {
                        o r0_o = (o) r3_Map.get(Byte.valueOf(r15_DataInputStream.readByte()));
                        if (r0_o != null) {
                            r0_o.a((DataInput)r15_DataInputStream);
                        }
                        r7b++;
                    }
                    break;
                case 272:
                    long r9j = r15_DataInputStream.readLong();
                    m r0_m = (m) r5_Map.get(Long.valueOf(r9j));
                    if (r0_m == null) {
                        r0_m = new m();
                    }
                    r0_m.a = r15_DataInputStream.readUTF();
                    r0_m.c = r15_DataInputStream.readInt();
                    r5_Map.put(Long.valueOf(r9j), r0_m);
                    break;
                case 273:
                    r15_DataInputStream.skipBytes(r0i);
                    break;
                default:
                    i.a("FlurryAgent", "Unknown chunkType: " + r8i);
                    r15_DataInputStream.skipBytes(r0i);
                    break;
            }
            if (r8i == 264) {
                if (q) {
                    if (r1_Map.isEmpty()) {
                        i.a("FlurryAgent", "No ads from server");
                    }
                    this.aa.a(r1_Map, r2_Map, r3_Map, r4_Map, r5_Map, r6_Map);
                }
                return;
            }
        }
    }

    private void a(Runnable r2_Runnable) {
        this.s.post(r2_Runnable);
    }

    private synchronized void a(String r4_String, String r5_String, String r6_String) {
        if (this.Y == null) {
            i.b("FlurryAgent", "onError called before onStartSession.  Error: " + r4_String);
        } else {
            this.S++;
            if (this.Y.size() < 10) {
                b r0_b = new b();
                r0_b.a = System.currentTimeMillis();
                r0_b.b = ad.a(r4_String, (int)Util.MASK_8BIT);
                r0_b.c = ad.a(r5_String, (int)AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
                r0_b.d = ad.a(r6_String, (int)Util.MASK_8BIT);
                this.Y.add(r0_b);
            }
        }
    }

    private synchronized void a(String r8_String, Map r9_Map, boolean r10z) {
        if (this.V == null) {
            i.b("FlurryAgent", "onEvent called before onStartSession.  Event: " + r8_String);
        } else {
            long r3j = SystemClock.elapsedRealtime() - this.K;
            String r1_String = ad.a(r8_String, (int)Util.MASK_8BIT);
            if (r1_String.length() != 0) {
                s r0_s = (s) this.U.get(r1_String);
                if (r0_s == null) {
                    if (this.U.size() < 100) {
                        r0_s = new s();
                        r0_s.a = 1;
                        this.U.put(r1_String, r0_s);
                    } else if (i.a("FlurryAgent")) {
                        i.a("FlurryAgent", "MaxEventIds exceeded: " + r1_String);
                    }
                } else {
                    r0_s.a++;
                }
                if ((!l) || this.V.size() >= 200 || this.X >= 16000) {
                    this.W = false;
                } else {
                    Map r2_Map;
                    r2_Map = r9_Map == null ? Collections.emptyMap() : r9_Map;
                    if (r2_Map.size() > 10) {
                        if (i.a("FlurryAgent")) {
                            i.a("FlurryAgent", "MaxEventParams exceeded: " + r2_Map.size());
                        }
                    } else {
                        u r0_u = new u(r1_String, r2_Map, r3j, r10z);
                        if (r0_u.b().length + this.X <= 16000) {
                            this.V.add(r0_u);
                            this.X = r0_u.b().length + this.X;
                        } else {
                            this.X = 16000;
                            this.W = false;
                        }
                    }
                }
            }
        }
    }

    static void a(List r1_List) {
        if (q) {
            j.aa.a(r1_List);
        }
    }

    static void a(boolean r1z) {
        if (q) {
            j.aa.a(r1z);
        }
    }

    static boolean a() {
        return j.aa.i();
    }

    private static boolean a(File r4_File) {
        File r0_File = r4_File.getParentFile();
        if (r0_File.mkdirs() || r0_File.exists()) {
            return true;
        }
        i.b("FlurryAgent", "Unable to create persistent dir: " + r0_File);
        return false;
    }

    private boolean a(byte[] r6_byteA) {
        boolean r0z;
        String r0_String = l();
        if (r0_String == null) {
            return false;
        }
        try {
            r0z = a(r6_byteA, r0_String);
        } catch (Exception e) {
            i.a("FlurryAgent", "Sending report exception: " + e.getMessage());
            r0z = false;
        }
        if (r0z || c != null || (!m) || n) {
            return r0z;
        }
        synchronized (j) {
            n = true;
            String r3_String = l();
            if (r3_String == null) {
                return false;
            } else {
                try {
                    return a(r6_byteA, r3_String);
                } catch (Exception e_2) {
                    return r0z;
                }
            }
        }
    }

    private boolean a(byte[] r7_byteA, String r8_String) {
        boolean r0z = true;
        if ("local".equals(r8_String)) {
            return r0z;
        }
        i.a("FlurryAgent", "Sending report to: " + r8_String);
        HttpEntity r2_HttpEntity = new ByteArrayEntity(r7_byteA);
        r2_HttpEntity.setContentType("application/octet-stream");
        HttpUriRequest r3_HttpUriRequest = new HttpPost(r8_String);
        r3_HttpUriRequest.setEntity(r2_HttpEntity);
        HttpParams r2_HttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(r2_HttpParams, 10000);
        HttpConnectionParams.setSoTimeout(r2_HttpParams, 15000);
        r3_HttpUriRequest.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        HttpResponse r2_HttpResponse = a(r2_HttpParams).execute(r3_HttpUriRequest);
        int r3i = r2_HttpResponse.getStatusLine().getStatusCode();
        synchronized (this) {
            if (r3i == 200) {
                i.a("FlurryAgent", "Report successful");
                this.G = true;
                this.I.removeAll(this.D);
                HttpEntity r1_HttpEntity = r2_HttpResponse.getEntity();
                i.a("FlurryAgent", "Processing report response");
                if (r1_HttpEntity == null || r1_HttpEntity.getContentLength() == 0) {
                    this.D = null;
                } else {
                    a(new DataInputStream(r1_HttpEntity.getContent()));
                    r1_HttpEntity.consumeContent();
                }
            } else {
                i.a("FlurryAgent", "Report failed. HTTP response: " + r3i);
                r0z = false;
            }
            this.D = null;
        }
        return r0z;
    }

    public static void addUserCookie(String r1_String, String r2_String) {
        if (q) {
            j.aa.a(r1_String, r2_String);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private String b(Context r8_Context) {
        /*
        r7_this = this;
        r0 = 0;
        r1 = 0;
        r2 = r7.F;
        if (r2 == 0) goto L_0x0009;
    L_0x0006:
        r0 = r7.F;
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = r8.getContentResolver();
        r3 = "android_id";
        r3 = android.provider.Settings.System.getString(r2, r3);
        if (r3 == 0) goto L_0x0023;
    L_0x0015:
        r2 = r3.length();
        if (r2 <= 0) goto L_0x0023;
    L_0x001b:
        r2 = "null";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0039;
    L_0x0023:
        if (r1 == 0) goto L_0x004c;
    L_0x0025:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "AND";
        r0 = r0.append(r1);
        r0 = r0.append(r3);
        r0 = r0.toString();
        goto L_0x0008;
    L_0x0039:
        r4 = b;
        r5 = r4.length;
        r2 = r1;
    L_0x003d:
        if (r2 >= r5) goto L_0x004a;
    L_0x003f:
        r6 = r4[r2];
        r6 = r3.equals(r6);
        if (r6 != 0) goto L_0x0023;
    L_0x0047:
        r2 = r2 + 1;
        goto L_0x003d;
    L_0x004a:
        r1 = 1;
        goto L_0x0023;
    L_0x004c:
        r1 = ".flurryb.";
        r1 = r8.getFileStreamPath(r1);
        r2 = r1.exists();
        if (r2 == 0) goto L_0x0008;
    L_0x0058:
        r3 = new java.io.FileInputStream;	 //Catch:{ Throwable -> 0x006d, all -> 0x007a }
        r3.<init>(r1);	 //Catch:{ Throwable -> 0x006d, all -> 0x007a }
        r2 = new java.io.DataInputStream;	 //Catch:{ Throwable -> 0x006d, all -> 0x007a }
        r2.<init>(r3);	 //Catch:{ Throwable -> 0x006d, all -> 0x007a }
        r2.readInt();	 //Catch:{ Throwable -> 0x0083 }
        r0 = r2.readUTF();	 //Catch:{ Throwable -> 0x0083 }
        com.flurry.android.ad.a(r2);
        goto L_0x0008;
    L_0x006d:
        r1 = move-exception;
        r2 = r0;
    L_0x006f:
        r3 = "FlurryAgent";
        r4 = "Error when loading b file";
        com.flurry.android.i.b(r3, r4, r1);	 //Catch:{ all -> 0x0081 }
        com.flurry.android.ad.a(r2);
        goto L_0x0008;
    L_0x007a:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x007d:
        com.flurry.android.ad.a(r2);
        throw r0;
    L_0x0081:
        r0 = move-exception;
        goto L_0x007d;
    L_0x0083:
        r1 = move-exception;
        goto L_0x006f;
        */

    }

    static List b(String r1_String) {
        return q ? j.aa.c(r1_String) : null;
    }

    private synchronized void b(Context r9_Context, String r10_String) {
        Context r1_Context;
        String r0_String;
        long r2j;
        a r2_a;
        if (this.z == null || this.z.equals(r10_String)) {
            if (((Context) this.y.put(r9_Context, r9_Context)) == null) {
                i.d("FlurryAgent", "onStartSession called with duplicate context, use a specific Activity or Service as context instead of using a global context");
            }
            if (!this.v) {
                i.a("FlurryAgent", "Initializing Flurry session");
                this.z = r10_String;
                this.u = r9_Context.getFileStreamPath(".flurryagent." + Integer.toString(this.z.hashCode(), Base64.URL_SAFE));
                this.t = r9_Context.getFileStreamPath(".flurryb.");
                if (o) {
                    Thread.setDefaultUncaughtExceptionHandler(new FlurryDefaultExceptionHandler());
                }
                r1_Context = r9_Context.getApplicationContext();
                if (this.B != null) {
                    this.B = c(r1_Context);
                }
                r0_String = r1_Context.getPackageName();
                if (this.A == null || this.A.equals(r0_String)) {
                    this.A = r0_String;
                    r2j = SystemClock.elapsedRealtime();
                    if (r2j - this.x <= k) {
                        i.a("FlurryAgent", "New session");
                        this.J = System.currentTimeMillis();
                        this.K = r2j;
                        this.L = -1;
                        this.P = RContactStorage.PRIMARY_KEY;
                        this.S = 0;
                        this.T = null;
                        this.N = TimeZone.getDefault().getID();
                        this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                        this.U = new HashMap();
                        this.V = new ArrayList();
                        this.W = true;
                        this.Y = new ArrayList();
                        this.X = 0;
                        this.Z = 0;
                        if (!q) {
                            if (this.aa.b()) {
                                i.a("FlurryAgent", "Initializing AppCircle");
                                r2_a = new a();
                                r2_a.a = this.z;
                                r2_a.b = this.H;
                                r2_a.c = this.J;
                                r2_a.d = this.K;
                                r2_a.e = f == null ? f : g;
                                r2_a.f = d();
                                r2_a.g = this.s;
                                this.aa.a(r9_Context, r2_a);
                                i.a("FlurryAgent", "AppCircle initialized");
                            }
                            this.aa.a();
                        }
                        a(new p(this, r1_Context, this.C));
                    } else {
                        i.a("FlurryAgent", "Continuing previous session");
                        if (this.I.isEmpty()) {
                            this.I.remove(this.I.size() - 1);
                        }
                    }
                    this.v = true;
                } else {
                    i.b("FlurryAgent", "onStartSession called from different application packages: " + this.A + " and " + r0_String);
                    this.A = r0_String;
                    r2j = SystemClock.elapsedRealtime();
                    if (r2j - this.x <= k) {
                        i.a("FlurryAgent", "Continuing previous session");
                        if (this.I.isEmpty()) {
                            this.v = true;
                        } else {
                            this.I.remove(this.I.size() - 1);
                        }
                    } else {
                        i.a("FlurryAgent", "New session");
                        this.J = System.currentTimeMillis();
                        this.K = r2j;
                        this.L = -1;
                        this.P = RContactStorage.PRIMARY_KEY;
                        this.S = 0;
                        this.T = null;
                        this.N = TimeZone.getDefault().getID();
                        this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                        this.U = new HashMap();
                        this.V = new ArrayList();
                        this.W = true;
                        this.Y = new ArrayList();
                        this.X = 0;
                        this.Z = 0;
                        if (q) {
                            a(new p(this, r1_Context, this.C));
                        } else if (this.aa.b()) {
                            this.aa.a();
                            a(new p(this, r1_Context, this.C));
                        } else {
                            i.a("FlurryAgent", "Initializing AppCircle");
                            r2_a = new a();
                            r2_a.a = this.z;
                            r2_a.b = this.H;
                            r2_a.c = this.J;
                            r2_a.d = this.K;
                            if (f == null) {
                            }
                            r2_a.e = f == null ? f : g;
                            r2_a.f = d();
                            r2_a.g = this.s;
                            this.aa.a(r9_Context, r2_a);
                            i.a("FlurryAgent", "AppCircle initialized");
                            this.aa.a();
                            a(new p(this, r1_Context, this.C));
                        }
                    }
                    this.v = true;
                }
            }
        } else {
            i.b("FlurryAgent", "onStartSession called with different api keys: " + this.z + " and " + r10_String);
            if (((Context) this.y.put(r9_Context, r9_Context)) == null) {
                if (this.v) {
                } else {
                    i.a("FlurryAgent", "Initializing Flurry session");
                    this.z = r10_String;
                    this.u = r9_Context.getFileStreamPath(".flurryagent." + Integer.toString(this.z.hashCode(), Base64.URL_SAFE));
                    this.t = r9_Context.getFileStreamPath(".flurryb.");
                    if (o) {
                        r1_Context = r9_Context.getApplicationContext();
                        if (this.B != null) {
                            r0_String = r1_Context.getPackageName();
                            if (this.A == null || this.A.equals(r0_String)) {
                                this.A = r0_String;
                                r2j = SystemClock.elapsedRealtime();
                                if (r2j - this.x <= k) {
                                    i.a("FlurryAgent", "New session");
                                    this.J = System.currentTimeMillis();
                                    this.K = r2j;
                                    this.L = -1;
                                    this.P = RContactStorage.PRIMARY_KEY;
                                    this.S = 0;
                                    this.T = null;
                                    this.N = TimeZone.getDefault().getID();
                                    this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                    this.U = new HashMap();
                                    this.V = new ArrayList();
                                    this.W = true;
                                    this.Y = new ArrayList();
                                    this.X = 0;
                                    this.Z = 0;
                                    if (q) {
                                        if (this.aa.b()) {
                                            i.a("FlurryAgent", "Initializing AppCircle");
                                            r2_a = new a();
                                            r2_a.a = this.z;
                                            r2_a.b = this.H;
                                            r2_a.c = this.J;
                                            r2_a.d = this.K;
                                            if (f == null) {
                                            }
                                            r2_a.e = f == null ? f : g;
                                            r2_a.f = d();
                                            r2_a.g = this.s;
                                            this.aa.a(r9_Context, r2_a);
                                            i.a("FlurryAgent", "AppCircle initialized");
                                        }
                                        this.aa.a();
                                    }
                                    a(new p(this, r1_Context, this.C));
                                } else {
                                    i.a("FlurryAgent", "Continuing previous session");
                                    if (this.I.isEmpty()) {
                                        this.I.remove(this.I.size() - 1);
                                    }
                                }
                                this.v = true;
                            } else {
                                i.b("FlurryAgent", "onStartSession called from different application packages: " + this.A + " and " + r0_String);
                                this.A = r0_String;
                                r2j = SystemClock.elapsedRealtime();
                                if (r2j - this.x <= k) {
                                    i.a("FlurryAgent", "Continuing previous session");
                                    if (this.I.isEmpty()) {
                                        this.v = true;
                                    } else {
                                        this.I.remove(this.I.size() - 1);
                                    }
                                } else {
                                    i.a("FlurryAgent", "New session");
                                    this.J = System.currentTimeMillis();
                                    this.K = r2j;
                                    this.L = -1;
                                    this.P = RContactStorage.PRIMARY_KEY;
                                    this.S = 0;
                                    this.T = null;
                                    this.N = TimeZone.getDefault().getID();
                                    this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                    this.U = new HashMap();
                                    this.V = new ArrayList();
                                    this.W = true;
                                    this.Y = new ArrayList();
                                    this.X = 0;
                                    this.Z = 0;
                                    if (q) {
                                        a(new p(this, r1_Context, this.C));
                                    } else if (this.aa.b()) {
                                        this.aa.a();
                                        a(new p(this, r1_Context, this.C));
                                    } else {
                                        i.a("FlurryAgent", "Initializing AppCircle");
                                        r2_a = new a();
                                        r2_a.a = this.z;
                                        r2_a.b = this.H;
                                        r2_a.c = this.J;
                                        r2_a.d = this.K;
                                        if (f == null) {
                                        }
                                        r2_a.e = f == null ? f : g;
                                        r2_a.f = d();
                                        r2_a.g = this.s;
                                        this.aa.a(r9_Context, r2_a);
                                        i.a("FlurryAgent", "AppCircle initialized");
                                        this.aa.a();
                                        a(new p(this, r1_Context, this.C));
                                    }
                                }
                                this.v = true;
                            }
                        } else {
                            this.B = c(r1_Context);
                            r0_String = r1_Context.getPackageName();
                            if (this.A == null || this.A.equals(r0_String)) {
                                this.A = r0_String;
                                r2j = SystemClock.elapsedRealtime();
                                if (r2j - this.x <= k) {
                                    i.a("FlurryAgent", "New session");
                                    this.J = System.currentTimeMillis();
                                    this.K = r2j;
                                    this.L = -1;
                                    this.P = RContactStorage.PRIMARY_KEY;
                                    this.S = 0;
                                    this.T = null;
                                    this.N = TimeZone.getDefault().getID();
                                    this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                    this.U = new HashMap();
                                    this.V = new ArrayList();
                                    this.W = true;
                                    this.Y = new ArrayList();
                                    this.X = 0;
                                    this.Z = 0;
                                    if (q) {
                                        if (this.aa.b()) {
                                            i.a("FlurryAgent", "Initializing AppCircle");
                                            r2_a = new a();
                                            r2_a.a = this.z;
                                            r2_a.b = this.H;
                                            r2_a.c = this.J;
                                            r2_a.d = this.K;
                                            if (f == null) {
                                            }
                                            r2_a.e = f == null ? f : g;
                                            r2_a.f = d();
                                            r2_a.g = this.s;
                                            this.aa.a(r9_Context, r2_a);
                                            i.a("FlurryAgent", "AppCircle initialized");
                                        }
                                        this.aa.a();
                                    }
                                    a(new p(this, r1_Context, this.C));
                                } else {
                                    i.a("FlurryAgent", "Continuing previous session");
                                    if (this.I.isEmpty()) {
                                        this.I.remove(this.I.size() - 1);
                                    }
                                }
                                this.v = true;
                            } else {
                                i.b("FlurryAgent", "onStartSession called from different application packages: " + this.A + " and " + r0_String);
                                this.A = r0_String;
                                r2j = SystemClock.elapsedRealtime();
                                if (r2j - this.x <= k) {
                                    i.a("FlurryAgent", "Continuing previous session");
                                    if (this.I.isEmpty()) {
                                        this.v = true;
                                    } else {
                                        this.I.remove(this.I.size() - 1);
                                    }
                                } else {
                                    i.a("FlurryAgent", "New session");
                                    this.J = System.currentTimeMillis();
                                    this.K = r2j;
                                    this.L = -1;
                                    this.P = RContactStorage.PRIMARY_KEY;
                                    this.S = 0;
                                    this.T = null;
                                    this.N = TimeZone.getDefault().getID();
                                    this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                    this.U = new HashMap();
                                    this.V = new ArrayList();
                                    this.W = true;
                                    this.Y = new ArrayList();
                                    this.X = 0;
                                    this.Z = 0;
                                    if (q) {
                                        a(new p(this, r1_Context, this.C));
                                    } else if (this.aa.b()) {
                                        this.aa.a();
                                        a(new p(this, r1_Context, this.C));
                                    } else {
                                        i.a("FlurryAgent", "Initializing AppCircle");
                                        r2_a = new a();
                                        r2_a.a = this.z;
                                        r2_a.b = this.H;
                                        r2_a.c = this.J;
                                        r2_a.d = this.K;
                                        if (f == null) {
                                        }
                                        r2_a.e = f == null ? f : g;
                                        r2_a.f = d();
                                        r2_a.g = this.s;
                                        this.aa.a(r9_Context, r2_a);
                                        i.a("FlurryAgent", "AppCircle initialized");
                                        this.aa.a();
                                        a(new p(this, r1_Context, this.C));
                                    }
                                }
                                this.v = true;
                            }
                        }
                    } else {
                        Thread.setDefaultUncaughtExceptionHandler(new FlurryDefaultExceptionHandler());
                        r1_Context = r9_Context.getApplicationContext();
                        if (this.B != null) {
                            this.B = c(r1_Context);
                        }
                        r0_String = r1_Context.getPackageName();
                        if (this.A == null || this.A.equals(r0_String)) {
                            this.A = r0_String;
                            r2j = SystemClock.elapsedRealtime();
                            if (r2j - this.x <= k) {
                                i.a("FlurryAgent", "New session");
                                this.J = System.currentTimeMillis();
                                this.K = r2j;
                                this.L = -1;
                                this.P = RContactStorage.PRIMARY_KEY;
                                this.S = 0;
                                this.T = null;
                                this.N = TimeZone.getDefault().getID();
                                this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                this.U = new HashMap();
                                this.V = new ArrayList();
                                this.W = true;
                                this.Y = new ArrayList();
                                this.X = 0;
                                this.Z = 0;
                                if (q) {
                                    if (this.aa.b()) {
                                        i.a("FlurryAgent", "Initializing AppCircle");
                                        r2_a = new a();
                                        r2_a.a = this.z;
                                        r2_a.b = this.H;
                                        r2_a.c = this.J;
                                        r2_a.d = this.K;
                                        if (f == null) {
                                        }
                                        r2_a.e = f == null ? f : g;
                                        r2_a.f = d();
                                        r2_a.g = this.s;
                                        this.aa.a(r9_Context, r2_a);
                                        i.a("FlurryAgent", "AppCircle initialized");
                                    }
                                    this.aa.a();
                                }
                                a(new p(this, r1_Context, this.C));
                            } else {
                                i.a("FlurryAgent", "Continuing previous session");
                                if (this.I.isEmpty()) {
                                    this.I.remove(this.I.size() - 1);
                                }
                            }
                            this.v = true;
                        } else {
                            i.b("FlurryAgent", "onStartSession called from different application packages: " + this.A + " and " + r0_String);
                            this.A = r0_String;
                            r2j = SystemClock.elapsedRealtime();
                            if (r2j - this.x <= k) {
                                i.a("FlurryAgent", "Continuing previous session");
                                if (this.I.isEmpty()) {
                                    this.v = true;
                                } else {
                                    this.I.remove(this.I.size() - 1);
                                }
                            } else {
                                i.a("FlurryAgent", "New session");
                                this.J = System.currentTimeMillis();
                                this.K = r2j;
                                this.L = -1;
                                this.P = RContactStorage.PRIMARY_KEY;
                                this.S = 0;
                                this.T = null;
                                this.N = TimeZone.getDefault().getID();
                                this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                this.U = new HashMap();
                                this.V = new ArrayList();
                                this.W = true;
                                this.Y = new ArrayList();
                                this.X = 0;
                                this.Z = 0;
                                if (q) {
                                    a(new p(this, r1_Context, this.C));
                                } else if (this.aa.b()) {
                                    this.aa.a();
                                    a(new p(this, r1_Context, this.C));
                                } else {
                                    i.a("FlurryAgent", "Initializing AppCircle");
                                    r2_a = new a();
                                    r2_a.a = this.z;
                                    r2_a.b = this.H;
                                    r2_a.c = this.J;
                                    r2_a.d = this.K;
                                    if (f == null) {
                                    }
                                    r2_a.e = f == null ? f : g;
                                    r2_a.f = d();
                                    r2_a.g = this.s;
                                    this.aa.a(r9_Context, r2_a);
                                    i.a("FlurryAgent", "AppCircle initialized");
                                    this.aa.a();
                                    a(new p(this, r1_Context, this.C));
                                }
                            }
                            this.v = true;
                        }
                    }
                }
            } else {
                i.d("FlurryAgent", "onStartSession called with duplicate context, use a specific Activity or Service as context instead of using a global context");
                if (this.v) {
                    i.a("FlurryAgent", "Initializing Flurry session");
                    this.z = r10_String;
                    this.u = r9_Context.getFileStreamPath(".flurryagent." + Integer.toString(this.z.hashCode(), Base64.URL_SAFE));
                    this.t = r9_Context.getFileStreamPath(".flurryb.");
                    if (o) {
                        Thread.setDefaultUncaughtExceptionHandler(new FlurryDefaultExceptionHandler());
                    }
                    r1_Context = r9_Context.getApplicationContext();
                    if (this.B != null) {
                        r0_String = r1_Context.getPackageName();
                        if (this.A == null || this.A.equals(r0_String)) {
                            this.A = r0_String;
                            r2j = SystemClock.elapsedRealtime();
                            if (r2j - this.x <= k) {
                                i.a("FlurryAgent", "New session");
                                this.J = System.currentTimeMillis();
                                this.K = r2j;
                                this.L = -1;
                                this.P = RContactStorage.PRIMARY_KEY;
                                this.S = 0;
                                this.T = null;
                                this.N = TimeZone.getDefault().getID();
                                this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                this.U = new HashMap();
                                this.V = new ArrayList();
                                this.W = true;
                                this.Y = new ArrayList();
                                this.X = 0;
                                this.Z = 0;
                                if (q) {
                                    if (this.aa.b()) {
                                        i.a("FlurryAgent", "Initializing AppCircle");
                                        r2_a = new a();
                                        r2_a.a = this.z;
                                        r2_a.b = this.H;
                                        r2_a.c = this.J;
                                        r2_a.d = this.K;
                                        if (f == null) {
                                        }
                                        r2_a.e = f == null ? f : g;
                                        r2_a.f = d();
                                        r2_a.g = this.s;
                                        this.aa.a(r9_Context, r2_a);
                                        i.a("FlurryAgent", "AppCircle initialized");
                                    }
                                    this.aa.a();
                                }
                                a(new p(this, r1_Context, this.C));
                            } else {
                                i.a("FlurryAgent", "Continuing previous session");
                                if (this.I.isEmpty()) {
                                    this.I.remove(this.I.size() - 1);
                                }
                            }
                            this.v = true;
                        } else {
                            i.b("FlurryAgent", "onStartSession called from different application packages: " + this.A + " and " + r0_String);
                            this.A = r0_String;
                            r2j = SystemClock.elapsedRealtime();
                            if (r2j - this.x <= k) {
                                i.a("FlurryAgent", "Continuing previous session");
                                if (this.I.isEmpty()) {
                                    this.v = true;
                                } else {
                                    this.I.remove(this.I.size() - 1);
                                }
                            } else {
                                i.a("FlurryAgent", "New session");
                                this.J = System.currentTimeMillis();
                                this.K = r2j;
                                this.L = -1;
                                this.P = RContactStorage.PRIMARY_KEY;
                                this.S = 0;
                                this.T = null;
                                this.N = TimeZone.getDefault().getID();
                                this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                this.U = new HashMap();
                                this.V = new ArrayList();
                                this.W = true;
                                this.Y = new ArrayList();
                                this.X = 0;
                                this.Z = 0;
                                if (q) {
                                    a(new p(this, r1_Context, this.C));
                                } else if (this.aa.b()) {
                                    this.aa.a();
                                    a(new p(this, r1_Context, this.C));
                                } else {
                                    i.a("FlurryAgent", "Initializing AppCircle");
                                    r2_a = new a();
                                    r2_a.a = this.z;
                                    r2_a.b = this.H;
                                    r2_a.c = this.J;
                                    r2_a.d = this.K;
                                    if (f == null) {
                                    }
                                    r2_a.e = f == null ? f : g;
                                    r2_a.f = d();
                                    r2_a.g = this.s;
                                    this.aa.a(r9_Context, r2_a);
                                    i.a("FlurryAgent", "AppCircle initialized");
                                    this.aa.a();
                                    a(new p(this, r1_Context, this.C));
                                }
                            }
                            this.v = true;
                        }
                    } else {
                        this.B = c(r1_Context);
                        r0_String = r1_Context.getPackageName();
                        if (this.A == null || this.A.equals(r0_String)) {
                            this.A = r0_String;
                            r2j = SystemClock.elapsedRealtime();
                            if (r2j - this.x <= k) {
                                i.a("FlurryAgent", "New session");
                                this.J = System.currentTimeMillis();
                                this.K = r2j;
                                this.L = -1;
                                this.P = RContactStorage.PRIMARY_KEY;
                                this.S = 0;
                                this.T = null;
                                this.N = TimeZone.getDefault().getID();
                                this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                this.U = new HashMap();
                                this.V = new ArrayList();
                                this.W = true;
                                this.Y = new ArrayList();
                                this.X = 0;
                                this.Z = 0;
                                if (q) {
                                    if (this.aa.b()) {
                                        i.a("FlurryAgent", "Initializing AppCircle");
                                        r2_a = new a();
                                        r2_a.a = this.z;
                                        r2_a.b = this.H;
                                        r2_a.c = this.J;
                                        r2_a.d = this.K;
                                        if (f == null) {
                                        }
                                        r2_a.e = f == null ? f : g;
                                        r2_a.f = d();
                                        r2_a.g = this.s;
                                        this.aa.a(r9_Context, r2_a);
                                        i.a("FlurryAgent", "AppCircle initialized");
                                    }
                                    this.aa.a();
                                }
                                a(new p(this, r1_Context, this.C));
                            } else {
                                i.a("FlurryAgent", "Continuing previous session");
                                if (this.I.isEmpty()) {
                                    this.I.remove(this.I.size() - 1);
                                }
                            }
                            this.v = true;
                        } else {
                            i.b("FlurryAgent", "onStartSession called from different application packages: " + this.A + " and " + r0_String);
                            this.A = r0_String;
                            r2j = SystemClock.elapsedRealtime();
                            if (r2j - this.x <= k) {
                                i.a("FlurryAgent", "Continuing previous session");
                                if (this.I.isEmpty()) {
                                    this.v = true;
                                } else {
                                    this.I.remove(this.I.size() - 1);
                                }
                            } else {
                                i.a("FlurryAgent", "New session");
                                this.J = System.currentTimeMillis();
                                this.K = r2j;
                                this.L = -1;
                                this.P = RContactStorage.PRIMARY_KEY;
                                this.S = 0;
                                this.T = null;
                                this.N = TimeZone.getDefault().getID();
                                this.M = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                                this.U = new HashMap();
                                this.V = new ArrayList();
                                this.W = true;
                                this.Y = new ArrayList();
                                this.X = 0;
                                this.Z = 0;
                                if (q) {
                                    a(new p(this, r1_Context, this.C));
                                } else if (this.aa.b()) {
                                    this.aa.a();
                                    a(new p(this, r1_Context, this.C));
                                } else {
                                    i.a("FlurryAgent", "Initializing AppCircle");
                                    r2_a = new a();
                                    r2_a.a = this.z;
                                    r2_a.b = this.H;
                                    r2_a.c = this.J;
                                    r2_a.d = this.K;
                                    if (f == null) {
                                    }
                                    r2_a.e = f == null ? f : g;
                                    r2_a.f = d();
                                    r2_a.g = this.s;
                                    this.aa.a(r9_Context, r2_a);
                                    i.a("FlurryAgent", "AppCircle initialized");
                                    this.aa.a();
                                    a(new p(this, r1_Context, this.C));
                                }
                            }
                            this.v = true;
                        }
                    }
                }
            }
        }
    }

    static /* synthetic */ void b(FlurryAgent r5_FlurryAgent, Context r6_Context) {
        int r0i = 0;
        try {
            synchronized (r5_FlurryAgent) {
                long r1j = SystemClock.elapsedRealtime() - r5_FlurryAgent.x;
                if (r5_FlurryAgent.v || r1j <= k || r5_FlurryAgent.I.size() <= 0) {
                } else {
                    r0i = 1;
                }
            }
            if (r0i != 0) {
                r5_FlurryAgent.c(false);
            }
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    private synchronized void b(DataInputStream r7_DataInputStream) {
        int r0i = 0;
        synchronized (this) {
            int r1i = r7_DataInputStream.readUnsignedShort();
            i.a("FlurryAgent", "File version: " + r1i);
            if (r1i > 2) {
                i.b("FlurryAgent", "Unknown agent file version: " + r1i);
                throw new IOException("Unknown agent file version: " + r1i);
            } else {
                if (r1i >= 2) {
                    String r1_String = r7_DataInputStream.readUTF();
                    i.a("FlurryAgent", "Loading API key: " + this.z);
                    if (r1_String.equals(this.z)) {
                        r1_String = r7_DataInputStream.readUTF();
                        if (this.F == null) {
                            i.a("FlurryAgent", "Loading phoneId: " + r1_String);
                        }
                        this.F = r1_String;
                        this.G = r7_DataInputStream.readBoolean();
                        this.H = r7_DataInputStream.readLong();
                        i.a("FlurryAgent", "Loading session reports");
                        while (true) {
                            r1i = r7_DataInputStream.readUnsignedShort();
                            if (r1i != 0) {
                                Object r1_Object = new Object[r1i];
                                r7_DataInputStream.readFully(r1_Object);
                                this.I.add(0, r1_Object);
                                r0i++;
                                i.a("FlurryAgent", "Session report added: " + r0i);
                            } else {
                                i.a("FlurryAgent", "Persistent file loaded");
                                this.w = true;
                            }
                        }
                    } else {
                        i.a("FlurryAgent", "Api keys do not match, old: " + r1_String + ", new: " + this.z);
                    }
                } else {
                    i.d("FlurryAgent", "Deleting old file version: " + r1i);
                }
            }
        }
    }

    protected static boolean b() {
        return o;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized byte[] b(boolean r9z) {
        /*
        r8_this = this;
        r1 = 0;
        r3 = 0;
        monitor-enter(r8);
        r4 = new java.io.ByteArrayOutputStream;	 //Catch:{ Throwable -> 0x0116, all -> 0x0112 }
        r4.<init>();	 //Catch:{ Throwable -> 0x0116, all -> 0x0112 }
        r2 = new java.io.DataOutputStream;	 //Catch:{ Throwable -> 0x0116, all -> 0x0112 }
        r2.<init>(r4);	 //Catch:{ Throwable -> 0x0116, all -> 0x0112 }
        r0 = 15;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = q;	 //Catch:{ Throwable -> 0x0052 }
        if (r0 == 0) goto L_0x0060;
    L_0x0016:
        if (r9 == 0) goto L_0x0060;
    L_0x0018:
        r0 = 1;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
    L_0x001c:
        r0 = q;	 //Catch:{ Throwable -> 0x0052 }
        if (r0 == 0) goto L_0x006d;
    L_0x0020:
        r0 = r8.aa;	 //Catch:{ Throwable -> 0x0052 }
        r5 = r0.e();	 //Catch:{ Throwable -> 0x0052 }
        r2.writeLong(r5);	 //Catch:{ Throwable -> 0x0052 }
        r0 = r8.aa;	 //Catch:{ Throwable -> 0x0052 }
        r0 = r0.f();	 //Catch:{ Throwable -> 0x0052 }
        r5 = r0.size();	 //Catch:{ Throwable -> 0x0052 }
        r2.writeShort(r5);	 //Catch:{ Throwable -> 0x0052 }
        r5 = r0.iterator();	 //Catch:{ Throwable -> 0x0052 }
    L_0x003a:
        r0 = r5.hasNext();	 //Catch:{ Throwable -> 0x0052 }
        if (r0 == 0) goto L_0x0076;
    L_0x0040:
        r0 = r5.next();	 //Catch:{ Throwable -> 0x0052 }
        r0 = (java.lang.Long) r0;	 //Catch:{ Throwable -> 0x0052 }
        r6 = r0.longValue();	 //Catch:{ Throwable -> 0x0052 }
        r0 = 1;
        r2.writeByte(r0);	 //Catch:{ Throwable -> 0x0052 }
        r2.writeLong(r6);	 //Catch:{ Throwable -> 0x0052 }
        goto L_0x003a;
    L_0x0052:
        r0 = move-exception;
    L_0x0053:
        r3 = "FlurryAgent";
        r4 = "Error when generating report";
        com.flurry.android.i.b(r3, r4, r0);	 //Catch:{ all -> 0x0065 }
        com.flurry.android.ad.a(r2);	 //Catch:{ all -> 0x006a }
        r0 = r1;
    L_0x005e:
        monitor-exit(r8);
        return r0;
    L_0x0060:
        r0 = 0;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
        goto L_0x001c;
    L_0x0065:
        r0 = move-exception;
    L_0x0066:
        com.flurry.android.ad.a(r2);	 //Catch:{ all -> 0x006a }
        throw r0;	 //Catch:{ all -> 0x006a }
    L_0x006a:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x006d:
        r5 = 0;
        r2.writeLong(r5);	 //Catch:{ Throwable -> 0x0052 }
        r0 = 0;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
    L_0x0076:
        r0 = 3;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
        r5 = java.lang.System.currentTimeMillis();	 //Catch:{ Throwable -> 0x0052 }
        r2.writeLong(r5);	 //Catch:{ Throwable -> 0x0052 }
        r0 = r8.z;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = r8.B;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = 0;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = r8.F;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r5 = r8.H;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeLong(r5);	 //Catch:{ Throwable -> 0x0052 }
        r5 = r8.J;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeLong(r5);	 //Catch:{ Throwable -> 0x0052 }
        r0 = 6;
        r2.writeShort(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = "device.model";
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = android.os.Build.MODEL;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = "build.brand";
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = android.os.Build.BRAND;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = "build.id";
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = android.os.Build.ID;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = "version.release";
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = android.os.Build.VERSION.RELEASE;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = "build.device";
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = android.os.Build.DEVICE;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = "build.product";
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = android.os.Build.PRODUCT;	 //Catch:{ Throwable -> 0x0052 }
        r2.writeUTF(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = r8.I;	 //Catch:{ Throwable -> 0x0052 }
        r5 = r0.size();	 //Catch:{ Throwable -> 0x0052 }
        r2.writeShort(r5);	 //Catch:{ Throwable -> 0x0052 }
    L_0x00ec:
        if (r3 >= r5) goto L_0x00fd;
    L_0x00ee:
        r0 = r8.I;	 //Catch:{ Throwable -> 0x0052 }
        r0 = r0.get(r3);	 //Catch:{ Throwable -> 0x0052 }
        r0 = (byte[]) r0;	 //Catch:{ Throwable -> 0x0052 }
        r2.write(r0);	 //Catch:{ Throwable -> 0x0052 }
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x00ec;
    L_0x00fd:
        r0 = new java.util.ArrayList;	 //Catch:{ Throwable -> 0x0052 }
        r3 = r8.I;	 //Catch:{ Throwable -> 0x0052 }
        r0.<init>(r3);	 //Catch:{ Throwable -> 0x0052 }
        r8.D = r0;	 //Catch:{ Throwable -> 0x0052 }
        r2.close();	 //Catch:{ Throwable -> 0x0052 }
        r0 = r4.toByteArray();	 //Catch:{ Throwable -> 0x0052 }
        com.flurry.android.ad.a(r2);	 //Catch:{ all -> 0x006a }
        goto L_0x005e;
    L_0x0112:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0066;
    L_0x0116:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0053;
        */

    }

    static ag c() {
        return j.aa;
    }

    private static String c(Context r3_Context) {
        try {
            PackageInfo r0_PackageInfo = r3_Context.getPackageManager().getPackageInfo(r3_Context.getPackageName(), 0);
            String r0_String;
            if (r0_PackageInfo.versionName != null) {
                r0_String = r0_PackageInfo.versionName;
                return r0_String;
            } else {
                if (r0_PackageInfo.versionCode != 0) {
                    r0_String = Integer.toString(r0_PackageInfo.versionCode);
                    return r0_String;
                }
                r0_String = "Unknown";
                return r0_String;
            }
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void c(Context r5_Context, String r6_String) {
        /*
        r4_this = this;
        monitor-enter(r4);
        r0 = ".flurryb.";
        r0 = r5.getFileStreamPath(r0);	 //Catch:{ all -> 0x002b }
        r4.t = r0;	 //Catch:{ all -> 0x002b }
        r0 = r4.t;	 //Catch:{ all -> 0x002b }
        r0 = a(r0);	 //Catch:{ all -> 0x002b }
        if (r0 != 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r4);
        return;
    L_0x0013:
        r2 = 0;
        r0 = new java.io.FileOutputStream;	 //Catch:{ Throwable -> 0x002e, all -> 0x003b }
        r1 = r4.t;	 //Catch:{ Throwable -> 0x002e, all -> 0x003b }
        r0.<init>(r1);	 //Catch:{ Throwable -> 0x002e, all -> 0x003b }
        r1 = new java.io.DataOutputStream;	 //Catch:{ Throwable -> 0x002e, all -> 0x003b }
        r1.<init>(r0);	 //Catch:{ Throwable -> 0x002e, all -> 0x003b }
        r0 = 1;
        r1.writeInt(r0);	 //Catch:{ Throwable -> 0x0043 }
        r1.writeUTF(r6);	 //Catch:{ Throwable -> 0x0043 }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x002b }
        goto L_0x0011;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x002e:
        r0 = move-exception;
        r1 = r2;
    L_0x0030:
        r2 = "FlurryAgent";
        r3 = "Error when saving b file";
        com.flurry.android.i.b(r2, r3, r0);	 //Catch:{ all -> 0x0041 }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x002b }
        goto L_0x0011;
    L_0x003b:
        r0 = move-exception;
        r1 = r2;
    L_0x003d:
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x002b }
        throw r0;	 //Catch:{ all -> 0x002b }
    L_0x0041:
        r0 = move-exception;
        goto L_0x003d;
    L_0x0043:
        r0 = move-exception;
        goto L_0x0030;
        */

    }

    private synchronized void c(String r4_String) {
        Iterator r1_Iterator = this.V.iterator();
        while (r1_Iterator.hasNext()) {
            u r0_u = (u) r1_Iterator.next();
            if (r0_u.a(r4_String)) {
                r0_u.a();
                break;
            }
        }
    }

    private void c(boolean r4z) {
        try {
            i.a("FlurryAgent", "generating report");
            byte[] r0_byteA = b(r4z);
            if (r0_byteA != null) {
                if (a(r0_byteA)) {
                    i.a("FlurryAgent", "Done sending " + (this.v ? "initial " : RContactStorage.PRIMARY_KEY) + "agent report");
                    m();
                }
            } else {
                i.a("FlurryAgent", "Error generating report");
            }
        } catch (IOException e) {
            i.a("FlurryAgent", RContactStorage.PRIMARY_KEY, e);
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    public static void clearUserCookies() {
        if (q) {
            j.aa.l();
        }
    }

    private Location d(Context r8_Context) {
        if (r8_Context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0 && r8_Context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return null;
        }
        LocationManager r0_LocationManager = (LocationManager) r8_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        synchronized (this) {
            if (this.E == null) {
                this.E = r0_LocationManager;
            } else {
                r0_LocationManager = this.E;
            }
        }
        Criteria r1_Criteria = p;
        if (r1_Criteria == null) {
            r1_Criteria = new Criteria();
        }
        String r1_String = r0_LocationManager.getBestProvider(r1_Criteria, true);
        if (r1_String != null) {
            r0_LocationManager.requestLocationUpdates(r1_String, 0, 0.0f, this, Looper.getMainLooper());
            return r0_LocationManager.getLastKnownLocation(r1_String);
        }
        return null;
    }

    static String d() {
        return h != null ? h : i;
    }

    static boolean e() {
        return q ? j.aa.n() : false;
    }

    public static void enableAppCircle() {
        q = true;
    }

    public static void endTimedEvent(String r4_String) {
        try {
            j.c(r4_String);
        } catch (Throwable th) {
            i.b("FlurryAgent", "Failed to signify the end of event: " + r4_String, th);
        }
    }

    static String f() {
        return j.z;
    }

    public static int getAgentVersion() {
        return 117;
    }

    public static AppCircle getAppCircle() {
        return r;
    }

    public static boolean getForbidPlaintextFallback() {
        return false;
    }

    public static String getPhoneId() {
        return j.o();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void j() {
        /*
        r6_this = this;
        monitor-enter(r6);
        r1 = 0;
        r3 = new java.io.ByteArrayOutputStream;	 //Catch:{ IOException -> 0x017f, all -> 0x0177 }
        r3.<init>();	 //Catch:{ IOException -> 0x017f, all -> 0x0177 }
        r2 = new java.io.DataOutputStream;	 //Catch:{ IOException -> 0x017f, all -> 0x0177 }
        r2.<init>(r3);	 //Catch:{ IOException -> 0x017f, all -> 0x0177 }
        r0 = 1;
        r2.writeShort(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.B;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeUTF(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.J;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeLong(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.L;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeLong(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = 0;
        r2.writeLong(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.M;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeUTF(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.N;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeUTF(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.O;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeByte(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.P;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 != 0) goto L_0x00a0;
    L_0x0037:
        r0 = "";
    L_0x0039:
        r2.writeUTF(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.T;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 != 0) goto L_0x00a3;
    L_0x0040:
        r0 = 0;
        r2.writeBoolean(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
    L_0x0044:
        r0 = r6.Z;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeInt(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = -1;
        r2.writeByte(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = -1;
        r2.writeByte(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.Q;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeByte(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.R;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 != 0) goto L_0x00d4;
    L_0x005a:
        r0 = 0;
        r2.writeBoolean(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
    L_0x005e:
        r0 = r6.U;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.size();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeShort(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.U;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.entrySet();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r4 = r0.iterator();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
    L_0x0071:
        r0 = r4.hasNext();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 == 0) goto L_0x00e3;
    L_0x0077:
        r0 = r4.next();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = (java.util.Map.Entry) r0;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r1 = r0.getKey();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r1 = (java.lang.String) r1;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeUTF(r1);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.getValue();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = (com.flurry.android.s) r0;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.a;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeInt(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        goto L_0x0071;
    L_0x0092:
        r0 = move-exception;
        r1 = r2;
    L_0x0094:
        r2 = "FlurryAgent";
        r3 = "";
        com.flurry.android.i.b(r2, r3, r0);	 //Catch:{ all -> 0x017b }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x00d1 }
    L_0x009e:
        monitor-exit(r6);
        return;
    L_0x00a0:
        r0 = r6.P;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        goto L_0x0039;
    L_0x00a3:
        r0 = 1;
        r2.writeBoolean(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.T;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.getLatitude();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = a(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeDouble(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.T;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.getLongitude();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = a(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeDouble(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.T;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.getAccuracy();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeFloat(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        goto L_0x0044;
    L_0x00cc:
        r0 = move-exception;
    L_0x00cd:
        com.flurry.android.ad.a(r2);	 //Catch:{ all -> 0x00d1 }
        throw r0;	 //Catch:{ all -> 0x00d1 }
    L_0x00d1:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x00d4:
        r0 = 1;
        r2.writeBoolean(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.R;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.longValue();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeLong(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        goto L_0x005e;
    L_0x00e3:
        r0 = r6.V;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.size();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeShort(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.V;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r1 = r0.iterator();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
    L_0x00f2:
        r0 = r1.hasNext();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 == 0) goto L_0x0106;
    L_0x00f8:
        r0 = r1.next();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = (com.flurry.android.u) r0;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.b();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.write(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        goto L_0x00f2;
    L_0x0106:
        r0 = r6.W;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeBoolean(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.S;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeInt(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.Y;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.size();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeShort(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r6.Y;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r1 = r0.iterator();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
    L_0x011f:
        r0 = r1.hasNext();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 == 0) goto L_0x0140;
    L_0x0125:
        r0 = r1.next();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = (com.flurry.android.b) r0;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r4 = r0.a;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeLong(r4);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r4 = r0.b;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeUTF(r4);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r4 = r0.c;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeUTF(r4);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.d;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeUTF(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        goto L_0x011f;
    L_0x0140:
        r0 = q;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 == 0) goto L_0x0165;
    L_0x0144:
        r0 = r6.aa;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = r0.g();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r1 = r0.size();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r2.writeShort(r1);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r1 = r0.iterator();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
    L_0x0155:
        r0 = r1.hasNext();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        if (r0 == 0) goto L_0x0169;
    L_0x015b:
        r0 = r1.next();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0 = (com.flurry.android.ab) r0;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0.a(r2);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        goto L_0x0155;
    L_0x0165:
        r0 = 0;
        r2.writeShort(r0);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
    L_0x0169:
        r0 = r6.I;	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r1 = r3.toByteArray();	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        r0.add(r1);	 //Catch:{ IOException -> 0x0092, all -> 0x00cc }
        com.flurry.android.ad.a(r2);	 //Catch:{ all -> 0x00d1 }
        goto L_0x009e;
    L_0x0177:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00cd;
    L_0x017b:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00cd;
    L_0x017f:
        r0 = move-exception;
        goto L_0x0094;
        */

    }

    private synchronized void k() {
        this.Z++;
    }

    private static String l() {
        if (c != null) {
            return c;
        }
        if (n) {
            return d;
        }
        if (m) {
            return e;
        }
        return d;
    }

    public static void logEvent(String r4_String) {
        try {
            j.a(r4_String, null, false);
        } catch (Throwable th) {
            i.b("FlurryAgent", "Failed to log event: " + r4_String, th);
        }
    }

    public static void logEvent(String r4_String, Map r5_Map) {
        try {
            j.a(r4_String, r5_Map, false);
        } catch (Throwable th) {
            i.b("FlurryAgent", "Failed to log event: " + r4_String, th);
        }
    }

    public static void logEvent(String r4_String, Map r5_Map, boolean r6z) {
        try {
            j.a(r4_String, r5_Map, r6z);
        } catch (Throwable th) {
            i.b("FlurryAgent", "Failed to log event: " + r4_String, th);
        }
    }

    public static void logEvent(String r4_String, boolean r5z) {
        try {
            j.a(r4_String, null, r5z);
        } catch (Throwable th) {
            i.b("FlurryAgent", "Failed to log event: " + r4_String, th);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m() {
        /*
        r6_this = this;
        r2 = 0;
        monitor-enter(r6);
        r0 = r6.u;	 //Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r0 = a(r0);	 //Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        if (r0 != 0) goto L_0x0010;
    L_0x000a:
        r0 = 0;
        com.flurry.android.ad.a(r0);	 //Catch:{ all -> 0x007a }
    L_0x000e:
        monitor-exit(r6);
        return;
    L_0x0010:
        r0 = new java.io.FileOutputStream;	 //Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r1 = r6.u;	 //Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r0.<init>(r1);	 //Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r1 = new java.io.DataOutputStream;	 //Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r1.<init>(r0);	 //Catch:{ Throwable -> 0x0087, all -> 0x0095 }
        r0 = 46586; // 0xb5fa float:6.5281E-41 double:2.30165E-319;
        r1.writeShort(r0);	 //Catch:{ Throwable -> 0x009d }
        r0 = 2;
        r1.writeShort(r0);	 //Catch:{ Throwable -> 0x009d }
        r0 = r6.z;	 //Catch:{ Throwable -> 0x009d }
        r1.writeUTF(r0);	 //Catch:{ Throwable -> 0x009d }
        r0 = r6.F;	 //Catch:{ Throwable -> 0x009d }
        r1.writeUTF(r0);	 //Catch:{ Throwable -> 0x009d }
        r0 = r6.G;	 //Catch:{ Throwable -> 0x009d }
        r1.writeBoolean(r0);	 //Catch:{ Throwable -> 0x009d }
        r2 = r6.H;	 //Catch:{ Throwable -> 0x009d }
        r1.writeLong(r2);	 //Catch:{ Throwable -> 0x009d }
        r0 = r6.I;	 //Catch:{ Throwable -> 0x009d }
        r0 = r0.size();	 //Catch:{ Throwable -> 0x009d }
        r0 = r0 + -1;
        r2 = r0;
    L_0x0043:
        if (r2 < 0) goto L_0x0072;
    L_0x0045:
        r0 = r6.I;	 //Catch:{ Throwable -> 0x009d }
        r0 = r0.get(r2);	 //Catch:{ Throwable -> 0x009d }
        r0 = (byte[]) r0;	 //Catch:{ Throwable -> 0x009d }
        r3 = r0.length;	 //Catch:{ Throwable -> 0x009d }
        r4 = r3 + 2;
        r5 = r1.size();	 //Catch:{ Throwable -> 0x009d }
        r4 = r4 + r5;
        r5 = 50000; // 0xc350 float:7.0065E-41 double:2.47033E-319;
        if (r4 <= r5) goto L_0x007d;
    L_0x005a:
        r0 = "FlurryAgent";
        r3 = new java.lang.StringBuilder;	 //Catch:{ Throwable -> 0x009d }
        r3.<init>();	 //Catch:{ Throwable -> 0x009d }
        r4 = "discarded sessions: ";
        r3 = r3.append(r4);	 //Catch:{ Throwable -> 0x009d }
        r2 = r3.append(r2);	 //Catch:{ Throwable -> 0x009d }
        r2 = r2.toString();	 //Catch:{ Throwable -> 0x009d }
        com.flurry.android.i.a(r0, r2);	 //Catch:{ Throwable -> 0x009d }
    L_0x0072:
        r0 = 0;
        r1.writeShort(r0);	 //Catch:{ Throwable -> 0x009d }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x007a }
        goto L_0x000e;
    L_0x007a:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x007d:
        r1.writeShort(r3);	 //Catch:{ Throwable -> 0x009d }
        r1.write(r0);	 //Catch:{ Throwable -> 0x009d }
        r0 = r2 + -1;
        r2 = r0;
        goto L_0x0043;
    L_0x0087:
        r0 = move-exception;
        r1 = r2;
    L_0x0089:
        r2 = "FlurryAgent";
        r3 = "";
        com.flurry.android.i.b(r2, r3, r0);	 //Catch:{ all -> 0x009b }
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x007a }
        goto L_0x000e;
    L_0x0095:
        r0 = move-exception;
        r1 = r2;
    L_0x0097:
        com.flurry.android.ad.a(r1);	 //Catch:{ all -> 0x007a }
        throw r0;	 //Catch:{ all -> 0x007a }
    L_0x009b:
        r0 = move-exception;
        goto L_0x0097;
    L_0x009d:
        r0 = move-exception;
        goto L_0x0089;
        */

    }

    private synchronized void n() {
        if (this.E != null) {
            this.E.removeUpdates(this);
        }
    }

    private synchronized String o() {
        return this.F;
    }

    public static void onEndSession(Context r3_Context) {
        if (r3_Context == null) {
            throw new NullPointerException("Null context");
        } else {
            try {
                j.a(r3_Context, false);
            } catch (Throwable th) {
                i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
            }
        }
    }

    public static void onError(String r3_String, String r4_String, String r5_String) {
        try {
            j.a(r3_String, r4_String, r5_String);
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    public static void onEvent(String r3_String) {
        try {
            j.a(r3_String, null, false);
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    public static void onEvent(String r3_String, Map r4_Map) {
        try {
            j.a(r3_String, r4_Map, false);
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    public static void onPageView() {
        try {
            j.k();
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    public static void onStartSession(Context r3_Context, String r4_String) {
        if (r3_Context == null) {
            throw new NullPointerException("Null context");
        } else if (r4_String == null || r4_String.length() == 0) {
            throw new IllegalArgumentException("Api key not specified");
        } else {
            try {
                j.b(r3_Context, r4_String);
            } catch (Throwable th) {
                i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
            }
        }
    }

    public static void setAge(int r7i) {
        if (r7i <= 0 || r7i >= 110) {
        } else {
            Date r1_Date = new Date(new Date(System.currentTimeMillis() - ((long) r7i) * 31449600000L).getYear(), 1, 1);
            j.R = Long.valueOf(r1_Date.getTime());
        }
    }

    public static void setCanvasUrl(String r0_String) {
        f = r0_String;
    }

    public static void setCaptureUncaughtExceptions(boolean r3z) {
        synchronized (j) {
            if (j.v) {
                i.b("FlurryAgent", "Cannot setCaptureUncaughtExceptions after onSessionStart");
            } else {
                o = r3z;
            }
        }
    }

    public static void setCatalogIntentName(String r0_String) {
        a = r0_String;
    }

    public static void setContinueSessionMillis(long r3j) {
        if (r3j < 5000) {
            i.b("FlurryAgent", "Invalid time set for session resumption: " + r3j);
        } else {
            synchronized (j) {
                k = r3j;
            }
        }
    }

    public static void setDefaultNoAdsMessage(String r1_String) {
        if (q) {
            if (r1_String == null) {
                r1_String = RContactStorage.PRIMARY_KEY;
            }
            ag.b = r1_String;
        }
    }

    public static void setGender(byte r2b) {
        switch (r2b) {
            case XListViewHeader.STATE_NORMAL:
            case XListViewHeader.STATE_READY:
                j.Q = r2b;
                return;
        }
        j.Q = (byte) -1;
    }

    public static void setGetAppUrl(String r0_String) {
        h = r0_String;
    }

    public static void setLocationCriteria(Criteria r2_Criteria) {
        synchronized (j) {
            p = r2_Criteria;
        }
    }

    public static void setLogEnabled(boolean r2z) {
        synchronized (j) {
            if (r2z) {
                i.b();
            } else {
                i.a();
            }
        }
    }

    public static void setLogEvents(boolean r2z) {
        synchronized (j) {
            l = r2z;
        }
    }

    public static void setLogLevel(int r2i) {
        synchronized (j) {
            i.a(r2i);
        }
    }

    public static void setReportLocation(boolean r2z) {
        synchronized (j) {
            j.C = r2z;
        }
    }

    public static void setReportUrl(String r0_String) {
        c = r0_String;
    }

    public static void setUseHttps(boolean r0z) {
        m = r0z;
    }

    public static void setUserId(String r3_String) {
        synchronized (j) {
            j.P = ad.a(r3_String, (int)Util.MASK_8BIT);
        }
    }

    public static void setVersionName(String r2_String) {
        synchronized (j) {
            j.B = r2_String;
        }
    }

    final void a(Throwable r5_Throwable) {
        r5_Throwable.printStackTrace();
        String r0_String = RContactStorage.PRIMARY_KEY;
        StackTraceElement[] r1_StackTraceElementA = r5_Throwable.getStackTrace();
        if (r1_StackTraceElementA == null || r1_StackTraceElementA.length <= 0) {
            if (r5_Throwable.getMessage() != null) {
                r0_String = r5_Throwable.getMessage();
            }
            onError("uncaught", r0_String, r5_Throwable.getClass().toString());
            this.y.clear();
            a(null, true);
        } else {
            StackTraceElement r0_StackTraceElement = r1_StackTraceElementA[0];
            StringBuilder r1_StringBuilder = new StringBuilder();
            r1_StringBuilder.append(r0_StackTraceElement.getClassName()).append(".").append(r0_StackTraceElement.getMethodName()).append(":").append(r0_StackTraceElement.getLineNumber());
            if (r5_Throwable.getMessage() != null) {
                r1_StringBuilder.append(" (" + r5_Throwable.getMessage() + ")");
            }
            r0_String = r1_StringBuilder.toString();
            onError("uncaught", r0_String, r5_Throwable.getClass().toString());
            this.y.clear();
            a(null, true);
        }
    }

    public final synchronized void onLocationChanged(Location r4_Location) {
        try {
            this.T = r4_Location;
            n();
        } catch (Throwable th) {
            i.b("FlurryAgent", RContactStorage.PRIMARY_KEY, th);
        }
    }

    public final void onProviderDisabled(String r1_String) {
    }

    public final void onProviderEnabled(String r1_String) {
    }

    public final void onStatusChanged(String r1_String, int r2i, Bundle r3_Bundle) {
    }
}