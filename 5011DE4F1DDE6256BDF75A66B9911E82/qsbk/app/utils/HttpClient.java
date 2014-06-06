package qsbk.app.utils;

import android.os.Build;
import android.text.TextUtils;
import com.qiubai.library.adview.util.AdViewNetFetchThread;
import com.tencent.cloudsdk.http.TURLStreamHandler;
import com.tencent.mm.sdk.contact.RContactStorage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.message.api.ChatEngine;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.HttpManager;

public class HttpClient {
    public static final int REQUEST_TIMEOUT_CODE = 408;
    public static final int RESP_CODE_LOCAL_ERROR = 9999;
    public static final int RESP_CODE_SUCCESS = 0;
    private static int a;
    private static HttpClient c;
    private int b;
    private String d;
    private long e;

    static {
        a = 0;
        c = null;
        System.loadLibrary("qbappsecret");
    }

    public HttpClient() {
        this.b = 1;
        this.d = null;
        this.e = -1;
    }

    private String a(String r2_String, String r3_String, String r4_String) throws QiushibaikeException {
        return httpRequest(r2_String, r3_String, r4_String, null);
    }

    private String a(String r4_String, Map<String, Object> r5_Map_String__Object) throws QiushibaikeException {
        String r0_String = HttpManager.HTTPMETHOD_GET;
        StringBuffer r2_StringBuffer;
        if (r5_Map_String__Object == null || r5_Map_String__Object.size() <= 0) {
            r2_StringBuffer = new StringBuffer(RContactStorage.PRIMARY_KEY);
            if (r5_Map_String__Object == null) {
                try {
                    r2_StringBuffer.append(encodeParameters(r5_Map_String__Object));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return a(r4_String, r2_StringBuffer.toString(), r0_String);
        } else {
            r0_String = UsersAPI.HTTPMETHOD_POST;
            r2_StringBuffer = new StringBuffer(RContactStorage.PRIMARY_KEY);
            if (r5_Map_String__Object == null) {
                return a(r4_String, r2_StringBuffer.toString(), r0_String);
            }
            r2_StringBuffer.append(encodeParameters(r5_Map_String__Object));
            return a(r4_String, r2_StringBuffer.toString(), r0_String);
        }
    }

    private HttpURLConnection a(String r6_String) throws IOException {
        HttpURLConnection r0_HttpURLConnection = (HttpURLConnection) (mobileSpeedupEnable(r6_String) ? new URL(null, r6_String, new TURLStreamHandler().getURLStreamHandler()) : new URL(r6_String)).openConnection();
        r0_HttpURLConnection.setConnectTimeout(10000);
        r0_HttpURLConnection.setReadTimeout(10000);
        String r1_String;
        if (r6_String.indexOf("vote_queue") == -1 && r6_String.indexOf("signup") == -1 && r6_String.indexOf("v2/signin") == -1 && r6_String.indexOf("review") == -1) {
            r1_String = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
            if (TextUtils.isEmpty(r1_String)) {
                r1_String = "auto";
                r0_HttpURLConnection.setRequestProperty("User-Agent", "qiushibalke_" + Constants.localVersionName + "_" + HttpUtils.getNetworkType(QsbkApp.mContext) + "_" + r1_String + "_" + QsbkApp.getInstance().getMetaDataValue("BaiduMobAd_CHANNEL"));
                return a(r0_HttpURLConnection);
            } else {
                r0_HttpURLConnection.setRequestProperty("User-Agent", "qiushibalke_" + Constants.localVersionName + "_" + HttpUtils.getNetworkType(QsbkApp.mContext) + "_" + r1_String + "_" + QsbkApp.getInstance().getMetaDataValue("BaiduMobAd_CHANNEL"));
                return a(r0_HttpURLConnection);
            }
        } else {
            r0_HttpURLConnection = b(r0_HttpURLConnection);
            r1_String = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
            if (TextUtils.isEmpty(r1_String)) {
                r1_String = "auto";
            }
            r0_HttpURLConnection.setRequestProperty("User-Agent", "qiushibalke_" + Constants.localVersionName + "_" + HttpUtils.getNetworkType(QsbkApp.mContext) + "_" + r1_String + "_" + QsbkApp.getInstance().getMetaDataValue("BaiduMobAd_CHANNEL"));
            return a(r0_HttpURLConnection);
        }
    }

    private HttpURLConnection a(HttpURLConnection r4_HttpURLConnection) {
        r4_HttpURLConnection.setRequestProperty("Source", "android_" + Constants.localVersionName);
        r4_HttpURLConnection.setRequestProperty("Model", Build.FINGERPRINT);
        if (QsbkApp.currentUser != null) {
            r4_HttpURLConnection.setRequestProperty("Qbtoken", QsbkApp.currentUser.token);
        }
        r4_HttpURLConnection.setRequestProperty("Uuid", DeviceUtils.getAndroidId());
        r4_HttpURLConnection.setRequestProperty("Deviceidinfo", DeviceUtils.getDeviceIdInfo());
        return r4_HttpURLConnection;
    }

    private HttpURLConnection b(HttpURLConnection r4_HttpURLConnection) {
        r4_HttpURLConnection.setRequestProperty("qb", UUID.randomUUID().toString().trim().replaceAll("-", RContactStorage.PRIMARY_KEY));
        return r4_HttpURLConnection;
    }

    public static String encodeParameters(Map<String, Object> r4_Map_String__Object) throws JSONException {
        JSONObject r2_JSONObject = new JSONObject();
        Iterator r3_Iterator = r4_Map_String__Object.entrySet().iterator();
        while (r3_Iterator.hasNext()) {
            Entry r0_Entry = (Entry) r3_Iterator.next();
            r2_JSONObject.put((String) r0_Entry.getKey(), r0_Entry.getValue());
        }
        return r2_JSONObject.toString();
    }

    public static HttpClient getIntentce() {
        if (c == null) {
            c = new HttpClient();
        }
        return c;
    }

    public static String getLocalErrorStr() {
        return QsbkApp.mContext.getResources().getString(R.string.network_error_retry);
    }

    public static String readStream(InputStream r4_InputStream) throws Exception {
        String r0_String = RContactStorage.PRIMARY_KEY;
        if (r4_InputStream == null) {
            return r0_String;
        }
        try {
            BufferedReader r1_BufferedReader = new BufferedReader(new InputStreamReader(r4_InputStream, AdViewNetFetchThread.NetEncoding));
            StringBuffer r2_StringBuffer = new StringBuffer();
            while (true) {
                String r3_String = r1_BufferedReader.readLine();
                if (r3_String != null) {
                    r2_StringBuffer.append(r3_String);
                } else {
                    r0_String = r2_StringBuffer.toString();
                    return r0_String.startsWith("<html>") ? RContactStorage.PRIMARY_KEY : r0_String;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return r0_String;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String delete(String r10_String) {
        /*
        r9_this = this;
        r0 = 0;
        r2 = "";
        r5 = 1;
        r3 = 0;
        r4 = r0;
        r0 = r2;
    L_0x0007:
        if (r4 >= r5) goto L_0x0081;
    L_0x0009:
        r1 = "http://msg.qiushibaike.com/messages";
        r1 = r10.startsWith(r1);	 //Catch:{ Exception -> 0x0082 }
        if (r1 == 0) goto L_0x0087;
    L_0x0011:
        r2 = new com.tencent.cloudsdk.report.HttpStatistics;	 //Catch:{ Exception -> 0x0082 }
        r2.<init>();	 //Catch:{ Exception -> 0x0082 }
        r2.timeStart();	 //Catch:{ Exception -> 0x0055 }
    L_0x0019:
        r1 = r9.a(r10);	 //Catch:{ Exception -> 0x0055 }
        r3 = "DELETE";
        r1.setRequestMethod(r3);	 //Catch:{ Exception -> 0x0055 }
        r3 = "http://msg.qiushibaike.com/messages";
        r3 = r10.startsWith(r3);	 //Catch:{ Exception -> 0x0055 }
        if (r3 == 0) goto L_0x002f;
    L_0x002a:
        if (r2 == 0) goto L_0x002f;
    L_0x002c:
        r2.timeEnd();	 //Catch:{ Exception -> 0x0055 }
    L_0x002f:
        r3 = r1.getResponseCode();	 //Catch:{ Exception -> 0x0055 }
        r9.b = r3;	 //Catch:{ Exception -> 0x0055 }
        r3 = r9.b;	 //Catch:{ Exception -> 0x0055 }
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 == r6) goto L_0x0063;
    L_0x003b:
        r1 = "http://msg.qiushibaike.com/messages";
        r1 = r10.startsWith(r1);	 //Catch:{ Exception -> 0x0055 }
        if (r1 == 0) goto L_0x004b;
    L_0x0043:
        if (r2 == 0) goto L_0x004b;
    L_0x0045:
        r1 = -1;
        r6 = 0;
        r2.report(r10, r1, r6);	 //Catch:{ Exception -> 0x0055 }
    L_0x004b:
        r1 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ Exception -> 0x0055 }
        r3 = "\u670d\u52a1\u5668\u8def\u5f84\u4e0d\u5b58\u5728";
        r6 = r9.b;	 //Catch:{ Exception -> 0x0055 }
        r1.<init>(r3, r6);	 //Catch:{ Exception -> 0x0055 }
        throw r1;	 //Catch:{ Exception -> 0x0055 }
    L_0x0055:
        r1 = move-exception;
        r8 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r8;
    L_0x005a:
        r0.printStackTrace();
        r0 = r4 + 1;
        r3 = r1;
        r4 = r0;
        r0 = r2;
        goto L_0x0007;
    L_0x0063:
        r1 = r1.getInputStream();	 //Catch:{ Exception -> 0x0055 }
        r3 = "http://msg.qiushibaike.com/messages";
        r3 = r10.startsWith(r3);	 //Catch:{ Exception -> 0x0055 }
        if (r3 == 0) goto L_0x007a;
    L_0x006f:
        if (r2 == 0) goto L_0x007a;
    L_0x0071:
        r3 = 0;
        r6 = r1.available();	 //Catch:{ Exception -> 0x0055 }
        r6 = (long) r6;	 //Catch:{ Exception -> 0x0055 }
        r2.report(r10, r3, r6);	 //Catch:{ Exception -> 0x0055 }
    L_0x007a:
        r0 = readStream(r1);	 //Catch:{ Exception -> 0x0055 }
        r1.close();	 //Catch:{ Exception -> 0x0055 }
    L_0x0081:
        return r0;
    L_0x0082:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x005a;
    L_0x0087:
        r2 = r3;
        goto L_0x0019;
        */

    }

    public String get(String r2_String) throws QiushibaikeException {
        return a(r2_String, null);
    }

    public long getLastSuccessTime() {
        return this.e;
    }

    public String getLastSuccessUrl() {
        return this.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String httpRequest(String r16_String, String r17_String, String r18_String, Map<String, String> r19_Map_String__String) throws QiushibaikeException {
        /*
        r15_this = this;
        r1 = "rqcnt=";
        r0 = r16;
        r1 = r0.indexOf(r1);
        r2 = -1;
        if (r1 == r2) goto L_0x006c;
    L_0x000b:
        r7 = "";
        r6 = -1;
        r1 = 2;
        r2 = "vote_queue";
        r0 = r16;
        r2 = r0.indexOf(r2);
        r3 = -1;
        if (r2 == r3) goto L_0x0243;
    L_0x001a:
        r1 = 1;
        r3 = r1;
    L_0x001c:
        r5 = 0;
        r1 = 0;
        r2 = 0;
        r8 = r2;
    L_0x0020:
        if (r8 >= r3) goto L_0x0240;
    L_0x0022:
        r2 = r15.mobileSpeedupEnable(r16);	 //Catch:{ Exception -> 0x0222 }
        if (r2 == 0) goto L_0x023d;
    L_0x0028:
        r4 = new com.tencent.cloudsdk.report.HttpStatistics;	 //Catch:{ Exception -> 0x0222 }
        r4.<init>();	 //Catch:{ Exception -> 0x0222 }
        r4.timeStart();	 //Catch:{ Exception -> 0x005c }
    L_0x0030:
        r5 = r15.a(r16);	 //Catch:{ Exception -> 0x005c }
        if (r19 == 0) goto L_0x00a1;
    L_0x0036:
        r1 = r19.isEmpty();	 //Catch:{ Exception -> 0x005c }
        if (r1 != 0) goto L_0x00a1;
    L_0x003c:
        r1 = r19.keySet();	 //Catch:{ Exception -> 0x005c }
        r9 = r1.iterator();	 //Catch:{ Exception -> 0x005c }
    L_0x0044:
        r1 = r9.hasNext();	 //Catch:{ Exception -> 0x005c }
        if (r1 == 0) goto L_0x00a1;
    L_0x004a:
        r1 = r9.next();	 //Catch:{ Exception -> 0x005c }
        r1 = (java.lang.String) r1;	 //Catch:{ Exception -> 0x005c }
        r0 = r19;
        r2 = r0.get(r1);	 //Catch:{ Exception -> 0x005c }
        r2 = (java.lang.String) r2;	 //Catch:{ Exception -> 0x005c }
        r5.setRequestProperty(r1, r2);	 //Catch:{ Exception -> 0x005c }
        goto L_0x0044;
    L_0x005c:
        r1 = move-exception;
        r1 = r4;
        r2 = r5;
        r4 = r6;
        r5 = r7;
    L_0x0061:
        r6 = r3 + -1;
        if (r8 == r6) goto L_0x0211;
    L_0x0065:
        r6 = r8 + 1;
        r8 = r6;
        r7 = r5;
        r6 = r4;
        r5 = r2;
        goto L_0x0020;
    L_0x006c:
        r1 = "?";
        r0 = r16;
        r1 = r0.indexOf(r1);
        r2 = -1;
        if (r1 <= r2) goto L_0x009e;
    L_0x0077:
        r1 = 38;
    L_0x0079:
        r2 = "%crqcnt=%d";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r1 = java.lang.Character.valueOf(r1);
        r3[r4] = r1;
        r1 = 1;
        r4 = a;
        r5 = r4 + 1;
        a = r5;
        r4 = java.lang.Integer.valueOf(r4);
        r3[r1] = r4;
        r1 = java.lang.String.format(r2, r3);
        r0 = r16;
        r16 = r0.concat(r1);
        goto L_0x000b;
    L_0x009e:
        r1 = 63;
        goto L_0x0079;
    L_0x00a1:
        r1 = android.text.TextUtils.isEmpty(r17);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        if (r1 != 0) goto L_0x0172;
    L_0x00a7:
        r1 = "POST";
        r0 = r18;
        r1 = r0.equals(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        if (r1 == 0) goto L_0x0172;
    L_0x00b1:
        r1 = "POST";
        r5.setRequestMethod(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = "signup";
        r0 = r16;
        r1 = r0.indexOf(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = -1;
        if (r1 != r2) goto L_0x00e2;
    L_0x00c1:
        r1 = "vote_queue";
        r0 = r16;
        r1 = r0.indexOf(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = -1;
        if (r1 != r2) goto L_0x00e2;
    L_0x00cc:
        r1 = "review";
        r0 = r16;
        r1 = r0.indexOf(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = -1;
        if (r1 != r2) goto L_0x00e2;
    L_0x00d7:
        r1 = "v2/signin";
        r0 = r16;
        r1 = r0.indexOf(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = -1;
        if (r1 == r2) goto L_0x00f5;
    L_0x00e2:
        r1 = r17.toString();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = "qb";
        r2 = r5.getRequestProperty(r2);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = r15.verifyStringEncode(r1, r2);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = "Url-Verify";
        r5.setRequestProperty(r2, r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
    L_0x00f5:
        r1 = "Charset";
        r2 = "utf-8";
        r5.setRequestProperty(r1, r2);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = 1;
        r5.setDoInput(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = 1;
        r5.setDoOutput(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = 0;
        r5.setUseCaches(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = new qsbk.app.utils.TimeDelta;	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1.<init>();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = r5.getOutputStream();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r9 = r17.toString();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r9 = r9.getBytes();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2.write(r9);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2.flush();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2.close();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r2 = "request url:%s use time:%d";
        r9 = 2;
        r9 = new java.lang.Object[r9];	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r10 = 0;
        r9[r10] = r16;	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r10 = 1;
        r11 = r1.getDelta();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = java.lang.Long.valueOf(r11);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r9[r10] = r1;	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = java.lang.String.format(r2, r9);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        qsbk.app.utils.LogUtil.d(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
    L_0x013c:
        r2 = r5.getResponseCode();	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r1) goto L_0x017e;
    L_0x0144:
        if (r4 == 0) goto L_0x0157;
    L_0x0146:
        r1 = r15.mobileSpeedupEnable(r16);	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        if (r1 == 0) goto L_0x0157;
    L_0x014c:
        r4.timeEnd();	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        r1 = -1;
        r9 = 0;
        r0 = r16;
        r4.report(r0, r1, r9);	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
    L_0x0157:
        r1 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        r6 = "\u670d\u52a1\u5668\u8def\u5f84\u4e0d\u5b58\u5728";
        r1.<init>(r6, r2);	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        throw r1;	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
    L_0x015f:
        r1 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r7;
    L_0x0163:
        r6.printStackTrace();	 //Catch:{ Exception -> 0x01db }
        r6 = r3 + -1;
        if (r8 == r6) goto L_0x01c2;
    L_0x016a:
        r13 = r4;
        r4 = r1;
        r1 = r13;
        r14 = r5;
        r5 = r2;
        r2 = r14;
        goto L_0x0065;
    L_0x0172:
        r1 = "GET";
        r5.setRequestMethod(r1);	 //Catch:{ JSONException -> 0x0178, Exception -> 0x0228 }
        goto L_0x013c;
    L_0x0178:
        r1 = move-exception;
        r2 = r7;
        r13 = r6;
        r6 = r1;
        r1 = r13;
        goto L_0x0163;
    L_0x017e:
        r6 = r5.getInputStream();	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        if (r6 == 0) goto L_0x023a;
    L_0x0184:
        r1 = "gzip";
        r9 = r5.getContentEncoding();	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        r1 = r1.equals(r9);	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        if (r1 == 0) goto L_0x023a;
    L_0x0190:
        r1 = new java.util.zip.GZIPInputStream;	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        r1.<init>(r6);	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
    L_0x0195:
        r6 = readStream(r1);	 //Catch:{ JSONException -> 0x015f, Exception -> 0x022e }
        if (r4 == 0) goto L_0x01af;
    L_0x019b:
        r7 = r15.mobileSpeedupEnable(r16);	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
        if (r7 == 0) goto L_0x01af;
    L_0x01a1:
        r4.timeEnd();	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
        r7 = 0;
        r9 = r1.available();	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
        r9 = (long) r9;	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
        r0 = r16;
        r4.report(r0, r7, r9);	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
    L_0x01af:
        r1.close();	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
        r0 = r16;
        r15.d = r0;	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
        r9 = java.lang.System.currentTimeMillis();	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
        r15.e = r9;	 //Catch:{ JSONException -> 0x0233, Exception -> 0x01e4 }
    L_0x01bc:
        if (r5 == 0) goto L_0x01c1;
    L_0x01be:
        r5.disconnect();
    L_0x01c1:
        return r6;
    L_0x01c2:
        r6 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ Exception -> 0x01db }
        r7 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x01db }
        r7.<init>();	 //Catch:{ Exception -> 0x01db }
        r7 = r7.append(r1);	 //Catch:{ Exception -> 0x01db }
        r9 = "";
        r7 = r7.append(r9);	 //Catch:{ Exception -> 0x01db }
        r7 = r7.toString();	 //Catch:{ Exception -> 0x01db }
        r6.<init>(r7, r1);	 //Catch:{ Exception -> 0x01db }
        throw r6;	 //Catch:{ Exception -> 0x01db }
    L_0x01db:
        r6 = move-exception;
        r13 = r4;
        r4 = r1;
        r1 = r13;
        r14 = r5;
        r5 = r2;
        r2 = r14;
        goto L_0x0061;
    L_0x01e4:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r6;
        r6 = r13;
    L_0x01e9:
        r6.printStackTrace();	 //Catch:{ Exception -> 0x01db }
        r6 = r3 + -1;
        if (r8 == r6) goto L_0x01f8;
    L_0x01f0:
        r13 = r4;
        r4 = r1;
        r1 = r13;
        r14 = r5;
        r5 = r2;
        r2 = r14;
        goto L_0x0065;
    L_0x01f8:
        r6 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ Exception -> 0x01db }
        r7 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x01db }
        r7.<init>();	 //Catch:{ Exception -> 0x01db }
        r7 = r7.append(r1);	 //Catch:{ Exception -> 0x01db }
        r9 = "";
        r7 = r7.append(r9);	 //Catch:{ Exception -> 0x01db }
        r7 = r7.toString();	 //Catch:{ Exception -> 0x01db }
        r6.<init>(r7, r1);	 //Catch:{ Exception -> 0x01db }
        throw r6;	 //Catch:{ Exception -> 0x01db }
    L_0x0211:
        r6 = r3 + -1;
        if (r8 != r6) goto L_0x0065;
    L_0x0215:
        if (r2 == 0) goto L_0x021a;
    L_0x0217:
        r2.disconnect();
    L_0x021a:
        r1 = new qsbk.app.exception.QiushibaikeException;
        r2 = "\u670d\u52a1\u5668\u79bb\u5bb6\u51fa\u8d70\u4e86 ";
        r1.<init>(r2, r4);
        throw r1;
    L_0x0222:
        r2 = move-exception;
        r2 = r5;
        r4 = r6;
        r5 = r7;
        goto L_0x0061;
    L_0x0228:
        r1 = move-exception;
        r2 = r7;
        r13 = r6;
        r6 = r1;
        r1 = r13;
        goto L_0x01e9;
    L_0x022e:
        r1 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x01e9;
    L_0x0233:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r6;
        r6 = r13;
        goto L_0x0163;
    L_0x023a:
        r1 = r6;
        goto L_0x0195;
    L_0x023d:
        r4 = r1;
        goto L_0x0030;
    L_0x0240:
        r6 = r7;
        goto L_0x01bc;
    L_0x0243:
        r3 = r1;
        goto L_0x001c;
        */

    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String messgePorst(String r11_String) throws QiushibaikeException {
        /*
        r10_this = this;
        r2 = 2;
        r1 = 1;
        r3 = 0;
        r7 = -1;
        r0 = "rqcnt=";
        r0 = r11.indexOf(r0);
        if (r0 == r7) goto L_0x0084;
    L_0x000c:
        r4 = "";
        r0 = "vote_queue";
        r0 = r11.indexOf(r0);
        if (r0 == r7) goto L_0x013a;
    L_0x0016:
        r0 = r1;
    L_0x0017:
        r1 = 0;
        r5 = r3;
        r3 = r4;
    L_0x001a:
        if (r5 >= r0) goto L_0x00d8;
    L_0x001c:
        r1 = r10.a(r11);	 //Catch:{ Exception -> 0x0135 }
        r6 = new com.tencent.cloudsdk.report.HttpStatistics;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r6.<init>();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r6.timeStart();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = "POST";
        r1.setRequestMethod(r2);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = "Charset";
        r4 = "utf-8";
        r1.setRequestProperty(r2, r4);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = 1;
        r1.setDoInput(r2);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = 1;
        r1.setDoOutput(r2);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = 0;
        r1.setUseCaches(r2);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = r1.getOutputStream();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r4 = "";
        r4 = r4.toString();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r4 = r4.getBytes();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2.write(r4);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2.flush();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2.close();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r6.timeEnd();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = r1.getResponseCode();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r10.b = r2;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = r10.b;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r4) goto L_0x00b1;
    L_0x0066:
        r2 = -1;
        r7 = 0;
        r6.report(r11, r2, r7);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r4 = "\u670d\u52a1\u5668\u8def\u5f84\u4e0d\u5b58\u5728";
        r6 = r10.b;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2.<init>(r4, r6);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        throw r2;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
    L_0x0076:
        r2 = move-exception;
        r9 = r2;
        r2 = r3;
        r3 = r9;
        r3.printStackTrace();	 //Catch:{ Exception -> 0x00fb }
        if (r5 == r0) goto L_0x00de;
    L_0x007f:
        r3 = r5 + 1;
        r5 = r3;
        r3 = r2;
        goto L_0x001a;
    L_0x0084:
        r0 = "?";
        r0 = r11.indexOf(r0);
        if (r0 <= r7) goto L_0x00ae;
    L_0x008c:
        r0 = 38;
    L_0x008e:
        r4 = "%crqcnt=%d";
        r5 = new java.lang.Object[r2];
        r0 = java.lang.Character.valueOf(r0);
        r5[r3] = r0;
        r0 = a;
        r6 = r0 + 1;
        a = r6;
        r0 = java.lang.Integer.valueOf(r0);
        r5[r1] = r0;
        r0 = java.lang.String.format(r4, r5);
        r11 = r11.concat(r0);
        goto L_0x000c;
    L_0x00ae:
        r0 = 63;
        goto L_0x008e;
    L_0x00b1:
        r4 = r1.getInputStream();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        if (r4 == 0) goto L_0x0138;
    L_0x00b7:
        r2 = "gzip";
        r7 = r1.getContentEncoding();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2 = r2.equals(r7);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        if (r2 == 0) goto L_0x0138;
    L_0x00c3:
        r2 = new java.util.zip.GZIPInputStream;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2.<init>(r4);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
    L_0x00c8:
        r4 = 0;
        r7 = r2.available();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r7 = (long) r7;	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r6.report(r11, r4, r7);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r3 = readStream(r2);	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
        r2.close();	 //Catch:{ JSONException -> 0x0076, Exception -> 0x010f }
    L_0x00d8:
        if (r1 == 0) goto L_0x00dd;
    L_0x00da:
        r1.disconnect();
    L_0x00dd:
        return r3;
    L_0x00de:
        r3 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ Exception -> 0x00fb }
        r4 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00fb }
        r4.<init>();	 //Catch:{ Exception -> 0x00fb }
        r6 = r10.b;	 //Catch:{ Exception -> 0x00fb }
        r4 = r4.append(r6);	 //Catch:{ Exception -> 0x00fb }
        r6 = "";
        r4 = r4.append(r6);	 //Catch:{ Exception -> 0x00fb }
        r4 = r4.toString();	 //Catch:{ Exception -> 0x00fb }
        r6 = r10.b;	 //Catch:{ Exception -> 0x00fb }
        r3.<init>(r4, r6);	 //Catch:{ Exception -> 0x00fb }
        throw r3;	 //Catch:{ Exception -> 0x00fb }
    L_0x00fb:
        r3 = move-exception;
    L_0x00fc:
        if (r5 != r0) goto L_0x007f;
    L_0x00fe:
        if (r5 != r0) goto L_0x007f;
    L_0x0100:
        if (r1 == 0) goto L_0x0105;
    L_0x0102:
        r1.disconnect();
    L_0x0105:
        r0 = new qsbk.app.exception.QiushibaikeException;
        r1 = "\u670d\u52a1\u5668\u79bb\u5bb6\u51fa\u8d70\u4e86";
        r2 = r10.b;
        r0.<init>(r1, r2);
        throw r0;
    L_0x010f:
        r2 = move-exception;
        r9 = r2;
        r2 = r3;
        r3 = r9;
        r3.printStackTrace();	 //Catch:{ Exception -> 0x00fb }
        if (r5 != r0) goto L_0x007f;
    L_0x0118:
        r3 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ Exception -> 0x00fb }
        r4 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x00fb }
        r4.<init>();	 //Catch:{ Exception -> 0x00fb }
        r6 = r10.b;	 //Catch:{ Exception -> 0x00fb }
        r4 = r4.append(r6);	 //Catch:{ Exception -> 0x00fb }
        r6 = "";
        r4 = r4.append(r6);	 //Catch:{ Exception -> 0x00fb }
        r4 = r4.toString();	 //Catch:{ Exception -> 0x00fb }
        r6 = r10.b;	 //Catch:{ Exception -> 0x00fb }
        r3.<init>(r4, r6);	 //Catch:{ Exception -> 0x00fb }
        throw r3;	 //Catch:{ Exception -> 0x00fb }
    L_0x0135:
        r2 = move-exception;
        r2 = r3;
        goto L_0x00fc;
    L_0x0138:
        r2 = r4;
        goto L_0x00c8;
    L_0x013a:
        r0 = r2;
        goto L_0x0017;
        */

    }

    public boolean mobileSpeedupEnable(String r2_String) {
        return !(QsbkApp.mobileSpeedupDisable) && r2_String.startsWith(ChatEngine.CHAT_SERVER);
    }

    public String post(String r2_String, String r3_String) throws QiushibaikeException {
        return a(r2_String, r3_String, UsersAPI.HTTPMETHOD_POST);
    }

    public String post(String r2_String, Map<String, Object> r3_Map_String__Object) throws QiushibaikeException {
        return a(r2_String, r3_Map_String__Object);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String submit(String r17_String, Map<String, Object> r18_Map_String__Object, InputStream r19_InputStream) throws QiushibaikeException {
        /*
        r16_this = this;
        r3 = "";
        r6 = 4;
        r1 = java.util.UUID.randomUUID();
        r7 = r1.toString();
        r8 = "--";
        r9 = "\r\n";
        r10 = "multipart/form-data";
        r11 = "unicode";
        r1 = 0;
        r5 = r1;
    L_0x0015:
        if (r5 >= r6) goto L_0x01ce;
    L_0x0017:
        r4 = -1;
        r12 = r16.a(r17);	 //Catch:{ Exception -> 0x0085 }
        r1 = "POST";
        r12.setRequestMethod(r1);	 //Catch:{ Exception -> 0x0085 }
        r1 = 1;
        r12.setDoOutput(r1);	 //Catch:{ Exception -> 0x0085 }
        r1 = 0;
        r12.setUseCaches(r1);	 //Catch:{ Exception -> 0x0085 }
        r1 = 1;
        r12.setDoInput(r1);	 //Catch:{ Exception -> 0x0085 }
        r1 = "Connection";
        r2 = "keep-alive";
        r12.setRequestProperty(r1, r2);	 //Catch:{ Exception -> 0x0085 }
        r1 = "Charsert";
        r2 = "UTF-8";
        r12.setRequestProperty(r1, r2);	 //Catch:{ Exception -> 0x0085 }
        r1 = "Content-Type";
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r2.<init>();	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r10);	 //Catch:{ Exception -> 0x0085 }
        r13 = ";boundary=";
        r2 = r2.append(r13);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r7);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0085 }
        r12.setRequestProperty(r1, r2);	 //Catch:{ Exception -> 0x0085 }
        r13 = new java.lang.StringBuffer;	 //Catch:{ Exception -> 0x0085 }
        r1 = "";
        r13.<init>(r1);	 //Catch:{ Exception -> 0x0085 }
        r14 = new org.json.JSONObject;	 //Catch:{ Exception -> 0x0085 }
        r14.<init>();	 //Catch:{ Exception -> 0x0085 }
        r1 = r18.entrySet();	 //Catch:{ Exception -> 0x0085 }
        r15 = r1.iterator();	 //Catch:{ Exception -> 0x0085 }
    L_0x006b:
        r1 = r15.hasNext();	 //Catch:{ Exception -> 0x0085 }
        if (r1 == 0) goto L_0x009c;
    L_0x0071:
        r1 = r15.next();	 //Catch:{ Exception -> 0x0085 }
        r1 = (java.util.Map.Entry) r1;	 //Catch:{ Exception -> 0x0085 }
        r2 = r1.getKey();	 //Catch:{ Exception -> 0x0085 }
        r2 = (java.lang.String) r2;	 //Catch:{ Exception -> 0x0085 }
        r1 = r1.getValue();	 //Catch:{ Exception -> 0x0085 }
        r14.put(r2, r1);	 //Catch:{ Exception -> 0x0085 }
        goto L_0x006b;
    L_0x0085:
        r1 = move-exception;
        r2 = r4;
    L_0x0087:
        r4 = new qsbk.app.exception.QiushibaikeException;	 //Catch:{ Exception -> 0x008f }
        r12 = "\u7f51\u7edc\u4e0d\u5b58\u5728\u54e6\u4eb2!";
        r4.<init>(r12, r1, r2);	 //Catch:{ Exception -> 0x008f }
        throw r4;	 //Catch:{ Exception -> 0x008f }
    L_0x008f:
        r1 = move-exception;
        if (r5 != r6) goto L_0x01a8;
    L_0x0092:
        r3 = new qsbk.app.exception.QiushibaikeException;
        r4 = r1.getMessage();
        r3.<init>(r4, r1, r2);
        throw r3;
    L_0x009c:
        r1 = r14.toString();	 //Catch:{ Exception -> 0x0085 }
        r13.append(r8);	 //Catch:{ Exception -> 0x0085 }
        r13.append(r7);	 //Catch:{ Exception -> 0x0085 }
        r13.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r2.<init>();	 //Catch:{ Exception -> 0x0085 }
        r14 = "Content-Disposition: form-data; name=json";
        r2 = r2.append(r14);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0085 }
        r13.append(r2);	 //Catch:{ Exception -> 0x0085 }
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r2.<init>();	 //Catch:{ Exception -> 0x0085 }
        r14 = "Content-Type: text/plain; charset=";
        r2 = r2.append(r14);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r11);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0085 }
        r13.append(r2);	 //Catch:{ Exception -> 0x0085 }
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r2.<init>();	 //Catch:{ Exception -> 0x0085 }
        r14 = "Content-Transfer-Encoding: 8bit";
        r2 = r2.append(r14);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0085 }
        r13.append(r2);	 //Catch:{ Exception -> 0x0085 }
        r13.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r13.append(r1);	 //Catch:{ Exception -> 0x0085 }
        r13.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r1 = r12.getOutputStream();	 //Catch:{ Exception -> 0x0085 }
        r2 = r13.toString();	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.getBytes();	 //Catch:{ Exception -> 0x0085 }
        r1.write(r2);	 //Catch:{ Exception -> 0x0085 }
        if (r19 == 0) goto L_0x0176;
    L_0x0109:
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r2.<init>();	 //Catch:{ Exception -> 0x0085 }
        r2.append(r8);	 //Catch:{ Exception -> 0x0085 }
        r2.append(r7);	 //Catch:{ Exception -> 0x0085 }
        r2.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r13 = "sendpic.jpg";
        r14 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r14.<init>();	 //Catch:{ Exception -> 0x0085 }
        r15 = "Content-Disposition: form-data; name=\"image\";filename=\"";
        r14 = r14.append(r15);	 //Catch:{ Exception -> 0x0085 }
        r13 = r14.append(r13);	 //Catch:{ Exception -> 0x0085 }
        r14 = "\"";
        r13 = r13.append(r14);	 //Catch:{ Exception -> 0x0085 }
        r13 = r13.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r13 = r13.toString();	 //Catch:{ Exception -> 0x0085 }
        r2.append(r13);	 //Catch:{ Exception -> 0x0085 }
        r13 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r13.<init>();	 //Catch:{ Exception -> 0x0085 }
        r14 = "Content-Type: text/plain; charset=";
        r13 = r13.append(r14);	 //Catch:{ Exception -> 0x0085 }
        r13 = r13.append(r11);	 //Catch:{ Exception -> 0x0085 }
        r13 = r13.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r13 = r13.toString();	 //Catch:{ Exception -> 0x0085 }
        r2.append(r13);	 //Catch:{ Exception -> 0x0085 }
        r2.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.getBytes();	 //Catch:{ Exception -> 0x0085 }
        r1.write(r2);	 //Catch:{ Exception -> 0x0085 }
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];	 //Catch:{ Exception -> 0x0085 }
    L_0x0165:
        r0 = r19;
        r13 = r0.read(r2);	 //Catch:{ Exception -> 0x0085 }
        r14 = -1;
        if (r13 == r14) goto L_0x0173;
    L_0x016e:
        r14 = 0;
        r1.write(r2, r14, r13);	 //Catch:{ Exception -> 0x0085 }
        goto L_0x0165;
    L_0x0173:
        r19.close();	 //Catch:{ Exception -> 0x0085 }
    L_0x0176:
        r2 = r9.getBytes();	 //Catch:{ Exception -> 0x0085 }
        r1.write(r2);	 //Catch:{ Exception -> 0x0085 }
        r2 = new java.lang.StringBuilder;	 //Catch:{ Exception -> 0x0085 }
        r2.<init>();	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r8);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r7);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r8);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.append(r9);	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.toString();	 //Catch:{ Exception -> 0x0085 }
        r2 = r2.getBytes();	 //Catch:{ Exception -> 0x0085 }
        r1.write(r2);	 //Catch:{ Exception -> 0x0085 }
        r1.flush();	 //Catch:{ Exception -> 0x0085 }
        r2 = r12.getResponseCode();	 //Catch:{ Exception -> 0x0085 }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r1) goto L_0x01ad;
    L_0x01a8:
        r1 = r5 + 1;
        r5 = r1;
        goto L_0x0015;
    L_0x01ad:
        r4 = r12.getInputStream();	 //Catch:{ Exception -> 0x01c9 }
        if (r4 == 0) goto L_0x01cc;
    L_0x01b3:
        r1 = "gzip";
        r12 = r12.getContentEncoding();	 //Catch:{ Exception -> 0x01c9 }
        r1 = r1.equals(r12);	 //Catch:{ Exception -> 0x01c9 }
        if (r1 == 0) goto L_0x01cc;
    L_0x01bf:
        r1 = new java.util.zip.GZIPInputStream;	 //Catch:{ Exception -> 0x01c9 }
        r1.<init>(r4);	 //Catch:{ Exception -> 0x01c9 }
    L_0x01c4:
        r1 = readStream(r1);	 //Catch:{ Exception -> 0x01c9 }
    L_0x01c8:
        return r1;
    L_0x01c9:
        r1 = move-exception;
        goto L_0x0087;
    L_0x01cc:
        r1 = r4;
        goto L_0x01c4;
    L_0x01ce:
        r1 = r3;
        goto L_0x01c8;
        */

    }

    public native String verifyStringEncode(String r1_String, String r2_String);
}