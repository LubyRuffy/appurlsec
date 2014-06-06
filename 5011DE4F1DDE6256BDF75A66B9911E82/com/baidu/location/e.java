package com.baidu.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import com.qiubai.library.adview.util.AdViewUtil;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.cordova.NetworkManager;
import qsbk.app.nearby.ui.NearbySelectView;

class e {
    private static String a;
    private final long b;
    private final long c;
    private final long d;
    private Context e;
    private Handler f;
    private WifiManager g;
    private b h;
    private a i;
    private c j;
    private long k;
    private long l;
    private boolean m;
    private Object n;
    private Method o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;


    private class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context r2_Context, Intent r3_Intent) {
            if (r2_Context == null || e.this.f == null) {
            } else {
                e.this.d();
            }
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(Context r3_Context, Intent r4_Intent) {
            if (r3_Context == null || e.this.f == null) {
            } else {
                e.this.c();
                e.this.f.obtainMessage(AdViewUtil.NETWORK_TYPE_IZPTEC).sendToTarget();
                j.if(a, "wifi manager receive new wifi...");
            }
        }
    }

    protected class c {
        private long b;
        private long c;
        private boolean d;
        public List for;

        public c(List r5_List, long r6j) {
            this.for = null;
            this.b = 0;
            this.c = 0;
            this.d = false;
            this.b = r6j;
            this.for = r5_List;
            this.c = System.currentTimeMillis();
            a();
            j.a(a, int());
        }

        private void a() {
            if (try() < 1) {
            } else {
                int r3i = this.for.size() - 1;
                int r2i = 1;
                while (r3i >= 1 && r2i != 0) {
                    int r4i = 0;
                    r2i = 0;
                    while (r4i < r3i) {
                        int r0i;
                        if (((ScanResult) this.for.get(r4i)).level < ((ScanResult) this.for.get(r4i + 1)).level) {
                            this.for.set(r4i + 1, this.for.get(r4i));
                            this.for.set(r4i, (ScanResult) this.for.get(r4i + 1));
                            r0i = 1;
                        } else {
                            r0i = r2i;
                        }
                        r4i++;
                        r2i = r0i;
                    }
                    r3i--;
                }
            }
        }

        public String a(int r15i) {
            if (try() < 1) {
                return null;
            }
            StringBuffer r8_StringBuffer = new StringBuffer(512);
            String r9_String = e.this.char();
            int r0i = this.for.size();
            int r6i;
            int r1i;
            int r2i;
            int r4i;
            String r10_String;
            Object[] r11_ObjectA;
            Object[] r12_ObjectA;
            if (r0i > r15i) {
                r6i = 0;
                r1i = 1;
                r2i = 0;
                r4i = 0;
                while (r6i < r15i) {
                    if (((ScanResult) this.for.get(r6i)).level != 0) {
                        r0i = r1i;
                        r1i = r4i;
                    } else if (r1i == 0) {
                        r8_StringBuffer.append("&wf=");
                        r10_String = ((ScanResult) this.for.get(r6i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY);
                        r8_StringBuffer.append(r10_String);
                        r0i = ((ScanResult) this.for.get(r6i)).level;
                        if (r0i >= 0) {
                            r0i = -r0i;
                        }
                        r11_ObjectA = new Object[1];
                        r11_ObjectA[0] = Integer.valueOf(r0i);
                        r8_StringBuffer.append(String.format(";%d;", r11_ObjectA));
                        r1i = r4i + 1;
                        if (r9_String == null || (!r9_String.equals(r10_String))) {
                            r0i = r2i;
                            r2i = r0i;
                            r0i = 0;
                        } else {
                            r0i = r1i;
                            r2i = r0i;
                            r0i = 0;
                        }
                    } else {
                        r8_StringBuffer.append("|");
                        r10_String = ((ScanResult) this.for.get(r6i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY);
                        r8_StringBuffer.append(r10_String);
                        r0i = ((ScanResult) this.for.get(r6i)).level;
                        if (r0i >= 0) {
                            r0i = -r0i;
                        }
                        r12_ObjectA = new Object[1];
                        r12_ObjectA[0] = Integer.valueOf(r0i);
                        r8_StringBuffer.append(String.format(";%d;", r12_ObjectA));
                        r0i = r4i + 1;
                        if (r9_String == null || (!r9_String.equals(r10_String))) {
                            r1i = r0i;
                            r0i = r1i;
                        } else {
                            r2i = r0i;
                            r1i = r0i;
                            r0i = r1i;
                        }
                    }
                    r6i++;
                    r4i = r1i;
                    r1i = r0i;
                }
                if (r1i == 0) {
                    return null;
                }
                j.if(a, r9_String + r2i);
                r8_StringBuffer.append("&wf_n=" + r2i);
                r8_StringBuffer.append("&wf_st=");
                r8_StringBuffer.append(this.b);
                r8_StringBuffer.append("&wf_et=");
                r8_StringBuffer.append(this.c);
                if (r2i <= 0) {
                    this.d = true;
                }
                return r8_StringBuffer.toString();
            } else {
                r15i = r0i;
                r6i = 0;
                r1i = 1;
                r2i = 0;
                r4i = 0;
                while (r6i < r15i) {
                    if (((ScanResult) this.for.get(r6i)).level != 0) {
                        if (r1i == 0) {
                            r8_StringBuffer.append("|");
                            r10_String = ((ScanResult) this.for.get(r6i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY);
                            r8_StringBuffer.append(r10_String);
                            r0i = ((ScanResult) this.for.get(r6i)).level;
                            if (r0i >= 0) {
                                r12_ObjectA = new Object[1];
                                r12_ObjectA[0] = Integer.valueOf(r0i);
                                r8_StringBuffer.append(String.format(";%d;", r12_ObjectA));
                                r0i = r4i + 1;
                                if (r9_String == null || r9_String.equals(r10_String)) {
                                    r1i = r0i;
                                    r0i = r1i;
                                } else {
                                    r2i = r0i;
                                    r1i = r0i;
                                    r0i = r1i;
                                }
                            } else {
                                r0i = -r0i;
                                r12_ObjectA = new Object[1];
                                r12_ObjectA[0] = Integer.valueOf(r0i);
                                r8_StringBuffer.append(String.format(";%d;", r12_ObjectA));
                                r0i = r4i + 1;
                                if (r9_String == null || r9_String.equals(r10_String)) {
                                    r1i = r0i;
                                    r0i = r1i;
                                } else {
                                    r2i = r0i;
                                    r1i = r0i;
                                    r0i = r1i;
                                }
                            }
                        } else {
                            r8_StringBuffer.append("&wf=");
                            r10_String = ((ScanResult) this.for.get(r6i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY);
                            r8_StringBuffer.append(r10_String);
                            r0i = ((ScanResult) this.for.get(r6i)).level;
                            if (r0i >= 0) {
                                r11_ObjectA = new Object[1];
                                r11_ObjectA[0] = Integer.valueOf(r0i);
                                r8_StringBuffer.append(String.format(";%d;", r11_ObjectA));
                                r1i = r4i + 1;
                                if (r9_String == null || r9_String.equals(r10_String)) {
                                    r0i = r2i;
                                    r2i = r0i;
                                    r0i = 0;
                                } else {
                                    r0i = r1i;
                                    r2i = r0i;
                                    r0i = 0;
                                }
                            } else {
                                r0i = -r0i;
                                r11_ObjectA = new Object[1];
                                r11_ObjectA[0] = Integer.valueOf(r0i);
                                r8_StringBuffer.append(String.format(";%d;", r11_ObjectA));
                                r1i = r4i + 1;
                                if (r9_String == null || r9_String.equals(r10_String)) {
                                    r0i = r2i;
                                    r2i = r0i;
                                    r0i = 0;
                                } else {
                                    r0i = r1i;
                                    r2i = r0i;
                                    r0i = 0;
                                }
                            }
                        }
                    } else {
                        r0i = r1i;
                        r1i = r4i;
                    }
                    r6i++;
                    r4i = r1i;
                    r1i = r0i;
                }
                if (r1i == 0) {
                    return null;
                }
                j.if(a, r9_String + r2i);
                r8_StringBuffer.append("&wf_n=" + r2i);
                r8_StringBuffer.append("&wf_st=");
                r8_StringBuffer.append(this.b);
                r8_StringBuffer.append("&wf_et=");
                r8_StringBuffer.append(this.c);
                if (r2i <= 0) {
                    return r8_StringBuffer.toString();
                }
                this.d = true;
                return r8_StringBuffer.toString();
            }
        }

        public boolean a(c r2_c) {
            return a(r2_c, this, j.a);
        }

        public boolean a(c r13_c, c r14_c, float r15f) {
            if (r13_c == null || r14_c == null) {
                return false;
            }
            List r6_List = r13_c.for;
            List r7_List = r14_c.for;
            if (r6_List == r7_List) {
                return true;
            }
            if (r6_List == null || r7_List == null) {
                return false;
            }
            int r8i = r6_List.size();
            int r9i = r7_List.size();
            float r10f = (float) (r8i + r9i);
            if (r8i == 0 && r9i == 0) {
                return true;
            }
            if (r8i == 0 || r9i == 0) {
                return false;
            }
            int r5i = 0;
            int r1i = 0;
            while (r5i < r8i) {
                int r0i;
                String r11_String = ((ScanResult) r6_List.get(r5i)).BSSID;
                if (r11_String == null) {
                    r0i = r1i;
                } else {
                    int r4i = 0;
                    while (r4i < r9i) {
                        if (r11_String.equals(((ScanResult) r7_List.get(r4i)).BSSID)) {
                            r0i = r1i + 1;
                            break;
                        } else {
                            r4i++;
                        }
                    }
                    r0i = r1i;
                }
                r5i++;
                r1i = r0i;
            }
            String r0_String = a;
            Object[] r5_ObjectA = new Object[3];
            r5_ObjectA[0] = Integer.valueOf(r1i);
            r5_ObjectA[1] = Float.valueOf(r10f);
            r5_ObjectA[2] = Float.valueOf(r15f);
            j.if(r0_String, String.format("same %d,total %f,rate %f...", r5_ObjectA));
            return (((float) (r1i * 2)) > (r10f * r15f) ? 1 : (((float) (r1i * 2)) == (r10f * r15f)? 0 : -1)) >= 0;
        }

        public String byte() {
            try {
                return a(NearbySelectView.TIME_15MIN);
            } catch (Exception e) {
                return null;
            }
        }

        public boolean case() {
            return this.d;
        }

        public String char() {
            try {
                return a(j.Y);
            } catch (Exception e) {
                return null;
            }
        }

        public int do() {
            int r1i = 0;
            while (r1i < try()) {
                int r0i = -((ScanResult) this.for.get(r1i)).level;
                if (r0i > 0) {
                    return r0i;
                }
                r1i++;
            }
            return 0;
        }

        public boolean do(c r8_c) {
            if (this.for == null || r8_c == null || r8_c.for == null) {
                return false;
            }
            int r1i;
            r1i = this.for.size() < r8_c.for.size() ? this.for.size() : r8_c.for.size();
            int r3i = 0;
            while (r3i < r1i) {
                String r4_String = ((ScanResult) this.for.get(r3i)).BSSID;
                int r5i = ((ScanResult) this.for.get(r3i)).level;
                String r6_String = ((ScanResult) r8_c.for.get(r3i)).BSSID;
                int r0i = ((ScanResult) r8_c.for.get(r3i)).level;
                if (!r4_String.equals(r6_String) || r5i != r0i) {
                    return false;
                }
                r3i++;
            }
            return true;
        }

        public String else() {
            StringBuffer r6_StringBuffer = new StringBuffer(512);
            r6_StringBuffer.append("wifi info:");
            if (try() < 1) {
                return r6_StringBuffer.toString();
            }
            int r1i = this.for.size();
            if (r1i > 10) {
                r1i = 10;
            }
            int r4i = 0;
            int r2i = 1;
            while (r4i < r1i) {
                int r0i;
                if (((ScanResult) this.for.get(r4i)).level == 0) {
                    r0i = r2i;
                } else if (r2i != 0) {
                    r6_StringBuffer.append("wifi=");
                    r6_StringBuffer.append(((ScanResult) this.for.get(r4i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY));
                    Object[] r7_ObjectA = new Object[1];
                    r7_ObjectA[0] = Integer.valueOf(((ScanResult) this.for.get(r4i)).level);
                    r6_StringBuffer.append(String.format(";%d;", r7_ObjectA));
                    r0i = 0;
                } else {
                    r6_StringBuffer.append(";");
                    r6_StringBuffer.append(((ScanResult) this.for.get(r4i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY));
                    Object[] r8_ObjectA = new Object[1];
                    r8_ObjectA[0] = Integer.valueOf(((ScanResult) this.for.get(r4i)).level);
                    r6_StringBuffer.append(String.format(",%d;", r8_ObjectA));
                    r0i = r2i;
                }
                r4i++;
                r2i = r0i;
            }
            return r6_StringBuffer.toString();
        }

        public boolean for() {
            return ((System.currentTimeMillis() - this.c) > 3000 ? 1 : ((System.currentTimeMillis() - this.c) == 3000? 0 : -1)) < 0;
        }

        public String if(int r6i) {
            int r1i = 1;
            if (r6i == 0 || try() < 1) {
                return null;
            }
            StringBuffer r4_StringBuffer = new StringBuffer(256);
            int r2i = 0;
            int r3i = r1i;
            r1i = 0;
            while (r1i < j.Y) {
                if ((r3i & r6i) != 0) {
                    if (r2i == 0) {
                        r4_StringBuffer.append("&ssid=");
                    } else {
                        r4_StringBuffer.append("|");
                    }
                    r4_StringBuffer.append(((ScanResult) this.for.get(r1i)).BSSID);
                    r4_StringBuffer.append(";");
                    r4_StringBuffer.append(((ScanResult) this.for.get(r1i)).SSID);
                    r2i++;
                }
                r3i <<= 1;
                r1i++;
            }
            return r4_StringBuffer.toString();
        }

        public boolean if() {
            return ((System.currentTimeMillis() - this.b) > 3000 ? 1 : ((System.currentTimeMillis() - this.b) == 3000? 0 : -1)) < 0;
        }

        public boolean if(c r6_c) {
            if (this.for == null || r6_c == null || r6_c.for == null) {
                return false;
            }
            int r1i;
            r1i = this.for.size() < r6_c.for.size() ? this.for.size() : r6_c.for.size();
            int r3i = 0;
            while (r3i < r1i) {
                if (!((ScanResult) this.for.get(r3i)).BSSID.equals(((ScanResult) r6_c.for.get(r3i)).BSSID)) {
                    return false;
                }
                r3i++;
            }
            return true;
        }

        public String int() {
            StringBuilder r3_StringBuilder = new StringBuilder();
            r3_StringBuilder.append("wifi=");
            if (this.for == null) {
                return r3_StringBuilder.toString();
            }
            int r1i = 0;
            while (r1i < this.for.size()) {
                int r4i = ((ScanResult) this.for.get(r1i)).level;
                r3_StringBuilder.append(((ScanResult) this.for.get(r1i)).BSSID.replace(":", RContactStorage.PRIMARY_KEY));
                Object[] r5_ObjectA = new Object[1];
                r5_ObjectA[0] = Integer.valueOf(r4i);
                r3_StringBuilder.append(String.format(",%d;", r5_ObjectA));
                r1i++;
            }
            return r3_StringBuilder.toString();
        }

        public boolean new() {
            return ((System.currentTimeMillis() - this.c) > 5000 ? 1 : ((System.currentTimeMillis() - this.c) == 5000? 0 : -1)) < 0;
        }

        public int try() {
            return this.for == null ? 0 : this.for.size();
        }
    }

    private class d implements Runnable {
        private d() {
        }

        public void run() {
            if (e.this.q && j.R) {
                e.this.f.obtainMessage(91).sendToTarget();
                e.this.f.postDelayed(this, (long) j.S);
                e.this.s = true;
            } else {
                e.this.s = false;
            }
        }
    }

    static {
        a = f.v;
    }

    public e(Context r7_Context, Handler r8_Handler) {
        this.b = 3000;
        this.c = 3000;
        this.d = 5000;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = null;
        this.o = null;
        this.p = true;
        this.q = false;
        this.r = false;
        this.s = false;
        this.e = r7_Context;
        this.f = r8_Handler;
    }

    private void c() {
        if (this.g == null) {
        } else {
            c r1_c;
            try {
                r1_c = new c(this.g.getScanResults(), this.k);
                this.k = 0;
            } catch (Exception e) {
            }
            if (this.j == null || (!r1_c.if(this.j))) {
                this.j = r1_c;
            }
        }
    }

    private void d() {
        State r0_State;
        try {
            r0_State = ((ConnectivityManager) this.e.getSystemService("connectivity")).getNetworkInfo(1).getState();
        } catch (Exception e) {
            r0_State = State.UNKNOWN;
        }
        if (State.CONNECTED == r0_State) {
            if (this.q) {
            } else {
                this.q = true;
                this.f.postDelayed(new d(null), (long) j.S);
                this.s = true;
            }
        } else {
            this.q = false;
        }
    }

    public boolean a() {
        long r0j = System.currentTimeMillis();
        if (r0j - this.l <= 10000) {
            return false;
        }
        this.l = r0j;
        return new();
    }

    public c byte() {
        if ((this.j != null && this.j.new()) || this.g == null) {
            return this.j;
        }
        try {
            return new c(this.g.getScanResults(), 0);
        } catch (Exception e) {
            return new c(null, 0);
        }
    }

    public void case() {
        if (this.i == null) {
            this.i = new a(null);
        }
    }

    public String char() {
        WifiInfo r1_WifiInfo = this.g.getConnectionInfo();
        if (r1_WifiInfo == null) {
            return null;
        }
        try {
            String r1_String = r1_WifiInfo.getBSSID();
            return r1_String != null ? r1_String.replace(":", RContactStorage.PRIMARY_KEY) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public void else() {
        if (this.m) {
            try {
                this.e.unregisterReceiver(this.h);
            } catch (Exception e) {
            }
            this.h = null;
            this.g = null;
            this.i = null;
            this.m = false;
            j.if(a, "wifimanager stop ...");
        }
    }

    public void for() {
        if (this.s) {
        }
    }

    public c int() {
        if ((this.j != null && this.j.for()) || this.g == null) {
            return this.j;
        }
        try {
            return new c(this.g.getScanResults(), 0);
        } catch (Exception e) {
            return new c(null, 0);
        }
    }

    public boolean new() {
        if (this.g == null) {
            return false;
        }
        long r3j = System.currentTimeMillis();
        if (r3j - this.k <= 3000) {
            return false;
        }
        try {
            if (this.g.isWifiEnabled()) {
                if (this.o == null || this.n == null) {
                    this.g.startScan();
                } else {
                    try {
                        Method r2_Method = this.o;
                        Object r5_Object = this.n;
                        Object[] r6_ObjectA = new Object[1];
                        r6_ObjectA[0] = Boolean.valueOf(this.p);
                        r2_Method.invoke(r5_Object, r6_ObjectA);
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.g.startScan();
                    }
                }
                this.k = r3j;
                j.if(a, "wifimanager start scan ...");
                return true;
            } else {
                this.k = 0;
                return false;
            }
        } catch (Exception e_2) {
            return false;
        }
    }

    public void try() {
        if (this.m) {
        } else {
            Field r0_Field;
            this.g = (WifiManager) this.e.getSystemService(NetworkManager.WIFI);
            this.h = new b(null);
            try {
                this.e.registerReceiver(this.h, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                this.i = new a(null);
            } catch (Exception e) {
            }
            this.m = true;
            j.if(a, "wifimanager start ...");
            try {
                r0_Field = Class.forName("android.net.wifi.WifiManager").getDeclaredField("mService");
            } catch (Exception e_2) {
                e_2.printStackTrace();
            }
            if (r0_Field == null) {
                j.if(a, "android.net.wifi.WifiManager.mService  NOT  found ...");
            } else {
                r0_Field.setAccessible(true);
                this.n = r0_Field.get(this.g);
                Class r0_Class = this.n.getClass();
                j.if(a, "mserviceClass : " + r0_Class.getName());
                Class[] r2_ClassA = new Class[1];
                r2_ClassA[0] = Boolean.TYPE;
                this.o = r0_Class.getDeclaredMethod("startScan", r2_ClassA);
                if (this.o == null) {
                    j.if(a, "mService.startScan NOT  found ...");
                } else {
                    this.o.setAccessible(true);
                }
            }
        }
    }
}