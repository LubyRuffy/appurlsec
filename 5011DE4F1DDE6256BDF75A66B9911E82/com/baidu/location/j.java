package com.baidu.location;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.UUID;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.share.ShareUtils;
import qsbk.app.widget.listview.XListViewHeader;

class j {
    public static String A;
    public static final boolean B = false;
    public static float C;
    public static float D;
    private static boolean E;
    public static float F;
    public static boolean G;
    public static boolean H;
    public static int I;
    public static double J;
    public static int K;
    public static int L;
    public static long M;
    public static String N;
    public static boolean O;
    public static boolean P;
    public static float Q;
    public static boolean R;
    public static int S;
    private static String T;
    public static float U;
    public static int V;
    public static float W;
    public static int X;
    public static int Y;
    public static double Z;
    public static float a;
    public static int aa;
    public static boolean ab;
    public static long ac;
    public static int ad;
    public static int ae;
    public static int af;
    public static boolean ag;
    public static String ah;
    public static float ai;
    private static Process aj;
    public static String ak;
    public static long al;
    public static float am;
    private static final String an;
    public static String b;
    public static float byte;
    public static float c;
    public static final boolean case = false;
    public static boolean d;
    public static String do;
    public static int e;
    private static String f;
    public static int for;
    public static int g;
    public static int goto;
    public static int h;
    private static String i;
    public static double if;
    public static int int;
    public static byte[] j;
    private static boolean k;
    public static int l;
    public static int long;
    public static boolean m;
    public static boolean n;
    public static int new;
    public static double o;
    public static int p;
    public static int q;
    public static int r;
    public static float s;
    public static int t;
    public static boolean try;
    public static float u;
    public static int v;
    public static boolean void;
    public static float w;
    public static boolean x;
    public static final boolean y = false;
    public static boolean z;

    public static class a {
        private static final String a;

        static {
            a = com.baidu.location.j.a.class.getSimpleName();
        }

        private static String a_(Context r1_Context) {
            return com.baidu.location.j.b.a(r1_Context);
        }

        public static String if(Context r3_Context) {
            String r1_String = a(r3_Context);
            String r0_String = com.baidu.location.j.b.do(r3_Context);
            if (TextUtils.isEmpty(r0_String)) {
                r0_String = "0";
            }
            return r1_String + "|" + new StringBuffer(r0_String).reverse().toString();
        }
    }

    public static class b {
        private b() {
        }

        public static String a(Context r6_Context) {
            SharedPreferences r2_SharedPreferences = r6_Context.getSharedPreferences("bids", 0);
            String r0_String = r2_SharedPreferences.getString("i", null);
            if (r0_String == null) {
                r0_String = do(r6_Context);
                Editor r1_Editor = r2_SharedPreferences.edit();
                r1_Editor.putString("i", r0_String);
                r1_Editor.commit();
            }
            String r1_String = r2_SharedPreferences.getString(QsbkDatabase.A, null);
            if (r1_String == null) {
                r1_String = if(r6_Context);
                Editor r2_Editor = r2_SharedPreferences.edit();
                r2_Editor.putString(QsbkDatabase.A, r1_String);
                r2_Editor.commit();
            }
            String r2_String = RContactStorage.PRIMARY_KEY;
            r2_String = j.a(("com.baidu" + r0_String + r1_String).getBytes(), true);
            CharSequence r3_CharSequence = System.getString(r6_Context.getContentResolver(), r2_String);
            if (!TextUtils.isEmpty(r3_CharSequence)) {
                return r3_CharSequence;
            }
            r0_String = j.a((r0_String + r1_String + UUID.randomUUID().toString()).getBytes(), true);
            System.putString(r6_Context.getContentResolver(), r2_String, r0_String);
            return r0_String.equals(System.getString(r6_Context.getContentResolver(), r2_String)) ? r0_String : r2_String;
        }

        public static String do(Context r2_Context) {
            String r1_String = RContactStorage.PRIMARY_KEY;
            TelephonyManager r0_TelephonyManager = (TelephonyManager) r2_Context.getSystemService("phone");
            if (r0_TelephonyManager == null) {
                return r1_String;
            }
            String r0_String = r0_TelephonyManager.getDeviceId();
            return TextUtils.isEmpty(r0_String) ? RContactStorage.PRIMARY_KEY : r0_String;
        }

        public static String if(Context r2_Context) {
            String r0_String = RContactStorage.PRIMARY_KEY;
            r0_String = Secure.getString(r2_Context.getContentResolver(), "android_id");
            return TextUtils.isEmpty(r0_String) ? RContactStorage.PRIMARY_KEY : r0_String;
        }
    }

    static {
        H = false;
        ab = false;
        d = false;
        h = 0;
        f = f.v;
        i = "http://loc.map.baidu.com/sdk.php";
        N = "http://loc.map.baidu.com/sdk_ep.php";
        k = false;
        E = true;
        T = "[baidu_location_service]";
        aj = null;
        do = null;
        A = "no";
        ah = "gcj02";
        R = true;
        G = true;
        l = 3;
        J = 0.0d;
        Z = 0.0d;
        if = 0.0d;
        o = 0.0d;
        I = 0;
        j = null;
        ag = false;
        g = 0;
        am = 1.1f;
        c = 2.2f;
        F = 2.3f;
        U = 3.8f;
        p = 3;
        K = 10;
        X = 2;
        int = 7;
        for = 20;
        ad = 70;
        long = 120;
        D = 2.0f;
        C = 10.0f;
        ai = 50.0f;
        Q = 200.0f;
        Y = 16;
        byte = 0.9f;
        S = 5000;
        a = 0.5f;
        u = 0.0f;
        s = 0.1f;
        r = 30;
        q = 100;
        void = true;
        try = true;
        V = 20;
        aa = 300;
        L = 1000;
        M = 1200000;
        ac = 20;
        al = 300000;
        af = 0;
        O = true;
        P = true;
        n = false;
        x = true;
        z = true;
        m = true;
        w = 6.0f;
        W = 10.0f;
        v = 60;
        ae = 70;
        goto = 6;
        an = f.a + "/con.dat";
        ak = null;
        b = null;
        new = -1;
        t = -1;
        e = 0;
    }

    static int a(String r6_String, String r7_String, String r8_String) {
        if (r6_String == null || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        int r1i = r6_String.indexOf(r7_String);
        if (r1i == -1) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        r1i += r7_String.length();
        int r2i = r6_String.indexOf(r8_String, r1i);
        if (r2i == -1) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        String r1_String = r6_String.substring(r1i, r2i);
        if (r1_String == null || r1_String.equals(RContactStorage.PRIMARY_KEY)) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        try {
            return Integer.parseInt(r1_String);
        } catch (NumberFormatException e) {
            if(f, "util numberFormatException, intStr : " + r1_String);
            e.printStackTrace();
            return ExploreByTouchHelper.INVALID_ID;
        }
    }

    static String a() {
        Calendar r0_Calendar = Calendar.getInstance();
        int r1i = r0_Calendar.get(ShareUtils.SHARE_SMS);
        int r2i = r0_Calendar.get(1);
        int r4i = r0_Calendar.get(REQUEST_CODE.REQUEST_CODE_EDIT_SIGNATURE);
        int r5i = r0_Calendar.get(REQUEST_CODE.REQUEST_CODE_EDIT_BIRTH);
        int r0i = r0_Calendar.get(REQUEST_CODE.REQUEST_CODE_EDIT_GENDER);
        Object[] r7_ObjectA = new Object[6];
        r7_ObjectA[0] = Integer.valueOf(r2i);
        r7_ObjectA[1] = Integer.valueOf(r0_Calendar.get(XListViewHeader.STATE_REFRESHING) + 1);
        r7_ObjectA[2] = Integer.valueOf(r1i);
        r7_ObjectA[3] = Integer.valueOf(r4i);
        r7_ObjectA[4] = Integer.valueOf(r5i);
        r7_ObjectA[5] = Integer.valueOf(r0i);
        return String.format("%d-%d-%d %d:%d:%d", r7_ObjectA);
    }

    public static String a(com.baidu.location.c.a r9_com_baidu_location_c_a, c r10_c, Location r11_Location, String r12_String, int r13i) {
        String r0_String;
        StringBuffer r2_StringBuffer = new StringBuffer();
        if (r9_com_baidu_location_c_a != null) {
            r0_String = r9_com_baidu_location_c_a.toString();
            if (r0_String != null) {
                r2_StringBuffer.append(r0_String);
            }
        }
        if (r10_c != null) {
            r0_String = r13i == 0 ? r10_c.char() : r10_c.byte();
            if (r0_String != null) {
                r2_StringBuffer.append(r0_String);
            }
        }
        if (r11_Location != null) {
            r0_String = (h == 0 || r13i == 0) ? b.do(r11_Location) : b.if(r11_Location);
            if (r0_String != null) {
                r2_StringBuffer.append(r0_String);
            }
        }
        boolean r0z = false;
        if (r13i == 0) {
            r0z = true;
        }
        r0_String = c.a(r0z);
        if (r0_String != null) {
            r2_StringBuffer.append(r0_String);
        }
        if (r12_String != null) {
            r2_StringBuffer.append(r12_String);
        }
        if (r9_com_baidu_location_c_a != null) {
            r0_String = r9_com_baidu_location_c_a.int();
            if (r0_String == null || r0_String.length() + r2_StringBuffer.length() >= 750) {
                r0_String = r2_StringBuffer.toString();
                if(f, "util format : " + r0_String);
            } else {
                r2_StringBuffer.append(r0_String);
            }
        }
        r0_String = r2_StringBuffer.toString();
        if(f, "util format : " + r0_String);
        if (r11_Location == null || r10_c == null) {
            l = 3;
        } else {
            float r2f = r11_Location.getSpeed();
            int r3i = h;
            int r4i = r10_c.do();
            int r5i = r10_c.try();
            boolean r6z = r10_c.case();
            if (r2f < w) {
                if (r3i == 1 || r3i == 0) {
                    if (r4i < v || r6z) {
                        l = 1;
                    }
                }
            }
            if (r2f < W) {
                if (r3i == 1 || r3i == 0 || r3i == 3) {
                    if (r4i < ae || r5i > goto) {
                        l = 2;
                    }
                }
            }
            l = 3;
        }
        return r0_String;
    }

    static String a(String r6_String, String r7_String, String r8_String, double r9d) {
        if (r6_String == null || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
            return null;
        }
        int r1i = r6_String.indexOf(r7_String);
        if (r1i == -1) {
            return null;
        }
        r1i += r7_String.length();
        int r2i = r6_String.indexOf(r8_String, r1i);
        if (r2i == -1) {
            return null;
        }
        String r0_String = r6_String.substring(0, r1i);
        String r1_String = r6_String.substring(r2i);
        Object[] r3_ObjectA = new Object[1];
        r3_ObjectA[0] = Double.valueOf(r9d);
        r0_String = r0_String + String.format("%.7f", r3_ObjectA) + r1_String;
        if(f, "NEW:" + r0_String);
        return r0_String;
    }

    public static String a(byte[] r6_byteA, String r7_String, boolean r8z) {
        StringBuilder r2_StringBuilder = new StringBuilder();
        int r3i = r6_byteA.length;
        int r1i = 0;
        while (r1i < r3i) {
            String r0_String = Integer.toHexString(r6_byteA[r1i] & 255);
            if (r8z) {
                r0_String = r0_String.toUpperCase();
            }
            if (r0_String.length() == 1) {
                r2_StringBuilder.append("0");
            }
            r2_StringBuilder.append(r0_String).append(r7_String);
            r1i++;
        }
        return r2_StringBuilder.toString();
    }

    public static String a(byte[] r2_byteA, boolean r3z) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.reset();
            r0_MessageDigest.update(r2_byteA);
            return a(r0_MessageDigest.digest(), RContactStorage.PRIMARY_KEY, r3z);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void a(int r6i) {
        boolean r1z = true;
        O = (r6i & 1) == 1;
        P = (r6i & 2) == 2;
        n = (r6i & 4) == 4;
        x = (r6i & 8) == 8;
        z = (r6i & 65536) == 65536;
        if ((r6i & 131072) == 131072) {
            m = r1z;
            if(f, "A1~C3:" + O + P + n + x + z + m);
        } else {
            r1z = false;
            m = r1z;
            if(f, "A1~C3:" + O + P + n + x + z + m);
        }
    }

    public static void a(String r1_String) {
        if (E) {
            Log.d(T, r1_String);
        }
    }

    public static void a(String r1_String, String r2_String) {
        if (k) {
            Log.d(r1_String, r2_String);
        }
    }

    static float b(String r6_String, String r7_String, String r8_String) {
        if (r6_String == null || r6_String.equals(RContactStorage.PRIMARY_KEY)) {
            return 1.4E-45f;
        }
        int r1i = r6_String.indexOf(r7_String);
        if (r1i == -1) {
            return 1.4E-45f;
        }
        r1i += r7_String.length();
        int r2i = r6_String.indexOf(r8_String, r1i);
        if (r2i == -1) {
            return 1.4E-45f;
        }
        String r1_String = r6_String.substring(r1i, r2i);
        if (r1_String == null || r1_String.equals(RContactStorage.PRIMARY_KEY)) {
            return 1.4E-45f;
        }
        try {
            return Float.parseFloat(r1_String);
        } catch (NumberFormatException e) {
            if(f, "util numberFormatException, intStr : " + r1_String);
            e.printStackTrace();
            return 1.4E-45f;
        }
    }

    static double c(String r7_String, String r8_String, String r9_String) {
        if (r7_String == null || r7_String.equals(RContactStorage.PRIMARY_KEY)) {
            return 4.9E-324d;
        }
        int r2i = r7_String.indexOf(r8_String);
        if (r2i == -1) {
            return 4.9E-324d;
        }
        r2i += r8_String.length();
        int r3i = r7_String.indexOf(r9_String, r2i);
        if (r3i == -1) {
            return 4.9E-324d;
        }
        String r2_String = r7_String.substring(r2i, r3i);
        if (r2_String == null || r2_String.equals(RContactStorage.PRIMARY_KEY)) {
            return 4.9E-324d;
        }
        try {
            return Double.parseDouble(r2_String);
        } catch (NumberFormatException e) {
            if(f, "util numberFormatException, doubleStr : " + r2_String);
            e.printStackTrace();
            return 4.9E-324d;
        }
    }

    public static String do() {
        return i;
    }

    public static boolean do(String r2_String) {
        int r0i = a(r2_String, "error\":\"", "\"");
        return r0i > 100 && r0i < 200;
    }

    public static void if() {
        int r0i = 0;
        try {
            File r1_File = new File(an);
            if (r1_File.exists()) {
                RandomAccessFile r2_RandomAccessFile = new RandomAccessFile(r1_File, "rw");
                r2_RandomAccessFile.seek(4);
                int r1i = r2_RandomAccessFile.readInt();
                int r3i = r2_RandomAccessFile.readInt();
                r2_RandomAccessFile.seek(128);
                byte[] r4_byteA = new byte[r1i];
                while (r0i < r3i) {
                    r2_RandomAccessFile.seek((long) (r1i * r0i + 128));
                    int r5i = r2_RandomAccessFile.readInt();
                    if (r5i <= 0 || r5i >= r1i) {
                        r0i++;
                    } else {
                        r2_RandomAccessFile.read(r4_byteA, 0, r5i);
                        if (r4_byteA[r5i - 1] == 0) {
                            String r6_String = new String(r4_byteA, 0, r5i - 1);
                            if(f, "a:" + r6_String);
                            if (r6_String.equals(ak)) {
                                new = r2_RandomAccessFile.readInt();
                                e = r0i;
                                if(f, r6_String + new);
                                break;
                            }
                        }
                        r0i++;
                    }
                }
                if (r0i == r3i) {
                    e = r3i;
                }
                r2_RandomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static void if(int r5i) {
        File r0_File = new File(an);
        if (!r0_File.exists()) {
            int();
        }
        try {
            RandomAccessFile r1_RandomAccessFile = new RandomAccessFile(r0_File, "rw");
            r1_RandomAccessFile.seek(4);
            int r0i = r1_RandomAccessFile.readInt();
            int r2i = r1_RandomAccessFile.readInt();
            r1_RandomAccessFile.seek((long) (r0i * e + 128));
            byte[] r0_byteA = (ak + '\u0000').getBytes();
            r1_RandomAccessFile.writeInt(r0_byteA.length);
            r1_RandomAccessFile.write(r0_byteA, 0, r0_byteA.length);
            r1_RandomAccessFile.writeInt(r5i);
            if (r2i == e) {
                r1_RandomAccessFile.seek(8);
                r1_RandomAccessFile.writeInt(r2i + 1);
            }
            r1_RandomAccessFile.close();
        } catch (Exception e) {
        }
    }

    public static void if(String r1_String) {
        if ((!k) || r1_String == null) {
        } else {
            i = r1_String;
        }
    }

    public static void if(String r0_String, String r1_String) {
    }

    public static void int() {
        try {
            File r0_File = new File(an);
            if (!r0_File.exists()) {
                File r1_File = new File(f.a);
                if (!r1_File.exists()) {
                    r1_File.mkdirs();
                }
                if (!r0_File.createNewFile()) {
                    r0_File = null;
                }
                RandomAccessFile r1_RandomAccessFile = new RandomAccessFile(r0_File, "rw");
                r1_RandomAccessFile.seek(0);
                r1_RandomAccessFile.writeInt(0);
                r1_RandomAccessFile.writeInt(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                r1_RandomAccessFile.writeInt(0);
                r1_RandomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    public static void new() {
        if (aj != null) {
            try {
                if(f, "logcat stop...");
                aj.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void try() {
        if (k) {
            try {
                if (aj != null) {
                    aj.destroy();
                    aj = null;
                }
                File r0_File = new File(f.a);
                if (r0_File.exists()) {
                    if("sdkdemo_applocation", "directory already exists...");
                } else {
                    r0_File.mkdirs();
                    if("sdkdemo_applocation", "directory not exists, make dirs...");
                }
                if(f, "logcat start ...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}