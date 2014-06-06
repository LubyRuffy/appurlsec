package com.aps;

import android.location.GpsSatellite;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import com.amap.api.location.LocationManagerProxy;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public final class ap implements Listener, NmeaListener {
    private long a;
    private long b;
    private boolean c;
    private List d;
    private String e;
    private String f;
    private String g;
    private /* synthetic */ t h;

    protected ap(t r5_t) {
        this.h = r5_t;
        this.a = 0;
        this.b = 0;
        this.c = false;
        this.d = new ArrayList();
        this.e = null;
        this.f = null;
        this.g = null;
    }

    public final void a(String r12_String) {
        if (System.currentTimeMillis() - this.b <= 400 || (!this.c) || this.d.size() <= 0) {
            if (r12_String.startsWith("$GPGGA")) {
                this.c = true;
                this.e = r12_String.trim();
            } else if (r12_String.startsWith("$GPGSV")) {
                this.d.add(r12_String.trim());
            } else if (r12_String.startsWith("$GPGSA")) {
                this.g = r12_String.trim();
            }
            this.b = System.currentTimeMillis();
        } else {
            s r0_s;
            try {
                r0_s = new s(this.d, this.e, null, this.g);
            } catch (Exception e) {
                t.d(this.h, 0);
            }
            if (r0_s.a()) {
                t.d(this.h, t.a(this.h, r0_s, t.m(this.h)));
                if (t.n(this.h) > 0) {
                    t r1_t = this.h;
                    Locale r2_Locale = Locale.CHINA;
                    Object[] r4_ObjectA = new Object[3];
                    r4_ObjectA[0] = Double.valueOf(r0_s.c());
                    r4_ObjectA[1] = Double.valueOf(r0_s.b());
                    r4_ObjectA[2] = Integer.valueOf(t.n(this.h));
                    t.b(r1_t, String.format(r2_Locale, "&nmea=%.1f|%.1f&g_tp=%d", r4_ObjectA));
                }
                this.d.clear();
                this.g = null;
                this.f = null;
                this.e = null;
                this.c = false;
                if (r12_String.startsWith("$GPGGA")) {
                    if (r12_String.startsWith("$GPGSV")) {
                        if (r12_String.startsWith("$GPGSA")) {
                            this.b = System.currentTimeMillis();
                        } else {
                            this.g = r12_String.trim();
                        }
                    } else {
                        this.d.add(r12_String.trim());
                    }
                } else {
                    this.c = true;
                    this.e = r12_String.trim();
                }
                this.b = System.currentTimeMillis();
            } else {
                t.d(this.h, 0);
                this.d.clear();
                this.g = null;
                this.f = null;
                this.e = null;
                this.c = false;
                if (r12_String.startsWith("$GPGGA")) {
                    this.c = true;
                    this.e = r12_String.trim();
                } else if (r12_String.startsWith("$GPGSV")) {
                    this.d.add(r12_String.trim());
                } else if (r12_String.startsWith("$GPGSA")) {
                    this.g = r12_String.trim();
                }
                this.b = System.currentTimeMillis();
            }
        }
    }

    public final void onGpsStatusChanged(int r8i) {
        if (t.e(this.h) == null) {
        } else {
            switch (r8i) {
                case XListViewHeader.STATE_REFRESHING:
                    t.c(this.h, 0);
                case XListViewFooter.STATE_NODATA:
                    if (t.a || System.currentTimeMillis() - this.a >= 10000) {
                        if (t.i(this.h) == null) {
                            t.a(this.h, t.e(this.h).getGpsStatus(null));
                        } else {
                            t.e(this.h).getGpsStatus(t.i(this.h));
                        }
                        Iterator r4_Iterator = t.i(this.h).getSatellites().iterator();
                        t.a(this.h, 0);
                        t.b(this.h, 0);
                        t.a(this.h, new HashMap());
                        int r1i = 0;
                        int r2i = 0;
                        int r3i = 0;
                        while (r4_Iterator.hasNext()) {
                            GpsSatellite r0_GpsSatellite = (GpsSatellite) r4_Iterator.next();
                            r2i++;
                            if (r0_GpsSatellite.usedInFix()) {
                                r3i++;
                            }
                            if (r0_GpsSatellite.getSnr() > 0.0f) {
                                r1i++;
                            }
                            if (r0_GpsSatellite.getSnr() >= ((float) t.m())) {
                                t.j(this.h);
                            }
                        }
                        t.c(this.h, r1i);
                        t.b(this.h, t.k(this.h));
                        if (!t.a) {
                            if ((r3i > 3 || r2i > 15) && t.e(this.h).getLastKnownLocation(LocationManagerProxy.GPS_PROVIDER) != null) {
                                this.a = System.currentTimeMillis();
                            }
                        }
                    }
            }
        }
    }

    public final void onNmeaReceived(long r4j, String r6_String) {
        if (t.a && r6_String != null && (!r6_String.equals(RContactStorage.PRIMARY_KEY)) && r6_String.length() >= 9 && r6_String.length() <= 150) {
            t.l(this.h).sendMessage(t.l(this.h).obtainMessage(1, r6_String));
        }
    }
}