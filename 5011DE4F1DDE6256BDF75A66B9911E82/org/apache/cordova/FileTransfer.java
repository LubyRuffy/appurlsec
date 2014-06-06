package org.apache.cordova;

import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.EditInfoEntranceActivity.REQUEST_CODE;
import qsbk.app.bean.Base;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.Base64;
import qsbk.app.widget.listview.XListViewFooter;
import qsbk.app.widget.listview.XListViewHeader;

public class FileTransfer extends CordovaPlugin {
    public static int ABORTED_ERR = 0;
    private static final String BOUNDARY = "+++++";
    public static int CONNECTION_ERR = 0;
    private static final HostnameVerifier DO_NOT_VERIFY;
    public static int FILE_NOT_FOUND_ERR = 0;
    public static int INVALID_URL_ERR = 0;
    private static final String LINE_END = "\r\n";
    private static final String LINE_START = "--";
    private static final String LOG_TAG = "FileTransfer";
    private static final int MAX_BUFFER_SIZE = 16384;
    private static HashMap<String, RequestContext> activeRequests;
    private static final TrustManager[] trustAllCerts;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ boolean val$chunkedMode;
        final /* synthetic */ RequestContext val$context;
        final /* synthetic */ String val$fileKey;
        final /* synthetic */ String val$fileName;
        final /* synthetic */ JSONObject val$headers;
        final /* synthetic */ String val$mimeType;
        final /* synthetic */ String val$objectId;
        final /* synthetic */ JSONObject val$params;
        final /* synthetic */ String val$source;
        final /* synthetic */ String val$target;
        final /* synthetic */ boolean val$trustEveryone;
        final /* synthetic */ URL val$url;
        final /* synthetic */ boolean val$useHttps;

        AnonymousClass_1(RequestContext r2_RequestContext, boolean r3z, boolean r4z, URL r5_URL, String r6_String, JSONObject r7_JSONObject, JSONObject r8_JSONObject, String r9_String, String r10_String, String r11_String, String r12_String, boolean r13z, String r14_String) {
            this.val$context = r2_RequestContext;
            this.val$useHttps = r3z;
            this.val$trustEveryone = r4z;
            this.val$url = r5_URL;
            this.val$target = r6_String;
            this.val$headers = r7_JSONObject;
            this.val$params = r8_JSONObject;
            this.val$fileKey = r9_String;
            this.val$fileName = r10_String;
            this.val$mimeType = r11_String;
            this.val$source = r12_String;
            this.val$chunkedMode = r13z;
            this.val$objectId = r14_String;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r21_this = this;
            r0 = r21;
            r2 = r0.val$context;
            r2 = r2.aborted;
            if (r2 == 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r5 = 0;
            r4 = 0;
            r3 = 0;
            r6 = 0;
            r9 = -1;
            r12 = new org.apache.cordova.FileUploadResult;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r12.<init>();	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r13 = new org.apache.cordova.FileProgressResult;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r13.<init>();	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r0 = r21;
            r2 = r0.val$useHttps;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            if (r2 == 0) goto L_0x00ca;
        L_0x001e:
            r0 = r21;
            r2 = r0.val$trustEveryone;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            if (r2 != 0) goto L_0x00ac;
        L_0x0024:
            r0 = r21;
            r2 = r0.val$url;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r2 = r2.openConnection();	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r2 = (javax.net.ssl.HttpsURLConnection) r2;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r7 = r3;
            r8 = r4;
            r4 = r2;
        L_0x0031:
            r2 = 1;
            r4.setDoInput(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = 1;
            r4.setDoOutput(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = 0;
            r4.setUseCaches(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = "POST";
            r4.setRequestMethod(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = "Content-Type";
            r3 = "multipart/form-data;boundary=+++++";
            r4.setRequestProperty(r2, r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = android.webkit.CookieManager.getInstance();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r3 = r0.val$target;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r2.getCookie(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 == 0) goto L_0x005c;
        L_0x0057:
            r3 = "Cookie";
            r4.setRequestProperty(r3, r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
        L_0x005c:
            r0 = r21;
            r2 = r0.val$headers;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 == 0) goto L_0x00da;
        L_0x0062:
            r0 = r21;
            r2 = r0.val$headers;	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r2.keys();	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
        L_0x006a:
            r2 = r5.hasNext();	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 == 0) goto L_0x00da;
        L_0x0070:
            r2 = r5.next();	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r10 = r2.toString();	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r2 = r0.val$headers;	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r2.optJSONArray(r10);	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 != 0) goto L_0x0694;
        L_0x0082:
            r2 = new org.json.JSONArray;	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r2.<init>();	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r3 = r0.val$headers;	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r3 = r3.getString(r10);	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r2.put(r3);	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r3 = r2;
        L_0x0093:
            r2 = 0;
            r2 = r3.getString(r2);	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r4.setRequestProperty(r10, r2);	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r2 = 1;
        L_0x009c:
            r11 = r3.length();	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 >= r11) goto L_0x006a;
        L_0x00a2:
            r11 = r3.getString(r2);	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r4.addRequestProperty(r10, r11);	 //Catch:{ JSONException -> 0x00d9, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r2 + 1;
            goto L_0x009c;
        L_0x00ac:
            r0 = r21;
            r2 = r0.val$url;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r2 = r2.openConnection();	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r2 = (javax.net.ssl.HttpsURLConnection) r2;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r3 = org.apache.cordova.FileTransfer.trustAllHosts(r2);	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r4 = r2.getHostnameVerifier();	 //Catch:{ FileNotFoundException -> 0x0680, IOException -> 0x0660, JSONException -> 0x064a, Throwable -> 0x0630, all -> 0x061a }
            r7 = DO_NOT_VERIFY;	 //Catch:{ FileNotFoundException -> 0x068a, IOException -> 0x0668, JSONException -> 0x0651, Throwable -> 0x0637, all -> 0x0620 }
            r2.setHostnameVerifier(r7);	 //Catch:{ FileNotFoundException -> 0x068a, IOException -> 0x0668, JSONException -> 0x0651, Throwable -> 0x0637, all -> 0x0620 }
            r7 = r3;
            r8 = r4;
            r4 = r2;
            goto L_0x0031;
        L_0x00ca:
            r0 = r21;
            r2 = r0.val$url;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r2 = r2.openConnection();	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r2 = (java.net.HttpURLConnection) r2;	 //Catch:{ FileNotFoundException -> 0x0676, IOException -> 0x0658, JSONException -> 0x0643, Throwable -> 0x0594, all -> 0x05ea }
            r7 = r3;
            r8 = r4;
            r4 = r2;
            goto L_0x0031;
        L_0x00d9:
            r2 = move-exception;
        L_0x00da:
            r3 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3.<init>();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r2 = r0.val$params;	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r2.keys();	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
        L_0x00e7:
            r5 = r2.hasNext();	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            if (r5 == 0) goto L_0x014b;
        L_0x00ed:
            r5 = r2.next();	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r10 = java.lang.String.valueOf(r5);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r11 = "headers";
            r10 = r10.equals(r11);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            if (r10 != 0) goto L_0x00e7;
        L_0x00fd:
            r10 = "--";
            r10 = r3.append(r10);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r11 = "+++++";
            r10 = r10.append(r11);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r11 = "\r\n";
            r10.append(r11);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r10 = "Content-Disposition: form-data; name=\"";
            r10 = r3.append(r10);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r11 = r5.toString();	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r10 = r10.append(r11);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r11 = 34;
            r10.append(r11);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r10 = "\r\n";
            r10 = r3.append(r10);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r11 = "\r\n";
            r10.append(r11);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r10 = r0.val$params;	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5.toString();	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r10.getString(r5);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r3.append(r5);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "\r\n";
            r3.append(r5);	 //Catch:{ JSONException -> 0x0141, FileNotFoundException -> 0x026b, IOException -> 0x0370, Throwable -> 0x063e, all -> 0x0626 }
            goto L_0x00e7;
        L_0x0141:
            r2 = move-exception;
            r5 = "FileTransfer";
            r10 = r2.getMessage();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            android.util.Log.e(r5, r10, r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
        L_0x014b:
            r2 = "--";
            r2 = r3.append(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "+++++";
            r2 = r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "\r\n";
            r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = "Content-Disposition: form-data; name=\"";
            r2 = r3.append(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r5 = r0.val$fileKey;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "\";";
            r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = " filename=\"";
            r2 = r3.append(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r5 = r0.val$fileName;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = 34;
            r2 = r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "\r\n";
            r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = "Content-Type: ";
            r2 = r3.append(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r5 = r0.val$mimeType;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "\r\n";
            r2 = r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "\r\n";
            r2.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r3.toString();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = "UTF-8";
            r10 = r2.getBytes(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = "\r\n--+++++--\r\n";
            r3 = "UTF-8";
            r14 = r2.getBytes(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r2 = org.apache.cordova.FileTransfer.this;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r3 = r0.val$source;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = r2.getPathFromUri(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r10.length;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r14.length;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5 + r2;
            r2 = r3 instanceof java.io.FileInputStream;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 == 0) goto L_0x01de;
        L_0x01c6:
            r0 = r3;
            r0 = (java.io.FileInputStream) r0;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r0;
            r2 = r2.getChannel();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r15 = r2.size();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = (int) r15;
            r5 = r5 + r2;
            r2 = 1;
            r13.setLengthComputable(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0670, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r15 = (long) r5;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0670, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r15;
            r13.setTotal(r0);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0670, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r9 = r5;
        L_0x01de:
            r2 = "FileTransfer";
            r5 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5.<init>();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r11 = "Content Length: ";
            r5 = r5.append(r11);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5.append(r9);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5.toString();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            android.util.Log.d(r2, r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r2 = r0.val$chunkedMode;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 == 0) goto L_0x0263;
        L_0x01fc:
            r2 = android.os.Build.VERSION.SDK_INT;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = 8;
            if (r2 < r5) goto L_0x0208;
        L_0x0202:
            r0 = r21;
            r2 = r0.val$useHttps;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            if (r2 == 0) goto L_0x0263;
        L_0x0208:
            r2 = 1;
        L_0x0209:
            if (r2 != 0) goto L_0x020e;
        L_0x020b:
            r2 = -1;
            if (r9 != r2) goto L_0x0265;
        L_0x020e:
            r2 = 1;
        L_0x020f:
            if (r2 == 0) goto L_0x0267;
        L_0x0211:
            r2 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
            r4.setChunkedStreamingMode(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = "Transfer-Encoding";
            r5 = "chunked";
            r4.setRequestProperty(r2, r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
        L_0x021d:
            r4.connect();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = 0;
            r5 = r4.getOutputStream();	 //Catch:{ all -> 0x0368 }
            r0 = r21;
            r11 = r0.val$context;	 //Catch:{ all -> 0x0368 }
            monitor-enter(r11);	 //Catch:{ all -> 0x0368 }
            r0 = r21;
            r2 = r0.val$context;	 //Catch:{ all -> 0x03ea }
            r2 = r2.aborted;	 //Catch:{ all -> 0x03ea }
            if (r2 == 0) goto L_0x02c1;
        L_0x0232:
            monitor-exit(r11);	 //Catch:{ all -> 0x03ea }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            org.apache.cordova.FileTransfer.safeClose(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = activeRequests;
            monitor-enter(r3);
            r2 = activeRequests;	 //Catch:{ all -> 0x02be }
            r0 = r21;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x02be }
            r2.remove(r5);	 //Catch:{ all -> 0x02be }
            monitor-exit(r3);	 //Catch:{ all -> 0x02be }
            if (r4 == 0) goto L_0x0008;
        L_0x024c:
            r0 = r21;
            r2 = r0.val$trustEveryone;
            if (r2 == 0) goto L_0x0008;
        L_0x0252:
            r0 = r21;
            r2 = r0.val$useHttps;
            if (r2 == 0) goto L_0x0008;
        L_0x0258:
            r2 = r4;
            r2 = (javax.net.ssl.HttpsURLConnection) r2;
            r2.setHostnameVerifier(r8);
            r2.setSSLSocketFactory(r7);
            goto L_0x0008;
        L_0x0263:
            r2 = 0;
            goto L_0x0209;
        L_0x0265:
            r2 = 0;
            goto L_0x020f;
        L_0x0267:
            r4.setFixedLengthStreamingMode(r9);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            goto L_0x021d;
        L_0x026b:
            r2 = move-exception;
            r3 = r2;
            r5 = r8;
            r2 = r4;
            r4 = r7;
        L_0x0270:
            r6 = FILE_NOT_FOUND_ERR;	 //Catch:{ all -> 0x062a }
            r0 = r21;
            r7 = r0.val$source;	 //Catch:{ all -> 0x062a }
            r0 = r21;
            r8 = r0.val$target;	 //Catch:{ all -> 0x062a }
            r6 = org.apache.cordova.FileTransfer.createFileTransferError(r6, r7, r8, r2);	 //Catch:{ all -> 0x062a }
            r7 = "FileTransfer";
            r8 = r6.toString();	 //Catch:{ all -> 0x062a }
            android.util.Log.e(r7, r8, r3);	 //Catch:{ all -> 0x062a }
            r0 = r21;
            r3 = r0.val$context;	 //Catch:{ all -> 0x062a }
            r7 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x062a }
            r8 = org.apache.cordova.api.PluginResult.Status.IO_EXCEPTION;	 //Catch:{ all -> 0x062a }
            r7.<init>(r8, r6);	 //Catch:{ all -> 0x062a }
            r3.sendPluginResult(r7);	 //Catch:{ all -> 0x062a }
            r3 = activeRequests;
            monitor-enter(r3);
            r6 = activeRequests;	 //Catch:{ all -> 0x058b }
            r0 = r21;
            r7 = r0.val$objectId;	 //Catch:{ all -> 0x058b }
            r6.remove(r7);	 //Catch:{ all -> 0x058b }
            monitor-exit(r3);	 //Catch:{ all -> 0x058b }
            if (r2 == 0) goto L_0x0008;
        L_0x02a8:
            r0 = r21;
            r3 = r0.val$trustEveryone;
            if (r3 == 0) goto L_0x0008;
        L_0x02ae:
            r0 = r21;
            r3 = r0.val$useHttps;
            if (r3 == 0) goto L_0x0008;
        L_0x02b4:
            r2 = (javax.net.ssl.HttpsURLConnection) r2;
            r2.setHostnameVerifier(r5);
            r2.setSSLSocketFactory(r4);
            goto L_0x0008;
        L_0x02be:
            r2 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x02be }
            throw r2;
        L_0x02c1:
            r0 = r21;
            r2 = r0.val$context;	 //Catch:{ all -> 0x03ea }
            r2.currentOutputStream = r5;	 //Catch:{ all -> 0x03ea }
            monitor-exit(r11);	 //Catch:{ all -> 0x03ea }
            r5.write(r10);	 //Catch:{ all -> 0x0368 }
            r2 = r10.length;	 //Catch:{ all -> 0x0368 }
            r6 = r6 + r2;
            r2 = r3.available();	 //Catch:{ all -> 0x0368 }
            r10 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
            r2 = java.lang.Math.min(r2, r10);	 //Catch:{ all -> 0x0368 }
            r15 = new byte[r2];	 //Catch:{ all -> 0x0368 }
            r10 = 0;
            r2 = r3.read(r15, r10, r2);	 //Catch:{ all -> 0x0368 }
            r10 = 0;
        L_0x02e0:
            if (r2 <= 0) goto L_0x03ed;
        L_0x02e2:
            r0 = (long) r6;	 //Catch:{ all -> 0x0368 }
            r16 = r0;
            r0 = r16;
            r12.setBytesSent(r0);	 //Catch:{ all -> 0x0368 }
            r16 = 0;
            r0 = r16;
            r5.write(r15, r0, r2);	 //Catch:{ all -> 0x0368 }
            r6 = r6 + r2;
            r0 = (long) r6;	 //Catch:{ all -> 0x0368 }
            r16 = r0;
            r18 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
            r18 = r18 + r10;
            r2 = (r16 > r18 ? 1 : (r16 == r18? 0 : -1));
            if (r2 <= 0) goto L_0x032d;
        L_0x02fe:
            r10 = (long) r6;	 //Catch:{ all -> 0x0368 }
            r2 = "FileTransfer";
            r16 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x0368 }
            r16.<init>();	 //Catch:{ all -> 0x0368 }
            r17 = "Uploaded ";
            r16 = r16.append(r17);	 //Catch:{ all -> 0x0368 }
            r0 = r16;
            r16 = r0.append(r6);	 //Catch:{ all -> 0x0368 }
            r17 = " of ";
            r16 = r16.append(r17);	 //Catch:{ all -> 0x0368 }
            r0 = r16;
            r16 = r0.append(r9);	 //Catch:{ all -> 0x0368 }
            r17 = " bytes";
            r16 = r16.append(r17);	 //Catch:{ all -> 0x0368 }
            r16 = r16.toString();	 //Catch:{ all -> 0x0368 }
            r0 = r16;
            android.util.Log.d(r2, r0);	 //Catch:{ all -> 0x0368 }
        L_0x032d:
            r2 = r3.available();	 //Catch:{ all -> 0x0368 }
            r16 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
            r0 = r16;
            r2 = java.lang.Math.min(r2, r0);	 //Catch:{ all -> 0x0368 }
            r16 = 0;
            r0 = r16;
            r2 = r3.read(r15, r0, r2);	 //Catch:{ all -> 0x0368 }
            r0 = (long) r6;	 //Catch:{ all -> 0x0368 }
            r16 = r0;
            r0 = r16;
            r13.setLoaded(r0);	 //Catch:{ all -> 0x0368 }
            r16 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x0368 }
            r17 = org.apache.cordova.api.PluginResult.Status.OK;	 //Catch:{ all -> 0x0368 }
            r18 = r13.toJSONObject();	 //Catch:{ all -> 0x0368 }
            r16.<init>(r17, r18);	 //Catch:{ all -> 0x0368 }
            r17 = 1;
            r16.setKeepCallback(r17);	 //Catch:{ all -> 0x0368 }
            r0 = r21;
            r0 = r0.val$context;	 //Catch:{ all -> 0x0368 }
            r17 = r0;
            r0 = r17;
            r1 = r16;
            r0.sendPluginResult(r1);	 //Catch:{ all -> 0x0368 }
            goto L_0x02e0;
        L_0x0368:
            r2 = move-exception;
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            org.apache.cordova.FileTransfer.safeClose(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            throw r2;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
        L_0x0370:
            r2 = move-exception;
            r3 = r2;
            r2 = r4;
            r4 = r9;
        L_0x0374:
            r5 = CONNECTION_ERR;	 //Catch:{ all -> 0x062e }
            r0 = r21;
            r9 = r0.val$source;	 //Catch:{ all -> 0x062e }
            r0 = r21;
            r10 = r0.val$target;	 //Catch:{ all -> 0x062e }
            r5 = org.apache.cordova.FileTransfer.createFileTransferError(r5, r9, r10, r2);	 //Catch:{ all -> 0x062e }
            r9 = "FileTransfer";
            r10 = r5.toString();	 //Catch:{ all -> 0x062e }
            android.util.Log.e(r9, r10, r3);	 //Catch:{ all -> 0x062e }
            r3 = "FileTransfer";
            r9 = new java.lang.StringBuilder;	 //Catch:{ all -> 0x062e }
            r9.<init>();	 //Catch:{ all -> 0x062e }
            r10 = "Failed after uploading ";
            r9 = r9.append(r10);	 //Catch:{ all -> 0x062e }
            r6 = r9.append(r6);	 //Catch:{ all -> 0x062e }
            r9 = " of ";
            r6 = r6.append(r9);	 //Catch:{ all -> 0x062e }
            r4 = r6.append(r4);	 //Catch:{ all -> 0x062e }
            r6 = " bytes.";
            r4 = r4.append(r6);	 //Catch:{ all -> 0x062e }
            r4 = r4.toString();	 //Catch:{ all -> 0x062e }
            android.util.Log.e(r3, r4);	 //Catch:{ all -> 0x062e }
            r0 = r21;
            r3 = r0.val$context;	 //Catch:{ all -> 0x062e }
            r4 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x062e }
            r6 = org.apache.cordova.api.PluginResult.Status.IO_EXCEPTION;	 //Catch:{ all -> 0x062e }
            r4.<init>(r6, r5);	 //Catch:{ all -> 0x062e }
            r3.sendPluginResult(r4);	 //Catch:{ all -> 0x062e }
            r3 = activeRequests;
            monitor-enter(r3);
            r4 = activeRequests;	 //Catch:{ all -> 0x058e }
            r0 = r21;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x058e }
            r4.remove(r5);	 //Catch:{ all -> 0x058e }
            monitor-exit(r3);	 //Catch:{ all -> 0x058e }
            if (r2 == 0) goto L_0x0008;
        L_0x03d4:
            r0 = r21;
            r3 = r0.val$trustEveryone;
            if (r3 == 0) goto L_0x0008;
        L_0x03da:
            r0 = r21;
            r3 = r0.val$useHttps;
            if (r3 == 0) goto L_0x0008;
        L_0x03e0:
            r2 = (javax.net.ssl.HttpsURLConnection) r2;
            r2.setHostnameVerifier(r8);
            r2.setSSLSocketFactory(r7);
            goto L_0x0008;
        L_0x03ea:
            r2 = move-exception;
            monitor-exit(r11);	 //Catch:{ all -> 0x03ea }
            throw r2;	 //Catch:{ all -> 0x0368 }
        L_0x03ed:
            r5.write(r14);	 //Catch:{ all -> 0x0368 }
            r2 = r14.length;	 //Catch:{ all -> 0x0368 }
            r6 = r6 + r2;
            r5.flush();	 //Catch:{ all -> 0x0368 }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            org.apache.cordova.FileTransfer.safeClose(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r2 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = 0;
            r2.currentOutputStream = r3;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = "FileTransfer";
            r3 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3.<init>();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = "Sent ";
            r3 = r3.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = r3.append(r6);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = " of ";
            r3 = r3.append(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = r3.append(r9);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = r3.toString();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            android.util.Log.d(r2, r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2 = r4.getResponseCode();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = "FileTransfer";
            r5 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5.<init>();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r10 = "response code: ";
            r5 = r5.append(r10);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5.append(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5.toString();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            android.util.Log.d(r3, r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = "FileTransfer";
            r5 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5.<init>();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r10 = "response headers: ";
            r5 = r5.append(r10);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r10 = r4.getHeaderFields();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5.append(r10);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = r5.toString();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            android.util.Log.d(r3, r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = 0;
            r3 = org.apache.cordova.FileTransfer.getInputStream(r4);	 //Catch:{ all -> 0x04ca }
            r0 = r21;
            r5 = r0.val$context;	 //Catch:{ all -> 0x04ca }
            monitor-enter(r5);	 //Catch:{ all -> 0x04ca }
            r0 = r21;
            r10 = r0.val$context;	 //Catch:{ all -> 0x0519 }
            r10 = r10.aborted;	 //Catch:{ all -> 0x0519 }
            if (r10 == 0) goto L_0x04a5;
        L_0x046e:
            monitor-exit(r5);	 //Catch:{ all -> 0x0519 }
            r0 = r21;
            r2 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = 0;
            r2.currentInputStream = r5;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = activeRequests;
            monitor-enter(r3);
            r2 = activeRequests;	 //Catch:{ all -> 0x04a2 }
            r0 = r21;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x04a2 }
            r2.remove(r5);	 //Catch:{ all -> 0x04a2 }
            monitor-exit(r3);	 //Catch:{ all -> 0x04a2 }
            if (r4 == 0) goto L_0x0008;
        L_0x048c:
            r0 = r21;
            r2 = r0.val$trustEveryone;
            if (r2 == 0) goto L_0x0008;
        L_0x0492:
            r0 = r21;
            r2 = r0.val$useHttps;
            if (r2 == 0) goto L_0x0008;
        L_0x0498:
            r4 = (javax.net.ssl.HttpsURLConnection) r4;
            r4.setHostnameVerifier(r8);
            r4.setSSLSocketFactory(r7);
            goto L_0x0008;
        L_0x04a2:
            r2 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x04a2 }
            throw r2;
        L_0x04a5:
            r0 = r21;
            r10 = r0.val$context;	 //Catch:{ all -> 0x0519 }
            r10.currentInputStream = r3;	 //Catch:{ all -> 0x0519 }
            monitor-exit(r5);	 //Catch:{ all -> 0x0519 }
            r5 = new java.io.ByteArrayOutputStream;	 //Catch:{ all -> 0x04ca }
            r10 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
            r11 = r4.getContentLength();	 //Catch:{ all -> 0x04ca }
            r10 = java.lang.Math.max(r10, r11);	 //Catch:{ all -> 0x04ca }
            r5.<init>(r10);	 //Catch:{ all -> 0x04ca }
            r10 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
            r10 = new byte[r10];	 //Catch:{ all -> 0x04ca }
        L_0x04bf:
            r11 = r3.read(r10);	 //Catch:{ all -> 0x04ca }
            if (r11 <= 0) goto L_0x051c;
        L_0x04c5:
            r13 = 0;
            r5.write(r10, r13, r11);	 //Catch:{ all -> 0x04ca }
            goto L_0x04bf;
        L_0x04ca:
            r2 = move-exception;
            r0 = r21;
            r5 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r10 = 0;
            r5.currentInputStream = r10;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            throw r2;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
        L_0x04d6:
            r2 = move-exception;
            r3 = r2;
            r2 = r4;
        L_0x04d9:
            r4 = "FileTransfer";
            r5 = r3.getMessage();	 //Catch:{ all -> 0x062e }
            android.util.Log.e(r4, r5, r3);	 //Catch:{ all -> 0x062e }
            r0 = r21;
            r3 = r0.val$context;	 //Catch:{ all -> 0x062e }
            r4 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x062e }
            r5 = org.apache.cordova.api.PluginResult.Status.JSON_EXCEPTION;	 //Catch:{ all -> 0x062e }
            r4.<init>(r5);	 //Catch:{ all -> 0x062e }
            r3.sendPluginResult(r4);	 //Catch:{ all -> 0x062e }
            r3 = activeRequests;
            monitor-enter(r3);
            r4 = activeRequests;	 //Catch:{ all -> 0x0591 }
            r0 = r21;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x0591 }
            r4.remove(r5);	 //Catch:{ all -> 0x0591 }
            monitor-exit(r3);	 //Catch:{ all -> 0x0591 }
            if (r2 == 0) goto L_0x0008;
        L_0x0503:
            r0 = r21;
            r3 = r0.val$trustEveryone;
            if (r3 == 0) goto L_0x0008;
        L_0x0509:
            r0 = r21;
            r3 = r0.val$useHttps;
            if (r3 == 0) goto L_0x0008;
        L_0x050f:
            r2 = (javax.net.ssl.HttpsURLConnection) r2;
            r2.setHostnameVerifier(r8);
            r2.setSSLSocketFactory(r7);
            goto L_0x0008;
        L_0x0519:
            r2 = move-exception;
            monitor-exit(r5);	 //Catch:{ all -> 0x0519 }
            throw r2;	 //Catch:{ all -> 0x04ca }
        L_0x051c:
            r10 = "UTF-8";
            r5 = r5.toString(r10);	 //Catch:{ all -> 0x04ca }
            r0 = r21;
            r10 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r11 = 0;
            r10.currentInputStream = r11;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = "FileTransfer";
            r10 = "got response from server";
            android.util.Log.d(r3, r10);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = "FileTransfer";
            r10 = 0;
            r11 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
            r13 = r5.length();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r11 = java.lang.Math.min(r11, r13);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r10 = r5.substring(r10, r11);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            android.util.Log.d(r3, r10);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r12.setResponseCode(r2);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r12.setResponse(r5);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r0 = r21;
            r2 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = new org.apache.cordova.api.PluginResult;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r5 = org.apache.cordova.api.PluginResult.Status.OK;	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r10 = r12.toJSONObject();	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3.<init>(r5, r10);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r2.sendPluginResult(r3);	 //Catch:{ FileNotFoundException -> 0x026b, IOException -> 0x0370, JSONException -> 0x04d6, Throwable -> 0x063e, all -> 0x0626 }
            r3 = activeRequests;
            monitor-enter(r3);
            r2 = activeRequests;	 //Catch:{ all -> 0x0588 }
            r0 = r21;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x0588 }
            r2.remove(r5);	 //Catch:{ all -> 0x0588 }
            monitor-exit(r3);	 //Catch:{ all -> 0x0588 }
            if (r4 == 0) goto L_0x0008;
        L_0x0572:
            r0 = r21;
            r2 = r0.val$trustEveryone;
            if (r2 == 0) goto L_0x0008;
        L_0x0578:
            r0 = r21;
            r2 = r0.val$useHttps;
            if (r2 == 0) goto L_0x0008;
        L_0x057e:
            r4 = (javax.net.ssl.HttpsURLConnection) r4;
            r4.setHostnameVerifier(r8);
            r4.setSSLSocketFactory(r7);
            goto L_0x0008;
        L_0x0588:
            r2 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x0588 }
            throw r2;
        L_0x058b:
            r2 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x058b }
            throw r2;
        L_0x058e:
            r2 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x058e }
            throw r2;
        L_0x0591:
            r2 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x0591 }
            throw r2;
        L_0x0594:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
        L_0x0599:
            r4 = CONNECTION_ERR;	 //Catch:{ all -> 0x062e }
            r0 = r21;
            r5 = r0.val$source;	 //Catch:{ all -> 0x062e }
            r0 = r21;
            r6 = r0.val$target;	 //Catch:{ all -> 0x062e }
            r4 = org.apache.cordova.FileTransfer.createFileTransferError(r4, r5, r6, r2);	 //Catch:{ all -> 0x062e }
            r5 = "FileTransfer";
            r6 = r4.toString();	 //Catch:{ all -> 0x062e }
            android.util.Log.e(r5, r6, r3);	 //Catch:{ all -> 0x062e }
            r0 = r21;
            r3 = r0.val$context;	 //Catch:{ all -> 0x062e }
            r5 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x062e }
            r6 = org.apache.cordova.api.PluginResult.Status.IO_EXCEPTION;	 //Catch:{ all -> 0x062e }
            r5.<init>(r6, r4);	 //Catch:{ all -> 0x062e }
            r3.sendPluginResult(r5);	 //Catch:{ all -> 0x062e }
            r3 = activeRequests;
            monitor-enter(r3);
            r4 = activeRequests;	 //Catch:{ all -> 0x05e7 }
            r0 = r21;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x05e7 }
            r4.remove(r5);	 //Catch:{ all -> 0x05e7 }
            monitor-exit(r3);	 //Catch:{ all -> 0x05e7 }
            if (r2 == 0) goto L_0x0008;
        L_0x05d1:
            r0 = r21;
            r3 = r0.val$trustEveryone;
            if (r3 == 0) goto L_0x0008;
        L_0x05d7:
            r0 = r21;
            r3 = r0.val$useHttps;
            if (r3 == 0) goto L_0x0008;
        L_0x05dd:
            r2 = (javax.net.ssl.HttpsURLConnection) r2;
            r2.setHostnameVerifier(r8);
            r2.setSSLSocketFactory(r7);
            goto L_0x0008;
        L_0x05e7:
            r2 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x05e7 }
            throw r2;
        L_0x05ea:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
        L_0x05ef:
            r4 = activeRequests;
            monitor-enter(r4);
            r5 = activeRequests;	 //Catch:{ all -> 0x0617 }
            r0 = r21;
            r6 = r0.val$objectId;	 //Catch:{ all -> 0x0617 }
            r5.remove(r6);	 //Catch:{ all -> 0x0617 }
            monitor-exit(r4);	 //Catch:{ all -> 0x0617 }
            if (r2 == 0) goto L_0x0616;
        L_0x0602:
            r0 = r21;
            r4 = r0.val$trustEveryone;
            if (r4 == 0) goto L_0x0616;
        L_0x0608:
            r0 = r21;
            r4 = r0.val$useHttps;
            if (r4 == 0) goto L_0x0616;
        L_0x060e:
            r2 = (javax.net.ssl.HttpsURLConnection) r2;
            r2.setHostnameVerifier(r8);
            r2.setSSLSocketFactory(r7);
        L_0x0616:
            throw r3;
        L_0x0617:
            r2 = move-exception;
            monitor-exit(r4);	 //Catch:{ all -> 0x0617 }
            throw r2;
        L_0x061a:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
            goto L_0x05ef;
        L_0x0620:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
            goto L_0x05ef;
        L_0x0626:
            r2 = move-exception;
            r3 = r2;
            r2 = r4;
            goto L_0x05ef;
        L_0x062a:
            r3 = move-exception;
            r7 = r4;
            r8 = r5;
            goto L_0x05ef;
        L_0x062e:
            r3 = move-exception;
            goto L_0x05ef;
        L_0x0630:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
            goto L_0x0599;
        L_0x0637:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
            goto L_0x0599;
        L_0x063e:
            r2 = move-exception;
            r3 = r2;
            r2 = r4;
            goto L_0x0599;
        L_0x0643:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
            goto L_0x04d9;
        L_0x064a:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
            goto L_0x04d9;
        L_0x0651:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r2 = r5;
            goto L_0x04d9;
        L_0x0658:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r4 = r9;
            r2 = r5;
            goto L_0x0374;
        L_0x0660:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r4 = r9;
            r2 = r5;
            goto L_0x0374;
        L_0x0668:
            r2 = move-exception;
            r7 = r3;
            r8 = r4;
            r3 = r2;
            r4 = r9;
            r2 = r5;
            goto L_0x0374;
        L_0x0670:
            r2 = move-exception;
            r3 = r2;
            r2 = r4;
            r4 = r5;
            goto L_0x0374;
        L_0x0676:
            r2 = move-exception;
            r20 = r2;
            r2 = r5;
            r5 = r4;
            r4 = r3;
            r3 = r20;
            goto L_0x0270;
        L_0x0680:
            r2 = move-exception;
            r20 = r2;
            r2 = r5;
            r5 = r4;
            r4 = r3;
            r3 = r20;
            goto L_0x0270;
        L_0x068a:
            r2 = move-exception;
            r20 = r2;
            r2 = r5;
            r5 = r4;
            r4 = r3;
            r3 = r20;
            goto L_0x0270;
        L_0x0694:
            r3 = r2;
            goto L_0x0093;
            */

        }
    }

    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ RequestContext val$context;
        final /* synthetic */ String val$objectId;
        final /* synthetic */ String val$source;
        final /* synthetic */ String val$target;
        final /* synthetic */ boolean val$trustEveryone;
        final /* synthetic */ URL val$url;
        final /* synthetic */ boolean val$useHttps;

        AnonymousClass_4(RequestContext r2_RequestContext, String r3_String, boolean r4z, boolean r5z, URL r6_URL, String r7_String, String r8_String) {
            this.val$context = r2_RequestContext;
            this.val$target = r3_String;
            this.val$useHttps = r4z;
            this.val$trustEveryone = r5z;
            this.val$url = r6_URL;
            this.val$source = r7_String;
            this.val$objectId = r8_String;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r16_this = this;
            r4 = 0;
            r0 = r16;
            r1 = r0.val$context;
            r1 = r1.aborted;
            if (r1 == 0) goto L_0x000a;
        L_0x0009:
            return;
        L_0x000a:
            r0 = r16;
            r1 = org.apache.cordova.FileTransfer.this;	 //Catch:{ FileNotFoundException -> 0x04b5, IOException -> 0x0289, JSONException -> 0x0302, Throwable -> 0x036d, all -> 0x03e6 }
            r0 = r16;
            r2 = r0.val$target;	 //Catch:{ FileNotFoundException -> 0x04b5, IOException -> 0x0289, JSONException -> 0x0302, Throwable -> 0x036d, all -> 0x03e6 }
            r6 = r1.getFileFromPath(r2);	 //Catch:{ FileNotFoundException -> 0x04b5, IOException -> 0x0289, JSONException -> 0x0302, Throwable -> 0x036d, all -> 0x03e6 }
            r0 = r16;
            r1 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1.targetFile = r6;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1 = r6.getParentFile();	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1.mkdirs();	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r0 = r16;
            r1 = r0.val$useHttps;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            if (r1 == 0) goto L_0x012d;
        L_0x0029:
            r0 = r16;
            r1 = r0.val$trustEveryone;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            if (r1 != 0) goto L_0x010f;
        L_0x002f:
            r0 = r16;
            r1 = r0.val$url;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1 = r1.openConnection();	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1 = (javax.net.ssl.HttpsURLConnection) r1;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r7 = r4;
            r8 = r4;
            r2 = r1;
        L_0x003c:
            r1 = r2 instanceof java.net.HttpURLConnection;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            if (r1 == 0) goto L_0x0049;
        L_0x0040:
            r0 = r2;
            r0 = (java.net.HttpURLConnection) r0;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1 = r0;
            r3 = "GET";
            r1.setRequestMethod(r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
        L_0x0049:
            r1 = android.webkit.CookieManager.getInstance();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r0 = r16;
            r3 = r0.val$source;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1 = r1.getCookie(r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            if (r1 == 0) goto L_0x005c;
        L_0x0057:
            r3 = "cookie";
            r2.setRequestProperty(r3, r1);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
        L_0x005c:
            r2.connect();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1 = "FileTransfer";
            r3 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3.<init>();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r5 = "Download file:";
            r3 = r3.append(r5);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r0 = r16;
            r5 = r0.val$url;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = r3.append(r5);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = r3.toString();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            android.util.Log.d(r1, r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1 = new org.apache.cordova.FileProgressResult;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1.<init>();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = r2.getContentEncoding();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            if (r3 != 0) goto L_0x0092;
        L_0x0086:
            r3 = 1;
            r1.setLengthComputable(r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = r2.getContentLength();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r9 = (long) r3;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1.setTotal(r9);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
        L_0x0092:
            r3 = org.apache.cordova.FileTransfer.getInputStream(r2);	 //Catch:{ all -> 0x04d2 }
            r5 = new java.io.FileOutputStream;	 //Catch:{ all -> 0x04d7 }
            r5.<init>(r6);	 //Catch:{ all -> 0x04d7 }
            r0 = r16;
            r9 = r0.val$context;	 //Catch:{ all -> 0x016d }
            monitor-enter(r9);	 //Catch:{ all -> 0x016d }
            r0 = r16;
            r10 = r0.val$context;	 //Catch:{ all -> 0x01f7 }
            r10 = r10.aborted;	 //Catch:{ all -> 0x01f7 }
            if (r10 == 0) goto L_0x013a;
        L_0x00a8:
            monitor-exit(r9);	 //Catch:{ all -> 0x01f7 }
            r0 = r16;
            r1 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r9 = 0;
            r1.currentInputStream = r9;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            org.apache.cordova.FileTransfer.safeClose(r5);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = activeRequests;
            monitor-enter(r3);
            r1 = activeRequests;	 //Catch:{ all -> 0x0453 }
            r0 = r16;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x0453 }
            r1.remove(r5);	 //Catch:{ all -> 0x0453 }
            monitor-exit(r3);	 //Catch:{ all -> 0x0453 }
            if (r2 == 0) goto L_0x00de;
        L_0x00c9:
            r0 = r16;
            r1 = r0.val$trustEveryone;
            if (r1 == 0) goto L_0x00de;
        L_0x00cf:
            r0 = r16;
            r1 = r0.val$useHttps;
            if (r1 == 0) goto L_0x00de;
        L_0x00d5:
            r1 = r2;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;
            r1.setHostnameVerifier(r8);
            r1.setSSLSocketFactory(r7);
        L_0x00de:
            if (r4 != 0) goto L_0x00f5;
        L_0x00e0:
            r4 = new org.apache.cordova.api.PluginResult;
            r1 = org.apache.cordova.api.PluginResult.Status.ERROR;
            r3 = CONNECTION_ERR;
            r0 = r16;
            r5 = r0.val$source;
            r0 = r16;
            r7 = r0.val$target;
            r2 = org.apache.cordova.FileTransfer.createFileTransferError(r3, r5, r7, r2);
            r4.<init>(r1, r2);
        L_0x00f5:
            r1 = r4.getStatus();
            r2 = org.apache.cordova.api.PluginResult.Status.OK;
            r2 = r2.ordinal();
            if (r1 == r2) goto L_0x0106;
        L_0x0101:
            if (r6 == 0) goto L_0x0106;
        L_0x0103:
            r6.delete();
        L_0x0106:
            r0 = r16;
            r1 = r0.val$context;
            r1.sendPluginResult(r4);
            goto L_0x0009;
        L_0x010f:
            r0 = r16;
            r1 = r0.val$url;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1 = r1.openConnection();	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1 = (javax.net.ssl.HttpsURLConnection) r1;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r2 = org.apache.cordova.FileTransfer.trustAllHosts(r1);	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r3 = r1.getHostnameVerifier();	 //Catch:{ FileNotFoundException -> 0x04c3, IOException -> 0x04a6, JSONException -> 0x0491, Throwable -> 0x047c, all -> 0x045f }
            r5 = DO_NOT_VERIFY;	 //Catch:{ FileNotFoundException -> 0x04ca, IOException -> 0x04ac, JSONException -> 0x0497, Throwable -> 0x0482, all -> 0x0465 }
            r1.setHostnameVerifier(r5);	 //Catch:{ FileNotFoundException -> 0x04ca, IOException -> 0x04ac, JSONException -> 0x0497, Throwable -> 0x0482, all -> 0x0465 }
            r7 = r2;
            r8 = r3;
            r2 = r1;
            goto L_0x003c;
        L_0x012d:
            r0 = r16;
            r1 = r0.val$url;	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r1 = r1.openConnection();	 //Catch:{ FileNotFoundException -> 0x04bc, IOException -> 0x04a0, JSONException -> 0x048b, Throwable -> 0x0476, all -> 0x0459 }
            r7 = r4;
            r8 = r4;
            r2 = r1;
            goto L_0x003c;
        L_0x013a:
            r0 = r16;
            r10 = r0.val$context;	 //Catch:{ all -> 0x01f7 }
            r10.currentInputStream = r3;	 //Catch:{ all -> 0x01f7 }
            monitor-exit(r9);	 //Catch:{ all -> 0x01f7 }
            r9 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
            r11 = new byte[r9];	 //Catch:{ all -> 0x016d }
            r9 = 0;
        L_0x0147:
            r12 = r3.read(r11);	 //Catch:{ all -> 0x016d }
            if (r12 <= 0) goto L_0x01fa;
        L_0x014d:
            r13 = 0;
            r5.write(r11, r13, r12);	 //Catch:{ all -> 0x016d }
            r12 = (long) r12;	 //Catch:{ all -> 0x016d }
            r9 = r9 + r12;
            r1.setLoaded(r9);	 //Catch:{ all -> 0x016d }
            r12 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x016d }
            r13 = org.apache.cordova.api.PluginResult.Status.OK;	 //Catch:{ all -> 0x016d }
            r14 = r1.toJSONObject();	 //Catch:{ all -> 0x016d }
            r12.<init>(r13, r14);	 //Catch:{ all -> 0x016d }
            r13 = 1;
            r12.setKeepCallback(r13);	 //Catch:{ all -> 0x016d }
            r0 = r16;
            r13 = r0.val$context;	 //Catch:{ all -> 0x016d }
            r13.sendPluginResult(r12);	 //Catch:{ all -> 0x016d }
            goto L_0x0147;
        L_0x016d:
            r1 = move-exception;
        L_0x016e:
            r0 = r16;
            r9 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r10 = 0;
            r9.currentInputStream = r10;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            org.apache.cordova.FileTransfer.safeClose(r5);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            throw r1;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
        L_0x017c:
            r1 = move-exception;
            r3 = r6;
            r5 = r7;
            r6 = r8;
        L_0x0180:
            r7 = FILE_NOT_FOUND_ERR;	 //Catch:{ all -> 0x046f }
            r0 = r16;
            r8 = r0.val$source;	 //Catch:{ all -> 0x046f }
            r0 = r16;
            r9 = r0.val$target;	 //Catch:{ all -> 0x046f }
            r8 = org.apache.cordova.FileTransfer.createFileTransferError(r7, r8, r9, r2);	 //Catch:{ all -> 0x046f }
            r7 = "FileTransfer";
            r9 = r8.toString();	 //Catch:{ all -> 0x046f }
            android.util.Log.e(r7, r9, r1);	 //Catch:{ all -> 0x046f }
            r7 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x046f }
            r1 = org.apache.cordova.api.PluginResult.Status.IO_EXCEPTION;	 //Catch:{ all -> 0x046f }
            r7.<init>(r1, r8);	 //Catch:{ all -> 0x046f }
            r4 = activeRequests;
            monitor-enter(r4);
            r1 = activeRequests;	 //Catch:{ all -> 0x0447 }
            r0 = r16;
            r8 = r0.val$objectId;	 //Catch:{ all -> 0x0447 }
            r1.remove(r8);	 //Catch:{ all -> 0x0447 }
            monitor-exit(r4);	 //Catch:{ all -> 0x0447 }
            if (r2 == 0) goto L_0x01c6;
        L_0x01b1:
            r0 = r16;
            r1 = r0.val$trustEveryone;
            if (r1 == 0) goto L_0x01c6;
        L_0x01b7:
            r0 = r16;
            r1 = r0.val$useHttps;
            if (r1 == 0) goto L_0x01c6;
        L_0x01bd:
            r1 = r2;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;
            r1.setHostnameVerifier(r6);
            r1.setSSLSocketFactory(r5);
        L_0x01c6:
            if (r7 != 0) goto L_0x04e7;
        L_0x01c8:
            r1 = new org.apache.cordova.api.PluginResult;
            r4 = org.apache.cordova.api.PluginResult.Status.ERROR;
            r5 = CONNECTION_ERR;
            r0 = r16;
            r6 = r0.val$source;
            r0 = r16;
            r7 = r0.val$target;
            r2 = org.apache.cordova.FileTransfer.createFileTransferError(r5, r6, r7, r2);
            r1.<init>(r4, r2);
        L_0x01dd:
            r2 = r1.getStatus();
            r4 = org.apache.cordova.api.PluginResult.Status.OK;
            r4 = r4.ordinal();
            if (r2 == r4) goto L_0x01ee;
        L_0x01e9:
            if (r3 == 0) goto L_0x01ee;
        L_0x01eb:
            r3.delete();
        L_0x01ee:
            r0 = r16;
            r2 = r0.val$context;
        L_0x01f2:
            r2.sendPluginResult(r1);
            goto L_0x0009;
        L_0x01f7:
            r1 = move-exception;
            monitor-exit(r9);	 //Catch:{ all -> 0x01f7 }
            throw r1;	 //Catch:{ all -> 0x016d }
        L_0x01fa:
            r0 = r16;
            r1 = r0.val$context;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r9 = 0;
            r1.currentInputStream = r9;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            org.apache.cordova.FileTransfer.safeClose(r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            org.apache.cordova.FileTransfer.safeClose(r5);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1 = "FileTransfer";
            r3 = new java.lang.StringBuilder;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3.<init>();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r5 = "Saved file: ";
            r3 = r3.append(r5);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r0 = r16;
            r5 = r0.val$target;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = r3.append(r5);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = r3.toString();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            android.util.Log.d(r1, r3);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1 = new org.apache.cordova.FileUtils;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1.<init>();	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r1 = r1.getEntry(r6);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3 = new org.apache.cordova.api.PluginResult;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r5 = org.apache.cordova.api.PluginResult.Status.OK;	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r3.<init>(r5, r1);	 //Catch:{ FileNotFoundException -> 0x017c, IOException -> 0x04b2, JSONException -> 0x049d, Throwable -> 0x0488 }
            r4 = activeRequests;
            monitor-enter(r4);
            r1 = activeRequests;	 //Catch:{ all -> 0x0456 }
            r0 = r16;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x0456 }
            r1.remove(r5);	 //Catch:{ all -> 0x0456 }
            monitor-exit(r4);	 //Catch:{ all -> 0x0456 }
            if (r2 == 0) goto L_0x025b;
        L_0x0246:
            r0 = r16;
            r1 = r0.val$trustEveryone;
            if (r1 == 0) goto L_0x025b;
        L_0x024c:
            r0 = r16;
            r1 = r0.val$useHttps;
            if (r1 == 0) goto L_0x025b;
        L_0x0252:
            r1 = r2;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;
            r1.setHostnameVerifier(r8);
            r1.setSSLSocketFactory(r7);
        L_0x025b:
            if (r3 != 0) goto L_0x04db;
        L_0x025d:
            r1 = new org.apache.cordova.api.PluginResult;
            r3 = org.apache.cordova.api.PluginResult.Status.ERROR;
            r4 = CONNECTION_ERR;
            r0 = r16;
            r5 = r0.val$source;
            r0 = r16;
            r7 = r0.val$target;
            r2 = org.apache.cordova.FileTransfer.createFileTransferError(r4, r5, r7, r2);
            r1.<init>(r3, r2);
        L_0x0272:
            r2 = r1.getStatus();
            r3 = org.apache.cordova.api.PluginResult.Status.OK;
            r3 = r3.ordinal();
            if (r2 == r3) goto L_0x0283;
        L_0x027e:
            if (r6 == 0) goto L_0x0283;
        L_0x0280:
            r6.delete();
        L_0x0283:
            r0 = r16;
            r2 = r0.val$context;
            goto L_0x01f2;
        L_0x0289:
            r1 = move-exception;
            r6 = r4;
            r7 = r4;
            r8 = r4;
            r2 = r4;
        L_0x028e:
            r3 = CONNECTION_ERR;	 //Catch:{ all -> 0x046b }
            r0 = r16;
            r5 = r0.val$source;	 //Catch:{ all -> 0x046b }
            r0 = r16;
            r9 = r0.val$target;	 //Catch:{ all -> 0x046b }
            r5 = org.apache.cordova.FileTransfer.createFileTransferError(r3, r5, r9, r2);	 //Catch:{ all -> 0x046b }
            r3 = "FileTransfer";
            r9 = r5.toString();	 //Catch:{ all -> 0x046b }
            android.util.Log.e(r3, r9, r1);	 //Catch:{ all -> 0x046b }
            r3 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x046b }
            r1 = org.apache.cordova.api.PluginResult.Status.IO_EXCEPTION;	 //Catch:{ all -> 0x046b }
            r3.<init>(r1, r5);	 //Catch:{ all -> 0x046b }
            r4 = activeRequests;
            monitor-enter(r4);
            r1 = activeRequests;	 //Catch:{ all -> 0x044a }
            r0 = r16;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x044a }
            r1.remove(r5);	 //Catch:{ all -> 0x044a }
            monitor-exit(r4);	 //Catch:{ all -> 0x044a }
            if (r2 == 0) goto L_0x02d4;
        L_0x02bf:
            r0 = r16;
            r1 = r0.val$trustEveryone;
            if (r1 == 0) goto L_0x02d4;
        L_0x02c5:
            r0 = r16;
            r1 = r0.val$useHttps;
            if (r1 == 0) goto L_0x02d4;
        L_0x02cb:
            r1 = r2;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;
            r1.setHostnameVerifier(r8);
            r1.setSSLSocketFactory(r7);
        L_0x02d4:
            if (r3 != 0) goto L_0x04e4;
        L_0x02d6:
            r1 = new org.apache.cordova.api.PluginResult;
            r3 = org.apache.cordova.api.PluginResult.Status.ERROR;
            r4 = CONNECTION_ERR;
            r0 = r16;
            r5 = r0.val$source;
            r0 = r16;
            r7 = r0.val$target;
            r2 = org.apache.cordova.FileTransfer.createFileTransferError(r4, r5, r7, r2);
            r1.<init>(r3, r2);
        L_0x02eb:
            r2 = r1.getStatus();
            r3 = org.apache.cordova.api.PluginResult.Status.OK;
            r3 = r3.ordinal();
            if (r2 == r3) goto L_0x02fc;
        L_0x02f7:
            if (r6 == 0) goto L_0x02fc;
        L_0x02f9:
            r6.delete();
        L_0x02fc:
            r0 = r16;
            r2 = r0.val$context;
            goto L_0x01f2;
        L_0x0302:
            r1 = move-exception;
            r6 = r4;
            r7 = r4;
            r8 = r4;
            r2 = r4;
        L_0x0307:
            r3 = "FileTransfer";
            r5 = r1.getMessage();	 //Catch:{ all -> 0x046b }
            android.util.Log.e(r3, r5, r1);	 //Catch:{ all -> 0x046b }
            r3 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x046b }
            r1 = org.apache.cordova.api.PluginResult.Status.JSON_EXCEPTION;	 //Catch:{ all -> 0x046b }
            r3.<init>(r1);	 //Catch:{ all -> 0x046b }
            r4 = activeRequests;
            monitor-enter(r4);
            r1 = activeRequests;	 //Catch:{ all -> 0x044d }
            r0 = r16;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x044d }
            r1.remove(r5);	 //Catch:{ all -> 0x044d }
            monitor-exit(r4);	 //Catch:{ all -> 0x044d }
            if (r2 == 0) goto L_0x033f;
        L_0x032a:
            r0 = r16;
            r1 = r0.val$trustEveryone;
            if (r1 == 0) goto L_0x033f;
        L_0x0330:
            r0 = r16;
            r1 = r0.val$useHttps;
            if (r1 == 0) goto L_0x033f;
        L_0x0336:
            r1 = r2;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;
            r1.setHostnameVerifier(r8);
            r1.setSSLSocketFactory(r7);
        L_0x033f:
            if (r3 != 0) goto L_0x04e1;
        L_0x0341:
            r1 = new org.apache.cordova.api.PluginResult;
            r3 = org.apache.cordova.api.PluginResult.Status.ERROR;
            r4 = CONNECTION_ERR;
            r0 = r16;
            r5 = r0.val$source;
            r0 = r16;
            r7 = r0.val$target;
            r2 = org.apache.cordova.FileTransfer.createFileTransferError(r4, r5, r7, r2);
            r1.<init>(r3, r2);
        L_0x0356:
            r2 = r1.getStatus();
            r3 = org.apache.cordova.api.PluginResult.Status.OK;
            r3 = r3.ordinal();
            if (r2 == r3) goto L_0x0367;
        L_0x0362:
            if (r6 == 0) goto L_0x0367;
        L_0x0364:
            r6.delete();
        L_0x0367:
            r0 = r16;
            r2 = r0.val$context;
            goto L_0x01f2;
        L_0x036d:
            r1 = move-exception;
            r6 = r4;
            r7 = r4;
            r8 = r4;
            r2 = r4;
        L_0x0372:
            r3 = CONNECTION_ERR;	 //Catch:{ all -> 0x046b }
            r0 = r16;
            r5 = r0.val$source;	 //Catch:{ all -> 0x046b }
            r0 = r16;
            r9 = r0.val$target;	 //Catch:{ all -> 0x046b }
            r5 = org.apache.cordova.FileTransfer.createFileTransferError(r3, r5, r9, r2);	 //Catch:{ all -> 0x046b }
            r3 = "FileTransfer";
            r9 = r5.toString();	 //Catch:{ all -> 0x046b }
            android.util.Log.e(r3, r9, r1);	 //Catch:{ all -> 0x046b }
            r3 = new org.apache.cordova.api.PluginResult;	 //Catch:{ all -> 0x046b }
            r1 = org.apache.cordova.api.PluginResult.Status.IO_EXCEPTION;	 //Catch:{ all -> 0x046b }
            r3.<init>(r1, r5);	 //Catch:{ all -> 0x046b }
            r4 = activeRequests;
            monitor-enter(r4);
            r1 = activeRequests;	 //Catch:{ all -> 0x0450 }
            r0 = r16;
            r5 = r0.val$objectId;	 //Catch:{ all -> 0x0450 }
            r1.remove(r5);	 //Catch:{ all -> 0x0450 }
            monitor-exit(r4);	 //Catch:{ all -> 0x0450 }
            if (r2 == 0) goto L_0x03b8;
        L_0x03a3:
            r0 = r16;
            r1 = r0.val$trustEveryone;
            if (r1 == 0) goto L_0x03b8;
        L_0x03a9:
            r0 = r16;
            r1 = r0.val$useHttps;
            if (r1 == 0) goto L_0x03b8;
        L_0x03af:
            r1 = r2;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;
            r1.setHostnameVerifier(r8);
            r1.setSSLSocketFactory(r7);
        L_0x03b8:
            if (r3 != 0) goto L_0x04de;
        L_0x03ba:
            r1 = new org.apache.cordova.api.PluginResult;
            r3 = org.apache.cordova.api.PluginResult.Status.ERROR;
            r4 = CONNECTION_ERR;
            r0 = r16;
            r5 = r0.val$source;
            r0 = r16;
            r7 = r0.val$target;
            r2 = org.apache.cordova.FileTransfer.createFileTransferError(r4, r5, r7, r2);
            r1.<init>(r3, r2);
        L_0x03cf:
            r2 = r1.getStatus();
            r3 = org.apache.cordova.api.PluginResult.Status.OK;
            r3 = r3.ordinal();
            if (r2 == r3) goto L_0x03e0;
        L_0x03db:
            if (r6 == 0) goto L_0x03e0;
        L_0x03dd:
            r6.delete();
        L_0x03e0:
            r0 = r16;
            r2 = r0.val$context;
            goto L_0x01f2;
        L_0x03e6:
            r1 = move-exception;
            r3 = r1;
            r6 = r4;
            r7 = r4;
            r8 = r4;
            r2 = r4;
        L_0x03ec:
            r5 = activeRequests;
            monitor-enter(r5);
            r1 = activeRequests;	 //Catch:{ all -> 0x0444 }
            r0 = r16;
            r9 = r0.val$objectId;	 //Catch:{ all -> 0x0444 }
            r1.remove(r9);	 //Catch:{ all -> 0x0444 }
            monitor-exit(r5);	 //Catch:{ all -> 0x0444 }
            if (r2 == 0) goto L_0x0414;
        L_0x03ff:
            r0 = r16;
            r1 = r0.val$trustEveryone;
            if (r1 == 0) goto L_0x0414;
        L_0x0405:
            r0 = r16;
            r1 = r0.val$useHttps;
            if (r1 == 0) goto L_0x0414;
        L_0x040b:
            r1 = r2;
            r1 = (javax.net.ssl.HttpsURLConnection) r1;
            r1.setHostnameVerifier(r8);
            r1.setSSLSocketFactory(r7);
        L_0x0414:
            if (r4 != 0) goto L_0x042b;
        L_0x0416:
            r4 = new org.apache.cordova.api.PluginResult;
            r1 = org.apache.cordova.api.PluginResult.Status.ERROR;
            r5 = CONNECTION_ERR;
            r0 = r16;
            r7 = r0.val$source;
            r0 = r16;
            r8 = r0.val$target;
            r2 = org.apache.cordova.FileTransfer.createFileTransferError(r5, r7, r8, r2);
            r4.<init>(r1, r2);
        L_0x042b:
            r1 = r4.getStatus();
            r2 = org.apache.cordova.api.PluginResult.Status.OK;
            r2 = r2.ordinal();
            if (r1 == r2) goto L_0x043c;
        L_0x0437:
            if (r6 == 0) goto L_0x043c;
        L_0x0439:
            r6.delete();
        L_0x043c:
            r0 = r16;
            r1 = r0.val$context;
            r1.sendPluginResult(r4);
            throw r3;
        L_0x0444:
            r1 = move-exception;
            monitor-exit(r5);	 //Catch:{ all -> 0x0444 }
            throw r1;
        L_0x0447:
            r1 = move-exception;
            monitor-exit(r4);	 //Catch:{ all -> 0x0447 }
            throw r1;
        L_0x044a:
            r1 = move-exception;
            monitor-exit(r4);	 //Catch:{ all -> 0x044a }
            throw r1;
        L_0x044d:
            r1 = move-exception;
            monitor-exit(r4);	 //Catch:{ all -> 0x044d }
            throw r1;
        L_0x0450:
            r1 = move-exception;
            monitor-exit(r4);	 //Catch:{ all -> 0x0450 }
            throw r1;
        L_0x0453:
            r1 = move-exception;
            monitor-exit(r3);	 //Catch:{ all -> 0x0453 }
            throw r1;
        L_0x0456:
            r1 = move-exception;
            monitor-exit(r4);	 //Catch:{ all -> 0x0456 }
            throw r1;
        L_0x0459:
            r1 = move-exception;
            r3 = r1;
            r7 = r4;
            r8 = r4;
            r2 = r4;
            goto L_0x03ec;
        L_0x045f:
            r1 = move-exception;
            r3 = r1;
            r7 = r2;
            r8 = r4;
            r2 = r4;
            goto L_0x03ec;
        L_0x0465:
            r1 = move-exception;
            r7 = r2;
            r8 = r3;
            r3 = r1;
            r2 = r4;
            goto L_0x03ec;
        L_0x046b:
            r1 = move-exception;
            r3 = r1;
            goto L_0x03ec;
        L_0x046f:
            r1 = move-exception;
            r7 = r5;
            r8 = r6;
            r6 = r3;
            r3 = r1;
            goto L_0x03ec;
        L_0x0476:
            r1 = move-exception;
            r7 = r4;
            r8 = r4;
            r2 = r4;
            goto L_0x0372;
        L_0x047c:
            r1 = move-exception;
            r7 = r2;
            r8 = r4;
            r2 = r4;
            goto L_0x0372;
        L_0x0482:
            r1 = move-exception;
            r7 = r2;
            r8 = r3;
            r2 = r4;
            goto L_0x0372;
        L_0x0488:
            r1 = move-exception;
            goto L_0x0372;
        L_0x048b:
            r1 = move-exception;
            r7 = r4;
            r8 = r4;
            r2 = r4;
            goto L_0x0307;
        L_0x0491:
            r1 = move-exception;
            r7 = r2;
            r8 = r4;
            r2 = r4;
            goto L_0x0307;
        L_0x0497:
            r1 = move-exception;
            r7 = r2;
            r8 = r3;
            r2 = r4;
            goto L_0x0307;
        L_0x049d:
            r1 = move-exception;
            goto L_0x0307;
        L_0x04a0:
            r1 = move-exception;
            r7 = r4;
            r8 = r4;
            r2 = r4;
            goto L_0x028e;
        L_0x04a6:
            r1 = move-exception;
            r7 = r2;
            r8 = r4;
            r2 = r4;
            goto L_0x028e;
        L_0x04ac:
            r1 = move-exception;
            r7 = r2;
            r8 = r3;
            r2 = r4;
            goto L_0x028e;
        L_0x04b2:
            r1 = move-exception;
            goto L_0x028e;
        L_0x04b5:
            r1 = move-exception;
            r3 = r4;
            r5 = r4;
            r6 = r4;
            r2 = r4;
            goto L_0x0180;
        L_0x04bc:
            r1 = move-exception;
            r3 = r6;
            r5 = r4;
            r2 = r4;
            r6 = r4;
            goto L_0x0180;
        L_0x04c3:
            r1 = move-exception;
            r3 = r6;
            r5 = r2;
            r6 = r4;
            r2 = r4;
            goto L_0x0180;
        L_0x04ca:
            r1 = move-exception;
            r5 = r2;
            r2 = r4;
            r15 = r3;
            r3 = r6;
            r6 = r15;
            goto L_0x0180;
        L_0x04d2:
            r1 = move-exception;
            r3 = r4;
            r5 = r4;
            goto L_0x016e;
        L_0x04d7:
            r1 = move-exception;
            r5 = r4;
            goto L_0x016e;
        L_0x04db:
            r1 = r3;
            goto L_0x0272;
        L_0x04de:
            r1 = r3;
            goto L_0x03cf;
        L_0x04e1:
            r1 = r3;
            goto L_0x0356;
        L_0x04e4:
            r1 = r3;
            goto L_0x02eb;
        L_0x04e7:
            r1 = r7;
            goto L_0x01dd;
            */

        }
    }

    class AnonymousClass_5 implements Runnable {
        final /* synthetic */ RequestContext val$context;

        AnonymousClass_5(RequestContext r2_RequestContext) {
            this.val$context = r2_RequestContext;
        }

        public void run() {
            synchronized (this.val$context) {
                FileTransfer.safeClose(this.val$context.currentInputStream);
                FileTransfer.safeClose(this.val$context.currentOutputStream);
            }
        }
    }

    private static final class DoneHandlerInputStream extends FilterInputStream {
        private boolean done;

        public DoneHandlerInputStream(InputStream r1_InputStream) {
            super(r1_InputStream);
        }

        public int read() throws IOException {
            int r1i;
            r1i = this.done ? -1 : super.read();
            this.done = r1i == -1;
            return r1i;
        }

        public int read(byte[] r3_byteA) throws IOException {
            int r1i;
            r1i = this.done ? -1 : super.read(r3_byteA);
            this.done = r1i == -1;
            return r1i;
        }

        public int read(byte[] r3_byteA, int r4i, int r5i) throws IOException {
            int r1i;
            r1i = this.done ? -1 : super.read(r3_byteA, r4i, r5i);
            this.done = r1i == -1;
            return r1i;
        }
    }

    private static final class RequestContext {
        boolean aborted;
        CallbackContext callbackContext;
        InputStream currentInputStream;
        OutputStream currentOutputStream;
        String source;
        String target;
        File targetFile;

        RequestContext(String r1_String, String r2_String, CallbackContext r3_CallbackContext) {
            this.source = r1_String;
            this.target = r2_String;
            this.callbackContext = r3_CallbackContext;
        }

        void sendPluginResult(PluginResult r2_PluginResult) {
            synchronized (this) {
                if (!this.aborted) {
                    this.callbackContext.sendPluginResult(r2_PluginResult);
                }
            }
        }
    }

    static {
        FILE_NOT_FOUND_ERR = 1;
        INVALID_URL_ERR = 2;
        CONNECTION_ERR = 3;
        ABORTED_ERR = 4;
        activeRequests = new HashMap();
        DO_NOT_VERIFY = new HostnameVerifier() {
            public boolean verify(String r2_String, SSLSession r3_SSLSession) {
                return true;
            }
        };
        TrustManager[] r0_TrustManagerA = new TrustManager[1];
        r0_TrustManagerA[0] = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] r1_X509CertificateA, String r2_String) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] r1_X509CertificateA, String r2_String) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        trustAllCerts = r0_TrustManagerA;
    }

    private void abort(String r6_String) {
        RequestContext r0_RequestContext;
        synchronized (activeRequests) {
            r0_RequestContext = (RequestContext) activeRequests.remove(r6_String);
        }
        if (r0_RequestContext != null) {
            File r1_File = r0_RequestContext.targetFile;
            if (r1_File != null) {
                r1_File.delete();
            }
            JSONObject r1_JSONObject = createFileTransferError(ABORTED_ERR, r0_RequestContext.source, r0_RequestContext.target, Integer.valueOf(-1));
            synchronized (r0_RequestContext) {
                r0_RequestContext.sendPluginResult(new PluginResult(Status.ERROR, r1_JSONObject));
                r0_RequestContext.aborted = true;
            }
            this.cordova.getThreadPool().execute(new AnonymousClass_5(r0_RequestContext));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static JSONObject createFileTransferError(int r5i, String r6_String, String r7_String, Integer r8_Integer) {
        /*
        r1 = 0;
        r0 = new org.json.JSONObject;	 //Catch:{ JSONException -> 0x001d }
        r0.<init>();	 //Catch:{ JSONException -> 0x001d }
        r1 = "code";
        r0.put(r1, r5);	 //Catch:{ JSONException -> 0x002b }
        r1 = "source";
        r0.put(r1, r6);	 //Catch:{ JSONException -> 0x002b }
        r1 = "target";
        r0.put(r1, r7);	 //Catch:{ JSONException -> 0x002b }
        if (r8 == 0) goto L_0x001c;
    L_0x0017:
        r1 = "http_status";
        r0.put(r1, r8);	 //Catch:{ JSONException -> 0x002b }
    L_0x001c:
        return r0;
    L_0x001d:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0021:
        r2 = "FileTransfer";
        r3 = r1.getMessage();
        android.util.Log.e(r2, r3, r1);
        goto L_0x001c;
    L_0x002b:
        r1 = move-exception;
        goto L_0x0021;
        */

    }

    private static JSONObject createFileTransferError(int r4i, String r5_String, String r6_String, URLConnection r7_URLConnection) {
        int r0i = 0;
        if (r7_URLConnection != null) {
            try {
                if (r7_URLConnection instanceof HttpURLConnection) {
                    r0i = ((HttpURLConnection) r7_URLConnection).getResponseCode();
                }
            } catch (IOException e) {
                Log.w(LOG_TAG, "Error getting HTTP status code from connection.", e);
            }
        }
        return createFileTransferError(r4i, r5_String, r6_String, Integer.valueOf(r0i));
    }

    private void download(String r11_String, String r12_String, JSONArray r13_JSONArray, CallbackContext r14_CallbackContext) throws JSONException {
        Log.d(LOG_TAG, "download " + r11_String + " to " + r12_String);
        boolean r5z = r13_JSONArray.optBoolean(XListViewHeader.STATE_REFRESHING);
        String r8_String = r13_JSONArray.getString(XListViewFooter.STATE_NOMORE);
        try {
            URL r6_URL = new URL(r11_String);
            boolean r4z = r6_URL.getProtocol().equals("https");
            if (Config.isUrlWhiteListed(r11_String)) {
                RequestContext r2_RequestContext = new RequestContext(r11_String, r12_String, r14_CallbackContext);
                synchronized (activeRequests) {
                    activeRequests.put(r8_String, r2_RequestContext);
                }
                this.cordova.getThreadPool().execute(new AnonymousClass_4(r2_RequestContext, r12_String, r4z, r5z, r6_URL, r11_String, r8_String));
            } else {
                Log.w(LOG_TAG, "Source URL is not in white list: '" + r11_String + "'");
                r14_CallbackContext.sendPluginResult(new PluginResult(Status.IO_EXCEPTION, createFileTransferError(CONNECTION_ERR, r11_String, r12_String, Integer.valueOf(401))));
            }
        } catch (MalformedURLException e) {
            JSONObject r1_JSONObject = createFileTransferError(INVALID_URL_ERR, r11_String, r12_String, Integer.valueOf(0));
            Log.e(LOG_TAG, r1_JSONObject.toString(), e);
            r14_CallbackContext.sendPluginResult(new PluginResult(Status.IO_EXCEPTION, r1_JSONObject));
        }
    }

    private static String getArgument(JSONArray r2_JSONArray, int r3i, String r4_String) {
        if (r2_JSONArray.length() < r3i) {
            return r4_String;
        }
        String r0_String = r2_JSONArray.optString(r3i);
        return (r0_String == null || "null".equals(r0_String)) ? r4_String : r0_String;
    }

    private File getFileFromPath(String r3_String) throws FileNotFoundException {
        File r0_File;
        String r1_String = "file://";
        r0_File = r3_String.startsWith(r1_String) ? new File(r3_String.substring(r1_String.length())) : new File(r3_String);
        if (r0_File.getParent() != null) {
            return r0_File;
        }
        throw new FileNotFoundException();
    }

    private static InputStream getInputStream(URLConnection r2_URLConnection) throws IOException {
        return VERSION.SDK_INT < 11 ? new DoneHandlerInputStream(r2_URLConnection.getInputStream()) : r2_URLConnection.getInputStream();
    }

    private InputStream getPathFromUri(String r4_String) throws FileNotFoundException {
        if (r4_String.startsWith("content:")) {
            return this.cordova.getActivity().getContentResolver().openInputStream(Uri.parse(r4_String));
        } else {
            if (!r4_String.startsWith("file://")) {
                return new FileInputStream(r4_String);
            }
            int r1i = r4_String.indexOf("?");
            return r1i == -1 ? new FileInputStream(r4_String.substring(ShareUtils.SHARE_COLLECT)) : new FileInputStream(r4_String.substring(ShareUtils.SHARE_COLLECT, r1i));
        }
    }

    private static void safeClose(Closeable r1_Closeable) {
        if (r1_Closeable != null) {
            try {
                r1_Closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private static SSLSocketFactory trustAllHosts(HttpsURLConnection r5_HttpsURLConnection) {
        SSLSocketFactory r1_SSLSocketFactory = r5_HttpsURLConnection.getSSLSocketFactory();
        try {
            SSLContext r0_SSLContext = SSLContext.getInstance("TLS");
            r0_SSLContext.init(null, trustAllCerts, new SecureRandom());
            r5_HttpsURLConnection.setSSLSocketFactory(r0_SSLContext.getSocketFactory());
        } catch (Exception e) {
            Throwable r0_Throwable = e;
            Log.e(LOG_TAG, r0_Throwable.getMessage(), r0_Throwable);
        }
        return r1_SSLSocketFactory;
    }

    private void upload(String r20_String, String r21_String, JSONArray r22_JSONArray, CallbackContext r23_CallbackContext) throws JSONException {
        JSONObject r11_JSONObject;
        boolean r16z;
        JSONObject r10_JSONObject;
        Log.d(LOG_TAG, "upload " + r20_String + " to " + r21_String);
        String r12_String = getArgument(r22_JSONArray, XListViewHeader.STATE_REFRESHING, "file");
        String r13_String = getArgument(r22_JSONArray, XListViewFooter.STATE_NOMORE, "image.jpg");
        String r14_String = getArgument(r22_JSONArray, XListViewFooter.STATE_NODATA, "image/jpeg");
        r11_JSONObject = r22_JSONArray.optJSONObject(ShareUtils.SHARE_SMS) == null ? new JSONObject() : r22_JSONArray.optJSONObject(ShareUtils.SHARE_SMS);
        boolean r7z = r22_JSONArray.optBoolean(ShareUtils.SHARE_COPY);
        r16z = r22_JSONArray.optBoolean(ShareUtils.SHARE_COLLECT) || r22_JSONArray.isNull(ShareUtils.SHARE_COLLECT);
        r10_JSONObject = r22_JSONArray.optJSONObject(Base64.DONT_BREAK_LINES) == null ? r11_JSONObject.optJSONObject("headers") : r22_JSONArray.optJSONObject(Base64.DONT_BREAK_LINES);
        String r17_String = r22_JSONArray.getString(REQUEST_CODE.REQUEST_CODE_EDIT_HOBBY);
        Log.d(LOG_TAG, "fileKey: " + r12_String);
        Log.d(LOG_TAG, "fileName: " + r13_String);
        Log.d(LOG_TAG, "mimeType: " + r14_String);
        Log.d(LOG_TAG, "params: " + r11_JSONObject);
        Log.d(LOG_TAG, "trustEveryone: " + r7z);
        Log.d(LOG_TAG, "chunkedMode: " + r16z);
        Log.d(LOG_TAG, "headers: " + r10_JSONObject);
        Log.d(LOG_TAG, "objectId: " + r17_String);
        try {
            URL r8_URL = new URL(r21_String);
            boolean r6z = r8_URL.getProtocol().equals("https");
            RequestContext r5_RequestContext = new RequestContext(r20_String, r21_String, r23_CallbackContext);
            synchronized (activeRequests) {
                activeRequests.put(r17_String, r5_RequestContext);
            }
            this.cordova.getThreadPool().execute(new AnonymousClass_1(r5_RequestContext, r6z, r7z, r8_URL, r21_String, r10_JSONObject, r11_JSONObject, r12_String, r13_String, r14_String, r20_String, r16z, r17_String));
        } catch (MalformedURLException e) {
            JSONObject r4_JSONObject = createFileTransferError(INVALID_URL_ERR, r20_String, r21_String, Integer.valueOf(0));
            Log.e(LOG_TAG, r4_JSONObject.toString(), e);
            r23_CallbackContext.sendPluginResult(new PluginResult(Status.IO_EXCEPTION, r4_JSONObject));
        }
    }

    public boolean execute(String r5_String, JSONArray r6_JSONArray, CallbackContext r7_CallbackContext) throws JSONException {
        int r1i = 0;
        if (r5_String.equals("upload") || r5_String.equals("download")) {
            String r1_String = r6_JSONArray.getString(r1i);
            String r2_String = r6_JSONArray.getString(1);
            if (r5_String.equals("upload")) {
                try {
                    upload(URLDecoder.decode(r1_String, Base.UTF8), r2_String, r6_JSONArray, r7_CallbackContext);
                    return true;
                } catch (UnsupportedEncodingException e) {
                    r7_CallbackContext.sendPluginResult(new PluginResult(Status.MALFORMED_URL_EXCEPTION, "UTF-8 error."));
                    return true;
                }
            } else {
                download(r1_String, r2_String, r6_JSONArray, r7_CallbackContext);
                return true;
            }
        } else {
            if (!r5_String.equals("abort")) {
                return false;
            }
            abort(r6_JSONArray.getString(r1i));
            r7_CallbackContext.success();
            return true;
        }
    }
}