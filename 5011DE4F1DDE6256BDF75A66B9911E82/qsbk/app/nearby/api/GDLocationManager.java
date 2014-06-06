package qsbk.app.nearby.api;

import android.location.Location;
import android.os.Bundle;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import qsbk.app.QsbkApp;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;

public class GDLocationManager implements AMapLocationListener, ILocationManager {
    private static GDLocationManager d;
    LocationManagerProxy a;
    TimerTask b;
    Timer c;
    private List<ILocationCallback> e;

    static {
        d = null;
    }

    private GDLocationManager() {
        this.a = null;
        this.e = new ArrayList();
        this.b = null;
        this.c = new Timer();
    }

    private void a() {
        TimeDelta r0_TimeDelta = new TimeDelta();
        this.a = LocationManagerProxy.getInstance(QsbkApp.mContext);
        LogUtil.d("init manager use time:" + r0_TimeDelta.getDelta());
    }

    private synchronized void a(AMapLocation r8_AMapLocation) {
        int r6i = this.e.size() - 1;
        while (r6i >= 0) {
            ILocationCallback r0_ILocationCallback = (ILocationCallback) this.e.remove(r6i);
            if (r8_AMapLocation != null) {
                r0_ILocationCallback.onLocation(0, r8_AMapLocation.getLatitude(), r8_AMapLocation.getLongitude());
            } else {
                r0_ILocationCallback.onLocation(-1, 0.0d, 0.0d);
            }
            r6i--;
        }
        if (this.a != null) {
            this.a.removeUpdates((AMapLocationListener)this);
        }
    }

    private synchronized int b() {
        if (this.b != null) {
            this.b.cancel();
        }
        this.b = new b(this);
        TimeDelta r6_TimeDelta = new TimeDelta();
        this.a.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 5000, 10.0f, (AMapLocationListener)this);
        this.c.schedule(this.b, ChatEngine.mQueryListInterval);
        LogUtil.d("time use:" + r6_TimeDelta.getDelta());
        return 0;
    }

    public static synchronized GDLocationManager instance() {
        GDLocationManager r0_GDLocationManager;
        synchronized (GDLocationManager.class) {
            if (d == null) {
                d = new GDLocationManager();
            }
            r0_GDLocationManager = d;
        }
        return r0_GDLocationManager;
    }

    public synchronized int getLocation(ILocationCallback r2_ILocationCallback) {
        if (this.a == null) {
            a();
        }
        if (r2_ILocationCallback == null || this.e.contains(r2_ILocationCallback)) {
        } else {
            this.e.add(r2_ILocationCallback);
        }
        return b();
    }

    public void onLocationChanged(Location r1_Location) {
    }

    public void onLocationChanged(AMapLocation r1_AMapLocation) {
        a(r1_AMapLocation);
    }

    public void onProviderDisabled(String r1_String) {
    }

    public void onProviderEnabled(String r1_String) {
    }

    public void onStatusChanged(String r1_String, int r2i, Bundle r3_Bundle) {
    }

    public void reinit() {
    }

    public void stop() {
        try {
            if (this.a != null) {
                this.a.destory();
            }
            this.a = null;
        } catch (Exception e) {
            e.printStackTrace();
            this.a = null;
        }
    }
}