package com.tencent.open;

import android.os.Message;
import android.util.Log;
import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.IRequestListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
class n implements IRequestListener {
    final /* synthetic */ k a;

    public n(k r1_k) {
        this.a = r1_k;
    }

    public void onComplete(JSONObject r4_JSONObject, Object r5_Object) {
        Log.d("toddtest", r4_JSONObject.toString() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = 0;
        r0_Message.obj = r4_JSONObject;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onConnectTimeoutException(ConnectTimeoutException r4_ConnectTimeoutException, Object r5_Object) {
        Log.d("toddtest", r4_ConnectTimeoutException.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -7;
        r0_Message.obj = r4_ConnectTimeoutException.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onHttpStatusException(HttpStatusException r4_HttpStatusException, Object r5_Object) {
        Log.d("toddtest", r4_HttpStatusException.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -9;
        r0_Message.obj = r4_HttpStatusException.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onIOException(IOException r4_IOException, Object r5_Object) {
        Log.d("toddtest", r4_IOException.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -2;
        r0_Message.obj = r4_IOException.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onJSONException(JSONException r4_JSONException, Object r5_Object) {
        Log.d("toddtest", r4_JSONException.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -4;
        r0_Message.obj = r4_JSONException.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onMalformedURLException(MalformedURLException r4_MalformedURLException, Object r5_Object) {
        Log.d("toddtest", r4_MalformedURLException.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -3;
        r0_Message.obj = r4_MalformedURLException.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onNetworkUnavailableException(NetworkUnavailableException r4_NetworkUnavailableException, Object r5_Object) {
        Log.d("toddtest", r4_NetworkUnavailableException.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -2;
        r0_Message.obj = r4_NetworkUnavailableException.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onSocketTimeoutException(SocketTimeoutException r4_SocketTimeoutException, Object r5_Object) {
        Log.d("toddtest", r4_SocketTimeoutException.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -8;
        r0_Message.obj = r4_SocketTimeoutException.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }

    public void onUnknowException(Exception r4_Exception, Object r5_Object) {
        Log.d("toddtest", r4_Exception.getMessage() + RContactStorage.PRIMARY_KEY);
        Message r0_Message = new Message();
        r0_Message.what = -6;
        r0_Message.obj = r4_Exception.getMessage() + RContactStorage.PRIMARY_KEY;
        k.c(this.a).sendMessage(r0_Message);
    }
}