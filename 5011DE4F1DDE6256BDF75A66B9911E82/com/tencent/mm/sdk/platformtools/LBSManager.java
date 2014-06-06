package com.tencent.mm.sdk.platformtools;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.amap.api.location.LocationManagerProxy;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.PhoneUtil.MacInfo;
import java.util.LinkedList;
import java.util.List;
import org.apache.cordova.NetworkManager;
import qsbk.app.nearby.ui.NearByListActivity;

public class LBSManager extends BroadcastReceiver {
    public static final String FILTER_GPS = "filter_gps";
    public static final int INVALID_ACC = -1000;
    public static final float INVALID_LAT = -1000.0f;
    public static final float INVALID_LNG = -1000.0f;
    public static final int MM_SOURCE_HARDWARE = 0;
    public static final int MM_SOURCE_NET = 1;
    public static final int MM_SOURCE_REPORT_HARWARE = 3;
    public static final int MM_SOURCE_REPORT_NETWORK = 4;
    private static a e;
    boolean a;
    boolean b;
    boolean c;
    int d;
    private OnLocationGotListener f;
    private LocationManager g;
    private Context h;
    private PendingIntent i;
    private boolean j;
    private MTimerHandler k;

    public static interface OnLocationGotListener {
        public void onLocationGot(float r1f, float r2f, int r3i, int r4i, String r5_String, String r6_String, boolean r7z);
    }

    static class a {
        float a;
        float b;
        int c;
        long d;
        int e;

        a() {
            this.a = -1000.0f;
            this.b = -1000.0f;
            this.c = -1000;
            this.e = 1;
        }
    }

    public LBSManager(Context r4_Context, OnLocationGotListener r5_OnLocationGotListener) {
        this.j = false;
        this.b = false;
        this.c = false;
        this.k = new MTimerHandler(new e(this), false);
        this.f = r5_OnLocationGotListener;
        this.a = false;
        this.d = 0;
        this.h = r4_Context;
        PhoneUtil.getSignalStrength(r4_Context);
        this.g = (LocationManager) r4_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        a();
        this.i = PendingIntent.getBroadcast(r4_Context, MM_SOURCE_HARDWARE, new Intent(FILTER_GPS), 134217728);
    }

    private boolean a() {
        if (this.g == null) {
            return false;
        }
        try {
            this.g.sendExtraCommand(LocationManagerProxy.GPS_PROVIDER, "force_xtra_injection", null);
            this.g.sendExtraCommand(LocationManagerProxy.GPS_PROVIDER, "force_time_injection", null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void b() {
        this.k.stopTimer();
        this.a = true;
    }

    public static void setLocationCache(float r3f, float r4f, int r5i, int r6i) {
        if (r5i == 0) {
        } else {
            Log.v("MicroMsg.LBSManager", new StringBuilder("setLocationCache [").append(r3f).append(",").append(r4f).append("] acc:").append(r5i).append(" source:").append(r6i).toString());
            if (e == null) {
                e = new a();
            }
            e.a = r3f;
            e.b = r4f;
            e.c = r5i;
            e.d = System.currentTimeMillis();
            e.e = r6i;
        }
    }

    public String getTelLocation() {
        return PhoneUtil.getCellXml(PhoneUtil.getCellInfoList(this.h));
    }

    public String getWIFILocation() {
        WifiManager r0_WifiManager = (WifiManager) this.h.getSystemService(NetworkManager.WIFI);
        if (r0_WifiManager == null) {
            Log.e("MicroMsg.LBSManager", "no wifi service");
            return RContactStorage.PRIMARY_KEY;
        } else if (r0_WifiManager.getConnectionInfo() == null) {
            Log.e("MicroMsg.LBSManager", "WIFILocation wifi info null");
            return RContactStorage.PRIMARY_KEY;
        } else {
            List r2_List = new LinkedList();
            List r3_List = r0_WifiManager.getScanResults();
            if (r3_List != null) {
                int r1i = 0;
                while (r1i < r3_List.size()) {
                    r2_List.add(new MacInfo(((ScanResult) r3_List.get(r1i)).BSSID, ((ScanResult) r3_List.get(r1i)).level));
                    r1i++;
                }
            }
            return PhoneUtil.getMacXml(r2_List);
        }
    }

    public boolean isGpsEnable() {
        try {
            return this.g.isProviderEnabled(LocationManagerProxy.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isNetworkPrividerEnable() {
        try {
            return this.g.isProviderEnabled(LocationManagerProxy.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void onReceive(Context r11_Context, Intent r12_Intent) {
        Location r3_Location = (Location) r12_Intent.getExtras().get(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        this.d++;
        if (r3_Location != null) {
            int r4i;
            String r5_String;
            String r6_String;
            boolean r0z = LocationManagerProxy.GPS_PROVIDER.equals(r3_Location.getProvider());
            if ((!r0z) || r3_Location.getAccuracy() > 200.0f) {
                if (r0z || r3_Location.getAccuracy() > 1000.0f || r3_Location.getAccuracy() <= 0.0f) {
                } else {
                    if (r0z) {
                        r4i = MM_SOURCE_HARDWARE;
                    }
                    setLocationCache((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), r4i);
                    if (this.f == null) {
                        if (!(this.a && this.b && this.c)) {
                            r5_String = Util.nullAsNil(getWIFILocation());
                            r6_String = Util.nullAsNil(getTelLocation());
                            if (this.a) {
                                b();
                                this.a = true;
                                Log.v("MicroMsg.LBSManager", new StringBuilder("location by provider ok:[").append(r3_Location.getLatitude()).append(" , ").append(r3_Location.getLongitude()).append("]  accuracy:").append(r3_Location.getAccuracy()).append("  retry count:").append(this.d).append(" isGpsProvider:").append(r0z).toString());
                                this.f.onLocationGot((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), r4i, r5_String, r6_String, true);
                            } else if (this.b || r4i != 0) {
                                if (this.c || r4i != 1) {
                                } else {
                                    this.c = true;
                                    Log.v("MicroMsg.LBSManager", new StringBuilder("report location by Network ok:[").append(r3_Location.getLatitude()).append(" , ").append(r3_Location.getLongitude()).append("]  accuracy:").append(r3_Location.getAccuracy()).append("  retry count:").append(this.d).append(" isGpsProvider:").append(r0z).toString());
                                    this.f.onLocationGot((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), MM_SOURCE_REPORT_NETWORK, r5_String, r6_String, true);
                                }
                            } else {
                                this.b = true;
                                Log.v("MicroMsg.LBSManager", new StringBuilder("report location by GPS ok:[").append(r3_Location.getLatitude()).append(" , ").append(r3_Location.getLongitude()).append("]  accuracy:").append(r3_Location.getAccuracy()).append("  retry count:").append(this.d).append(" isGpsProvider:").append(r0z).toString());
                                this.f.onLocationGot((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), MM_SOURCE_REPORT_HARWARE, r5_String, r6_String, true);
                            }
                        }
                    }
                }
            }
            r4i = 1;
            setLocationCache((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), r4i);
            if (this.f == null) {
            } else if (this.a || this.b || this.c) {
                r5_String = Util.nullAsNil(getWIFILocation());
                r6_String = Util.nullAsNil(getTelLocation());
                if (this.a) {
                    if (this.b || r4i != 0) {
                        if (this.c || r4i != 1) {
                        } else {
                            this.c = true;
                            Log.v("MicroMsg.LBSManager", new StringBuilder("report location by Network ok:[").append(r3_Location.getLatitude()).append(" , ").append(r3_Location.getLongitude()).append("]  accuracy:").append(r3_Location.getAccuracy()).append("  retry count:").append(this.d).append(" isGpsProvider:").append(r0z).toString());
                            this.f.onLocationGot((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), MM_SOURCE_REPORT_NETWORK, r5_String, r6_String, true);
                        }
                    } else {
                        this.b = true;
                        Log.v("MicroMsg.LBSManager", new StringBuilder("report location by GPS ok:[").append(r3_Location.getLatitude()).append(" , ").append(r3_Location.getLongitude()).append("]  accuracy:").append(r3_Location.getAccuracy()).append("  retry count:").append(this.d).append(" isGpsProvider:").append(r0z).toString());
                        this.f.onLocationGot((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), MM_SOURCE_REPORT_HARWARE, r5_String, r6_String, true);
                    }
                } else {
                    b();
                    this.a = true;
                    Log.v("MicroMsg.LBSManager", new StringBuilder("location by provider ok:[").append(r3_Location.getLatitude()).append(" , ").append(r3_Location.getLongitude()).append("]  accuracy:").append(r3_Location.getAccuracy()).append("  retry count:").append(this.d).append(" isGpsProvider:").append(r0z).toString());
                    this.f.onLocationGot((float) r3_Location.getLatitude(), (float) r3_Location.getLongitude(), (int) r3_Location.getAccuracy(), r4i, r5_String, r6_String, true);
                }
            }
        }
    }

    public void removeGpsUpdate() {
        Log.v("MicroMsg.LBSManager", "removed gps update");
        if (this.g != null) {
            this.g.removeUpdates(this.i);
        }
        try {
            this.h.unregisterReceiver(this);
        } catch (Exception e) {
            Log.v("MicroMsg.LBSManager", "location receiver has already unregistered");
        }
    }

    public void removeListener() {
        Log.v("MicroMsg.LBSManager", "removed gps update on destroy");
        removeGpsUpdate();
        if (this.k != null) {
            b();
        }
        this.f = null;
        this.h = null;
        this.k = null;
        this.g = null;
    }

    public void requestGpsUpdate() {
        if (isGpsEnable() || isNetworkPrividerEnable()) {
            Log.v("MicroMsg.LBSManager", "requested gps update");
            IntentFilter r0_IntentFilter = new IntentFilter();
            r0_IntentFilter.addAction(FILTER_GPS);
            this.h.registerReceiver(this, r0_IntentFilter);
            if (isGpsEnable()) {
                this.g.requestLocationUpdates(LocationManagerProxy.GPS_PROVIDER, 500, 0.0f, this.i);
            }
            if (isNetworkPrividerEnable()) {
                this.g.requestLocationUpdates(LocationManagerProxy.NETWORK_PROVIDER, 500, 0.0f, this.i);
            }
        }
    }

    public void start() {
        int r0i;
        String r5_String = Util.nullAsNil(getWIFILocation());
        String r6_String = Util.nullAsNil(getTelLocation());
        r0i = (isGpsEnable() || isNetworkPrividerEnable()) ? 1 : 0;
        if (r0i == 0 || this.j) {
            if (e == null) {
                r0i = 0;
            } else {
                r0i = (((System.currentTimeMillis() - e.d) > 180000 ? 1 : ((System.currentTimeMillis() - e.d) == 180000? 0 : -1)) > 0 || e.c <= 0) ? 0 : 1;
                if (this.f == null) {
                    this.a = true;
                    Log.v("MicroMsg.LBSManager", new StringBuilder("location by GPS cache ok:[").append(e.a).append(" , ").append(e.b).append("]  accuracy:").append(e.c).append(" source:").append(e.e).toString());
                    this.f.onLocationGot(e.a, e.b, e.c, e.e, r5_String, r6_String, true);
                }
            }
            if (r0i != 0) {
                if (this.f == null) {
                } else {
                    this.a = true;
                    Log.v("MicroMsg.LBSManager", new StringBuilder("location by GPS cache ok:[").append(e.a).append(" , ").append(e.b).append("]  accuracy:").append(e.c).append(" source:").append(e.e).toString());
                    this.f.onLocationGot(e.a, e.b, e.c, e.e, r5_String, r6_String, true);
                }
            } else {
                this.a = true;
                if (r5_String.equals(RContactStorage.PRIMARY_KEY) && r6_String.equals(RContactStorage.PRIMARY_KEY)) {
                    Log.v("MicroMsg.LBSManager", "get location by network failed");
                    if (this.f != null) {
                        this.f.onLocationGot(INVALID_LNG, -1000.0f, INVALID_ACC, MM_SOURCE_HARDWARE, RContactStorage.PRIMARY_KEY, RContactStorage.PRIMARY_KEY, false);
                    }
                } else {
                    Log.v("MicroMsg.LBSManager", new StringBuilder("get location by network ok, macs : ").append(r5_String).append(" cell ids :").append(r6_String).toString());
                    if (this.f != null) {
                        this.f.onLocationGot(INVALID_LNG, -1000.0f, INVALID_ACC, MM_SOURCE_HARDWARE, r5_String, r6_String, true);
                    }
                }
            }
        } else {
            this.j = true;
            this.d = 0;
            requestGpsUpdate();
            this.k.startTimer(3000);
        }
    }
}