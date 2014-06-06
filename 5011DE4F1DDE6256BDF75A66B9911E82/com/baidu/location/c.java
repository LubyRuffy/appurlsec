package com.baidu.location;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.qiubai.library.adview.util.AdViewUtil;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewFooter;

class c {
    private static String f;
    private static String g;
    private static Method k;
    private static Method l;
    private static Method m;
    private static Class n;
    private static long o;
    private static int p;
    private static boolean q;
    private final String a;
    private Context b;
    private TelephonyManager c;
    private a d;
    private List e;
    private Handler h;
    private b i;
    private boolean j;

    public class a {
        public long byte;
        public int do;
        public int for;
        public int if;
        public int int;
        public char new;
        public int try;

        public a() {
            this.for = -1;
            this.try = -1;
            this.do = -1;
            this.if = -1;
            this.byte = 0;
            this.int = -1;
            this.new = '\u0000';
            this.byte = System.currentTimeMillis();
        }

        public a(int r6i, int r7i, int r8i, int r9i, char r10c) {
            this.for = -1;
            this.try = -1;
            this.do = -1;
            this.if = -1;
            this.byte = 0;
            this.int = -1;
            this.new = '\u0000';
            this.for = r6i;
            this.try = r7i;
            this.do = r8i;
            this.if = r9i;
            this.new = r10c;
            this.byte = System.currentTimeMillis() / 1000;
        }

        public String a_() {
            StringBuffer r0_StringBuffer = new StringBuffer(128);
            r0_StringBuffer.append(this.try + 23);
            r0_StringBuffer.append("H");
            r0_StringBuffer.append(this.for + 45);
            r0_StringBuffer.append("K");
            r0_StringBuffer.append(this.if + 54);
            r0_StringBuffer.append("Q");
            r0_StringBuffer.append(this.do + 203);
            return r0_StringBuffer.toString();
        }

        public boolean a_(com.baidu.location.c.a r3_com_baidu_location_c_a) {
            return this.for == r3_com_baidu_location_c_a.for && this.try == r3_com_baidu_location_c_a.try && this.if == r3_com_baidu_location_c_a.if;
        }

        public boolean do() {
            return ((System.currentTimeMillis() - this.byte) > o ? 1 : ((System.currentTimeMillis() - this.byte) == o? 0 : -1)) < 0;
        }

        public boolean for() {
            return this.for > -1 && this.try > 0;
        }

        public String if() {
            StringBuffer r0_StringBuffer = new StringBuffer(64);
            Object[] r2_ObjectA = new Object[5];
            r2_ObjectA[0] = Integer.valueOf(this.do);
            r2_ObjectA[1] = Integer.valueOf(this.if);
            r2_ObjectA[2] = Integer.valueOf(this.for);
            r2_ObjectA[3] = Integer.valueOf(this.try);
            r2_ObjectA[4] = Integer.valueOf(this.int);
            r0_StringBuffer.append(String.format("cell=%d|%d|%d|%d:%d", r2_ObjectA));
            return r0_StringBuffer.toString();
        }

        public String int() {
            String r0_String;
            try {
                List r3_List = c.this.c.getNeighboringCellInfo();
                if (r3_List == null || r3_List.isEmpty()) {
                    r0_String = null;
                } else {
                    String r1_String = "&nc=";
                    Iterator r4_Iterator = r3_List.iterator();
                    int r3i = 0;
                    while (r4_Iterator.hasNext()) {
                        NeighboringCellInfo r0_NeighboringCellInfo = (NeighboringCellInfo) r4_Iterator.next();
                        if (r3i == 0) {
                            r0_String = r0_NeighboringCellInfo.getLac() != this.for ? r1_String + r0_NeighboringCellInfo.getLac() + "|" + r0_NeighboringCellInfo.getCid() + "|" + r0_NeighboringCellInfo.getRssi() : r1_String + "|" + r0_NeighboringCellInfo.getCid() + "|" + r0_NeighboringCellInfo.getRssi();
                        } else if (r3i < 8) {
                            r0_String = r0_NeighboringCellInfo.getLac() != this.for ? r1_String + ";" + r0_NeighboringCellInfo.getLac() + "|" + r0_NeighboringCellInfo.getCid() + "|" + r0_NeighboringCellInfo.getRssi() : r1_String + ";" + "|" + r0_NeighboringCellInfo.getCid() + "|" + r0_NeighboringCellInfo.getRssi();
                        } else {
                            break;
                        }
                        r3i++;
                        r1_String = r0_String;
                    }
                    r0_String = r1_String;
                }
            } catch (Exception e) {
                r0_String = null;
            }
            j.if(f.v, "Neighbour:" + r0_String);
            return r0_String;
        }

        public String toString() {
            StringBuffer r2_StringBuffer = new StringBuffer(128);
            r2_StringBuffer.append("&nw=");
            r2_StringBuffer.append(c.this.d.new);
            Object[] r3_ObjectA = new Object[5];
            r3_ObjectA[0] = Integer.valueOf(this.do);
            r3_ObjectA[1] = Integer.valueOf(this.if);
            r3_ObjectA[2] = Integer.valueOf(this.for);
            r3_ObjectA[3] = Integer.valueOf(this.try);
            r3_ObjectA[4] = Integer.valueOf(this.int);
            r2_StringBuffer.append(String.format("&cl=%d|%d|%d|%d&cl_s=%d", r3_ObjectA));
            r2_StringBuffer.append("&cl_t=");
            r2_StringBuffer.append(this.byte);
            if (c.this.e == null || c.this.e.size() <= 0) {
                return r2_StringBuffer.toString();
            }
            int r3i = c.this.e.size();
            r2_StringBuffer.append("&clt=");
            int r1i = 0;
            while (r1i < r3i) {
                com.baidu.location.c.a r0_com_baidu_location_c_a = (com.baidu.location.c.a) c.this.e.get(r1i);
                if (r0_com_baidu_location_c_a.do != this.do) {
                    r2_StringBuffer.append(r0_com_baidu_location_c_a.do);
                }
                r2_StringBuffer.append("|");
                if (r0_com_baidu_location_c_a.if != this.if) {
                    r2_StringBuffer.append(r0_com_baidu_location_c_a.if);
                }
                r2_StringBuffer.append("|");
                if (r0_com_baidu_location_c_a.for != this.for) {
                    r2_StringBuffer.append(r0_com_baidu_location_c_a.for);
                }
                r2_StringBuffer.append("|");
                if (r0_com_baidu_location_c_a.try != this.try) {
                    r2_StringBuffer.append(r0_com_baidu_location_c_a.try);
                }
                r2_StringBuffer.append("|");
                if (r1i != r3i - 1) {
                    r2_StringBuffer.append(r0_com_baidu_location_c_a.byte / 1000);
                } else {
                    r2_StringBuffer.append((System.currentTimeMillis() - r0_com_baidu_location_c_a.byte) / 1000);
                }
                r2_StringBuffer.append(";");
                r1i++;
            }
            return r2_StringBuffer.toString();
        }
    }

    private class b extends PhoneStateListener {
        public void onCellLocationChanged(CellLocation r3_CellLocation) {
            if (r3_CellLocation == null) {
            } else {
                try {
                    c.this.a(c.this.c.getCellLocation());
                } catch (Exception e) {
                }
            }
        }

        public void onSignalStrengthsChanged(SignalStrength r4_SignalStrength) {
            if (c.this.d != null) {
                if (c.this.d.new == 'g') {
                    c.this.d.int = r4_SignalStrength.getGsmSignalStrength();
                } else if (c.this.d.new == 'c') {
                    c.this.d.int = r4_SignalStrength.getCdmaDbm();
                }
                j.if("cell strength", "===== cell singal strength changed : " + c.this.d.int);
                if (c.this.h != null) {
                    c.this.h.obtainMessage(AdViewUtil.NETWORK_TYPE_VPON).sendToTarget();
                }
            }
        }
    }

    static {
        f = null;
        g = null;
        k = null;
        l = null;
        m = null;
        n = null;
        o = 3000;
        p = 3;
        q = false;
    }

    public c(Context r3_Context, Handler r4_Handler) {
        this.a = f.v;
        this.b = null;
        this.c = null;
        this.d = new a();
        this.e = null;
        this.h = null;
        this.i = null;
        this.j = false;
        this.b = r3_Context;
        this.h = r4_Handler;
    }

    public static String a(boolean r4z) {
        StringBuffer r1_StringBuffer = new StringBuffer(256);
        r1_StringBuffer.append("&sdk=");
        r1_StringBuffer.append(3.3f);
        String r0_String;
        if (r4z && j.A.equals("all")) {
            r1_StringBuffer.append("&addr=all");
            if (r4z) {
                r1_StringBuffer.append("&coor=gcj02");
                if (g == null) {
                    r1_StringBuffer.append("&im=");
                    r1_StringBuffer.append(f);
                } else {
                    r1_StringBuffer.append("&cu=");
                    r1_StringBuffer.append(g);
                }
                r1_StringBuffer.append("&mb=");
                r1_StringBuffer.append(Build.MODEL);
                r1_StringBuffer.append("&resid=");
                r1_StringBuffer.append("12");
                r1_StringBuffer.append("&os=A");
                r1_StringBuffer.append(VERSION.SDK);
                if (r4z) {
                    r1_StringBuffer.append("&sv=");
                    r0_String = VERSION.RELEASE;
                    if (r0_String == null || r0_String.length() <= 5) {
                        r1_StringBuffer.append(r0_String);
                    } else {
                        r0_String = r0_String.substring(0, ShareUtils.SHARE_SMS);
                        r1_StringBuffer.append(r0_String);
                    }
                }
                return r1_StringBuffer.toString();
            } else {
                if (g == null) {
                    r1_StringBuffer.append("&cu=");
                    r1_StringBuffer.append(g);
                } else {
                    r1_StringBuffer.append("&im=");
                    r1_StringBuffer.append(f);
                }
                r1_StringBuffer.append("&mb=");
                r1_StringBuffer.append(Build.MODEL);
                r1_StringBuffer.append("&resid=");
                r1_StringBuffer.append("12");
                r1_StringBuffer.append("&os=A");
                r1_StringBuffer.append(VERSION.SDK);
                if (r4z) {
                    return r1_StringBuffer.toString();
                }
                r1_StringBuffer.append("&sv=");
                r0_String = VERSION.RELEASE;
                if (r0_String == null || r0_String.length() <= 5) {
                    r1_StringBuffer.append(r0_String);
                } else {
                    r0_String = r0_String.substring(0, ShareUtils.SHARE_SMS);
                    r1_StringBuffer.append(r0_String);
                }
                return r1_StringBuffer.toString();
            }
        } else {
            if (r4z) {
                r1_StringBuffer.append("&coor=gcj02");
            }
            if (g == null) {
                r1_StringBuffer.append("&im=");
                r1_StringBuffer.append(f);
            } else {
                r1_StringBuffer.append("&cu=");
                r1_StringBuffer.append(g);
            }
            r1_StringBuffer.append("&mb=");
            r1_StringBuffer.append(Build.MODEL);
            r1_StringBuffer.append("&resid=");
            r1_StringBuffer.append("12");
            r1_StringBuffer.append("&os=A");
            r1_StringBuffer.append(VERSION.SDK);
            if (r4z) {
                r1_StringBuffer.append("&sv=");
                r0_String = VERSION.RELEASE;
                if (r0_String == null || r0_String.length() <= 5) {
                    r1_StringBuffer.append(r0_String);
                } else {
                    r0_String = r0_String.substring(0, ShareUtils.SHARE_SMS);
                    r1_StringBuffer.append(r0_String);
                }
            }
            return r1_StringBuffer.toString();
        }
    }

    private void a(CellLocation r8_CellLocation) {
        if (r8_CellLocation == null || this.c == null) {
        } else {
            if (!q) {
                f = this.c.getDeviceId();
                q = c();
            }
            j.if(f.v, "set cell info..");
            a r3_a = new a();
            r3_a.byte = System.currentTimeMillis();
            String r4_String = this.c.getNetworkOperator();
            int r0i;
            a r0_a;
            if (r4_String == null || r4_String.length() <= 0) {
                if (r8_CellLocation instanceof GsmCellLocation) {
                    if (!r8_CellLocation instanceof CdmaCellLocation) {
                        r3_a.new = 'c';
                        if (Integer.parseInt(VERSION.SDK) < 5) {
                            if (n != null) {
                                try {
                                    n = Class.forName("android.telephony.cdma.CdmaCellLocation");
                                    k = n.getMethod("getBaseStationId", new Class[0]);
                                    l = n.getMethod("getNetworkId", new Class[0]);
                                    m = n.getMethod("getSystemId", new Class[0]);
                                } catch (Exception e) {
                                    n = null;
                                    e.printStackTrace();
                                }
                            }
                            if (n != null) {
                                if (n.isInstance(r8_CellLocation)) {
                                    try {
                                        r0i = ((Integer) m.invoke(r8_CellLocation, new Object[0])).intValue();
                                        if (r0i < 0) {
                                            r0i = this.d.if;
                                        }
                                        r3_a.if = r0i;
                                        r3_a.try = ((Integer) k.invoke(r8_CellLocation, new Object[0])).intValue();
                                        r3_a.for = ((Integer) l.invoke(r8_CellLocation, new Object[0])).intValue();
                                    } catch (Exception e_2) {
                                        e_2.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    r3_a.for = ((GsmCellLocation) r8_CellLocation).getLac();
                    r3_a.try = ((GsmCellLocation) r8_CellLocation).getCid();
                    r3_a.new = 'g';
                }
                if (!r3_a.for()) {
                    if (this.d == null || (!this.d.a(r3_a))) {
                        this.d = r3_a;
                        this.h.obtainMessage(AdViewUtil.NETWORK_TYPE_VPON).sendToTarget();
                        if (r3_a.for()) {
                            if (this.e == null) {
                                this.e.clear();
                            }
                        } else {
                            if (this.e != null) {
                                this.e = new LinkedList();
                            }
                            r0i = this.e.size();
                            r0_a = r0i == 0 ? null : (a) this.e.get(r0i - 1);
                            if (!(r0_a != null && r0_a.try == this.d.try && r0_a.for == this.d.for)) {
                                if (r0_a != null) {
                                    r0_a.byte = this.d.byte - r0_a.byte;
                                }
                                this.e.add(this.d);
                                if (this.e.size() <= p) {
                                    this.e.remove(0);
                                }
                            }
                        }
                    }
                }
            } else {
                try {
                    if (r4_String.length() >= 3) {
                        r0i = Integer.valueOf(r4_String.substring(0, XListViewFooter.STATE_NOMORE)).intValue();
                        if (r0i < 0) {
                            r0i = this.d.do;
                        }
                        r3_a.do = r0i;
                    }
                    r4_String = r4_String.substring(XListViewFooter.STATE_NOMORE);
                    if (r4_String != null) {
                        char[] r5_charA = r4_String.toCharArray();
                        r0i = 0;
                        while (r0i < r5_charA.length && Character.isDigit(r5_charA[r0i])) {
                            r0i++;
                        }
                    } else {
                        r0i = 0;
                    }
                    r0i = Integer.valueOf(r4_String.substring(0, r0i)).intValue();
                    if (r0i < 0) {
                        r0i = this.d.if;
                    }
                    r3_a.if = r0i;
                } catch (Exception e_3) {
                    e_3.printStackTrace();
                }
                if (r8_CellLocation instanceof GsmCellLocation) {
                    if (r8_CellLocation instanceof CdmaCellLocation) {
                    } else {
                        r3_a.new = 'c';
                        if (Integer.parseInt(VERSION.SDK) < 5) {
                        } else if (n != null) {
                            if (n != null) {
                            } else if (n.isInstance(r8_CellLocation)) {
                            } else {
                                r0i = ((Integer) m.invoke(r8_CellLocation, new Object[0])).intValue();
                                if (r0i < 0) {
                                    r3_a.if = r0i;
                                    r3_a.try = ((Integer) k.invoke(r8_CellLocation, new Object[0])).intValue();
                                    r3_a.for = ((Integer) l.invoke(r8_CellLocation, new Object[0])).intValue();
                                } else {
                                    r0i = this.d.if;
                                    r3_a.if = r0i;
                                    r3_a.try = ((Integer) k.invoke(r8_CellLocation, new Object[0])).intValue();
                                    r3_a.for = ((Integer) l.invoke(r8_CellLocation, new Object[0])).intValue();
                                }
                            }
                        } else {
                            n = Class.forName("android.telephony.cdma.CdmaCellLocation");
                            k = n.getMethod("getBaseStationId", new Class[0]);
                            l = n.getMethod("getNetworkId", new Class[0]);
                            m = n.getMethod("getSystemId", new Class[0]);
                            if (n != null) {
                                if (n.isInstance(r8_CellLocation)) {
                                    r0i = ((Integer) m.invoke(r8_CellLocation, new Object[0])).intValue();
                                    if (r0i < 0) {
                                        r0i = this.d.if;
                                    }
                                    r3_a.if = r0i;
                                    r3_a.try = ((Integer) k.invoke(r8_CellLocation, new Object[0])).intValue();
                                    r3_a.for = ((Integer) l.invoke(r8_CellLocation, new Object[0])).intValue();
                                }
                            }
                        }
                    }
                } else {
                    r3_a.for = ((GsmCellLocation) r8_CellLocation).getLac();
                    r3_a.try = ((GsmCellLocation) r8_CellLocation).getCid();
                    r3_a.new = 'g';
                }
                if (r3_a.for()) {
                } else if (this.d == null || this.d.a(r3_a)) {
                    this.d = r3_a;
                    this.h.obtainMessage(AdViewUtil.NETWORK_TYPE_VPON).sendToTarget();
                    if (r3_a.for()) {
                        if (this.e == null) {
                        } else {
                            this.e.clear();
                        }
                    } else if (this.e != null) {
                        r0i = this.e.size();
                        if (r0i == 0) {
                        }
                        if (r0_a != null || r0_a.try == this.d.try || r0_a.for == this.d.for) {
                            if (r0_a != null) {
                                this.e.add(this.d);
                                if (this.e.size() <= p) {
                                } else {
                                    this.e.remove(0);
                                }
                            } else {
                                r0_a.byte = this.d.byte - r0_a.byte;
                                this.e.add(this.d);
                                if (this.e.size() <= p) {
                                    this.e.remove(0);
                                }
                            }
                        }
                    } else {
                        this.e = new LinkedList();
                        r0i = this.e.size();
                        if (r0i == 0) {
                        }
                        if (r0_a != null || r0_a.try == this.d.try || r0_a.for == this.d.for) {
                            if (r0_a != null) {
                                r0_a.byte = this.d.byte - r0_a.byte;
                            }
                            this.e.add(this.d);
                            if (this.e.size() <= p) {
                            } else {
                                this.e.remove(0);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean c_() {
        if (f == null || f.length() < 10) {
            return false;
        }
        try {
            char[] r2_charA = f.toCharArray();
            int r1i = 0;
            while (r1i < 10) {
                if (r2_charA[r1i] > '9' || r2_charA[r1i] < '0') {
                    return false;
                }
                r1i++;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public a a() {
        if ((this.d != null && this.d.do() && this.d.for()) || this.c == null) {
            return this.d;
        }
        try {
            a(this.c.getCellLocation());
        } catch (Exception e) {
        }
        return this.d;
    }

    public void byte() {
        if (this.j) {
            if (this.i == null || this.c == null) {
                this.i = null;
                this.c = null;
                this.e.clear();
                this.e = null;
                j.if(f.v, "cell manager stop ...");
                this.j = false;
            } else {
                this.c.listen(this.i, 0);
                this.i = null;
                this.c = null;
                this.e.clear();
                this.e = null;
                j.if(f.v, "cell manager stop ...");
                this.j = false;
            }
        }
    }

    public void do() {
        if (this.j) {
        } else {
            this.c = (TelephonyManager) this.b.getSystemService("phone");
            this.e = new LinkedList();
            this.i = new b();
            if (this.c == null || this.i == null) {
            } else {
                try {
                    this.c.listen(this.i, 272);
                    f = this.c.getDeviceId();
                    j.do = "v3.3" + f + "|" + Build.MODEL;
                } catch (Exception e) {
                }
                try {
                    g = com.baidu.location.j.a.if(this.b);
                    j.if(f.v, "CUID:" + g);
                } catch (Exception e_2) {
                    g = null;
                }
                try {
                    if (g != null) {
                        j.do = "v3.3|" + g + "|" + Build.MODEL;
                    }
                    j.if(f.v, "CUID:" + j.do);
                } catch (Exception e_3) {
                }
                q = c();
                j.a(f.v, "i:" + f);
                j.if(f.v, "cell manager start...");
                this.j = true;
            }
        }
    }

    public String for() {
        if (this.c == null) {
            this.c = (TelephonyManager) this.b.getSystemService("phone");
        }
        try {
            a(this.c.getCellLocation());
        } catch (Exception e) {
        }
        return this.d.toString();
    }

    public String int() {
        return f;
    }

    public int new() {
        return this.c.getNetworkType();
    }
}