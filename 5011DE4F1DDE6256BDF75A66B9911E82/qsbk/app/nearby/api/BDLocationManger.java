package qsbk.app.nearby.api;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.listview.XListViewHeader;

public class BDLocationManger implements ILocationManager {
    private static BDLocationManger a;
    private LocationClient b;
    private List<ILocationCallback> c;

    static {
        a = null;
    }

    private BDLocationManger() {
        this.b = null;
        this.c = new ArrayList();
    }

    private void a() {
        if (this.b != null) {
            this.b.stop();
        }
        this.b = new LocationClient(QsbkApp.mContext);
        LocationClientOption r0_LocationClientOption = new LocationClientOption();
        r0_LocationClientOption.disableCache(false);
        r0_LocationClientOption.setProdName("qsbk.app");
        r0_LocationClientOption.setCoorType("gcj02");
        r0_LocationClientOption.setPriority(XListViewHeader.STATE_REFRESHING);
        r0_LocationClientOption.setTimeOut(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        this.b.setLocOption(r0_LocationClientOption);
        this.b.registerLocationListener(new a(this));
        this.b.start();
    }

    private synchronized void a(BDLocation r8_BDLocation) {
        int r6i = this.c.size() - 1;
        while (r6i >= 0) {
            ((ILocationCallback) this.c.remove(r6i)).onLocation(r8_BDLocation.getLocType(), r8_BDLocation.getLatitude(), r8_BDLocation.getLongitude());
            r6i--;
        }
    }

    private synchronized int b() {
        LogUtil.d("location started:" + this.b.isStarted());
        return this.b.requestLocation();
    }

    public static synchronized BDLocationManger instance() {
        BDLocationManger r0_BDLocationManger;
        synchronized (BDLocationManger.class) {
            if (a == null) {
                a = new BDLocationManger();
            }
            r0_BDLocationManger = a;
        }
        return r0_BDLocationManger;
    }

    public synchronized int getLocation(ILocationCallback r2_ILocationCallback) {
        if (this.b == null) {
            a();
        }
        if (r2_ILocationCallback == null || this.c.contains(r2_ILocationCallback)) {
        } else {
            this.c.add(r2_ILocationCallback);
        }
        return b();
    }

    public void reinit() {
        a();
    }

    public void stop() {
        try {
            if (this.b != null) {
                this.b.stop();
            }
            this.b = null;
        } catch (Exception e) {
            e.printStackTrace();
            this.b = null;
        }
    }
}