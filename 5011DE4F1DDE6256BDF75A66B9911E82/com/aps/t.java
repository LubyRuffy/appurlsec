package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import com.amap.api.location.LocationManagerProxy;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class t {
    private static float O;
    private static float P;
    private static float Q;
    private static float R;
    private static int S;
    private static int T;
    private static int U;
    private static int V;
    private static int W;
    private static int X;
    private static int Y;
    protected static boolean a;
    protected static boolean b;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static int g;
    private static int h;
    private static Object i;
    private static t j;
    private Looper A;
    private aq B;
    private Location C;
    private ap D;
    private Handler E;
    private ar F;
    private LocationListener G;
    private BroadcastReceiver H;
    private GpsStatus I;
    private int J;
    private int K;
    private HashMap L;
    private int M;
    private int N;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private long p;
    private Context q;
    private LocationManager r;
    private ae s;
    private at t;
    private ba u;
    private ab v;
    private az w;
    private as x;
    private x y;
    private Thread z;

    static {
        a = false;
        b = true;
        c = 1000;
        d = 2;
        e = 10;
        f = 10;
        g = 50;
        h = 200;
        i = new Object();
        O = 1.1f;
        P = 2.2f;
        Q = 2.3f;
        R = 3.8f;
        S = 3;
        T = 10;
        U = 2;
        V = 7;
        W = 20;
        X = 70;
        Y = 120;
    }

    private t(Context r6_Context) {
        boolean r0z;
        this.k = false;
        this.l = false;
        this.m = 0;
        this.n = 0;
        this.o = 10000;
        this.p = 0;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = new ar(this);
        this.G = new ah(this);
        this.H = new ai(this);
        this.I = null;
        this.J = 0;
        this.K = 0;
        this.L = null;
        this.M = 0;
        this.N = 0;
        this.q = r6_Context;
        this.s = ae.a(r6_Context);
        this.y = new x();
        this.t = new at(this.s);
        this.v = new ab(r6_Context);
        this.u = new ba(this.v);
        this.w = new az(this.v);
        this.r = (LocationManager) this.q.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        this.x = as.a(this.q);
        this.x.a(this.F);
        n();
        List r0_List = this.r.getAllProviders();
        r0z = r0_List != null && r0_List.contains(LocationManagerProxy.GPS_PROVIDER) && r0_List.contains("passive");
        this.l = r0z;
        q.a(r6_Context);
    }

    static /* synthetic */ int a(t r6_t, s r7_s, int r8i) {
        if (r6_t.M >= T) {
            return 1;
        }
        if (r6_t.M <= S) {
            return 4;
        }
        double r2d = r7_s.c();
        if (r2d <= ((double) O)) {
            return 1;
        }
        if (r2d >= ((double) P)) {
            return 4;
        }
        r2d = r7_s.b();
        if (r2d <= ((double) Q)) {
            return 1;
        }
        if (r2d >= ((double) R)) {
            return 4;
        }
        if (r8i >= V) {
            return 1;
        }
        if (r8i <= U) {
            return 4;
        }
        if (r6_t.L != null) {
            return r6_t.a(r6_t.L);
        }
        return XListViewFooter.STATE_NOMORE;
    }

    private int a(HashMap r13_HashMap) {
        if (this.J > 4) {
            int r0i;
            List r3_List = new ArrayList();
            List r4_List = new ArrayList();
            Iterator r2_Iterator = r13_HashMap.entrySet().iterator();
            int r1i = 0;
            while (r2_Iterator.hasNext()) {
                List r0_List = (List) ((Entry) r2_Iterator.next()).getValue();
                if (r0_List != null) {
                    Object r0_Object = a(r0_List);
                    if (r0_Object != null) {
                        r3_List.add(r0_Object);
                        r0i = r1i + 1;
                        r4_List.add(Integer.valueOf(r1i));
                    }
                    r0i = r1i;
                } else {
                    r0i = r1i;
                }
                r1i = r0i;
            }
            if (!r3_List.isEmpty()) {
                double r0d;
                double[] r5_doubleA = new double[2];
                int r6i = r3_List.size();
                int r2i = 0;
                while (r2i < r6i) {
                    double[] r1_doubleA = (double[]) r3_List.get(r2i);
                    r0i = ((Integer) r4_List.get(r2i)).intValue();
                    r1_doubleA[0] = r1_doubleA[0] * ((double) r0i);
                    r1_doubleA[1] = r1_doubleA[1] * ((double) r0i);
                    r5_doubleA[0] = r5_doubleA[0] + r1_doubleA[0];
                    r5_doubleA[1] = r5_doubleA[1] + r1_doubleA[1];
                    r2i++;
                }
                r5_doubleA[0] = r5_doubleA[0] / ((double) r6i);
                r5_doubleA[1] = r5_doubleA[1] / ((double) r6i);
                double r2d = r5_doubleA[0];
                double r6d = r5_doubleA[1];
                if (r6d == 0.0d) {
                    if (r2d > 0.0d) {
                        r0d = 90.0d;
                    } else if (r2d < 0.0d) {
                        r0d = 270.0d;
                    } else {
                        r0d = 0.0d;
                    }
                } else {
                    r0d = Math.toDegrees(Math.atan(r2d / r6d));
                }
                double[] r4_doubleA = new double[2];
                r4_doubleA[0] = Math.sqrt(r2d * r2d + r6d * r6d);
                r4_doubleA[1] = r0d;
                Locale r0_Locale = Locale.CHINA;
                Object[] r2_ObjectA = new Object[4];
                r2_ObjectA[0] = Long.valueOf(Math.round(r5_doubleA[0] * 100.0d));
                r2_ObjectA[1] = Long.valueOf(Math.round(r5_doubleA[1] * 100.0d));
                r2_ObjectA[2] = Long.valueOf(Math.round(r4_doubleA[0] * 100.0d));
                r2_ObjectA[3] = Long.valueOf(Math.round(r4_doubleA[1] * 100.0d));
                String.format(r0_Locale, "%d,%d,%d,%d", r2_ObjectA);
                if (r4_doubleA[0] <= ((double) X)) {
                    return 1;
                }
                if (r4_doubleA[0] >= ((double) Y)) {
                    return XListViewFooter.STATE_NODATA;
                }
            }
        }
        return XListViewFooter.STATE_NOMORE;
    }

    public static t a(Context r2_Context) {
        if (j == null) {
            synchronized (i) {
                if (j == null) {
                    j = new t(r2_Context);
                }
            }
        }
        return j;
    }

    static /* synthetic */ void a(t r6_t, Location r7_Location, boolean r8z) {
        ao r1_ao;
        int r0i = 1;
        System.currentTimeMillis();
        boolean r3z = r6_t.t.a(r7_Location);
        if (r3z) {
            r6_t.t.b.b = new Location(r7_Location);
        }
        boolean r1z = r6_t.t.b(r7_Location);
        if (r1z) {
            r6_t.t.a.b = new Location(r7_Location);
        }
        if (r8z) {
            r7_Location = r6_t.C;
            r1z = true;
            r3z = true;
        }
        if (r3z) {
            if (r1z) {
                r0i = XListViewFooter.STATE_NOMORE;
            }
        } else if (r1z) {
            r0i = XListViewHeader.STATE_REFRESHING;
        } else {
            r0i = 0;
        }
        try {
            x r1_x = r6_t.y;
            r1_ao = x.a(r7_Location, r6_t.s, r0i, (byte) r6_t.N);
        } catch (Exception e) {
            r1_ao = null;
        }
        if (r1_ao == null || r6_t.s == null) {
        } else {
            List r3_List = r6_t.s.n();
            Long r0_Long = Long.valueOf(0);
            if (r3_List == null || r3_List.size() <= 0) {
                r6_t.u.a(r0_Long.longValue(), r1_ao.a());
            } else {
                r0_Long = (Long) r3_List.get(0);
                r6_t.u.a(r0_Long.longValue(), r1_ao.a());
            }
        }
    }

    private double[] a(List r13_List) {
        if (r13_List == null || r13_List.isEmpty()) {
            return null;
        }
        double[] r1_doubleA = new double[2];
        Iterator r2_Iterator = r13_List.iterator();
        while (r2_Iterator.hasNext()) {
            GpsSatellite r0_GpsSatellite = (GpsSatellite) r2_Iterator.next();
            if (r0_GpsSatellite != null) {
                double r3d = (double) (90.0f - r0_GpsSatellite.getElevation());
                double r5d = (double) r0_GpsSatellite.getAzimuth();
                double[] r0_doubleA = new double[2];
                r0_doubleA[0] = Math.sin(Math.toRadians(r5d)) * r3d;
                r0_doubleA[1] = r3d * Math.cos(Math.toRadians(r5d));
                r1_doubleA[0] = r1_doubleA[0] + r0_doubleA[0];
                r1_doubleA[1] = r1_doubleA[1] + r0_doubleA[1];
            }
        }
        int r0i = r13_List.size();
        r1_doubleA[0] = r1_doubleA[0] / ((double) r0i);
        r1_doubleA[1] = r1_doubleA[1] / ((double) r0i);
        return r1_doubleA;
    }

    static /* synthetic */ String b(t r0_t, String r1_String) {
        return r1_String;
    }

    static /* synthetic */ int j(t r2_t) {
        int r0i = r2_t.K;
        r2_t.K = r0i + 1;
        return r0i;
    }

    private void n() {
        this.m = this.x.b() * 1000;
        this.n = this.x.c();
        at r0_at = this.t;
        int r0i = this.m;
        r0i = this.n;
        at.a();
    }

    public void a() {
        if ((!this.l) || this.s == null) {
            q.a("collector", "no gps or passive, so not to collect!");
        } else {
            if (!a) {
                IntentFilter r0_IntentFilter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
                r0_IntentFilter.addAction("android.location.GPS_FIX_CHANGE");
                this.q.registerReceiver(this.H, r0_IntentFilter);
                String r0_String = RContactStorage.PRIMARY_KEY;
                this.r.removeUpdates(this.G);
                if (this.A != null) {
                    this.A.quit();
                    this.A = null;
                }
                if (this.z != null) {
                    this.z.interrupt();
                    this.z = null;
                }
                this.z = new aj(this, r0_String);
                this.z.start();
                this.s.a();
                a = true;
            }
        }
    }

    public void a(int r3i) {
        if (r3i == 256 || r3i == 8736 || r3i == 768) {
            this.v.a(r3i);
        } else {
            throw new RuntimeException("invalid Size! must be COLLECTOR_SMALL_SIZE or COLLECTOR_BIG_SIZE or COLLECTOR_MEDIUM_SIZE");
        }
    }

    public void a(aa r5_aa, String r6_String) {
        boolean r1z = this.x.a(r6_String);
        if (r5_aa != null) {
            byte[] r2_byteA = r5_aa.a();
            if ((!r1z) || r2_byteA == null) {
                r5_aa.a(r1z);
                this.w.a(r5_aa);
            } else {
                NetworkInfo r0_NetworkInfo = ((ConnectivityManager) this.q.getSystemService("connectivity")).getActiveNetworkInfo();
                if (r0_NetworkInfo == null || (!r0_NetworkInfo.isConnected())) {
                    r5_aa.a(r1z);
                    this.w.a(r5_aa);
                } else if (r0_NetworkInfo.getType() == 1) {
                    this.x.a(r2_byteA.length + this.x.e());
                    r5_aa.a(r1z);
                    this.w.a(r5_aa);
                } else {
                    this.x.b(r2_byteA.length + this.x.f());
                    r5_aa.a(r1z);
                    this.w.a(r5_aa);
                }
            }
        }
    }

    public void b() {
        if (this.l && this.s != null && a) {
            this.q.unregisterReceiver(this.H);
            this.r.removeGpsStatusListener(this.D);
            this.r.removeNmeaListener(this.D);
            this.D = null;
            this.r.removeUpdates(this.G);
            if (this.A != null) {
                this.A.quit();
                this.A = null;
            }
            if (this.z != null) {
                this.z.interrupt();
                this.z = null;
            }
            if (this.B != null) {
                this.k = false;
                this.B.interrupt();
                this.B = null;
            }
            this.s.b();
            a = false;
        }
    }

    public void c() {
        if (this.l) {
            b();
        }
    }

    public aa d() {
        return this.x.a() ? this.w.a(this.x.d()) : null;
    }

    public boolean e() {
        boolean r0z = false;
        if (this.s == null) {
            return false;
        }
        List r1_List = this.s.n();
        return (r1_List == null || r1_List.size() <= 0) ? false : this.v.b(((Long) r1_List.get(r0z)).longValue());
    }

    public int f() {
        return this.w != null ? this.w.a() : 0;
    }
}