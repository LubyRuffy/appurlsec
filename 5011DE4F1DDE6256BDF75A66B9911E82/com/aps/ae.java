package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.GpsStatus.NmeaListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.amap.api.location.LocationManagerProxy;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TreeMap;
import org.apache.cordova.NetworkManager;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public final class ae {
    private static int E;
    private static ae u;
    private Timer A;
    private Thread B;
    private Thread C;
    private Looper D;
    private Context a;
    private TelephonyManager b;
    private LocationManager c;
    private WifiManager d;
    private String e;
    private String f;
    private String g;
    private boolean h;
    private int i;
    private boolean j;
    private long k;
    private String l;
    private String m;
    private int n;
    private int o;
    private int p;
    private String q;
    private long r;
    private long s;
    private boolean t;
    private ac v;
    private ad w;
    private CellLocation x;
    private af y;
    private List z;

    static {
        u = null;
        E = 10000;
    }

    private ae(Context r9_Context) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = RContactStorage.PRIMARY_KEY;
        this.f = RContactStorage.PRIMARY_KEY;
        this.g = RContactStorage.PRIMARY_KEY;
        this.h = false;
        this.i = 0;
        this.j = false;
        this.k = -1;
        this.l = RContactStorage.PRIMARY_KEY;
        this.m = RContactStorage.PRIMARY_KEY;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = RContactStorage.PRIMARY_KEY;
        this.r = 0;
        this.s = 0;
        this.t = true;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = new ArrayList();
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        if (r9_Context == null) {
        } else {
            this.a = r9_Context;
            this.e = Build.MODEL;
            this.b = (TelephonyManager) r9_Context.getSystemService("phone");
            this.c = (LocationManager) r9_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
            this.d = (WifiManager) r9_Context.getSystemService(NetworkManager.WIFI);
            if (this.b == null || this.d == null) {
            } else {
                String[] r0_StringA;
                this.f = this.b.getDeviceId();
                this.g = this.b.getSubscriberId();
                if (this.d.getConnectionInfo() != null) {
                    this.m = this.d.getConnectionInfo().getMacAddress();
                    if (this.m == null || this.m.length() <= 0) {
                        r0_StringA = b(this.b);
                        this.n = Integer.parseInt(r0_StringA[0]);
                        this.o = Integer.parseInt(r0_StringA[1]);
                        this.p = this.b.getNetworkType();
                        this.q = r9_Context.getPackageName();
                    } else {
                        this.m = this.m.replace(":", RContactStorage.PRIMARY_KEY);
                    }
                    this.h = this.b.getPhoneType() != XListViewHeader.STATE_REFRESHING;
                }
                r0_StringA = b(this.b);
                this.n = Integer.parseInt(r0_StringA[0]);
                this.o = Integer.parseInt(r0_StringA[1]);
                this.p = this.b.getNetworkType();
                this.q = r9_Context.getPackageName();
                if (this.b.getPhoneType() != XListViewHeader.STATE_REFRESHING) {
                }
                this.h = this.b.getPhoneType() != XListViewHeader.STATE_REFRESHING;
            }
        }
    }

    protected static ae a(Context r3_Context) {
        if (u != null || !c(r3_Context)) {
            return u;
        }
        int r0i;
        LocationManager r0_LocationManager = (LocationManager) r3_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        if (r0_LocationManager != null) {
            Iterator r1_Iterator = r0_LocationManager.getAllProviders().iterator();
            while (r1_Iterator.hasNext()) {
                String r0_String = (String) r1_Iterator.next();
                if (!r0_String.equals("passive")) {
                    if (r0_String.equals(LocationManagerProxy.GPS_PROVIDER)) {
                    }
                }
                r0i = 1;
                break;
                break;
            }
            r0i = 0;
        } else {
            r0i = 0;
        }
        if (r0i != 0) {
            u = new ae(r3_Context);
        }
        return u;
    }

    private void a(BroadcastReceiver r3_BroadcastReceiver) {
        if (r3_BroadcastReceiver == null || this.a == null) {
        } else {
            IntentFilter r0_IntentFilter = new IntentFilter();
            r0_IntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            this.a.registerReceiver(r3_BroadcastReceiver, r0_IntentFilter);
        }
    }

    static /* synthetic */ void a(ae r1_ae, NmeaListener r2_NmeaListener) {
        if (r1_ae.c == null || r2_NmeaListener == null) {
        } else {
            r1_ae.c.addNmeaListener(r2_NmeaListener);
        }
    }

    static /* synthetic */ void a(ae r2_ae, PhoneStateListener r3_PhoneStateListener) {
        if (r2_ae.b != null) {
            r2_ae.b.listen(r3_PhoneStateListener, 273);
        }
    }

    static /* synthetic */ void a(ae r4_ae, List r5_List) {
        if (r5_List == null || r5_List.size() <= 0) {
        } else {
            HashMap r2_HashMap = new HashMap();
            int r1i = 0;
            while (r1i < r5_List.size()) {
                ScanResult r0_ScanResult = (ScanResult) r5_List.get(r1i);
                if (r0_ScanResult.SSID == null) {
                    r0_ScanResult.SSID = "null";
                }
                r2_HashMap.put(Integer.valueOf(r0_ScanResult.level), r0_ScanResult);
                r1i++;
            }
            TreeMap r1_TreeMap = new TreeMap(Collections.reverseOrder());
            r1_TreeMap.putAll(r2_HashMap);
            r5_List.clear();
            Iterator r3_Iterator = r1_TreeMap.keySet().iterator();
            while (r3_Iterator.hasNext()) {
                r5_List.add(r1_TreeMap.get((Integer) r3_Iterator.next()));
            }
            r2_HashMap.clear();
            r1_TreeMap.clear();
        }
    }

    private void b(BroadcastReceiver r2_BroadcastReceiver) {
        if (r2_BroadcastReceiver == null || this.a == null) {
        } else {
            this.a.unregisterReceiver(r2_BroadcastReceiver);
        }
    }

    protected static boolean b(Context r11_Context) {
        if (r11_Context == null) {
            return false;
        }
        boolean r1z;
        if (Secure.getString(r11_Context.getContentResolver(), "mock_location").equals("0")) {
            r1z = false;
        } else {
            PackageManager r4_PackageManager = r11_Context.getPackageManager();
            List r0_List = r4_PackageManager.getInstalledApplications(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            String r5_String = "android.permission.ACCESS_MOCK_LOCATION";
            String r6_String = r11_Context.getPackageName();
            Iterator r7_Iterator = r0_List.iterator();
            r1z = false;
            while (r7_Iterator.hasNext()) {
                ApplicationInfo r0_ApplicationInfo = (ApplicationInfo) r7_Iterator.next();
                if (r1z) {
                    break;
                } else {
                    boolean r0z;
                    try {
                        String[] r8_StringA = r4_PackageManager.getPackageInfo(r0_ApplicationInfo.packageName, LVBuffer.LENGTH_ALLOC_PER_NEW).requestedPermissions;
                        if (r8_StringA != null) {
                            int r9i = r8_StringA.length;
                            int r3i = 0;
                            while (r3i < r9i) {
                                if (r8_StringA[r3i].equals(r5_String)) {
                                    if (!r0_ApplicationInfo.packageName.equals(r6_String)) {
                                        r0z = true;
                                        r1z = r0z;
                                        break;
                                    }
                                } else {
                                    r3i++;
                                }
                            }
                        }
                    } catch (Exception e) {
                        r0z = r1z;
                    }
                }
            }
        }
        return r1z;
    }

    private static String[] b(TelephonyManager r7_TelephonyManager) {
        int r1i = 0;
        CharSequence r0_CharSequence = null;
        if (r7_TelephonyManager != null) {
            r0_CharSequence = r7_TelephonyManager.getNetworkOperator();
        }
        String[] r2_StringA = new String[2];
        r2_StringA[0] = "0";
        r2_StringA[1] = "0";
        if ((!TextUtils.isDigitsOnly(r0_CharSequence)) || r0_CharSequence.length() <= 4) {
            return r2_StringA;
        }
        r2_StringA[0] = r0_CharSequence.substring(0, XListViewFooter.STATE_NOMORE);
        char[] r3_charA = r0_CharSequence.substring(XListViewFooter.STATE_NOMORE).toCharArray();
        while (r1i < r3_charA.length && Character.isDigit(r3_charA[r1i])) {
            r1i++;
        }
        r2_StringA[1] = r0_CharSequence.substring(XListViewFooter.STATE_NOMORE, r1i + 3);
        return r2_StringA;
    }

    private static boolean c(Context r4_Context) {
        try {
            String[] r2_StringA = r4_Context.getPackageManager().getPackageInfo(r4_Context.getPackageName(), LVBuffer.LENGTH_ALLOC_PER_NEW).requestedPermissions;
            int r0i = 0;
            while (r0i < ay.a.length) {
                if (!ay.a(r2_StringA, ay.a[r0i])) {
                    return false;
                }
                r0i++;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private void z() {
        if (this.d == null) {
        } else {
            this.d.startScan();
        }
    }

    protected final void a() {
        String r0_String = RContactStorage.PRIMARY_KEY;
        b();
        if (this.D != null) {
            this.D.quit();
            this.D = null;
        }
        if (this.C != null) {
            this.C.interrupt();
            this.C = null;
        }
        this.C = new y(this, r0_String);
        this.C.start();
    }

    protected final void b() {
        if (this.v != null) {
            PhoneStateListener r0_PhoneStateListener = this.v;
            if (this.b != null) {
                this.b.listen(r0_PhoneStateListener, 0);
            }
            this.v = null;
        }
        if (this.B != null) {
            this.t = false;
            this.B.interrupt();
            this.B = null;
        }
        if (this.w != null) {
            NmeaListener r0_NmeaListener = this.w;
            if (this.c == null || r0_NmeaListener == null) {
                this.w = null;
            } else {
                this.c.removeNmeaListener(r0_NmeaListener);
                this.w = null;
            }
        }
        if (this.y != null) {
            b(this.y);
            this.y = null;
        }
        if (this.A != null) {
            this.A.cancel();
            this.A = null;
        }
        if (this.D != null) {
            this.D.quit();
            this.D = null;
        }
        if (this.C != null) {
            this.C.interrupt();
            this.C = null;
        }
    }

    protected final boolean c() {
        if (this.b != null && this.b.getSimState() == 5 && this.j) {
            return true;
        }
        if (this.b != null) {
            CellLocation r1_CellLocation = this.b.getCellLocation();
            if (r1_CellLocation != null) {
                this.s = System.currentTimeMillis();
                this.x = r1_CellLocation;
                return true;
            }
        }
        return false;
    }

    protected final boolean d() {
        return this.d != null && this.d.isWifiEnabled();
    }

    protected final boolean e() {
        return this.c != null && this.c.isProviderEnabled(LocationManagerProxy.GPS_PROVIDER);
    }

    protected final String f() {
        if (this.e == null) {
            this.e = Build.MODEL;
        }
        return this.e != null ? this.e : RContactStorage.PRIMARY_KEY;
    }

    protected final String g() {
        if (this.f != null || this.a == null) {
            return this.f == null ? this.f : RContactStorage.PRIMARY_KEY;
        } else {
            this.b = (TelephonyManager) this.a.getSystemService("phone");
            if (this.b != null) {
                this.f = this.b.getDeviceId();
            }
            if (this.f == null) {
            }
        }
    }

    protected final String h() {
        if (this.g != null || this.a == null) {
            return this.g == null ? this.g : RContactStorage.PRIMARY_KEY;
        } else {
            this.b = (TelephonyManager) this.a.getSystemService("phone");
            if (this.b != null) {
                this.g = this.b.getSubscriberId();
            }
            if (this.g == null) {
            }
        }
    }

    protected final boolean i() {
        return this.h;
    }

    protected final List j() {
        if (System.getInt(this.a.getContentResolver(), "airplane_mode_on", 0) == 1) {
            return new ArrayList();
        }
        if (!c()) {
            return new ArrayList();
        }
        List r0_List = new ArrayList();
        r0_List.add(Long.valueOf(this.s));
        r0_List.add(this.x);
        return r0_List;
    }

    protected final List k() {
        int r1i = 0;
        List r3_List = new ArrayList();
        if (!d()) {
            return new ArrayList();
        }
        List r0_List = new ArrayList();
        synchronized (this) {
            if ((((System.currentTimeMillis() - this.r) > 3500 ? 1 : ((System.currentTimeMillis() - this.r) == 3500? 0 : -1)) < 0 ? 1 : 0) != 0) {
                r0_List.add(Long.valueOf(this.r));
                while (r1i < this.z.size()) {
                    r3_List.add(this.z.get(r1i));
                    r1i++;
                }
                r0_List.add(r3_List);
            }
        }
        return r0_List;
    }

    protected final byte l() {
        return c() ? (byte) this.i : (byte) -128;
    }

    protected final List m() {
        List r2_List = new ArrayList();
        if (this.b == null) {
            return r2_List;
        }
        if (!c()) {
            return r2_List;
        }
        Iterator r3_Iterator = this.b.getNeighboringCellInfo().iterator();
        int r1i = 0;
        while (r3_Iterator.hasNext()) {
            NeighboringCellInfo r0_NeighboringCellInfo = (NeighboringCellInfo) r3_Iterator.next();
            if (r1i <= 15) {
                if (r0_NeighboringCellInfo.getLac() == 0 || r0_NeighboringCellInfo.getLac() == 65535 || r0_NeighboringCellInfo.getCid() == 65535 || r0_NeighboringCellInfo.getCid() == 268435455) {
                } else {
                    r2_List.add(r0_NeighboringCellInfo);
                    r1i++;
                }
            } else {
                break;
            }
        }
        return r2_List;
    }

    protected final List n() {
        long r0j;
        String r2_String;
        List r3_List = new ArrayList();
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (e()) {
            r0j = this.k;
            r2_String = this.l;
        } else {
            r0j = -1;
            r2_String = r0_String;
        }
        if (r0j <= 0) {
            r0j = System.currentTimeMillis() / 1000;
        }
        if (r0j > 2147483647L) {
            r0j /= 1000;
        }
        r3_List.add(Long.valueOf(r0j));
        r3_List.add(r2_String);
        return r3_List;
    }

    protected final long o() {
        long r0j = 0;
        long r2j = this.k;
        if (r2j <= 0) {
            return r0j;
        }
        r0j = r2j;
        int r2i = String.valueOf(r2j).length();
        while (r2i != 13) {
            r0j = r2i > 13 ? r0j / 10 : r0j * 10;
            r2i = String.valueOf(r0j).length();
        }
        return r0j;
    }

    protected final String p() {
        if (this.m != null || this.a == null) {
            return this.m == null ? this.m : RContactStorage.PRIMARY_KEY;
        } else {
            this.d = (WifiManager) this.a.getSystemService(NetworkManager.WIFI);
            if (this.d == null || this.d.getConnectionInfo() == null) {
                if (this.m == null) {
                }
            } else {
                this.m = this.d.getConnectionInfo().getMacAddress();
                if (this.m == null || this.m.length() <= 0) {
                    if (this.m == null) {
                    }
                } else {
                    this.m = this.m.replace(":", RContactStorage.PRIMARY_KEY);
                    if (this.m == null) {
                    }
                }
            }
        }
    }

    protected final int q() {
        return this.n;
    }

    protected final int r() {
        return this.o;
    }

    protected final int s() {
        return this.p;
    }

    protected final String t() {
        if (this.q != null || this.a == null) {
            return this.q == null ? this.q : RContactStorage.PRIMARY_KEY;
        } else {
            this.q = this.a.getPackageName();
            if (this.q == null) {
            }
        }
    }

    protected final List u() {
        List r2_List = new ArrayList();
        if (c()) {
            CellLocation r0_CellLocation = (CellLocation) j().get(1);
            if (r0_CellLocation == null || (!r0_CellLocation instanceof GsmCellLocation)) {
                return r2_List;
            }
            r2_List.add(Integer.valueOf(((GsmCellLocation) r0_CellLocation).getLac()));
            r2_List.add(Integer.valueOf(((GsmCellLocation) r0_CellLocation).getCid()));
        }
        return r2_List;
    }

    protected final List v() {
        List r1_List = new ArrayList();
        if (c()) {
            CellLocation r0_CellLocation = (CellLocation) j().get(1);
            if (r0_CellLocation == null || (!r0_CellLocation instanceof CdmaCellLocation)) {
                return r1_List;
            }
            CdmaCellLocation r0_CdmaCellLocation = (CdmaCellLocation) r0_CellLocation;
            r1_List.add(Integer.valueOf(r0_CdmaCellLocation.getSystemId()));
            r1_List.add(Integer.valueOf(r0_CdmaCellLocation.getNetworkId()));
            r1_List.add(Integer.valueOf(r0_CdmaCellLocation.getBaseStationId()));
            r1_List.add(Integer.valueOf(r0_CdmaCellLocation.getBaseStationLongitude()));
            r1_List.add(Integer.valueOf(r0_CdmaCellLocation.getBaseStationLatitude()));
        }
        return r1_List;
    }

    protected final List w() {
        int r2i = 0;
        List r3_List = new ArrayList();
        if (d()) {
            List r1_List = k();
            List r0_List = (List) r1_List.get(1);
            r3_List.add(Long.valueOf(((Long) r1_List.get(0)).longValue()));
            if (r0_List == null || r0_List.size() <= 0) {
                return r3_List;
            }
            while (r2i < r0_List.size()) {
                ScanResult r1_ScanResult = (ScanResult) r0_List.get(r2i);
                if (r3_List.size() - 1 < 40) {
                    if (r1_ScanResult != null) {
                        List r4_List = new ArrayList();
                        r4_List.add(r1_ScanResult.BSSID.replace(":", RContactStorage.PRIMARY_KEY));
                        r4_List.add(Integer.valueOf(r1_ScanResult.level));
                        r4_List.add(r1_ScanResult.SSID);
                        r3_List.add(r4_List);
                    }
                    r2i++;
                }
            }
        }
        return r3_List;
    }

    public final Context x() {
        return this.a;
    }
}