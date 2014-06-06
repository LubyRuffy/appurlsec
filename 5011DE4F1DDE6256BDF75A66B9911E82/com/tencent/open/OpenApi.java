package com.tencent.open;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import com.tencent.tauth.IRequestListener;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.ZipException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.net.HttpManager;
import qsbk.app.utils.audit.RequestListener;

// compiled from: ProGuard
public class OpenApi {
    private TContext a;

    public OpenApi(TContext r1_TContext) {
        this.a = r1_TContext;
    }

    private static int a(IOException r1_IOException) {
        if (r1_IOException instanceof CharConversionException) {
            return Constants.ERROR_IO_CharConversionException;
        }
        if (r1_IOException instanceof MalformedInputException) {
            return Constants.ERROR_IO_CharacterCodingException_MalformedInputException;
        }
        if (r1_IOException instanceof UnmappableCharacterException) {
            return Constants.ERROR_IO_CharacterCodingException_UnmappableCharacterException;
        }
        if (r1_IOException instanceof HttpResponseException) {
            return Constants.ERROR_IO_ClientProtocolException_HttpResponseException;
        }
        if (r1_IOException instanceof ClosedChannelException) {
            return Constants.ERROR_IO_ClosedChannelException;
        }
        if (r1_IOException instanceof ConnectionClosedException) {
            return Constants.ERROR_IO_ConnectionClosedException;
        }
        if (r1_IOException instanceof EOFException) {
            return Constants.ERROR_IO_EOFException;
        }
        if (r1_IOException instanceof FileLockInterruptionException) {
            return Constants.ERROR_IO_FileLockInterruptionException;
        }
        if (r1_IOException instanceof FileNotFoundException) {
            return Constants.ERROR_IO_FileNotFoundException;
        }
        if (r1_IOException instanceof HttpRetryException) {
            return Constants.ERROR_IO_HttpRetryException;
        }
        if (r1_IOException instanceof ConnectTimeoutException) {
            return Constants.ERROR_IO_InterruptedIOException_ConnectTimeoutException;
        }
        if (r1_IOException instanceof SocketTimeoutException) {
            return Constants.ERROR_SOCKETTIMEOUT;
        }
        if (r1_IOException instanceof InvalidPropertiesFormatException) {
            return Constants.ERROR_IO_InvalidPropertiesFormatException;
        }
        if (r1_IOException instanceof MalformedChunkCodingException) {
            return Constants.ERROR_IO_MalformedChunkCodingException;
        }
        if (r1_IOException instanceof MalformedURLException) {
            return Constants.ERROR_URL;
        }
        if (r1_IOException instanceof NoHttpResponseException) {
            return Constants.ERROR_IO_NoHttpResponseException;
        }
        if (r1_IOException instanceof InvalidClassException) {
            return Constants.ERROR_IO_ObjectStreamException_InvalidClassException;
        }
        if (r1_IOException instanceof InvalidObjectException) {
            return Constants.ERROR_IO_ObjectStreamException_InvalidObjectException;
        }
        if (r1_IOException instanceof NotActiveException) {
            return Constants.ERROR_IO_ObjectStreamException_NotActiveException;
        }
        if (r1_IOException instanceof NotSerializableException) {
            return Constants.ERROR_IO_ObjectStreamException_NotSerializableException;
        }
        if (r1_IOException instanceof OptionalDataException) {
            return Constants.ERROR_IO_ObjectStreamException_OptionalDataException;
        }
        if (r1_IOException instanceof StreamCorruptedException) {
            return Constants.ERROR_IO_ObjectStreamException_StreamCorruptedException;
        }
        if (r1_IOException instanceof WriteAbortedException) {
            return Constants.ERROR_IO_ObjectStreamException_WriteAbortedException;
        }
        if (r1_IOException instanceof ProtocolException) {
            return Constants.ERROR_IO_ProtocolException;
        }
        if (r1_IOException instanceof SSLHandshakeException) {
            return Constants.ERROR_IO_SSLException_SSLHandshakeException;
        }
        if (r1_IOException instanceof SSLKeyException) {
            return Constants.ERROR_IO_SSLException_SSLKeyException;
        }
        if (r1_IOException instanceof SSLPeerUnverifiedException) {
            return Constants.ERROR_IO_SSLException_SSLPeerUnverifiedException;
        }
        if (r1_IOException instanceof SSLProtocolException) {
            return Constants.ERROR_IO_SSLException_SSLProtocolException;
        }
        if (r1_IOException instanceof BindException) {
            return Constants.ERROR_IO_SocketException_BindException;
        }
        if (r1_IOException instanceof ConnectException) {
            return Constants.ERROR_IO_SocketException_ConnectException;
        }
        if (r1_IOException instanceof NoRouteToHostException) {
            return Constants.ERROR_IO_SocketException_NoRouteToHostException;
        }
        if (r1_IOException instanceof PortUnreachableException) {
            return Constants.ERROR_IO_SocketException_PortUnreachableException;
        }
        if (r1_IOException instanceof SyncFailedException) {
            return Constants.ERROR_IO_SyncFailedException;
        }
        if (r1_IOException instanceof UTFDataFormatException) {
            return Constants.ERROR_IO_UTFDataFormatException;
        }
        if (r1_IOException instanceof UnknownHostException) {
            return Constants.ERROR_IO_UnknownHostException;
        }
        if (r1_IOException instanceof UnknownServiceException) {
            return Constants.ERROR_IO_UnknownServiceException;
        }
        if (r1_IOException instanceof UnsupportedEncodingException) {
            return Constants.ERROR_IO_UnsupportedEncodingException;
        }
        if (r1_IOException instanceof ZipException) {
            return Constants.ERROR_IO_ZipException;
        }
        return RequestListener.DEFAULT_LOADED_SIZE;
    }

    private Bundle a(String r4_String, Bundle r5_Bundle) {
        if (r5_Bundle == null) {
            r5_Bundle = new Bundle();
        }
        r5_Bundle.putString("format", "json");
        r5_Bundle.putString("status_os", VERSION.RELEASE);
        r5_Bundle.putString("status_machine", Build.MODEL);
        r5_Bundle.putString("status_version", VERSION.SDK);
        r5_Bundle.putString("sdkv", Constants.SDK_VERSION);
        r5_Bundle.putString("sdkp", QsbkDatabase.A);
        if (this.a == null) {
            return r5_Bundle;
        }
        if (c()) {
            r5_Bundle.putString(ThirdParty.KEY_TOKEN, b());
        }
        if (!Constants.GRAPH_OPEN_ID.equals(r4_String)) {
            r5_Bundle.putString(Constants.PARAM_CONSUMER_KEY, this.a.d() + RContactStorage.PRIMARY_KEY);
            if (this.a.c() != null) {
                r5_Bundle.putString(Constants.PARAM_OPEN_ID, this.a.c() + RContactStorage.PRIMARY_KEY);
            }
        }
        r5_Bundle.putString("appid_for_getting_config", this.a.d() + RContactStorage.PRIMARY_KEY);
        try {
            r5_Bundle.putString(Constants.PARAM_PLATFORM_ID, this.a.f().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, "openmobile_android"));
        } catch (Exception e) {
            e.printStackTrace();
            r5_Bundle.putString(Constants.PARAM_PLATFORM_ID, "openmobile_android");
        }
        return r5_Bundle;
    }

    public static String a() {
        return Constants.SDK_VERSION_STRING;
    }

    private void a(String r5_String) {
        if (r5_String.indexOf("add_share") > -1 || r5_String.indexOf("upload_pic") > -1 || r5_String.indexOf("add_topic") > -1 || r5_String.indexOf("set_user_face") > -1 || r5_String.indexOf("add_t") > -1 || r5_String.indexOf("add_pic_t") > -1 || r5_String.indexOf("add_pic_url") > -1 || r5_String.indexOf("add_video") > -1) {
            TContext r0_TContext = this.a;
            String[] r2_StringA = new String[1];
            r2_StringA[0] = r5_String;
            TencentStat.a(r0_TContext, "requireApi", r2_StringA);
        }
    }

    private String b() {
        return this.a != null ? this.a.b() : null;
    }

    private boolean c() {
        return this.a != null && this.a.a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONObject a(Context r21_Context, String r22_String, Bundle r23_Bundle, String r24_String) {
        /*
        r20_this = this;
        r0 = r20;
        r1 = r22;
        r2 = r23;
        r16 = r0.a(r1, r2);
        r3 = r22.toLowerCase();
        r4 = "http";
        r3 = r3.startsWith(r4);
        if (r3 != 0) goto L_0x0227;
    L_0x0016:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = com.tencent.open.ServerSetting.getInstance();
        r5 = 6;
        r0 = r21;
        r4 = r4.getSettingUrl(r0, r5);
        r3 = r3.append(r4);
        r0 = r22;
        r3 = r3.append(r0);
        r3 = r3.toString();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = com.tencent.open.ServerSetting.getInstance();
        r6 = 6;
        r0 = r21;
        r5 = r5.getSettingUrl(r0, r6);
        r4 = r4.append(r5);
        r0 = r22;
        r4 = r4.append(r0);
        r5 = r4.toString();
    L_0x0052:
        r0 = r20;
        r1 = r22;
        r0.a(r1);
        r9 = 0;
        r7 = android.os.SystemClock.elapsedRealtime();
        r6 = 0;
        r0 = r20;
        r4 = r0.a;
        r4 = r4.d();
        r0 = r21;
        r4 = com.tencent.open.OpenConfig.a(r0, r4);
        r10 = "Common_HttpRetryCount";
        r4 = r4.b(r10);
        r10 = "OpenConfig_test";
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "config 1:Common_HttpRetryCount            config_value:";
        r11 = r11.append(r12);
        r11 = r11.append(r4);
        r12 = "   appid:";
        r11 = r11.append(r12);
        r0 = r20;
        r12 = r0.a;
        r12 = r12.d();
        r11 = r11.append(r12);
        r12 = "     url:";
        r11 = r11.append(r12);
        r11 = r11.append(r5);
        r11 = r11.toString();
        android.util.Log.d(r10, r11);
        if (r4 != 0) goto L_0x00aa;
    L_0x00a9:
        r4 = 3;
    L_0x00aa:
        r10 = "OpenConfig_test";
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "config 1:Common_HttpRetryCount            result_value:";
        r11 = r11.append(r12);
        r11 = r11.append(r4);
        r12 = "   appid:";
        r11 = r11.append(r12);
        r0 = r20;
        r12 = r0.a;
        r12 = r12.d();
        r11 = r11.append(r12);
        r12 = "     url:";
        r11 = r11.append(r12);
        r11 = r11.append(r5);
        r11 = r11.toString();
        android.util.Log.d(r10, r11);
        r17 = r6;
        r18 = r7;
        r6 = r18;
        r8 = r17;
    L_0x00e6:
        r13 = r8 + 1;
        r0 = r21;
        r1 = r24;
        r2 = r16;
        r10 = com.tencent.open.Util.a(r0, r3, r1, r2);	 //Catch:{ ConnectTimeoutException -> 0x021e, SocketTimeoutException -> 0x0219, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
        r8 = r10.a;	 //Catch:{ ConnectTimeoutException -> 0x021e, SocketTimeoutException -> 0x0219, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
        r14 = com.tencent.open.Util.d(r8);	 //Catch:{ ConnectTimeoutException -> 0x021e, SocketTimeoutException -> 0x0219, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
        r8 = "oauth2.0/m_me";
        r0 = r22;
        r8 = r8.equals(r0);	 //Catch:{ ConnectTimeoutException -> 0x0132, SocketTimeoutException -> 0x0162, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
        if (r8 == 0) goto L_0x0113;
    L_0x0102:
        if (r14 == 0) goto L_0x0113;
    L_0x0104:
        r8 = "openid";
        r8 = r14.getString(r8);	 //Catch:{ ConnectTimeoutException -> 0x0132, SocketTimeoutException -> 0x0162, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
        if (r8 == 0) goto L_0x0113;
    L_0x010c:
        r0 = r20;
        r9 = r0.a;	 //Catch:{ ConnectTimeoutException -> 0x0132, SocketTimeoutException -> 0x0162, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
        r9.a(r8);	 //Catch:{ ConnectTimeoutException -> 0x0132, SocketTimeoutException -> 0x0162, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
    L_0x0113:
        r8 = "ret";
        r12 = r14.getInt(r8);	 //Catch:{ JSONException -> 0x012f, ConnectTimeoutException -> 0x0132, SocketTimeoutException -> 0x0162, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de }
    L_0x0119:
        r8 = r10.b;	 //Catch:{ ConnectTimeoutException -> 0x0132, SocketTimeoutException -> 0x0162, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
        r10 = r10.c;	 //Catch:{ ConnectTimeoutException -> 0x0132, SocketTimeoutException -> 0x0162, HttpStatusException -> 0x018b, NetworkUnavailableException -> 0x01bd, MalformedURLException -> 0x01c2, IOException -> 0x01de, JSONException -> 0x01fd }
    L_0x011d:
        r3 = com.tencent.open.cgireport.ReportManager.a();
        r0 = r20;
        r4 = r0.a;
        r13 = r4.d();
        r4 = r21;
        r3.a(r4, r5, r6, r8, r10, r12, r13);
        return r14;
    L_0x012f:
        r8 = move-exception;
        r12 = -4;
        goto L_0x0119;
    L_0x0132:
        r8 = move-exception;
        r15 = r14;
        r14 = r8;
    L_0x0135:
        r14.printStackTrace();
        r12 = -7;
        r8 = 0;
        r10 = 0;
        if (r13 >= r4) goto L_0x0150;
    L_0x013f:
        r6 = android.os.SystemClock.elapsedRealtime();
        r17 = r8;
        r8 = r15;
        r14 = r17;
    L_0x0148:
        if (r13 < r4) goto L_0x0223;
    L_0x014a:
        r17 = r14;
        r14 = r8;
        r8 = r17;
        goto L_0x011d;
    L_0x0150:
        r3 = com.tencent.open.cgireport.ReportManager.a();
        r0 = r20;
        r4 = r0.a;
        r13 = r4.d();
        r4 = r21;
        r3.a(r4, r5, r6, r8, r10, r12, r13);
        throw r14;
    L_0x0162:
        r8 = move-exception;
        r15 = r14;
        r14 = r8;
    L_0x0165:
        r14.printStackTrace();
        r12 = -8;
        r8 = 0;
        r10 = 0;
        if (r13 >= r4) goto L_0x0179;
    L_0x016f:
        r6 = android.os.SystemClock.elapsedRealtime();
        r17 = r8;
        r8 = r15;
        r14 = r17;
        goto L_0x0148;
    L_0x0179:
        r3 = com.tencent.open.cgireport.ReportManager.a();
        r0 = r20;
        r4 = r0.a;
        r13 = r4.d();
        r4 = r21;
        r3.a(r4, r5, r6, r8, r10, r12, r13);
        throw r14;
    L_0x018b:
        r3 = move-exception;
        r14 = r3;
        r14.printStackTrace();
        r3 = r14.getMessage();
        r4 = "http status code error:";
        r8 = "";
        r3 = r3.replace(r4, r8);	 //Catch:{ Exception -> 0x01b6 }
        r12 = java.lang.Integer.parseInt(r3);	 //Catch:{ Exception -> 0x01b6 }
    L_0x01a0:
        r8 = 0;
        r10 = 0;
        r3 = com.tencent.open.cgireport.ReportManager.a();
        r0 = r20;
        r4 = r0.a;
        r13 = r4.d();
        r4 = r21;
        r3.a(r4, r5, r6, r8, r10, r12, r13);
        throw r14;
    L_0x01b6:
        r3 = move-exception;
        r3.printStackTrace();
        r12 = -9;
        goto L_0x01a0;
    L_0x01bd:
        r3 = move-exception;
        r3.printStackTrace();
        throw r3;
    L_0x01c2:
        r3 = move-exception;
        r14 = r3;
        r14.printStackTrace();
        r12 = -3;
        r8 = 0;
        r10 = 0;
        r3 = com.tencent.open.cgireport.ReportManager.a();
        r0 = r20;
        r4 = r0.a;
        r13 = r4.d();
        r4 = r21;
        r3.a(r4, r5, r6, r8, r10, r12, r13);
        throw r14;
    L_0x01de:
        r3 = move-exception;
        r14 = r3;
        r14.printStackTrace();
        r12 = a(r14);
        r8 = 0;
        r10 = 0;
        r3 = com.tencent.open.cgireport.ReportManager.a();
        r0 = r20;
        r4 = r0.a;
        r13 = r4.d();
        r4 = r21;
        r3.a(r4, r5, r6, r8, r10, r12, r13);
        throw r14;
    L_0x01fd:
        r3 = move-exception;
        r14 = r3;
        r14.printStackTrace();
        r12 = -4;
        r8 = 0;
        r10 = 0;
        r3 = com.tencent.open.cgireport.ReportManager.a();
        r0 = r20;
        r4 = r0.a;
        r13 = r4.d();
        r4 = r21;
        r3.a(r4, r5, r6, r8, r10, r12, r13);
        throw r14;
    L_0x0219:
        r8 = move-exception;
        r14 = r8;
        r15 = r9;
        goto L_0x0165;
    L_0x021e:
        r8 = move-exception;
        r14 = r8;
        r15 = r9;
        goto L_0x0135;
    L_0x0223:
        r9 = r8;
        r8 = r13;
        goto L_0x00e6;
    L_0x0227:
        r5 = r22;
        r3 = r22;
        goto L_0x0052;
        */

    }

    public void a(Context r9_Context, String r10_String, Bundle r11_Bundle, String r12_String, IRequestListener r13_IRequestListener, Object r14_Object) {
        new r(this, r9_Context, r10_String, r11_Bundle, r12_String, r13_IRequestListener, r14_Object).start();
    }

    public void a(IRequestListener r8_IRequestListener) {
        a(this.a.f(), "user/get_app_friends", null, HttpManager.HTTPMETHOD_GET, r8_IRequestListener, null);
    }
}