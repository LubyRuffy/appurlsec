package com.baidu.location;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public final class LocationClient {
    private ServiceConnection A;
    private String a;
    private long b;
    private long c;
    private String d;
    private LocationClientOption e;
    private boolean f;
    private Context g;
    private Messenger h;
    private a i;
    private final Messenger j;
    private ArrayList k;
    private BDLocation l;
    private boolean m;
    private boolean n;
    private boolean o;
    private b p;
    private boolean q;
    private boolean r;
    private final Object s;
    private i t;
    private boolean u;
    private BDLocationListener v;
    private String w;
    private String x;
    private Boolean y;
    private Boolean z;

    private class a extends Handler {
        private a() {
        }

        public void handleMessage(Message r3_Message) {
            switch (r3_Message.what) {
                case XListViewHeader.STATE_READY:
                    LocationClient.this.b();
                    return;
                case XListViewHeader.STATE_REFRESHING:
                    LocationClient.this.c();
                    return;
                case XListViewFooter.STATE_NOMORE:
                    LocationClient.this.c(r3_Message);
                    return;
                case XListViewFooter.STATE_NODATA:
                    LocationClient.this.g();
                    return;
                case ShareUtils.SHARE_SMS:
                    LocationClient.this.e(r3_Message);
                    return;
                case ShareUtils.SHARE_COPY:
                    LocationClient.this.f(r3_Message);
                    return;
                case ShareUtils.SHARE_COLLECT:
                    LocationClient.this.h();
                    return;
                case Base64.DONT_BREAK_LINES:
                    LocationClient.this.d(r3_Message);
                    return;
                case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                    LocationClient.this.a(r3_Message);
                    return;
                case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                    LocationClient.this.b(r3_Message);
                    return;
                case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                    LocationClient.this.f();
                    return;
                case REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH:
                    LocationClient.this.a();
                    return;
                case AdViewUtil.NETWORK_TYPE_WOOBOO:
                    LocationClient.this.a(r3_Message, (int)AdViewUtil.NETWORK_TYPE_WOOBOO);
                    return;
                case AdViewUtil.NETWORK_TYPE_ADCHINA:
                    LocationClient.this.a(r3_Message, (int)AdViewUtil.NETWORK_TYPE_ADCHINA);
                    return;
                case 27:
                    LocationClient.this.g(r3_Message);
                    return;
                case 54:
                    if (LocationClient.this.e.h) {
                        LocationClient.this.q = true;
                    }
                    return;
                case 55:
                    if (LocationClient.this.e.h) {
                        LocationClient.this.q = false;
                    }
                    return;
            }
            super.handleMessage(r3_Message);
        }
    }

    private class b implements Runnable {
        private b() {
        }

        public void run() {
            synchronized (LocationClient.this.s) {
                LocationClient.this.o = false;
                if (LocationClient.this.h == null || LocationClient.this.j == null) {
                } else if (LocationClient.this.k == null || LocationClient.this.k.size() < 1) {
                } else {
                    j.if("baidu_location_Client", "request location ...");
                    LocationClient.this.i.obtainMessage(XListViewFooter.STATE_NODATA).sendToTarget();
                }
            }
        }
    }

    public LocationClient(Context r7_Context) {
        this.a = "3.3";
        this.b = 0;
        this.c = 0;
        this.d = null;
        this.e = new LocationClientOption();
        this.f = false;
        this.g = null;
        this.h = null;
        this.i = new a(null);
        this.j = new Messenger(this.i);
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = new Object();
        this.t = null;
        this.u = false;
        this.v = null;
        this.w = null;
        this.x = "http://loc.map.baidu.com/sdk.php";
        this.y = Boolean.valueOf(false);
        this.z = Boolean.valueOf(false);
        this.A = new a(this);
        this.g = r7_Context;
        this.e = new LocationClientOption();
        this.t = new i(this.g, this);
    }

    public LocationClient(Context r7_Context, LocationClientOption r8_LocationClientOption) {
        this.a = "3.3";
        this.b = 0;
        this.c = 0;
        this.d = null;
        this.e = new LocationClientOption();
        this.f = false;
        this.g = null;
        this.h = null;
        this.i = new a(null);
        this.j = new Messenger(this.i);
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = new Object();
        this.t = null;
        this.u = false;
        this.v = null;
        this.w = null;
        this.x = "http://loc.map.baidu.com/sdk.php";
        this.y = Boolean.valueOf(false);
        this.z = Boolean.valueOf(false);
        this.A = new a(this);
        this.g = r7_Context;
        this.e = r8_LocationClientOption;
        this.t = new i(this.g, this);
    }

    private void a() {
        Message r0_Message = Message.obtain(null, AdViewUtil.NETWORK_TYPE_ADVIEWAD);
        try {
            r0_Message.replyTo = this.j;
            this.h.send(r0_Message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(int r8i) {
        Iterator r1_Iterator;
        if (r8i == 26 && this.n) {
            r1_Iterator = this.k.iterator();
            while (r1_Iterator.hasNext()) {
                ((BDLocationListener) r1_Iterator.next()).onReceivePoi(this.l);
            }
            this.n = false;
            if (this.m) {
                if (!(this.e != null && this.e.isDisableCache() && this.l.getLocType() == 65)) {
                    r1_Iterator = this.k.iterator();
                    while (r1_Iterator.hasNext()) {
                        ((BDLocationListener) r1_Iterator.next()).onReceiveLocation(this.l);
                    }
                    if (this.r) {
                        if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                        } else {
                            this.m = false;
                        }
                    } else if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                    } else {
                        this.m = false;
                    }
                }
            } else if ((this.e.h && this.l.getLocType() == 61) || this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                if (this.e != null || this.e.isDisableCache() || this.l.getLocType() == 65) {
                    r1_Iterator = this.k.iterator();
                    while (r1_Iterator.hasNext()) {
                        ((BDLocationListener) r1_Iterator.next()).onReceiveLocation(this.l);
                    }
                    if (this.r) {
                        if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                        } else {
                            this.m = false;
                        }
                    } else if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                    } else {
                        this.m = false;
                    }
                }
            }
        } else {
            if (this.m) {
                if ((this.e.h || this.l.getLocType() == 61) && this.l.getLocType() == 66 && this.l.getLocType() == 67) {
                }
            }
            if (this.e != null || this.e.isDisableCache() || this.l.getLocType() == 65) {
                r1_Iterator = this.k.iterator();
                while (r1_Iterator.hasNext()) {
                    ((BDLocationListener) r1_Iterator.next()).onReceiveLocation(this.l);
                }
                if (this.r) {
                    if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                    } else {
                        this.m = false;
                    }
                } else if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                } else {
                    this.m = false;
                }
            }
        }
    }

    private void a(Message r3_Message) {
        if (r3_Message == null || r3_Message.obj == null) {
        } else {
            this.t.if((BDNotifyListener) r3_Message.obj);
        }
    }

    private void a(Message r5_Message, int r6i) {
        String r0_String = r5_Message.getData().getString("locStr");
        j.if("baidu_location_Client", "on receive new location : " + r0_String);
        j.a("baidu_location_Client", "on receive new location : " + r0_String);
        this.l = new BDLocation(r0_String);
        a(r6i);
    }

    private void b() {
        if (this.f) {
        } else {
            j.try();
            this.d = this.g.getPackageName();
            this.w = this.d + "_bdls_v2.9";
            j.if("baidu_location_Client", this.w);
            Intent r0_Intent = new Intent(this.g, f.class);
            if (this.e == null) {
                this.e = new LocationClientOption();
            }
            try {
                this.g.bindService(r0_Intent, this.A, 1);
            } catch (Exception e) {
                e.printStackTrace();
                this.f = false;
            }
        }
    }

    private void b(Message r3_Message) {
        if (r3_Message == null || r3_Message.obj == null) {
        } else {
            this.t.do((BDNotifyListener) r3_Message.obj);
        }
    }

    private void c() {
        if ((!this.f) || this.h == null) {
        } else {
            Message r0_Message = Message.obtain(null, REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
            r0_Message.replyTo = this.j;
            try {
                this.h.send(r0_Message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.g.unbindService(this.A);
            synchronized (this.s) {
                try {
                    if (this.o) {
                        this.i.removeCallbacks(this.p);
                        this.o = false;
                    }
                } catch (Exception e_2) {
                }
            }
            this.t.if();
            this.h = null;
            j.new();
            this.f = false;
        }
    }

    private void c(Message r7_Message) {
        j.if("baidu_location_Client", "onSetOption...");
        if (r7_Message == null || r7_Message.obj == null) {
            j.if("baidu_location_Client", "setOption, but msg.obj is null");
        } else {
            LocationClientOption r0_LocationClientOption = (LocationClientOption) r7_Message.obj;
            if (!this.e.equals(r0_LocationClientOption)) {
                Message r0_Message;
                if (this.e.d != r0_LocationClientOption.d) {
                    try {
                    } catch (Exception e) {
                        j.if("baidu_location_Client", "set location excpetion...");
                    }
                    synchronized (this.s) {
                        if (this.o) {
                            this.i.removeCallbacks(this.p);
                            this.o = false;
                        }
                        if (r0_LocationClientOption.d < 1000 || this.o) {
                        } else {
                            if (this.p == null) {
                                this.p = new b(null);
                            }
                            this.i.postDelayed(this.p, (long) r0_LocationClientOption.d);
                            this.o = true;
                        }
                        if (this.h != null) {
                            j.if("baidu_location_Client", "server not connected");
                        } else {
                            try {
                                r0_Message = Message.obtain(null, NearbySelectView.TIME_15MIN);
                                r0_Message.replyTo = this.j;
                                r0_Message.setData(d());
                                this.h.send(r0_Message);
                                j.if("baidu_location_Client", "change option ...");
                            } catch (Exception e_2) {
                                e_2.printStackTrace();
                            }
                        }
                    }
                }
                this.e = new LocationClientOption(r0_LocationClientOption);
                if (this.h != null) {
                    r0_Message = Message.obtain(null, NearbySelectView.TIME_15MIN);
                    r0_Message.replyTo = this.j;
                    r0_Message.setData(d());
                    this.h.send(r0_Message);
                    j.if("baidu_location_Client", "change option ...");
                } else {
                    j.if("baidu_location_Client", "server not connected");
                }
            }
        }
    }

    private Bundle d() {
        if (this.e == null) {
            return null;
        }
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putString("packName", this.d);
        r0_Bundle.putString("prodName", this.e.f);
        r0_Bundle.putString("coorType", this.e.a);
        r0_Bundle.putString("addrType", this.e.b);
        r0_Bundle.putString("Url", this.x);
        r0_Bundle.putBoolean("openGPS", this.e.c);
        r0_Bundle.putBoolean("location_change_notify", this.e.h);
        r0_Bundle.putInt("scanSpan", this.e.d);
        r0_Bundle.putInt("timeOut", this.e.e);
        r0_Bundle.putInt("priority", this.e.g);
        r0_Bundle.putBoolean("map", this.y.booleanValue());
        r0_Bundle.putBoolean("import", this.z.booleanValue());
        return r0_Bundle;
    }

    private void d(Message r2_Message) {
        if (r2_Message == null || r2_Message.obj == null) {
        } else {
            this.v = (BDLocationListener) r2_Message.obj;
        }
    }

    private Bundle e() {
        if (this.e == null) {
            return null;
        }
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putInt("num", this.e.m);
        r0_Bundle.putFloat("distance", this.e.l);
        r0_Bundle.putBoolean("extraInfo", this.e.k);
        return r0_Bundle;
    }

    private void e(Message r3_Message) {
        if (r3_Message == null || r3_Message.obj == null) {
        } else {
            BDLocationListener r0_BDLocationListener = (BDLocationListener) r3_Message.obj;
            if (this.k == null) {
                this.k = new ArrayList();
            }
            this.k.add(r0_BDLocationListener);
        }
    }

    private void f() {
        if (this.h == null) {
            j.if("baidu_location_Client", "server not connected");
        } else {
            Message r0_Message = Message.obtain(null, Util.BEGIN_TIME);
            try {
                r0_Message.replyTo = this.j;
                this.h.send(r0_Message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void f(Message r3_Message) {
        if (r3_Message == null || r3_Message.obj == null) {
        } else {
            BDLocationListener r0_BDLocationListener = (BDLocationListener) r3_Message.obj;
            if (this.k == null || (!this.k.contains(r0_BDLocationListener))) {
            } else {
                this.k.remove(r0_BDLocationListener);
            }
        }
    }

    private void g() {
        if (this.h == null) {
            j.if("baidu_location_Client", "server not connected");
        } else if (this.q && this.e.h) {
            synchronized (this.s) {
                if (this.e == null || this.e.d < 1000 || this.o) {
                } else if (this.p != null) {
                    this.i.postDelayed(this.p, (long) this.e.d);
                    this.o = true;
                } else {
                    this.p = new b(null);
                    this.i.postDelayed(this.p, (long) this.e.d);
                    this.o = true;
                }
            }
        } else {
            Message r0_Message = Message.obtain(null, Util.BEGIN_TIME);
            try {
                r0_Message.replyTo = this.j;
                this.h.send(r0_Message);
                this.b = System.currentTimeMillis();
                this.m = true;
                j.if("baidu_location_Client", "send request to server...");
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (this.s) {
                if (this.e == null || this.e.d < 1000 || this.o) {
                } else {
                    if (this.p != null) {
                        this.p = new b(null);
                    }
                    this.i.postDelayed(this.p, (long) this.e.d);
                    this.o = true;
                }
            }
        }
    }

    private void g(Message r4_Message) {
        BDLocation r1_BDLocation = new BDLocation(r4_Message.getData().getString("locStr"));
        if (this.v != null) {
            if (!(this.e != null && this.e.isDisableCache() && r1_BDLocation.getLocType() == 65)) {
                this.v.onReceiveLocation(r1_BDLocation);
            }
        }
    }

    private void h() {
        if (this.h == null) {
        } else {
            Message r0_Message = Message.obtain(null, AdViewUtil.NETWORK_TYPE_WIYUN);
            try {
                r0_Message.replyTo = this.j;
                r0_Message.setData(e());
                this.h.send(r0_Message);
                this.c = System.currentTimeMillis();
                this.n = true;
                j.if("baidu_location_Client", "send poi request to server...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BDLocation getLastKnownLocation() {
        return this.l;
    }

    public LocationClientOption getLocOption() {
        return this.e;
    }

    public String getVersion() {
        return this.a;
    }

    public boolean isStarted() {
        return this.f;
    }

    public void registerLocationListener(BDLocationListener r3_BDLocationListener) {
        Message r0_Message = this.i.obtainMessage(ShareUtils.SHARE_SMS);
        r0_Message.obj = r3_BDLocationListener;
        r0_Message.sendToTarget();
    }

    public void registerNotify(BDNotifyListener r3_BDNotifyListener) {
        Message r0_Message = this.i.obtainMessage(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY);
        r0_Message.obj = r3_BDNotifyListener;
        r0_Message.sendToTarget();
    }

    public void registerNotifyLocationListener(BDLocationListener r3_BDLocationListener) {
        Message r0_Message = this.i.obtainMessage(Base64.DONT_BREAK_LINES);
        r0_Message.obj = r3_BDLocationListener;
        r0_Message.sendToTarget();
    }

    public void removeNotifyEvent(BDNotifyListener r3_BDNotifyListener) {
        Message r0_Message = this.i.obtainMessage(REQUEST_CODE.REQUEST_CODE_EDIT_INTRO);
        r0_Message.obj = r3_BDNotifyListener;
        r0_Message.sendToTarget();
    }

    public int requestLocation() {
        if (this.h == null || this.j == null) {
            return 1;
        }
        if (this.k == null || this.k.size() < 1) {
            return XListViewHeader.STATE_REFRESHING;
        }
        if (System.currentTimeMillis() - this.b < 1000) {
            return ShareUtils.SHARE_COPY;
        }
        j.if("baidu_location_Client", "request location ...");
        this.i.obtainMessage(XListViewFooter.STATE_NODATA).sendToTarget();
        return 0;
    }

    public void requestNotifyLocation() {
        this.i.obtainMessage(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE).sendToTarget();
    }

    public int requestOfflineLocation() {
        if (this.h == null || this.j == null) {
            return 1;
        }
        if (this.k == null || this.k.size() < 1) {
            return XListViewHeader.STATE_REFRESHING;
        }
        this.i.obtainMessage(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH).sendToTarget();
        return 0;
    }

    public int requestPoi() {
        int r0i = ShareUtils.SHARE_COLLECT;
        if (this.h == null || this.j == null) {
            return 1;
        }
        if (this.k == null || this.k.size() < 1) {
            return XListViewHeader.STATE_REFRESHING;
        }
        if (System.currentTimeMillis() - this.c < 6000) {
            return ShareUtils.SHARE_COPY;
        }
        if (this.e.m < 1) {
            return ShareUtils.SHARE_COLLECT;
        }
        j.if("baidu_location_Client", "request location ...");
        this.i.obtainMessage(r0i).sendToTarget();
        return 0;
    }

    public void setForBaiduMap(boolean r2z) {
        this.y = Boolean.valueOf(r2z);
    }

    public void setLocOption(LocationClientOption r3_LocationClientOption) {
        if (r3_LocationClientOption == null) {
            r3_LocationClientOption = new LocationClientOption();
        }
        Message r0_Message = this.i.obtainMessage(XListViewFooter.STATE_NOMORE);
        r0_Message.obj = r3_LocationClientOption;
        r0_Message.sendToTarget();
    }

    public void start() {
        this.i.obtainMessage(1).sendToTarget();
    }

    public void stop() {
        this.i.obtainMessage(XListViewHeader.STATE_REFRESHING).sendToTarget();
    }

    public void unRegisterLocationListener(BDLocationListener r3_BDLocationListener) {
        Message r0_Message = this.i.obtainMessage(ShareUtils.SHARE_COPY);
        r0_Message.obj = r3_BDLocationListener;
        r0_Message.sendToTarget();
    }

    public boolean updateLocation(Location r3_Location) {
        if (this.h == null || this.j == null || r3_Location == null) {
            return false;
        }
        try {
            Message r0_Message = Message.obtain(null, AdViewUtil.NETWORK_TYPE_PUNCHBOX);
            r0_Message.obj = r3_Location;
            this.h.send(r0_Message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}