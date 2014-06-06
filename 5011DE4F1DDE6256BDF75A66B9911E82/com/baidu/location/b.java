package com.baidu.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import com.amap.api.location.LocationManagerProxy;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

class b {
    private static File A;
    private static StringBuffer B;
    private static boolean C;
    private static int D;
    private static int E;
    private static long F;
    private static long G;
    private static long H;
    private static double I;
    private static double J;
    private static int K;
    private static int L;
    private static int M;
    private static int h;
    private static String x;
    private static String z;
    private final long a;
    private Context b;
    private LocationManager c;
    private Location d;
    private b e;
    private GpsStatus f;
    private a g;
    private long i;
    private long j;
    private boolean k;
    private Handler l;
    private boolean m;
    private String n;
    private boolean o;
    private long p;
    private final int q;
    private boolean r;
    private boolean s;
    private List t;
    private String u;
    private String v;
    private String w;
    private d y;


    private class a implements Listener, NmeaListener {
        private a() {
        }

        public void onGpsStatusChanged(int r7i) {
            if (b.this.c == null) {
            } else {
                switch (r7i) {
                    case XListViewHeader.STATE_REFRESHING:
                        b.this.a(null);
                        b.this.b(false);
                        h = 0;
                    case XListViewFooter.STATE_NODATA:
                        j.if(f.v, "gps status change");
                        if (b.this.f == null) {
                            b.this.f = b.this.c.getGpsStatus(null);
                        } else {
                            b.this.c.getGpsStatus(b.this.f);
                        }
                        Iterator r3_Iterator = b.this.f.getSatellites().iterator();
                        int r1i = 0;
                        while (r3_Iterator.hasNext()) {
                            r1i = ((GpsSatellite) r3_Iterator.next()).usedInFix() ? r1i + 1 : r1i;
                        }
                        j.if(f.v, "gps nunmber in count:" + r1i);
                        if (h < 3 || r1i >= 3) {
                            if (r1i >= 3) {
                                b.this.b(false);
                            }
                            if (h > 3 || r1i <= 3) {
                                h = r1i;
                            } else {
                                b.this.b(true);
                                h = r1i;
                            }
                        } else {
                            b.this.i = System.currentTimeMillis();
                            if (r1i >= 3) {
                                if (h > 3 || r1i <= 3) {
                                    h = r1i;
                                } else {
                                    b.this.b(true);
                                    h = r1i;
                                }
                            }
                        }
                        b.this.b(false);
                        if (h > 3 || r1i <= 3) {
                            h = r1i;
                        } else {
                            b.this.b(true);
                            h = r1i;
                        }
                }
            }
        }

        public void onNmeaReceived(long r11j, String r13_String) {
            if (j.m) {
                if (r13_String == null || r13_String.equals(RContactStorage.PRIMARY_KEY) || r13_String.length() < 9 || r13_String.length() > 150 || (!b.this.new())) {
                } else {
                    long r0j = System.currentTimeMillis();
                    j.if(f.v, "gps manager onNmeaReceived : " + r0j + " " + r13_String);
                    if (r0j - b.this.p <= 400 || (!b.this.s) || b.this.t.size() <= 0) {
                    } else {
                        c r0_c;
                        try {
                            r0_c = new c(b.this.t, b.this.u, b.this.v, b.this.w, null);
                        } catch (Exception e) {
                            j.h = 0;
                        }
                        if (r0_c.c()) {
                            j.h = r0_c.b();
                            if (j.h > 0) {
                                Object[] r2_ObjectA = new Object[3];
                                r2_ObjectA[0] = Double.valueOf(r0_c.e());
                                r2_ObjectA[1] = Double.valueOf(r0_c.d());
                                r2_ObjectA[2] = Integer.valueOf(j.h);
                                x = String.format("&nmea=%.1f|%.1f&g_tp=%d", r2_ObjectA);
                            }
                            b.this.t.clear();
                            b.this.u = b.this.v = b.this.w = null;
                            b.this.s = false;
                        } else {
                            j.h = 0;
                            j.if(f.v, "nmea invalid");
                            b.this.t.clear();
                            b.this.u = b.this.v = b.this.w = null;
                            b.this.s = false;
                        }
                    }
                    if (r13_String.startsWith("$GPGGA")) {
                        b.this.s = true;
                        b.this.u = r13_String.trim();
                    } else if (r13_String.startsWith("$GPGSV")) {
                        b.this.t.add(r13_String.trim());
                    } else if (r13_String.startsWith("$GPGSA")) {
                        b.this.w = r13_String.trim();
                    }
                    b.this.p = System.currentTimeMillis();
                }
            } else {
                j.h = 0;
            }
        }
    }

    private class b implements LocationListener {
        private b() {
        }

        public void onLocationChanged(Location r3_Location) {
            b.this.a(r3_Location);
            b.this.k = false;
            if (b.this.o) {
                b.this.b(true);
            }
        }

        public void onProviderDisabled(String r3_String) {
            b.this.a(null);
            b.this.b(false);
        }

        public void onProviderEnabled(String r1_String) {
        }

        public void onStatusChanged(String r5_String, int r6i, Bundle r7_Bundle) {
            switch (r6i) {
                case XListViewHeader.STATE_NORMAL:
                    b.this.a(null);
                    b.this.b(false);
                    break;
                case XListViewHeader.STATE_READY:
                    b.this.j = System.currentTimeMillis();
                    b.this.k = true;
                    b.this.b(false);
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    b.this.k = false;
                    break;
            }
        }
    }

    private class c {
        public int a;
        private boolean c;
        private String d;
        private boolean e;
        private String f;
        private int g;
        private int h;
        private String i;
        private boolean j;
        private String k;
        private char l;
        private String m;
        private boolean n;
        private int o;
        private double p;
        private double q;
        private List r;
        private boolean s;
        private List t;
        private int u;

        private class a {
            private int b;
            private int c;
            private int d;
            private int e;

            public a(int r3i, int r4i, int r5i, int r6i) {
                this.b = 0;
                this.c = 0;
                this.d = 0;
                this.e = 0;
                this.b = r3i;
                this.c = r4i;
                this.d = r5i;
                this.e = r6i;
            }

            public int a_() {
                return this.c;
            }

            public int do() {
                return this.e;
            }

            public int if() {
                return this.d;
            }
        }

        private c(List r7_List, String r8_String, String r9_String, String r10_String) {
            this.c = false;
            this.d = RContactStorage.PRIMARY_KEY;
            this.e = false;
            this.f = RContactStorage.PRIMARY_KEY;
            this.g = 0;
            this.h = 0;
            this.i = RContactStorage.PRIMARY_KEY;
            this.j = false;
            this.k = RContactStorage.PRIMARY_KEY;
            this.l = 'N';
            this.m = RContactStorage.PRIMARY_KEY;
            this.n = false;
            this.o = 1;
            this.p = 0.0d;
            this.q = 0.0d;
            this.r = null;
            this.s = false;
            this.t = null;
            this.u = 0;
            this.a = 0;
            this.r = r7_List;
            this.d = r8_String;
            this.i = r9_String;
            this.m = r10_String;
            this.t = new ArrayList();
            a();
        }

        private int a(boolean r13z, boolean r14z, boolean r15z, boolean r16z, boolean r17z) {
            if (!(this.c)) {
                return 0;
            }
            Iterator r2_Iterator;
            int r1i;
            List r3_List;
            List r5_List;
            List r6_List;
            int r0i;
            Iterator r7_Iterator;
            int r2i;
            a r0_a;
            Object r0_Object;
            double[] r0_doubleA;
            int r3i;
            double[] r1_doubleA;
            if (r13z && this.e) {
                this.a = 1;
                if (this.h >= j.K) {
                    return 1;
                }
                if (this.h <= j.p) {
                    return XListViewFooter.STATE_NODATA;
                }
                if (r14z && this.n) {
                    this.a = 2;
                    if (this.q <= ((double) j.am)) {
                        return 1;
                    }
                    if (this.q >= ((double) j.c)) {
                        return XListViewFooter.STATE_NODATA;
                    }
                    if (r15z && this.n) {
                        this.a = 3;
                        if (this.p > ((double) j.F)) {
                            return 1;
                        }
                        if (this.p < ((double) j.U)) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        if (this.s) {
                            return 0;
                        }
                        if (r16z) {
                            this.a = 4;
                            r2_Iterator = this.t.iterator();
                            r1i = 0;
                            while (r2_Iterator.hasNext()) {
                                r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                            }
                            if (r1i < j.int) {
                                return 1;
                            }
                            if (r1i > j.X) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            if (r17z) {
                                this.a = 5;
                                r3_List = new ArrayList();
                                r5_List = new ArrayList();
                                r6_List = new ArrayList();
                                r0i = 0;
                                while (r0i < 10) {
                                    r3_List.add(new ArrayList());
                                    r0i++;
                                }
                                r7_Iterator = this.t.iterator();
                                r2i = 0;
                                while (r7_Iterator.hasNext()) {
                                    r0_a = (a) r7_Iterator.next();
                                    if (r0_a.do() < 10 || r0_a.if() < 1) {
                                        r0i = r2i;
                                        r2i = r0i;
                                    } else {
                                        ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                        r0i = r2i + 1;
                                        r2i = r0i;
                                    }
                                }
                                if (r2i < 4) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                r1i = 0;
                                while (r1i < r3_List.size()) {
                                    if (((List) r3_List.get(r1i)).size() == 0) {
                                        r1i++;
                                    } else {
                                        r0_Object = a((List) r3_List.get(r1i));
                                        if (r0_Object == null) {
                                            r1i++;
                                        } else {
                                            r5_List.add(r0_Object);
                                            r6_List.add(Integer.valueOf(r1i));
                                            r1i++;
                                        }
                                    }
                                }
                                if (r5_List == null || r5_List.size() <= 0) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                r0_doubleA = (double[]) r5_List.get(0);
                                r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                                r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                                if (r5_List.size() <= 1) {
                                    r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                    if (r0_doubleA[0] <= ((double) j.ad)) {
                                        return 1;
                                    }
                                    if (r0_doubleA[0] >= ((double) j.long)) {
                                        return XListViewFooter.STATE_NODATA;
                                    }
                                    this.a = 0;
                                    return 3;
                                } else {
                                    r3i = 1;
                                    while (r3i < r5_List.size()) {
                                        r1_doubleA = (double[]) r5_List.get(r3i);
                                        r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                        r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                        r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                        r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                        r3i++;
                                    }
                                    r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                    r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                                    r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                    if (r0_doubleA[0] <= ((double) j.ad)) {
                                        return 1;
                                    }
                                    if (r0_doubleA[0] >= ((double) j.long)) {
                                        return XListViewFooter.STATE_NODATA;
                                    }
                                    this.a = 0;
                                    return 3;
                                }
                            } else {
                                this.a = 0;
                                return 3;
                            }
                        } else {
                            if (r17z) {
                                this.a = 5;
                                r3_List = new ArrayList();
                                r5_List = new ArrayList();
                                r6_List = new ArrayList();
                                r0i = 0;
                                while (r0i < 10) {
                                    r3_List.add(new ArrayList());
                                    r0i++;
                                }
                                r7_Iterator = this.t.iterator();
                                r2i = 0;
                                while (r7_Iterator.hasNext()) {
                                    r0_a = (a) r7_Iterator.next();
                                    if (r0_a.do() < 10 || r0_a.if() < 1) {
                                        r0i = r2i;
                                        r2i = r0i;
                                    } else {
                                        ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                        r0i = r2i + 1;
                                        r2i = r0i;
                                    }
                                }
                                if (r2i < 4) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                r1i = 0;
                                while (r1i < r3_List.size()) {
                                    if (((List) r3_List.get(r1i)).size() == 0) {
                                        r0_Object = a((List) r3_List.get(r1i));
                                        if (r0_Object == null) {
                                            r5_List.add(r0_Object);
                                            r6_List.add(Integer.valueOf(r1i));
                                        }
                                    }
                                    r1i++;
                                }
                                if (r5_List == null || r5_List.size() <= 0) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                r0_doubleA = (double[]) r5_List.get(0);
                                r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                                r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                                if (r5_List.size() <= 1) {
                                    r3i = 1;
                                    while (r3i < r5_List.size()) {
                                        r1_doubleA = (double[]) r5_List.get(r3i);
                                        r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                        r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                        r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                        r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                        r3i++;
                                    }
                                    r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                    r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                                }
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                            }
                            this.a = 0;
                            return 3;
                        }
                    } else {
                        if (this.s) {
                            return 0;
                        }
                        if (r16z) {
                            this.a = 4;
                            r2_Iterator = this.t.iterator();
                            r1i = 0;
                            while (r2_Iterator.hasNext()) {
                                if (((a) r2_Iterator.next()).do() < j.for) {
                                }
                                r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                            }
                            if (r1i < j.int) {
                                return 1;
                            }
                            if (r1i > j.X) {
                                return XListViewFooter.STATE_NODATA;
                            }
                        }
                        if (r17z) {
                            this.a = 0;
                            return 3;
                        } else {
                            this.a = 5;
                            r3_List = new ArrayList();
                            r5_List = new ArrayList();
                            r6_List = new ArrayList();
                            r0i = 0;
                            while (r0i < 10) {
                                r3_List.add(new ArrayList());
                                r0i++;
                            }
                            r7_Iterator = this.t.iterator();
                            r2i = 0;
                            while (r7_Iterator.hasNext()) {
                                r0_a = (a) r7_Iterator.next();
                                if (r0_a.do() < 10 || r0_a.if() < 1) {
                                    r0i = r2i;
                                    r2i = r0i;
                                } else {
                                    ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                    r0i = r2i + 1;
                                    r2i = r0i;
                                }
                            }
                            if (r2i < 4) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r1i = 0;
                            while (r1i < r3_List.size()) {
                                if (((List) r3_List.get(r1i)).size() == 0) {
                                    r1i++;
                                } else {
                                    r0_Object = a((List) r3_List.get(r1i));
                                    if (r0_Object == null) {
                                        r1i++;
                                    } else {
                                        r5_List.add(r0_Object);
                                        r6_List.add(Integer.valueOf(r1i));
                                        r1i++;
                                    }
                                }
                            }
                            if (r5_List == null || r5_List.size() <= 0) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r0_doubleA = (double[]) r5_List.get(0);
                            r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                            r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                            if (r5_List.size() <= 1) {
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            } else {
                                r3i = 1;
                                while (r3i < r5_List.size()) {
                                    r1_doubleA = (double[]) r5_List.get(r3i);
                                    r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                    r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                    r3i++;
                                }
                                r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            }
                        }
                    }
                } else if (r15z || this.n) {
                    if (this.s) {
                        return 0;
                    }
                    if (r16z) {
                        this.a = 4;
                        r2_Iterator = this.t.iterator();
                        r1i = 0;
                        while (r2_Iterator.hasNext()) {
                            if (((a) r2_Iterator.next()).do() < j.for) {
                            }
                            r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                        }
                        if (r1i < j.int) {
                            return 1;
                        }
                        if (r1i > j.X) {
                            return XListViewFooter.STATE_NODATA;
                        }
                    }
                    if (r17z) {
                        this.a = 5;
                        r3_List = new ArrayList();
                        r5_List = new ArrayList();
                        r6_List = new ArrayList();
                        r0i = 0;
                        while (r0i < 10) {
                            r3_List.add(new ArrayList());
                            r0i++;
                        }
                        r7_Iterator = this.t.iterator();
                        r2i = 0;
                        while (r7_Iterator.hasNext()) {
                            r0_a = (a) r7_Iterator.next();
                            if (r0_a.do() < 10 || r0_a.if() < 1) {
                                r0i = r2i;
                                r2i = r0i;
                            } else {
                                ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                r0i = r2i + 1;
                                r2i = r0i;
                            }
                        }
                        if (r2i < 4) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        r1i = 0;
                        while (r1i < r3_List.size()) {
                            if (((List) r3_List.get(r1i)).size() == 0) {
                                r0_Object = a((List) r3_List.get(r1i));
                                if (r0_Object == null) {
                                    r5_List.add(r0_Object);
                                    r6_List.add(Integer.valueOf(r1i));
                                }
                            }
                            r1i++;
                        }
                        if (r5_List == null || r5_List.size() <= 0) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        r0_doubleA = (double[]) r5_List.get(0);
                        r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                        r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                        if (r5_List.size() <= 1) {
                            r3i = 1;
                            while (r3i < r5_List.size()) {
                                r1_doubleA = (double[]) r5_List.get(r3i);
                                r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                r3i++;
                            }
                            r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                            r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                        }
                        r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                        if (r0_doubleA[0] <= ((double) j.ad)) {
                            return 1;
                        }
                        if (r0_doubleA[0] >= ((double) j.long)) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        this.a = 0;
                    }
                    this.a = 0;
                    return 3;
                } else {
                    this.a = 3;
                    if (this.p > ((double) j.F)) {
                        return 1;
                    }
                    if (this.p < ((double) j.U)) {
                        return XListViewFooter.STATE_NODATA;
                    }
                    if (this.s) {
                        return 0;
                    }
                    if (r16z) {
                        if (r17z) {
                            this.a = 0;
                            return 3;
                        } else {
                            this.a = 5;
                            r3_List = new ArrayList();
                            r5_List = new ArrayList();
                            r6_List = new ArrayList();
                            r0i = 0;
                            while (r0i < 10) {
                                r3_List.add(new ArrayList());
                                r0i++;
                            }
                            r7_Iterator = this.t.iterator();
                            r2i = 0;
                            while (r7_Iterator.hasNext()) {
                                r0_a = (a) r7_Iterator.next();
                                if (r0_a.do() < 10 || r0_a.if() < 1) {
                                    r0i = r2i;
                                    r2i = r0i;
                                } else {
                                    ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                    r0i = r2i + 1;
                                    r2i = r0i;
                                }
                            }
                            if (r2i < 4) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r1i = 0;
                            while (r1i < r3_List.size()) {
                                if (((List) r3_List.get(r1i)).size() == 0) {
                                    r1i++;
                                } else {
                                    r0_Object = a((List) r3_List.get(r1i));
                                    if (r0_Object == null) {
                                        r1i++;
                                    } else {
                                        r5_List.add(r0_Object);
                                        r6_List.add(Integer.valueOf(r1i));
                                        r1i++;
                                    }
                                }
                            }
                            if (r5_List == null || r5_List.size() <= 0) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r0_doubleA = (double[]) r5_List.get(0);
                            r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                            r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                            if (r5_List.size() <= 1) {
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            } else {
                                r3i = 1;
                                while (r3i < r5_List.size()) {
                                    r1_doubleA = (double[]) r5_List.get(r3i);
                                    r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                    r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                    r3i++;
                                }
                                r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            }
                        }
                    } else {
                        this.a = 4;
                        r2_Iterator = this.t.iterator();
                        r1i = 0;
                        while (r2_Iterator.hasNext()) {
                            if (((a) r2_Iterator.next()).do() < j.for) {
                            }
                            r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                        }
                        if (r1i < j.int) {
                            return 1;
                        }
                        if (r1i > j.X) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        if (r17z) {
                            this.a = 5;
                            r3_List = new ArrayList();
                            r5_List = new ArrayList();
                            r6_List = new ArrayList();
                            r0i = 0;
                            while (r0i < 10) {
                                r3_List.add(new ArrayList());
                                r0i++;
                            }
                            r7_Iterator = this.t.iterator();
                            r2i = 0;
                            while (r7_Iterator.hasNext()) {
                                r0_a = (a) r7_Iterator.next();
                                if (r0_a.do() < 10 || r0_a.if() < 1) {
                                    r0i = r2i;
                                    r2i = r0i;
                                } else {
                                    ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                    r0i = r2i + 1;
                                    r2i = r0i;
                                }
                            }
                            if (r2i < 4) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r1i = 0;
                            while (r1i < r3_List.size()) {
                                if (((List) r3_List.get(r1i)).size() == 0) {
                                    r0_Object = a((List) r3_List.get(r1i));
                                    if (r0_Object == null) {
                                        r5_List.add(r0_Object);
                                        r6_List.add(Integer.valueOf(r1i));
                                    }
                                }
                                r1i++;
                            }
                            if (r5_List == null || r5_List.size() <= 0) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r0_doubleA = (double[]) r5_List.get(0);
                            r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                            r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                            if (r5_List.size() <= 1) {
                                r3i = 1;
                                while (r3i < r5_List.size()) {
                                    r1_doubleA = (double[]) r5_List.get(r3i);
                                    r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                    r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                    r3i++;
                                }
                                r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                            }
                            r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                            if (r0_doubleA[0] <= ((double) j.ad)) {
                                return 1;
                            }
                            if (r0_doubleA[0] >= ((double) j.long)) {
                                return XListViewFooter.STATE_NODATA;
                            }
                        }
                        this.a = 0;
                        return 3;
                    }
                }
            } else if (r14z || this.n) {
                if (r15z || this.n) {
                    if (this.s) {
                        return 0;
                    }
                    if (r16z) {
                        this.a = 4;
                        r2_Iterator = this.t.iterator();
                        r1i = 0;
                        while (r2_Iterator.hasNext()) {
                            if (((a) r2_Iterator.next()).do() < j.for) {
                            }
                            r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                        }
                        if (r1i < j.int) {
                            return 1;
                        }
                        if (r1i > j.X) {
                            return XListViewFooter.STATE_NODATA;
                        }
                    }
                    if (r17z) {
                        this.a = 5;
                        r3_List = new ArrayList();
                        r5_List = new ArrayList();
                        r6_List = new ArrayList();
                        r0i = 0;
                        while (r0i < 10) {
                            r3_List.add(new ArrayList());
                            r0i++;
                        }
                        r7_Iterator = this.t.iterator();
                        r2i = 0;
                        while (r7_Iterator.hasNext()) {
                            r0_a = (a) r7_Iterator.next();
                            if (r0_a.do() < 10 || r0_a.if() < 1) {
                                r0i = r2i;
                                r2i = r0i;
                            } else {
                                ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                r0i = r2i + 1;
                                r2i = r0i;
                            }
                        }
                        if (r2i < 4) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        r1i = 0;
                        while (r1i < r3_List.size()) {
                            if (((List) r3_List.get(r1i)).size() == 0) {
                                r0_Object = a((List) r3_List.get(r1i));
                                if (r0_Object == null) {
                                    r5_List.add(r0_Object);
                                    r6_List.add(Integer.valueOf(r1i));
                                }
                            }
                            r1i++;
                        }
                        if (r5_List == null || r5_List.size() <= 0) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        r0_doubleA = (double[]) r5_List.get(0);
                        r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                        r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                        if (r5_List.size() <= 1) {
                            r3i = 1;
                            while (r3i < r5_List.size()) {
                                r1_doubleA = (double[]) r5_List.get(r3i);
                                r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                r3i++;
                            }
                            r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                            r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                        }
                        r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                        if (r0_doubleA[0] <= ((double) j.ad)) {
                            return 1;
                        }
                        if (r0_doubleA[0] >= ((double) j.long)) {
                            return XListViewFooter.STATE_NODATA;
                        }
                    }
                    this.a = 0;
                    return 3;
                } else {
                    this.a = 3;
                    if (this.p > ((double) j.F)) {
                        return 1;
                    }
                    if (this.p < ((double) j.U)) {
                        return XListViewFooter.STATE_NODATA;
                    }
                    if (this.s) {
                        return 0;
                    }
                    if (r16z) {
                        if (r17z) {
                            this.a = 0;
                            return 3;
                        } else {
                            this.a = 5;
                            r3_List = new ArrayList();
                            r5_List = new ArrayList();
                            r6_List = new ArrayList();
                            r0i = 0;
                            while (r0i < 10) {
                                r3_List.add(new ArrayList());
                                r0i++;
                            }
                            r7_Iterator = this.t.iterator();
                            r2i = 0;
                            while (r7_Iterator.hasNext()) {
                                r0_a = (a) r7_Iterator.next();
                                if (r0_a.do() < 10 || r0_a.if() < 1) {
                                    r0i = r2i;
                                    r2i = r0i;
                                } else {
                                    ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                    r0i = r2i + 1;
                                    r2i = r0i;
                                }
                            }
                            if (r2i < 4) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r1i = 0;
                            while (r1i < r3_List.size()) {
                                if (((List) r3_List.get(r1i)).size() == 0) {
                                    r1i++;
                                } else {
                                    r0_Object = a((List) r3_List.get(r1i));
                                    if (r0_Object == null) {
                                        r1i++;
                                    } else {
                                        r5_List.add(r0_Object);
                                        r6_List.add(Integer.valueOf(r1i));
                                        r1i++;
                                    }
                                }
                            }
                            if (r5_List == null || r5_List.size() <= 0) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r0_doubleA = (double[]) r5_List.get(0);
                            r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                            r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                            if (r5_List.size() <= 1) {
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            } else {
                                r3i = 1;
                                while (r3i < r5_List.size()) {
                                    r1_doubleA = (double[]) r5_List.get(r3i);
                                    r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                    r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                    r3i++;
                                }
                                r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            }
                        }
                    } else {
                        this.a = 4;
                        r2_Iterator = this.t.iterator();
                        r1i = 0;
                        while (r2_Iterator.hasNext()) {
                            if (((a) r2_Iterator.next()).do() < j.for) {
                            }
                            r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                        }
                        if (r1i < j.int) {
                            return 1;
                        }
                        if (r1i > j.X) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        if (r17z) {
                            this.a = 5;
                            r3_List = new ArrayList();
                            r5_List = new ArrayList();
                            r6_List = new ArrayList();
                            r0i = 0;
                            while (r0i < 10) {
                                r3_List.add(new ArrayList());
                                r0i++;
                            }
                            r7_Iterator = this.t.iterator();
                            r2i = 0;
                            while (r7_Iterator.hasNext()) {
                                r0_a = (a) r7_Iterator.next();
                                if (r0_a.do() < 10 || r0_a.if() < 1) {
                                    r0i = r2i;
                                    r2i = r0i;
                                } else {
                                    ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                    r0i = r2i + 1;
                                    r2i = r0i;
                                }
                            }
                            if (r2i < 4) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r1i = 0;
                            while (r1i < r3_List.size()) {
                                if (((List) r3_List.get(r1i)).size() == 0) {
                                    r0_Object = a((List) r3_List.get(r1i));
                                    if (r0_Object == null) {
                                        r5_List.add(r0_Object);
                                        r6_List.add(Integer.valueOf(r1i));
                                    }
                                }
                                r1i++;
                            }
                            if (r5_List == null || r5_List.size() <= 0) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r0_doubleA = (double[]) r5_List.get(0);
                            r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                            r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                            if (r5_List.size() <= 1) {
                                r3i = 1;
                                while (r3i < r5_List.size()) {
                                    r1_doubleA = (double[]) r5_List.get(r3i);
                                    r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                    r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                    r3i++;
                                }
                                r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                            }
                            r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                            if (r0_doubleA[0] <= ((double) j.ad)) {
                                return 1;
                            }
                            if (r0_doubleA[0] >= ((double) j.long)) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            this.a = 0;
                        }
                        this.a = 0;
                        return 3;
                    }
                }
            } else {
                this.a = 2;
                if (this.q <= ((double) j.am)) {
                    return 1;
                }
                if (this.q >= ((double) j.c)) {
                    return XListViewFooter.STATE_NODATA;
                }
                if (r15z || this.n) {
                    if (this.s) {
                        return 0;
                    }
                    if (r16z) {
                        this.a = 4;
                        r2_Iterator = this.t.iterator();
                        r1i = 0;
                        while (r2_Iterator.hasNext()) {
                            if (((a) r2_Iterator.next()).do() < j.for) {
                            }
                            r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                        }
                        if (r1i < j.int) {
                            return 1;
                        }
                        if (r1i > j.X) {
                            return XListViewFooter.STATE_NODATA;
                        }
                    }
                    if (r17z) {
                        this.a = 0;
                        return 3;
                    } else {
                        this.a = 5;
                        r3_List = new ArrayList();
                        r5_List = new ArrayList();
                        r6_List = new ArrayList();
                        r0i = 0;
                        while (r0i < 10) {
                            r3_List.add(new ArrayList());
                            r0i++;
                        }
                        r7_Iterator = this.t.iterator();
                        r2i = 0;
                        while (r7_Iterator.hasNext()) {
                            r0_a = (a) r7_Iterator.next();
                            if (r0_a.do() < 10 || r0_a.if() < 1) {
                                r0i = r2i;
                                r2i = r0i;
                            } else {
                                ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                r0i = r2i + 1;
                                r2i = r0i;
                            }
                        }
                        if (r2i < 4) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        r1i = 0;
                        while (r1i < r3_List.size()) {
                            if (((List) r3_List.get(r1i)).size() == 0) {
                                r1i++;
                            } else {
                                r0_Object = a((List) r3_List.get(r1i));
                                if (r0_Object == null) {
                                    r1i++;
                                } else {
                                    r5_List.add(r0_Object);
                                    r6_List.add(Integer.valueOf(r1i));
                                    r1i++;
                                }
                            }
                        }
                        if (r5_List == null || r5_List.size() <= 0) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        r0_doubleA = (double[]) r5_List.get(0);
                        r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                        r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                        if (r5_List.size() <= 1) {
                            r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                            if (r0_doubleA[0] <= ((double) j.ad)) {
                                return 1;
                            }
                            if (r0_doubleA[0] >= ((double) j.long)) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            this.a = 0;
                            return 3;
                        } else {
                            r3i = 1;
                            while (r3i < r5_List.size()) {
                                r1_doubleA = (double[]) r5_List.get(r3i);
                                r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                r3i++;
                            }
                            r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                            r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                            r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                            if (r0_doubleA[0] <= ((double) j.ad)) {
                                return 1;
                            }
                            if (r0_doubleA[0] >= ((double) j.long)) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            this.a = 0;
                            return 3;
                        }
                    }
                } else {
                    this.a = 3;
                    if (this.p > ((double) j.F)) {
                        return 1;
                    }
                    if (this.p < ((double) j.U)) {
                        return XListViewFooter.STATE_NODATA;
                    }
                    if (this.s) {
                        return 0;
                    }
                    if (r16z) {
                        if (r17z) {
                            this.a = 5;
                            r3_List = new ArrayList();
                            r5_List = new ArrayList();
                            r6_List = new ArrayList();
                            r0i = 0;
                            while (r0i < 10) {
                                r3_List.add(new ArrayList());
                                r0i++;
                            }
                            r7_Iterator = this.t.iterator();
                            r2i = 0;
                            while (r7_Iterator.hasNext()) {
                                r0_a = (a) r7_Iterator.next();
                                if (r0_a.do() < 10 || r0_a.if() < 1) {
                                    r0i = r2i;
                                    r2i = r0i;
                                } else {
                                    ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                    r0i = r2i + 1;
                                    r2i = r0i;
                                }
                            }
                            if (r2i < 4) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r1i = 0;
                            while (r1i < r3_List.size()) {
                                if (((List) r3_List.get(r1i)).size() == 0) {
                                    r0_Object = a((List) r3_List.get(r1i));
                                    if (r0_Object == null) {
                                        r5_List.add(r0_Object);
                                        r6_List.add(Integer.valueOf(r1i));
                                    }
                                }
                                r1i++;
                            }
                            if (r5_List == null || r5_List.size() <= 0) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r0_doubleA = (double[]) r5_List.get(0);
                            r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                            r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                            if (r5_List.size() <= 1) {
                                r3i = 1;
                                while (r3i < r5_List.size()) {
                                    r1_doubleA = (double[]) r5_List.get(r3i);
                                    r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                    r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                    r3i++;
                                }
                                r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                            }
                            r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                            if (r0_doubleA[0] <= ((double) j.ad)) {
                                return 1;
                            }
                            if (r0_doubleA[0] >= ((double) j.long)) {
                                return XListViewFooter.STATE_NODATA;
                            }
                        }
                        this.a = 0;
                        return 3;
                    } else {
                        this.a = 4;
                        r2_Iterator = this.t.iterator();
                        r1i = 0;
                        while (r2_Iterator.hasNext()) {
                            if (((a) r2_Iterator.next()).do() < j.for) {
                            }
                            r1i = ((a) r2_Iterator.next()).do() < j.for ? r1i : r1i + 1;
                        }
                        if (r1i < j.int) {
                            return 1;
                        }
                        if (r1i > j.X) {
                            return XListViewFooter.STATE_NODATA;
                        }
                        if (r17z) {
                            this.a = 0;
                            return 3;
                        } else {
                            this.a = 5;
                            r3_List = new ArrayList();
                            r5_List = new ArrayList();
                            r6_List = new ArrayList();
                            r0i = 0;
                            while (r0i < 10) {
                                r3_List.add(new ArrayList());
                                r0i++;
                            }
                            r7_Iterator = this.t.iterator();
                            r2i = 0;
                            while (r7_Iterator.hasNext()) {
                                r0_a = (a) r7_Iterator.next();
                                if (r0_a.do() < 10 || r0_a.if() < 1) {
                                    r0i = r2i;
                                    r2i = r0i;
                                } else {
                                    ((List) r3_List.get((r0_a.do() - 10) / 5)).add(r0_a);
                                    r0i = r2i + 1;
                                    r2i = r0i;
                                }
                            }
                            if (r2i < 4) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r1i = 0;
                            while (r1i < r3_List.size()) {
                                if (((List) r3_List.get(r1i)).size() == 0) {
                                    r1i++;
                                } else {
                                    r0_Object = a((List) r3_List.get(r1i));
                                    if (r0_Object == null) {
                                        r1i++;
                                    } else {
                                        r5_List.add(r0_Object);
                                        r6_List.add(Integer.valueOf(r1i));
                                        r1i++;
                                    }
                                }
                            }
                            if (r5_List == null || r5_List.size() <= 0) {
                                return XListViewFooter.STATE_NODATA;
                            }
                            r0_doubleA = (double[]) r5_List.get(0);
                            r0_doubleA[0] = r0_doubleA[0] * ((double) ((Integer) r6_List.get(0)).intValue());
                            r0_doubleA[1] = r0_doubleA[1] * ((double) ((Integer) r6_List.get(0)).intValue());
                            if (r5_List.size() <= 1) {
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            } else {
                                r3i = 1;
                                while (r3i < r5_List.size()) {
                                    r1_doubleA = (double[]) r5_List.get(r3i);
                                    r1_doubleA[0] = r1_doubleA[0] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r1_doubleA[1] = r1_doubleA[1] * ((double) ((Integer) r6_List.get(r3i)).intValue());
                                    r0_doubleA[0] = r0_doubleA[0] + r1_doubleA[0];
                                    r0_doubleA[1] = r0_doubleA[1] + r1_doubleA[1];
                                    r3i++;
                                }
                                r0_doubleA[0] = r0_doubleA[0] / ((double) r5_List.size());
                                r0_doubleA[1] = r0_doubleA[1] / ((double) r5_List.size());
                                r0_doubleA = b(r0_doubleA[0], r0_doubleA[1]);
                                if (r0_doubleA[0] <= ((double) j.ad)) {
                                    return 1;
                                }
                                if (r0_doubleA[0] >= ((double) j.long)) {
                                    return XListViewFooter.STATE_NODATA;
                                }
                                this.a = 0;
                                return 3;
                            }
                        }
                    }
                }
            }
        }

        private void a() {
            String[] r0_StringA;
            String r2_String;
            int r0i;
            int r1i;
            Iterator r9_Iterator;
            String r0_String;
            String[] r10_StringA;
            boolean r0z;
            int r8i;
            boolean r7z = true;
            if (a(this.d)) {
                r0_StringA = this.d.split(",");
                if (r0_StringA.length != 15) {
                    return;
                }
                if (r0_StringA[6].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[7].equals(RContactStorage.PRIMARY_KEY)) {
                } else {
                    this.g = Integer.valueOf(r0_StringA[6]).intValue();
                    this.h = Integer.valueOf(r0_StringA[7]).intValue();
                    this.e = true;
                }
                if (!a(this.m)) {
                    r2_String = this.m.substring(0, this.m.length() - 3);
                    r0i = 0;
                    r1i = 0;
                    while (r0i < r2_String.length()) {
                        if (r2_String.charAt(r0i) != ',') {
                            r1i++;
                        }
                        r0i++;
                    }
                    r0_StringA = r2_String.split(",", r1i + 1);
                    if (r0_StringA.length < 6) {
                        if (r0_StringA[2].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 3].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 2].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 1].equals(RContactStorage.PRIMARY_KEY)) {
                        } else {
                            this.o = Integer.valueOf(r0_StringA[2]).intValue();
                            this.p = Double.valueOf(r0_StringA[r0_StringA.length - 3]).doubleValue();
                            this.q = Double.valueOf(r0_StringA[r0_StringA.length - 2]).doubleValue();
                            this.n = true;
                        }
                        if (this.r == null || this.r.size() <= 0) {
                            this.s = false;
                        } else {
                            r9_Iterator = this.r.iterator();
                            while (r9_Iterator.hasNext()) {
                                r0_String = (String) r9_Iterator.next();
                                if (a(r0_String)) {
                                    this.s = false;
                                    break;
                                    break;
                                    break;
                                    break;
                                } else {
                                    r2_String = r0_String.substring(0, r0_String.length() - 3);
                                    r0i = 0;
                                    r1i = 0;
                                    while (r0i < r2_String.length()) {
                                        if (r2_String.charAt(r0i) != ',') {
                                            r1i++;
                                        }
                                        r0i++;
                                    }
                                    r10_StringA = r2_String.split(",", r1i + 1);
                                    if (Integer.valueOf(r10_StringA[1]).intValue() != this.r.size() || r10_StringA.length <= 8) {
                                        r0z = false;
                                        this.s = r0z;
                                    } else {
                                        r0z = true;
                                        this.s = r0z;
                                    }
                                    if (this.s) {
                                        break;
                                        break;
                                        break;
                                        break;
                                    } else {
                                        r8i = 4;
                                        while (r8i < r10_StringA.length) {
                                            if (r10_StringA[r8i].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 1].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 2].equals(RContactStorage.PRIMARY_KEY)) {
                                                r8i += 4;
                                            } else {
                                                this.u++;
                                                this.t.add(new a(Integer.valueOf(r10_StringA[r8i]).intValue(), Integer.valueOf(r10_StringA[r8i + 2]).intValue(), Integer.valueOf(r10_StringA[r8i + 1]).intValue(), r10_StringA[r8i + 3].equals(RContactStorage.PRIMARY_KEY) ? Integer.valueOf(r10_StringA[r8i + 3]).intValue() : 0));
                                                r8i += 4;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (this.e && this.n) {
                            this.c = r7z;
                        } else {
                            r7z = false;
                            this.c = r7z;
                        }
                    }
                }
                if (this.r == null || this.r.size() <= 0) {
                    this.s = false;
                } else {
                    r9_Iterator = this.r.iterator();
                    while (r9_Iterator.hasNext()) {
                        r0_String = (String) r9_Iterator.next();
                        if (a(r0_String)) {
                            this.s = false;
                            break;
                            break;
                            break;
                            break;
                        } else {
                            r2_String = r0_String.substring(0, r0_String.length() - 3);
                            r0i = 0;
                            r1i = 0;
                            while (r0i < r2_String.length()) {
                                if (r2_String.charAt(r0i) != ',') {
                                    r0i++;
                                } else {
                                    r1i++;
                                    r0i++;
                                }
                            }
                            r10_StringA = r2_String.split(",", r1i + 1);
                            if (Integer.valueOf(r10_StringA[1]).intValue() != this.r.size() || r10_StringA.length <= 8) {
                                r0z = false;
                                this.s = r0z;
                            } else {
                                r0z = true;
                                this.s = r0z;
                            }
                            if (this.s) {
                                r8i = 4;
                                while (r8i < r10_StringA.length) {
                                    if (r10_StringA[r8i].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 1].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 2].equals(RContactStorage.PRIMARY_KEY)) {
                                        r8i += 4;
                                    } else {
                                        this.u++;
                                        if (r10_StringA[r8i + 3].equals(RContactStorage.PRIMARY_KEY)) {
                                        }
                                        this.t.add(new a(Integer.valueOf(r10_StringA[r8i]).intValue(), Integer.valueOf(r10_StringA[r8i + 2]).intValue(), Integer.valueOf(r10_StringA[r8i + 1]).intValue(), r10_StringA[r8i + 3].equals(RContactStorage.PRIMARY_KEY) ? Integer.valueOf(r10_StringA[r8i + 3]).intValue() : 0));
                                        r8i += 4;
                                    }
                                }
                            } else {
                                break;
                                break;
                                break;
                                break;
                            }
                        }
                    }
                }
                if (this.e || this.n) {
                    r7z = false;
                    this.c = r7z;
                } else {
                    this.c = r7z;
                }
            }
            if (a(this.m)) {
                if (this.r == null || this.r.size() <= 0) {
                    this.s = false;
                } else {
                    r9_Iterator = this.r.iterator();
                    while (r9_Iterator.hasNext()) {
                        r0_String = (String) r9_Iterator.next();
                        if (a(r0_String)) {
                            r2_String = r0_String.substring(0, r0_String.length() - 3);
                            r0i = 0;
                            r1i = 0;
                            while (r0i < r2_String.length()) {
                                if (r2_String.charAt(r0i) != ',') {
                                    r1i++;
                                }
                                r0i++;
                            }
                            r10_StringA = r2_String.split(",", r1i + 1);
                            if (Integer.valueOf(r10_StringA[1]).intValue() != this.r.size() || r10_StringA.length <= 8) {
                                r0z = false;
                                this.s = r0z;
                            } else {
                                r0z = true;
                                this.s = r0z;
                            }
                            if (this.s) {
                                break;
                                break;
                                break;
                                break;
                            } else {
                                r8i = 4;
                                while (r8i < r10_StringA.length) {
                                    if (r10_StringA[r8i].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 1].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 2].equals(RContactStorage.PRIMARY_KEY)) {
                                        r8i += 4;
                                    } else {
                                        this.u++;
                                        if (r10_StringA[r8i + 3].equals(RContactStorage.PRIMARY_KEY)) {
                                        }
                                        this.t.add(new a(Integer.valueOf(r10_StringA[r8i]).intValue(), Integer.valueOf(r10_StringA[r8i + 2]).intValue(), Integer.valueOf(r10_StringA[r8i + 1]).intValue(), r10_StringA[r8i + 3].equals(RContactStorage.PRIMARY_KEY) ? Integer.valueOf(r10_StringA[r8i + 3]).intValue() : 0));
                                        r8i += 4;
                                    }
                                }
                            }
                        } else {
                            this.s = false;
                            break;
                            break;
                            break;
                            break;
                        }
                    }
                }
                if (this.e || this.n) {
                    r7z = false;
                    this.c = r7z;
                } else {
                    this.c = r7z;
                }
            } else {
                r2_String = this.m.substring(0, this.m.length() - 3);
                r0i = 0;
                r1i = 0;
                while (r0i < r2_String.length()) {
                    if (r2_String.charAt(r0i) != ',') {
                        r0i++;
                    } else {
                        r1i++;
                        r0i++;
                    }
                }
                r0_StringA = r2_String.split(",", r1i + 1);
                if (r0_StringA.length < 6) {
                } else {
                    if (r0_StringA[2].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 3].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 2].equals(RContactStorage.PRIMARY_KEY) || r0_StringA[r0_StringA.length - 1].equals(RContactStorage.PRIMARY_KEY)) {
                    } else {
                        this.o = Integer.valueOf(r0_StringA[2]).intValue();
                        this.p = Double.valueOf(r0_StringA[r0_StringA.length - 3]).doubleValue();
                        this.q = Double.valueOf(r0_StringA[r0_StringA.length - 2]).doubleValue();
                        this.n = true;
                    }
                    if (this.r == null || this.r.size() <= 0) {
                        this.s = false;
                    } else {
                        r9_Iterator = this.r.iterator();
                        while (r9_Iterator.hasNext()) {
                            r0_String = (String) r9_Iterator.next();
                            if (a(r0_String)) {
                                this.s = false;
                                break;
                                break;
                                break;
                                break;
                            } else {
                                r2_String = r0_String.substring(0, r0_String.length() - 3);
                                r0i = 0;
                                r1i = 0;
                                while (r0i < r2_String.length()) {
                                    if (r2_String.charAt(r0i) != ',') {
                                        r0i++;
                                    } else {
                                        r1i++;
                                        r0i++;
                                    }
                                }
                                r10_StringA = r2_String.split(",", r1i + 1);
                                if (Integer.valueOf(r10_StringA[1]).intValue() != this.r.size() || r10_StringA.length <= 8) {
                                    r0z = false;
                                    this.s = r0z;
                                } else {
                                    r0z = true;
                                    this.s = r0z;
                                }
                                if (this.s) {
                                    r8i = 4;
                                    while (r8i < r10_StringA.length) {
                                        if (r10_StringA[r8i].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 1].equals(RContactStorage.PRIMARY_KEY) || r10_StringA[r8i + 2].equals(RContactStorage.PRIMARY_KEY)) {
                                            r8i += 4;
                                        } else {
                                            this.u++;
                                            if (r10_StringA[r8i + 3].equals(RContactStorage.PRIMARY_KEY)) {
                                            }
                                            this.t.add(new a(Integer.valueOf(r10_StringA[r8i]).intValue(), Integer.valueOf(r10_StringA[r8i + 2]).intValue(), Integer.valueOf(r10_StringA[r8i + 1]).intValue(), r10_StringA[r8i + 3].equals(RContactStorage.PRIMARY_KEY) ? Integer.valueOf(r10_StringA[r8i + 3]).intValue() : 0));
                                            r8i += 4;
                                        }
                                    }
                                } else {
                                    break;
                                    break;
                                    break;
                                    break;
                                }
                            }
                        }
                    }
                    if (this.e || this.n) {
                        r7z = false;
                        this.c = r7z;
                    } else {
                        this.c = r7z;
                    }
                }
            }
        }

        private boolean a(String r6_String) {
            if (r6_String == null || r6_String.length() <= 8) {
                return false;
            }
            int r0i = 1;
            int r2i = 0;
            while (r0i < r6_String.length() - 3) {
                r2i ^= r6_String.charAt(r0i);
                r0i++;
            }
            return Integer.toHexString(r2i).equalsIgnoreCase(r6_String.substring(r6_String.length() + -2, r6_String.length()));
        }

        private double[] a(double r5d, double r7d) {
            double[] r0_doubleA = new double[2];
            r0_doubleA[0] = Math.sin(Math.toRadians(r7d)) * r5d;
            r0_doubleA[1] = Math.cos(Math.toRadians(r7d)) * r5d;
            return r0_doubleA;
        }

        private double[] a(List r10_List) {
            if (r10_List == null || r10_List.size() <= 0) {
                return null;
            }
            double[] r3_doubleA = a((double) (90 - ((a) r10_List.get(0)).if()), (double) ((a) r10_List.get(0)).a());
            if (r10_List.size() > 1) {
                int r1i = 1;
                while (r1i < r10_List.size()) {
                    double[] r0_doubleA = a((double) (90 - ((a) r10_List.get(r1i)).if()), (double) ((a) r10_List.get(r1i)).a());
                    r3_doubleA[0] = r3_doubleA[0] + r0_doubleA[0];
                    r3_doubleA[1] = r3_doubleA[1] + r0_doubleA[1];
                    r1i++;
                }
                r3_doubleA[0] = r3_doubleA[0] / ((double) r10_List.size());
                r3_doubleA[1] = r3_doubleA[1] / ((double) r10_List.size());
            }
            return r3_doubleA;
        }

        private int b() {
            return a(true, true, true, true, true);
        }

        private double[] b(double r9d, double r11d) {
            double r0d = 0.0d;
            if (r11d == 0.0d) {
                if (r9d > 0.0d) {
                    r0d = 90.0d;
                } else if (r9d < 0.0d) {
                    r0d = 270.0d;
                }
            } else {
                r0d = Math.toDegrees(Math.atan(r9d / r11d));
            }
            double[] r2_doubleA = new double[2];
            r2_doubleA[0] = Math.sqrt(r9d * r9d + r11d * r11d);
            r2_doubleA[1] = r0d;
            return r2_doubleA;
        }

        private boolean c_() {
            return this.c;
        }

        private double d() {
            return this.p;
        }

        private double e() {
            return this.q;
        }

        public String do() {
            return this.f;
        }

        public int else() {
            return this.g;
        }
    }

    public static class d {
        private String a;
        private boolean b;

        public d(String r3_String) {
            this.a = null;
            this.b = true;
            if (r3_String != null) {
                if (r3_String.length() > 100) {
                    r3_String = r3_String.substring(0, 100);
                }
            } else {
                r3_String = RContactStorage.PRIMARY_KEY;
            }
            this.a = r3_String;
        }

        private String a(int r10i) {
            long r5j = 0;
            if (!A.exists()) {
                return null;
            }
            try {
                RandomAccessFile r2_RandomAccessFile = new RandomAccessFile(A, "rw");
                r2_RandomAccessFile.seek(0);
                int r1i = r2_RandomAccessFile.readInt();
                if (b.b(r1i, r2_RandomAccessFile.readInt(), r2_RandomAccessFile.readInt())) {
                    if (r10i == 0 || r10i == r1i + 1) {
                        r2_RandomAccessFile.close();
                        return null;
                    } else {
                        long r3j = 12 + r5j + ((long) ((r10i - 1) * 1024));
                        r2_RandomAccessFile.seek(r3j);
                        int r5i = r2_RandomAccessFile.readInt();
                        byte[] r6_byteA = new byte[r5i];
                        r2_RandomAccessFile.seek(r3j + 4);
                        r1i = 0;
                        while (r1i < r5i) {
                            r6_byteA[r1i] = r2_RandomAccessFile.readByte();
                            r1i++;
                        }
                        r2_RandomAccessFile.close();
                        return new String(r6_byteA);
                    }
                } else {
                    r2_RandomAccessFile.close();
                    b.u();
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
        }

        private void a() {
            if (B == null || B.length() < 100) {
                b.v();
            } else {
                a(B.toString());
                b.v();
            }
        }

        private boolean a(Location r3_Location) {
            return a(r3_Location, j.V, j.aa);
        }

        private boolean a(Location r12_Location, int r13i, int r14i) {
            if (r12_Location == null || !(j.void) || !(this.b) || !(j.P)) {
                return false;
            }
            if (j.V < 5) {
                j.V = 5;
            } else if (j.V > 1000) {
                j.V = 1000;
            }
            if (j.aa < 5) {
                j.aa = 5;
            } else if (j.aa > 3600) {
                j.aa = 3600;
            }
            double r2d = r12_Location.getLongitude();
            double r0d = r12_Location.getLatitude();
            long r9j = r12_Location.getTime() / 1000;
            StringBuffer r4_StringBuffer;
            Object[] r6_ObjectA;
            if (C) {
                D = 1;
                B = new StringBuffer(RContactStorage.PRIMARY_KEY);
                r4_StringBuffer = B;
                r6_ObjectA = new Object[4];
                r6_ObjectA[0] = this.a;
                r6_ObjectA[1] = Long.valueOf(r9j);
                r6_ObjectA[2] = Double.valueOf(r2d);
                r6_ObjectA[3] = Double.valueOf(r0d);
                r4_StringBuffer.append(String.format("&nr=%s&traj=%d,%.5f,%.5f|", r6_ObjectA));
                E = B.length();
                F = r9j;
                I = r2d;
                J = r0d;
                G = (long) Math.floor(r2d * 100000.0d + 0.5d);
                H = (long) Math.floor(r0d * 100000.0d + 0.5d);
                C = false;
                return true;
            } else {
                float[] r8_floatA = new float[1];
                Location.distanceBetween(r0d, r2d, J, I, r8_floatA);
                long r4j = r9j - F;
                if (r8_floatA[0] < ((float) j.V) && r4j < ((long) j.aa)) {
                    return false;
                }
                if (B == null) {
                    b.g();
                    E = 0;
                    B = new StringBuffer(RContactStorage.PRIMARY_KEY);
                    r4_StringBuffer = B;
                    r6_ObjectA = new Object[4];
                    r6_ObjectA[0] = this.a;
                    r6_ObjectA[1] = Long.valueOf(r9j);
                    r6_ObjectA[2] = Double.valueOf(r2d);
                    r6_ObjectA[3] = Double.valueOf(r0d);
                    r4_StringBuffer.append(String.format("&nr=%s&traj=%d,%.5f,%.5f|", r6_ObjectA));
                    E = B.length();
                    F = r9j;
                    I = r2d;
                    J = r0d;
                    G = (long) Math.floor(r2d * 100000.0d + 0.5d);
                    H = (long) Math.floor(r0d * 100000.0d + 0.5d);
                } else {
                    I = r2d;
                    J = r0d;
                    long r2j = (long) Math.floor(r2d * 100000.0d + 0.5d);
                    long r0j = (long) Math.floor(r0d * 100000.0d + 0.5d);
                    K = (int) (r9j - F);
                    L = (int) (r2j - G);
                    M = (int) (r0j - H);
                    r4_StringBuffer = B;
                    r6_ObjectA = new Object[3];
                    r6_ObjectA[0] = Integer.valueOf(K);
                    r6_ObjectA[1] = Integer.valueOf(L);
                    r6_ObjectA[2] = Integer.valueOf(M);
                    r4_StringBuffer.append(String.format("%d,%d,%d|", r6_ObjectA));
                    E = B.length();
                    F = r9j;
                    G = r2j;
                    H = r0j;
                }
                if (E + 15 > 750) {
                    a(B.toString());
                    B = null;
                }
                if (D >= j.L) {
                    this.b = false;
                }
                return true;
            }
        }

        private boolean a(String r12_String) {
            if (r12_String == null || (!r12_String.startsWith("&nr"))) {
                return false;
            }
            if (!A.exists() && !b.u()) {
                return false;
            }
            try {
                RandomAccessFile r4_RandomAccessFile = new RandomAccessFile(A, "rw");
                r4_RandomAccessFile.seek(0);
                int r3i = r4_RandomAccessFile.readInt();
                int r5i = r4_RandomAccessFile.readInt();
                int r6i = r4_RandomAccessFile.readInt();
                if (b.b(r3i, r5i, r6i)) {
                    if (j.try) {
                        if (r3i != j.L) {
                            if (r6i <= 1 || (!r12_String.equals(a(r6i - 1)))) {
                                r4_RandomAccessFile.seek(((long) ((r6i - 1) * 1024 + 12)) + 0);
                            } else {
                                r4_RandomAccessFile.close();
                                return false;
                            }
                        } else {
                            if (r12_String.equals(a(r6i == 1 ? j.L : r6i - 1))) {
                                r4_RandomAccessFile.close();
                                return false;
                            }
                        }
                    }
                    r4_RandomAccessFile.seek(((long) ((r6i - 1) * 1024 + 12)) + 0);
                    if (r12_String.length() > 750) {
                        r4_RandomAccessFile.close();
                        return false;
                    } else {
                        String r2_String = Jni.if(r12_String);
                        int r7i = r2_String.length();
                        if (r7i > 1020) {
                            r4_RandomAccessFile.close();
                            return false;
                        } else {
                            r4_RandomAccessFile.writeInt(r7i);
                            r4_RandomAccessFile.writeBytes(r2_String);
                            if (r3i == 0) {
                                r4_RandomAccessFile.seek(0);
                                r4_RandomAccessFile.writeInt(1);
                                r4_RandomAccessFile.writeInt(1);
                                r4_RandomAccessFile.writeInt(XListViewHeader.STATE_REFRESHING);
                            } else if (r3i < j.L - 1) {
                                r4_RandomAccessFile.seek(0);
                                r4_RandomAccessFile.writeInt(r3i + 1);
                                r4_RandomAccessFile.seek(8);
                                r4_RandomAccessFile.writeInt(r3i + 2);
                            } else if (r3i == j.L - 1) {
                                r4_RandomAccessFile.seek(0);
                                r4_RandomAccessFile.writeInt(j.L);
                                if (r5i == 0 || r5i == 1) {
                                    r4_RandomAccessFile.writeInt(XListViewHeader.STATE_REFRESHING);
                                    r4_RandomAccessFile.seek(8);
                                    r4_RandomAccessFile.writeInt(1);
                                } else {
                                    r4_RandomAccessFile.seek(8);
                                    r4_RandomAccessFile.writeInt(1);
                                }
                            } else if (r6i == r5i) {
                                r3i = r6i == j.L ? 1 : r6i + 1;
                                r2i = r3i == j.L ? 1 : r3i + 1;
                                r4_RandomAccessFile.seek(4);
                                r4_RandomAccessFile.writeInt(r2i);
                                r4_RandomAccessFile.writeInt(r3i);
                            } else {
                                r3i = r6i == j.L ? 1 : r6i + 1;
                                if (r3i == r5i) {
                                    r2i = r3i == j.L ? 1 : r3i + 1;
                                    r4_RandomAccessFile.seek(4);
                                    r4_RandomAccessFile.writeInt(r2i);
                                }
                                r4_RandomAccessFile.seek(8);
                                r4_RandomAccessFile.writeInt(r3i);
                            }
                            r4_RandomAccessFile.close();
                            return true;
                        }
                    }
                } else {
                    r4_RandomAccessFile.close();
                    b.u();
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
    }

    static {
        h = 0;
        x = null;
        z = "Temp_in.dat";
        A = new File(f.a, z);
        B = null;
        C = true;
        D = 0;
        E = 0;
        F = 0;
        G = 0;
        H = 0;
        I = 0.0d;
        J = 0.0d;
        K = 0;
        L = 0;
        M = 0;
    }

    public b(Context r7_Context, Handler r8_Handler) {
        this.a = 1000;
        this.c = null;
        this.e = null;
        this.g = null;
        this.i = 0;
        this.j = 0;
        this.k = false;
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = false;
        this.p = 0;
        this.q = 400;
        this.r = true;
        this.s = false;
        this.t = new ArrayList();
        this.u = null;
        this.v = null;
        this.w = null;
        this.y = null;
        this.b = r7_Context;
        this.l = r8_Handler;
        try {
            if (j.do != null) {
                this.y = new d(j.do);
            } else {
                this.y = new d("NULL");
            }
        } catch (Exception e) {
            this.y = null;
        }
    }

    private void a(double r10d, double r12d, float r14f) {
        double r5d = 1000.0d;
        int r0i = 0;
        j.if(f.v, "check...gps ...");
        if (j.z) {
            if (r10d < 73.146973d || r10d > 135.252686d || r12d > 54.258807d || r12d < 14.604847d || r14f > 18.0f) {
                if (j.I == r0i) {
                    j.I = r0i;
                    try {
                        if (j.I != 3) {
                            this.c.removeUpdates(this.e);
                            this.c.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, Util.MILLSECONDS_OF_SECOND, 1.0f, this.e);
                        } else {
                            this.c.removeUpdates(this.e);
                            this.c.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, Util.MILLSECONDS_OF_SECOND, 5.0f, this.e);
                        }
                    } catch (Exception e) {
                    }
                }
            } else {
                j.if(f.v, "check...gps2 ...");
                int r1i = (int) ((r10d - j.if) * r5d);
                int r2i = (int) ((j.o - r12d) * r5d);
                j.if(f.v, "check...gps ..." + r1i + r2i);
                if (r1i <= 0 || r1i >= 50 || r2i <= 0 || r2i >= 50) {
                    Object[] r2_ObjectA = new Object[2];
                    r2_ObjectA[0] = Double.valueOf(r10d);
                    r2_ObjectA[1] = Double.valueOf(r12d);
                    String r1_String = String.format("&ll=%.5f|%.5f", r2_ObjectA) + "&im=" + j.do;
                    j.J = r10d;
                    j.Z = r12d;
                    g.a(r1_String, true);
                } else {
                    j.if(f.v, "check...gps ..." + r1i + r2i);
                    r1i += r2i * 50;
                    r2i = r1i >> 2;
                    r1i &= 3;
                    if (j.ag) {
                        r0i = (j.j[r2i] >> (r1i * 2)) & 3;
                        j.if(f.v, "check gps scacity..." + r0i);
                    }
                }
                if (j.I == r0i) {
                } else {
                    j.I = r0i;
                    if (j.I != 3) {
                        this.c.removeUpdates(this.e);
                        this.c.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, Util.MILLSECONDS_OF_SECOND, 5.0f, this.e);
                    } else {
                        this.c.removeUpdates(this.e);
                        this.c.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, Util.MILLSECONDS_OF_SECOND, 1.0f, this.e);
                    }
                }
            }
        }
    }

    private void a(Location r9_Location) {
        j.if(f.v, "set new gpsLocation ...");
        this.d = r9_Location;
        if (this.d == null) {
            this.n = null;
        } else {
            long r0j = System.currentTimeMillis();
            this.d.setTime(r0j);
            float r5f = (float) (((double) this.d.getSpeed()) * 3.6d);
            Object[] r3_ObjectA = new Object[6];
            r3_ObjectA[0] = Double.valueOf(this.d.getLongitude());
            r3_ObjectA[1] = Double.valueOf(this.d.getLatitude());
            r3_ObjectA[2] = Float.valueOf(r5f);
            r3_ObjectA[3] = Float.valueOf(this.d.getBearing());
            r3_ObjectA[4] = Integer.valueOf(h);
            r3_ObjectA[5] = Long.valueOf(r0j);
            this.n = String.format("&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", r3_ObjectA);
            a(this.d.getLongitude(), this.d.getLatitude(), r5f);
        }
        if (this.y != null) {
            try {
                this.y.a(this.d);
            } catch (Exception e) {
            }
        }
        this.l.obtainMessage(AdViewUtil.NETWORK_TYPE_DoubleClick).sendToTarget();
    }

    public static boolean a(Location r6_Location, Location r7_Location, boolean r8z) {
        if (r6_Location == r7_Location) {
            return false;
        }
        if (r6_Location == null || r7_Location == null) {
            return true;
        }
        float r2f = r7_Location.getSpeed();
        if (r8z && j.I == 3 && r2f < 5.0f) {
            return true;
        }
        float r3f = r7_Location.distanceTo(r6_Location);
        if (r2f > j.C) {
            return (r3f > j.Q ? 1 : (r3f == j.Q? 0 : -1)) > 0;
        } else if (r2f > j.D) {
            return (r3f > j.ai ? 1 : (r3f == j.ai? 0 : -1)) > 0;
        } else {
            if (r3f <= 5.0f) {
                return false;
            }
            return true;
        }
    }

    private void b_(boolean r3z) {
        this.o = r3z;
        if (((!r3z) || new()) && j.ab != r3z) {
            j.ab = r3z;
            if (j.H) {
                this.l.obtainMessage(AdViewUtil.NETWORK_TYPE_YUNYUN).sendToTarget();
            }
        }
    }

    private static boolean b_(int r3i, int r4i, int r5i) {
        if (r3i < 0 || r3i > j.L) {
            return false;
        }
        if (r4i < 0 || r4i > r3i + 1) {
            return false;
        }
        if (r5i < 1 || r5i > r3i + 1 || r5i > j.L) {
            return false;
        }
        return true;
    }

    public static String do(Location r2_Location) {
        String r0_String = for(r2_Location);
        return r0_String != null ? r0_String + "&g_tp=0" : r0_String;
    }

    public static String for(Location r9_Location) {
        if (r9_Location == null) {
            return null;
        }
        double r0d;
        float r2f = (float) (((double) r9_Location.getSpeed()) * 3.6d);
        int r3i = (int) (r9_Location.hasAccuracy() ? r9_Location.getAccuracy() : -1.0f);
        r0d = r9_Location.hasAltitude() ? r9_Location.getAltitude() : 555.0d;
        Object[] r5_ObjectA = new Object[8];
        r5_ObjectA[0] = Double.valueOf(r9_Location.getLongitude());
        r5_ObjectA[1] = Double.valueOf(r9_Location.getLatitude());
        r5_ObjectA[2] = Float.valueOf(r2f);
        r5_ObjectA[3] = Float.valueOf(r9_Location.getBearing());
        r5_ObjectA[4] = Integer.valueOf(r3i);
        r5_ObjectA[5] = Integer.valueOf(h);
        r5_ObjectA[6] = Double.valueOf(r0d);
        r5_ObjectA[7] = Long.valueOf(r9_Location.getTime() / 1000);
        return String.format("&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d", r5_ObjectA);
    }

    static /* synthetic */ int g() {
        int r0i = D + 1;
        D = r0i;
        return r0i;
    }

    public static String if(Location r2_Location) {
        String r0_String = for(r2_Location);
        return r0_String != null ? r0_String + x : r0_String;
    }

    public static String j() {
        j.if(f.v, "GPS readline...");
        if (A == null) {
            return null;
        }
        if (!A.exists()) {
            return null;
        }
        try {
            RandomAccessFile r3_RandomAccessFile = new RandomAccessFile(A, "rw");
            long r4j = 0;
            r3_RandomAccessFile.seek(0);
            int r6i = r3_RandomAccessFile.readInt();
            int r7i = r3_RandomAccessFile.readInt();
            int r0i = r3_RandomAccessFile.readInt();
            if (b(r6i, r7i, r0i)) {
                j.if(f.v, "GPS readline1...");
                if (r7i == 0 || r7i == r0i) {
                    r3_RandomAccessFile.close();
                    return null;
                } else {
                    j.if(f.v, "GPS readline2...");
                    r4j += (long) ((r7i - 1) * 1024 + 12);
                    r3_RandomAccessFile.seek(r4j);
                    int r2i = r3_RandomAccessFile.readInt();
                    byte[] r8_byteA = new byte[r2i];
                    r3_RandomAccessFile.seek(r4j + 4);
                    r0i = 0;
                    while (r0i < r2i) {
                        r8_byteA[r0i] = r3_RandomAccessFile.readByte();
                        r0i++;
                    }
                    String r2_String = new String(r8_byteA);
                    if (r6i < j.L) {
                        r0i = r7i + 1;
                    } else if (r7i == j.L) {
                        r0i = 1;
                    } else {
                        r0i = r7i + 1;
                    }
                    r3_RandomAccessFile.seek(4);
                    r3_RandomAccessFile.writeInt(r0i);
                    r3_RandomAccessFile.close();
                    return r2_String;
                }
            } else {
                r3_RandomAccessFile.close();
                u();
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static boolean u() {
        if (A.exists()) {
            A.delete();
        }
        if (!A.getParentFile().exists()) {
            A.getParentFile().mkdirs();
        }
        try {
            A.createNewFile();
            RandomAccessFile r1_RandomAccessFile = new RandomAccessFile(A, "rw");
            r1_RandomAccessFile.seek(0);
            r1_RandomAccessFile.writeInt(0);
            r1_RandomAccessFile.writeInt(0);
            r1_RandomAccessFile.writeInt(1);
            r1_RandomAccessFile.close();
            v();
            return A.exists();
        } catch (IOException e) {
            return false;
        }
    }

    private static void v() {
        C = true;
        B = null;
        D = 0;
        E = 0;
        F = 0;
        G = 0;
        H = 0;
        I = 0.0d;
        J = 0.0d;
        K = 0;
        L = 0;
        M = 0;
    }

    public boolean case() {
        return this.d != null && this.d.getLatitude() != 0.0d && this.d.getLongitude() != 0.0d;
    }

    public String char() {
        return this.n;
    }

    public void goto() {
    }

    public String int() {
        if (this.d != null) {
            String r1_String = "{\"result\":{\"time\":\"" + j.a() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\"," + "\"s\":\"%f\",\"n\":\"%d\"}}";
            int r0i = (int) (this.d.hasAccuracy() ? this.d.getAccuracy() : 10.0f);
            float r2f = (float) (((double) this.d.getSpeed()) * 3.6d);
            double[] r3_doubleA = Jni.if(this.d.getLongitude(), this.d.getLatitude(), "gps2gcj");
            Object[] r4_ObjectA;
            String r0_String;
            if (r3_doubleA[0] > 0.0d || r3_doubleA[1] > 0.0d) {
                r4_ObjectA = new Object[6];
                r4_ObjectA[0] = Double.valueOf(r3_doubleA[0]);
                r4_ObjectA[1] = Double.valueOf(r3_doubleA[1]);
                r4_ObjectA[2] = Integer.valueOf(r0i);
                r4_ObjectA[3] = Float.valueOf(this.d.getBearing());
                r4_ObjectA[4] = Float.valueOf(r2f);
                r4_ObjectA[5] = Integer.valueOf(h);
                r0_String = String.format(r1_String, r4_ObjectA);
                j.if(f.v, "wgs84: " + this.d.getLongitude() + " " + this.d.getLatitude() + " gcj02: " + r3_doubleA[0] + " " + r3_doubleA[1]);
                return r0_String;
            } else {
                r3_doubleA[0] = this.d.getLongitude();
                r3_doubleA[1] = this.d.getLatitude();
                r4_ObjectA = new Object[6];
                r4_ObjectA[0] = Double.valueOf(r3_doubleA[0]);
                r4_ObjectA[1] = Double.valueOf(r3_doubleA[1]);
                r4_ObjectA[2] = Integer.valueOf(r0i);
                r4_ObjectA[3] = Float.valueOf(this.d.getBearing());
                r4_ObjectA[4] = Float.valueOf(r2f);
                r4_ObjectA[5] = Integer.valueOf(h);
                r0_String = String.format(r1_String, r4_ObjectA);
                j.if(f.v, "wgs84: " + this.d.getLongitude() + " " + this.d.getLatitude() + " gcj02: " + r3_doubleA[0] + " " + r3_doubleA[1]);
                return r0_String;
            }
        } else {
            j.if(f.v, "gps man getGpsJson but gpslocation is null");
            return null;
        }
    }

    public void k() {
        if (this.m) {
        } else {
            try {
                this.c = (LocationManager) this.b.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
                this.e = new b(null);
                this.g = new a(null);
                this.c.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, Util.MILLSECONDS_OF_SECOND, 5.0f, this.e);
                this.c.addGpsStatusListener(this.g);
                this.c.addNmeaListener(this.g);
                this.m = true;
            } catch (Exception e) {
            }
        }
    }

    public void l() {
        if (this.m) {
            if (this.c != null) {
                try {
                    if (this.e != null) {
                        this.c.removeUpdates(this.e);
                    }
                    if (this.g != null) {
                        this.c.removeGpsStatusListener(this.g);
                        this.c.removeNmeaListener(this.g);
                    }
                    if (this.y != null) {
                        this.y.a();
                    }
                } catch (Exception e) {
                }
            }
            this.e = null;
            this.g = null;
            this.c = null;
            this.m = false;
            b(false);
        }
    }

    public boolean new() {
        if (!case()) {
            return false;
        }
        long r2j = System.currentTimeMillis();
        if (this.k && r2j - this.j > 3000) {
            return false;
        }
        if (h >= 3) {
            return true;
        }
        if (r2j - this.i < 3000) {
            return true;
        }
        return false;
    }

    public Location try() {
        return this.d;
    }
}