package com.zkmm.adsdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.cordova.NetworkManager;
import qsbk.app.activity.EditInfoEntranceActivity.EDIT_TYPE;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.bean.Base;
import qsbk.app.nearby.ui.NearByListActivity;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: SourceFile
final class m {
    protected static int A;
    protected static int B;
    protected static ZKMMKey C;
    protected static bg D;
    protected static int[] E;
    protected static boolean F;
    protected static boolean G;
    protected static boolean H;
    protected static boolean I;
    protected static double J;
    protected static double K;
    protected static double L;
    protected static SimpleDateFormat M;
    protected static String N;
    protected static String O;
    protected static String P;
    protected static boolean Q;
    protected static Handler R;
    protected static int S;
    protected static int T;
    protected static byte U;
    protected static int V;
    private static TelephonyManager W;
    private static byte[] X;
    private static String Y;
    private static byte Z;
    protected static String a;
    private static byte aa;
    private static HashMap ab;
    private static boolean ac;
    private static boolean ad;
    private static int ae;
    private static Location af;
    private static long ag;
    private static long ah;
    protected static byte[] b;
    protected static byte[] c;
    protected static byte[] d;
    protected static byte[] e;
    protected static byte[] f;
    protected static byte[] g;
    protected static String h;
    protected static String i;
    protected static String j;
    protected static String k;
    protected static String l;
    protected static String m;
    protected static byte n;
    protected static byte o;
    protected static byte p;
    protected static byte q;
    protected static byte r;
    protected static byte s;
    protected static byte t;
    protected static byte u;
    protected static byte v;
    protected static HashMap w;
    protected static HashMap x;
    protected static int y;
    protected static int z;

    static {
        a = null;
        b = null;
        c = null;
        d = null;
        e = EDIT_TYPE.TYPE_UNKNOWN.getBytes();
        f = null;
        g = null;
        X = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = RContactStorage.PRIMARY_KEY;
        m = "UNKNOWN";
        Z = (byte) 2;
        n = (byte) -1;
        o = (byte) 0;
        p = (byte) 0;
        q = (byte) 0;
        r = (byte) 0;
        aa = (byte) 0;
        s = (byte) 8;
        t = (byte) 64;
        u = (byte) 1;
        v = (byte) 0;
        w = new HashMap();
        x = new HashMap();
        ab = new HashMap();
        y = 800;
        z = 480;
        A = 320;
        B = 50;
        E = new int[0];
        F = false;
        ac = false;
        ad = false;
        G = false;
        H = false;
        I = false;
        J = 0.0d;
        K = 0.0d;
        L = 1.0d;
        SimpleDateFormat r0_SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        M = r0_SimpleDateFormat;
        N = r0_SimpleDateFormat.format(new Date());
        ae = 30;
        O = "/mnt/sdcard/adwo/";
        P = "/mnt/sdcard/adwo/entryad/";
        Q = false;
        af = null;
        R = null;
        ag = 0;
        ah = 0;
        S = 20;
        T = 0;
        U = (byte) 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static synchronized j a(Context r13_Context, byte r14b) {
        /*
        r11 = -1;
        r8 = 0;
        r10 = com.zkmm.adsdk.m.class;
        monitor-enter(r10);
        r0 = "android.permission.INTERNET";
        r0 = r13.checkCallingOrSelfPermission(r0);	 //Catch:{ all -> 0x0121 }
        if (r0 != r11) goto L_0x0015;
    L_0x000d:
        r0 = "Can not make an ad request without INTERNET permission! Please add:  <uses-permission android:name=\"android.permission.INTERNET\" /> inside the </manifest> tag of the 'manifest.xml' file.";
        c(r0);	 //Catch:{ all -> 0x0121 }
        r0 = r8;
    L_0x0013:
        monitor-exit(r10);
        return r0;
    L_0x0015:
        r0 = ac;	 //Catch:{ all -> 0x0121 }
        r1 = ac;	 //Catch:{ Exception -> 0x012e }
        if (r1 == 0) goto L_0x0051;
    L_0x001b:
        r1 = new java.net.URL;	 //Catch:{ Exception -> 0x012e }
        r2 = "http://adtest.adwo.com/adaweb32";
        r1.<init>(r2);	 //Catch:{ Exception -> 0x012e }
        r9 = r1;
    L_0x0023:
        r1 = d();	 //Catch:{ Exception -> 0x012e }
        if (r1 == 0) goto L_0x0033;
    L_0x0029:
        r2 = 0;
        r2 = r1[r2];	 //Catch:{ Exception -> 0x012e }
        J = r2;	 //Catch:{ Exception -> 0x012e }
        r2 = 1;
        r1 = r1[r2];	 //Catch:{ Exception -> 0x012e }
        K = r1;	 //Catch:{ Exception -> 0x012e }
    L_0x0033:
        r1 = N;	 //Catch:{ Exception -> 0x012e }
        r7 = com.zkmm.adsdk.s.a(r13, r1);	 //Catch:{ Exception -> 0x012e }
        r1 = F;	 //Catch:{ Exception -> 0x012e }
        r2 = K;	 //Catch:{ Exception -> 0x012e }
        r4 = J;	 //Catch:{ Exception -> 0x012e }
        r6 = 0;
        r1 = a(r0, r1, r2, r4, r6, r7);	 //Catch:{ Exception -> 0x012e }
        f(r13);	 //Catch:{ Exception -> 0x012e }
        r0 = r9.openConnection();	 //Catch:{ Exception -> 0x012e }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ Exception -> 0x012e }
        if (r0 != 0) goto L_0x005e;
    L_0x004f:
        r0 = r8;
        goto L_0x0013;
    L_0x0051:
        r1 = D;	 //Catch:{ Exception -> 0x012e }
        r2 = r1.a();	 //Catch:{ Exception -> 0x012e }
        r1 = new java.net.URL;	 //Catch:{ Exception -> 0x012e }
        r1.<init>(r2);	 //Catch:{ Exception -> 0x012e }
        r9 = r1;
        goto L_0x0023;
    L_0x005e:
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ Exception -> 0x0133 }
        r0.setConnectTimeout(r2);	 //Catch:{ Exception -> 0x0133 }
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ Exception -> 0x0133 }
        r0.setReadTimeout(r2);	 //Catch:{ Exception -> 0x0133 }
        r2 = "POST";
        r0.setRequestMethod(r2);	 //Catch:{ Exception -> 0x0133 }
        r2 = 1;
        r0.setDoOutput(r2);	 //Catch:{ Exception -> 0x0133 }
        r2 = "Content-Type";
        r3 = "application/x-www-form-urlencoded";
        r0.setRequestProperty(r2, r3);	 //Catch:{ Exception -> 0x0133 }
        if (r1 == 0) goto L_0x0084;
    L_0x007a:
        r2 = "Content-Length";
        r3 = r1.length;	 //Catch:{ Exception -> 0x0133 }
        r3 = java.lang.Integer.toString(r3);	 //Catch:{ Exception -> 0x0133 }
        r0.setRequestProperty(r2, r3);	 //Catch:{ Exception -> 0x0133 }
    L_0x0084:
        r0.connect();	 //Catch:{ Exception -> 0x0133 }
        r3 = r0.getOutputStream();	 //Catch:{ Exception -> 0x0133 }
        if (r1 == 0) goto L_0x0090;
    L_0x008d:
        r3.write(r1);	 //Catch:{ Exception -> 0x013a }
    L_0x0090:
        r2 = r0.getInputStream();	 //Catch:{ Exception -> 0x013a }
        r1 = new java.io.ByteArrayOutputStream;	 //Catch:{ Exception -> 0x0100 }
        r1.<init>();	 //Catch:{ Exception -> 0x0100 }
    L_0x0099:
        r4 = r2.read();	 //Catch:{ Exception -> 0x0100 }
        if (r4 != r11) goto L_0x00fc;
    L_0x009f:
        r1 = r1.toByteArray();	 //Catch:{ Exception -> 0x0100 }
        r4 = r1.length;	 //Catch:{ Exception -> 0x0100 }
        if (r4 <= 0) goto L_0x0124;
    L_0x00a6:
        r4 = com.zkmm.adsdk.bz.b(r1);	 //Catch:{ Exception -> 0x0100 }
        r1 = r4.b;	 //Catch:{ Exception -> 0x0100 }
        if (r1 != 0) goto L_0x00e6;
    L_0x00ae:
        r1 = "Adwo SDK";
        r5 = "Get a new banner.";
        android.util.Log.d(r1, r5);	 //Catch:{ Exception -> 0x0100 }
        r1 = ab;	 //Catch:{ Exception -> 0x0100 }
        r5 = r4.a;	 //Catch:{ Exception -> 0x0100 }
        r5 = java.lang.Integer.valueOf(r5);	 //Catch:{ Exception -> 0x0100 }
        r1 = r1.containsKey(r5);	 //Catch:{ Exception -> 0x0100 }
        if (r1 == 0) goto L_0x0110;
    L_0x00c3:
        r1 = ab;	 //Catch:{ Exception -> 0x0100 }
        r5 = r4.a;	 //Catch:{ Exception -> 0x0100 }
        r5 = java.lang.Integer.valueOf(r5);	 //Catch:{ Exception -> 0x0100 }
        r1 = r1.get(r5);	 //Catch:{ Exception -> 0x0100 }
        r1 = (java.lang.Integer) r1;	 //Catch:{ Exception -> 0x0100 }
        r1 = r1.intValue();	 //Catch:{ Exception -> 0x0100 }
        r1 = r1 + 1;
        r5 = ab;	 //Catch:{ Exception -> 0x0100 }
        r6 = r4.a;	 //Catch:{ Exception -> 0x0100 }
        r6 = java.lang.Integer.valueOf(r6);	 //Catch:{ Exception -> 0x0100 }
        r1 = java.lang.Integer.valueOf(r1);	 //Catch:{ Exception -> 0x0100 }
        r5.put(r6, r1);	 //Catch:{ Exception -> 0x0100 }
    L_0x00e6:
        r1 = r0;
        r0 = r4;
    L_0x00e8:
        if (r2 == 0) goto L_0x00ed;
    L_0x00ea:
        r2.close();	 //Catch:{ Exception -> 0x00f9 }
    L_0x00ed:
        if (r3 == 0) goto L_0x00f2;
    L_0x00ef:
        r3.close();	 //Catch:{ Exception -> 0x00f9 }
    L_0x00f2:
        if (r1 == 0) goto L_0x0013;
    L_0x00f4:
        r1.disconnect();	 //Catch:{ Exception -> 0x00f9 }
        goto L_0x0013;
    L_0x00f9:
        r1 = move-exception;
        goto L_0x0013;
    L_0x00fc:
        r1.write(r4);	 //Catch:{ Exception -> 0x0100 }
        goto L_0x0099;
    L_0x0100:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
    L_0x0104:
        r0.printStackTrace();	 //Catch:{ all -> 0x0121 }
        r0 = "Adwo SDK";
        r4 = "Could not get an ad from Adwo servers,Network Error!";
        android.util.Log.w(r0, r4);	 //Catch:{ all -> 0x0121 }
        r0 = r8;
        goto L_0x00e8;
    L_0x0110:
        r1 = ab;	 //Catch:{ Exception -> 0x0100 }
        r5 = r4.a;	 //Catch:{ Exception -> 0x0100 }
        r5 = java.lang.Integer.valueOf(r5);	 //Catch:{ Exception -> 0x0100 }
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 //Catch:{ Exception -> 0x0100 }
        r1.put(r5, r6);	 //Catch:{ Exception -> 0x0100 }
        goto L_0x00e6;
    L_0x0121:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0124:
        r1 = "Adwo SDK";
        r4 = "Could not get an ad from Adwo servers.";
        android.util.Log.w(r1, r4);	 //Catch:{ Exception -> 0x0100 }
        r1 = r0;
        r0 = r8;
        goto L_0x00e8;
    L_0x012e:
        r0 = move-exception;
        r1 = r8;
        r2 = r8;
        r3 = r8;
        goto L_0x0104;
    L_0x0133:
        r1 = move-exception;
        r2 = r8;
        r3 = r8;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x0104;
    L_0x013a:
        r1 = move-exception;
        r2 = r8;
        r12 = r0;
        r0 = r1;
        r1 = r12;
        goto L_0x0104;
        */

    }

    protected static String a(Context r4_Context) {
        try {
            ApplicationInfo r1_ApplicationInfo = r4_Context.getPackageManager().getApplicationInfo(r4_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            return r1_ApplicationInfo != null ? r1_ApplicationInfo.metaData.getString("Adwo_PID") : null;
        } catch (Exception e) {
            Log.e("Adwo SDK", "Could not read 'Adwo_PID meta-data' from the AndroidManifest.xml.");
            return null;
        }
    }

    protected static void a(boolean r0z) {
        ad = r0z;
    }

    protected static boolean a() {
        long r1j = System.currentTimeMillis();
        if (ag == 0) {
            ag = r1j;
            return false;
        } else if (r1j - ag < 15000) {
            Log.e("Adwo SDK", "Requests have been too frequent, Pleae make sure just one request after another within 15 seconds.");
            return true;
        } else {
            ag = r1j;
            return false;
        }
    }

    public static boolean a(String r1_String) {
        return Pattern.compile("[0-9]*").matcher(r1_String).matches();
    }

    private static byte[] a(boolean r34z, boolean r35z, double r36d, double r38d, byte r40b, int r41i) {
        byte[] r7_byteA;
        byte[] r0_byteA = null;
        if (bg.b != null) {
            try {
                r0_byteA = bg.b.getBytes(Base.UTF8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            b = r0_byteA;
            r7_byteA = r0_byteA;
        } else {
            r7_byteA = b;
        }
        return bz.a((byte) 0, t, s, r, aa, Z, r34z, r7_byteA, c, q, X, (short) z, (short) y, (short) A, (short) B, l, L, d, e, f, g, n, p, r35z, r36d, r38d, Y, x, w, ab, r41i);
    }

    protected static int b(Context r4_Context) {
        try {
            PackageInfo r1_PackageInfo = r4_Context.getPackageManager().getPackageInfo(r4_Context.getPackageName(), 0);
            return r1_PackageInfo != null ? r1_PackageInfo.versionCode : 0;
        } catch (Exception e) {
            Log.e("Adwo SDK", "Could not read the versionCode of the package from the AndroidManifest.xml.");
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static synchronized g b(Context r13_Context, byte r14b) {
        /*
        r11 = -1;
        r8 = 0;
        r10 = com.zkmm.adsdk.m.class;
        monitor-enter(r10);
        r0 = "android.permission.INTERNET";
        r0 = r13.checkCallingOrSelfPermission(r0);	 //Catch:{ all -> 0x0122 }
        if (r0 != r11) goto L_0x0015;
    L_0x000d:
        r0 = "Can not make an ad request without INTERNET permission! Please add:  <uses-permission android:name=\"android.permission.INTERNET\" /> inside the </manifest> tag of the 'manifest.xml' file.";
        c(r0);	 //Catch:{ all -> 0x0122 }
        r0 = r8;
    L_0x0013:
        monitor-exit(r10);
        return r0;
    L_0x0015:
        r0 = ad;	 //Catch:{ all -> 0x0122 }
        r1 = ad;	 //Catch:{ Exception -> 0x012f }
        if (r1 == 0) goto L_0x0051;
    L_0x001b:
        r1 = new java.net.URL;	 //Catch:{ Exception -> 0x012f }
        r2 = "http://adtest.adwo.com/adfsa32";
        r1.<init>(r2);	 //Catch:{ Exception -> 0x012f }
        r9 = r1;
    L_0x0023:
        r1 = d();	 //Catch:{ Exception -> 0x012f }
        if (r1 == 0) goto L_0x0033;
    L_0x0029:
        r2 = 0;
        r2 = r1[r2];	 //Catch:{ Exception -> 0x012f }
        J = r2;	 //Catch:{ Exception -> 0x012f }
        r2 = 1;
        r1 = r1[r2];	 //Catch:{ Exception -> 0x012f }
        K = r1;	 //Catch:{ Exception -> 0x012f }
    L_0x0033:
        r1 = N;	 //Catch:{ Exception -> 0x012f }
        r7 = com.zkmm.adsdk.s.a(r13, r1);	 //Catch:{ Exception -> 0x012f }
        r1 = F;	 //Catch:{ Exception -> 0x012f }
        r2 = K;	 //Catch:{ Exception -> 0x012f }
        r4 = J;	 //Catch:{ Exception -> 0x012f }
        r6 = 0;
        r1 = a(r0, r1, r2, r4, r6, r7);	 //Catch:{ Exception -> 0x012f }
        f(r13);	 //Catch:{ Exception -> 0x012f }
        r0 = r9.openConnection();	 //Catch:{ Exception -> 0x012f }
        r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ Exception -> 0x012f }
        if (r0 != 0) goto L_0x005f;
    L_0x004f:
        r0 = r8;
        goto L_0x0013;
    L_0x0051:
        r1 = D;	 //Catch:{ Exception -> 0x012f }
        r1.a();	 //Catch:{ Exception -> 0x012f }
        r1 = new java.net.URL;	 //Catch:{ Exception -> 0x012f }
        r2 = com.zkmm.adsdk.bg.c;	 //Catch:{ Exception -> 0x012f }
        r1.<init>(r2);	 //Catch:{ Exception -> 0x012f }
        r9 = r1;
        goto L_0x0023;
    L_0x005f:
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ Exception -> 0x0134 }
        r0.setConnectTimeout(r2);	 //Catch:{ Exception -> 0x0134 }
        r2 = com.zkmm.adsdk.s.a;	 //Catch:{ Exception -> 0x0134 }
        r0.setReadTimeout(r2);	 //Catch:{ Exception -> 0x0134 }
        r2 = "POST";
        r0.setRequestMethod(r2);	 //Catch:{ Exception -> 0x0134 }
        r2 = 1;
        r0.setDoOutput(r2);	 //Catch:{ Exception -> 0x0134 }
        r2 = "Content-Type";
        r3 = "application/x-www-form-urlencoded";
        r0.setRequestProperty(r2, r3);	 //Catch:{ Exception -> 0x0134 }
        if (r1 == 0) goto L_0x0085;
    L_0x007b:
        r2 = "Content-Length";
        r3 = r1.length;	 //Catch:{ Exception -> 0x0134 }
        r3 = java.lang.Integer.toString(r3);	 //Catch:{ Exception -> 0x0134 }
        r0.setRequestProperty(r2, r3);	 //Catch:{ Exception -> 0x0134 }
    L_0x0085:
        r0.connect();	 //Catch:{ Exception -> 0x0134 }
        r3 = r0.getOutputStream();	 //Catch:{ Exception -> 0x0134 }
        if (r1 == 0) goto L_0x0091;
    L_0x008e:
        r3.write(r1);	 //Catch:{ Exception -> 0x013b }
    L_0x0091:
        r2 = r0.getInputStream();	 //Catch:{ Exception -> 0x013b }
        r1 = new java.io.ByteArrayOutputStream;	 //Catch:{ Exception -> 0x0101 }
        r1.<init>();	 //Catch:{ Exception -> 0x0101 }
    L_0x009a:
        r4 = r2.read();	 //Catch:{ Exception -> 0x0101 }
        if (r4 != r11) goto L_0x00fd;
    L_0x00a0:
        r1 = r1.toByteArray();	 //Catch:{ Exception -> 0x0101 }
        r4 = r1.length;	 //Catch:{ Exception -> 0x0101 }
        if (r4 <= 0) goto L_0x0125;
    L_0x00a7:
        r4 = com.zkmm.adsdk.bz.a(r1);	 //Catch:{ Exception -> 0x0101 }
        r1 = r4.m;	 //Catch:{ Exception -> 0x0101 }
        if (r1 != 0) goto L_0x00e7;
    L_0x00af:
        r1 = "Adwo SDK";
        r5 = "Get a full screen ad from Adwo servers.";
        android.util.Log.d(r1, r5);	 //Catch:{ Exception -> 0x0101 }
        r1 = ab;	 //Catch:{ Exception -> 0x0101 }
        r5 = r4.a;	 //Catch:{ Exception -> 0x0101 }
        r5 = java.lang.Integer.valueOf(r5);	 //Catch:{ Exception -> 0x0101 }
        r1 = r1.containsKey(r5);	 //Catch:{ Exception -> 0x0101 }
        if (r1 == 0) goto L_0x0111;
    L_0x00c4:
        r1 = ab;	 //Catch:{ Exception -> 0x0101 }
        r5 = r4.a;	 //Catch:{ Exception -> 0x0101 }
        r5 = java.lang.Integer.valueOf(r5);	 //Catch:{ Exception -> 0x0101 }
        r1 = r1.get(r5);	 //Catch:{ Exception -> 0x0101 }
        r1 = (java.lang.Integer) r1;	 //Catch:{ Exception -> 0x0101 }
        r1 = r1.intValue();	 //Catch:{ Exception -> 0x0101 }
        r1 = r1 + 1;
        r5 = ab;	 //Catch:{ Exception -> 0x0101 }
        r6 = r4.a;	 //Catch:{ Exception -> 0x0101 }
        r6 = java.lang.Integer.valueOf(r6);	 //Catch:{ Exception -> 0x0101 }
        r1 = java.lang.Integer.valueOf(r1);	 //Catch:{ Exception -> 0x0101 }
        r5.put(r6, r1);	 //Catch:{ Exception -> 0x0101 }
    L_0x00e7:
        r1 = r0;
        r0 = r4;
    L_0x00e9:
        if (r2 == 0) goto L_0x00ee;
    L_0x00eb:
        r2.close();	 //Catch:{ Exception -> 0x00fa }
    L_0x00ee:
        if (r3 == 0) goto L_0x00f3;
    L_0x00f0:
        r3.close();	 //Catch:{ Exception -> 0x00fa }
    L_0x00f3:
        if (r1 == 0) goto L_0x0013;
    L_0x00f5:
        r1.disconnect();	 //Catch:{ Exception -> 0x00fa }
        goto L_0x0013;
    L_0x00fa:
        r1 = move-exception;
        goto L_0x0013;
    L_0x00fd:
        r1.write(r4);	 //Catch:{ Exception -> 0x0101 }
        goto L_0x009a;
    L_0x0101:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
    L_0x0105:
        r0.printStackTrace();	 //Catch:{ all -> 0x0122 }
        r0 = "Adwo SDK";
        r4 = "Could not get an ad from Adwo servers,Network Error!";
        android.util.Log.w(r0, r4);	 //Catch:{ all -> 0x0122 }
        r0 = r8;
        goto L_0x00e9;
    L_0x0111:
        r1 = ab;	 //Catch:{ Exception -> 0x0101 }
        r5 = r4.a;	 //Catch:{ Exception -> 0x0101 }
        r5 = java.lang.Integer.valueOf(r5);	 //Catch:{ Exception -> 0x0101 }
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 //Catch:{ Exception -> 0x0101 }
        r1.put(r5, r6);	 //Catch:{ Exception -> 0x0101 }
        goto L_0x00e7;
    L_0x0122:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0125:
        r1 = "Adwo SDK";
        r4 = "Could not get an ad from Adwo servers.";
        android.util.Log.w(r1, r4);	 //Catch:{ Exception -> 0x0101 }
        r1 = r0;
        r0 = r8;
        goto L_0x00e9;
    L_0x012f:
        r0 = move-exception;
        r1 = r8;
        r2 = r8;
        r3 = r8;
        goto L_0x0105;
    L_0x0134:
        r1 = move-exception;
        r2 = r8;
        r3 = r8;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x0105;
    L_0x013b:
        r1 = move-exception;
        r2 = r8;
        r12 = r0;
        r0 = r1;
        r1 = r12;
        goto L_0x0105;
        */

    }

    protected static void b(String r3_String) {
        if (r3_String == null || r3_String.length() != 32) {
            c(new StringBuilder("CONFIGURATION ERROR:  Incorrect Adwo publisher ID. It should be composed of 32 [a-z,0-9] characters:  ").append(b).toString());
            Log.d("Adwo SDK", new StringBuilder("Adwo PID is ").append(r3_String).toString());
            try {
                b = r3_String.getBytes(Base.UTF8);
            } catch (UnsupportedEncodingException e) {
                c(new StringBuilder("CONFIGURATION ERROR:  Incorrect Adwo publisher ID. It should be composed of 32 [a-z,0-9] characters:  ").append(b).toString());
            }
        } else {
            Log.d("Adwo SDK", new StringBuilder("Adwo PID is ").append(r3_String).toString());
            b = r3_String.getBytes(Base.UTF8);
        }
    }

    protected static void b(boolean r0z) {
        ac = r0z;
    }

    protected static boolean b() {
        long r1j = System.currentTimeMillis();
        if (ah == 0) {
            ah = r1j;
            return false;
        } else if (r1j - ah < ((long) (S * 1000))) {
            Log.e("Adwo SDK", "Requests have been too frequent, Pleae make sure just one request after another within 20 seconds.");
            return true;
        } else {
            ah = r1j;
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void c(Context r10_Context) {
        /*
        r5 = 2;
        r6 = 16;
        r7 = 3;
        r3 = 1;
        r4 = 0;
        r0 = new java.io.File;
        r1 = "/system/bin/su";
        r0.<init>(r1);
        r0 = r0.exists();
        if (r0 == 0) goto L_0x001c;
    L_0x0013:
        p = r3;
        r0 = "Adwo SDK";
        r1 = "The device is rooted.";
        android.util.Log.e(r0, r1);
    L_0x001c:
        r0 = android.os.Environment.getExternalStorageState();
        r1 = "mounted";
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x006d;
    L_0x0028:
        q = r3;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = android.os.Environment.getExternalStorageDirectory();
        r0 = r0.append(r1);
        r1 = "/ad";
        r0 = r0.append(r1);
        r1 = "wo/";
        r0 = r0.append(r1);
        r0 = r0.toString();
        O = r0;
        r0 = new java.lang.StringBuilder;
        r1 = O;
        r1 = java.lang.String.valueOf(r1);
        r0.<init>(r1);
        r1 = "entryad/";
        r0 = r0.append(r1);
        r0 = r0.toString();
        P = r0;
        r0 = new java.lang.Thread;
        r1 = new com.zkmm.adsdk.n;
        r1.<init>(r10);
        r0.<init>(r1);
        r0.start();
    L_0x006d:
        r0 = W;
        if (r0 != 0) goto L_0x0235;
    L_0x0071:
        r0 = "phone";
        r0 = r10.getSystemService(r0);
        r0 = (android.telephony.TelephonyManager) r0;
        W = r0;
        r0 = "android.permission.READ_PHONE_STATE";
        r0 = r10.checkCallingOrSelfPermission(r0);
        r1 = -1;
        if (r0 != r1) goto L_0x0089;
    L_0x0084:
        r0 = "Cannot make an ad request without READ_PHONE_STATE permissions!  Please add:  <uses-permission android:name=\"android.permission.READ_PHONE_STATE\" /> in the AndroidManifest.xml file.";
        c(r0);
    L_0x0089:
        r0 = 0;
        r1 = h;
        if (r1 != 0) goto L_0x039e;
    L_0x008e:
        r1 = W;
        if (r1 == 0) goto L_0x039e;
    L_0x0092:
        r0 = W;
        r0 = r0.getDeviceId();
        r1 = r0;
    L_0x0099:
        r0 = 0;
        r2 = r10.getContentResolver();	 //Catch:{ Exception -> 0x0236 }
        r8 = "android_id";
        r0 = android.provider.Settings.System.getString(r2, r8);	 //Catch:{ Exception -> 0x0236 }
        r2 = r0;
    L_0x00a5:
        r8 = 0;
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = r10.checkCallingOrSelfPermission(r0);
        if (r0 != 0) goto L_0x039b;
    L_0x00ae:
        r0 = "wifi";
        r0 = r10.getSystemService(r0);
        r0 = (android.net.wifi.WifiManager) r0;
        r0 = r0.getConnectionInfo();
        if (r0 == 0) goto L_0x039b;
    L_0x00bc:
        r0 = r0.getMacAddress();
        if (r0 == 0) goto L_0x00d2;
    L_0x00c2:
        r8 = ":";
        r9 = "";
        r0 = r0.replace(r8, r9);	 //Catch:{ UnsupportedEncodingException -> 0x038f }
        r8 = "UTF-8";
        r8 = r0.getBytes(r8);	 //Catch:{ UnsupportedEncodingException -> 0x038f }
        X = r8;	 //Catch:{ UnsupportedEncodingException -> 0x038f }
    L_0x00d2:
        if (r1 == 0) goto L_0x023a;
    L_0x00d4:
        if (r2 != 0) goto L_0x00d7;
    L_0x00d6:
        r2 = r1;
    L_0x00d7:
        r0 = new java.lang.StringBuilder;
        r1 = java.lang.String.valueOf(r1);
        r0.<init>(r1);
        r1 = "|";
        r0 = r0.append(r1);
        r0 = r0.append(r2);
        r0 = r0.toString();
        h = r0;
    L_0x00f0:
        r0 = h;	 //Catch:{ UnsupportedEncodingException -> 0x027b }
        if (r0 == 0) goto L_0x0271;
    L_0x00f4:
        r0 = h;	 //Catch:{ UnsupportedEncodingException -> 0x027b }
        r1 = "UTF-8";
        r0 = r0.getBytes(r1);	 //Catch:{ UnsupportedEncodingException -> 0x027b }
        c = r0;	 //Catch:{ UnsupportedEncodingException -> 0x027b }
    L_0x00fe:
        r0 = h;
        r0 = r0.toUpperCase();
        r0 = com.zkmm.adsdk.s.b(r0);
        i = r0;
        r0 = android.os.Environment.getExternalStorageState();
        r1 = "mounted";
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x0398;
    L_0x0116:
        r0 = r3;
    L_0x0117:
        r1 = r10.getResources();
        r1 = r1.getConfiguration();
        r1 = r1.locale;
        r1 = r1.getLanguage();
        r2 = "en";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x0281;
    L_0x012d:
        if (r0 == 0) goto L_0x027e;
    L_0x012f:
        r3 = 18;
    L_0x0131:
        Z = r3;
        r0 = e(r10);
        if (r0 != 0) goto L_0x013d;
    L_0x0139:
        r0 = r10.getPackageName();
    L_0x013d:
        r1 = "UTF-8";
        r0 = r0.getBytes(r1);	 //Catch:{ UnsupportedEncodingException -> 0x0321 }
        f = r0;	 //Catch:{ UnsupportedEncodingException -> 0x0321 }
    L_0x0145:
        r0 = android.os.Build.VERSION.RELEASE;
        if (r0 == 0) goto L_0x0181;
    L_0x0149:
        r1 = r0.length();
        if (r1 < r7) goto L_0x0181;
    L_0x014f:
        r1 = 0;
        r2 = 1;
        r1 = r0.substring(r1, r2);	 //Catch:{ Exception -> 0x038c }
        r1 = java.lang.Integer.parseInt(r1);	 //Catch:{ Exception -> 0x038c }
        r1 = (byte) r1;	 //Catch:{ Exception -> 0x038c }
        r = r1;	 //Catch:{ Exception -> 0x038c }
        r1 = 2;
        r2 = 3;
        r1 = r0.substring(r1, r2);	 //Catch:{ Exception -> 0x038c }
        r1 = java.lang.Integer.parseInt(r1);	 //Catch:{ Exception -> 0x038c }
        r1 = (byte) r1;	 //Catch:{ Exception -> 0x038c }
        r2 = r0.length();	 //Catch:{ Exception -> 0x038c }
        r3 = 5;
        if (r2 != r3) goto L_0x0395;
    L_0x016e:
        r2 = 4;
        r3 = 5;
        r0 = r0.substring(r2, r3);	 //Catch:{ Exception -> 0x038c }
        r0 = java.lang.Integer.parseInt(r0);	 //Catch:{ Exception -> 0x038c }
        r0 = (byte) r0;	 //Catch:{ Exception -> 0x038c }
    L_0x0179:
        r1 = r1 << 4;
        r0 = r0 & 255;
        r0 = r0 | r1;
        r0 = (byte) r0;	 //Catch:{ Exception -> 0x038c }
        aa = r0;	 //Catch:{ Exception -> 0x038c }
    L_0x0181:
        r0 = android.os.Build.class;
        r1 = android.os.Build.MODEL;	 //Catch:{ Exception -> 0x0389 }
        r1 = r1.toLowerCase();	 //Catch:{ Exception -> 0x0389 }
        r2 = "UTF-8";
        r1 = r1.getBytes(r2);	 //Catch:{ Exception -> 0x0389 }
        e = r1;	 //Catch:{ Exception -> 0x0389 }
        r1 = "MANUFACTURER";
        r0 = r0.getField(r1);	 //Catch:{ Exception -> 0x0389 }
        r1 = new android.os.Build;	 //Catch:{ Exception -> 0x0389 }
        r1.<init>();	 //Catch:{ Exception -> 0x0389 }
        r0 = r0.get(r1);	 //Catch:{ Exception -> 0x0389 }
        r0 = (java.lang.String) r0;	 //Catch:{ Exception -> 0x0389 }
        if (r0 == 0) goto L_0x01b6;
    L_0x01a4:
        r1 = r0.length();	 //Catch:{ Exception -> 0x0389 }
        if (r1 <= 0) goto L_0x01b6;
    L_0x01aa:
        r0 = r0.toLowerCase();	 //Catch:{ Exception -> 0x0389 }
        r1 = "UTF-8";
        r0 = r0.getBytes(r1);	 //Catch:{ Exception -> 0x0389 }
        d = r0;	 //Catch:{ Exception -> 0x0389 }
    L_0x01b6:
        r0 = W;	 //Catch:{ Exception -> 0x0386 }
        r0 = r0.getNetworkOperator();	 //Catch:{ Exception -> 0x0386 }
        r1 = 0;
        r2 = 3;
        r1 = r0.substring(r1, r2);	 //Catch:{ Exception -> 0x0386 }
        r2 = 3;
        r0 = r0.substring(r2);	 //Catch:{ Exception -> 0x0386 }
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0386 }
        r1 = java.lang.String.valueOf(r1);	 //Catch:{ Exception -> 0x0386 }
        r2.<init>(r1);	 //Catch:{ Exception -> 0x0386 }
        r1 = "_";
        r1 = r2.append(r1);	 //Catch:{ Exception -> 0x0386 }
        r0 = r1.append(r0);	 //Catch:{ Exception -> 0x0386 }
        r0 = r0.toString();	 //Catch:{ Exception -> 0x0386 }
        r1 = "UTF-8";
        r0 = r0.getBytes(r1);	 //Catch:{ Exception -> 0x0386 }
        g = r0;	 //Catch:{ Exception -> 0x0386 }
    L_0x01e6:
        f(r10);
        r0 = 0;
        r3 = "0000000000000000";
        r2 = h;	 //Catch:{ NumberFormatException -> 0x0329 }
        if (r2 == 0) goto L_0x0392;
    L_0x01f1:
        r2 = h;	 //Catch:{ NumberFormatException -> 0x0329 }
        r2 = r2.length();	 //Catch:{ NumberFormatException -> 0x0329 }
        r7 = 8;
        if (r2 <= r7) goto L_0x0216;
    L_0x01fb:
        r7 = new java.lang.StringBuilder;	 //Catch:{ NumberFormatException -> 0x0384 }
        r8 = h;	 //Catch:{ NumberFormatException -> 0x0384 }
        r9 = r2 + -7;
        r8 = r8.substring(r9);	 //Catch:{ NumberFormatException -> 0x0384 }
        r8 = java.lang.String.valueOf(r8);	 //Catch:{ NumberFormatException -> 0x0384 }
        r7.<init>(r8);	 //Catch:{ NumberFormatException -> 0x0384 }
        r8 = "00000000";
        r7 = r7.append(r8);	 //Catch:{ NumberFormatException -> 0x0384 }
        r3 = r7.toString();	 //Catch:{ NumberFormatException -> 0x0384 }
    L_0x0216:
        r7 = r3.toUpperCase();	 //Catch:{ NumberFormatException -> 0x0384 }
        r8 = 16;
        r0 = java.lang.Long.parseLong(r7, r8);	 //Catch:{ NumberFormatException -> 0x0384 }
    L_0x0220:
        r2 = C;
        r0 = r2.a(r0);
        Y = r0;
        r0 = r0.length();
        if (r0 >= r6) goto L_0x0232;
    L_0x022e:
        r0 = 16 - r0;
    L_0x0230:
        if (r4 < r0) goto L_0x036a;
    L_0x0232:
        com.zkmm.adsdk.s.b(r10);
    L_0x0235:
        return;
    L_0x0236:
        r2 = move-exception;
        r2 = r0;
        goto L_0x00a5;
    L_0x023a:
        if (r0 == 0) goto L_0x025a;
    L_0x023c:
        if (r2 != 0) goto L_0x023f;
    L_0x023e:
        r2 = r0;
    L_0x023f:
        r1 = new java.lang.StringBuilder;
        r0 = java.lang.String.valueOf(r0);
        r1.<init>(r0);
        r0 = "|";
        r0 = r1.append(r0);
        r0 = r0.append(r2);
        r0 = r0.toString();
        h = r0;
        goto L_0x00f0;
    L_0x025a:
        if (r2 != 0) goto L_0x025e;
    L_0x025c:
        r2 = "9774d56d682e549c";
    L_0x025e:
        r0 = new java.lang.StringBuilder;
        r1 = "0|";
        r0.<init>(r1);
        r0 = r0.append(r2);
        r0 = r0.toString();
        h = r0;
        goto L_0x00f0;
    L_0x0271:
        r0 = "000000000000000|1a9ef48939761a75";
        r0 = r0.getBytes();	 //Catch:{ UnsupportedEncodingException -> 0x027b }
        c = r0;	 //Catch:{ UnsupportedEncodingException -> 0x027b }
        goto L_0x00fe;
    L_0x027b:
        r0 = move-exception;
        goto L_0x00fe;
    L_0x027e:
        r3 = r5;
        goto L_0x0131;
    L_0x0281:
        r2 = "zh";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x0291;
    L_0x0289:
        if (r0 == 0) goto L_0x028e;
    L_0x028b:
        r3 = r6;
        goto L_0x0131;
    L_0x028e:
        r3 = r4;
        goto L_0x0131;
    L_0x0291:
        r2 = "ko";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x02a2;
    L_0x0299:
        if (r0 == 0) goto L_0x029f;
    L_0x029b:
        r3 = 21;
        goto L_0x0131;
    L_0x029f:
        r3 = 5;
        goto L_0x0131;
    L_0x02a2:
        r2 = "fr";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x02b3;
    L_0x02aa:
        if (r0 == 0) goto L_0x02b0;
    L_0x02ac:
        r3 = 19;
        goto L_0x0131;
    L_0x02b0:
        r3 = r7;
        goto L_0x0131;
    L_0x02b3:
        r2 = "es";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x02c5;
    L_0x02bb:
        if (r0 == 0) goto L_0x02c1;
    L_0x02bd:
        r3 = 24;
        goto L_0x0131;
    L_0x02c1:
        r3 = 8;
        goto L_0x0131;
    L_0x02c5:
        r2 = "de";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x02d6;
    L_0x02cd:
        if (r0 == 0) goto L_0x02d3;
    L_0x02cf:
        r3 = 22;
        goto L_0x0131;
    L_0x02d3:
        r3 = 6;
        goto L_0x0131;
    L_0x02d6:
        r2 = "it";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x02e7;
    L_0x02de:
        if (r0 == 0) goto L_0x02e4;
    L_0x02e0:
        r3 = 23;
        goto L_0x0131;
    L_0x02e4:
        r3 = 7;
        goto L_0x0131;
    L_0x02e7:
        r2 = "ja";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x02f8;
    L_0x02ef:
        if (r0 == 0) goto L_0x02f5;
    L_0x02f1:
        r3 = 20;
        goto L_0x0131;
    L_0x02f5:
        r3 = 4;
        goto L_0x0131;
    L_0x02f8:
        r2 = "ru";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x030a;
    L_0x0300:
        if (r0 == 0) goto L_0x0306;
    L_0x0302:
        r3 = 25;
        goto L_0x0131;
    L_0x0306:
        r3 = 9;
        goto L_0x0131;
    L_0x030a:
        r2 = "pt";
        r1 = r1.contains(r2);
        if (r1 == 0) goto L_0x0318;
    L_0x0312:
        if (r0 == 0) goto L_0x0131;
    L_0x0314:
        r3 = 17;
        goto L_0x0131;
    L_0x0318:
        if (r0 == 0) goto L_0x031e;
    L_0x031a:
        r3 = 18;
        goto L_0x0131;
    L_0x031e:
        r3 = r5;
        goto L_0x0131;
    L_0x0321:
        r0 = move-exception;
        r0 = "Package Name ERROR:  Incorrect application pakage name.  ";
        c(r0);
        goto L_0x0145;
    L_0x0329:
        r2 = move-exception;
        r2 = r6;
    L_0x032b:
        r7 = h;
        r3 = r3.substring(r4, r5);
        r5 = "00";
        r3 = r7.replace(r3, r5);
        h = r3;
        r3 = h;	 //Catch:{ Exception -> 0x0381 }
        r5 = "UTF-8";
        r3 = r3.getBytes(r5);	 //Catch:{ Exception -> 0x0381 }
        c = r3;	 //Catch:{ Exception -> 0x0381 }
        r3 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0381 }
        r5 = h;	 //Catch:{ Exception -> 0x0381 }
        r2 = r2 + -7;
        r2 = r5.substring(r2);	 //Catch:{ Exception -> 0x0381 }
        r2 = java.lang.String.valueOf(r2);	 //Catch:{ Exception -> 0x0381 }
        r3.<init>(r2);	 //Catch:{ Exception -> 0x0381 }
        r2 = "00000000";
        r2 = r3.append(r2);	 //Catch:{ Exception -> 0x0381 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0381 }
        r2 = r2.toUpperCase();	 //Catch:{ Exception -> 0x0381 }
        r3 = 16;
        r0 = java.lang.Long.parseLong(r2, r3);	 //Catch:{ Exception -> 0x0381 }
        goto L_0x0220;
    L_0x036a:
        r1 = new java.lang.StringBuilder;
        r2 = "0";
        r1.<init>(r2);
        r2 = Y;
        r1 = r1.append(r2);
        r1 = r1.toString();
        Y = r1;
        r4 = r4 + 1;
        goto L_0x0230;
    L_0x0381:
        r2 = move-exception;
        goto L_0x0220;
    L_0x0384:
        r7 = move-exception;
        goto L_0x032b;
    L_0x0386:
        r0 = move-exception;
        goto L_0x01e6;
    L_0x0389:
        r0 = move-exception;
        goto L_0x01b6;
    L_0x038c:
        r0 = move-exception;
        goto L_0x0181;
    L_0x038f:
        r8 = move-exception;
        goto L_0x00d2;
    L_0x0392:
        r2 = r6;
        goto L_0x0216;
    L_0x0395:
        r0 = r4;
        goto L_0x0179;
    L_0x0398:
        r0 = r4;
        goto L_0x0117;
    L_0x039b:
        r0 = r8;
        goto L_0x00d2;
    L_0x039e:
        r1 = r0;
        goto L_0x0099;
        */

    }

    private static void c(String r1_String) {
        Log.e("Adwo SDK", r1_String);
    }

    protected static void d(Context r11_Context) {
        Location r1_Location;
        double[] r0_doubleA;
        LocationManager r0_LocationManager = (LocationManager) r11_Context.getSystemService(NearByListActivity.DIALOG_KEY_REQ_LOCATION);
        List r5_List = r0_LocationManager.getProviders(true);
        int r4i = r5_List.size() - 1;
        Location r2_Location = null;
        while (r4i >= 0) {
            String r1_String = (String) r5_List.get(r4i);
            if (r0_LocationManager.isProviderEnabled(r1_String)) {
                r1_Location = r0_LocationManager.getLastKnownLocation(r1_String);
                if (r1_Location == null) {
                    r4i--;
                    r2_Location = r1_Location;
                } else {
                    break;
                }
            } else {
                r1_Location = r2_Location;
            }
            r4i--;
            r2_Location = r1_Location;
        }
        r1_Location = r2_Location;
        if (r1_Location != null) {
            r0_doubleA = new double[4];
            r0_doubleA[0] = r1_Location.getLatitude();
            r0_doubleA[1] = r1_Location.getLongitude();
            r0_doubleA[2] = (double) r1_Location.getAccuracy();
            r0_doubleA[3] = (double) r1_Location.getTime();
        } else {
            r0_doubleA = null;
        }
        if (r0_doubleA != null) {
            r1_Location = new Location("last_known_loc");
            r1_Location.setLatitude(r0_doubleA[0]);
            r1_Location.setLongitude(r0_doubleA[1]);
            r1_Location.setAccuracy((float) r0_doubleA[2]);
            r1_Location.setTime((long) r0_doubleA[3]);
            af = r1_Location;
        }
    }

    private static double[] d() {
        if (af == null) {
            return null;
        }
        double[] r0_doubleA = new double[2];
        r0_doubleA[0] = af.getLatitude();
        r0_doubleA[1] = af.getLongitude();
        return r0_doubleA;
    }

    private static String e(Context r3_Context) {
        try {
            ApplicationInfo r0_ApplicationInfo = r3_Context.getPackageManager().getApplicationInfo(r3_Context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (r0_ApplicationInfo != null) {
                return r0_ApplicationInfo.packageName;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static void f(Context r4_Context) {
        if (Q) {
            NetworkInfo r0_NetworkInfo = ((ConnectivityManager) r4_Context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (NetworkManager.WIFI.equalsIgnoreCase(r0_NetworkInfo.getTypeName().toLowerCase())) {
                n = (byte) 1;
            } else {
                n = (byte) 0;
            }
            if (r0_NetworkInfo == null) {
                m = "UNKNOWN";
            } else {
                int r1i = r0_NetworkInfo.getType();
                int r0i = r0_NetworkInfo.getSubtype();
                if (r1i == 1) {
                    m = "WIFI";
                }
                if (r1i == 6) {
                    m = "WIMAX";
                }
                if (r1i == 0) {
                    switch (r0i) {
                        case XListViewHeader.STATE_READY:
                            m = "GPRS";
                            break;
                        case XListViewHeader.STATE_REFRESHING:
                            m = "EDGE";
                            break;
                        case XListViewFooter.STATE_NOMORE:
                            m = "UMTS";
                            break;
                        case XListViewFooter.STATE_NODATA:
                            m = "CDMA";
                            break;
                        case ShareUtils.SHARE_SMS:
                            m = "EVDO_0";
                            break;
                        case ShareUtils.SHARE_COPY:
                            m = "EVDO_A";
                            break;
                        case ShareUtils.SHARE_COLLECT:
                            m = "1xRTT";
                            break;
                        case Base64.DONT_BREAK_LINES:
                            m = "HSDPA";
                            break;
                        case REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY:
                            m = "HSUPA";
                            break;
                        case REQUEST_CODE.REQUEST_CODE_EDIT_INTRO:
                            m = "HSPA";
                            break;
                        case REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE:
                            m = "IDEN";
                            break;
                        case REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH:
                            m = "EVDO_B";
                            break;
                        case REQUEST_CODE.REQUEST_CODE_EDIT_GENDER:
                            m = "LTE";
                            break;
                        case REQUEST_CODE.REQUEST_CODE_EDIT_LOCATION:
                            m = "EHRPD";
                            break;
                        case NearbySelectView.TIME_15MIN:
                            m = "HSPAP";
                            break;
                    }
                    m = "MOBILE";
                }
            }
        }
    }
}