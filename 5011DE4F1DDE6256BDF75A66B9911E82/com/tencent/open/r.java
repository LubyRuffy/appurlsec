package com.tencent.open;

import android.content.Context;
import android.os.Bundle;
import com.tencent.tauth.IRequestListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
class r extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ Bundle c;
    final /* synthetic */ String d;
    final /* synthetic */ IRequestListener e;
    final /* synthetic */ Object f;
    final /* synthetic */ OpenApi g;

    r(OpenApi r1_OpenApi, Context r2_Context, String r3_String, Bundle r4_Bundle, String r5_String, IRequestListener r6_IRequestListener, Object r7_Object) {
        this.g = r1_OpenApi;
        this.a = r2_Context;
        this.b = r3_String;
        this.c = r4_Bundle;
        this.d = r5_String;
        this.e = r6_IRequestListener;
        this.f = r7_Object;
    }

    public void run() {
        try {
            JSONObject r0_JSONObject = this.g.a(this.a, this.b, this.c, this.d);
            if (this.e != null) {
                this.e.onComplete(r0_JSONObject, this.f);
            }
        } catch (MalformedURLException e) {
            MalformedURLException r0_MalformedURLException = e;
            if (this.e != null) {
                this.e.onMalformedURLException(r0_MalformedURLException, this.f);
            }
        } catch (ConnectTimeoutException e_2) {
            ConnectTimeoutException r0_ConnectTimeoutException = e_2;
            if (this.e != null) {
                this.e.onConnectTimeoutException(r0_ConnectTimeoutException, this.f);
            }
        } catch (SocketTimeoutException e_3) {
            SocketTimeoutException r0_SocketTimeoutException = e_3;
            if (this.e != null) {
                this.e.onSocketTimeoutException(r0_SocketTimeoutException, this.f);
            }
        } catch (NetworkUnavailableException e_4) {
            NetworkUnavailableException r0_NetworkUnavailableException = e_4;
            if (this.e != null) {
                this.e.onNetworkUnavailableException(r0_NetworkUnavailableException, this.f);
            }
        } catch (HttpStatusException e_5) {
            HttpStatusException r0_HttpStatusException = e_5;
            if (this.e != null) {
                this.e.onHttpStatusException(r0_HttpStatusException, this.f);
            }
        } catch (IOException e_6) {
            IOException r0_IOException = e_6;
            if (this.e != null) {
                this.e.onIOException(r0_IOException, this.f);
            }
        } catch (JSONException e_7) {
            JSONException r0_JSONException = e_7;
            if (this.e != null) {
                this.e.onJSONException(r0_JSONException, this.f);
            }
        } catch (Exception e_8) {
            Exception r0_Exception = e_8;
            if (this.e != null) {
                this.e.onUnknowException(r0_Exception, this.f);
            }
        }
    }
}