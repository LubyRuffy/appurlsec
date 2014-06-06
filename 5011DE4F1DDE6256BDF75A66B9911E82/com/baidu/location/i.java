package com.baidu.location;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.activity.base.MysBaseActivity;

public class i {
    public static final String new = "android.com.baidu.location.TIMER.NOTIFY";
    private String a;
    private ArrayList b;
    private float c;
    private BDLocation d;
    private long e;
    private LocationClient f;
    private Context g;
    private int h;
    private long i;
    private boolean j;
    private boolean k;
    private PendingIntent l;
    private AlarmManager m;
    private b n;
    private a o;
    private boolean p;

    public class a implements BDLocationListener {
        public void onReceiveLocation(BDLocation r2_BDLocation) {
            i.this.a(r2_BDLocation);
        }

        public void onReceivePoi(BDLocation r1_BDLocation) {
        }
    }

    public class b extends BroadcastReceiver {
        public void onReceive(Context r3_Context, Intent r4_Intent) {
            j.if(i.this, "timer expire,request location...");
            if (i.this.b == null || i.this.b.isEmpty()) {
            } else {
                i.this.f.requestNotifyLocation();
            }
        }
    }

    public i(Context r6_Context, LocationClient r7_LocationClient) {
        this.a = f.v;
        this.b = null;
        this.c = 3.4028235E38f;
        this.d = null;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.j = false;
        this.k = false;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = new a();
        this.p = false;
        this.g = r6_Context;
        this.f = r7_LocationClient;
        this.f.registerNotifyLocationListener(this.o);
        this.m = (AlarmManager) this.g.getSystemService("alarm");
        this.n = new b();
        this.p = false;
    }

    private void a(long r6j) {
        if (this.j) {
            this.m.cancel(this.l);
        }
        this.l = PendingIntent.getBroadcast(this.g, 0, new Intent(new), 134217728);
        this.m.set(0, System.currentTimeMillis() + r6j, this.l);
        j.if(this.a, "timer start:" + r6j);
    }

    private void a(BDLocation r13_BDLocation) {
        j.if(this.a, "notify new loation");
        this.j = false;
        if (r13_BDLocation.getLocType() == BDLocation.TypeGpsLocation || r13_BDLocation.getLocType() == 161 || r13_BDLocation.getLocType() == 65) {
            if (System.currentTimeMillis() - this.e < 5000 || this.b == null) {
            } else {
                this.d = r13_BDLocation;
                this.e = System.currentTimeMillis();
                float[] r8_floatA = new float[1];
                Iterator r11_Iterator = this.b.iterator();
                float r10f = 3.4028235E38f;
                while (r11_Iterator.hasNext()) {
                    BDNotifyListener r9_BDNotifyListener = (BDNotifyListener) r11_Iterator.next();
                    Location.distanceBetween(r13_BDLocation.getLatitude(), r13_BDLocation.getLongitude(), r9_BDNotifyListener.mLatitudeC, r9_BDNotifyListener.mLongitudeC, r8_floatA);
                    float r0f = r8_floatA[0] - r9_BDNotifyListener.mRadius - r13_BDLocation.getRadius();
                    j.if(this.a, "distance:" + r0f);
                    if (r0f > 0.0f) {
                        if (r0f < r10f) {
                            r10f = r0f;
                        }
                        r0f = r10f;
                    } else {
                        if (r9_BDNotifyListener.Notified < 3) {
                            r9_BDNotifyListener.Notified++;
                            r9_BDNotifyListener.onNotify(r13_BDLocation, r8_floatA[0]);
                            if (r9_BDNotifyListener.Notified < 3) {
                                this.k = true;
                            }
                        }
                        r0f = r10f;
                    }
                    r10f = r0f;
                }
                if (r10f < this.c) {
                    this.c = r10f;
                }
                this.h = 0;
                b();
            }
        } else {
            a(120000);
        }
    }

    private boolean a() {
        if (this.b == null || this.b.isEmpty()) {
            return false;
        }
        Iterator r2_Iterator = this.b.iterator();
        boolean r1z = false;
        while (r2_Iterator.hasNext()) {
            r1z = ((BDNotifyListener) r2_Iterator.next()).Notified < 3 ? true : r1z;
        }
        return r1z;
    }

    private void b() {
        int r1i = 10000;
        if (a()) {
            int r0i;
            if (this.c > 5000.0f) {
                r0i = 600000;
            } else if (this.c > 1000.0f) {
                r0i = 120000;
            } else if (this.c > 500.0f) {
                r0i = MysBaseActivity.DEFAULT_REFRESH_INTERVAL;
            } else {
                r0i = 10000;
            }
            if (this.k) {
                this.k = false;
            } else {
                r1i = r0i;
            }
            if (this.h == 0 || ((long) r1i) <= this.i + ((long) this.h) - System.currentTimeMillis()) {
                r0i = 1;
                if (r0i == 0) {
                    this.h = r1i;
                    this.i = System.currentTimeMillis();
                    a((long) this.h);
                }
            } else {
                r0i = 0;
                if (r0i == 0) {
                }
            }
            this.h = r1i;
            this.i = System.currentTimeMillis();
            a((long) this.h);
        }
    }

    public void a(BDNotifyListener r13_BDNotifyListener) {
        j.if(this.a, r13_BDNotifyListener.mCoorType + "2gcj");
        if (r13_BDNotifyListener.mCoorType == null) {
        } else {
            if (!r13_BDNotifyListener.mCoorType.equals("gcj02")) {
                double[] r0_doubleA = Jni.if(r13_BDNotifyListener.mLongitude, r13_BDNotifyListener.mLatitude, r13_BDNotifyListener.mCoorType + "2gcj");
                r13_BDNotifyListener.mLongitudeC = r0_doubleA[0];
                r13_BDNotifyListener.mLatitudeC = r0_doubleA[1];
                j.if(this.a, r13_BDNotifyListener.mCoorType + "2gcj");
                j.if(this.a, "coor:" + r13_BDNotifyListener.mLongitude + "," + r13_BDNotifyListener.mLatitude + ":" + r13_BDNotifyListener.mLongitudeC + "," + r13_BDNotifyListener.mLatitudeC);
            }
            if (this.d == null || System.currentTimeMillis() - this.e > 300000) {
                this.f.requestNotifyLocation();
                b();
            } else {
                float[] r8_floatA = new float[1];
                Location.distanceBetween(this.d.getLatitude(), this.d.getLongitude(), r13_BDNotifyListener.mLatitudeC, r13_BDNotifyListener.mLongitudeC, r8_floatA);
                float r0f = r8_floatA[0] - r13_BDNotifyListener.mRadius - this.d.getRadius();
                if (r0f > 0.0f) {
                    if (r0f < this.c) {
                        this.c = r0f;
                    }
                    b();
                } else {
                    if (r13_BDNotifyListener.Notified < 3) {
                        r13_BDNotifyListener.Notified++;
                        r13_BDNotifyListener.onNotify(this.d, r8_floatA[0]);
                        if (r13_BDNotifyListener.Notified < 3) {
                            this.k = true;
                        }
                    }
                    b();
                }
            }
        }
    }

    public int do(BDNotifyListener r3_BDNotifyListener) {
        if (this.b == null) {
            return 0;
        }
        if (this.b.contains(r3_BDNotifyListener)) {
            this.b.remove(r3_BDNotifyListener);
        }
        if (this.b.size() != 0 || !(this.j)) {
            return 1;
        }
        this.m.cancel(this.l);
        return 1;
    }

    public int if(BDNotifyListener r13_BDNotifyListener) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(r13_BDNotifyListener);
        r13_BDNotifyListener.isAdded = true;
        r13_BDNotifyListener.mNotifyCache = this;
        if (!this.p) {
            this.g.registerReceiver(this.n, new IntentFilter(new));
            this.p = true;
        }
        if (r13_BDNotifyListener.mCoorType == null) {
            return 1;
        }
        if (!r13_BDNotifyListener.mCoorType.equals("gcj02")) {
            double[] r0_doubleA = Jni.if(r13_BDNotifyListener.mLongitude, r13_BDNotifyListener.mLatitude, r13_BDNotifyListener.mCoorType + "2gcj");
            r13_BDNotifyListener.mLongitudeC = r0_doubleA[0];
            r13_BDNotifyListener.mLatitudeC = r0_doubleA[1];
            j.if(this.a, r13_BDNotifyListener.mCoorType + "2gcj");
            j.if(this.a, "coor:" + r13_BDNotifyListener.mLongitude + "," + r13_BDNotifyListener.mLatitude + ":" + r13_BDNotifyListener.mLongitudeC + "," + r13_BDNotifyListener.mLatitudeC);
        }
        if (this.d == null || System.currentTimeMillis() - this.e > 30000) {
            this.f.requestNotifyLocation();
        } else {
            float[] r8_floatA = new float[1];
            Location.distanceBetween(this.d.getLatitude(), this.d.getLongitude(), r13_BDNotifyListener.mLatitudeC, r13_BDNotifyListener.mLongitudeC, r8_floatA);
            float r0f = r8_floatA[0] - r13_BDNotifyListener.mRadius - this.d.getRadius();
            if (r0f > 0.0f) {
                if (r0f < this.c) {
                    this.c = r0f;
                }
            } else if (r13_BDNotifyListener.Notified < 3) {
                r13_BDNotifyListener.Notified++;
                r13_BDNotifyListener.onNotify(this.d, r8_floatA[0]);
                if (r13_BDNotifyListener.Notified < 3) {
                    this.k = true;
                }
            }
        }
        b();
        return 1;
    }

    public void if() {
        if (this.j) {
            this.m.cancel(this.l);
        }
        this.d = null;
        this.e = 0;
        if (this.p) {
            j.if(this.a, "unregister...");
            this.g.unregisterReceiver(this.n);
        }
        this.p = false;
    }
}