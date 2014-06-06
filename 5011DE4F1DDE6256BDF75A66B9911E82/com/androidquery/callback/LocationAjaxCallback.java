package com.androidquery.callback;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.amap.api.location.LocationManagerProxy;
import com.androidquery.util.AQUtility;
import com.qiubai.library.adview.util.AdViewUtil;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.share.ShareUtils;

public class LocationAjaxCallback extends AbstractAjaxCallback<Location, LocationAjaxCallback> {
    private LocationManager i;
    private long j;
    private long k;
    private float l;
    private float m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private a r;
    private a s;
    private long t;

    private class a extends TimerTask implements LocationListener {
        private a() {
        }

        public void onLocationChanged(Location r2_Location) {
            AQUtility.debug("changed", r2_Location);
            LocationAjaxCallback.this.a(r2_Location);
        }

        public void onProviderDisabled(String r2_String) {
            AQUtility.debug((Object)"onProviderDisabled");
        }

        public void onProviderEnabled(String r3_String) {
            AQUtility.debug((Object)"onProviderEnabled");
            LocationAjaxCallback.this.a(LocationAjaxCallback.this.g());
            LocationAjaxCallback.this.i.removeUpdates(this);
        }

        public void onStatusChanged(String r2_String, int r3i, Bundle r4_Bundle) {
            AQUtility.debug((Object)"onStatusChanged");
        }

        public void run() {
            LocationAjaxCallback.this.e();
        }
    }

    public LocationAjaxCallback() {
        this.j = 30000;
        this.k = 1000;
        this.l = 10.0f;
        this.m = 1000.0f;
        this.n = 3;
        this.o = 0;
        this.p = false;
        this.q = false;
        ((LocationAjaxCallback) type(Location.class)).url("device");
    }

    private static float a(double r10d, double r12d, double r14d, double r16d) {
        double r2d = Math.toRadians(r14d - r10d);
        double r4d = Math.toRadians(r16d - r12d);
        r2d = Math.sin(r2d / 2.0d) * Math.sin(r2d / 2.0d) + Math.sin(r4d / 2.0d) * ((Math.cos(Math.toRadians(r10d)) * Math.cos(Math.toRadians(r14d))) * Math.sin(r4d / 2.0d));
        return ((float) (3958.75d * (Math.atan2(Math.sqrt(r2d), Math.sqrt(1.0d - r2d)) * 2.0d))) * ((float) 1609);
    }

    private void a(Location r8_Location) {
        boolean r2z = false;
        if (r8_Location == null || (!e(r8_Location))) {
        } else {
            int r0i;
            this.o++;
            r0i = this.o >= this.n ? 1 : 0;
            boolean r3z = c(r8_Location);
            boolean r4z = d(r8_Location);
            if ((!this.q) || LocationManagerProxy.GPS_PROVIDER.equals(r8_Location.getProvider())) {
                r2z = true;
                AQUtility.debug(Integer.valueOf(this.o), Integer.valueOf(this.n));
                AQUtility.debug("acc", Boolean.valueOf(r3z));
                AQUtility.debug("best", Boolean.valueOf(r2z));
                if (!r4z) {
                    if (r0i == 0) {
                        if (r3z && r2z) {
                            stop();
                            b(r8_Location);
                        }
                    } else if (r3z && r2z) {
                        stop();
                        b(r8_Location);
                    } else {
                        b(r8_Location);
                    }
                }
            } else {
                AQUtility.debug(Integer.valueOf(this.o), Integer.valueOf(this.n));
                AQUtility.debug("acc", Boolean.valueOf(r3z));
                AQUtility.debug("best", Boolean.valueOf(r2z));
                if (r4z) {
                } else if (r0i == 0) {
                    if (r3z || r2z) {
                        b(r8_Location);
                    } else {
                        stop();
                        b(r8_Location);
                    }
                } else if (r3z || r2z) {
                } else {
                    stop();
                    b(r8_Location);
                }
            }
        }
    }

    private void a(Location r5_Location, int r6i) {
        if (this.f == null) {
            this.f = new AjaxStatus();
        }
        if (r5_Location != null) {
            this.f.a(new Date(r5_Location.getTime()));
        }
        this.f.code(r6i).done().a((int)ShareUtils.SHARE_SMS);
    }

    private void b(Location r2_Location) {
        this.d = r2_Location;
        a(r2_Location, 200);
        a();
    }

    private boolean c(Location r3_Location) {
        return (r3_Location.getAccuracy() > this.m ? 1 : (r3_Location.getAccuracy() == this.m? 0 : -1)) < 0;
    }

    private boolean d(Location r10_Location) {
        if (this.d == null) {
            return true;
        }
        if (a(((Location) this.d).getLatitude(), ((Location) this.d).getLongitude(), r10_Location.getLatitude(), r10_Location.getLongitude()) >= this.l) {
            return true;
        }
        AQUtility.debug((Object)"duplicate location");
        return false;
    }

    private void e() {
        if (!(this.s == null && this.r == null)) {
            AQUtility.debug(AdViewUtil.COUNTFAIL);
            this.d = null;
            a(null, (int)AjaxStatus.TRANSFORM_ERROR);
            stop();
            a();
        }
    }

    private boolean e(Location r7_Location) {
        if (this.d == null) {
            return true;
        }
        if (((Location) this.d).getTime() <= this.t || !((Location) this.d).getProvider().equals(LocationManagerProxy.GPS_PROVIDER) || !r7_Location.getProvider().equals(LocationManagerProxy.NETWORK_PROVIDER)) {
            return true;
        }
        AQUtility.debug((Object)"inferior location");
        return false;
    }

    private void f() {
        Location r7_Location = g();
        Timer r8_Timer = new Timer(false);
        if (this.p) {
            AQUtility.debug((Object)"register net");
            this.r = new a(null);
            this.i.requestLocationUpdates(LocationManagerProxy.NETWORK_PROVIDER, this.k, 0.0f, this.r, Looper.getMainLooper());
            r8_Timer.schedule(this.r, this.j);
        }
        if (this.q) {
            AQUtility.debug((Object)"register gps");
            this.s = new a(null);
            this.i.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, this.k, 0.0f, this.s, Looper.getMainLooper());
            r8_Timer.schedule(this.s, this.j);
        }
        if (this.n <= 1 || r7_Location == null) {
            this.t = System.currentTimeMillis();
        } else {
            this.o++;
            b(r7_Location);
            this.t = System.currentTimeMillis();
        }
    }

    private Location g() {
        Location r0_Location = this.i.getLastKnownLocation(LocationManagerProxy.GPS_PROVIDER);
        Location r1_Location = this.i.getLastKnownLocation(LocationManagerProxy.NETWORK_PROVIDER);
        if (r1_Location == null) {
            return r0_Location;
        }
        if (r0_Location == null) {
            return r1_Location;
        }
        if (r0_Location.getTime() <= r1_Location.getTime()) {
            return r1_Location;
        }
        return r0_Location;
    }

    public LocationAjaxCallback accuracy(float r1f) {
        this.m = r1f;
        return this;
    }

    public void async(Context r3_Context) {
        this.i = (LocationManager) r3_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        this.q = this.i.isProviderEnabled(LocationManagerProxy.GPS_PROVIDER);
        this.p = this.i.isProviderEnabled(LocationManagerProxy.NETWORK_PROVIDER);
        f();
    }

    public LocationAjaxCallback iteration(int r1i) {
        this.n = r1i;
        return this;
    }

    public void stop() {
        AQUtility.debug((Object)"stop");
        a r0_a = this.s;
        if (r0_a != null) {
            this.i.removeUpdates(r0_a);
            r0_a.cancel();
        }
        r0_a = this.r;
        if (r0_a != null) {
            this.i.removeUpdates(r0_a);
            r0_a.cancel();
        }
        this.s = null;
        this.r = null;
    }

    public LocationAjaxCallback timeout(long r1j) {
        this.j = r1j;
        return this;
    }

    public LocationAjaxCallback tolerance(float r1f) {
        this.l = r1f;
        return this;
    }
}