package com.amap.api.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.amap.api.location.core.c;
import com.amap.api.location.core.d;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class LocationManagerProxy {
    public static final String GPS_PROVIDER = "gps";
    public static final String KEY_LOCATION_CHANGED = "location";
    public static final String KEY_PROVIDER_ENABLED = "providerEnabled";
    public static final String KEY_PROXIMITY_ENTERING = "entering";
    public static final String KEY_STATUS_CHANGED = "status";
    public static final String NETWORK_PROVIDER = "network";
    private static LocationManagerProxy b;
    private LocationManager a;
    private a c;
    private Context d;
    private e e;
    private b f;
    private ArrayList<PendingIntent> g;
    private Hashtable<String, LocationProviderProxy> h;
    private Vector<f> i;
    private a j;
    private c k;
    private String l;
    private List<e> m;

    class a implements AMapLocationListener {
        a() {
        }

        public void onLocationChanged(Location r4_Location) {
            if (r4_Location != null) {
                AMapLocation r2_AMapLocation = new AMapLocation(r4_Location);
                if (LocationManagerProxy.this.i == null || LocationManagerProxy.this.i.size() <= 0) {
                } else {
                    int r1i = 0;
                    while (r1i < LocationManagerProxy.this.i.size()) {
                        if (LocationManagerProxy.this.i.get(r1i) == null || ((f) LocationManagerProxy.this.i.get(r1i)).c == null) {
                            r1i++;
                        } else {
                            ((f) LocationManagerProxy.this.i.get(r1i)).c.onLocationChanged(r2_AMapLocation);
                            r1i++;
                        }
                    }
                }
            }
        }

        public void onLocationChanged(AMapLocation r1_AMapLocation) {
        }

        public void onProviderDisabled(String r1_String) {
        }

        public void onProviderEnabled(String r1_String) {
        }

        public void onStatusChanged(String r1_String, int r2i, Bundle r3_Bundle) {
        }
    }

    class b implements AMapLocationListener {
        b() {
        }

        public void onLocationChanged(Location r6_Location) {
            if (LocationManagerProxy.this.g == null || LocationManagerProxy.this.g.size() <= 0) {
            } else {
                Iterator r1_Iterator = LocationManagerProxy.this.g.iterator();
                while (r1_Iterator.hasNext()) {
                    PendingIntent r0_PendingIntent = (PendingIntent) r1_Iterator.next();
                    Intent r2_Intent = new Intent();
                    Bundle r3_Bundle = new Bundle();
                    r3_Bundle.putParcelable(KEY_LOCATION_CHANGED, r6_Location);
                    r2_Intent.putExtras(r3_Bundle);
                    try {
                        r0_PendingIntent.send(LocationManagerProxy.this.d, 0, r2_Intent);
                    } catch (CanceledException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void onLocationChanged(AMapLocation r6_AMapLocation) {
            if (LocationManagerProxy.this.g == null || LocationManagerProxy.this.g.size() <= 0) {
            } else {
                Iterator r1_Iterator = LocationManagerProxy.this.g.iterator();
                while (r1_Iterator.hasNext()) {
                    PendingIntent r0_PendingIntent = (PendingIntent) r1_Iterator.next();
                    Intent r2_Intent = new Intent();
                    Bundle r3_Bundle = new Bundle();
                    r3_Bundle.putParcelable(KEY_LOCATION_CHANGED, r6_AMapLocation);
                    r2_Intent.putExtras(r3_Bundle);
                    try {
                        r0_PendingIntent.send(LocationManagerProxy.this.d, 0, r2_Intent);
                    } catch (CanceledException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void onProviderDisabled(String r1_String) {
        }

        public void onProviderEnabled(String r1_String) {
        }

        public void onStatusChanged(String r1_String, int r2i, Bundle r3_Bundle) {
        }
    }

    static {
        b = null;
    }

    private LocationManagerProxy(Activity r2_Activity) {
        this.a = null;
        this.c = null;
        this.g = new ArrayList();
        this.h = new Hashtable();
        this.i = new Vector();
        this.j = new a();
        this.m = new ArrayList();
        a(r2_Activity.getApplicationContext());
    }

    private LocationManagerProxy(Context r2_Context) {
        this.a = null;
        this.c = null;
        this.g = new ArrayList();
        this.h = new Hashtable();
        this.i = new Vector();
        this.j = new a();
        this.m = new ArrayList();
        a(r2_Context);
    }

    private void a(Context r3_Context) {
        this.d = r3_Context;
        this.k = c.a(r3_Context);
        this.a = (LocationManager) r3_Context.getSystemService(KEY_LOCATION_CHANGED);
        this.c = a.a(r3_Context.getApplicationContext(), this.a);
        this.l = this.k.c(r3_Context);
    }

    public static synchronized LocationManagerProxy getInstance(Activity r2_Activity) {
        LocationManagerProxy r0_LocationManagerProxy;
        synchronized (LocationManagerProxy.class) {
            try {
                if (b == null) {
                    b = new LocationManagerProxy(r2_Activity);
                }
                r0_LocationManagerProxy = b;
            } catch (Exception e) {
                e.printStackTrace();
                r0_LocationManagerProxy = null;
            }
        }
        return r0_LocationManagerProxy;
    }

    public static synchronized LocationManagerProxy getInstance(Context r2_Context) {
        LocationManagerProxy r0_LocationManagerProxy;
        synchronized (LocationManagerProxy.class) {
            try {
                if (b == null) {
                    b = new LocationManagerProxy(r2_Context);
                }
                r0_LocationManagerProxy = b;
            } catch (Exception e) {
                e.printStackTrace();
                r0_LocationManagerProxy = null;
            }
        }
        return r0_LocationManagerProxy;
    }

    public boolean addGpsStatusListener(Listener r2_Listener) {
        return this.a != null ? this.a.addGpsStatusListener(r2_Listener) : false;
    }

    public void addProximityAlert(double r11d, double r13d, float r15f, long r16j, PendingIntent r18_PendingIntent) {
        try {
            if (a.e) {
                this.a.addProximityAlert(r11d, r13d, r15f, r16j, r18_PendingIntent);
            }
            e r9_e = new e(this);
            this.m.add(r9_e);
            this.c.a(r11d, r13d, r15f, r16j, r18_PendingIntent);
            requestLocationUpdates(LocationProviderProxy.AMapNetwork, 5000, r15f, (AMapLocationListener)r9_e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTestProvider(String r12_String, boolean r13z, boolean r14z, boolean r15z, boolean r16z, boolean r17z, boolean r18z, boolean r19z, int r20i, int r21i) {
        try {
            if (this.a != null) {
                this.a.addTestProvider(r12_String, r13z, r14z, r15z, r16z, r17z, r18z, r19z, r20i, r21i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTestProviderEnabled(String r2_String) {
        if (this.a != null) {
            this.a.clearTestProviderEnabled(r2_String);
        }
    }

    public void clearTestProviderLocation(String r2_String) {
        if (this.a != null) {
            this.a.clearTestProviderLocation(r2_String);
        }
    }

    public void clearTestProviderStatus(String r2_String) {
        if (this.a != null) {
            this.a.clearTestProviderStatus(r2_String);
        }
    }

    public void destory() {
        if (this.c != null) {
            this.c.b();
        }
        if (this.h != null) {
            this.h.clear();
        }
        if (this.i != null) {
            this.i.clear();
        }
        if (this.a != null) {
            this.a.removeUpdates(this.j);
            if (this.g != null) {
                int r1i = 0;
                while (r1i < this.g.size()) {
                    PendingIntent r0_PendingIntent = (PendingIntent) this.g.get(r1i);
                    if (r0_PendingIntent != null) {
                        this.a.removeUpdates(r0_PendingIntent);
                    }
                    r1i++;
                }
            }
        }
        if (this.g != null) {
            this.g.clear();
        }
        this.h = null;
        this.g = null;
        this.c = null;
        b = null;
        this.m = null;
        this.i = null;
        this.j = null;
    }

    public List<String> getAllProviders() {
        List<String> r0_List_String;
        try {
            r0_List_String = this.a.getAllProviders();
            if (r0_List_String != null) {
                if (!r0_List_String.contains(LocationProviderProxy.AMapNetwork)) {
                    r0_List_String.add(LocationProviderProxy.AMapNetwork);
                }
                return r0_List_String;
            } else {
                r0_List_String = new ArrayList();
                r0_List_String.add(LocationProviderProxy.AMapNetwork);
                r0_List_String.addAll(this.a.getAllProviders());
                return r0_List_String;
            }
        } catch (Exception e) {
            e.printStackTrace();
            r0_List_String = null;
        }
    }

    public String getBestProvider(Criteria r3_Criteria, boolean r4z) {
        String r0_String;
        try {
            r0_String = LocationProviderProxy.AMapNetwork;
            if (r3_Criteria == null) {
                return r0_String;
            }
            if (!getProvider(LocationProviderProxy.AMapNetwork).meetsCriteria(r3_Criteria)) {
                r0_String = this.a.getBestProvider(r3_Criteria, r4z);
            }
            if ((!r4z) || d.a(this.d)) {
                return r0_String;
            }
            r0_String = this.a.getBestProvider(r3_Criteria, r4z);
            return r0_String;
        } catch (Exception e) {
            e.printStackTrace();
            r0_String = null;
        }
    }

    public GpsStatus getGpsStatus(GpsStatus r3_GpsStatus) {
        try {
            return this.a != null ? this.a.getGpsStatus(r3_GpsStatus) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public AMapLocation getLastKnownLocation(String r4_String) {
        AMapLocation r0_AMapLocation;
        try {
            if (LocationProviderProxy.AMapNetwork.equals(r4_String) && this.k.a(this.l)) {
                r0_AMapLocation = this.c.a();
            } else {
                Location r2_Location = this.a.getLastKnownLocation(r4_String);
                r0_AMapLocation = (this.a == null || r2_Location == null) ? null : new AMapLocation(r2_Location);
            }
        } catch (Exception e) {
            e.printStackTrace();
            r0_AMapLocation = null;
        }
        return r0_AMapLocation;
    }

    public LocationProviderProxy getProvider(String r3_String) {
        if (r3_String == null) {
            throw new IllegalArgumentException("name\u4e0d\u80fd\u4e3a\u7a7a\uff01");
        } else {
            if (this.h.containsKey(r3_String)) {
                return (LocationProviderProxy) this.h.get(r3_String);
            }
            LocationProviderProxy r0_LocationProviderProxy = LocationProviderProxy.a(this.a, r3_String);
            this.h.put(r3_String, r0_LocationProviderProxy);
            return r0_LocationProviderProxy;
        }
        e.printStackTrace();
        return null;
    }

    public List<String> getProviders(Criteria r4_Criteria, boolean r5z) {
        try {
            List<String> r0_List_String = this.a.getProviders(r4_Criteria, r5z);
            if (r0_List_String == null || r0_List_String.size() == 0) {
                r0_List_String = new ArrayList();
                if (LocationProviderProxy.AMapNetwork.equals(getBestProvider(r4_Criteria, r5z))) {
                    return r0_List_String;
                }
                r0_List_String.add(LocationProviderProxy.AMapNetwork);
                return r0_List_String;
            } else {
                if (LocationProviderProxy.AMapNetwork.equals(getBestProvider(r4_Criteria, r5z))) {
                    return r0_List_String;
                }
                r0_List_String.add(LocationProviderProxy.AMapNetwork);
                return r0_List_String;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getProviders(boolean r3z) {
        try {
            List<String> r0_List_String = this.a.getProviders(r3z);
            if (!isProviderEnabled(LocationProviderProxy.AMapNetwork)) {
                return r0_List_String;
            }
            if (r0_List_String == null || r0_List_String.size() == 0) {
                r0_List_String = new ArrayList();
                r0_List_String.add(LocationProviderProxy.AMapNetwork);
                return r0_List_String;
            } else {
                r0_List_String.add(LocationProviderProxy.AMapNetwork);
                return r0_List_String;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isProviderEnabled(String r2_String) {
        boolean r0z;
        try {
            if (LocationProviderProxy.AMapNetwork.equals(r2_String)) {
                r0z = d.a(this.d);
                return r0z;
            } else {
                r0z = this.a.isProviderEnabled(r2_String);
                return r0z;
            }
        } catch (Exception e) {
            e.printStackTrace();
            r0z = false;
        }
    }

    public void removeGpsStatusListener(Listener r2_Listener) {
        if (this.a != null) {
            this.a.removeGpsStatusListener(r2_Listener);
        }
    }

    public void removeProximityAlert(PendingIntent r3_PendingIntent) {
        try {
            int r1i;
            if ((!a.e) || this.a == null) {
                this.c.a(r3_PendingIntent);
                if (this.c.c() <= 0) {
                    if (this.m == null || this.m.size() <= 0) {
                        this.m.remove(0);
                    } else {
                        removeUpdates((AMapLocationListener) this.m.get(0));
                        this.m.remove(0);
                    }
                } else {
                    r1i = 0;
                    while (r1i < this.m.size()) {
                        removeUpdates((AMapLocationListener) this.m.get(r1i));
                        this.m.remove(r1i);
                        r1i++;
                    }
                }
            } else {
                this.a.removeProximityAlert(r3_PendingIntent);
                this.c.a(r3_PendingIntent);
                if (this.c.c() <= 0) {
                    r1i = 0;
                    while (r1i < this.m.size()) {
                        removeUpdates((AMapLocationListener) this.m.get(r1i));
                        this.m.remove(r1i);
                        r1i++;
                    }
                } else {
                    if (this.m == null || this.m.size() <= 0) {
                        this.m.remove(0);
                    } else {
                        removeUpdates((AMapLocationListener) this.m.get(0));
                        this.m.remove(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUpdates(PendingIntent r2_PendingIntent) {
        try {
            if (this.e != null) {
                this.g.remove(r2_PendingIntent);
                if (this.g.size() == 0) {
                    this.e.a();
                }
            }
            this.e = null;
            this.a.removeUpdates(r2_PendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUpdates(AMapLocationListener r5_AMapLocationListener) {
        if (r5_AMapLocationListener != null) {
            if (this.c != null) {
                this.c.a(r5_AMapLocationListener);
            }
            this.a.removeUpdates(r5_AMapLocationListener);
        }
        if (this.i == null || this.i.size() <= 0) {
        } else {
            int r2i = this.i.size();
            int r1i = 0;
            while (r1i < r2i) {
                int r0i;
                f r0_f = (f) this.i.get(r1i);
                if (r5_AMapLocationListener.equals(r0_f.c)) {
                    this.i.remove(r0_f);
                    r0i = r1i - 1;
                    r1i = r2i - 1;
                } else {
                    r0i = r1i;
                    r1i = r2i;
                }
                r2i = r1i;
                r1i = r0i + 1;
            }
            if (this.i.size() == 0) {
                this.a.removeUpdates(this.j);
            }
        }
    }

    public void requestLocationUpdates(String r7_String, long r8j, float r10f, PendingIntent r11_PendingIntent) {
        try {
            if (LocationProviderProxy.AMapNetwork.equals(r7_String) && this.k.a(this.l)) {
                if (this.e == null) {
                    this.e = new e(this);
                }
                if (this.f == null) {
                    this.f = new b();
                }
                this.e.a(this.f, r8j, r10f, r7_String);
                this.g.add(r11_PendingIntent);
            } else {
                this.a.requestLocationUpdates(r7_String, r8j, r10f, r11_PendingIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestLocationUpdates(String r11_String, long r12j, float r14f, AMapLocationListener r15_AMapLocationListener) {
        String r5_String;
        try {
            String r5_String_2;
            if (this.c == null) {
                this.c = a.a(this.d.getApplicationContext(), this.a);
            }
            r5_String_2 = r11_String == null ? LocationProviderProxy.AMapNetwork : r11_String;
            if (this.k.a(this.l) || (!LocationProviderProxy.AMapNetwork.equals(r5_String_2))) {
                if (LocationProviderProxy.AMapNetwork.equals(r5_String_2) && this.k.a(this.l)) {
                    this.c.a(r12j, r14f, r15_AMapLocationListener, LocationProviderProxy.AMapNetwork);
                } else if (GPS_PROVIDER.equals(r5_String_2)) {
                    if (!this.a.isProviderEnabled(r5_String_2)) {
                        this.i.add(new f(r12j, r14f, r15_AMapLocationListener, r5_String_2));
                        this.a.requestLocationUpdates(r5_String_2, r12j, r14f, this.j);
                    }
                } else {
                    this.c.a(r12j, r14f, r15_AMapLocationListener, GPS_PROVIDER);
                }
            } else {
                r5_String_2 = NETWORK_PROVIDER;
                if (LocationProviderProxy.AMapNetwork.equals(r5_String_2) || this.k.a(this.l)) {
                    if (GPS_PROVIDER.equals(r5_String_2)) {
                        if (this.a.isProviderEnabled(r5_String_2)) {
                        } else {
                            this.i.add(new f(r12j, r14f, r15_AMapLocationListener, r5_String_2));
                            this.a.requestLocationUpdates(r5_String_2, r12j, r14f, this.j);
                        }
                    } else {
                        this.c.a(r12j, r14f, r15_AMapLocationListener, GPS_PROVIDER);
                    }
                } else {
                    this.c.a(r12j, r14f, r15_AMapLocationListener, LocationProviderProxy.AMapNetwork);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGpsEnable(boolean r2z) {
        this.c.a(r2z);
    }
}