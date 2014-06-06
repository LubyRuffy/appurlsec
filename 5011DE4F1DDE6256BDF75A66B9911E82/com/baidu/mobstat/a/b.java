package com.baidu.mobstat.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import qsbk.app.utils.HttpUtils;

public final class b {
    private static final Proxy a;
    private static final Proxy b;

    static {
        a = new Proxy(Type.HTTP, new InetSocketAddress(HttpUtils.PROXY_IP, 80));
        b = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80));
    }

    public static String a(Context r4_Context, String r5_String) {
        c.a("MoUtil.read", r5_String);
        String r1_String = RContactStorage.PRIMARY_KEY;
        try {
            byte[] r2_byteA = b(r4_Context, r5_String);
            if (r2_byteA != null) {
                return new String(r2_byteA, AdViewNetFetchThread.NetEncoding);
            }
        } catch (Exception e) {
            Log.w("sdkstat", "MoUtil.read", e);
        }
        return r1_String;
    }

    public static String a(boolean r1z, Context r2_Context, String r3_String) {
        return r1z ? b(r3_String) : a(r2_Context, r3_String);
    }

    public static HttpURLConnection a(Context r4_Context, String r5_String, int r6i, int r7i) {
        HttpURLConnection r0_HttpURLConnection;
        URL r1_URL = new URL(r5_String);
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r4_Context.getSystemService("connectivity");
        NetworkInfo r2_NetworkInfo = r0_ConnectivityManager.getNetworkInfo(0);
        NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getNetworkInfo(1);
        if (r0_NetworkInfo == null || (!r0_NetworkInfo.isAvailable())) {
            if (r2_NetworkInfo == null || (!r2_NetworkInfo.isAvailable())) {
                c.a(RContactStorage.PRIMARY_KEY, "getConnection:not wifi and mobile");
                r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection();
            } else {
                String r0_String = r2_NetworkInfo.getExtraInfo();
                r0_String = r0_String != null ? r0_String.toLowerCase() : RContactStorage.PRIMARY_KEY;
                c.a("current APN", r0_String);
                if (r0_String.startsWith("cmwap") || r0_String.startsWith("uniwap") || r0_String.startsWith("3gwap")) {
                    r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection(a);
                } else if (r0_String.startsWith("ctwap")) {
                    r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection(b);
                } else {
                    r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection();
                }
            }
        } else {
            c.a(RContactStorage.PRIMARY_KEY, "WIFI is available");
            r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection();
        }
        r0_HttpURLConnection.setConnectTimeout(r6i);
        r0_HttpURLConnection.setReadTimeout(r7i);
        return r0_HttpURLConnection;
    }

    public static void a(Context r5_Context, String r6_String, String r7_String, boolean r8z) {
        FileOutputStream r1_FileOutputStream;
        boolean r0z = false;
        r1_FileOutputStream = null;
        try {
            r1_FileOutputStream = r5_Context.openFileOutput(r6_String, r8z ? AccessibilityNodeInfoCompat.ACTION_PASTE : 0);
            if (r1_FileOutputStream != null) {
                r1_FileOutputStream.write(r7_String.getBytes(AdViewNetFetchThread.NetEncoding));
            } else {
                String r2_String = "sdkstat";
                StringBuilder r3_StringBuilder = new StringBuilder().append("MoUtil.write fout is null:");
                if (r1_FileOutputStream == null) {
                    r0z = true;
                }
                Log.w(r2_String, r3_StringBuilder.append(r0z).toString());
            }
            if (r1_FileOutputStream != null) {
                try {
                    r1_FileOutputStream.close();
                } catch (Exception e) {
                    Log.w("sdkstat", "MoUtil.write", e);
                }
            }
        } catch (Exception e_2) {
            Log.w("sdkstat", "MoUtil.write", e_2);
            if (r1_FileOutputStream != null) {
                r1_FileOutputStream.close();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(String r4_String, String r5_String, boolean r6z) {
        /*
        r0 = android.os.Environment.getExternalStorageState();
        r1 = "mounted";
        r0 = r1.equals(r0);
        if (r0 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r1 = 0;
        r0 = android.os.Environment.getExternalStorageDirectory();	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r3 = new java.io.File;	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r2 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r2.<init>();	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r0 = r2.append(r0);	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r2 = java.io.File.separator;	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r0 = r0.append(r2);	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r0 = r0.append(r4);	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r0 = r0.toString();	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r3.<init>(r0);	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r0 = r3.exists();	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        if (r0 != 0) goto L_0x003e;
    L_0x0034:
        r0 = r3.getParentFile();	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r0.mkdirs();	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r3.createNewFile();	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
    L_0x003e:
        r2 = new java.io.FileOutputStream;	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r2.<init>(r3, r6);	 //Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0072 }
        r0 = "utf-8";
        r0 = r5.getBytes(r0);	 //Catch:{ FileNotFoundException -> 0x009f, IOException -> 0x009c, all -> 0x0099 }
        r2.write(r0);	 //Catch:{ FileNotFoundException -> 0x009f, IOException -> 0x009c, all -> 0x0099 }
        if (r2 == 0) goto L_0x000c;
    L_0x004e:
        r2.close();	 //Catch:{ IOException -> 0x0052 }
        goto L_0x000c;
    L_0x0052:
        r0 = move-exception;
        r1 = "sdkstat";
        r2 = "MoUtil.writeExt";
        android.util.Log.w(r1, r2, r0);
        goto L_0x000c;
    L_0x005b:
        r0 = move-exception;
    L_0x005c:
        r2 = "sdkstat";
        r3 = "MoUtil.writeExt";
        android.util.Log.e(r2, r3, r0);	 //Catch:{ all -> 0x0089 }
        if (r1 == 0) goto L_0x000c;
    L_0x0065:
        r1.close();	 //Catch:{ IOException -> 0x0069 }
        goto L_0x000c;
    L_0x0069:
        r0 = move-exception;
        r1 = "sdkstat";
        r2 = "MoUtil.writeExt";
        android.util.Log.w(r1, r2, r0);
        goto L_0x000c;
    L_0x0072:
        r0 = move-exception;
    L_0x0073:
        r2 = "sdkstat";
        r3 = "MoUtil.writeExt";
        android.util.Log.e(r2, r3, r0);	 //Catch:{ all -> 0x0089 }
        if (r1 == 0) goto L_0x000c;
    L_0x007c:
        r1.close();	 //Catch:{ IOException -> 0x0080 }
        goto L_0x000c;
    L_0x0080:
        r0 = move-exception;
        r1 = "sdkstat";
        r2 = "MoUtil.writeExt";
        android.util.Log.w(r1, r2, r0);
        goto L_0x000c;
    L_0x0089:
        r0 = move-exception;
    L_0x008a:
        if (r1 == 0) goto L_0x008f;
    L_0x008c:
        r1.close();	 //Catch:{ IOException -> 0x0090 }
    L_0x008f:
        throw r0;
    L_0x0090:
        r1 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.writeExt";
        android.util.Log.w(r2, r3, r1);
        goto L_0x008f;
    L_0x0099:
        r0 = move-exception;
        r1 = r2;
        goto L_0x008a;
    L_0x009c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0073;
    L_0x009f:
        r0 = move-exception;
        r1 = r2;
        goto L_0x005c;
        */

    }

    public static void a(boolean r0z, Context r1_Context, String r2_String, String r3_String, boolean r4z) {
        if (r0z) {
            a(r2_String, r3_String, r4z);
        } else {
            a(r1_Context, r2_String, r3_String, r4z);
        }
    }

    public static boolean a(String r4_String) {
        c.a("MoUtil.deleteExt", r4_String);
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return false;
        }
        File r2_File = new File(Environment.getExternalStorageDirectory() + File.separator + r4_String);
        return r2_File.exists() ? r2_File.delete() : false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String b_(String r5_String) {
        /*
        r0 = "MoUtil.readExt";
        com.baidu.mobstat.a.c.a(r0, r5);
        r0 = android.os.Environment.getExternalStorageState();
        r1 = "mounted";
        r1 = r1.equals(r0);
        if (r1 != 0) goto L_0x001c;
    L_0x0011:
        r1 = "mounted_ro";
        r0 = r1.equals(r0);
        if (r0 != 0) goto L_0x001c;
    L_0x0019:
        r1 = "";
    L_0x001b:
        return r1;
    L_0x001c:
        r0 = android.os.Environment.getExternalStorageDirectory();
        r4 = new java.io.File;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = java.io.File.separator;
        r0 = r0.append(r1);
        r0 = r0.append(r5);
        r0 = r0.toString();
        r4.<init>(r0);
        r1 = "";
        r0 = r4.exists();
        if (r0 == 0) goto L_0x001b;
    L_0x0044:
        r3 = 0;
        r2 = new java.io.FileInputStream;	 //Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0084, all -> 0x009e }
        r2.<init>(r4);	 //Catch:{ FileNotFoundException -> 0x006a, IOException -> 0x0084, all -> 0x009e }
        r0 = r2.available();	 //Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x00b1 }
        r3 = new byte[r0];	 //Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x00b1 }
        r2.read(r3);	 //Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x00b1 }
        r0 = new java.lang.String;	 //Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x00b1 }
        r4 = "utf-8";
        r0.<init>(r3, r4);	 //Catch:{ FileNotFoundException -> 0x00b3, IOException -> 0x00b1 }
        if (r2 == 0) goto L_0x005f;
    L_0x005c:
        r2.close();	 //Catch:{ IOException -> 0x0061 }
    L_0x005f:
        r1 = r0;
        goto L_0x001b;
    L_0x0061:
        r1 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readExt";
        android.util.Log.w(r2, r3, r1);
        goto L_0x005f;
    L_0x006a:
        r0 = move-exception;
        r2 = r3;
    L_0x006c:
        r3 = "sdkstat";
        r4 = "MoUtil.readExt";
        android.util.Log.e(r3, r4, r0);	 //Catch:{ all -> 0x00af }
        if (r2 == 0) goto L_0x0078;
    L_0x0075:
        r2.close();	 //Catch:{ IOException -> 0x007a }
    L_0x0078:
        r0 = r1;
        goto L_0x005f;
    L_0x007a:
        r0 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readExt";
        android.util.Log.w(r2, r3, r0);
        r0 = r1;
        goto L_0x005f;
    L_0x0084:
        r0 = move-exception;
        r2 = r3;
    L_0x0086:
        r3 = "sdkstat";
        r4 = "MoUtil.readExt";
        android.util.Log.e(r3, r4, r0);	 //Catch:{ all -> 0x00af }
        if (r2 == 0) goto L_0x0092;
    L_0x008f:
        r2.close();	 //Catch:{ IOException -> 0x0094 }
    L_0x0092:
        r0 = r1;
        goto L_0x005f;
    L_0x0094:
        r0 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readExt";
        android.util.Log.w(r2, r3, r0);
        r0 = r1;
        goto L_0x005f;
    L_0x009e:
        r0 = move-exception;
        r2 = r3;
    L_0x00a0:
        if (r2 == 0) goto L_0x00a5;
    L_0x00a2:
        r2.close();	 //Catch:{ IOException -> 0x00a6 }
    L_0x00a5:
        throw r0;
    L_0x00a6:
        r1 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readExt";
        android.util.Log.w(r2, r3, r1);
        goto L_0x00a5;
    L_0x00af:
        r0 = move-exception;
        goto L_0x00a0;
    L_0x00b1:
        r0 = move-exception;
        goto L_0x0086;
    L_0x00b3:
        r0 = move-exception;
        goto L_0x006c;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static byte[] b_(Context r6_Context, String r7_String) {
        /*
        r2 = 0;
        r1 = r6.openFileInput(r7);	 //Catch:{ FileNotFoundException -> 0x0020, IOException -> 0x0039 }
        if (r1 == 0) goto L_0x007d;
    L_0x0007:
        r0 = r1.available();	 //Catch:{ FileNotFoundException -> 0x0071, IOException -> 0x0065, all -> 0x0062 }
        r2 = new byte[r0];	 //Catch:{ FileNotFoundException -> 0x0071, IOException -> 0x0065, all -> 0x0062 }
        r1.read(r2);	 //Catch:{ FileNotFoundException -> 0x0077, IOException -> 0x006b, all -> 0x0062 }
        r0 = r2;
    L_0x0011:
        if (r1 == 0) goto L_0x0016;
    L_0x0013:
        r1.close();	 //Catch:{ IOException -> 0x0017 }
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0016;
    L_0x0020:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0023:
        r3 = "sdkstat";
        r4 = "MoUtil.readBinary";
        android.util.Log.e(r3, r4, r1);	 //Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x0016;
    L_0x002c:
        r2.close();	 //Catch:{ IOException -> 0x0030 }
        goto L_0x0016;
    L_0x0030:
        r1 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0016;
    L_0x0039:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x003c:
        r3 = "sdkstat";
        r4 = "MoUtil.readBinary";
        android.util.Log.e(r3, r4, r1);	 //Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x0016;
    L_0x0045:
        r2.close();	 //Catch:{ IOException -> 0x0049 }
        goto L_0x0016;
    L_0x0049:
        r1 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0016;
    L_0x0052:
        r0 = move-exception;
    L_0x0053:
        if (r2 == 0) goto L_0x0058;
    L_0x0055:
        r2.close();	 //Catch:{ IOException -> 0x0059 }
    L_0x0058:
        throw r0;
    L_0x0059:
        r1 = move-exception;
        r2 = "sdkstat";
        r3 = "MoUtil.readBinary";
        android.util.Log.e(r2, r3, r1);
        goto L_0x0058;
    L_0x0062:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0053;
    L_0x0065:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x003c;
    L_0x006b:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x003c;
    L_0x0071:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x0023;
    L_0x0077:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x0023;
    L_0x007d:
        r0 = r2;
        goto L_0x0011;
        */

    }

    public static void c(String r3_String) {
        Object[] r0_ObjectA = new Object[2];
        r0_ObjectA[0] = "sdkstat";
        r0_ObjectA[1] = r3_String;
        c.c(r0_ObjectA);
        Log.e("sdkstat", "SDK install error:" + r3_String);
    }

    public static boolean c(Context r4_Context, String r5_String) {
        boolean r0z = r4_Context.getFileStreamPath(r5_String).exists();
        c.a("MoUtil.exists", r0z + " " + r5_String);
        return r0z;
    }

    public static void d(Context r2_Context, String r3_String) {
        if (!e(r2_Context, r3_String)) {
            c("You need the " + r3_String + " permission. Open AndroidManifest.xml and just before the final </manifest> tag add:  <uses-permission android:name=\"" + r3_String + "\" />");
        }
    }

    public static boolean e(Context r4_Context, String r5_String) {
        boolean r0z;
        r0z = r4_Context.checkCallingOrSelfPermission(r5_String) != -1;
        c.a("hasPermission ", r0z + " | " + r5_String);
        return r0z;
    }
}