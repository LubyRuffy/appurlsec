package com.baidu.mobads.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import com.baidu.location.BDLocation;
import com.tencent.mm.sdk.contact.RContactStorage;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.JarFile;
import qsbk.app.activity.base.MysBaseActivity;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.image.ImageFetcher;
import qsbk.app.widget.listview.XListViewHeader;

public final class b {
    private static final Proxy a;
    private static final Proxy b;
    private static ClassLoader c;
    private static AtomicBoolean d;
    private static boolean e;

    static {
        a = new Proxy(Type.HTTP, new InetSocketAddress(HttpUtils.PROXY_IP, 80));
        b = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80));
        c = null;
        d = new AtomicBoolean(false);
        e = false;
    }

    private static synchronized ClassLoader a(Context r11_Context) {
        ClassLoader r0_ClassLoader;
        synchronized (b.class) {
            Object[] r0_ObjectA = new Object[XListViewHeader.STATE_REFRESHING];
            r0_ObjectA[0] = "getPatchClassLoader";
            r0_ObjectA[1] = d;
            d.a(r0_ObjectA);
            if (d.compareAndSet(false, true)) {
                File r3_File = new File(r11_Context.getFilesDir().getAbsolutePath() + "/" + a.d);
                File r4_File = new File(r11_Context.getFilesDir().getAbsolutePath() + "/" + a.e);
                File r5_File = new File(r11_Context.getFilesDir().getAbsolutePath() + "/" + a.c + ".tmp.jar");
                b(r11_Context);
                double r0d;
                try {
                    double r0d_2;
                    a(false, r11_Context, b.class.getResource("/extra/" + a.d), r5_File.getName());
                    double r6d = Double.parseDouble(new JarFile(r5_File).getManifest().getMainAttributes().getValue("Implementation-Version"));
                    r0d_2 = r3_File.exists() ? Double.parseDouble(new JarFile(r3_File).getManifest().getMainAttributes().getValue("Implementation-Version")) : 0.0d;
                    Object[] r8_ObjectA = new Object[3];
                    r8_ObjectA[0] = ">>>local/cache core";
                    r8_ObjectA[1] = Double.valueOf(r6d);
                    r8_ObjectA[2] = Double.valueOf(r0d_2);
                    d.c(r8_ObjectA);
                    if (r6d > r0d_2) {
                        r3_File.delete();
                        r5_File.renameTo(r3_File);
                    }
                    r4_File.delete();
                    c = new DexClassLoader(r3_File.getAbsolutePath(), r11_Context.getFilesDir().getAbsolutePath(), null, ClassLoader.getSystemClassLoader());
                    try {
                        Class r0_Class = c.loadClass("com.baidu.mobads.remote.AdView");
                        Class[] r3_ClassA = new Class[1];
                        r3_ClassA[0] = ClassLoader.class;
                        Method r1_Method = r0_Class.getDeclaredMethod("setProxyLoader", r3_ClassA);
                        r1_Method.setAccessible(true);
                        Object[] r4_ObjectA = new Object[1];
                        r4_ObjectA[0] = b.class.getClassLoader();
                        r1_Method.invoke(null, r4_ObjectA);
                        r3_ClassA = new Class[1];
                        r3_ClassA[0] = String.class;
                        Method r0_Method = r0_Class.getDeclaredMethod("setPackageName", r3_ClassA);
                        r0_Method.setAccessible(true);
                        String r1_String = b.class.getPackage().getName();
                        String r3_String = r1_String.substring(0, r1_String.lastIndexOf("."));
                        r4_ObjectA = new Object[3];
                        r4_ObjectA[0] = "AdUtil.getRemoteClassLoader().getName()";
                        r4_ObjectA[1] = r1_String;
                        r4_ObjectA[2] = r3_String;
                        d.a(r4_ObjectA);
                        r4_ObjectA = new Object[1];
                        r4_ObjectA[0] = r3_String;
                        r0_Method.invoke(null, r4_ObjectA);
                    } catch (Exception e) {
                        d.b(e);
                    }
                } catch (Exception e_2) {
                    d.b(e_2);
                    d.set(false);
                }
                r0_ObjectA = new Object[1];
                r0_ObjectA[0] = c;
                d.a(r0_ObjectA);
                r0_ClassLoader = c;
            } else {
                r0_ClassLoader = c;
            }
        }
        return r0_ClassLoader;
    }

    public static String a(Context r2_Context, String r3_String) {
        return b(r2_Context, r3_String, (int)ImageFetcher.READ_TIMEOUT, (int)MysBaseActivity.DEFAULT_REFRESH_INTERVAL);
    }

    public static HttpURLConnection a(Context r6_Context, String r7_String, int r8i, int r9i) {
        HttpURLConnection r0_HttpURLConnection;
        int r3i = XListViewHeader.STATE_REFRESHING;
        URL r1_URL = new URL(r7_String);
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r6_Context.getSystemService("connectivity");
        NetworkInfo r2_NetworkInfo = r0_ConnectivityManager.getNetworkInfo(0);
        NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getNetworkInfo(1);
        if (r0_NetworkInfo == null || (!r0_NetworkInfo.isAvailable())) {
            if (r2_NetworkInfo == null || (!r2_NetworkInfo.isAvailable())) {
                r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection();
            } else {
                String r0_String = r2_NetworkInfo.getExtraInfo();
                r0_String = r0_String != null ? r0_String.toLowerCase() : RContactStorage.PRIMARY_KEY;
                Object[] r2_ObjectA = new Object[r3i];
                r2_ObjectA[0] = "current APN";
                r2_ObjectA[1] = r0_String;
                d.a(r2_ObjectA);
                if (r0_String.startsWith("cmwap") || r0_String.startsWith("uniwap") || r0_String.startsWith("3gwap")) {
                    r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection(a);
                } else if (r0_String.startsWith("ctwap")) {
                    r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection(b);
                } else {
                    r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection();
                }
            }
        } else {
            Object[] r0_ObjectA = new Object[2];
            r0_ObjectA[0] = RContactStorage.PRIMARY_KEY;
            r0_ObjectA[1] = "WIFI is available";
            d.a(r0_ObjectA);
            r0_HttpURLConnection = (HttpURLConnection) r1_URL.openConnection();
        }
        r0_HttpURLConnection.setConnectTimeout(r8i);
        r0_HttpURLConnection.setReadTimeout(r9i);
        return r0_HttpURLConnection;
    }

    private static HttpURLConnection a(Context r1_Context, URL r2_URL) {
        return c(r1_Context, r2_URL.toString());
    }

    public static boolean a(String r4_String) {
        Object[] r1_ObjectA = new Object[2];
        r1_ObjectA[0] = "AdUtil.deleteExt";
        r1_ObjectA[1] = r4_String;
        d.a(r1_ObjectA);
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return false;
        }
        File r2_File = new File(Environment.getExternalStorageDirectory() + File.separator + r4_String);
        return r2_File.exists() ? r2_File.delete() : false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(boolean r10z, Context r11_Context, URL r12_URL, String r13_String) {
        /*
        r3 = 0;
        r9 = 3;
        r8 = 2;
        r2 = 1;
        r1 = 0;
        r0 = new java.lang.Object[r8];
        r4 = "AdUtil.save";
        r0[r1] = r4;
        r4 = "[%s] %s";
        r5 = new java.lang.Object[r8];
        r5[r1] = r13;
        r6 = r12.toString();
        r5[r2] = r6;
        r4 = java.lang.String.format(r4, r5);
        r0[r2] = r4;
        com.baidu.mobads.a.d.a(r0);
        r5 = 0;
        r0 = r12.openConnection();	 //Catch:{ IOException -> 0x010f, all -> 0x00ef }
        r0 = (java.net.JarURLConnection) r0;	 //Catch:{ IOException -> 0x010f, all -> 0x00ef }
        r0.connect();	 //Catch:{ IOException -> 0x010f, all -> 0x00ef }
        r4 = new java.io.BufferedInputStream;	 //Catch:{ IOException -> 0x010f, all -> 0x00ef }
        r0 = r0.getInputStream();	 //Catch:{ IOException -> 0x010f, all -> 0x00ef }
        r4.<init>(r0);	 //Catch:{ IOException -> 0x010f, all -> 0x00ef }
        if (r10 == 0) goto L_0x00b3;
    L_0x0035:
        r0 = android.os.Environment.getExternalStorageState();	 //Catch:{ IOException -> 0x0092 }
        r6 = "mounted";
        r0 = r6.equals(r0);	 //Catch:{ IOException -> 0x0092 }
        if (r0 != 0) goto L_0x005d;
    L_0x0041:
        if (r4 == 0) goto L_0x0046;
    L_0x0043:
        r4.close();	 //Catch:{ IOException -> 0x004c }
    L_0x0046:
        if (r3 == 0) goto L_0x004b;
    L_0x0048:
        r5.close();	 //Catch:{ IOException -> 0x004c }
    L_0x004b:
        return r1;
    L_0x004c:
        r0 = move-exception;
        r3 = new java.lang.Object[r9];
        r4 = "AdUtil.saveJar";
        r3[r1] = r4;
        r4 = "";
        r3[r2] = r4;
        r3[r8] = r0;
        com.baidu.mobads.a.d.b(r3);
        goto L_0x004b;
    L_0x005d:
        r5 = android.os.Environment.getExternalStorageDirectory();	 //Catch:{ IOException -> 0x0092 }
        r0 = new java.io.BufferedOutputStream;	 //Catch:{ IOException -> 0x0092 }
        r6 = new java.io.FileOutputStream;	 //Catch:{ IOException -> 0x0092 }
        r7 = new java.lang.StringBuilder;	 //Catch:{ IOException -> 0x0092 }
        r7.<init>();	 //Catch:{ IOException -> 0x0092 }
        r5 = r7.append(r5);	 //Catch:{ IOException -> 0x0092 }
        r7 = java.io.File.separator;	 //Catch:{ IOException -> 0x0092 }
        r5 = r5.append(r7);	 //Catch:{ IOException -> 0x0092 }
        r5 = r5.append(r13);	 //Catch:{ IOException -> 0x0092 }
        r5 = r5.toString();	 //Catch:{ IOException -> 0x0092 }
        r6.<init>(r5);	 //Catch:{ IOException -> 0x0092 }
        r0.<init>(r6);	 //Catch:{ IOException -> 0x0092 }
        r3 = r0;
    L_0x0083:
        r0 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r0 = new byte[r0];	 //Catch:{ IOException -> 0x0092 }
    L_0x0087:
        r5 = r4.read(r0);	 //Catch:{ IOException -> 0x0092 }
        if (r5 <= 0) goto L_0x00bf;
    L_0x008d:
        r6 = 0;
        r3.write(r0, r6, r5);	 //Catch:{ IOException -> 0x0092 }
        goto L_0x0087;
    L_0x0092:
        r0 = move-exception;
    L_0x0093:
        r5 = 3;
        r5 = new java.lang.Object[r5];	 //Catch:{ all -> 0x010d }
        r6 = 0;
        r7 = "AdUtil.saveJar";
        r5[r6] = r7;	 //Catch:{ all -> 0x010d }
        r6 = 1;
        r7 = "";
        r5[r6] = r7;	 //Catch:{ all -> 0x010d }
        r6 = 2;
        r5[r6] = r0;	 //Catch:{ all -> 0x010d }
        com.baidu.mobads.a.d.b(r5);	 //Catch:{ all -> 0x010d }
        if (r4 == 0) goto L_0x00ab;
    L_0x00a8:
        r4.close();	 //Catch:{ IOException -> 0x00dd }
    L_0x00ab:
        if (r3 == 0) goto L_0x00b0;
    L_0x00ad:
        r3.close();	 //Catch:{ IOException -> 0x00dd }
    L_0x00b0:
        r0 = r2;
    L_0x00b1:
        r1 = r0;
        goto L_0x004b;
    L_0x00b3:
        r0 = new java.io.BufferedOutputStream;	 //Catch:{ IOException -> 0x0092 }
        r5 = 0;
        r5 = r11.openFileOutput(r13, r5);	 //Catch:{ IOException -> 0x0092 }
        r0.<init>(r5);	 //Catch:{ IOException -> 0x0092 }
        r3 = r0;
        goto L_0x0083;
    L_0x00bf:
        if (r4 == 0) goto L_0x00c4;
    L_0x00c1:
        r4.close();	 //Catch:{ IOException -> 0x00cb }
    L_0x00c4:
        if (r3 == 0) goto L_0x00c9;
    L_0x00c6:
        r3.close();	 //Catch:{ IOException -> 0x00cb }
    L_0x00c9:
        r0 = r2;
        goto L_0x00b1;
    L_0x00cb:
        r0 = move-exception;
        r3 = new java.lang.Object[r9];
        r4 = "AdUtil.saveJar";
        r3[r1] = r4;
        r4 = "";
        r3[r2] = r4;
        r3[r8] = r0;
        com.baidu.mobads.a.d.b(r3);
        r0 = r1;
        goto L_0x00b1;
    L_0x00dd:
        r0 = move-exception;
        r3 = new java.lang.Object[r9];
        r4 = "AdUtil.saveJar";
        r3[r1] = r4;
        r4 = "";
        r3[r2] = r4;
        r3[r8] = r0;
        com.baidu.mobads.a.d.b(r3);
        r0 = r1;
        goto L_0x00b1;
    L_0x00ef:
        r0 = move-exception;
        r4 = r3;
    L_0x00f1:
        if (r4 == 0) goto L_0x00f6;
    L_0x00f3:
        r4.close();	 //Catch:{ IOException -> 0x00fc }
    L_0x00f6:
        if (r3 == 0) goto L_0x00fb;
    L_0x00f8:
        r3.close();	 //Catch:{ IOException -> 0x00fc }
    L_0x00fb:
        throw r0;
    L_0x00fc:
        r3 = move-exception;
        r4 = new java.lang.Object[r9];
        r5 = "AdUtil.saveJar";
        r4[r1] = r5;
        r1 = "";
        r4[r2] = r1;
        r4[r8] = r3;
        com.baidu.mobads.a.d.b(r4);
        goto L_0x00fb;
    L_0x010d:
        r0 = move-exception;
        goto L_0x00f1;
    L_0x010f:
        r0 = move-exception;
        r4 = r3;
        goto L_0x0093;
        */

    }

    public static Class<?> b_(Context r1_Context, String r2_String) {
        return a(r1_Context).loadClass(r2_String);
    }

    public static String b_(Context r10_Context, String r11_String, int r12i, int r13i) {
        int r7i = 0;
        if (r11_String.startsWith("file:///")) {
            return d(r10_Context, r11_String);
        }
        StringBuilder r0_StringBuilder = new StringBuilder();
        HttpURLConnection r1_HttpURLConnection = a(r10_Context, r11_String, r12i, r13i);
        r1_HttpURLConnection.connect();
        BufferedReader r2_BufferedReader = new BufferedReader(new InputStreamReader(r1_HttpURLConnection.getInputStream()));
        while (true) {
            String r3_String = r2_BufferedReader.readLine();
            if (r3_String != null) {
                r0_StringBuilder.append(r3_String);
            } else {
                Object[] r3_ObjectA = new Object[2];
                r3_ObjectA[r7i] = "AdUtil.get";
                Object[] r5_ObjectA = new Object[5];
                r5_ObjectA[r7i] = Integer.valueOf(r0_StringBuilder.length());
                r5_ObjectA[1] = Integer.valueOf(r1_HttpURLConnection.getContentLength());
                r5_ObjectA[2] = Integer.valueOf(r1_HttpURLConnection.getResponseCode());
                r5_ObjectA[3] = r1_HttpURLConnection.getResponseMessage();
                r5_ObjectA[4] = r0_StringBuilder;
                r3_ObjectA[1] = String.format("Header: %d/%d %d %s \t%s", r5_ObjectA);
                d.a(r3_ObjectA);
                if (r2_BufferedReader != null) {
                    r2_BufferedReader.close();
                }
                if (r1_HttpURLConnection.getContentLength() < 0) {
                    r0_StringBuilder = new StringBuilder("{error}");
                }
                return r0_StringBuilder.toString();
            }
        }
    }

    private static synchronized void b_(Context r4_Context) {
        synchronized (b.class) {
            if (e) {
            } else {
                e = true;
                new c(new File(r4_Context.getFilesDir().getAbsolutePath() + "/" + a.d), r4_Context).start();
            }
        }
    }

    public static boolean b_(boolean r10z, Context r11_Context, URL r12_URL, String r13_String) {
        BufferedOutputStream r0_BufferedOutputStream;
        File r2_File;
        File r1_File;
        Object[] r0_ObjectA = new Object[2];
        r0_ObjectA[0] = "AdUtil.save";
        Object[] r2_ObjectA = new Object[2];
        r2_ObjectA[0] = r13_String;
        r2_ObjectA[1] = r12_URL.toString();
        r0_ObjectA[1] = String.format("[%s] %s", r2_ObjectA);
        d.a(r0_ObjectA);
        String r1_String = r13_String + ".tm";
        HttpURLConnection r0_HttpURLConnection = a(r11_Context, r12_URL);
        r0_HttpURLConnection.connect();
        BufferedInputStream r5_BufferedInputStream = new BufferedInputStream(r0_HttpURLConnection.getInputStream());
        File r6_File;
        if (r10z) {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return false;
            }
            r6_File = Environment.getExternalStorageDirectory();
            r0_BufferedOutputStream = new BufferedOutputStream(new FileOutputStream(r6_File + File.separator + r1_String));
            r2_File = new File(r6_File + File.separator + r1_String);
            r1_File = new File(r6_File + File.separator + r13_String);
        } else {
            r0_BufferedOutputStream = new BufferedOutputStream(r11_Context.openFileOutput(r1_String, 1));
            r6_File = r11_Context.getFilesDir();
            r2_ObjectA = new Object[3];
            r2_ObjectA[0] = "AdUtil.save";
            r2_ObjectA[1] = "localFile:";
            r2_ObjectA[2] = r6_File;
            d.a(r2_ObjectA);
            r2_File = new File(r6_File + File.separator + r1_String);
            r1_File = new File(r6_File + File.separator + r13_String);
        }
        byte[] r6_byteA = new byte[5120];
        while (true) {
            int r7i = r5_BufferedInputStream.read(r6_byteA);
            if (r7i > 0) {
                r0_BufferedOutputStream.write(r6_byteA, 0, r7i);
            } else {
                if (r5_BufferedInputStream != null) {
                    r5_BufferedInputStream.close();
                }
                if (r0_BufferedOutputStream != null) {
                    r0_BufferedOutputStream.close();
                }
                boolean r0z;
                if (r2_File == null || (!r2_File.exists())) {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "AdUtil.save";
                    r0_ObjectA[1] = "tmFile not exists";
                    d.a(r0_ObjectA);
                    r0z = false;
                    return r0z;
                } else if (r2_File.renameTo(r1_File)) {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "AdUtil.save";
                    r0_ObjectA[1] = "rename success";
                    d.a(r0_ObjectA);
                    r0z = true;
                    return r0z;
                } else {
                    r0_ObjectA = new Object[2];
                    r0_ObjectA[0] = "AdUtil.save";
                    r0_ObjectA[1] = "rename failed";
                    d.a(r0_ObjectA);
                    r0z = false;
                    return r0z;
                }
            }
        }
    }

    private static HttpURLConnection c(Context r2_Context, String r3_String) {
        return a(r2_Context, r3_String, (int)ImageFetcher.READ_TIMEOUT, (int)MysBaseActivity.DEFAULT_REFRESH_INTERVAL);
    }

    private static String d(Context r4_Context, String r5_String) {
        StringBuilder r0_StringBuilder = new StringBuilder();
        int r1i = r5_String.indexOf(BDLocation.TypeNetWorkException);
        if (r1i >= 0) {
            r5_String = r5_String.substring(0, r1i);
        }
        URLConnection r1_URLConnection = new URL(r5_String).openConnection();
        r1_URLConnection.connect();
        BufferedReader r2_BufferedReader = new BufferedReader(new InputStreamReader(r1_URLConnection.getInputStream()));
        while (true) {
            String r1_String = r2_BufferedReader.readLine();
            if (r1_String != null) {
                r0_StringBuilder.append(r1_String);
            } else {
                if (r2_BufferedReader != null) {
                    r2_BufferedReader.close();
                }
                return r0_StringBuilder.toString();
            }
        }
    }
}