package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import com.amap.api.location.core.d;
import com.aps.h;
import java.util.Iterator;
import java.util.Vector;

// compiled from: AMapLocationManager.java
public class a {
    public static int a;
    static boolean b;
    static long c;
    static boolean d;
    static boolean e;
    private static Vector<f> g;
    private static c i;
    private static b j;
    private static a k;
    private Context f;
    private a h;
    private AMapLocation l;
    private Thread m;

    // compiled from: AMapLocationManager.java
    class a extends Handler {
        a() {
        }

        public void handleMessage(Message r4_Message) {
            if (r4_Message.what == a) {
                Iterator r1_Iterator = g.iterator();
                while (r1_Iterator.hasNext()) {
                    ((f) r1_Iterator.next()).c.onLocationChanged((AMapLocation) r4_Message.obj);
                }
                a.this.l = (AMapLocation) r4_Message.obj;
                if (a.this.l != null) {
                    d.a(a.this.f, a.this.l);
                }
            }
        }
    }

    static {
        g = null;
        a = 100;
        i = null;
        j = null;
        k = null;
        b = false;
        d = true;
        e = true;
    }

    private a(Context r2_Context, LocationManager r3_LocationManager) {
        this.h = null;
        this.f = r2_Context;
        g = new Vector();
        this.h = new a();
        j = b.a(r2_Context, this.h);
        i = c.a(r2_Context, r3_LocationManager, this.h);
    }

    static a a_(Context r1_Context, LocationManager r2_LocationManager) {
        if (k == null) {
            k = new a(r1_Context, r2_LocationManager);
        }
        return k;
    }

    AMapLocation a_() {
        return this.l != null ? this.l : d.b(this.f);
    }

    void a_(double r3d, double r5d, float r7f, long r8j, PendingIntent r10_PendingIntent) {
        h r0_h = new h();
        r0_h.b = r3d;
        r0_h.a = r5d;
        r0_h.c = r7f;
        r0_h.a(r8j);
        j.a(r0_h, r10_PendingIntent);
    }

    void a_(long r7j, float r9f, AMapLocationListener r10_AMapLocationListener, String r11_String) {
        if (r10_AMapLocationListener != null) {
            g.add(new f(r7j, r9f, r10_AMapLocationListener, r11_String));
        }
        if (r11_String == LocationManagerProxy.GPS_PROVIDER) {
            i.a(r7j, r9f, r10_AMapLocationListener, r11_String);
        } else {
            if (r11_String == LocationProviderProxy.AMapNetwork) {
                if (e) {
                    i.a(r7j, r9f, r10_AMapLocationListener, r11_String);
                }
                j.a(r7j);
                d = true;
                if (this.m == null) {
                    this.m = new Thread(j);
                    this.m.start();
                }
            }
        }
    }

    void a_(PendingIntent r2_PendingIntent) {
        j.a(r2_PendingIntent);
    }

    void a_(AMapLocationListener r6_AMapLocationListener) {
        int r2i = g.size();
        int r1i = 0;
        while (r1i < r2i) {
            int r0i;
            f r0_f = (f) g.get(r1i);
            if (r6_AMapLocationListener.equals(r0_f.c)) {
                g.remove(r0_f);
                r0i = r1i - 1;
                r1i = r2i - 1;
            } else {
                r0i = r1i;
                r1i = r2i;
            }
            r2i = r1i;
            r1i = r0i + 1;
        }
        if (i == null || g.size() != 0) {
        } else {
            i.a();
            b = false;
            d = false;
            if (this.m != null) {
                this.m.interrupt();
                this.m = null;
            }
        }
    }

    void a_(boolean r1z) {
        e = r1z;
    }

    void b() {
        if (i != null) {
            i.a();
            i = null;
        }
        if (j != null) {
            j.a();
            j = null;
        }
        g.clear();
        b = false;
        this.m = null;
        k = null;
    }

    int c() {
        return j != null ? j.b() : 0;
    }
}