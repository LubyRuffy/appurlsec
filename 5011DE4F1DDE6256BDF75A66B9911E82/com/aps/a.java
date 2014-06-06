package com.aps;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.core.c;
import com.qiubai.library.adview.util.AdViewUtil;
import com.qq.e.comm.DownloadService;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.mm.sdk.message.RMsgInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.apache.cordova.NetworkManager;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.activity.OneProfileActivity;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: APS.java
public class a implements i {
    private static a e;
    private long A;
    private j B;
    private int C;
    private String D;
    private t E;
    private aa F;
    private StringBuilder G;
    private long H;
    private long I;
    private CellLocation J;
    private boolean K;
    TimerTask a;
    Timer b;
    aa c;
    int d;
    private Context f;
    private int g;
    private ConnectivityManager h;
    private WifiManager i;
    private TelephonyManager j;
    private List<e> k;
    private List<ScanResult> l;
    private Map<PendingIntent, List<h>> m;
    private b n;
    private PhoneStateListener o;
    private int p;
    private a q;
    private WifiInfo r;
    private JSONObject s;
    private String t;
    private c u;
    private long v;
    private boolean w;
    private boolean x;
    private long y;
    private long z;

    // compiled from: APS.java
    private class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context r9_Context, Intent r10_Intent) {
            if (r10_Intent == null) {
            } else {
                String r0_String = r10_Intent.getAction();
                if (r0_String.equals("android.net.wifi.SCAN_RESULTS")) {
                    if (a.this.i != null) {
                        a.this.l = a.this.i.getScanResults();
                        if (a.this.l == null) {
                            a.this.l = new ArrayList();
                        }
                    }
                } else if (r0_String.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    if (a.this.i != null) {
                        r0i = XListViewFooter.STATE_NODATA;
                        try {
                            r0i = a.this.i.getWifiState();
                        } catch (SecurityException e) {
                        }
                        switch (r0i) {
                            case XListViewHeader.STATE_NORMAL:
                                a.this.o();
                                break;
                            case XListViewHeader.STATE_READY:
                                a.this.o();
                                break;
                            case XListViewFooter.STATE_NODATA:
                                a.this.o();
                                break;
                        }
                    }
                } else if (r0_String.equals("android.intent.action.SCREEN_ON")) {
                    CellLocation.requestLocationUpdate();
                    a.this.p();
                    f.i = 15000;
                    f.j = 30000;
                } else if (r0_String.equals("android.intent.action.SCREEN_OFF")) {
                    if (a.this.C >= 5) {
                        f.i = 30000;
                        f.j = 60000;
                    }
                } else if (r0_String.equals("android.intent.action.AIRPLANE_MODE")) {
                    a.this.w = o.a(r9_Context);
                } else if (r0_String.equals("android.intent.action.BATTERY_CHANGED")) {
                    r0i = r10_Intent.getIntExtra("level", 0);
                    int r1i = r10_Intent.getIntExtra("scale", 100);
                    int r2i = r10_Intent.getIntExtra(RMsgInfo.COL_STATUS, 0);
                    r0i = (r0i * 100) / r1i;
                    Object[] r1_ObjectA = new Object[3];
                    r1_ObjectA[0] = "batt is ";
                    r1_ObjectA[1] = Integer.valueOf(r0i);
                    r1_ObjectA[2] = "%";
                    o.a(r1_ObjectA);
                    switch (r2i) {
                        case XListViewFooter.STATE_NODATA:
                            if (r0i < 15) {
                                if (a.this.C >= 5) {
                                    a.this.x = false;
                                } else {
                                    a.this.x = false;
                                }
                            } else {
                                a.this.x = true;
                            }
                    }
                    a.this.x = true;
                } else if (r0_String.equals("android.location.GPS_FIX_CHANGE")) {
                    a.this.d();
                } else if (r0_String.equals("android.net.conn.CONNECTIVITY_CHANGE") && a.this.n()) {
                    a.this.a(true, (int)XListViewHeader.STATE_REFRESHING);
                }
            }
        }
    }

    static {
        e = null;
    }

    private a() {
        this.f = null;
        this.g = 9;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.m = new HashMap();
        this.n = new b();
        this.o = null;
        this.p = -113;
        this.q = new a(null);
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = 0;
        this.w = false;
        this.x = true;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = j.a();
        this.C = 0;
        this.D = "00:00:00:00:00:00";
        this.E = null;
        this.F = null;
        this.G = new StringBuilder();
        this.H = 0;
        this.I = 0;
        this.J = null;
        this.K = false;
        this.d = 0;
    }

    private c a_(byte[] r9_byteA, boolean r10z) throws Exception {
        if (this.f == null) {
            return null;
        }
        c r0_c = new c();
        k r2_k = new k();
        String r0_String = this.B.a(r9_byteA, this.f, this.s);
        String r3_String = RContactStorage.PRIMARY_KEY;
        String[] r3_StringA = j.a(this.s);
        if (r0_String == null || r0_String.indexOf("<saps>") == -1) {
            Object[] r3_ObjectA;
            if (r3_StringA[0].equals("true")) {
                r3_ObjectA = new Object[1];
                r3_ObjectA[0] = "api return pure";
                o.a(r3_ObjectA);
                r0_c = r2_k.b(r0_String);
            } else {
                r3_ObjectA = new Object[1];
                r3_ObjectA[0] = "aps return pure";
                o.a(r3_ObjectA);
                r0_c = r2_k.b(r0_String);
            }
        } else {
            r0_String = this.n.a(r2_k.a(r0_String), "GBK");
            r0_c = r2_k.b(r0_String);
        }
        if (this.E == null || r0_c == null) {
            if (!o.a(r0_c)) {
                return null;
            }
            if (r0_c.l() == null) {
                return r0_c;
            }
            return r0_c;
        } else {
            JSONObject r2_JSONObject = new JSONObject();
            try {
                JSONObject r3_JSONObject = r0_c.l();
                String r4_String = r3_JSONObject.get("eab").toString();
                r2_JSONObject.put("e", r4_String);
                r2_JSONObject.put("d", r3_JSONObject.get("ctl"));
                r2_JSONObject.put("u", r3_JSONObject.get(AdViewUtil.COUNTSUCCESS));
                this.E.a(this.F, r2_JSONObject.toString());
                if (r4_String != null) {
                    if (r4_String.equals("0")) {
                        this.E.c();
                        this.E = null;
                        this.K = false;
                    } else if (r4_String.equals("1")) {
                        if (this.E == null) {
                            d();
                        }
                    }
                }
            } catch (JSONException e) {
            } catch (Exception e_2) {
            }
            if (!o.a(r0_c)) {
                return null;
            }
            if (r0_c.l() == null) {
                return r0_c;
            }
            return r0_c;
        }
    }

    private e a_(NeighboringCellInfo r5_NeighboringCellInfo) {
        if (o.b() < 5) {
            return null;
        }
        try {
            e r1_e = new e();
            String[] r2_StringA = o.a(this.j);
            r1_e.a = r2_StringA[0];
            r1_e.b = r2_StringA[1];
            r1_e.c = l.a(r5_NeighboringCellInfo, "getLac", new Object[0]);
            r1_e.d = r5_NeighboringCellInfo.getCid();
            r1_e.j = o.a(r5_NeighboringCellInfo.getRssi());
            return r1_e;
        } catch (Exception e) {
            return null;
        }
    }

    static synchronized i a_() {
        i r0_i;
        synchronized (a.class) {
            if (e == null) {
                e = new a();
            }
            r0_i = e;
        }
        return r0_i;
    }

    private String a_(int r3i, int r4i, int r5i) throws Exception {
        JSONObject r0_JSONObject = new JSONObject();
        r0_JSONObject.put("e", r3i);
        r0_JSONObject.put("d", r4i);
        r0_JSONObject.put("u", r5i);
        return r0_JSONObject.toString();
    }

    private void a_(CellLocation r2_CellLocation) {
        if (r2_CellLocation != null || this.w || this.j == null) {
            if (r2_CellLocation != null) {
            } else {
                switch (o.a(r2_CellLocation, this.f)) {
                    case XListViewHeader.STATE_READY:
                        if (r2_CellLocation == null || this.j == null) {
                        } else {
                            c(r2_CellLocation);
                        }
                    case XListViewHeader.STATE_REFRESHING:
                        if (r2_CellLocation == null) {
                            d(r2_CellLocation);
                        }
                }
            }
        } else {
            r2_CellLocation = this.j.getCellLocation();
            if (r2_CellLocation != null) {
                switch (o.a(r2_CellLocation, this.f)) {
                    case XListViewHeader.STATE_READY:
                        if (r2_CellLocation == null || this.j == null) {
                        } else {
                            c(r2_CellLocation);
                        }
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        if (r2_CellLocation == null) {
                        } else {
                            d(r2_CellLocation);
                        }
                        break;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a_(StringBuilder r8_StringBuilder) {
        /*
        r7_this = this;
        r0 = 0;
        r6 = -1;
        if (r8 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r1 = 23;
        r1 = new java.lang.String[r1];
        r2 = " phnum=\"\"";
        r1[r0] = r2;
        r2 = 1;
        r3 = " nettype=\"\"";
        r1[r2] = r3;
        r2 = 2;
        r3 = " nettype=\"UNKNOWN\"";
        r1[r2] = r3;
        r2 = 3;
        r3 = " inftype=\"\"";
        r1[r2] = r3;
        r2 = 4;
        r3 = "<macs><![CDATA[]]></macs>";
        r1[r2] = r3;
        r2 = 5;
        r3 = "<nb></nb>";
        r1[r2] = r3;
        r2 = 6;
        r3 = "<mmac><![CDATA[]]></mmac>";
        r1[r2] = r3;
        r2 = 7;
        r3 = " gtype=\"0\"";
        r1[r2] = r3;
        r2 = 8;
        r3 = " glong=\"0.0\"";
        r1[r2] = r3;
        r2 = 9;
        r3 = " glat=\"0.0\"";
        r1[r2] = r3;
        r2 = 10;
        r3 = " precision=\"0.0\"";
        r1[r2] = r3;
        r2 = 11;
        r3 = " glong=\"0\"";
        r1[r2] = r3;
        r2 = 12;
        r3 = " glat=\"0\"";
        r1[r2] = r3;
        r2 = 13;
        r3 = " precision=\"0\"";
        r1[r2] = r3;
        r2 = 14;
        r3 = "<smac>null</smac>";
        r1[r2] = r3;
        r2 = 15;
        r3 = "<smac>00:00:00:00:00:00</smac>";
        r1[r2] = r3;
        r2 = 16;
        r3 = "<imei>000000000000000</imei>";
        r1[r2] = r3;
        r2 = 17;
        r3 = "<imsi>000000000000000</imsi>";
        r1[r2] = r3;
        r2 = 18;
        r3 = "<mcc>000</mcc>";
        r1[r2] = r3;
        r2 = 19;
        r3 = "<mcc>0</mcc>";
        r1[r2] = r3;
        r2 = 20;
        r3 = "<lac>0</lac>";
        r1[r2] = r3;
        r2 = 21;
        r3 = "<cellid>0</cellid>";
        r1[r2] = r3;
        r2 = 22;
        r3 = "<key></key>";
        r1[r2] = r3;
        r2 = r1.length;
    L_0x008b:
        if (r0 >= r2) goto L_0x00a5;
    L_0x008d:
        r3 = r1[r0];
    L_0x008f:
        r4 = r8.indexOf(r3);
        if (r4 == r6) goto L_0x00a2;
    L_0x0095:
        r4 = r8.indexOf(r3);
        r5 = r3.length();
        r5 = r5 + r4;
        r8.delete(r4, r5);
        goto L_0x008f;
    L_0x00a2:
        r0 = r0 + 1;
        goto L_0x008b;
    L_0x00a5:
        r0 = "*<";
        r0 = r8.indexOf(r0);
        if (r0 == r6) goto L_0x0004;
    L_0x00ad:
        r0 = "*<";
        r0 = r8.indexOf(r0);
        r8.deleteCharAt(r0);
        goto L_0x00a5;
        */

    }

    private synchronized void a_(List<ScanResult> r7_List_ScanResult) {
        if (r7_List_ScanResult != null) {
            if (r7_List_ScanResult.size() < 1) {
            } else {
                HashMap r2_HashMap = new HashMap();
                int r1i = 0;
                while (r1i < r7_List_ScanResult.size()) {
                    ScanResult r0_ScanResult = (ScanResult) r7_List_ScanResult.get(r1i);
                    if (a(r0_ScanResult.level)) {
                        if (r0_ScanResult.SSID != null) {
                            r0_ScanResult.SSID = r0_ScanResult.SSID.replace("*", ".");
                        } else {
                            r0_ScanResult.SSID = "null";
                        }
                        r2_HashMap.put(Integer.valueOf(r0_ScanResult.level * 30 + r1i), r0_ScanResult);
                        r1i++;
                    } else {
                        r1i++;
                    }
                }
                TreeMap r0_TreeMap = new TreeMap(Collections.reverseOrder());
                r0_TreeMap.putAll(r2_HashMap);
                r7_List_ScanResult.clear();
                Iterator r1_Iterator = r0_TreeMap.keySet().iterator();
                while (r1_Iterator.hasNext()) {
                    r7_List_ScanResult.add(r0_TreeMap.get(r1_Iterator.next()));
                    if (r7_List_ScanResult.size() > 29) {
                        break;
                    }
                }
                r2_HashMap.clear();
                r0_TreeMap.clear();
            }
        }
    }

    private boolean a_(int r4i) {
        int r1i = OneProfileActivity.REQ_CODE_SHARE;
        try {
            r1i = WifiManager.calculateSignalLevel(r4i, OneProfileActivity.REQ_CODE_SHARE);
        } catch (ArithmeticException e) {
            o.a(e);
        }
        return r1i >= 1;
    }

    private boolean a_(long r8j) {
        long r3j = o.a();
        if (r3j - r8j >= 300) {
            return false;
        }
        long r1j = 0;
        if (this.u != null) {
            r1j = r3j - this.u.f();
        }
        return (r1j > 10000 ? 1 : (r1j == 10000? 0 : -1)) <= 0;
    }

    private boolean a_(WifiInfo r5_WifiInfo) {
        return r5_WifiInfo != null && r5_WifiInfo.getBSSID() != null && r5_WifiInfo.getSSID() != null && !r5_WifiInfo.getBSSID().equals("00:00:00:00:00:00") && !TextUtils.isEmpty(r5_WifiInfo.getSSID());
    }

    private boolean a_(c r8_c, c r9_c) {
        if (r8_c == null || r9_c == null) {
            return true;
        }
        double[] r2_doubleA = new double[4];
        r2_doubleA[0] = r8_c.d();
        r2_doubleA[1] = r8_c.c();
        r2_doubleA[2] = r9_c.d();
        r2_doubleA[3] = r9_c.c();
        return (o.a(r2_doubleA) > 20.0f ? 1 : (o.a(r2_doubleA) == 20.0f? 0 : -1)) > 0 || Math.abs(r8_c.e() - r9_c.e()) > 20.0f;
    }

    private synchronized byte[] a_(Object r18_Object) {
        m r7_m;
        String r6_String;
        NetworkInfo r5_NetworkInfo;
        r7_m = new m();
        this.G.delete(0, this.G.length());
        String r5_String = "0";
        String r8_String = "0";
        String r9_String = "0";
        String r10_String = "0";
        String r11_String = "0";
        String r2_String = RContactStorage.PRIMARY_KEY;
        f.a = "888888888888888";
        f.b = "888888888888888";
        f.c = RContactStorage.PRIMARY_KEY;
        String r4_String = RContactStorage.PRIMARY_KEY;
        String r3_String = RContactStorage.PRIMARY_KEY;
        String r1_String = RContactStorage.PRIMARY_KEY;
        StringBuilder r12_StringBuilder = new StringBuilder();
        StringBuilder r13_StringBuilder = new StringBuilder();
        StringBuilder r14_StringBuilder = new StringBuilder();
        r6_String = this.g == 2 ? "1" : r5_String;
        if (this.j != null) {
            if (f.a == null) {
                f.a = this.j.getDeviceId();
            } else if (f.a.equals("888888888888888")) {
                f.a = "888888888888888";
                try {
                    f.a = this.j.getDeviceId();
                } catch (SecurityException e) {
                }
            }
            if (f.a == null) {
                f.a = "888888888888888";
            }
            if (f.b == null || f.b.equals("888888888888888")) {
                f.b = "888888888888888";
                try {
                    f.b = this.j.getSubscriberId();
                } catch (SecurityException e_2) {
                }
                if (f.b != null) {
                    f.b = "888888888888888";
                }
                if (TextUtils.isEmpty(f.c)) {
                    f.c = RContactStorage.PRIMARY_KEY;
                }
                if (f.c != null) {
                    f.c = RContactStorage.PRIMARY_KEY;
                }
            } else if (f.b != null) {
                if (TextUtils.isEmpty(f.c)) {
                    if (f.c != null) {
                        r5_NetworkInfo = null;
                    } else {
                        f.c = RContactStorage.PRIMARY_KEY;
                    }
                } else {
                    f.c = RContactStorage.PRIMARY_KEY;
                    if (f.c != null) {
                        f.c = RContactStorage.PRIMARY_KEY;
                    }
                }
            } else {
                f.b = "888888888888888";
                if (TextUtils.isEmpty(f.c)) {
                    f.c = RContactStorage.PRIMARY_KEY;
                }
                if (f.c != null) {
                    r5_NetworkInfo = null;
                } else {
                    f.c = RContactStorage.PRIMARY_KEY;
                }
            }
        }
        r5_NetworkInfo = null;
        try {
            r5_NetworkInfo = this.h.getActiveNetworkInfo();
        } catch (SecurityException e_3) {
        }
        if (j.a(r5_NetworkInfo) != -1) {
            r4_String = j.a(this.j);
            if (t()) {
                if (a(this.r)) {
                    r3_String = DownloadService.V2;
                }
            }
            r3_String = "1";
            if (!t()) {
                o();
            }
        } else {
            this.r = null;
        }
        String[] r5_StringA = j.a(this.s);
        if (r5_StringA[0].equals("true")) {
            r2_String = r5_StringA[1];
        }
        r7_m.i = r6_String;
        r7_m.j = r8_String;
        r7_m.l = r9_String;
        r7_m.m = r10_String;
        r7_m.n = r11_String;
        r7_m.c = f.d;
        r7_m.d = f.e;
        r7_m.o = r2_String;
        r7_m.p = f.a;
        r7_m.s = f.c;
        r7_m.q = f.b;
        r7_m.z = this.D;
        r7_m.t = r4_String;
        r7_m.u = r3_String;
        r7_m.f = c.f();
        r7_m.g = "android" + c.i();
        r7_m.h = c.h();
        this.G.append("<?xml version=\"1.0\" encoding=\"");
        this.G.append("GBK").append("\"?>");
        this.G.append("<Cell_Req ver=\"3.0\"><HDR version=\"3.0\" cdma=\"");
        this.G.append(r6_String);
        this.G.append("\" gtype=\"").append(r8_String);
        this.G.append("\" glong=\"").append(r9_String);
        this.G.append("\" glat=\"").append(r10_String);
        this.G.append("\" precision=\"").append(r11_String);
        this.G.append("\"><src>").append(f.d);
        this.G.append("</src><license>").append(f.e);
        this.G.append("</license><key>").append(r2_String);
        this.G.append("</key><clientid>").append(f.f);
        this.G.append("</clientid><imei>").append(f.a);
        this.G.append("</imei><imsi>").append(f.b);
        this.G.append("</imsi><smac>").append(this.D);
        this.G.append("</smac></HDR><DRR phnum=\"").append(f.c);
        this.G.append("\" nettype=\"").append(r4_String);
        this.G.append("\" inftype=\"").append(r3_String).append("\">");
        if (this.k.size() > 0) {
            StringBuilder r4_StringBuilder = new StringBuilder();
            e r1_e;
            switch (this.g) {
                case XListViewHeader.STATE_READY:
                    r1_e = (e) this.k.get(0);
                    r4_StringBuilder.delete(0, r4_StringBuilder.length());
                    r4_StringBuilder.append("<mcc>").append(r1_e.a).append("</mcc>");
                    r4_StringBuilder.append("<mnc>").append(r1_e.b).append("</mnc>");
                    r4_StringBuilder.append("<lac>").append(r1_e.c).append("</lac>");
                    r4_StringBuilder.append("<cellid>").append(r1_e.d);
                    r4_StringBuilder.append("</cellid>");
                    r4_StringBuilder.append("<signal>").append(r1_e.j);
                    r4_StringBuilder.append("</signal>");
                    r2_String = r4_StringBuilder.toString();
                    int r3i = 0;
                    while (r3i < this.k.size()) {
                        if (r3i == 0) {
                            r3i++;
                        } else {
                            r1_e = (e) this.k.get(r3i);
                            r12_StringBuilder.append(r1_e.c).append(",");
                            r12_StringBuilder.append(r1_e.d).append(",");
                            r12_StringBuilder.append(r1_e.j);
                            if (r3i != this.k.size() - 1) {
                                r12_StringBuilder.append("*");
                            }
                            r3i++;
                        }
                    }
                    r1_String = r2_String;
                    break;
                case XListViewHeader.STATE_REFRESHING:
                    r1_e = (e) this.k.get(0);
                    r4_StringBuilder.delete(0, r4_StringBuilder.length());
                    r4_StringBuilder.append("<mcc>").append(r1_e.a).append("</mcc>");
                    r4_StringBuilder.append("<sid>").append(r1_e.g).append("</sid>");
                    r4_StringBuilder.append("<nid>").append(r1_e.h).append("</nid>");
                    r4_StringBuilder.append("<bid>").append(r1_e.i).append("</bid>");
                    if (r1_e.f <= 0 || r1_e.e <= 0) {
                        r4_StringBuilder.append("<signal>").append(r1_e.j).append("</signal>");
                        r1_String = r4_StringBuilder.toString();
                    } else {
                        r4_StringBuilder.append("<lon>").append(r1_e.f).append("</lon>");
                        r4_StringBuilder.append("<lat>").append(r1_e.e).append("</lat>");
                        r4_StringBuilder.append("<signal>").append(r1_e.j).append("</signal>");
                        r1_String = r4_StringBuilder.toString();
                    }
                    break;
            }
            r4_StringBuilder.delete(0, r4_StringBuilder.length());
            r3_String = r1_String;
        } else {
            r3_String = r1_String;
        }
        if (t()) {
            if (a(this.r)) {
                r14_StringBuilder.append(this.r.getBSSID()).append(",");
                r14_StringBuilder.append(this.r.getRssi()).append(",");
                r14_StringBuilder.append(this.r.getSSID().replace("*", "."));
            }
            int r2i = 0;
            while (r2i < this.l.size()) {
                ScanResult r1_ScanResult = (ScanResult) this.l.get(r2i);
                r13_StringBuilder.append(r1_ScanResult.BSSID).append(",");
                r13_StringBuilder.append(r1_ScanResult.level).append(",");
                r13_StringBuilder.append(r2i).append("*");
                r2i++;
            }
        } else {
            o();
        }
        this.G.append(r3_String);
        StringBuilder r1_StringBuilder = this.G;
        Object[] r4_ObjectA = new Object[1];
        r4_ObjectA[0] = r12_StringBuilder;
        r1_StringBuilder.append(String.format("<nb>%s</nb>", r4_ObjectA));
        if (r13_StringBuilder.length() == 0) {
            r1_StringBuilder = this.G;
            r4_ObjectA = new Object[1];
            r4_ObjectA[0] = r14_StringBuilder;
            r1_StringBuilder.append(String.format("<macs><![CDATA[%s]]></macs>", r4_ObjectA));
        } else {
            r13_StringBuilder.deleteCharAt(r13_StringBuilder.length() - 1);
            r1_StringBuilder = this.G;
            r4_ObjectA = new Object[1];
            r4_ObjectA[0] = r13_StringBuilder;
            r1_StringBuilder.append(String.format("<macs><![CDATA[%s]]></macs>", r4_ObjectA));
        }
        r1_StringBuilder = this.G;
        r4_ObjectA = new Object[1];
        r4_ObjectA[0] = r14_StringBuilder;
        r1_StringBuilder.append(String.format("<mmac><![CDATA[%s]]></mmac>", r4_ObjectA));
        this.G.append("</DRR></Cell_Req>");
        a(this.G);
        r7_m.w = r3_String;
        r7_m.x = r12_StringBuilder.toString();
        r7_m.z = r14_StringBuilder.toString();
        r7_m.y = r13_StringBuilder.toString();
        r7_m.v = String.valueOf(this.g);
        if (this.E == null || this.C < 0 || (!this.x)) {
            r12_StringBuilder.delete(0, r12_StringBuilder.length());
            r13_StringBuilder.delete(0, r13_StringBuilder.length());
            r14_StringBuilder.delete(0, r14_StringBuilder.length());
        } else {
            try {
                this.F = this.E.d();
                if (this.F != null) {
                    byte[] r1_byteA = this.F.a();
                    if (r1_byteA.length > 0) {
                        r7_m.A = new byte[r1_byteA.length];
                        System.arraycopy(r1_byteA, 0, r7_m.A, 0, r1_byteA.length);
                        this.G.insert(this.G.length() - 11, "<COR><inf>");
                        this.G.insert(this.G.length() - 11, b.a(r1_byteA));
                        this.G.insert(this.G.length() - 11, "</inf></COR>");
                    }
                }
            } catch (Exception e_4) {
            }
            r12_StringBuilder.delete(0, r12_StringBuilder.length());
            r13_StringBuilder.delete(0, r13_StringBuilder.length());
            r14_StringBuilder.delete(0, r14_StringBuilder.length());
        }
        return r7_m.a();
    }

    private e b(CellLocation r4_CellLocation) {
        GsmCellLocation r4_GsmCellLocation = (GsmCellLocation) r4_CellLocation;
        e r0_e = new e();
        String[] r1_StringA = o.a(this.j);
        r0_e.a = r1_StringA[0];
        r0_e.b = r1_StringA[1];
        r0_e.c = r4_GsmCellLocation.getLac();
        r0_e.d = r4_GsmCellLocation.getCid();
        r0_e.j = this.p;
        return r0_e;
    }

    private void b(int r3i) {
        if (r3i == -113) {
            this.p = -113;
        } else {
            this.p = r3i;
            switch (this.g) {
                case XListViewHeader.STATE_READY:
                case XListViewHeader.STATE_REFRESHING:
                    if (this.k.size() > 0) {
                        ((e) this.k.get(0)).j = this.p;
                    }
            }
        }
    }

    private synchronized void c(int r7i) {
        y();
        if (this.a == null) {
            this.a = new u(this, r7i);
        }
        if (this.b == null) {
            this.b = new Timer(false);
            this.b.schedule(this.a, 3000, 3000);
        }
    }

    private void c(CellLocation r8_CellLocation) {
        if (this.k == null || r8_CellLocation == null || this.j == null) {
        } else {
            this.k.clear();
            GsmCellLocation r0_GsmCellLocation = (GsmCellLocation) r8_CellLocation;
            Object[] r0_ObjectA;
            if (r0_GsmCellLocation.getLac() == -1) {
                this.g = 9;
                r0_ObjectA = new Object[1];
                r0_ObjectA[0] = "gsm illegal";
                o.a(r0_ObjectA);
            } else if (r0_GsmCellLocation.getCid() == -1 || r0_GsmCellLocation.getCid() == 65535 || r0_GsmCellLocation.getCid() == 268435455) {
                this.g = 9;
                r0_ObjectA = new Object[1];
                r0_ObjectA[0] = "gsm illegal";
                o.a(r0_ObjectA);
            } else {
                this.g = 1;
                this.k.add(b(r8_CellLocation));
                List r0_List = this.j.getNeighboringCellInfo();
                if (r0_List != null) {
                    Iterator r1_Iterator = r0_List.iterator();
                    while (r1_Iterator.hasNext()) {
                        NeighboringCellInfo r0_NeighboringCellInfo = (NeighboringCellInfo) r1_Iterator.next();
                        if (r0_NeighboringCellInfo.getCid() != -1) {
                            e r0_e = a(r0_NeighboringCellInfo);
                            if (r0_e != null) {
                                this.k.add(r0_e);
                            }
                        }
                    }
                }
            }
        }
    }

    private void d(int r6i) {
        int r0i = 70254591;
        if (f()) {
            try {
                x();
                switch (r6i) {
                    case XListViewHeader.STATE_READY:
                        r0i = 674234367;
                        break;
                    case XListViewHeader.STATE_REFRESHING:
                        r0i = n() ? 2083520511 : 674234367;
                        break;
                }
                this.E.a(null, a(1, r0i, 1));
                this.c = this.E.d();
                y();
            } catch (Exception e) {
                o.a(e);
            }
            if (this.c != null) {
                String r1_String = this.B.a(this.c.a(), this.f);
                if (f()) {
                    if (TextUtils.isEmpty(r1_String) || (!r1_String.equals("true"))) {
                        this.d++;
                        this.E.a(this.c, a(1, r0i, 0));
                    } else {
                        this.E.a(this.c, a(1, r0i, 1));
                    }
                    if (f() && this.E.f() == 0) {
                        w();
                    } else {
                        if (this.d < 3) {
                            w();
                        }
                    }
                }
            }
            if (f() || this.E.f() == 0) {
                if (this.d < 3) {
                } else {
                    w();
                }
            } else {
                w();
            }
        }
    }

    private void d(CellLocation r4_CellLocation) {
        this.k.clear();
        if (o.b() < 5) {
        } else {
            try {
            } catch (Exception e) {
            }
            Object[] r0_ObjectA;
            if (l.a(r4_CellLocation, "getSystemId", new Object[0]) <= 0) {
                this.g = 9;
                r0_ObjectA = new Object[1];
                r0_ObjectA[0] = "cdma illegal";
                o.a(r0_ObjectA);
            } else if (l.a(r4_CellLocation, "getNetworkId", new Object[0]) == -1) {
                this.g = 9;
                r0_ObjectA = new Object[1];
                r0_ObjectA[0] = "cdma illegal";
                o.a(r0_ObjectA);
            } else if (l.a(r4_CellLocation, "getBaseStationId", new Object[0]) == -1) {
                this.g = 9;
                r0_ObjectA = new Object[1];
                r0_ObjectA[0] = "cdma illegal";
                o.a(r0_ObjectA);
            } else {
                this.g = 2;
                String[] r0_StringA = o.a(this.j);
                e r1_e = new e();
                r1_e.a = r0_StringA[0];
                r1_e.b = r0_StringA[1];
                r1_e.g = l.a(r4_CellLocation, "getSystemId", new Object[0]);
                r1_e.h = l.a(r4_CellLocation, "getNetworkId", new Object[0]);
                r1_e.i = l.a(r4_CellLocation, "getBaseStationId", new Object[0]);
                r1_e.j = this.p;
                r1_e.e = l.a(r4_CellLocation, "getBaseStationLatitude", new Object[0]);
                r1_e.f = l.a(r4_CellLocation, "getBaseStationLongitude", new Object[0]);
                this.k.add(r1_e);
            }
        }
    }

    private void g() {
        this.i = (WifiManager) o.b(this.f, NetworkManager.WIFI);
        IntentFilter r0_IntentFilter = new IntentFilter();
        r0_IntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        r0_IntentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        r0_IntentFilter.addAction("android.intent.action.SCREEN_ON");
        r0_IntentFilter.addAction("android.intent.action.SCREEN_OFF");
        r0_IntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        r0_IntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        r0_IntentFilter.addAction("android.location.GPS_FIX_CHANGE");
        r0_IntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.f.registerReceiver(this.q, r0_IntentFilter);
        p();
    }

    private void h() {
        String r1_String;
        int r0i;
        this.h = (ConnectivityManager) o.b(this.f, "connectivity");
        CellLocation.requestLocationUpdate();
        this.j = (TelephonyManager) o.b(this.f, "phone");
        this.J = this.j.getCellLocation();
        switch (this.j.getPhoneType()) {
            case XListViewHeader.STATE_READY:
                this.g = 1;
                this.o = new r(this);
                r1_String = "android.telephony.PhoneStateListener";
                r0_String = RContactStorage.PRIMARY_KEY;
                r0i = 0;
                if (o.b() < 7) {
                    try {
                    } catch (Exception e) {
                        o.a(e);
                    }
                }
                if (r0i == 0) {
                    this.j.listen(this.o, Base64.URL_SAFE);
                } else {
                    try {
                        this.j.listen(this.o, r0i | 16);
                    } catch (SecurityException e_2) {
                        o.a(e_2);
                    }
                }
            case XListViewHeader.STATE_REFRESHING:
                this.g = 2;
                this.o = new r(this);
                r1_String = "android.telephony.PhoneStateListener";
                r0_String = RContactStorage.PRIMARY_KEY;
                r0i = 0;
                r0i = o.b() < 7 ? l.b(r1_String, "LISTEN_SIGNAL_STRENGTHS") : l.b(r1_String, "LISTEN_SIGNAL_STRENGTH");
                if (r0i == 0) {
                    this.j.listen(this.o, r0i | 16);
                } else {
                    this.j.listen(this.o, Base64.URL_SAFE);
                }
        }
        this.g = 9;
        this.o = new r(this);
        r1_String = "android.telephony.PhoneStateListener";
        r0_String = RContactStorage.PRIMARY_KEY;
        r0i = 0;
        if (o.b() < 7) {
        }
        if (r0i == 0) {
            this.j.listen(this.o, Base64.URL_SAFE);
        } else {
            this.j.listen(this.o, r0i | 16);
        }
    }

    private String i() {
        int r4i = 0;
        v();
        String r0_String = RContactStorage.PRIMARY_KEY;
        String r2_String = RContactStorage.PRIMARY_KEY;
        r2_String = LocationManagerProxy.NETWORK_PROVIDER;
        if (t()) {
            this.r = this.i.getConnectionInfo();
        } else {
            o();
        }
        String r3_String = RContactStorage.PRIMARY_KEY;
        e r0_e;
        StringBuilder r1_StringBuilder;
        switch (this.g) {
            case XListViewHeader.STATE_READY:
                if (this.k.size() > 0) {
                    r0_e = (e) this.k.get(r4i);
                    r1_StringBuilder = new StringBuilder();
                    r1_StringBuilder.append(r0_e.a).append("#");
                    r1_StringBuilder.append(r0_e.b).append("#");
                    r1_StringBuilder.append(r0_e.c).append("#");
                    r1_StringBuilder.append(r0_e.d).append("#");
                    r1_StringBuilder.append(r2_String).append("#");
                    r1_StringBuilder.append(this.l.size() > 0 ? "cellwifi" : "cell");
                    return r1_StringBuilder.toString();
                }
            case XListViewHeader.STATE_REFRESHING:
                if (this.k.size() > 0) {
                    r0_e = (e) this.k.get(r4i);
                    r1_StringBuilder = new StringBuilder();
                    r1_StringBuilder.append(r0_e.a).append("#");
                    r1_StringBuilder.append(r0_e.b).append("#");
                    r1_StringBuilder.append(r0_e.g).append("#");
                    r1_StringBuilder.append(r0_e.h).append("#");
                    r1_StringBuilder.append(r0_e.i).append("#");
                    r1_StringBuilder.append(r2_String).append("#");
                    r1_StringBuilder.append(this.l.size() > 0 ? "cellwifi" : "cell");
                    return r1_StringBuilder.toString();
                }
            case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                Object[] r3_ObjectA = new Object[1];
                r3_ObjectA[0] = r2_String;
                r2_String = String.format("#%s#", r3_ObjectA);
                if ((this.l.size() == 1 && !a(this.r)) || this.l.size() == 0) {
                    return null;
                }
                if (this.l.size() != 1 || !a(this.r)) {
                    return r2_String + NetworkManager.WIFI;
                }
                ScanResult r0_ScanResult = (ScanResult) this.l.get(0);
                if (r0_ScanResult == null || (!this.r.getBSSID().equals(r0_ScanResult.BSSID))) {
                    r0_String = r2_String;
                    return r0_String;
                } else {
                    r0_String = null;
                    return r0_String;
                }
        }
        return r0_String;
    }

    private StringBuilder j() {
        v();
        StringBuilder r6_StringBuilder = new StringBuilder(700);
        switch (this.g) {
            case XListViewHeader.STATE_READY:
                int r1i = 0;
                while (r1i < this.k.size()) {
                    if (r1i == 0) {
                        r1i++;
                    } else {
                        e r0_e = (e) this.k.get(r1i);
                        r6_StringBuilder.append("#").append(r0_e.b);
                        r6_StringBuilder.append("|").append(r0_e.c);
                        r6_StringBuilder.append("|").append(r0_e.d);
                        r1i++;
                    }
                }
                break;
        }
        String r1_String;
        int r3i;
        int r4i;
        String r7_String;
        String r0_String;
        Object[] r9_ObjectA;
        if ((this.D == null || this.D.equals("00:00:00:00:00:00")) && this.r != null) {
            this.D = this.r.getMacAddress();
            if (this.D == null) {
                this.D = "00:00:00:00:00:00";
            }
            if (t()) {
                o();
            } else {
                r1_String = a(this.r) ? RContactStorage.PRIMARY_KEY : this.r.getBSSID();
                r3i = 0;
                r4i = 0;
                while (r3i < this.l.size()) {
                    r7_String = ((ScanResult) this.l.get(r3i)).BSSID;
                    r0_String = "nb";
                    if (!r1_String.equals(r7_String)) {
                        r0_String = "access";
                        r4i = 1;
                    }
                    r9_ObjectA = new Object[2];
                    r9_ObjectA[0] = r7_String;
                    r9_ObjectA[1] = r0_String;
                    r6_StringBuilder.append(String.format("#%s,%s", r9_ObjectA));
                    r3i++;
                }
                if (r4i != 0 || r1_String.length() <= 0) {
                } else {
                    r6_StringBuilder.append("#").append(r1_String);
                    r6_StringBuilder.append(",access");
                }
            }
            if (r6_StringBuilder.length() <= 0) {
                r6_StringBuilder.deleteCharAt(0);
            }
            return r6_StringBuilder;
        } else {
            if (t()) {
                o();
            } else {
                if (a(this.r)) {
                }
                r3i = 0;
                r4i = 0;
                while (r3i < this.l.size()) {
                    r7_String = ((ScanResult) this.l.get(r3i)).BSSID;
                    r0_String = "nb";
                    if (r1_String.equals(r7_String)) {
                        r9_ObjectA = new Object[2];
                        r9_ObjectA[0] = r7_String;
                        r9_ObjectA[1] = r0_String;
                        r6_StringBuilder.append(String.format("#%s,%s", r9_ObjectA));
                        r3i++;
                    } else {
                        r0_String = "access";
                        r4i = 1;
                        r9_ObjectA = new Object[2];
                        r9_ObjectA[0] = r7_String;
                        r9_ObjectA[1] = r0_String;
                        r6_StringBuilder.append(String.format("#%s,%s", r9_ObjectA));
                        r3i++;
                    }
                }
                if (r4i != 0 || r1_String.length() <= 0) {
                } else {
                    r6_StringBuilder.append("#").append(r1_String);
                    r6_StringBuilder.append(",access");
                }
            }
            if (r6_StringBuilder.length() <= 0) {
                return r6_StringBuilder;
            }
            r6_StringBuilder.deleteCharAt(0);
            return r6_StringBuilder;
        }
    }

    private synchronized byte[] k() {
        if (l()) {
            CellLocation.requestLocationUpdate();
            this.z = o.a();
        }
        if (m()) {
            p();
        }
        return a(null);
    }

    private boolean l() {
        return !(this.w) && this.z != 0 && o.a() - this.z >= f.j;
    }

    private boolean m() {
        return t() && this.A != 0 && o.a() - this.A >= f.i;
    }

    private boolean n() {
        if (this.i == null || !t()) {
            return false;
        }
        try {
            return j.a(this.h.getActiveNetworkInfo()) != -1 && a(this.i.getConnectionInfo());
        } catch (SecurityException e) {
            return false;
        } catch (Exception e_2) {
            return false;
        }
    }

    private void o() {
        this.l.clear();
        this.r = null;
    }

    private void p() {
        if (t()) {
            try {
                this.i.startScan();
                this.A = o.a();
            } catch (SecurityException e) {
            }
        }
    }

    private boolean q() {
        return (this.y > 0 ? 1 : (this.y == 0? 0 : -1)) != 0 && o.a() - this.y < 2000;
    }

    private void r() {
        if (this.u == null || this.m.size() < 1) {
        } else {
            Iterator r2_Iterator = this.m.entrySet().iterator();
            while (r2_Iterator != null && r2_Iterator.hasNext()) {
                Entry r0_Entry = (Entry) r2_Iterator.next();
                PendingIntent r1_PendingIntent = (PendingIntent) r0_Entry.getKey();
                Intent r3_Intent = new Intent();
                Bundle r4_Bundle = new Bundle();
                Iterator r5_Iterator = ((List) r0_Entry.getValue()).iterator();
                while (r5_Iterator.hasNext()) {
                    h r0_h = (h) r5_Iterator.next();
                    long r6j = r0_h.a();
                    if (r6j == -1 || r6j >= o.a()) {
                        double[] r6_doubleA = new double[4];
                        r6_doubleA[0] = r0_h.b;
                        r6_doubleA[1] = r0_h.a;
                        r6_doubleA[2] = this.u.d();
                        r6_doubleA[3] = this.u.c();
                        float r6f = o.a(r6_doubleA);
                        if (r6f < r0_h.c) {
                            r4_Bundle.putFloat("distance", r6f);
                            r4_Bundle.putString("fence", r0_h.b());
                            r3_Intent.putExtras(r4_Bundle);
                            try {
                                r1_PendingIntent.send(this.f, 0, r3_Intent);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }
    }

    private void s() {
        switch (this.g) {
            case XListViewHeader.STATE_READY:
                if (this.k.size() == 0) {
                    this.g = 9;
                }
                break;
            case XListViewHeader.STATE_REFRESHING:
                if (this.k.size() == 0) {
                    this.g = 9;
                }
                break;
        }
    }

    private boolean t() {
        try {
            return this.i.isWifiEnabled();
        } catch (SecurityException e) {
            return false;
        }
    }

    private c u() throws Exception {
        byte[] r0_byteA = k();
        if (this.G == null || (!this.G.toString().equals(this.t))) {
            this.t = this.G.toString();
            return a(r0_byteA, false);
        } else {
            if (this.u != null) {
                this.v = o.a();
                return this.u;
            }
            return a(r0_byteA, false);
        }
    }

    private void v() {
        if (this.w) {
            this.g = 9;
            this.k.clear();
        } else {
            s();
        }
    }

    private synchronized void w() {
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
        }
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
    }

    private void x() {
        if (f()) {
            try {
                this.E.a(768);
            } catch (Exception e) {
                o.a(e);
            }
        }
    }

    private void y() {
        if (f() && this.E.f() <= 0) {
            try {
                if (this.E.e()) {
                }
            } catch (Exception e) {
            }
        }
    }

    public synchronized int a_(boolean r2z, int r3i) {
        if (r2z) {
            c(r3i);
        } else {
            w();
        }
        return f() ? this.E.f() : -1;
    }

    public void a_(PendingIntent r2_PendingIntent) {
        if (r2_PendingIntent == null) {
        } else {
            this.m.remove(r2_PendingIntent);
        }
    }

    public void a_(Context r3_Context) {
        if (r3_Context != null && this.f == null) {
            this.f = r3_Context.getApplicationContext();
            o.a(this.f, "in debug mode, only for test");
            g();
            h();
            this.H = System.currentTimeMillis();
        }
    }

    public void a_(h r5_h, PendingIntent r6_PendingIntent) {
        if (r6_PendingIntent == null || r5_h == null) {
        } else {
            long r0j = r5_h.a();
            if (r0j == -1 || r0j >= o.a()) {
                List r0_List;
                if (this.m.get(r6_PendingIntent) != null) {
                    r0_List = (List) this.m.get(r6_PendingIntent);
                    r0_List.add(r5_h);
                    this.m.put(r6_PendingIntent, r0_List);
                } else {
                    r0_List = new ArrayList();
                    r0_List.add(r5_h);
                    this.m.put(r6_PendingIntent, r0_List);
                }
            }
        }
    }

    public void a_(String r5_String) {
        if (r5_String == null || r5_String.indexOf("##") == -1) {
        } else {
            String[] r0_StringA = r5_String.split("##");
            if (r0_StringA.length == 3) {
                f.d = r0_StringA[0];
                if (!f.e.equals(r0_StringA[1])) {
                    d.a().c();
                }
                f.e = r0_StringA[1];
                f.f = r0_StringA[2];
            }
        }
    }

    public void a_(JSONObject r1_JSONObject) {
        this.s = r1_JSONObject;
    }

    public synchronized c b() throws Exception {
        c r0_c = null;
        synchronized (this) {
            if (this.f == null) {
            } else if (TextUtils.isEmpty(f.d) || TextUtils.isEmpty(f.e)) {
            } else if ("false".equals(j.a(this.s)[0])) {
                Log.e("AuthLocation", "key\u9274\u6743\u5931\u8d25");
            } else {
                this.C++;
                if (this.C > 1) {
                    d();
                }
                if (this.C == 1) {
                    this.I = System.currentTimeMillis();
                    this.w = o.a(this.f);
                    if (this.i != null) {
                        this.l = this.i.getScanResults();
                    }
                }
                if (this.l == null) {
                    this.l = new ArrayList();
                }
                String r1_String;
                StringBuilder r0_StringBuilder;
                c r2_c;
                if (this.C == 1 && t() && this.I - this.H < 2000) {
                    int r1i = XListViewFooter.STATE_NODATA;
                    while (r1i > 0 && this.l.size() == 0) {
                        SystemClock.sleep(500);
                        r1i--;
                    }
                    if ((!a(this.v)) || this.u == null) {
                        a(this.J);
                        a(this.l);
                        r1_String = i();
                        if (TextUtils.isEmpty(r1_String)) {
                        } else {
                            r0_StringBuilder = j();
                            r2_c = d.a().a(r1_String, r0_StringBuilder, "mem");
                            if (r2_c != null) {
                                this.u = r2_c;
                            } else {
                                r2_c = u();
                                if (a(r2_c, this.u)) {
                                    this.u = r2_c;
                                } else {
                                    d.a().a(r1_String, this.u, r0_StringBuilder, this.f);
                                    r0_StringBuilder.delete(0, r0_StringBuilder.length());
                                    this.v = o.a();
                                    r();
                                    r0_c = this.u;
                                }
                            }
                            d.a().a(r1_String, this.u, r0_StringBuilder, this.f);
                            r0_StringBuilder.delete(0, r0_StringBuilder.length());
                            this.v = o.a();
                            r();
                            r0_c = this.u;
                        }
                    } else {
                        this.v = o.a();
                        r0_c = this.u;
                    }
                } else {
                    if (a(this.v) || this.u == null) {
                        a(this.J);
                        a(this.l);
                        r1_String = i();
                        if (TextUtils.isEmpty(r1_String)) {
                            r0_StringBuilder = j();
                            r2_c = d.a().a(r1_String, r0_StringBuilder, "mem");
                            if (r2_c != null) {
                                r2_c = u();
                                if (a(r2_c, this.u)) {
                                    this.u = r2_c;
                                }
                            } else {
                                this.u = r2_c;
                            }
                            d.a().a(r1_String, this.u, r0_StringBuilder, this.f);
                            r0_StringBuilder.delete(0, r0_StringBuilder.length());
                            this.v = o.a();
                            r();
                            r0_c = this.u;
                        }
                    } else {
                        this.v = o.a();
                        r0_c = this.u;
                    }
                }
            }
        }
        return r0_c;
    }

    public void c() {
        try {
            if (this.E != null) {
                this.E.c();
                this.K = false;
            }
        } catch (Exception e) {
        }
        this.F = null;
        try {
            if (this.f != null) {
                this.f.unregisterReceiver(this.q);
            }
            this.q = null;
        } catch (Exception e_2) {
            this.q = null;
        }
        try {
            if (this.j == null || this.o == null) {
                d.a().c();
                f.g = false;
                this.v = 0;
                this.k.clear();
                this.m.clear();
                this.p = -113;
                o();
                this.t = null;
                this.u = null;
                this.f = null;
                this.j = null;
                e = null;
                System.gc();
                Runtime.getRuntime().runFinalization();
            } else {
                this.j.listen(this.o, 0);
                d.a().c();
                f.g = false;
                this.v = 0;
                this.k.clear();
                this.m.clear();
                this.p = -113;
                o();
                this.t = null;
                this.u = null;
                this.f = null;
                this.j = null;
                e = null;
                System.gc();
                Runtime.getRuntime().runFinalization();
            }
        } catch (Exception e_3) {
            o.a(e_3);
        }
    }

    public void d() {
        try {
            if (this.E == null) {
                this.E = t.a(this.f);
                this.E.a((int)AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
            }
            if (!this.K) {
                this.K = true;
                this.E.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int e() {
        return this.m != null ? this.m.size() : 0;
    }

    boolean f() {
        return this.E != null;
    }
}