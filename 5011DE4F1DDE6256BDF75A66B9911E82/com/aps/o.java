package com.aps;

import android.content.ContentResolver;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.params.HttpParams;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

// compiled from: Utils.java
public class o {
    private o() {
    }

    static float a(double[] r10_doubleA) {
        int r2i = 1;
        if (r10_doubleA.length != 4) {
            return 0.0f;
        }
        float[] r8_floatA = new float[r2i];
        Location.distanceBetween(r10_doubleA[0], r10_doubleA[r2i], r10_doubleA[2], r10_doubleA[3], r8_floatA);
        return r8_floatA[0];
    }

    static int a(int r1i) {
        return r1i * 2 - 113;
    }

    static int a(CellLocation r4_CellLocation, Context r5_Context) {
        int r1i = 1;
        if (a(r5_Context)) {
            Object[] r1_ObjectA = new Object[r1i];
            r1_ObjectA[0] = "air plane mode on";
            a(r1_ObjectA);
            return REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY;
        } else {
            if (r4_CellLocation instanceof GsmCellLocation) {
                return 1;
            }
            try {
                Class.forName("android.telephony.cdma.CdmaCellLocation");
                return XListViewHeader.STATE_REFRESHING;
            } catch (Exception e) {
                a(e);
                return REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY;
            }
        }
    }

    static long a() {
        return System.currentTimeMillis();
    }

    static void a(Context r7_Context, String r8_String) {
        int r0i;
        if (r8_String == null) {
            r8_String = "null";
        }
        if ("http://apilocate.amap.com/mobile/binary".indexOf("test") != -1) {
            r0i = 1;
        } else if ("http://aps.amap.com/APS/r".indexOf("test") != -1) {
            r0i = 1;
        } else if (f.d.indexOf("test") != -1) {
            r0i = 1;
        } else {
            char[] r0_charA = null;
            if ("http://apilocate.amap.com/mobile/binary".length() > 0) {
                r0_charA = "http://apilocate.amap.com/mobile/binary".substring(ShareUtils.SHARE_COLLECT, Base64.DONT_BREAK_LINES).toCharArray();
            }
            char[] r3_charA = null;
            if ("http://aps.amap.com/APS/r".length() > 0) {
                r3_charA = "http://aps.amap.com/APS/r".substring(ShareUtils.SHARE_COLLECT, Base64.DONT_BREAK_LINES).toCharArray();
            }
            if (r0_charA == null || (!Character.isLetter(r0_charA[0]))) {
                r0i = 1;
            } else if (r3_charA == null || (!Character.isLetter(r3_charA[0]))) {
                r0i = 1;
            } else {
                r0i = 0;
            }
        }
        if (r0i == 0 || r7_Context == null) {
        } else {
            Toast.makeText(r7_Context, r8_String, 0).show();
            Object[] r0_ObjectA = new Object[1];
            r0_ObjectA[0] = r8_String;
            a(r0_ObjectA);
        }
    }

    static void a(Throwable r0_Throwable) {
    }

    static void a(HttpParams r3_HttpParams, int r4i) {
        r3_HttpParams.setIntParameter("http.connection.timeout", r4i);
        r3_HttpParams.setIntParameter("http.socket.timeout", r4i);
        r3_HttpParams.setLongParameter("http.conn-manager.timeout", (long) r4i);
    }

    static void a(Object ... r0_ObjectA) {
    }

    static boolean a(Context r7_Context) {
        boolean r1z = true;
        if (r7_Context == null) {
            return false;
        }
        ContentResolver r3_ContentResolver = r7_Context.getContentResolver();
        String r4_String;
        String r0_String;
        Object[] r5_ObjectA;
        Class[] r0_ClassA;
        if (b() < 17) {
            try {
                r4_String = "android.provider.Settings$System";
                r0_String = ((String) l.a(r4_String, "AIRPLANE_MODE_ON")).toString();
                r5_ObjectA = new Object[2];
                r5_ObjectA[0] = r3_ContentResolver;
                r5_ObjectA[1] = r0_String;
                r0_ClassA = new Class[2];
                r0_ClassA[0] = ContentResolver.class;
                r0_ClassA[1] = String.class;
                return ((Integer) l.a(r4_String, "getInt", r5_ObjectA, r0_ClassA)).intValue() == 1;
            } catch (Exception e) {
                a(e);
                return false;
            }
        } else {
            try {
                r4_String = "android.provider.Settings$Global";
                r0_String = ((String) l.a(r4_String, "AIRPLANE_MODE_ON")).toString();
                r5_ObjectA = new Object[2];
                r5_ObjectA[0] = r3_ContentResolver;
                r5_ObjectA[1] = r0_String;
                r0_ClassA = new Class[2];
                r0_ClassA[0] = ContentResolver.class;
                r0_ClassA[1] = String.class;
                if (((Integer) l.a(r4_String, "getInt", r5_ObjectA, r0_ClassA)).intValue() == 1) {
                    return r1z;
                }
                r1z = false;
                return r1z;
            } catch (Exception e_2) {
                return false;
            }
        }
    }

    static boolean a(c r9_c) {
        if (r9_c == null || r9_c.h().equals("5") || r9_c.h().equals("6")) {
            return false;
        }
        double r2d = r9_c.c();
        double r4d = r9_c.d();
        float r6f = r9_c.e();
        return (r2d > 0.0d ? 1 : (r2d == 0.0d? 0 : -1)) != 0 || r4d != 0.0d || ((double) r6f) != 0.0d;
    }

    public static byte[] a(byte[] r3_byteA) {
        try {
            OutputStream r1_OutputStream = new ByteArrayOutputStream();
            GZIPOutputStream r2_GZIPOutputStream = new GZIPOutputStream(r1_OutputStream);
            r2_GZIPOutputStream.write(r3_byteA);
            r2_GZIPOutputStream.close();
            byte[] r0_byteA = r1_OutputStream.toByteArray();
            r1_OutputStream.close();
            return r0_byteA;
        } catch (Exception e) {
            return null;
        }
    }

    static String[] a(TelephonyManager r7_TelephonyManager) {
        int r1i = 0;
        String r0_String = null;
        if (r7_TelephonyManager != null) {
            r0_String = r7_TelephonyManager.getNetworkOperator();
        }
        String[] r2_StringA = new String[2];
        r2_StringA[0] = "0";
        r2_StringA[1] = "0";
        if (r0_String == null || r0_String.indexOf("null") != -1 || r0_String.length() <= 4) {
            Object[] r0_ObjectA = new Object[1];
            r0_ObjectA[0] = "get mcc and mnc e";
            a(r0_ObjectA);
        } else {
            r2_StringA[0] = r0_String.substring(0, XListViewFooter.STATE_NOMORE);
            char[] r3_charA = r0_String.substring(XListViewFooter.STATE_NOMORE).toCharArray();
            while (r1i < r3_charA.length && Character.isDigit(r3_charA[r1i])) {
                r1i++;
            }
            r2_StringA[1] = r0_String.substring(XListViewFooter.STATE_NOMORE, r1i + 3);
        }
        return r2_StringA;
    }

    static int b() {
        String r1_String = "android.os.Build$VERSION";
        try {
            return l.b(r1_String, "SDK_INT");
        } catch (Exception e) {
            return Integer.parseInt(l.a(r1_String, "SDK").toString());
        }
    }

    static NetworkInfo b(Context r2_Context) {
        NetworkInfo r0_NetworkInfo;
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) b(r2_Context, "connectivity");
        if (r0_ConnectivityManager != null) {
            try {
                r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
            } catch (SecurityException e) {
                return null;
            }
        } else {
            r0_NetworkInfo = null;
        }
        return r0_NetworkInfo;
    }

    static Object b(Context r1_Context, String r2_String) {
        return r1_Context == null ? null : r1_Context.getApplicationContext().getSystemService(r2_String);
    }
}