package com.tencent.mm.sdk.platformtools;

import android.os.Process;
import java.io.File;
import java.util.Scanner;

public final class TrafficStats {
    public static final String DEV_FILE = "/proc/self/net/dev";
    public static final String GPRSLINE = "rmnet0";
    public static final String WIFILINE = "tiwlan0";
    private static long a;
    private static long b;
    private static long c;
    private static long d;
    private static long e;
    private static long f;
    private static long g;
    private static long h;

    private TrafficStats() {
    }

    public static long getMobileRx(long r2j) {
        return (f > r2j ? 1 : (f == r2j? 0 : -1)) > 0 ? f : r2j;
    }

    public static long getMobileTx(long r2j) {
        return (e > r2j ? 1 : (e == r2j? 0 : -1)) > 0 ? e : r2j;
    }

    public static long getWifiRx(long r2j) {
        return (h > r2j ? 1 : (h == r2j? 0 : -1)) > 0 ? h : r2j;
    }

    public static long getWifiTx(long r2j) {
        return (g > r2j ? 1 : (g == r2j? 0 : -1)) > 0 ? g : r2j;
    }

    public static void reset() {
        a = -1;
        b = -1;
        c = -1;
        d = -1;
        update();
    }

    public static void update() {
        long r8j = 0;
        long r6j = 0;
        long r2j = 0;
        long r0j = 0;
        Object[] r10_ObjectA;
        try {
            Object[] r10_ObjectA_2;
            Scanner r5_Scanner = new Scanner(new File(new StringBuilder("/proc/").append(Process.myPid()).append("/net/dev").toString()));
            r5_Scanner.nextLine();
            r5_Scanner.nextLine();
            while (r5_Scanner.hasNext()) {
                int r4i;
                String[] r10_StringA = r5_Scanner.nextLine().split("[ :\t]+");
                r4i = r10_StringA[0].length() == 0 ? 1 : 0;
                if (r10_StringA[0].equals("lo") || (!r10_StringA[r4i + 0].startsWith("rmnet"))) {
                    if (r10_StringA[r4i + 0].equals("lo") || r10_StringA[r4i + 0].startsWith("rmnet")) {
                    } else {
                        r2j += Long.parseLong(r10_StringA[r4i + 9]);
                        r0j += Long.parseLong(r10_StringA[r4i + 1]);
                    }
                } else {
                    r8j += Long.parseLong(r10_StringA[r4i + 9]);
                    r6j += Long.parseLong(r10_StringA[r4i + 1]);
                    if (r10_StringA[r4i + 0].equals("lo") || r10_StringA[r4i + 0].startsWith("rmnet")) {
                    } else {
                        r2j += Long.parseLong(r10_StringA[r4i + 9]);
                        r0j += Long.parseLong(r10_StringA[r4i + 1]);
                    }
                }
            }
            r5_Scanner.close();
            if (a < 0) {
                a = r8j;
                r10_ObjectA_2 = new Object[1];
                r10_ObjectA_2[0] = Long.valueOf(r8j);
                Log.v("MicroMsg.SDK.TrafficStats", "fix loss newMobileTx %d", r10_ObjectA_2);
            }
            if (b < 0) {
                b = r6j;
                r10_ObjectA = new Object[1];
                r10_ObjectA[0] = Long.valueOf(r6j);
                Log.v("MicroMsg.SDK.TrafficStats", "fix loss newMobileRx %d", r10_ObjectA);
            }
            if (c < 0) {
                c = r2j;
                r10_ObjectA = new Object[1];
                r10_ObjectA[0] = Long.valueOf(r2j);
                Log.v("MicroMsg.SDK.TrafficStats", "fix loss newWifiTx %d", r10_ObjectA);
            }
            if (d < 0) {
                d = r0j;
                r10_ObjectA = new Object[1];
                r10_ObjectA[0] = Long.valueOf(r0j);
                Log.v("MicroMsg.SDK.TrafficStats", "fix loss newWifiRx %d", r10_ObjectA);
            }
            if (r0j - d < 0) {
                r10_ObjectA = new Object[1];
                r10_ObjectA[0] = Long.valueOf(r0j - d);
                Log.v("MicroMsg.SDK.TrafficStats", "minu %d", r10_ObjectA);
            }
            if (r2j - c < 0) {
                r10_ObjectA = new Object[1];
                r10_ObjectA[0] = Long.valueOf(r2j - c);
                Log.v("MicroMsg.SDK.TrafficStats", "minu %d", r10_ObjectA);
            }
            e = (r8j > a ? 1 : (r8j == a? 0 : -1)) >= 0 ? r8j - a : r8j;
            f = (r6j > b ? 1 : (r6j == b? 0 : -1)) >= 0 ? r6j - b : r6j;
            g = (r2j > c ? 1 : (r2j == c? 0 : -1)) >= 0 ? r2j - c : r2j;
            h = (r0j > d ? 1 : (r0j == d? 0 : -1)) >= 0 ? r0j - d : r0j;
            a = r8j;
            b = r6j;
            c = r2j;
            d = r0j;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object[] r2_ObjectA = new Object[4];
        r2_ObjectA[0] = Long.valueOf(h);
        r2_ObjectA[1] = Long.valueOf(g);
        r2_ObjectA[2] = Long.valueOf(f);
        r2_ObjectA[3] = Long.valueOf(e);
        Log.d("MicroMsg.SDK.TrafficStats", "current system traffic: wifi rx/tx=%d/%d, mobile rx/tx=%d/%d", r2_ObjectA);
    }

    public static long updateMobileRx(long r2j) {
        update();
        return getMobileRx(r2j);
    }

    public static long updateMobileTx(long r2j) {
        update();
        return getMobileTx(r2j);
    }

    public static long updateWifiRx(long r2j) {
        update();
        return getWifiRx(r2j);
    }

    public static long updateWifiTx(long r2j) {
        update();
        return getWifiTx(r2j);
    }
}