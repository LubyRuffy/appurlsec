package com.aps;

import android.content.Context;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: NetManager.java
public class j {
    private static j a;

    static {
        a = null;
    }

    private j() {
    }

    public static int a(NetworkInfo r2_NetworkInfo) {
        return (r2_NetworkInfo != null && r2_NetworkInfo.isAvailable() && r2_NetworkInfo.isConnected()) ? r2_NetworkInfo.getType() : -1;
    }

    public static synchronized j a() {
        j r0_j;
        synchronized (j.class) {
            if (a == null) {
                a = new j();
            }
            r0_j = a;
        }
        return r0_j;
    }

    public static String a(TelephonyManager r3_TelephonyManager) {
        int r0i = 0;
        if (r3_TelephonyManager != null) {
            r0i = r3_TelephonyManager.getNetworkType();
        }
        return (String) f.l.get(r0i, "UNKNOWN");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static HttpClient a(Context r12_Context, NetworkInfo r13_NetworkInfo) throws Exception {
        /*
        r6 = -1;
        r8 = 80;
        r9 = 1;
        r10 = 0;
        r7 = 0;
        r11 = new org.apache.http.params.BasicHttpParams;
        r11.<init>();
        r0 = r13.getType();
        if (r0 != 0) goto L_0x0161;
    L_0x0011:
        r0 = "content://telephony/carriers/preferapn";
        r1 = android.net.Uri.parse(r0);
        r0 = r12.getContentResolver();
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 //Catch:{ SecurityException -> 0x00cd, Exception -> 0x0124, all -> 0x0134 }
        if (r2 == 0) goto L_0x0159;
    L_0x0025:
        r0 = r2.moveToFirst();	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r0 == 0) goto L_0x0159;
    L_0x002b:
        r0 = "apn";
        r0 = r2.getColumnIndex(r0);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        r0 = r2.getString(r0);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r0 == 0) goto L_0x0049;
    L_0x0037:
        r0 = r0.toLowerCase();	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        r1 = 2;
        r1 = new java.lang.Object[r1];	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        r3 = 0;
        r4 = "nm|found apn:";
        r1[r3] = r4;	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        r3 = 1;
        r1[r3] = r0;	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        com.aps.o.a(r1);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
    L_0x0049:
        if (r0 == 0) goto L_0x00a9;
    L_0x004b:
        r1 = "ctwap";
        r1 = r0.contains(r1);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r1 == 0) goto L_0x00a9;
    L_0x0053:
        r0 = b();	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        r1 = android.text.TextUtils.isEmpty(r0);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r1 != 0) goto L_0x015d;
    L_0x005d:
        r1 = "null";
        r1 = r0.equals(r1);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r1 != 0) goto L_0x015d;
    L_0x0065:
        r1 = r9;
    L_0x0066:
        if (r1 != 0) goto L_0x006a;
    L_0x0068:
        r0 = "10.0.0.200";
    L_0x006a:
        r1 = r0;
        r0 = r8;
    L_0x006c:
        if (r2 == 0) goto L_0x0071;
    L_0x006e:
        r2.close();
    L_0x0071:
        r2 = a(r1, r0);
        if (r2 == 0) goto L_0x0083;
    L_0x0077:
        r2 = "http";
        r3 = new org.apache.http.HttpHost;
        r3.<init>(r1, r0, r2);
        r0 = "http.route.default-proxy";
        r11.setParameter(r0, r3);
    L_0x0083:
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        com.aps.o.a(r11, r0);
        org.apache.http.params.HttpProtocolParams.setUseExpectContinue(r11, r10);
        r0 = new org.apache.http.conn.scheme.SchemeRegistry;
        r0.<init>();
        r1 = org.apache.http.conn.scheme.PlainSocketFactory.getSocketFactory();
        r2 = new org.apache.http.conn.scheme.Scheme;
        r3 = "http";
        r2.<init>(r3, r1, r8);
        r0.register(r2);
        r1 = new org.apache.http.impl.conn.SingleClientConnManager;
        r1.<init>(r11, r0);
        r0 = new org.apache.http.impl.client.DefaultHttpClient;
        r0.<init>(r1, r11);
        return r0;
    L_0x00a9:
        if (r0 == 0) goto L_0x0159;
    L_0x00ab:
        r1 = "wap";
        r0 = r0.contains(r1);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r0 == 0) goto L_0x0159;
    L_0x00b3:
        r0 = b();	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        r1 = android.text.TextUtils.isEmpty(r0);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r1 != 0) goto L_0x0155;
    L_0x00bd:
        r1 = "null";
        r1 = r0.equals(r1);	 //Catch:{ SecurityException -> 0x0147, Exception -> 0x0141 }
        if (r1 != 0) goto L_0x0155;
    L_0x00c5:
        r1 = r9;
    L_0x00c6:
        if (r1 != 0) goto L_0x00ca;
    L_0x00c8:
        r0 = "10.0.0.172";
    L_0x00ca:
        r1 = r0;
        r0 = r8;
        goto L_0x006c;
    L_0x00cd:
        r0 = move-exception;
        r0 = r7;
    L_0x00cf:
        r1 = r13.getExtraInfo();	 //Catch:{ all -> 0x013e }
        if (r1 == 0) goto L_0x0150;
    L_0x00d5:
        r1 = r13.getExtraInfo();	 //Catch:{ all -> 0x013e }
        r2 = r1.toLowerCase();	 //Catch:{ all -> 0x013e }
        r1 = b();	 //Catch:{ all -> 0x013e }
        r3 = "ctwap";
        r3 = r2.indexOf(r3);	 //Catch:{ all -> 0x013e }
        if (r3 == r6) goto L_0x0105;
    L_0x00e9:
        r2 = android.text.TextUtils.isEmpty(r1);	 //Catch:{ all -> 0x013e }
        if (r2 != 0) goto L_0x0153;
    L_0x00ef:
        r2 = "null";
        r2 = r1.equals(r2);	 //Catch:{ all -> 0x013e }
        if (r2 != 0) goto L_0x0153;
    L_0x00f7:
        r0 = r1;
    L_0x00f8:
        if (r9 != 0) goto L_0x00fc;
    L_0x00fa:
        r0 = "10.0.0.200";
    L_0x00fc:
        r1 = r0;
        r0 = r8;
    L_0x00fe:
        if (r7 == 0) goto L_0x0071;
    L_0x0100:
        r7.close();
        goto L_0x0071;
    L_0x0105:
        r3 = "wap";
        r2 = r2.indexOf(r3);	 //Catch:{ all -> 0x013e }
        if (r2 == r6) goto L_0x0150;
    L_0x010d:
        r2 = android.text.TextUtils.isEmpty(r1);	 //Catch:{ all -> 0x013e }
        if (r2 != 0) goto L_0x014e;
    L_0x0113:
        r2 = "null";
        r2 = r1.equals(r2);	 //Catch:{ all -> 0x013e }
        if (r2 != 0) goto L_0x014e;
    L_0x011b:
        r0 = r1;
        r1 = r9;
    L_0x011d:
        if (r1 != 0) goto L_0x0121;
    L_0x011f:
        r0 = "10.0.0.200";
    L_0x0121:
        r1 = r0;
        r0 = r8;
        goto L_0x00fe;
    L_0x0124:
        r0 = move-exception;
        r1 = r0;
        r2 = r7;
        r0 = r7;
    L_0x0128:
        com.aps.o.a(r1);	 //Catch:{ all -> 0x013c }
        if (r2 == 0) goto L_0x0130;
    L_0x012d:
        r2.close();
    L_0x0130:
        r1 = r0;
        r0 = r6;
        goto L_0x0071;
    L_0x0134:
        r0 = move-exception;
        r2 = r7;
    L_0x0136:
        if (r2 == 0) goto L_0x013b;
    L_0x0138:
        r2.close();
    L_0x013b:
        throw r0;
    L_0x013c:
        r0 = move-exception;
        goto L_0x0136;
    L_0x013e:
        r0 = move-exception;
        r2 = r7;
        goto L_0x0136;
    L_0x0141:
        r0 = move-exception;
        r1 = r0;
        r0 = r7;
        goto L_0x0128;
    L_0x0145:
        r1 = move-exception;
        goto L_0x0128;
    L_0x0147:
        r0 = move-exception;
        r0 = r7;
        r7 = r2;
        goto L_0x00cf;
    L_0x014b:
        r1 = move-exception;
        r7 = r2;
        goto L_0x00cf;
    L_0x014e:
        r1 = r10;
        goto L_0x011d;
    L_0x0150:
        r1 = r0;
        r0 = r6;
        goto L_0x00fe;
    L_0x0153:
        r9 = r10;
        goto L_0x00f8;
    L_0x0155:
        r1 = r10;
        r0 = r7;
        goto L_0x00c6;
    L_0x0159:
        r0 = r6;
        r1 = r7;
        goto L_0x006c;
    L_0x015d:
        r1 = r10;
        r0 = r7;
        goto L_0x0066;
    L_0x0161:
        r0 = r6;
        r1 = r7;
        goto L_0x0071;
        */

    }

    private static boolean a(String r1_String, int r2i) {
        return r1_String != null && r1_String.length() > 0 && r2i != -1;
    }

    private static boolean a(HttpResponse r2_HttpResponse) {
        Header r0_Header = r2_HttpResponse.getFirstHeader("Content-Encoding");
        return r0_Header != null && r0_Header.getValue().equalsIgnoreCase("gzip");
    }

    public static String[] a(JSONObject r8_JSONObject) {
        String[] r0_StringA = new String[5];
        r0_StringA[0] = null;
        r0_StringA[1] = null;
        r0_StringA[2] = null;
        r0_StringA[3] = null;
        r0_StringA[4] = null;
        if (r8_JSONObject == null || "http://apilocate.amap.com/mobile/binary".length() == 0) {
            r0_StringA[0] = "false";
        } else {
            try {
                CharSequence r1_CharSequence = r8_JSONObject.getString(SharedPref.KEY);
                CharSequence r2_CharSequence = r8_JSONObject.getString("X-INFO");
                String r3_String = r8_JSONObject.getString("X-BIZ");
                CharSequence r4_CharSequence = r8_JSONObject.getString("User-Agent");
                if (TextUtils.isEmpty(r1_CharSequence) || TextUtils.isEmpty(r2_CharSequence) || TextUtils.isEmpty(r4_CharSequence)) {
                    if (r0_StringA[0] == null || (!r0_StringA[0].equals("true"))) {
                        r0_StringA[0] = "false";
                    }
                } else {
                    r0_StringA[0] = "true";
                    r0_StringA[1] = r1_CharSequence;
                    r0_StringA[2] = r2_CharSequence;
                    r0_StringA[3] = r3_String;
                    r0_StringA[4] = r4_CharSequence;
                    if (r0_StringA[0] == null || r0_StringA[0].equals("true")) {
                        r0_StringA[0] = "false";
                    }
                }
            } catch (JSONException e) {
            }
        }
        return r0_StringA;
    }

    private static String b() {
        String r0_String;
        try {
            r0_String = ((String) l.a("android.net.Proxy", "getDefaultHost", null, null)).toString();
        } catch (Exception e) {
            r0_String = null;
        }
        return r0_String == null ? "null" : r0_String;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized String a(Context r12_Context, String r13_String, byte[] r14_byteA) {
        /*
        r11_this = this;
        r10 = -1;
        r0 = 0;
        monitor-enter(r11);
        r1 = android.text.TextUtils.isEmpty(r13);	 //Catch:{ all -> 0x016b }
        if (r1 != 0) goto L_0x000b;
    L_0x0009:
        if (r14 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r11);
        return r0;
    L_0x000d:
        r2 = com.aps.o.b(r12);	 //Catch:{ all -> 0x016b }
        r1 = a(r2);	 //Catch:{ all -> 0x016b }
        if (r1 == r10) goto L_0x000b;
    L_0x0017:
        r8 = new java.lang.StringBuffer;	 //Catch:{ all -> 0x016b }
        r8.<init>();	 //Catch:{ all -> 0x016b }
        r1 = "";
        r7 = a(r12, r2);	 //Catch:{ UnknownHostException -> 0x02f4, SocketException -> 0x0177, SocketTimeoutException -> 0x01a8, ConnectTimeoutException -> 0x01d9, Exception -> 0x020a, all -> 0x0140 }
        r6 = new org.apache.http.client.methods.HttpPost;	 //Catch:{ UnknownHostException -> 0x02fd, SocketException -> 0x02d3, SocketTimeoutException -> 0x02b2, ConnectTimeoutException -> 0x0291, Exception -> 0x0276, all -> 0x0250 }
        r6.<init>(r13);	 //Catch:{ UnknownHostException -> 0x02fd, SocketException -> 0x02d3, SocketTimeoutException -> 0x02b2, ConnectTimeoutException -> 0x0291, Exception -> 0x0276, all -> 0x0250 }
        r2 = new org.apache.http.entity.ByteArrayEntity;	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r2.<init>(r14);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = "Content-Type";
        r4 = "application/x-www-form-urlencoded";
        r6.addHeader(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = "User-Agent";
        r4 = com.amap.api.location.core.d.b;	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r6.addHeader(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = "Accept-Encoding";
        r4 = "gzip";
        r6.addHeader(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = "Connection";
        r4 = "Keep-Alive";
        r6.addHeader(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = "X-INFO";
        r4 = 0;
        r4 = com.amap.api.location.core.c.a(r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r4 = r4.b();	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r6.addHeader(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = "ia";
        r4 = "1";
        r6.addHeader(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = "key";
        r4 = com.amap.api.location.core.c.a;	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r6.addHeader(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = 0;
        r4 = r8.length();	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r8.delete(r3, r4);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r6.setEntity(r2);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r3 = r7.execute(r6);	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r2 = r3.getStatusLine();	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r2 = r2.getStatusCode();	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r4) goto L_0x0137;
    L_0x007f:
        r2 = r3.getEntity();	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r5 = r2.getContent();	 //Catch:{ UnknownHostException -> 0x0305, SocketException -> 0x02db, SocketTimeoutException -> 0x02ba, ConnectTimeoutException -> 0x0299, Exception -> 0x027d, all -> 0x0259 }
        r2 = r3.getEntity();	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
        r2 = r2.getContentType();	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
        r4 = r2.getValue();	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
        r2 = "";
        r9 = "charset=";
        r9 = r4.indexOf(r9);	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
        if (r9 == r10) goto L_0x00a3;
    L_0x009d:
        r2 = r9 + 8;
        r2 = r4.substring(r2);	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
    L_0x00a3:
        r4 = android.text.TextUtils.isEmpty(r2);	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
        if (r4 == 0) goto L_0x00ab;
    L_0x00a9:
        r2 = "UTF-8";
    L_0x00ab:
        r3 = a(r3);	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
        if (r3 == 0) goto L_0x0316;
    L_0x00b1:
        r4 = new java.util.zip.GZIPInputStream;	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
        r4.<init>(r5);	 //Catch:{ UnknownHostException -> 0x030c, SocketException -> 0x02e2, SocketTimeoutException -> 0x02c1, ConnectTimeoutException -> 0x02a0, Exception -> 0x0283, all -> 0x0261 }
    L_0x00b6:
        if (r4 == 0) goto L_0x00fc;
    L_0x00b8:
        r3 = new java.io.InputStreamReader;	 //Catch:{ UnknownHostException -> 0x0102, SocketException -> 0x02e8, SocketTimeoutException -> 0x02c7, ConnectTimeoutException -> 0x02a6, Exception -> 0x0288, all -> 0x0268 }
        r3.<init>(r4, r2);	 //Catch:{ UnknownHostException -> 0x0102, SocketException -> 0x02e8, SocketTimeoutException -> 0x02c7, ConnectTimeoutException -> 0x02a6, Exception -> 0x0288, all -> 0x0268 }
    L_0x00bd:
        r2 = new java.io.BufferedReader;	 //Catch:{ UnknownHostException -> 0x0312, SocketException -> 0x02ed, SocketTimeoutException -> 0x02cc, ConnectTimeoutException -> 0x02ab, Exception -> 0x028c, all -> 0x026e }
        r9 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r2.<init>(r3, r9);	 //Catch:{ UnknownHostException -> 0x0312, SocketException -> 0x02ed, SocketTimeoutException -> 0x02cc, ConnectTimeoutException -> 0x02ab, Exception -> 0x028c, all -> 0x026e }
        r9 = "";
    L_0x00c6:
        r9 = r2.readLine();	 //Catch:{ UnknownHostException -> 0x00d0, SocketException -> 0x02f1, SocketTimeoutException -> 0x02d0, ConnectTimeoutException -> 0x02af, Exception -> 0x028f, all -> 0x0273 }
        if (r9 == 0) goto L_0x0106;
    L_0x00cc:
        r8.append(r9);	 //Catch:{ UnknownHostException -> 0x00d0, SocketException -> 0x02f1, SocketTimeoutException -> 0x02d0, ConnectTimeoutException -> 0x02af, Exception -> 0x028f, all -> 0x0273 }
        goto L_0x00c6;
    L_0x00d0:
        r8 = move-exception;
    L_0x00d1:
        if (r6 == 0) goto L_0x00d6;
    L_0x00d3:
        r6.abort();	 //Catch:{ all -> 0x016b }
    L_0x00d6:
        if (r7 == 0) goto L_0x00df;
    L_0x00d8:
        r6 = r7.getConnectionManager();	 //Catch:{ all -> 0x016b }
        r6.shutdown();	 //Catch:{ all -> 0x016b }
    L_0x00df:
        if (r4 == 0) goto L_0x00e4;
    L_0x00e1:
        r4.close();	 //Catch:{ Exception -> 0x016e }
    L_0x00e4:
        if (r5 == 0) goto L_0x00e9;
    L_0x00e6:
        r5.close();	 //Catch:{ Exception -> 0x0171 }
    L_0x00e9:
        if (r3 == 0) goto L_0x00ee;
    L_0x00eb:
        r3.close();	 //Catch:{ Exception -> 0x0174 }
    L_0x00ee:
        if (r2 == 0) goto L_0x00f3;
    L_0x00f0:
        r2.close();	 //Catch:{ Exception -> 0x0135 }
    L_0x00f3:
        r2 = android.text.TextUtils.isEmpty(r1);	 //Catch:{ all -> 0x016b }
        if (r2 != 0) goto L_0x000b;
    L_0x00f9:
        r0 = r1;
        goto L_0x000b;
    L_0x00fc:
        r3 = new java.io.InputStreamReader;	 //Catch:{ UnknownHostException -> 0x0102, SocketException -> 0x02e8, SocketTimeoutException -> 0x02c7, ConnectTimeoutException -> 0x02a6, Exception -> 0x0288, all -> 0x0268 }
        r3.<init>(r5, r2);	 //Catch:{ UnknownHostException -> 0x0102, SocketException -> 0x02e8, SocketTimeoutException -> 0x02c7, ConnectTimeoutException -> 0x02a6, Exception -> 0x0288, all -> 0x0268 }
        goto L_0x00bd;
    L_0x0102:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x00d1;
    L_0x0106:
        r1 = r8.toString();	 //Catch:{ UnknownHostException -> 0x00d0, SocketException -> 0x02f1, SocketTimeoutException -> 0x02d0, ConnectTimeoutException -> 0x02af, Exception -> 0x028f, all -> 0x0273 }
        r9 = 0;
        r10 = r8.length();	 //Catch:{ UnknownHostException -> 0x00d0, SocketException -> 0x02f1, SocketTimeoutException -> 0x02d0, ConnectTimeoutException -> 0x02af, Exception -> 0x028f, all -> 0x0273 }
        r8.delete(r9, r10);	 //Catch:{ UnknownHostException -> 0x00d0, SocketException -> 0x02f1, SocketTimeoutException -> 0x02d0, ConnectTimeoutException -> 0x02af, Exception -> 0x028f, all -> 0x0273 }
    L_0x0112:
        if (r6 == 0) goto L_0x0117;
    L_0x0114:
        r6.abort();	 //Catch:{ all -> 0x016b }
    L_0x0117:
        if (r7 == 0) goto L_0x0120;
    L_0x0119:
        r6 = r7.getConnectionManager();	 //Catch:{ all -> 0x016b }
        r6.shutdown();	 //Catch:{ all -> 0x016b }
    L_0x0120:
        if (r4 == 0) goto L_0x0125;
    L_0x0122:
        r4.close();	 //Catch:{ Exception -> 0x0247 }
    L_0x0125:
        if (r5 == 0) goto L_0x012a;
    L_0x0127:
        r5.close();	 //Catch:{ Exception -> 0x024a }
    L_0x012a:
        if (r3 == 0) goto L_0x012f;
    L_0x012c:
        r3.close();	 //Catch:{ Exception -> 0x024d }
    L_0x012f:
        if (r2 == 0) goto L_0x00f3;
    L_0x0131:
        r2.close();	 //Catch:{ Exception -> 0x0135 }
        goto L_0x00f3;
    L_0x0135:
        r2 = move-exception;
        goto L_0x00f3;
    L_0x0137:
        r3 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r2 != r3) goto L_0x013b;
    L_0x013b:
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        goto L_0x0112;
    L_0x0140:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
        r0 = r1;
    L_0x0148:
        if (r6 == 0) goto L_0x014d;
    L_0x014a:
        r6.abort();	 //Catch:{ all -> 0x016b }
    L_0x014d:
        if (r7 == 0) goto L_0x0156;
    L_0x014f:
        r1 = r7.getConnectionManager();	 //Catch:{ all -> 0x016b }
        r1.shutdown();	 //Catch:{ all -> 0x016b }
    L_0x0156:
        if (r4 == 0) goto L_0x015b;
    L_0x0158:
        r4.close();	 //Catch:{ Exception -> 0x023b }
    L_0x015b:
        if (r5 == 0) goto L_0x0160;
    L_0x015d:
        r5.close();	 //Catch:{ Exception -> 0x023e }
    L_0x0160:
        if (r3 == 0) goto L_0x0165;
    L_0x0162:
        r3.close();	 //Catch:{ Exception -> 0x0241 }
    L_0x0165:
        if (r2 == 0) goto L_0x016a;
    L_0x0167:
        r2.close();	 //Catch:{ Exception -> 0x0244 }
    L_0x016a:
        throw r0;	 //Catch:{ all -> 0x016b }
    L_0x016b:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x016e:
        r4 = move-exception;
        goto L_0x00e4;
    L_0x0171:
        r4 = move-exception;
        goto L_0x00e9;
    L_0x0174:
        r3 = move-exception;
        goto L_0x00ee;
    L_0x0177:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
    L_0x017e:
        if (r6 == 0) goto L_0x0183;
    L_0x0180:
        r6.abort();	 //Catch:{ all -> 0x016b }
    L_0x0183:
        if (r7 == 0) goto L_0x018c;
    L_0x0185:
        r6 = r7.getConnectionManager();	 //Catch:{ all -> 0x016b }
        r6.shutdown();	 //Catch:{ all -> 0x016b }
    L_0x018c:
        if (r4 == 0) goto L_0x0191;
    L_0x018e:
        r4.close();	 //Catch:{ Exception -> 0x01a2 }
    L_0x0191:
        if (r5 == 0) goto L_0x0196;
    L_0x0193:
        r5.close();	 //Catch:{ Exception -> 0x01a4 }
    L_0x0196:
        if (r3 == 0) goto L_0x019b;
    L_0x0198:
        r3.close();	 //Catch:{ Exception -> 0x01a6 }
    L_0x019b:
        if (r2 == 0) goto L_0x00f3;
    L_0x019d:
        r2.close();	 //Catch:{ Exception -> 0x0135 }
        goto L_0x00f3;
    L_0x01a2:
        r4 = move-exception;
        goto L_0x0191;
    L_0x01a4:
        r4 = move-exception;
        goto L_0x0196;
    L_0x01a6:
        r3 = move-exception;
        goto L_0x019b;
    L_0x01a8:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
    L_0x01af:
        if (r6 == 0) goto L_0x01b4;
    L_0x01b1:
        r6.abort();	 //Catch:{ all -> 0x016b }
    L_0x01b4:
        if (r7 == 0) goto L_0x01bd;
    L_0x01b6:
        r6 = r7.getConnectionManager();	 //Catch:{ all -> 0x016b }
        r6.shutdown();	 //Catch:{ all -> 0x016b }
    L_0x01bd:
        if (r4 == 0) goto L_0x01c2;
    L_0x01bf:
        r4.close();	 //Catch:{ Exception -> 0x01d3 }
    L_0x01c2:
        if (r5 == 0) goto L_0x01c7;
    L_0x01c4:
        r5.close();	 //Catch:{ Exception -> 0x01d5 }
    L_0x01c7:
        if (r3 == 0) goto L_0x01cc;
    L_0x01c9:
        r3.close();	 //Catch:{ Exception -> 0x01d7 }
    L_0x01cc:
        if (r2 == 0) goto L_0x00f3;
    L_0x01ce:
        r2.close();	 //Catch:{ Exception -> 0x0135 }
        goto L_0x00f3;
    L_0x01d3:
        r4 = move-exception;
        goto L_0x01c2;
    L_0x01d5:
        r4 = move-exception;
        goto L_0x01c7;
    L_0x01d7:
        r3 = move-exception;
        goto L_0x01cc;
    L_0x01d9:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
    L_0x01e0:
        if (r6 == 0) goto L_0x01e5;
    L_0x01e2:
        r6.abort();	 //Catch:{ all -> 0x016b }
    L_0x01e5:
        if (r7 == 0) goto L_0x01ee;
    L_0x01e7:
        r6 = r7.getConnectionManager();	 //Catch:{ all -> 0x016b }
        r6.shutdown();	 //Catch:{ all -> 0x016b }
    L_0x01ee:
        if (r4 == 0) goto L_0x01f3;
    L_0x01f0:
        r4.close();	 //Catch:{ Exception -> 0x0204 }
    L_0x01f3:
        if (r5 == 0) goto L_0x01f8;
    L_0x01f5:
        r5.close();	 //Catch:{ Exception -> 0x0206 }
    L_0x01f8:
        if (r3 == 0) goto L_0x01fd;
    L_0x01fa:
        r3.close();	 //Catch:{ Exception -> 0x0208 }
    L_0x01fd:
        if (r2 == 0) goto L_0x00f3;
    L_0x01ff:
        r2.close();	 //Catch:{ Exception -> 0x0135 }
        goto L_0x00f3;
    L_0x0204:
        r4 = move-exception;
        goto L_0x01f3;
    L_0x0206:
        r4 = move-exception;
        goto L_0x01f8;
    L_0x0208:
        r3 = move-exception;
        goto L_0x01fd;
    L_0x020a:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
    L_0x0211:
        if (r6 == 0) goto L_0x0216;
    L_0x0213:
        r6.abort();	 //Catch:{ all -> 0x016b }
    L_0x0216:
        if (r7 == 0) goto L_0x021f;
    L_0x0218:
        r6 = r7.getConnectionManager();	 //Catch:{ all -> 0x016b }
        r6.shutdown();	 //Catch:{ all -> 0x016b }
    L_0x021f:
        if (r4 == 0) goto L_0x0224;
    L_0x0221:
        r4.close();	 //Catch:{ Exception -> 0x0235 }
    L_0x0224:
        if (r5 == 0) goto L_0x0229;
    L_0x0226:
        r5.close();	 //Catch:{ Exception -> 0x0237 }
    L_0x0229:
        if (r3 == 0) goto L_0x022e;
    L_0x022b:
        r3.close();	 //Catch:{ Exception -> 0x0239 }
    L_0x022e:
        if (r2 == 0) goto L_0x00f3;
    L_0x0230:
        r2.close();	 //Catch:{ Exception -> 0x0135 }
        goto L_0x00f3;
    L_0x0235:
        r4 = move-exception;
        goto L_0x0224;
    L_0x0237:
        r4 = move-exception;
        goto L_0x0229;
    L_0x0239:
        r3 = move-exception;
        goto L_0x022e;
    L_0x023b:
        r1 = move-exception;
        goto L_0x015b;
    L_0x023e:
        r1 = move-exception;
        goto L_0x0160;
    L_0x0241:
        r1 = move-exception;
        goto L_0x0165;
    L_0x0244:
        r1 = move-exception;
        goto L_0x016a;
    L_0x0247:
        r4 = move-exception;
        goto L_0x0125;
    L_0x024a:
        r4 = move-exception;
        goto L_0x012a;
    L_0x024d:
        r3 = move-exception;
        goto L_0x012f;
    L_0x0250:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r0 = r1;
        goto L_0x0148;
    L_0x0259:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r0 = r1;
        goto L_0x0148;
    L_0x0261:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r0 = r1;
        goto L_0x0148;
    L_0x0268:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
        goto L_0x0148;
    L_0x026e:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0148;
    L_0x0273:
        r0 = move-exception;
        goto L_0x0148;
    L_0x0276:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x0211;
    L_0x027d:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        goto L_0x0211;
    L_0x0283:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        goto L_0x0211;
    L_0x0288:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x0211;
    L_0x028c:
        r2 = move-exception;
        r2 = r0;
        goto L_0x0211;
    L_0x028f:
        r8 = move-exception;
        goto L_0x0211;
    L_0x0291:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x01e0;
    L_0x0299:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        goto L_0x01e0;
    L_0x02a0:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        goto L_0x01e0;
    L_0x02a6:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x01e0;
    L_0x02ab:
        r2 = move-exception;
        r2 = r0;
        goto L_0x01e0;
    L_0x02af:
        r8 = move-exception;
        goto L_0x01e0;
    L_0x02b2:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x01af;
    L_0x02ba:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        goto L_0x01af;
    L_0x02c1:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        goto L_0x01af;
    L_0x02c7:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x01af;
    L_0x02cc:
        r2 = move-exception;
        r2 = r0;
        goto L_0x01af;
    L_0x02d0:
        r8 = move-exception;
        goto L_0x01af;
    L_0x02d3:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x017e;
    L_0x02db:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        goto L_0x017e;
    L_0x02e2:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        goto L_0x017e;
    L_0x02e8:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x017e;
    L_0x02ed:
        r2 = move-exception;
        r2 = r0;
        goto L_0x017e;
    L_0x02f1:
        r8 = move-exception;
        goto L_0x017e;
    L_0x02f4:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        r7 = r0;
        goto L_0x00d1;
    L_0x02fd:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        r6 = r0;
        goto L_0x00d1;
    L_0x0305:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r5 = r0;
        goto L_0x00d1;
    L_0x030c:
        r2 = move-exception;
        r2 = r0;
        r3 = r0;
        r4 = r0;
        goto L_0x00d1;
    L_0x0312:
        r2 = move-exception;
        r2 = r0;
        goto L_0x00d1;
    L_0x0316:
        r4 = r0;
        goto L_0x00b6;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized String a(byte[] r17_byteA, Context r18_Context) throws Exception {
        /*
        r16_this = this;
        monitor-enter(r16);
        r9 = "";
        r12 = com.aps.o.b(r18);	 //Catch:{ all -> 0x0133 }
        r1 = a(r12);	 //Catch:{ all -> 0x0133 }
        r2 = -1;
        if (r1 != r2) goto L_0x0011;
    L_0x000e:
        r1 = 0;
    L_0x000f:
        monitor-exit(r16);
        return r1;
    L_0x0011:
        r8 = 0;
        r7 = 0;
        r6 = 0;
        r4 = 0;
        r5 = 0;
        r3 = new java.lang.StringBuffer;	 //Catch:{ all -> 0x0133 }
        r3.<init>();	 //Catch:{ all -> 0x0133 }
        r13 = new java.lang.StringBuffer;	 //Catch:{ all -> 0x0133 }
        r13.<init>();	 //Catch:{ all -> 0x0133 }
        r1 = "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&";
        r13.append(r1);	 //Catch:{ all -> 0x0133 }
        r1 = "zei=";
        r1 = r13.append(r1);	 //Catch:{ all -> 0x0133 }
        r2 = com.aps.f.a;	 //Catch:{ all -> 0x0133 }
        r1.append(r2);	 //Catch:{ all -> 0x0133 }
        r1 = "&zsi=";
        r1 = r13.append(r1);	 //Catch:{ all -> 0x0133 }
        r2 = com.aps.f.b;	 //Catch:{ all -> 0x0133 }
        r1.append(r2);	 //Catch:{ all -> 0x0133 }
        r2 = 0;
        r1 = 0;
        r11 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r9;
    L_0x0041:
        r9 = 2;
        if (r11 >= r9) goto L_0x0046;
    L_0x0044:
        if (r1 == 0) goto L_0x0058;
    L_0x0046:
        r1 = 0;
        r2 = r13.length();	 //Catch:{ all -> 0x0133 }
        r13.delete(r1, r2);	 //Catch:{ all -> 0x0133 }
        r1 = "";
        r1 = r3.equals(r1);	 //Catch:{ all -> 0x0133 }
        if (r1 == 0) goto L_0x0158;
    L_0x0056:
        r1 = 0;
        goto L_0x000f;
    L_0x0058:
        r0 = r18;
        r10 = a(r0, r12);	 //Catch:{ UnknownHostException -> 0x0249, SocketException -> 0x0227, SocketTimeoutException -> 0x0205, ConnectTimeoutException -> 0x01e8, all -> 0x01d3 }
        r9 = new org.apache.http.client.methods.HttpPost;	 //Catch:{ UnknownHostException -> 0x0252, SocketException -> 0x022f, SocketTimeoutException -> 0x020d, ConnectTimeoutException -> 0x01ef, all -> 0x01d8 }
        r8 = r13.toString();	 //Catch:{ UnknownHostException -> 0x0252, SocketException -> 0x022f, SocketTimeoutException -> 0x020d, ConnectTimeoutException -> 0x01ef, all -> 0x01d8 }
        r9.<init>(r8);	 //Catch:{ UnknownHostException -> 0x0252, SocketException -> 0x022f, SocketTimeoutException -> 0x020d, ConnectTimeoutException -> 0x01ef, all -> 0x01d8 }
        r7 = 0;
        r8 = r2.length();	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r2.delete(r7, r8);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = "application/soap+xml;charset=";
        r2.append(r7);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = "UTF-8";
        r2.append(r7);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = 0;
        r8 = r2.length();	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r2.delete(r7, r8);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = "gzipped";
        r8 = "1";
        r9.addHeader(r7, r8);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = com.aps.o.a(r17);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r8 = new org.apache.http.entity.ByteArrayEntity;	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r8.<init>(r7);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = "application/octet-stream";
        r8.setContentType(r7);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r9.setEntity(r8);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = r10.execute(r9);	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r8 = r7.getStatusLine();	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r8 = r8.getStatusCode();	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r14 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r8 != r14) goto L_0x028b;
    L_0x00a9:
        r7 = r7.getEntity();	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r7 = r7.getContent();	 //Catch:{ UnknownHostException -> 0x025a, SocketException -> 0x0236, SocketTimeoutException -> 0x0214, ConnectTimeoutException -> 0x01f5, all -> 0x01dc }
        r6 = new java.io.InputStreamReader;	 //Catch:{ UnknownHostException -> 0x0262, SocketException -> 0x023c, SocketTimeoutException -> 0x021a, ConnectTimeoutException -> 0x01fa, all -> 0x01df }
        r8 = "UTF-8";
        r6.<init>(r7, r8);	 //Catch:{ UnknownHostException -> 0x0262, SocketException -> 0x023c, SocketTimeoutException -> 0x021a, ConnectTimeoutException -> 0x01fa, all -> 0x01df }
        r4 = new java.io.BufferedReader;	 //Catch:{ UnknownHostException -> 0x026a, SocketException -> 0x0242, SocketTimeoutException -> 0x0220, ConnectTimeoutException -> 0x01ff, all -> 0x01e3 }
        r8 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r4.<init>(r6, r8);	 //Catch:{ UnknownHostException -> 0x026a, SocketException -> 0x0242, SocketTimeoutException -> 0x0220, ConnectTimeoutException -> 0x01ff, all -> 0x01e3 }
        r5 = "";
    L_0x00c1:
        r5 = r4.readLine();	 //Catch:{ UnknownHostException -> 0x00cb, SocketException -> 0x015b, SocketTimeoutException -> 0x0183, ConnectTimeoutException -> 0x01ab, all -> 0x0136 }
        if (r5 == 0) goto L_0x00ff;
    L_0x00c7:
        r2.append(r5);	 //Catch:{ UnknownHostException -> 0x00cb, SocketException -> 0x015b, SocketTimeoutException -> 0x0183, ConnectTimeoutException -> 0x01ab, all -> 0x0136 }
        goto L_0x00c1;
    L_0x00cb:
        r5 = move-exception;
        r5 = r7;
        r8 = r3;
        r3 = r4;
        r7 = r10;
        r4 = r6;
        r6 = r9;
    L_0x00d2:
        if (r6 == 0) goto L_0x00d8;
    L_0x00d4:
        r6.abort();	 //Catch:{ all -> 0x0133 }
        r6 = 0;
    L_0x00d8:
        if (r7 == 0) goto L_0x00e2;
    L_0x00da:
        r7 = r7.getConnectionManager();	 //Catch:{ all -> 0x0133 }
        r7.shutdown();	 //Catch:{ all -> 0x0133 }
        r7 = 0;
    L_0x00e2:
        if (r5 == 0) goto L_0x00e8;
    L_0x00e4:
        r5.close();	 //Catch:{ all -> 0x0133 }
        r5 = 0;
    L_0x00e8:
        if (r4 == 0) goto L_0x00ee;
    L_0x00ea:
        r4.close();	 //Catch:{ all -> 0x0133 }
        r4 = 0;
    L_0x00ee:
        if (r3 == 0) goto L_0x00f4;
    L_0x00f0:
        r3.close();	 //Catch:{ all -> 0x0133 }
    L_0x00f3:
        r3 = 0;
    L_0x00f4:
        r9 = r11 + 1;
        r11 = r9;
        r15 = r3;
        r3 = r8;
        r8 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r15;
        goto L_0x0041;
    L_0x00ff:
        r3 = r2.toString();	 //Catch:{ UnknownHostException -> 0x00cb, SocketException -> 0x015b, SocketTimeoutException -> 0x0183, ConnectTimeoutException -> 0x01ab, all -> 0x0136 }
        r5 = 0;
        r8 = r2.length();	 //Catch:{ UnknownHostException -> 0x00cb, SocketException -> 0x015b, SocketTimeoutException -> 0x0183, ConnectTimeoutException -> 0x01ab, all -> 0x0136 }
        r2.delete(r5, r8);	 //Catch:{ UnknownHostException -> 0x00cb, SocketException -> 0x015b, SocketTimeoutException -> 0x0183, ConnectTimeoutException -> 0x01ab, all -> 0x0136 }
        r2 = 0;
        r1 = 1;
        r5 = r7;
        r8 = r3;
        r3 = r4;
        r4 = r6;
    L_0x0111:
        if (r9 == 0) goto L_0x0276;
    L_0x0113:
        r9.abort();	 //Catch:{ all -> 0x0133 }
        r6 = 0;
    L_0x0117:
        if (r10 == 0) goto L_0x0273;
    L_0x0119:
        r7 = r10.getConnectionManager();	 //Catch:{ all -> 0x0133 }
        r7.shutdown();	 //Catch:{ all -> 0x0133 }
        r7 = 0;
    L_0x0121:
        if (r5 == 0) goto L_0x0127;
    L_0x0123:
        r5.close();	 //Catch:{ all -> 0x0133 }
        r5 = 0;
    L_0x0127:
        if (r4 == 0) goto L_0x012d;
    L_0x0129:
        r4.close();	 //Catch:{ all -> 0x0133 }
        r4 = 0;
    L_0x012d:
        if (r3 == 0) goto L_0x00f4;
    L_0x012f:
        r3.close();	 //Catch:{ all -> 0x0133 }
        goto L_0x00f3;
    L_0x0133:
        r1 = move-exception;
        monitor-exit(r16);
        throw r1;
    L_0x0136:
        r1 = move-exception;
        r5 = r4;
        r4 = r6;
        r6 = r7;
    L_0x013a:
        if (r9 == 0) goto L_0x013f;
    L_0x013c:
        r9.abort();	 //Catch:{ all -> 0x0133 }
    L_0x013f:
        if (r10 == 0) goto L_0x0148;
    L_0x0141:
        r2 = r10.getConnectionManager();	 //Catch:{ all -> 0x0133 }
        r2.shutdown();	 //Catch:{ all -> 0x0133 }
    L_0x0148:
        if (r6 == 0) goto L_0x014d;
    L_0x014a:
        r6.close();	 //Catch:{ all -> 0x0133 }
    L_0x014d:
        if (r4 == 0) goto L_0x0152;
    L_0x014f:
        r4.close();	 //Catch:{ all -> 0x0133 }
    L_0x0152:
        if (r5 == 0) goto L_0x0157;
    L_0x0154:
        r5.close();	 //Catch:{ all -> 0x0133 }
    L_0x0157:
        throw r1;	 //Catch:{ all -> 0x0133 }
    L_0x0158:
        r1 = r3;
        goto L_0x000f;
    L_0x015b:
        r5 = move-exception;
        r5 = r7;
        r8 = r3;
        r3 = r4;
        r4 = r6;
    L_0x0160:
        if (r9 == 0) goto L_0x0288;
    L_0x0162:
        r9.abort();	 //Catch:{ all -> 0x0133 }
        r6 = 0;
    L_0x0166:
        if (r10 == 0) goto L_0x0285;
    L_0x0168:
        r7 = r10.getConnectionManager();	 //Catch:{ all -> 0x0133 }
        r7.shutdown();	 //Catch:{ all -> 0x0133 }
        r7 = 0;
    L_0x0170:
        if (r5 == 0) goto L_0x0176;
    L_0x0172:
        r5.close();	 //Catch:{ all -> 0x0133 }
        r5 = 0;
    L_0x0176:
        if (r4 == 0) goto L_0x017c;
    L_0x0178:
        r4.close();	 //Catch:{ all -> 0x0133 }
        r4 = 0;
    L_0x017c:
        if (r3 == 0) goto L_0x00f4;
    L_0x017e:
        r3.close();	 //Catch:{ all -> 0x0133 }
        goto L_0x00f3;
    L_0x0183:
        r5 = move-exception;
        r5 = r7;
        r8 = r3;
        r3 = r4;
        r4 = r6;
    L_0x0188:
        if (r9 == 0) goto L_0x0282;
    L_0x018a:
        r9.abort();	 //Catch:{ all -> 0x0133 }
        r6 = 0;
    L_0x018e:
        if (r10 == 0) goto L_0x027f;
    L_0x0190:
        r7 = r10.getConnectionManager();	 //Catch:{ all -> 0x0133 }
        r7.shutdown();	 //Catch:{ all -> 0x0133 }
        r7 = 0;
    L_0x0198:
        if (r5 == 0) goto L_0x019e;
    L_0x019a:
        r5.close();	 //Catch:{ all -> 0x0133 }
        r5 = 0;
    L_0x019e:
        if (r4 == 0) goto L_0x01a4;
    L_0x01a0:
        r4.close();	 //Catch:{ all -> 0x0133 }
        r4 = 0;
    L_0x01a4:
        if (r3 == 0) goto L_0x00f4;
    L_0x01a6:
        r3.close();	 //Catch:{ all -> 0x0133 }
        goto L_0x00f3;
    L_0x01ab:
        r5 = move-exception;
        r5 = r7;
        r8 = r3;
        r3 = r4;
        r4 = r6;
    L_0x01b0:
        if (r9 == 0) goto L_0x027c;
    L_0x01b2:
        r9.abort();	 //Catch:{ all -> 0x0133 }
        r6 = 0;
    L_0x01b6:
        if (r10 == 0) goto L_0x0279;
    L_0x01b8:
        r7 = r10.getConnectionManager();	 //Catch:{ all -> 0x0133 }
        r7.shutdown();	 //Catch:{ all -> 0x0133 }
        r7 = 0;
    L_0x01c0:
        if (r5 == 0) goto L_0x01c6;
    L_0x01c2:
        r5.close();	 //Catch:{ all -> 0x0133 }
        r5 = 0;
    L_0x01c6:
        if (r4 == 0) goto L_0x01cc;
    L_0x01c8:
        r4.close();	 //Catch:{ all -> 0x0133 }
        r4 = 0;
    L_0x01cc:
        if (r3 == 0) goto L_0x00f4;
    L_0x01ce:
        r3.close();	 //Catch:{ all -> 0x0133 }
        goto L_0x00f3;
    L_0x01d3:
        r1 = move-exception;
        r9 = r7;
        r10 = r8;
        goto L_0x013a;
    L_0x01d8:
        r1 = move-exception;
        r9 = r7;
        goto L_0x013a;
    L_0x01dc:
        r1 = move-exception;
        goto L_0x013a;
    L_0x01df:
        r1 = move-exception;
        r6 = r7;
        goto L_0x013a;
    L_0x01e3:
        r1 = move-exception;
        r4 = r6;
        r6 = r7;
        goto L_0x013a;
    L_0x01e8:
        r9 = move-exception;
        r9 = r7;
        r10 = r8;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x01b0;
    L_0x01ef:
        r8 = move-exception;
        r9 = r7;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x01b0;
    L_0x01f5:
        r7 = move-exception;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x01b0;
    L_0x01fa:
        r6 = move-exception;
        r8 = r3;
        r3 = r5;
        r5 = r7;
        goto L_0x01b0;
    L_0x01ff:
        r4 = move-exception;
        r4 = r6;
        r8 = r3;
        r3 = r5;
        r5 = r7;
        goto L_0x01b0;
    L_0x0205:
        r9 = move-exception;
        r9 = r7;
        r10 = r8;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x0188;
    L_0x020d:
        r8 = move-exception;
        r9 = r7;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x0188;
    L_0x0214:
        r7 = move-exception;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x0188;
    L_0x021a:
        r6 = move-exception;
        r8 = r3;
        r3 = r5;
        r5 = r7;
        goto L_0x0188;
    L_0x0220:
        r4 = move-exception;
        r4 = r6;
        r8 = r3;
        r3 = r5;
        r5 = r7;
        goto L_0x0188;
    L_0x0227:
        r9 = move-exception;
        r9 = r7;
        r10 = r8;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x0160;
    L_0x022f:
        r8 = move-exception;
        r9 = r7;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x0160;
    L_0x0236:
        r7 = move-exception;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x0160;
    L_0x023c:
        r6 = move-exception;
        r8 = r3;
        r3 = r5;
        r5 = r7;
        goto L_0x0160;
    L_0x0242:
        r4 = move-exception;
        r4 = r6;
        r8 = r3;
        r3 = r5;
        r5 = r7;
        goto L_0x0160;
    L_0x0249:
        r9 = move-exception;
        r15 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r3;
        r3 = r15;
        goto L_0x00d2;
    L_0x0252:
        r8 = move-exception;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r10;
        goto L_0x00d2;
    L_0x025a:
        r7 = move-exception;
        r7 = r10;
        r8 = r3;
        r3 = r5;
        r5 = r6;
        r6 = r9;
        goto L_0x00d2;
    L_0x0262:
        r6 = move-exception;
        r6 = r9;
        r8 = r3;
        r3 = r5;
        r5 = r7;
        r7 = r10;
        goto L_0x00d2;
    L_0x026a:
        r4 = move-exception;
        r4 = r6;
        r8 = r3;
        r6 = r9;
        r3 = r5;
        r5 = r7;
        r7 = r10;
        goto L_0x00d2;
    L_0x0273:
        r7 = r10;
        goto L_0x0121;
    L_0x0276:
        r6 = r9;
        goto L_0x0117;
    L_0x0279:
        r7 = r10;
        goto L_0x01c0;
    L_0x027c:
        r6 = r9;
        goto L_0x01b6;
    L_0x027f:
        r7 = r10;
        goto L_0x0198;
    L_0x0282:
        r6 = r9;
        goto L_0x018e;
    L_0x0285:
        r7 = r10;
        goto L_0x0170;
    L_0x0288:
        r6 = r9;
        goto L_0x0166;
    L_0x028b:
        r8 = r3;
        r3 = r5;
        r5 = r6;
        goto L_0x0111;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized String a(byte[] r20_byteA, Context r21_Context, JSONObject r22_JSONObject) throws Exception {
        /*
        r19_this = this;
        monitor-enter(r19);
        r15 = com.aps.o.b(r21);	 //Catch:{ all -> 0x01b0 }
        r2 = a(r15);	 //Catch:{ all -> 0x01b0 }
        r3 = -1;
        if (r2 != r3) goto L_0x000f;
    L_0x000c:
        r2 = 0;
    L_0x000d:
        monitor-exit(r19);
        return r2;
    L_0x000f:
        r11 = 0;
        r10 = 0;
        r9 = 0;
        r8 = 0;
        r7 = 0;
        r6 = 0;
        r5 = new java.lang.StringBuffer;	 //Catch:{ all -> 0x01b0 }
        r5.<init>();	 //Catch:{ all -> 0x01b0 }
        r4 = "";
        r3 = 0;
        r2 = 0;
        r14 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r11;
    L_0x0028:
        r11 = 2;
        if (r14 >= r11) goto L_0x002d;
    L_0x002b:
        if (r2 == 0) goto L_0x0035;
    L_0x002d:
        r2 = android.text.TextUtils.isEmpty(r3);	 //Catch:{ all -> 0x01b0 }
        if (r2 == 0) goto L_0x01e2;
    L_0x0033:
        r2 = 0;
        goto L_0x000d;
    L_0x0035:
        r0 = r21;
        r11 = a(r0, r15);	 //Catch:{ UnknownHostException -> 0x02f3, SocketException -> 0x02d8, SocketTimeoutException -> 0x02bd, ConnectTimeoutException -> 0x02a8, all -> 0x028d }
        r16 = a(r22);	 //Catch:{ UnknownHostException -> 0x0164, SocketException -> 0x02dd, SocketTimeoutException -> 0x02c2, ConnectTimeoutException -> 0x02ac, all -> 0x0292 }
        r10 = 0;
        r10 = r16[r10];	 //Catch:{ UnknownHostException -> 0x0164, SocketException -> 0x02dd, SocketTimeoutException -> 0x02c2, ConnectTimeoutException -> 0x02ac, all -> 0x0292 }
        r12 = "true";
        r10 = r10.equals(r12);	 //Catch:{ UnknownHostException -> 0x0164, SocketException -> 0x02dd, SocketTimeoutException -> 0x02c2, ConnectTimeoutException -> 0x02ac, all -> 0x0292 }
        if (r10 == 0) goto L_0x0157;
    L_0x004a:
        r10 = 1;
        r13 = r10;
    L_0x004c:
        if (r13 == 0) goto L_0x015b;
    L_0x004e:
        r10 = new org.apache.http.client.methods.HttpPost;	 //Catch:{ UnknownHostException -> 0x0164, SocketException -> 0x02dd, SocketTimeoutException -> 0x02c2, ConnectTimeoutException -> 0x02ac, all -> 0x0292 }
        r12 = "http://apilocate.amap.com/mobile/binary";
        r10.<init>(r12);	 //Catch:{ UnknownHostException -> 0x0164, SocketException -> 0x02dd, SocketTimeoutException -> 0x02c2, ConnectTimeoutException -> 0x02ac, all -> 0x0292 }
    L_0x0055:
        if (r13 == 0) goto L_0x0167;
    L_0x0057:
        r12 = "UTF-8";
    L_0x0059:
        r9 = com.aps.o.a(r20);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r17 = new org.apache.http.entity.ByteArrayEntity;	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r0 = r17;
        r0.<init>(r9);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = "application/octet-stream";
        r0 = r17;
        r0.setContentType(r9);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = "Accept-Encoding";
        r18 = "gzip";
        r0 = r18;
        r10.addHeader(r9, r0);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = "gzipped";
        r18 = "1";
        r0 = r18;
        r10.addHeader(r9, r0);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        if (r13 == 0) goto L_0x00ad;
    L_0x007f:
        r9 = "X-INFO";
        r13 = 2;
        r13 = r16[r13];	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r10.addHeader(r9, r13);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = "X-BIZ";
        r13 = 3;
        r13 = r16[r13];	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r10.addHeader(r9, r13);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = "KEY";
        r13 = 1;
        r13 = r16[r13];	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r10.addHeader(r9, r13);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = 4;
        r9 = r16[r9];	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        if (r9 == 0) goto L_0x00ad;
    L_0x009c:
        r9 = 4;
        r9 = r16[r9];	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = r9.length();	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        if (r9 <= 0) goto L_0x00ad;
    L_0x00a5:
        r9 = "User-Agent";
        r13 = 4;
        r13 = r16[r13];	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r10.addHeader(r9, r13);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
    L_0x00ad:
        r9 = 0;
        r13 = r4.length();	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r4.delete(r9, r13);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r0 = r17;
        r10.setEntity(r0);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r13 = r11.execute(r10);	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = r13.getStatusLine();	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = r9.getStatusCode();	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r16 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0 = r16;
        if (r9 != r0) goto L_0x01b3;
    L_0x00cc:
        r9 = r13.getEntity();	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r9 = r9.getContent();	 //Catch:{ UnknownHostException -> 0x02f6, SocketException -> 0x02e1, SocketTimeoutException -> 0x02c6, ConnectTimeoutException -> 0x02af, all -> 0x0296 }
        r8 = r13.getEntity();	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
        r8 = r8.getContentType();	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
        r16 = r8.getValue();	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
        r8 = "";
        r17 = "charset=";
        r17 = r16.indexOf(r17);	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
        r18 = -1;
        r0 = r17;
        r1 = r18;
        if (r0 == r1) goto L_0x00f8;
    L_0x00f0:
        r8 = r17 + 8;
        r0 = r16;
        r8 = r0.substring(r8);	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
    L_0x00f8:
        r16 = android.text.TextUtils.isEmpty(r8);	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
        if (r16 == 0) goto L_0x032a;
    L_0x00fe:
        r8 = a(r13);	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
        if (r8 == 0) goto L_0x0327;
    L_0x0104:
        r8 = new java.util.zip.GZIPInputStream;	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
        r8.<init>(r9);	 //Catch:{ UnknownHostException -> 0x02fb, SocketException -> 0x02e4, SocketTimeoutException -> 0x02c9, ConnectTimeoutException -> 0x02b1, all -> 0x0299 }
    L_0x0109:
        if (r8 == 0) goto L_0x016b;
    L_0x010b:
        r7 = new java.io.InputStreamReader;	 //Catch:{ UnknownHostException -> 0x0171, SocketException -> 0x02e8, SocketTimeoutException -> 0x02cd, ConnectTimeoutException -> 0x02b4, all -> 0x029d }
        r7.<init>(r8, r12);	 //Catch:{ UnknownHostException -> 0x0171, SocketException -> 0x02e8, SocketTimeoutException -> 0x02cd, ConnectTimeoutException -> 0x02b4, all -> 0x029d }
    L_0x0110:
        r6 = new java.io.BufferedReader;	 //Catch:{ UnknownHostException -> 0x0301, SocketException -> 0x02ed, SocketTimeoutException -> 0x02d2, ConnectTimeoutException -> 0x02b8, all -> 0x02a2 }
        r12 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r6.<init>(r7, r12);	 //Catch:{ UnknownHostException -> 0x0301, SocketException -> 0x02ed, SocketTimeoutException -> 0x02d2, ConnectTimeoutException -> 0x02b8, all -> 0x02a2 }
        r5 = "";
    L_0x0119:
        r5 = r6.readLine();	 //Catch:{ UnknownHostException -> 0x0123, SocketException -> 0x01eb, SocketTimeoutException -> 0x021d, ConnectTimeoutException -> 0x024f, all -> 0x01ba }
        if (r5 == 0) goto L_0x0177;
    L_0x011f:
        r4.append(r5);	 //Catch:{ UnknownHostException -> 0x0123, SocketException -> 0x01eb, SocketTimeoutException -> 0x021d, ConnectTimeoutException -> 0x024f, all -> 0x01ba }
        goto L_0x0119;
    L_0x0123:
        r5 = move-exception;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r11;
    L_0x012a:
        if (r9 == 0) goto L_0x0130;
    L_0x012c:
        r9.abort();	 //Catch:{ all -> 0x01b0 }
        r9 = 0;
    L_0x0130:
        if (r10 == 0) goto L_0x013a;
    L_0x0132:
        r10 = r10.getConnectionManager();	 //Catch:{ all -> 0x01b0 }
        r10.shutdown();	 //Catch:{ all -> 0x01b0 }
        r10 = 0;
    L_0x013a:
        if (r7 == 0) goto L_0x0140;
    L_0x013c:
        r7.close();	 //Catch:{ Exception -> 0x01e5 }
    L_0x013f:
        r7 = 0;
    L_0x0140:
        if (r8 == 0) goto L_0x0146;
    L_0x0142:
        r8.close();	 //Catch:{ Exception -> 0x01e8 }
    L_0x0145:
        r8 = 0;
    L_0x0146:
        if (r6 == 0) goto L_0x014c;
    L_0x0148:
        r6.close();	 //Catch:{ all -> 0x01b0 }
        r6 = 0;
    L_0x014c:
        if (r5 == 0) goto L_0x0152;
    L_0x014e:
        r5.close();	 //Catch:{ all -> 0x01b0 }
    L_0x0151:
        r5 = 0;
    L_0x0152:
        r11 = r14 + 1;
        r14 = r11;
        goto L_0x0028;
    L_0x0157:
        r10 = 0;
        r13 = r10;
        goto L_0x004c;
    L_0x015b:
        r10 = new org.apache.http.client.methods.HttpPost;	 //Catch:{ UnknownHostException -> 0x0164, SocketException -> 0x02dd, SocketTimeoutException -> 0x02c2, ConnectTimeoutException -> 0x02ac, all -> 0x0292 }
        r12 = "http://aps.amap.com/APS/r";
        r10.<init>(r12);	 //Catch:{ UnknownHostException -> 0x0164, SocketException -> 0x02dd, SocketTimeoutException -> 0x02c2, ConnectTimeoutException -> 0x02ac, all -> 0x0292 }
        goto L_0x0055;
    L_0x0164:
        r10 = move-exception;
        r10 = r11;
        goto L_0x012a;
    L_0x0167:
        r12 = "GBK";
        goto L_0x0059;
    L_0x016b:
        r7 = new java.io.InputStreamReader;	 //Catch:{ UnknownHostException -> 0x0171, SocketException -> 0x02e8, SocketTimeoutException -> 0x02cd, ConnectTimeoutException -> 0x02b4, all -> 0x029d }
        r7.<init>(r9, r12);	 //Catch:{ UnknownHostException -> 0x0171, SocketException -> 0x02e8, SocketTimeoutException -> 0x02cd, ConnectTimeoutException -> 0x02b4, all -> 0x029d }
        goto L_0x0110;
    L_0x0171:
        r7 = move-exception;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r11;
        goto L_0x012a;
    L_0x0177:
        r3 = r4.toString();	 //Catch:{ UnknownHostException -> 0x0123, SocketException -> 0x01eb, SocketTimeoutException -> 0x021d, ConnectTimeoutException -> 0x024f, all -> 0x01ba }
        r5 = 0;
        r12 = r4.length();	 //Catch:{ UnknownHostException -> 0x0123, SocketException -> 0x01eb, SocketTimeoutException -> 0x021d, ConnectTimeoutException -> 0x024f, all -> 0x01ba }
        r4.delete(r5, r12);	 //Catch:{ UnknownHostException -> 0x0123, SocketException -> 0x01eb, SocketTimeoutException -> 0x021d, ConnectTimeoutException -> 0x024f, all -> 0x01ba }
        r4 = 0;
        r2 = 1;
        r5 = r6;
        r12 = r9;
        r6 = r7;
    L_0x0188:
        if (r10 == 0) goto L_0x0312;
    L_0x018a:
        r10.abort();	 //Catch:{ all -> 0x01b0 }
        r9 = 0;
    L_0x018e:
        if (r11 == 0) goto L_0x030f;
    L_0x0190:
        r7 = r11.getConnectionManager();	 //Catch:{ all -> 0x01b0 }
        r7.shutdown();	 //Catch:{ all -> 0x01b0 }
        r10 = 0;
    L_0x0198:
        if (r8 == 0) goto L_0x030c;
    L_0x019a:
        r8.close();	 //Catch:{ Exception -> 0x0287 }
    L_0x019d:
        r7 = 0;
    L_0x019e:
        if (r12 == 0) goto L_0x0309;
    L_0x01a0:
        r12.close();	 //Catch:{ Exception -> 0x028a }
    L_0x01a3:
        r8 = 0;
    L_0x01a4:
        if (r6 == 0) goto L_0x01aa;
    L_0x01a6:
        r6.close();	 //Catch:{ all -> 0x01b0 }
        r6 = 0;
    L_0x01aa:
        if (r5 == 0) goto L_0x0152;
    L_0x01ac:
        r5.close();	 //Catch:{ all -> 0x01b0 }
        goto L_0x0151;
    L_0x01b0:
        r2 = move-exception;
        monitor-exit(r19);
        throw r2;
    L_0x01b3:
        r12 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r9 != r12) goto L_0x01b7;
    L_0x01b7:
        r12 = r8;
        r8 = r7;
        goto L_0x0188;
    L_0x01ba:
        r2 = move-exception;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
    L_0x01bf:
        if (r10 == 0) goto L_0x01c4;
    L_0x01c1:
        r10.abort();	 //Catch:{ all -> 0x01b0 }
    L_0x01c4:
        if (r11 == 0) goto L_0x01cd;
    L_0x01c6:
        r3 = r11.getConnectionManager();	 //Catch:{ all -> 0x01b0 }
        r3.shutdown();	 //Catch:{ all -> 0x01b0 }
    L_0x01cd:
        if (r7 == 0) goto L_0x01d2;
    L_0x01cf:
        r7.close();	 //Catch:{ Exception -> 0x0281 }
    L_0x01d2:
        if (r8 == 0) goto L_0x01d7;
    L_0x01d4:
        r8.close();	 //Catch:{ Exception -> 0x0284 }
    L_0x01d7:
        if (r6 == 0) goto L_0x01dc;
    L_0x01d9:
        r6.close();	 //Catch:{ all -> 0x01b0 }
    L_0x01dc:
        if (r5 == 0) goto L_0x01e1;
    L_0x01de:
        r5.close();	 //Catch:{ all -> 0x01b0 }
    L_0x01e1:
        throw r2;	 //Catch:{ all -> 0x01b0 }
    L_0x01e2:
        r2 = r3;
        goto L_0x000d;
    L_0x01e5:
        r7 = move-exception;
        goto L_0x013f;
    L_0x01e8:
        r8 = move-exception;
        goto L_0x0145;
    L_0x01eb:
        r5 = move-exception;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
    L_0x01f0:
        if (r10 == 0) goto L_0x0324;
    L_0x01f2:
        r10.abort();	 //Catch:{ all -> 0x01b0 }
        r9 = 0;
    L_0x01f6:
        if (r11 == 0) goto L_0x0321;
    L_0x01f8:
        r10 = r11.getConnectionManager();	 //Catch:{ all -> 0x01b0 }
        r10.shutdown();	 //Catch:{ all -> 0x01b0 }
        r10 = 0;
    L_0x0200:
        if (r7 == 0) goto L_0x0206;
    L_0x0202:
        r7.close();	 //Catch:{ Exception -> 0x0219 }
    L_0x0205:
        r7 = 0;
    L_0x0206:
        if (r8 == 0) goto L_0x020c;
    L_0x0208:
        r8.close();	 //Catch:{ Exception -> 0x021b }
    L_0x020b:
        r8 = 0;
    L_0x020c:
        if (r6 == 0) goto L_0x0212;
    L_0x020e:
        r6.close();	 //Catch:{ all -> 0x01b0 }
        r6 = 0;
    L_0x0212:
        if (r5 == 0) goto L_0x0152;
    L_0x0214:
        r5.close();	 //Catch:{ all -> 0x01b0 }
        goto L_0x0151;
    L_0x0219:
        r7 = move-exception;
        goto L_0x0205;
    L_0x021b:
        r8 = move-exception;
        goto L_0x020b;
    L_0x021d:
        r5 = move-exception;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
    L_0x0222:
        if (r10 == 0) goto L_0x031e;
    L_0x0224:
        r10.abort();	 //Catch:{ all -> 0x01b0 }
        r9 = 0;
    L_0x0228:
        if (r11 == 0) goto L_0x031b;
    L_0x022a:
        r10 = r11.getConnectionManager();	 //Catch:{ all -> 0x01b0 }
        r10.shutdown();	 //Catch:{ all -> 0x01b0 }
        r10 = 0;
    L_0x0232:
        if (r7 == 0) goto L_0x0238;
    L_0x0234:
        r7.close();	 //Catch:{ Exception -> 0x024b }
    L_0x0237:
        r7 = 0;
    L_0x0238:
        if (r8 == 0) goto L_0x023e;
    L_0x023a:
        r8.close();	 //Catch:{ Exception -> 0x024d }
    L_0x023d:
        r8 = 0;
    L_0x023e:
        if (r6 == 0) goto L_0x0244;
    L_0x0240:
        r6.close();	 //Catch:{ all -> 0x01b0 }
        r6 = 0;
    L_0x0244:
        if (r5 == 0) goto L_0x0152;
    L_0x0246:
        r5.close();	 //Catch:{ all -> 0x01b0 }
        goto L_0x0151;
    L_0x024b:
        r7 = move-exception;
        goto L_0x0237;
    L_0x024d:
        r8 = move-exception;
        goto L_0x023d;
    L_0x024f:
        r5 = move-exception;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r9;
    L_0x0254:
        if (r10 == 0) goto L_0x0318;
    L_0x0256:
        r10.abort();	 //Catch:{ all -> 0x01b0 }
        r9 = 0;
    L_0x025a:
        if (r11 == 0) goto L_0x0315;
    L_0x025c:
        r10 = r11.getConnectionManager();	 //Catch:{ all -> 0x01b0 }
        r10.shutdown();	 //Catch:{ all -> 0x01b0 }
        r10 = 0;
    L_0x0264:
        if (r7 == 0) goto L_0x026a;
    L_0x0266:
        r7.close();	 //Catch:{ Exception -> 0x027d }
    L_0x0269:
        r7 = 0;
    L_0x026a:
        if (r8 == 0) goto L_0x0270;
    L_0x026c:
        r8.close();	 //Catch:{ Exception -> 0x027f }
    L_0x026f:
        r8 = 0;
    L_0x0270:
        if (r6 == 0) goto L_0x0276;
    L_0x0272:
        r6.close();	 //Catch:{ all -> 0x01b0 }
        r6 = 0;
    L_0x0276:
        if (r5 == 0) goto L_0x0152;
    L_0x0278:
        r5.close();	 //Catch:{ all -> 0x01b0 }
        goto L_0x0151;
    L_0x027d:
        r7 = move-exception;
        goto L_0x0269;
    L_0x027f:
        r8 = move-exception;
        goto L_0x026f;
    L_0x0281:
        r3 = move-exception;
        goto L_0x01d2;
    L_0x0284:
        r3 = move-exception;
        goto L_0x01d7;
    L_0x0287:
        r7 = move-exception;
        goto L_0x019d;
    L_0x028a:
        r8 = move-exception;
        goto L_0x01a3;
    L_0x028d:
        r2 = move-exception;
        r11 = r10;
        r10 = r9;
        goto L_0x01bf;
    L_0x0292:
        r2 = move-exception;
        r10 = r9;
        goto L_0x01bf;
    L_0x0296:
        r2 = move-exception;
        goto L_0x01bf;
    L_0x0299:
        r2 = move-exception;
        r8 = r9;
        goto L_0x01bf;
    L_0x029d:
        r2 = move-exception;
        r7 = r8;
        r8 = r9;
        goto L_0x01bf;
    L_0x02a2:
        r2 = move-exception;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        goto L_0x01bf;
    L_0x02a8:
        r11 = move-exception;
        r11 = r10;
        r10 = r9;
        goto L_0x0254;
    L_0x02ac:
        r10 = move-exception;
        r10 = r9;
        goto L_0x0254;
    L_0x02af:
        r9 = move-exception;
        goto L_0x0254;
    L_0x02b1:
        r8 = move-exception;
        r8 = r9;
        goto L_0x0254;
    L_0x02b4:
        r7 = move-exception;
        r7 = r8;
        r8 = r9;
        goto L_0x0254;
    L_0x02b8:
        r6 = move-exception;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        goto L_0x0254;
    L_0x02bd:
        r11 = move-exception;
        r11 = r10;
        r10 = r9;
        goto L_0x0222;
    L_0x02c2:
        r10 = move-exception;
        r10 = r9;
        goto L_0x0222;
    L_0x02c6:
        r9 = move-exception;
        goto L_0x0222;
    L_0x02c9:
        r8 = move-exception;
        r8 = r9;
        goto L_0x0222;
    L_0x02cd:
        r7 = move-exception;
        r7 = r8;
        r8 = r9;
        goto L_0x0222;
    L_0x02d2:
        r6 = move-exception;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        goto L_0x0222;
    L_0x02d8:
        r11 = move-exception;
        r11 = r10;
        r10 = r9;
        goto L_0x01f0;
    L_0x02dd:
        r10 = move-exception;
        r10 = r9;
        goto L_0x01f0;
    L_0x02e1:
        r9 = move-exception;
        goto L_0x01f0;
    L_0x02e4:
        r8 = move-exception;
        r8 = r9;
        goto L_0x01f0;
    L_0x02e8:
        r7 = move-exception;
        r7 = r8;
        r8 = r9;
        goto L_0x01f0;
    L_0x02ed:
        r6 = move-exception;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        goto L_0x01f0;
    L_0x02f3:
        r11 = move-exception;
        goto L_0x012a;
    L_0x02f6:
        r9 = move-exception;
        r9 = r10;
        r10 = r11;
        goto L_0x012a;
    L_0x02fb:
        r8 = move-exception;
        r8 = r9;
        r9 = r10;
        r10 = r11;
        goto L_0x012a;
    L_0x0301:
        r6 = move-exception;
        r6 = r7;
        r7 = r8;
        r8 = r9;
        r9 = r10;
        r10 = r11;
        goto L_0x012a;
    L_0x0309:
        r8 = r12;
        goto L_0x01a4;
    L_0x030c:
        r7 = r8;
        goto L_0x019e;
    L_0x030f:
        r10 = r11;
        goto L_0x0198;
    L_0x0312:
        r9 = r10;
        goto L_0x018e;
    L_0x0315:
        r10 = r11;
        goto L_0x0264;
    L_0x0318:
        r9 = r10;
        goto L_0x025a;
    L_0x031b:
        r10 = r11;
        goto L_0x0232;
    L_0x031e:
        r9 = r10;
        goto L_0x0228;
    L_0x0321:
        r10 = r11;
        goto L_0x0200;
    L_0x0324:
        r9 = r10;
        goto L_0x01f6;
    L_0x0327:
        r8 = r7;
        goto L_0x0109;
    L_0x032a:
        r12 = r8;
        goto L_0x00fe;
        */

    }
}