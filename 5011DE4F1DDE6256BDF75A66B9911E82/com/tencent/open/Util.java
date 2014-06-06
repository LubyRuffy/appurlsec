package com.tencent.open;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.push.Utils;
import qsbk.app.share.QQDialogAuthorizeActivity;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;
import qsbk.app.utils.HttpUtils;

// compiled from: ProGuard
public class Util {
    private static boolean a;

    // compiled from: ProGuard
    public class NetworkProxy {
        public final String a;
        public final int b;

        private NetworkProxy(String r1_String, int r2i) {
            this.a = r1_String;
            this.b = r2i;
        }
    }

    // compiled from: ProGuard
    public class Statistic {
        public String a;
        public long b;
        public long c;

        public Statistic(String r3_String, int r4i) {
            this.a = r3_String;
            this.b = (long) r4i;
            if (this.a != null) {
                this.c = (long) this.a.length();
            }
        }
    }

    static {
        a = true;
    }

    private static char a(int r2i) {
        int r0i = r2i & 15;
        return r0i < 10 ? (char) (r0i + 48) : (char) (r0i - 10 + 97);
    }

    public static Bundle a(String r8_String) {
        Bundle r2_Bundle = new Bundle();
        if (r8_String != null) {
            String[] r3_StringA = r8_String.split("&");
            int r4i = r3_StringA.length;
            int r0i = 0;
            while (r0i < r4i) {
                String[] r5_StringA = r3_StringA[r0i].split("=");
                if (r5_StringA.length == 2) {
                    r2_Bundle.putString(URLDecoder.decode(r5_StringA[0]), URLDecoder.decode(r5_StringA[1]));
                }
                r0i++;
            }
        }
        return r2_Bundle;
    }

    public static NetworkProxy a(Context r4_Context) {
        if (r4_Context == null) {
            return null;
        }
        ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r4_Context.getSystemService("connectivity");
        if (r0_ConnectivityManager == null) {
            return null;
        }
        NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
        if (r0_NetworkInfo == null) {
            return null;
        }
        if (r0_NetworkInfo.getType() == 0) {
            String r2_String = c(r4_Context);
            int r3i = d(r4_Context);
            if (!e(r2_String) && r3i >= 0) {
                return new NetworkProxy(r3i, null);
            }
        }
        return null;
    }

    public static Statistic a(Context r12_Context, String r13_String, String r14_String, Bundle r15_Bundle) {
        Object r1_Object;
        int r0i;
        if (r12_Context != null) {
            ConnectivityManager r0_ConnectivityManager = (ConnectivityManager) r12_Context.getSystemService("connectivity");
            if (r0_ConnectivityManager != null) {
                NetworkInfo r0_NetworkInfo = r0_ConnectivityManager.getActiveNetworkInfo();
                if (r0_NetworkInfo == null || (!r0_NetworkInfo.isAvailable())) {
                    throw new NetworkUnavailableException("network unavailable");
                }
            }
        }
        Bundle r5_Bundle = new Bundle(r15_Bundle);
        String r0_String = RContactStorage.PRIMARY_KEY;
        r0_String = r5_Bundle.getString("appid_for_getting_config");
        r5_Bundle.remove("appid_for_getting_config");
        HttpClient r6_HttpClient = a(r12_Context, r0_String, r13_String);
        Object r0_Object = null;
        int r1i;
        if (r14_String.equals(HttpManager.HTTPMETHOD_GET)) {
            String r4_String = a(r5_Bundle);
            r1i = 0 + r4_String.length();
            HttpUriRequest r0_HttpUriRequest = new HttpGet((r13_String.indexOf("?") == -1 ? r13_String + "?" : r13_String + "&") + r4_String);
            r0_HttpUriRequest.addHeader("Accept-Encoding", "gzip");
            r1_Object = r0_HttpUriRequest;
            r0i = r1i;
        } else if (r14_String.equals(UsersAPI.HTTPMETHOD_POST)) {
            HttpPost r4_HttpPost = new HttpPost(r13_String);
            r4_HttpPost.addHeader("Accept-Encoding", "gzip");
            Bundle r7_Bundle = new Bundle();
            Iterator r8_Iterator = r5_Bundle.keySet().iterator();
            while (r8_Iterator.hasNext()) {
                r0_String = (String) r8_Iterator.next();
                r1_Object = r5_Bundle.get(r0_String);
                if (r1_Object instanceof byte[]) {
                    r7_Bundle.putByteArray(r0_String, (byte[]) r1_Object);
                }
            }
            if (!r5_Bundle.containsKey(Utils.RESPONSE_METHOD)) {
                r5_Bundle.putString(Utils.RESPONSE_METHOD, r14_String);
            }
            r4_HttpPost.setHeader("Content-Type", "multipart/form-data;boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
            r4_HttpPost.setHeader("Connection", "Keep-Alive");
            ByteArrayOutputStream r8_ByteArrayOutputStream = new ByteArrayOutputStream();
            r8_ByteArrayOutputStream.write("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
            r8_ByteArrayOutputStream.write(a(r5_Bundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
            if (!r7_Bundle.isEmpty()) {
                int r5i = r7_Bundle.size();
                r8_ByteArrayOutputStream.write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
                Iterator r9_Iterator = r7_Bundle.keySet().iterator();
                r1i = -1;
                while (r9_Iterator.hasNext()) {
                    r0_String = (String) r9_Iterator.next();
                    r1i++;
                    r8_ByteArrayOutputStream.write(("Content-Disposition: form-data; name=\"" + r0_String + "\"; filename=\"" + r0_String + "\"" + "\r\n").getBytes());
                    r8_ByteArrayOutputStream.write("Content-Type: content/unknown\r\n\r\n".getBytes());
                    r8_ByteArrayOutputStream.write(r7_Bundle.getByteArray(r0_String));
                    if (r1i < r5i - 1) {
                        r8_ByteArrayOutputStream.write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
                    }
                }
            }
            r8_ByteArrayOutputStream.write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n".getBytes());
            byte[] r1_byteA = r8_ByteArrayOutputStream.toByteArray();
            r0i = r1_byteA.length + 0;
            r8_ByteArrayOutputStream.close();
            r4_HttpPost.setEntity(new ByteArrayEntity(r1_byteA));
            r1_Object = r4_HttpPost;
        } else {
            r1_Object = r0_Object;
            r0i = 0;
        }
        HttpResponse r1_HttpResponse = r6_HttpClient.execute(r1_Object);
        int r2i = r1_HttpResponse.getStatusLine().getStatusCode();
        if (r2i == 200) {
            return new Statistic(a(r1_HttpResponse), r0i);
        }
        throw new HttpStatusException("http status code error:" + r2i);
    }

    public static String a() {
        try {
            Enumeration r1_Enumeration = NetworkInterface.getNetworkInterfaces();
            while (r1_Enumeration != null && r1_Enumeration.hasMoreElements()) {
                Enumeration r2_Enumeration = ((NetworkInterface) r1_Enumeration.nextElement()).getInetAddresses();
                while (r2_Enumeration.hasMoreElements()) {
                    InetAddress r0_InetAddress = (InetAddress) r2_Enumeration.nextElement();
                    if (!r0_InetAddress.isLoopbackAddress()) {
                        return r0_InetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            a("Tencent-Util", e.toString());
        }
        return RContactStorage.PRIMARY_KEY;
    }

    public static String a(Bundle r8_Bundle) {
        if (r8_Bundle == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuilder r4_StringBuilder = new StringBuilder();
        Iterator r5_Iterator = r8_Bundle.keySet().iterator();
        int r1i = 1;
        while (r5_Iterator.hasNext()) {
            String r0_String = (String) r5_Iterator.next();
            Object r3_Object = r8_Bundle.get(r0_String);
            if (r3_Object instanceof String || r3_Object instanceof String[]) {
                int r0i;
                if (r3_Object instanceof String[]) {
                    if (r1i != 0) {
                        r1i = 0;
                    } else {
                        r4_StringBuilder.append("&");
                    }
                    r4_StringBuilder.append(URLEncoder.encode(r0_String) + "=");
                    String[] r0_StringA = r8_Bundle.getStringArray(r0_String);
                    int r3i = 0;
                    while (r3i < r0_StringA.length) {
                        if (r3i == 0) {
                            r4_StringBuilder.append(URLEncoder.encode(r0_StringA[r3i]));
                        } else {
                            r4_StringBuilder.append(URLEncoder.encode("," + r0_StringA[r3i]));
                        }
                        r3i++;
                    }
                    r0i = r1i;
                } else {
                    if (r1i != 0) {
                        r1i = 0;
                    } else {
                        r4_StringBuilder.append("&");
                    }
                    r4_StringBuilder.append(URLEncoder.encode(r0_String) + "=" + URLEncoder.encode(r8_Bundle.getString(r0_String)));
                    r0i = r1i;
                }
                r1i = r0i;
            }
        }
        return r4_StringBuilder.toString();
    }

    public static String a(Bundle r8_Bundle, String r9_String) {
        if (r8_Bundle == null) {
            return RContactStorage.PRIMARY_KEY;
        }
        StringBuilder r3_StringBuilder = new StringBuilder();
        int r4i = r8_Bundle.size();
        Iterator r5_Iterator = r8_Bundle.keySet().iterator();
        int r1i = -1;
        while (r5_Iterator.hasNext()) {
            String r0_String = (String) r5_Iterator.next();
            int r2i = r1i + 1;
            Object r1_Object = r8_Bundle.get(r0_String);
            if (r1_Object instanceof String) {
                r3_StringBuilder.append("Content-Disposition: form-data; name=\"" + r0_String + "\"" + "\r\n" + "\r\n" + ((String) r1_Object));
                if (r2i < r4i - 1) {
                    r3_StringBuilder.append("\r\n--" + r9_String + "\r\n");
                }
                r1i = r2i;
            } else {
                r1i = r2i;
            }
        }
        return r3_StringBuilder.toString();
    }

    private static String a(HttpResponse r6_HttpResponse) {
        InputStream r0_InputStream;
        String r0_String = RContactStorage.PRIMARY_KEY;
        InputStream r1_InputStream = r6_HttpResponse.getEntity().getContent();
        ByteArrayOutputStream r2_ByteArrayOutputStream = new ByteArrayOutputStream();
        Header r0_Header = r6_HttpResponse.getFirstHeader("Content-Encoding");
        r0_InputStream = (r0_Header == null || r0_Header.getValue().toLowerCase().indexOf("gzip") <= -1) ? r1_InputStream : new GZIPInputStream(r1_InputStream);
        byte[] r1_byteA = new byte[512];
        while (true) {
            int r3i = r0_InputStream.read(r1_byteA);
            if (r3i == -1) {
                return new String(r2_ByteArrayOutputStream.toByteArray());
            }
            r2_ByteArrayOutputStream.write(r1_byteA, 0, r3i);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static HttpClient a(Context r8_Context, String r9_String, String r10_String) {
        /*
        r1 = 0;
        r0 = new com.tencent.open.MySSLSocketFactory;	 //Catch:{ KeyManagementException -> 0x0160, NoSuchAlgorithmException -> 0x0169, KeyStoreException -> 0x0172, UnrecoverableKeyException -> 0x017b }
        r0.<init>();	 //Catch:{ KeyManagementException -> 0x0160, NoSuchAlgorithmException -> 0x0169, KeyStoreException -> 0x0172, UnrecoverableKeyException -> 0x017b }
        r1 = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;	 //Catch:{ KeyManagementException -> 0x018a, NoSuchAlgorithmException -> 0x0188, KeyStoreException -> 0x0186, UnrecoverableKeyException -> 0x0184 }
        r0.setHostnameVerifier(r1);	 //Catch:{ KeyManagementException -> 0x018a, NoSuchAlgorithmException -> 0x0188, KeyStoreException -> 0x0186, UnrecoverableKeyException -> 0x0184 }
    L_0x000b:
        r2 = new org.apache.http.params.BasicHttpParams;
        r2.<init>();
        r1 = com.tencent.open.OpenConfig.a(r8, r9);
        r3 = "Common_HttpConnectionTimeout";
        r1 = r1.b(r3);
        r3 = "OpenConfig_test";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "config 3:Common_HttpConnectionTimeout     config_value:";
        r4 = r4.append(r5);
        r4 = r4.append(r1);
        r5 = "   appid:";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r5 = "     url:";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r4 = r4.toString();
        android.util.Log.d(r3, r4);
        if (r1 != 0) goto L_0x004a;
    L_0x0048:
        r1 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
    L_0x004a:
        r3 = "OpenConfig_test";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "config 3:Common_HttpConnectionTimeout     result_value:";
        r4 = r4.append(r5);
        r4 = r4.append(r1);
        r5 = "   appid:";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r5 = "     url:";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r4 = r4.toString();
        android.util.Log.d(r3, r4);
        org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r2, r1);
        r1 = com.tencent.open.OpenConfig.a(r8, r9);
        r3 = "Common_SocketConnectionTimeout";
        r1 = r1.b(r3);
        r3 = "OpenConfig_test";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "config 4:Common_SocketConnectionTimeout   config_value:";
        r4 = r4.append(r5);
        r4 = r4.append(r1);
        r5 = "   appid:";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r5 = "     url:";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r4 = r4.toString();
        android.util.Log.d(r3, r4);
        if (r1 != 0) goto L_0x00b3;
    L_0x00b1:
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
    L_0x00b3:
        r3 = "OpenConfig_test";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "config 4:Common_SocketConnectionTimeout   result_value:";
        r4 = r4.append(r5);
        r4 = r4.append(r1);
        r5 = "   appid:";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r5 = "     url:";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r4 = r4.toString();
        android.util.Log.d(r3, r4);
        org.apache.http.params.HttpConnectionParams.setSoTimeout(r2, r1);
        r1 = org.apache.http.HttpVersion.HTTP_1_1;
        org.apache.http.params.HttpProtocolParams.setVersion(r2, r1);
        r1 = "UTF-8";
        org.apache.http.params.HttpProtocolParams.setContentCharset(r2, r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "AndroidSDK_";
        r1 = r1.append(r3);
        r3 = android.os.Build.VERSION.SDK;
        r1 = r1.append(r3);
        r3 = "_";
        r1 = r1.append(r3);
        r3 = android.os.Build.DEVICE;
        r1 = r1.append(r3);
        r3 = "_";
        r1 = r1.append(r3);
        r3 = android.os.Build.VERSION.RELEASE;
        r1 = r1.append(r3);
        r1 = r1.toString();
        org.apache.http.params.HttpProtocolParams.setUserAgent(r2, r1);
        r1 = new org.apache.http.conn.scheme.SchemeRegistry;
        r1.<init>();
        r3 = new org.apache.http.conn.scheme.Scheme;
        r4 = "http";
        r5 = org.apache.http.conn.scheme.PlainSocketFactory.getSocketFactory();
        r6 = 80;
        r3.<init>(r4, r5, r6);
        r1.register(r3);
        r3 = new org.apache.http.conn.scheme.Scheme;
        r4 = "https";
        r5 = 443; // 0x1bb float:6.21E-43 double:2.19E-321;
        r3.<init>(r4, r0, r5);
        r1.register(r3);
        r0 = new org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
        r0.<init>(r2, r1);
        r1 = new org.apache.http.impl.client.DefaultHttpClient;
        r1.<init>(r0, r2);
        r0 = a(r8);
        if (r0 == 0) goto L_0x015f;
    L_0x014d:
        r2 = new org.apache.http.HttpHost;
        r3 = r0.a;
        r0 = r0.b;
        r2.<init>(r3, r0);
        r0 = r1.getParams();
        r3 = "http.route.default-proxy";
        r0.setParameter(r3, r2);
    L_0x015f:
        return r1;
    L_0x0160:
        r0 = move-exception;
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x0164:
        r1.printStackTrace();
        goto L_0x000b;
    L_0x0169:
        r0 = move-exception;
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x016d:
        r1.printStackTrace();
        goto L_0x000b;
    L_0x0172:
        r0 = move-exception;
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x0176:
        r1.printStackTrace();
        goto L_0x000b;
    L_0x017b:
        r0 = move-exception;
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x017f:
        r1.printStackTrace();
        goto L_0x000b;
    L_0x0184:
        r1 = move-exception;
        goto L_0x017f;
    L_0x0186:
        r1 = move-exception;
        goto L_0x0176;
    L_0x0188:
        r1 = move-exception;
        goto L_0x016d;
    L_0x018a:
        r1 = move-exception;
        goto L_0x0164;
        */

    }

    public static JSONObject a(JSONObject r6_JSONObject, String r7_String) {
        if (r6_JSONObject == null) {
            r6_JSONObject = new JSONObject();
        }
        if (r7_String != null) {
            String[] r2_StringA = r7_String.split("&");
            int r3i = r2_StringA.length;
            int r1i = 0;
            while (r1i < r3i) {
                String[] r0_StringA = r2_StringA[r1i].split("=");
                if (r0_StringA.length == 2) {
                    try {
                        r6_JSONObject.put(URLDecoder.decode(r0_StringA[0]), URLDecoder.decode(r0_StringA[1]));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                r1i++;
            }
        }
        return r6_JSONObject;
    }

    public static void a(Context r3_Context, String r4_String, long r5j, String r7_String) {
        Bundle r0_Bundle = new Bundle();
        r0_Bundle.putString("appid_for_getting_config", r7_String);
        r0_Bundle.putString("strValue", r7_String);
        r0_Bundle.putString("nValue", r4_String);
        r0_Bundle.putString("qver", Constants.SDK_VERSION);
        if (r5j != 0) {
            r0_Bundle.putLong("elt", r5j);
        }
        new p(r3_Context, r0_Bundle).start();
    }

    private static void a(Context r2_Context, String r3_String, String r4_String, String r5_String) {
        Intent r0_Intent = new Intent();
        r0_Intent.setComponent(new ComponentName(r3_String, r4_String));
        r0_Intent.setAction("android.intent.action.VIEW");
        r0_Intent.addFlags(1073741824);
        r0_Intent.addFlags(268435456);
        r0_Intent.setData(Uri.parse(r5_String));
        r2_Context.startActivity(r0_Intent);
    }

    public static void a(String r1_String, String r2_String) {
        if (a) {
            Log.d(r1_String, r2_String);
        }
    }

    public static boolean a(Context r2_Context, String r3_String) {
        try {
            a(r2_Context, "com.android.browser", "com.android.browser.BrowserActivity", r3_String);
        } catch (Exception e) {
            a(r2_Context, "com.google.android.browser", "com.android.browser.BrowserActivity", r3_String);
        }
        return true;
    }

    public static Bundle b(String r2_String) {
        try {
            URL r1_URL = new URL(r2_String.replace("auth://", HttpUtils.http));
            Bundle r0_Bundle = a(r1_URL.getQuery());
            r0_Bundle.putAll(a(r1_URL.getRef()));
            return r0_Bundle;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static boolean b() {
        File r0_File = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            r0_File = Environment.getExternalStorageDirectory();
        }
        return r0_File != null;
    }

    public static boolean b(Context r7_Context) {
        PackageInfo r2_PackageInfo;
        try {
            r2_PackageInfo = r7_Context.getPackageManager().getPackageInfo(Constants.MOBILEQQ_PACKAGE_NAME, 0);
        } catch (NameNotFoundException e) {
            Log.d("checkMobileQQ", QQDialogAuthorizeActivity.ERROR_RET);
            e.printStackTrace();
            r2_PackageInfo = null;
        }
        if (r2_PackageInfo == null) {
            return false;
        }
        String r2_String = r2_PackageInfo.versionName;
        try {
            Log.d("MobileQQ verson", r2_String);
            String[] r2_StringA = r2_String.split("\\.");
            int r3i = Integer.parseInt(r2_StringA[0]);
            int r2i = Integer.parseInt(r2_StringA[1]);
            if (r3i <= 4) {
                if (r3i != 4 || r2i < 1) {
                    return false;
                }
            }
            return true;
        } catch (Exception e_2) {
            e_2.printStackTrace();
            return false;
        }
    }

    private static String c(Context r2_Context) {
        if (VERSION.SDK_INT >= 11) {
            return System.getProperty("http.proxyHost");
        }
        if (r2_Context == null) {
            return Proxy.getDefaultHost();
        }
        String r0_String = Proxy.getHost(r2_Context);
        return e(r0_String) ? Proxy.getDefaultHost() : r0_String;
    }

    public static JSONObject c(String r3_String) {
        try {
            URL r1_URL = new URL(r3_String.replace("auth://", HttpUtils.http));
            JSONObject r0_JSONObject = a(null, r1_URL.getQuery());
            a(r0_JSONObject, r1_URL.getRef());
            return r0_JSONObject;
        } catch (MalformedURLException e) {
            return new JSONObject();
        }
    }

    private static int d(Context r3_Context) {
        if (VERSION.SDK_INT < 11) {
            if (r3_Context == null) {
                return Proxy.getDefaultPort();
            }
            int r0i = Proxy.getPort(r3_Context);
            return r0i < 0 ? Proxy.getDefaultPort() : r0i;
        } else {
            String r1_String = System.getProperty("http.proxyPort");
            if (e(r1_String)) {
                return -1;
            }
            try {
                return Integer.parseInt(r1_String);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    public static JSONObject d(String r2_String) {
        if (r2_String.equals("false")) {
            r2_String = "{value : false}";
        }
        if (r2_String.equals("true")) {
            r2_String = "{value : true}";
        }
        if (r2_String.contains("allback(")) {
            r2_String = r2_String.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        return new JSONObject(r2_String);
    }

    public static boolean e(String r1_String) {
        return r1_String == null || r1_String.length() == 0;
    }

    public static String f(String r6_String) {
        try {
            MessageDigest r0_MessageDigest = MessageDigest.getInstance("MD5");
            r0_MessageDigest.update(r6_String.getBytes());
            byte[] r1_byteA = r0_MessageDigest.digest();
            if (r1_byteA == null) {
                return r6_String;
            }
            StringBuilder r2_StringBuilder = new StringBuilder();
            int r3i = r1_byteA.length;
            int r0i = 0;
            while (r0i < r3i) {
                int r4i = r1_byteA[r0i];
                r2_StringBuilder.append(a(r4i >>> 4));
                r2_StringBuilder.append(a(r4i));
                r0i++;
            }
            return r2_StringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return r6_String;
        }
    }
}