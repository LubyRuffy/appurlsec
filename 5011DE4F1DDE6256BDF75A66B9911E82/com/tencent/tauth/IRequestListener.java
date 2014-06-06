package com.tencent.tauth;

import com.tencent.open.HttpStatusException;
import com.tencent.open.NetworkUnavailableException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public interface IRequestListener {
    public void onComplete(JSONObject r1_JSONObject, Object r2_Object);

    public void onConnectTimeoutException(ConnectTimeoutException r1_ConnectTimeoutException, Object r2_Object);

    public void onHttpStatusException(HttpStatusException r1_HttpStatusException, Object r2_Object);

    public void onIOException(IOException r1_IOException, Object r2_Object);

    public void onJSONException(JSONException r1_JSONException, Object r2_Object);

    public void onMalformedURLException(MalformedURLException r1_MalformedURLException, Object r2_Object);

    public void onNetworkUnavailableException(NetworkUnavailableException r1_NetworkUnavailableException, Object r2_Object);

    public void onSocketTimeoutException(SocketTimeoutException r1_SocketTimeoutException, Object r2_Object);

    public void onUnknowException(Exception r1_Exception, Object r2_Object);
}